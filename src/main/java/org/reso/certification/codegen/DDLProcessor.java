package org.reso.certification.codegen;

import com.google.common.base.CaseFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reso.commander.common.Utils;
import org.reso.models.ReferenceStandardField;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static com.google.common.hash.Hashing.sha256;
import static org.reso.certification.codegen.EDMXProcessor.EMPTY_STRING;
import static org.reso.certification.containers.WebAPITestContainer.SINGLE_SPACE;
import static org.reso.commander.common.DataDictionaryMetadata.v1_7.*;

public class DDLProcessor extends WorksheetProcessor {
  private static final Logger LOG = LogManager.getLogger(DDLProcessor.class);
  private static final String PADDING = "  ";
  private static final int STRING_KEY_SIZE = 64;
  private static final int DEFAULT_VARCHAR_SIZE = 255;

  private final boolean useKeyNumeric;

  public DDLProcessor(boolean useKeyNumeric) {
    this.useKeyNumeric = useKeyNumeric;
  }

  public static String buildDbTableName(String resourceName) {
    return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, resourceName).replace("o_u_i_d", "ouid");
  }

  private static String buildCreateLookupStatement(boolean useKeyNumeric) {
    return
        "\n\n/**\n" +
            " This creates the Lookup resource, as described in RESO RCP-032, \n" +
            " with an optional numeric key. \n\n" +
            " SEE: https://reso.atlassian.net/wiki/spaces/RESOWebAPIRCP/pages/2275152879/RCP+-+WEBAPI-032+Lookup+and+RelatedLookup+Resources+for+Lookup+Metadata\n" +
            "**/\n" +
            "CREATE TABLE IF NOT EXISTS lookup ( \n" +
            "  LookupKey VARCHAR(" + STRING_KEY_SIZE + ") NOT NULL, \n" +
            "  LookupKeyNumeric BIGINT NOT NULL AUTO_INCREMENT, \n" +
            "  LookupName VARCHAR(255) NOT NULL, \n" +
            "  LookupValue VARCHAR(255) NOT NULL, \n" +
            "  StandardLookupValue VARCHAR(255) DEFAULT NULL, \n" +
            "  LegacyODataValue VARCHAR(128) DEFAULT NULL, \n" +
            "  ModificationTimestamp DATETIME DEFAULT CURRENT_TIMESTAMP, \n" +
            "    PRIMARY KEY (LookupKey" + (useKeyNumeric ? "Numeric" : EMPTY_STRING) + ")\n" +
            ") ENGINE=MyISAM DEFAULT CHARSET=utf8;";
  }

  /**
   * Add treatments for any SQL replacements that need to be done here
   * <p>
   * For instance, 'Order' is used by the DD in the Media resource, but it's a SQL keyword.
   *
   * @param sql SQL statement to be sanitized
   * @return sanitized SQL statement
   */
  private static String sanitizeSql(String sql) {
    return sql
        .replaceAll("\\bOrder\\b", "`Order`");
  }

  private String buildFieldMarkup(ReferenceStandardField field, String typeMarkup) {
    StringBuilder fieldMarkup = new StringBuilder();
    String resourceName = field.getResourceName(), fieldName = field.getStandardName();

    if (resourceTemplates.get(resourceName).length() > 0) {
      fieldMarkup.append(", ");
    }

    fieldMarkup.append("\n")
        .append(PADDING)
        .append(fieldName)
        .append(SINGLE_SPACE)
        .append(typeMarkup);

    //if the current field is a key field then append NOT NULL depending on which key it is
    if ((useKeyNumeric && isPrimaryKeyNumericField(resourceName, fieldName))) {
      fieldMarkup.append(SINGLE_SPACE).append("NOT NULL AUTO_INCREMENT");
    } else if (isPrimaryKeyField(field.getResourceName(), fieldName)) {
      fieldMarkup.append(SINGLE_SPACE).append("NOT NULL");
    } else if (!typeMarkup.contains("DEFAULT")) {
      fieldMarkup.append(SINGLE_SPACE).append("DEFAULT NULL");
    }

    return fieldMarkup.toString();
  }

  private String buildPrimaryKeyMarkup(String resourceName) {
    return "PRIMARY KEY (" +
        (useKeyNumeric ? getKeyNumericFieldForResource(resourceName) : getKeyFieldForResource(resourceName)) + ")";
  }

  @Override
  void processNumber(ReferenceStandardField field) {
    String typeMarkup = "";

    if (field.getSuggestedMaxPrecision() != null) {
      //omitting the length and precision attributes as described here in order to maximize cross-platform compatibility.
      //https://dev.mysql.com/doc/refman/8.0/en/floating-point-types.html#:~:text=A%20precision%20from%2024%20to,be%20after%20the%20decimal%20point
      typeMarkup += "DOUBLE PRECISION";
    } else {
      if (Math.pow(2, field.getSuggestedMaxLength()) <= Math.pow(2, 16) - 1) {
        //TINYINT
        typeMarkup += "TINYINT";
      } else if (Math.pow(2, field.getSuggestedMaxLength()) <= Math.pow(2, 32) - 1) {
        typeMarkup += "INTEGER";
      } else {
        typeMarkup += "BIGINT";
      }
    }

    resourceTemplates.get(field.getResourceName()).append(buildFieldMarkup(field, typeMarkup));
  }

  @Override
  void processStringListSingle(ReferenceStandardField field) {
    String typeMarkup = useKeyNumeric ? "BIGINT" : "VARCHAR(" + STRING_KEY_SIZE + ")";
    resourceTemplates.get(field.getResourceName()).append(buildFieldMarkup(field, typeMarkup));
  }

  @Override
  void processString(ReferenceStandardField field) {
    int length = field.getSuggestedMaxLength() != null ? field.getSuggestedMaxLength() : DEFAULT_VARCHAR_SIZE;
    String typeMarkup = !field.getStandardName().contains("Key") && length > 80 ? "TEXT" :
        "VARCHAR" + "(" + length + ")";
    resourceTemplates.get(field.getResourceName()).append(buildFieldMarkup(field, typeMarkup));
  }

  @Override
  void processBoolean(ReferenceStandardField field) {
    String typeMarkup = "TINYINT(1) DEFAULT FALSE";
    resourceTemplates.get(field.getResourceName()).append(buildFieldMarkup(field, typeMarkup));
  }

  @Override
  void processStringListMulti(ReferenceStandardField field) {
    String typeMarkup = useKeyNumeric ? "BIGINT" : "VARCHAR(" + STRING_KEY_SIZE + ")";
    resourceTemplates.get(field.getResourceName()).append(buildFieldMarkup(field, typeMarkup));
  }

  @Override
  void processDate(ReferenceStandardField field) {
    String typeMarkup = "DATE";
    resourceTemplates.get(field.getResourceName()).append(buildFieldMarkup(field, typeMarkup));
  }

  @Override
  void processTimestamp(ReferenceStandardField field) {
    String typeMarkup = "DATETIME";
    resourceTemplates.get(field.getResourceName()).append(buildFieldMarkup(field, typeMarkup));
  }

  @Override
  void processExpansion(ReferenceStandardField field) {
    //Does not apply in this case...
  }

  @Override
  void generateOutput() {
    StringBuilder content = new StringBuilder();

    content
        .append("/**\n")
        .append(PADDING).append("RESO Data Dictionary 1.7 Database Generation (DDL) Script\n")
        .append(PADDING).append("Autogenerated on: ").append(Utils.getIsoTimestamp()).append("\n")
        .append(PADDING).append("This content is covered by RESO's EULA. SEE: https://www.reso.org/eula/\n")
        .append(PADDING).append("For questions or comments, please contact dev@reso.org\n")
        .append("**/\n\n");

    content.append("CREATE DATABASE IF NOT EXISTS reso_data_dictionary_1_7;\n");
    content.append("USE reso_data_dictionary_1_7;");

    resourceTemplates.forEach((resourceName, buffer) -> content
        .append("\n\n")
        .append("CREATE TABLE IF NOT EXISTS ")
        //exception for ouid so it doesn't become o_u_i_d
        .append(buildDbTableName(resourceName))
        .append(" ( ")
        .append(buffer.toString()).append(",\n")
        .append(PADDING).append(PADDING).append(buildPrimaryKeyMarkup(resourceName)).append("\n")
        .append(") ENGINE=MyISAM DEFAULT CHARSET=utf8;"));

    LOG.info(sanitizeSql(content.toString()));

    //create the lookup resource so we can populate it with the enum definitions
    LOG.info(sanitizeSql(buildCreateLookupStatement(useKeyNumeric)));

    LOG.info(this::buildInsertLookupsStatement);
  }

  /**
   * INSERT INTO tbl_name (a,b,c)
   * VALUES(1,2,3), (4,5,6), (7,8,9);
   *
   * @return a string containing the insert statements for the current lookups in the Data Dictionary.
   */
  private String buildInsertLookupsStatement() {
    //enumeration markup keyed by enumeration standard name
    Map<String, String> markupMap = new LinkedHashMap<>();

    StringBuilder content = new StringBuilder("\n\n")
        .append("INSERT INTO lookup (LookupKey, LookupName, LookupValue, StandardLookupValue, LegacyOdataValue) VALUES");

    standardFieldsMap.forEach((resourceName, standardFieldMap) ->
        standardFieldMap.forEach((standardName, referenceStandardField) -> {
          String inserts = buildLookupValueInserts(referenceStandardField);
          if (inserts.length() > 0) {
            markupMap.putIfAbsent(referenceStandardField.getLookupName(),
                (markupMap.keySet().size() > 0 ? ", " : EMPTY_STRING) + PADDING + inserts);
          }
        }));

    markupMap.forEach((lookupStandardName, markup) -> content.append(markup));
    return content.append(";").toString();
  }

  // "LookupKey, LookupName, LookupValue, StandardLookupValue, LegacyOdataValue"
  private String buildLookupValueInserts(ReferenceStandardField standardField) {
    StringBuilder content = new StringBuilder();

    if (getEnumerations().get(standardField.getLookupName()) != null) {
      AtomicReference<String> fieldHash = new AtomicReference<>();

      //iterate through each of the lookup values and generate their edm:EnumType content
      getEnumerations().get(standardField.getLookupName()).forEach(lookup -> {

        // key is the sha256 of the following values
        fieldHash.set(sha256()
            .hashString(
                standardField.getLookupName()
                    + lookup.getLegacyODataValue()
                    + lookup.getLookupValue(), StandardCharsets.UTF_8)
            .toString());

        content
            .append(content.length() > 0 ? ", " : EMPTY_STRING).append("\n")
            .append(PADDING).append("(")
            .append("\"").append(fieldHash.get()).append("\"")
            .append(", ").append("\"").append(standardField.getLookupName()).append("\"")
            .append(", ").append("\"").append(lookup.getLegacyODataValue()).append("\"")
            .append(", ").append("\"").append(lookup.getLegacyODataValue()).append("\"")
            .append(", ").append("\"").append(lookup.getLookupValue()).append("\"")
            .append(")");
      });
    }
    return content.toString();
  }
}
