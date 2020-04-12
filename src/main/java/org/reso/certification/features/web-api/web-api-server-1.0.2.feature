Feature: Web API Server 1.0.2 Certification
  All Scenarios passing means the given Web API server is fully-compliant with the RESO Platinum Web API 1.0.2 Server specification.

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
  @REQ-WA103-END3 @core @2.4.1 @core-endorsement @metadata
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

  @REQ-WA103-END2 @core @2.4.1 @core-endorsement @datasystem
  Scenario: REQ-WA103-END2 - Data System Endpoint test
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-END2"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And the results match the expected DataSystem JSON schema

  @REQ-WA103-QR1 @core @2.4.1 @query-functions-endorsement
  Scenario: REQ-WA103-QR1 - Search Parameters: Select KeyOrKeyNumeric Field
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QR1"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has singleton results in "Parameter_KeyOrKeyNumericField"
    And the provided "Parameter_KeyOrKeyNumericValue" is returned in "Parameter_KeyOrKeyNumericField"

  @REQ-WA103-QR3 @core @2.4.2 @query-functions-endorsement
  Scenario: REQ-WA103-QR3 - Query Support: $select
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QR3"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list

  @REQ-WA103-QR4 @core @2.4.2 @client-paging-endorsement
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

  @REQ-WA103-QR5 @core @2.4.2 @query-support-endorsement
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

  @REQ-WA103-QO1.1 @core @2.4.4 @core-endorsement @OData-4.0
  Scenario: REQ-WA103-QO1.1 - Query Support: $select case-sensitivity for OData 4.0
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO1.1"
    And the server has an OData-Version header value of "4.0" or "4.01"
    Then the server responds with a status code of 400 if the server reports OData-Version "4.0"
    And the server responds with a status code of 200 if the server reports OData-Version "4.01"

  @REQ-WA103-QO1.2 @core @2.4.4 @core-endorsement @OData-4.0
  Scenario: REQ-WA103-QO1.2 - Query Support: $filter case-sensitivity for OData 4.0
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO1.2"
    And the server has an OData-Version header value of "4.0" or "4.01"
    Then the server responds with a status code of 400 if the server reports OData-Version "4.0"
    And the server responds with a status code of 200 if the server reports OData-Version "4.01"

  @REQ-WA103-QO1.3 @core @2.4.4 @core-endorsement @OData-4.0
  Scenario: REQ-WA103-QO1.3 - Query Support: $orderby asc case-sensitivity for OData 4.0
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO1.3"
    And the server has an OData-Version header value of "4.0" or "4.01"
    Then the server responds with a status code of 400 if the server reports OData-Version "4.0"
    And the server responds with a status code of 200 if the server reports OData-Version "4.01"

  @REQ-WA103-QO1.4 @core @2.4.4 @core-endorsement @OData-4.0
  Scenario: REQ-WA103-QO1.4 - Query Support: $orderby desc case-sensitivity for OData 4.0
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO1.4"
    And the server has an OData-Version header value of "4.0" or "4.01"
    Then the server responds with a status code of 400 if the server reports OData-Version "4.0"
    And the server responds with a status code of 200 if the server reports OData-Version "4.01"

  @REQ-WA103-QO2 @core @2.4.4 @filterability-endorsement
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

  @REQ-WA103-QO3 @core @2.4.4 @filterability-endorsement
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

  @REQ-WA103-QO4 @core @2.4.4 @filterability-endorsement
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

  @REQ-WA103-QO5 @core @2.4.4 @filterability-endorsement
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

  @REQ-WA103-QO6 @core @2.4.4 @filterability-endorsement
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

  @REQ-WA103-QO7 @core @2.4.4 @filterability-endorsement
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

  @REQ-WA103-QO9 @core @2.4.4 @filterability-endorsement
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

  @REQ-WA103-QO10 @core @2.4.4 @filterability-endorsement
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

  @REQ-WA103-QO11 @core @2.4.4 @filterability-endorsement
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

  @REQ-WA103-QO25 @core @2.4.4 @filterability-endorsement
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

  @REQ-WA103-QO26 @core @2.4.4 @filterability-endorsement
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

  @REQ-WA103-QO26.2 @core @2.4.4 @filterability-endorsement
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

  @REQ-WA103-QO27 @core @2.4.4 @filterability-endorsement
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


  #######################################
  #  RESPONSE CODE TESTING
  #######################################

  @REQ-WA103-END1 @core @2.4.1 @core-endorsement
  Scenario: REQ-WA103-END1 - Service Endpoint
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-END1"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"

  @REQ-WA103-RC3 @core @2.5.2 @core-endorsement
  Scenario: REQ-WA103-RC3 - 200 OK Request
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-RC3"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"

  @REQ-WA103-RC5 @core @2.4.2 @core-endorsement
  Scenario: REQ-WA103-RC5 - 400 Bad Request
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-RC5"
    Then the server responds with a status code of 400
    # Disable this check for now until Olingo-1380 is fixed - see: https://issues.apache.org/jira/browse/OLINGO-1380
    # And the server has an OData-Version header value of "4.0" or "4.01"

  @REQ-WA103-RC07 @core @2.5.2 @core-endorsement
  Scenario: REQ-WA103-RC07 - 404 Not Found Request
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-RC07"
    Then the server responds with a status code of 404
    # Disable this check for now until Olingo-1380 is fixed - see: https://issues.apache.org/jira/browse/OLINGO-1380
    # And the server has an OData-Version header value of "4.0" or "4.01"

  #######################################
  #  Bronze Tests
  #######################################

  @REQ-WA103-QM7 @bronze @2.4.9 @filterability-endorsement
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

  @REQ-WA103-QM8 @bronze @2.4.10 @filterability-endorsement
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

  @REQ-WA103-QM8.2 @bronze @2.4.10 @filterability-endorsement
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

  @REQ-WA103-QO8 @bronze @2.4.4 @filterability-endorsement
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

  @REQ-WA103-QO28.1 @bronze @2.4.4 @sortability-endorsement
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

  @REQ-WA103-QO28.2 @bronze @2.4.4 @sortability-endorsement
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

  @REQ-WA103-QO28.3 @bronze @2.4.4 @sortability-endorsement
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

  @REQ-WA103-QO28.4 @bronze @2.4.4 @sortability-endorsement
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


  #######################################
  #  Gold Tests
  #######################################

  @REQ-WA103-QO18.1 @gold @2.4.4 @filterability-endorsement
  Scenario: REQ-WA103-QO18.1 - Query Support: $filter: Date: year
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO18.1"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And "year" data in Date Field "Parameter_DateField" "eq" "Parameter_YearValue"

  @REQ-WA103-QO18.2 @gold @2.4.4 @filterability-endorsement
  Scenario: REQ-WA103-QO18.2 - Query Support: $filter: Date: year comparison with timestamp
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO18.2"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And "year" data in Timestamp Field "Parameter_TimestampField" "eq" "Parameter_YearValue"

  @REQ-WA103-QO19.1 @gold @2.4.4 @filterability-endorsement
  Scenario: REQ-WA103-QO19.1 - Query Support: $filter: Date: month
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO19.1"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And "month" data in Date Field "Parameter_DateField" "eq" "Parameter_MonthValue"

  @REQ-WA103-QO19.2 @gold @2.4.4 @filterability-endorsement
  Scenario: REQ-WA103-QO19.2 - Query Support: $filter: Date: month comparison with timestamp
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO19.2"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And "month" data in Timestamp Field "Parameter_TimestampField" "eq" "Parameter_MonthValue"

  @REQ-WA103-QO20.1 @gold @2.4.4 @filterability-endorsement
  Scenario: REQ-WA103-QO20.1 - Query Support: $filter: Date: day
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO20.1"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And "day" data in Date Field "Parameter_DateField" "eq" "Parameter_DayValue"

  @REQ-WA103-QO20.2 @gold @2.4.4 @filterability-endorsement
  Scenario: REQ-WA103-QO20.2 - Query Support: $filter: Date: day comparison with timestamp
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO20.2"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And "day" data in Timestamp Field "Parameter_TimestampField" "eq" "Parameter_DayValue"

  @REQ-WA103-QO21 @gold @2.4.4 @filterability-endorsement
  Scenario: REQ-WA103-QO21 - Query Support: $filter: Date: hour comparison with timestamp
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO21"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And "hour" data in Timestamp Field "Parameter_TimestampField" "eq" "Parameter_HourValue"

  @REQ-WA103-QO22 @gold @2.4.4 @filterability-endorsement
  Scenario: REQ-WA103-QO22 - Query Support: $filter: Date: minute comparison with timestamp
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO22"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And "minute" data in Timestamp Field "Parameter_TimestampField" "gt" "Parameter_MinuteValue"

  @REQ-WA103-QO23 @gold @2.4.4 @filterability-endorsement
  Scenario: REQ-WA103-QO23 - Query Support: $filter: Date: second comparison with timestamp
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO23"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And "second" data in Timestamp Field "Parameter_TimestampField" "lt" "Parameter_SecondValue"

  @REQ-WA103-QO24 @gold @2.4.4 @filterability-endorsement
  Scenario: REQ-WA103-QO24 - Query Support: $filter: Date: fractional seconds comparison with timestamp
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO24"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And "fractional" data in Timestamp Field "Parameter_TimestampField" "lt" "Parameter_FractionalValue"

  #######################################
  #  Platinum Tests
  #######################################

  @REQ-WA103-QO13 @platinum @2.4.4 @filterability-endorsement
  Scenario: REQ-WA103-QO13 - Query Support: $filter - String: contains
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO13"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And String data in "Parameter_StringField" "contains" "Parameter_ContainsValue"

  @REQ-WA103-QO14 @platinum @2.4.4 @filterability-endorsement
  Scenario: REQ-WA103-QO14 - Query Support: $filter - String: ends with
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO14"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And String data in "Parameter_StringField" "endswith" "Parameter_EndsWithValue"

  @REQ-WA103-QO15 @platinum @2.4.4 @filterability-endorsement
  Scenario: REQ-WA103-QO15 - Query Support: $filter - String: starts with
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO15"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And String data in "Parameter_StringField" "startswith" "Parameter_StartsWithValue"

  @REQ-WA103-QO16 @platinum @2.4.4 @filterability-endorsement
  Scenario: REQ-WA103-QO16 - Query Support: $filter - String: tolower() support
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO16"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And String data in "Parameter_StringField" "tolower" "Parameter_ToLowerValue"

  @REQ-WA103-QO17 @platinum @2.4.4 @filterability-endorsement
  Scenario: REQ-WA103-QO17 - Query Support: $filter - String: toupper() support
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO17"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And String data in "Parameter_StringField" "toupper" "Parameter_ToUpperValue"

  @REQ-WA103-QO29.1 @platinum @2.4.4 @expandability-endorsement
  Scenario: REQ-WA103-QO29.1 - Query Support: $expand
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO29.1"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list
    And data and type information exist in the results and within the given "Parameter_ExpandField"
    And an OData NavigationProperty exists for the given "Parameter_EndpointResource"
    And the expanded data were found in the related resource

  @REQ-WA103-QO29.2 @platinum @2.4.4 @expandability-endorsement @todo
  Scenario: REQ-WA103-QO29.2 - Query Support: $expand media photo count (TODO)
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO29.2"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list

  @REQ-WA103-QO29.3 @platinum @2.4.4 @expandability-endorsement @todo
  Scenario: REQ-WA103-QO29.3 - Query Support: $expand required field (TODO)
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO29.3"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list

  @REQ-WA103-QM3 @platinum @2.4.6 @queryability-endorsement @todo
  Scenario: REQ-WA103-QM3 - Support Literals: any() Lambda Expression (TODO)
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QM3"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list

  @REQ-WA103-QM4 @platinum @2.4.6 @queryability-endorsement @todo
  Scenario: REQ-WA103-QM4 - Query Support Literals: all() Lambda Operator (TODO)
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QM4"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list

  @REQ-WA103-QM5.1 @platinum @2.4.7 @queryability-endorsement @geospatial @todo
  Scenario: REQ-WA103-QM5.1 - Query Support: GeoSpatial Search Implementation (TODO)
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QM5.1"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list

  @REQ-WA103-QM5.2 @platinum @2.4.7 @queryability-endorsement @geospatial @todo
  Scenario: REQ-WA103-QM5.2 - Query Support: GeoSpatial Search Implementation (TODO)
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QM5.2"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the response is valid JSON
    And the response has results
    And resource metadata for "Parameter_EndpointResource" contains the fields in the given select list
    And data are present for fields contained within the given select list

  @REQ-WA103-QO12 @platinum @2.4.4 @filterability-endorsement @filterability-endorsement
  Scenario: REQ-WA103-QO12 - Query Support: $filter - Grouping: filter (ge, le) and (gt, lt) and expect (gt, lt)
    Given valid metadata have been retrieved
    When a GET request is made to the resolved Url in "REQ-WA103-QO12" using the OData Client
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the OData client response has client entity set data
    And client entity set Integer data in "Parameter_IntegerField" "gt" "Parameter_IntegerValueLow"
    And client entity set Integer data in "Parameter_IntegerField" "lt" "Parameter_IntegerValueHigh"
