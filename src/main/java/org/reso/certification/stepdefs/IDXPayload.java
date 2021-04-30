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
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.edm.EdmKeyPropertyRef;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.format.ContentType;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.commander.common.DataDictionaryMetadata;
import org.reso.commander.common.Utils;
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

  @Inject
  public IDXPayload(WebAPITestContainer c) {
    container.set(c);
  }

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

  public static String hashValues(String... values) {
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

      //TODO: decide whether to store in memory or serialize resource samples files upon completion
      AtomicReference<Map<String, List<PayloadSample>>> resourcePayloadSamplesMap = new AtomicReference<>(new LinkedHashMap<>());
      standardResources.get().stream().parallel().forEach(resourceName -> {
        resourcePayloadSamplesMap.get().putIfAbsent(resourceName, new LinkedList<>());
        resourcePayloadSamplesMap.get().put(resourceName, fetchAndProcessRecords(resourceName, numRecords));
      });

      createDataAvailabilityReport(resourcePayloadSamplesMap.get());
    }
  }

  List<PayloadSample> fetchAndProcessRecords(String resourceName, int targetRecordCount) {
    final String TIMESTAMP_STANDARD_FIELD = "ModificationTimestamp";
    final AtomicReference<ZonedDateTime> lastFetchedDate = new AtomicReference<>(ZonedDateTime.now());
    final List<String> timestampCandidateFields = new LinkedList<>();
    final EdmEntityType entityType = container.get().getEdm().getEntityContainer().getEntitySet(resourceName).getEntityType();

    ODataTransportWrapper transportWrapper;
    boolean hasStandardTimestampField = false;

    if (entityType.getProperty(TIMESTAMP_STANDARD_FIELD) == null) {
      LOG.info("Could not find " + MODIFICATION_TIMESTAMP_FIELD + " in the " + resourceName + " resource!");
      LOG.info("Searching for suitable timestamp fields...");

      entityType.getPropertyNames().forEach(propertyName -> {
        if (entityType.getProperty(propertyName).getType().getFullQualifiedName().getFullQualifiedNameAsString()
            .contentEquals(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName().getFullQualifiedNameAsString())) {
          LOG.info("Found Edm.DateTimeOffset field " + propertyName + " in the " + resourceName + " resource!");
          timestampCandidateFields.add(propertyName);
        }
      });
    } else {
      hasStandardTimestampField = true;
    }

    //all RESO Resources MUST have ModificationTimestamp or previous test steps will fail
    //TODO: need to add UOI for shared systems
    final String REQUEST_URI_TEMPLATE = "?$filter=%s" + " lt %s&$orderby=%s desc&$top=100";

    String requestUri;
    ResWrap<EntityCollection> entityCollectionResWrap;

    final AtomicReference<Map<String, String>> encodedSample = new AtomicReference<>();

    //assumes the 0th key is always used. TODO: determine if we need to scan the keys.
    final List<EdmKeyPropertyRef> keyFields = container.get().getEdm().getEntityContainer()
        .getEntitySet(resourceName).getEntityType().getKeyPropertyRefs();

    LOG.info("Keys found: " + keyFields.stream().map(EdmKeyPropertyRef::getName).collect(Collectors.joining(", ")));

    int recordsProcessed = 0;
    int numRetries = 0;
    int lastTimestampCandidateIndex = 0;
    String timestampField;

    final int MAX_RETRIES = 3;

    final AtomicBoolean noMoreRecords = new AtomicBoolean(false);
    final AtomicReference<PayloadSample> payloadSample = new AtomicReference<>();
    final AtomicReference<List<PayloadSample>> payloadSamples = new AtomicReference<>(new LinkedList<>());

    do {
      if (hasStandardTimestampField) {
        timestampField = TIMESTAMP_STANDARD_FIELD;
      } else if (timestampCandidateFields.size() > 0 && lastTimestampCandidateIndex < timestampCandidateFields.size()) {
        timestampField = timestampCandidateFields.get(lastTimestampCandidateIndex++);
      } else {
        scenario.log("ERROR: Could not find a suitable timestamp field in the " + resourceName + " resource to sample with...");
        return null;
      }

      requestUri = container.get().getCommander().getClient()
          .newURIBuilder(container.get().getServiceRoot())
          .appendEntitySetSegment(resourceName).build().toString()
          + String.format(REQUEST_URI_TEMPLATE, timestampField, lastFetchedDate.get().format(DateTimeFormatter.ISO_INSTANT), timestampField);


      payloadSample.set(new PayloadSample(resourceName, timestampField, keyFields));
      payloadSample.get().setRequestUri(requestUri);

      LOG.info("Making request to: " + requestUri);
      transportWrapper = container.get().getCommander().executeODataGetRequest(requestUri);

      // retries. sometimes requests can time out and fail and we don't want to stop sampling
      // immediately, but retry a couple of times before we bail
      if (recordsProcessed == 0 && transportWrapper.getResponseData() == null) {
        //only count retries if we're constantly making requests and not getting anything
        numRetries += 1;
      } else {
        numRetries = 0;
      }

      if (numRetries >= MAX_RETRIES) {
        if (timestampCandidateFields.size() > 0 && (lastTimestampCandidateIndex < timestampCandidateFields.size())) {
          scenario.log("Trying next candidate timestamp field: " + timestampCandidateFields.get(lastTimestampCandidateIndex));
          numRetries = 0;
        } else {
          scenario.log("Could not fetch records from the " + resourceName + " resource after " + MAX_RETRIES
              + " tries from the given URL: " + requestUri);
          break;
        }
      }

      if (transportWrapper.getHttpResponseCode() == null || transportWrapper.getHttpResponseCode() != 200) {
        if (transportWrapper.getHttpResponseCode() == null) {
          getDefaultErrorMessage("Request to", requestUri, "failed! No response code was provided. Check commander.log for any errors...");
        } else {
          getDefaultErrorMessage("Request to", requestUri, "failed with response code", transportWrapper.getHttpResponseCode().toString());
        }
        break;
      } else {
        //need to look at the records from the response and get the lowest timestamp
        try {
          entityCollectionResWrap = container.get().getCommander().getClient()
              .getDeserializer(ContentType.APPLICATION_JSON)
              .toEntitySet(new ByteArrayInputStream(transportWrapper.getResponseData().getBytes()));

          LOG.info("Time taken: "
              + (transportWrapper.getElapsedTimeMillis() >= 1000 ? (transportWrapper.getElapsedTimeMillis() / 1000) + "s" : transportWrapper.getElapsedTimeMillis() + "ms"));

          if (entityCollectionResWrap.getPayload().getEntities().size() > 0) {
            assert (keyFields.size() > 0) :
                getDefaultErrorMessage("no Key Fields found! Resources MUST have at least one key.");

            final String keyField = keyFields.get(0).getName();
            LOG.info("Hashing " + resourceName + " payload values...");
            entityCollectionResWrap.getPayload().getEntities().forEach(entity -> {
              encodedSample.set(new LinkedHashMap<>());
              entity.getProperties().forEach(property -> {
                // TODO: clean up. If field is timestamp field or key field unmask, if field is null report null, otherwise hash value
                encodedSample.get().put(property.getName(),
                    property.getName().contentEquals(MODIFICATION_TIMESTAMP_FIELD) || keyFields.stream().reduce(false,
                        (acc, f) -> acc && f.getName().contentEquals(property.getName()), Boolean::logicalAnd)
                        ? property.getValue().toString() : (property.getValue() != null
                        ? hashValues(property.getValue().toString()) : null));

                if (property.getName().contentEquals(MODIFICATION_TIMESTAMP_FIELD)) {
                  if (ZonedDateTime.parse(property.getValue().toString()).isBefore(lastFetchedDate.get())) {
                    lastFetchedDate.set(ZonedDateTime.parse(property.getValue().toString()));
                  }
                }
              });
              payloadSample.get().addSample(encodedSample.get());
            });
            LOG.info("Values encoded!");
            recordsProcessed += entityCollectionResWrap.getPayload().getEntities().size();
            LOG.info("Records processed: " + recordsProcessed + ". Target record count: " + targetRecordCount + "\n");
            payloadSample.get().setResponseTimeMillis(transportWrapper.getElapsedTimeMillis());
            payloadSamples.get().add(payloadSample.get());
          } else {
            LOG.info("All available records fetched! Total: " + recordsProcessed);
            noMoreRecords.set(true);
          }
        } catch (Exception ex) {
          LOG.error(getDefaultErrorMessage(ex.toString()));
        }
      }
    } while (!noMoreRecords.get() && recordsProcessed < targetRecordCount);
    return payloadSamples.get();
  }

  public void createDataAvailabilityReport(Map<String, List<PayloadSample>> resourcePayloadSamplesMap) {
    AtomicReference<Map<String, Map<String, Integer>>> resourceTallies = new AtomicReference<>(new LinkedHashMap<>());
    resourcePayloadSamplesMap.keySet().forEach(resourceName -> {
      LOG.debug("Processing resource: " + resourceName);
      LOG.debug("Sample size: " + resourcePayloadSamplesMap.get(resourceName).size());
      //for each resource, go through the keys and tally the data presence counts for each field
      //as well as the number of samples in each case
      resourceTallies.get().putIfAbsent(resourceName, new LinkedHashMap<>());
      resourcePayloadSamplesMap.get(resourceName).forEach(payloadSample -> {
        payloadSample.getSamples().forEach(sample -> {
          sample.forEach((fieldName, encodedValue) -> {
            if (encodedValue != null) {
              resourceTallies.get().get(resourceName).putIfAbsent(fieldName, 0);
              resourceTallies.get().get(resourceName).put(fieldName, resourceTallies.get().get(resourceName).get(fieldName) + 1);
            }
          });
        });
      });
    });
    Utils.createFile("build", "availability-report.txt", resourceTallies.get().toString());
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
