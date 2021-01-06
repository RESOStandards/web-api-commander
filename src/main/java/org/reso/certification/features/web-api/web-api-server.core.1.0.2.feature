Feature: Web API Server 1.0.2 Core Certification
  All Scenarios passing means the given Web API server is fully-compliant with the RESO Core Specification.
  # SEE: https://docs.google.com/document/d/1btCduOpWWzeadeMcSviA8M9dclIz23P-bPUGKwcD0NY/edit?usp=sharing

  Background:
    Given a RESOScript file was provided
    And Client Settings and Parameters were read from the file
    And a test container was successfully created from the given RESOScript
    And the test container uses an authorization_code or client_credentials for authentication

  @metadata-validation @2.4.1
  Scenario: metadata-validation - Request and Validate Server Metadata
    When XML Metadata are requested from the service root in "ClientSettings_WebAPIURI"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the XML Metadata response is valid XML
    And the XML Metadata returned by the server are valid
    And the XML Metadata returned by the server contains Edm metadata
    And the Edm metadata returned by the server are valid
    And the metadata contains a valid service document
    And the given "Parameter_EndpointResource" resource exists within "Parameter_DD17_WellKnownResourceList"
    And the metadata contains the "Parameter_EndpointResource" resource
    And the metadata contains at least one resource from "Parameter_WebAPI102_RequiredResourceList"

  @service-document @2.4.1
  Scenario: service-document - Service Document Request
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "service-document"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON

  @fetch-by-key @2.4.1
  Scenario: fetch-by-key - fetch by Key Field
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "fetch-by-key"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has singleton results in "Parameter_KeyField"
    And the provided "Parameter_KeyValue" is returned in "Parameter_KeyField"

  @select @2.4.2
  Scenario: select - Query Support: $select
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "select"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list

  @top @2.4.2
  Scenario: top - Query Support: $top
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "top"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And the number of results is less than or equal to "Parameter_TopCount"

  @skip @2.4.2
  Scenario: skip - Query Support: $skip
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "skip"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And a GET request is made to the resolved Url in "skip" with $skip="Parameter_TopCount"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And data in the "Parameter_Key" fields are different in the second request than in the first

  @orderby-timestamp-asc @2.4.4
  Scenario: orderby-timestamp-asc - Query Support: $orderby ascending
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "orderby-timestamp-asc"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" is sorted in "asc" order

  @orderby-timestamp-desc @2.4.4
  Scenario: orderby-timestamp-desc - Query Support: $orderby timestamp descending
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "orderby-timestamp-desc"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" is sorted in "desc" order

  @orderby-timestamp-asc-filter-int-gt @2.4.4
  Scenario: orderby-timestamp-asc-filter-int-gt - Query Support: $orderby timestamp asc 
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "orderby-timestamp-asc-filter-int-gt"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" is sorted in "asc" order

  @orderby-timestamp-desc-filter-int-gt @2.4.4
  Scenario: orderby-timestamp-desc-filter-int-gt - Query Support: $orderby desc filtered
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "orderby-timestamp-desc-filter-int-gt"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" is sorted in "desc" order

  @filter-int-and @2.4.4
  Scenario: filter-int-and - $filter - Integer Comparison: and
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-int-and"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "gt" "Parameter_IntegerValueLow" "and" "lt" "Parameter_IntegerValueHigh"

  @filter-int-or @2.4.4
  Scenario: filter-int-or - $filter - Integer Comparison: or
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-int-or"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "gt" "Parameter_IntegerValueLow" "or" "lt" "Parameter_IntegerValueHigh"

  @filter-int-not @2.4.4
  Scenario: filter-int-not - $filter - Integer Comparison: not() operator
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-int-not"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_FilterNotField" "ne" "Parameter_FilterNotValue"

  @filter-int-eq @2.4.4
  Scenario: filter-int-eq - $filter - Integer Comparison: eq
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-int-eq"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "eq" "Parameter_IntegerValueLow"

  @filter-int-ne @2.4.4
  Scenario: filter-int-ne - $filter - Integer Comparison: ne
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-int-ne"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "ne" "Parameter_IntegerValueLow"

  @filter-int-gt @2.4.4
  Scenario: filter-int-gt - $filter - Integer Comparison: gt
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-int-gt"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "gt" "Parameter_IntegerValueLow"

  @filter-int-ge @2.4.4
  Scenario: filter-int-ge - $filter - Integer Comparison: ge
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-int-ge"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "ge" "Parameter_IntegerValueLow"

  @filter-int-lt @2.4.4
  Scenario: filter-int-lt - $filter - Integer Comparison: lt
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-int-lt"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "lt" "Parameter_IntegerValueLow"

  @filter-int-le @2.4.4
  Scenario: filter-int-le - $filter - Integer Comparison: le
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-int-le"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "le" "Parameter_IntegerValueLow"

  @filter-enum-single-eq @2.4.4
  Scenario: filter-enum-single-eq - Query Support: Single Edm.EnumType, eq
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-enum-single-eq"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And Single Valued Enumeration Data in "Parameter_SingleValueLookupField" "eq" "Parameter_SingleLookupValue"

  @filter-enum-ne @2.4.4
  Scenario: filter-enum-single-ne - Query Support: Single Edm.EnumType, ne
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-enum-single-ne"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results

  @filter-date-eq @2.4.4
  Scenario: filter-date-eq - DateField eq 'yyyy-mm-dd' date value
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-date-eq"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Date data in "Parameter_DateField" "eq" "Parameter_DateValue"

  @filter-date-ne @2.4.4
  Scenario: filter-date-ne - DateField ne 'yyyy-mm-dd' date value
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-date-ne"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Date data in "Parameter_DateField" "ne" "Parameter_DateValue"

  @filter-date-gt @2.4.4
  Scenario: filter-date-gt - DateField gt 'yyyy-mm-dd' date value
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-date-gt"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Date data in "Parameter_DateField" "gt" "Parameter_DateValue"

  @filter-date-ge @2.4.4
  Scenario: filter-date-ge - DateField ge 'yyyy-mm-dd' date value
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-date-ge"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Date data in "Parameter_DateField" "ge" "Parameter_DateValue"

  @filter-date-lt @2.4.4
  Scenario: filter-date-gt - DateField lt 'yyyy-mm-dd' date value
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-date-lt"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Date data in "Parameter_DateField" "lt" "Parameter_DateValue"

  @filter-date-le @2.4.4
  Scenario: filter-date-le - DateField le 'yyyy-mm-dd' date value
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-date-le"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Date data in "Parameter_DateField" "le" "Parameter_DateValue"

  @filter-datetime-gt @2.4.4
  Scenario: filter-datetime-lt - TimestampField gt DateTimeOffset
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-datetime-gt"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" "gt" "Parameter_DateTimeValue"

  @filter-datetime-ge @2.4.4
  Scenario: filter-datetime-gt - TimestampField ge DateTimeOffset
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-datetime-ge"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" "ge" "Parameter_DateTimeValue"

  @filter-datetime-lt @2.4.4
  Scenario: filter-datetime-lt - TimestampField lt DateTimeOffset
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-datetime-lt"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" "lt" now()

  @filter-datetime-le @2.4.4
  Scenario: filter-datetime-le - TimestampField le DateTimeOffset
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-datetime-le"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" "le" now()

  @filter-datetime-ne @2.4.4
  Scenario: filter-datetime-ne - TimestampField le DateTimeOffset
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-datetime-ne"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" "ne" now()

  @filter-coll-enum-any @2.4.9
  Scenario: filter-coll-enum-any - Collections for Multi-Enumerations: any()
    Given valid metadata have been retrieved
    And field "Parameter_MultipleValueLookupField" in "Parameter_EndpointResource" has Collection of Enumeration data type
    When a GET request is made to the resolved Url in "filter-coll-enum-any"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Multiple Valued Enumeration Data in "Parameter_MultipleValueLookupField" has "Parameter_MultipleLookupValue1"

  @filter-coll-enum-all @2.4.9
  Scenario: filter-coll-enum-all - Collections of Multi-Enumerations: all()
    Given valid metadata have been retrieved
    And field "Parameter_MultipleValueLookupField" in "Parameter_EndpointResource" has Collection of Enumeration data type
    When a GET request is made to the resolved Url in "filter-coll-enum-all"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Multiple Valued Enumeration Data in "Parameter_MultipleValueLookupField" has "Parameter_MultipleLookupValue1"

  @filter-enum-single-has @2.4.9
  Scenario: filter-enum-single-has - Support Single Value Lookups
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-enum-single-has"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Single Valued Enumeration Data in "Parameter_SingleValueLookupField" "has" "Parameter_SingleLookupValue"

  @filter-enum-multi-has @2.4.10
  Scenario: filter-enum-multi-has - Support Multi Value Lookups
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-enum-multi-has"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Multiple Valued Enumeration Data in "Parameter_MultipleValueLookupField" has "Parameter_MultipleLookupValue1"

  @filter-enum-multi-has-and @2.4.10
  Scenario: filter-enum-multi-has-and - Support Multi Value Lookups multiple values
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-enum-multi-has-and"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Multiple Valued Enumeration Data in "Parameter_MultipleValueLookupField" has "Parameter_MultipleLookupValue1"
    And Multiple Valued Enumeration Data in "Parameter_MultipleValueLookupField" has "Parameter_MultipleLookupValue2"


  #######################################
  #  RESPONSE CODE TESTING
  #######################################F

  @response-code-400 @2.4.2
  Scenario: response-code-400 - 400 Bad Request
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "response-code-400"
    Then the server responds with a status code of 400
    # Disable this check for now until Olingo-1380 is fixed - see: https://issues.apache.org/jira/browse/OLINGO-1380
    # And the server has an OData-Version header value of "4.0" or "4.01"

  @response-code-404 @2.5.2
  Scenario: response-code-404 - 404 Not Found Request
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "response-code-404"
    Then the server responds with a status code of 404
    # Disable this check for now until Olingo-1380 is fixed - see: https://issues.apache.org/jira/browse/OLINGO-1380
    # And the server has an OData-Version header value of "4.0" or "4.01"