package org.reso.certification.codegen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import static org.reso.certification.codegen.WorksheetProcessor.*;

public class DataDictionaryCodeGenerator {
  private static final Logger LOG = LogManager.getLogger(DataDictionaryCodeGenerator.class);
  WorksheetProcessor processor = null;
  Workbook workbook = null;

  private DataDictionaryCodeGenerator() {
    //private constructor, should not instantiate directly
  }

  /**
   * Instantiates a new DataDictionary generator with the given worksheet processor
   * @param processor the worksheet processor to use to generate the data dictionary
   */
  public DataDictionaryCodeGenerator(WorksheetProcessor processor) {
    this.processor = processor;
    processor.setDataDictionarySpecification(WorksheetProcessor.getReferenceWorksheet(processor.getVersion()));
    workbook = processor.getReferenceWorkbook();
    processor.buildEnumerationMap();
  }

  /**
   * Generates Data Dictionary references for local workbook instance using the configured WorksheetProcessor
   */
  public void processWorksheets() {
    final Sheet fieldsWorksheet;
    final int FIRST_ROW_INDEX = 1;
    final String FIELDS_WORKSHEET = "Fields";

    try {
      fieldsWorksheet = workbook.getSheet(FIELDS_WORKSHEET);
      assert fieldsWorksheet != null;

      processor.wellKnownStandardFieldHeaderMap = buildWellKnownStandardFieldHeaderMap(fieldsWorksheet);

      //starts at row 1 to skip header row
      for (int rowIndex = FIRST_ROW_INDEX; rowIndex < fieldsWorksheet.getPhysicalNumberOfRows(); rowIndex++) {
        if (fieldsWorksheet.getRow(rowIndex) != null) {
          processor.processResourceRow(fieldsWorksheet.getRow(rowIndex));
        }
      }

      processor.generateOutput();
    } catch (Exception ex) {
      LOG.error(ex);
    }
  }
}
