package org.reso.models;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reso.commander.Commander;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;

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
  public static Map<String, Map<String, DataGenerator>> buildReferenceGeneratorCache() {
    Map<String, Map<String, DataGenerator>> dataGeneratorResourceFieldMap =
        Collections.synchronizedMap(new LinkedHashMap<>());

    URL resource = Thread.currentThread().getContextClassLoader().getResource(DATA_GENERATOR_JSON);
    assert resource != null : "ERROR: could not load resource " + DATA_GENERATOR_JSON;

    final String generatorJson = Commander.convertInputStreamToString(Commander.deserializeFileFromPath(resource.getPath()));

    //note the open braces before getType()
    Type targetClassType = new TypeToken<DataGenerator>() {}.getType();
    DataGenerator dataGenerator = new Gson().fromJson(generatorJson, targetClassType);

    LOG.info("Target Collection deserialized: " + dataGenerator);

    return dataGeneratorResourceFieldMap;
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
  }

  static final class ResourceInfo {
    private String resourceName;
    private Integer recordCount;

    public ResourceInfo(String resourceName,Integer recordCount) {
      this.resourceName = resourceName;
      this.recordCount = recordCount;
    }
  }
}
