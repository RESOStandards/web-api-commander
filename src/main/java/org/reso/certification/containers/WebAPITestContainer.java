package org.reso.certification.containers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Singleton;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.communication.ODataClientErrorException;
import org.apache.olingo.client.api.communication.ODataServerErrorException;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetRequest;
import org.apache.olingo.client.api.communication.request.retrieve.ODataRawRequest;
import org.apache.olingo.client.api.communication.response.ODataRawResponse;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.edm.xml.XMLMetadata;
import org.apache.olingo.client.api.uri.QueryOption;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.format.ContentType;
import org.reso.commander.Commander;
import org.reso.commander.common.DataDictionaryMetadata;
import org.reso.commander.common.TestUtils;
import org.reso.models.*;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.reso.commander.Commander.*;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;
import static org.reso.commander.common.TestUtils.HEADER_ODATA_VERSION;
import static org.reso.commander.common.TestUtils.JSON_VALUE_PATH;
import static org.reso.models.Request.loadFromRESOScript;

/**
 * Encapsulates Commander Requests and Responses during runtime
 */
@Singleton
public final class WebAPITestContainer implements TestContainer {
  public static final String FIELD_SEPARATOR = ",";
  public static final String EMPTY_STRING = "";
  public static final String SINGLE_SPACE = " ";
  public static final String DOLLAR_SIGN = "$";
  public static final String DATA_SYSTEM_JSON_4_SCHEMA = "jsonschema/misc/datasystem.schema.4.json";
  public static final String PRETTY_FIELD_SEPARATOR = FIELD_SEPARATOR + SINGLE_SPACE;
  private static final Logger LOG = LogManager.getLogger(WebAPITestContainer.class);

  private final AtomicReference<Commander> commander = new AtomicReference<>();
  private final AtomicReference<XMLMetadata> xmlMetadata = new AtomicReference<>();
  private final AtomicReference<Edm> edm = new AtomicReference<>();
  private final AtomicReference<Settings> settings = new AtomicReference<>();
  private final AtomicReference<String> serviceRoot = new AtomicReference<>();
  private final AtomicReference<String> bearerToken = new AtomicReference<>();
  private final AtomicReference<String> clientId = new AtomicReference<>();
  private final AtomicReference<String> clientSecret = new AtomicReference<>();
  private final AtomicReference<String> authorizationUri = new AtomicReference<>();
  private final AtomicReference<String> tokenUri = new AtomicReference<>();
  private final AtomicReference<String> redirectUri = new AtomicReference<>();
  private final AtomicReference<String> scope = new AtomicReference<>();
  private final AtomicReference<String> pathToRESOScript = new AtomicReference<>();
  private final AtomicReference<String> pathToMetadata = new AtomicReference<>();
  private final AtomicReference<String> xmlResponseData = new AtomicReference<>();
  private final AtomicBoolean showResponses = new AtomicBoolean(false);
  private final AtomicBoolean shouldValidateMetadata = new AtomicBoolean(true);
  private final AtomicBoolean isInitialized = new AtomicBoolean(false);

  // Metadata and DataSystem state variables
  private final AtomicBoolean isValidXMLMetadata = new AtomicBoolean(false);
  private final AtomicBoolean isValidEdm = new AtomicBoolean(false);
  private final AtomicBoolean isValidXMLMetadataXML = new AtomicBoolean(false);
  private final AtomicBoolean haveMetadataBeenRequested = new AtomicBoolean(false);
  private final AtomicBoolean isDataSystemValid = new AtomicBoolean(false);
  private final AtomicReference<Set<ValidationMessage>> schemaValidationErrors = new AtomicReference<>();
  private final AtomicBoolean isUsingMetadataFile = new AtomicBoolean(false);
  private final AtomicBoolean useEdmEnabledClient = new AtomicBoolean(true);

  // request instance variables - these get resetMarkupBuffer with every request
  //TODO: refactor underlying response properties to use a ODataTransportWrapper (or any TransportWrapper)
  //      and create the test container with the appropriate response of the transport wrapper
  private final AtomicReference<String> selectList = new AtomicReference<>();
  private final AtomicReference<ODataRawResponse> oDataRawResponse = new AtomicReference<>();
  private final AtomicReference<Request> request = new AtomicReference<>();
  private final AtomicReference<URI> requestUri = new AtomicReference<>();
  private final AtomicReference<Integer> responseCode = new AtomicReference<>();
  private final AtomicReference<String> responseData = new AtomicReference<>();
  private final AtomicReference<String> initialResponseData = new AtomicReference<>(); //used if two result sets need to be compared
  private final AtomicReference<ODataRawRequest> rawRequest = new AtomicReference<>();
  private final AtomicReference<ODataClientErrorException> oDataClientErrorException = new AtomicReference<>();
  private final AtomicReference<ODataServerErrorException> oDataServerErrorException = new AtomicReference<>();
  private final AtomicReference<String> serverODataHeaderVersion = new AtomicReference<>();
  private final AtomicReference<Boolean> testAppliesToServerODataHeaderVersion = new AtomicReference<>();
  private final AtomicReference<ODataEntitySetRequest<ClientEntitySet>> clientEntitySetRequest = new AtomicReference<>();
  private final AtomicReference<ODataRetrieveResponse<ClientEntitySet>> clientEntitySetResponse = new AtomicReference<>();
  private final AtomicReference<ClientEntitySet> clientEntitySet = new AtomicReference<>();
  private static final String WEB_API_CORE_REFERENCE_REQUESTS = "reference-web-api-core-requests.xml";

  //singleton variables
  private static final AtomicReference<Map<String, Map<String, CsdlProperty>>> fieldMap = new AtomicReference<>();

  /**
   * Initializes the container with a singleton instance of the RESO Commander
   */
  public void initialize() {
    if (getIsInitialized()) return;

    LOG.info("Using Edm Enabled Client: " + useEdmEnabledClient.get());
    Commander.Builder builder = new Commander.Builder().useEdmEnabledClient(useEdmEnabledClient.get());

    if (getSettings() != null) {
      //overwrite any requests loaded with the reference queries
      //TODO: make the reference requests something that can be passed in during initialization
      getSettings().setRequests(loadFromRESOScript(new File(Objects.requireNonNull(
          getClass().getClassLoader().getResource(WEB_API_CORE_REFERENCE_REQUESTS)).getPath()))
          .stream().map(request -> Settings.resolveParameters(request, getSettings())).collect(Collectors.toList()));


      setServiceRoot(getSettings().getClientSettings().get(ClientSettings.SERVICE_ROOT));

      //TODO: add base64 un-encode when applicable
      setBearerToken(getSettings().getClientSettings().get(ClientSettings.BEARER_TOKEN));
      if (getAuthToken() != null && getAuthToken().length() > 0) {
        LOG.info("Bearer token loaded... first 4 characters: " + getAuthToken().substring(0, 4));
      }

      setClientId(getSettings().getClientSettings().get(ClientSettings.CLIENT_IDENTIFICATION));
      setClientSecret(getSettings().getClientSettings().get(ClientSettings.CLIENT_SECRET));
      setAuthorizationUri(getSettings().getClientSettings().get(ClientSettings.AUTHORIZATION_URI));
      setTokenUri(getSettings().getClientSettings().get(ClientSettings.TOKEN_URI));
      setRedirectUri(getSettings().getClientSettings().get(ClientSettings.REDIRECT_URI));
      setScope(getSettings().getClientSettings().get(ClientSettings.CLIENT_SCOPE));

      LOG.debug("Service root is: " + getServiceRoot());

      builder
          .clientId(getClientId())
          .clientSecret(getClientSecret())
          .tokenUri(getTokenUri())
          .scope(getScope())
          .serviceRoot(getServiceRoot())
          .bearerToken(getAuthToken());
    }

    //singleton Commander instance
    setCommander(builder.build());
    LOG.debug("Commander created!");
    isInitialized.set(true);
  }

  public void initialize(String pathToMetadataFile) {
    if (getIsInitialized()) return;
    assertNotNull(getDefaultErrorMessage("pathToMetadataFile MUST not be null!", pathToMetadataFile));
    this.setPathToMetadata(pathToMetadataFile);
    this.isUsingMetadataFile.set(pathToMetadataFile != null);
    this.initialize();
  }

  /**
   * Resets the state of the test container
   */
  public void resetState() {
    clientEntitySetRequest.set(null);
    clientEntitySetResponse.set(null);
    clientEntitySet.set(null);
    oDataRawResponse.set(null);
    request.set(null);
    requestUri.set(null);
    responseCode.set(null);
    responseData.set(null);
    initialResponseData.set(null);
    isDataSystemValid.set(false);
    rawRequest.set(null);
    oDataClientErrorException.set(null);
    oDataServerErrorException.set(null);
    serverODataHeaderVersion.set(null);
    selectList.set(null);
    testAppliesToServerODataHeaderVersion.set(false);
  }

  /**
   * Gets the field map from the well-known resource name passed in the given RESOScript
   *
   * @return a map of all Csdl properties keyed by field name.
   */
  public Map<String, Map<String, CsdlProperty>> getFieldMap() {
    if (fieldMap.get() == null) buildFieldMap();
    return fieldMap.get();
  }

  /**
   * Returns a field map for the given Entity Type (Resource) name
   *
   * @param entityTypeName the name of the entity type to search for
   * @return a field map, possibly empty, containing any fields that were found for the given resource
   */
  public Map<String, CsdlProperty> getFieldMap(String entityTypeName) {
    return getFieldMap().get(entityTypeName);
  }

  /**
   * Creates a metadata field map for the given resource name and each set of fields found for that resource, if present
   */
  private void buildFieldMap() {
    try {
      if (fieldMap.get() == null) {
        fieldMap.set(new LinkedHashMap<>());
      }

      LOG.debug("Building Field Map...");

      //if settings exist
      if (getXMLMetadata() == null) {
        if (getSettings() != null) {
          LOG.info("No XML Metadata found in the container but settings exist. Trying to fetch it from the server...");
          assertNotNull(getDefaultErrorMessage("No XML Metadata was fetched from the server!"), fetchXMLMetadata());
          assertNotNull(getDefaultErrorMessage("No Entity Data Model (edm) found in the container!"), getEdm());
          LOG.info("Metadata fetched!");
        } else {
          LOG.debug("Metadata does not exist in the container!");
          return;
        }
      }

      //build a map of all of the discovered fields on the server for the given resource by field name
      //TODO: add multiple Data Dictionary version support
      DataDictionaryMetadata.v1_7.WELL_KNOWN_RESOURCES.forEach(resourceName -> {
        List<CsdlProperty> csdlProperties = null;
        try {
          csdlProperties = TestUtils.findEntityTypesForEntityTypeName(getEdm(), getXMLMetadata(), resourceName);
        } catch (Exception e) {
          LOG.error(e);
        }

        if (csdlProperties != null) {
          LOG.debug("Found '" + resourceName + "' resource");
          csdlProperties.forEach(csdlProperty -> {
            if (!fieldMap.get().containsKey(resourceName)) fieldMap.get().put(resourceName, new LinkedHashMap<>());
            fieldMap.get().get(resourceName).put(csdlProperty.getName(), csdlProperty);
          });
        }
      });
      assertTrue("ERROR: No field were found in the server's metadata!", fieldMap.get().size() > 0);
      LOG.debug("Metadata Field Map created!");

    } catch (Exception ex) {
      LOG.error(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Gets XML Response data from the container
   *
   * @return the XML response data in the container
   */
  public String getXMLResponseData() {
    return xmlResponseData.get();
  }

  /**
   * Sets XML Response data in the container
   *
   * @param xmlResponseData the response data to set
   */
  public void setXMLResponseData(String xmlResponseData) {
    this.xmlResponseData.set(xmlResponseData);
  }

  /**
   * Executes HTTP GET request and sets the expected local variables in the WebAPITestContainer
   * Handles exceptions and sets response codes as well.
   */
  public void executePreparedRawGetRequest() throws Exception {
    try {
      setRawRequest(getCommander().getClient().getRetrieveRequestFactory().getRawRequest(getRequestUri()));
      getRawRequest().setFormat(ContentType.JSON.toContentTypeString());
      setODataRawResponse(getRawRequest().execute());
      setResponseData(TestUtils.convertInputStreamToString(getODataRawResponse().getRawResponse()));
      setServerODataHeaderVersion(TestUtils.getHeaderData(HEADER_ODATA_VERSION, getODataRawResponse()));
      setResponseCode(getODataRawResponse().getStatusCode());
      LOG.info("Request succeeded..." + getResponseData().getBytes().length + " bytes received.");
    } catch (Exception ex) {
      processODataRequestException(ex);
    }
  }

  /**
   * Gets OData Csdl for given field
   *
   * @param fieldName the name of the field to retrieve metadata about
   * @return the metadata for the given field
   */
  public CsdlProperty getCsdlProperty(String resourceName, String fieldName) {
    return getFieldMap() != null && getFieldMap().containsKey(resourceName)
        ? getFieldMap().get(resourceName).get(fieldName) : null;
  }

  /**
   * Parses an OData $select list
   *
   * @return the de-duplicated set of select list items
   */
  public Collection<String> getSelectList() {
    Arrays.stream(getRequestUri().getQuery().split(AMPERSAND)).forEach(fragment -> {
      if (fragment.contains(QueryOption.SELECT.toString())) {
        selectList.set(fragment.replace(ODATA_QUERY_PARAMS.SELECT, EMPTY_STRING).replace(EQUALS, EMPTY_STRING));
      }
    });
    if (selectList.get() == null) {
      return new ArrayList<>();
    } else {
      return new LinkedHashSet<>((Arrays.asList(selectList.get().split(FIELD_SEPARATOR))));
    }
  }

  /**
   * Settings getter
   *
   * @return local settings instance
   */
  public Settings getSettings() {
    return settings.get();
  }

  /**
   * Settings setter
   *
   * @param settings sets local settings instance to the given settings
   */
  public void setSettings(Settings settings) {
    this.settings.set(settings);
  }

  /**
   * Gets the Expand field from the RESOScript
   *
   * @return the configured Expand field
   */
  public String getExpandField() {
    return getSettings().getParameters().getValue(Parameters.WELL_KNOWN.EXPAND_FIELD);
  }

  /**
   * Gets server metadata in Edm format.
   *
   * @return the Entity Data Model contained in the container
   * @implNote the data in this item are cached in the test container once fetched
   */
  public Edm getEdm() {
    if (edm.get() == null) {
      assertNotNull(getDefaultErrorMessage("No XML response data found, cannot return Edm!"), xmlResponseData.get());
      edm.set(Commander.deserializeEdm(xmlResponseData.get(), getCommander().getClient()));
    }
    return edm.get();
  }

  public void setEdm(Edm edm) {
    this.edm.set(edm);
  }

  /**
   * Gets server metadata in XMLMetadata format.
   * <p>
   * Note: this method takes a slightly different approach than getting XML Metadata did previously in that
   * rather than fetching the metadata directly from the server using the Olingo getXmlMetadata method,
   * we make a raw request instead so that we can capture the response string for XML validation, and
   * we deserialize the XML Metadata object from the response string.
   *
   * @return XMLMetadata representation of the server metadata.
   * @implNote the data in this item are cached in the test container once fetched
   */
  public XMLMetadata fetchXMLMetadata() throws Exception {
    if (getSettings() != null && xmlMetadata.get() == null) {
      try {
        Request request = getSettings().getRequest(Request.WELL_KNOWN.METADATA_ENDPOINT);
        setRequest(request);
        assertNotNull(getDefaultErrorMessage("Metadata request was null! Please check your RESOScript."), request);

        URI pathToMetadata = getCommander().getPathToMetadata(request.getRequestUrl());
        if (pathToMetadata != null) {
          LOG.info("Requesting XML Metadata from: " + pathToMetadata.toString());
          ODataTransportWrapper wrapper = getCommander().executeODataGetRequest(pathToMetadata.toString());
          setODataRawResponse(wrapper.getODataRawResponse());
          responseCode.set(wrapper.getHttpResponseCode());

          if (wrapper.getException() != null) {
            processODataRequestException(wrapper.getException());
            System.exit(NOT_OK);
          }

          setServerODataHeaderVersion(wrapper.getServerVersion());
          xmlResponseData.set(wrapper.getResponseData());
          xmlMetadata.set(Commander.deserializeXMLMetadata(xmlResponseData.get(), getCommander().getClient()));
        } else {
          LOG.error(getDefaultErrorMessage("could not create metadata URI from given requestUri:", request.getRequestUrl()));
          System.exit(NOT_OK);
        }
      } finally {
        haveMetadataBeenRequested.set(true);
      }
    }
    return xmlMetadata.get();
  }

  public XMLMetadata getXMLMetadata() {
    return xmlMetadata.get();
  }

  /**
   * XML Metadata setter
   *
   * @param xmlMetadata the XML metadata to set
   */
  public void setXMLMetadata(XMLMetadata xmlMetadata) {
    this.xmlMetadata.set(xmlMetadata);
  }

  /**
   * Commander getter
   *
   * @return the local Commander instance
   */
  public Commander getCommander() {
    if (commander.get() == null) initialize();
    return commander.get();
  }

  /**
   * Commander setter
   *
   * @param commander the Commander to set
   */
  public void setCommander(Commander commander) {
    this.commander.set(commander);
  }

  /**
   * OData response getter
   *
   * @return the local OData response
   */
  public ODataRawResponse getODataRawResponse() {
    return oDataRawResponse.get();
  }

  /**
   * OData response setter
   *
   * @param oDataRawResponse the OData response to set
   */
  public void setODataRawResponse(ODataRawResponse oDataRawResponse) {
    this.oDataRawResponse.set(oDataRawResponse);
  }

  /**
   * Request getter
   *
   * @return the local Request instance
   */
  public Request getRequest() {
    return request.get();
  }

  /**
   * Request setter
   *
   * @param request the Request to set
   */
  public void setRequest(Request request) {
    this.request.set(request);
  }

  /**
   * Request setter
   *
   * @param requestId the Request Id of the Request
   */
  public void setRequest(String requestId) {
    setRequest(getSettings().getRequest(requestId));
  }

  /**
   * Request URI getter
   *
   * @return the URI of the current request
   */
  public URI getRequestUri() {
    return requestUri.get();
  }

  /**
   * Request URI setter
   *
   * @param requestUri the URI of the current request
   */
  public void setRequestUri(URI requestUri) {
    this.requestUri.set(requestUri);
  }

  /**
   * Response code getter
   *
   * @return the Response code of the last request
   */
  public Integer getResponseCode() {
    return responseCode.get();
  }

  /**
   * Response code setter
   *
   * @param responseCode the response code to set
   */
  public void setResponseCode(Integer responseCode) {
    this.responseCode.set(responseCode);
  }

  /**
   * Response Data getter
   *
   * @return the local response data
   */
  public String getResponseData() {
    return responseData.get();
  }

  /**
   * Response code setter
   *
   * @param responseData the response data to set
   */
  public void setResponseData(String responseData) {
    this.responseData.set(responseData);
  }

  /**
   * Initial response data getter
   *
   * @return the local response data
   */
  public String getInitialResponseData() {
    return initialResponseData.get();
  }

  /**
   * Initial response data setter
   *
   * @param initialResponseData the response data to set
   */
  public void setInitialResponseData(String initialResponseData) {
    this.initialResponseData.set(initialResponseData);
  }

  public ODataRawRequest getRawRequest() {
    return rawRequest.get();
  }

  public void setRawRequest(ODataRawRequest oDataRawRequest) {
    this.rawRequest.set(oDataRawRequest);
  }

  public ODataClientErrorException getODataClientErrorException() {
    return oDataClientErrorException.get();
  }

  public void setODataClientErrorException(ODataClientErrorException oDataClientErrorException) {
    this.oDataClientErrorException.set(oDataClientErrorException);
  }

  public ODataServerErrorException getODataServerErrorException() {
    return oDataServerErrorException.get();
  }

  public void setODataServerErrorException(ODataServerErrorException oDataServerErrorException) {
    this.oDataServerErrorException.set(oDataServerErrorException);
  }

  public String getServerODataHeaderVersion() {
    return this.serverODataHeaderVersion.get();
  }

  public void setServerODataHeaderVersion(String serverODataHeaderVersion) {
    this.serverODataHeaderVersion.set(serverODataHeaderVersion);
  }

  public ODataEntitySetRequest<ClientEntitySet> getClientEntitySetRequest() {
    return clientEntitySetRequest.get();
  }

  public void setClientEntitySetRequest(ODataEntitySetRequest<ClientEntitySet> clientEntitySetRequest) {
    this.clientEntitySetRequest.set(clientEntitySetRequest);
  }

  public ODataRetrieveResponse<ClientEntitySet> getClientEntitySetResponse() {
    return clientEntitySetResponse.get();
  }

  public void setClientEntitySetResponse(ODataRetrieveResponse<ClientEntitySet> clientEntitySetResponse) {
    this.clientEntitySetResponse.set(clientEntitySetResponse);
  }

  public ClientEntitySet getClientEntitySet() {
    return clientEntitySet.get();
  }

  public void setClientEntitySet(ClientEntitySet clientEntitySet) {
    this.clientEntitySet.set(clientEntitySet);
  }

  public String getServiceRoot() {
    return serviceRoot.get();
  }

  public void setServiceRoot(String serviceRoot) {
    this.serviceRoot.set(serviceRoot);
  }

  public String getAuthToken() {
    return bearerToken.get();
  }

  public void setBearerToken(String bearerToken) {
    this.bearerToken.set(bearerToken);
  }

  public String getClientId() {
    return clientId.get();
  }

  public void setClientId(String clientId) {
    this.clientId.set(clientId);
  }

  public String getClientSecret() {
    return clientSecret.get();
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret.set(clientSecret);
  }

  public void setAuthorizationUri(String authorizationUri) {
    this.authorizationUri.set(authorizationUri);
  }

  public String getTokenUri() {
    return tokenUri.get();
  }

  public void setTokenUri(String tokenUri) {
    this.tokenUri.set(tokenUri);
  }

  public void setRedirectUri(String redirectUri) {
    this.redirectUri.set(redirectUri);
  }

  public String getScope() {
    return scope.get();
  }

  public void setScope(String scope) {
    this.scope.set(scope);
  }

  public String getPathToMetadata() {
    return pathToMetadata.get();
  }

  public void setPathToMetadata(String path) {
    this.pathToMetadata.set(path);
  }

  public String getPathToRESOScript() {
    return pathToRESOScript.get();
  }

  public void setPathToRESOScript(String pathToRESOScript) {
    this.pathToRESOScript.set(pathToRESOScript);
  }

  private void processODataRequestException(Exception exception, boolean bubble) throws Exception {
    if (exception instanceof ODataClientErrorException)
      processODataRequestException((ODataClientErrorException) exception);
    else if (exception instanceof ODataServerErrorException)
      processODataRequestException(((ODataServerErrorException) exception));
    else LOG.error(getDefaultErrorMessage(exception));

    if (bubble) throw exception;
  }

  private void processODataRequestException(Exception exception) throws Exception {
    processODataRequestException(exception, true);
  }

  private void processODataRequestException(ODataClientErrorException exception) {
    LOG.debug("ODataClientErrorException caught. Check tests for asserted conditions...");
    LOG.debug(exception);
    setODataClientErrorException(exception);
    setServerODataHeaderVersion(TestUtils.getHeaderData(HEADER_ODATA_VERSION, Arrays.asList(exception.getHeaderInfo())));
    setResponseCode(exception.getStatusLine().getStatusCode());
  }

  private void processODataRequestException(ODataServerErrorException exception) {
    LOG.debug("ODataServerErrorException thrown in executeGetRequest. Check tests for asserted conditions...");
    //TODO: look for better ways to do this in Olingo or open PR
    if (exception.getMessage().contains(Integer.toString(HttpStatus.SC_NOT_IMPLEMENTED))) {
      setResponseCode(HttpStatus.SC_NOT_IMPLEMENTED);
    }
    setODataServerErrorException(exception);
  }


  public boolean getIsValidXMLMetadata() {
    return isValidXMLMetadata.get();
  }

  private void setIsValidXMLMetadata(boolean isValid) {
    isValidXMLMetadata.set(isValid);
  }

  public boolean getIsValidEdm() {
    return isValidEdm.get();
  }

  private void setIsValidEdm(boolean isValid) {
    isValidEdm.set(isValid);
  }

  public boolean getIsValidXMLMetadataXML() {
    return isValidXMLMetadataXML.get();
  }

  private void setIsValidXMLMetadataXML(boolean isValid) {
    isValidXMLMetadataXML.set(isValid);
  }

  public boolean getHaveMetadataBeenRequested() {
    return haveMetadataBeenRequested.get();
  }

  public boolean getShowResponses() {
    return showResponses.get();
  }

  public void setShowResponses(Boolean value) {
    showResponses.set(value);
  }

  public boolean getIsValidDataSystem() {
    return isDataSystemValid.get();
  }

  public final boolean hasValidMetadata() {
    return xmlMetadata.get() != null && getIsValidXMLMetadata()
        && xmlResponseData.get() != null && getIsValidXMLMetadataXML()
        && edm.get() != null && getIsValidEdm();
  }

  public final WebAPITestContainer validateMetadata() {
    try {
      if (!haveMetadataBeenRequested.get()) fetchXMLMetadata();
      assertNotNull(getDefaultErrorMessage("no XML response data found!"), getXMLResponseData());
      validateXMLMetadataXML();

      setXMLMetadata(Commander.deserializeXMLMetadata(getXMLResponseData(), getCommander().getClient()));
      validateXMLMetadata();

      setEdm(Commander.deserializeEdm(getXMLResponseData(), getCommander().getClient()));
      validateEdm();

      return this;
    } catch (Exception ex) {
      LOG.error(getDefaultErrorMessage(ex.toString()));
      System.exit(NOT_OK);
    }
    return this;
  }

  public WebAPITestContainer validateDataSystem() {
    isDataSystemValid.set(false);
    if (getResponseData() != null) {
      try {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        InputStream is = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream(DATA_SYSTEM_JSON_4_SCHEMA);
        JsonSchema schema = factory.getSchema(is);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(getResponseData());

        if (node.findPath(JSON_VALUE_PATH).size() > 0) {
          schemaValidationErrors.set(schema.validate(node));

          if (getSchemaValidationErrors().size() > 0) {
            LOG.error("ERROR: JSON Schema validation errors were found!");
            getSchemaValidationErrors().forEach(LOG::error);
          } else {
            LOG.info("DataSystem response matches reference schema!");
            isDataSystemValid.set(true);
          }
        }
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    }
    return this;
  }

  public void validateEdm() {
    try {
      assertNotNull("ERROR: No Entity Data Model (Edm) Exists!", getEdm());
      boolean isValid = getCommander().validateMetadata(getEdm());
      setIsValidEdm(isValid);
      LOG.info("Edm Metadata is " + (isValid ? "valid" : "invalid") + "!");
    } catch (Exception ex) {
      fail("ERROR: could not validate Edm Metadata!\n" + ex.toString());
    }
  }

  public void validateXMLMetadata() {
    try {
      //note that this will lazy-load XML metadata when it's not present
      assertNotNull(getDefaultErrorMessage("XML metadata was not found!"), fetchXMLMetadata());
      boolean isValid = getCommander().validateMetadata(fetchXMLMetadata());
      setIsValidXMLMetadata(isValid);
      LOG.info("XML Metadata is " + (isValid ? "valid" : "invalid") + "!");
    } catch (Exception ex) {
      fail("ERROR: could not validate XML Metadata!\n" + ex.toString());
    }
  }

  public WebAPITestContainer validateXMLMetadataXML() {
    LOG.info("Validating XML Metadata response to ensure it's valid XML and matches OASIS OData XSDs...");
    LOG.info("See: https://docs.oasis-open.org/odata/odata/v4.0/errata03/os/complete/schemas/");
    assertNotNull("XML response data were not found in the test container! Please ensure the XML Metadata request succeeded.",
        getXMLResponseData());

    try {
      boolean isValid = Commander.validateXML(getXMLResponseData());
      setIsValidXMLMetadataXML(isValid);
      LOG.info("XMLMetadata string is " + (isValid ? "valid" : "invalid") + " XML!");
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
    return this;
  }

  public WebAPITestContainer validateJSON() {
    assertNotNull(getDefaultErrorMessage("JSON response data were not found in the test container! Please ensure your request succeeded.",
        getResponseData()));

    try {
      assertTrue("ERROR: invalid JSON response!", TestUtils.isValidJson(getResponseData()));
      LOG.info("Response is valid JSON!");

      if (getShowResponses())
        LOG.info("Response: " + new ObjectMapper().readTree(getResponseData()).toPrettyString());
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
    return this;
  }

  public Set<ValidationMessage> getSchemaValidationErrors() {
    return schemaValidationErrors.get();
  }

  public Boolean getShouldValidateMetadata() {
    return shouldValidateMetadata.get();
  }

  public void setShouldValidateMetadata(boolean value) {
    shouldValidateMetadata.set(value);
  }

  public boolean getIsInitialized() {
    return isInitialized.get();
  }

  public void setIsInitialized(boolean value) {
    isInitialized.set(value);
  }

  public static final class ODATA_QUERY_PARAMS {
    private static final String format = DOLLAR_SIGN + "%s";

    //TODO: add additional items as needed, and see if there's a lib for this in Olingo
    public static final String
        COUNT = String.format(format, QueryOption.COUNT),
        EXPAND = String.format(format, QueryOption.EXPAND),
        FILTER = String.format(format, QueryOption.FILTER),
        ORDERBY = String.format(format, QueryOption.ORDERBY),
        SELECT = String.format(format, QueryOption.SELECT),
        SEARCH = String.format(format, QueryOption.SEARCH),
        SKIP = String.format(format, QueryOption.SKIP),
        TOP = String.format(format, QueryOption.TOP);
  }
}