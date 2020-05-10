package org.reso.certification.generators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.reso.commander.common.Utils;
import org.reso.models.DataDictionaryRow;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.reso.certification.generators.WorksheetProcessor.WELL_KNOWN_COLUMN_INDICES.*;
import static org.reso.certification.generators.WorksheetProcessor.WELL_KNOWN_DATA_TYPES.*;
import static org.reso.certification.generators.WorksheetProcessor.WELL_KNOWN_HEADER_NAMES.*;
import static org.reso.certification.generators.WorksheetProcessor.WELL_KNOWN_HEADER_NAMES.COLLECTION;
import static org.reso.certification.generators.WorksheetProcessor.WELL_KNOWN_HEADER_NAMES.WIKI_PAGE_ID;
import static org.reso.certification.stepdefs.DataDictionary.REFERENCE_RESOURCE;

public abstract class WorksheetProcessor {
  private static final Logger LOG = LogManager.getLogger(WorksheetProcessor.class);

  String referenceResource = null;
  StringBuffer markup;
  DataDictionaryRow dictionaryRow;
  String startTimestamp;
  Map<String, String> resourceTemplates = new LinkedHashMap<>();
  Map<String, Set<String>> lookups = new LinkedHashMap<>();

  public WorksheetProcessor() {
    startTimestamp = Utils.getTimestamp();
    markup = new StringBuffer();
    try {
      lookups = buildLookups();
    } catch (Exception ex) {
      LOG.error(ex);
    }
  }

  public static Integer getIntegerValue(Integer index, Row row, Integer defaultValue) {
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

  public static Integer getIntegerValue(Integer index, Row row) {
    return getIntegerValue(index, row, null);
  }

  public static String getStringValue(Integer index, Row row, String defaultValue) {
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

  public static String getStringValue(Integer index, Row row) {
    return getStringValue(index, row, null);
  }

  public static Boolean getBooleanValue(Integer index, Row row, Boolean defaultValue) {
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

  public static Boolean getBooleanValue(Integer index, Row row) {
    return getBooleanValue(index, row, false);
  }

  public static List<String> getArrayValue(Integer index, Row row, List<String> defaultValue) {
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

  public static List<String> getArrayValue(Integer index, Row row) {
    return getArrayValue(index, row, new ArrayList<>());
  }

  public static DataDictionaryRow extractDataDictionaryRow(Row row) {
    return new DataDictionaryRow.Builder()
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
  }

  abstract void processSheet(Sheet sheet);

  abstract void processNumber();

  abstract void processStringListSingle();

  abstract void processString();

  abstract void processBoolean();

  abstract void processStringListMulti();

  abstract void processDate();

  abstract void processTimestamp();

  abstract void processCollection();

  abstract void generateOutput();

  void processRow(Row row) {
    dictionaryRow = extractDataDictionaryRow(row);

    //now that row has been processed, extract field type and assemble the template
    switch (dictionaryRow.getSimpleDataType()) {
      case NUMBER:
        processNumber();
        break;
      case STRING_LIST_SINGLE:
        processStringListSingle();
        break;
      case STRING:
        processString();
        break;
      case BOOLEAN:
        processBoolean();
        break;
      case STRING_LIST_MULTI:
        processStringListMulti();
        break;
      case DATE:
        processDate();
        break;
      case TIMESTAMP:
        processTimestamp();
        break;
      case COLLECTION:
        processCollection();
        break;
      default:
        if (dictionaryRow.getSimpleDataType() != null)
          LOG.debug("Data type: " + dictionaryRow.getSimpleDataType() + " is not supported!");
    }
  }

  String getDirectoryName() {
    return startTimestamp + "-" + REFERENCE_RESOURCE.toLowerCase().substring(0, REFERENCE_RESOURCE.lastIndexOf("."));
  }

  public String getReferenceResource() {
    return referenceResource;
  }

  public void setReferenceResource(String referenceResource) {
    this.referenceResource = referenceResource;
  }

  public Workbook getReferenceWorkbook() throws IOException, InvalidFormatException {
    return new XSSFWorkbook(OPCPackage.open(Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(referenceResource))));
  }

  void finishProcessing(Sheet sheet) {
    resourceTemplates.put(sheet.getSheetName(), markup.toString());
    reset();
  }

  Map<String, Set<String>> buildLookups() throws Exception {
    final String TAB_NAME = "Lookup Fields and Values";
    final int LOOKUP_INDEX = 0, LOOKUP_VALUE_INDEX = 1;
    DataFormatter formatter = new DataFormatter();
    AtomicReference<String> lookup = new AtomicReference<>();
    AtomicReference<String> lookupValue = new AtomicReference<>();

//    Sheet sheet = getReferenceWorkbook().getSheet(TAB_NAME);
//    sheet.rowIterator().forEachRemaining(row -> {
//      lookup.set(formatter.getDefaultFormat(row.getCell(LOOKUP_INDEX)).toString());
//      lookupValue.set(formatter.getDefaultFormat(row.getCell(LOOKUP_VALUE_INDEX)).toString());
//
//      if (!lookups.containsKey(lookup.get())) {
//        lookups.put(lookup.get(), new LinkedHashSet<>());
//      }
//      lookups.get(lookup.get()).add(lookupValue.get());
//    });
//    lookups.forEach((key, items) -> LOG.info("key: " + key + " , items: " + items.toString() + "\n"));
//    return lookups;
    return null;
  }

  public void reset() {
    markup = new StringBuffer();
  }

  public static final List<String> WELL_KNOWN_HEADERS = Arrays.asList(
      STANDARD_NAME,
      DEFINITION,
      GROUPS,
      SIMPLE_DATA_TYPE,
      SUGGESTED_MAX_LENGTH,
      SYNONYM,
      ELEMENT_STATUS,
      BEDES,
      CERTIFICATION_LEVEL,
      RECORD_ID,
      LOOKUP_STATUS,
      LOOKUP,
      COLLECTION,
      SUGGESTED_MAX_PRECISION,
      REPEATING_ELEMENT,
      PROPERTY_TYPES,
      PAYLOADS,
      SPANISH_STANDARD_NAME,
      STATUS_CHANGE_DATE,
      REVISED_DATE,
      ADDED_IN_VERSION,
      WIKI_PAGE_TITLE,
      WIKI_PAGE_URL,
      WIKI_PAGE_ID
  );

  public static final class WELL_KNOWN_DATA_TYPES {
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

  public static final class WELL_KNOWN_HEADER_NAMES {
    public static final String
        STANDARD_NAME = "StandardName",
        DEFINITION = "Definition",
        GROUPS = "Groups",
        SIMPLE_DATA_TYPE = "SimpleDataType",
        SUGGESTED_MAX_LENGTH = "SugMaxLength",
        SYNONYM = "Synonym",
        ELEMENT_STATUS = "ElementStatus",
        BEDES = "BEDES",
        CERTIFICATION_LEVEL = "CertificationLevel",
        RECORD_ID = "RecordID",
        LOOKUP_STATUS = "LookupStatus",
        LOOKUP = "Lookup",
        COLLECTION = "Collection",
        SUGGESTED_MAX_PRECISION = "SugMaxPrecision",
        REPEATING_ELEMENT = "RepeatingElement",
        PROPERTY_TYPES = "PropertyTypes",
        PAYLOADS = "Payloads",
        SPANISH_STANDARD_NAME = "SpanishStandardName",
        STATUS_CHANGE_DATE = "StatusChangeDate",
        REVISED_DATE = "RevisedDate",
        ADDED_IN_VERSION = "AddedInVersion",
        WIKI_PAGE_TITLE = "Wiki Page Title",
        WIKI_PAGE_URL = "Wiki Page URL",
        WIKI_PAGE_ID = "Wiki Page ID";
  }

  public static final class WELL_KNOWN_COLUMN_INDICES {
    public static final Integer
        STANDARD_NAME_INDEX = 0,
        DEFINITION_INDEX = 1,
        GROUPS_INDEX = 2,
        SIMPLE_DATA_TYPE_INDEX = 3,
        SUGGESTED_MAX_LENGTH_INDEX = 4,
        SYNONYM_INDEX = 5,
        ELEMENT_STATUS_INDEX = 6,
        BEDES_INDEX = 7,
        CERTIFICATION_LEVEL_INDEX = 8,
        RECORD_ID_INDEX = 9,
        LOOKUP_STATUS_INDEX = 10,
        LOOKUP_INDEX = 11,
        COLLECTION_INDEX = 12,
        SUGGESTED_MAX_PRECISION_INDEX = 13,
        REPEATING_ELEMENT_INDEX = 14,
        PROPERTY_TYPES_INDEX = 15,
        PAYLOADS_INDEX = 16,
        SPANISH_STANDARD_NAME_INDEX = 17,
        STATUS_CHANGE_DATE_INDEX = 18,
        REVISED_DATE_INDEX = 19,
        ADDED_IN_VERSION_INDEX = 20,
        WIKI_PAGE_TITLE_INDEX = 21,
        WIKI_PAGE_URL_INDEX = 22,
        WIKI_PAGE_ID_INDEX = 23;
  }
}
