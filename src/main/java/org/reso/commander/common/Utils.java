package org.reso.commander.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.core.edm.xml.ClientCsdlAnnotation;
import org.apache.olingo.commons.api.edm.EdmAnnotation;
import org.apache.olingo.commons.core.edm.EdmAnnotationImpl;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Utils {
  private static final Logger LOG = LogManager.getLogger(Utils.class);

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

  /**
   * Gets the current timestamp
   * @return the current timestamp returned as a string
   */
  public static String getTimestamp() {
    return getTimestamp(new Date());
  }

  /**
   * Creates a file in the given directory with the given content
   * @param directoryName the directory name to create the file in
   * @param fileName the name of the file to create
   * @param content the content to write to the file
   */
  public static File createFile(String directoryName, String fileName, String content) {
    if (directoryName == null || fileName == null) return null;
    String outputPath = directoryName.contains(System.getProperty("user.dir")) ? directoryName
        : System.getProperty("user.dir") + File.separator + directoryName;
    File baseDirectory = new File(outputPath);
    FileWriter writer;
    File outputFile = null;

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
    return outputFile;
  }

  /**
   * Creates a file in the given directory with the given content
   * @param content the content to write to the file
   */
  public static File createFile(String outputPath, String content) {
    if (outputPath == null) return null;
    File outputFile = new File(outputPath);
    try {
      FileWriter writer;

      if (!outputFile.exists()) {
        if (!outputFile.mkdirs()) throw new Exception("ERROR: could not create directory: " + outputFile);
      }
      writer = new FileWriter(outputFile);
      writer.write(new String(content.getBytes(StandardCharsets.UTF_8)));
      writer.flush();
    } catch (Exception ex) {
      LOG.error("Filename: " + outputPath + ". Could not create file: " + ex);
    }
    return outputFile;
  }

  /**
   * Removes a directory at the given pathToDirectory.
   *
   * If current user has write access then directory creation will result in True being returned.
   * Otherwise will return false if the directory couldn't be created for some reason.
   * @param pathToDirectory
   * @return
   */
  public static Boolean removeDirectory(String pathToDirectory) {
    if (pathToDirectory == null) return null;
    File outputDirectory = new File(pathToDirectory);

    if (outputDirectory.exists()) {
      if (outputDirectory.canWrite()) {
        if (outputDirectory.listFiles() != null) {
          Arrays.stream(Objects.requireNonNull(outputDirectory.listFiles())).forEach(File::delete);
        }
        return outputDirectory.delete();
      } else {
        LOG.error("Tried deleting directory " + outputDirectory.getPath() + " but didn't have sufficient access.");
        return false;
      }
    }
    return true;
  }

  public static class SneakyAnnotationReader {
    Class<? extends EdmAnnotationImpl> object;
    Field field;
    EdmAnnotationImpl edmAnnotationImpl;
    ClientCsdlAnnotation clientCsdlAnnotation;

    public SneakyAnnotationReader(EdmAnnotation edmAnnotation) {
      try {
        edmAnnotationImpl = ((EdmAnnotationImpl) edmAnnotation);

        // create an object of the class named Class
        object = edmAnnotationImpl.getClass();

        // access the private variable
        field = object.getDeclaredField("annotation");
        // make private field accessible
        field.setAccessible(true);

        clientCsdlAnnotation = (ClientCsdlAnnotation) field.get(edmAnnotationImpl);

      } catch (Exception ex) {
        LOG.error(ex);
        ex.printStackTrace();
      }
    }

    public String getTerm() {
      return clientCsdlAnnotation.getTerm();
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

  public static String getIsoTimestamp(OffsetDateTime fromDate) {
    return OffsetDateTime.from(fromDate.toInstant()).format(DateTimeFormatter.ISO_INSTANT);
  }

}
