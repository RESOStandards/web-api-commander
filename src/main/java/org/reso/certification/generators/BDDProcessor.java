package org.reso.certification.generators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.reso.commander.common.Utils;
import org.reso.models.DataDictionaryRow;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.reso.certification.generators.BDDTemplates.buildHeaderInfo;
import static org.reso.certification.generators.Common.*;
import static org.reso.certification.generators.Common.WELL_KNOWN_DATA_TYPES.*;
import static org.reso.certification.generators.DataDictionaryGenerator.REFERENCE_RESOURCE;
import static org.reso.certification.generators.DataDictionaryGenerator.createFile;
import static org.reso.commander.common.Utils.getTimestamp;

public class BDDProcessor extends WorksheetProcessor {
  private static final Logger LOG = LogManager.getLogger(BDDProcessor.class);

  @Override
  void processSheet(Sheet sheet) {
    markup.append(buildHeaderInfo(sheet.getSheetName(), startTimestamp));
  }

  @Override
  void processNumber() {
    markup.append(BDDTemplates.buildNumberTest(dictionaryRow));
  }

  @Override
  void processStringListSingle() {
    markup.append(BDDTemplates.buildStringListSingleTest(dictionaryRow));
  }

  @Override
  void processString() {
    markup.append(BDDTemplates.buildStringTest(dictionaryRow));
  }

  @Override
  void processBoolean() {
    markup.append(BDDTemplates.buildBooleanTest(dictionaryRow));
  }

  @Override
  void processStringListMulti() {
    markup.append(BDDTemplates.buildStringListMultiTest(dictionaryRow));
  }

  @Override
  void processDate() {
    markup.append(BDDTemplates.buildDateTest(dictionaryRow));
  }

  @Override
  void processTimestamp() {
    markup.append(BDDTemplates.buildTimestampTest(dictionaryRow));
  }

  @Override
  void processCollection() {
    LOG.debug("Collection Type is not supported!");
  }
}
