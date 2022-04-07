package org.reso.commander.jsonSerializers;

import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.commons.api.edm.EdmAnnotation;
import org.apache.olingo.commons.api.edm.EdmElement;
import org.apache.olingo.commons.api.edm.EdmProperty;
import org.reso.commander.common.ODataUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;

/**
 * FieldJson uses a JSON payload with the following structure:
 * <p>
 * {
 * "resourceName": "Property",
 * "fieldName": "AboveGradeFinishedArea",
 * "type": "Edm.Decimal"
 * }
 */
public final class FieldJson implements JsonSerializer<FieldJson> {
  private static final Logger LOG = LogManager.getLogger(FieldJson.class);

  static final String
      RESOURCE_NAME_KEY = "resourceName",
      FIELD_NAME_KEY = "fieldName",
      NULLABLE_KEY = "nullable",
      MAX_LENGTH_KEY = "maxLength",
      PRECISION_KEY = "precision",
      SCALE_KEY = "scale",
      IS_COLLECTION_KEY = "isCollection",
      DEFAULT_VALUE_KEY = "defaultValue",
      UNICODE_KEY = "unicode",
      TYPE_KEY = "type",
      TERM_KEY = "term",
      VALUE_KEY = "value",
      ANNOTATIONS_KEY = "annotations",
      FIELDS_KEY = "fields";

  String resourceName;
  EdmElement edmElement;

  /**
   * Constructor which takes an edmElement and reads the type from it, then
   * uses it as the resource name.
   * @param edmElement edmElement to create FieldJson for
   */
  public FieldJson(EdmElement edmElement) {
    Optional<EdmElement> element = Optional.ofNullable(edmElement);
    assert element.isPresent() : "EdmElement cannot be null!";
    this.edmElement = edmElement;

    Optional<String> resourceName = Optional.ofNullable(edmElement.getType().getName());
    assert resourceName.isPresent() : "Could not read name from edmElement type!";
    this.resourceName = resourceName.get();
  }

  /**
   * Constructor which takes an edmElement and reads the type from it, then
   * uses it as the resource name.
   * @param resourceName the resourceName the element belongs to
   * @param edmElement edmElement to create FieldJson for
   */
  public FieldJson(String resourceName, EdmElement edmElement) {
    this.resourceName = resourceName;
    this.edmElement = edmElement;
  }

  /**
   * Metadata Pretty Printer
   * @param metadataReport the metadata report
   * @return a human-friendly string version of the metadata report
   */
  public static String buildReportString(JsonElement metadataReport) {
    StringBuilder reportBuilder = new StringBuilder();
    metadataReport.getAsJsonObject().get(FIELDS_KEY).getAsJsonArray().forEach(field -> {
      reportBuilder.append("\nResource: ");
      reportBuilder.append(field.getAsJsonObject().get(RESOURCE_NAME_KEY));
      reportBuilder.append("\nField: ");
      reportBuilder.append(field.getAsJsonObject().get(FIELD_NAME_KEY));
      reportBuilder.append("\nType: ");
      reportBuilder.append(field.getAsJsonObject().get(TYPE_KEY));

      if (field.getAsJsonObject().get(ANNOTATIONS_KEY) != null) {
        JsonArray annotations = field.getAsJsonObject().get(ANNOTATIONS_KEY).getAsJsonArray();
        if (annotations != null && annotations.size() > 0) {
          reportBuilder.append("\n");
          reportBuilder.append("Annotations:");
          annotations.forEach(annotation -> {
            if (annotation.getAsJsonObject().get(TERM_KEY) != null) {
              reportBuilder.append("\n\tTerm: ");
              reportBuilder.append(annotation.getAsJsonObject().get(TERM_KEY));
            }

            if (annotation.getAsJsonObject().get(VALUE_KEY) != null) {
              reportBuilder.append("\n\tValue: ");
              reportBuilder.append(annotation.getAsJsonObject().get(VALUE_KEY));
            }
          });
        }
      }
      reportBuilder.append("\n");
    });
    return reportBuilder.toString();
  }

  @Override
  public JsonElement serialize(FieldJson src, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject field = new JsonObject();


    field.addProperty(RESOURCE_NAME_KEY, src.resourceName);
    field.addProperty(FIELD_NAME_KEY, src.edmElement.getName());

    String typeName = null;
    try {
      typeName = src.edmElement.getType().getFullQualifiedName().getFullQualifiedNameAsString();
      field.addProperty(TYPE_KEY, typeName);
    } catch (Exception ex) {
      LOG.error(getDefaultErrorMessage("Field Name:", src.edmElement.getName(), ex.toString()));
      field.addProperty(TYPE_KEY, "UNDEFINED");
    }

    field.addProperty(NULLABLE_KEY, ((EdmProperty) src.edmElement).isNullable());
    field.addProperty(MAX_LENGTH_KEY, ((EdmProperty) src.edmElement).getMaxLength());
    field.addProperty(SCALE_KEY, ((EdmProperty) src.edmElement).getScale());
    field.addProperty(PRECISION_KEY, ((EdmProperty) src.edmElement).getPrecision());
    field.addProperty(DEFAULT_VALUE_KEY, ((EdmProperty) src.edmElement).getDefaultValue());
    field.addProperty(IS_COLLECTION_KEY, src.edmElement.isCollection());
    field.addProperty(UNICODE_KEY, ((EdmProperty) src.edmElement).isUnicode());

    //TODO: report issue to Apache
    // Can only get the annotation term using ((ClientCsdlAnnotation) ((EdmAnnotationImpl)edmAnnotation).annotatable).term
    // which a private member and cannot be accessed
    List<EdmAnnotation> annotations = ((EdmProperty) src.edmElement).getAnnotations();
    if (annotations != null && annotations.size() > 0) {
      JsonArray annotationsJsonArray = new JsonArray();
      annotations.forEach(edmAnnotation -> {
        if (edmAnnotation.getExpression() != null) {
          if (edmAnnotation.getExpression().isConstant()) {
            JsonObject annotation = new JsonObject();
            if (edmAnnotation.getTerm() != null) {
              annotation.addProperty(TERM_KEY, edmAnnotation.getTerm().getFullQualifiedName().getFullQualifiedNameAsString());
            } else {
              ODataUtils.SneakyAnnotationReader sneakyAnnotationReader = new ODataUtils.SneakyAnnotationReader(edmAnnotation);
              annotation.addProperty(TERM_KEY, sneakyAnnotationReader.getTerm());
            }
            annotation.addProperty(VALUE_KEY, edmAnnotation.getExpression().asConstant().getValueAsString());
            annotationsJsonArray.add(annotation);
          } else if (edmAnnotation.getExpression().isDynamic()) {
            if (edmAnnotation.getExpression().asDynamic().isCollection()) {
              edmAnnotation.getExpression().asDynamic().asCollection().getItems().forEach(edmExpression -> {
                //OData Allowed Values come across as Records, in which case their key is "Value"
                if (edmExpression.asDynamic().isRecord()) {
                  JsonObject annotation = new JsonObject();
                  edmExpression.asDynamic().asRecord().getPropertyValues().forEach(edmPropertyValue -> {
                    annotation.addProperty(TERM_KEY, edmPropertyValue.getProperty());
                    annotation.addProperty(VALUE_KEY, edmPropertyValue.getValue().asConstant().getValueAsString());
                    annotationsJsonArray.add(annotation);
                  });
                }
              });
            }
          }
        }
      });
      if (annotationsJsonArray.size() > 0) field.add(ANNOTATIONS_KEY, annotationsJsonArray);
    }
    return field;
  }
}

