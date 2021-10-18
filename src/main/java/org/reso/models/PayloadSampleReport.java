package org.reso.models;

import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.edm.EdmElement;
import org.apache.regexp.RE;
import org.reso.commander.common.Utils;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class PayloadSampleReport implements JsonSerializer<PayloadSampleReport> {
  private static final Logger LOG = LogManager.getLogger(PayloadSampleReport.class);
  private static final String POSTAL_CODE_KEY = "PostalCode";
  private static final AtomicReference<Map<String, List<PayloadSample>>> resourcePayloadSamplesMap = new AtomicReference<>(Collections.synchronizedMap(new LinkedHashMap<>()));
  private static final AtomicReference<Map<String, Map<String, Integer>>> resourceFieldFrequencyMap = new AtomicReference<>(Collections.synchronizedMap(new LinkedHashMap<>(new LinkedHashMap<>())));
  private static final AtomicReference<Map<LookupValue, Integer>> lookupValueFrequencyMap = new AtomicReference<>(Collections.synchronizedMap(new LinkedHashMap<>()));
  private static final AtomicReference<Map<String, Integer>> resourceCounts = new AtomicReference<>(Collections.synchronizedMap(new LinkedHashMap<>()));

  private static Edm metadata;

  public PayloadSampleReport(final Edm metadata, final Map<String, List<PayloadSample>> resourcePayloadSamplesMap,
                             final Map<String, Integer> resourceCounts, final Map<LookupValue, Integer> lookupValueFrequencyMap) {
    PayloadSampleReport.metadata = metadata;
    PayloadSampleReport.resourcePayloadSamplesMap.get().putAll(resourcePayloadSamplesMap);
    PayloadSampleReport.resourceFieldFrequencyMap.get().putAll(createResourceFieldTallies(resourcePayloadSamplesMap));
    PayloadSampleReport.lookupValueFrequencyMap.get().putAll(lookupValueFrequencyMap);
    PayloadSampleReport.resourceCounts.get().putAll(resourceCounts);
  }

  @Override
  public String toString() {
    return String.valueOf(serialize(this, FieldsJson.class, null));
  }

  /**
   * FieldsJson uses a JSON payload with the following structure:
   *
   *    {
   *       "resourceName": "Property",
   *       "fieldName": "AboveGradeFinishedArea",
   *       "availability": 0.1
   *     }
   */
  private static final class FieldsJson implements JsonSerializer<FieldsJson> {
    static final String
        RESOURCE_NAME_KEY = "resourceName",
        FIELD_NAME_KEY = "fieldName",
        FIELDS_KEY = "fields",
        FREQUENCY_KEY = "frequency";

    String resourceName;
    EdmElement edmElement;

    public FieldsJson(String resourceName, EdmElement edmElement) {
      this.resourceName = resourceName;
      this.edmElement = edmElement;
    }

    public String buildReportString(JsonElement dataAvailabilityReport) {
      StringBuilder reportBuilder = new StringBuilder();
      dataAvailabilityReport.getAsJsonObject().get(FIELDS_KEY).getAsJsonArray().forEach(field -> {
        reportBuilder.append("\nResource: ");
        reportBuilder.append(field.getAsJsonObject().get(RESOURCE_NAME_KEY));
        reportBuilder.append("\nField: ");
        reportBuilder.append(field.getAsJsonObject().get(FIELD_NAME_KEY));
        reportBuilder.append("\nFrequency: ");
        reportBuilder.append(field.getAsJsonObject().get(FREQUENCY_KEY));
        reportBuilder.append("\n");
      });
      return reportBuilder.toString();
    }

    @Override
    public JsonElement serialize(FieldsJson src, Type typeOfSrc, JsonSerializationContext context) {
      JsonObject field = new JsonObject();

      int frequency = resourceFieldFrequencyMap.get().get(src.resourceName) != null
          && resourceFieldFrequencyMap.get().get(src.resourceName).get(src.edmElement.getName()) != null
          ? resourceFieldFrequencyMap.get().get(src.resourceName).get(src.edmElement.getName()) : 0;

      field.addProperty(RESOURCE_NAME_KEY, src.resourceName);
      field.addProperty(FIELD_NAME_KEY, src.edmElement.getName());
      field.addProperty(FREQUENCY_KEY, frequency);

      return field;
    }
  }

  /**
   *   resourceName: "Property",
   *   fieldName: "StateOrProvince",
   *   lookupName: "StateOrProvince",
   *   lookupValue: "CA",
   *   availability: 0.03
   */
  private static final class LookupValuesJson implements JsonSerializer<LookupValuesJson> {
    final String resourceName, fieldName, lookupValue;
    final Integer frequency;

    static final String
        RESOURCE_NAME_KEY = "resourceName",
        FIELD_NAME_KEY = "fieldName",
        LOOKUP_VALUE_KEY = "lookupValue",
        FREQUENCY_KEY = "frequency";

    public LookupValuesJson(String resourceName, String fieldName, String lookupValue, Integer frequency) {
      this.resourceName = resourceName;
      this.fieldName = fieldName;
      this.lookupValue = lookupValue;
      this.frequency = frequency;
    }

    /**
     * Gson invokes this call-back method during serialization when it encounters a field of the
     * specified type.
     *
     * <p>In the implementation of this call-back method, you should consider invoking
     * {@link JsonSerializationContext#serialize(Object, Type)} method to create JsonElements for any
     * non-trivial field of the {@code src} object. However, you should never invoke it on the
     * {@code src} object itself since that will cause an infinite loop (Gson will call your
     * call-back method again).</p>
     *
     * @param src       the object that needs to be converted to Json.
     * @param typeOfSrc the actual type (fully genericized version) of the source object.
     * @param context   the context of the request
     * @return a JsonElement corresponding to the specified object.
     */
    @Override
    public JsonElement serialize(LookupValuesJson src, Type typeOfSrc, JsonSerializationContext context) {
      JsonObject obj = new JsonObject();

      obj.addProperty(RESOURCE_NAME_KEY, resourceName);
      obj.addProperty(FIELD_NAME_KEY, fieldName);
      obj.addProperty(LOOKUP_VALUE_KEY, lookupValue);
      obj.addProperty(FREQUENCY_KEY, frequency);

      return obj;
    }
  }

  private static Map<String, Map<String, Integer>> createResourceFieldTallies(Map<String, List<PayloadSample>> resourcePayloadSamplesMap) {
    AtomicReference<Map<String, Map<String, Integer>>> resourceTallies = new AtomicReference<>(new LinkedHashMap<>());
    AtomicInteger numSamples = new AtomicInteger(0);
    resourcePayloadSamplesMap.keySet().forEach(resourceName -> {
      LOG.info("Processing resource: " + resourceName);
      numSamples.set(resourcePayloadSamplesMap.get(resourceName) != null
          ? resourcePayloadSamplesMap.get(resourceName).stream().reduce(0, (a, f) -> a + f.getSamples().size(), Integer::sum) : 0);
      LOG.info("Sample size: " + numSamples.get());

      //for each resource, go through the keys and tally the data presence counts for each field
      //as well as the number of samples in each case
      resourceTallies.get().putIfAbsent(resourceName, new LinkedHashMap<>());
      if (numSamples.get() > 0) {
        resourcePayloadSamplesMap.get(resourceName)
            .forEach(payloadSample -> payloadSample.getSamples()
                .forEach(sample -> sample
                    .forEach((fieldName, encodedValue) -> {
          if (encodedValue != null) {
            resourceTallies.get().get(resourceName).putIfAbsent(fieldName, 0);
            resourceTallies.get().get(resourceName).put(fieldName, resourceTallies.get().get(resourceName).get(fieldName) + 1);
          }
        })));
      }
    });
    return resourceTallies.get();
  }

  @Override
  public JsonElement serialize(PayloadSampleReport src, Type typeOfSrc, JsonSerializationContext context) {
    final String
        DESCRIPTION_KEY = "description", DESCRIPTION = "RESO Data Availability Report",
        VERSION_KEY = "version", VERSION = "1.7",
        GENERATED_ON_KEY = "generatedOn",
        RESOURCE_INFO_KEY = "resources",
        FIELDS_KEY = "fields",
        LOOKUPS_KEY = "lookups",
        LOOKUP_VALUES_KEY = "lookupValues";

    //serialize fields
    JsonArray fields = new JsonArray();
    metadata.getSchemas().forEach(edmSchema -> {
      //serialize entities (resources) and members (fields)
      edmSchema.getEntityTypes().forEach(edmEntityType -> edmEntityType.getPropertyNames().forEach(propertyName -> {
        FieldsJson fieldJson = new FieldsJson(edmEntityType.getName(), edmEntityType.getProperty(propertyName));
        fields.add(fieldJson.serialize(fieldJson, FieldsJson.class, null));
      }));
    });

    //serialize lookups
    JsonArray lookups = new JsonArray();
    final Map<String, Map<String, Integer>> resourceFieldLookupTotalsMap = new LinkedHashMap<>();
    lookupValueFrequencyMap.get().forEach((lookupValue, frequency) -> {
      resourceFieldLookupTotalsMap.putIfAbsent(lookupValue.getResourceName(), new LinkedHashMap<>());
      resourceFieldLookupTotalsMap.get(lookupValue.getResourceName()).putIfAbsent(lookupValue.getFieldName(), 0);
      resourceFieldLookupTotalsMap.get(lookupValue.getResourceName()).put(lookupValue.getFieldName(),
          resourceFieldLookupTotalsMap.get(lookupValue.getResourceName()).get(lookupValue.getFieldName()) + frequency);
    });

    resourceFieldLookupTotalsMap.forEach((resourceName, fieldLookupTotalsMap) -> {
      fieldLookupTotalsMap.forEach((fieldName, numLookupsTotal) -> {
        LookupsJson lookupsJson = new LookupsJson(resourceName, fieldName, numLookupsTotal);
        lookups.add(lookupsJson.serialize(lookupsJson, LookupsJson.class, null));
      });
    });

    //serialize lookup values
    JsonArray lookupValues = new JsonArray();
    lookupValueFrequencyMap.get().forEach((lookupValue, frequency) -> {
     LookupValuesJson lookupValuesJson = new LookupValuesJson(lookupValue.getResourceName(), lookupValue.getFieldName(), lookupValue.getLookupValue(), frequency);
     lookupValues.add(lookupValuesJson.serialize(lookupValuesJson, LookupValuesJson.class, null));
    });

    JsonObject availabilityReport = new JsonObject();
    availabilityReport.addProperty(DESCRIPTION_KEY, DESCRIPTION);
    availabilityReport.addProperty(VERSION_KEY, VERSION);
    availabilityReport.addProperty(GENERATED_ON_KEY, Utils.getIsoTimestamp());

    final JsonArray resourceTotalsByResource = new JsonArray();
    resourcePayloadSamplesMap.get().keySet().forEach(resourceName -> {
      Set<String> postalCodes = new LinkedHashSet<>();
      ResourcesJson resourcesJson = new ResourcesJson(resourceName);

      int resourceRecordCount = 0;
      if (resourceCounts.get().get(resourceName) != null) {
        resourceRecordCount = resourceCounts.get().get(resourceName);
      }
      resourcesJson.numRecordsTotal.set(resourceRecordCount);

      PayloadSample zerothSample = resourcePayloadSamplesMap.get().get(resourceName) != null
          && resourcePayloadSamplesMap.get().get(resourceName).size() > 0
          ? resourcePayloadSamplesMap.get().get(resourceName).get(0) : null;

      if (zerothSample != null) {
        resourcesJson.keyFields.set(zerothSample.keyFields);
        resourcesJson.dateField.set(zerothSample.dateField);
      }

      if (resourcePayloadSamplesMap.get().get(resourceName) != null) {
        AtomicReference<OffsetDateTime> offsetDateTime = new AtomicReference<>();

        resourcePayloadSamplesMap.get().get(resourceName).forEach(payloadSample -> {
         resourcesJson.totalBytesReceived.getAndAdd(payloadSample.getResponseSizeBytes());
         resourcesJson.totalResponseTimeMillis.getAndAdd(payloadSample.getResponseTimeMillis());
         resourcesJson.numSamplesProcessed.getAndIncrement();
         resourcesJson.numRecordsFetched.getAndAdd(payloadSample.encodedSamples.size());

         payloadSample.encodedSamples.forEach(encodedSample -> {
           try {
             offsetDateTime.set(OffsetDateTime.parse(encodedSample.get(payloadSample.dateField)));
             if (offsetDateTime.get() != null) {
               if (resourcesJson.dateLow.get() == null) {
                 resourcesJson.dateLow.set(offsetDateTime.get());
               } else if (offsetDateTime.get().isBefore(resourcesJson.dateLow.get())) {
                 resourcesJson.dateLow.set(offsetDateTime.get());
               }

               if (resourcesJson.dateHigh.get() == null) {
                 resourcesJson.dateHigh.set(offsetDateTime.get());
               } else if (offsetDateTime.get().isAfter(resourcesJson.dateHigh.get())) {
                 resourcesJson.dateHigh.set(offsetDateTime.get());
               }
             }

             if (encodedSample.containsKey(POSTAL_CODE_KEY)) {
               postalCodes.add(encodedSample.get(POSTAL_CODE_KEY));
             }
           } catch (DateTimeParseException dateTimeParseException) {
             LOG.error("Could not parse date for field " + payloadSample.dateField + ", with value: "
                 + encodedSample.get(payloadSample.dateField) + ". Expected ISO 8601 timestamp format!"
             );
             throw dateTimeParseException;
           }
         });

         if (resourcesJson.pageSize.get() == 0) resourcesJson.pageSize.set(payloadSample.getSamples().size());
       });
      }
      if (postalCodes.size() > 0) {
        resourcesJson.postalCodes.set(postalCodes);
      }

      resourceTotalsByResource.add(resourcesJson.serialize(resourcesJson, ResourcesJson.class, null));
    });

    availabilityReport.add(RESOURCE_INFO_KEY, resourceTotalsByResource);
    availabilityReport.add(FIELDS_KEY, fields);
    availabilityReport.add(LOOKUPS_KEY, lookups);
    availabilityReport.add(LOOKUP_VALUES_KEY, lookupValues);

    return availabilityReport;
  }

  static final class LookupsJson implements JsonSerializer<LookupsJson> {
    final String resourceName, fieldName;
    final Integer numLookupsTotal;

    public LookupsJson(String resourceName, String fieldName, Integer numLookupsTotal) {
      this.resourceName = resourceName;
      this.fieldName = fieldName;
      this.numLookupsTotal = numLookupsTotal;
    }

    final String
      RESOURCE_NAME_KEY = "resourceName",
      FIELD_NAME_KEY = "fieldName",
      NUM_LOOKUPS_TOTAL = "numLookupsTotal";

    /**
     * Gson invokes this call-back method during serialization when it encounters a field of the
     * specified type.
     *
     * <p>In the implementation of this call-back method, you should consider invoking
     * {@link JsonSerializationContext#serialize(Object, Type)} method to create JsonElements for any
     * non-trivial field of the {@code src} object. However, you should never invoke it on the
     * {@code src} object itself since that will cause an infinite loop (Gson will call your
     * call-back method again).</p>
     *
     * @param src       the object that needs to be converted to Json.
     * @param typeOfSrc the actual type (fully genericized version) of the source object.
     * @param context   the context of the request
     * @return a JsonElement corresponding to the specified object.
     */
    @Override
    public JsonElement serialize(LookupsJson src, Type typeOfSrc, JsonSerializationContext context) {
      JsonObject obj = new JsonObject();
      obj.addProperty(RESOURCE_NAME_KEY, src.resourceName);
      obj.addProperty(FIELD_NAME_KEY, src.fieldName);
      obj.addProperty(NUM_LOOKUPS_TOTAL, src.numLookupsTotal);
      return obj;
    }
  }

  static final class ResourcesJson implements JsonSerializer<ResourcesJson> {
    final String
        RESOURCE_NAME_KEY = "resourceName",
        RECORD_COUNT_KEY = "recordCount",
        TOTAL_NUM_RECORDS_FETCHED = "numRecordsFetched",
        TOTAL_NUM_SAMPLES_KEY = "numSamples",
        PAGE_SIZE_KEY = "pageSize",
        AVERAGE_RESPONSE_TIME_MILLIS_KEY = "averageResponseTimeMillis",
        AVERAGE_RESPONSE_BYTES_KEY = "averageResponseBytes",
        KEY_FIELDS_KEY = "keyFields",
        DATE_FIELD_KEY = "dateField",
        DATE_LOW_KEY = "dateLow",
        DATE_HIGH_KEY = "dateHigh",
        POSTAL_CODES_KEY = "postalCodes";

    final AtomicInteger numSamplesProcessed = new AtomicInteger(0);
    final AtomicInteger numRecordsTotal = new AtomicInteger(0);
    final AtomicInteger numRecordsFetched = new AtomicInteger(0);
    final AtomicReference<String> resourceName = new AtomicReference<>();
    final AtomicLong totalResponseTimeMillis = new AtomicLong(0);
    final AtomicLong totalBytesReceived = new AtomicLong(0);
    final AtomicInteger pageSize = new AtomicInteger(0);
    final AtomicReference<List<String>> keyFields = new AtomicReference<>(new LinkedList<>());
    final AtomicReference<String> dateField = new AtomicReference<>();
    final AtomicReference<OffsetDateTime> dateLow = new AtomicReference<>(null);
    final AtomicReference<OffsetDateTime> dateHigh = new AtomicReference<>(null);
    final AtomicReference<Set<String>> postalCodes = new AtomicReference<>(new LinkedHashSet<>());

    public ResourcesJson(String resourceName) {
      this.resourceName.set(resourceName);
    }

    /**
     * Gson invokes this call-back method during serialization when it encounters a field of the
     * specified type.
     *
     * <p>In the implementation of this call-back method, you should consider invoking
     * {@link JsonSerializationContext#serialize(Object, Type)} method to create JsonElements for any
     * non-trivial field of the {@code src} object. However, you should never invoke it on the
     * {@code src} object itself since that will cause an infinite loop (Gson will call your
     * call-back method again).</p>
     *
     * @param src       the object that needs to be converted to Json.
     * @param typeOfSrc the actual type (fully genericized version) of the source object.
     * @param context   the context of the request
     * @return a JsonElement corresponding to the specified object.
     */
    @Override
    public JsonElement serialize(ResourcesJson src, Type typeOfSrc, JsonSerializationContext context) {
      JsonObject totals = new JsonObject();

      totals.addProperty(RESOURCE_NAME_KEY, src.resourceName.get());

      totals.addProperty(RECORD_COUNT_KEY, src.numRecordsTotal);

      totals.addProperty(TOTAL_NUM_RECORDS_FETCHED, src.numRecordsFetched.get());

      totals.addProperty(TOTAL_NUM_SAMPLES_KEY, src.numSamplesProcessed.get());

      totals.addProperty(PAGE_SIZE_KEY, src.pageSize.get());

      totals.addProperty(AVERAGE_RESPONSE_BYTES_KEY, src.numSamplesProcessed.get() > 0
          ? src.totalBytesReceived.get() / src.numSamplesProcessed.get() : 0);

      totals.addProperty(AVERAGE_RESPONSE_TIME_MILLIS_KEY, src.numSamplesProcessed.get() > 0
          ? src.totalResponseTimeMillis.get() / src.numSamplesProcessed.get() : 0);

      totals.addProperty(DATE_FIELD_KEY, src.dateField.get());

      totals.addProperty(DATE_LOW_KEY, src.dateLow.get() != null
          ? src.dateLow.get().format(DateTimeFormatter.ISO_INSTANT) : null);

      totals.addProperty(DATE_HIGH_KEY, src.dateHigh.get() != null
          ? src.dateHigh.get().format(DateTimeFormatter.ISO_INSTANT): null);

      JsonArray keyFields = new JsonArray();
      src.keyFields.get().forEach(keyFields::add);
      totals.add(KEY_FIELDS_KEY, keyFields);

      if (src.postalCodes.get().size() > 0) {
        JsonArray postalCodes = new JsonArray();
        src.postalCodes.get().forEach(postalCodes::add);
        totals.add(POSTAL_CODES_KEY, postalCodes);
      }

      return totals;
    }
  }
}
