package org.reso.certification.codegen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.reso.commander.common.Utils;
import org.reso.models.DataDictionaryRow;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertTrue;
import static org.reso.certification.codegen.WorksheetProcessor.WELL_KNOWN_COLUMN_INDICES.*;
import static org.reso.certification.codegen.WorksheetProcessor.WELL_KNOWN_DATA_TYPES.*;
import static org.reso.certification.codegen.WorksheetProcessor.WELL_KNOWN_HEADER_NAMES.COLLECTION;
import static org.reso.certification.codegen.WorksheetProcessor.WELL_KNOWN_HEADER_NAMES.*;
import static org.reso.certification.stepdefs.DataDictionary.REFERENCE_RESOURCE;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;

public abstract class WorksheetProcessor {
  private static final Logger LOG = LogManager.getLogger(WorksheetProcessor.class);

  String referenceResource = null;
  StringBuffer markup;
  DataDictionaryRow dictionaryRow;
  Sheet sheet;
  String startTimestamp;
  static final Map<String, String> resourceTemplates = new LinkedHashMap<>();
  static final Map<String, Set<String>> lookups = new LinkedHashMap<>();
  static final Map<String, Map<String, DataDictionaryRow>> processedResourceRows = new LinkedHashMap<>(new LinkedHashMap<>());

  public WorksheetProcessor() {
    startTimestamp = Utils.getTimestamp();
    markup = new StringBuffer();
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
        .setDisplayName(getStringValue(DISPLAY_NAME_INDEX, row))
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

  abstract void processResourceSheet(Sheet sheet);

  abstract void processNumber();

  abstract void processStringListSingle();

  abstract void processString();

  abstract void processBoolean();

  abstract void processStringListMulti();

  abstract void processDate();

  abstract void processTimestamp();

  abstract void processCollection();

  abstract void generateOutput();

  void processResourceRow(Row row) {
    assertTrue(getDefaultErrorMessage("sheet name was null but was expected to contain a resource name!"),
        sheet != null && sheet.getSheetName() != null);

    dictionaryRow = extractDataDictionaryRow(row);
    dictionaryRow.setParentResourceName(sheet.getSheetName());

    processedResourceRows.putIfAbsent(sheet.getSheetName(), new LinkedHashMap<>());
    processedResourceRows.get(sheet.getSheetName()).put(dictionaryRow.getSimpleDataType(), dictionaryRow);

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

  public Workbook getReferenceWorkbook() {
    try {
      return new XSSFWorkbook(
              OPCPackage.open(Objects.requireNonNull(this.getClass().getClassLoader().getResource(getReferenceResource())).getFile()));
    } catch (Exception ex) {
      LOG.error(getDefaultErrorMessage(ex));
    }
    return null;
  }

  void finishProcessingResourceSheet(Sheet sheet) {
    resourceTemplates.put(sheet.getSheetName(), markup.toString());
    reset();
  }

  public void buildLookups() {
    final String TAB_NAME = "Lookup Fields and Values";
    final int LOOKUP_INDEX = 0, LOOKUP_VALUE_INDEX = 1;
    DataFormatter formatter = new DataFormatter();
    AtomicReference<String> lookup = new AtomicReference<>();
    AtomicReference<String> lookupValue = new AtomicReference<>();

    Sheet sheet = getReferenceWorkbook().getSheet(TAB_NAME);
    sheet.rowIterator().forEachRemaining(row -> {
      if (row.getRowNum() > 0) {
        lookup.set(formatter.formatCellValue(row.getCell(LOOKUP_INDEX)));
        lookupValue.set(formatter.formatCellValue(row.getCell(LOOKUP_VALUE_INDEX)));

        if (!lookups.containsKey(lookup.get())) {
          lookups.put(lookup.get(), new LinkedHashSet<>());
        }
        lookups.get(lookup.get()).add(lookupValue.get());
      }
    });
    lookups.forEach((key, items) -> LOG.info("key: " + key + " , items: " + items.toString()));
  }

  public Map<String, Set<String>> getLookups() {
    return lookups;
  }

  public void reset() {
    markup = new StringBuffer();
  }

  public static final List<String> WELL_KNOWN_HEADERS = Arrays.asList(
      STANDARD_NAME,
      DISPLAY_NAME,
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
        DISPLAY_NAME = "DisplayName",
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
        DISPLAY_NAME_INDEX = 1,
        DEFINITION_INDEX = 2,
        GROUPS_INDEX = 3,
        SIMPLE_DATA_TYPE_INDEX = 4,
        SUGGESTED_MAX_LENGTH_INDEX = 5,
        SYNONYM_INDEX = 6,
        ELEMENT_STATUS_INDEX = 7,
        BEDES_INDEX = 8,
        CERTIFICATION_LEVEL_INDEX = 9,
        RECORD_ID_INDEX = 10,
        LOOKUP_STATUS_INDEX = 11,
        LOOKUP_INDEX = 12,
        COLLECTION_INDEX = 13,
        SUGGESTED_MAX_PRECISION_INDEX = 14,
        REPEATING_ELEMENT_INDEX = 15,
        PROPERTY_TYPES_INDEX = 16,
        PAYLOADS_INDEX = 17,
        SPANISH_STANDARD_NAME_INDEX = 18,
        STATUS_CHANGE_DATE_INDEX = 19,
        REVISED_DATE_INDEX = 20,
        ADDED_IN_VERSION_INDEX = 21,
        WIKI_PAGE_TITLE_INDEX = 22,
        WIKI_PAGE_URL_INDEX = 23,
        WIKI_PAGE_ID_INDEX = 24;
  }
}
