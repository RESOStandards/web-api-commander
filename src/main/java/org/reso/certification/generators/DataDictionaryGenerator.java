package org.reso.certification.generators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.reso.commander.common.Utils.getTimestamp;

public class DataDictionaryGenerator {
  private static final Logger LOG = LogManager.getLogger(DataDictionaryGenerator.class);
  public static final String REFERENCE_RESOURCE = "RESO.Dictionary.Final.v.1.7.0.20190124T0000.xlsx";

  static Map<String, String> resourceTemplates = new LinkedHashMap<>();

  String dataDictionaryReference = null;
  DDWorksheetProcessor processor = null;

  public DataDictionaryGenerator(DDWorksheetProcessor processor) throws Exception {
    if (processor == null) throw new Exception("Data Dictionary processor cannot be null!");
    this.processor = processor;
    dataDictionaryReference = REFERENCE_RESOURCE;
  }

  public void readDictionaryReference() {
    try {
      Workbook workbook = new XSSFWorkbook(
          OPCPackage.open(Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
              .getResourceAsStream(dataDictionaryReference))));

      Sheet worksheet;
      int sheetIndex, rowIndex;
      String dateString = getTimestamp(new Date()), sourceFilename = REFERENCE_RESOURCE;
      String parentDirectoryName = dateString + "-" + REFERENCE_RESOURCE.toLowerCase().substring(0, REFERENCE_RESOURCE.lastIndexOf("."));

      //workbook consists of many sheets, process only the ones that have the name of a well-known resource
      for (sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
        worksheet = workbook.getSheetAt(sheetIndex);
        if (DDReferenceMetadata.WellKnownResources.WELL_KNOWN_RESOURCES.contains(worksheet.getSheetName()) && worksheet.getPhysicalNumberOfRows() > 1) {
          processor.addHeader(worksheet.getSheetName(), dateString);
          //starts at row 1 to skip header row
          for (rowIndex = 1; rowIndex < worksheet.getPhysicalNumberOfRows(); rowIndex++) {
            processor.processRow(worksheet.getRow(rowIndex));
          }
          resourceTemplates.put(worksheet.getSheetName(), processor.getTemplateMarkup());
        }
        processor.reset();
      }

      LOG.info("Generating BDD .feature files for the following resources: " + resourceTemplates.keySet().toString());
      resourceTemplates.forEach((resourceName, content) -> {
        //put in local directory rather than relative to where the input file is
        createFile( parentDirectoryName, resourceName.toLowerCase() + ".feature", content);
      });
    } catch (IOException | NullPointerException | InvalidFormatException e) {
      e.printStackTrace();
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
      writer.write(content);
      writer.flush();
    } catch (Exception ex) {
      LOG.error("Filename: " + fileName + ". Could not create file: " + ex);
    }
  }
}
