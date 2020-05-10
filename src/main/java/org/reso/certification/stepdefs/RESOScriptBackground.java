package org.reso.certification.stepdefs;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.models.Settings;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RESOScriptBackground {
  private static final Logger LOG = LogManager.getLogger(RESOScriptBackground.class);
  private static final String SHOW_RESPONSES = "showResponses";

  //extract any params here
  private static final boolean showResponses = Boolean.parseBoolean(System.getProperty(SHOW_RESPONSES));

  WebAPITestContainer container;

  @Inject
  public RESOScriptBackground(WebAPITestContainer webAPITestContainer) {
    container = webAPITestContainer;
  }

  @Given("a RESOScript file was provided")
  public void aRESOScriptFileWasProvided() {
    if (container.getPathToRESOScript() == null) {
      container.setPathToRESOScript(System.getProperty("pathToRESOScript"));
    }
    assertNotNull("ERROR: pathToRESOScript must be present in command arguments, see README", container.getPathToRESOScript());
    LOG.info("Using RESOScript: " + container.getPathToRESOScript());
  }

  @And("Client Settings and Parameters were read from the file")
  public void clientSettingsAndParametersWereReadFromTheFile() {
    if (container.getSettings() == null) {
      container.setSettings(Settings.loadFromRESOScript(new File(System.getProperty("pathToRESOScript"))));
    }
    assertNotNull("ERROR: Settings could not be loaded.", container.getSettings());
    LOG.info("RESOScript loaded successfully!");
  }

  @And("a test container was successfully created from the given RESOScript")
  public void aTestContainerWasSuccessfullyCreatedFromTheGivenRESOScript() {
    container.initialize();
  }

  @And("the test container uses an Authorization Code or Client Credentials for authentication")
  public void theTestContainerUsesAnAuthorizationCodeOrClientCredentialsForAuthentication() {
    assertNotNull(container.getCommander());
    assertTrue("ERROR: Commander must either have a valid Authorization Code or Client Credentials configuration.",
        container.getCommander().isAuthTokenClient() || (container.getCommander().isOAuth2Client() && container.getCommander().hasValidAuthConfig()));

    if (container.getCommander().isAuthTokenClient()) {
      LOG.info("Authentication Type: authorization_code");
    } else if (container.getCommander().isOAuth2Client()) {
      LOG.info("Authentication Type: client_credentials");
    }
  }
}
