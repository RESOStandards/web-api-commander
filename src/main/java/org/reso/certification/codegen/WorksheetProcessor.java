package org.reso.certification.codegen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.reso.commander.common.Utils;
import org.reso.models.ReferenceStandardField;
import org.reso.models.ReferenceStandardLookup;
import org.reso.models.ReferenceStandardRelationship;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.reso.certification.codegen.WorksheetProcessor.WELL_KNOWN_DATA_TYPES.*;
import static org.reso.certification.codegen.WorksheetProcessor.WELL_KNOWN_FIELD_HEADERS.COLLECTION;
import static org.reso.certification.codegen.WorksheetProcessor.WELL_KNOWN_FIELD_HEADERS.STANDARD_NAME;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;

public abstract class WorksheetProcessor {
  //TODO: make this a dynamic property based on DD version
  public static final String REFERENCE_WORKSHEET = "RESODataDictionary-1.7.xlsx";

  static final Map<String, String> resourceTemplates = new LinkedHashMap<>();
  static final Map<String, List<ReferenceStandardLookup>> standardEnumerationsMap = new LinkedHashMap<>();
  static final Map<String, Map<String, ReferenceStandardField>> standardFieldsMap = new LinkedHashMap<>(new LinkedHashMap<>());
  private static final Logger LOG = LogManager.getLogger(WorksheetProcessor.class);
  String referenceDocument = null;
  StringBuffer markup;
  Sheet sheet;
  String startTimestamp;

  Map<String, Integer> wellKnownStandardFieldHeaderMap = new LinkedHashMap<>();
  Map<String, Integer> wellKnownStandardEnumerationHeaderMap = new LinkedHashMap<>();

  private final List<ReferenceStandardRelationship> referenceStandardRelationships = new ArrayList<>();

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

  public static Map<String, Integer> buildWellKnownStandardFieldHeaderMap(Sheet sheet) {
    Map<String, Integer> headerMap = new LinkedHashMap<>();
    sheet.getRow(0).cellIterator().forEachRemaining(cell ->
            headerMap.put(cell.getStringCellValue(), cell.getColumnIndex()));
    return headerMap;
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

  public List<ReferenceStandardRelationship> getStandardRelationships() {
    return this.referenceStandardRelationships;
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

  public ReferenceStandardRelationship deserializeStandardRelationshipRow(Row row) {
    ReferenceStandardRelationship referenceStandardRelationship = null;

    try {
      referenceStandardRelationship = ReferenceStandardRelationship.Builder.build(
          row.getCell(TARGET_RESOURCE).getStringCellValue(),
          row.getCell(TARGET_RESOURCE_KEY, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(),
          row.getCell(TARGET_STANDARD_NAME).getStringCellValue(),
          ReferenceStandardRelationship.Cardinality.stream().filter(cardinality ->
              cardinality.getRelationshipType().contentEquals(
                  row.getCell(CARDINALITY).getStringCellValue())).findFirst().orElseThrow(Exception::new),
          row.getCell(SOURCE_RESOURCE).getStringCellValue(),
          row.getCell(SOURCE_RESOURCE_KEY).getStringCellValue()
      );
    } catch (Exception ex) {
      LOG.error(ex);
    }
    return referenceStandardRelationship;
  }

  public ReferenceStandardField deserializeStandardFieldRow(Row row) {
    return new ReferenceStandardField.Builder()
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

  public ReferenceStandardLookup deserializeStandardEnumerationRow(Row row) {
    return new ReferenceStandardLookup.Builder()
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

  void processResourceSheet(Sheet sheet) {
    this.sheet = sheet;
  }

  abstract void processNumber(ReferenceStandardField field);

  abstract void processStringListSingle(ReferenceStandardField field);

  abstract void processString(ReferenceStandardField field);

  abstract void processBoolean(ReferenceStandardField field);

  abstract void processStringListMulti(ReferenceStandardField field);

  abstract void processDate(ReferenceStandardField field);

  abstract void processTimestamp(ReferenceStandardField field);

  abstract void processCollection(ReferenceStandardField field);

  abstract void generateOutput();

  public void processResourceRow(Row row) {
    assertTrue(getDefaultErrorMessage("sheet name was null but was expected to contain a resource name!"),
        sheet != null && sheet.getSheetName() != null);

    //if there's no field in the standard name column, don't process the row
    if (row.getCell(getWellKnownStandardFieldIndex(STANDARD_NAME)) == null) return;

    ReferenceStandardField referenceStandardField = deserializeStandardFieldRow(row);
    referenceStandardField.setParentResourceName(sheet.getSheetName());

    //add empty top-level resource name map
    standardFieldsMap.putIfAbsent(sheet.getSheetName(), new LinkedHashMap<>());

    //add a resource, standard field
    standardFieldsMap.get(sheet.getSheetName()).put(referenceStandardField.getStandardName(), referenceStandardField);

    //now that row has been processed, extract field type and assemble the template
    switch (referenceStandardField.getSimpleDataType()) {
      case NUMBER:
        processNumber(referenceStandardField);
        break;
      case STRING_LIST_SINGLE:
        processStringListSingle(referenceStandardField);
        break;
      case STRING:
        processString(referenceStandardField);
        break;
      case BOOLEAN:
        processBoolean(referenceStandardField);
        break;
      case STRING_LIST_MULTI:
        processStringListMulti(referenceStandardField);
        break;
      case DATE:
        processDate(referenceStandardField);
        break;
      case TIMESTAMP:
        processTimestamp(referenceStandardField);
        break;
      case COLLECTION:
        processCollection(referenceStandardField);
        break;
      default:
        if (referenceStandardField.getSimpleDataType() != null)
          LOG.debug("Data type: " + referenceStandardField.getSimpleDataType() + " is not supported!");
    }
  }

  String getDirectoryName() {
    return startTimestamp + "-" + REFERENCE_WORKSHEET.toLowerCase().substring(0, REFERENCE_WORKSHEET.lastIndexOf("."));
  }

  public String getReferenceResource() {
    return referenceDocument;
  }

  public void setReferenceResource(String referenceResource) {
    this.referenceDocument = referenceResource;
  }

  public Workbook getReferenceWorkbook() {
    try {
      return new XSSFWorkbook(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(getReferenceResource())));
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

    Sheet sheet = getReferenceWorkbook().getSheet(ENUMERATION_TAB_NAME);
    buildWellKnownStandardEnumerationHeaderMap(sheet);

    AtomicReference<ReferenceStandardLookup> standardEnumeration = new AtomicReference<>();

    sheet.rowIterator().forEachRemaining(row -> {
      if (row.getRowNum() > 0) {
        standardEnumeration.set(deserializeStandardEnumerationRow(row));

        if (!standardEnumerationsMap.containsKey(standardEnumeration.get().getLookupField())) {
          standardEnumerationsMap.put(standardEnumeration.get().getLookupField(), new ArrayList<>());
        }
        standardEnumerationsMap.get(standardEnumeration.get().getLookupField()).add(standardEnumeration.get());
      }
    });
  }

  public void buildStandardRelationships(Sheet worksheet) {
    int FIRST_ROW_INDEX = 1;
    Row currentRow;
    for(int rowIndex = FIRST_ROW_INDEX; rowIndex < worksheet.getPhysicalNumberOfRows(); rowIndex++) {
      currentRow = worksheet.getRow(rowIndex);
      if (currentRow.getCell(TARGET_RESOURCE, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().length() > 0
              && !currentRow.getCell(TARGET_RESOURCE_KEY, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().toLowerCase().contains("keynumeric")
              && !currentRow.getCell(SOURCE_RESOURCE_KEY, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().toLowerCase().contains("keynumeric")) {
        referenceStandardRelationships.add(deserializeStandardRelationshipRow(currentRow));
      }
    }
  }

  public Map<String, List<ReferenceStandardLookup>> getEnumerations() {
    return standardEnumerationsMap;
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
