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
import org.apache.olingo.commons.api.format.ContentType;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.commander.Commander;
import org.reso.commander.common.DataDictionaryMetadata;
import org.reso.models.Settings;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.google.common.hash.Hashing.sha256;
import static org.junit.Assume.assumeTrue;
import static org.reso.certification.containers.WebAPITestContainer.EMPTY_STRING;
import static org.reso.commander.common.TestUtils.failAndExitWithErrorMessage;

public class IDXPayload {
  private static final Logger LOG = LogManager.getLogger(IDXPayload.class);
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

    standardResources.set(resources.stream().filter(DataDictionaryMetadata.v1_7.WELL_KNOWN_RESOURCES::contains).collect(Collectors.toSet()));
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
    return sha256().hashString(String.join(EMPTY_STRING, values), StandardCharsets.UTF_8).toString();
  }

  @When("{int} records are sampled from each RESO Standard resource in the server metadata")
  public void recordsAreSampledFromEachRESOStandardResourceInTheServerMetadata(int numRecords) {
    if (!hasStandardResources.get()) {
      scenario.log("No RESO Standard Resources to sample");
      assumeTrue(true);
    } else {
      AtomicReference<ResWrap<EntityCollection>> entitySet = new AtomicReference<>();

      //Map of String Resource Names to Key-Mapped Hash of Field and SHA value
      AtomicReference<Map<String, Map<String, List<String>>>> encodedSamples = new AtomicReference<>(new LinkedHashMap<>());

      standardResources.get().forEach(resourceName -> {
        encodedSamples.get().put(resourceName, new LinkedHashMap<>());

        try {
          container.get().setRequestUri(Commander.prepareURI(
              container.get().getCommander().getClient().newURIBuilder(
                  container.get().getServiceRoot()).appendEntitySetSegment(resourceName).build().toString()));

          container.get().executePreparedRawGetRequest();
          entitySet.set(container.get().getCommander().getClient().getDeserializer(ContentType.APPLICATION_JSON).toEntitySet(new ByteArrayInputStream(container.get().getResponseData().getBytes())));
        } catch (Exception exception) {
          LOG.error(exception);
        } finally {
          final String keyField = container.get().getEdm().getEntityContainer().getEntitySet(resourceName).getEntityType().getKeyPropertyRefs().get(0).getName();
          entitySet.get().getPayload().getEntities().forEach(entity -> {
            entity.getProperties().forEach(property -> {
              if (!encodedSamples.get().get(resourceName).containsKey(keyField)) {
                encodedSamples.get().get(resourceName).put(keyField, new LinkedList<>());
              }

              encodedSamples.get().get(resourceName).get(keyField).add(property.getName());
              encodedSamples.get().get(resourceName).get(keyField).add(
                property.getName().contentEquals(keyField)
                    ? property.getValue().toString() : (property.getValue() != null
                    ? hashValues(property.getValue().toString()) : null));
            });
          });
          LOG.info(encodedSamples.get().toString());
        }
      });
    }
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
