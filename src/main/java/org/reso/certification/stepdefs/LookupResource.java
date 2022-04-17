package org.reso.certification.stepdefs;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.inject.Inject;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.commons.api.edm.*;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.commander.common.ODataFetchApi;
import org.reso.commander.common.ODataUtils;
import org.reso.commander.common.Utils;
import org.reso.models.ReferenceStandardField;
import org.reso.models.Settings;

import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;
import static org.reso.certification.stepdefs.DataAvailability.CERTIFICATION_RESULTS_PATH;
import static org.reso.commander.common.ODataUtils.getEdmElement;
import static org.reso.commander.common.ODataUtils.hasAnnotationTerm;
import static org.reso.commander.common.TestUtils.failAndExitWithErrorMessage;
import static org.reso.commander.common.Utils.wrapColumns;

public class LookupResource {
  private static final Logger LOG = LogManager.getLogger(LookupResource.class);
  private static Scenario scenario;
  private final static AtomicReference<WebAPITestContainer> container = new AtomicReference<>();
  private static final String PATH_TO_RESOSCRIPT_ARG = "pathToRESOScript";
  private static final AtomicReference<Map<String, List<ClientEntity>>> lookupResourceCache = new AtomicReference<>(new LinkedHashMap<>());
  private static final String LOOKUP_RESOURCE_LOOKUP_METADATA_FILE_NAME = "lookup-resource-lookup-metadata.json";
  private static final String LOOKUP_RESOURCE_FIELD_METADATA_FILE_NAME = "lookup-resource-field-metadata.json";


  //TODO: migrate to test file
  private static final String LOOKUP_ANNOTATION_TERM = "RESO.OData.Metadata.LookupName";

  @Inject
  public LookupResource(WebAPITestContainer c) {
    if (container.get() == null) {
      container.set(c);
    }
  }

  @Before
  public void beforeStep(Scenario scenario) {
    final String pathToRESOScript = System.getProperty(PATH_TO_RESOSCRIPT_ARG, null);

    LookupResource.scenario = scenario;

    if (pathToRESOScript != null && !container.get().getIsInitialized()) {
      container.get().setSettings(Settings.loadFromRESOScript(new File(System.getProperty(PATH_TO_RESOSCRIPT_ARG))));
      container.get().initialize();
    }
  }

  @Then("valid data is replicated from the {string} Resource")
  public void validDataIsReplicatedFromTheResource(String resourceName) {
    if (lookupResourceCache.get() == null) {
      failAndExitWithErrorMessage("Could not replicate data from resource: " + resourceName, scenario);
    }

    if (!lookupResourceCache.get().containsKey(resourceName)) {
      lookupResourceCache.get().put(resourceName, new ArrayList<>());
      try {
        final List<ClientEntity> results = ODataFetchApi.replicateDataFromResource(container.get(), resourceName,
            ODataFetchApi.WebApiReplicationStrategy.TopAndSkip);

        if (results.size() == 0) {
          failAndExitWithErrorMessage("Could not replicate data from the " + resourceName + " resource!", scenario);
        }

        lookupResourceCache.get().get(resourceName).addAll(results);

        final JsonObject metadata = new JsonObject();
        metadata.add("lookups", ODataUtils.serializeLookupMetadata(container.get().getCommander().getClient(), results));
        Utils.createFile(CERTIFICATION_RESULTS_PATH, LOOKUP_RESOURCE_LOOKUP_METADATA_FILE_NAME, metadata.toString());

      } catch (Exception exception) {
        failAndExitWithErrorMessage("Unable to retrieve data from the Lookup Resource! " + exception.getMessage(), scenario);
      }
    } else {
      LOG.debug("Using cached data from: " + resourceName);
    }
  }

  @Then("{string} Resource data and metadata MUST contain the following fields")
  public void resourceDataAndMetadataMUSTContainTheFollowingFields(String resourceName, List<String> fields) {
    if (lookupResourceCache.get() == null || lookupResourceCache.get().get(resourceName) == null) {
      failAndExitWithErrorMessage("Entity Cache could not be created for the " + resourceName + " resource!", scenario);
    }

    final String mandatoryFields = "'" + String.join(", ", fields) + "'";

    //check metadata
    scenario.log("Ensuring mandatory fields " + mandatoryFields + " are present in server metadata");
    assertTrue("The fields " + mandatoryFields + " MUST be present in the server metadata for the " + resourceName + " Resource!",
        container.get().getFieldMap(resourceName) != null && container.get().getFieldMap(resourceName).keySet().containsAll(fields));

    //check resource data cache
    scenario.log("Ensuring mandatory fields " + mandatoryFields + " are present in " + resourceName + " Resource data");
    lookupResourceCache.get().get(resourceName).forEach(clientEntity -> fields.forEach(fieldName -> {
      if (clientEntity.getProperty(fieldName) == null || clientEntity.getProperty(fieldName).getValue() == null) {
        failAndExitWithErrorMessage("Missing required field in the " + resourceName + " Resource!", scenario);
      }
    }));
    scenario.log("All mandatory fields present!");
  }

  @When("the {string} Resource exists in the metadata")
  public void theResourceExistsInTheMetadata(String resourceName) {
    boolean hasResource = container.get().getFieldMap(resourceName) != null;
    assumeTrue("The " + resourceName + " Resource was not found in the default entity container in the metadata!", hasResource);
    scenario.log("Found " + resourceName + " Resource!");
  }

  /*
  <!-- OData annotation for String List, Single field -->
    <Property Name="OfficeCountyOrParish" Type="Edm.String">
    <Annotation Term="RESO.OData.Metadata.LookupName" String="CountyOrParish" />
    </Property>

    <!-- OData annotation for String List, Multi field -->
     <Property Name="ExteriorFeatures" Type="Collection(Edm.String)">
     <Annotation Term="RESO.OData.Metadata.LookupName" String="ExteriorFeatures" />
     </Property>
  */
  @Then("RESO Lookups using String or String Collection data types MUST have the annotation {string}")
  public void resoLookupsUsingStringOrStringCollectionDataTypesMUSTHaveTheAnnotation(String annotationTerm) {
    if (container.get().getDDCacheProcessor() == null || container.get().getDDCacheProcessor().getStandardFieldCache() == null) {
      failAndExitWithErrorMessage("Could not access standard field cache. Check to make sure metadata requests have succeeded.", scenario);
    }

    final Map<String, Map<String, ReferenceStandardField>> standardLookupFieldCache =
        container.get().getDDCacheProcessor().getStandardFieldCache();

    final Set<ReferenceStandardField> lookupFields =
        standardLookupFieldCache.keySet().stream().flatMap(resourceName ->
        standardLookupFieldCache.get(resourceName).values().stream()
            .filter(referenceStandardField -> referenceStandardField.getLookupName() != null)).collect(Collectors.toSet());

    lookupFields.forEach(referenceStandardField -> {
      LOG.debug("Standard Field: { "
          + "resourceName: \"" + referenceStandardField.getParentResourceName() + "\""
          + ", standardName: \"" + referenceStandardField.getStandardName() + "\""
          + ", lookupName: \"" + referenceStandardField.getLookupName() + "\" }");

      EdmElement foundElement = getEdmElement(container.get().getEdm(), referenceStandardField.getParentResourceName(), referenceStandardField.getStandardName());
      final boolean isStringDataType = foundElement != null &&
          foundElement.getType().getFullQualifiedName().toString().contentEquals(EdmPrimitiveTypeKind.String.getFullQualifiedName().toString());

      if (foundElement != null && isStringDataType) {
        if (!hasAnnotationTerm(foundElement, annotationTerm)) {
          final String message = "Could not find required annotation with term \"" + annotationTerm + "\" for field: "
              + referenceStandardField.getStandardName();
          LOG.info("WARN: " + message);
          failAndExitWithErrorMessage(message, scenario);
        }
      }
    });
  }

  @And("fields with the annotation term {string} MUST have a LookupName in the Lookup Resource")
  public void fieldsWithTheAnnotationTermMUSTHaveALookupNameInTheLookupResource(String annotationTerm) {
    //every item annotated with the annotation should have a corresponding element in the Lookup set
    final Map<EdmElement, String> annotatedLookupFields =
        ODataUtils.getEdmElementsWithAnnotation.apply(container.get().getEdm(), annotationTerm);

    final Set<String> lookupNamesFromLookupData =
        ODataUtils.extractAnnotationNamesFromEdmElements.apply(annotatedLookupFields);

    final Set<String> missingLookupNames =
        Utils.getDifference(new HashSet<>(annotatedLookupFields.values()), lookupNamesFromLookupData);

    if (missingLookupNames.size() > 0) {
      failAndExitWithErrorMessage("LookupName elements missing from LookupMetadata: "
          + wrapColumns(String.join(", ", missingLookupNames)), scenario);
    } else {
      if (annotatedLookupFields.size() > 0) {
        scenario.log("Found all annotated LookupName elements in the Lookup data. Unique count: " + annotatedLookupFields.size());
        scenario.log("LookupNames: " + wrapColumns(String.join(", ", annotatedLookupFields.values())));

        Utils.createFile(CERTIFICATION_RESULTS_PATH, LOOKUP_RESOURCE_FIELD_METADATA_FILE_NAME,
            ODataUtils.serializeFieldMetadata(annotatedLookupFields).toString());
      } else {
        scenario.log("No annotated lookup names found in the OData XML Metadata.");
      }
    }
  }
}
