package org.reso.certification.stepdefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.POJONode;
import io.cucumber.java8.En;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.communication.ODataClientErrorException;
import org.apache.olingo.client.api.communication.ODataServerErrorException;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetRequest;
import org.apache.olingo.client.api.communication.request.retrieve.ODataRawRequest;
import org.apache.olingo.client.api.communication.response.ODataRawResponse;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.data.ResWrap;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.edm.xml.XMLMetadata;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeException;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlNavigationProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.format.ContentType;
import org.reso.commander.Commander;
import org.reso.commander.TestUtils;
import org.reso.models.ClientSettings;
import org.reso.models.Request;
import org.reso.models.Settings;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URI;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import static io.restassured.path.json.JsonPath.from;
import static org.junit.Assert.*;
import static org.reso.commander.TestUtils.*;
import static org.reso.commander.TestUtils.Operators.*;

public class WebAPIServer_1_0_2 implements En {
  private static final Logger LOG = LogManager.getLogger(WebAPIServer_1_0_2.class);
  private static final  String FIELD_SEPARATOR = ",", PRETTY_FIELD_SEPARATOR = FIELD_SEPARATOR + " ";

  private static Settings settings;

  //container to hold retrieved metadata for later comparisons
  private static AtomicReference<XMLMetadata> xmlMetadata = new AtomicReference<>();
  private static AtomicReference<Edm> edm = new AtomicReference<>();

  private String serviceRoot, bearerToken, clientId, clientSecret, authorizationUri, tokenUri, redirectUri, scope;
  private String pathToRESOScript;

  /**
   * Accessors used for running one-off tests that have dependent metadata tests
   * @param commander the commander instance to make the request with.
   * @return the XMLMetadata for the given request.
   */
  private static XMLMetadata getXMLMetadata(Commander commander) {
    if (xmlMetadata.get() == null) {
      xmlMetadata.set(commander.getXMLMetadata());
    }
    return xmlMetadata.get();
  }

  /**
   * Accessors used for running one-off tests that have dependent metadata tests
   * @param commander the commander instance to make the request with.
   * @return the Edm for the given request.
   */
  private static Edm getEdmMetadata(Commander commander) {
    if (edm.get() == null) {
      edm.set(commander.getEdm());
    }
    return edm.get();
  }

  public WebAPIServer_1_0_2() {

    //TODO: split into separate test files and parallelize to remove the need for Atomic "globals"
    AtomicReference<Commander> commander = new AtomicReference<>();
    AtomicReference<ODataRawResponse> oDataRawResponse = new AtomicReference<>();
    AtomicReference<Request> request = new AtomicReference<>();
    AtomicReference<URI> requestUri = new AtomicReference<>();
    AtomicReference<Integer> responseCode = new AtomicReference<>();
    AtomicReference<String> responseData = new AtomicReference<>();
    AtomicReference<String> initialResponseData = new AtomicReference<>(); //used if two result sets need to be compared
    AtomicReference<ODataRawRequest> rawRequest = new AtomicReference<>();

    AtomicReference<ODataClientErrorException> oDataClientErrorException = new AtomicReference<>();
    AtomicReference<String> serverODataHeaderVersion = new AtomicReference<>();
    AtomicReference<Boolean> testAppliesToServerODataHeaderVersion = new AtomicReference<>();

    AtomicReference<ODataEntitySetRequest<ClientEntitySet>> clientEntitySetRequest = new AtomicReference<>();
    AtomicReference<ODataRetrieveResponse<ClientEntitySet>> clientEntitySetResponse = new AtomicReference<>();
    AtomicReference<ClientEntitySet> clientEntitySet = new AtomicReference<>();

    final boolean showResponses = Boolean.parseBoolean(System.getProperty("showResponses"));

    /*
     * Instance Utility Methods - must precede usage
     */

    /*
     * Resets the local instance state used during test time.
     * NOTE: you should add any local variables you need reset between tests here.
     *
     * TODO: refactor into collection of AtomicReference<T>
     */
    Runnable resetRequestState = () -> {
      oDataRawResponse.set(null);
      request.set(null);
      responseCode.set(null);
      responseData.set(null);
      initialResponseData.set(null);
      rawRequest.set(null);
      oDataClientErrorException.set(null);
      serverODataHeaderVersion.set(null);
      testAppliesToServerODataHeaderVersion.set(false);
    };

    /*
     * This is used to prepare the URI coming from the raw RESOScript file to handle special characters
     * needing to be processed prior to being converted to a valid URI.
     */
    Function<String, URI> prepareUri = queryString -> URI.create(
        /* replace spaces with %20 */
        queryString.replace(" ", "%20")

        /* add other handlers here */
    );

    /*
     * Executes HTTP GET request and sets the expected local variables.
     * Handles exceptions and sets response codes.
     */
    Function<URI, Void> executeGetRequest = (URI uri) -> {
      LOG.info("Request URI: " + uri);
      try {
        rawRequest.set(commander.get().getClient().getRetrieveRequestFactory().getRawRequest(uri));
        oDataRawResponse.set(rawRequest.get().execute());
        responseData.set(TestUtils.convertInputStreamToString(oDataRawResponse.get().getRawResponse()));
        serverODataHeaderVersion.set(oDataRawResponse.get().getHeader(HEADER_ODATA_VERSION).toString());
        responseCode.set(oDataRawResponse.get().getStatusCode());
        LOG.info("Request succeeded..." + responseData.get().getBytes().length + " bytes received.");
      } catch (ODataClientErrorException cex) {
        LOG.debug("ODataClientErrorException caught. Check tests for asserted conditions...");
        oDataClientErrorException.set(cex);
        serverODataHeaderVersion.set(TestUtils.getHeaderData(HEADER_ODATA_VERSION, cex.getHeaderInfo()));
        responseCode.set(cex.getStatusLine().getStatusCode());
      } catch (ODataServerErrorException sex) {
        LOG.debug("ODataServerErrorException thrown in executeGetRequest. Check tests for asserted conditions...");
        //TODO: look for better ways to do this in Olingo or open PR
        if (sex.getMessage().contains(Integer.toString(HttpStatus.SC_NOT_IMPLEMENTED))) {
          responseCode.set(HttpStatus.SC_NOT_IMPLEMENTED);
          return null;
        }
        fail("ERROR: unhandled ODataServerErrorException in executeGetRequest()!\n" + sex.toString());
        throw sex;
      } catch (Exception ex) {
        fail("ERROR: unhandled Exception in executeGetRequest()!\n" + ex.toString());
        throw ex;
      }
      return null;
    };

    /*
     * Background
     */
    Given("^a RESOScript file was provided$", () -> {
      if (pathToRESOScript == null) {
        pathToRESOScript = System.getProperty("pathToRESOScript");
      }
      assertNotNull("ERROR: pathToRESOScript must be present in command arguments, see README", pathToRESOScript);
      LOG.info("Using RESOScript: " + pathToRESOScript);
    });
    And("^Client Settings and Parameters were read from the file$", () -> {
      if (settings == null) {
        settings = Settings.loadFromRESOScript(new File(System.getProperty("pathToRESOScript")));
      }
      assertNotNull("ERROR: Settings could not be loaded.", settings);
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
     * Ensures that the client either uses Authorization Codes or Client Credentials
     */
    And("^the OData client uses either Authorization Codes or Client Credentials$", () -> {
      assertNotNull(commander.get());
      assertTrue("ERROR: Commander must either have a valid Authorization Code or Client Credentials configuration.",
          commander.get().isTokenClient() || commander.get().isOAuthClient() && commander.get().hasValidAuthConfig());

      if (commander.get().isTokenClient()) {
        LOG.info("Authentication Type: authorization_code");
      } else if (commander.get().isOAuthClient()) {
        LOG.info("Authentication Type: client_credentials");
      }
    });

    /*
     * REQ-WA103-END2
     */
    And("^the results match the expected DataSystem JSON schema$", () -> {
      //TODO - need to add JSON Schema for DataSystem
    });


    /*
     * Edm Metadata Validator
     */
    And("^the Edm metadata returned by the server are valid$", () -> {
      assertNotNull("ERROR: No Entity Data Model (Edm) Exists!", edm.get());

      try {
        boolean isValid = commander.get().validateMetadata(edm.get());
        LOG.info("Edm Metadata is " + (isValid ? "valid" : "invalid") + "!");
        assertTrue("Edm Metadata at the given service root is not valid! " + serviceRoot, isValid);
      } catch (Exception ex) {
        fail("ERROR: could not validate Edm Metadata!\n" + ex.getMessage());
      }
    });


    /*
     * XMLMetadata Validator
     */
    And("^the XML metadata returned by the server are valid$", () -> {
      assertNotNull("ERROR: No Response Data exists to convert to XML Metadata!", responseData.get());

      try {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(responseData.get().getBytes());
        xmlMetadata.set(commander.get().getClient().getDeserializer(ContentType.APPLICATION_XML).toMetadata(byteArrayInputStream));

        boolean isValid = commander.get().validateMetadata(xmlMetadata.get());
        LOG.info("XML Metadata is " + (isValid ? "valid" : "invalid") + "!");
        assertTrue("ERROR: XML Metadata at the given service root is not valid! " + serviceRoot, isValid);
      } catch (Exception ex) {
        fail("ERROR: could not validate XML Metadata!\n" + ex.getMessage());
      }
    });


    /*
     * REQ-WA103-QR1
     */
    And("^the provided \"([^\"]*)\" is returned in \"([^\"]*)\"$", (String parameterUniqueIdValue, String parameterUniqueId) -> {
      try {
        String expectedValueAsString = Settings.resolveParametersString(parameterUniqueIdValue, settings), resolvedValueAsString = null;
        Object resolvedValue = from(responseData.get()).get(Settings.resolveParametersString(parameterUniqueId, settings));

        //both of the inputs should be present
        assertNotNull(expectedValueAsString);
        assertNotNull(resolvedValue);

        //quotes are passed for strings, let's strip them off
        expectedValueAsString = expectedValueAsString
            .replace("'", "").replace("\"", "");

        LOG.info("Expected Value is: " + expectedValueAsString);
        LOG.info("Resolved value is: " + resolvedValue);

        if (resolvedValue.getClass().isInstance(Integer.class)) {
          assertEquals("ERROR: the given Integer value is not equal to the value found on the server!", Integer.parseInt(expectedValueAsString), resolvedValue);
        } else {
          assertEquals("ERROR: the given String value is not equal to the value found on the server!", expectedValueAsString, resolvedValue.toString());
        }
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * REQ-WA103-QR3 - $select
     */
    And("^data are present in fields contained within \"([^\"]*)\"$", (String parameterSelectList) -> {
      try {
        AtomicInteger numFieldsWithData = new AtomicInteger();
        List<String> fieldList = new ArrayList<>(Arrays.asList(Settings.resolveParametersString(parameterSelectList, settings).split(FIELD_SEPARATOR)));

        AtomicInteger numResults = new AtomicInteger();
        //iterate over the items and count the number of fields with data to determine whether there are data present
        from(responseData.get()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
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
        LOG.info("Fields with Data: " + numFieldsWithData.get());
        if (numFieldsWithData.get() > 0) {
          LOG.info("Percent Fill: " + ((numResults.get() * fieldList.size()) / (1.0 * numFieldsWithData.get()) * 100) + "%");
        } else {
          LOG.info("Percent Fill: 0% - no fields with data found!");
        }
        assertTrue("ERROR: no fields with data could be found from the given $select list!",numFieldsWithData.get() > 0);
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });


    /*
     * REQ-WA103-QR4 - $top
     * $top=*Parameter_TopCount*
     */
    And("^the results contain at most \"([^\"]*)\" records$", (String parameterTopCount) -> {
      try {
        List<String> items = from(responseData.get()).getList(JSON_VALUE_PATH);
        AtomicInteger numResults = new AtomicInteger(items.size());

        int topCount = Integer.parseInt(Settings.resolveParametersString(parameterTopCount, settings));
        LOG.info("Number of values returned: " + numResults.get() + ", top count is: " + topCount);

        assertTrue("ERROR: results count must be greater than zero and less than " + parameterTopCount + "!",
            numResults.get() > 0 && numResults.get() <= topCount);
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });


    /*
     * REQ-WA103-QR5 - $skip
     * $skip=*Parameter_TopCount*
     */
    And("^a GET request is made to the resolved Url in \"([^\"]*)\" with \\$skip=\"([^\"]*)\"$", (String requirementId, String parameterTopCount) -> {
      try {
        int skipCount = Integer.parseInt(Settings.resolveParametersString(parameterTopCount, settings));
        LOG.info("Skip count is: " + skipCount);

        //preserve initial response data for later comparisons
        initialResponseData.set(responseData.get());

        //TODO: convert to OData filter factory
        requestUri.set(Commander.prepareURI(Settings.resolveParameters(settings.getRequests().get(requirementId), settings).getUrl() + "&$skip=" + skipCount));
        executeGetRequest.apply(requestUri.get());
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });
    And("^data in the \"([^\"]*)\" fields are different in the second request than in the first$", (String parameterUniqueId) -> {
      try {
        List<POJONode> l1 = from(initialResponseData.get()).getJsonObject(JSON_VALUE_PATH);
        List<POJONode> l2 = from(responseData.get()).getJsonObject(JSON_VALUE_PATH);

        int combinedCount = l1.size() + l2.size();
        Set<POJONode> combined = new LinkedHashSet<>(l1);

        new POJONode(l1);
        if (showResponses) LOG.info("Response Page 1: " + l1);

        combined.addAll(l2);
        new POJONode(l2);
        if (showResponses) LOG.info("Response Page 2: " + l2);

        assertEquals("ERROR: repeated data found, expected unique data on each page!", combinedCount, combined.size());
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    //==================================================================================================================
    // Common Methods
    //==================================================================================================================

    /*
     * GET request by requirementId (see generic.resoscript)
     */
    When("^a GET request is made to the resolved Url in \"([^\"]*)\"$", (String requirementId) -> {
      try {
        //reset local state each time a get request is run
        resetRequestState.run();

        LOG.info("RequirementId: " + requirementId);
        requestUri.set(Commander.prepareURI(Settings.resolveParameters(settings.getRequests().get(requirementId), settings).getUrl()));
        executeGetRequest.apply(requestUri.get());
      } catch (Exception ex) {
        LOG.debug("Exception was thrown in " + this.getClass() + ": " + ex.toString());
      }
    });

    /*
     * Assert response code
     */
    Then("^the server responds with a status code of (\\d+)$", (Integer assertedResponseCode) -> {
      try {
        LOG.info("Asserted Response Code: " + assertedResponseCode + ", Server Response Code: " + responseCode);
        assertTrue(responseCode.get() > 0 && assertedResponseCode > 0);

        assertEquals("ERROR: asserted response code does not match the one returned from the server!",
            assertedResponseCode.intValue(), responseCode.get().intValue());
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * validate XML wrapper
     */
    And("^the response is valid XML$", () -> {
      try {
        assertTrue("ERROR: invalid XML response!", Commander.validateXML(responseData.get()));
        LOG.info("Response is valid XML!");
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * validate JSON wrapper
     */
    And("^the response is valid JSON$", () -> {
      try {
        assertTrue("ERROR: invalid JSON response!", TestUtils.isValidJson(responseData.get()));
        LOG.info("Response is valid JSON!");

        if (showResponses) LOG.info("Response: " + new ObjectMapper().readTree(responseData.get()).toPrettyString());
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * Assert HTTP Response Code given asserted OData version
     *
     * TODO: make a general Header assertion function
     */
    Then("^the server responds with a status code of (\\d+) if the server headers report OData version \"([^\"]*)\"$", (Integer assertedHttpResponseCode, String assertedODataVersion) -> {
      try {
        boolean versionsMatch = serverODataHeaderVersion.get().equals(assertedODataVersion),
            responseCodesMatch = responseCode.get().intValue() == assertedHttpResponseCode.intValue();

        LOG.info("Asserted OData Version: " + assertedODataVersion + ", Server Version: " + serverODataHeaderVersion.get());

        if (versionsMatch) {
          LOG.info("Asserted Response Code: " + assertedHttpResponseCode + ", Response code: " + responseCode.get());
          assertTrue("ERROR: asserted response code does not match the one returned from the server!", responseCodesMatch);
        }
      } catch (Exception ex) {
        //Don't fail tests like in other cases because get requests may generate exceptions that we want to
        //continue past and test. Log exceptions to DEBUG instead.
        LOG.debug(ex.toString());
      }
    });

    /*
     * Compares field data (LHS) to a given parameter value (RHS). The operator is passed as a string,
     * and is used to select among the supported comparisons.
     */
    And("^Integer data in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String parameterFieldName, String op, String parameterAssertedValue) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, settings);
        int assertedValue = Integer.parseInt(Settings.resolveParametersString(parameterAssertedValue, settings));

        LOG.info("fieldName: " + fieldName + ", op: " + op + ", assertedValue: " + assertedValue);

        //subsequent value comparisons are and-ed together while iterating over the list of items, so init to true
        AtomicBoolean result = new AtomicBoolean(true);

        AtomicReference<Integer> fieldValue = new AtomicReference<>();

        //iterate through response data and ensure that with data, the statement fieldName "op" assertValue is true
        from(responseData.get()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
          fieldValue.set(Integer.parseInt(item.get(fieldName).toString()));
          result.set(result.get() && TestUtils.compare(fieldValue.get(), op, assertedValue));
        });

        assertTrue(result.get());
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * True if response has results, meaning value.length > 0
     */
    And("^the response has results$", () -> {
      try {
        int count = from(responseData.get()).getList(JSON_VALUE_PATH, HashMap.class).size();
        LOG.info("Results count is: " + count);
        assertTrue("ERROR: no results returned from the server!", count > 0);
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * True if data are present in the response
     */
    And("^the response has singleton results in \"([^\"]*)\"", (String parameterFieldName) -> {
      try {
        String value = Settings.resolveParametersString(parameterFieldName, settings);
        boolean isPresent = from(responseData.get()).get() != null;
        LOG.info("Response value is: " + value);
        LOG.info("IsPresent: " + isPresent);
        assertTrue("ERROR: singleton results not found for '" + value + "'!", isPresent);
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * True if results count less than or equal to limit
     */
    And("^the number of results is less than or equal to \"([^\"]*)\"$", (String limitField) -> {
      try {
        int count = from(responseData.get()).getList(JSON_VALUE_PATH, HashMap.class).size(),
            limit = Integer.parseInt(Settings.resolveParametersString(limitField, settings));
        LOG.info("Results count is: " + count + ", Limit is: " + limit);
        assertTrue("ERROR: number of results exceeds that specified in '" + limitField + "'!", count <= limit);
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * True if data in the lhs expression and rhs expressions pass the AND or OR condition given in andOrOp
     */
    And("^Integer data in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String parameterFieldName, String opLhs, String parameterAssertedLhsValue, String andOrOp, String opRhs, String parameterAssertedRhsValue) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, settings);
        Integer assertedLhsValue = Integer.parseInt(Settings.resolveParametersString(parameterAssertedLhsValue, settings)),
            assertedRhsValue = Integer.parseInt(Settings.resolveParametersString(parameterAssertedRhsValue, settings));

        String op = andOrOp.toLowerCase();
        boolean isAndOp = op.contains(AND);

        //these should default to true when And and false when Or for the purpose of boolean comparisons
        AtomicBoolean lhsResult = new AtomicBoolean(isAndOp);
        AtomicBoolean rhsResult = new AtomicBoolean(isAndOp);
        AtomicBoolean itemResult = new AtomicBoolean(isAndOp);

        AtomicReference<Integer> lhsValue = new AtomicReference<>(),
            rhsValue = new AtomicReference<>();

        //iterate through response data and ensure that with data, the statement fieldName "op" assertValue is true
        from(responseData.get()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
          lhsValue.set(Integer.parseInt(item.get(fieldName).toString()));
          rhsValue.set(Integer.parseInt(item.get(fieldName).toString()));


          lhsResult.set(TestUtils.compare(lhsValue.get(), opLhs, assertedLhsValue));
          rhsResult.set(TestUtils.compare(rhsValue.get(), opRhs, assertedRhsValue));

          if (op.contentEquals(AND)) {
            itemResult.set(lhsResult.get() && rhsResult.get());
            LOG.info("Assert True: " + lhsResult.get() + " AND " + rhsResult.get() + " ==> " + itemResult.get());
            assertTrue(itemResult.get());
          } else if (op.contentEquals(OR)) {
            itemResult.set(lhsResult.get() || rhsResult.get());
            LOG.info("Assert True: " + lhsResult.get() + " OR " + rhsResult.get() + " ==> " + itemResult.get());
            assertTrue(itemResult.get());
          }
        });
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * Date Comparison glue
     */
    And("^Date data in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String parameterFieldName, String op, String parameterAssertedValue) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, settings);
        AtomicReference<Date> fieldValue = new AtomicReference<>();
        AtomicReference<Date> assertedValue = new AtomicReference<>();

        assertedValue.set(TestUtils.parseDateFromEdmDateString(Settings.resolveParametersString(parameterAssertedValue, settings)));
        LOG.info("Asserted value is: " + assertedValue.get().toString());

        from(responseData.get()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
          try {
            fieldValue.set(TestUtils.parseDateFromEdmDateTimeOffsetString(item.get(fieldName).toString()));
            assertTrue(TestUtils.compare(fieldValue.get(), op, assertedValue.get()));
          } catch (Exception ex) {
            fail(ex.toString());
          }
        });
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * Time comparison glue
     */
    And("^TimeOfDay data in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String parameterFieldName, String op, String parameterAssertedValue) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, settings);
        AtomicReference<Time> fieldValue = new AtomicReference<>();
        AtomicReference<Time> assertedValue = new AtomicReference<>();

        assertedValue.set(TestUtils.parseTimeOfDayFromEdmTimeOfDayString(Settings.resolveParametersString(parameterAssertedValue, settings)));
        LOG.info("Asserted value is: " + assertedValue.get().toString());

        from(responseData.get()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
          try {
            fieldValue.set(TestUtils.parseTimeOfDayFromEdmDateTimeOffsetString(item.get(fieldName).toString()));
            assertTrue(TestUtils.compare(fieldValue.get(), op, assertedValue.get()));
          } catch (Exception ex) {
            LOG.error(ex.getMessage());
          }
        });
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * Timestamp comparison glue
     */
    And("^DateTimeOffset data in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String parameterFieldName, String op, String parameterAssertedValue) -> {
      try {
        TestUtils.assertDateTimeOffset(parameterFieldName, op, parameterAssertedValue, responseData.get(), settings);
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * Timestamp comparison to now()
     */
    And("^DateTimeOffset data in \"([^\"]*)\" \"([^\"]*)\" now\\(\\)$", (String parameterFieldName, String op) -> {
      try {
        TestUtils.assertDateTimeOffset(parameterFieldName, op, Timestamp.from(Instant.now()), responseData.get(), settings);
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * Single-Valued enumerations
     */
    And("^Single Valued Enumeration Data in \"([^\"]*)\" has \"([^\"]*)\"$", (String parameterFieldName, String parameterAssertedValue) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, settings);
        AtomicReference<String> fieldValue = new AtomicReference<>();
        AtomicReference<String> assertedValue = new AtomicReference<>();

        AtomicBoolean result = new AtomicBoolean(false);

        assertedValue.set(Settings.resolveParametersString(parameterAssertedValue, settings));
        LOG.info("Asserted value is: " + assertedValue.get());

        from(responseData.get()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
          fieldValue.set(item.get(fieldName).toString());
          result.set(fieldValue.get().contentEquals(assertedValue.get()));
          LOG.info("Assert True: " + fieldValue.get() + " equals " + assertedValue.get() + " ==> " + result.get());
          assertTrue(result.get());
        });
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * Multi-valued enumerations testing.
     * TODO: turn array into JSON array and parse values from there
     */
    And("^Multiple Valued Enumeration Data in \"([^\"]*)\" has \"([^\"]*)\"$", (String parameterFieldName, String parameterAssertedValue) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, settings);
        AtomicReference<String> fieldValue = new AtomicReference<>();
        AtomicReference<String> assertedValue = new AtomicReference<>();

        AtomicBoolean result = new AtomicBoolean(false);

        assertedValue.set(Settings.resolveParametersString(parameterAssertedValue, settings));
        LOG.info("Asserted value is: " + assertedValue.get());

        from(responseData.get()).getList(JSON_VALUE_PATH, ObjectNode.class).forEach(item -> {
          fieldValue.set(item.get(fieldName).toString());
          result.set(fieldValue.get().contains(assertedValue.get()));
          LOG.info("Assert True: " + fieldValue.get() + " has " + assertedValue.get() + " ==> " + result.get());
          assertTrue(result.get());
        });
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * Date comparison ordering (asc, desc).
     */
    And("^DateTimeOffset data in \"([^\"]*)\" is sorted in \"([^\"]*)\" order$", (String parameterFieldName, String parameterOrderByDirection) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, settings);
        final String ASC = "asc", DESC = "desc";
        AtomicReference<String> orderBy = new AtomicReference<>(parameterOrderByDirection.toLowerCase());

        assertTrue(orderBy.get().equals(ASC) || orderBy.get().equals(DESC));

        //used to store the last value for comparisons
        AtomicReference<Timestamp> initialValue = new AtomicReference<>();
        AtomicReference<Timestamp> currentValue = new AtomicReference<>();
        AtomicInteger count = new AtomicInteger(0);

        from(responseData.get()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
          try {
            if (count.get() == 0) {
              initialValue.set(TestUtils.parseTimestampFromEdmDateTimeOffsetString(item.get(fieldName).toString()));
            } else {
              currentValue.set(TestUtils.parseTimestampFromEdmDateTimeOffsetString(item.get(fieldName).toString()));
              if (orderBy.get().equals(ASC)) {
                assertTrue(TestUtils.compare(initialValue.get(), LESS_THAN_OR_EQUAL, currentValue.get()));
              } else if (orderBy.get().equals(DESC)) {
                assertTrue(TestUtils.compare(initialValue.get(), GREATER_THAN_OR_EQUAL, currentValue.get()));
              }
              initialValue.set(currentValue.get());
            }
            count.getAndIncrement();
          } catch (EdmPrimitiveTypeException ptex) {
            fail(ptex.getMessage());
          }
        });
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * Date Field comparisons
     */
    And("^\"([^\"]*)\" data in Date Field \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String stringDatePart, String parameterFieldName, String op, String parameterAssertedValue) -> {
      String fieldName = Settings.resolveParametersString(parameterFieldName, settings);
      AtomicReference<Integer> fieldValue = new AtomicReference<>();
      AtomicInteger assertedValue = new AtomicInteger();
      AtomicReference<String> datePart = new AtomicReference<>(stringDatePart.toLowerCase());
      AtomicReference<String> operator = new AtomicReference<>(op.toLowerCase());

      try {
        assertedValue.set(Integer.parseInt(Settings.resolveParametersString(parameterAssertedValue, settings)));
        LOG.info("Asserted value is: " + assertedValue.get());

        from(responseData.get()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
          try {
            fieldValue.set(TestUtils.getDatePart(datePart.get(), item.get(fieldName)));
            assertTrue(TestUtils.compare(fieldValue.get(), operator.get(), assertedValue.get()));
          } catch (Exception ex) {
            fail(ex.getMessage());
          }
        });
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * Year comparison from Timestamp Field
     * TODO: consolidate with Year comparison with Date Field
     */
    And("^\"([^\"]*)\" data in Timestamp Field \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String stringDatePart, String parameterFieldName, String op, String parameterAssertedValue) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, settings);
        AtomicReference<Integer> fieldValue = new AtomicReference<>();
        AtomicReference<Integer> assertedValue = new AtomicReference<>();
        AtomicReference<String> datePart = new AtomicReference<>(stringDatePart.toLowerCase());
        AtomicReference<String> operator = new AtomicReference<>(op.toLowerCase());

        try {
          assertedValue.set(Integer.parseInt(Settings.resolveParametersString(parameterAssertedValue, settings)));
          LOG.info("Asserted value is: " + assertedValue.get().toString());

          from(responseData.get()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
            try {
              fieldValue.set(TestUtils.getTimestampPart(datePart.get(), item.get(fieldName).toString()));
              assertTrue(TestUtils.compare(fieldValue.get(), operator.get(), assertedValue.get()));
            } catch (Exception ex) {
              fail(ex.getMessage());
            }
          });
        } catch (Exception ex) {
          fail(ex.getMessage());
        }
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * String functions
     */
    And("^String data in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String parameterFieldName, String op, String parameterAssertedValue) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, settings);
        AtomicReference<String> assertedValue = new AtomicReference<>(Settings.resolveParametersString(parameterAssertedValue, settings));
        AtomicReference<String> operator = new AtomicReference<>(op.toLowerCase());

        from(responseData.get()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
          assertTrue(TestUtils.compare(item.get(fieldName).toString(), operator.get(), assertedValue.get()));
        });
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });


    //===============================\\
    //  Metadata validation methods  \\
    //===============================\\

    /*
      Checks that metadata are accessible and contain the resource name specified in generic.resoscript
     */
    And("^the metadata contains the \"([^\"]*)\" resource$", (String parameterResourceName) -> {
      final String resourceName = Settings.resolveParametersString(parameterResourceName, settings);
      AtomicReference<CsdlEntityContainer> entityContainer = new AtomicReference<>();

      try {
        entityContainer.set(TestUtils.findDefaultEntityContainer(edm.get(), xmlMetadata.get()));

        assertNotNull("ERROR: server metadata does not contain the given resource name: " + resourceName,
            entityContainer.get().getEntitySet(resourceName));

        LOG.info("Found EntityContainer for the given resource: '" + resourceName + "'");

      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * Ensures that the server metadata for the given resource in parameterResourceName contains
     * all of the fields in the given parameterSelectList.
     */
    And("^resource metadata for \"([^\"]*)\" contains the fields in \"([^\"]*)\"$", (String parameterResourceName, String parameterSelectList) -> {
      final String selectList = Settings.resolveParametersString(parameterSelectList, settings);

      try {
        final String resourceName = Settings.resolveParametersString(parameterResourceName, settings);
        List<String> fieldNames = Arrays.asList(selectList.split(FIELD_SEPARATOR));

        //create field lookup
        Map<String, CsdlProperty> fieldMap = new HashMap<>();
        TestUtils.findEntityTypesForEntityTypeName(edm.get(), xmlMetadata.get(), resourceName)
            .forEach(csdlProperty -> fieldMap.put(csdlProperty.getName(), csdlProperty));

        LOG.info("Searching metadata for fields in given select list: " + selectList);
        fieldNames.forEach(fieldName -> {
          //trim string just in case spaces were used after the commas
          assertNotNull("ERROR: Field name '" + fieldName + "' is not present in server metadata!", fieldMap.get(fieldName.trim()));
          LOG.info("Found: '" + fieldName.trim() + "'");
        });
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * Finds default Edm entity container at the given Service Root.
     * Note that this assumes the server can process the Accept application/xml header!
     */
    When("^a default entity container exists for the service root in \"([^\"]*)\"$", (String clientSettingsServiceRoot) -> {
      final String serviceRoot = Settings.resolveParametersString(clientSettingsServiceRoot, settings);
      assertEquals("ERROR: given service root doesn't match the one configured in the Commander", serviceRoot, commander.get().getServiceRoot());

      try {
        ODataRetrieveResponse<Edm> oDataRetrieveResponse = commander.get().getODataRetrieveEdmResponse();
        responseCode.set(oDataRetrieveResponse.getStatusCode());
        edm.set(oDataRetrieveResponse.getBody());

        assertNotNull("ERROR: could not find default entity container for given service root: " + serviceRoot, edm.get().getEntityContainer());
        LOG.info("Found Default Entity Container: '" + edm.get().getEntityContainer().getNamespace() + "'");
      } catch (ODataClientErrorException cex) {
        responseCode.set(cex.getStatusLine().getStatusCode());
        fail(cex.getMessage());
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * XML Metadata getter
     */
    And("^XML Metadata are requested from the service root in \"([^\"]*)\"$", (String clientSettingsServiceRoot) -> {
      final String serviceRoot = Settings.resolveParametersString(clientSettingsServiceRoot, settings);
      assertEquals("ERROR: given service root doesn't match the one configured in the Commander", serviceRoot, commander.get().getServiceRoot());

      try {
        xmlMetadata.set(commander.get().getXMLMetadata());
        assertNotNull("ERROR: could not find valid XML Metadata for given service root: " + serviceRoot, xmlMetadata.get());
        LOG.info("XML Metadata retrieved from: " + serviceRoot);
      } catch (ODataClientErrorException cex) {
        responseCode.set(cex.getStatusLine().getStatusCode());
        fail(cex.getMessage());
      } catch (Exception ex) {
        fail(ex.getMessage());
      }
    });

    /*
     * Tests whether a navigation property can be found in the given resource name.
     */
    And("^an OData NavigationProperty exists for the given \"([^\"]*)\"$", (String parameterEndpointResource) -> {
      String resourceName = Settings.resolveParametersString(parameterEndpointResource, settings);

      List<CsdlNavigationProperty> navigationProperties
          = TestUtils.findNavigationPropertiesForEntityTypeName(getEdmMetadata(commander.get()), getXMLMetadata(commander.get()), resourceName);

      assertTrue("ERROR: no navigation properties found for the given '" + resourceName + "' resource!",
          navigationProperties.size() > 0);

      LOG.info("Found the following Navigation Properties:");
      navigationProperties.forEach(csdlNavigationProperty -> {
        LOG.info("\tName: " + csdlNavigationProperty.getName());
        LOG.info("\tType: " + csdlNavigationProperty.getType());
      });

    });

    /*
     * Checks to see whether the expanded field has data
     */
    And("^data are present within the expanded field \"([^\"]*)\"$", (String parameterExpandField) -> {
      String expandField = Settings.resolveParametersString(parameterExpandField, settings);
      assertFalse("ERROR: no expand field found for " + parameterExpandField, expandField.isEmpty());

      ClientEntitySet results = commander.get().getClient().getRetrieveRequestFactory().getEntitySetRequest(requestUri.get()).execute().getBody();

      LOG.info("Results count is: " + results.getEntities().size());
      AtomicInteger counter = new AtomicInteger();
      results.getEntities().forEach(clientEntity -> {
        if (showResponses) LOG.info("\nItem #" + counter.getAndIncrement());

        clientEntity.getProperties().forEach(clientProperty -> {
          if (showResponses) {
            LOG.info("\tField Name: " + clientProperty.getName());
            LOG.info("\tField Value: " + clientProperty.getValue().toString());
            LOG.info("\tType Name: " + clientProperty.getValue().getTypeName());
            LOG.info("\n");
          }
          assertNotNull("ERROR: '" + parameterExpandField + "' not found in results!", clientProperty.getName());
          assertNotNull("ERROR: '" + parameterExpandField + "' contains no data!", clientProperty.getValue());
          assertNotNull("ERROR: data type could not be found for " + clientProperty.getName(), clientProperty.getValue().getTypeName());
        });
      });
    });

    /*
     * Checks to see whether expanding the EndpointResource on ExpandField produces equivalent records from the corresponding
     * resource of the expanded type
     */
    And("^the expanded data were found in the related resource$", () -> {
      //TODO: this depends on either finding the appropriate navigation property for a given relationship, or having the Expanded resource type name
    });


    /*
     * Checks the Standard Resources requirement from Section 2.6 of the Web API specification
     */
    And("^the metadata contains at least one resource from \"([^\"]*)\"$", (String parameterRequiredResourceList) -> {
      String requiredResourceString = Settings.resolveParametersString(parameterRequiredResourceList, settings).replace(" ", "");
      List<String> requiredResources = Arrays.asList(requiredResourceString.split(","));

      LOG.info("Searching the default entity container for one of the following Standard Resources: " + requiredResourceString.replace(FIELD_SEPARATOR, PRETTY_FIELD_SEPARATOR));

      AtomicBoolean found = new AtomicBoolean(false);
      requiredResources.forEach(requiredResource -> {
        if (!found.get()) found.set(found.get() || edm.get().getEntityContainer().getEntitySet(requiredResource) != null);
      });

      assertTrue("ERROR: could not find one of the following Standard Resource Names in the default entity container: " + requiredResourceString.replace(FIELD_SEPARATOR, PRETTY_FIELD_SEPARATOR),
          found.get());

      LOG.info("Standard Resource Names requirement met!");
    });

    /*
     * Checks that the resource in Parameter_EndpointResource is within the allowed resources for the
     * latest version of the Data Dictionary, currently 1.7.
     */
    And("^the given \"([^\"]*)\" resource exists within \"([^\"]*)\"$", (String parameterResourceName, String parameterResourceList) -> {
      String resourceName = Settings.resolveParametersString(parameterResourceName, settings),
             allowedResourceString = Settings.resolveParametersString(parameterResourceList, settings).replace(" ", "");
      List<String> allowedResources = new ArrayList<>(Arrays.asList(allowedResourceString.split(FIELD_SEPARATOR)));

      LOG.info("Resource Name: " + resourceName);
      LOG.info("Allowed Resources: " + allowedResourceString.replace(FIELD_SEPARATOR, PRETTY_FIELD_SEPARATOR));

      assertTrue("ERROR: the given resource name '" + resourceName + "' does not exist in the known resources within '" + parameterResourceList + "'. ",
          allowedResources.contains(resourceName));
    });


    When("^a GET request is made to the resolved Url in \"([^\"]*)\" using the OData Client$", (String parameterRequirementId) -> {
      String uriString = Settings.resolveParameters(settings.getRequests().get(parameterRequirementId), settings).getUrl();
      assertTrue("ERROR: the resolved Url in '" + parameterRequirementId + "' was invalid!", uriString != null && uriString.length() > 0);

      LOG.info("Requirement Id: " + parameterRequirementId);
      try {
        requestUri.set(prepareUri.apply(uriString));
        clientEntitySetRequest.set(commander.get().getClient().getRetrieveRequestFactory().getEntitySetRequest(requestUri.get()));
        LOG.info("OData Client Request being made to: " + uriString);
        clientEntitySetResponse.set(clientEntitySetRequest.get().execute());

        responseCode.set(clientEntitySetResponse.get().getStatusCode());

        ResWrap<EntityCollection> coll = (commander.get().getClient().getDeserializer(ContentType.JSON).toEntitySet(clientEntitySetResponse.get().getRawResponse()));
        clientEntitySet.set(commander.get().getClient().getBinder().getODataEntitySet(coll));
      } catch (ODataClientErrorException cex) {
        oDataClientErrorException.set(cex);
        responseCode.set(cex.getStatusLine().getStatusCode());
      } catch (Exception ex) {
        fail(ex.toString());
      }
    });

    /*
     * Uses the OData ClientEntitySet rather than raw JSON responses for comparisons
     */
    And("^client entity set Integer data in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String parameterFieldName, String operator, String parameterFieldValue) -> {
      String fieldName = Settings.resolveParametersString(parameterFieldName, settings),
             op = operator.trim().toLowerCase();

      Integer fieldValue = Integer.parseInt(Settings.resolveParametersString(parameterFieldValue, settings));
      assertNotNull(fieldValue);

      clientEntitySet.get().getEntities().forEach(entity -> {
        assertTrue(compare((Integer)entity.getProperty(fieldName).getValue().asPrimitive().toValue(), op, fieldValue));
      });

    });

    And("^the OData client response has client entity set data$", () -> {
      assertNotNull("ERROR: no entity collection returned in response!", clientEntitySet.get());
      assertTrue("ERROR: no results returned!", clientEntitySet.get().getCount() > 0);

      if (showResponses) {
        clientEntitySet.get().getEntities().forEach(entity -> {
          LOG.info("Entity Type is: " + entity.getTypeName());
          entity.getProperties().forEach(property -> LOG.info("\tProperty: " + property.toString()));
        });
      }
    });
  }
}
