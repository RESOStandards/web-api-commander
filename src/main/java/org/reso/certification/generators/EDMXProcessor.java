package org.reso.certification.generators;

import org.apache.poi.ss.usermodel.Row;

public class EDMXProcessor implements DDWorksheetProcessor {

  @Override
  public String getTemplateMarkup() {
    return null;
  }

  @Override
  public void processRow(Row row) {

  }

  @Override
  public void addHeader(String content, String timestamp) { }

  @Override
  public void reset() {

  }
}
