package org.reso.certification.stepdefs;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.commons.api.edm.EdmEnumType;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.commander.Commander;
import org.reso.commander.common.TestUtils;
import org.reso.models.Settings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;

public class DataDictionary {
  private static final Logger LOG = LogManager.getLogger(DataDictionary.class);

  @Inject
  WebAPITestContainer container;

  //TODO: make this a dynamic property based on DD version
  public static final String REFERENCE_RESOURCE = "DDv1.7-StandardAndDisplayNames-20200922210930847.xlsx";
  static final AtomicReference<String> currentResourceName = new AtomicReference<>();

  //used to keep track of the found standard fields being certified on a per-resource basis
  static final AtomicReference<Map<String, Map<String, CsdlProperty>>> standardFieldMap = new AtomicReference<>(new LinkedHashMap<>());
  private static final String SHOW_RESPONSES = "showResponses";
  private static final String PATH_TO_METADATA = "pathToMetadata";
  private static final String PATH_TO_RESOSCRIPT = "pathToRESOScript";

  //extract any params here
  private final boolean showResponses = Boolean.parseBoolean(System.getProperty(SHOW_RESPONSES));
  private final String pathToMetadata = System.getProperty(PATH_TO_METADATA);
  private final String pathToRESOScript = System.getProperty(PATH_TO_RESOSCRIPT);

  private final boolean isUsingRESOScript = pathToRESOScript != null;
  private final boolean isUsingMetadata = pathToMetadata != null;

  private boolean isFieldContainedInMetadata(String fieldName) {
    return standardFieldMap.get().containsKey(currentResourceName.get())
        && standardFieldMap.get().get(currentResourceName.get()).containsKey(fieldName);
  }


  @Given("a RESOScript or Metadata file are provided")
  public void aRESOScriptOrMetadataFileAreProvided() {
    assertTrue("ERROR: one of pathToRESOScript OR pathToMetadata MUST be present in command arguments, see README",
        pathToRESOScript != null || pathToMetadata != null);

    if (isUsingRESOScript) {
      LOG.debug("Using RESOScript: " + pathToRESOScript);
    } else if (isUsingMetadata) {
      LOG.debug("Using Metadata: " + pathToMetadata);
    }
  }

  @And("a test container was successfully created from the given RESOScript file")
  public void aTestContainerWasSuccessfullyCreatedFromTheGivenRESOScriptFile() {
    if (isUsingRESOScript) {
      container.initialize();
      container.setShowResponses(showResponses);
    }
  }

  @And("a test container was successfully created from the given metadata file")
  public void aMetadataTestContainerWasSuccessfullyCreatedFromTheGivenMetadataFile() {
    if (isUsingMetadata) {
      container.initialize(pathToMetadata);
      container.setShowResponses(showResponses);
    }
  }

  @When("a RESOScript file is provided")
  public void aRESOScriptFileIsProvided() {
    if (isUsingRESOScript) {
      if (container.getPathToRESOScript() == null) {
        container.setPathToRESOScript(System.getProperty("pathToRESOScript"));
      }
      assertNotNull("ERROR: pathToRESOScript must be present in command arguments, see README", container.getPathToRESOScript());
      LOG.debug("Using RESOScript: " + container.getPathToRESOScript());
    }
  }

  @Then("Client Settings and Parameters can be read from the RESOScript")
  public void clientSettingsAndParametersCanBeReadFromTheRESOScript() {
    if (isUsingRESOScript) {
      if (container.getSettings() == null) {
        container.setSettings(Settings.loadFromRESOScript(new File(System.getProperty("pathToRESOScript"))));
        assertNotNull("ERROR: Settings could not be loaded.", container.getSettings());
        LOG.info("RESOScript loaded successfully!");
      }
    }
  }

  @When("a metadata file is provided")
  public void aMetadataFileIsProvided() {
    if (isUsingMetadata) {
      assertTrue(pathToMetadata != null && Files.exists(Paths.get(pathToMetadata)));
    }
  }

  @And("valid metadata are loaded into the test container")
  public void validMetadataAreLoadedIntoTheTestContainer() {
    if (isUsingMetadata && container.getXMLMetadata() == null) {
      try {
        final String xmlMetadataString = new String(Files.readAllBytes(Paths.get(pathToMetadata)));

        assertTrue("XML Metadata MUST be valid XML according to the OASIS XSDs!", Commander.validateXML(xmlMetadataString));
        container.setXMLResponseData(xmlMetadataString);

        container.setXMLMetadata(Commander.deserializeXMLMetadata(xmlMetadataString, container.getCommander().getClient()));
        assertTrue("XML Metadata MUST be valid OData XML Metadata!", Commander.validateMetadata(container.getXMLMetadata(), container.getCommander().getClient()));

        container.setEdm(Commander.deserializeEdm(xmlMetadataString, container.getCommander().getClient()));
        assertTrue("Edm metadata MUST be valid!", Commander.validateMetadata(container.getEdm(), container.getCommander().getClient()));
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      LOG.debug("Using cached metadata!");
    }
  }

  @And("the test container uses an Authorization Code or Client Credentials for authentication")
  public void theTestContainerUsesAnAuthorizationCodeOrClientCredentialsForAuthentication() {
    if (isUsingRESOScript) {
      assertNotNull(container.getCommander());
      assertTrue("ERROR: Commander must either have a valid Authorization Code or Client Credentials configuration.",
          container.getCommander().isAuthTokenClient() || (container.getCommander().isOAuth2Client() && container.getCommander().hasValidAuthConfig()));
    }
  }

  @And("valid metadata were retrieved from the server")
  public void validMetadataWereRetrievedFromTheServer() {

    if (isUsingRESOScript && container.getShouldValidateMetadata()) {

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
      container.setShouldValidateMetadata(false);
    }
  }


  @When("{string} exists in the {string} metadata")
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

    if (isFieldContainedInMetadata(fieldName)) LOG.info("\nFound Field: " + fieldName);
    assumeTrue("Skipped: " + fieldName, isFieldContainedInMetadata(fieldName));
  }

  @Then("{string} MUST be {string} data type")
  public void mustBeDataType(String fieldName, String dataTypeName) {
    String foundTypeName = container.getFieldMap().get(currentResourceName.get()).get(fieldName).getType();
    LOG.info("Field data type: " + foundTypeName);
    assertDataTypeMapping(fieldName, dataTypeName, foundTypeName);
  }

  private static class TypeMappings {

    public static class DataDictionaryTypes {
      public static final String
        STRING = "String",
        DATE = "Date",
        DECIMAL = "Decimal",
        INTEGER = "Integer",
        BOOLEAN = "Boolean",
        SINGLE_ENUM = "Single Enumeration",
        MULTI_ENUM = "Multiple Enumeration",
        TIMESTAMP = "Timestamp";
    }

    public static class ODataTypes {
      public static final String
        STRING = "Edm.String",
        DATE = "Edm.Date",
        DECIMAL = "Edm.Decimal",
        DOUBLE = "Edm.Double",
        INT16 = "Edm.Int16",
        INT32 = "Edm.Int32",
        INT64 = "Edm.Int64",
        BOOLEAN = "Edm.Boolean",
        DATETIME_OFFSET = "Edm.DateTimeOffset";
    }
  }

  private void assertDataTypeMapping(String fieldName, String assertedTypeName, String foundTypeName) {
    assertNotNull(getDefaultErrorMessage("you must specify a Data Dictionary type name to check!"), assertedTypeName);
    assertNotNull(getDefaultErrorMessage("you must specify an Edm type name to check!"), foundTypeName);
    EdmEnumType enumType;
    boolean isIntegerType = false;

    final boolean isPrimitiveType =
        foundTypeName.contentEquals(TypeMappings.ODataTypes.INT16)
        || foundTypeName.contentEquals(TypeMappings.ODataTypes.INT32)
        || foundTypeName.contentEquals(TypeMappings.ODataTypes.INT64)
        || foundTypeName.contentEquals(TypeMappings.ODataTypes.STRING)
        || foundTypeName.contentEquals(TypeMappings.ODataTypes.DATE)
        || foundTypeName.contentEquals(TypeMappings.ODataTypes.DATETIME_OFFSET)
        || foundTypeName.contentEquals(TypeMappings.ODataTypes.BOOLEAN)
        || foundTypeName.contentEquals(TypeMappings.ODataTypes.DECIMAL)
        || foundTypeName.contentEquals(TypeMappings.ODataTypes.DOUBLE);

    switch (assertedTypeName) {
      case TypeMappings.DataDictionaryTypes.STRING:
        assertTrue(getDefaultErrorMessage(fieldName, "MUST map to", assertedTypeName, "but found", foundTypeName),
            foundTypeName.contentEquals(TypeMappings.ODataTypes.STRING));
        break;
      case TypeMappings.DataDictionaryTypes.DATE:
        assertTrue(getDefaultErrorMessage(fieldName, "MUST map to", assertedTypeName, "but found", foundTypeName),
            foundTypeName.contentEquals(TypeMappings.ODataTypes.DATE));
        break;
      case TypeMappings.DataDictionaryTypes.DECIMAL:
        assertTrue(getDefaultErrorMessage(fieldName, "MUST map to", assertedTypeName, "but found", foundTypeName),
            foundTypeName.contentEquals(TypeMappings.ODataTypes.DECIMAL)
                || foundTypeName.contentEquals(TypeMappings.ODataTypes.DOUBLE));
        break;
      case TypeMappings.DataDictionaryTypes.INTEGER:
        isIntegerType = foundTypeName.contentEquals(TypeMappings.ODataTypes.INT16)
            || foundTypeName.contentEquals(TypeMappings.ODataTypes.INT32)
            || foundTypeName.contentEquals(TypeMappings.ODataTypes.INT64);

        assertTrue(getDefaultErrorMessage(fieldName, "MUST map to", assertedTypeName, "but found", foundTypeName),
            isIntegerType);
        break;
      case TypeMappings.DataDictionaryTypes.BOOLEAN:
        assertTrue(getDefaultErrorMessage(fieldName, "MUST map to", assertedTypeName, "but found", foundTypeName),
            foundTypeName.contentEquals(TypeMappings.ODataTypes.BOOLEAN));
        break;
      case TypeMappings.DataDictionaryTypes.SINGLE_ENUM:
        if (foundTypeName.contentEquals(TypeMappings.ODataTypes.STRING)) {
          LOG.error(getDefaultErrorMessage("String types are not allowed for enumerated fields at the current time.", "\nSee RCP-031 for further information:",
              "https://members.reso.org/pages/viewpage.action?pageId=67962918#RCP-WEBAPI-031DataDictionaryRepresentationintheWebAPI-DataTypeMappings.1"));
        }

        assertFalse(getDefaultErrorMessage("Enumerated data type MUST declare a unique nominal type.",
            "Found primitive type of", foundTypeName, "\nSee: http://docs.oasis-open.org/odata/odata/v4.0/errata03/os/complete/part3-csdl/odata-v4.0-errata03-os-part3-csdl-complete.html#_Toc453752565"),
            isPrimitiveType);

        //check for enum type by FQDN in the Edm cached in the container
        enumType = container.getEdm().getEnumType(new FullQualifiedName(foundTypeName));

        isIntegerType = enumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString().contentEquals(TypeMappings.ODataTypes.INT16)
            || enumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString().contentEquals(TypeMappings.ODataTypes.INT32)
            || enumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString().contentEquals(TypeMappings.ODataTypes.INT64);

        assertTrue(getDefaultErrorMessage("Enumerated Types MUST use an underlying type of",
            TypeMappings.ODataTypes.INT16, "OR", TypeMappings.ODataTypes.INT32,  "OR", TypeMappings.ODataTypes.INT64), isIntegerType);

        assertNotNull(getDefaultErrorMessage(
            "could not find a definition for", foundTypeName, "in the Entity Data Model!"), enumType);

        assertFalse(getDefaultErrorMessage("isFlags is True but MUST be false for single-valued enumerations!"), enumType.isFlags());
        break;
      case TypeMappings.DataDictionaryTypes.MULTI_ENUM:
        if (foundTypeName.contentEquals(TypeMappings.ODataTypes.STRING)) {
          LOG.error(getDefaultErrorMessage("String types are not allowed for enumerated fields at the current time.", "\nSee RCP-031 for further information:",
              "https://members.reso.org/pages/viewpage.action?pageId=67962918#RCP-WEBAPI-031DataDictionaryRepresentationintheWebAPI-DataTypeMappings.1"));
        }

        assertFalse(getDefaultErrorMessage("Enumerated data type MUST declare a unique nominal type.",
            "Found primitive type of", foundTypeName, "\nSee: http://docs.oasis-open.org/odata/odata/v4.0/errata03/os/complete/part3-csdl/odata-v4.0-errata03-os-part3-csdl-complete.html#_Toc453752565"),
            isPrimitiveType);

        //check for enum type by FQDN in the Edm cached in the container
        enumType = container.getEdm().getEnumType(new FullQualifiedName(foundTypeName));

        isIntegerType = enumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString().contentEquals(TypeMappings.ODataTypes.INT16)
            || enumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString().contentEquals(TypeMappings.ODataTypes.INT32)
            || enumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString().contentEquals(TypeMappings.ODataTypes.INT64);

        assertTrue(getDefaultErrorMessage("Enumerated Types MUST use an underlying type of",
            TypeMappings.ODataTypes.INT16, "OR", TypeMappings.ODataTypes.INT32,  "OR", TypeMappings.ODataTypes.INT64), isIntegerType);

        assertNotNull(getDefaultErrorMessage(
            "could not find a definition for", foundTypeName, "in the Entity Data Model!"), enumType);

        boolean isCollection = container.getFieldMap().get(currentResourceName.get()).get(fieldName).isCollection();

        if (isCollection) {
          LOG.info("Found Collection of Enumerations for " + foundTypeName +
              " with the following members: \n\t" + container.getEdm().getEnumType(new FullQualifiedName(foundTypeName)).getMemberNames());
        } else {
          assertTrue(getDefaultErrorMessage("Multi-Enumerations MUST have IsFlags=true"), enumType.isFlags());
          LOG.info("Found Edm.EnumType Enumerations with IsFlags=true for " + foundTypeName +
              " with the following members: \n\t" + container.getEdm().getEnumType(new FullQualifiedName(foundTypeName)).getMemberNames());
        }
        break;
      case TypeMappings.DataDictionaryTypes.TIMESTAMP:
        assertTrue(getDefaultErrorMessage(fieldName, "MUST map to", assertedTypeName, "but found", foundTypeName),
            foundTypeName.contentEquals(TypeMappings.ODataTypes.DATETIME_OFFSET));
        break;
      default:
        fail(getDefaultErrorMessage("could not find data type mapping for", assertedTypeName));
        break;
    }
  }

  @And("{string} precision SHOULD be less than or equal to the RESO Suggested Max Length of {int}")
  public void precisionSHOULDBeLessThanOrEqualToTheRESOSuggestedMaxLengthOf(String fieldName, Integer suggestedPrecision) {
    Integer precision = container.getFieldMap(currentResourceName.get()) != null
        && container.getFieldMap(currentResourceName.get()).containsKey(fieldName)
        ? container.getFieldMap(currentResourceName.get()).get(fieldName).getPrecision() : null;

    if (precision != null && precision > suggestedPrecision) {
      LOG.warn("Precision for field " + fieldName + "SHOULD be less than or equal to the RESO Suggested Max Length of " + suggestedPrecision
      + " but was " + precision);
    }
  }

  @And("{string} scale SHOULD be less than or equal to the RESO Suggested Max Scale of {int}")
  public void scaleSHOULDBeLessThanOrEqualToTheRESOSuggestedMaxScaleOf(String fieldName, Integer suggestedMaxScale) {
    Integer scale = container.getFieldMap(currentResourceName.get()) != null
        && container.getFieldMap(currentResourceName.get()).containsKey(fieldName)
        ? container.getFieldMap(currentResourceName.get()).get(fieldName).getPrecision() : null;

    if (scale != null && scale > suggestedMaxScale) {
      LOG.warn("Precision for field " + fieldName + "SHOULD be less than or equal to the RESO Suggested Max Length of " + suggestedMaxScale
          + " but was " + scale);
    }
  }

  @And("{string} length SHOULD be less than or equal to the RESO Suggested Max Length of {int}")
  public void lengthSHOULDBeLessThanOrEqualToTheRESOSuggestedMaxLengthOf(String fieldName, Integer suggestedMaxLength) {
    Integer length = container.getFieldMap(currentResourceName.get()) != null
        && container.getFieldMap(currentResourceName.get()).containsKey(fieldName)
        ? container.getFieldMap(currentResourceName.get()).get(fieldName).getPrecision() : null;

    if (length != null && length > suggestedMaxLength) {
      LOG.warn("Precision for field " + fieldName + "SHOULD be less than or equal to the RESO Suggested Max Length of " + suggestedMaxLength
          + " but was " + length);
    }
  }

  @Then("{string} standard enumeration values exist in the metadata")
  public void enumValuesExistInTheMetadata(String lookupName) {
  }

  @And("{string} MUST only contain enum values found in the metadata")
  public void mustOnlyContainEnumValuesFoundInTheMetadata(String lookupName) {
  }

  @And("{string} MUST contain only standard enumerations")
  public void mustContainOnlyStandardEnumerations(String fieldName) {
  }

  @And("{string} MUST contain at least one standard enumeration")
  public void mustContainAtLeastOneStandardEnumeration(String enumName) {
  }

  @When("metadata are checked for synonyms")
  public void metadataAreCheckedForSynonyms() {
  }

  @Then("field synonyms MUST NOT exist in the metadata")
  public void fieldSynonymsMUSTNOTExistInTheMetadata() {
  }

  @And("enumeration synonyms MUST NOT exist in the metadata")
  public void enumerationSynonymsMUSTNOTExistInTheMetadata() {
  }

  @When("metadata are checked for similar standard names")
  public void metadataAreCheckedForSimilarStandardNames() {
  }

  @Then("fields that are similar to standard names MAY cause certification to fail")
  public void fieldsThatAreSimilarToStandardNamesMAYCauseCertificationToFail() {
  }

  @And("enumerations that are similar to standard names MAY cause Certification to fail")
  public void enumerationsThatAreSimilarToStandardNamesMAYCauseCertificationToFail() {
  }

  @When("metadata are checked for substring matches of standard names")
  public void metadataAreCheckedForSubstringMatchesOfStandardNames() {
  }

  @Then("fields with substring matches of standard names MAY cause certification to fail")
  public void fieldsWithSubstringMatchesOfStandardNamesMAYCauseCertificationToFail() {
  }

  @And("enumerations with substring matches of standard names MAY cause certification to fail")
  public void enumerationsWithSubstringMatchesOfStandardNamesMAYCauseCertificationToFail() {
  }
}
