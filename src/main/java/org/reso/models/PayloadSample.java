package org.reso.models;

import com.google.gson.*;
import org.apache.olingo.commons.api.edm.EdmKeyPropertyRef;
import org.reso.commander.common.Utils;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PayloadSample implements JsonSerializer<PayloadSample> {
  String resourceName;
  String dateField;
  Long responseTimeMillis = null;
  Integer responseSizeBytes = null;
  String requestUri = null;

  //format is a list of key/value pairs where all fields besides
  //keys and timestamps are encoded with SHA
  final List<Map<String, String>> encodedSamples = Collections.synchronizedList(new LinkedList<>());

  //keeps track of the list of key fields found on the server
  final List<EdmKeyPropertyRef> keyFields = new LinkedList<>();

  public PayloadSample(String resourceName, String dateField, List<EdmKeyPropertyRef> keyFields) {
    assert resourceName != null : "resourceName MUST be present";
    this.resourceName = resourceName;
    this.dateField = dateField;
    this.keyFields.addAll(keyFields);
  }

  public void addSample(Map<String, String> sample) {
    encodedSamples.add(sample);
  }

  public List<Map<String, String>> getSamples() {
    return encodedSamples;
  }

  public Long getResponseTimeMillis() {
    return responseTimeMillis;
  }

  public void setResponseTimeMillis(Long value) {
    responseTimeMillis = value;
  }

  public String getRequestUri() {
    return requestUri;
  }

  public void setRequestUri(String value) {
    requestUri = value;
  }

  public Integer getResponseSizeBytes() {
    return responseSizeBytes;
  }

  public void setResponseSizeBytes(Integer responseSizeBytes) {
    this.responseSizeBytes = responseSizeBytes;
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
   * @param context context of the serialization request
   * @return a JsonElement corresponding to the specified object.
   */
  @Override
  public JsonElement serialize(PayloadSample src, Type typeOfSrc, JsonSerializationContext context) {
    final String
      DESCRIPTION_KEY = "description",
      GENERATED_ON_KEY = "generatedOn",
      NUM_SAMPLES_KEY = "numSamples",
      REQUEST_URI_KEY = "requestUri",
      RESOURCE_NAME_KEY = "resourceName",
      DATE_FIELD_KEY = "dateField",
      KEY_FIELDS_KEY = "keyFields",
      ENCODED_VALUES_KEY = "encodedValues",
      DESCRIPTION = "RESO Payload Sample";


    JsonObject serialized = new JsonObject();
    serialized.addProperty(DESCRIPTION_KEY, DESCRIPTION);
    serialized.addProperty(GENERATED_ON_KEY, Utils.getIsoTimestamp());
    serialized.addProperty(NUM_SAMPLES_KEY, src.encodedSamples.size());
    serialized.addProperty(REQUEST_URI_KEY, src.requestUri);
    serialized.addProperty(RESOURCE_NAME_KEY,src.resourceName);

    JsonArray keyFields = new JsonArray();
    src.keyFields.forEach(k -> keyFields.add(k.getName()));
    serialized.add(KEY_FIELDS_KEY, keyFields);

    serialized.addProperty(DATE_FIELD_KEY, src.dateField);

    JsonArray encodedSamplesJson = new JsonArray();
    src.encodedSamples.forEach(sample -> {
      JsonObject sampleJson = new JsonObject();
      sample.forEach(sampleJson::addProperty);
      encodedSamplesJson.add(sampleJson);
    });

    serialized.add(ENCODED_VALUES_KEY, encodedSamplesJson);

    return serialized;
  }
}
