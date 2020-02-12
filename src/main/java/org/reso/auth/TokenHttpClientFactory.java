package org.reso.auth;

import org.apache.http.client.HttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.olingo.client.core.http.DefaultHttpClientFactory;
import org.apache.olingo.commons.api.http.HttpMethod;

import java.net.URI;

/**
 * Extends DefaultHttpClientFactory with one that can accept tokens passed in to make requests.
 */
public class TokenHttpClientFactory extends DefaultHttpClientFactory {
  String token;

  private static final Logger LOG = LogManager.getLogger(TokenHttpClientFactory.class);

  /**
   * Constructor for use with tokens.
   *
   * @param token the token to be used for server requests.
   */
  public TokenHttpClientFactory(String token) {
    this.token = token;
  }

  @SuppressWarnings("deprecation")
  @Override
  public org.apache.http.impl.client.DefaultHttpClient create(final HttpMethod method, final URI uri) {
    final org.apache.http.impl.client.DefaultHttpClient client = new org.apache.http.impl.client.DefaultHttpClient();

    client.getParams().setParameter(HTTP.USER_AGENT, USER_AGENT);

    // add auth token using the vendor's bearer token passed from the command line.
    // TODO: handle refresh tokens
    client.addRequestInterceptor((request, context) -> request.addHeader("Authorization", "Bearer " + token));

    return client;
  }

  @SuppressWarnings("deprecation")
  @Override
  public void close(final HttpClient httpClient) {
    try {
      httpClient.getConnectionManager().shutdown();
    } catch (Exception ex) {
      LOG.error(ex.toString());
    }
  }
}
