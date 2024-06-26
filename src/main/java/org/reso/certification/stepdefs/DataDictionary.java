package org.reso.certification.stepdefs;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.edm.xml.XMLMetadata;
import org.apache.olingo.commons.api.edm.EdmEnumType;
import org.apache.olingo.commons.api.edm.EdmMember;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlEnumMember;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.commander.Commander;
import org.reso.commander.common.TestUtils;
import org.reso.commander.common.TestUtils.TypeMappings;
import org.reso.commander.common.Utils;
import org.reso.models.Settings;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;
import static org.reso.certification.codegen.WorksheetProcessor.DEFAULT_DATA_DICTIONARY_VERSION;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;
import static org.reso.commander.common.TestUtils.failAndExitWithErrorMessage;
import static org.reso.commander.common.Utils.pluralize;
import static org.reso.commander.common.Utils.wrapColumns;

public class DataDictionary {
  protected static final String PATH_TO_METADATA_ARG = "pathToMetadata";
  protected static final String PATH_TO_RESOSCRIPT_ARG = "pathToRESOScript";
  private static final Logger LOG = LogManager.getLogger(DataDictionary.class);
  //local variables
  private static final AtomicReference<String> currentResourceName = new AtomicReference<>();
  private static final AtomicReference<Map<String, EdmMember>> foundMembers = new AtomicReference<>(new LinkedHashMap<>());
  private static final AtomicReference<Set<EdmMember>> foundStandardMembers = new AtomicReference<>(new LinkedHashSet<>());
  private static final AtomicReference<Map<String, String>> currentLookups = new AtomicReference<>(new LinkedHashMap<>());
  //named args
  private static final String SHOW_RESPONSES_ARG = "showResponses";
  private static final String USE_STRICT_MODE_ARG = "strict";
  private static final String LOOKUP_VALUE = "lookupValue";
  private static final String DD_VERSION_ARG = "version";
  //extract any params here
  private static final boolean showResponses = Boolean.parseBoolean(System.getProperty(SHOW_RESPONSES_ARG));
  private static final String version = System.getProperty(DD_VERSION_ARG, DEFAULT_DATA_DICTIONARY_VERSION);
  public static final String REFERENCE_METADATA = "RESODataDictionary-" + version + ".xml";
  //strict mode is enabled by default
  private static final boolean strictMode = System.getProperty(USE_STRICT_MODE_ARG) == null
      || Boolean.parseBoolean(System.getProperty(USE_STRICT_MODE_ARG));
  private static Scenario scenario;
  private static XMLMetadata referenceMetadata = null;
  private static boolean isMetadataValid = false;
  private final String pathToMetadata = System.getProperty(PATH_TO_METADATA_ARG);
  private final String pathToRESOScript = System.getProperty(PATH_TO_RESOSCRIPT_ARG);
  //derived variables
  private final boolean isUsingRESOScript = pathToRESOScript != null;
  private final boolean isUsingMetadata = pathToMetadata != null;
  @Inject
  WebAPITestContainer container;

  @Before
  public void beforeStep(Scenario scenario) {
    DataDictionary.scenario = scenario;
  }

  @Given("a RESOScript or Metadata file are provided")
  public void aRESOScriptOrMetadataFileAreProvided() {
    if (pathToRESOScript == null && pathToMetadata == null) {
      failAndExitWithErrorMessage("one of pathToRESOScript OR pathToMetadata MUST be present in command arguments, see README", scenario);
    }
  }

  @And("a test container was successfully created from the given RESOScript file")
  public void aTestContainerWasSuccessfullyCreatedFromTheGivenRESOScriptFile() {
    if (isUsingRESOScript) {
      container.initialize();
      container.setShowResponses(showResponses);
    }
    container.setDataDictionaryVersion(version);
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
        container.setPathToRESOScript(System.getProperty(PATH_TO_RESOSCRIPT_ARG));
      }

      if (container.getPathToRESOScript() == null) {
        failAndExitWithErrorMessage("pathToRESOScript must be present in command arguments, see README.", scenario);
      }
    } else {
      assumeTrue(true);
    }
  }

  @Then("Client Settings and Parameters can be read from the RESOScript")
  public void clientSettingsAndParametersCanBeReadFromTheRESOScript() {
    if (isUsingRESOScript) {
      if (container.getSettings() == null) {
        container.setSettings(Settings.loadFromRESOScript(new File(System.getProperty(PATH_TO_RESOSCRIPT_ARG))));
        if (container.getPathToRESOScript() == null) {
          failAndExitWithErrorMessage("Settings could not be loaded!", scenario);
        }
        LOG.info("RESOScript loaded successfully!");
      }
    }
  }

  @And("the test container uses an Authorization Code or Client Credentials for authentication")
  public void theTestContainerUsesAnAuthorizationCodeOrClientCredentialsForAuthentication() {
    if (isUsingRESOScript) {
      if (container.getCommander() == null) {
        failAndExitWithErrorMessage("Commander instance was null! Cannot continue.", scenario);
      }

      if (!(container.getCommander().isAuthTokenClient()
          || (container.getCommander().isOAuth2Client() && container.getCommander().hasValidAuthConfig()))) {
        failAndExitWithErrorMessage("Commander must either have a valid Authorization Code or Client Credentials configuration!", scenario);
      }
    }
  }

  @When("a metadata file is provided")
  public void aMetadataFileIsProvided() {
    if (isUsingMetadata) {
      boolean result = pathToMetadata != null && Files.exists(Paths.get(pathToMetadata));
      if (!result) {
        failAndExitWithErrorMessage("Path to given metadata file does not exist: " + PATH_TO_METADATA_ARG + "=" + pathToMetadata, scenario);
      }
    }
  }

  @And("valid metadata are loaded into the test container")
  public void validMetadataAreLoadedIntoTheTestContainer() {
    if (isUsingMetadata && container.getXMLMetadata() == null) {
      try {
        final String xmlMetadataString = new String(Files.readAllBytes(Paths.get(pathToMetadata)));

        if (!Commander.validateXML(xmlMetadataString)) {
          failAndExitWithErrorMessage("XML Metadata MUST be valid XML according to the OASIS XSDs!", scenario);
        }
        container.setXMLResponseData(xmlMetadataString);
        container.setXMLMetadata(Commander.deserializeXMLMetadata(xmlMetadataString, container.getCommander().getClient()));

        if (!Commander.validateMetadata(container.getXMLMetadata(), container.getCommander().getClient())) {
          failAndExitWithErrorMessage("XML Metadata MUST be valid OData XML Metadata!", scenario);
        }

        container.setEdm(Commander.deserializeEdm(xmlMetadataString, container.getCommander().getClient()));
        if (!Commander.validateMetadata(container.getEdm(), container.getCommander().getClient())) {
          failAndExitWithErrorMessage("Edm metadata MUST be valid!", scenario);
        }

        //if we have gotten to this point without exceptions, then metadata are valid
        container.validateMetadata();
        isMetadataValid = container.hasValidMetadata();

        //create metadata report
        Commander.generateMetadataReport(container.getEdm(), container.getDataDictionaryVersion());

        //the container needs a field map built when the metadata is being loaded from a file
        container.buildFieldMap();

      } catch (IOException e) {
        failAndExitWithErrorMessage(getDefaultErrorMessage(e), scenario);
      }
    }
  }

  @And("valid metadata were retrieved from the server")
  public void validMetadataWereRetrievedFromTheServer() {
    try {
      if (isUsingRESOScript && container.getShouldValidateMetadata()) {
        //request metadata from server using service root in RESOScript file
        TestUtils.assertXMLMetadataAreRequestedFromTheServer(container, scenario);

        if (container.getResponseCode() != HttpStatus.SC_OK) {
          failAndExitWithErrorMessage("Could not retrieve metadata from " + container.getServiceRoot() + "/$metadata", scenario);
        }

        //metadata validation tests
        TestUtils.assertValidXMLMetadata(container, scenario);
        TestUtils.assertXmlMetadataContainsEdm(container, scenario);
        TestUtils.assertXMLMetadataHasValidServiceDocument(container, scenario);

        //build field map and ensure it's not null
        assertNotNull(container.getFieldMap());

        //everything succeeded, mark validation successful
        container.setShouldValidateMetadata(false);

        //if we have gotten to this point without exceptions, then metadata are valid
        isMetadataValid = container.hasValidMetadata();

        if (!isMetadataValid) {
          failAndExitWithErrorMessage("OData XML Metadata MUST be valid!", scenario);
        }

        //save metadata locally
        Utils.createFile("build" + File.separator + "certification" + File.separator + "results",
            "metadata.xml", container.getXMLResponseData());

        //create metadata report
        final String metadataReport = Commander.generateMetadataReport(container.getEdm(), container.getDataDictionaryVersion());
        if (metadataReport == null || metadataReport.isEmpty()) {
          failAndExitWithErrorMessage("Could not create metadata-report.json!", LOG);
        }
      }
    } catch (Exception ex) {
      failAndExitWithErrorMessage(getDefaultErrorMessage(ex), scenario);
    }
  }

  @When("{string} exists in the {string} metadata")
  public void existsInTheMetadata(String fieldName, String resourceName) {

    if (strictMode && !isMetadataValid) {
      failAndExitWithErrorMessage("Metadata validation failed, but is required to pass when using strict mode!", scenario);
    }

    assertNotNull(getDefaultErrorMessage("field name cannot be null!"), fieldName);
    assertNotNull(getDefaultErrorMessage("resource name cannot be null!"), resourceName);

    currentResourceName.set(resourceName);

    if (container.getFieldMap().containsKey(currentResourceName.get())) {
      //add keyed hash for the given resource name if it doesn't exist
      if (!container.getFieldMap().containsKey(currentResourceName.get())) {
        container.getFieldMap().put(currentResourceName.get(), new LinkedHashMap<>());
      }

      //if the field for the given resource contains the given field, then add it to the standard field map
      if (container.getFieldMap(currentResourceName.get()).containsKey(fieldName)) {
        container.getFieldMap().get(resourceName).put(fieldName, container.getFieldMap(resourceName).get(fieldName));
      }
    }

    if (isFieldContainedInMetadata(fieldName)) LOG.info("\nFound Field: " + fieldName);
    assumeTrue("Skipped: " + fieldName, isFieldContainedInMetadata(fieldName));
  }

  @Then("{string} MUST be {string} data type")
  public void mustBeDataType(String fieldName, String dataTypeName) {
    String foundTypeName = container.getFieldMap().get(currentResourceName.get()).get(fieldName).getType();
    assertDataTypeMapping(fieldName, dataTypeName, foundTypeName);
    LOG.info("Field data type: " + foundTypeName);
  }

  @And("{string} MAY contain any of the following standard lookups")
  public void mayContainAnyOfTheFollowingStandardLookups(String fieldName, DataTable dataTable) {
    currentLookups.set(dataTable.asMap(String.class, String.class));
    foundMembers.set(getFoundMembers(fieldName));
    foundStandardMembers.set(getFoundStandardMembers(foundMembers.get(), dataTable));

    if (foundStandardMembers.get().isEmpty()) {
      scenario.log("No RESO Standard Enumerations found for field: " + fieldName);
    }
  }

  @And("{string} MUST contain at least one of the following standard lookups")
  public void mustContainAtLeastOneOfTheFollowingStandardLookups(String fieldName, DataTable dataTable) {
    foundMembers.set(getFoundMembers(fieldName));
    foundStandardMembers.set(getFoundStandardMembers(foundMembers.get(), dataTable));

    assertFalse(getDefaultErrorMessage(fieldName, "MUST contain at least one standard enumeration!"),
        foundStandardMembers.get().isEmpty());
  }

  /**
   * Gets the set of found enumeration members for the given field
   *
   * @param fieldName the field name of the field whose enumeration to get
   * @return a map of lookup value and lookup display name
   */
  private Map<String, EdmMember> getFoundMembers(String fieldName) {
    final FullQualifiedName fqdn = container.getFieldMap(currentResourceName.get()).get(fieldName).getTypeAsFQNObject();
    final EdmEnumType enumType = container.getEdm().getEnumType(fqdn);

    final Map<String, EdmMember> foundMembers = new LinkedHashMap<>();
    enumType.getMemberNames().forEach(name -> foundMembers.put(name, enumType.getMember(name)));

    if (!foundMembers.isEmpty()) {
      LOG.info("Found " + foundMembers.size() + " Enumeration"
          + pluralize(foundMembers.size()) + ": "
          + Utils.wrapColumns("[" + String.join(", ", foundMembers.keySet()) + "]", 80, "\n\t"));
    }

    return foundMembers;
  }

  /**
   * Gets the set of found RESO Standard member names in a given found members Map
   *
   * @param members   a map of the enumeration members that was found in the metadata for a given field
   * @param dataTable data table of standard lookup value and lookup display name items
   * @return a set of EdmMembers from the Lookup metadata that contains the standard items in the data table
   */
  private Set<EdmMember> getFoundStandardMembers(Map<String, EdmMember> members, DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

    Set<String> standardMembers = rows.stream().map(row -> row.get(LOOKUP_VALUE)).collect(Collectors.toCollection(LinkedHashSet::new));
    Set<String> foundMembers = members.keySet();
    Set<String> difference = Sets.difference(foundMembers, standardMembers);
    Set<String> intersection = Sets.intersection(foundMembers, standardMembers);

    if (!intersection.isEmpty()) {
      LOG.info("Found " + intersection.size() + " RESO Standard Enumeration"
          + pluralize(intersection.size()) + ": " + Utils.wrapColumns("[" + String.join(", ", intersection) + "]", "\n\t"));
    }

    if (!standardMembers.isEmpty()) {
      LOG.info(standardMembers.size() + " Possible RESO Standard Enumeration"
          + pluralize(standardMembers.size()) + ": " + Utils.wrapColumns("[" + String.join(", ", standardMembers) + "]", "\n\t"));
    }

    if (!difference.isEmpty()) {
      LOG.info("Found " + difference.size() + " Non-standard Enumeration"
          + pluralize(difference.size()) + ": " + Utils.wrapColumns("[" + String.join(", ", difference) + "]", "\n\t"));
    }
    return intersection.stream().map(members::get).collect(Collectors.toCollection(LinkedHashSet::new));
  }

  /**
   * Determines whether the given field name has the correct data type, according to the mappings in the
   * Data Dictionary specification and RCP-031.
   *
   * @param fieldName        the field name of the field to assert the mapping for
   * @param assertedTypeName the asserted type name (from the BDD tests, usually)
   * @param foundTypeName    the type name that was found in the metadata
   * @apiNote SEE: <a href="https://docs.google.com/document/d/15DFf9kDX_mlGCJVOch2fztl8W5h-yd18N0_03Sb4HwM/edit#heading=h.dw8owdmv988f">...</a>
   */
  protected final void assertDataTypeMapping(String fieldName, String assertedTypeName, String foundTypeName) {
    assertNotNull(getDefaultErrorMessage("you must specify a Data Dictionary type name to check!"), assertedTypeName);
    assertNotNull(getDefaultErrorMessage("you must specify an Edm type name to check!"), foundTypeName);
    EdmEnumType enumType;
    boolean isIntegerType;

    final boolean isPrimitiveType = Arrays.stream(new String[]{
        TypeMappings.ODataTypes.INT16,
        TypeMappings.ODataTypes.INT32,
        TypeMappings.ODataTypes.INT64,
        TypeMappings.ODataTypes.STRING,
        TypeMappings.ODataTypes.DATE,
        TypeMappings.ODataTypes.DATETIME_OFFSET,
        TypeMappings.ODataTypes.BOOLEAN,
        TypeMappings.ODataTypes.DECIMAL,
        TypeMappings.ODataTypes.DOUBLE
    }).anyMatch(foundTypeName::contentEquals);

    final boolean isCollection = container.getFieldMap().get(currentResourceName.get()).get(fieldName).isCollection();

    //TODO: make functions
    switch (assertedTypeName) {
      case TypeMappings.DataDictionaryTypes.STRING:
        assertTrue(wrapColumns(getDefaultErrorMessage(fieldName, "MUST map to", TypeMappings.ODataTypes.STRING, "but found", foundTypeName)),
            foundTypeName.contentEquals(TypeMappings.ODataTypes.STRING));
        break;
      case TypeMappings.DataDictionaryTypes.DATE:
        assertTrue(wrapColumns(getDefaultErrorMessage(fieldName, "MUST map to", TypeMappings.ODataTypes.DATE, "but found", foundTypeName)),
            foundTypeName.contentEquals(TypeMappings.ODataTypes.DATE));
        break;
      case TypeMappings.DataDictionaryTypes.DECIMAL:
        assertTrue(wrapColumns(getDefaultErrorMessage(fieldName, "MUST map to", TypeMappings.ODataTypes.DECIMAL, "OR",
                TypeMappings.ODataTypes.DOUBLE, "but found", foundTypeName)),
            foundTypeName.contentEquals(TypeMappings.ODataTypes.DECIMAL)
                || foundTypeName.contentEquals(TypeMappings.ODataTypes.DOUBLE));
        break;
      case TypeMappings.DataDictionaryTypes.INTEGER:
        isIntegerType = foundTypeName.contentEquals(TypeMappings.ODataTypes.INT16)
            || foundTypeName.contentEquals(TypeMappings.ODataTypes.INT32)
            || foundTypeName.contentEquals(TypeMappings.ODataTypes.INT64);

        assertTrue(wrapColumns(getDefaultErrorMessage(fieldName, "MUST map to", TypeMappings.ODataTypes.INT64,
                "OR", TypeMappings.ODataTypes.INT32,
                "OR", TypeMappings.ODataTypes.INT16, "but found", foundTypeName)),
            isIntegerType);
        break;
      case TypeMappings.DataDictionaryTypes.BOOLEAN:
        assertTrue(getDefaultErrorMessage(fieldName, "MUST map to", TypeMappings.ODataTypes.BOOLEAN, "but found", foundTypeName),
            foundTypeName.contentEquals(TypeMappings.ODataTypes.BOOLEAN));
        break;
      case TypeMappings.DataDictionaryTypes.SINGLE_ENUM:
        assertFalse(getDefaultErrorMessage("Single enumerations cannot be collections!"), isCollection);
        if (foundTypeName.contentEquals(TypeMappings.ODataTypes.STRING)) {
          LOG.info("Found data type of Edm.String for field: " + fieldName);
        } else {
          assertFalse(getDefaultErrorMessage("Enumerated data types MUST declare a unique nominal (lookup) type.",
                  "\nFound primitive type of", foundTypeName)
                  + "\nSee: http://docs.oasis-open.org/odata/odata/v4.0/errata03/os/complete/part3-csdl/odata-v4.0-errata03-os-part3-csdl-complete.html#_Toc453752565",
              isPrimitiveType);

          //check for enum type by FQDN in the Edm cached in the container
          enumType = container.getEdm().getEnumType(new FullQualifiedName(foundTypeName));

          isIntegerType = enumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString().contentEquals(TypeMappings.ODataTypes.INT16)
              || enumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString().contentEquals(TypeMappings.ODataTypes.INT32)
              || enumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString().contentEquals(TypeMappings.ODataTypes.INT64);

          assertTrue(wrapColumns(getDefaultErrorMessage("Enumerated Types MUST use an underlying type of",
              TypeMappings.ODataTypes.INT16, "OR", TypeMappings.ODataTypes.INT32, "OR", TypeMappings.ODataTypes.INT64)), isIntegerType);

          assertNotNull(wrapColumns(getDefaultErrorMessage(
              "could not find a definition for", foundTypeName, "in the Entity Data Model!")), enumType);

          assertFalse(wrapColumns(getDefaultErrorMessage("IsFlags=\"true\" but MUST be false for single-valued enumerations!")),
              enumType.isFlags());
        }

        break;
      case TypeMappings.DataDictionaryTypes.MULTI_ENUM:

        if (foundTypeName.contentEquals(TypeMappings.ODataTypes.STRING)) {
          if (!isCollection) {
            fail(getDefaultErrorMessage("Multiple enumerations MUST use Collection(Edm.String)! Found Edm.String."));
          } else {
            LOG.info("Found data type of Collection(Edm.String) for field: " + fieldName);
          }
        } else {
          assertFalse(wrapColumns(getDefaultErrorMessage("Enumerated data type MUST declare a unique nominal type.",
                  "Found primitive type of", foundTypeName,
                  "\nSee: http://docs.oasis-open.org/odata/odata/v4.0/errata03/os/complete/part3-csdl/odata-v4.0-errata03-os-part3-csdl-complete.html#_Toc453752565")),
              isPrimitiveType);

          //check for enum type by FQDN in the Edm cached in the container
          enumType = container.getEdm().getEnumType(new FullQualifiedName(foundTypeName));

          final String underlyingTypeName = enumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString();
          isIntegerType = TypeMappings.ODataTypes.INT16.contentEquals(underlyingTypeName)
              || TypeMappings.ODataTypes.INT32.contentEquals(underlyingTypeName)
              || TypeMappings.ODataTypes.INT64.contentEquals(underlyingTypeName);

          assertTrue(wrapColumns(getDefaultErrorMessage("Enumerated Types MUST use an underlying type of",
                  TypeMappings.ODataTypes.INT16, "OR", TypeMappings.ODataTypes.INT32, "OR", TypeMappings.ODataTypes.INT64)),
              isIntegerType);

          assertNotNull(wrapColumns(getDefaultErrorMessage(
              "could not find a definition for", foundTypeName, "in the Entity Data Model!")), enumType);

          if (!isCollection) {
            assertTrue(wrapColumns(getDefaultErrorMessage("Multi-Enumerations MUST have IsFlags=\"true\"")),
                enumType.isFlags());
          }
        }

        break;
      case TypeMappings.DataDictionaryTypes.TIMESTAMP:
        assertTrue(wrapColumns(getDefaultErrorMessage(fieldName, "MUST map to", TypeMappings.ODataTypes.DATETIME_OFFSET, "but found", foundTypeName)),
            foundTypeName.contentEquals(TypeMappings.ODataTypes.DATETIME_OFFSET));
        break;
      default:
        fail(wrapColumns(getDefaultErrorMessage("could not find data type mapping for", assertedTypeName)));
        break;
    }
  }

  @And("{string} precision SHOULD be equal to the RESO Suggested Max Precision of {int}")
  public void precisionSHOULDBeEqualToTheRESOSuggestedMaxPrecisionOf(String fieldName, Integer suggestedPrecision) {
    Integer precision = container.getFieldMap(currentResourceName.get()) != null
        && container.getFieldMap(currentResourceName.get()).containsKey(fieldName)
        ? container.getFieldMap(currentResourceName.get()).get(fieldName).getPrecision() : null;

    if (!Objects.equals(precision, suggestedPrecision)) {
      scenario.log("Precision for field " + fieldName + " SHOULD be equal to the RESO Suggested Max Precision of " + suggestedPrecision
          + " but was " + precision);
    } else {
      scenario.log("Precision for field " + fieldName + " is equal to the RESO Suggested Max Scale of " + suggestedPrecision);
    }
  }

  @And("{string} scale SHOULD be equal to the RESO Suggested Max Scale of {int}")
  public void scaleSHOULDBeEqualToTheRESOSuggestedMaxScaleOf(String fieldName, Integer suggestedMaxScale) {
    Integer scale = container.getFieldMap(currentResourceName.get()) != null
        && container.getFieldMap(currentResourceName.get()).containsKey(fieldName)
        ? container.getFieldMap(currentResourceName.get()).get(fieldName).getScale() : null;

    if (!Objects.equals(scale, suggestedMaxScale)) {
      scenario.log("Scale for field " + fieldName + " SHOULD be equal to the RESO Suggested Max Scale of " + suggestedMaxScale
          + " but was " + scale);
    } else {
      scenario.log("Scale for field " + fieldName + " is equal to the RESO Suggested Max Scale of " + suggestedMaxScale);
    }
  }

  @And("{string} length SHOULD be equal to the RESO Suggested Max Length of {int}")
  public void lengthSHOULDBeEqualToTheRESOSuggestedMaxLengthOf(String fieldName, Integer suggestedMaxLength) {
    Integer length = container.getFieldMap(currentResourceName.get()) != null
        && container.getFieldMap(currentResourceName.get()).containsKey(fieldName)
        ? container.getFieldMap(currentResourceName.get()).get(fieldName).getMaxLength() : null;

    if (!Objects.equals(length, suggestedMaxLength)) {
      scenario.log("Length for field " + fieldName + " SHOULD be equal to the RESO Suggested Max Length of " + suggestedMaxLength
          + " but was " + length);
    } else {
      scenario.log("Length for field " + fieldName + " is equal to the RESO Suggested Max Length of " + length);
    }
  }

  @And("{string} MUST contain only standard enumerations")
  public void mustContainOnlyStandardEnumerations(String fieldName) {
    final String REFERENCE_ENUMS_NAMESPACE = "org.reso.metadata.enums";
    FullQualifiedName fqn = container.getFieldMap().get(currentResourceName.get()).get(fieldName).getTypeAsFQNObject();

    Set<String> foundMembers = new LinkedHashSet<>(container.getEdm().getEnumType(fqn).getMemberNames());
    Set<String> standardMembers = getReferenceMetadata().getSchema(REFERENCE_ENUMS_NAMESPACE).getEnumType(fieldName)
        .getMembers().stream()
        .map(CsdlEnumMember::getName)
        .collect(Collectors.toCollection(LinkedHashSet::new));

    assertTrue("\n" + getDefaultErrorMessage("Lookups for field", fieldName, "MUST only contain Standard Enumerations!",
            "\nFound the following non-standard enumerations:", Utils.wrapColumns("[" + String.join(", ", Sets.difference(foundMembers, standardMembers)) + "]")) + "\n",
        standardMembers.containsAll(foundMembers));

    LOG.info("PASSED: Field \"" + fieldName + "\" only contains Standard Names!");
  }

  @Given("that the following synonyms for {string} DO NOT exist in the {string} metadata")
  public void theFollowingSynonymsForDONOTExistInTheMetadata(String fieldName, String resourceName, List<String> synonyms) {
    if (container.getFieldMap(resourceName) == null) {
      assumeTrue("\"" + resourceName + "\" not found in metadata!", true);
    } else {
      synonyms.forEach(synonym ->
          assertFalse(wrapColumns(getDefaultErrorMessage("Synonym", "\"" + synonym + "\"", "of fieldName", "\"" + fieldName + "\"", "found in the metadata!",
                  "\nSynonyms are not allowed!")),
              container.getFieldMap(resourceName).containsKey(synonym)));
    }
  }

  private XMLMetadata getReferenceMetadata() {
    if (referenceMetadata == null) {
      URL resource = Thread.currentThread().getContextClassLoader().getResource(REFERENCE_METADATA);
      assert resource != null;
      referenceMetadata = Commander
          .deserializeXMLMetadata(Commander.convertInputStreamToString(Commander.deserializeFileFromPath(resource.getPath())),
              container.getCommander().getClient());
    }
    return referenceMetadata;
  }

  private boolean isFieldContainedInMetadata(String fieldName) {
    return container.getFieldMap().containsKey(currentResourceName.get())
        && container.getFieldMap().get(currentResourceName.get()).containsKey(fieldName);
  }
}