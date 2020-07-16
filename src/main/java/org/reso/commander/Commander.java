package org.reso.commander;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.ODataClientErrorException;
import org.apache.olingo.client.api.communication.request.retrieve.EdmMetadataRequest;
import org.apache.olingo.client.api.communication.request.retrieve.ODataRawRequest;
import org.apache.olingo.client.api.communication.request.retrieve.XMLMetadataRequest;
import org.apache.olingo.client.api.communication.response.ODataRawResponse;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.edm.xml.XMLMetadata;
import org.apache.olingo.client.api.serialization.ODataSerializerException;
import org.apache.olingo.client.api.uri.QueryOption;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.format.ContentType;
import org.reso.auth.OAuth2HttpClientFactory;
import org.reso.auth.TokenHttpClientFactory;
import org.reso.commander.common.TestUtils;
import org.xml.sax.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;

/**
 * Most of the work done by the WebAPI commander is done by this class. Its public methods are, therefore,
 * the ones the Client programmer is expected to use.
 */
public class Commander {
  public static final int OK = 0;
  public static final int NOT_OK = 1;
  //TODO: find the corresponding query params constant for this
  public static final String AMPERSAND = "&";
  //TODO: find the corresponding query params constant for this
  public static final String EQUALS = "=";
  public static final Integer DEFAULT_PAGE_SIZE = 10;
  public static final Integer DEFAULT_PAGE_LIMIT = 1;
  public static final String REPORT_DIVIDER = "==============================================================";
  public static final String REPORT_DIVIDER_SMALL = "===========================";
  public static final String RESOSCRIPT_EXTENSION = ".resoscript";
  public static final String EDMX_EXTENSION = ".xml";
  private static final Logger LOG = LogManager.getLogger(Commander.class);
  private static final String EDM_4_0_3_XSD = "edm.4.0.errata03.xsd", EDMX_4_0_3_XSD = "edmx.4.0.errata03.xsd";

  private static String bearerToken;
  private static String clientId;
  private static String clientSecret;
  private static String authorizationUri;
  private static String tokenUri;
  private static String redirectUri;
  private static String scope;
  private static boolean isTokenClient, isOAuthClient;
  private static ODataClient client;
  private static boolean useEdmEnabledClient;
  private static String serviceRoot;
  private static Edm edm;
  private static XMLMetadata xmlMetadata;

  private Commander() {
    //private constructor, should not be used. Use Builder instead.
  }

  /**
   * Uses an XML validator to validate that the given string contains valid XML.
   *
   * @param xmlString the string containing the XML to validate.
   * @return true if the given xmlString is valid and false otherwise.
   */
  public static boolean validateXML(final String xmlString) {
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();

      //turn off expectation of having DTD in DOCTYPE tag
      factory.setValidating(false);
      factory.setNamespaceAware(true);

      factory.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new StreamSource[]{
          new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(EDM_4_0_3_XSD)),
          new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(EDMX_4_0_3_XSD))
      }));

      SAXParser parser = factory.newSAXParser();
      XMLReader reader = parser.getXMLReader();
      reader.setErrorHandler(new SimpleErrorHandler());
      InputSource inputSource = new InputSource(new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8)));
      inputSource.setEncoding(StandardCharsets.UTF_8.toString());
      reader.parse(inputSource);
      return true;
    } catch (SAXException saxEx) {
      if (saxEx.getMessage() != null) {
        LOG.error(getDefaultErrorMessage(saxEx));
      }
    } catch (Exception ex) {
      LOG.error(getDefaultErrorMessage("general error validating XML!"));
      LOG.debug("Exception in validateXML: " + ex);
    }
    return false;
  }

  /**
   * Prepares a URI for an OData request
   *
   * @param uriString the uri string to use for the request
   * @return the prepared URI
   */
  public static URI prepareURI(String uriString) {
    try {
      URL url = new URL(uriString);
      URIBuilder uriBuilder = new URIBuilder();
      uriBuilder.setScheme(url.getProtocol());

      uriBuilder.setHost(url.getHost());
      if (url.getPath() != null) uriBuilder.setPath(url.getPath());
      if (url.getQuery() != null) uriBuilder.setCustomQuery(url.getQuery());
      if (url.getPort() >= 0) uriBuilder.setPort(url.getPort());
      return uriBuilder.build();
    } catch (Exception ex) {
      LOG.error("ERROR in prepareURI: " + ex.toString());
    }
    return null;
  }

  /**
   * Translates supported string formats into those of ContentType.
   * <p>
   * See: https://olingo.apache.org/javadoc/odata4/org/apache/olingo/commons/api/format/ContentType.html#TEXT_HTML
   *
   * @param contentType the string representation of the requested content type.
   * @return one of ContentType if a match is found, or ContentType.JSON if no other format is available.
   */
  public static ContentType getContentType(String contentType) {
    final String
        JSON = "JSON",
        JSON_NO_METADATA = "JSON_NO_METADATA",
        JSON_FULL_METADATA = "JSON_FULL_METADATA",
        XML = "XML";

    ContentType type = ContentType.JSON;

    if (contentType == null) {
      return type;
    }

    if (contentType.matches(JSON)) {
      type = ContentType.JSON;
    } else if (contentType.matches(JSON_NO_METADATA)) {
      type = ContentType.JSON_NO_METADATA;
    } else if (contentType.matches(JSON_FULL_METADATA)) {
      type = ContentType.JSON_FULL_METADATA;
    } else if (contentType.matches(XML)) {
      type = ContentType.APPLICATION_XML;
    }

    return type;
  }

  /**
   * Creates a URI with a skip parameter
   *
   * @param requestUri the URI to add the $skip parameter to
   * @param pageSize   the page size of the page to get
   * @param skip       the number of pages to skip
   * @return a URI with the skip parameter added
   */
  private static URI buildSkipUri(String requestUri, Integer pageSize, Integer skip) {
    try {
      URIBuilder uriBuilder = null;
      URI preparedUri = prepareURI(requestUri);

      if (requestUri != null && requestUri.length() > 0 && preparedUri != null) {
        uriBuilder = new URIBuilder(preparedUri);

        if (skip != null && skip > 0) uriBuilder.setParameter(QueryOption.SKIP.toString(), skip.toString());
        uriBuilder.setParameter(QueryOption.TOP.toString(), pageSize == null || pageSize == 0 ? DEFAULT_PAGE_SIZE.toString() : pageSize.toString());

        URI uri = uriBuilder.build();
        LOG.debug("URI created: " + uri.toString());

        return uri;
      }
    } catch (Exception ex) {
      LOG.error("ERROR: " + ex.toString());
    }
    return null;
  }

  /**
   * Metadata Pretty Printer
   *
   * @param metadata any metadata in Edm format
   */
  public static String getMetadataReport(Edm metadata) {
    StringBuilder reportBuilder = new StringBuilder();

    reportBuilder.append("\n\n" + REPORT_DIVIDER);
    reportBuilder.append("\nMetadata Report");
    reportBuilder.append("\n" + REPORT_DIVIDER);

    //Note: other treatments may be added to this summary info
    metadata.getSchemas().forEach(schema -> {
      reportBuilder.append("\n\nNamespace: ").append(schema.getNamespace());
      reportBuilder.append("\n" + REPORT_DIVIDER_SMALL);

      schema.getTypeDefinitions().forEach(a ->
          reportBuilder.append("\nType Definition:").append(a.getName()));

      schema.getEntityTypes().forEach(a -> {
        reportBuilder.append("\nEntity Type: ").append(a.getName());
        a.getKeyPropertyRefs().forEach(ref ->
            reportBuilder.append("\n\tKey Field: ").append(ref.getName()));
        a.getPropertyNames().forEach(n -> reportBuilder.append("\n\tName: ").append(n));
      });

      schema.getEnumTypes().forEach(edmEnumType -> {
        reportBuilder.append("\nEnum Type: ")
            .append(edmEnumType.getName())
            .append(" (").append(schema.getNamespace()).append(", ")
            .append(edmEnumType.getUnderlyingType().getFullQualifiedName().getFullQualifiedNameAsString())
            .append(")");

        edmEnumType.getMemberNames().forEach(n -> reportBuilder.append("\n\tName: ").append(n));
      });

      schema.getComplexTypes().forEach(a ->
          reportBuilder.append("\nComplex Entity Type: ").append(a.getFullQualifiedName().getFullQualifiedNameAsString()));

      schema.getAnnotationGroups().forEach(a ->
          reportBuilder.append("\nAnnotation: ").append(a.getQualifier()).append(", Target Path: ").append(a.getTargetPath()));

      schema.getTerms().forEach(a ->
          reportBuilder.append("\nTerm: ").append(a.getFullQualifiedName().getFullQualifiedNameAsString()));
    });

    return reportBuilder.toString();
  }

  /**
   * Deserializes XML Metadata from a string
   *
   * @param xmlMetadataString a string containing XML Metadata
   * @param client            an instance of an OData Client
   * @return the XML Metadata contained within the string
   */
  public static XMLMetadata deserializeXMLMetadata(String xmlMetadataString, ODataClient client) {
    //deserialize response into XML Metadata - will throw an exception if metadata are invalid
    return client.getDeserializer(ContentType.APPLICATION_XML)
        .toMetadata(new ByteArrayInputStream(xmlMetadataString.getBytes(StandardCharsets.UTF_8)));
  }

  /**
   * Deserializes Edm from XML Metadata
   *
   * @param xmlMetadataString a string containing XML metadata
   * @param client            an instance of an OData Client
   * @return the Edm contained within the xmlMetadataString
   * <p>
   * TODO: rewrite the separate Edm request in the Web API server test code to only make one request and convert
   * to Edm from the XML Metadata that was received.
   */
  public static Edm deserializeEdm(String xmlMetadataString, ODataClient client) {
    return client.getReader().readMetadata(new ByteArrayInputStream(xmlMetadataString.getBytes(StandardCharsets.UTF_8)));
  }

  /**
   * Static version of the metadata validator that can work with a given client
   *
   * @param metadata the XML Metadata to validate
   * @param client   the OData client to use for validation
   * @return true if the given XML metadata is valid, false otherwise
   */
  public static boolean validateMetadata(XMLMetadata metadata, ODataClient client) {
    try {
      // call the probably-useless metadata validator. can't hurt though
      // SEE: https://github.com/apache/olingo-odata4/blob/master/lib/client-core/src/main/java/org/apache/olingo/client/core/serialization/ODataMetadataValidationImpl.java#L77-L116
      client.metadataValidation().validateMetadata(metadata);

      // also check whether metadata contains a valid service document in OData v4 format
      return client.metadataValidation().isServiceDocument(metadata)
          && client.metadataValidation().isV4Metadata(metadata);
    } catch (NullPointerException nex) {
      LOG.error("ERROR: Validation Failed! Null pointer exception while trying to validate metadata.");
    } catch (Exception ex) {
      LOG.error("ERROR: Validation Failed! General error occurred when validating metadata.\n" + ex.getMessage());
      if (ex.getCause() != null) {
        LOG.error("Caused by: " + ex.getCause().getMessage());
      }
    }
    return false;
  }

  /**
   * Static version of the metadata validator that can work with a given client
   *
   * @param edm    the Edm to validate
   * @param client the OData client to use for validation
   * @return true if the given XML metadata is valid, false otherwise
   */
  public static boolean validateMetadata(Edm edm, ODataClient client) {
    try {
      // call the probably-useless metadata validator. can't hurt though
      // SEE: https://github.com/apache/olingo-odata4/blob/master/lib/client-core/src/main/java/org/apache/olingo/client/core/serialization/ODataMetadataValidationImpl.java#L77-L116
      client.metadataValidation().validateMetadata(edm);
      //if Edm metadata are invalid, the previous line will throw an exception and this line won't be reached.
      return true;
    } catch (NullPointerException nex) {
      LOG.error("ERROR: Validation Failed! Null pointer exception while trying to validate metadata.");
    } catch (Exception ex) {
      LOG.error("ERROR: Validation Failed! General error occurred when validating metadata.\n" + ex.getMessage());
      if (ex.getCause() != null) {
        LOG.error("Caused by: " + ex.getCause().getMessage());
      }
    }
    return false;
  }

  /**
   * OData client getter
   *
   * @return the OData client for the current Commander instance
   */
  public ODataClient getClient() {
    return client;
  }

  /**
   * OData client setter
   *
   * @param client sets the current Commander instance to use the given client
   */
  public void setClient(ODataClient client) {
    Commander.client = client;
  }

  /**
   * Token URI getter
   *
   * @return the tokenUri used by the current Commander instance, or null
   */
  public String getTokenUri() {
    return tokenUri;
  }

  /**
   * Service Root getter
   *
   * @return the serviceRoot used by the current Commander instance, or null
   */
  public String getServiceRoot() {
    return serviceRoot;
  }

  /**
   * Determines whether the given authorization configuration is valid.
   *
   * @return true if the auth config is valid, false otherwise.
   */
  public boolean hasValidAuthConfig() {
    if (isAuthTokenClient()) {
      return bearerToken != null && bearerToken.length() > 0;
    }

    if (isOAuth2Client()) {
      return getTokenUri() != null && getTokenUri().length() > 0
          && clientId != null && clientId.length() > 0
          && clientSecret != null && clientSecret.length() > 0;
    }
    return false;
  }

  /**
   * Prepares an Edm Metadata request
   *
   * @return a prepared Edm metadata request
   */
  public EdmMetadataRequest prepareEdmMetadataRequest() {
    EdmMetadataRequest request = getClient().getRetrieveRequestFactory().getMetadataRequest(getServiceRoot());
    request.addCustomHeader(HttpHeaders.CONTENT_TYPE, null);
    request.addCustomHeader(HttpHeaders.ACCEPT, null);
    return request;
  }

  /**
   * Prepares an XML Metadata request
   *
   * @return a prepared XML Metadata request
   */
  public XMLMetadataRequest prepareXMLMetadataRequest() {
    XMLMetadataRequest request = getClient().getRetrieveRequestFactory().getXMLMetadataRequest(getServiceRoot());
    request.addCustomHeader(HttpHeaders.CONTENT_TYPE, null);
    request.addCustomHeader(HttpHeaders.ACCEPT, null);
    return request;
  }

  /**
   * Reads Edm from XMLMetadata in the given path.
   *
   * @param pathToXmlMetadata the path to the XML metadata.
   * @return an Edm object containing XML Metadata to read.
   */
  public Edm readEdm(String pathToXmlMetadata) {
    try {
      return client.getReader().readMetadata(new FileInputStream(pathToXmlMetadata));
    } catch (FileNotFoundException fex) {
      LOG.error(fex.toString());
    }
    return null;
  }

  /**
   * Saves the given Edm model to the given outputFileName.
   *
   * @param metadata       the metadata to save.
   * @param outputFileName the file name to output the metadata to.
   */
  public void saveMetadata(Edm metadata, String outputFileName) throws IOException, ODataSerializerException {
    FileWriter writer = new FileWriter(outputFileName);
    client.getSerializer(ContentType.APPLICATION_XML).write(writer, metadata);
  }

  /**
   * Validates given XMLMetadata
   *
   * @param metadata the XMLMetadata to be validated
   * @return true if the metadata is valid, meaning that it's also a valid OData 4 Service Document
   */
  public boolean validateMetadata(XMLMetadata metadata) {
    return validateMetadata(metadata, client);
  }

  /**
   * Validates given XMLMetadata
   *
   * @param metadata the XMLMetadata to be validated
   * @return true if the metadata is valid, meaning that it's also a valid OData 4 Service Document
   */
  public boolean validateMetadata(Edm metadata) {
    return validateMetadata(metadata, client);
  }

  /**
   * Ensures that the input stream contains valid XMLMetadata.
   *
   * @param inputStream the input stream containing the metadata to validate.
   * @return true if the given input stream contains valid XML Metadata, false otherwise.
   */
  public boolean validateMetadata(InputStream inputStream) {
    try {
      String xmlString = TestUtils.convertInputStreamToString(inputStream);

      if (xmlString == null) return false;

      //require the XML Document to be valid XML before trying to validate it with the OData validator
      if (validateXML(xmlString)) {
        // deserialize metadata from given file
        XMLMetadata metadata = client.getDeserializer(ContentType.APPLICATION_XML)
                .toMetadata(new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8)));
        if (metadata != null) {
          return validateMetadata(metadata);
        } else {
          LOG.error("ERROR: no valid metadata was found!");
          return false;
        }
      }
    } catch (Exception ex) {
      LOG.error("ERROR in validateMetadata! " + ex.toString());
    }
    return false;
  }

  /**
   * Validates the given metadata contained in the given file path.
   *
   * @param pathToEdmx the path to look for metadata in. Assumes metadata is stored as XML.
   * @return true if the metadata is valid and false otherwise.
   */
  public boolean validateMetadata(String pathToEdmx) {
    try {
      return validateMetadata(new FileInputStream(pathToEdmx));
    } catch (Exception ex) {
      LOG.error("ERROR: could not validate metadata.\nPath was:" + pathToEdmx);
      LOG.error(ex.getMessage());
    }
    return false;
  }

  /**
   * Boolean to determine whether this Commander instance is a token client.
   *
   * @return true if the commander instance is a token client, false otherwise.
   */
  public boolean isAuthTokenClient() {
    return isTokenClient;
  }

  /**
   * Boolean to determine whether this Commander instance is an OAuth2 client.
   *
   * @return true if the commander instance is an OAuth2 client credentials client, false otherwise.
   */
  public boolean isOAuth2Client() {
    return isOAuthClient;
  }

  /**
   * Executes a get request on URI and saves raw response to outputFilePath.
   * Intended to be used mostly for testing.
   *
   * @param requestUri     the URI to make the request against
   * @param outputFilePath the outputFilePath to write the response to
   */
  public Integer saveGetRequest(String requestUri, String outputFilePath) {
    final String ERROR_EXTENSION = ".ERROR";
    File outputFile = null;
    InputStream inputStream = null;
    ODataRawResponse oDataRawResponse = null;
    Integer responseCode = null;

    try {
      if (requestUri != null && requestUri.length() > 0) {
        LOG.debug("Request URI: " + requestUri);
        outputFile = new File(outputFilePath);
        oDataRawResponse = client.getRetrieveRequestFactory().getRawRequest(prepareURI(requestUri)).execute();
        responseCode = oDataRawResponse.getStatusCode();
        inputStream = oDataRawResponse.getRawResponse();
        FileUtils.copyInputStreamToFile(inputStream, outputFile);
      } else {
        LOG.info("Empty Request Uri... Skipping!");
      }
    } catch (ODataClientErrorException oex) {
      outputFile = new File(outputFilePath + ERROR_EXTENSION);
      String errMsg = "Request URI: " + requestUri + "\n\nERROR:" + oex.toString();
      responseCode = oex.getStatusLine().getStatusCode();
      try {
        FileUtils.writeByteArrayToFile(outputFile, errMsg.getBytes());
      } catch (IOException ioEx) {
        LOG.error("Could not write to error log file!");
      } finally {
        LOG.error("Exception occurred in saveRawGetRequest. " + oex.getMessage());
      }
    } catch (IOException ioEx) {
      LOG.error("Could not write to error log file!");
      System.exit(NOT_OK);
    }
    return responseCode;
  }

  /**
   * Executes a raw OData request in the current commander instance without trying to intepret the results
   *
   * @param requestUri the URI to make the request to
   * @return a string containing the serialized response, or null
   */
  public String executeRawRequest(String requestUri) {
    String data = null;
    if (requestUri != null) {
      try {
        ODataRawRequest request = getClient().getRetrieveRequestFactory().getRawRequest(URI.create(requestUri));
        ODataRawResponse response = request.execute();
        data = TestUtils.convertInputStreamToString(response.getRawResponse());
      } catch (Exception ex) {
        LOG.error(ex);
      }
    }
    return data;
  }

  /**
   * Gets a ClientEntitySet for the given uri string.
   *
   * @param requestUri the string version of the URI to retrieve data from.
   * @return a ClientEntitySet with data containing results from a GET request to requestUri
   */
  public ClientEntitySet getEntitySet(String requestUri) {
    AtomicReference<ClientEntitySet> result = new AtomicReference<>();
    Function<ClientEntitySet, Void> pageHandler = clientEntitySet -> {
      result.set(clientEntitySet);
      return null;
    };

    //get one page from the pager and return the local result
    getPagedEntitySet(requestUri, DEFAULT_PAGE_LIMIT, DEFAULT_PAGE_SIZE, pageHandler);
    return result.get();
  }

  /**
   * Gets a paged entity set up to limit. Will do continuous paging depending on the limit passed.
   *
   * @param requestUri          the string version of the URI to make requests from.
   * @param pageLimit           the number of records to fetch.
   * @param pageSize            the number of records to fetch for each page.
   * @param pageHandlerCallback a function that processes results as they come in.
   * @return a count of the total number of records processed
   */
  public int getPagedEntitySet(String requestUri, Integer pageLimit, Integer pageSize, Function<ClientEntitySet, Void> pageHandlerCallback) {
    final int NO_LIMIT_SENTINEL = -1;

    ODataRetrieveResponse<ClientEntitySet> entitySetResponse = null;
    ClientEntitySet clientEntitySet = null;
    int totalPageCount = 0;

    try {
      URI uri = buildSkipUri(requestUri, pageSize, null);

      do {
        entitySetResponse = client.getRetrieveRequestFactory().getEntitySetRequest(uri).execute();
        clientEntitySet = entitySetResponse.getBody();

        //update the caller with results
        pageHandlerCallback.apply(clientEntitySet);

        totalPageCount++;

        //some of the next links don't currently work, so we can't use getNext() reliably.
        //we can, however, use the results of what we've downloaded previously to inform the skip.
        uri = buildSkipUri(requestUri, pageSize, totalPageCount * pageSize);

      } while (entitySetResponse.getStatusCode() == HttpStatus.SC_OK && entitySetResponse.getBody().getNext() != null
          && (pageLimit == NO_LIMIT_SENTINEL || totalPageCount < pageLimit));

    } catch (Exception ex) {
      //NOTE: sometimes a bad skip link in the payload can cause exceptions...the Olingo library validates the responses.
      LOG.error("ERROR: getEntitySet could not continue. " + ex.toString());
      System.exit(NOT_OK);
    }
    return totalPageCount;
  }

  /**
   * Converts metadata in EDMX format to metadata in Swagger 2.0 format.
   * Converted file will have the same name as the input file, with .swagger.json appended to the name.
   *
   * @param pathToEDMX the metadata file to convert.
   */
  public void convertEDMXToSwagger(String pathToEDMX) {
    final String FILENAME_EXTENSION = ".swagger.json";
    final String XSLT_RESOURCE_NAME = "V4-CSDL-to-OpenAPI.xslt";

    if (validateMetadata(pathToEDMX)) {
      try {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(XSLT_RESOURCE_NAME)));

        Source text = new StreamSource(new File(pathToEDMX));
        transformer.transform(text, new StreamResult(new File(pathToEDMX + FILENAME_EXTENSION)));

      } catch (Exception ex) {
        LOG.error("ERROR: convertMetadata failed. " + ex.toString());
        System.exit(NOT_OK);
      }
    } else {
      LOG.error("ERROR: invalid metadata! Please ensure metadata is valid using validateMetadata() before proceeding.");
    }
  }

  /**
   * Writes an Entity Set to the given outputFilePath.
   *
   * @param entitySet      - the ClientEntitySet to serialize.
   * @param outputFilePath - the path to write the file to.
   * @param contentType    - the OData content type to write with. Currently supported options are
   *                       JSON, JSON_NO_METADATA, JSON_FULL_METADATA, and XML.
   */
  public void serializeEntitySet(ClientEntitySet entitySet, String outputFilePath, ContentType contentType) {
    try {
      LOG.info("Saving " + entitySet.getEntities().size() + " item(s) to " + outputFilePath);
      client.getSerializer(contentType).write(new FileWriter(outputFilePath), client.getBinder().getEntitySet(entitySet));
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      System.exit(NOT_OK);
    }
  }

  /**
   * Writes the given entitySet to the given outputFilePath.
   * Writes in JSON format.
   *
   * @param entitySet      the ClientEntitySet to serialize.
   * @param outputFilePath the outputFilePath used to write to.
   */
  public void serializeEntitySet(ClientEntitySet entitySet, String outputFilePath) {
    //JSON is the default format, though other formats like JSON_FULL_METADATA and XML are supported as well
    serializeEntitySet(entitySet, outputFilePath, ContentType.JSON);
  }

  /**
   * Error handler class for SAX parser
   */
  public static class SimpleErrorHandler implements ErrorHandler {
    public void warning(SAXParseException e) throws SAXException {
      LOG.warn(e.getMessage());
    }

    public void error(SAXParseException e) throws SAXException {
      LOG.error(e.getMessage());
      throw new SAXException();
    }

    public void fatalError(SAXParseException e) throws SAXException {
      LOG.fatal(e.getCause().getMessage());
      throw new SAXException();
    }
  }

  /**
   * Builder pattern for creating Commander instances.
   */
  public static class Builder {
    String serviceRoot, bearerToken, clientId, clientSecret, authorizationUri, tokenUri, redirectUri, scope;
    boolean useEdmEnabledClient;

    /**
     * Default constructor
     */
    public Builder() {
      this.useEdmEnabledClient = false;
    }

    /**
     * Service root setter
     *
     * @param serviceRoot the Web API service root
     * @return a Builder containing the given Web API service root
     */
    public Builder serviceRoot(String serviceRoot) {
      this.serviceRoot = serviceRoot;
      return this;
    }

    /**
     * Bearer token setter
     *
     * @param bearerToken the token to use to connect to the server
     * @return a Builder set with the given bearerToken
     */
    public Builder bearerToken(String bearerToken) {
      this.bearerToken = bearerToken;
      return this;
    }

    /**
     * Client Identification setter
     *
     * @param clientId the OAuth2 client_id to use to authenticate against the server
     * @return a Builder set with the given clientId
     */
    public Builder clientId(String clientId) {
      this.clientId = clientId;
      return this;
    }

    /**
     * Client Secret setter
     *
     * @param clientSecret the OAuth2 client_secret to use to authenticate against the server
     * @return a Builder set with the given clientSecret
     */
    public Builder clientSecret(String clientSecret) {
      this.clientSecret = clientSecret;
      return this;
    }

    /**
     * Token URI setter
     *
     * @param tokenUri the OAuth2 token_uri to use to authenticate against the server
     * @return a Builder set with the given tokenUri
     */
    public Builder tokenUri(String tokenUri) {
      this.tokenUri = tokenUri;
      return this;
    }

    /**
     * Scope setter
     *
     * @param scope the OAuth2 scope to use to authenticate against the server
     * @return a Builder set with the given scope
     */
    public Builder scope(String scope) {
      this.scope = scope;
      return this;
    }

    /**
     * Use EDM Enabled Client setter - turns on additional OData query, payload, and metadata
     * checking in the underlying Olingo client library.
     *
     * @param useEdmEnabledClient true if the EdmEnabledClient is to be used, false otherwise.
     * @return a Builder set with EdmEnabledClient property set
     */
    public Builder useEdmEnabledClient(boolean useEdmEnabledClient) {
      this.useEdmEnabledClient = useEdmEnabledClient;
      return this;
    }

    /**
     * Commander builder is used to create instances of the RESO Commander, which should not be instantiated directly.
     *
     * @return a Commander instantiated with the given properties set
     */
    public Commander build() {
      Commander commander = new Commander();
      Commander.serviceRoot = this.serviceRoot;
      Commander.bearerToken = this.bearerToken;
      Commander.clientId = this.clientId;
      Commander.clientSecret = this.clientSecret;
      Commander.authorizationUri = this.authorizationUri;
      Commander.tokenUri = this.tokenUri;
      Commander.redirectUri = this.redirectUri;
      Commander.scope = this.scope;
      Commander.useEdmEnabledClient = this.useEdmEnabledClient;

      //items required for OAuth client
      isOAuthClient = clientId != null && clientId.length() > 0
          && clientSecret != null && clientSecret.length() > 0
          && tokenUri != null && tokenUri.length() > 0;

      //items required for token client
      isTokenClient = bearerToken != null && bearerToken.length() > 0;

      LOG.debug("\nUsing EdmEnabledClient: " + useEdmEnabledClient + "...");
      if (useEdmEnabledClient) {
        commander.setClient(ODataClientFactory.getEdmEnabledClient(serviceRoot));
      } else {
        commander.setClient(ODataClientFactory.getClient());
      }

      if (isOAuthClient) {
        commander.getClient().getConfiguration().setHttpClientFactory(new OAuth2HttpClientFactory(clientId, clientSecret, tokenUri, scope));
      } else if (isTokenClient) {
        commander.getClient().getConfiguration().setHttpClientFactory(new TokenHttpClientFactory(bearerToken));
      }
      return commander;
    }
  }
}