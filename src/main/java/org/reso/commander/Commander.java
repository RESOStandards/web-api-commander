package org.reso.commander;

import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.ODataRawRequest;
import org.apache.olingo.client.api.edm.xml.XMLMetadata;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.format.ContentType;
import org.reso.auth.OAuth2HttpClientFactory;
import org.reso.auth.TokenHttpClientFactory;
import org.reso.commander.common.TestUtils;
import org.reso.commander.jsonSerializers.MetadataReport;
import org.reso.models.ODataTransportWrapper;
import org.xml.sax.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.reso.certification.containers.WebAPITestContainer.EMPTY_STRING;
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
  public static final String REPORT_DIVIDER = "==============================================================";
  public static final String REPORT_DIVIDER_SMALL = "===========================";
  public static final String RESOSCRIPT_EXTENSION = ".resoscript";
  public static final String METADATA_PATH = "/$metadata";
  private static final Logger LOG = LogManager.getLogger(Commander.class);
  private static final String EDM_4_0_3_XSD = "edm.4.0.errata03.xsd", EDMX_4_0_3_XSD = "edmx.4.0.errata03.xsd";
  private static String bearerToken;
  private static String clientId;
  private static String clientSecret;

  private static String tokenUri;
  private static boolean isTokenClient, isOAuthClient;
  private static ODataClient client;
  private static String serviceRoot;

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
      LOG.error("ERROR in prepareURI: " + ex);
    }
    return null;
  }

  /**
   * Metadata Pretty Printer
   *
   * @param metadata any metadata in Edm format
   */
  public static String generateMetadataReport(Edm metadata, String version, String fileName) {
    final String DEFAULT_FILENAME = "metadata-report.json";

    try {
      MetadataReport report = new MetadataReport(metadata, version);
      GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
      gsonBuilder.registerTypeAdapter(MetadataReport.class, report);

      File targetReportFile;

      if (fileName != null) {
        targetReportFile = new File(fileName.replaceAll(".edmx|.xml", EMPTY_STRING) + ".metadata-report.json");
      } else {
        //place unnamed files in the build directory
        targetReportFile = new File("build" + File.separator + "certification" + File.separator + "results", DEFAULT_FILENAME);
      }

      FileUtils.copyInputStreamToFile(new ByteArrayInputStream(gsonBuilder.create().toJson(report).getBytes()), targetReportFile);

      return report.toString();
    } catch (Exception ex) {
      LOG.error(getDefaultErrorMessage(ex));
    }

    return null;
  }

  public static String generateMetadataReport(Edm metadata, String version) {
    return generateMetadataReport(metadata, version, null);
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
   * Deserializes Edm from XML Metadata
   *
   * @param pathToMetadataFile a string containing the path to the raw XML Metadata file
   * @param client             an instance of an OData Client
   * @return the Edm contained within the xmlMetadataString
   * <p>
   * TODO: rewrite the separate Edm request in the Web API server test code to only make one request and convert
   * to Edm from the XML Metadata that was received.
   */
  public static Edm deserializeEdmFromPath(String pathToMetadataFile, ODataClient client) {
    return client.getReader().readMetadata(deserializeFileFromPath(pathToMetadataFile));
  }

  public static InputStream deserializeFileFromPath(String pathToFile) {
    try {
      return Files.newInputStream(Paths.get(pathToFile));
    } catch (IOException e) {
      LOG.fatal(getDefaultErrorMessage("Could not open file: " + pathToFile));
    }
    return null;
  }

  public static String convertInputStreamToString(InputStream inputStream) {
    return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
        .lines()
        .collect(Collectors.joining("\n"));
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
      LOG.error(getDefaultErrorMessage("Metadata validation Failed! See error messages and commander.log for further information."));
    } catch (Exception ex) {
      LOG.error(getDefaultErrorMessage("Metadata validation Failed! General error occurred when validating metadata.\n" + ex.getMessage()));
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
      LOG.error(getDefaultErrorMessage("Metadata validation Failed! See error messages and commander.log for further information."));
    } catch (Exception ex) {
      LOG.error(getDefaultErrorMessage("Metadata validation Failed! General error occurred when validating metadata.\n" + ex.getMessage()));
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
      return bearerToken != null && !bearerToken.isEmpty();
    }

    if (isOAuth2Client()) {
      return getTokenUri() != null && !getTokenUri().isEmpty()
          && clientId != null && !clientId.isEmpty()
          && clientSecret != null && !clientSecret.isEmpty();
    }
    return false;
  }

  /**
   * Prepares an OData raw request with the given requestUri
   *
   * @param requestUri the URI to use for the request
   * @return an ODataRawRequest for the given URI or null if one couldn't be created
   */
  public ODataRawRequest prepareODataRawMetadataRequest(String requestUri) {
    try {
      ODataRawRequest request = getClient().getRetrieveRequestFactory().getRawRequest(new URI(requestUri));
      request.setAccept(ContentType.APPLICATION_XML.toContentTypeString());
      request.setContentType(ContentType.APPLICATION_XML.toContentTypeString());
      return request;
    } catch (URISyntaxException uriSyntaxException) {
      LOG.error(getDefaultErrorMessage("Invalid Service Root URI:", getServiceRoot(), "\n" + uriSyntaxException.getMessage()));
    }
    return null;
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
      LOG.error("ERROR in validateMetadata! " + ex);
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
      return validateMetadata(Files.newInputStream(Paths.get(pathToEdmx)));
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
  public ODataTransportWrapper saveGetRequest(String requestUri, String outputFilePath) {
    final String ERROR_EXTENSION = ".ERROR";

    final ODataTransportWrapper wrapper = executeODataGetRequest(requestUri);
    try {
      if (wrapper.getResponseData() != null) {
        FileUtils.copyInputStreamToFile(new ByteArrayInputStream(wrapper.getResponseData().getBytes()),
            new File(outputFilePath));
        LOG.info("Response saved to: " + outputFilePath);
      }

      if (wrapper.getException() != null) {
        String errMsg = getDefaultErrorMessage("request failed!") +
            "\nURI: " + requestUri +
            "\nException: " + wrapper.getException();

        FileUtils.copyInputStreamToFile(new ByteArrayInputStream(errMsg.getBytes()),
            new File(outputFilePath + ERROR_EXTENSION));
      }
    } catch (Exception ex) {
      LOG.error(getDefaultErrorMessage(ex));
    }
    return wrapper;
  }

  /**
   * Constructs a metadata path if not present already
   *
   * @param requestUri string URI to build the path with
   * @return a URI with the metadata path included
   */
  public URI getPathToMetadata(String requestUri) {
    if (requestUri == null || requestUri.isEmpty()) {
      TestUtils.failAndExitWithErrorMessage("OData service root is missing!", LOG);
    } else {
      try {
        String uri = requestUri;
        if (!requestUri.contains(METADATA_PATH)) {
          uri += METADATA_PATH;
        }
        return new URI(uri).normalize();
      } catch (Exception ex) {
        TestUtils.failAndExitWithErrorMessage("Could not create metadata URI.\n\t" + ex, LOG);
      }
    }
    return null;
  }

  /**
   * Executes an OData GET Request with the current Commander instance
   *
   * @param wrapper the OData transport wrapper to use for the request
   * @return and OData transport wrapper with the response, or exception if one was thrown
   */
  public ODataTransportWrapper executeODataGetRequest(ODataTransportWrapper wrapper) {
    try {
      if (wrapper.getRequestUri().contains(METADATA_PATH)) {
        wrapper.setODataRawRequest(prepareODataRawMetadataRequest(wrapper.getRequestUri()));
      } else {
        wrapper.setODataRawRequest(getClient().getRetrieveRequestFactory().getRawRequest(URI.create(wrapper.getRequestUri())));
        wrapper.getODataRawRequest().setFormat(ContentType.JSON.toContentTypeString());
      }

      wrapper.startTimer();
      wrapper.setODataRawResponse(wrapper.getODataRawRequest().execute());

      if (wrapper.getHttpResponseCode() == HttpStatus.SC_OK) {
        LOG.info("Request succeeded..." + wrapper.getResponseData().getBytes().length + " bytes received.");
      }

    } catch (Exception ex) {
      getDefaultErrorMessage(ex.toString());
      wrapper.setException(ex);
    } finally {
      wrapper.stopTimer();
    }
    return wrapper;
  }

  public ODataTransportWrapper executeODataGetRequest(String requestUri) {
    return executeODataGetRequest(new ODataTransportWrapper(requestUri));
  }

  /**
   * Error handler class for SAX parser
   */
  public static class SimpleErrorHandler implements ErrorHandler {
    public void warning(SAXParseException e) {
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
    String serviceRoot;
    String bearerToken;
    String clientId;
    String clientSecret;
    String tokenUri;
    String scope;
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
      Commander.tokenUri = this.tokenUri;

      //items required for OAuth client
      isOAuthClient = clientId != null && !clientId.isEmpty()
          && clientSecret != null && !clientSecret.isEmpty()
          && tokenUri != null && !tokenUri.isEmpty();

      //items required for token client
      isTokenClient = bearerToken != null && !bearerToken.isEmpty();

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