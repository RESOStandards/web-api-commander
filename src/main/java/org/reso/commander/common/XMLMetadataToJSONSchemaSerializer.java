package org.reso.commander.common;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.edm.EdmElement;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLMetadataToJSONSchemaSerializer implements JsonSerializer<XMLMetadataToJSONSchemaSerializer> {
  private static final Logger LOG = LogManager.getLogger(XMLMetadataToJSONSchemaSerializer.class);

  private XMLMetadataToJSONSchemaSerializer() {
    //should not use default constructor
  }

  /**
   * Gson invokes this call-back method during serialization when it encounters a field of the
   * specified type.
   *
   * <p>In the implementation of this call-back method, you should consider invoking
   * {@link JsonSerializationContext#serialize(Object, Type)} method to create JsonElements for any
   * non-trivial field of the {@code src} object. However, you should never invoke it on the
   * {@code src} object itself since that will cause an infinite loop (Gson will call your
   * call-back method again).</p>
   *
   * @param src       the object that needs to be converted to Json.
   * @param typeOfSrc the actual type (fully genericized version) of the source object.
   * @param context
   * @return a JsonElement corresponding to the specified object.
   */
  @Override
  public JsonElement serialize(XMLMetadataToJSONSchemaSerializer src, Type typeOfSrc, JsonSerializationContext context) {
    return null;
  }

  /**
   * Converts an OData Entity Data Model into a collection of JSON Schema 6 Documents
   *
   * @param edm
   * @return HashMap containing a collection of resource name, JSON Schema pairs
   */
  public static Map<String, String> convertEdmToJsonSchemaDocuments(Edm edm) {
    final Map<String, String> jsonSchemas = Collections.synchronizedMap(new LinkedHashMap<>());

    final String
        JSON_SCHEMA_RESOURCE_VALUE_WRAPPER =
        "{\n" +
            "  \"$id\": \"https://reso.org/data-dictionary/schemas/1.7/%s\",\n" +                    /* resource name */
            "  \"$schema\": \"https://json-schema.org/draft/2020-12/schema\",\n" +
            "  \"type\": \"array\",\n" +
            "  \"required\": [\"value\", \"@odata.context\" ],\n" +
            "  \"properties\" : {\n" +
            "    \"@odata.context\" : { \"type\": \"string\" }, \n" +
            "    \"value\": { \"type\": \"array\",\n" +
            "    \"items\": { \"$ref\": \"#/$defs/%s\" }, \n" +                                      /* resource name */
            "  }\n" +
            "},\n",
        JSON_SCHEMA_TEMPLATE_DEFS =
            "$defs: {\n" +
                "    \"%s\": { \n" +                                                             /* resource name, string */
                "      \"type\": \"object\",\n" +
                "      \"required\" : [ %s ],\n" +                                 /* key fields, string list with quotes */
                "      \"properties\" : { \n" +
                "          %s\n" +                               /* comma-separated JSON Schema type definition fragments */
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}\n";

    edm.getSchemas().forEach(edmSchema -> {
      StringBuilder schemaDocument = new StringBuilder();

      //serialize entities (resources) and members (fields)
      edmSchema.getEntityTypes().forEach(edmEntityType -> {
        edmEntityType.getPropertyNames().forEach(propertyName -> {
          final String jsonSchemaFragment = getJsonSchemaType(edmEntityType.getProperty(propertyName));
          if (jsonSchemaFragment != null) {
            schemaDocument
                .append(schemaDocument.length() > 0 ? ",\n" : "")
                .append("    \"")
                .append(propertyName)
                .append("\": ")
                .append(getJsonSchemaType(edmEntityType.getProperty(propertyName)));
          }
        });
        final String schemaString = String.format(JSON_SCHEMA_RESOURCE_VALUE_WRAPPER, edmEntityType.getName(), schemaDocument.toString());
        jsonSchemas.put(edmEntityType.getName(), schemaString);
      });

//      //serialize enum types
//      edmSchema.getEnumTypes().forEach(edmEnumType -> {
//        edmEnumType.getMemberNames().forEach(memberName -> {
//
//        });
//      });
    });
    return jsonSchemas;
  }

  private static String getJsonSchemaType(EdmElement element) {
    final String fullyQualifiedName = element.getType().getFullQualifiedName().getFullQualifiedNameAsString();

    final String
        EDM_STRING = EdmPrimitiveTypeKind.String.getFullQualifiedName().getFullQualifiedNameAsString(),
        EDM_BINARY = EdmPrimitiveTypeKind.Binary.getFullQualifiedName().getFullQualifiedNameAsString(),
        EDM_SBYTE = EdmPrimitiveTypeKind.SByte.getFullQualifiedName().getFullQualifiedNameAsString(),
        EDM_DATE_TIME_OFFSET = EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName().getFullQualifiedNameAsString(),
        EDM_DATE = EdmPrimitiveTypeKind.Date.getFullQualifiedName().getFullQualifiedNameAsString(),
        EDM_DECIMAL = EdmPrimitiveTypeKind.Decimal.getFullQualifiedName().getFullQualifiedNameAsString(),
        EDM_INT_64 = EdmPrimitiveTypeKind.Int64.getFullQualifiedName().getFullQualifiedNameAsString(),
        EDM_INT_32 = EdmPrimitiveTypeKind.Int32.getFullQualifiedName().getFullQualifiedNameAsString(),
        EDM_INT_16 = EdmPrimitiveTypeKind.Int16.getFullQualifiedName().getFullQualifiedNameAsString(),
        EDM_BOOLEAN = EdmPrimitiveTypeKind.Boolean.getFullQualifiedName().getFullQualifiedNameAsString();

    if (fullyQualifiedName.contentEquals(EDM_STRING)
        || fullyQualifiedName.contentEquals(EDM_SBYTE)
        || fullyQualifiedName.contentEquals(EDM_BINARY)) {
      return "{ \"type\" : \"string\" }";
    } else if (fullyQualifiedName.contentEquals(EDM_DATE_TIME_OFFSET)) {
      return "{ \"type\": \"string\", \"format\": \"date-time\" }";
    } else if (fullyQualifiedName.contentEquals(EDM_DATE)) {
      return "{ \"type\": \"string\", \"format\": \"date\" }";
    } else if (fullyQualifiedName.contentEquals(EDM_DECIMAL)
        || fullyQualifiedName.contentEquals(EDM_INT_64)
        || fullyQualifiedName.contentEquals(EDM_INT_32)
        || fullyQualifiedName.contentEquals(EDM_INT_16)) {
      return "{ \"type\": \"number\" }";
    } else if (fullyQualifiedName.contentEquals(EDM_BOOLEAN)) {
      return "{ \"type\": \"boolean\" }";
    } else {
      LOG.error("Unsupported type mapping! Type:" + fullyQualifiedName);
      return null;
    }
  }

}
