package org.reso.certification.generators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.reso.models.DataDictionaryRow;

import java.util.*;

import static org.reso.certification.generators.BDDTemplates.buildHeaderInfo;

public class BDDProcessor implements DDWorksheetProcessor {
  private static final Logger LOG = LogManager.getLogger(BDDProcessor.class);

  static StringBuffer markup;
  static DataDictionaryRow.Builder rowBuilder = new DataDictionaryRow.Builder();
  static DataDictionaryRow dictionaryRow;

  public BDDProcessor() {
    markup = new StringBuffer();
  }

  public void reset() { markup = new StringBuffer(); }

  @Override
  public void addHeader(String headerInfo, String timestamp) {
    markup.append(buildHeaderInfo(headerInfo, timestamp));
  }

  @Override
  public void processRow(Row row) {
    dictionaryRow = rowBuilder
      .setStandardName(getStringValue(STANDARD_NAME_INDEX, row))
      .setDefinition(getStringValue(DEFINITION_INDEX, row))
      .setGroups(getArrayValue(GROUPS_INDEX, row))
      .setSimpleDataType(getStringValue(SIMPLE_DATA_TYPE_INDEX, row))
      .setSuggestedMaxLength(getIntegerValue(SUGGESTED_MAX_LENGTH_INDEX, row))
      .setSynonym(getStringValue(SYNONYM_INDEX, row))
      .setElementStatus(getStringValue(ELEMENT_STATUS_INDEX, row))
      .setBedes(getStringValue(BEDES_INDEX, row))
      .setCertificationLevel(getStringValue(CERTIFICATION_LEVEL_INDEX, row))
      .setRecordId(getIntegerValue(RECORD_ID_INDEX, row))
      .setLookupStatus(getStringValue(LOOKUP_STATUS_INDEX, row))
      .setLookup(getStringValue(LOOKUP_INDEX, row))
      .setCollection(getStringValue(COLLECTION_INDEX, row))
      .setSuggestedMaxPrecision(getIntegerValue(SUGGESTED_MAX_PRECISION_INDEX, row))
      .setRepeatingElement(getBooleanValue(REPEATING_ELEMENT_INDEX, row))
      .setPropertyType(getArrayValue(PROPERTY_TYPES_INDEX, row))
      .setPayloads(getStringValue(PAYLOADS_INDEX, row))
      .setSpanishStandardName(getStringValue(SPANISH_STANDARD_NAME_INDEX, row))
      .setStatusChangeDate(getStringValue(STATUS_CHANGE_DATE_INDEX, row))
      .setRevisedDate(getStringValue(REVISED_DATE_INDEX, row))
      .setAddedInVersion(getStringValue(ADDED_IN_VERSION_INDEX, row))
      .setWikiPageTitle(getStringValue(WIKI_PAGE_TITLE_INDEX, row))
      .setWikiPageURL(getStringValue(WIKI_PAGE_URL_INDEX, row))
      .setWikiPageID(getIntegerValue(WIKI_PAGE_ID_INDEX, row))
      .build();

    //now that row has been processed, extract field type and assemble the template
    switch (dictionaryRow.getSimpleDataType()) {
      case WELL_KNOWN_DATA_TYPES.NUMBER:
        markup.append(BDDTemplates.buildNumberTest(dictionaryRow.getStandardName(), dictionaryRow.getSuggestedMaxLength(), dictionaryRow.getSuggestedMaxPrecision()));
        break;
      case WELL_KNOWN_DATA_TYPES.STRING_LIST_SINGLE:
        markup.append(BDDTemplates.buildStringListSingleTest(dictionaryRow.getStandardName(), dictionaryRow.getSuggestedMaxLength()));
        break;
      case WELL_KNOWN_DATA_TYPES.STRING:
        markup.append(BDDTemplates.buildStringTest(dictionaryRow.getStandardName(), dictionaryRow.getSuggestedMaxLength()));
        break;
      case WELL_KNOWN_DATA_TYPES.BOOLEAN:
        markup.append(BDDTemplates.buildBooleanTest(dictionaryRow.getStandardName()));
        break;
      case WELL_KNOWN_DATA_TYPES.STRING_LIST_MULTI:
        markup.append(BDDTemplates.buildStringListMultiTest(dictionaryRow.getStandardName(), dictionaryRow.getSuggestedMaxLength()));
        break;
      case WELL_KNOWN_DATA_TYPES.DATE:
        markup.append(BDDTemplates.buildDateTest(dictionaryRow.getStandardName(), dictionaryRow.getSuggestedMaxLength()));
        break;
      case WELL_KNOWN_DATA_TYPES.TIMESTAMP:
        markup.append(BDDTemplates.buildTimestampTest(dictionaryRow.getStandardName(), dictionaryRow.getSuggestedMaxLength()));
        break;
      case WELL_KNOWN_DATA_TYPES.COLLECTION:
        LOG.debug("Collection Type is not supported!");
        break;
      default:
        if (dictionaryRow.getSimpleDataType() != null)
          LOG.debug("Data type: " + dictionaryRow.getSimpleDataType() + " is not supported!");
    }
  }

  private Integer getIntegerValue(Integer index, Row row, Integer defaultValue) {
    if (!(index >= 0)) return defaultValue;
    Integer value;
    DataFormatter formatter = new DataFormatter();
    try {
      value = Integer.parseInt(formatter.formatCellValue(row.getCell(index)));
    } catch (Exception ex) {
      value = defaultValue;
    }
    return value;
  }

  private Integer getIntegerValue(Integer index, Row row) {
    return getIntegerValue(index, row, null);
  }

  private String getStringValue(Integer index, Row row, String defaultValue) {
    if (!(index >= 0)) return defaultValue;
    String value;
    DataFormatter formatter = new DataFormatter();
    try {
      value = formatter.formatCellValue(row.getCell(index));
    } catch (Exception ex) {
      value = defaultValue;
    }
    return value;
  }

  private String getStringValue(Integer index, Row row) {
    return getStringValue(index, row, null);
  }

  private Boolean getBooleanValue(Integer index, Row row, Boolean defaultValue) {
    if (!(index >= 0)) return defaultValue;
    Boolean value = false;
    String cellValue;
    DataFormatter formatter = new DataFormatter();
    try {
      cellValue = formatter.formatCellValue(row.getCell(index));
      //value coming from spreadsheet is "yes"
      if (cellValue.toLowerCase().contains("yes")) {
        value = true;
      }
    } catch (Exception ex) {
      value = defaultValue;
    }
    return value;
  }

  private Boolean getBooleanValue(Integer index, Row row) {
    return getBooleanValue(index, row, false);
  }

  private List<String> getArrayValue(Integer index, Row row, List<String> defaultValue) {
    if (!(index >= 0)) return defaultValue;
    List<String> value = new ArrayList<>();
    DataFormatter formatter = new DataFormatter();
    try {
      value = Arrays.asList(
          formatter.formatCellValue(row.getCell(GROUPS_INDEX)).replace(" ", "").split(","));
    } catch (Exception ex) {
      value = defaultValue;
    }
    return value;
  }

  private List<String> getArrayValue(Integer index, Row row) {
    return getArrayValue(index, row, new ArrayList<>());
  }

  private static final class WELL_KNOWN_DATA_TYPES {
    public static final String
      NUMBER = "Number",
      STRING_LIST_SINGLE = "String List, Single",
      STRING_LIST_MULTI = "String List, Multi",
      STRING = "String",
      BOOLEAN = "Boolean",
      DATE = "Date",
      TIMESTAMP = "Timestamp",
      COLLECTION = "Collection";
  }


  @Override
  public String getTemplateMarkup() {
    return markup.toString();
  }
}
