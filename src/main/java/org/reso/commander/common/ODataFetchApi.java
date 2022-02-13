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
import java.util.ArrayList;
import java.util.List;

public class ODataFetchApi {

  private static final Logger LOG = LogManager.getLogger(ODataFetchApi.class);

  private final static int TOP_COUNT = 100;
  private final static String FILTER_REQUEST_TEMPLATE_STRING = "?$filter=%s" + " lt %s&$orderby=%s desc";
  private final static String TOP_QUERY_PARAMETER = "&$top=" + TOP_COUNT;
  final static int DEFAULT_PAGE_SIZE = 100;


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
    if (container.getXMLMetadata().getSchemas().parallelStream()
        .anyMatch(item -> item.getEntityType(resourceName) != null)) {
      LOG.info("Replicating data from " + resourceName + " using strategy: " + strategy.toString());
      return replicateUsingTopAndSkip(container, resourceName);
    } else {
      throw new Exception(resourceName + " resource was not found in metadata!");
    }
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
