package org.reso.models;

import com.google.gson.*;
import org.apache.olingo.commons.api.edm.*;
import org.reso.commander.common.Utils;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import static org.reso.commander.Commander.REPORT_DIVIDER;

public class MetadataReport implements JsonSerializer<MetadataReport> {
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
   *
   *    {
   *       "resourceName": "Property",
   *       "fieldName": "AboveGradeFinishedArea",
   *       "type": "Edm.Decimal"
   *     }
   */
  private static final class FieldJson implements JsonSerializer<FieldJson> {
    static final String
        RESOURCE_NAME_KEY = "resourceName", FIELD_NAME_KEY = "fieldName", TYPE_KEY = "type",
        VALUE_KEY= "value", ANNOTATIONS_KEY = "annotations", FIELDS_KEY = "fields";

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
              if (annotation.getAsJsonObject().get(TYPE_KEY) != null) {
                reportBuilder.append("\n\tType: ");
                reportBuilder.append(annotation.getAsJsonObject().get(TYPE_KEY));
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
      field.addProperty(TYPE_KEY, src.edmElement.getType().getFullQualifiedName().getFullQualifiedNameAsString());

      List<EdmAnnotation> annotations = ((EdmProperty)edmElement).getAnnotations();
      if (annotations != null && annotations.size() > 0) {
        JsonArray annotationsJsonArray = new JsonArray();
        annotations.forEach(edmAnnotation -> {
          JsonObject annotation = new JsonObject();
          if (edmAnnotation.getTerm() != null) {
            annotation.addProperty(TYPE_KEY, edmAnnotation.getTerm().getFullQualifiedName().getFullQualifiedNameAsString());
          }

          if (edmAnnotation.getExpression() != null) {
            annotation.addProperty(VALUE_KEY, edmAnnotation.getExpression().asConstant().getValueAsString());
          }
          annotationsJsonArray.add(annotation);
        });
        field.add(ANNOTATIONS_KEY, annotationsJsonArray);
      }
      return field;
    }
  }

  /**
   * LookupJson uses a JSON payload with the following structure:
   *
   *    {
   *       "lookupName": "org.reso.metadata.enums.CommunityFeatures",
   *       "lookupValue": "Stables",
   *       "type": "Edm.Int32"
   *     }
   */
  private static final class LookupJson implements JsonSerializer<LookupJson> {
    static final String
        LOOKUP_NAME_KEY = "lookupName", LOOKUP_VALUE_KEY = "lookupValue",
        TYPE_KEY = "type", VALUE_KEY= "value", ANNOTATIONS_KEY = "annotations",
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
              if (annotation.getAsJsonObject().get(TYPE_KEY) != null) {
                reportBuilder.append("\n\tType: ");
                reportBuilder.append(annotation.getAsJsonObject().get(TYPE_KEY));
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
            annotation.addProperty(TYPE_KEY, edmAnnotation.getTerm().getFullQualifiedName().getFullQualifiedNameAsString());
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
    metadataReport.addProperty(GENERATED_ON_KEY, Utils.getTimestamp());
    metadataReport.add(FIELDS_KEY, fields);
    metadataReport.add(LOOKUPS_KEY, lookups);
    return metadataReport;
  }
}
