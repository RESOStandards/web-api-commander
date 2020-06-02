package org.reso.certification.codegen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.reso.commander.common.DataDictionaryMetadata;

import static org.reso.certification.stepdefs.DataDictionary.REFERENCE_RESOURCE;

public class DataDictionaryController {
  private static final Logger LOG = LogManager.getLogger(DataDictionaryController.class);
  WorksheetProcessor processor = null;
  Workbook workbook = null;

  /**
   * Instantiates a new DataDictionary generator with the given worksheet processor
   * @param processor the worksheet processor to use to generate the data dictionary
   * @throws Exception an exception if the Data Dictionary processor is null
   */
  public DataDictionaryController(WorksheetProcessor processor) throws Exception {
    if (processor == null) throw new Exception("Data Dictionary processor cannot be null!");
    this.processor = processor;
    processor.setReferenceResource(REFERENCE_RESOURCE);
    workbook = processor.getReferenceWorkbook();
    processor.buildLookups();
  }

  /**
   * Generates Data Dictionary references for local workbook instance using the configured WorksheetProcessor
   */
  public void processWorksheets() {
    try {
      Sheet worksheet;
      int sheetIndex, rowIndex;

      //workbook consists of many sheets, process only the ones that have the name of a well-known resource
      for (sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
        worksheet = workbook.getSheetAt(sheetIndex);

        if (DataDictionaryMetadata.v1_7.WELL_KNOWN_RESOURCES.contains(worksheet.getSheetName()) && worksheet.getPhysicalNumberOfRows() > 1) {
          processor.processResourceSheet(worksheet);
          //starts at row 1 to skip header row
          for (rowIndex = 1; rowIndex < worksheet.getPhysicalNumberOfRows(); rowIndex++) {
            processor.processResourceRow(worksheet.getRow(rowIndex));
          }
          processor.finishProcessingResourceSheet(worksheet);
        }
//        else if (DataDictionaryMetadata.v1_7.LOOKUP_FIELDS_AND_VALUES.contentEquals(worksheet.getSheetName())) {
//
//        }
      }
      processor.generateOutput();
    } catch (Exception ex) {
      LOG.error(ex);
    }
  }
}
