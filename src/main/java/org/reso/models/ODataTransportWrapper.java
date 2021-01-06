package org.reso.models;

import org.apache.olingo.client.api.communication.request.retrieve.ODataRawRequest;
import org.apache.olingo.client.api.communication.response.ODataRawResponse;
import org.reso.commander.common.TestUtils;

import java.util.Date;

import static org.reso.commander.common.TestUtils.HEADER_ODATA_VERSION;
import static org.reso.commander.common.TestUtils.prepareUri;

/**
 * Provides a wrapper for OData requests and responses
 * TODO: generalize interface for any HTTP API Transport
 */
public class ODataTransportWrapper {
  private String requestUri;
  private Date startDate, endDate;
  private Integer httpResponseCode;
  private Exception exception;
  private String responseData;
  private ODataRawRequest oDataRawRequest;
  private ODataRawResponse oDataRawResponse;
  private Request request;

  /**
   * Creates an OData transport wrapper for the given Request
   * @param request a Request object to make the request with
   */
  public ODataTransportWrapper(Request request) {
    this.request = request;
  }

  /**
   * Creates an OData transport wrapper for the given request URI string
   * @param requestUri
   */
  public ODataTransportWrapper(String requestUri) {
    this.requestUri = prepareUri(requestUri).toString();
  }

  /**
   * Starts request timer
   * @return the current wrapper
   */
  public ODataTransportWrapper startTimer() {
    this.startDate = new Date();
    return this;
  }

  /**
   * Stops request timer
   * @return the current wrapper
   */
  public ODataTransportWrapper stopTimer() {
    this.endDate = new Date();
    return this;
  }

  /**
   * Gets the elapsed time the request took to run
   * @return the elapsed time of the request in milliseconds
   */
  public long getElapsedTimeMillis() {
    return endDate != null && startDate != null ? endDate.getTime() - startDate.getTime() : 0L;
  }

  /**
   * Sets the exception for a given request
   * @param exception the exception that was thrown when the request was executed
   */
  public void setException(Exception exception) {
    this.exception = exception;
  }

  /**
   * HTTP Response code getter
   * @return an Integer representing the response code
   */
  public Integer getHttpResponseCode() {
    return httpResponseCode;
  }

  /**
   * HTTP Response code setter
   * @param httpResponseCode an Integer representing the HTTP Response code
   */
  public void setHttpResponseCode(Integer httpResponseCode) {
    this.httpResponseCode = httpResponseCode;
  }

  /**
   * Sets response data for the given request
   * @param responseData serialized data returned in the response
   */
  public void setResponseData(String responseData) {
    this.responseData = responseData;
  }

  /**
   * OData raw request getter
   * @return the ODataRawRequest used in this wrapper
   */
  public ODataRawRequest getODataRawRequest() {
    return oDataRawRequest;
  }

  /**
   * OData raw request setter
   * @param odataRawRequest sets the ODataRawRequest used in this wrapper
   */
  public void setODataRawRequest(ODataRawRequest odataRawRequest) {
    this.oDataRawRequest = odataRawRequest;
  }

  /**
   * OData raw response getter
   * @return gets the ODataRawResponse in this wrapper
   */
  public ODataRawResponse getODataRawResponse() {
    return oDataRawResponse;
  }

  /**
   * OData raw response setter
   * @param oDataRawResponse sets the ODataRawResponse for this wrapper
   */
  public void setODataRawResponse(ODataRawResponse oDataRawResponse) {
    this.oDataRawResponse = oDataRawResponse;
    setResponseData(TestUtils.convertInputStreamToString(getODataRawResponse().getRawResponse()));
    setHttpResponseCode(getODataRawResponse().getStatusCode());
  }

  /**
   * Response data getter
   * @return gets the response data from the wrapper
   */
  public String getResponseData() {
    return responseData;
  }

  /**
   * Request URI getter
   * @return gets the string request URI used for the request
   */
  public String getRequestUri() {
    if (requestUri != null) return prepareUri(requestUri).toString();
    if (request != null) return prepareUri(request.getRequestUrl()).toString();
    return null;
  }

  /**
   * Server version getter
   * @return gets the OData server version from the response header
   */
  public String getServerVersion() {
    return TestUtils.getHeaderData(HEADER_ODATA_VERSION, oDataRawResponse);
  }

  /**
   * Exception getter
   * @return gets any exception that was generated during the request
   */
  public Exception getException() {
    return exception;
  }
}
