package org.reso.certification.stepdefs;

import com.google.inject.Inject;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.ODataRawRequest;
import org.apache.olingo.client.api.communication.response.ODataRawResponse;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.domain.ClientPrimitiveValue;
import org.apache.olingo.client.api.http.HttpClientException;
import org.apache.olingo.client.api.serialization.ODataDeserializerException;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeException;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.commander.common.Utils;
import org.reso.models.Settings;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;
import static org.reso.commander.common.TestUtils.failAndExitWithErrorMessage;

public class LookupResource {
  private static final Logger LOG = LogManager.getLogger(LookupResource.class);
  private static Scenario scenario;
  private final static AtomicReference<WebAPITestContainer> container = new AtomicReference<>();
  private static final String PATH_TO_RESOSCRIPT_ARG = "pathToRESOScript";
  private static final AtomicReference<Map<String, List<ClientEntity>>> entityCache = new AtomicReference<>(new LinkedHashMap<>());


  @Inject
  public LookupResource(WebAPITestContainer c) {
    if (container.get() == null) {
      container.set(c);
    }
  }

  @Before
  public void beforeStep(Scenario scenario) {
    final String pathToRESOScript = System.getProperty(PATH_TO_RESOSCRIPT_ARG, null);

    LookupResource.scenario = scenario;

    if (pathToRESOScript != null && !container.get().getIsInitialized()) {
      container.get().setSettings(Settings.loadFromRESOScript(new File(System.getProperty(PATH_TO_RESOSCRIPT_ARG))));
      container.get().initialize();
    }
  }

  @Then("valid data is replicated from the {string} Resource")
  public void validDataIsReplicatedFromTheResource(String resourceName) {
    final ODataClient client = container.get().getCommander().getClient();
    final String serviceRoot = container.get().getServiceRoot();
    final int DEFAULT_PAGE_SIZE = 100;

    if (!entityCache.get().containsKey(resourceName)) {
      entityCache.get().put(resourceName, new ArrayList<>());

      try {
        int pageCount = DEFAULT_PAGE_SIZE, totalCount = 0;

        //get initial count
        URI requestUri = client.newURIBuilder(serviceRoot).count(true).top(1).appendEntitySetSegment(resourceName).build();
        ODataRetrieveResponse<ClientEntitySet> response = client.getRetrieveRequestFactory().getEntitySetRequest(requestUri).execute();

        if (response != null && response.getStatusCode() == HttpStatusCode.OK.getStatusCode() && response.getBody() != null) {
          totalCount = response.getBody().getCount();
          LOG.info("Total Count is: " + totalCount);
        }

        for (int skipAmount = 0; pageCount > 0 && entityCache.get().get(resourceName).size() <= totalCount; skipAmount += pageCount) {
          requestUri = client.newURIBuilder(serviceRoot).appendEntitySetSegment(resourceName).top(pageCount).skip(skipAmount).build();
          response = client.getRetrieveRequestFactory().getEntitySetRequest(requestUri).execute();

          LOG.info("Fetching " + resourceName + " Resource data from URL: " + requestUri.toString());

          if (response != null && response.getStatusCode() == HttpStatusCode.OK.getStatusCode() && response.getBody() != null) {
            pageCount = response.getBody().getEntities().size();
            if (pageCount > 0) {
              entityCache.get().get(resourceName).addAll(response.getBody().getEntities());
              LOG.info("Total records fetched: " + entityCache.get().get(resourceName).size());
            }
          }
        }
        scenario.log("Retrieved all available records from the " + resourceName + " Resource! Count: " + entityCache.get().get(resourceName).size());
      } catch (HttpClientException exception) {
        LOG.error("Could not retrieve data! " + exception.getMessage());
        LOG.error("Cause " + exception.getCause().getMessage());
        failAndExitWithErrorMessage("Unable to retrieve data from the Lookup Resource!", scenario);
      }
    }
  }

  @Then("each lookup name referenced by a field in the metadata has the term {string}")
  public void eachLookupNameReferencedByAFieldInTheMetadataHasTheTerm(String term) {
  }

  @And("data from the {string} Resource matches what is advertised in the server metadata")
  public void dataFromTheResourceMatchesWhatIsAdvertisedInTheServerMetadata(String resourceName) {
  }

  @Then("each LookupName referenced by a field in the metadata MUST have the term {string}")
  public void eachLookupNameReferencedByAFieldInTheMetadataMUSTHaveTheTerm(String annotationTerm) {
  }

  @Then("Lookup fields MUST have an annotation with term {string} and string value of LookupName")
  public void lookupFieldsMUSTHaveAnAnnotationWithTermAndStringValueOfLookupName(String arg0) {
  }

  @Then("{string} Resource data and metadata MUST contain the following fields")
  public void resourceDataAndMetadataMUSTContainTheFollowingFields(String resourceName, List<String> fields) {
    final String mandatoryFields = "'" + String.join(", ", fields) + "'";

    //check metadata
    scenario.log("Ensuring mandatory fields " + mandatoryFields + " are present in server metadata");
    assertTrue("The fields " + mandatoryFields + " MUST be present in the server metadata for the " + resourceName + " Resource!",
        container.get().getFieldMap(resourceName) != null && container.get().getFieldMap(resourceName).keySet().containsAll(fields));

    //check resource data cache
    scenario.log("Ensuring mandatory fields " + mandatoryFields + " are present in " + resourceName + " Resource data");
    entityCache.get().get(resourceName).forEach(clientEntity -> {
      fields.forEach(fieldName -> {
        if (clientEntity.getProperty(fieldName) == null || clientEntity.getProperty(fieldName).getValue() == null) {
          failAndExitWithErrorMessage("Missing required field in the " + resourceName + " Resource!", scenario);
        }
      });
    });
    scenario.log("All mandatory fields present!");
  }

  enum WebApiReplicationStrategy {
    ModificationTimestampDescending
  }

  private void replicateDataFromResource(String resourceName, WebApiReplicationStrategy strategy) {
    if (resourceName == null) failAndExitWithErrorMessage("No resourceName specified!", scenario);

    if (container.get().getXMLMetadata().getSchemas().parallelStream()
        .anyMatch(item -> item.getEntityType(resourceName) != null)) {
      scenario.log("TODO: replicating data from " + resourceName + " using strategy: " + strategy.toString());
    } else {
      failAndExitWithErrorMessage("Cannot retrieve data for the Lookup resource", scenario);
    }
  }

  @When("the {string} Resource exists in the metadata")
  public void theResourceExistsInTheMetadata(String resourceName) {
    boolean hasResource = container.get().getFieldMap(resourceName) != null;
    assumeTrue("The " + resourceName + " Resource was not found in the default entity container in the metadata!", hasResource);
    scenario.log("Found Lookup Resource!");
  }
}
