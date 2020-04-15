package org.reso.certification.generators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.*;

public class DataDictionaryGenerator {
  private static final Logger LOG = LogManager.getLogger(DataDictionaryGenerator.class);

  static Map<String, String> resourceTemplates = new LinkedHashMap<>();

  String dataDictionaryReference = null;
  DDWorksheetProcessor processor = null;

  public DataDictionaryGenerator(DDWorksheetProcessor processor) throws Exception {
    if (processor == null) throw new Exception("Data Dictionary processor cannot be null!");
    this.processor = processor;
    dataDictionaryReference = DDReferenceMetadata.v1_7_0;
  }

  public void readDictionaryReference() {
    try {
      Workbook workbook = new XSSFWorkbook(
          OPCPackage.open(Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
              .getResourceAsStream(dataDictionaryReference))));

      Sheet worksheet;
      Set<String> headerNames = new LinkedHashSet<>();
      int sheetIndex, rowIndex;

      //workbook consists of many sheets, process only the ones that have the name of a well-known resource
      for (sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
        worksheet = workbook.getSheetAt(sheetIndex);
        if (DDReferenceMetadata.WellKnownResources.WELL_KNOWN_RESOURCES.contains(worksheet.getSheetName())
            && worksheet.getPhysicalNumberOfRows() > 1) {

          worksheet.getRow(0).cellIterator().forEachRemaining(cell -> headerNames.add(cell.getStringCellValue()));

          //skip header row
          for (rowIndex = 1; rowIndex < worksheet.getPhysicalNumberOfRows(); rowIndex++) {
            processor.processRow(worksheet.getRow(rowIndex));
          }

          resourceTemplates.put(worksheet.getSheetName(), processor.getTemplateMarkup());
          processor.reset();
        }
      }

      resourceTemplates.forEach((key, value) -> LOG.info("\nFeature: " + key + "\n" + value));
    } catch (IOException | NullPointerException | InvalidFormatException e) {
      e.printStackTrace();
    }
  }

  private static final class DDReferenceMetadata {
    public static final String version = "1.7.0";
    public static final String v1_7_0 = "RESO.Dictionary.Final.v.1.7.0.20190124T0000.xlsx";

    public static final class WellKnownResources {
      public static final String
          PROPERTY = "Property",
          MEMBER = "Member",
          OFFICE = "Office",
          CONTACTS = "Contacts",
          CONTACT_LISTINGS = "ContactListings",
          HISTORY_TRANSACTIONAL = "HistoryTransactional",
          INTERNET_TRACKING = "InternetTracking",
          MEDIA = "Media",
          OPEN_HOUSE = "OpenHouse",
          OUID = "OUID",
          PROSPECTING = "Prospecting",
          QUEUE = "Queue",
          RULES = "Rules",
          SAVED_SEARCH = "SavedSearch",
          SHOWING = "Showing",
          TEAMS = "Teams";

      public static final Set<String> WELL_KNOWN_RESOURCES = new LinkedHashSet<>(Arrays.asList(
          PROPERTY,
          MEMBER,
          OFFICE,
          CONTACTS,
          CONTACT_LISTINGS,
          HISTORY_TRANSACTIONAL,
          INTERNET_TRACKING,
          MEDIA,
          OPEN_HOUSE,
          OUID,
          PROSPECTING,
          QUEUE,
          RULES,
          SAVED_SEARCH,
          SHOWING,
          TEAMS
      ));
    }
  }
}
