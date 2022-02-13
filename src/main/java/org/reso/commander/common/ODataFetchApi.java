package org.reso.commander.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.http.HttpClientException;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.reso.certification.containers.WebAPITestContainer;

import java.net.URI;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class ODataFetchApi {

  private static final Logger LOG = LogManager.getLogger(ODataFetchApi.class);

  final static int DEFAULT_PAGE_SIZE = 1000;
  private final static String FILTER_REQUEST_TEMPLATE_STRING = "?$filter=%s" + " lt %s&$orderby=%s desc";
  private final static String TOP_QUERY_PARAMETER = "&$top=" + DEFAULT_PAGE_SIZE;
  final static String DEFAULT_TIMESTAMP_FIELD = "ModificationTimestamp";


  public static Integer getResourceCount(WebAPITestContainer container, String resourceName) {
    final ODataClient client = container.getCommander().getClient();
    client.getConfiguration().setDefaultPubFormat(ContentType.APPLICATION_JSON);

    ODataEntitySetRequest<ClientEntitySet> request = client.getRetrieveRequestFactory()
        .getEntitySetRequest(client.newURIBuilder(container.getServiceRoot())
            .appendEntitySetSegment(resourceName).count(true).top(1).build());

    final ODataRetrieveResponse<ClientEntitySet> response = request.execute();
    Integer count = 0;

    if (response != null && response.getStatusCode() == HttpStatusCode.OK.getStatusCode() && response.getBody() != null) {
      count = response.getBody().getCount();
      LOG.info("Total Count is: " + count);
    } else {
      LOG.debug("Couldn't get count! Returning 0.");
    }

    return count;
  }

  public enum WebApiReplicationStrategy {
    ModificationTimestampDescending,
    TopAndSkip
  }

  public static List<ClientEntity> replicateDataFromResource(WebAPITestContainer container, String resourceName, WebApiReplicationStrategy strategy)
      throws Exception {
    LOG.info("Checking metadata for resource: " + resourceName);
    if (container.getXMLMetadata().getSchemas().parallelStream()
        .anyMatch(item -> item.getEntityType(resourceName) != null)) {

      LOG.info("Replicating data from " + resourceName + " using strategy: " + strategy.toString());
      if (strategy == WebApiReplicationStrategy.TopAndSkip)
        return replicateUsingTopAndSkip(container, resourceName);

      if (strategy == WebApiReplicationStrategy.ModificationTimestampDescending)
        return replicateUsingModificationTimestampField(container, resourceName);

    } else {
      throw new Exception(resourceName + " resource was not found in metadata!");
    }
    return new ArrayList<>();
  }


  private static List<ClientEntity> replicateUsingTopAndSkip(WebAPITestContainer container, String resourceName) throws Exception {
    final ODataClient client = container.getCommander().getClient();
    final String serviceRoot = container.getServiceRoot();

    int pageSize = DEFAULT_PAGE_SIZE;
    final Integer resourceCount = ODataFetchApi.getResourceCount(container, resourceName);

    final ArrayList<ClientEntity> entities = new ArrayList<>();
    try {
      for (int skipAmount = 0; pageSize > 0 && entities.size() <= resourceCount; skipAmount += pageSize) {
        final URI requestUri = client.newURIBuilder(serviceRoot).appendEntitySetSegment(resourceName).top(pageSize).skip(skipAmount).build();
        final ODataRetrieveResponse<ClientEntitySet> response = client.getRetrieveRequestFactory().getEntitySetRequest(requestUri).execute();

        LOG.info("Fetching " + resourceName + " Resource data from URL: " + requestUri.toString());

        if (response != null && response.getStatusCode() == HttpStatusCode.OK.getStatusCode() && response.getBody() != null) {
          pageSize = response.getBody().getEntities().size();
          if (pageSize > 0) {
            entities.addAll(response.getBody().getEntities());
            LOG.info("Total records fetched: " + entities.size());
          }
        }
      }
    } catch (HttpClientException httpClientException) {
      final String message = "Could not retrieve data from the " + resourceName + " resource!" + httpClientException.getMessage();
      LOG.error(message);
      LOG.error("Cause " + httpClientException.getCause().getMessage());

      throw new Exception(message);
    }
    return entities;
  }

  private static List<ClientEntity> replicateUsingModificationTimestampField(WebAPITestContainer container, String resourceName) throws Exception {
    return replicateUsingTimestampField(container, resourceName, DEFAULT_TIMESTAMP_FIELD);
  }

  private static List<ClientEntity> replicateUsingTimestampField(WebAPITestContainer container, String resourceName, String timestampField) throws Exception {
    final ODataClient client = container.getCommander().getClient();
    final AtomicReference<OffsetDateTime> lastOffsetDateTime = new AtomicReference<>(OffsetDateTime.now());
    final int MAX_RETRIES = 3;
    final int RETRY_SKIP_MS = 1;
    int numRetries = 0;

    final Integer resourceCount = ODataFetchApi.getResourceCount(container, resourceName);
    final Set<ClientEntity> entities = new HashSet<>();

    try {

      do {
        URI requestUri = TestUtils.prepareUri(buildODataTimestampRequestUriString(container, resourceName, timestampField, lastOffsetDateTime.get()));
        LOG.info("Fetching " + resourceName + " Resource data from URL: " + requestUri.toString());
        final ODataRetrieveResponse<ClientEntitySet> response = client.getRetrieveRequestFactory().getEntitySetRequest(requestUri).execute();
        final List<ClientEntity> currentPage = response.getBody().getEntities();

        if (currentPage.size() == 0) {
          LOG.error("Page contained no records! Request URI: " + requestUri.toString());
          LOG.error("\t--> Subtracting " + RETRY_SKIP_MS + " ms from the current time...");
          lastOffsetDateTime.set(lastOffsetDateTime.get().minus(RETRY_SKIP_MS, ChronoUnit.MILLIS));
          numRetries++;
        } else {
          for (ClientEntity clientEntity : currentPage) {
            try {
              if (entities.contains(clientEntity)) {
                LOG.error("Duplicate page detected!");
                LOG.error("Last Timestamp: " + lastOffsetDateTime.get().format(DateTimeFormatter.ISO_INSTANT));
                numRetries++;

                LOG.error("\t--> Subtracting " + RETRY_SKIP_MS + " ms from the current time...");
                lastOffsetDateTime.set(lastOffsetDateTime.get().minus(RETRY_SKIP_MS, ChronoUnit.MILLIS));
              } else {
                entities.add(clientEntity);
                OffsetDateTime currentOffsetDateTime = OffsetDateTime.parse(clientEntity.getProperty(timestampField).getValue().toString());
                if (currentOffsetDateTime.isBefore(lastOffsetDateTime.get())) {
                  LOG.debug("Current " + timestampField + " field timestamp is: " + currentOffsetDateTime.format(DateTimeFormatter.ISO_INSTANT));
                  LOG.debug("Found earlier timestamp! Last timestamp: " + lastOffsetDateTime.get().format(DateTimeFormatter.ISO_INSTANT) + "\n");
                  lastOffsetDateTime.set(currentOffsetDateTime);
                }
              }
            } catch (DateTimeParseException exception) {
              LOG.error(exception);
              throw new Exception("Could not convert ModificationTimestamp to timestamp value!");
            }
          }
        }
      } while (entities.size() <= resourceCount && numRetries < MAX_RETRIES);

      if (numRetries >= MAX_RETRIES) {
        LOG.warn("Exceeded maximum number of retries (" + MAX_RETRIES + ")! ");
      }

      if (entities.size() != resourceCount) {
        LOG.error("Could not fetch all records!");
        throw new Exception("Could not fetch all records! Total Count: " + resourceCount + ". Records fetched: " + entities.size());
      }

      LOG.info("Records fetched: " + entities.size());

    } catch (HttpClientException httpClientException) {
      final String message = "Could not retrieve data from the " + resourceName + " resource!" + httpClientException.getMessage();
      LOG.error(message);
      LOG.error("Cause " + httpClientException.getCause().getMessage());

      throw new Exception(message);
    }
    return new ArrayList<>(entities);
  }


  /**
   * Builds a request URI string, taking into account whether the sampling is being done with an optional
   * filter, for instance in the shared systems case
   *
   * @param resourceName    the resource name to query
   * @param timestampField  the timestamp field for the resource
   * @param lastFetchedDate the last fetched date for filtering
   * @return a string OData query used for sampling
   */
  public static String buildODataTimestampRequestUriString(WebAPITestContainer container, String resourceName,
                                                           String timestampField, OffsetDateTime lastFetchedDate) {
    String requestUri = container.getCommander().getClient()
        .newURIBuilder(container.getServiceRoot())
        .appendEntitySetSegment(resourceName).build().toString();

    requestUri += String.format(FILTER_REQUEST_TEMPLATE_STRING + TOP_QUERY_PARAMETER, timestampField,
        lastFetchedDate.format(DateTimeFormatter.ISO_INSTANT), timestampField);

    return requestUri;
  }
}
