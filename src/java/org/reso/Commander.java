package org.reso;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.XMLMetadataRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.edm.xml.XMLMetadata;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.client.core.domain.ClientEntitySetImpl;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.format.ContentType;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * URI
 * Most of the work done by the WebAPI commander is done by this class. Its public methods are, therefore,
 * the ones the Client programmer is expected to use.
 */
public class Commander {
  private ODataClient client;
  private String serviceRoot;
  boolean useEdmEnabledClient;

  private static final Logger log = Logger.getLogger(Commander.class);

  final static int NOT_OK = 1;

  /**
   * Creates a Commander instance that uses the given Bearer token for authentication and allows the Client
   * to specify whether to use an EdmEnabledClient or normal OData client.
   * <p>
   * NOTE: serviceRoot can sometimes be null, but is required if useEdmEnabledClient is true.
   * A check has been added for this condition.
   * *
   *
   * @param serviceRoot         the service root of the WebAPI server.
   * @param bearerToken         the bearer token to use to authenticate with the given serviceRoot.
   * @param useEdmEnabledClient if true, the payload is checked against the Server's metadata for actions
   *                            that support it.
   */
  public Commander(String serviceRoot, String bearerToken, boolean useEdmEnabledClient) {
    this(serviceRoot, useEdmEnabledClient);
    client.getConfiguration().setHttpClientFactory(new TokenHttpClientFactory(bearerToken));
  }

  /**
   * Private constructor for internal use.
   * <p>
   * Creates a Commander instance that allows the caller to use an EdmEnabledClient,
   * meaning that all payloads will be verified against the metadata published at serviceRoot.
   *
   * @param serviceRoot the service root of the WebAPI server.
   */
  private Commander(String serviceRoot, boolean useEdmEnabledClient) {
    this.useEdmEnabledClient = useEdmEnabledClient;

    this.serviceRoot = serviceRoot;
    log.debug("\nUsing EdmEnabledClient: " + useEdmEnabledClient);
    if (useEdmEnabledClient) {
      client = ODataClientFactory.getEdmEnabledClient(serviceRoot);
    } else {
      client = ODataClientFactory.getClient();
    }
  }

  /**
   * Gets server metadata in EDMX format.
   * <p>
   * TODO: add optional validation upon fetch
   *
   * @return Edm representation of the server metadata.
   */
  public Edm getMetadata(String outputFileName) {
    XMLMetadataRequest request = client.getRetrieveRequestFactory().getXMLMetadataRequest(serviceRoot);

    try {
      log.info("Fetching Metadata from " + serviceRoot + "...");
      byte[] buffer = IOUtils.toByteArray(request.rawExecute());
      log.info("Transfer complete! Bytes received: " + buffer.length);

      // copy response to given output file
      FileUtils.writeByteArrayToFile(new File(outputFileName), buffer);
      log.info("Wrote metadata to: " + outputFileName);

      // read metadata from binary to ensure it deserializes properly and return
      return client.getReader().readMetadata(new ByteArrayInputStream(buffer));
    } catch (Exception ex) {
      log.error(ex.toString());
      System.exit(NOT_OK);
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
    try {
      // call the probably-useless metadata validator. can't hurt though
      // SEE: https://github.com/apache/olingo-odata4/blob/master/lib/client-core/src/main/java/org/apache/olingo/client/core/serialization/ODataMetadataValidationImpl.java#L77-L116
      client.metadataValidation().validateMetadata(metadata);

      // also check whether metadata contains a valid service document in OData v4 format
      return client.metadataValidation().isServiceDocument(metadata)
          && client.metadataValidation().isV4Metadata(metadata);
    } catch (NullPointerException nex) {
      log.error("ERROR: Validation Failed! Null pointer exception while trying to validate metadata.");
    } catch (Exception ex) {
      log.error("ERROR: Validation Failed! General error occurred when validating metadata.\n" + ex.getMessage());
      if (ex.getCause() != null) {
        log.error("Caused by: " + ex.getCause().getMessage());
      }
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
      XMLMetadata metadata =
          client.getDeserializer(ContentType.APPLICATION_XML).toMetadata(new FileInputStream(pathToEdmx));

      return validateMetadata(metadata);

    } catch (Exception ex) {
      log.error("Error occurred while validating metadata.\nPath was:" + pathToEdmx);
      log.error(ex.getMessage());
    }
    return false;
  }

  /**
   * Prepares a URI for an OData request
   */
  Function<String, URI> prepareURI = uriString -> {
    try {
        URL url = new URL(uriString);
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme(url.getProtocol());

        uriBuilder.setHost(url.getHost());
        if (url.getPath() != null) uriBuilder.setPath(url.getPath());
        if (url.getQuery() != null) uriBuilder.setQuery(url.getQuery());
        return uriBuilder.build();
    } catch (Exception ex) {
      log.error("ERROR in prepareURI: " + ex.toString());
    }
    return null;
  };

  /**
   * Executes a get request on URI and saves raw response to outputFilePath.
   * Intended to be used mostly for testing.
   *
   * @param requestUri     the URI to make the request against
   * @param outputFilePath the outputFilePath to write the response to
   */
  public void saveRawGetRequest(String requestUri, String outputFilePath) {
    try {

      log.debug("RequestURI: " + requestUri);

      FileUtils.copyInputStreamToFile(
          client.getRetrieveRequestFactory().getRawRequest(prepareURI.apply(requestUri)).rawExecute(), new File(outputFilePath));

      log.info("Request complete... Response written to file: " + outputFilePath);

    } catch (Exception ex) {
      log.error("ERROR: exception occurred in writeRawResponse. " + ex.getMessage());
      System.exit(NOT_OK);
    }
  }

  public ClientEntitySet getEntitySet(String requestUri, Integer limit) {
    Function<Integer, Void> integerVoidFunction = num -> null;
    return getEntitySet(requestUri, limit, integerVoidFunction);
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
        URIBuilder uriBuilder = new URIBuilder(prepareURI.apply(requestUri));
        if (skip != null && skip > 0) uriBuilder.addParameter("$skip", skip.toString());

        URI uri = uriBuilder.build();
        log.debug("URI created: " + uri.toString());

        return uri;
      } catch (Exception ex) {
        log.error("ERROR: " + ex.toString());
      }
      return null;
    };

    try {
      URI uri = buildSkipUri.apply(null);

      log.info("Fetching results from: " + requestUri);

      do {
        entitySetResponse = client.getRetrieveRequestFactory().getEntitySetRequest(uri).execute();
        entities.addAll(entitySetResponse.getBody().getEntities());

        log.debug(limit > 0 ? Math.min(entities.size(), limit) : entities.size());

        //some of the next links don't currently work, so we can't use getNext() reliably.
        //we can, however, use the results of what we've downloaded previously to inform the skip.
        uri = buildSkipUri.apply(entities.size());

        updateStatusFunction.apply(entities.size());

      } while (entitySetResponse.getStatusCode() == HttpStatus.SC_OK && entitySetResponse.getBody().getNext() != null
          && (limit == -1 || entities.size() < limit));

    } catch (Exception ex) {
      //NOTE: sometimes a bad skip link in the payload can cause exceptions...the Olingo library validates the responses.
      log.error("ERROR: getEntitySet could not continue. " + ex.getCause());
      System.exit(NOT_OK);
    } finally {
      //trim results size to requested limit
      results.getEntities().addAll(entities.subList(0, limit > 0 ? Math.min(limit, entities.size()) : entities.size()));
      results.setCount(entitySetResponse.getBody().getCount());

      log.info("\nTransfer Complete!");
      return results;
    }
  }

  /**
   * Converts metadata in EDMX format to metadata in Swagger 2.0 format.
   * Converted file will have the same name as the input file, with .swagger.json appended to the name.
   *
   * @param pathToEDMX the metadata file to convert.
   */
  public void convertMetadata(String pathToEDMX) {
    final String FILENAME_EXTENSION = ".swagger.json";
    final String XSLT_FILENAME = "./V4-CSDL-to-OpenAPI.xslt";

    if (validateMetadata(pathToEDMX)) {
      try {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(new File(XSLT_FILENAME)));

        Source text = new StreamSource(new File(pathToEDMX));
        transformer.transform(text, new StreamResult(new File(pathToEDMX + FILENAME_EXTENSION)));

      } catch (Exception ex) {
        log.error("ERROR: convertMetadata failed. " + ex.toString());
        System.exit(NOT_OK);
      }
    } else {
      log.error("ERROR: invalid metadata! Please ensure metadata is valid using validateMetadata() before proceeding.");
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
      log.info("Saving " + entitySet.getEntities().size() + " item(s) to " + outputFilePath);
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
   * Builder pattern implemented for creating Commander instances
   */
  public static class Builder {
    String serviceRoot;
    String bearerToken;
    boolean useEdmEnabledClient;

    public Builder() {
      useEdmEnabledClient = false;
    }

    public Builder serviceRoot(String serviceRoot) {
      this.serviceRoot = serviceRoot;
      return this;
    }

    public Builder bearerToken(String bearerToken) {
      this.bearerToken = bearerToken;
      return this;
    }

    public Builder useEdmEnabledClient(boolean useEdmEnabledClient) {
      this.useEdmEnabledClient = useEdmEnabledClient;
      return this;
    }

    public Commander build() {
      return new Commander(serviceRoot, bearerToken, useEdmEnabledClient);
    }
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