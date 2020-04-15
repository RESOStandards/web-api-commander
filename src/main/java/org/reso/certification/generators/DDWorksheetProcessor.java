package org.reso.certification.generators;

import org.apache.poi.ss.usermodel.Row;

import java.util.Arrays;
import java.util.List;

public interface DDWorksheetProcessor {
  String getTemplateMarkup();
  void processRow(Row row);
  void addHeader(String content, String timestamp);
  void reset();

  String
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

  Integer
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


  List<String> WELL_KNOWN_HEADER_NAMES = Arrays.asList(
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

}
