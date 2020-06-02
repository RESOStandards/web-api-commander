package org.reso.certification.codegen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.reso.commander.common.Utils;
import org.reso.models.DataDictionaryRow;

public class EDMXProcessor extends WorksheetProcessor {
  private static final Logger LOG = LogManager.getLogger(EDMXProcessor.class);
  String openEntityTypeTag = null, closeEntityTypeTag = null;
  final static String EMPTY_STRING = "";
  final static String RESO_NAMESPACE = "org.reso.metadata";

  final static String openingTag =
    "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<edmx:Edmx xmlns:edmx=\"http://docs.oasis-open.org/odata/ns/edmx\" Version=\"4.0\">\n" +
            "  <edmx:DataServices>\n" +
            "    <Schema xmlns=\"http://docs.oasis-open.org/odata/ns/edm\" Namespace=\"" + RESO_NAMESPACE + "\">\n";

  final static String closingTag =
            "    </Schema>\n" +
                    "  </edmx:DataServices>\n" +
                    "</edmx:Edmx>\n";

  @Override
  public void processResourceSheet(Sheet sheet) {
    this.sheet = sheet;
    openEntityTypeTag  = "      <EntityType Name=\"" + sheet.getSheetName() + "\">\n";
    closeEntityTypeTag = "      </EntityType>\n";
  }

  @Override
  void finishProcessingResourceSheet(Sheet sheet) {
    resourceTemplates.put(sheet.getSheetName(), openEntityTypeTag + markup.toString() + closeEntityTypeTag);
    reset();
  }

  @Override
  void processNumber() {
    markup.append(EDMXTemplates.buildNumberMember(dictionaryRow));
  }

  @Override
  void processStringListSingle() {
    markup.append(EDMXTemplates.buildEnumTypeSingleMember(dictionaryRow));
  }

  @Override
  void processString() {
    markup.append(EDMXTemplates.buildStringMember(dictionaryRow));
  }

  @Override
  void processBoolean() {
    markup.append(EDMXTemplates.buildBooleanMember(dictionaryRow));
  }

  @Override
  void processStringListMulti() {
    markup.append(EDMXTemplates.buildEnumTypeMultiMember(dictionaryRow));
  }

  @Override
  void processDate() {
    markup.append(EDMXTemplates.buildDateMember(dictionaryRow));
  }

  @Override
  void processTimestamp() {
    markup.append(EDMXTemplates.buildDateTimeWithOffsetMember(dictionaryRow));
  }

  @Override
  void processCollection() {
    LOG.debug("Collection Type is not supported!");
  }

  @Override
  void generateOutput() {
    StringBuilder entityContainerTag = new StringBuilder();

    //add entity type definitions for each of the Data Dictionary resources to EDM
    entityContainerTag.append("      <EntityContainer Name=\"RESO\">\n");
    resourceTemplates.forEach((name, templateContent) ->
            entityContainerTag.append("        <EntitySet Name=\"").append(sanitizeTSimpleName(name)).append("\" EntityType=\"" + RESO_NAMESPACE).append(".").append(name).append("\" />\n"));
    entityContainerTag.append("      </EntityContainer>\n");


    StringBuilder content = new StringBuilder();
    content.append(openingTag);

    //iterate through each of the found resources and generate their edm:EntityType content content
    resourceTemplates.forEach((name, templateContent) -> content.append(templateContent));

    //iterate through each of the lookup values and generate their edm:EnumType content
    //TODO: single vs. multi, single for now
    getLookups().forEach((lookupName, lookup) -> {
      content.append("\n      <EnumType Name=\"").append(sanitizeTSimpleName(lookupName)).append("\">");
      lookup.forEach(member -> content.append("\n        <Member Name=\"").append(sanitizeTSimpleName(member)).append("\" />"));
      content.append("\n      </EnumType>");
    });

    content.append("\n").append(entityContainerTag.toString());
    content.append(closingTag);

    Utils.createFile(getDirectoryName(), getReferenceResource().replace(".xlsx", ".edmx"), content.toString());
  }

  public static String sanitizeTSimpleName(String name) {
    if (name == null) return null;
    String val = name
            .replace(" ", "_")
            .replace("'", "_")
            .replace("&", "_")
            .replace("\\", "_")
            .replace("/", "_")
            .replace("[", "_")
            .replace("]", "_")
            .replace("(s)", "s")
            .replace("(", "_")
            .replace(")", "_")
            .replace("-", "_")
            .replace(",", "_")
            .replace("+", "_")
            .replace("$", "_")
            .replace(".", "_")
            .replace("%", "_");

    if (Character.isDigit(val.charAt(0))) val = "_" + val;
    return val.replaceAll(" {2,}", " ");
  }

  public static final class EDMXTemplates {

    public static String buildBooleanMember(DataDictionaryRow row) {
      if (row == null) return EMPTY_STRING;
      return "        <Property Name=\""+ sanitizeTSimpleName(row.getStandardName()) + "\" Type=\"Edm.Boolean\" />\n";
    }

    public static String buildDateMember(DataDictionaryRow row) {
      if (row == null) return EMPTY_STRING;
      return "        <Property Name=\""+ sanitizeTSimpleName(row.getStandardName()) + "\" Type=\"Edm.Date\" />\n";
    }

    public static String buildNumberMember(DataDictionaryRow row) {
      if (row == null) return EMPTY_STRING;

      if (row.getSuggestedMaxPrecision() != null) return buildDecimalMember(row);
      else return buildIntegerMember(row);
    }

    public static String buildDecimalMember(DataDictionaryRow row) {
      if (row == null) return EMPTY_STRING;
      String template = "        <Property Name=\"" + sanitizeTSimpleName(row.getStandardName()) + "\" Type=\"Edm.Decimal\"";

      //DD uses length as precision in this case
      if (row.getSuggestedMaxLength() != null) template += " Precision=\"" + row.getSuggestedMaxLength() + "\"";

      //DD uses precision as scale in this case
      if (row.getSuggestedMaxPrecision() != null) template += " Scale=\"" + row.getSuggestedMaxPrecision() + "\"";

      template += " />\n";

      return template;
    }

    public static String buildIntegerMember(DataDictionaryRow row) {
      if (row == null) return EMPTY_STRING;
      return  "        <Property Name=\"" + sanitizeTSimpleName(row.getStandardName()) + "\" Type=\"Edm.Int64\" />\n";
    }

    public static String buildEnumTypeMultiMember(DataDictionaryRow row) {
      if (row == null || row.getLookup() == null) return EMPTY_STRING;
      if (!row.getLookup().toLowerCase().contains("lookups")) return EMPTY_STRING;
      String lookupName = row.getLookup().replace("Lookups", "").trim();
      return "        <Property Name=\"" + sanitizeTSimpleName(row.getStandardName()) + "\" Type=\"" + RESO_NAMESPACE + "." + sanitizeTSimpleName(lookupName) + "\" />\n";
    }

    public static String buildEnumTypeSingleMember(DataDictionaryRow row) {
      if (row == null || row.getLookup() == null) return EMPTY_STRING;
      if (!row.getLookup().toLowerCase().contains("lookups")) return EMPTY_STRING;
      String lookupName = row.getLookup().replace("Lookups", "").trim();
      return "        <Property Name=\"" + sanitizeTSimpleName(row.getStandardName()) + "\" Type=\"" + RESO_NAMESPACE + "." + sanitizeTSimpleName(lookupName) + "\" />\n";
    }

    public static String buildStringMember(DataDictionaryRow row) {
      if (row == null) return EMPTY_STRING;
      String template = "        <Property Name=\"" + sanitizeTSimpleName(row.getStandardName()) + "\" Type=\"Edm.String\"";

      if (row.getSuggestedMaxLength() != null) template += " MaxLength=\"" + row.getSuggestedMaxLength() + "\"";
      template += " />\n";

      return template;
    }

    public static String buildDateTimeWithOffsetMember(DataDictionaryRow row) {
      if (row == null) return EMPTY_STRING;
      String template = "        <Property Name=\"" + sanitizeTSimpleName(row.getStandardName()) + "\" Type=\"Edm.DateTimeOffset\"";

      if (row.getSuggestedMaxLength() != null) template += " Precision=\"" + row.getSuggestedMaxLength() + "\"";
      template += " />\n";

      return template;
    }
  }
}
