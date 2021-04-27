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
import org.reso.commander.Commander;
import org.reso.commander.common.DataDictionaryMetadata;
import org.reso.models.Settings;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.google.common.hash.Hashing.sha256;
import static junit.framework.TestCase.assertNotNull;
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
      final AtomicReference<ResWrap<EntityCollection>> entitySet = new AtomicReference<>();

      //Map of String Resource Names to Key-Mapped Hash of Field and SHA value
      final AtomicReference<Map<String, Map<String, List<Vector<String>>>>> encodedSamples = new AtomicReference<>(new LinkedHashMap<>());

      standardResources.get().forEach(resourceName -> {
        //create placeholder hashmap for this resource name
        encodedSamples.get().put(resourceName, new LinkedHashMap<>());

        try {
          final URI uri = Commander.prepareURI(container.get().getCommander().getClient().newURIBuilder(
                  container.get().getServiceRoot()).appendEntitySetSegment(resourceName).build().toString());

          container.get().setRequestUri(uri);

          assertNotNull(getDefaultErrorMessage("Request URI was null!"), uri);

          LOG.info("Requesting " + resourceName + " data from: " + uri.toString());
          container.get().executePreparedRawGetRequest();

          entitySet.set(container.get().getCommander().getClient()
              .getDeserializer(ContentType.APPLICATION_JSON)
              .toEntitySet(new ByteArrayInputStream(container.get().getResponseData().getBytes())));

        } catch (Exception exception) {
          LOG.error(exception);
        } finally {

          //assumes the 0th key is always used. TODO: determine if we need to scan the keys.
          final List<EdmKeyPropertyRef> keyFields = container.get().getEdm().getEntityContainer()
              .getEntitySet(resourceName).getEntityType().getKeyPropertyRefs();

          LOG.debug("Keys is: " + keyFields.stream().map(EdmKeyPropertyRef::getName).collect(Collectors.joining(", ")));

          assert(keyFields.size() > 0) :
              getDefaultErrorMessage("no Key Fields found! Resources MUST have at least one key.");

          //we will always key the hash by the first key, the other key fields will still be there
          //for the MUST requirement checks
          final String keyField = keyFields.get(0).getName();
          LOG.info("Hashing " + resourceName + " payload values...");
          entitySet.get().getPayload().getEntities().forEach(entity -> {
            entity.getProperties().parallelStream().forEach(property -> {
              if (!encodedSamples.get().get(resourceName).containsKey(keyField)) {
                encodedSamples.get().get(resourceName).put(entity.getProperty(keyField).getValue().toString(), new LinkedList<>());
              }

              final Vector<String> fieldMeta = new Vector<>();
              fieldMeta.setSize(3);
              fieldMeta.set(0, property.getName());
              fieldMeta.set(1, property.getName().contentEquals(MODIFICATION_TIMESTAMP_FIELD) || keyFields.stream().reduce(false,
                  (acc, f) -> acc && f.getName().contentEquals(property.getName()), Boolean::logicalAnd)
                  ? property.getValue().toString() : (property.getValue() != null
                  ? hashValues(property.getValue().toString()) : null));
              fieldMeta.set(2, property.getValue() == null ? null :
                  (keyFields.stream().anyMatch(f -> f.getName().contentEquals(property.getName())) ? "primaryKey" : null));

              LOG.info("Adding fieldMeta: " + fieldMeta.toString());

              encodedSamples.get().get(resourceName).get(entity.getProperty(keyField).getValue().toString()).add(fieldMeta);
            });
          });
          LOG.info("Values encoded!");
          //createDataAvailabilityReport(encodedSamples);
        }
        LOG.info("Records Sampled: " + encodedSamples.get().get(resourceName).values().size());
      });
    }
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
