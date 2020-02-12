package org.reso.commander;

import org.reso.models.Request;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The Stats class keeps information about a Commander run.
 */
public class Stats {
  Date startDate, endDate;
  private Map<Request, Request> requests = new HashMap<>();

  public Stats () { /* */ }

  public void startRequest(Request request) {
    if (request != null) {
      requests.put(request, request.setStatus(Request.Status.STARTED).startTimer());
    }
  }

  public void updateRequest(Request request, Request.Status status) {
    // only affects existing requestStatuses, use startRequest to register a Request
    requests.get(request).stopTimer().setStatus(status);
  }

  public void updateRequest(Request request, Request.Status status, Exception ex) {
    requests.get(request).setFailedRequestException(ex);
    updateRequest(request, status);
  }

  public Map<Request, Request> getRequests() {
    return requests;
  }

  public int totalRequestCount() {
    return requests.size();
  }

  public int getRequestCount(Request.Status status) {
    return filterByStatus(status, requests.values()).size();
  }

  public Collection<Request> filterByStatus(Request.Status status) {
    return filterByStatus(status, requests.values());
  }

  public Collection<Request> filterByStatus(Request.Status status, Collection<Request> collection) {
    return collection.stream()
        .filter(requestStatus -> requestStatus.getStatus().compareTo(status) == 0).collect(Collectors.toList());
  }

  public Collection<Request> filterByMetallicCertification(String metallicName) {
    return filterByMetallicCertification(metallicName, requests.values());
  }

  public Collection<Request> filterByMetallicCertification(String metallicName, Collection<Request> collection) {
    return collection.stream()
        .filter(request -> request.getMetallicLevel().compareTo(metallicName) == 0).collect(Collectors.toList());
  }

  public Collection<Request> filterByCapability(String capabilityName) {
    return filterByCapability(capabilityName, requests.values());
  }

  public Collection<Request> filterByCapability(String capabilityName, Collection<Request> collection) {
    return collection.stream()
        .filter(request -> request.getCapability().compareTo(capabilityName) == 0).collect(Collectors.toList());
  }

  public HashMap<String, Integer> metallicStats;
  public HashMap<String, Integer> capabilitiesStats;

  public void startTimer() {
    startDate = new Date();
  }

  public void stopTimer() {
    endDate = new Date();
  }

  public long getElapsedTimeMillis() {
    return endDate.getTime() - startDate.getTime();
  }

}
