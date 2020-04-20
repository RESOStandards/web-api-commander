package org.reso.certification.generators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.reso.models.DataDictionaryRow;

import static org.reso.certification.generators.DataDictionaryGenerator.createFile;

public class EDMXProcessor extends WorksheetProcessor {
  private static final Logger LOG = LogManager.getLogger(EDMXProcessor.class);
  String openEntityTypeTag = null, closeEntityTypeTag = null;
  final static String EMPTY_STRING = "";

  @Override
  public void processSheet(Sheet sheet) {
    openEntityTypeTag = "      <EntityType Name=\"" + sheet.getSheetName() + "\">\n";
    closeEntityTypeTag = "      </EntityType>\n";
  }

  @Override
  void finishProcessing(Sheet sheet) {
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
    final String
      openingTag =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
        "<edmx:Edmx xmlns:edmx=\"http://docs.oasis-open.org/odata/ns/edmx\" Version=\"4.0\">\n" +
        "  <edmx:DataServices>\n" +
        "    <Schema xmlns=\"http://docs.oasis-open.org/odata/ns/edm\" Namespace=\"org.reso.metadata\">\n",
      closingTag =
        "    </Schema>\n" +
        "  </edmx:DataServices>\n" +
        "</edmx:Edmx>\n";

    StringBuilder entityContainerTag = new StringBuilder();
    entityContainerTag.append("      <EntityContainer>\n");
    resourceTemplates.forEach((name, templateContent) ->
      entityContainerTag.append("        <EntitySet Name=\"" + name + "\" EntityType=\"" + name + "\" />\n"));
    entityContainerTag.append("      </EntityContainer>\n");

    StringBuilder content = new StringBuilder();
    content.append(openingTag);
    resourceTemplates.forEach((name, templateContent) -> content.append(templateContent));
    //TODO: add lookups as well
    content.append(entityContainerTag.toString());
    content.append(closingTag);

    createFile(getDirectoryName(), getReferenceResource().replace(".xlsx", ".edmx"), content.toString());
  }

  public static final class EDMXTemplates {

    public static String buildBooleanMember(DataDictionaryRow row) {
      if (row == null) return EMPTY_STRING;
      return "        <Property Name=\""+ row.getStandardName() + "\" Type=\"Edm.Boolean\" />\n";
    }

    public static String buildDateMember(DataDictionaryRow row) {
      if (row == null) return EMPTY_STRING;
      return "        <Property Name=\""+ row.getStandardName() + "\" Type=\"Edm.Date\" />\n";
    }

    public static String buildNumberMember(DataDictionaryRow row) {
      if (row == null) return EMPTY_STRING;

      if (row.getSuggestedMaxPrecision() != null) return buildDecimalMember(row);
      else return buildIntegerMember(row);
    }

    public static String buildDecimalMember(DataDictionaryRow row) {
      if (row == null) return EMPTY_STRING;
      String template = "        <Property Name=\"" + row.getStandardName() + "\" Type=\"Edm.Decimal\"";

      //DD uses length as precision in this case
      if (row.getSuggestedMaxLength() != null) template += " Precision=\"" + row.getSuggestedMaxLength() + "\"";

      //DD uses precision as scale in this case
      if (row.getSuggestedMaxPrecision() != null) template += " Scale=\"" + row.getSuggestedMaxPrecision() + "\"";

      template += " />\n";

      return template;
    }

    public static String buildIntegerMember(DataDictionaryRow row) {
      if (row == null) return EMPTY_STRING;
      return  "        <Property Name=\"" + row.getStandardName() + "\" Type=\"Edm.Int64\" />\n";
    }

    public static String buildEnumTypeMultiMember(DataDictionaryRow row) {
      if (row == null || row.getLookup() == null) return EMPTY_STRING;
      if (!row.getLookup().toLowerCase().contains("lookups")) return EMPTY_STRING;
      String lookupName = row.getLookup().replace("Lookups", "").trim();
      return "        <Property Name=\"" + row.getStandardName() + "\" Type=\"" + lookupName + "\" />\n";
    }

    public static String buildEnumTypeSingleMember(DataDictionaryRow row) {
      if (row == null || row.getLookup() == null) return EMPTY_STRING;
      if (!row.getLookup().toLowerCase().contains("lookups")) return EMPTY_STRING;
      String lookupName = row.getLookup().replace("Lookups", "").trim();
      return "        <Property Name=\"" + row.getStandardName() + "\" Type=\"" + lookupName + "\" />\n";
    }

    public static String buildStringMember(DataDictionaryRow row) {
      if (row == null) return EMPTY_STRING;
      String template = "        <Property Name=\"" + row.getStandardName() + "\" Type=\"Edm.String\"";

      if (row.getSuggestedMaxLength() != null) template += " MaxLength=\"" + row.getSuggestedMaxLength() + "\"";
      template += " />\n";

      return template;
    }

    public static String buildDateTimeWithOffsetMember(DataDictionaryRow row) {
      if (row == null) return EMPTY_STRING;
      String template = "        <Property Name=\"" + row.getStandardName() + "\" Type=\"Edm.DateTimeOffset\"";

      if (row.getSuggestedMaxLength() != null) template += " Precision=\"" + row.getSuggestedMaxLength() + "\"";
      template += " />\n";

      return template;
    }
  }
}
