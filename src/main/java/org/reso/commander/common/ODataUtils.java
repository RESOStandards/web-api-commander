package org.reso.commander.common;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.serialization.ODataSerializerException;
import org.apache.olingo.client.core.edm.xml.ClientCsdlAnnotation;
import org.apache.olingo.client.core.serialization.JsonSerializer;
import org.apache.olingo.commons.api.edm.*;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.core.edm.EdmAnnotationImpl;
import org.apache.olingo.commons.core.edm.EdmPropertyImpl;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ODataUtils {

  private static final Logger LOG = LogManager.getLogger(ODataUtils.class);

  public static EdmElement getEdmElement(Edm edm, String resourceName, String fieldName) {
    final Optional<EdmEntitySet> entitySet = Optional.ofNullable(edm.getEntityContainer().getEntitySet(resourceName));

    if (entitySet.isPresent()) {
      final EdmElement fieldEdm = entitySet.get().getEntityTypeWithAnnotations().getProperty(fieldName);
      if (fieldEdm != null && fieldEdm.getType().getFullQualifiedName().toString().contentEquals(EdmPrimitiveTypeKind.String.getFullQualifiedName().toString())) {
        LOG.debug("\nFound field with resource: " + resourceName + " and standard name: " + fieldName);
        LOG.debug("\t\t Data type is: " + fieldEdm.getType().getFullQualifiedName().toString() + (fieldEdm.isCollection() ? ", Collection: true" : ""));
        return fieldEdm;
      }
    }
    return null;
  }

  /**
   * Determines whether the element has the given term.
   *
   * @param element        the Edm element to check.
   * @param annotationTerm the term to search for.
   * @return true if the Edm element contains the annotationTerm, false otherwise.
   */
  public static boolean hasAnnotationTerm(EdmElement element, String annotationTerm) {
    return Optional.ofNullable(getAnnotationValue(element, annotationTerm)).isPresent();
  }

  /**
   * Gets the annotation value for the given annotation term.
   *
   * @param element        the Edm element to check.
   * @param annotationTerm the term to search for.
   * @return a string value, if present, otherwise null.
   */
  public static String getAnnotationValue(EdmElement element, String annotationTerm) {
    if (element == null || annotationTerm == null) return null;

    final Optional<EdmAnnotation> foundAnnotation = Optional.of((EdmPropertyImpl) element).get().getAnnotations().stream()
        .filter(edmAnnotation -> {
          final SneakyAnnotationReader annotationReader = new SneakyAnnotationReader(edmAnnotation);
          return annotationReader.getTerm() != null && annotationReader.getTerm().contentEquals(annotationTerm);
        }).findFirst();

    if (foundAnnotation.isPresent()) {
      final Optional<String> value = Optional.ofNullable(foundAnnotation.get().getExpression().asConstant().getValueAsString());

      if (value.isPresent()) {
        LOG.debug("Found \"" + annotationTerm + "\" annotation! Value is: " + value);
        return value.get();
      }
    }
    return null;
  }

  /**
   * Serializes a list of OData ClientEntity items in a JSON Array with those properties.
   *
   * @param results list of OData ClientEntity results
   * @param client  OData client to use as serializer
   * @return a JsonArray of results
   */
  public static JsonArray serializeLookupMetadata(ODataClient client, List<ClientEntity> results) {
    final JsonArray lookups = new JsonArray();

    try {
      final Gson gson = new Gson();
      final JsonSerializer jsonSerializer = new JsonSerializer(false, ContentType.APPLICATION_JSON);
      results.forEach(clientEntity -> {
        try {
          StringWriter writer = new StringWriter();
          jsonSerializer.write(writer, client.getBinder().getEntity(clientEntity));
          Optional<JsonElement> element = Optional.ofNullable(gson.fromJson(writer.toString(), JsonElement.class));
          element.ifPresent(lookups::add);
        } catch (ODataSerializerException e) {
          LOG.error("ERROR: could not deserialize. Exception: " + e);
        }
      });
    } catch (Exception exception) {
      LOG.error(exception);
    }

    return lookups;
  }

  public static JsonObject serializeFieldMetadataForLookupFields(Map<String, Set<EdmElement>> resourceFieldMap) {
    //TODO: migrate to test file
    final String LOOKUP_ANNOTATION_TERM = "RESO.OData.Metadata.LookupName";

    final String
        DESCRIPTION_KEY = "description", DESCRIPTION = "Lookup Resource Annotated Fields Metadata",
        VERSION_KEY = "version", VERSION = "1.7",
        GENERATED_ON_KEY = "generatedOn",
        FIELDS_KEY = "fields";

    JsonObject metadataReport = new JsonObject();
    metadataReport.addProperty(DESCRIPTION_KEY, DESCRIPTION);
    metadataReport.addProperty(VERSION_KEY, VERSION);
    metadataReport.addProperty(GENERATED_ON_KEY, Utils.getIsoTimestamp());

    JsonArray fieldsArray = new JsonArray();
    resourceFieldMap.forEach((resourceName, fieldElements) -> fieldElements.forEach(
        fieldElement -> {
          JsonObject fieldObject = new JsonObject();
          fieldObject.addProperty("resourceName", resourceName);
          fieldObject.addProperty("fieldName:", fieldElement.getName());
          fieldObject.addProperty("type", getAnnotationValue(fieldElement, LOOKUP_ANNOTATION_TERM));
          fieldsArray.add(fieldObject);
        }
    ));

    metadataReport.add(FIELDS_KEY, fieldsArray);
    return metadataReport;
  }

  /**
   * Returns a Map of EntityDataModel (Edm) elements and annotation value with the given annotation term.
   */
  public static Map<String, Set<EdmElement>> getEdmElementsWithAnnotation(Edm edm, String annotationTerm) {
    return edm.getSchemas().parallelStream()
        .filter(edmSchema -> edmSchema != null && edmSchema.getEntityContainer() != null)
        .flatMap(edmSchema -> edmSchema.getEntityContainer().getEntitySets().parallelStream())
        .collect(Collectors.toMap(edmEntitySet -> edmEntitySet.getEntityTypeWithAnnotations().getName(),
            edmEntitySet -> edmEntitySet.getEntityTypeWithAnnotations().getPropertyNames().parallelStream()
                .map(propertyName -> edmEntitySet.getEntityTypeWithAnnotations().getProperty(propertyName))
                .filter(edmElement -> getAnnotationValue(edmElement, annotationTerm) != null)
                .collect(Collectors.toSet())));
  }

  /**
   * Class to read OData internal annotation variables.
   */
  public static class SneakyAnnotationReader {
    Class<? extends EdmAnnotationImpl> object;
    Field field;
    EdmAnnotationImpl edmAnnotationImpl;
    ClientCsdlAnnotation clientCsdlAnnotation;

    /**
     * Allows the consumer to read internal annotations.
     *
     * @param edmAnnotation the annotation to read from
     */
    public SneakyAnnotationReader(EdmAnnotation edmAnnotation) {
      try {
        edmAnnotationImpl = ((EdmAnnotationImpl) edmAnnotation);

        // create an object of the class named Class
        object = edmAnnotationImpl.getClass();

        // access the private variable
        field = object.getDeclaredField("annotation");
        // make private field accessible
        field.setAccessible(true);

        clientCsdlAnnotation = (ClientCsdlAnnotation) field.get(edmAnnotationImpl);

      } catch (Exception ex) {
        LOG.error(ex);
        ex.printStackTrace();
      }
    }

    public String getTerm() {
      return clientCsdlAnnotation.getTerm();
    }
  }

}
