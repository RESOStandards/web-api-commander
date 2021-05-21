package org.reso.certification.stepdefs;

import com.google.common.collect.Sets;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.data.ResWrap;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.edm.EdmKeyPropertyRef;
import org.apache.olingo.commons.api.edm.EdmNamed;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.format.ContentType;
import org.reso.certification.codegen.DDCacheProcessor;
import org.reso.certification.codegen.DataDictionaryCodeGenerator;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.commander.common.DataDictionaryMetadata;
import org.reso.commander.common.Utils;
import org.reso.models.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static io.restassured.path.json.JsonPath.from;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;
import static org.reso.certification.containers.WebAPITestContainer.EMPTY_STRING;
import static org.reso.commander.Commander.NOT_OK;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;
import static org.reso.commander.common.TestUtils.failAndExitWithErrorMessage;

public class IDXPayload {
  private static final Logger LOG = LogManager.getLogger(IDXPayload.class);
  private static final String MODIFICATION_TIMESTAMP_FIELD = "ModificationTimestamp";
  private static final String POSTAL_CODE_FIELD = "PostalCode";
  private static final int TOP_COUNT = 100;

  private static final int MAX_RETRIES = 3;

  private static final String SAMPLES_DIRECTORY_ROOT = "build";
  private static final String SAMPLES_DIRECTORY_TEMPLATE = SAMPLES_DIRECTORY_ROOT + File.separator + "%s";
  private static final String PATH_TO_RESOSCRIPT_KEY = "pathToRESOScript";

  final String REQUEST_URI_TEMPLATE = "?$filter=%s" + " lt %s&$orderby=%s desc&$top=" + TOP_COUNT;
  final String COUNT_REQUEST_URI_TEMPLATE = "?$count=true";

  //TODO: get this from the parameters
  private final static boolean DEBUG = false;

  private static Scenario scenario;

  private final static AtomicBoolean hasStandardResources = new AtomicBoolean(false);
  private final static AtomicBoolean hasLocalResources = new AtomicBoolean(false);
  private final static AtomicReference<Set<String>> standardResources = new AtomicReference<>(new LinkedHashSet<>());
  private final static AtomicReference<Set<String>> localResources = new AtomicReference<>(new LinkedHashSet<>());
  private final static AtomicReference<WebAPITestContainer> container = new AtomicReference<>();
  private final static AtomicBoolean hasSamplesDirectoryBeenCleared = new AtomicBoolean(false);

  private final static AtomicReference<Map<String, List<PayloadSample>>> resourcePayloadSampleMap =
      new AtomicReference<>(Collections.synchronizedMap(new LinkedHashMap<>()));

  private final static AtomicReference<Map<String, List<ReferenceStandardField>>> standardFieldCache =
      new AtomicReference<>(Collections.synchronizedMap(new LinkedHashMap<>()));

  private final static AtomicReference<Map<String, Integer>> resourceCounts =
      new AtomicReference<>(Collections.synchronizedMap(new LinkedHashMap<>()));

  @Inject
  public IDXPayload(WebAPITestContainer c) {
    container.set(c);
  }

  @Before
  public void beforeStep(Scenario scenario) {
    final String pathToRESOScript = System.getProperty(PATH_TO_RESOSCRIPT_KEY, null);

    if (pathToRESOScript == null) return;

    IDXPayload.scenario = scenario;

    if (!container.get().getIsInitialized()) {
      container.get().setSettings(Settings.loadFromRESOScript(new File(System.getProperty(PATH_TO_RESOSCRIPT_KEY))));
      container.get().initialize();
    }
  }

  /**
   * Creates a data availability report for the given samples map
   * @param resourcePayloadSamplesMap the samples map to create the report from
   * @param reportName the name of the report
   */
  public void createDataAvailabilityReport(Map<String, List<PayloadSample>> resourcePayloadSamplesMap,
                                           String reportName, Map<String, Integer> resourceCounts) {
    PayloadSampleReport payloadSampleReport = new PayloadSampleReport(container.get().getEdm(), resourcePayloadSamplesMap, resourceCounts);
    GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
    gsonBuilder.registerTypeAdapter(PayloadSampleReport.class, payloadSampleReport);

    Utils.createFile(SAMPLES_DIRECTORY_ROOT, reportName, gsonBuilder.create().toJson(payloadSampleReport));
  }

  /**
   * Hashes the given values
   *
   * @param values items to hash, will be joined together, then hashed
   * @return the SHA hash of the given values
   */
  private static String hashValues(String... values) {
    return Hashing.sha256().hashString(String.join(EMPTY_STRING, values), StandardCharsets.UTF_8).toString();
  }

  /**
   * Builds a request URI string, taking into account whether the sampling is being done with an optional
   * filter, for instance in the shared systems case
   * @param resourceName the resource name to query
   * @param timestampField the timestamp field for the resource
   * @param lastFetchedDate the last fetched date for filtering
   * @return a string OData query used for sampling
   */
  private String buildODataTimestampRequestUriString(String resourceName, String timestampField, OffsetDateTime lastFetchedDate) {
    String requestUri = container.get().getCommander().getClient()
        .newURIBuilder(container.get().getServiceRoot())
        .appendEntitySetSegment(resourceName).build().toString();

    requestUri += String.format(REQUEST_URI_TEMPLATE, timestampField,
        lastFetchedDate.format(DateTimeFormatter.ISO_INSTANT), timestampField);

    return requestUri;
  }

  /**
   * Builds a request URI string for counting the number of available items on a resource, taking into account
   * whether the sample is being done with an optional filter, for instance in the shared system case
   * @param resourceName the resource name to query
   * @return a request URI string for getting OData counts
   */
  private String buildODataCountRequestUriString(String resourceName) {
    String requestUri = container.get().getCommander().getClient()
        .newURIBuilder(container.get().getServiceRoot())
        .appendEntitySetSegment(resourceName).build().toString();

    requestUri += COUNT_REQUEST_URI_TEMPLATE;

    return requestUri;
  }

  /**
   * Queries the server and fetches a resource count for the given resource name
   * @param resourceName the resource name to get the count for
   * @return the count found for the resource, or null if the request did not return a count
   */
  private Integer getResourceCount(String resourceName) {
    ODataTransportWrapper transportWrapper;
    String requestUri = buildODataCountRequestUriString(resourceName);
    Integer count = null;

    LOG.info("\n\nMaking count request to the " + resourceName + " resource: " + requestUri);
    transportWrapper = container.get().getCommander().executeODataGetRequest(requestUri);

    if (transportWrapper.getHttpResponseCode() == null || transportWrapper.getHttpResponseCode() != HttpStatus.SC_OK) {
      if (transportWrapper.getHttpResponseCode() == null) {
        scenario.log(getDefaultErrorMessage("Count request to", requestUri,
            "failed! No response code was provided. Check commander.log for any errors..."));
      } else {
        scenario.log(getDefaultErrorMessage("Count request to", requestUri,
            "failed with response code", transportWrapper.getHttpResponseCode().toString()));
      }
    } else {
      String oDataCountString = from(transportWrapper.getResponseData()).getString("\"@odata.count\"");
      count = oDataCountString != null && oDataCountString.trim().length() > 0 ? Integer.parseInt(oDataCountString) : 0;
      scenario.log("Total record count for the " + resourceName + " resource: " + oDataCountString);
    }
    return count;
  }

  /**
   * Fetches and processes records in cases where encoding the results is necessary
   *
   * @param resourceName                the resource name to sample from
   * @param targetRecordCount           the target record count to fetch (will stop before then if the end is reached)
   * @param encodedResultsDirectoryName the directory name for encoded results
   * @return a list of PayloadSample items
   */
  List<PayloadSample> fetchAndProcessRecords(String resourceName, int targetRecordCount, String encodedResultsDirectoryName) {
    final AtomicReference<OffsetDateTime> lastFetchedDate = new AtomicReference<>(OffsetDateTime.now());
    final List<String> timestampCandidateFields = new LinkedList<>();
    final AtomicReference<EdmEntityType> entityType = new AtomicReference<>();
    final AtomicReference<Map<String, String>> encodedSample = new AtomicReference<>(Collections.synchronizedMap(new LinkedHashMap<>()));
    final AtomicReference<ODataTransportWrapper> transportWrapper = new AtomicReference<>();
    final AtomicReference<ResWrap<EntityCollection>> entityCollectionResWrap = new AtomicReference<>();
    final AtomicReference<String> timestampField = new AtomicReference<>();
    final AtomicBoolean hasRecords = new AtomicBoolean(true);
    final AtomicReference<PayloadSample> payloadSample = new AtomicReference<>();
    final AtomicReference<List<PayloadSample>> payloadSamples =
        new AtomicReference<>(Collections.synchronizedList(new LinkedList<>()));

    boolean hasStandardTimestampField = false;
    String requestUri;
    int recordsProcessed = 0;
    int numRetries = 0;
    int lastTimestampCandidateIndex = 0;

    container.get().getEdm().getSchemas().forEach(edmSchema ->
        edmSchema.getEntityTypes().stream().filter(edmEntityType -> edmEntityType.getName().equals(resourceName))
            .findFirst().ifPresent(entityType::set));

    //return null if the entity type isn't defined
    if (entityType.get() == null) return new ArrayList<>();

    if (entityType.get().getProperty(MODIFICATION_TIMESTAMP_FIELD) == null) {
      scenario.log("Could not find " + MODIFICATION_TIMESTAMP_FIELD + " in the " + resourceName + " resource!\n");
      scenario.log("Searching for suitable timestamp fields...");

      entityType.get().getPropertyNames().forEach(propertyName -> {
        try {
          if (entityType.get().getProperty(propertyName).getType().getFullQualifiedName().getFullQualifiedNameAsString()
              .contentEquals(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName().getFullQualifiedNameAsString())) {
            scenario.log("Found Edm.DateTimeOffset field " + propertyName + " in the " + resourceName + " resource!\n");
            timestampCandidateFields.add(propertyName);
          }
        } catch (Exception ex) {
          LOG.error(ex);
        }
      });
    } else {
      hasStandardTimestampField = true;
    }

    final List<EdmKeyPropertyRef> keyFields = entityType.get().getKeyPropertyRefs();

    scenario.log("Sampling resource: " + resourceName);
    scenario.log("Keys found: " + keyFields.stream().map(EdmKeyPropertyRef::getName).collect(Collectors.joining(", ")));

    //loop and fetch records as long as items are available and we haven't reached our target count yet
    while (hasRecords.get() && recordsProcessed < targetRecordCount) {
      if (hasStandardTimestampField) {
        timestampField.set(MODIFICATION_TIMESTAMP_FIELD);
      } else if (timestampCandidateFields.size() > 0 && lastTimestampCandidateIndex < timestampCandidateFields.size()) {
        timestampField.set(timestampCandidateFields.get(lastTimestampCandidateIndex++));
      } else {
        scenario.log(getDefaultErrorMessage("Could not find a suitable timestamp field in the "
            + resourceName + " resource to sample with..."));

        //skip this resource since no suitable fields were found
        break;
      }

      payloadSample.set(new PayloadSample(resourceName, timestampField.get(),
          keyFields.stream().map(EdmKeyPropertyRef::getName).collect(Collectors.toList())));

      requestUri = buildODataTimestampRequestUriString(resourceName, timestampField.get(), lastFetchedDate.get());
      payloadSample.get().setRequestUri(requestUri);

      LOG.info("Making request to: " + requestUri);
      transportWrapper.set(container.get().getCommander().executeODataGetRequest(requestUri));

      // retries. sometimes requests can time out and fail and we don't want to stop sampling
      // immediately, but retry a couple of times before we bail
      if (recordsProcessed == 0 && transportWrapper.get().getResponseData() == null) {
        //only count retries if we're constantly making requests and not getting anything
        numRetries += 1;
      } else {
        numRetries = 0;
      }

      if (numRetries >= MAX_RETRIES) {
        if (timestampCandidateFields.size() > 0 && (lastTimestampCandidateIndex < timestampCandidateFields.size())) {
          LOG.info("Trying next candidate timestamp field: " + timestampCandidateFields.get(lastTimestampCandidateIndex));
          numRetries = 0;
        } else {
          LOG.info("Could not fetch records from the " + resourceName + " resource after " + MAX_RETRIES
              + " tries from the given URL: " + requestUri);
          break;
        }
      }

      if (transportWrapper.get().getHttpResponseCode() == null || transportWrapper.get().getHttpResponseCode() != HttpStatus.SC_OK) {
        if (transportWrapper.get().getHttpResponseCode() == null) {
          LOG.error(getDefaultErrorMessage("Request to", requestUri,
              "failed! No response code was provided. Check commander.log for any errors..."));
        } else {
          scenario.log(getDefaultErrorMessage("Request to", requestUri,
              "failed with response code", transportWrapper.get().getHttpResponseCode().toString()));
        }
        break;
      } else {
        LOG.info("Time taken: "
            + (transportWrapper.get().getElapsedTimeMillis() >= 1000 ? (transportWrapper.get().getElapsedTimeMillis() / 1000) + "s"
            : transportWrapper.get().getElapsedTimeMillis() + "ms"));

        try {
          payloadSample.get().setResponseSizeBytes(transportWrapper.get().getResponseData().getBytes().length);

          entityCollectionResWrap.set(container.get().getCommander().getClient()
              .getDeserializer(ContentType.APPLICATION_JSON)
              .toEntitySet(new ByteArrayInputStream(transportWrapper.get().getResponseData().getBytes())));

          if (entityCollectionResWrap.get().getPayload().getEntities().size() > 0) {
            LOG.info("Hashing " + resourceName + " payload values...");
            entityCollectionResWrap.get().getPayload().getEntities().forEach(entity -> {
              encodedSample.set(Collections.synchronizedMap(new LinkedHashMap<>()));

              entity.getProperties().forEach(property -> {
                //value will be considered null unless trimmed string or collection has non-zero length
                String value = property.getValue() != null
                    && ((property.isCollection() && property.asCollection().size() > 0)
                    || (property.isComplex() && property.asComplex().getValue().size() > 0)
                    || property.getValue().toString().trim().length() > 0
                    || property.isGeospatial() && property.asGeospatial() != null)
                    ? property.getValue().toString() : null;

                if (DEBUG) {
                  if (property.isCollection() && property.asCollection().size() > 0) {
                    LOG.info("Found Collection for field: " + property.getName() + ", value: " + property.asCollection());
                  }

                  if (property.isComplex() && property.asComplex().getValue().size() > 0) {
                    LOG.info("Found Complex Type for field: " + property.getName() + ", value: " + property.asComplex());
                  }

                  if (property.isEnum() && property.asEnum() != null) {
                    LOG.info("Found Enum for field" + property.getName() + ", value: " + property.asEnum());
                  }

                  if (property.isGeospatial() && property.asGeospatial() != null) {
                    LOG.info("Found Enum for field: " + property.getName() + ", value: " + property.asGeospatial());
                  }
                }

                //turn off hashing when DEBUG is true
                if (!DEBUG && value != null) {
                  if (!(property.getName().contentEquals(timestampField.get())
                      || property.getName().equals(POSTAL_CODE_FIELD)
                      || keyFields.stream().reduce(true, (acc, f) -> acc && f.getName().contentEquals(property.getName()), Boolean::logicalAnd))) {
                    value = hashValues(property.getValue().toString());
                  }
                }

                // TODO: clean up. If field is timestamp field or key field unmask, if field is null report null, otherwise hash value
                encodedSample.get().put(property.getName(), value);

                if (property.getName().contentEquals(MODIFICATION_TIMESTAMP_FIELD)) {
                  if (OffsetDateTime.parse(property.getValue().toString()).isBefore(lastFetchedDate.get())) {
                    lastFetchedDate.set(OffsetDateTime.parse(property.getValue().toString()));
                  }
                }
              });
              payloadSample.get().addSample(encodedSample.get());
            });
            LOG.info("Values encoded!");

            recordsProcessed += entityCollectionResWrap.get().getPayload().getEntities().size();
            LOG.info("Records processed: " + recordsProcessed + ". Target record count: " + targetRecordCount + "\n");

            payloadSample.get().setResponseTimeMillis(transportWrapper.get().getElapsedTimeMillis());

            if (encodedResultsDirectoryName != null) {
              payloadSample.get().setPayloadFields(standardFieldCache.get().get(resourceName).stream()
                  .map(ReferenceStandardField::getStandardName).collect(Collectors.toList()));

              //serialize results once resource processing has finished
              Utils.createFile(String.format(SAMPLES_DIRECTORY_TEMPLATE, encodedResultsDirectoryName),
                  resourceName + "-" + Utils.getTimestamp() + ".json",
                  payloadSample.get().serialize(payloadSample.get(), PayloadSample.class, null).toString());
            }

            payloadSamples.get().add(payloadSample.get());
          } else {
            scenario.log("All available records fetched! Total: " + recordsProcessed);
            hasRecords.set(false);
          }
        } catch (Exception ex) {
          scenario.log("Error in fetchAndProcessRecords: " + getDefaultErrorMessage(ex.toString()));
          scenario.log("Skipping sample...");
          lastFetchedDate.set(lastFetchedDate.get().minus(1, ChronoUnit.WEEKS));
        }
      }
    }
    return payloadSamples.get();
  }

  /**
   * fetches and processes records in cases where only sampling is required and encoding is not necessary
   *
   * @param resourceName      the resource name to sample from
   * @param targetRecordCount the target record count for the resource (will stop if the end of the records is reached)
   * @return a list of PayloadSample items
   */
  List<PayloadSample> fetchAndProcessRecords(String resourceName, int targetRecordCount) {
    return fetchAndProcessRecords(resourceName, targetRecordCount, null);
  }


  /*==================================== TESTS START HERE ====================================*/


  @Given("that valid metadata have been requested from the server")
  public void thatValidMetadataHaveBeenRequestedFromTheServer() {
    try {
      if (container.get().hasValidMetadata()) {
        if (standardFieldCache.get().size() == 0) {
          LOG.info("Creating standard field cache...");
          DDCacheProcessor cacheProcessor = new DDCacheProcessor();
          DataDictionaryCodeGenerator generator = new DataDictionaryCodeGenerator(cacheProcessor);
          generator.processWorksheets();
          standardFieldCache.get().putAll(cacheProcessor.getStandardFieldCache());
          LOG.info("Standard field cache created!");
        }
      } else {
        failAndExitWithErrorMessage("Valid metadata was not retrieved from the server. Exiting!", scenario);
      }
    } catch (Exception ex) {
      failAndExitWithErrorMessage(ex.toString(), scenario);
    }
  }

  @And("the metadata contains RESO Standard Resources")
  public void theMetadataContainsRESOStandardResources() {
    Set<String> resources = container.get().getEdm().getSchemas().stream().map(schema ->
      schema.getEntityTypes().stream().map(EdmNamed::getName)
          .collect(Collectors.toSet()))
        .flatMap(Collection::stream)
        .collect(Collectors.toSet());

    standardResources.set(resources.stream()
        .filter(DataDictionaryMetadata.v1_7.WELL_KNOWN_RESOURCES::contains).collect(Collectors.toSet()));
    localResources.set(Sets.difference(resources, standardResources.get()));
    hasStandardResources.set(standardResources.get().size() > 0);
    hasLocalResources.set(localResources.get().size() > 0);

    if (hasStandardResources.get()) {
      //TODO: add pluralizer
      scenario.log("Found " + standardResources.get().size() + " RESO Standard Resource"
          + (standardResources.get().size() == 1 ? "" : "s") + ": "
          + String.join(", ", standardResources.get()));
    } else {
      scenario.log("No RESO Standard Resources found. Skipping...");
      assumeTrue(true);
    }
  }

  @And("each resource MUST have a primary key field by the OData specification")
  public void eachResourceMUSTHaveAPrimaryKeyFieldByTheODataSpecification() {
    scenario.log("Each resource MUST have a primary key field by the OData specification!");
    assumeTrue(true);
  }

  @And("the metadata contains local resources")
  public void theMetadataContainsLocalResources() {
    if (localResources.get() == null || localResources.get().size() == 0) {
      scenario.log("No local resources found! Skipping...");
      assumeTrue(true);
    } else {
      scenario.log("Found " + localResources.get().size() + " Local Resource"
          + (localResources.get().size() == 1 ? "" : "s") + ": "
          + String.join(", ", localResources.get()));
    }
  }

  @Then("up to {int} records are sampled from each resource with {string} payload samples stored in {string}")
  public void upToRecordsAreSampledFromEachResourceWithPayloadSamplesStoredIn(int numRecords, String payloadName, String resultsDirectoryName) {
    assertNotNull(getDefaultErrorMessage("resultsDirectoryName MUST be present!"), resultsDirectoryName);

    if (!hasStandardResources.get()) {
      scenario.log("No RESO Standard Resources to sample!");
      assumeTrue(true);
    } else {
      Set<String> payloadResources = new LinkedHashSet<>();
      standardFieldCache.get().forEach((resourceName, fieldList) -> {
        if (!payloadResources.contains(resourceName) && fieldList.stream().anyMatch(field -> field.getPayloads().contains(payloadName))) {
          payloadResources.add(resourceName);
        }
      });

      standardResources.get().forEach(resourceName -> {
        resourceCounts.get().put(resourceName, getResourceCount(resourceName));
        resourcePayloadSampleMap.get().putIfAbsent(resourceName, Collections.synchronizedList(new LinkedList<>()));
        //only save results to the directory if the resources are part of the given payload
        resourcePayloadSampleMap.get().put(resourceName,
            fetchAndProcessRecords(resourceName, numRecords, payloadResources.contains(resourceName) ? resultsDirectoryName : null));
      });
    }
  }

  @Then("up to {int} records are sampled from each local resource")
  public void upToRecordsAreSampledFromEachLocalResource(int numRecords) {
    if (!hasLocalResources.get()) {
      scenario.log("No local resources were found to sample!");
      assumeTrue(true);
    } else {
      localResources.get().forEach(resourceName -> {
        resourceCounts.get().put(resourceName, getResourceCount(resourceName));
        resourcePayloadSampleMap.get().putIfAbsent(resourceName, Collections.synchronizedList(new LinkedList<>()));
        resourcePayloadSampleMap.get().put(resourceName, fetchAndProcessRecords(resourceName, numRecords, null));
      });
    }
  }

  @Given("samples exist in {string} in the build directory")
  public void samplesExistInInTheBuildDirectory(String resultsDirectory) {
    scenario.log("Samples exist in {string} in the build directory!");
    assumeTrue(true);
  }

  @And("a RESOScript file was provided for the IDX User")
  public void aRESOScriptFileWasProvidedForTheIDXUser() {
    scenario.log("!!TODO!! A RESOScript file was provided for the IDX User!");
    assumeTrue(true);
  }

  @When("samples from {string} are fetched as the representative user for each resource in the {string} payload")
  public void samplesFromAreFetchedAsTheRepresentativeUserForEachResourceInThePayload(String resultsDirectory, String payloadName) {
    File f = new File(SAMPLES_DIRECTORY_ROOT + File.separator + resultsDirectory);
    AtomicReference<PayloadSample> payloadSample = new AtomicReference<>();

    if (f.list() == null) return;

    Arrays.stream(Objects.requireNonNull(f.list((file, s) -> s.endsWith("json")))).forEach(sampleResults -> {
      try {
        final String jsonString = new String(Files.readAllBytes(Paths.get(f.getPath() + File.separator + sampleResults)));
        payloadSample.set(new Gson().fromJson(jsonString, PayloadSample.class));

      } catch (FileNotFoundException fnfEx) {
        LOG.error(getDefaultErrorMessage("file", sampleResults, "could not be found! Skipping..."));
        LOG.error("Exception: " + fnfEx);
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    });
  }

  @Then("each result MUST contain the string version of the key and the following fields")
  public void eachResultMUSTContainTheStringVersionOfTheKeyAndTheFollowingFields(List<String> requiredFields) {
    scenario.log("!!TODO!! Each result MUST contain the string version of the key and the following fields!");
    assumeTrue(true);
  }

  @And("the {string} payload field values MUST match those in the samples")
  public void thePayloadFieldValuesMUSTMatchThoseInTheSamples(String arg0) {
    scenario.log("!!TODO!! The {string} payload field values MUST match those in the samples!");
    assumeTrue(true);
  }

  @Given("standard and local resources have been processed")
  public void standardAndLocalResourcesHaveBeenProcessed() {
    scenario.log("!!TODO!! Standard and local resources have been processed!");
    assumeTrue(true);
  }

  @Then("a data availability report is created in {string}")
  public void aDataAvailabilityReportIsCreatedIn(String reportFileName) {
    if (resourcePayloadSampleMap.get() == null) {
      LOG.info("No resource payload samples found! Skipping...");
      assumeTrue(true);
    }
    LOG.info("\n\nCreating data availability report!");
    createDataAvailabilityReport(resourcePayloadSampleMap.get(), reportFileName, resourceCounts.get());
  }

  @And("{string} has been created in the build directory")
  public void hasBeenCreatedInTheBuildDirectory(String encodedResultsDirectoryName) {
    if (encodedResultsDirectoryName != null && !hasSamplesDirectoryBeenCleared.get()) {
      if (!Utils.removeDirectory(String.format(SAMPLES_DIRECTORY_TEMPLATE, encodedResultsDirectoryName))) {
        LOG.error("Failed to create runtime directories needed for program execution. Exiting...");
        System.exit(NOT_OK);
      } else {
        hasSamplesDirectoryBeenCleared.set(true);
      }
    }
  }
}
