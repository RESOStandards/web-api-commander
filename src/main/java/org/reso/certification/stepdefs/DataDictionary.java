package org.reso.certification.stepdefs;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reso.certification.containers.WebAPITestContainer;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assume.assumeTrue;

public class DataDictionary {

  private static final Logger LOG = LogManager.getLogger(DataDictionary.class);

  //TODO: make this a dynamic property based on DD version
  public static final String REFERENCE_RESOURCE = "RESO.Dictionary.Final.v.1.7.0.20190124T0000.xlsx";

  AtomicBoolean isFieldContainedInMetadata = new AtomicBoolean(false);

  @Inject
  WebAPITestContainer container;

  @And("metadata were retrieved from the server")
  public void metadataWereRetrievedFromTheServer() {
  }

  @And("metadata are valid")
  public void metadataAreValid() {
  }

  @Given("{string} exists in the metadata")
  public void existsInTheMetadata(String fieldName) {
    try {
      isFieldContainedInMetadata.set(container.getFieldMap().containsKey(fieldName));
    } catch (Exception ex) {
      LOG.debug(fieldName + " is not contained in the given metadata.");
    }
    assumeTrue(isFieldContainedInMetadata.get());
  }
  
  @Then("{string} MUST be {string} data type")
  public void mustBeDataType(String fieldName, String dataTypeName) {
    assumeTrue(isFieldContainedInMetadata.get());
  }

  @And("{string} precision SHOULD be less than or equal to the RESO Suggested Max Length of {int}")
  public void precisionSHOULDBeLessThanOrEqualToTheRESOSuggestedMaxLengthOf(String fieldName, Integer suggestedPrecision) {
    assumeTrue(isFieldContainedInMetadata.get());
  }

  @And("{string} scale SHOULD be less than or equal to the RESO Suggested Max Scale of {int}")
  public void scaleSHOULDBeLessThanOrEqualToTheRESOSuggestedMaxScaleOf(String fieldName, Integer suggestedMaxScale) {
    assumeTrue(isFieldContainedInMetadata.get());
  }

  @And("{string} enum values exist in the metadata")
  public void enumValuesExistInTheMetadata(String lookupName) {
    assumeTrue(isFieldContainedInMetadata.get());
  }

  @And("{string} enum types MUST have exactly one member")
  public void enumTypesMUSTHaveExactlyOneMember(String lookupName) {
    assumeTrue(isFieldContainedInMetadata.get());
  }

  @And("{string} MUST only contain enum values found in the metadata")
  public void mustOnlyContainEnumValuesFoundInTheMetadata(String lookupName) {
    assumeTrue(isFieldContainedInMetadata.get());
  }

  @And("{string} length SHOULD be less than or equal to the RESO Suggested Max Length of {int}")
  public void lengthSHOULDBeLessThanOrEqualToTheRESOSuggestedMaxLengthOf(String fieldName, Integer suggestedMaxLength) {
    assumeTrue(isFieldContainedInMetadata.get());
  }

  @And("{string} enum types MUST have at least one member")
  public void enumTypesMUSTHaveAtLeastOneMember(String lookupName) {
    assumeTrue(isFieldContainedInMetadata.get());
  }

  @And("{string} SHOULD have no more than the RESO Suggested Max Length of {int} item\\(s)")
  public void shouldHaveNoMoreThanTheRESOSuggestedMaxLengthOfItemS(String lookupName, Integer suggestedMaxItems) {
    assumeTrue(isFieldContainedInMetadata.get());
  }

  @And("{string} is not a synonym for another field")
  public void isNotASynonymForAnotherField(String fieldName) {
    assumeTrue(isFieldContainedInMetadata.get());
  }
}
