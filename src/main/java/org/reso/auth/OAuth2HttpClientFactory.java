package org.reso.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.core.http.AbstractHttpClientFactory;
import org.apache.olingo.client.core.http.OAuth2Exception;
import org.apache.olingo.commons.api.http.HttpMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class OAuth2HttpClientFactory extends AbstractHttpClientFactory {

  final static String EXPIRES_IN = "expires_in";
  final static String ACCESS_TOKEN = "access_token";
  private static final Logger LOG = LogManager.getLogger(OAuth2HttpClientFactory.class);
  HttpClientConnectionManager connectionManager = null;
  private final String clientId;
  private final String clientSecret;
  private final String tokenUri;
  private final String scope;
  private String accessToken;
  private Integer expiresIn;

  /**
   * Creates an OAuth2 client for making HTTP requests using Client Credentials
   *
   * @param clientId     the client Id of the consumer
   * @param clientSecret the client secret of the consumer
   * @param tokenUri     the token uri of the server to connect to
   * @param scope        (optional) scope
   */
  public OAuth2HttpClientFactory(String clientId, String clientSecret, String tokenUri, String scope) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.tokenUri = tokenUri;
    this.scope = scope;

    fetchAccessToken();
  }

  private void fetchAccessToken() {
    accessToken = null;
    List<NameValuePair> params = new ArrayList<>();

    try {
      LOG.debug("Fetching access token...");
      final HttpPost post = new HttpPost(tokenUri);

      params.add(new BasicNameValuePair("grant_type", "client_credentials"));
      params.add(new BasicNameValuePair("client_id", clientId));
      params.add(new BasicNameValuePair("client_secret", clientSecret));

      if (scope != null && scope.length() > 0) {
        params.add(new BasicNameValuePair("scope", scope));
      }

      post.setEntity(new UrlEncodedFormEntity(params));
      CloseableHttpResponse response = HttpClientBuilder.create().build().execute(post);

      LOG.debug("Executed httpClient...");

      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        String inputLine;
        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder buffer = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
          buffer.append(inputLine);
        }
        in.close();

        //Read JSON response and print
        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseData = mapper.readTree(buffer.toString());

        if (responseData.get(ACCESS_TOKEN) != null) {
          accessToken = responseData.get(ACCESS_TOKEN).asText();
        }
        assert (accessToken != null);

        if (responseData.get(EXPIRES_IN) != null) {
          expiresIn = responseData.get(EXPIRES_IN).asInt();
        }
      }
      response.close();
    } catch (Exception e) {
      throw new OAuth2Exception(e);
    }
  }

  @Override
  public CloseableHttpClient create(HttpMethod method, URI uri) {
    assert (accessToken != null);

    BasicHeader authHeader = new BasicHeader("Authorization", "Bearer " + accessToken);
    List<Header> headers = new ArrayList<>();
    headers.add(authHeader);

    return HttpClientBuilder.create()
        .setUserAgent(USER_AGENT)
        .setDefaultHeaders(headers)
        .setConnectionManager(connectionManager)
        .build();
  }

  @Override
  public void close(HttpClient httpClient) {
    try {
      if (connectionManager != null) connectionManager.shutdown();
    } catch (Exception ex) {
      LOG.error(ex.toString());
    }
  }
}