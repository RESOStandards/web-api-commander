package org.reso.commander.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.http.HttpClientException;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.reso.certification.containers.WebAPITestContainer;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ODataFetchApi {

  private static final Logger LOG = LogManager.getLogger(ODataFetchApi.class);

  final static int DEFAULT_PAGE_SIZE = 1000;

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
  public static List<Entity> replicateDataFromResource(WebAPITestContainer container, String resourceName, WebApiReplicationStrategy strategy)
      throws Exception {
    LOG.info("Checking metadata for resource: " + resourceName);
    if (container.getXMLMetadata().getSchemas().parallelStream()
        .anyMatch(item -> item.getEntityType(resourceName) != null)) {

      LOG.info("Replicating data from " + resourceName + " using strategy: " + strategy.toString());
      if (strategy == WebApiReplicationStrategy.TopAndSkip)
        return replicateUsingTopAndSkip(container, resourceName);

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
  private static List<Entity> replicateUsingTopAndSkip(WebAPITestContainer container, String resourceName) throws Exception {
    final ODataClient client = container.getCommander().getClient();
    final String serviceRoot = container.getServiceRoot();

    int pageSize = DEFAULT_PAGE_SIZE;
    final Integer resourceCount = ODataFetchApi.getResourceCount(container, resourceName);

    final ArrayList<Entity> entities = new ArrayList<>();
    try {
      for (int skipAmount = 0; pageSize > 0 && entities.size() <= resourceCount; skipAmount += pageSize) {
        final URI requestUri = client.newURIBuilder(serviceRoot).appendEntitySetSegment(resourceName).top(pageSize).skip(skipAmount).build();

        LOG.info("Fetching " + resourceName + " Resource data from URL: " + requestUri.toString());
        final ODataRetrieveResponse<ClientEntitySet> response = client.getRetrieveRequestFactory().getEntitySetRequest(requestUri).execute();

        if (response != null && response.getStatusCode() == HttpStatusCode.OK.getStatusCode()) {
          try {
            final InputStream rawData = response.getRawResponse();
            List<Entity> results = Optional.of(client.getDeserializer(ContentType.JSON).toEntitySet(rawData).getPayload().getEntities()).orElse(new ArrayList<>());
            pageSize = Optional.of(results.size()).orElse(0);

            if (pageSize > 0) {
              entities.addAll(results);
            }
          } catch (Exception ex) {
            LOG.error("Could not fetch results! Skipping..." + (ex.getMessage() != null ? "Exception: " + ex : "") + "\n");
          }
        }

        //sleep 1s between requests - drift is not an issue here as long as we always sleep at least 1s
        //noinspection BusyWait
        Thread.sleep(1000);
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
}