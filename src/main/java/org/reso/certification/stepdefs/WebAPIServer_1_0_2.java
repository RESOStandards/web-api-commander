package org.reso.certification.stepdefs;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.POJONode;
import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
import org.apache.olingo.commons.api.format.ContentType;
import org.reso.certification.containers.WebAPITestContainer;
import org.reso.commander.Commander;
import org.reso.commander.common.TestUtils;
import org.reso.models.Request;
import org.reso.models.Settings;

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
import static org.reso.certification.containers.WebAPITestContainer.*;
import static org.reso.certification.containers.WebAPITestContainer.ODATA_QUERY_PARAMS.SKIP;
import static org.reso.commander.Commander.*;
import static org.reso.commander.Commander.REPORT_DIVIDER;
import static org.reso.commander.common.ErrorMsg.getAssertResponseCodeErrorMessage;
import static org.reso.commander.common.ErrorMsg.getDefaultErrorMessage;
import static org.reso.commander.common.TestUtils.*;
import static org.reso.commander.common.TestUtils.DateParts.FRACTIONAL;
import static org.reso.commander.common.TestUtils.Operators.*;
import static org.reso.commander.common.TestUtils.Operators.GREATER_THAN_OR_EQUAL;

public class WebAPIServer_1_0_2 {
  private static final Logger LOG = LogManager.getLogger(WebAPIServer_1_0_2.class);

  @Inject
  WebAPITestContainer container;

  /**
   * XML Metadata Getter
   * @param clientSettingsServiceRoot the service root to get metadata from
   */
  @When("XML Metadata are requested from the service root in {string}")
  public void xmlMetadataAreRequestedFromTheServiceRootIn(String clientSettingsServiceRoot) {
    assertNotNull(container);
    assertNotNull("Commander is null!", container.getCommander());

    final String serviceRoot = Settings.resolveParametersString(clientSettingsServiceRoot, container.getSettings());
    assertEquals(getDefaultErrorMessage("given service root doesn't match the one configured in the Commander"),
        serviceRoot,
        container.getCommander().getServiceRoot());

    try {
      assertNotNull(getDefaultErrorMessage("could not find valid XML Metadata for given service root:", serviceRoot),
          container.getXMLMetadata());

    } catch (ODataClientErrorException cex) {
      container.setResponseCode(cex.getStatusLine().getStatusCode());
      fail(getDefaultErrorMessage(cex));
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Test for asserted response codes
   * @param assertedResponseCode the response code to assert
   */
  @Then("the server responds with a status code of {int}")
  public void theServerRespondsWithAStatusCodeOf(Integer assertedResponseCode) {
    try {
      LOG.info("Asserted Response Code: " + assertedResponseCode + ", Server Response Code: " + container.getResponseCode());

      //TODO: clean up logic
      if (container.getResponseCode() != null && assertedResponseCode.intValue() != container.getResponseCode().intValue()) {
        if (container.getODataClientErrorException() != null) {

          if (container.getODataClientErrorException().getODataError().getMessage() != null) {
            LOG.error(getDefaultErrorMessage("Request failed with the following message:",
                container.getODataClientErrorException().getODataError().getMessage()));
          } else if (container.getODataClientErrorException().getMessage() != null) {
            LOG.error(getDefaultErrorMessage("Request failed with the following message:",
                container.getODataClientErrorException().getMessage()));
          }

        } else if (container.getODataServerErrorException() != null) {
          LOG.error(getDefaultErrorMessage("Request failed with the following message:",
              container.getODataServerErrorException().toString()));

          if (container.getODataServerErrorException().toString().contains(String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR))) {
            container.setResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
          }
        }

        //fail for all inner conditions
        fail(getAssertResponseCodeErrorMessage(assertedResponseCode, container.getResponseCode()));
      }

      //if we make it through without failing, things are good
      assertTrue(container.getResponseCode() > 0 && assertedResponseCode > 0);
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Ensures that the server reports one of the given headers
   * @param val1 the first header value
   * @param val2 the second header value
   */
  @And("the server has an OData-Version header value of {string} or {string}")
  public void theServerHasAnODataVersionHeaderValueOfOr(String val1, String val2) {
    assertNotNull("ERROR: must enter a first value", val1);
    assertNotNull("ERROR: must enter a second value", val2);

    assertNotNull("ERROR: must specify an 'OData-Version' in the response header!"
            + "\nSee: http://docs.oasis-open.org/odata/odata/v4.0/errata03/os/complete/part1-protocol/odata-v4.0-errata03-os-part1-protocol-complete.html#_Toc453752225",
        container.getServerODataHeaderVersion());

    LOG.info("Reported OData-Version header value: '" + container.getServerODataHeaderVersion() + "'");

    assertTrue("ERROR: the 'OData-Version' response header must either be '" + val1 + "' or '" + val2 + "' (without quotes).",
        container.getServerODataHeaderVersion().contentEquals(val1)
            || container.getServerODataHeaderVersion().contentEquals(val2));
  }

  /**
   * XML Validation Wrapper
   */
  @And("the XML Metadata response is valid XML")
  public void theXMLMetadataResponseIsValidXML() {
    assertNotNull(getDefaultErrorMessage("no XML Response data were found!"), container.getXMLResponseData());
    container.validateXMLMetadataXML();
    assertTrue("ERROR: invalid XML response!", container.getIsValidXMLMetadataXML());
  }

  /**
   * Gets XML Metadata from the server and validates them
   * @throws Exception if the metadata cannot be fetched and validated
   */
  @And("the XML Metadata returned by the server are valid")
  public void theXMLMetadataReturnedByTheServerAreValid() throws Exception {
    if (!container.getHaveMetadataBeenRequested()) {
      //will lazy-load metadata from the server if not yet requested
      container.getXMLMetadata();
    }
    container.validateMetadata();
    assertTrue("XML Metadata at the given service root is not valid! " + container.getServiceRoot(),
        container.getIsValidXMLMetadata());
  }

  /**
   * Checks that the given XML Metadata (EDMX) contain an Entity Data Model (Edm)
   * @throws Exception if the given XML Metadata could not be processed
   */
  @And("the XML Metadata returned by the server contains Edm metadata")
  public void theXMLMetadataReturnedByTheServerContainsEdmMetadata() throws Exception {
    container.setEdm(Commander.deserializeEdm(container.getXMLResponseData(), container.getCommander().getClient()));
    assertNotNull(getDefaultErrorMessage("Edm de-serialized to an empty object!"), container.getEdm());
  }

  /**
   * Entity Data Model (Edm) validator
   */
  @And("the Edm metadata returned by the server are valid")
  public void theEdmMetadataReturnedByTheServerAreValid() {
    assertTrue("Edm Metadata at the given service root is not valid! " + container.getServiceRoot(),
        container.getIsValidEdm());
  }

  /**
   * Finds default Edm entity container at the given Service Root
   */
  @And("the metadata contains a valid service document")
  public void theMetadataContainsAValidServiceDocument() {
    try {
      assertNotNull("ERROR: could not find default entity container for given service root: " +
          container.getServiceRoot(), container.getEdm().getEntityContainer());
      LOG.info("Found Default Entity Container: '" + container.getEdm().getEntityContainer().getNamespace() + "'");
    } catch (ODataClientErrorException cex) {
      container.setResponseCode(cex.getStatusLine().getStatusCode());
      fail(cex.toString());
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Checks that the resource in Parameter_EndpointResource is within the allowed resources for the
   * latest version of the Data Dictionary, currently 1.7.
   * @param parameterResourceName the resource name parameter
   * @param parameterResourceList the resource list parameter
   */
  @And("the given {string} resource exists within {string}")
  public void theGivenResourceExistsWithin(String parameterResourceName, String parameterResourceList) {
    String
        resourceName = Settings.resolveParametersString(parameterResourceName, container.getSettings()),
        allowedResourceString = Settings.resolveParametersString(parameterResourceList, container.getSettings()).replace(" ", "");
    List<String> allowedResources = new ArrayList<>(Arrays.asList(allowedResourceString.split(FIELD_SEPARATOR)));

    LOG.info("Resource Name: " + resourceName);
    LOG.info("Allowed Resources: " + allowedResourceString.replace(FIELD_SEPARATOR, PRETTY_FIELD_SEPARATOR));

    assertTrue("ERROR: the given resource name '" + resourceName + "' does not exist in the known resources within '" + parameterResourceList + "'. ",
        allowedResources.contains(resourceName));
  }

  /**
   * Checks that metadata are accessible and contain the resource name specified in generic.resoscript
   * @param parameterResourceName the resource name parameter to check
   */
  @And("the metadata contains the {string} resource")
  public void theMetadataContainsTheResource(String parameterResourceName) {
    final String resourceName = Settings.resolveParametersString(parameterResourceName, container.getSettings());
    AtomicReference<CsdlEntityContainer> entityContainer = new AtomicReference<>();

    try {
      entityContainer.set(TestUtils.findDefaultEntityContainer(container.getEdm(), container.getXMLMetadata()));

      assertNotNull("ERROR: server metadata does not contain the given resource name: " + resourceName,
          entityContainer.get().getEntitySet(resourceName));

      LOG.info("Found EntityContainer for the given resource: '" + resourceName + "'");

    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Checks the Standard Resources requirement from Section 2.6 of the Web API specification
   * @param parameterRequiredResourceList the required resource list parameter to check
   */
  @And("the metadata contains at least one resource from {string}")
  public void theMetadataContainsAtLeastOneResourceFrom(String parameterRequiredResourceList) {
    String requiredResourceString =
        Settings.resolveParametersString(parameterRequiredResourceList, container.getSettings()).replace(SINGLE_SPACE, EMPTY_STRING);
    List<String> requiredResources = Arrays.asList(requiredResourceString.split(FIELD_SEPARATOR));

    LOG.info("Searching the default entity container for one of the following Standard Resources (Web API 1.0.2 Section 2.6): "
        + requiredResourceString.replace(FIELD_SEPARATOR, PRETTY_FIELD_SEPARATOR));

    AtomicBoolean found = new AtomicBoolean(false);
    requiredResources.forEach(requiredResource -> {
      try {
        if (!found.get())
          found.set(found.get() || container.getEdm().getEntityContainer().getEntitySet(requiredResource) != null);
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    });

    assertTrue("ERROR: could not find one of the following Standard Resource Names in the default entity container: " + requiredResourceString.replace(FIELD_SEPARATOR, PRETTY_FIELD_SEPARATOR),
        found.get());

    LOG.info("Standard Resource Names requirement met!");
  }

  /**
   * Allows metadata-dependent tests to be run individually
   * @throws Exception if the metadata cannot be retrieved and validated
   */
  @Given("valid metadata have been retrieved")
  public void validMetadataHaveBeenRetrieved() throws Exception {
    //NOTE: this is here so that tests may be run individually
    if (!container.getHaveMetadataBeenRequested()) {
      container.getXMLMetadata();
      container.validateMetadata();
    }
    assertTrue(getDefaultErrorMessage("Valid metadata could not be retrieved from the server! Please check the log for more information."),
        container.hasValidMetadata());
  }

  /**
   * GET request by requirementId (see generic.resoscript)
   * @param requestId the id of the request to run
   */
  @When("a GET request is made to the resolved Url in {string}")
  public void aGETRequestIsMadeToTheResolvedUrlIn(String requestId) {
    this.prepareAndExecuteRawGetRequest(requestId);
  }

  /**
   * JSON validation wrapper
   */
  @And("the response is valid JSON")
  public void theResponseIsValidJSON() {
    container.validateJSON();
  }

  /**
   * True if response has results, meaning value.length > 0
   */
  @And("the response has results")
  public void theResponseHasResults() {
    try {
      assertTrue("ERROR: no results were found in the '" + JSON_VALUE_PATH + "' path of the JSON response!",
          from(container.getResponseData()).getList(JSON_VALUE_PATH, Map.class).size() > 0);
      LOG.info("Results count is: " + from(container.getResponseData()).getList(JSON_VALUE_PATH, Map.class).get(0).size());
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * REQ-WA103-END2 - validate DataSystem endpoint, if present
   */
  @And("the results match the expected DataSystem JSON schema")
  public void theResultsMatchTheExpectedDataSystemJSONSchema() {
    container.validateDataSystem();
    assertEquals("ERROR: JSON Schema validation produced errors!", 0, container.getSchemaValidationErrors().size());
  }

  /**
   * True if data are present in the response
   * @param parameterFieldName the field name to check for response data
   */
  @And("the response has singleton results in {string}")
  public void theResponseHasSingletonResultsIn(String parameterFieldName) {
    try {
      String value = Settings.resolveParametersString(parameterFieldName, container.getSettings());
      boolean isPresent = from(container.getResponseData()).get() != null;
      assertTrue("ERROR: singleton results not found for '" + value + "'!", isPresent);
      LOG.info("Data are present and response value is: " + value);
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * REQ-WA103-QR1 - Ensures that the given Unique Id Field has the given Unique Id Value
   * @param parameterUniqueIdValue the unique Id value to check
   * @param parameterUniqueId the unique Id field to check
   */
  @And("the provided {string} is returned in {string}")
  public void theProvidedIsReturnedIn(String parameterUniqueIdValue, String parameterUniqueId) {
    try {
      String expectedValueAsString = Settings.resolveParametersString(parameterUniqueIdValue, container.getSettings());
      Object resolvedValue = from(container.getResponseData())
          .get(Settings.resolveParametersString(parameterUniqueId, container.getSettings()));

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
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Ensures that the server metadata for the given resource in parameterResourceName contains
   * all of the fields in the given parameterSelectList
   */
  @And("resource metadata contain the fields in the given select list")
  public void resourceMetadataContainsTheFieldsInTheGivenSelectList() {
    try {
      LOG.info("Searching metadata for fields in given select list: " + container.getSelectList().toString());
      container.getSelectList().forEach(fieldName -> {
        //need to skip the expand field when looking through the metadata
        if (container.getExpandField() == null || !fieldName.contentEquals(container.getExpandField())) {
          try {
            assertNotNull("ERROR: Field name '" + fieldName + "' is not present in server metadata!", container.getCsdlForFieldName(fieldName));
            LOG.info("Found: '" + fieldName.trim() + "'");
          } catch (Exception ex) {
            LOG.error(getDefaultErrorMessage(ex));
          }
        }
      });
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * REQ-WA103-QR3 - $select
   */
  @And("data are present for fields contained within the given select list")
  public void dataArePresentForFieldsContainedWithinTheGivenSelectList() {
    try {
      AtomicInteger numFieldsWithData = new AtomicInteger();

      DecimalFormat df = new DecimalFormat();
      df.setMaximumFractionDigits(1);
      double fill;

      assertNotNull("ERROR: no fields found within the given $select list. Check request Id: " + container.getRequest().getRequestId() + " in your .resoscript file!",
          container.getSelectList());

      LOG.info(QueryOption.SELECT + " list is: " + container.getSelectList());

      AtomicInteger numResults = new AtomicInteger();
      //iterate over the items and count the number of fields with data to determine whether there are data present
      from(container.getResponseData()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
        numResults.getAndIncrement();
        container.getSelectList().forEach(field -> {
          if (item.get(field) != null) {
            numFieldsWithData.getAndIncrement();
          }
        });
      });

      LOG.info("Number of Results: " + numResults.get());
      LOG.info("Number of Fields: " + container.getSelectList().size());
      LOG.info("Fields with Data: " + numFieldsWithData.get());

      if (numResults.get() > 0 && container.getSelectList().size() > 0) {
        fill = ((100.0 * numFieldsWithData.get()) / (numResults.get() * container.getSelectList().size()));
        LOG.info("Percent Fill: " + df.format(fill) + "%");
      } else {
        LOG.info("Percent Fill: 0% - no fields with data found!");
      }
      assertTrue("ERROR: no fields with data could be found from the given $select list!", numFieldsWithData.get() > 0);
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * True if results count less than or equal to limit
   * @param limitParameter the parameter containing the limit to test
   */
  @And("the number of results is less than or equal to {string}")
  public void theNumberOfResultsIsLessThanOrEqualTo(String limitParameter) {
    try {
      int count = from(container.getResponseData()).getList(JSON_VALUE_PATH, HashMap.class).size(),
          limit = Integer.parseInt(Settings.resolveParametersString(limitParameter, container.getSettings()));
      LOG.info("Results count is: " + count + ", Limit is: " + limit);
      assertTrue("ERROR: number of results exceeds that specified in '" + limitParameter + "'!", count <= limit);
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Makes a GET Request for the given request Id so that skip may be tested.
   * REQ-WA103-QR5 - $skip
   * @param requestId the request Id to test skip on
   * @param skipCountParam the skip count parameter to be used
   */
  @And("a GET request is made to the resolved Url in {string} with $skip={string}")
  public void aGETRequestIsMadeToTheResolvedUrlInWithSkip(String requestId, String skipCountParam) {
    try {
      int skipCount = Integer.parseInt(Settings.resolveParametersString(skipCountParam, container.getSettings()));
      LOG.info("Skip count is: " + skipCount);

      container.setRequest(requestId);

      //preserve initial response data for later comparisons
      container.setInitialResponseData(container.getResponseData());

      //TODO: convert to OData filter factory
      container.setRequestUri(Commander.prepareURI(
          Settings.resolveParameters(container.getSettings().getRequest(requestId), container.getSettings()).getUrl()
              + AMPERSAND + SKIP + EQUALS + skipCount));
      container.executePreparedRawGetRequest();
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Tests that data in the given unique Id field are different between two pages of data.
   * Requires that the data have been previously fetched
   * @param parameterUniqueId the unique Id field to compare results with
   */
  @And("data in the {string} fields are different in the second request than in the first")
  public void dataInTheFieldsAreDifferentInTheSecondRequestThanInTheFirst(String parameterUniqueId) {
    try {
      List<POJONode> l1 = from(container.getInitialResponseData()).getJsonObject(JSON_VALUE_PATH);
      List<POJONode> l2 = from(container.getResponseData()).getJsonObject(JSON_VALUE_PATH);

      int combinedCount = l1.size() + l2.size();
      Set<POJONode> combined = new LinkedHashSet<>(l1);

      new POJONode(l1);
      if (container.getShowResponses()) LOG.info("Response Page 1: " + l1);

      combined.addAll(l2);
      new POJONode(l2);
      if (container.getShowResponses()) LOG.info("Response Page 2: " + l2);

      assertEquals("ERROR: repeated data found, expected unique data on each page!", combinedCount, combined.size());
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Assert HTTP Response Code given asserted OData version
   * @param assertedHttpResponseCode the Integer response code to assert
   * @param assertedODataVersion the version that response code is supposed to apply to
   */
  @Then("the server responds with a status code of {int} if the server reports OData-Version {string}")
  public void theServerRespondsWithAStatusCodeOfIfTheServerReportsODataVersion(Integer assertedHttpResponseCode, String assertedODataVersion) {
    try {
      boolean versionsMatch = container.getServerODataHeaderVersion().equals(assertedODataVersion),
          responseCodesMatch = container.getResponseCode().intValue() == assertedHttpResponseCode.intValue();

      LOG.info("Asserted OData Version: " + assertedODataVersion + ", Server Version: " + container.getServerODataHeaderVersion());

      if (versionsMatch) {
        LOG.info("Asserted Response Code: " + assertedHttpResponseCode + ", Response code: " + container.getResponseCode());
        assertTrue("ERROR: asserted response code (" + assertedHttpResponseCode + ") does not match the one returned from the server (" + container.getResponseCode() + ") !", responseCodesMatch);
      } else {
        LOG.info("Test skipped! Only applies when the asserted version matches the reported server version.");
      }
    } catch (Exception ex) {
      //Don't fail tests like in other cases because get requests may generate exceptions that we want to
      //continue past and test. Log exceptions to DEBUG instead.
      LOG.debug(ex.toString());
    }
  }

  /**
   * Compares field data (LHS) to a given parameter value (RHS). The operator is passed as a string,
   * and is used to select among the supported comparisons.
   * @param parameterFieldName the field name with data to compare on the LHS
   * @param op the binary operator to be used for comparisons
   * @param parameterAssertedValue the asserted value to use on the RHS
   */
  @And("Integer data in {string} {string} {string}")
  public void integerDataIn(String parameterFieldName, String op, String parameterAssertedValue) {
    try {
      String fieldName = Settings.resolveParametersString(parameterFieldName, container.getSettings());
      int assertedValue = Integer.parseInt(Settings.resolveParametersString(parameterAssertedValue, container.getSettings()));

      LOG.info("fieldName: " + fieldName + ", op: " + op + ", assertedValue: " + assertedValue);
      assertTrue(TestUtils.compareIntegerPayloadToAssertedValue(container.getResponseData(), fieldName, op, assertedValue));
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * True if data in the lhs expression and rhs expressions pass the AND or OR condition given in andOrOp
   * @param parameterFieldName the field name to test
   * @param opLhs the binary operator for the LHS comparison
   * @param parameterAssertedLhsValue the asserted LHS value
   * @param andOrOp 'and' or 'or', depending on whether the LHS and RHS are AND'd or ORd together
   * @param opRhs the binary operator to use for the RHS comparison
   * @param parameterAssertedRhsValue the asserted RHS value
   */
  @And("Integer data in {string} {string} {string} {string} {string} {string}")
  public void integerDataIn(String parameterFieldName, String opLhs, String parameterAssertedLhsValue, String andOrOp, String opRhs, String parameterAssertedRhsValue) {
    try {
      String fieldName = Settings.resolveParametersString(parameterFieldName, container.getSettings());
      Integer assertedLhsValue = Integer.parseInt(Settings.resolveParametersString(parameterAssertedLhsValue, container.getSettings())),
          assertedRhsValue = Integer.parseInt(Settings.resolveParametersString(parameterAssertedRhsValue, container.getSettings()));

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
      from(container.getResponseData()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
        lhsValue.set(Integer.parseInt(item.get(fieldName).toString()));
        rhsValue.set(Integer.parseInt(item.get(fieldName).toString()));


        lhsResult.set(compare(lhsValue.get(), opLhs, assertedLhsValue));
        rhsResult.set(compare(rhsValue.get(), opRhs, assertedRhsValue));

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
  }

  /**
   * Date Comparison glue
   * @param parameterFieldName date field to compare
   * @param op binary date comparison operator
   * @param parameterAssertedValue asserted value to compare
   */
  @And("Date data in {string} {string} {string}")
  public void dateDataIn(String parameterFieldName, String op, String parameterAssertedValue) {
    try {
      String fieldName = Settings.resolveParametersString(parameterFieldName, container.getSettings());
      AtomicReference<Date> fieldValue = new AtomicReference<>();
      AtomicReference<Date> assertedValue = new AtomicReference<>();

      assertedValue.set(TestUtils.parseDateFromEdmDateString(Settings.resolveParametersString(parameterAssertedValue, container.getSettings())));
      LOG.info("Asserted value is: " + assertedValue.get().toString());

      from(container.getResponseData()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
        try {
          fieldValue.set(TestUtils.parseDateFromEdmDateTimeOffsetString(item.get(fieldName).toString()));
          assertTrue(compare(fieldValue.get(), op, assertedValue.get()));
        } catch (Exception ex) {
          fail(getDefaultErrorMessage(ex));
        }
      });
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Time comparison glue
   * @param parameterFieldName time of day field to compare
   * @param op binary time of day comparison operator
   * @param parameterAssertedValue asserted value to compare
   */
  @And("TimeOfDay data in {string} {string} {string}")
  public void timeOfDayDataIn(String parameterFieldName, String op, String parameterAssertedValue) {
    try {
      String fieldName = Settings.resolveParametersString(parameterFieldName, container.getSettings());
      AtomicReference<Time> fieldValue = new AtomicReference<>();
      AtomicReference<Time> assertedValue = new AtomicReference<>();

      assertedValue.set(TestUtils.parseTimeOfDayFromEdmTimeOfDayString(Settings.resolveParametersString(parameterAssertedValue, container.getSettings())));
      LOG.info("Asserted value is: " + assertedValue.get().toString());

      from(container.getResponseData()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
        try {
          fieldValue.set(TestUtils.parseTimeOfDayFromEdmDateTimeOffsetString(item.get(fieldName).toString()));
          assertTrue(compare(fieldValue.get(), op, assertedValue.get()));
        } catch (Exception ex) {
          LOG.error(ex.toString());
        }
      });
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Timestamp comparison glue
   * @param parameterFieldName date time offset field to compare
   * @param op binary date time offset comparison operator
   * @param parameterAssertedValue asserted value to compare
   */
  @And("DateTimeOffset data in {string} {string} {string}")
  public void dateTimeOffsetDataIn(String parameterFieldName, String op, String parameterAssertedValue) {
    try {
      TestUtils.assertDateTimeOffset(parameterFieldName, op, parameterAssertedValue, container.getResponseData(), container.getSettings());
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Timestamp comparison to now()
   * @param parameterFieldName date time offset field to compare
   * @param op binary date time offset comparison operator
   */
  @And("DateTimeOffset data in {string} {string} now")
  public void dateTimeOffsetDataInNow(String parameterFieldName, String op) {
    dateTimeOffsetDataIn(parameterFieldName, op, Timestamp.from(Instant.now()).toString());
  }

  /**
   * Single-Valued enumerations
   * @param parameterFieldName single-valued enumeration field to compare
   * @param parameterAssertedValue asserted value to compare
   */
  @And("Single Valued Enumeration Data in {string} has {string}")
  public void singleValuedEnumerationDataInHas(String parameterFieldName, String parameterAssertedValue) {
    try {
      String fieldName = Settings.resolveParametersString(parameterFieldName, container.getSettings());
      AtomicReference<String> fieldValue = new AtomicReference<>();
      AtomicReference<String> assertedValue = new AtomicReference<>();

      AtomicBoolean result = new AtomicBoolean(false);

      assertedValue.set(Settings.resolveParametersString(parameterAssertedValue, container.getSettings()));
      LOG.info("Asserted value is: " + assertedValue.get());

      from(container.getResponseData()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
        fieldValue.set(item.get(fieldName).toString());
        result.set(fieldValue.get().contentEquals(assertedValue.get()));
        LOG.info("Assert True: " + fieldValue.get() + " equals " + assertedValue.get() + " ==> " + result.get());
        assertTrue(result.get());
      });
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Multi-valued enumerations testing.
   * TODO: turn into String array
   * @param parameterFieldName multi-valued enumeration field to compare
   * @param parameterAssertedValue asserted value to compare
   */
  @And("Multiple Valued Enumeration Data in {string} has {string}")
  public void multipleValuedEnumerationDataInHas(String parameterFieldName, String parameterAssertedValue) {
    try {
      String fieldName = Settings.resolveParametersString(parameterFieldName, container.getSettings());
      AtomicReference<String> fieldValue = new AtomicReference<>();
      AtomicReference<String> assertedValue = new AtomicReference<>();

      AtomicBoolean result = new AtomicBoolean(false);

      assertedValue.set(Settings.resolveParametersString(parameterAssertedValue, container.getSettings()));
      LOG.info("Asserted value is: " + assertedValue.get());

      from(container.getResponseData()).getList(JSON_VALUE_PATH, ObjectNode.class).forEach(item -> {
        fieldValue.set(item.get(fieldName).toString());
        result.set(fieldValue.get().contains(assertedValue.get()));
        LOG.info("Assert True: " + fieldValue.get() + " has " + assertedValue.get() + " ==> " + result.get());
        assertTrue(result.get());
      });
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Date comparison ordering (asc, desc)
   * @param parameterFieldName the field name to check for ordering
   * @param parameterOrderByDirection the direction to assert, either 'asc' or 'desc'
   */
  @And("DateTimeOffset data in {string} is sorted in {string} order")
  public void dateTimeOffsetDataInIsSortedInOrder(String parameterFieldName, String parameterOrderByDirection) {
    try {
      String fieldName = Settings.resolveParametersString(parameterFieldName, container.getSettings());
      final String ASC = "asc", DESC = "desc";
      AtomicReference<String> orderBy = new AtomicReference<>(parameterOrderByDirection.toLowerCase());

      assertTrue(orderBy.get().equals(ASC) || orderBy.get().equals(DESC));

      //used to store the last value for comparisons
      AtomicReference<Timestamp> initialValue = new AtomicReference<>();
      AtomicReference<Timestamp> currentValue = new AtomicReference<>();
      AtomicInteger count = new AtomicInteger(0);

      from(container.getResponseData()).getList(JSON_VALUE_PATH, HashMap.class).forEach(item -> {
        try {
          if (count.get() == 0) {
            initialValue.set(TestUtils.parseTimestampFromEdmDateTimeOffsetString(item.get(fieldName).toString()));
          } else {
            currentValue.set(TestUtils.parseTimestampFromEdmDateTimeOffsetString(item.get(fieldName).toString()));
            if (orderBy.get().equals(ASC)) {
              assertTrue(compare(initialValue.get(), LESS_THAN_OR_EQUAL, currentValue.get()));
            } else if (orderBy.get().equals(DESC)) {
              assertTrue(compare(initialValue.get(), GREATER_THAN_OR_EQUAL, currentValue.get()));
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
  }

  /**
   * Date Field comparisons
   * @param stringDatePart the date part of the field to compare
   * @param parameterFieldName the field name to compare
   * @param op binary date part operator
   * @param parameterAssertedValue asserted value to compare
   */
  @And("{string} data in Date Field {string} {string} {string}")
  public void dataInDateField(String stringDatePart, String parameterFieldName, String op, String parameterAssertedValue) {
    String fieldName = Settings.resolveParametersString(parameterFieldName, container.getSettings());
    AtomicReference<Integer> fieldValue = new AtomicReference<>();
    AtomicInteger assertedValue = new AtomicInteger();
    AtomicReference<String> datePart = new AtomicReference<>(stringDatePart.toLowerCase());
    AtomicReference<String> operator = new AtomicReference<>(op.toLowerCase());

    try {
      assertedValue.set(Integer.parseInt(Settings.resolveParametersString(parameterAssertedValue, container.getSettings())));
      LOG.info("Asserted value is: " + assertedValue.get());
      assertTrue(TestUtils.compareDatePayloadToAssertedDatePartValue(container.getResponseData(), datePart.get(), fieldName, operator.get(), assertedValue.intValue()));
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Date part comparison with Timestamp Field
   * @param stringDatePart the date part of the field to compare
   * @param parameterFieldName the field name to compare
   * @param op binary date part operator
   * @param parameterAssertedValue asserted value to compare
   */
  @And("{string} data in Timestamp Field {string} {string} {string}")
  public void dataInTimestampField(String stringDatePart, String parameterFieldName, String op, String parameterAssertedValue) {
    try {
      String fieldName = Settings.resolveParametersString(parameterFieldName, container.getSettings());
      double assertedValue;
      String datePart = stringDatePart.toLowerCase();
      String operator = op.toLowerCase();

      try {
        assertedValue = Double.parseDouble(Settings.resolveParametersString(parameterAssertedValue, container.getSettings()));

        if (assertedValue % 1 == 0) LOG.info("Asserted value is: " + (int) assertedValue);
        else LOG.info("Asserted value is: " + assertedValue);

        //TODO: re-consolidate fractional with other date part ops
        if (datePart.contentEquals(FRACTIONAL)) {
          assertTrue(TestUtils.compareFractionalSecondsPayloadToAssertedValue(container.getResponseData(), fieldName, operator, assertedValue));
        } else {
          assertTrue(TestUtils.compareTimestampPayloadToAssertedDatePartValue(container.getResponseData(), datePart, fieldName, operator, (int) assertedValue));
        }
      } catch (Exception ex) {
        fail(getDefaultErrorMessage(ex));
      }
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Tests parameter field name with op to determine whether asserted value is true
   * @param parameterFieldName the field name to test
   * @param op binary string operator
   * @param parameterAssertedValue asserted value to compare
   */
  @And("String data in {string} {string} {string}")
  public void stringDataIn(String parameterFieldName, String op, String parameterAssertedValue) {
    try {
      String fieldName = Settings.resolveParametersString(parameterFieldName, container.getSettings());
      String assertedValue = Settings.resolveParametersString(parameterAssertedValue, container.getSettings());
      assertTrue(TestUtils.compareStringPayloadToAssertedValue(container.getResponseData(), fieldName, op, assertedValue));
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Checks to see whether the expanded field has data
   * @param parameterExpandField the expanded field name to check
   */
  @And("data and type information exist in the results and within the given {string}")
  public void dataAndTypeInformationExistInTheResultsAndWithinTheGiven(String parameterExpandField) {
    String expandField = Settings.resolveParametersString(parameterExpandField, container.getSettings());
    assertFalse("ERROR: no expand field found for " + parameterExpandField, expandField.isEmpty());

    ClientEntitySet results = container.getCommander().getClient().getRetrieveRequestFactory()
        .getEntitySetRequest(container.getRequestUri()).execute().getBody();

    LOG.info("Results count is: " + results.getEntities().size());
    AtomicInteger counter = new AtomicInteger();
    results.getEntities().forEach(clientEntity -> {
      //counter is only used for display and not logic
      if (container.getShowResponses()) LOG.info("\nRecord #" + counter.getAndIncrement());

      clientEntity.getProperties().forEach(clientProperty -> {
        if (clientProperty.getName().equals(expandField)) {
          // There may be nothing to expand, empty or null is a valid result
          if (clientProperty.hasNullValue()) return;

          assertNotNull("ERROR: data type could not be found for " + clientProperty.getName() + "! "
              + "\nCheck the NavigationProperty for your $expand field.", clientProperty.getValue().getTypeName());

          LOG.info("\tExpanded Field Name: " + expandField);
          clientProperty.getValue().asComplex().forEach(expandedClientProperty -> {
            assertNotNull("ERROR: field name cannot be null!", expandedClientProperty.getName());
            assertNotNull("ERROR: data type could not be found for: " + expandedClientProperty.getName(),
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
  }

  /**
   * Tests whether a navigation property can be found in the given resource name.
   * @param parameterEndpointResource the endpoint resource parameter to test
   * @throws Exception if metadata could not be parsed for the given server
   */
  @And("an OData NavigationProperty exists for the given {string}")
  public void anODataNavigationPropertyExistsForTheGiven(String parameterEndpointResource) throws Exception {
    String resourceName = Settings.resolveParametersString(parameterEndpointResource, container.getSettings());

    List<CsdlNavigationProperty> navigationProperties
        = TestUtils.findNavigationPropertiesForEntityTypeName(container.getEdm(), container.getXMLMetadata(), resourceName);

    assertTrue("ERROR: no navigation properties found for the given '" + resourceName + "' resource!",
        navigationProperties.size() > 0);

    LOG.info("Found the following Navigation Properties:");
    navigationProperties.forEach(csdlNavigationProperty -> {
      LOG.info("\tName: " + csdlNavigationProperty.getName());
      LOG.info("\tType: " + csdlNavigationProperty.getType());
    });
  }

  /**
   * Checks to see whether expanding the EndpointResource on ExpandField produces equivalent records from the corresponding
   * resource of the expanded type
   */
  @And("the expanded data were found in the related resource")
  public void theExpandedDataWereFoundInTheRelatedResource() {
    LOG.info("TODO: this depends on either finding the appropriate navigation property for a given relationship, or having the Expanded resource type name.");
  }

  /**
   * Makes a get request to the resolved URL in the given request Id
   * @param requestId the request Id that contains the URL to resolve
   */
  @When("a GET request is made to the resolved Url in {string} using the OData Client")
  public void aGETRequestIsMadeToTheResolvedUrlInUsingTheODataClient(String requestId) {
    Request request = container.getSettings().getRequest(requestId);
    String uriString = Settings.resolveParameters(request, container.getSettings()).getUrl();
    assertTrue("ERROR: the resolved Url in '" + requestId + "' was invalid!", uriString != null && uriString.length() > 0);

    LOG.info("Request Id: " + requestId);
    try {
      container.setRequest(request);
      container.setRequestUri(prepareUri(uriString));
      container.setClientEntitySetRequest(container.getCommander().getClient().getRetrieveRequestFactory().getEntitySetRequest(container.getRequestUri()));
      LOG.info("OData Client Request being made to: " + uriString);
      container.setClientEntitySetRequest(container.getClientEntitySetRequest());
      container.setClientEntitySetResponse(container.getClientEntitySetRequest().execute());
      container.setResponseCode(container.getClientEntitySetResponse().getStatusCode());

      ResWrap<EntityCollection> coll = (container.getCommander().getClient().getDeserializer(ContentType.JSON).toEntitySet(container.getClientEntitySetResponse().getRawResponse()));
      container.setClientEntitySet(container.getCommander().getClient().getBinder().getODataEntitySet(coll));
    } catch (ODataClientErrorException cex) {
      container.setODataClientErrorException(cex);
      container.setResponseCode(cex.getStatusLine().getStatusCode());
    } catch (Exception ex) {
      fail(getDefaultErrorMessage(ex));
    }
  }

  /**
   * Determines whether the OData response contains Entity Set data
   */
  @And("the OData client response has client entity set data")
  public void theODataClientResponseHasClientEntitySetData() {
    assertNotNull("ERROR: no entity collection returned in response!", container.getClientEntitySet());
    assertTrue("ERROR: no results returned!", container.getClientEntitySet().getCount() > 0);

    if (container.getShowResponses()) {
      container.getClientEntitySet().getEntities().forEach(entity -> {
        LOG.info("Entity Type is: " + entity.getTypeName());
        entity.getProperties().forEach(property -> LOG.info("\tProperty: " + property.toString()));
      });
    }
  }

  /**
   * Uses the OData ClientEntitySet rather than raw JSON responses for comparisons
   * @param parameterFieldName the field name to test
   * @param operator binary string operator
   * @param parameterAssertedValue asserted value to compare
   */
  @And("client entity set Integer data in {string} {string} {string}")
  public void clientEntitySetIntegerDataIn(String parameterFieldName, String operator, String parameterAssertedValue) {
    String fieldName = Settings.resolveParametersString(parameterFieldName, container.getSettings()),
        op = operator.trim().toLowerCase();

    Integer fieldValue = Integer.parseInt(Settings.resolveParametersString(parameterAssertedValue, container.getSettings()));
    assertNotNull(fieldValue);

    container.getClientEntitySet().getEntities().forEach(entity ->
        assertTrue(compare((Integer) entity.getProperty(fieldName).getValue().asPrimitive().toValue(), op, fieldValue)));
  }


  /*
   * Execute Get Request Wrapper
   */
  private void prepareAndExecuteRawGetRequest(String requestId) {
    try {
      //reset local state each time a get request is run
      container.resetState();

      assertNotNull("ERROR: request Id cannot be null!", requestId);
      container.setRequest(container.getSettings().getRequest(requestId));
      LOG.info("Request ID: " + requestId);

      //prepare request URI
      container.setRequestUri(Commander.prepareURI(Settings.resolveParameters(
          container.getSettings().getRequest(requestId), container.getSettings()).getUrl()));
      LOG.info("Request URI: " + container.getRequestUri().toString());

      //execute request
      container.executePreparedRawGetRequest();
    } catch (Exception ex) {
      LOG.info("Exception was thrown in " + this.getClass() + ": " + ex.toString());
    }
  }

}
