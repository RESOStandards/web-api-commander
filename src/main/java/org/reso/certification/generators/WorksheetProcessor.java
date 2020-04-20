package org.reso.certification.generators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.reso.commander.common.Utils;
import org.reso.models.DataDictionaryRow;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.reso.certification.generators.Common.WELL_KNOWN_DATA_TYPES.*;
import static org.reso.certification.generators.Common.WELL_KNOWN_DATA_TYPES.COLLECTION;
import static org.reso.certification.generators.Common.extractDataDictionaryRow;
import static org.reso.certification.generators.DataDictionaryGenerator.REFERENCE_RESOURCE;
import static org.reso.certification.generators.DataDictionaryGenerator.createFile;

public abstract class WorksheetProcessor {
  private static final Logger LOG = LogManager.getLogger(WorksheetProcessor.class);
  StringBuffer markup;
  DataDictionaryRow dictionaryRow;
  String startTimestamp;
  Map<String, String> resourceTemplates = new LinkedHashMap<>();

  public WorksheetProcessor() {
    startTimestamp = Utils.getTimestamp();
    markup = new StringBuffer();
  }

  abstract void processSheet(Sheet sheet);

  abstract void processNumber();
  abstract void processStringListSingle();
  abstract void processString();
  abstract void processBoolean();
  abstract void processStringListMulti();
  abstract void processDate();
  abstract void processTimestamp();
  abstract void processCollection();

  void processRow(Row row) {
    dictionaryRow = extractDataDictionaryRow(row);

    //now that row has been processed, extract field type and assemble the template
    switch (dictionaryRow.getSimpleDataType()) {
      case NUMBER: processNumber();
      case STRING_LIST_SINGLE: processStringListSingle();
      case STRING: processString();
      case BOOLEAN: processBoolean();
      case STRING_LIST_MULTI: processStringListMulti();
      case DATE: processDate();
      case TIMESTAMP: processTimestamp();
      case COLLECTION: processCollection();
      default:
        if (dictionaryRow.getSimpleDataType() != null)
          LOG.error("Data type: " + dictionaryRow.getSimpleDataType() + " is not supported!");
    }
  }

  void finishProcessing(Sheet sheet) {
    resourceTemplates.put(sheet.getSheetName(), markup.toString());
    reset();
  }

  void generateOutput() {
    String parentDirectoryName = startTimestamp + "-" + REFERENCE_RESOURCE.toLowerCase().substring(0, REFERENCE_RESOURCE.lastIndexOf("."));

    LOG.info("Generating BDD .feature files for the following resources: " + resourceTemplates.keySet().toString());
    resourceTemplates.forEach((resourceName, content) -> {
      //put in local directory rather than relative to where the input file is
      createFile( parentDirectoryName, resourceName.toLowerCase() + ".feature", content);
    });
  }

  public void reset() {
    markup = new StringBuffer();
  }
}
