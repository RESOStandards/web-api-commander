package org.reso.certification.stepdefs;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.POJONode;
import io.cucumber.java8.En;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.olingo.client.api.communication.ODataClientErrorException;
import org.apache.olingo.client.api.data.ResWrap;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.uri.QueryOption;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeException;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlNavigationProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.format.ContentType;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.commander.Commander;
import org.reso.commander.common.TestUtils;
import org.reso.models.Request;
import org.reso.models.Settings;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static io.restassured.path.json.JsonPath.from;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;
import static org.reso.certification.containers.WebAPITestContainer.*;
import static org.reso.commander.Commander.*;
import static org.reso.commander.common.ErrorMsg.getAssertResponseCodeErrorMessage;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;
import static org.reso.commander.common.TestUtils.DateParts.FRACTIONAL;
import static org.reso.commander.common.TestUtils.*;
import static org.reso.commander.common.TestUtils.Operators.*;

/**
 * Contains the glue code for Web API Core 1.0.2 Certification as well as previous Platinum tests,
 * which will converted to standalone endorsements, where applicable.
 */
class WebAPIServer implements En {
  private static final Logger LOG = LogManager.getLogger(WebAPIServer.class);
  private static final String
      SHOW_RESPONSES_PARAM = "showResponses",
      USE_COLLECTIONS_PARAM = "useCollections";

  //extract any params here
  private static final boolean showResponses = Boolean.parseBoolean(System.getProperty(SHOW_RESPONSES_PARAM));
  // boolean used for indicating whether Web API tests are using collections of enums or not
  // defaults to useCollections=true since IsFlags is being deprecated
  private static final boolean useCollections = Boolean.parseBoolean(System.getProperty(USE_COLLECTIONS_PARAM, "true"));


  /*
   * Used to store a static instance of the WebAPITestContainer class
   */
  private static final AtomicReference<WebAPITestContainer> container = new AtomicReference<>(new WebAPITestContainer());

  /**
   * Entry point to the Web API Server tests
   */
  public WebAPIServer() {
    getTestContainer().setShowResponses(showResponses);

    runBackground();

    And("^the XML Metadata returned by the server contains Edm metadata$", () -> {
      getTestContainer().setEdm(
          Commander.deserializeEdm(getTestContainer().getXMLResponseData(), getTestContainer().getCommander().getClient())
      );
      assertNotNull(getDefaultErrorMessage("Edm deserialized to an empty object!"), getTestContainer().getEdm());
    });

    /*
     * Edm Metadata Validator
     */
    And("^the Edm metadata returned by the server are valid$", () ->
        assertTrue("Edm Metadata at the given service root is not valid! " + getTestContainer().getServiceRoot(),
            getTestContainer().getIsValidEdm()));

    /*
     * XML Metadata Validator
     */
    And("^the XML Metadata returned by the server are valid$", () -> {
      if (!getTestContainer().getHaveMetadataBeenRequested()) {
        //will lazy-load metadata from the server if not yet requested
        getTestContainer().getXMLMetadata();
      }
      getTestContainer().validateMetadata();
      assertTrue("XML Metadata at the given service root is not valid! " + getTestContainer().getServiceRoot(),
          getTestContainer().getIsValidXMLMetadata());
    });

    /*
     * fetch-by-key
     */
    And("^the provided \"([^\"]*)\" is returned in \"([^\"]*)\"$", (String parameterUniqueIdValue, String parameterUniqueId) -> {
      try {
        String expectedValueAsString = Settings.resolveParametersString(parameterUniqueIdValue, getTestContainer().getSettings());
        Object resolvedValue = from(getTestContainer().getResponseData())
            .get(Settings.resolveParametersString(parameterUniqueId, getTestContainer().getSettings()));

        //both of the inputs should be present
        assertNotNull(expectedValueAsString);
        assertNotNull(resolvedValue);

        //quotes are passed for strings, let's strip them off
        expectedValueAsString = expectedValueAsString
            .replace("'", "").replace("\"", "");

        LOG.info("Expected Value is: " + expectedValueAsString);
        LOG.info("Resolved value is: " + resolvedValue);

        if (resolvedValue.getClass().isInstance(Integer.class)) {
          assertEquals(getDefaultErrorMessage("the given Integer value is not equal to the value found on the server!"),
              Integer.parseInt(expectedValueAsString), resolvedValue);
        } else {
          assertEquals(getDefaultErrorMessage("the given String value is not equal to the value found on the server!"), expectedValueAsString, resolvedValue.toString());
        }
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * select - $select
     */
    And("^data are present for fields contained within the given select list$", () -> {
      try {
        AtomicInteger numFieldsWithData = new AtomicInteger();

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
        double fill;

        assertNotNull(getDefaultErrorMessage("no fields found within the given $select list. Check request Id:",
                getTestContainer().getRequest().getRequestId(), "in your .resoscript file!"), getTestContainer().getSelectList());

        LOG.info(QueryOption.SELECT + " list is: " + getTestContainer().getSelectList());

        AtomicInteger numResults = new AtomicInteger();
        //iterate over the items and count the number of fields with data to determine whether there are data present
        from(getTestContainer().getResponseData()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
          numResults.getAndIncrement();
          getTestContainer().getSelectList().forEach(field -> {
            if (item.get(field) != null) {
              numFieldsWithData.getAndIncrement();
            }
          });
        });

        LOG.info("Number of Results: " + numResults.get());
        LOG.info("Number of Fields: " + getTestContainer().getSelectList().size());
        LOG.info("Fields with Data: " + numFieldsWithData.get());

        if (numResults.get() > 0 && getTestContainer().getSelectList().size() > 0) {
          fill = ((100.0 * numFieldsWithData.get()) / (numResults.get() * getTestContainer().getSelectList().size()));
          LOG.info("Percent Fill: " + df.format(fill) + "%");
        } else {
          LOG.info("Percent Fill: 0% - no fields with data found!");
        }
        assertTrue(getDefaultErrorMessage("no fields with data could be found from the given $select list!"), numFieldsWithData.get() > 0);
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });


    /*
     * top - $top
     * $top=*Parameter_TopCount*
     */
    And("^the results contain at most \"([^\"]*)\" records$", (String parameterTopCount) -> {
      try {
        List<String> items = from(getTestContainer().getResponseData()).getList(JSON_VALUE_PATH);
        AtomicInteger numResults = new AtomicInteger(items.size());

        int topCount = Integer.parseInt(Settings.resolveParametersString(parameterTopCount, getTestContainer().getSettings()));
        LOG.info("Number of values returned: " + numResults.get() + ", top count is: " + topCount);

        assertTrue(getDefaultErrorMessage("results count must be greater than zero and less than", parameterTopCount + "!"),
            numResults.get() > 0 && numResults.get() <= topCount);
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });


    /*
     * skip - $skip
     * $skip=*Parameter_TopCount*
     */
    And("^a GET request is made to the resolved Url in \"([^\"]*)\" with \\$skip=\"([^\"]*)\"$", (String requestId, String parameterTopCount) -> {
      try {
        int skipCount = Integer.parseInt(Settings.resolveParametersString(parameterTopCount, getTestContainer().getSettings()));
        LOG.info("Skip count is: " + skipCount);

        getTestContainer().setRequest(requestId);

        //preserve initial response data for later comparisons
        getTestContainer().setInitialResponseData(getTestContainer().getResponseData());

        //TODO: convert to OData filter factory
        getTestContainer().setRequestUri(Commander.prepareURI(
            Settings.resolveParameters(getTestContainer().getSettings().getRequest(requestId), getTestContainer().getSettings()).getRequestUrl()
                + AMPERSAND + ODATA_QUERY_PARAMS.SKIP + EQUALS + skipCount));
        getTestContainer().executePreparedRawGetRequest();
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    And("^data in the \"([^\"]*)\" fields are different in the second request than in the first$", (String parameterUniqueId) -> {
      try {
        List<POJONode> l1 = from(getTestContainer().getInitialResponseData()).getJsonObject(JSON_VALUE_PATH);
        List<POJONode> l2 = from(getTestContainer().getResponseData()).getJsonObject(JSON_VALUE_PATH);

        int combinedCount = l1.size() + l2.size();
        Set<POJONode> intersection = new LinkedHashSet<>(l1);

        if (showResponses) LOG.info("Response Page 1: " + l1);

        intersection.addAll(l2);
        if (showResponses) LOG.info("Response Page 2: " + l2);
        LOG.info("Expected unique count:" + combinedCount + ", found unique count: " + intersection.size());
        assertEquals(getDefaultErrorMessage("repeated data found, expected unique data on each page!"), combinedCount, intersection.size());
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    //==================================================================================================================
    // Common Methods
    //==================================================================================================================

    /*
     * GET request by requirementId (see generic.resoscript)
     */
    When("^a GET request is made to the resolved Url in \"([^\"]*)\"$", (final String requestId) -> {
      final Set<String> collectionRequestIds = new HashSet<>(Arrays.asList("filter-coll-enum-any", "filter-coll-enum-all"));
      final Set<String> isFlagsRequestIds = new HashSet<>(Arrays.asList("filter-enum-multi-has", "filter-enum-multi-has-and"));

      if (useCollections) {
        assumeFalse("Using Collection(Edm.EnumType). Skipping Test: " + requestId, isFlagsRequestIds.contains(requestId));
      } else {
        assumeFalse("Using IsFlags=\"true\". Skipping Test: " + requestId, collectionRequestIds.contains(requestId));
      }
      prepareAndExecuteRawGetRequest(requestId);
    });

    /*
     * Assert response code
     */
    Then("^the server responds with a status code of (\\d+)$", (Integer assertedResponseCode) -> {
      assertNotNull(getDefaultErrorMessage("request was null! \nCheck RESOScript to make sure requestId exists."), getTestContainer().getRequest());
      try {
        LOG.info("Asserted Response Code: " + assertedResponseCode + ", Server Response Code: " + getTestContainer().getResponseCode());

        if (getTestContainer().getODataClientErrorException() != null) {
          if (getTestContainer().getODataClientErrorException().getODataError().getMessage() != null) {
            LOG.error(getDefaultErrorMessage("Request failed with the following message:",
                getTestContainer().getODataClientErrorException().getODataError().getMessage()));
          } else if (getTestContainer().getODataClientErrorException().getMessage() != null) {
            LOG.error(getDefaultErrorMessage("Request failed with the following message:",
                getTestContainer().getODataClientErrorException().getMessage()));
          }
        }

        if (getTestContainer().getODataServerErrorException() != null) {
          LOG.error(getDefaultErrorMessage("Request failed with the following message:",
              getTestContainer().getODataServerErrorException().toString()));

          if (getTestContainer().getODataServerErrorException().toString().contains(String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR))) {
            getTestContainer().setResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
          }
        }

        //TODO: clean up logic
        if (getTestContainer().getResponseCode() != null && assertedResponseCode.intValue() != getTestContainer().getResponseCode().intValue()) {
          fail(getAssertResponseCodeErrorMessage(assertedResponseCode, getTestContainer().getResponseCode()));
        }

        //if we make it through without failing, things are good
        assertTrue(getTestContainer().getResponseCode() > 0 && assertedResponseCode > 0);
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * validate XML wrapper
     */
    And("^the XML Metadata response is valid XML$", () -> {
      assertNotNull(getDefaultErrorMessage("no XML Response data were found!"), getTestContainer().getXMLResponseData());
      getTestContainer().validateXMLMetadataXML();
      assertTrue(getDefaultErrorMessage("invalid XML response!"), getTestContainer().getIsValidXMLMetadataXML());
    });

    /*
     * validate JSON wrapper
     */
    And("^the response is valid JSON$", getTestContainer()::validateJSON);

    /*
     * Assert HTTP Response Code given asserted OData version
     *
     * TODO: make a general Header assertion function
     */
    Then("^the server responds with a status code of (\\d+) if the server reports OData-Version \"([^\"]*)\"$", (Integer assertedHttpResponseCode, String assertedODataVersion) -> {
      try {
        boolean versionsMatch = getTestContainer().getServerODataHeaderVersion().equals(assertedODataVersion),
            responseCodesMatch = getTestContainer().getResponseCode().intValue() == assertedHttpResponseCode.intValue();

        LOG.info("Asserted OData Version: " + assertedODataVersion + ", Server Version: " + getTestContainer().getServerODataHeaderVersion());

        if (versionsMatch) {
          LOG.info("Asserted Response Code: " + assertedHttpResponseCode + ", Response code: " + getTestContainer().getResponseCode());
          assertTrue(getDefaultErrorMessage("asserted response code (" + assertedHttpResponseCode + ")",
              "does not match the one returned from the server (" + getTestContainer().getResponseCode() + ")!"), responseCodesMatch);
        } else {
          LOG.info("Test skipped! Only applies when the asserted version matches the reported server version.");
        }
      } catch (Exception ex) {
        //Don't fail tests like in other cases because get requests may generate exceptions that we want to
        //continue past and test. Log exceptions to DEBUG instead.
        LOG.debug(ex.toString());
      }
    });

    /*
     * True if response has results, meaning value.length > 0
     */
    And("^the response has results$", () -> {
      try {
        assertTrue(getDefaultErrorMessage("no results were found in the '" + JSON_VALUE_PATH + "' path of the JSON response!"),
            from(getTestContainer().getResponseData()).getList(JSON_VALUE_PATH, Map.class).size() > 0);
        LOG.info("Results count is: " + from(getTestContainer().getResponseData()).getList(JSON_VALUE_PATH, Map.class).size());
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * True if data are present in the response
     */
    And("^the response has singleton results in \"([^\"]*)\"", (String parameterFieldName) -> {
      try {
        String value = Settings.resolveParametersString(parameterFieldName, getTestContainer().getSettings());
        boolean isPresent = from(getTestContainer().getResponseData()).get() != null;
        assertTrue(getDefaultErrorMessage("singleton results not found for '" + value + "'!"), isPresent);
        LOG.info("Data are present and response value is: " + value);
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * True if results count less than or equal to limit
     */
    And("^the number of results is less than or equal to \"([^\"]*)\"$", (String limitField) -> {
      try {
        int count = from(getTestContainer().getResponseData()).getList(JSON_VALUE_PATH, HashMap.class).size(),
            limit = Integer.parseInt(Settings.resolveParametersString(limitField, getTestContainer().getSettings()));
        LOG.info("Results count is: " + count + ", Limit is: " + limit);
        assertTrue(getDefaultErrorMessage("number of results exceeds that specified in '" + limitField + "'!"), count <= limit);
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * Compares field data (LHS) to a given parameter value (RHS). The operator is passed as a string,
     * and is used to select among the supported comparisons.
     */
    And("^Integer data in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String parameterFieldName, String op, String parameterAssertedValue) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, getTestContainer().getSettings());
        int assertedValue = Integer.parseInt(Settings.resolveParametersString(parameterAssertedValue, getTestContainer().getSettings()));

        LOG.info("fieldName: " + fieldName + ", op: " + op + ", assertedValue: " + assertedValue);
        assertTrue(TestUtils.compareIntegerPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, op, assertedValue));
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * True if data in the lhs expression and rhs expressions pass the AND or OR condition given in andOrOp
     * TODO: integrate with TestUtils compareIntegerPayloadToAssertedValue
     */
    And("^Integer data in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String parameterFieldName, String opLhs, String parameterAssertedLhsValue, String andOrOp, String opRhs, String parameterAssertedRhsValue) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, getTestContainer().getSettings());
        Integer assertedLhsValue = Integer.parseInt(Settings.resolveParametersString(parameterAssertedLhsValue, getTestContainer().getSettings())),
            assertedRhsValue = Integer.parseInt(Settings.resolveParametersString(parameterAssertedRhsValue, getTestContainer().getSettings()));

        String op = andOrOp.toLowerCase();
        boolean isAndOp = op.contains(AND);

        //these should default to true when And and false when Or for the purpose of boolean comparisons
        AtomicBoolean lhsResult = new AtomicBoolean(isAndOp);
        AtomicBoolean rhsResult = new AtomicBoolean(isAndOp);
        AtomicBoolean itemResult = new AtomicBoolean(isAndOp);

        AtomicReference<Integer>
            lhsValue = new AtomicReference<>(),
            rhsValue = new AtomicReference<>();

        //iterate through response data and ensure that with data, the statement fieldName "op" assertValue is true
        from(getTestContainer().getResponseData()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
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
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * Date Comparison glue
     */
    And("^Date data in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String parameterFieldName, String op, String parameterAssertedValue) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, getTestContainer().getSettings());
        AtomicReference<Date> fieldValue = new AtomicReference<>();
        AtomicReference<Date> assertedValue = new AtomicReference<>();

        assertedValue.set(TestUtils.parseDateFromEdmDateString(Settings.resolveParametersString(parameterAssertedValue, getTestContainer().getSettings())));
        LOG.info("Asserted value is: " + assertedValue.get().toString());

        from(getTestContainer().getResponseData()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
          try {
            fieldValue.set(TestUtils.parseDateFromEdmDateTimeOffsetString(item.get(fieldName).toString()));
            assertTrue(TestUtils.compare(fieldValue.get(), op, assertedValue.get()));
          } catch (Exception ex) {
            fail(getDefaultErrorMessage(ex));
          }
        });
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * Time comparison glue
     */
    And("^TimeOfDay data in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String parameterFieldName, String op, String parameterAssertedValue) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, getTestContainer().getSettings());
        AtomicReference<Time> fieldValue = new AtomicReference<>();
        AtomicReference<Time> assertedValue = new AtomicReference<>();

        assertedValue.set(TestUtils.parseTimeOfDayFromEdmTimeOfDayString(Settings.resolveParametersString(parameterAssertedValue, getTestContainer().getSettings())));
        LOG.info("Asserted value is: " + assertedValue.get().toString());

        from(getTestContainer().getResponseData()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
          try {
            fieldValue.set(TestUtils.parseTimeOfDayFromEdmDateTimeOffsetString(item.get(fieldName).toString()));
            assertTrue(TestUtils.compare(fieldValue.get(), op, assertedValue.get()));
          } catch (Exception ex) {
            LOG.error(ex.toString());
          }
        });
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * Timestamp comparison glue
     */
    And("^DateTimeOffset data in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String parameterFieldName, String op, String parameterAssertedValue) -> {
      try {
        TestUtils.assertDateTimeOffset(parameterFieldName, op, parameterAssertedValue, getTestContainer().getResponseData(), getTestContainer().getSettings());
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * Timestamp comparison to now()
     */
    And("^DateTimeOffset data in \"([^\"]*)\" \"([^\"]*)\" now\\(\\)$", (String parameterFieldName, String op) -> {
      try {
        TestUtils.assertDateTimeOffset(parameterFieldName, op, Timestamp.from(Instant.now()), getTestContainer().getResponseData(), getTestContainer().getSettings());
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * Single-Valued enumerations
     */
    And("^Single Valued Enumeration Data in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String parameterFieldName, String op, String parameterAssertedValue) -> {
      try {

        final String fieldName = Settings.resolveParametersString(parameterFieldName, getTestContainer().getSettings());
        AtomicReference<String> fieldValue = new AtomicReference<>();
        AtomicReference<String> assertedValue = new AtomicReference<>();

        AtomicBoolean result = new AtomicBoolean(false);

        assertedValue.set(Settings.resolveParametersString(parameterAssertedValue, getTestContainer().getSettings()));
        LOG.info("Asserted value is: " + assertedValue.get());

        from(getTestContainer().getResponseData()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
          fieldValue.set(item.get(fieldName).toString());
          if (op.toLowerCase().contentEquals(EQ) || op.toLowerCase().contentEquals(HAS)) {
            result.set(fieldValue.get().contentEquals(assertedValue.get()));
          } else if (op.toLowerCase().contentEquals(NE)) {
            result.set(!fieldValue.get().contentEquals(assertedValue.get()));
          }

          LOG.info("Assert True: " + fieldValue.get() + " " + op + " " + assertedValue.get() + " ==> " + result.get());
          assertTrue(result.get());
        });
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * Multi-valued enumerations
     */
    And("^Multiple Valued Enumeration Data in \"([^\"]*)\" has \"([^\"]*)\"$", (String parameterFieldName, String parameterAssertedValue) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, getTestContainer().getSettings());
        AtomicReference<String> fieldValue = new AtomicReference<>();
        AtomicReference<String> assertedValue = new AtomicReference<>();
        AtomicBoolean result = new AtomicBoolean(true);

        assertedValue.set(Settings.resolveParametersString(parameterAssertedValue, getTestContainer().getSettings()));
        LOG.info("Asserted value is: " + assertedValue.get());

        from(getTestContainer().getResponseData()).getList(JSON_VALUE_PATH, ObjectNode.class).forEach(item -> {
          fieldValue.set(item.get(fieldName).toString());
          if (useCollections) {
            if (item.get(fieldName).isArray()) {
              result.set(result.get() && TestUtils.testAnyOperator(item, fieldName, assertedValue.get()));
              LOG.info("Assert True: " + fieldValue.get() + " contains " + assertedValue.get() + " ==> " + result.get());
              assertTrue(result.get());
            } else {
              fail(getDefaultErrorMessage(fieldName, "MUST contain an array of values but found:", item.get(fieldName).toString()));
            }
          } else {
            result.set(fieldValue.get().contains(assertedValue.get()));
            LOG.info("Assert True: " + fieldValue.get() + " has " + assertedValue.get() + " ==> " + result.get());
            assertTrue(result.get());
          }
        });
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    And("^Multiple Valued Enumeration Data in \"([^\"]*)\" is empty OR has \"([^\"]*)\"$", (String parameterFieldName, String parameterAssertedValue) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, getTestContainer().getSettings());
        AtomicReference<String> fieldValue = new AtomicReference<>();
        AtomicReference<String> assertedValue = new AtomicReference<>();

        AtomicBoolean result = new AtomicBoolean(true);

        assertedValue.set(Settings.resolveParametersString(parameterAssertedValue, getTestContainer().getSettings()));
        LOG.info("Asserted value is: " + assertedValue.get());

        from(getTestContainer().getResponseData()).getList(JSON_VALUE_PATH, ObjectNode.class).forEach(item -> {
          fieldValue.set(item.get(fieldName).toString());
          if (item.get(fieldName).isArray()) {
            result.set(result.get() && testAllOperator(item, fieldName, assertedValue.get()));
            LOG.info("Assert True: " + fieldValue.get() + " equals [] or contains only " + assertedValue.get() + " ==> " + result.get());
            assertTrue(result.get());
          } else {
            fail(getDefaultErrorMessage(fieldName, "MUST contain an array of values but found:", item.get(fieldName).toString()));
          }
        });
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * Date comparison ordering (asc, desc).
     */
    And("^DateTimeOffset data in \"([^\"]*)\" is sorted in \"([^\"]*)\" order$", (String parameterFieldName, String parameterOrderByDirection) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, getTestContainer().getSettings());
        final String ASC = "asc", DESC = "desc";
        AtomicReference<String> orderBy = new AtomicReference<>(parameterOrderByDirection.toLowerCase());

        assertTrue(orderBy.get().equals(ASC) || orderBy.get().equals(DESC));

        //used to store the last value for comparisons
        AtomicReference<Timestamp> initialValue = new AtomicReference<>();
        AtomicReference<Timestamp> currentValue = new AtomicReference<>();
        AtomicInteger count = new AtomicInteger(0);

        from(getTestContainer().getResponseData()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
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
            fail(ptex.toString());
          }
        });
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * Date Field comparisons
     */
    And("^\"([^\"]*)\" data in Date Field \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String stringDatePart, String parameterFieldName, String op, String parameterAssertedValue) -> {
      String fieldName = Settings.resolveParametersString(parameterFieldName, getTestContainer().getSettings());
      AtomicInteger assertedValue = new AtomicInteger();
      AtomicReference<String> datePart = new AtomicReference<>(stringDatePart.toLowerCase());
      AtomicReference<String> operator = new AtomicReference<>(op.toLowerCase());

      try {
        assertedValue.set(Integer.parseInt(Settings.resolveParametersString(parameterAssertedValue, getTestContainer().getSettings())));
        LOG.info("Asserted value is: " + assertedValue.get());
        assertTrue(TestUtils.compareDatePayloadToAssertedDatePartValue(getTestContainer().getResponseData(), datePart.get(), fieldName, operator.get(), assertedValue.intValue()));
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * Date part comparison with Timestamp Field
     */
    And("^\"([^\"]*)\" data in Timestamp Field \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String stringDatePart, String parameterFieldName, String op, String parameterAssertedValue) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, getTestContainer().getSettings());
        double assertedValue;
        String datePart = stringDatePart.toLowerCase();
        String operator = op.toLowerCase();

        try {
          assertedValue = Double.parseDouble(Settings.resolveParametersString(parameterAssertedValue, getTestContainer().getSettings()));

          if (assertedValue % 1 == 0) LOG.info("Asserted value is: " + (int) assertedValue);
          else LOG.info("Asserted value is: " + assertedValue);

          //TODO: re-consolidate fractional with other date part ops
          if (datePart.contentEquals(FRACTIONAL)) {
            assertTrue(TestUtils.compareFractionalSecondsPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, operator, assertedValue));
          } else {
            assertTrue(TestUtils.compareTimestampPayloadToAssertedDatePartValue(getTestContainer().getResponseData(), datePart, fieldName, operator, (int) assertedValue));
          }
        } catch (Exception ex) {
          fail(getDefaultErrorMessage(ex));
        }
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * String functions
     */
    And("^String data in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String parameterFieldName, String op, String parameterAssertedValue) -> {
      try {
        String fieldName = Settings.resolveParametersString(parameterFieldName, getTestContainer().getSettings());
        String assertedValue = Settings.resolveParametersString(parameterAssertedValue, getTestContainer().getSettings());
        assertTrue(TestUtils.compareStringPayloadToAssertedValue(getTestContainer().getResponseData(), fieldName, op, assertedValue));
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });


    //===============================\\
    //  Metadata validation methods  \\
    //===============================\\

    /*
      Checks that metadata are accessible and contain the resource name specified in generic.resoscript
     */
    And("^the metadata contains the \"([^\"]*)\" resource$", (String parameterResourceName) -> {
      final String resourceName = Settings.resolveParametersString(parameterResourceName, getTestContainer().getSettings());
      AtomicReference<CsdlEntityContainer> entityContainer = new AtomicReference<>();

      try {
        entityContainer.set(TestUtils.findDefaultEntityContainer(getTestContainer().getEdm(), getTestContainer().getXMLMetadata()));

        assertNotNull(getDefaultErrorMessage("server metadata does not contain the given resource name:", resourceName),
            entityContainer.get().getEntitySet(resourceName));

        LOG.info("Found EntityContainer for the given resource: '" + resourceName + "'");

      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * Ensures that the server metadata for the given resource in parameterResourceName contains
     * all of the fields in the given parameterSelectList.
     */
    And("^resource metadata for \"([^\"]*)\" contains the fields in the given select list$", (String parameterResourceName) -> {
      assertTrue(getDefaultErrorMessage("no $select list found for requestId:", getTestContainer().getRequest().getRequestId()),
          getTestContainer().getSelectList().size() > 0);

      try {
        LOG.info("Searching metadata for fields in given select list: " + getTestContainer().getSelectList().toString());
        getTestContainer().getSelectList().forEach(fieldName -> {
          //need to skip the expand field when looking through the metadata
          if (getTestContainer().getExpandField() != null && !fieldName.contentEquals(getTestContainer().getExpandField())) {
            try {
              assertNotNull(getDefaultErrorMessage("Field name '" + fieldName + "' is not present in server metadata!"),
                  getTestContainer().getCsdlProperty(parameterResourceName, fieldName));
              LOG.info("Found: '" + fieldName.trim() + "'");
            } catch (Exception ex) {
              LOG.error(getDefaultErrorMessage(ex));
            }
          }
        });
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }

    });

    /*
     * Finds default Edm entity container at the given Service Root.
     * Note that this assumes the server can process the Accept application/xml header!
     */
    When("^the metadata contains a valid service document$", () -> {
      try {
        assertNotNull(getDefaultErrorMessage("could not find default entity container for given service root:",
            getTestContainer().getServiceRoot()), getTestContainer().getEdm().getEntityContainer());
        LOG.info("Found Default Entity Container: '" + getTestContainer().getEdm().getEntityContainer().getNamespace() + "'");
      } catch (ODataClientErrorException cex) {
        getTestContainer().setResponseCode(cex.getStatusLine().getStatusCode());
        fail(cex.toString());
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * XML Metadata getter
     */
    And("^XML Metadata are requested from the service root in \"([^\"]*)\"$", (String clientSettingsServiceRoot) -> {
      final String serviceRoot = Settings.resolveParametersString(clientSettingsServiceRoot, getTestContainer().getSettings());
      assertEquals(getDefaultErrorMessage("given service root doesn't match the one configured in the Commander"),
          serviceRoot,
          getTestContainer().getCommander().getServiceRoot());

      try {
        if (getTestContainer().fetchXMLMetadata() == null) {
          //force exit rather than allowing the tests to finish
          LOG.error(getDefaultErrorMessage("could not retrieve valid XML Metadata for given service root:", serviceRoot));
          System.exit(NOT_OK);
        }
      } catch (ODataClientErrorException cex) {
        getTestContainer().setResponseCode(cex.getStatusLine().getStatusCode());
        LOG.error(getDefaultErrorMessage(cex));
        System.exit(NOT_OK);
      } catch (Exception ex) {
        LOG.error(getDefaultErrorMessage(ex));
        System.exit(NOT_OK);
      }
    });

    /*
     * Tests whether a navigation property can be found in the given resource name.
     */
    And("^an OData NavigationProperty exists for the given \"([^\"]*)\"$", (String parameterEndpointResource) -> {
      String resourceName = Settings.resolveParametersString(parameterEndpointResource, getTestContainer().getSettings());

      List<CsdlNavigationProperty> navigationProperties
          = TestUtils.findNavigationPropertiesForEntityTypeName(getTestContainer().getEdm(), getTestContainer().getXMLMetadata(), resourceName);

      assertTrue(getDefaultErrorMessage("no navigation properties found for the given '" + resourceName + "' resource!"),
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
    And("^data and type information exist in the results and within the given \"([^\"]*)\"$", (String parameterExpandField) -> {
      String expandField = Settings.resolveParametersString(parameterExpandField, getTestContainer().getSettings());
      assertFalse(getDefaultErrorMessage("no expand field found for", parameterExpandField), expandField.isEmpty());

      ClientEntitySet results = getTestContainer().getCommander().getClient().getRetrieveRequestFactory()
          .getEntitySetRequest(getTestContainer().getRequestUri()).execute().getBody();

      LOG.info("Results count is: " + results.getEntities().size());
      AtomicInteger counter = new AtomicInteger();
      results.getEntities().forEach(clientEntity -> {
        //counter is only used for display and not logic
        if (showResponses) LOG.info("\nRecord #" + counter.getAndIncrement());

        clientEntity.getProperties().forEach(clientProperty -> {
          if (clientProperty.getName().equals(expandField)) {
            // There may be nothing to expand, empty or null is a valid result
            if (clientProperty.hasNullValue()) return;

            assertNotNull(getDefaultErrorMessage("data type could not be found for", clientProperty.getName() + "!",
                "\nCheck the NavigationProperty for your $expand field."), clientProperty.getValue().getTypeName());

            LOG.info("\tExpanded Field Name: " + expandField);
            clientProperty.getValue().asComplex().forEach(expandedClientProperty -> {
              assertNotNull(getDefaultErrorMessage("field name cannot be null!"), expandedClientProperty.getName());
              assertNotNull(getDefaultErrorMessage("data type could not be found for:", expandedClientProperty.getName()),
                  expandedClientProperty.getValue().getTypeName());

              if (expandedClientProperty.getValue().asPrimitive().toValue() != null) {
                LOG.info("\t\tField Name: " + expandedClientProperty.getName());
                LOG.info("\t\tField Value: " + expandedClientProperty.getValue().toString());
                LOG.info("\t\tType Name: " + expandedClientProperty.getValue().getTypeName());
                LOG.info("\t\t" + REPORT_DIVIDER_SMALL);
              }

            });
          } else {
            LOG.info("\tField Name: " + clientProperty.getName());
            LOG.info("\tField Value: " + clientProperty.getValue().toString());
            LOG.info("\tType Name: " + clientProperty.getValue().getTypeName());
            LOG.info("\t" + REPORT_DIVIDER);
          }

        });
      });
    });

    /*
     * Checks to see whether expanding the EndpointResource on ExpandField produces equivalent records from the corresponding
     * resource of the expanded type
     */
    And("^the expanded data were found in the related resource$", () ->
        LOG.info("TODO: this depends on either finding the appropriate navigation property for a given relationship, or having the Expanded resource type name."));


    /*
     * Checks the Standard Resources requirement from Section 2.6 of the Web API specification
     */
    And("^the metadata contains at least one resource from \"([^\"]*)\"$", (String parameterRequiredResourceList) -> {
      String requiredResourceString =
          Settings.resolveParametersString(parameterRequiredResourceList, getTestContainer().getSettings()).replace(SINGLE_SPACE, EMPTY_STRING);
      List<String> requiredResources = Arrays.asList(requiredResourceString.split(FIELD_SEPARATOR));

      LOG.info("Searching the default entity container for one of the following Standard Resources: "
          + requiredResourceString.replace(FIELD_SEPARATOR, PRETTY_FIELD_SEPARATOR));

      AtomicBoolean found = new AtomicBoolean(false);
      requiredResources.forEach(requiredResource -> {
        try {
          if (!found.get())
            found.set(found.get() || getTestContainer().getEdm().getEntityContainer().getEntitySet(requiredResource) != null);
        } catch (Exception ex) {
          fail(getDefaultErrorMessage(ex));
        }
      });

      assertTrue(getDefaultErrorMessage("could not find one of the following Standard Resource Names in the default entity container:",
          requiredResourceString.replace(FIELD_SEPARATOR, PRETTY_FIELD_SEPARATOR)),
          found.get());

      LOG.info("Standard Resource Names requirement met!");
    });

    /*
     * Checks that the resource in Parameter_EndpointResource is within the allowed resources for the
     * latest version of the Data Dictionary, currently 1.7.
     */
    And("^the given \"([^\"]*)\" resource exists within \"([^\"]*)\"$", (String parameterResourceName, String parameterResourceList) -> {
      String resourceName = Settings.resolveParametersString(parameterResourceName, getTestContainer().getSettings()),
          allowedResourceString = Settings.resolveParametersString(parameterResourceList, getTestContainer().getSettings()).replace(" ", "");
      List<String> allowedResources = new ArrayList<>(Arrays.asList(allowedResourceString.split(FIELD_SEPARATOR)));

      LOG.info("Resource Name: " + resourceName);
      LOG.info("Allowed Resources: " + allowedResourceString.replace(FIELD_SEPARATOR, PRETTY_FIELD_SEPARATOR));

      assertTrue(getDefaultErrorMessage("the given resource name", "'" + resourceName + "'",
          "does not exist in the known resources within", "'" + parameterResourceList + "'. "),
          allowedResources.contains(resourceName));
    });


    When("^a GET request is made to the resolved Url in \"([^\"]*)\" using the OData Client$", (String requestId) -> {
      Request request = getTestContainer().getSettings().getRequest(requestId);
      String uriString = Settings.resolveParameters(request, getTestContainer().getSettings()).getRequestUrl();
      assertTrue(getDefaultErrorMessage("the resolved Url in", "'" + requestId + "'", "was invalid!"), uriString != null && uriString.length() > 0);

      LOG.info("Request Id: " + requestId);
      try {
        getTestContainer().setRequest(request);
        getTestContainer().setRequestUri(prepareUri(uriString));
        getTestContainer().setClientEntitySetRequest(getTestContainer().getCommander().getClient().getRetrieveRequestFactory().getEntitySetRequest(getTestContainer().getRequestUri()));
        LOG.info("OData Client Request being made to: " + uriString);
        getTestContainer().setClientEntitySetRequest(getTestContainer().getClientEntitySetRequest());
        getTestContainer().setClientEntitySetResponse(getTestContainer().getClientEntitySetRequest().execute());
        getTestContainer().setResponseCode(getTestContainer().getClientEntitySetResponse().getStatusCode());

        ResWrap<EntityCollection> coll = (getTestContainer().getCommander().getClient().getDeserializer(ContentType.JSON).toEntitySet(getTestContainer().getClientEntitySetResponse().getRawResponse()));
        getTestContainer().setClientEntitySet(getTestContainer().getCommander().getClient().getBinder().getODataEntitySet(coll));
      } catch (ODataClientErrorException cex) {
        getTestContainer().setODataClientErrorException(cex);
        getTestContainer().setResponseCode(cex.getStatusLine().getStatusCode());
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    /*
     * Uses the OData ClientEntitySet rather than raw JSON responses for comparisons
     */
    And("^client entity set Integer data in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$", (String parameterFieldName, String operator, String parameterFieldValue) -> {
      String fieldName = Settings.resolveParametersString(parameterFieldName, getTestContainer().getSettings()),
          op = operator.trim().toLowerCase();

      Integer fieldValue = Integer.parseInt(Settings.resolveParametersString(parameterFieldValue, getTestContainer().getSettings()));
      assertNotNull(fieldValue);

      getTestContainer().getClientEntitySet().getEntities().forEach(entity ->
          assertTrue(compare((Integer) entity.getProperty(fieldName).getValue().asPrimitive().toValue(), op, fieldValue)));

    });

    And("^the OData client response has client entity set data$", () -> {
      assertNotNull(getDefaultErrorMessage("no entity collection returned in response!"), getTestContainer().getClientEntitySet());
      assertTrue(getDefaultErrorMessage("no results returned!"), getTestContainer().getClientEntitySet().getCount() > 0);

      if (showResponses) {
        getTestContainer().getClientEntitySet().getEntities().forEach(entity -> {
          LOG.info("Entity Type is: " + entity.getTypeName());
          entity.getProperties().forEach(property -> LOG.info("\tProperty: " + property.toString()));
        });
      }
    });

    /*
     * Ensures that the server reports one of the currently supported version headers, being 4.0 or 4.01 at the time of writing
     * TODO: add additional items for additional subsequent OData versions, as released
     */
    And("^the server has an OData-Version header value of \"([^\"]*)\" or \"([^\"]*)\"$", (String val1, String val2) -> {
      assertNotNull(getDefaultErrorMessage("must enter a first value"), val1);
      assertNotNull(getDefaultErrorMessage("must enter a second value"), val2);

      assertNotNull(getDefaultErrorMessage("must specify an 'OData-Version' in the response header!"
              + "\nSee: http://docs.oasis-open.org/odata/odata/v4.0/errata03/os/complete/part1-protocol/odata-v4.0-errata03-os-part1-protocol-complete.html#_Toc453752225\n"),
          getTestContainer().getServerODataHeaderVersion());

      LOG.info("Reported OData-Version header value: '" + getTestContainer().getServerODataHeaderVersion() + "'");

      assertTrue(getDefaultErrorMessage("the 'OData-Version' response header must either be", "'" + val1 + "'", "or", "'" + val2 + "'", "(without quotes)."),
          getTestContainer().getServerODataHeaderVersion().contentEquals(val1)
              || getTestContainer().getServerODataHeaderVersion().contentEquals(val2));
    });

    /*
     * Ensures valid metadata have been retrieved from the server
     */
    Given("^valid metadata have been retrieved$", () -> {
      //NOTE: this is here so that tests may be run individually
      if (!getTestContainer().getHaveMetadataBeenRequested()) {
        getTestContainer().getXMLMetadata();
        getTestContainer().validateMetadata();
      }

      if (!getTestContainer().hasValidMetadata()) {
        LOG.error(getDefaultErrorMessage("Valid metadata could not be retrieved from the server! Please check the log for more information."));
        System.exit(NOT_OK);
      }
    });

    /*
     * Skips tests when not Collection of Enumeration data type
     */
    And("^field \"([^\"]*)\" in \"([^\"]*)\" has Collection of Enumeration data type$", (String fieldName, String resourceName) -> {
      assertNotNull(getDefaultErrorMessage("fieldName was null!"), fieldName);
      assertNotNull(getDefaultErrorMessage("resourceName was null!"), resourceName);

      assumeTrue("Skipping Test: using IsFlags enumerations.", useCollections);

      final String
          resolvedFieldName = Settings.resolveParametersString(fieldName, getTestContainer().getSettings()),
          resolvedResourceName = Settings.resolveParametersString(resourceName, getTestContainer().getSettings());

      assertNotNull(getDefaultErrorMessage("resolved field name for parameter", "'" + fieldName + "'", "was null!"), resolvedFieldName);
      assertNotNull(getDefaultErrorMessage("resolved resource name for parameter", "'" + resourceName + "'", "was null!"), resolvedResourceName);

      CsdlProperty csdlProperty = getTestContainer().getCsdlProperty(resolvedResourceName, resolvedFieldName);

      assertNotNull(getDefaultErrorMessage("could not find metadata item for", "'" + resolvedResourceName + "'", "and", "'" + resolvedFieldName + "'!"),
          csdlProperty);

      assertTrue(getDefaultErrorMessage("Field:", "'" + resolvedFieldName + "'",
          "does not have type OData Collection(Edm.EnumType)!"), csdlProperty.isCollection());

      LOG.info("Field '" + resolvedFieldName + "' has OData type Collection(Edm.EnumType)");
    });

  }

  static WebAPITestContainer getTestContainer() {
    return container.get();
  }

  /*
   * Execute Get Request Wrapper
   */
  void prepareAndExecuteRawGetRequest(String requestId) {
    try {
      //reset local state each time a get request is run
      getTestContainer().resetState();

      assertNotNull(getDefaultErrorMessage("request Id cannot be null!"), requestId);

      Request request = getTestContainer().getSettings().getRequest(requestId);

      if (request == null) {
        throw new Exception(getDefaultErrorMessage("request for requestId:", requestId, "was null!"));
      }

      getTestContainer().setRequest(getTestContainer().getSettings().getRequest(requestId));
      LOG.info("Request ID: " + requestId);

      URI requestUri = prepareURI(getTestContainer().getRequest().getRequestUrl());

      //prepare request URI
      getTestContainer().setRequestUri(requestUri);
      LOG.info("Request URI: " + getTestContainer().getRequestUri().toString());

      //execute request
      getTestContainer().executePreparedRawGetRequest();
    } catch (MalformedURLException urlException) {
      LOG.info("Malformed URL was thrown in " + this.getClass() + ": " + urlException.toString()
          + "\nSkipping Request!");
    } catch (Exception ex) {
      LOG.info("Exception was thrown in " + this.getClass() + "!\n" + ex.toString());
    }
  }

  /**
   * Contains background logic - make sure to update if the background changes
   */
  final void runBackground() {

    /*
     * Background
     */
    Given("^a RESOScript file was provided$", () -> {
      if (getTestContainer().getPathToRESOScript() == null) {
        getTestContainer().setPathToRESOScript(System.getProperty("pathToRESOScript"));
        LOG.info("Using RESOScript: " + getTestContainer().getPathToRESOScript());
      }
      assertNotNull(getDefaultErrorMessage("pathToRESOScript must be present in command arguments, see README"), getTestContainer().getPathToRESOScript());
    });

    And("^Client Settings and Parameters were read from the file$", () -> {
      if (getTestContainer().getSettings() == null) {
        getTestContainer().setSettings(Settings.loadFromRESOScript(new File(System.getProperty("pathToRESOScript"))));
        LOG.info("RESOScript loaded successfully!");
      }
      assertNotNull(getDefaultErrorMessage("Settings could not be loaded."), getTestContainer().getSettings());
    });

    Given("^a test container was successfully created from the given RESOScript$", () -> {
      if (!getTestContainer().getIsInitialized()) {
        getTestContainer().initialize();
        if (getTestContainer().getCommander().isAuthTokenClient()) {
          LOG.info("Authentication Type: authorization_code");
        } else if (getTestContainer().getCommander().isOAuth2Client()) {
          LOG.info("Authentication Type: client_credentials");
        }
      }
    });

    /*
     * Ensures that the client either uses Authorization Codes or Client Credentials
     */
    And("^the test container uses an authorization_code or client_credentials for authentication$", () -> {
      assertNotNull(getTestContainer().getCommander());
      assertTrue(getDefaultErrorMessage("Commander must either have a valid Authorization Code or Client Credentials configuration."),
          getTestContainer().getCommander().isAuthTokenClient()
              || (getTestContainer().getCommander().isOAuth2Client() && getTestContainer().getCommander().hasValidAuthConfig()));
    });
  }
}