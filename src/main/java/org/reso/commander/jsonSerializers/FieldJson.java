package org.reso.commander.jsonSerializers;

import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.commons.api.edm.EdmAnnotation;
import org.apache.olingo.commons.api.edm.EdmElement;
import org.apache.olingo.commons.api.edm.EdmNavigationProperty;
import org.apache.olingo.commons.api.edm.EdmProperty;
import org.reso.commander.common.ODataUtils;

import static org.reso.commander.common.TestUtils.failAndExitWithErrorMessage;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
  static final String
      RESOURCE_NAME_KEY = "resourceName",
      FIELD_NAME_KEY = "fieldName",
      NULLABLE_KEY = "nullable",
      MAX_LENGTH_KEY = "maxLength",
      PRECISION_KEY = "precision",
      SCALE_KEY = "scale",
      IS_COLLECTION_KEY = "isCollection",
      IS_COMPLEX_TYPE_KEY = "isComplexType",
      IS_EXPANSION_KEY = "isExpansion",
      DEFAULT_VALUE_KEY = "defaultValue",
      TYPE_KEY = "type",
      TYPE_NAME_KEY = "typeName",
      TERM_KEY = "term",
      VALUE_KEY = "value",
      ANNOTATIONS_KEY = "annotations",
      FIELDS_KEY = "fields";
  private static final Logger LOG = LogManager.getLogger(FieldJson.class);
  String resourceName;
  EdmElement edmElement;
  EdmNavigationProperty edmNavigationProperty;

  /**
   * Constructor which takes an edmElement and reads the type from it, then
   * uses it as the resource name.
   *
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
   *
   * @param resourceName the resourceName the element belongs to
   * @param edmElement   edmElement to create FieldJson for
   */
  public FieldJson(String resourceName, EdmElement edmElement) {
    this.resourceName = resourceName;
    this.edmElement = edmElement;
  }

  public FieldJson(String resourceName, EdmNavigationProperty edmNavigationProperty) {
    this.resourceName = resourceName;
    this.edmNavigationProperty = edmNavigationProperty;
  }

  /**
   * Metadata Pretty Printer
   *
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
        if (annotations != null && !annotations.isEmpty()) {
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

    final List<EdmAnnotation> annotations = new ArrayList<>();

    Optional.ofNullable(src.resourceName).ifPresent(value -> field.addProperty(RESOURCE_NAME_KEY, value));
    Optional.ofNullable(src.edmElement).ifPresent(value -> field.addProperty(FIELD_NAME_KEY, value.getName()));

    String typeName;

    if (Optional.ofNullable(src.edmElement).isPresent() && Optional.ofNullable(src.edmElement.getType()).isPresent()) {
      try {
        typeName = src.edmElement.getType().getFullQualifiedName().getFullQualifiedNameAsString();
        field.addProperty(TYPE_KEY, typeName);
      } catch (Exception ex) {
        //Issue #162: Need to fail on serialization exceptions since Olingo metadata validation might not catch them
        failAndExitWithErrorMessage(ex.toString(), LOG);
      }
    }

    Optional.ofNullable(src.resourceName).ifPresent(value -> field.addProperty(RESOURCE_NAME_KEY, value));

    if (Optional.ofNullable(src.edmNavigationProperty).isPresent()) {

      Optional.ofNullable(src.edmNavigationProperty.getAnnotations()).ifPresent(annotations::addAll);

      Optional.ofNullable(src.edmNavigationProperty.getName()).ifPresent(
          value -> {
            field.addProperty(FIELD_NAME_KEY, value);
            field.addProperty(IS_EXPANSION_KEY, true);
          }
      );

      Optional.of(src.edmNavigationProperty.isCollection())
          .ifPresent(value -> field.addProperty(IS_COLLECTION_KEY, value));

      Optional.of(src.edmNavigationProperty.getType().getFullQualifiedName().getFullQualifiedNameAsString())
          .ifPresent(value -> field.addProperty(TYPE_KEY, value));

      Optional.of(src.edmNavigationProperty.getType().getName())
          .ifPresent(value -> field.addProperty(TYPE_NAME_KEY, value));

    } else if (Optional.ofNullable(src.edmElement).isPresent()) {
      Optional.ofNullable(((EdmProperty) src.edmElement).getAnnotations()).ifPresent(annotations::addAll);

      Optional.ofNullable(src.edmElement.getName()).ifPresent(value -> field.addProperty(FIELD_NAME_KEY, value));

      try {
        typeName = Optional.of(src.edmElement.getType().getFullQualifiedName().getFullQualifiedNameAsString()).orElse(null);
        field.addProperty(TYPE_KEY, typeName);
        if (!typeName.startsWith("Edm.")) {
          field.addProperty(TYPE_NAME_KEY, src.edmElement.getType().getName());
        }
      } catch (Exception ex) {
        LOG.error(getDefaultErrorMessage("Field Name:", src.edmElement.getName(), ex.toString()));
        field.addProperty(TYPE_KEY, "UNDEFINED");
      }

      Optional.of(((EdmProperty) src.edmElement).isNullable())
          .ifPresent(isNullable -> field.addProperty(NULLABLE_KEY, isNullable));

      Optional.ofNullable(((EdmProperty) src.edmElement).getMaxLength())
          .ifPresent(maxLength -> field.addProperty(MAX_LENGTH_KEY, maxLength));

      Optional.ofNullable(((EdmProperty) src.edmElement).getScale())
          .ifPresent(scale -> field.addProperty(SCALE_KEY, scale));

      Optional.ofNullable(((EdmProperty) src.edmElement).getPrecision())
          .ifPresent(precision -> field.addProperty(PRECISION_KEY, precision));

      Optional.ofNullable(((EdmProperty) src.edmElement).getDefaultValue())
          .ifPresent(value -> field.addProperty(DEFAULT_VALUE_KEY, value));

      if (Optional.of(src.edmElement.isCollection()).orElse(false)) {
        field.addProperty(IS_COLLECTION_KEY, true);
      }

      if (Optional.of(src.edmElement.getType().getKind().toString().contentEquals("COMPLEX")).orElse(false)) {
        field.addProperty(IS_COMPLEX_TYPE_KEY, true);
      }
    }

    //TODO: report issue to Apache
    // Can only get the annotation term using ((ClientCsdlAnnotation) ((EdmAnnotationImpl)edmAnnotation).annotatable).term
    // which a private member and cannot be accessed
    if (!annotations.isEmpty()) {
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
      if (!annotationsJsonArray.isEmpty()) field.add(ANNOTATIONS_KEY, annotationsJsonArray);
    }
    return field;
  }
}

