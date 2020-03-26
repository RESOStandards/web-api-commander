package org.reso.commander.certfication.containers;

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
import org.apache.olingo.commons.api.edm.Edm;
import org.reso.commander.Commander;
import org.reso.commander.TestUtils;
import org.reso.models.ClientSettings;
import org.reso.models.Request;
import org.reso.models.Settings;

import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.fail;
import static org.reso.commander.TestUtils.HEADER_ODATA_VERSION;

/**
 * Encapsulates Commander Requests and Responses during runtime
 */
public final class WebApiTestContainer implements TestContainer {
  private static final Logger LOG = LogManager.getLogger(WebApiTestContainer.class);

  private AtomicReference<Commander> commander = new AtomicReference<>();
  private AtomicReference<ODataRawResponse> oDataRawResponse = new AtomicReference<>();
  private AtomicReference<Request> request = new AtomicReference<>();
  private AtomicReference<URI> requestUri = new AtomicReference<>();
  private AtomicReference<Integer> responseCode = new AtomicReference<>();
  private AtomicReference<String> responseData = new AtomicReference<>();
  private AtomicReference<String> initialResponseData = new AtomicReference<>(); //used if two result sets need to be compared
  private AtomicReference<ODataRawRequest> rawRequest = new AtomicReference<>();
  private AtomicReference<ODataClientErrorException> oDataClientErrorException = new AtomicReference<>();
  private AtomicReference<ODataServerErrorException> oDataServerErrorException = new AtomicReference<>();
  private AtomicReference<String> serverODataHeaderVersion = new AtomicReference<>();
  private AtomicReference<Boolean> testAppliesToServerODataHeaderVersion = new AtomicReference<>();
  private AtomicReference<ODataEntitySetRequest<ClientEntitySet>> clientEntitySetRequest = new AtomicReference<>();
  private AtomicReference<ODataRetrieveResponse<ClientEntitySet>> clientEntitySetResponse = new AtomicReference<>();
  private AtomicReference<ClientEntitySet> clientEntitySet = new AtomicReference<>();
  private AtomicReference<XMLMetadata> xmlMetadata = new AtomicReference<>();
  private AtomicReference<Edm> edm = new AtomicReference<>();
  private AtomicReference<Settings> settings = new AtomicReference<>();
  private AtomicReference<String> serviceRoot = new AtomicReference<>();
  private AtomicReference<String> bearerToken = new AtomicReference<>();
  private AtomicReference<String> clientId = new AtomicReference<>();
  private AtomicReference<String> clientSecret = new AtomicReference<>();
  private AtomicReference<String> authorizationUri = new AtomicReference<>();
  private AtomicReference<String> tokenUri = new AtomicReference<>();
  private AtomicReference<String> redirectUri = new AtomicReference<>();
  private AtomicReference<String> scope = new AtomicReference<>();
  private AtomicReference<String> pathToRESOScript = new AtomicReference<>();


  public void initialize() {
    setServiceRoot(getSettings().getClientSettings().get(ClientSettings.WEB_API_URI));

    //TODO: add base64 un-encode when applicable
    setBearerToken(getSettings().getClientSettings().get(ClientSettings.BEARER_TOKEN));
    if (getBearerToken() != null && getBearerToken().length() > 0) {
      LOG.info("Bearer token loaded... first 4 characters: " + getBearerToken().substring(0, 4));
    }

    setClientId(getSettings().getClientSettings().get(ClientSettings.CLIENT_IDENTIFICATION));
    setClientSecret(getSettings().getClientSettings().get(ClientSettings.CLIENT_SECRET));
    setAuthorizationUri(getSettings().getClientSettings().get(ClientSettings.AUTHORIZATION_URI));
    setTokenUri(getSettings().getClientSettings().get(ClientSettings.TOKEN_URI));
    setRedirectUri(getSettings().getClientSettings().get(ClientSettings.REDIRECT_URI));
    setScope(getSettings().getClientSettings().get(ClientSettings.CLIENT_SCOPE));

    LOG.info("Service root is: " + getServiceRoot());

    if (getCommander() == null) {
      //create Commander instance
      setCommander(new Commander.Builder()
          .clientId(getClientId())
          .clientSecret(getClientSecret())
          .tokenUri(getTokenUri())
          .scope(getScope())
          .serviceRoot(getServiceRoot())
          .bearerToken(getBearerToken())
          .useEdmEnabledClient(true)
          .build());
    }
  }

  /**
   * Resets the state of the test container
   */
  public void resetState() {
    oDataRawResponse.set(null);
    request.set(null);
    responseCode.set(null);
    responseData.set(null);
    initialResponseData.set(null);
    rawRequest.set(null);
    oDataClientErrorException.set(null);
    oDataServerErrorException.set(null);
    serverODataHeaderVersion.set(null);
    testAppliesToServerODataHeaderVersion.set(false);
  }

  /**
   * Executes HTTP GET request and sets the expected local variables in the WebApiTestContainer
   * Handles exceptions and sets response codes as well.
   */
  public void executePreparedGetRequest() {
    try {
      setRawRequest(getCommander().getClient().getRetrieveRequestFactory().getRawRequest(getRequestUri()));
      setODataRawResponse(getRawRequest().execute());
      setResponseData(TestUtils.convertInputStreamToString(getODataRawResponse().getRawResponse()));
      setServerODataHeaderVersion(getODataRawResponse().getHeader(HEADER_ODATA_VERSION).toString());
      setResponseCode(getODataRawResponse().getStatusCode());
      LOG.info("Request succeeded..." + getResponseData().getBytes().length + " bytes received.");
    } catch (ODataClientErrorException cex) {
      LOG.debug("ODataClientErrorException caught. Check tests for asserted conditions...");
      LOG.debug(cex);
      setODataClientErrorException(cex);
      setServerODataHeaderVersion(TestUtils.getHeaderData(HEADER_ODATA_VERSION, cex.getHeaderInfo()));
      setResponseCode(cex.getStatusLine().getStatusCode());
    } catch (ODataServerErrorException ode) {
      LOG.debug("ODataServerErrorException thrown in executeGetRequest. Check tests for asserted conditions...");
      //TODO: look for better ways to do this in Olingo or open PR
      if (ode.getMessage().contains(Integer.toString(HttpStatus.SC_NOT_IMPLEMENTED))) {
        setResponseCode(HttpStatus.SC_NOT_IMPLEMENTED);
      }
      setODataServerErrorException(ode);
    } catch (Exception ex) {
      fail("ERROR: unhandled Exception in executeGetRequest()!\n" + ex.toString());
    }
  }

  public Settings getSettings() {
    return settings.get();
  }

  public void setSettings(Settings settings) {
    this.settings.set(settings);
  }

  /**
   * Gets server metadata in Edm format.
   *
   * @return
   * @implNote the data in this item are cached in the commander once fetched
   */
  public Edm getEdm() {
    if (edm.get() == null) {
      ODataRetrieveResponse<Edm> response = getCommander().prepareEdmMetadataRequest().execute();
      responseCode.set(response.getStatusCode());
      edm.set(response.getBody());
    }
    return edm.get();
  }

  /**
   * Gets server metadata in XMLMetadata format.
   *
   * @return XMLMetadata representation of the server metadata.
   * @implNote the data in this item are cached in the commander once fetched
   */
  public XMLMetadata getXMLMetadata() {
    if (xmlMetadata.get() == null) {
      ODataRetrieveResponse<XMLMetadata> response = getCommander().prepareXMLMetadataRequest().execute();
      responseCode.set(response.getStatusCode());
      xmlMetadata.set(response.getBody());
    }
    return xmlMetadata.get();
  }

  public Commander getCommander() {
    return commander.get();
  }

  public void setCommander(Commander commander) {
    this.commander.set(commander);
  }

  public ODataRawResponse getODataRawResponse() {
    return oDataRawResponse.get();
  }

  public void setODataRawResponse(ODataRawResponse oDataRawResponse) {
    this.oDataRawResponse.set(oDataRawResponse);
  }

  public Request getRequest() {
    return request.get();
  }

  public void setRequest(Request request) {
    this.request.set(request);
  }

  public URI getRequestUri() {
    return requestUri.get();
  }

  public void setRequestUri(URI requestUri) {
    this.requestUri.set(requestUri);
  }

  public Integer getResponseCode() {
    return responseCode.get();
  }

  public void setResponseCode(Integer responseCode) {
    this.responseCode.set(responseCode);
  }

  public String getResponseData() {
    return responseData.get();
  }

  public void setResponseData(String responseData) {
    this.responseData.set(responseData);
  }

  public String getInitialResponseData() {
    return initialResponseData.get();
  }

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
    return serverODataHeaderVersion.get();
  }

  public void setServerODataHeaderVersion(String serverODataHeaderVersion) {
    this.serverODataHeaderVersion.set(serverODataHeaderVersion);
  }

  public Boolean getTestAppliesToServerODataHeaderVersion() {
    return testAppliesToServerODataHeaderVersion.get();
  }

  public void setTestAppliesToServerODataHeaderVersion(Boolean testAppliesToServerODataHeaderVersion) {
    this.testAppliesToServerODataHeaderVersion.set(testAppliesToServerODataHeaderVersion);
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

  public String getBearerToken() {
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

  public String getAuthorizationUri() {
    return authorizationUri.get();
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

  public String getRedirectUri() {
    return redirectUri.get();
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

  public String getPathToRESOScript() {
    return pathToRESOScript.get();
  }

  public void setPathToRESOScript(String pathToRESOScript) {
    this.pathToRESOScript.set(pathToRESOScript);
  }
}