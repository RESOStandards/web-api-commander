Feature: Web API Server 1.0.2 Core Certification
  All Scenarios passing means the given Web API server is fully-compliant with the RESO Core Specification.

  #######################################
  #  Background
  #######################################
  Background:
    Given a RESOScript file was provided
    And Client Settings and Parameters were read from the file
    And a test container was successfully created from the given RESOScript
    And the test container uses an authorization_code or client_credentials for authentication


  #######################################
  #  Core Tests
  #######################################
  @REQ-WA103-END3 @2.4.1 @metadata
  Scenario: REQ-WA103-END3 - Request and Validate Server Metadata
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

  @REQ-WA103-END2 @2.4.1 @datasystem
  Scenario: REQ-WA103-END2 - Data System Endpoint test
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-END2"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And the results match the expected DataSystem JSON schema

  @REQ-WA103-QM7 @2.4.9
  Scenario: REQ-WA103-QM7 - Support Single Value Lookups
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QM7"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Single Valued Enumeration Data in "Parameter_SingleValueLookupField" has "Parameter_SingleLookupValue"

  @REQ-WA103-QM8 @2.4.10
  Scenario: REQ-WA103-QM8 - Support Multi Value Lookups
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QM8"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Multiple Valued Enumeration Data in "Parameter_MultipleValueLookupField" has "Parameter_MultipleLookupValue1"

  @REQ-WA103-QM8.2 @2.4.10
  Scenario: REQ-WA103-QM8.2 - Support Multi Value Lookups multiple values
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QM8.2"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Multiple Valued Enumeration Data in "Parameter_MultipleValueLookupField" has "Parameter_MultipleLookupValue1"
    And Multiple Valued Enumeration Data in "Parameter_MultipleValueLookupField" has "Parameter_MultipleLookupValue2"

  @REQ-WA103-QO1.1 @2.4.4 @OData-4.0
  Scenario: REQ-WA103-QO1.1 - Query Support: $select case-sensitivity for OData 4.0
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO1.1"
    And the server has an OData-Version header value of "4.0" or "4.01"
    Then the server responds with a status code of 400 if the server reports OData-Version "4.0"
    And the server responds with a status code of 200 if the server reports OData-Version "4.01"

  @REQ-WA103-QO1.2 @2.4.4 @OData-4.0
  Scenario: REQ-WA103-QO1.2 - Query Support: $filter case-sensitivity for OData 4.0
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO1.2"
    And the server has an OData-Version header value of "4.0" or "4.01"
    Then the server responds with a status code of 400 if the server reports OData-Version "4.0"
    And the server responds with a status code of 200 if the server reports OData-Version "4.01"

  @REQ-WA103-QO1.3 @2.4.4 @OData-4.0
  Scenario: REQ-WA103-QO1.3 - Query Support: $orderby asc case-sensitivity for OData 4.0
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO1.3"
    And the server has an OData-Version header value of "4.0" or "4.01"
    Then the server responds with a status code of 400 if the server reports OData-Version "4.0"
    And the server responds with a status code of 200 if the server reports OData-Version "4.01"

  @REQ-WA103-QO1.4 @2.4.4 @OData-4.0
  Scenario: REQ-WA103-QO1.4 - Query Support: $orderby desc case-sensitivity for OData 4.0
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO1.4"
    And the server has an OData-Version header value of "4.0" or "4.01"
    Then the server responds with a status code of 400 if the server reports OData-Version "4.0"
    And the server responds with a status code of 200 if the server reports OData-Version "4.01"

  @REQ-WA103-QO2 @2.4.4
  Scenario: REQ-WA103-QO2 - Query Support: $filter - Integer Comparison: eq
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO2"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "eq" "Parameter_IntegerValueLow"

  @REQ-WA103-QO3 @2.4.4
  Scenario: REQ-WA103-QO3 - Query Support: $filter - Integer Comparison: ne
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO3"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "ne" "Parameter_IntegerValueLow"

  @REQ-WA103-QO4 @2.4.4
  Scenario: REQ-WA103-QO4 - Query Support: $filter - Integer Comparison: gt
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO4"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "gt" "Parameter_IntegerValueLow"

  @REQ-WA103-QO5 @2.4.4
  Scenario: REQ-WA103-QO5 - Query Support: $filter - Integer Comparison: ge
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO5"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "ge" "Parameter_IntegerValueLow"

  @REQ-WA103-QO6 @2.4.4
  Scenario: REQ-WA103-QO6 - Query Support: $filter - Integer Comparison: lt
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO6"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "lt" "Parameter_IntegerValueLow"

  @REQ-WA103-QO7 @2.4.4
  Scenario: REQ-WA103-QO7 - Query Support: $filter - Integer Comparison: le
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO7"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "le" "Parameter_IntegerValueLow"

  @REQ-WA103-QO8 @2.4.4
  Scenario: REQ-WA103-QO8 - Query Support: $filter - Comparison: has
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO8"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Single Valued Enumeration Data in "Parameter_FilterHasField" has "Parameter_FilterHasLookupValue"

  @REQ-WA103-QO9 @2.4.4
  Scenario: REQ-WA103-QO9 - Query Support: $filter - Integer Comparison: and
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO9"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "gt" "Parameter_IntegerValueLow" "and" "lt" "Parameter_IntegerValueHigh"

  @REQ-WA103-QO10 @2.4.4
  Scenario: REQ-WA103-QO10 - Query Support: $filter - Integer Comparison: or
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO10"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_IntegerField" "gt" "Parameter_IntegerValueLow" "or" "lt" "Parameter_IntegerValueHigh"

  @REQ-WA103-QO11 @2.4.4
  Scenario: REQ-WA103-QO11 - Query Support: $filter - Integer Comparison: not() (operator)
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO11"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Integer data in "Parameter_FilterNotField" "ne" "Parameter_FilterNotValue"

  @REQ-WA103-QO25 @2.4.4
  Scenario: REQ-WA103-QO25 - Query Support: $filter: Date portion of EdmDateTimeOffset field is greater than EdmDate value
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO25"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And Date data in "Parameter_TimestampField" "gt" "Parameter_DateValue"

  @REQ-WA103-QO26 @2.4.4
  Scenario: REQ-WA103-QO26 - Query Support: $filter: Time portion of EdmDateTimeOffset field is less than EdmTime value
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO26"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And TimeOfDay data in "Parameter_TimestampField" "lt" "Parameter_TimeValue"

  @REQ-WA103-QO26.2 @2.4.4
  Scenario: REQ-WA103-QO26.2 - Query Support: $filter: Date: EdmDateTimeOffset field is less than EdmDateTimeOffset value
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO26.2"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" "lt" "Parameter_DateTimeValue"

  @REQ-WA103-QO27 @2.4.4
  Scenario: REQ-WA103-QO27 - Query Support: $filter: DateTimeOffset le now()
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO27"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" "le" now()

  @REQ-WA103-QO28.1 @2.4.4
  Scenario: REQ-WA103-QO28.1 - Query Support: $orderby asc filtered
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO28.1"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" is sorted in "asc" order

  @REQ-WA103-QO28.2 @2.4.4
  Scenario: REQ-WA103-QO28.2 - Query Support: $orderby asc no filter
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO28.2"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" is sorted in "asc" order

  @REQ-WA103-QO28.3 @2.4.4
  Scenario: REQ-WA103-QO28.3 - Query Support: $orderby desc filtered
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO28.3"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" is sorted in "desc" order

  @REQ-WA103-QO28.4 @2.4.4
  Scenario: REQ-WA103-QO28.4 - Query Support: $orderby desc no filter
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO28.4"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And DateTimeOffset data in "Parameter_TimestampField" is sorted in "desc" order

  @REQ-WA103-QR1 @2.4.1
  Scenario: REQ-WA103-QR1 - Search Parameters: Select KeyOrKeyNumeric Field
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QR1"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has singleton results in "Parameter_KeyOrKeyNumericField"
    And the provided "Parameter_KeyOrKeyNumericValue" is returned in "Parameter_KeyOrKeyNumericField"

  @REQ-WA103-QR3 @2.4.2
  Scenario: REQ-WA103-QR3 - Query Support: $select
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QR3"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list

  @REQ-WA103-QR4 @2.4.2
  Scenario: REQ-WA103-QR4 - Query Support: $top
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QR4"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And the number of results is less than or equal to "Parameter_TopCount"

  @REQ-WA103-QR5 @2.4.2
  Scenario: REQ-WA103-QR5 - Query Support: $skip
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QR5"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And a GET request is made to the resolved Url in "REQ-WA103-QR5" with $skip="Parameter_TopCount"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And data in the "Parameter_KeyOrKeyNumeric" fields are different in the second request than in the first

  #######################################
  #  RESPONSE CODE TESTING
  #######################################F
  @REQ-WA103-END1 @2.4.1
  Scenario: REQ-WA103-END1 - Service Endpoint
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-END1"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"

  @REQ-WA103-RC3 @2.5.2
  Scenario: REQ-WA103-RC3 - 200 OK Request
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-RC3"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"

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