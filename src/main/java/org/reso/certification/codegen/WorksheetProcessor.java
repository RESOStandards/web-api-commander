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
import org.reso.models.StandardEnumeration;
import org.reso.models.StandardField;
import org.reso.models.StandardRelationship;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.reso.certification.codegen.WorksheetProcessor.WELL_KNOWN_DATA_TYPES.*;
import static org.reso.certification.codegen.WorksheetProcessor.WELL_KNOWN_FIELD_HEADERS.COLLECTION;
import static org.reso.certification.codegen.WorksheetProcessor.WELL_KNOWN_FIELD_HEADERS.STANDARD_NAME;
import static org.reso.certification.stepdefs.DataDictionary.REFERENCE_RESOURCE;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;

public abstract class WorksheetProcessor {

  static final Map<String, String> resourceTemplates = new LinkedHashMap<>();
  static final Map<String, Set<StandardEnumeration>> enumerations = new LinkedHashMap<>();
  static final Map<String, Map<String, StandardField>> processedStandardFields = new LinkedHashMap<>(new LinkedHashMap<>());
  private static final Logger LOG = LogManager.getLogger(WorksheetProcessor.class);
  String referenceResource = null;
  StringBuffer markup;
  Sheet sheet;
  String startTimestamp;

  Map<String, Integer> wellKnownStandardFieldHeaderMap = new LinkedHashMap<>();
  Map<String, Integer> wellKnownStandardEnumerationHeaderMap = new LinkedHashMap<>();

  private final List<StandardRelationship> standardRelationships = new ArrayList<>();

  static final int
          TARGET_RESOURCE = 0,
          TARGET_RESOURCE_KEY = 1,
          TARGET_STANDARD_NAME = 2,
          CARDINALITY = 3,
          SOURCE_RESOURCE = 4,
          SOURCE_RESOURCE_KEY = 5;

  public WorksheetProcessor() {
    startTimestamp = Utils.getTimestamp();
    markup = new StringBuffer();
  }

  public void buildWellKnownStandardFieldHeaderMap(Sheet sheet) {
    wellKnownStandardFieldHeaderMap = new LinkedHashMap<>();
    sheet.getRow(0).cellIterator().forEachRemaining(cell ->
            wellKnownStandardFieldHeaderMap.put(cell.getStringCellValue(), cell.getColumnIndex()));
  }

  public Integer getWellKnownStandardFieldIndex(String wellKnownStandardFieldKey) {
    return wellKnownStandardFieldHeaderMap.get(wellKnownStandardFieldKey);
  }

  public Integer getWellKnownStandardEnumerationIndex(String wellKnownStandardEnumerationKey) {
    return wellKnownStandardEnumerationHeaderMap.get(wellKnownStandardEnumerationKey);
  }

  public void buildWellKnownStandardEnumerationHeaderMap(Sheet sheet) {
    wellKnownStandardEnumerationHeaderMap = new LinkedHashMap<>();
    sheet.getRow(0).cellIterator().forEachRemaining(cell ->
        wellKnownStandardEnumerationHeaderMap.put(cell.getStringCellValue(), cell.getColumnIndex()));
  }

  public static Integer getIntegerValue(Integer index, Row row, Integer defaultValue) {
    if (index == null || !(index >= 0)) return defaultValue;
    Integer value;
    DataFormatter formatter = new DataFormatter();
    try {
      value = Integer.parseInt(formatter.formatCellValue(row.getCell(index)));
    } catch (Exception ex) {
      value = defaultValue;
    }
    return value;
  }

  public List<StandardRelationship> getStandardRelationships() {
    return this.standardRelationships;
  }

  public static Integer getIntegerValue(Integer index, Row row) {
    return getIntegerValue(index, row, null);
  }

  public static String getStringValue(Integer index, Row row, String defaultValue) {
    if (index == null || !(index >= 0)) return defaultValue;
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
    if (index == null || !(index >= 0)) return defaultValue;
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
    if (index == null || !(index >= 0)) return defaultValue;
    DataFormatter formatter = new DataFormatter();
    String cellValue;
    List<String> value = new ArrayList<>();
    try {
      cellValue = formatter.formatCellValue(row.getCell(index));
      if (cellValue != null && cellValue.length() > 0) {
        //LOG.info("Cell index is: " + index + ", cell value is: " + cellValue);
        value = Arrays.stream(cellValue
                .replace(" ", "").split(","))
                .map(String::trim)
                .filter(item -> item.length() > 0)
                .collect(Collectors.toList());
      }
    } catch (Exception ex) {
      value = defaultValue;
    }
    return value;
  }

  public static List<String> getArrayValue(Integer index, Row row) {
    return getArrayValue(index, row, new ArrayList<>());
  }

  public StandardRelationship deserializeStandardRelationshipRow(Row row) {
    StandardRelationship standardRelationship = null;

    try {
      standardRelationship = StandardRelationship.Builder.build(
          row.getCell(TARGET_RESOURCE).getStringCellValue(),
          row.getCell(TARGET_RESOURCE_KEY, Row.CREATE_NULL_AS_BLANK).getStringCellValue(),
          row.getCell(TARGET_STANDARD_NAME).getStringCellValue(),
          StandardRelationship.Cardinality.stream().filter(cardinality ->
              cardinality.getRelationshipType().contentEquals(
                  row.getCell(CARDINALITY).getStringCellValue())).findFirst().orElseThrow(Exception::new),
          row.getCell(SOURCE_RESOURCE).getStringCellValue(),
          row.getCell(SOURCE_RESOURCE_KEY).getStringCellValue()
      );
    } catch (Exception ex) {
      LOG.error(ex);
    }
    return standardRelationship;
  }

  public StandardField deserializeStandardFieldRow(Row row) {
    return new StandardField.Builder()
        .setStandardName(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.STANDARD_NAME), row))
        .setDisplayName(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.DISPLAY_NAME), row))
        .setDefinition(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.DEFINITION), row))
        .setGroups(getArrayValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.GROUPS), row))
        .setSimpleDataType(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.SIMPLE_DATA_TYPE), row))
        .setSuggestedMaxLength(getIntegerValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.SUGGESTED_MAX_LENGTH), row))
        .setSynonyms(getArrayValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.SYNONYM), row))
        .setElementStatus(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.ELEMENT_STATUS), row))
        .setBedes(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.BEDES), row))
        .setCertificationLevel(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.CERTIFICATION_LEVEL), row))
        .setRecordId(getIntegerValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.RECORD_ID), row))
        .setLookupStatus(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.LOOKUP_STATUS), row))
        .setLookup(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.LOOKUP), row))
        .setCollection(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.COLLECTION), row))
        .setSuggestedMaxPrecision(getIntegerValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.SUGGESTED_MAX_PRECISION), row))
        .setRepeatingElement(getBooleanValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.REPEATING_ELEMENT), row))
        .setPropertyTypes(getArrayValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.PROPERTY_TYPES), row))
        .setPayloads(getArrayValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.PAYLOADS), row))
        .setSpanishStandardName(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.SPANISH_STANDARD_NAME), row))
        .setStatusChangeDate(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.STATUS_CHANGE_DATE), row))
        .setRevisedDate(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.REVISED_DATE), row))
        .setAddedInVersion(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.ADDED_IN_VERSION), row))
        .setWikiPageTitle(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.WIKI_PAGE_TITLE), row))
        .setWikiPageURL(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.WIKI_PAGE_URL), row))
        .setWikiPageID(getIntegerValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.WIKI_PAGE_ID), row))
        .build();
  }

  public StandardEnumeration deserializeStandardEnumerationRow(Row row) {
    return new StandardEnumeration.Builder()
        .setLookupField(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.LOOKUP_FIELD), row))
        .setLookupValue(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.LOOKUP_VALUE), row))
        .setLookupDisplayName(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.LOOKUP_DISPLAY_NAME), row))
        .setDefinition(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.DEFINITION), row))
        .setLookupDisplayNameSynonyms(getArrayValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.LOOKUP_DISPLAY_NAME_SYNONYMS), row))
        .setBedes(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.BEDES), row))
        .setReferences(getArrayValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.REFERENCES), row))
        .setElementStatus(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.ELEMENT_STATUS), row))
        .setLookupId(getIntegerValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.LOOKUP_ID), row))
        .setLookupFieldId(getIntegerValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.LOOKUP_FIELD_ID), row))
        .setSpanishLookupField(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.SPANISH_LOOKUP_FIELD), row))
        .setSpanishLookupValue(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.SPANISH_LOOKUP_VALUE), row))
        .setStatusChangeDate(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.STATUS_CHANGE_DATE), row))
        .setRevisedDate(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.REVISED_DATE), row))
        .setAddedInVersion(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.ADDED_IN_VERSION), row))
        .setWikiPageTitle(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.WIKI_PAGE_TITLE), row))
        .setWikiPageUrl(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.WIKI_PAGE_URL), row))
        .build();

  }

  abstract void processResourceSheet(Sheet sheet);

  abstract void processNumber(StandardField field);

  abstract void processStringListSingle(StandardField field);

  abstract void processString(StandardField field);

  abstract void processBoolean(StandardField field);

  abstract void processStringListMulti(StandardField field);

  abstract void processDate(StandardField field);

  abstract void processTimestamp(StandardField field);

  abstract void processCollection(StandardField field);

  abstract void generateOutput();

  void processResourceRow(Row row) {
    assertTrue(getDefaultErrorMessage("sheet name was null but was expected to contain a resource name!"),
        sheet != null && sheet.getSheetName() != null);

    //if there's no field in the standard name column, don't process the row
    if (row.getCell(getWellKnownStandardFieldIndex(STANDARD_NAME)) == null) return;

    StandardField standardField = deserializeStandardFieldRow(row);
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

  public void beforeResourceSheetProcessed(Sheet sheet) {
    //Add any before events here
  }

  public void afterResourceSheetProcessed(Sheet sheet) {
    resourceTemplates.put(sheet.getSheetName(), markup.toString());
    resetMarkupBuffer();
  }

  public void buildEnumerationMap() {
    final String ENUMERATION_TAB_NAME = "Lookup Fields and Values";
    final int LOOKUP_NAME_INDEX = 0, STANDARD_NAME_INDEX = 1;

    DataFormatter formatter = new DataFormatter();
    Sheet sheet = getReferenceWorkbook().getSheet(ENUMERATION_TAB_NAME);
    buildWellKnownStandardEnumerationHeaderMap(sheet);

    AtomicReference<StandardEnumeration> standardEnumeration = new AtomicReference<>();

    sheet.rowIterator().forEachRemaining(row -> {
      if (row.getRowNum() > 0) {
        standardEnumeration.set(deserializeStandardEnumerationRow(row));

        if (!enumerations.containsKey(standardEnumeration.get().getLookupField())) {
          enumerations.put(standardEnumeration.get().getLookupField(), new LinkedHashSet<>());
        }
        enumerations.get(standardEnumeration.get().getLookupField()).add(standardEnumeration.get());
      }
    });
    enumerations.forEach((key, items) -> LOG.info("key: " + key + " , items: " + items.toString()));
  }

  public void buildStandardRelationships(Sheet worksheet) {
    int FIRST_ROW_INDEX = 1;
    Row currentRow;
    for(int rowIndex = FIRST_ROW_INDEX; rowIndex < worksheet.getPhysicalNumberOfRows(); rowIndex++) {
      currentRow = worksheet.getRow(rowIndex);
      if (currentRow.getCell(TARGET_RESOURCE, Row.CREATE_NULL_AS_BLANK).getStringCellValue().length() > 0
              && !currentRow.getCell(TARGET_RESOURCE_KEY, Row.CREATE_NULL_AS_BLANK).getStringCellValue().toLowerCase().contains("keynumeric")
              && !currentRow.getCell(SOURCE_RESOURCE_KEY, Row.CREATE_NULL_AS_BLANK).getStringCellValue().toLowerCase().contains("keynumeric")) {
        standardRelationships.add(deserializeStandardRelationshipRow(currentRow));
      }
    }
  }

  public Map<String, Set<StandardEnumeration>> getEnumerations() {
    return enumerations;
  }

  public void resetMarkupBuffer() {
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

  /**
   * Must match what's in the DD spreadsheet EXACTLY
   */
  public static final class WELL_KNOWN_FIELD_HEADERS {
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

  /**
   * Must match what's in the DD spreadsheet EXACTLY
   */
  public static final class WELL_KNOWN_ENUMERATION_HEADERS {
    public static final String
      LOOKUP_FIELD = "LookupField",
      LOOKUP_VALUE = "LookupValue",
      LOOKUP_DISPLAY_NAME = "LookupDisplayName",
      DEFINITION = "Definition",
      LOOKUP_DISPLAY_NAME_SYNONYMS = "LookupDisplayNameSynonyms",
      BEDES = "BEDES",
      REFERENCES = "References",
      ELEMENT_STATUS = "ElementStatus",
      LOOKUP_ID = "LookupID",
      LOOKUP_FIELD_ID = "LookupFieldID",
      SPANISH_LOOKUP_FIELD = "SpanishLookupField",
      SPANISH_LOOKUP_VALUE = "SpanishLookupValue",
      STATUS_CHANGE_DATE = "StatusChangeDate",
      REVISED_DATE = "RevisedDate",
      ADDED_IN_VERSION = "AddedInVersion",
      WIKI_PAGE_TITLE = "Wiki Page Title",
      WIKI_PAGE_URL = "Wiki Page URL";
  }
}
