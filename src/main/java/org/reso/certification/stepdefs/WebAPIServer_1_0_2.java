package org.reso.certification.stepdefs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java8.En;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.communication.request.retrieve.ODataRawRequest;
import org.apache.olingo.client.api.communication.response.ODataRawResponse;
import org.apache.olingo.client.api.edm.xml.XMLMetadata;
import org.apache.olingo.commons.api.format.ContentType;
import org.reso.commander.Commander;
import org.reso.models.ClientSettings;
import org.reso.models.Request;
import org.reso.models.Settings;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static io.restassured.path.json.JsonPath.from;
import static org.junit.Assert.*;

public class WebAPIServer_1_0_2 implements En {
  private static final Logger LOG = LogManager.getLogger(WebAPIServer_1_0_2.class);

  private Response response;
  private ValidatableResponse json;
  private RequestSpecification request;

  private static Settings settings;
  private String serviceRoot, bearerToken, clientId, clientSecret, authorizationUri, tokenUri, redirectUri, scope;
  private String pathToRESOScript;

  public WebAPIServer_1_0_2() {

    //TODO: split into separate test files and parallelize to remove the need for Atomic "globals"
    AtomicReference<Commander> commander = new AtomicReference<>();
    AtomicReference<ODataRawResponse> oDataRawResponse = new AtomicReference<>();
    AtomicReference<Request> request = new AtomicReference<>();
    AtomicReference<String> responseData = new AtomicReference<>();
    AtomicReference<String> initialResponseData = new AtomicReference<>(); //used if two result sets need to be compared
    AtomicReference<ODataRawRequest> rawRequest = new AtomicReference<>();

    //container to hold retrieved metadata for later comparisons
    AtomicReference<XMLMetadata> xmlMetadata = new AtomicReference<>();

    /*
     * Background
     */
    Given("^a RESOScript file was provided$", () -> {
      if (pathToRESOScript == null) {
        pathToRESOScript = System.getProperty("pathToRESOScript");
      }
      LOG.info("Using RESOScript: " + pathToRESOScript);
    });
    And("^Client Settings and Parameters were read from the file$", () -> {
      if (settings == null) {
        settings = Settings.loadFromRESOScript(new File(System.getProperty("pathToRESOScript")));
      }
      LOG.info("RESOScript loaded successfully!");
    });
    Given("^an OData client was successfully created from the given RESOScript$", () -> {
      serviceRoot = settings.getClientSettings().get(ClientSettings.WEB_API_URI);

      //TODO: add base64 un-encode when applicable
      bearerToken = settings.getClientSettings().get(ClientSettings.BEARER_TOKEN);
      if (bearerToken != null && bearerToken.length() > 0) {
        LOG.info("Bearer token loaded... first 4 characters: " + bearerToken.substring(0, 4));
      }

      clientId = settings.getClientSettings().get(ClientSettings.CLIENT_IDENTIFICATION);
      clientSecret = settings.getClientSettings().get(ClientSettings.CLIENT_SECRET);
      authorizationUri = settings.getClientSettings().get(ClientSettings.AUTHORIZATION_URI);
      tokenUri = settings.getClientSettings().get(ClientSettings.TOKEN_URI);
      redirectUri = settings.getClientSettings().get(ClientSettings.REDIRECT_URI);
      scope = settings.getClientSettings().get(ClientSettings.CLIENT_SCOPE);

      LOG.info("Service root is: " + serviceRoot);

      //create Commander instance
      commander.set(new Commander.Builder()
          .clientId(clientId)
          .clientSecret(clientSecret)
          .authorizationUri(authorizationUri)
          .tokenUri(tokenUri)
          .redirectUri(redirectUri)
          .scope(scope)
          .serviceRoot(serviceRoot)
          .bearerToken(bearerToken)
          .useEdmEnabledClient(true)
          .build());
    });


    /*
     * REQ-WA103-END2
     */
    And("^the results match the expected DataSystem JSON schema$", () -> {
      //TODO - need to add JSON Schema for DataSystem
    });


    /*
     * REQ-WA103-END3
     */
    And("^the metadata returned is valid$", () -> {
      //store the metadata for later comparisons
      xmlMetadata.set(commander.get().getClient().getDeserializer(ContentType.APPLICATION_XML)
            .toMetadata(new ByteArrayInputStream(responseData.get().getBytes())));

      boolean isValid = commander.get().validateMetadata(xmlMetadata.get());
      LOG.info("Metadata is " + (isValid ? "valid" : "invalid") + "!");
      assertTrue(isValid);
    });


    /*
     * REQ-WA103-QR1
     */
    And("^the provided \"([^\"]*)\" is returned in the \"([^\"]*)\" field$", (String parameterUniqueIdValue, String parameterUniqueId) -> {

      String expectedValueAsString = Utils.resolveValue(parameterUniqueIdValue, settings),
          resolvedValueAsString = from(responseData.get()).get(Utils.resolveValue(parameterUniqueId, settings));

      //string values in the RESOScript have single quotes inside of double quotes
      //OData already checks the response data against the metadata that's being advertised,
      //so we can just do string comparisons
      if (expectedValueAsString.contains("'")) {
        expectedValueAsString = expectedValueAsString.replace("'", "");
        assertEquals(expectedValueAsString, resolvedValueAsString);
      } else {
        assertEquals(expectedValueAsString, resolvedValueAsString);
      }

      LOG.info("Expected Value is:" + expectedValueAsString);
      LOG.info("Resolved value is:" + resolvedValueAsString);
    });


    /*
     * REQ-WA103-QR3 - $select
     */
    And("^data are present in fields contained within \"([^\"]*)\"$", (String parameterSelectList) -> {
      AtomicInteger numFieldsWithData = new AtomicInteger();
      List<String> fieldList = new ArrayList<>(Arrays.asList(Utils.resolveValue(parameterSelectList, settings).split(",")));

      AtomicInteger numResults = new AtomicInteger();

      //iterate over the items and count the number of fields with data to determine whether there are data present
      from(responseData.get()).getList("value", HashMap.class).forEach(item -> {
        if (item != null) {
          numResults.getAndIncrement();
          fieldList.forEach(field -> {
            if (item.get(field) != null) {
              numFieldsWithData.getAndIncrement();
            }
          });
        }
      });

      LOG.info("Number of Results: " + numResults.get());
      LOG.info("Number of Fields: " + fieldList.size());
      LOG.info("Field with Data: " + numFieldsWithData.get());
      LOG.info("Percent Fill: " + ((numResults.get() * fieldList.size()) / (1.0 * numFieldsWithData.get()) * 100) + "%");
      assertTrue(numFieldsWithData.get() > 0);
    });


    /*
     * REQ-WA103-QR4 - $top
     * $top=*Parameter_TopCount*
     */
    And("^the results contain at most \"([^\"]*)\" records$", (String parameterTopCount) -> {
      List<String> items = from(responseData.get()).getList("value");
      AtomicInteger numResults = new AtomicInteger(items.size());

      int topCount = Integer.parseInt(Utils.resolveValue(parameterTopCount, settings));
      LOG.info("Number of values returned: " + numResults.get() + ", top count is: " + topCount);

      assertTrue(numResults.get() > 0 && numResults.get() <= topCount);
    });


    /*
     * REQ-WA103-QR5 - $skip
     * $skip=*Parameter_TopCount*
     */
    And("^a GET request is made to the resolved Url in \"([^\"]*)\" with \\$skip=\"([^\"]*)\"$", (String requirementId, String parameterTopCount) -> {
      int skipCount = Integer.parseInt(Utils.resolveValue(parameterTopCount, settings));
      LOG.info("Skip count is: " + skipCount);

      //preserve initial response data for later comparisons
      initialResponseData.set(responseData.get());

      //TODO: convert to OData filter factory
      URI requestUri = Commander.prepareURI(Settings.resolveParameters(settings.getRequests().get(requirementId), settings).getUrl() + "&$skip=" + skipCount);
      LOG.info("Request URI: " + (requestUri != null ? requestUri.toString() : ""));
      rawRequest.set(commander.get().getClient().getRetrieveRequestFactory().getRawRequest(requestUri));
      oDataRawResponse.set(rawRequest.get().execute());
      responseData.set(convertInputStreamToString(oDataRawResponse.get().getRawResponse()));
    });
    And("^data in the \"([^\"]*)\" fields are different in the second request than in the first$", (String parameterUniqueId) -> {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode n1 = mapper.readTree(initialResponseData.get());
      JsonNode n2 = mapper.readTree(responseData.get());

      assertFalse(n1.equals(n2));
    });


    //==================================================================================================================
    // Common Methods
    //==================================================================================================================

    /*
     * GET request by requirementId (see generic.resoscript)
     */
    When("^a GET request is made to the resolved Url in \"([^\"]*)\"$", (String requirementId) -> {
      URI requestUri = Commander.prepareURI(Settings.resolveParameters(settings.getRequests().get(requirementId), settings).getUrl());
      LOG.info("Request URI: " + requestUri);

      rawRequest.set(commander.get().getClient().getRetrieveRequestFactory().getRawRequest(requestUri));
      oDataRawResponse.set(rawRequest.get().execute());
      responseData.set(convertInputStreamToString(oDataRawResponse.get().getRawResponse()));

      LOG.info("Request succeeded..." + responseData.get().getBytes().length + " bytes received.");
    });

    /*
     * GET request by requirementId with an additional Uri fragment for the base Uri
     */
    When("^a GET request is made to the resolved Url in \"([^\"]*)\" with \"([^\"]*)\"$", (String requirementId, String parameterTopCount) -> {
      request.set(Settings.resolveParameters(settings.getRequests().get(requirementId), settings));

      LOG.info("Request URL: " + request.get().getUrl());

      rawRequest.set(commander.get().getClient().getRetrieveRequestFactory().getRawRequest(Commander.prepareURI(request.get().getUrl())));
      oDataRawResponse.set(rawRequest.get().execute());
      responseData.set(convertInputStreamToString(oDataRawResponse.get().getRawResponse()));

      LOG.info("Request succeeded..." + responseData.get().getBytes().length + " bytes received.");
    });

    /*
     * Assert response code
     */
    Then("^the server responds with a status code of (\\d+)$", (Integer code) -> {
      int responseCode = oDataRawResponse.get().getStatusCode();
      LOG.info("Response code is: " + responseCode);
      assertEquals(code.intValue(), responseCode);
    });

    /*
     * Assert greater than: lValFromItem > rValFromSetting
     *
     * TODO: add general op expression parameter rather than creating individual comparators
     */
    And("^data in \"([^\"]*)\" are greater than \"([^\"]*)\"$", (String lValFromItem, String rValFromSetting) -> {
      from(responseData.get()).getList("value", HashMap.class).forEach(item -> {
        Integer lVal = new Integer(item.get(Utils.resolveValue(lValFromItem, settings)).toString()),
                rVal = new Integer(Utils.resolveValue(rValFromSetting, settings));

        assertTrue( lVal > rVal );
      });
    });

    /*
     * validate XML wrapper
     */
    And("^the response is valid XML$", () -> {
      assertTrue(Commander.validateXML(responseData.get()));

      LOG.info("Response is valid XML!");
    });

    /*
     * validate JSON wrapper
     */
    And("^the response is valid JSON$", () -> {
      oDataRawResponse.get().getRawResponse().toString();
      assertTrue(Utils.isValidJson(responseData.get()));

      LOG.info("Response is valid JSON!");
    });
  }

  private static class Utils {
    /**
     * Tests the given string to see if it's valid JSON
     * @param jsonString
     * @return true if valid, false otherwise. Throws {@link IOException}
     */
    private static boolean isValidJson(String jsonString) {
      try {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.readTree(jsonString);
        return true;
      } catch (IOException e) {
        return false;
      }
    }

    /**
     * Resolves the given item into a value
     * @param item an item which can either be a reference to a parameter, client setting, or it can be an actual value.
     * @return the resolved setting or item if it's not a client setting or parameter
     */
    private static String resolveValue(String item, Settings settings) {
      if (item.contains("Parameter_")) {
        return settings.getParameters().getValue(item.replace("Parameter_", ""));
      }

      if (item.contains("ClientSettings_")) {
        return settings.getClientSettings().get(item.replace("ClientSettings_", ""));
      }

      return item;
    }

    /**
     * Returns the String data contained within a given ODataRawResponse.
     * @param oDataRawResponse the response to convert.
     * @return the response stream as a string.
     */
    private static String getResponseData(ODataRawResponse oDataRawResponse) {
      return convertInputStreamToString(oDataRawResponse.getRawResponse());
    }
  }

  /**
   * These are now passed dynamically but we may want to verify the choices. Leave in for now.
   */
  private static class REQUESTS {
    private static class WEB_API_1_0_2 {
      private static final String REQ_WA_103_END_3 = "REQ-WA103-END3";
      private static final String REQ_WA_103_QR_3 = "REQ-WA103-QR3";
      private static final String REQ_WA103_END2 = "REQ-WA103-END2";
    }
  }

  /**
   * Converts the given inputStream to a string.
   * @param inputStream the input stream to convert.
   * @return the string value contained in the stream.
   */
  private static String convertInputStreamToString(InputStream inputStream) {
    InputStreamReader isReader = new InputStreamReader(inputStream);
    BufferedReader reader = new BufferedReader(isReader);
    StringBuilder sb = new StringBuilder();
    String str;
    try {
      while((str = reader.readLine())!= null){
        sb.append(str);
      }
      return sb.toString();
    } catch (Exception ex) {
      LOG.error("Error in convertInputStreamToString: " + ex);
    }
    return null;
  }
}
