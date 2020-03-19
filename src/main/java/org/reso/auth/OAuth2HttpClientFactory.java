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
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
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

  private static final Logger LOG = LogManager.getLogger(OAuth2HttpClientFactory.class);

  HttpClientConnectionManager connectionManager = null;

  private String clientId;
  private String clientSecret;
  private String tokenUri;
  private String scope;
  private String accessToken;

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
        StringBuffer buffer = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
          buffer.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(buffer.toString());

        //Read JSON response and print
        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseData = mapper.readTree(buffer.toString());

        String token = responseData.get("access_token").textValue();
        assert(token != null);

        accessToken = token;

        response.close();
      }

    } catch (Exception e) {
      throw new OAuth2Exception(e);
    }
  }

  @Override
  public HttpClient create(HttpMethod method, URI uri) {
    assert(accessToken != null);

    BasicHeader authHeader = new BasicHeader("Authorization", "Bearer " + accessToken);
    List<Header> headers = new ArrayList<>();
    headers.add(authHeader);

    connectionManager = new BasicHttpClientConnectionManager();

    return HttpClientBuilder.create()
        .setUserAgent(USER_AGENT)
        .setDefaultHeaders(headers)
        .setConnectionManager(connectionManager)
        .build();
  }

  @Override
  public void close(HttpClient httpClient) {
    try {
      connectionManager.shutdown();
    } catch (Exception ex) {
      LOG.error(ex.toString());
    }
  }
}