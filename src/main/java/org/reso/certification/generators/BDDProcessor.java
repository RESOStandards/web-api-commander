package org.reso.certification.generators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.reso.models.DataDictionaryRow;

import static org.reso.certification.generators.BDDTemplates.buildHeaderInfo;
import static org.reso.certification.generators.Common.*;

public class BDDProcessor implements DDWorksheetProcessor {
  private static final Logger LOG = LogManager.getLogger(BDDProcessor.class);

  static StringBuffer markup;
  static DataDictionaryRow.Builder rowBuilder = new DataDictionaryRow.Builder();
  static DataDictionaryRow dictionaryRow;

  public BDDProcessor() {
    markup = new StringBuffer();
  }

  public void reset() { markup = new StringBuffer(); }

  @Override
  public void addHeader(String headerInfo, String timestamp) {
    markup.append(buildHeaderInfo(headerInfo, timestamp));
  }

  @Override
  public void processRow(Row row) {
    dictionaryRow = extractDataDictionaryRow(row);

    //now that row has been processed, extract field type and assemble the template
    switch (dictionaryRow.getSimpleDataType()) {
      case Common.WELL_KNOWN_DATA_TYPES.NUMBER:
        markup.append(BDDTemplates.buildNumberTest(dictionaryRow.getStandardName(), dictionaryRow.getSuggestedMaxLength(), dictionaryRow.getSuggestedMaxPrecision()));
        break;
      case Common.WELL_KNOWN_DATA_TYPES.STRING_LIST_SINGLE:
        markup.append(BDDTemplates.buildStringListSingleTest(dictionaryRow.getStandardName(), dictionaryRow.getSuggestedMaxLength()));
        break;
      case Common.WELL_KNOWN_DATA_TYPES.STRING:
        markup.append(BDDTemplates.buildStringTest(dictionaryRow.getStandardName(), dictionaryRow.getSuggestedMaxLength()));
        break;
      case Common.WELL_KNOWN_DATA_TYPES.BOOLEAN:
        markup.append(BDDTemplates.buildBooleanTest(dictionaryRow.getStandardName()));
        break;
      case Common.WELL_KNOWN_DATA_TYPES.STRING_LIST_MULTI:
        markup.append(BDDTemplates.buildStringListMultiTest(dictionaryRow.getStandardName(), dictionaryRow.getSuggestedMaxLength()));
        break;
      case Common.WELL_KNOWN_DATA_TYPES.DATE:
        markup.append(BDDTemplates.buildDateTest(dictionaryRow.getStandardName(), dictionaryRow.getSuggestedMaxLength()));
        break;
      case Common.WELL_KNOWN_DATA_TYPES.TIMESTAMP:
        markup.append(BDDTemplates.buildTimestampTest(dictionaryRow.getStandardName(), dictionaryRow.getSuggestedMaxLength()));
        break;
      case Common.WELL_KNOWN_DATA_TYPES.COLLECTION:
        LOG.debug("Collection Type is not supported!");
        break;
      default:
        if (dictionaryRow.getSimpleDataType() != null)
          LOG.debug("Data type: " + dictionaryRow.getSimpleDataType() + " is not supported!");
    }
  }

  @Override
  public String getTemplateMarkup() {
    return markup.toString();
  }
}
