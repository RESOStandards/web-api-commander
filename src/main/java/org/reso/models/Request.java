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
    private String requirementId;
    private String name;
    private String outputFile;
    private String url;
    private String testDescription;
    private String metallicLevel;
    private String capability;
    private String webApiReference;
    private String assertResponseCode;

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
    public Request(String requirementId, String outputFile, String url, String testDescription, String metallicLevel,
                   String capability, String webApiReference, String assertResponseCode) {

        //TODO: add Builder
        setRequirementId(requirementId);
        setOutputFile(outputFile);
        setUrl(url);
        setTestDescription(testDescription);
        setMetallicLevel(metallicLevel);
        setCapability(capability);
        setWebApiReference(webApiReference);
        setAssertResponseCode(assertResponseCode);
    }


    public Status getStatus() {
        return status;
    }

    public Request setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Request startTimer() {
        startDate = new Date();
        return this;
    }

    public Request stopTimer() {
        endDate = new Date();
        return this;
    }

    public long getElapsedTimeMillis() {
        return endDate != null && startDate != null ? endDate.getTime() - startDate.getTime() : 0L;
    }

    public void setFailedRequestException(Exception failedRequestException) {
        this.failedRequestException = failedRequestException;
    }

    public Integer getHttpResponseCode() {
        return httpResponseCode;
    }

    public void setHttpResponseCode(Integer httpResponseCode) {
        this.httpResponseCode = httpResponseCode;
    }

    public enum Status {
        STARTED, SUCCEEDED, FAILED, SKIPPED
    }

    /**
     * Provides null handling for getting the requested item
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
            String name, outputFile, url, testDescription, requirementId, metallicLevel, capability, webApiReference,
                assertResponseCode;
            Request request;

            for (int i = 0; i < nodes.getLength(); i++) {
                node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    outputFile = safeGetNamedItem(FIELDS.OUTPUT_FILE, node);
                    url = safeGetNamedItem(FIELDS.URL, node);
                    testDescription = safeGetNamedItem(FIELDS.TEST_DESCRIPTION, node);
                    requirementId = safeGetNamedItem(FIELDS.REQUIREMENT_ID, node);
                    metallicLevel = safeGetNamedItem(FIELDS.METALLIC_LEVEL, node);
                    capability = safeGetNamedItem(FIELDS.CAPABILITY, node);
                    webApiReference = safeGetNamedItem(FIELDS.WEB_API_REFERENCE, node);
                    assertResponseCode = safeGetNamedItem(FIELDS.ASSERT_RESPONSE_CODE, node);

                    request = new Request(requirementId, outputFile, url, testDescription, metallicLevel, capability, webApiReference, assertResponseCode);

                    name = safeGetNamedItem(FIELDS.NAME, node);
                    request.setName(name == null ? outputFile : name);

                    requests.add(request);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requests;
    }

    private int deserializeTests(Node node) {
        int numTests = 0;


        return numTests;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    /**
     * Name getter
     * @return the name of the request
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter
     * @param name the name of the request
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * Output file getter
     * @return the name of the output file for the request
     */
    public String getOutputFile() {
        return outputFile;
    }

    /**
     * Output file setter
     * @param outputFile the name of the output file for the request (required, not null)
     */
    private void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    /**
     * URL getter
     * @return the URL for the request, or null
     */
    public String getUrl() {
        return url;
    }

    /**
     * URL setter
     * @param url the URL for the request, or null
     */
    private void setUrl(String url) {
        this.url = url;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public String getMetallicLevel() {
        return metallicLevel;
    }

    public void setMetallicLevel(String metallicLevel) {
        this.metallicLevel = metallicLevel;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }

    public String getWebApiReference() {
        return webApiReference;
    }

    public void setWebApiReference(String webApiReference) {
        this.webApiReference = webApiReference;
    }

    public String getAssertResponseCode() {
        return assertResponseCode;
    }

    public void setAssertResponseCode(String assertResponseCode) {
        this.assertResponseCode = assertResponseCode;
    }

    private static final class FIELDS {
        static final String NAME = "Name";
        static final String OUTPUT_FILE = "OutputFile";
        static final String URL = "Url";
        static final String TEST_DESCRIPTION = "TestDescription";
        static final String REQUIREMENT_ID = "RequirementId";
        static final String METALLIC_LEVEL = "MetallicLevel";
        static final String CAPABILITY = "Capability";
        static final String WEB_API_REFERENCE = "WebAPIReference";
        static final String ASSERT_RESPONSE_CODE = "AssertResponseCode";
    }
}
