package org.reso.commander.test.stepdefs;

import io.cucumber.java8.En;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reso.certification.codegen.WorksheetProcessor;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.commander.Commander;
import org.reso.commander.common.TestUtils;
import org.reso.models.Settings;

import java.io.File;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;
import static org.reso.commander.common.TestUtils.*;

public class TestWebAPICoreTestContainer implements En {
  private static final AtomicReference<WebAPITestContainer> testContainer = new AtomicReference<>();
  private static final Logger LOG = LogManager.getLogger(TestWebAPICoreTestContainer.class);
  private static final String REFERENCE_METADATA_TEMPLATE = "RESODataDictionary-%s.xml";

  public TestWebAPICoreTestContainer() {

    /*
     * Background
     */
    Given("^a Web API test container was created using the RESOScript \"([^\"]*)\"$", (String fileName) -> {
      try {
        //reuse the existing test container if it already exists
        if (testContainer.get() != null && testContainer.get().getIsInitialized()) {
          testContainer.get().resetState();
        } else {
          //get settings from mock RESOScript file
          URL resource = getClass().getClassLoader().getResource(fileName);
          assertNotNull(getDefaultErrorMessage("was unable to find the given RESOScript:", fileName), resource);

          setTestContainer(new WebAPITestContainer());
          getTestContainer().setSettings(Settings.loadFromRESOScript(new File(resource.getFile())));
          assertNotNull(getDefaultErrorMessage("could not load mock RESOScript: " + resource.getFile()), getTestContainer().getSettings());
          getTestContainer().initialize();
          LOG.info("Test container initialized!");
        }
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    And("^sample metadata from \"([^\"]*)\" are loaded into the test container$", (String resourceName) -> {
      assertNotNull(getDefaultErrorMessage("resourceName cannot be null!"), resourceName);

      try {
        String xmlMetadataString = loadResourceAsString(resourceName);
        assertNotNull(getDefaultErrorMessage("could not load resourceName:", resourceName), xmlMetadataString);
        getTestContainer().setResponseCode(HttpStatus.SC_OK);
        getTestContainer().setXMLResponseData(xmlMetadataString);
        LOG.info("XML Metadata loaded from " + resourceName);
        getTestContainer().setXMLMetadata(Commander.deserializeXMLMetadata(xmlMetadataString, getTestContainer().getCommander().getClient()));
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * Auth settings validation
     */
    When("^an auth token is provided in \"([^\"]*)\"$", (String clientSettingsAuthToken) -> {
      String token = Settings.resolveParametersString(clientSettingsAuthToken, getTestContainer().getSettings());
      assertNotNull(getDefaultErrorMessage("BearerToken is null in the ClientSettings section!"), token);
    });

    Then("^the Commander is created using auth token client mode$", () ->
        assertTrue(getDefaultErrorMessage("expected auth token Commander client!"), getTestContainer().getCommander().isAuthTokenClient()));

    And("^the auth token has a value of \"([^\"]*)\"$", (String assertedTokenValue) ->
        assertEquals(getDefaultErrorMessage("asserted token value is not equal to the one provided in the container!"),
            assertedTokenValue, getTestContainer().getAuthToken()));

    And("^a Commander instance exists within the test container$", () ->
        assertNotNull(getDefaultErrorMessage("Commander instance is null in the container!"),
            getTestContainer().getCommander()));

    But("^the Commander is not created using client credentials mode$", () ->
        assertFalse(getDefaultErrorMessage("expected that the Commander was not using client credentials"),
            getTestContainer().getCommander().isOAuth2Client()));

    And("^Settings are present in the test container$", () ->
        assertNotNull(getDefaultErrorMessage("settings were not found in the Web API test container!"),
            getTestContainer().getSettings()));


    /*
     * Metadata validation
     */
    Then("^metadata are valid$", () -> {
      getTestContainer().validateMetadata();
      assertTrue(getDefaultErrorMessage("getIsMetadataValid() returned false when true was expected!"),
          getTestContainer().hasValidMetadata());
    });

    Then("^metadata are invalid$", () -> {
      getTestContainer().validateMetadata();
      assertFalse(getDefaultErrorMessage("getIsMetadataValid() returned true when false was expected!"),
          getTestContainer().hasValidMetadata());
    });

    /*
     * Data loading step
     */
    When("^sample JSON data from \"([^\"]*)\" are loaded into the test container$", (String resourceName) -> {
      getTestContainer().setResponseCode(HttpStatus.SC_OK);
      getTestContainer().setResponseData(loadResourceAsString(resourceName));
      System.out.println("JSON Data loaded from: " + resourceName);
    });


    /*
     * DataSystem validation
     */
    Then("^schema validation passes for the sample DataSystem data$", () ->
        assertTrue(getDefaultErrorMessage("expected DataSystem to pass validation, but it failed!"),
            getTestContainer().validateDataSystem().getIsValidDataSystem()));

    Then("^schema validation fails for the sample DataSystem data$", () ->
        assertFalse(getDefaultErrorMessage("expected DataSystem to fail validation, but it passed!"),
            getTestContainer().validateDataSystem().getIsValidDataSystem()));

    /*
     * Integer Response Testing
     */
    Then("^Integer comparisons of \"([^\"]*)\" \"([^\"]*)\" (\\d+) return \"([^\"]*)\"$", (String fieldName, String op, Integer assertedValue, String expectedValue) -> {
      final boolean expected = Boolean.parseBoolean(expectedValue),
          result = compareIntegerPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, op, assertedValue);
      if (expected) {
        assertTrue(result);
      } else {
        assertFalse(result);
      }
    });

    Then("^Integer comparisons of \"([^\"]*)\" \"([^\"]*)\" null return \"([^\"]*)\"$", (String fieldName, String op, String expectedValue) -> {
      final boolean expected = Boolean.parseBoolean(expectedValue),
          result = compareIntegerPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, op, null);
      if (expected) {
        assertTrue(result);
      } else {
        assertFalse(result);
      }
    });

    Then("^Decimal comparisons of \"([^\"]*)\" \"([^\"]*)\" (\\d+)\\.(\\d+) return \"([^\"]*)\"$", (String arg0, String arg1, Integer arg2, Integer arg3, String arg4) -> {
    });
    Then("^Decimal comparisons of \"([^\"]*)\" \"([^\"]*)\" null return \"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
    });
    Then("^Integer comparisons of \"([^\"]*)\" \"([^\"]*)\" (\\d+)\\.(\\d+) return \"([^\"]*)\"$", (String arg0, String arg1, Integer arg2, Integer arg3, String arg4) -> {
    });


    /*
     * String Response Testing
     */
    Then("^String data in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" returns \"([^\"]*)\"$", (String fieldName, String op, String assertedValue, String expectedValue) -> {
      final boolean expected = Boolean.parseBoolean(expectedValue),
          result = compareStringPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, op, assertedValue);
      if (expected) {
        assertTrue(result);
      } else {
        assertFalse(result);
      }
    });

    Then("^String data in \"([^\"]*)\" \"([^\"]*)\" null returns \"([^\"]*)\"$", (String fieldName, String op, String expectedValue) -> {
      final boolean expected = Boolean.parseBoolean(expectedValue),
          result = compareStringPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, op, null);
      if (expected) {
        assertTrue(result);
      } else {
        assertFalse(result);
      }
    });

    Then("^String data in \"([^\"]*)\" \"([^\"]*)\" equals \"([^\"]*)\"$", (String fieldName, String op, String assertedValue)
        -> assertTrue(compareStringPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, op, assertedValue)));

    Then("^String data in \"([^\"]*)\" \"([^\"]*)\" does not equal \"([^\"]*)\"$", (String fieldName, String op, String assertedValue)
        -> assertFalse(compareStringPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, op, assertedValue)));

    Then("^String data in \"([^\"]*)\" \"([^\"]*)\" is null$", (String fieldName, String op)
        -> assertFalse(compareStringPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, op, null)));


    /*
     * Timestamp Response Testing
     */
    Then("^comparisons of Timestamp field \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" return \"([^\"]*)\"$", (String fieldName, String op, String assertedValue, String expectedValue) -> {
      final boolean expected = Boolean.parseBoolean(expectedValue),
          result = compareTimestampPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, op, assertedValue);
      if (expected) {
        assertTrue(result);
      } else {
        assertFalse(result);
      }
    });

    Then("^comparisons of Timestamp field \"([^\"]*)\" \"([^\"]*)\" null return \"([^\"]*)\"$", (String fieldName, String op, String expectedValue) -> {
      final boolean expected = Boolean.parseBoolean(expectedValue),
          result = compareTimestampPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, op, null);
      if (expected) {
        assertTrue(result);
      } else {
        assertFalse(result);
      }
    });

    Then("^\"([^\"]*)\" comparisons of Timestamp field \"([^\"]*)\" \"([^\"]*)\" (\\d+) return \"([^\"]*)\"$", (String datePart, String fieldName, String op, Integer assertedValue, String expectedValue) -> {
      final boolean expected = Boolean.parseBoolean(expectedValue),
          result = compareTimestampPayloadToAssertedDatePartValue(getTestContainer().getResponseData(), datePart, fieldName, op, assertedValue);
      if (expected) {
        assertTrue(result);
      } else {
        assertFalse(result);
      }
    });

    Then("^\"([^\"]*)\" comparisons of Timestamp field \"([^\"]*)\" \"([^\"]*)\" null return \"([^\"]*)\"$", (String datePart, String fieldName, String op, String expectedValue) -> {
      final boolean expected = Boolean.parseBoolean(expectedValue),
          result = compareTimestampPayloadToAssertedDatePartValue(getTestContainer().getResponseData(), datePart, fieldName, op,null);
      if (expected) {
        assertTrue(result);
      } else {
        assertFalse(result);
      }
    });

    /*
     * Fractional Second Response Testing
     */
    Then("^fractionalsecond comparisons of Timestamp field \"([^\"]*)\" \"([^\"]*)\" (\\d+\\.\\d+) return \"([^\"]*)\"$", (String fieldName, String op, Double assertedValue, String expectedValue) -> {
      final boolean expected = Boolean.parseBoolean(expectedValue),
          result = compareFractionalSecondsPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, op, assertedValue);
      if (expected) {
        assertTrue(result);
      } else {
        assertFalse(result);
      }
    });

    Then("^fractionalsecond comparisons of Timestamp field \"([^\"]*)\" \"([^\"]*)\" null return \"([^\"]*)\"$", (String fieldName, String op, String expectedValue) -> {
      final boolean expected = Boolean.parseBoolean(expectedValue),
          result = compareFractionalSecondsPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, op, null);
      if (expected) {
        assertTrue(result);
      } else {
        assertFalse(result);
      }
    });


    /*
     * Date Comparisons
     */
    Then("^\"([^\"]*)\" comparisons of Date field \"([^\"]*)\" \"([^\"]*)\" (\\d+) return \"([^\"]*)\"$", (String datePart, String fieldName, String op, Integer assertedValue, String expectedValue) -> {
      final boolean expected = Boolean.parseBoolean(expectedValue),
          result = compareDatePayloadToAssertedDatePartValue(getTestContainer().getResponseData(), datePart, fieldName, op, assertedValue);
      if (expected) {
        assertTrue(result);
      } else {
        assertFalse(result);
      }
    });

    Then("^\"([^\"]*)\" comparisons of Date field \"([^\"]*)\" \"([^\"]*)\" null return \"([^\"]*)\"$", (String datePart, String fieldName, String op, String expectedValue) -> {
      final boolean expected = Boolean.parseBoolean(expectedValue),
          result = compareDatePayloadToAssertedDatePartValue(getTestContainer().getResponseData(), datePart, fieldName, op, null);
      if (expected) {
        assertTrue(result);
      } else {
        assertFalse(result);
      }
    });

    Then("^collection values only contain \"([^\"]*)\" or the empty list is \"([^\"]*)\"$", (String value, String expectedResult) -> {
//      final boolean expected = Boolean.parseBoolean(expectedResult),
//          result = TestUtils.compareCollectionPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, op, expected);
//      expected ? assertTrue(result) : false ;
    });

    Then("^collection values in the \"([^\"]*)\" field contain only \"([^\"]*)\" or the empty list is \"([^\"]*)\"$",
        (String fieldName, String assertedValue, String expectedResult) -> {

          final boolean expected = Boolean.parseBoolean(expectedResult),
              result = TestUtils.compareCollectionPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, "all", assertedValue);

          if (expected) {
            assertTrue(result);
          } else {
            assertFalse(result);
          }
        });

    Then("^collection results in the \"([^\"]*)\" field contain \"([^\"]*)\" is \"([^\"]*)\"$",
        (String fieldName, String assertedValue, String expectedResult) -> {

          final boolean expected = Boolean.parseBoolean(expectedResult),
              result = TestUtils.compareCollectionPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, "any", assertedValue);

          if (expected) {
            assertTrue(result);
          } else {
            assertFalse(result);
          }
        });


    Then("^OData count tests return \"([^\"]*)\"$", (String expectedResult) -> {
      final boolean expected = Boolean.parseBoolean(expectedResult),
          result = TestUtils.validateODataCount(getTestContainer().getResponseData());

      if (expected) {
        assertTrue(result);
      } else {
        assertFalse(result);
      }
    });
  }

  /**
   * Returns a string containing the contents of the given resource name
   *
   * @param resourceName the resource name to load
   * @return string data from the resource
   */
  public String loadResourceAsString(String resourceName) {
    return TestUtils.convertInputStreamToString(getClass().getClassLoader().getResourceAsStream(resourceName));
  }

  private WebAPITestContainer getTestContainer() {
    return testContainer.get();
  }

  private void setTestContainer(WebAPITestContainer testContainer) {
    this.testContainer.set(testContainer);
  }
}
