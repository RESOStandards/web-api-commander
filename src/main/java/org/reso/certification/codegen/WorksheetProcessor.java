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
import org.reso.models.StandardField;

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
  static final Map<String, String> resourceTemplates = new LinkedHashMap<>();
  static final Map<String, Set<String>> enumerations = new LinkedHashMap<>();
  static final Map<String, Map<String, StandardField>> processedStandardFields = new LinkedHashMap<>(new LinkedHashMap<>());
  private static final Logger LOG = LogManager.getLogger(WorksheetProcessor.class);
  String referenceResource = null;
  StringBuffer markup;
  Sheet sheet;
  String startTimestamp;

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
    final String BOOLEAN_VALUE = "yes";

    Boolean value = false;
    String cellValue;
    DataFormatter formatter = new DataFormatter();
    try {
      cellValue = formatter.formatCellValue(row.getCell(index));
      if (cellValue.toLowerCase().contains(BOOLEAN_VALUE)) {
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

  public static StandardField extractDataDictionaryRow(Row row) {
    return new StandardField.Builder()
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

  abstract void processNumber(StandardField row);

  abstract void processStringListSingle(StandardField row);

  abstract void processString(StandardField row);

  abstract void processBoolean(StandardField row);

  abstract void processStringListMulti(StandardField row);

  abstract void processDate(StandardField row);

  abstract void processTimestamp(StandardField row);

  abstract void processCollection(StandardField row);

  abstract void generateOutput();

  void processResourceRow(Row row) {
    assertTrue(getDefaultErrorMessage("sheet name was null but was expected to contain a resource name!"),
        sheet != null && sheet.getSheetName() != null);

    StandardField standardField = extractDataDictionaryRow(row);
    standardField.setParentResourceName(sheet.getSheetName());

    //add empty top-level resource name map
    processedStandardFields.putIfAbsent(sheet.getSheetName(), new LinkedHashMap<>());

    //add a resource, standard field
    processedStandardFields.get(sheet.getSheetName()).put(standardField.getStandardName(), standardField);

    //now that row has been processed, extract field type and assemble the template
    switch (standardField.getSimpleDataType()) {
      case NUMBER:
        processNumber(standardField);
        break;
      case STRING_LIST_SINGLE:
        processStringListSingle(standardField);
        break;
      case STRING:
        processString(standardField);
        break;
      case BOOLEAN:
        processBoolean(standardField);
        break;
      case STRING_LIST_MULTI:
        processStringListMulti(standardField);
        break;
      case DATE:
        processDate(standardField);
        break;
      case TIMESTAMP:
        processTimestamp(standardField);
        break;
      case COLLECTION:
        processCollection(standardField);
        break;
      default:
        if (standardField.getSimpleDataType() != null)
          LOG.debug("Data type: " + standardField.getSimpleDataType() + " is not supported!");
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
      return new XSSFWorkbook(OPCPackage.open(Objects.requireNonNull(
          this.getClass().getClassLoader().getResource(getReferenceResource())).getFile()));
    } catch (Exception ex) {
      LOG.error(getDefaultErrorMessage(ex));
    }
    return null;
  }

  void finishProcessingResourceSheet(Sheet sheet) {
    resourceTemplates.put(sheet.getSheetName(), markup.toString());
    reset();
  }

  public void buildEnumerationMap() {
    final String ENUMERATION_TAB_NAME = "Lookup Fields and Values";
    final int LOOKUP_NAME_INDEX = 0, STANDARD_NAME_INDEX = 1;

    DataFormatter formatter = new DataFormatter();
    AtomicReference<String> lookupName = new AtomicReference<>();
    AtomicReference<String> standardName = new AtomicReference<>();

    Sheet sheet = getReferenceWorkbook().getSheet(ENUMERATION_TAB_NAME);
    sheet.rowIterator().forEachRemaining(row -> {
      if (row.getRowNum() > 0) {
        lookupName.set(formatter.formatCellValue(row.getCell(LOOKUP_NAME_INDEX)));
        standardName.set(formatter.formatCellValue(row.getCell(STANDARD_NAME_INDEX)));

        if (!enumerations.containsKey(lookupName.get())) {
          enumerations.put(lookupName.get(), new LinkedHashSet<>());
        }
        enumerations.get(lookupName.get()).add(standardName.get());
      }
    });
    enumerations.forEach((key, items) -> LOG.info("key: " + key + " , items: " + items.toString()));
  }

  public Map<String, Set<String>> getEnumerations() {
    return enumerations;
  }

  public void reset() {
    markup = new StringBuffer();
  }

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
