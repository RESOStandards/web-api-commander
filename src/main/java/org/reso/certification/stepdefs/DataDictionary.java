package org.reso.certification.stepdefs;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.edm.xml.XMLMetadata;
import org.apache.olingo.commons.api.edm.EdmEnumType;
import org.apache.olingo.commons.api.edm.EdmMember;
import org.apache.olingo.commons.api.edm.EdmNamed;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlEnumMember;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.commander.Commander;
import org.reso.commander.common.TestUtils;
import org.reso.commander.common.TestUtils.TypeMappings;
import org.reso.commander.common.Utils;
import org.reso.models.IgnoredItem;
import org.reso.models.Settings;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;
import static org.reso.commander.Commander.NOT_OK;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;
import static org.reso.commander.common.Utils.pluralize;
import static org.reso.commander.common.Utils.wrapColumns;

public class DataDictionary {
  private static final Logger LOG = LogManager.getLogger(DataDictionary.class);

  @Inject
  WebAPITestContainer container;

  //TODO: make this a dynamic property based on DD version
  public static final String REFERENCE_WORKSHEET = "RESODataDictionary-1.7.xlsx";
  public static final String REFERENCE_METADATA = "RESODataDictionary-1.7.edmx";

  //local variables
  private static final AtomicReference<String> currentResourceName = new AtomicReference<>();
  private static final AtomicReference<Map<String, EdmMember>> foundMembers = new AtomicReference<>(new LinkedHashMap<>());
  private static final AtomicReference<Set<EdmMember>> foundStandardMembers = new AtomicReference<>(new LinkedHashSet<>());
  private static final AtomicReference<Map<String, String>> currentLookups = new AtomicReference<>();
  private static final AtomicReference<Map<String, Map<String, Set<String>>>> ignoredItems = new AtomicReference<>(new LinkedHashMap<>());

  private static XMLMetadata referenceMetadata = null;
  private static boolean areMetadataValid = false;

  //named args
  private static final String SHOW_RESPONSES_ARG = "showResponses";
  private static final String USE_STRICT_MODE_ARG = "strict";
  private static final String PATH_TO_METADATA_ARG = "pathToMetadata";
  private static final String PATH_TO_RESOSCRIPT_ARG = "pathToRESOScript";
  private static final String LOOKUP_VALUE = "lookupValue";

  //extract any params here
  private final boolean showResponses = Boolean.parseBoolean(System.getProperty(SHOW_RESPONSES_ARG));
  private final boolean strictMode = Boolean.parseBoolean(System.getProperty(USE_STRICT_MODE_ARG));
  private final String pathToMetadata = System.getProperty(PATH_TO_METADATA_ARG);
  private final String pathToRESOScript = System.getProperty(PATH_TO_RESOSCRIPT_ARG);

  //derived variables
  private final boolean isUsingRESOScript = pathToRESOScript != null;
  private final boolean isUsingMetadata = pathToMetadata != null;


  @Given("a RESOScript or Metadata file are provided")
  public void aRESOScriptOrMetadataFileAreProvided() {
    if (pathToRESOScript == null && pathToMetadata == null) {
      LOG.error(getDefaultErrorMessage("one of pathToRESOScript OR pathToMetadata MUST be present in command arguments, see README"));
      System.exit(NOT_OK);
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

  @And("the test container uses an Authorization Code or Client Credentials for authentication")
  public void theTestContainerUsesAnAuthorizationCodeOrClientCredentialsForAuthentication() {
    if (isUsingRESOScript) {
      assertNotNull(container.getCommander());
      assertTrue("ERROR: Commander must either have a valid Authorization Code or Client Credentials configuration.",
          container.getCommander().isAuthTokenClient()
              || (container.getCommander().isOAuth2Client() && container.getCommander().hasValidAuthConfig()));
    }
  }

  @When("a metadata file is provided")
  public void aMetadataFileIsProvided() {
    boolean result = false;
    if (isUsingMetadata) {
      result = pathToMetadata != null && Files.exists(Paths.get(pathToMetadata));
      if (!result) {
        LOG.error(getDefaultErrorMessage("Path to given metadata file does not exist: " + PATH_TO_METADATA_ARG + "=" + pathToMetadata));
        System.exit(NOT_OK);
      }
    }
  }

  @And("valid metadata are loaded into the test container")
  public void validMetadataAreLoadedIntoTheTestContainer() {
    if (isUsingMetadata && container.getXMLMetadata() == null) {
      try {
        final String xmlMetadataString = new String(Files.readAllBytes(Paths.get(pathToMetadata)));

        assertTrue("XML Metadata MUST be valid XML according to the OASIS XSDs!",
            Commander.validateXML(xmlMetadataString));
        container.setXMLResponseData(xmlMetadataString);

        container.setXMLMetadata(Commander.deserializeXMLMetadata(xmlMetadataString, container.getCommander().getClient()));
        assertTrue("XML Metadata MUST be valid OData XML Metadata!",
            Commander.validateMetadata(container.getXMLMetadata(), container.getCommander().getClient()));

        container.setEdm(Commander.deserializeEdm(xmlMetadataString, container.getCommander().getClient()));
        assertTrue("Edm metadata MUST be valid!",
            Commander.validateMetadata(container.getEdm(), container.getCommander().getClient()));

        //if we have gotten to this point without exceptions, then metadata are valid
        container.validateMetadata();
        areMetadataValid = container.hasValidMetadata();

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @And("valid metadata were retrieved from the server")
  public void validMetadataWereRetrievedFromTheServer() {
    boolean result = false;

    if (isUsingRESOScript && container.getShouldValidateMetadata()) {
      //request metadata from server using service root in RESOScript file
      TestUtils.assertXMLMetadataAreRequestedFromTheServer(container);

      if (container.getResponseCode() != HttpStatus.SC_OK) {
        LOG.error(getDefaultErrorMessage("Could not retrieve metadata from", container.getServiceRoot() + "/$metadata"));
        System.exit(NOT_OK);
      }

      //metadata validation tests
      TestUtils.assertValidXMLMetadata(container);
      TestUtils.assertXmlMetadataContainsEdm(container);
      TestUtils.assertXMLMetadataHasValidServiceDocument(container);

      //build field map and ensure it's not null
      assertNotNull(container.getFieldMap());

      //everything succeeded, mark validation successful
      container.setShouldValidateMetadata(false);

      //if we have gotten to this point without exceptions, then metadata are valid
      areMetadataValid = container.hasValidMetadata();
    }
  }

  @When("{string} exists in the {string} metadata")
  public void existsInTheMetadata(String fieldName, String resourceName) {

    if (strictMode && !areMetadataValid) {
      LOG.error(getDefaultErrorMessage("Metadata validation failed, but is required to pass when using strict mode!"));
      System.exit(NOT_OK);
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
    LOG.info("Field data type: " + foundTypeName);
    assertDataTypeMapping(fieldName, dataTypeName, foundTypeName);
  }

  @And("{string} MAY contain any of the following standard lookups")
  public void mayContainAnyOfTheFollowingStandardLookups(String fieldName, DataTable dataTable) {
    currentLookups.set(dataTable.asMap(String.class, String.class));
    foundMembers.set(getFoundMembers(fieldName));
    foundStandardMembers.set(getFoundStandardMembers(foundMembers.get(), dataTable));

    if (foundStandardMembers.get().size() == 0) {
      LOG.info("No RESO Standard Enumerations found for field: " + fieldName);
    }
  }

  @And("{string} MUST contain at least one of the following standard lookups")
  public void mustContainAtLeastOneOfTheFollowingStandardLookups(String fieldName, DataTable dataTable) {
    foundMembers.set(getFoundMembers(fieldName));
    foundStandardMembers.set(getFoundStandardMembers(foundMembers.get(), dataTable));

    assertTrue(getDefaultErrorMessage(fieldName, "MUST contain at least one standard enumeration!"),
        foundStandardMembers.get().size() > 0);
  }

  /**
   * Gets the set of found enumeration members for the given field
   * @param fieldName the field name of the field whose enumeration to get
   * @return a map of lookup value and lookup display name
   */
  private Map<String, EdmMember> getFoundMembers(String fieldName) {
    final FullQualifiedName fqdn = container.getFieldMap(currentResourceName.get()).get(fieldName).getTypeAsFQNObject();
    final EdmEnumType enumType = container.getEdm().getEnumType(fqdn);

    final Map<String, EdmMember> foundMembers = new LinkedHashMap<>();
    enumType.getMemberNames().forEach(name -> foundMembers.put(name, enumType.getMember(name)));

    if (foundMembers.size() > 0) {
      LOG.info("Found " + foundMembers.size() + " Enumeration"
          + pluralize(foundMembers.size()) + ": " + Utils.wrapColumns("[" + String.join(", ", foundMembers.keySet()) + "]", 80, "\n\t"));
    }

    return foundMembers;
  }

  /**
   * Gets the set of found RESO Standard member names in a given found members Map
   * @param members a map of the enumeration members that was found in the metadata for a given field
   * @param dataTable data table of standard lookup value and lookup display name items
   * @return a set of EdmMembers from the Lookup metadata that contains the standard items in the data table
   */
  private Set<EdmMember> getFoundStandardMembers(Map<String, EdmMember> members, DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

    Set<String> standardMembers = rows.stream().map(row -> row.get(LOOKUP_VALUE)).collect(Collectors.toCollection(LinkedHashSet::new));
    Set<String> foundMembers = members.keySet();
    Set<String> difference = Sets.difference(foundMembers, standardMembers);
    Set<String> intersection = Sets.intersection(foundMembers, standardMembers);

    if (intersection.size() > 0) {
      LOG.info("Found " + intersection.size() + " RESO Standard Enumeration"
          + pluralize(intersection.size()) + ": " + Utils.wrapColumns("[" + String.join(", ", intersection) + "]", "\n\t"));
    }

    if (standardMembers.size() > 0) {
      LOG.info(standardMembers.size() + " Possible RESO Standard Enumeration"
          + pluralize(standardMembers.size()) + ": " + Utils.wrapColumns("[" + String.join(", ", standardMembers) + "]", "\n\t"));
    }

    if (difference.size() > 0) {
      LOG.info("Found " + difference.size() + " Non-standard Enumeration"
          + pluralize(difference.size()) + ": " + Utils.wrapColumns("[" + String.join(", ", difference) + "]", "\n\t"));
    }
    return intersection.stream().map(members::get).collect(Collectors.toCollection(LinkedHashSet::new));
  }

  /**
   * Determines whether the given field name has the correct data type, according to the mappings in the
   * Data Dictionary specification and RCP-031.
   * @apiNote SEE: https://docs.google.com/document/d/15DFf9kDX_mlGCJVOch2fztl8W5h-yd18N0_03Sb4HwM/edit#heading=h.dw8owdmv988f
   * @param fieldName the field name of the field to assert the mapping for
   * @param assertedTypeName the asserted type name (from the BDD tests, usually)
   * @param foundTypeName the type name that was found in the metadata
   */
  protected final void assertDataTypeMapping(String fieldName, String assertedTypeName, String foundTypeName) {
    assertNotNull(getDefaultErrorMessage("you must specify a Data Dictionary type name to check!"), assertedTypeName);
    assertNotNull(getDefaultErrorMessage("you must specify an Edm type name to check!"), foundTypeName);
    EdmEnumType enumType;
    boolean isIntegerType;

    final boolean isPrimitiveType = Arrays.stream(new String[] {
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
        if (foundTypeName.contentEquals(TypeMappings.ODataTypes.STRING)) {
          LOG.error(wrapColumns(getDefaultErrorMessage("String types are not allowed for enumerated fields at the current time.", "\nSee RCP-031 for further information:",
              "https://members.reso.org/pages/viewpage.action?pageId=67962918#RCP-WEBAPI-031DataDictionaryRepresentationintheWebAPI-DataTypeMappings.1")));
        }

        assertFalse(wrapColumns(getDefaultErrorMessage("Enumerated data type MUST declare a unique nominal type.",
            "Found primitive type of", foundTypeName, "\nSee: http://docs.oasis-open.org/odata/odata/v4.0/errata03/os/complete/part3-csdl/odata-v4.0-errata03-os-part3-csdl-complete.html#_Toc453752565")),
            isPrimitiveType);

        //check for enum type by FQDN in the Edm cached in the container
        enumType = container.getEdm().getEnumType(new FullQualifiedName(foundTypeName));

        isIntegerType = enumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString().contentEquals(TypeMappings.ODataTypes.INT16)
            || enumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString().contentEquals(TypeMappings.ODataTypes.INT32)
            || enumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString().contentEquals(TypeMappings.ODataTypes.INT64);

        assertTrue(wrapColumns(getDefaultErrorMessage("Enumerated Types MUST use an underlying type of",
            TypeMappings.ODataTypes.INT16, "OR", TypeMappings.ODataTypes.INT32,  "OR", TypeMappings.ODataTypes.INT64)), isIntegerType);

        assertNotNull(wrapColumns(getDefaultErrorMessage(
            "could not find a definition for", foundTypeName, "in the Entity Data Model!")), enumType);

        assertFalse(wrapColumns(getDefaultErrorMessage("IsFlags=\"true\" but MUST be false for single-valued enumerations!")),
            enumType.isFlags());
        break;
      case TypeMappings.DataDictionaryTypes.MULTI_ENUM:
        if (foundTypeName.contentEquals(TypeMappings.ODataTypes.STRING)) {
          LOG.error(wrapColumns(getDefaultErrorMessage("String types are not allowed for enumerated fields at the current time.", "\nSee RCP-031 for further information:",
              "https://members.reso.org/pages/viewpage.action?pageId=67962918#RCP-WEBAPI-031DataDictionaryRepresentationintheWebAPI-DataTypeMappings.1")));
        }

        assertFalse(wrapColumns(getDefaultErrorMessage("Enumerated data type MUST declare a unique nominal type.",
            "Found primitive type of", foundTypeName,
            "\nSee: http://docs.oasis-open.org/odata/odata/v4.0/errata03/os/complete/part3-csdl/odata-v4.0-errata03-os-part3-csdl-complete.html#_Toc453752565")),
            isPrimitiveType);

        //check for enum type by FQDN in the Edm cached in the container
        enumType = container.getEdm().getEnumType(new FullQualifiedName(foundTypeName));

        isIntegerType =
               enumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString().contentEquals(TypeMappings.ODataTypes.INT16)
            || enumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString().contentEquals(TypeMappings.ODataTypes.INT32)
            || enumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString().contentEquals(TypeMappings.ODataTypes.INT64);

        assertTrue(wrapColumns(getDefaultErrorMessage("Enumerated Types MUST use an underlying type of",
            TypeMappings.ODataTypes.INT16, "OR", TypeMappings.ODataTypes.INT32,  "OR", TypeMappings.ODataTypes.INT64)),
            isIntegerType);

        assertNotNull(wrapColumns(getDefaultErrorMessage(
            "could not find a definition for", foundTypeName, "in the Entity Data Model!")), enumType);

        boolean isCollection = container.getFieldMap().get(currentResourceName.get()).get(fieldName).isCollection();
        if (!isCollection) {
          assertTrue(wrapColumns(getDefaultErrorMessage("Multi-Enumerations MUST have IsFlags=\"true\"")),
              enumType.isFlags());
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
      LOG.warn("Precision for field " + fieldName +  " SHOULD be equal to the RESO Suggested Max Precision of " + suggestedPrecision
          + " but was " + precision);
    } else {
      LOG.info("Precision for field " + fieldName +  " is equal to the RESO Suggested Max Scale of " + suggestedPrecision);
    }
  }

  @And("{string} scale SHOULD be equal to the RESO Suggested Max Scale of {int}")
  public void scaleSHOULDBeEqualToTheRESOSuggestedMaxScaleOf(String fieldName, Integer suggestedMaxScale) {
    Integer scale = container.getFieldMap(currentResourceName.get()) != null
        && container.getFieldMap(currentResourceName.get()).containsKey(fieldName)
        ? container.getFieldMap(currentResourceName.get()).get(fieldName).getScale() : null;

    if (!Objects.equals(scale, suggestedMaxScale)) {
      LOG.warn("Scale for field " + fieldName +  " SHOULD be equal to the RESO Suggested Max Scale of " + suggestedMaxScale
          + " but was " + scale);
    } else {
      LOG.info("Scale for field " + fieldName +  " is equal to the RESO Suggested Max Scale of " + suggestedMaxScale);
    }
  }

  @And("{string} length SHOULD be equal to the RESO Suggested Max Length of {int}")
  public void lengthSHOULDBeEqualToTheRESOSuggestedMaxLengthOf(String fieldName, Integer suggestedMaxLength) {
    Integer length = container.getFieldMap(currentResourceName.get()) != null
        && container.getFieldMap(currentResourceName.get()).containsKey(fieldName)
        ? container.getFieldMap(currentResourceName.get()).get(fieldName).getMaxLength() : null;

    if (!Objects.equals(length, suggestedMaxLength)) {
      LOG.warn("Length for field " + fieldName + " SHOULD be equal to the RESO Suggested Max Length of " + suggestedMaxLength
          + " but was " + length);
    } else {
      LOG.info("Length for field " + fieldName +  " is equal to the RESO Suggested Max Length of " + length);
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

  @And("the following synonyms for {string} MUST NOT exist in the metadata")
  public void theFollowingSynonymsForMUSTNOTExistInTheMetadata(String fieldName, List<String> synonyms) {
    synonyms.forEach(synonym ->
        assertFalse(wrapColumns(getDefaultErrorMessage("Synonym", "\"" + synonym + "\"", "of fieldName", "\"" + fieldName + "\"", "found in the metadata!",
            "\nSynonyms are not allowed!")),
            container.getFieldMap(currentResourceName.get()).containsKey(synonym)));
  }

  private static int getDistanceThreshold(String word) {
    final double TOLERANCE = 0.25;
    return (int) (TOLERANCE * word.length());
  }

  private void populateIgnoredItems() {
    final String IGNORED_ITEMS = "ignored.json";
    String ignoredJson = Commander.convertInputStreamToString(Thread.currentThread().getContextClassLoader().getResourceAsStream(IGNORED_ITEMS));
    Type targetClassType = new TypeToken<ArrayList<IgnoredItem>>(){}.getType();
    Collection<IgnoredItem> ignored = new Gson().fromJson(ignoredJson, targetClassType);
    ignored.forEach(ignoredItem -> {
      if (!ignoredItems.get().containsKey(ignoredItem.getLookup())) {
        ignoredItems.get().put(ignoredItem.getLookup(), new LinkedHashMap<>());
      }
      ignoredItems.get().get(ignoredItem.getLookup()).put(ignoredItem.getValue(), new LinkedHashSet<>(ignoredItem.getIgnored()));
    });
  }

  @But("{string} MUST NOT contain any similar lookups")
  public void mustNOTContainAnySimilarLookups(String fieldName) {
    if (currentLookups.get() == null) return;

    //deserialize the ignored items list
    if (ignoredItems.get().size() == 0) populateIgnoredItems();

    Map<String, EdmMember> similarMembers = new LinkedHashMap<>();
    Set<String> standardMembers = foundStandardMembers.get().stream()
        .map(EdmNamed::getName)
        .filter(item -> !item.contentEquals(LOOKUP_VALUE))
        .collect(Collectors.toCollection(LinkedHashSet::new));
    Set<String> difference = Sets.difference(foundMembers.get().keySet(), standardMembers);

    int distance, lastDistance;
    for (String nonstandardField : difference) {
      lastDistance = 0;
      for (String lookupName : currentLookups.get().keySet()) {
        //TODO: gross, clean up
        if (!(ignoredItems.get().containsKey(fieldName) && ignoredItems.get().get(fieldName).containsKey(lookupName)
            && ignoredItems.get().get(fieldName).get(lookupName).contains(nonstandardField))) {
          if (lookupName.compareToIgnoreCase(nonstandardField) == 0) {
            similarMembers.put(lookupName, foundMembers.get().get(nonstandardField));
          } else {
            distance = LevenshteinDistance.getDefaultInstance().apply(lookupName, nonstandardField);
            if (distance < getDistanceThreshold(lookupName)) {
              if (lastDistance < distance) {
                lastDistance = distance;
                similarMembers.put(lookupName, foundMembers.get().get(nonstandardField));
              }
            }
          }
        }
      }
    }

    assertFalse("\n" + getDefaultErrorMessage("Lookups were found that are similar to RESO Standard Lookup Values!\n\n",
        similarMembers.keySet().stream()
            .map(member -> "\t{RESO: " + member + ", Yours: " + similarMembers.get(member) + "}")
            .collect(Collectors.joining("\n")),
        "\n"), similarMembers.size() > 0);
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
