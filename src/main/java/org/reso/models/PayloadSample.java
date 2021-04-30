package org.reso.models;

import org.apache.olingo.commons.api.edm.EdmKeyPropertyRef;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PayloadSample {
  String resourceName;
  String dateField;
  Long responseTimeMillis = null;
  Integer responseSizeBytes = null;
  String requestUri = null;

  //format is a list of key/value pairs where all fields besides
  //keys and timestamps are encoded with SHA
  final List<Map<String, String>> encodedSamples = new LinkedList<>();

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


}
