package org.reso.commander.jsonSerializers;

import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.commons.api.edm.EdmEnumType;
import org.reso.commander.common.ODataUtils;

import java.lang.reflect.Type;

/**
 * LookupJson uses a JSON payload with the following structure:
 * <p>
 * {
 * "lookupName": "org.reso.metadata.enums.CommunityFeatures",
 * "lookupValue": "Stables",
 * "type": "Edm.Int32"
 * }
 */
public final class LookupJson implements JsonSerializer<LookupJson> {
  private static final Logger LOG = LogManager.getLogger(LookupJson.class);

  public static final String
      LOOKUP_NAME_KEY = "lookupName", LOOKUP_VALUE_KEY = "lookupValue",
      TYPE_KEY = "type", VALUE_KEY = "value", ANNOTATIONS_KEY = "annotations",
      LOOKUPS_KEY = "lookups", TERM_KEY = "term";

  EdmEnumType edmEnumType;
  String memberName;

  public LookupJson(String memberName, EdmEnumType edmEnumType) {
    this.edmEnumType = edmEnumType;
    this.memberName = memberName;
  }

  /**
   * Metadata Pretty Printer
   * @param metadataReport the metadata report
   * @return a human-friendly string version of the metadata report
   */
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
          annotation.addProperty(TERM_KEY, edmAnnotation.getTerm().getFullQualifiedName().getFullQualifiedNameAsString());
        } else {
          ODataUtils.SneakyAnnotationReader sneakyAnnotationReader = new ODataUtils.SneakyAnnotationReader(edmAnnotation);
          annotation.addProperty(TERM_KEY, sneakyAnnotationReader.getTerm());
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
