package org.reso.certification.stepdefs;

import com.google.inject.Inject;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.models.Settings;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assume.assumeTrue;

public class IdxPayload {
  private static final Logger LOG = LogManager.getLogger(IdxPayload.class);
  private static final String PATH_TO_RESOSCRIPT_KEY = "pathToRESOScript";
  private static Scenario scenario;

  private final static AtomicReference<WebAPITestContainer> container = new AtomicReference<>();

  @Inject
  public IdxPayload(WebAPITestContainer c) {
    container.set(c);
  }

  @Before
  public void beforeStep(Scenario scenario) {
    final String pathToRESOScript = System.getProperty(PATH_TO_RESOSCRIPT_KEY, null);

    if (pathToRESOScript == null) return;

    IdxPayload.scenario = scenario;

    if (!container.get().getIsInitialized()) {
      container.get().setSettings(Settings.loadFromRESOScript(new File(System.getProperty(PATH_TO_RESOSCRIPT_KEY))));
      container.get().initialize();
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
    scenario.log("Testing complete!");
    assumeTrue(true);
  }
}
