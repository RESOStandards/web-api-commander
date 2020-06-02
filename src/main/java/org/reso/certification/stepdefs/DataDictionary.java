package org.reso.certification.stepdefs;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.commander.common.TestUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;

public class DataDictionary {
  private static final Logger LOG = LogManager.getLogger(DataDictionary.class);

  //TODO: make this a dynamic property based on DD version
  public static final String REFERENCE_RESOURCE = "reso-ddwiki-export-2020-03-05-DDW17-v1.7.xlsx";

  static final AtomicBoolean shouldValidateMetadata = new AtomicBoolean(true);
  static final AtomicReference<String> currentResourceName = new AtomicReference<>();

  //used to keep track of the found standard fields being certified on a per-resource basis
  static final AtomicReference<Map<String, Map<String, CsdlProperty>>> standardFieldMap = new AtomicReference<>(new LinkedHashMap<>());

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

  private boolean isFieldContainedInMetadata(String fieldName) {
    return standardFieldMap.get().containsKey(currentResourceName.get())
        && standardFieldMap.get().get(currentResourceName.get()).containsKey(fieldName);
  }

  @Given("{string} exists in the {string} metadata")
  public void existsInTheMetadata(String fieldName, String resourceName) {
    assertNotNull(getDefaultErrorMessage("field name cannot be null!"), fieldName);
    assertNotNull(getDefaultErrorMessage("resource name cannot be null!"), resourceName);

    currentResourceName.set(resourceName);

    if (container.getFieldMap().containsKey(currentResourceName.get())) {
      //add keyed hash for the given resource name if it doesn't exist
      if (!standardFieldMap.get().containsKey(currentResourceName.get()))
        standardFieldMap.get().put(currentResourceName.get(), new LinkedHashMap<>());

      //if the field for the given resource contains the given field, then add it to the standard field map
      if (container.getFieldMap(currentResourceName.get()).containsKey(fieldName))
        standardFieldMap.get().get(resourceName).put(fieldName, container.getFieldMap(resourceName).get(fieldName));

    }

    if (isFieldContainedInMetadata(fieldName)) LOG.info("Found: " + fieldName);
    assumeTrue("Skipped: " + fieldName, isFieldContainedInMetadata(fieldName));
  }

  @Then("{string} MUST be {string} data type")
  public void mustBeDataType(String fieldName, String dataTypeName) {
    //container.getEdm().getEnumType(container.getFieldMap().get(currentResourceName.get()).get(fieldName).getTypeAsFQNObject()).getUnderlyingType()
    String foundTypeName = container.getFieldMap().get(currentResourceName.get()).get(fieldName).getType();
    assertDataTypeMapping(dataTypeName, foundTypeName);
  }

  private void assertDataTypeMapping(String ddTypeName, String edmTypeName) {
    assertNotNull(getDefaultErrorMessage("you must specify a Data Dictionary type name to check!"), ddTypeName);
    assertNotNull(getDefaultErrorMessage("you must specify an Edm type name to check!"), edmTypeName);


    if ("string".equals(ddTypeName.toLowerCase())) {
      assertTrue(getDefaultErrorMessage(ddTypeName, "data type must map to", edmTypeName),
              edmTypeName.contentEquals("Edm.String"));
    } else if ("date".equals(ddTypeName.toLowerCase())) {
      assertTrue(getDefaultErrorMessage(ddTypeName, "data type must map to", edmTypeName),
              edmTypeName.contentEquals("Edm.Date"));
    } else if ("decimal".equals(ddTypeName.toLowerCase())) {
      assertTrue(getDefaultErrorMessage(ddTypeName, "data type must map to", edmTypeName),
              edmTypeName.contentEquals("Edm.Double"));
    } else if ("integer".equals(ddTypeName.toLowerCase())) {
      assertTrue(getDefaultErrorMessage(ddTypeName, "data type must map to", edmTypeName),
              edmTypeName.contentEquals("Edm.Int16")
                      || edmTypeName.contentEquals("Edm.Int32")
                      || edmTypeName.contentEquals("Edm.Int64"));
    } else if ("boolean".equals(ddTypeName.toLowerCase())) {
      assertTrue(getDefaultErrorMessage(ddTypeName, "data type must map to", edmTypeName),
              edmTypeName.contentEquals("Edm.Boolean"));
    } else {
      fail(getDefaultErrorMessage("could not find data type mapping for", ddTypeName));
    }

  }

  @And("{string} precision SHOULD be less than or equal to the RESO Suggested Max Length of {int}")
  public void precisionSHOULDBeLessThanOrEqualToTheRESOSuggestedMaxLengthOf(String fieldName, Integer suggestedPrecision) {
    assumeTrue("Skipped: " + fieldName, isFieldContainedInMetadata(fieldName));
  }

  @And("{string} scale SHOULD be less than or equal to the RESO Suggested Max Scale of {int}")
  public void scaleSHOULDBeLessThanOrEqualToTheRESOSuggestedMaxScaleOf(String fieldName, Integer suggestedMaxScale) {
    assumeTrue("Skipped: " + fieldName, isFieldContainedInMetadata(fieldName));
  }

  @And("{string} enum values exist in the metadata")
  public void enumValuesExistInTheMetadata(String lookupName) {
    assumeTrue("Skipped: " + lookupName, isFieldContainedInMetadata(lookupName));
  }

  @And("{string} enum types MUST have exactly one member")
  public void enumTypesMUSTHaveExactlyOneMember(String lookupName) {
    assumeTrue("Skipped: " + lookupName, isFieldContainedInMetadata(lookupName));
  }

  @And("{string} MUST only contain enum values found in the metadata")
  public void mustOnlyContainEnumValuesFoundInTheMetadata(String lookupName) {
    assumeTrue("Skipped: " + lookupName, isFieldContainedInMetadata(lookupName));
  }

  @And("{string} length SHOULD be less than or equal to the RESO Suggested Max Length of {int}")
  public void lengthSHOULDBeLessThanOrEqualToTheRESOSuggestedMaxLengthOf(String fieldName, Integer suggestedMaxLength) {
    assumeTrue("Skipped: " + fieldName, isFieldContainedInMetadata(fieldName));
  }

  @And("{string} enum types MUST have at least one member")
  public void enumTypesMUSTHaveAtLeastOneMember(String lookupName) {
    assumeTrue("Skipped: " + lookupName, isFieldContainedInMetadata(lookupName));
  }

  @And("{string} SHOULD have no more than the RESO Suggested Max Length of {int} item\\(s)")
  public void shouldHaveNoMoreThanTheRESOSuggestedMaxLengthOfItemS(String lookupName, Integer suggestedMaxItems) {
    assumeTrue("Skipped: " + lookupName, isFieldContainedInMetadata(lookupName));
  }

  @And("{string} is not a synonym for another field")
  public void isNotASynonymForAnotherField(String fieldName) {
    assumeTrue("Skipped: " + fieldName, isFieldContainedInMetadata(fieldName));
  }

  @And("{string} enum types MUST allow only one member")
  public void enumTypesMUSTAllowOnlyOneMember(String arg0) {
  }
}
