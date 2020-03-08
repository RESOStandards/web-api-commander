Feature: Web API Server 1.0.2 Certification
  All Scenarios passing means the given Web API server is fully-compliant with the RESO Platinum Web API 1.0.2 Server specification.

  Background:
    Given a RESOScript file was provided
    And Client Settings and Parameters were read from the file
    And an OData client was successfully created from the given RESOScript

  @REQ-WA103-END3 @core @x.y.z @core-endorsement @metadata
  Scenario: Request and Validate Server Metadata
    When a default entity container exists for the service root in "ClientSettings_WebAPIURI"
    And XML Metadata are requested from the service root in "ClientSettings_WebAPIURI"
    Then the server responds with a status code of 200
    And the Edm metadata returned by the server are valid
    And the XML metadata returned by the server are valid
    And the metadata contains the "Parameter_EndpointResource" resource
    And resource metadata for "Parameter_EndpointResource" contains the fields in "Parameter_SelectList"

  @REQ-WA103-END2 @core @x.y.z @core-endorsement @datasystem
  Scenario: Data System Endpoint test
    When a GET request is made to the resolved Url in "REQ-WA103-END2"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And the results match the expected DataSystem JSON schema

  @REQ-WA103-QR1 @core @2.4.1 @query-functions-endorsement
  Scenario: Search Parameters: Select KeyOrKeyNumeric Field
    When a GET request is made to the resolved Url in "REQ-WA103-QR1"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has singleton results in "Parameter_KeyOrKeyNumericField"
    And the provided "Parameter_KeyOrKeyNumericValue" is returned in "Parameter_KeyOrKeyNumericField"

  @REQ-WA103-QR3 @core @2.4.2 @query-functions-endorsement
  Scenario: Query Support: $select
    When a GET request is made to the resolved Url in "REQ-WA103-QR3"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And data are present in fields contained within "Parameter_SelectList"

  @REQ-WA103-QR4 @core @2.4.2 @client-paging-endorsement
  Scenario: Query Support: $top
    When a GET request is made to the resolved Url in "REQ-WA103-QR4"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And the number of results is less than or equal to "Parameter_TopCount"

  @REQ-WA103-QR5 @core @2.4.2 @query-support-endorsement
  Scenario: Query Support: $skip
    When a GET request is made to the resolved Url in "REQ-WA103-QR5"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And a GET request is made to the resolved Url in "REQ-WA103-QR5" with $skip="Parameter_TopCount"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And data in the "Parameter_KeyOrKeyNumeric" fields are different in the second request than in the first

  @REQ-WA103-QO1.1 @core @2.4.4 @core-endorsement @OData-4.0
  Scenario: Query Support: $select case-sensitivity for OData 4.0
    When a GET request is made to the resolved Url in "REQ-WA103-QO1.1"
    Then the server responds with a status code of 400 if the server headers report OData version "4.0"

  @REQ-WA103-QO1.2 @core @2.4.4 @core-endorsement @OData-4.0
  Scenario: Query Support: $filter case-sensitivity for OData 4.0
    When a GET request is made to the resolved Url in "REQ-WA103-QO1.2"
    Then the server responds with a status code of 400 if the server headers report OData version "4.0"

  @REQ-WA103-QO1.3 @core @2.4.4 @core-endorsement @OData-4.0
  Scenario: Query Support: $orderby asc case-sensitivity for OData 4.0
    When a GET request is made to the resolved Url in "REQ-WA103-QO1.3"
    Then the server responds with a status code of 400 if the server headers report OData version "4.0"

  @REQ-WA103-QO1.4 @core @2.4.4 @core-endorsement @OData-4.0
  Scenario: Query Support: $orderby desc case-sensitivity for OData 4.0
    When a GET request is made to the resolved Url in "REQ-WA103-QO1.4"
    Then the server responds with a status code of 400 if the server headers report OData version "4.0"

  @REQ-WA103-QO2 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: eq
    When a GET request is made to the resolved Url in "REQ-WA103-QO2"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterIntegerField" "eq" "Parameter_FilterIntegerValueLow"

  @REQ-WA103-QO3 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: ne
    When a GET request is made to the resolved Url in "REQ-WA103-QO3"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterIntegerField" "ne" "Parameter_FilterIntegerValueLow"

  @REQ-WA103-QO4 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: gt
    When a GET request is made to the resolved Url in "REQ-WA103-QO4"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterIntegerField" "gt" "Parameter_FilterIntegerValueLow"

  @REQ-WA103-QO5 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: ge
    When a GET request is made to the resolved Url in "REQ-WA103-QO5"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterIntegerField" "ge" "Parameter_FilterIntegerValueLow"

  @REQ-WA103-QO6 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: lt
    When a GET request is made to the resolved Url in "REQ-WA103-QO6"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterIntegerField" "lt" "Parameter_FilterIntegerValueLow"

  @REQ-WA103-QO7 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: le
    When a GET request is made to the resolved Url in "REQ-WA103-QO7"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterIntegerField" "le" "Parameter_FilterIntegerValueLow"

  @REQ-WA103-QO9 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: and
    When a GET request is made to the resolved Url in "REQ-WA103-QO9"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterIntegerField" "gt" "Parameter_FilterIntegerValueLow" "and" "lt" "Parameter_FilterIntegerValueHigh"

  @REQ-WA103-QO10 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: or
    When a GET request is made to the resolved Url in "REQ-WA103-QO10"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterIntegerField" "gt" "Parameter_FilterIntegerValueLow" "or" "lt" "Parameter_FilterIntegerValueHigh"

  @REQ-WA103-QO11 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: not() (operator)
    When a GET request is made to the resolved Url in "REQ-WA103-QO11"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterNotField" "ne" "Parameter_FilterNotValue"

  @REQ-WA103-QO25 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter: Date portion of EdmDateTimeOffset field is greater than EdmDate value
    When a GET request is made to the resolved Url in "REQ-WA103-QO25"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Date data in "Parameter_TimestampField" "gt" "Parameter_DateValue"

  @REQ-WA103-QO26 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter: Time portion of EdmDateTimeOffset field is less than EdmTime value
    When a GET request is made to the resolved Url in "REQ-WA103-QO26"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And TimeOfDay data in "Parameter_TimestampField" "lt" "Parameter_TimeValue"

  @REQ-WA103-QO26.2 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter: Date: EdmDateTimeOffset field is less than EdmDateTimeOffset value
    When a GET request is made to the resolved Url in "REQ-WA103-QO26.2"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And DateTimeOffset data in "Parameter_TimestampField" "lt" "Parameter_DateTimeValue"

  @REQ-WA103-QO27 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter: DateTimeOffset le now()
    When a GET request is made to the resolved Url in "REQ-WA103-QO27"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And DateTimeOffset data in "Parameter_TimestampField" "le" now()

  @REQ-WA103-QM7 @bronze @2.4.9 @filterability-endorsement
  Scenario: Support Single Value Lookups
    When a GET request is made to the resolved Url in "REQ-WA103-QM7"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Single Valued Enumeration Data in "Parameter_SingleValueLookupField" has "Parameter_SingleLookupValue"

  @REQ-WA103-QM8 @bronze @2.4.10 @filterability-endorsement
  Scenario: Support Multi Value Lookups
    When a GET request is made to the resolved Url in "REQ-WA103-QM8"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Multiple Valued Enumeration Data in "Parameter_MultipleValueLookupField" has "Parameter_MultipleLookupValue1"

  @REQ-WA103-QM8.2 @bronze @2.4.10 @filterability-endorsement
  Scenario: Support Multi Value Lookups multiple values
    When a GET request is made to the resolved Url in "REQ-WA103-QM8.2"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Multiple Valued Enumeration Data in "Parameter_MultipleValueLookupField" has "Parameter_MultipleLookupValue1"
    And Multiple Valued Enumeration Data in "Parameter_MultipleValueLookupField" has "Parameter_MultipleLookupValue2"

  @REQ-WA103-QO8 @bronze @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Comparison: has
    When a GET request is made to the resolved Url in "REQ-WA103-QO8"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Single Valued Enumeration Data in "Parameter_FilterHasField" has "Parameter_FilterHasValue"

  @REQ-WA103-QO28.1 @bronze @2.4.4 @sortability-endorsement
  Scenario: Query Support: $orderby asc filtered
    When a GET request is made to the resolved Url in "REQ-WA103-QO28.1"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And DateTimeOffset data in "Parameter_TimestampField" is sorted in "asc" order

  @REQ-WA103-QO28.2 @bronze @2.4.4 @sortability-endorsement
  Scenario: Query Support: $orderby asc no filter
    When a GET request is made to the resolved Url in "REQ-WA103-QO28.2"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And DateTimeOffset data in "Parameter_TimestampField" is sorted in "asc" order

  @REQ-WA103-QO28.3 @bronze @2.4.4 @sortability-endorsement
  Scenario: Query Support: $orderby desc filtered
    When a GET request is made to the resolved Url in "REQ-WA103-QO28.3"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And DateTimeOffset data in "Parameter_TimestampField" is sorted in "desc" order

  @REQ-WA103-QO28.4 @bronze @2.4.4 @sortability-endorsement
  Scenario: Query Support: $orderby desc no filter
    When a GET request is made to the resolved Url in "REQ-WA103-QO28.4"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And DateTimeOffset data in "Parameter_TimestampField" is sorted in "desc" order

  @REQ-WA103-QO18.1 @gold @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter: Date: year
    When a GET request is made to the resolved Url in "REQ-WA103-QO18.1"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And "year" data in Date Field "Parameter_DateField" "eq" "Parameter_YearValue"

  @REQ-WA103-QO18.2 @gold @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter: Date: year comparison with timestamp
    When a GET request is made to the resolved Url in "REQ-WA103-QO18.2"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And "year" data in Timestamp Field "Parameter_TimestampField" "eq" "Parameter_YearValue"

  @REQ-WA103-QO19.1 @gold @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter: Date: month
    When a GET request is made to the resolved Url in "REQ-WA103-QO19.1"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And "month" data in Date Field "Parameter_DateField" "eq" "Parameter_MonthValue"

  @REQ-WA103-QO19.2 @gold @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter: Date: month comparison with timestamp
    When a GET request is made to the resolved Url in "REQ-WA103-QO19.2"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And "month" data in Timestamp Field "Parameter_TimestampField" "eq" "Parameter_MonthValue"

  @REQ-WA103-QO20.1 @gold @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter: Date: day
    When a GET request is made to the resolved Url in "REQ-WA103-QO20.1"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And "day" data in Date Field "Parameter_DateField" "eq" "Parameter_DayValue"

  @REQ-WA103-QO20.2 @gold @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter: Date: day comparison with timestamp
    When a GET request is made to the resolved Url in "REQ-WA103-QO20.2"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And "day" data in Timestamp Field "Parameter_TimestampField" "eq" "Parameter_DayValue"

  @REQ-WA103-QO21 @gold @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter: Date: hour comparison with timestamp
    When a GET request is made to the resolved Url in "REQ-WA103-QO21"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And "hour" data in Timestamp Field "Parameter_TimestampField" "eq" "Parameter_HourValue"

  @REQ-WA103-QO22 @gold @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter: Date: minute comparison with timestamp
    When a GET request is made to the resolved Url in "REQ-WA103-QO22"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And "minute" data in Timestamp Field "Parameter_TimestampField" "gt" "Parameter_MinuteValue"

  @REQ-WA103-QO23 @gold @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter: Date: second comparison with timestamp
    When a GET request is made to the resolved Url in "REQ-WA103-QO23"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And "second" data in Timestamp Field "Parameter_TimestampField" "lt" "Parameter_SecondValue"

  @REQ-WA103-QO24 @gold @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter: Date: fractional seconds comparison with timestamp
    When a GET request is made to the resolved Url in "REQ-WA103-QO24"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And "fractional" data in Timestamp Field "Parameter_TimestampField" "lt" "Parameter_FractionalValue"

  @REQ-WA103-QO13 @platinum @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - String: contains
    When a GET request is made to the resolved Url in "REQ-WA103-QO13"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And String data in "Parameter_ContainsField" "contains" "Parameter_ContainsValue"

  @REQ-WA103-QO14 @platinum @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - String: ends with
    When a GET request is made to the resolved Url in "REQ-WA103-QO14"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And String data in "Parameter_EndsWithField" "endswith" "Parameter_EndsWithValue"

  @REQ-WA103-QO15 @platinum @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - String: starts with
    When a GET request is made to the resolved Url in "REQ-WA103-QO15"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And String data in "Parameter_StartsWithField" "startswith" "Parameter_StartsWithValue"

  @REQ-WA103-QO16 @platinum @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - String: tolower() support
    When a GET request is made to the resolved Url in "REQ-WA103-QO16"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And String data in "Parameter_ToLowerField" "tolower" "Parameter_ToLowerValue"

  @REQ-WA103-QO17 @platinum @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - String: toupper() support
    When a GET request is made to the resolved Url in "REQ-WA103-QO17"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And String data in "Parameter_ToUpperField" "toupper" "Parameter_ToUpperValue"
  