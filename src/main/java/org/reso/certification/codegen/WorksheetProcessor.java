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

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.reso.certification.codegen.WorksheetProcessor.WELL_KNOWN_DATA_TYPES.*;
import static org.reso.commander.common.DataDictionaryMetadata.v1_7.LOOKUPS;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;

public abstract class WorksheetProcessor {
  private static final Logger LOG = LogManager.getLogger(WorksheetProcessor.class);

  //TODO: make this a dynamic property based on DD version
  public static final String REFERENCE_WORKSHEET = "RESODataDictionary-1.7.xlsx";

  static final Map<String, StringBuffer> resourceTemplates = new LinkedHashMap<>();

  static final Map<String, Set<ReferenceStandardLookup>> standardEnumerationsMap = new LinkedHashMap<>();
  static final Map<String, Map<String, ReferenceStandardField>> standardFieldsMap = new LinkedHashMap<>(new LinkedHashMap<>());

  String dataDictionarySpecification = null;
  String startTimestamp;
  Map<String, Integer> wellKnownStandardFieldHeaderMap = new LinkedHashMap<>();
  Map<String, Integer> wellKnownStandardEnumerationHeaderMap = new LinkedHashMap<>();

  public WorksheetProcessor() {
    LOG.info("Using Data Dictionary Reference sheet: " + REFERENCE_WORKSHEET);
    startTimestamp = Utils.getTimestamp();
  }

  public static Map<String, Integer> buildWellKnownStandardFieldHeaderMap(Sheet sheet) {
    Map<String, Integer> headerMap = new LinkedHashMap<>();
    sheet.getRow(0).cellIterator().forEachRemaining(cell ->
        headerMap.put(cell.getStringCellValue(), cell.getColumnIndex()));
    return headerMap;
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

  abstract void processNumber(ReferenceStandardField field);

  abstract void processStringListSingle(ReferenceStandardField field);

  abstract void processString(ReferenceStandardField field);

  abstract void processBoolean(ReferenceStandardField field);

  abstract void processStringListMulti(ReferenceStandardField field);

  abstract void processDate(ReferenceStandardField field);

  abstract void processTimestamp(ReferenceStandardField field);

  abstract void processExpansion(ReferenceStandardField field);

  abstract void generateOutput();

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

  public ReferenceStandardField deserializeStandardFieldRow(Row row) {
    return new ReferenceStandardField.Builder()
        .setResourceName(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.RESOURCE_NAME), row))
        .setStandardName(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.STANDARD_NAME), row))
        .setDisplayName(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.DISPLAY_NAME), row))
        .setDefinition(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.DEFINITION), row))
        .setGroups(getArrayValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.GROUPS), row))
        .setSimpleDataType(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.SIMPLE_DATA_TYPE), row))
        .setSourceResource(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.SOURCE_RESOURCE), row))
        .setSuggestedMaxLength(getIntegerValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.SUGGESTED_MAX_LENGTH), row))
        .setSuggestedMaxPrecision(getIntegerValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.SUGGESTED_MAX_PRECISION), row))
        .setSynonyms(getArrayValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.SYNONYMS), row))
        .setElementStatus(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.ELEMENT_STATUS), row))
        .setBedes(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.BEDES), row))
        .setRecordId(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.RECORD_ID), row))
        .setLookupStatus(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.LOOKUP_STATUS), row))
        .setLookupName(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.LOOKUP_NAME), row))
        .setRepeatingElement(getBooleanValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.REPEATING_ELEMENT), row))
        .setPropertyTypes(getArrayValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.PROPERTY_TYPES), row))
        .setPayloads(getArrayValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.PAYLOADS), row))
        .setSpanishDisplayName(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.SPANISH_DISPLAY_NAME), row))
        .setFrenchCanadianDisplayName(getStringValue(getWellKnownStandardFieldIndex(WELL_KNOWN_FIELD_HEADERS.FRENCH_CANADIAN_DISPLAY_NAME), row))
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
        .setLookupName(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.LOOKUP_NAME), row))
        .setLookupValue(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.STANDARD_LOOKUP_VALUE), row))
        .setLegacyODataValue(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.LEGACY_ODATA_VALUE), row))
        .setDefinition(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.DEFINITION), row))
        .setLookupDisplayNameSynonyms(getArrayValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.SYNONYMS), row))
        .setBedes(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.BEDES), row))
        .setReferences(getArrayValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.REFERENCES), row))
        .setElementStatus(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.ELEMENT_STATUS), row))
        .setLookupId(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.LOOKUP_ID), row))
        .setLookupNameId(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.LOOKUP_NAME_ID), row))
        .setSpanishLookupValue(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.SPANISH_LOOKUP_VALUE), row))
        .setFrenchCanadianLookupValue(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.FRENCH_CANADIAN_LOOKUP_VALUE), row))
        .setStatusChangeDate(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.STATUS_CHANGE_DATE), row))
        .setRevisedDate(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.REVISED_DATE), row))
        .setAddedInVersion(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.ADDED_IN_VERSION), row))
        .setWikiPageTitle(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.WIKI_PAGE_TITLE), row))
        .setWikiPageUrl(getStringValue(getWellKnownStandardEnumerationIndex(WELL_KNOWN_ENUMERATION_HEADERS.WIKI_PAGE_URL), row))
        .build();

  }

  public void processResourceRow(Row row) {

    ReferenceStandardField referenceStandardField = deserializeStandardFieldRow(row);

    //add empty top-level resource name map
    standardFieldsMap.putIfAbsent(referenceStandardField.getResourceName(), new LinkedHashMap<>());

    //add a resource standard field
    standardFieldsMap.get(referenceStandardField.getResourceName()).put(referenceStandardField.getStandardName(), referenceStandardField);

    resourceTemplates.putIfAbsent(referenceStandardField.getResourceName(), new StringBuffer());

    //handle expanded fields
    if (referenceStandardField.isExpansion()) {
      processExpansion(referenceStandardField);
    } else {
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
        default:
          if (referenceStandardField.getSimpleDataType() != null)
            LOG.debug("Data type: " + referenceStandardField.getSimpleDataType() + " is not supported!");
      }
    }
  }

  String getDirectoryName() {
    return startTimestamp + "-" + getDataDictionarySpecification()
        .toLowerCase().substring(0, getDataDictionarySpecification().lastIndexOf("."));
  }

  public String getDataDictionarySpecification() {
    return dataDictionarySpecification;
  }

  public void setDataDictionarySpecification(String dataDictionarySpecification) {
    this.dataDictionarySpecification = dataDictionarySpecification;
  }

  public Workbook getReferenceWorkbook() {
    try {
      return new XSSFWorkbook(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(getDataDictionarySpecification())));
    } catch (Exception ex) {
      LOG.error(getDefaultErrorMessage(ex));
    }
    return null;
  }

  public void buildEnumerationMap() {
    Sheet sheet = getReferenceWorkbook().getSheet(LOOKUPS);
    buildWellKnownStandardEnumerationHeaderMap(sheet);

    AtomicReference<ReferenceStandardLookup> standardEnumeration = new AtomicReference<>();

    sheet.rowIterator().forEachRemaining(row -> {
      if (row.getRowNum() > 0) {
        standardEnumeration.set(deserializeStandardEnumerationRow(row));

        if (!standardEnumerationsMap.containsKey(standardEnumeration.get().getLookupName())) {
          standardEnumerationsMap.put(standardEnumeration.get().getLookupName(), new LinkedHashSet<>());
        }
        standardEnumerationsMap.get(standardEnumeration.get().getLookupName()).add(standardEnumeration.get());
      }
    });
  }

  public Map<String, Set<ReferenceStandardLookup>> getEnumerations() {
    return standardEnumerationsMap;
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
        COLLECTION = "Collection",
        RESOURCE = "Resource";
  }

  /**
   * Must match what's in the DD spreadsheet EXACTLY
   */
  public static final class WELL_KNOWN_FIELD_HEADERS {
    public static final String
        RESOURCE_NAME = "ResourceName",
        STANDARD_NAME = "StandardName",
        DISPLAY_NAME = "DisplayName",
        DEFINITION = "Definition",
        GROUPS = "Groups",
        SIMPLE_DATA_TYPE = "SimpleDataType",
        SOURCE_RESOURCE = "SourceResource",
        SUGGESTED_MAX_LENGTH = "SugMaxLength",
        SUGGESTED_MAX_PRECISION = "SugMaxPrecision",
        SYNONYMS = "Synonyms",
        FRENCH_CANADIAN_DISPLAY_NAME = "FrenchCanadianDisplayName",
        SPANISH_DISPLAY_NAME = "SpanishDisplayName",
        ELEMENT_STATUS = "ElementStatus",
        RECORD_ID = "RecordId",
        LOOKUP_STATUS = "LookupStatus",
        LOOKUP_NAME = "LookupName",
        REPEATING_ELEMENT = "RepeatingElement",
        PROPERTY_TYPES = "PropertyTypes",
        PAYLOADS = "Payloads",
        STATUS_CHANGE_DATE = "StatusChangeDate",
        REVISED_DATE = "RevisedDate",
        ADDED_IN_VERSION = "AddedInVersion",
        WIKI_PAGE_TITLE = "WikiPageTitle",
        WIKI_PAGE_URL = "WikiPageUrl",
        WIKI_PAGE_ID = "WikiPageId",
        BEDES = "BEDES";
  }

  /**
   * Must match what's in the DD spreadsheet EXACTLY
   */
  public static final class WELL_KNOWN_ENUMERATION_HEADERS {
    public static final String
        LOOKUP_NAME = "LookupName",
        STANDARD_LOOKUP_VALUE = "StandardLookupValue",
        LEGACY_ODATA_VALUE = "LegacyODataValue",
        DEFINITION = "Definition",
        REFERENCES = "References",
        SYNONYMS = "Synonyms",
        FRENCH_CANADIAN_LOOKUP_VALUE = "FrenchCanadianLookupValue",
        SPANISH_LOOKUP_VALUE = "SpanishLookupValue",
        ELEMENT_STATUS = "ElementStatus",
        LOOKUP_NAME_ID = "LookupNameId",
        LOOKUP_ID = "LookupID",
        STATUS_CHANGE_DATE = "StatusChangeDate",
        REVISED_DATE = "RevisedDate",
        ADDED_IN_VERSION = "AddedInVersion",
        WIKI_PAGE_TITLE = "Wiki Page Title",
        WIKI_PAGE_URL = "Wiki Page URL",
        BEDES = "BEDES";
  }
}
