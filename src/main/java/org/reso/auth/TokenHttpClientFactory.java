package org.reso.auth;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.core.http.AbstractHttpClientFactory;
import org.apache.olingo.commons.api.http.HttpMethod;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Extends AbstractHttpClientFactory with one that can accept tokens passed in to make requests.
 */
public class TokenHttpClientFactory extends AbstractHttpClientFactory {
  private static final Logger LOG = LogManager.getLogger(TokenHttpClientFactory.class);
  String token;
  HttpClientConnectionManager connectionManager = null;

  /**
   * Constructor for use with tokens.
   *
   * @param token the token to be used for server requests.
   */
  public TokenHttpClientFactory(String token) {
    this.token = token;
  }

  @Override
  public CloseableHttpClient create(final HttpMethod method, final URI uri) {
    BasicHeader authHeader = new BasicHeader("Authorization", "Bearer " + token);
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
  public void close(final HttpClient httpClient) {
    try {
      connectionManager.shutdown();
    } catch (Exception ex) {
      LOG.error(ex.toString());
    }
  }
}
