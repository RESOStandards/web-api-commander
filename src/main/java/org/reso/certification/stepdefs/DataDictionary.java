package org.reso.certification.stepdefs;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.commander.common.TestUtils;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class DataDictionary {

  private static final Logger LOG = LogManager.getLogger(DataDictionary.class);

  //TODO: make this a dynamic property based on DD version
  public static final String REFERENCE_RESOURCE = "RESO.Dictionary.Final.v.1.7.0.20190124T0000.xlsx";

  static final AtomicBoolean isFieldContainedInMetadata = new AtomicBoolean(false);
  static final AtomicBoolean shouldValidateMetadata = new AtomicBoolean(true);

  @Inject
  WebAPITestContainer container;

  @And("valid metadata were retrieved from the server")
  public void validMetadataWereRetrievedFromTheServer() {

    if (shouldValidateMetadata.get()) {
      //request metadata from server using service root in RESOScript file
      TestUtils.assertXMLMetadataAreRequestedFromTheServer(container);

      //ensure request succeeded
      assertEquals(container.getResponseCode().intValue(), HttpStatus.SC_OK);

      //metadata validation tests
      TestUtils.assertValidXMLMetadata(container);
      TestUtils.assertXmlMetadataContainsEdm(container);
      TestUtils.assertXMLMetadataHasValidServiceDocument(container);

      //build field map and ensure it's not null
      assertNotNull(container.getFieldMap());

      //everything succeeded, mark validation successful
      shouldValidateMetadata.set(false);
    }
  }

  @Given("{string} exists in the metadata")
  public void existsInTheMetadata(String fieldName) {
    isFieldContainedInMetadata.set(container.getFieldMap().containsKey(fieldName));
    if (isFieldContainedInMetadata.get()) LOG.info("Found: " + fieldName);
    assumeTrue("Skipped: " + fieldName, isFieldContainedInMetadata.get());
  }
  
  @Then("{string} MUST be {string} data type")
  public void mustBeDataType(String fieldName, String dataTypeName) {
    assumeTrue("Skipped: " + fieldName, isFieldContainedInMetadata.get());
  }

  @And("{string} precision SHOULD be less than or equal to the RESO Suggested Max Length of {int}")
  public void precisionSHOULDBeLessThanOrEqualToTheRESOSuggestedMaxLengthOf(String fieldName, Integer suggestedPrecision) {
    assumeTrue("Skipped: " + fieldName, isFieldContainedInMetadata.get());
  }

  @And("{string} scale SHOULD be less than or equal to the RESO Suggested Max Scale of {int}")
  public void scaleSHOULDBeLessThanOrEqualToTheRESOSuggestedMaxScaleOf(String fieldName, Integer suggestedMaxScale) {
    assumeTrue("Skipped: " + fieldName, isFieldContainedInMetadata.get());
  }

  @And("{string} enum values exist in the metadata")
  public void enumValuesExistInTheMetadata(String lookupName) {
    assumeTrue("Skipped: " + lookupName, isFieldContainedInMetadata.get());
  }

  @And("{string} enum types MUST have exactly one member")
  public void enumTypesMUSTHaveExactlyOneMember(String lookupName) {
    assumeTrue("Skipped: " + lookupName, isFieldContainedInMetadata.get());
  }

  @And("{string} MUST only contain enum values found in the metadata")
  public void mustOnlyContainEnumValuesFoundInTheMetadata(String lookupName) {
    assumeTrue("Skipped: " + lookupName, isFieldContainedInMetadata.get());
  }

  @And("{string} length SHOULD be less than or equal to the RESO Suggested Max Length of {int}")
  public void lengthSHOULDBeLessThanOrEqualToTheRESOSuggestedMaxLengthOf(String fieldName, Integer suggestedMaxLength) {
    assumeTrue("Skipped: " + fieldName, isFieldContainedInMetadata.get());
  }

  @And("{string} enum types MUST have at least one member")
  public void enumTypesMUSTHaveAtLeastOneMember(String lookupName) {
    assumeTrue("Skipped: " + lookupName, isFieldContainedInMetadata.get());
  }

  @And("{string} SHOULD have no more than the RESO Suggested Max Length of {int} item\\(s)")
  public void shouldHaveNoMoreThanTheRESOSuggestedMaxLengthOfItemS(String lookupName, Integer suggestedMaxItems) {
    assumeTrue("Skipped: " + lookupName, isFieldContainedInMetadata.get());
  }

  @And("{string} is not a synonym for another field")
  public void isNotASynonymForAnotherField(String fieldName) {
    assumeTrue("Skipped: " + fieldName, isFieldContainedInMetadata.get());
  }
}
