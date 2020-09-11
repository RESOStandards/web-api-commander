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

  @fetch-by-id @2.4.1
  Scenario: fetch-by-id - fetch by Key Field
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "fetch-by-id"
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

  @orderby-asc @2.4.4
  Scenario: orderby-asc - Query Support: $orderby asc no filter
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "orderby-asc"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" is sorted in "asc" order

  @orderby-desc @2.4.4
  Scenario: orderby-desc - Query Support: $orderby desc no filter
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "orderby-desc"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" is sorted in "desc" order

  @orderby-asc-filter @2.4.4
  Scenario: orderby-asc-filter - Query Support: $orderby asc filtered
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "orderby-asc-filter"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" is sorted in "asc" order

  @orderby-desc-filter @2.4.4
  Scenario: orderby-desc-filter - Query Support: $orderby desc filtered
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "orderby-desc-filter"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" is sorted in "desc" order

  @filter-int-and @2.4.4
  Scenario: filter-int-and - Query Support: $filter - Integer Comparison: and
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
  Scenario: filter-int-or - Query Support: $filter - Integer Comparison: or
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
  Scenario: filter-int-not - Query Support: $filter - Integer Comparison: not() (operator)
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
  Scenario: filter-int-eq - Query Support: $filter - Integer Comparison: eq
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
  Scenario: filter-int-ne - Query Support: $filter - Integer Comparison: ne
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
  Scenario: filter-int-gt - Query Support: $filter - Integer Comparison: gt
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
  Scenario: filter-int-ge - Query Support: $filter - Integer Comparison: ge
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
  Scenario: filter-int-lt - Query Support: $filter - Integer Comparison: lt
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
  Scenario: filter-int-le - Query Support: $filter - Integer Comparison: le
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-int-le"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "le" "Parameter_IntegerValueLow"

  #TODO
  @filter-enum-eq @2.4.4
  Scenario: filter-enum-eq - Query Support: Single EnumType, eq
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-enum-eq"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results

  #TODO
  @filter-enum-ne @2.4.4
  Scenario: filter-enum-ne - Query Support: Single EnumType, ne
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO13"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results

  #TODO
  @filter-date-eq @2.4.4
  Scenario: filter-date-eq - TimestampField eq 'yyyy-mm-dd' date value
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-date-eq"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Date data in "Parameter_TimestampField" "eq" "Parameter_DateValue"

  #TODO
  @filter-date-ne @2.4.4
  Scenario: filter-date-ne - TimestampField ne 'yyyy-mm-dd' date value
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-date-ne"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Date data in "Parameter_TimestampField" "ne" "Parameter_DateValue"

  @filter-date-gt @2.4.4
  Scenario: filter-date-gt - TimestampField gt 'yyyy-mm-dd' date value
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-date-gt"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Date data in "Parameter_TimestampField" "gt" "Parameter_DateValue"

  #TODO
  @filter-date-ge @2.4.4
  Scenario: filter-date-ge - TimestampField ge 'yyyy-mm-dd' date value
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-date-ge"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Date data in "Parameter_TimestampField" "ge" "Parameter_DateValue"

  #TODO
  @filter-date-lt @2.4.4
  Scenario: filter-date-gt - TimestampField lt 'yyyy-mm-dd' date value
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-date-lt"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Date data in "Parameter_TimestampField" "lt" "Parameter_DateValue"

  #TODO
  @filter-date-le @2.4.4
  Scenario: filter-date-le - TimestampField le 'yyyy-mm-dd' date value
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-date-le"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Date data in "Parameter_TimestampField" "le" "Parameter_DateValue"

  #TODO
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
    And DateTimeOffset data in "Parameter_TimestampField" "gt" now()

  #TODO
  @filter-datetime-ge @2.4.4
  Scenario: filter-datetime-gt - TimestampField ge DateTimeOffset
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-datetime-le"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" "ge" now()

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

  #TODO
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

  #TODO
  @filter-enum-coll-any @2.4.9
  Scenario: filter-enum-coll-any - Collections for Multi-Enumerations: any()
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-enum-coll-any"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results

  #TODO
  @filter-enum-coll-all @2.4.9
  Scenario: filter-enum-coll-all - Collections of Multi-Enumerations: all()
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "filter-enum-coll-all"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results

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
    And Single Valued Enumeration Data in "Parameter_SingleValueLookupField" has "Parameter_SingleLookupValue"

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

  @REQ-WA103-RC5 @2.4.2
  Scenario: REQ-WA103-RC5 - 400 Bad Request
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-RC5"
    Then the server responds with a status code of 400
    # Disable this check for now until Olingo-1380 is fixed - see: https://issues.apache.org/jira/browse/OLINGO-1380
    # And the server has an OData-Version header value of "4.0" or "4.01"

  @REQ-WA103-RC07 @2.5.2
  Scenario: REQ-WA103-RC07 - 404 Not Found Request
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-RC07"
    Then the server responds with a status code of 404
    # Disable this check for now until Olingo-1380 is fixed - see: https://issues.apache.org/jira/browse/OLINGO-1380
    # And the server has an OData-Version header value of "4.0" or "4.01"
