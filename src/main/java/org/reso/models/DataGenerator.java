package org.reso.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reso.commander.Commander;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Used to deserialize the Data Dictionary reference sheet into a cache of generators
 */
public class DataGenerator {
  private static final Logger LOG = LogManager.getLogger(DataGenerator.class);
  private final static String DATA_GENERATOR_JSON = "data-generator-dd1.7.json";

  private String description;
  private String version;
  private String generatedOn;
  private List<ResourceInfo> resourceInfo;
  private List<FieldDataGenerator> fields;

  /**
   * Creates a nested map of Data Dictionary reference generators where
   * * the outer map is keyed by resource name
   * * inner map is keyed by standard field name and returns a generator for that field
   *
   * @return nested hashes of standard field generators
   */
  public static DataGenerator deserialize() {
    Map<String, Map<String, DataGenerator>> dataGeneratorResourceFieldMap =
        Collections.synchronizedMap(new LinkedHashMap<>());

    URL resource = Thread.currentThread().getContextClassLoader().getResource(DATA_GENERATOR_JSON);
    assert resource != null : "ERROR: could not load resource " + DATA_GENERATOR_JSON;

    final String generatorJson = Commander.convertInputStreamToString(Commander.deserializeFileFromPath(resource.getPath()));

    //note the open braces before getType()
    Type targetClassType = new TypeToken<DataGenerator>() {}.getType();
    return new Gson().fromJson(generatorJson, targetClassType);
  }

  public String getDescription() {
    return description;
  }

  public String getVersion() {
    return version;
  }

  public String getGeneratedOn() {
    return generatedOn;
  }

  public List<ResourceInfo> getResourceInfo() {
    return resourceInfo;
  }

  public List<FieldDataGenerator> getFields() {
    return fields;
  }

  public static final class FieldDataGenerator {
    private String fieldName;
    private String resourceName;
    private String fakerGeneratorName;
    private List<String> customExamples;

    public FieldDataGenerator(String fieldName, String resourceName, String fakerGeneratorName, List<String> customExamples) {
      this.fieldName = fieldName;
      this.resourceName = resourceName;
      this.fakerGeneratorName = fakerGeneratorName;
      this.customExamples = customExamples;
    }

    public String getFieldName() {
      return fieldName;
    }

    public String getResourceName() {
      return resourceName;
    }

    public void setResourceName(String resourceName) {
      this.resourceName = resourceName;
    }

    public String getFakerGeneratorName() {
      return fakerGeneratorName;
    }

    public List<String> getCustomExamples() {
      return customExamples;
    }

    public boolean hasFakerGenerator() {
      return fakerGeneratorName != null && fakerGeneratorName.length() > 0;
    }

    public boolean hasCustomExamples() {
      return customExamples != null && customExamples.size() > 0;
    }

    @Override
    public String toString() {
      return "FieldDataGenerator{" +
          "fieldName='" + fieldName + '\'' +
          ", resourceName=" + (resourceName == null ? "null" : "'" + resourceName + "'") +
          ", fakerGeneratorName=" + (fakerGeneratorName == null ? "null" : "'" + fakerGeneratorName + "'") +
          ", customExamples=" + customExamples +
          '}';
    }
  }

  public static final class ResourceInfo {
    private String resourceName;
    private Integer recordCount;

    public ResourceInfo(String resourceName,Integer recordCount) {
      this.resourceName = resourceName;
      this.recordCount = recordCount;
    }

    public String getResourceName() {
      return resourceName;
    }

    public Integer getRecordCount() {
      return recordCount;
    }
  }
}
