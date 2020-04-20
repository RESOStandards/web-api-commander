package org.reso.commander.test.stepdefs;

import io.cucumber.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.edm.xml.XMLMetadata;
import org.apache.olingo.commons.api.edm.Edm;
import org.reso.commander.Commander;
import org.reso.commander.common.TestUtils;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;

/**
 * Tests metadata validation methods
 */
public class TestXMLAndMetadataValidation implements En {
  private static final Logger LOG = LogManager.getLogger(TestXMLAndMetadataValidation.class);
  AtomicReference<String> xmlMetadataString = new AtomicReference<>();
  AtomicReference<XMLMetadata> xmlMetadata = new AtomicReference<>();
  AtomicReference<Edm> edm = new AtomicReference<>();
  AtomicReference<Commander> commander = new AtomicReference<>();

  //state variables
  AtomicBoolean isXMLValid = new AtomicBoolean(false);
  AtomicBoolean isXMLMetadataValid = new AtomicBoolean(false);
  AtomicBoolean isEdmValid = new AtomicBoolean(false);

  public TestXMLAndMetadataValidation() {

    /*
     * Background
     */
    Given("^an OData test client has been created$", () -> {
      commander.set(new Commander.Builder().build());
    });


    /*
     * loads a test resource to a local string object by name
     */
    Given("^metadata were loaded from the sample resource \"([^\"]*)\"$", (String resourceName) -> {
      assertNotNull(getDefaultErrorMessage("ERROR: resource name cannot be null!", resourceName));

      try {
        xmlMetadataString.set(TestUtils.convertInputStreamToString(getClass().getClassLoader().getResourceAsStream(resourceName)));
        assertNotNull("ERROR: no string data was loaded from the given resource!", xmlMetadataString.get());
      } catch (Exception ex) {
        LOG.error("An exception was thrown trying to load the given resource name: " + resourceName);
        LOG.error(ex);
      }
    });


    /*
     * XML validation
     */
    When("^XML validation is performed on the resource data$", () -> {
      assertNotNull(getDefaultErrorMessage("resource data is null!", xmlMetadataString.get()));
      isXMLValid.set(Commander.validateXML(xmlMetadataString.get()));
    });
    Then("^XML validation succeeds$", () -> {
      assertTrue(getDefaultErrorMessage("expected XML validation to succeed but it failed!"), isXMLValid.get());
    });
    Then("^XML validation fails$", () -> {
      assertFalse(getDefaultErrorMessage("expected XML validation to succeed but it failed!"), isXMLValid.get());
    });


    /*
     * XML Metadata validation
     */
    When("^XML Metadata validation is performed on the resource data$", () -> {
      assertNotNull(getDefaultErrorMessage("resource data is null!", xmlMetadataString.get()));

      try {
        xmlMetadata.set(Commander.deserializeXMLMetadata(xmlMetadataString.get(), commander.get().getClient()));
        assertNotNull(getDefaultErrorMessage("XML Metadata cannot be null!"), xmlMetadata.get());
        isXMLMetadataValid.set(Commander.validateMetadata(xmlMetadata.get(), commander.get().getClient()));
      } catch (Exception ex) {
        fail(getDefaultErrorMessage("could not deserialize XML Metadata!"));
      }
    });
    Then("^XML Metadata validation succeeds$", () -> {
      assertTrue(getDefaultErrorMessage("expected XML validation to succeed but it failed!"), isXMLMetadataValid.get());
    });
    Then("^XML Metadata validation fails$", () -> {
      assertFalse(getDefaultErrorMessage("expected XML validation to fail but it succeeded!"), isXMLMetadataValid.get());
    });


    /*
     * Edm validation
     */
    When("^Edm validation is performed on the resource data$", () -> {
      try {
        assertNotNull(getDefaultErrorMessage("resource data is null!", xmlMetadataString.get()));
        edm.set(Commander.deserializeEdm(xmlMetadataString.get(), commander.get().getClient()));
        isEdmValid.set(Commander.validateMetadata(edm.get(), commander.get().getClient()));
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }

    });
    Then("^Edm Metadata validation succeeds$", () -> {
      assertTrue(getDefaultErrorMessage("expected Entity Data Model (Edm) to succeed but it failed!"), isEdmValid.get());
    });
    Then("^Edm Metadata validation fails$", () -> {
      assertFalse(getDefaultErrorMessage("expected Entity Data Model (Edm) to fail but it succeeded!"), isEdmValid.get());
    });

  }
}
