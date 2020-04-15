package org.reso.commander.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
  /**
   * Gets a formatted date string for the given date.
   *
   * @param date the date to format
   * @return date string in yyyyMMddHHMMssS format
   */
  public static String getTimestamp(Date date) {
    DateFormat df = new SimpleDateFormat("yyyyMMddHHMMssS");
    return df.format(date);
  }
}
