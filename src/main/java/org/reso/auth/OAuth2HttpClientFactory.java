package org.reso.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.core.http.AbstractOAuth2HttpClientFactory;
import org.apache.olingo.client.core.http.OAuth2Exception;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class OAuth2HttpClientFactory extends AbstractOAuth2HttpClientFactory {

  private static final Logger LOG = LogManager.getLogger(OAuth2HttpClientFactory.class);

  private String clientId;
  private String clientSecret;
  private String redirectUri;
  private String tokenUri;
  private String scope;
  private ObjectNode token;

  public OAuth2HttpClientFactory(
      String clientId,
      String clientSecret,
      String authorizationUri,
      String tokenUri,
      String redirectUri,
      String scope) {

    super(URI.create(tokenUri), URI.create(authorizationUri));
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.redirectUri = redirectUri;
    this.tokenUri = tokenUri;
    this.scope = scope;
  }

  @Override
  protected boolean isInited() throws OAuth2Exception {
    return token != null;
  }

  private void fetchAccessToken(final HttpClient httpClient, final List<BasicNameValuePair> data) {
    token = null;

    try {
      LOG.debug("Fetching access token...");
      final HttpPost post = new HttpPost(this.tokenUri);
      post.setEntity(new UrlEncodedFormEntity(data, "UTF-8"));
      final HttpResponse response = httpClient.execute(post);
      LOG.debug("Executed httpClient...");

      InputStream tokenResponse = response.getEntity().getContent();
      token = (ObjectNode) new ObjectMapper().readTree(tokenResponse);
      LOG.debug("Fetched token: " + token.toString());
      tokenResponse.close();
    } catch (Exception e) {
      throw new OAuth2Exception(e);
    }
  }

  @Override
  protected void init() throws OAuth2Exception {
    @SuppressWarnings("deprecation")
    final org.apache.http.impl.client.DefaultHttpClient httpClient = wrapped.create(null, null);
    String code = null;
    final List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
    data.add(new BasicNameValuePair("grant_type", "client_credentials"));
    data.add(new BasicNameValuePair("client_id", clientId));
    data.add(new BasicNameValuePair("client_secret", clientSecret));
    data.add(new BasicNameValuePair("scope", scope));

    fetchAccessToken(httpClient, data);

    if (token == null) {
      throw new OAuth2Exception("No OAuth2 access token");
    }
  }

  @SuppressWarnings("deprecation")
  @Override
  protected void accessToken(final org.apache.http.impl.client.DefaultHttpClient client) throws OAuth2Exception {
    client.addRequestInterceptor((request, context) -> {
      request.removeHeaders(HttpHeaders.AUTHORIZATION);
      request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token.get("access_token").asText());
    });
  }

  @SuppressWarnings("deprecation")
  @Override
  protected void refreshToken(final org.apache.http.impl.client.DefaultHttpClient client) throws OAuth2Exception {
    final List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
    data.add(new BasicNameValuePair("grant_type", "refresh_token"));
    data.add(new BasicNameValuePair("refresh_token", token.get("refresh_token").asText()));

    fetchAccessToken(wrapped.create(null, null), data);

    if (token == null) {
      throw new OAuth2Exception("No OAuth2 refresh token");
    }
  }

}