package org.reso.certification.codegen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.reso.commander.common.DataDictionaryMetadata;

import static org.reso.certification.codegen.WorksheetProcessor.REFERENCE_WORKSHEET;
import static org.reso.certification.codegen.WorksheetProcessor.buildWellKnownStandardFieldHeaderMap;

public class DataDictionaryCodeGenerator {
  private static final Logger LOG = LogManager.getLogger(DataDictionaryCodeGenerator.class);
  WorksheetProcessor processor = null;
  Workbook workbook = null;

  /**
   * Instantiates a new DataDictionary generator with the given worksheet processor
   * @param processor the worksheet processor to use to generate the data dictionary
   * @throws Exception an exception if the Data Dictionary processor is null
   */
  public DataDictionaryCodeGenerator(WorksheetProcessor processor) throws Exception {
    if (processor == null) throw new Exception("Data Dictionary processor cannot be null!");
    this.processor = processor;
    processor.setReferenceResource(REFERENCE_WORKSHEET);
    workbook = processor.getReferenceWorkbook();
    processor.buildEnumerationMap();
  }

  /**
   * Generates Data Dictionary references for local workbook instance using the configured WorksheetProcessor
   */
  public void processWorksheets() {
    Sheet currentWorksheet, standardResourcesWorksheet;
    int sheetIndex, rowIndex;
    final int ROW_HEADER_INDEX = 0, FIRST_ROW_INDEX = 1;
    final String STANDARD_RELATIONSHIPS_WORKSHEET = "Standard Relationships";

    try {
      standardResourcesWorksheet = workbook.getSheet(STANDARD_RELATIONSHIPS_WORKSHEET);
      assert standardResourcesWorksheet != null;

      processor.buildStandardRelationships(standardResourcesWorksheet);

      //workbook consists of many sheets, process only the ones that have the name of a well-known resource
      for (sheetIndex = ROW_HEADER_INDEX; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
        currentWorksheet = workbook.getSheetAt(sheetIndex);

        if (DataDictionaryMetadata.v1_7.WELL_KNOWN_RESOURCES.contains(currentWorksheet.getSheetName()) && currentWorksheet.getPhysicalNumberOfRows() > 1) {
          processor.beforeResourceSheetProcessed(currentWorksheet);

          processor.wellKnownStandardFieldHeaderMap = buildWellKnownStandardFieldHeaderMap(currentWorksheet);
          processor.processResourceSheet(currentWorksheet);

          //starts at row 1 to skip header row
          for (rowIndex = FIRST_ROW_INDEX; rowIndex < currentWorksheet.getPhysicalNumberOfRows(); rowIndex++) {
            if (currentWorksheet.getRow(rowIndex) != null) {
              processor.processResourceRow(currentWorksheet.getRow(rowIndex));
            }
          }

          processor.afterResourceSheetProcessed(currentWorksheet);
        }
      }
      processor.generateOutput();
    } catch (Exception ex) {
      LOG.info(ex);
    }
  }
}
