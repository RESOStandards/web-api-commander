package org.reso.models;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Request {
  private String requestId;
  private String outputFile;
  private String url;

  private Request request;
  private Status status;
  private Date startDate, endDate;
  private Integer httpResponseCode;
  private Exception failedRequestException;


  /**
   * Public constructor requires both outputFile and url. Remaining Request properties
   * may be set individually after Request has been instantiated.
   *
   * @param outputFile
   * @param url
   */
  public Request(String url, String outputFile, String requestId) {
    setUrl(url);
    setOutputFile(outputFile);
    setRequestId(requestId);
  }

  /**
   * Provides null handling for getting the requested item
   *
   * @param name the name of the item to get
   * @param node the nod to get the named item from
   * @return the named item, if present. Otherwise null
   */
  private static String safeGetNamedItem(String name, Node node) {
    Node named = name != null && node != null ? node.getAttributes().getNamedItem(name) : null;
    return named != null ? named.getNodeValue() : null;
  }

  /**
   * Loads the requests from the given File as an Observable List of Requests
   *
   * @param file the file containing the requests
   * @return an Observable list of requests, or an exception is thrown
   */
  public static List<Request> loadFromRESOScript(File file) {
    final String REQUESTS_KEY = "Requests";
    ArrayList<Request> requests = new ArrayList<>();

    try {
      FileInputStream fileIS = new FileInputStream(file);
      DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = builderFactory.newDocumentBuilder();
      Document xmlDocument = builder.parse(fileIS);
      XPath xPath = XPathFactory.newInstance().newXPath();
      String expression = "/OutputScript/" + REQUESTS_KEY + "/node()";
      NodeList nodes = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
      Node node;
      String outputFile, url, requestId;
      Request request;

      for (int i = 0; i < nodes.getLength(); i++) {
        node = nodes.item(i);

        if (node.getNodeType() == Node.ELEMENT_NODE) {
          outputFile = safeGetNamedItem(FIELDS.OUTPUT_FILE, node);
          url = safeGetNamedItem(FIELDS.URL, node);
          requestId = safeGetNamedItem(FIELDS.REQUEST_ID, node);

          request = new Request(url, outputFile, requestId);
          requests.add(request);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return requests;
  }

  /**
   * Gets the status of the given request
   *
   * @return the status of the request
   */
  public Status getStatus() {
    return status;
  }

  /**
   * Sets the status for the give request
   *
   * @param status the status to set
   * @return the current instance of the request
   */
  public Request setStatus(Status status) {
    this.status = status;
    return this;
  }

  /**
   * Starts request timer
   *
   * @return the current request
   */
  public Request startTimer() {
    startDate = new Date();
    return this;
  }

  /**
   * Stops request timer
   *
   * @return the current request
   */
  public Request stopTimer() {
    endDate = new Date();
    return this;
  }

  /**
   * Gets the elapsed time the request took to run
   *
   * @return the elapsed time of the request in milliseconds
   */
  public long getElapsedTimeMillis() {
    return endDate != null && startDate != null ? endDate.getTime() - startDate.getTime() : 0L;
  }

  /**
   * Sets the exception for a given request
   *
   * @param failedRequestException the exception that was thrown when the request was executed
   */
  public void setFailedRequestException(Exception failedRequestException) {
    this.failedRequestException = failedRequestException;
  }

  /**
   * HTTP Response code getter
   *
   * @return an Integer representing the response code
   */
  public Integer getHttpResponseCode() {
    return httpResponseCode;
  }

  /**
   * HTTP Response code setter
   *
   * @param httpResponseCode an Integer representing the HTTP Response code
   */
  public void setHttpResponseCode(Integer httpResponseCode) {
    this.httpResponseCode = httpResponseCode;
  }

  /**
   * Gets the given request by Id, if present.
   *
   * @return either the request Id or null if none was passed. May be null.
   */
  public String getRequestId() {
    return requestId;
  }

  /**
   * Sets the given request Id, if present.
   *
   * @param requestId the request Id to set.
   */
  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  /**
   * Output file getter
   *
   * @return the name of the output file for the request
   */
  public String getOutputFile() {
    return outputFile;
  }

  /**
   * Output file setter
   *
   * @param outputFile the name of the output file for the request (required, not null)
   */
  private void setOutputFile(String outputFile) {
    this.outputFile = outputFile;
  }

  /**
   * URL getter
   *
   * @return the URL for the request, or null
   */
  public String getUrl() {
    return url;
  }

  /**
   * URL setter
   *
   * @param url the URL for the request, or null
   */
  private void setUrl(String url) {
    this.url = url;
  }

  /**
   * An enumeration of request status options
   */
  public enum Status {
    STARTED, SUCCEEDED, FAILED, SKIPPED
  }

  /**
   * Represents the known fields that can be in a serialized Request
   */
  private static final class FIELDS {
    static final String OUTPUT_FILE = "OutputFile";
    static final String URL = "Url";
    static final String REQUEST_ID = "RequestId";
  }

  public static final class WELL_KNOWN {
    public static final String
      METADATA_ENDPOINT = "REQ-WA103-END3";
  }
}
