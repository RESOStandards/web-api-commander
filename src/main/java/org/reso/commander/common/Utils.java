package org.reso.commander.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {
  private static final Logger LOG = LogManager.getLogger(Utils.class);

  /**
   * Gets a formatted date string for the given date.
   *
   * @param date the date to format
   * @return date string in yyyyMMddHHMMssS format
   */
  public static String getTimestamp(Date date) {
    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssS");
    return df.format(date);
  }

  /**
   * Gets the current timestamp
   *
   * @return the current timestamp returned as a string
   */
  public static String getTimestamp() {
    return getTimestamp(new Date());
  }

  /**
   * Creates a file in the given directory with the given content
   *
   * @param directoryName the directory name to create the file in
   * @param fileName      the name of the file to create
   * @param content       the content to write to the file
   */
  public static void createFile(String directoryName, String fileName, String content) {
    if (directoryName == null || fileName == null) return;
    String outputPath = directoryName.contains(System.getProperty("user.dir")) ? directoryName
        : System.getProperty("user.dir") + File.separator + directoryName;
    File baseDirectory = new File(outputPath);
    FileWriter writer;
    File outputFile;

    try {
      if (!baseDirectory.exists()) {
        if (!baseDirectory.mkdirs()) throw new Exception("ERROR: could not create directory: " + baseDirectory);
      }
      outputFile = new File(outputPath + File.separator + fileName);
      writer = new FileWriter(outputFile);
      writer.write(new String(content.getBytes(StandardCharsets.UTF_8)));
      writer.flush();
    } catch (Exception ex) {
      LOG.error("Filename: " + fileName + ". Could not create file: " + ex);
    }
  }

  public static String pluralize(int lengthAttribute) {
    return lengthAttribute != 1 ? "s" : "";
  }

  final static int DEFAULT_COLUMN_WIDTH = 105;
  final static String DEFAULT_SPACE_REPLACEMENT = "\n";

  public static String wrapColumns(String content, String spaceReplacement) {
    return wrapColumns(content, DEFAULT_COLUMN_WIDTH, spaceReplacement);
  }

  public static String wrapColumns(String content) {
    return wrapColumns(content, DEFAULT_COLUMN_WIDTH, DEFAULT_SPACE_REPLACEMENT);
  }

  public static String wrapColumns(String content, int columnWidth) {
    return wrapColumns(content, columnWidth, DEFAULT_SPACE_REPLACEMENT);
  }

  public static String wrapColumns(String content, int columnWidth, String spaceReplacement) {
    return content.replaceAll(String.format("(.{1,%d})( +|$\\n?)|(.{1,%d})\n", columnWidth, columnWidth), spaceReplacement + "$1");
  }

  public static String getIsoTimestamp() {
    return OffsetDateTime.now().format(DateTimeFormatter.ISO_INSTANT);
  }

  /**
   * Gets the difference of two generic sets.
   * @param a the minuend set
   * @param b the subtrahend set
   * @param <T> the type of set
   * @return Set of type T that contains A \ B
   */
  public static <T> Set<T> getDifference(Set<T> a, Set<T> b) {
    return a.parallelStream()
        .filter(item -> !b.contains(item))
        .filter(Objects::nonNull)
        .collect(Collectors.toSet());
  }
}
