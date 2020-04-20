package org.reso.certification.generators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class DataDictionaryGenerator {
  private static final Logger LOG = LogManager.getLogger(DataDictionaryGenerator.class);

  //TODO: make this a dynamic property based on DD version
  public static final String REFERENCE_RESOURCE = "RESO.Dictionary.Final.v.1.7.0.20190124T0000.xlsx";

  WorksheetProcessor processor = null;
  Workbook workbook = null;

  public DataDictionaryGenerator(WorksheetProcessor processor) throws Exception {
    if (processor == null) throw new Exception("Data Dictionary processor cannot be null!");
    this.processor = processor;
    processor.setReferenceResource(REFERENCE_RESOURCE);
    workbook = processor.getReferenceWorkbook();
  }

  public void createReference() {
    try {
      Sheet worksheet;
      int sheetIndex, rowIndex;

      //workbook consists of many sheets, process only the ones that have the name of a well-known resource
      for (sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
        worksheet = workbook.getSheetAt(sheetIndex);
        if (DDReferenceMetadata.WellKnownResources.WELL_KNOWN_RESOURCES.contains(worksheet.getSheetName()) && worksheet.getPhysicalNumberOfRows() > 1) {
          processor.processSheet(worksheet);
          //starts at row 1 to skip header row
          for (rowIndex = 1; rowIndex < worksheet.getPhysicalNumberOfRows(); rowIndex++) {
            processor.processRow(worksheet.getRow(rowIndex));
          }
          processor.finishProcessing(worksheet);
        }
      }
      processor.generateOutput();
    } catch (Exception ex) {
      LOG.error(ex);
    }
  }

  private static final class DDReferenceMetadata {
    public static final String version = "1.7.0";

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
          TEAMS = "Teams",
          TEAM_MEMBERS = "TeamMembers",
          CONTACT_LISTING_NOTES = "ContactListingNotes",
          OTHER_PHONE = "OtherPhone",
          PROPERTY_GREEN_VERIFICATION = "PropertyGreenVerification",
          PROPERTY_POWER_PRODUCTION = "PropertyPowerProduction",
          PROPERTY_ROOMS = "PropertyRooms",
          PROPERTY_UNIT_TYPES = "PropertyUnitTypes",
          SOCIAL_MEDIA = "SocialMedia";

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
          TEAMS,
          TEAM_MEMBERS,
          CONTACT_LISTING_NOTES,
          OTHER_PHONE,
          PROPERTY_GREEN_VERIFICATION,
          PROPERTY_POWER_PRODUCTION,
          PROPERTY_ROOMS,
          PROPERTY_UNIT_TYPES,
          SOCIAL_MEDIA
      ));
    }
  }

  public static void createFile(String directoryName, String fileName, String content) {
    if (directoryName == null || fileName == null) return;
    String outputPath = System.getProperty("user.dir") + File.separator + directoryName;
    File baseDirectory = new File(outputPath);
    FileWriter writer = null;
    try {
      if (!baseDirectory.exists()) {
        if (!baseDirectory.mkdirs()) throw new Exception("ERROR: could not create directory: " + baseDirectory);
      }
      writer = new FileWriter(new File(outputPath + File.separator + fileName));
      writer.write(new String(content.getBytes(StandardCharsets.UTF_8)));
      writer.flush();
    } catch (Exception ex) {
      LOG.error("Filename: " + fileName + ". Could not create file: " + ex);
    }
  }
}
