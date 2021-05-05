package org.reso.models;

import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.edm.EdmElement;
import org.apache.olingo.commons.api.edm.EdmKeyPropertyRef;
import org.reso.commander.common.Utils;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class PayloadSampleReport implements JsonSerializer<PayloadSampleReport> {
  private static final Logger LOG = LogManager.getLogger(PayloadSampleReport.class);
  Map<String, List<PayloadSample>> resourcePayloadSamplesMap = new LinkedHashMap<>();
  Map<String, Map<String, Integer>> resourceFieldTallies = new LinkedHashMap<>(new LinkedHashMap<>());

  private Edm metadata;

  private PayloadSampleReport() {
    //private default constructor
  }

  public PayloadSampleReport(Edm metadata, Map<String, List<PayloadSample>> resourcePayloadSamplesMap) {
    this.metadata = metadata;
    this.resourcePayloadSamplesMap = resourcePayloadSamplesMap;
    resourceFieldTallies = createResourceFieldTallies(resourcePayloadSamplesMap);
  }

  @Override
  public String toString() {
    return String.valueOf(serialize(this, FieldAvailabilityJson.class, null));
  }

  /**
   * FieldAvailabilityJson uses a JSON payload with the following structure:
   *
   *    {
   *       "resourceName": "Property",
   *       "fieldName": "AboveGradeFinishedArea",
   *       "availability": 0.1
   *     }
   */
  private final class FieldAvailabilityJson implements JsonSerializer<FieldAvailabilityJson> {
    static final String
        RESOURCE_NAME_KEY = "resourceName",
        FIELD_NAME_KEY = "fieldName",
        FIELDS_KEY = "fields",
        AVAILABILITY_KEY = "availability";

    String resourceName;
    EdmElement edmElement;

    public FieldAvailabilityJson(String resourceName, EdmElement edmElement) {
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
        reportBuilder.append("\nAvailability: ");
        reportBuilder.append(field.getAsJsonObject().get(AVAILABILITY_KEY));
        reportBuilder.append("\n");
      });
      return reportBuilder.toString();
    }

    @Override
    public JsonElement serialize(FieldAvailabilityJson src, Type typeOfSrc, JsonSerializationContext context) {
      JsonObject field = new JsonObject();

      int numTimesPresent = resourceFieldTallies != null && resourceFieldTallies.get(src.resourceName) != null
          && resourceFieldTallies.get(src.resourceName).get(src.edmElement.getName()) != null
          ? resourceFieldTallies.get(src.resourceName).get(src.edmElement.getName()) : 0;

      int numSamples = resourcePayloadSamplesMap.get(src.resourceName) != null
          ? resourcePayloadSamplesMap.get(src.resourceName).stream().reduce(0, (a, f) -> a + f.encodedSamples.size(), Integer::sum) : 0;

      field.addProperty(RESOURCE_NAME_KEY, src.resourceName);
      field.addProperty(FIELD_NAME_KEY, src.edmElement.getName());
      field.addProperty(AVAILABILITY_KEY, numSamples > 0 ? (1.0 * numTimesPresent) / numSamples : 0);

      return field;
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
        resourcePayloadSamplesMap.get(resourceName).forEach(payloadSample -> {
          payloadSample.getSamples().forEach(sample -> {
            sample.forEach((fieldName, encodedValue) -> {
              if (encodedValue != null) {
                resourceTallies.get().get(resourceName).putIfAbsent(fieldName, 0);
                resourceTallies.get().get(resourceName).put(fieldName, resourceTallies.get().get(resourceName).get(fieldName) + 1);
              }
            });
          });
        });
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
        RESOURCE_INFO_KEY = "resourceInfo",
        FIELDS_KEY = "fields";

    JsonArray fields = new JsonArray();
    Set<String> postalCodes = new LinkedHashSet<>();

    src.metadata.getSchemas().forEach(edmSchema -> {
      //serialize entities (resources) and members (fields)
      edmSchema.getEntityTypes().forEach(edmEntityType -> {
        edmEntityType.getPropertyNames().forEach(propertyName -> {
          FieldAvailabilityJson fieldJson = new FieldAvailabilityJson(edmEntityType.getName(), edmEntityType.getProperty(propertyName));
          fields.add(fieldJson.serialize(fieldJson, FieldAvailabilityJson.class, null));
        });
      });
    });

    JsonObject availabilityReport = new JsonObject();
    availabilityReport.addProperty(DESCRIPTION_KEY, DESCRIPTION);
    availabilityReport.addProperty(VERSION_KEY, VERSION);
    availabilityReport.addProperty(GENERATED_ON_KEY, Utils.getIsoTimestamp());

    final JsonArray resourceTotalsByResource = new JsonArray();
    src.resourcePayloadSamplesMap.keySet().forEach(resourceName -> {
      ResourceInfo resourceInfo = new ResourceInfo(resourceName);
      PayloadSample zerothSample = resourcePayloadSamplesMap.get(resourceName) != null
          && resourcePayloadSamplesMap.get(resourceName).size() > 0
          ? resourcePayloadSamplesMap.get(resourceName).get(0) : null;

      if (zerothSample != null) {
        resourceInfo.keyFields.set(zerothSample.keyFields.stream().map(EdmKeyPropertyRef::getName).collect(Collectors.toList()));
        resourceInfo.dateField.set(zerothSample.dateField);
      }

      if (src.resourcePayloadSamplesMap.get(resourceName) != null) {
       src.resourcePayloadSamplesMap.get(resourceName).forEach(payloadSample -> {
         resourceInfo.totalBytesReceived.getAndAdd(payloadSample.getResponseSizeBytes());
         resourceInfo.totalResponseTimeMillis.getAndAdd(payloadSample.getResponseTimeMillis());
         resourceInfo.numSamplesProcessed.getAndIncrement();
         resourceInfo.numRecordsFetched.getAndAdd(payloadSample.encodedSamples.size());

         payloadSample.encodedSamples.forEach(encodedSample -> {
           OffsetDateTime offsetDateTime = OffsetDateTime.parse(encodedSample.get(payloadSample.dateField));
           if (offsetDateTime != null) {
             if (resourceInfo.dateLow.get() != null && offsetDateTime.isBefore(resourceInfo.dateLow.get())) {
               resourceInfo.dateLow.set(offsetDateTime);
             }

             if (resourceInfo.dateHigh.get() != null && offsetDateTime.isAfter(resourceInfo.dateHigh.get())) {
               resourceInfo.dateHigh.set(offsetDateTime);
             }
           }

           if (encodedSample.containsKey("PostalCode")) {
             postalCodes.add(encodedSample.get("PostalCode"));
           }
         });

         if (resourceInfo.pageSize.get() == 0) resourceInfo.pageSize.set(payloadSample.getSamples().size());
       });
      }
      resourceTotalsByResource.add(resourceInfo.serialize(resourceInfo, ResourceInfo.class, null));
    });

    LOG.info("Postal Codes: " + postalCodes);
    availabilityReport.add(RESOURCE_INFO_KEY, resourceTotalsByResource);
    availabilityReport.add(FIELDS_KEY, fields);

    return availabilityReport;
  }

  static final class ResourceInfo implements JsonSerializer<ResourceInfo> {
    final String
        RESOURCE_NAME_KEY = "resourceName",
        TOTAL_NUM_RECORDS_FETCHED = "numRecordsFetched",
        TOTAL_NUM_SAMPLES_KEY = "numSamples",
        PAGE_SIZE_KEY = "pageSize",
        AVERAGE_RESPONSE_TIME_MILLIS_KEY = "averageResponseTimeMillis",
        AVERAGE_RESPONSE_BYTES_KEY = "averageResponseBytes",
        KEY_FIELDS_KEY = "keyFields",
        DATE_FIELD_KEY = "dateField",
        DATE_LOW_KEY = "dateLow",
        DATE_HIGH_KEY = "dateHigh";

    final AtomicInteger numSamplesProcessed = new AtomicInteger(0);
    final AtomicInteger numRecordsFetched = new AtomicInteger(0);
    final AtomicReference<String> resourceName = new AtomicReference<>();
    final AtomicLong totalResponseTimeMillis = new AtomicLong(0);
    final AtomicLong totalBytesReceived = new AtomicLong(0);
    final AtomicInteger pageSize = new AtomicInteger(0);
    final AtomicReference<List<String>> keyFields = new AtomicReference<>(new LinkedList<>());
    final AtomicReference<String> dateField = new AtomicReference<>();
    final AtomicReference<OffsetDateTime> dateLow = new AtomicReference<>(null);
    final AtomicReference<OffsetDateTime> dateHigh = new AtomicReference<>(null);

    public ResourceInfo(String resourceName) {
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
     * @param context
     * @return a JsonElement corresponding to the specified object.
     */
    @Override
    public JsonElement serialize(ResourceInfo src, Type typeOfSrc, JsonSerializationContext context) {
      JsonObject totals = new JsonObject();
      totals.addProperty(RESOURCE_NAME_KEY, src.resourceName.get());
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

      return totals;
    }
  }

}
