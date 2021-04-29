package org.reso.certification.stepdefs;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.data.ResWrap;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.edm.EdmKeyPropertyRef;
import org.apache.olingo.commons.api.format.ContentType;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.commander.common.DataDictionaryMetadata;
import org.reso.models.ODataTransportWrapper;
import org.reso.models.PayloadSample;
import org.reso.models.Settings;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.google.common.hash.Hashing.sha256;
import static org.junit.Assume.assumeTrue;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;
import static org.reso.commander.common.TestUtils.failAndExitWithErrorMessage;

public class IDXPayload {
  private static final Logger LOG = LogManager.getLogger(IDXPayload.class);
  private static final CharSequence SEPARATOR_CHARACTER = "|";
  private static final CharSequence MODIFICATION_TIMESTAMP_FIELD = "ModificationTimestamp";
  private static Scenario scenario;

  private final static AtomicBoolean hasStandardResources = new AtomicBoolean(false);
  private final static AtomicReference<Set<String>> standardResources = new AtomicReference<>(new LinkedHashSet<>());
  private final static AtomicReference<Set<String>> nonStandardResources = new AtomicReference<>(new LinkedHashSet<>());

  private final static AtomicReference<WebAPITestContainer> container = new AtomicReference<>();

  private final static AtomicReference<Map<String, List<String>>> requests = new AtomicReference<>();

  @Before
  public void beforeStep(Scenario scenario) {
    final String pathToRESOScript = System.getProperty("pathToRESOScript", null);

    if (pathToRESOScript == null) return;

    IDXPayload.scenario = scenario;

    //TODO: share state between Web API background and IDX Payload Containers
    if (!container.get().getIsInitialized()) {
      container.get().setSettings(Settings.loadFromRESOScript(new File(System.getProperty("pathToRESOScript"))));
      container.get().initialize();
    }
  }

  @Inject
  public IDXPayload(WebAPITestContainer c) {
    container.set(c);
  }

  @Given("that metadata have been requested from the server")
  public void thatMetadataHaveBeenRequestedFromTheServer() {
    try {
      container.get().fetchXMLMetadata();
    } catch (Exception ex) {
      failAndExitWithErrorMessage(ex.toString(), scenario);
    }
  }

  @And("the metadata contains RESO Standard Resources")
  public void theMetadataContainsRESOStandardResources() {
    Set<String> resources = container.get().getEdm().getEntityContainer().getEntitySets().stream()
        .map(Object::toString)
        .collect(Collectors.toSet());

    standardResources.set(resources.stream()
        .filter(DataDictionaryMetadata.v1_7.WELL_KNOWN_RESOURCES::contains).collect(Collectors.toSet()));
    nonStandardResources.set(Sets.difference(resources, standardResources.get()));

    hasStandardResources.set(standardResources.get().size() > 0);

    if (hasStandardResources.get()) {
      scenario.log("Found " + standardResources.get().size() + " RESO Standard Resource" + (standardResources.get().size() == 1 ? "" : "s") + ": "
          + String.join(", ", standardResources.get()));
    } else {
      scenario.log("No RESO Standard Resources found. Skipping...");
      assumeTrue(true);
    }
  }

  public static String hashValues(String ...values) {
    return sha256().hashString(String.join(SEPARATOR_CHARACTER, values), StandardCharsets.UTF_8).toString();
  }

  @When("{int} records are sampled from each RESO Standard resource in the server metadata")
  public void recordsAreSampledFromEachRESOStandardResourceInTheServerMetadata(int numRecords) {
    if (!hasStandardResources.get()) {
      scenario.log("No RESO Standard Resources to sample");
      assumeTrue(true);
    } else {
     /*
        For each resource on the server
          1) read parameters from config
          2) generate k requests in order to sample n records
          3) run requests
          4) schema validation against metadata (when present)
          5) encode results (possibly serialize)
          6) generate reports
      */

      AtomicReference<PayloadSample> result = new AtomicReference<>();
      standardResources.get().parallelStream().forEach(resourceName -> {
        result.set(fetchAndProcessRecords(resourceName, numRecords));
      });
    }
  }

  PayloadSample fetchAndProcessRecords(String resourceName, int targetRecordCount) {
    final AtomicReference<ZonedDateTime> lastFetchedDate = new AtomicReference<>(ZonedDateTime.now()); //.minus(5, ChronoUnit.YEARS)
    final ZonedDateTime startDate = lastFetchedDate.get();

    ODataTransportWrapper transportWrapper;
    final String TIMESTAMP_STANDARD_FIELD = "ModificationTimestamp";

    if (container.get().getEdm().getEntityContainer().getEntitySet(resourceName).getEntityType().getProperty("ModificationTimestamp") == null)
      return null;

    //consider parameterizing the timestamp field and let applicant pass them in the config
    //all RESO Resources MUST have ModificationTimestamp or previous test steps will fail
    final String REQUEST_URI_TEMPLATE = "?$filter=" + TIMESTAMP_STANDARD_FIELD
        + " lt '%s'&$orderby=" + TIMESTAMP_STANDARD_FIELD + " desc";

    int recordsFetched;
    String requestUri;
    ResWrap<EntityCollection> entityCollectionResWrap = null;
    //Map of String Resource Names to Key-Mapped Hash of Field and SHA value
    final AtomicReference<Map<String, Map<String, List<Vector<String>>>>> encodedSamples = new AtomicReference<>(new LinkedHashMap<>());

    //assumes the 0th key is always used. TODO: determine if we need to scan the keys.
    final List<EdmKeyPropertyRef> keyFields = container.get().getEdm().getEntityContainer()
        .getEntitySet(resourceName).getEntityType().getKeyPropertyRefs();

    LOG.info("Keys found: " + keyFields.stream().map(EdmKeyPropertyRef::getName).collect(Collectors.joining(", ")));

    int recordsProcessed = 0;
    int numRetries = 0;
    do {
      requestUri = container.get().getCommander().getClient()
          .newURIBuilder(container.get().getServiceRoot())
          .appendEntitySetSegment(resourceName).build().toString()
          + String.format(REQUEST_URI_TEMPLATE, lastFetchedDate.get().format(DateTimeFormatter.ISO_INSTANT));

      LOG.info("Making request to: " + requestUri);
      transportWrapper = container.get().getCommander().executeODataGetRequest(requestUri);

      if (recordsProcessed == 0 && transportWrapper.getResponseData() == null) {
        numRetries += 1;
      } else {
        numRetries = 0;
      }

      if (numRetries >= 3) {
        failAndExitWithErrorMessage("Could not fetch records from the " + resourceName + " resource after 3 tries from the given URL: " + requestUri, scenario);
      }

      if (transportWrapper.getHttpResponseCode() != null && transportWrapper.getHttpResponseCode() != 200) {
        getDefaultErrorMessage("Request to", requestUri, "failed with response code", transportWrapper.getHttpResponseCode().toString());
        break;
      } else {
        //need to look at the records from the response and get the lowest timestamp
        try {
          entityCollectionResWrap = container.get().getCommander().getClient()
              .getDeserializer(ContentType.APPLICATION_JSON)
              .toEntitySet(new ByteArrayInputStream(transportWrapper.getResponseData().getBytes()));

          LOG.info("Time taken: "
              + (transportWrapper.getElapsedTimeMillis() >= 1000 ? (transportWrapper.getElapsedTimeMillis() / 1000) + "s" : transportWrapper.getElapsedTimeMillis() + "ms"));

          assert (keyFields.size() > 0) :
              getDefaultErrorMessage("no Key Fields found! Resources MUST have at least one key.");

          //we will always key the hash by the first key, the other key fields will still be there
          //for the MUST requirement checks
          final String keyField = keyFields.get(0).getName();
          LOG.info("Hashing " + resourceName + " payload values...");
          entityCollectionResWrap.getPayload().getEntities().forEach(entity -> {
            entity.getProperties().forEach(property -> {
              encodedSamples.get().computeIfAbsent(resourceName, k -> new LinkedHashMap<>());

              if (!encodedSamples.get().get(resourceName).containsKey(keyField)) {
                encodedSamples.get().get(resourceName).put(entity.getProperty(keyField).getValue().toString(), new LinkedList<>());
              }

              final Vector<String> fieldMeta = new Vector<>();
              fieldMeta.setSize(2);
              fieldMeta.set(0, property.getName());
              fieldMeta.set(1, property.getName().contentEquals(MODIFICATION_TIMESTAMP_FIELD) || keyFields.stream().reduce(false,
                  (acc, f) -> acc && f.getName().contentEquals(property.getName()), Boolean::logicalAnd)
                  ? property.getValue().toString() : (property.getValue() != null
                  ? hashValues(property.getValue().toString()) : null));

              encodedSamples.get().get(resourceName).get(entity.getProperty(keyField).getValue().toString()).add(fieldMeta);

              if (property.getName().contentEquals(MODIFICATION_TIMESTAMP_FIELD)) {
                if (ZonedDateTime.parse(property.getValue().toString()).isBefore(lastFetchedDate.get())) {
                  lastFetchedDate.set(ZonedDateTime.parse(property.getValue().toString()));
                }
              }
            });
          });
          LOG.info("Values encoded!");

          recordsProcessed += entityCollectionResWrap.getPayload().getEntities().size();

          LOG.info("Records processed: " + recordsProcessed + " of " + targetRecordCount + "\n");
        } catch (Exception ex) {
          getDefaultErrorMessage(ex.toString());
        }
      }

    } while (recordsProcessed < targetRecordCount);
    return null;
  }

  public void createDataAvailabilityReport(AtomicReference<Map<String, Map<String, List<Vector<String>>>>> encodedSamples) {
    AtomicReference<Map<String, Map<String, Integer>>> resourceTallies = new AtomicReference<>(new LinkedHashMap<>());
    encodedSamples.get().keySet().forEach(resourceName -> {
      LOG.debug("Processing resource: " + resourceName);
      LOG.debug("Sample size: " + encodedSamples.get().get(resourceName).keySet().size());
      //for each resource, go through the keys and tally the data presence counts for each field
      //as well as the number of samples in each case

      resourceTallies.get().put(resourceName, new LinkedHashMap<>());
      encodedSamples.get().get(resourceName).forEach((key, value) -> {
        if (value != null) {
          value.forEach(sample -> {
            if (sample != null) {
              //if element has a value
              if (sample.get(1) != null) {
                //if the value hasn't already been tallied, then just add one
                if (!resourceTallies.get().get(resourceName).containsKey(sample.get(0))) {
                  resourceTallies.get().get(resourceName).put(sample.get(0), 1);
                } else {
                  //otherwise, increment the current value
                  resourceTallies.get().get(resourceName).put(sample.get(0),
                      resourceTallies.get().get(resourceName).get(sample.get(0) + 1));
                }
              }
            }
          });
        }
      });
    });
    LOG.info(resourceTallies.get());
  }

  @When("{int} records are sampled from each non standard resource in the server metadata")
  public void recordsAreSampledFromEachNonStandardResourceInTheServerMetadata(int numRecords) {
  }

  @Then("each record MUST have the string version of the Primary Key and ModificationTimestamp field")
  public void eachRecordMUSTHaveTheStringVersionOfThePrimaryKeyAndModificationTimestampField() {
  }

  @And("the data MUST match what is advertised in the metadata")
  public void theDataMUSTMatchWhatIsAdvertisedInTheMetadata() {
  }
}
