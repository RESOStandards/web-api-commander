package org.reso.certification.stepdefs;

import com.google.inject.Inject;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.commons.api.edm.EdmAnnotation;
import org.apache.olingo.commons.api.edm.EdmElement;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.core.edm.EdmPropertyImpl;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.commander.common.ODataFetchApi;
import org.reso.models.MetadataReport;
import org.reso.models.ReferenceStandardField;
import org.reso.models.Settings;

import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;
import static org.reso.commander.common.TestUtils.failAndExitWithErrorMessage;

public class LookupResource {
  private static final Logger LOG = LogManager.getLogger(LookupResource.class);
  private static Scenario scenario;
  private final static AtomicReference<WebAPITestContainer> container = new AtomicReference<>();
  private static final String PATH_TO_RESOSCRIPT_ARG = "pathToRESOScript";
  private static final AtomicReference<Map<String, List<ClientEntity>>> entityCache = new AtomicReference<>(new LinkedHashMap<>());
  private static final String LOOKUP_RESOURCE_REQUIRED_ANNOTATION_TERM = "RESO.OData.Metadata.LookupName";

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
    if (entityCache.get() == null) {
      failAndExitWithErrorMessage("Could not replicate data from resource: " + resourceName, scenario);
    }

    if (!entityCache.get().containsKey(resourceName)) {
      entityCache.get().put(resourceName, new ArrayList<>());
      try {
        entityCache.get().get(resourceName).addAll(ODataFetchApi.replicateDataFromResource(container.get(), resourceName,
            ODataFetchApi.WebApiReplicationStrategy.TopAndSkip));
      } catch (Exception exception) {
        failAndExitWithErrorMessage("Unable to retrieve data from the Lookup Resource! " + exception.getMessage(), scenario);
      }
    } else {
      LOG.info("Using cached data from: " + resourceName);
    }
  }

  @Then("Lookup fields MUST have an annotation with term {string} and string value of LookupName")
  public void lookupFieldsMUSTHaveAnAnnotationWithTermAndStringValueOfLookupName(String arg0) {
  }

  @Then("{string} Resource data and metadata MUST contain the following fields")
  public void resourceDataAndMetadataMUSTContainTheFollowingFields(String resourceName, List<String> fields) {
    if (entityCache.get() == null || entityCache.get().get(resourceName) == null) {
      failAndExitWithErrorMessage("Entity Cache could not be created for the " + resourceName + " resource!", scenario);
    }

    final String mandatoryFields = "'" + String.join(", ", fields) + "'";

    //check metadata
    scenario.log("Ensuring mandatory fields " + mandatoryFields + " are present in server metadata");
    assertTrue("The fields " + mandatoryFields + " MUST be present in the server metadata for the " + resourceName + " Resource!",
        container.get().getFieldMap(resourceName) != null && container.get().getFieldMap(resourceName).keySet().containsAll(fields));

    //check resource data cache
    scenario.log("Ensuring mandatory fields " + mandatoryFields + " are present in " + resourceName + " Resource data");
    entityCache.get().get(resourceName).forEach(clientEntity -> fields.forEach(fieldName -> {
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
    scenario.log("Found Lookup Resource!");
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

    final Set<ReferenceStandardField> lookupFields = standardLookupFieldCache.keySet().stream().flatMap(resourceName ->
        standardLookupFieldCache.get(resourceName).values().stream()
            .filter(referenceStandardField -> referenceStandardField.getLookupName() != null)).collect(Collectors.toSet());


    final String LOOKUP_RESOURCE_NAME = "Lookup", LOOKUP_RESOURCE_LOOKUP_NAME_PROPERTY = "LookupName";

    lookupFields.forEach(referenceStandardField -> {
//      LOG.debug("Standard Field: { "
//          + "resourceName: \"" + referenceStandardField.getParentResourceName() + "\""
//          + ", standardName: \"" + referenceStandardField.getStandardName() + "\""
//          + ", lookupName: \"" + referenceStandardField.getLookupName() + "\" }");

      final EdmEntitySet entitySet = container.get().getEdm().getEntityContainer().getEntitySet(referenceStandardField.getParentResourceName());


      if (entitySet != null) {
        final EdmElement fieldEdm = entitySet.getEntityTypeWithAnnotations().getProperty(referenceStandardField.getStandardName());
        if (fieldEdm != null) {
          final boolean isStringDataType = fieldEdm.getType().getFullQualifiedName().toString().contentEquals(EdmPrimitiveTypeKind.String.getFullQualifiedName().toString());

          LOG.info("\nFound field with resource: " + referenceStandardField.getParentResourceName() + " and standard name: " + referenceStandardField.getStandardName());
          LOG.info("\t\t Data type is: " + fieldEdm.getType().getFullQualifiedName().toString());

          if (isStringDataType) {
            final Optional<EdmAnnotation> foundAnnotation = ((EdmPropertyImpl) fieldEdm).getAnnotations().stream()
                .filter(edmAnnotation -> {
                  final MetadataReport.SneakyAnnotationReader annotationReader = new MetadataReport.SneakyAnnotationReader(edmAnnotation);
                  return annotationReader.getTerm() != null && annotationReader.getTerm().contentEquals(LOOKUP_RESOURCE_REQUIRED_ANNOTATION_TERM);
                }).findFirst();

            if (foundAnnotation.isPresent()) {
              MetadataReport.SneakyAnnotationReader annotationReader = new MetadataReport.SneakyAnnotationReader(foundAnnotation.get());

              LOG.info("Found annotation for " + referenceStandardField.getStandardName() + "!");

              LOG.debug("Annotation term: " + annotationReader.getTerm());
              final String lookupName = foundAnnotation.get().getExpression().asConstant().getValueAsString();

              if (lookupName != null) {
                LOG.info("Found lookupName annotation! LookupName is: " + lookupName);
              } else {
                failAndExitWithErrorMessage("Could not find value for annotation term: \"" + annotationTerm + "\"", scenario);
              }

              LOG.info("\t--> Checking for LookupName \"" + lookupName + "\" in the " + LOOKUP_RESOURCE_NAME + " resource...");
              boolean lookupNameFoundInLookupData = entityCache.get().get(LOOKUP_RESOURCE_NAME).stream().anyMatch(clientEntity -> lookupName != null
                  && clientEntity.getProperty(LOOKUP_RESOURCE_LOOKUP_NAME_PROPERTY).getValue().asPrimitive().toString().contentEquals(lookupName));

              if (lookupNameFoundInLookupData) {
                LOG.info("\t--> Found!");
              } else {
                failAndExitWithErrorMessage("Could not find LookupName \"" + lookupName + "\" in the " + LOOKUP_RESOURCE_NAME + " data! Field: " + fieldEdm.getName(), scenario);
              }

            } else {
              failAndExitWithErrorMessage("Required annotation \"" + LOOKUP_RESOURCE_REQUIRED_ANNOTATION_TERM
                  + "\" not present for the " + referenceStandardField.getStandardName() + " field in the "
                  + referenceStandardField.getParentResourceName() + " resource!", scenario);
            }
          }
        }
      }
    });

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
  @And("fields with the annotation term {string} MUST have a LookupName in the Lookup Resource")
  public void fieldsWithTheAnnotationTermMUSTHaveALookupNameInTheLookupResource(String annotationTerm) {
//    final List<EdmAnnotation> lookupNameAnnotations = container.get().getEdm().getSchemas().parallelStream().map(
//        edmSchema -> edmSchema.getEntityTypes().parallelStream().map(edmEntityType ->
//          edmEntityType.getAnnotations().stream().filter(edmAnnotation ->
//              edmAnnotation.getTerm().getFullQualifiedName().getName().contentEquals("RESO.OData.Metadata.LookupName")))
//    );
  }


}
