package org.reso.commander;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.ODataClientErrorException;
import org.apache.olingo.client.api.communication.request.retrieve.EdmMetadataRequest;
import org.apache.olingo.client.api.communication.response.ODataRawResponse;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.edm.xml.XMLMetadata;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.client.core.domain.ClientEntitySetImpl;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.format.ContentType;
import org.reso.auth.OAuth2HttpClientFactory;
import org.reso.auth.TokenHttpClientFactory;
import org.xml.sax.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * URI
 * Most of the work done by the WebAPI commander is done by this class. Its public methods are, therefore,
 * the ones the Client programmer is expected to use.
 */
public class Commander {
  //one instance of client per Commander. See Builder
  private ODataClient client;
  private boolean useEdmEnabledClient;

  private String serviceRoot;
  String bearerToken;
  String clientId;
  String clientSecret;
  String authorizationUri;
  String tokenUri;
  String redirectUri;
  String scope;
  boolean isTokenClient, isOAuthClient;

  private static final Logger LOG = LogManager.getLogger(Commander.class);

  //TODO move to utils class
  public static final int OK = 0;
  public static final int NOT_OK = 1;

  public ODataClient getClient() {
    return this.client;
  }

  public void setClient(ODataClient client) {
    this.client = client;
  }

  public String getTokenUri() {
    return tokenUri;
  }

  public String getServiceRoot() {
    return serviceRoot;
  }

  /**
   * Builder pattern for creating Commander instances.
   */
  public static class Builder {
    String serviceRoot, bearerToken, clientId, clientSecret, authorizationUri, tokenUri, redirectUri, scope;
    boolean useEdmEnabledClient;

    public Builder() {
      this.useEdmEnabledClient = false;
    }

    public Builder serviceRoot(String serviceRoot) {
      this.serviceRoot = serviceRoot;
      return this;
    }

    public Builder bearerToken(String bearerToken) {
      this.bearerToken = bearerToken;
      return this;
    }

    public Builder clientId(String clientId) {
      this.clientId = clientId;
      return this;
    }

    public Builder clientSecret(String clientSecret) {
      this.clientSecret = clientSecret;
      return this;
    }

    public Builder authorizationUri(String authorizationUri) {
      this.authorizationUri = authorizationUri;
      return this;
    }

    public Builder tokenUri(String tokenUri) {
      this.tokenUri = tokenUri;
      return this;
    }

    public Builder redirectUri(String redirectURI) {
      this.redirectUri = redirectURI;
      return this;
    }

    public Builder scope(String scope) {
      this.scope= scope;
      return this;
    }

    public Builder useEdmEnabledClient(boolean useEdmEnabledClient) {
      this.useEdmEnabledClient = useEdmEnabledClient;
      return this;
    }

    public Commander build() {
      Commander commander = new Commander();
      commander.serviceRoot = this.serviceRoot;
      commander.bearerToken = this.bearerToken;
      commander.clientId = this.clientId;
      commander.clientSecret = this.clientSecret;
      commander.authorizationUri = this.authorizationUri;
      commander.tokenUri = this.tokenUri;
      commander.redirectUri = this.redirectUri;
      commander.scope = this.scope;
      commander.useEdmEnabledClient = this.useEdmEnabledClient;

      //items required for OAuth client
      commander.isOAuthClient =
          clientId != null && clientId.length() > 0 &&
              clientSecret != null && clientSecret.length() > 0 &&
              authorizationUri != null && authorizationUri.length() > 0 &&
              tokenUri != null && tokenUri.length() > 0;

      //items required for token client
      commander.isTokenClient = bearerToken != null && bearerToken.length() > 0;

      LOG.debug("\nUsing EdmEnabledClient: " + useEdmEnabledClient + "...");
      if (useEdmEnabledClient) {
        commander.setClient(ODataClientFactory.getEdmEnabledClient(serviceRoot));
      } else {
        commander.setClient(ODataClientFactory.getClient());
      }

      if (commander.isOAuthClient) {
        commander.getClient().getConfiguration().setHttpClientFactory(new OAuth2HttpClientFactory(
            clientId, clientSecret, authorizationUri, tokenUri, redirectUri, scope));
      } else if (commander.isTokenClient) {
        commander.getClient().getConfiguration().setHttpClientFactory(new TokenHttpClientFactory(bearerToken));
      }
      return commander;
    }
  }

  //private constructor for internal use, use Builder to construct instances
  private Commander() { }

  private Edm edm;
  private XMLMetadata xmlMetadata;

  /**
   * Gets server metadata in Edm format.
   * @implNote the data in this item are cached in the commander once fetched
   * @return Edm representation of the server metadata.
   * @return
   */
  public Edm getEdm() {
    if (edm == null) {
      edm = getODataRetrieveEdmResponse().getBody();
    }
    return edm;
  }

  /**
   * Gets server metadata in XMLMetadata format.
   * @implNote the data in this item are cached in the commander once fetched
   * @return XMLMetadata representation of the server metadata.
   */
  public XMLMetadata getXMLMetadata() {
    if (xmlMetadata == null) {
      EdmMetadataRequest metadataRequest = client.getRetrieveRequestFactory().getMetadataRequest(serviceRoot);
      LOG.info("Fetching XMLMetadata with OData Client from: " + metadataRequest.getURI().toString());
      xmlMetadata = metadataRequest.getXMLMetadata();
    }
    return xmlMetadata;
  }

  /**
   * Used to contain the response for the request as well as get metadata.
   * @return the OData response retrieved from the server when making the request.
   */
  public ODataRetrieveResponse<Edm> getODataRetrieveEdmResponse() {
    EdmMetadataRequest metadataRequest = client.getRetrieveRequestFactory().getMetadataRequest(serviceRoot);
    LOG.info("Fetching Edm with OData Client from: " + metadataRequest.getURI().toString());
    return metadataRequest.execute();
  }

  /**
   * Reads Edm from XMLMetadata in the given path.
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
   * @param metadata the metadata to save.
   * @param outputFileName the file name to output the metadata to.
   */
  public void saveMetadata(Edm metadata, String outputFileName) {
    try {
      FileWriter writer = new FileWriter(outputFileName);
      client.getSerializer(ContentType.APPLICATION_XML).write(writer, metadata);
    } catch (Exception ex) {
      LOG.error(ex.getStackTrace());
    }
  }

  /**
   * Validates given XMLMetadata
   *
   * @param metadata the XMLMetadata to be validated
   * @return true if the metadata is valid, meaning that it's also a valid OData 4 Service Document
   */
  public boolean validateMetadata(XMLMetadata metadata) {
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
   * Validates given XMLMetadata
   *
   * @param metadata the XMLMetadata to be validated
   * @return true if the metadata is valid, meaning that it's also a valid OData 4 Service Document
   */
  public boolean validateMetadata(Edm metadata) {
    try {
      // call the probably-useless metadata validator. can't hurt though
      // SEE: https://github.com/apache/olingo-odata4/blob/master/lib/client-core/src/main/java/org/apache/olingo/client/core/serialization/ODataMetadataValidationImpl.java#L77-L116
      client.metadataValidation().validateMetadata(metadata);
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

  public boolean validateMetadata(InputStream inputStream) {
    try {
      // deserialize metadata from given file
      XMLMetadata metadata = client.getDeserializer(ContentType.APPLICATION_XML).toMetadata(inputStream);
      return validateMetadata(metadata);
    } catch (Exception ex) {
      LOG.error("ERROR in validateMetadata! " + ex.toString() );
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
      // deserialize metadata from given file
      return validateMetadata(new FileInputStream(pathToEdmx));
    } catch (Exception ex) {
      LOG.error("Error occurred while validating metadata.\nPath was:" + pathToEdmx);
      LOG.error(ex.getMessage());
    }
    return false;
  }

  public boolean isTokenClient() {
    return isTokenClient;
  }

  public boolean isOAuthClient() {
    return isOAuthClient;
  }

  public static boolean validateXML(String xmlString) {
    return validateXML(new ByteArrayInputStream(xmlString.getBytes(UTF_8)));
  }

  public static boolean validateXML(InputStream inputStream) {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(false);
    factory.setNamespaceAware(true);

    try {
      SAXParser parser = factory.newSAXParser();
      XMLReader reader = parser.getXMLReader();
      reader.setErrorHandler(new SimpleErrorHandler());
      InputSource inputSource = new InputSource();
      inputSource.setByteStream(inputStream);
      reader.parse(inputSource);
      return true;
    } catch (Exception ex) {
      LOG.error(ex);
    }
    return false;
  }

  public static class SimpleErrorHandler implements ErrorHandler {
    public void warning(SAXParseException e) throws SAXException {
      System.out.println(e.getMessage());
    }

    public void error(SAXParseException e) throws SAXException {
      System.out.println(e.getMessage());
    }

    public void fatalError(SAXParseException e) throws SAXException {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Prepares a URI for an OData request
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
    } finally {
      if (requestUri != null && requestUri.length() > 0) {
        assert outputFile != null;
        LOG.info("Output File: " + outputFile.getPath());
      }
      return responseCode;
    }
  }

  public ClientEntitySet getEntitySet(String requestUri, Integer limit) {
    Function<Integer, Void> integerVoidFunction = num -> null;
    return getEntitySet(requestUri, limit, integerVoidFunction);
  }

  public ClientEntitySet getEntitySet(String requestUri) {
    return getEntitySet(requestUri, 1);
  }

  /**
   * Fairly primitive, for now, version of a fetch function.
   * TODO: add a function that can write a page at a time.
   * TODO: add a parallel function which can create n queries at a time and download their results
   *
   * @param requestUri the request URI to read from.
   * @param limit      the limit for the number of records to read from the Server. Use -1 to fetch all.
   * @return a ClientEntitySet containing any entities found.
   */
  public ClientEntitySet getEntitySet(String requestUri, Integer limit, Function<Integer, Void> updateStatusFunction) {

    List<ClientEntity> entities = new ArrayList<>();
    ODataRetrieveResponse<ClientEntitySet> entitySetResponse = null;
    ClientEntitySet results = new ClientEntitySetImpl();

    // function to create URIs from skips and local params
    Function<Integer, URI> buildSkipUri = skip -> {
      try {
        URIBuilder uriBuilder = null;
        URI preparedUri = prepareURI(requestUri);

        if (requestUri != null && requestUri.length() > 0 && preparedUri != null) {
          uriBuilder = new URIBuilder(preparedUri);

          if (skip != null && skip > 0) uriBuilder.addParameter("$skip", skip.toString());

          URI uri = uriBuilder.build();
          LOG.debug("URI created: " + uri.toString());

          return uri;
        }
      } catch (Exception ex) {
        LOG.error("ERROR: " + ex.toString());
      }
      return null;
    };

    try {
      URI uri = buildSkipUri.apply(null);

      LOG.info("Fetching results from: " + requestUri);

      do {
        entitySetResponse = client.getRetrieveRequestFactory().getEntitySetRequest(uri).execute();
        entities.addAll(entitySetResponse.getBody().getEntities());

        LOG.debug(limit > 0 ? Math.min(entities.size(), limit) : entities.size());

        //some of the next links don't currently work, so we can't use getNext() reliably.
        //we can, however, use the results of what we've downloaded previously to inform the skip.
        uri = buildSkipUri.apply(entities.size());

        updateStatusFunction.apply(entities.size());

      } while (entitySetResponse.getStatusCode() == HttpStatus.SC_OK && entitySetResponse.getBody().getNext() != null
          && (limit == -1 || entities.size() < limit));

    } catch (Exception ex) {
      //NOTE: sometimes a bad skip link in the payload can cause exceptions...the Olingo library validates the responses.
      LOG.error("ERROR: getEntitySet could not continue. " + ex.toString());
      System.exit(NOT_OK);
    } finally {
      //trim results size to requested limit
      results.getEntities().addAll(entities.subList(0, limit > 0 ? Math.min(limit, entities.size()) : entities.size()));
      results.setCount(entitySetResponse.getBody().getCount());
      LOG.info("\nTransfer Complete!");
    }
    return results;
  }

  /**
   * Converts metadata in EDMX format to metadata in Swagger 2.0 format.
   * Converted file will have the same name as the input file, with .swagger.json appended to the name.
   *
   * @param pathToEDMX the metadata file to convert.
   */
  public void convertEDMXToSwagger(String pathToEDMX) {
    final String FILENAME_EXTENSION = ".swagger.json";
    final String XSLT_FILENAME = "./V4-CSDL-to-OpenAPI.xslt";

    if (validateMetadata(pathToEDMX)) {
      try {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(new File(XSLT_FILENAME)));

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

    ContentType defaultType = ContentType.JSON;
    ContentType type;

    if (contentType == null) {
      return defaultType;
    } else {
      if (contentType.matches(JSON)) {
        type = ContentType.JSON;
      } else if (contentType.matches(JSON_NO_METADATA)) {
        type = ContentType.JSON_NO_METADATA;
      } else if (contentType.matches(JSON_FULL_METADATA)) {
        type = ContentType.JSON_FULL_METADATA;
      } else if (contentType.matches(XML)) {
        type = ContentType.APPLICATION_XML;
      } else {
        type = ContentType.JSON;
      }
    }
    return type;
  }
}
