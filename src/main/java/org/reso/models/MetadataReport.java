package org.reso.models;

import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.core.edm.xml.ClientCsdlAnnotation;
import org.apache.olingo.commons.api.edm.*;
import org.apache.olingo.commons.core.edm.EdmAnnotationImpl;
import org.apache.xmlgraphics.xmp.Metadata;
import org.reso.commander.common.ODataUtils;
import org.reso.commander.common.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import static org.reso.commander.Commander.REPORT_DIVIDER;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;

public class MetadataReport implements JsonSerializer<MetadataReport> {
  private static final Logger LOG = LogManager.getLogger(MetadataReport.class);

  private Edm metadata;

  private MetadataReport() {
    //private default constructor
  }

  public MetadataReport(Edm metadata) {
    this.metadata = metadata;
  }

  @Override
  public String toString() {
    StringBuilder reportBuilder = new StringBuilder();

    reportBuilder
        .append("\n\n" + REPORT_DIVIDER)
        .append("\nRESO Metadata Report")
        .append("\n").append(new Date())
        .append("\n" + REPORT_DIVIDER);

    JsonElement metadataReport = serialize(this, MetadataReport.class, null);
    reportBuilder.append(FieldJson.buildReportString(metadataReport));
    reportBuilder.append(LookupJson.buildReportString(metadataReport));

    return reportBuilder.toString();
  }

  /**
   * FieldJson uses a JSON payload with the following structure:
   * <p>
   * {
   * "resourceName": "Property",
   * "fieldName": "AboveGradeFinishedArea",
   * "type": "Edm.Decimal"
   * }
   */
  private static final class FieldJson implements JsonSerializer<FieldJson> {
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

    public FieldJson(String resourceName, EdmElement edmElement) {
      this.resourceName = resourceName;
      this.edmElement = edmElement;
    }

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

  /**
   * LookupJson uses a JSON payload with the following structure:
   * <p>
   * {
   * "lookupName": "org.reso.metadata.enums.CommunityFeatures",
   * "lookupValue": "Stables",
   * "type": "Edm.Int32"
   * }
   */
  private static final class LookupJson implements JsonSerializer<LookupJson> {
    static final String
        LOOKUP_NAME_KEY = "lookupName", LOOKUP_VALUE_KEY = "lookupValue",
        TYPE_KEY = "type", VALUE_KEY = "value", ANNOTATIONS_KEY = "annotations",
        LOOKUPS_KEY = "lookups";

    EdmEnumType edmEnumType;
    String memberName;

    public LookupJson(String memberName, EdmEnumType edmEnumType) {
      this.edmEnumType = edmEnumType;
      this.memberName = memberName;
    }

    public static String buildReportString(JsonElement metadataReport) {
      StringBuilder reportBuilder = new StringBuilder();
      metadataReport.getAsJsonObject().get(LOOKUPS_KEY).getAsJsonArray().forEach(field -> {
        reportBuilder.append("\nLookup Name: ");
        reportBuilder.append(field.getAsJsonObject().get(LOOKUP_NAME_KEY));
        reportBuilder.append("\nLookup Value: ");
        reportBuilder.append(field.getAsJsonObject().get(LOOKUP_VALUE_KEY));
        reportBuilder.append("\nType: ");
        reportBuilder.append(field.getAsJsonObject().get(TYPE_KEY));

        if (field.getAsJsonObject().get(ANNOTATIONS_KEY) != null) {
          JsonArray annotations = field.getAsJsonObject().get(ANNOTATIONS_KEY).getAsJsonArray();
          if (annotations != null && annotations.size() > 0) {
            reportBuilder.append("\n");
            reportBuilder.append("Annotations:");
            annotations.forEach(annotation -> {
              if (annotation.getAsJsonObject().get(FieldJson.TERM_KEY) != null) {
                reportBuilder.append("\n\tTerm: ");
                reportBuilder.append(annotation.getAsJsonObject().get(FieldJson.TERM_KEY));
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
    public JsonElement serialize(LookupJson src, Type typeOfSrc, JsonSerializationContext context) {
      JsonObject membersJsonObject = new JsonObject();
      membersJsonObject.addProperty(LOOKUP_NAME_KEY, src.edmEnumType.getFullQualifiedName().toString());
      membersJsonObject.addProperty(LOOKUP_VALUE_KEY, src.memberName);
      membersJsonObject.addProperty(TYPE_KEY, src.edmEnumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString());

      if (src.edmEnumType.getMember(memberName).getAnnotations().size() > 0) {
        JsonArray annotations = new JsonArray();
        src.edmEnumType.getMember(memberName).getAnnotations().forEach(edmAnnotation -> {
          JsonObject annotation = new JsonObject();
          if (edmAnnotation.getTerm() != null) {
            annotation.addProperty(FieldJson.TERM_KEY, edmAnnotation.getTerm().getFullQualifiedName().getFullQualifiedNameAsString());
          } else {
            ODataUtils.SneakyAnnotationReader sneakyAnnotationReader = new ODataUtils.SneakyAnnotationReader(edmAnnotation);
            annotation.addProperty(FieldJson.TERM_KEY, sneakyAnnotationReader.getTerm());
          }

          if (edmAnnotation.getExpression() != null) {
            annotation.addProperty(VALUE_KEY, edmAnnotation.getExpression().asConstant().getValueAsString());
          }
          annotations.add(annotation);
        });
        membersJsonObject.add(ANNOTATIONS_KEY, annotations);
      }
      return membersJsonObject;
    }
  }

  @Override
  public JsonElement serialize(MetadataReport src, Type typeOfSrc, JsonSerializationContext context) {
    final String
        DESCRIPTION_KEY = "description", DESCRIPTION = "RESO Data Dictionary Metadata Report",
        VERSION_KEY = "version", VERSION = "1.7",
        GENERATED_ON_KEY = "generatedOn",
        FIELDS_KEY = "fields",
        LOOKUPS_KEY = "lookups";

    JsonArray fields = new JsonArray();
    JsonArray lookups = new JsonArray();

    src.metadata.getSchemas().forEach(edmSchema -> {
      //serialize entities (resources) and members (fields)
      edmSchema.getEntityTypes().forEach(edmEntityType -> {
        edmEntityType.getPropertyNames().forEach(propertyName -> {
          FieldJson fieldJson = new FieldJson(edmEntityType.getName(), edmEntityType.getProperty(propertyName));
          fields.add(fieldJson.serialize(fieldJson, FieldJson.class, null));
        });
      });

      //serialize enum types
      edmSchema.getEnumTypes().forEach(edmEnumType -> {
        edmEnumType.getMemberNames().forEach(memberName -> {
          LookupJson lookupJson = new LookupJson(memberName, edmEnumType);
          lookups.add(lookupJson.serialize(lookupJson, LookupJson.class, null));
        });
      });
    });

    JsonObject metadataReport = new JsonObject();
    metadataReport.addProperty(DESCRIPTION_KEY, DESCRIPTION);
    metadataReport.addProperty(VERSION_KEY, VERSION);
    metadataReport.addProperty(GENERATED_ON_KEY, Utils.getIsoTimestamp());
    metadataReport.add(FIELDS_KEY, fields);
    metadataReport.add(LOOKUPS_KEY, lookups);
    return metadataReport;
  }
}
