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
  private final static String FILTER_DESCENDING_TEMPLATE = "?$filter=%s" + " lt %s&$orderby=%s desc";
  private final static String FILTER_ASCENDING_INIT_TEMPLATE = "?$orderby=%s asc";
  private final static String FILTER_ASCENDING_TEMPLATE = "?$filter=%s" + " gt %s&$orderby=%s asc";
  private final static String TOP_QUERY_PARAMETER = "&$top=" + DEFAULT_PAGE_SIZE;
  final static String DEFAULT_TIMESTAMP_FIELD = "ModificationTimestamp";


  /***
   * Gets the total count for the given resource.
   * @param container the test container.
   * @param resourceName the name of the resource to get the count for.
   * @return the total available number of available records.
   */
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

  /**
   * Contains the replication strategies available for the fetch client replication methods.
   */
  public enum WebApiReplicationStrategy {
    ModificationTimestampDescending,
    ModificationTimestampAscending,
    TopAndSkip
  }

  /**
   * Replicates data using the given WebApiReplicationStrategy
   *
   * @param container    the test container
   * @param resourceName the name of the resource to replicate from
   * @param strategy     the replication strategy
   * @return a list of ClientEntity items that were replicated.
   * @throws Exception exceptions are thrown with messages so that the caller can respond and exit or continue,
   *                   as needed. Clients can use the included message for the reason for the error.
   */
  public static List<ClientEntity> replicateDataFromResource(WebAPITestContainer container, String resourceName, WebApiReplicationStrategy strategy)
      throws Exception {
    LOG.info("Checking metadata for resource: " + resourceName);
    if (container.getXMLMetadata().getSchemas().parallelStream()
        .anyMatch(item -> item.getEntityType(resourceName) != null)) {

      LOG.info("Replicating data from " + resourceName + " using strategy: " + strategy.toString());
      if (strategy == WebApiReplicationStrategy.TopAndSkip)
        return replicateUsingTopAndSkip(container, resourceName);

      if (strategy == WebApiReplicationStrategy.ModificationTimestampDescending)
        return replicateUsingModificationTimestampField(container, resourceName, WebApiReplicationStrategy.ModificationTimestampDescending);

      if (strategy == WebApiReplicationStrategy.ModificationTimestampAscending)
        return replicateUsingModificationTimestampField(container, resourceName, WebApiReplicationStrategy.ModificationTimestampAscending);

    } else {
      throw new Exception(resourceName + " resource was not found in metadata!");
    }
    return new ArrayList<>();
  }


  /**
   * Implementation of an OData client using a TopAndSkip replication strategy.
   *
   * @param container    the test container.
   * @param resourceName the name of the resource to replicate from.
   * @return a list of ClientEntity items that were replicated.
   * @throws Exception exceptions are thrown so that their messages can be used to fail or continue. Implementations
   *                   should bubble any relevant errors up.
   */
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
          }
        }
      }
    } catch (HttpClientException httpClientException) {
      final String message = "Could not retrieve data from the " + resourceName + " resource!" + httpClientException.getMessage();
      LOG.error(message);
      LOG.error("Cause " + httpClientException.getCause().getMessage());

      throw new Exception(message);
    }
    LOG.info("Total records fetched: " + entities.size());
    return entities;
  }

  /**
   * Default ModificationTimestamp replication client.
   *
   * @param container    the test container.
   * @param resourceName the name of the resource to replicate from.
   * @param strategy     the replication strategy, either desc or asc.
   * @return a list of ClientEntity items that were replicated.
   * @throws Exception exceptions are thrown so that their messages can be used to fail or continue. Implementations
   *                   should bubble any relevant errors up.
   */
  private static List<ClientEntity> replicateUsingModificationTimestampField(WebAPITestContainer container, String resourceName, WebApiReplicationStrategy strategy) throws Exception {
    return replicateUsingTimestampField(container, resourceName, DEFAULT_TIMESTAMP_FIELD, strategy);
  }


  /**
   * General timestamp replication client.
   *
   * @param container      the test container.
   * @param resourceName   the name of the resource to replicate from.
   * @param timestampField the name of the timestamp field to use for comparisons.
   * @param strategy       the replication strantegy, either asc or desc.
   * @return a list of ClientEntity items that were replicated.
   * @throws Exception exceptions are thrown so that their messages can be used to fail or continue. Implementations
   *                   should bubble any relevant errors up.
   */
  private static List<ClientEntity> replicateUsingTimestampField(WebAPITestContainer container, String resourceName, String timestampField, WebApiReplicationStrategy strategy) throws Exception {
    final ODataClient client = container.getCommander().getClient();
    final AtomicReference<OffsetDateTime> lastOffsetDateTime = new AtomicReference<>(OffsetDateTime.now());
    final int MAX_RETRIES = 3;
    final int RETRY_SKIP_MS = 1;
    int numRetries = 0;

    final Integer resourceCount = ODataFetchApi.getResourceCount(container, resourceName);
    final Set<ClientEntity> entities = new HashSet<>();
    boolean isInitialRequest = true;
    try {
      do {
        URI requestUri;
        if (strategy == WebApiReplicationStrategy.ModificationTimestampDescending) {
          requestUri = TestUtils.prepareUri(buildTimestampDescendingFilterRequestUri(container, resourceName, timestampField, lastOffsetDateTime.get()));
        } else if (strategy == WebApiReplicationStrategy.ModificationTimestampAscending) {
          if (isInitialRequest) {
            requestUri = TestUtils.prepareUri(buildTimestampAscendingInitFilterRequestUri(container, resourceName, timestampField));
          } else {
            requestUri = TestUtils.prepareUri(buildTimestampAscendingFilterRequestUri(container, resourceName, timestampField, lastOffsetDateTime.get()));
          }
        } else {
          throw new Exception("Unsupported WebApiReplicationStrategy: " + strategy);
        }

        LOG.info("Fetching " + resourceName + " Resource data from URL: " + requestUri.toString());
        final ODataRetrieveResponse<ClientEntitySet> response = client.getRetrieveRequestFactory().getEntitySetRequest(requestUri).execute();
        final List<ClientEntity> currentPage = response.getBody().getEntities();

        if (currentPage.size() == 0) {
          LOG.error("Page contained no records, exiting! Request URI: " + requestUri.toString());
          break;
        } else {
          for (ClientEntity clientEntity : currentPage) {
            try {
              if (entities.contains(clientEntity)) {
                LOG.error("Duplicate page detected!");
                LOG.error("Last Timestamp: " + lastOffsetDateTime.get().format(DateTimeFormatter.ISO_INSTANT));
                numRetries++;

                if (strategy == WebApiReplicationStrategy.ModificationTimestampDescending) {
                  LOG.error("\t--> Subtracting " + RETRY_SKIP_MS + "ms from last timestamp...");
                  lastOffsetDateTime.set(lastOffsetDateTime.get().minus(RETRY_SKIP_MS, ChronoUnit.MILLIS));
                } else {
                  LOG.error("\t--> Adding " + RETRY_SKIP_MS + "ms to last timestamp...");
                  lastOffsetDateTime.set(lastOffsetDateTime.get().plus(RETRY_SKIP_MS, ChronoUnit.MILLIS));
                }
                break;
              } else {
                entities.add(clientEntity);
                OffsetDateTime currentOffsetDateTime = OffsetDateTime.parse(clientEntity.getProperty(timestampField).getValue().toString());
                if (strategy == WebApiReplicationStrategy.ModificationTimestampDescending && currentOffsetDateTime.isBefore(lastOffsetDateTime.get())) {
                  LOG.debug("Current " + timestampField + " field timestamp is: " + currentOffsetDateTime.format(DateTimeFormatter.ISO_INSTANT));
                  LOG.debug("Found earlier timestamp! Last timestamp: " + lastOffsetDateTime.get().format(DateTimeFormatter.ISO_INSTANT) + "\n");
                  lastOffsetDateTime.set(currentOffsetDateTime);
                } else if (strategy == WebApiReplicationStrategy.ModificationTimestampAscending) {
                  if (!isInitialRequest && currentOffsetDateTime.isAfter(lastOffsetDateTime.get())) {
                    LOG.debug("Current " + timestampField + " field timestamp is: " + currentOffsetDateTime.format(DateTimeFormatter.ISO_INSTANT));
                    LOG.debug("Found later timestamp! Last timestamp: " + lastOffsetDateTime.get().format(DateTimeFormatter.ISO_INSTANT) + "\n");
                  }
                  lastOffsetDateTime.set(currentOffsetDateTime);
                }
              }
            } catch (DateTimeParseException exception) {
              LOG.error(exception);
              throw new Exception("Could not convert " + timestampField + " to timestamp value!");
            }
          }
        }
        isInitialRequest = false;
      } while (entities.size() <= resourceCount && numRetries < MAX_RETRIES);

      if (numRetries >= MAX_RETRIES) {
        LOG.warn("Exceeded maximum number of retries (" + MAX_RETRIES + ")! ");
      }

      if (entities.size() != resourceCount) {
        throw new Exception("Could not fetch all records!\n\tTotal Count: " + resourceCount + ". Records fetched: " + entities.size());
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
  public static String buildTimestampDescendingFilterRequestUri(WebAPITestContainer container, String resourceName,
                                                                String timestampField, OffsetDateTime lastFetchedDate) {
    String requestUri = container.getCommander().getClient()
        .newURIBuilder(container.getServiceRoot())
        .appendEntitySetSegment(resourceName).build().toString();

    requestUri += String.format(FILTER_DESCENDING_TEMPLATE + TOP_QUERY_PARAMETER, timestampField,
        lastFetchedDate.format(DateTimeFormatter.ISO_INSTANT), timestampField);

    return requestUri;
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
  public static String buildTimestampAscendingFilterRequestUri(WebAPITestContainer container, String resourceName,
                                                               String timestampField, OffsetDateTime lastFetchedDate) {
    String requestUri = container.getCommander().getClient()
        .newURIBuilder(container.getServiceRoot())
        .appendEntitySetSegment(resourceName).build().toString();

    requestUri += String.format(FILTER_ASCENDING_TEMPLATE + TOP_QUERY_PARAMETER, timestampField,
        lastFetchedDate.format(DateTimeFormatter.ISO_INSTANT), timestampField);

    return requestUri;
  }

  /**
   * Builds a request URI string, taking into account whether the sampling is being done with an optional
   * filter, for instance in the shared systems case
   *
   * @param resourceName    the resource name to query
   * @param timestampField  the timestamp field for the resource
   * @return a string OData query used for sampling
   */
  public static String buildTimestampAscendingInitFilterRequestUri(WebAPITestContainer container, String resourceName,
                                                                   String timestampField) {
    String requestUri = container.getCommander().getClient()
        .newURIBuilder(container.getServiceRoot())
        .appendEntitySetSegment(resourceName).build().toString();

    requestUri += String.format(FILTER_ASCENDING_INIT_TEMPLATE + TOP_QUERY_PARAMETER, timestampField);

    return requestUri;
  }
}
