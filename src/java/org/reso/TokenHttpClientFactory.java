package org.reso;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.log4j.Logger;
import org.apache.olingo.client.core.http.DefaultHttpClientFactory;
import org.apache.olingo.commons.api.http.HttpMethod;

import java.net.URI;

/**
 * Extends DefaultHttpClientFactory with one that can accept tokens passed in to make requests.
 */
public class TokenHttpClientFactory extends DefaultHttpClientFactory {
  String token;

  private static final Logger log = Logger.getLogger(TokenHttpClientFactory.class);

  /**
   * Constructor for use with tokens.
   *
   * @param token the token to be used for server requests.
   */
  public TokenHttpClientFactory(String token) {
    this.token = token;
  }

  @Override
  public DefaultHttpClient create(final HttpMethod method, final URI uri) {
    final DefaultHttpClient client = new DefaultHttpClient();

    client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, USER_AGENT);

    // add auth token using the vendor's bearer token passed from the command line.
    // TODO: handle refresh tokens
    client.addRequestInterceptor((request, context) -> request.addHeader("Authorization", "Bearer " + token));

    return client;
  }

  @Override
  public void close(final HttpClient httpClient) {
    try {
      httpClient.getConnectionManager().shutdown();
    } catch (Exception ex) {
      log.error(ex.toString());
    }

  }
}
