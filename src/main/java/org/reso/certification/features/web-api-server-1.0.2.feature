Feature: Web API Server 1.0.2 Certification
  All Scenarios Passing means that the Web API server is fully-compliant with the RESO specification.
  It's not expected that a server will pass all scenarios.

  Background:
    Given a RESOScript file was provided
    And Client Settings and Parameters were read from the file
    And an OData client was successfully created from the given RESOScript

  @REQ-WA103-END3 @core @x.y.z @core-endorsement
  Scenario: Request and Validate Server Metadata
    When a GET request is made to the resolved Url in "REQ-WA103-END3.metadata"
    Then the server responds with a status code of 200
    And the response is valid XML
    And the metadata returned is valid

  @REQ-WA103-END2 @core @x.y.z @core-endorsement
  Scenario: Data System Endpoint test
    When a GET request is made to the resolved Url in "REQ-WA103-END2.datasystem"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And the results match the expected DataSystem JSON schema

  @REQ-WA103-QR1 @core @2.4.1 @query-functions-endorsement
  Scenario: Search Parameters: Select UniqueID
    When a GET request is made to the resolved Url in "REQ-WA103-QR1.select.uniqueId"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has singleton results in the "Parameter_UniqueID" field
    And the provided "Parameter_UniqueIDValue" is returned in the "Parameter_UniqueID" field

  @REQ-WA103-QR3 @core @2.4.2 @query-functions-endorsement
  Scenario: Query Support: $select
    When a GET request is made to the resolved Url in "REQ-WA103-QR3.select"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And data are present in fields contained within "Parameter_SelectList"

  @REQ-WA103-QR4 @core @2.4.2 @client-paging-endorsement
  Scenario: Query Support: $top
    When a GET request is made to the resolved Url in "REQ-WA103-QR4.top"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And the number of results is less than or equal to "Parameter_TopCount"

  @REQ-WA103-QR5 @core @2.4.2 @query-support-endorsement
  Scenario: Query Support: $skip
    When a GET request is made to the resolved Url in "REQ-WA103-QR5.skip"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And a GET request is made to the resolved Url in "REQ-WA103-QR5.skip" with $skip="Parameter_TopCount"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And data in the "Parameter_UniqueId" fields are different in the second request than in the first

  @REQ-WA103-QO1 @REQ-WA103-QO1.select @core @2.4.4 @core-endorsement @OData-4.0
  Scenario: Query Support: $select case-sensitivity for OData 4.0
    When a GET request is made to the resolved Url in "REQ-WA103-QO1.select.case"
    Then the server responds with a status code of 400 if the server headers report OData version "4.0"

  @REQ-WA103-QO1 @REQ-WA103-QO1.filter @core @2.4.4 @core-endorsement @OData-4.0
  Scenario: Query Support: $filter case-sensitivity for OData 4.0
    When a GET request is made to the resolved Url in "REQ-WA103-QO1.filter.case"
    Then the server responds with a status code of 400 if the server headers report OData version "4.0"

  @REQ-WA103-QO1 @REQ-WA103-QO1.orderby.asc.case @core @2.4.4 @core-endorsement @OData-4.0
  Scenario: Query Support: $orderby asc case-sensitivity for OData 4.0
    When a GET request is made to the resolved Url in "REQ-WA103-QO1.orderby.asc.case"
    Then the server responds with a status code of 400 if the server headers report OData version "4.0"

  @REQ-WA103-QO1 @REQ-WA103-QO1.orderby.desc.case @core @2.4.4 @core-endorsement @OData-4.0
  Scenario: Query Support: $orderby desc case-sensitivity for OData 4.0
    When a GET request is made to the resolved Url in "REQ-WA103-QO1.orderby.desc.case"
    Then the server responds with a status code of 400 if the server headers report OData version "4.0"

  @REQ-WA103-QO2 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: eq
    When a GET request is made to the resolved Url in "REQ-WA103-QO2.filter.int.compare.eq"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterIntegerField" "eq" "Parameter_FilterIntegerValueLow"

  @REQ-WA103-QO3 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: ne
    When a GET request is made to the resolved Url in "REQ-WA103-QO3.filter.int.compare.ne"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterIntegerField" "ne" "Parameter_FilterIntegerValueLow"

  @REQ-WA103-QO4 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: gt
    When a GET request is made to the resolved Url in "REQ-WA103-QO4.filter.int.compare.gt"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterIntegerField" "gt" "Parameter_FilterIntegerValueLow"

  @REQ-WA103-QO5 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: ge
    When a GET request is made to the resolved Url in "REQ-WA103-QO5.filter.int.compare.ge"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterIntegerField" "ge" "Parameter_FilterIntegerValueLow"

  @REQ-WA103-QO6 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: lt
    When a GET request is made to the resolved Url in "REQ-WA103-QO6.filter.int.compare.lt"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterIntegerField" "lt" "Parameter_FilterIntegerValueLow"

  @REQ-WA103-QO7 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: le
    When a GET request is made to the resolved Url in "REQ-WA103-QO7.filter.int.compare.le"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterIntegerField" "le" "Parameter_FilterIntegerValueLow"

  @REQ-WA103-QO9 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: and
    When a GET request is made to the resolved Url in "REQ-WA103-QO9.filter.int.compare.and"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterIntegerField" "gt" "Parameter_FilterIntegerValueLow" "and" "lt" "Parameter_FilterIntegerValueHigh"

  @REQ-WA103-QO10 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: or
    When a GET request is made to the resolved Url in "REQ-WA103-QO10.filter.int.compare.or"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterIntegerField" "gt" "Parameter_FilterIntegerValueLow" "or" "lt" "Parameter_FilterIntegerValueHigh"

  @REQ-WA103-QO11 @core @2.4.4 @filterability-endorsement
  Scenario: Query Support: $filter - Integer Comparison: not() (operator)
    When a GET request is made to the resolved Url in "REQ-WA103-QO11.filter.int.compare.not.operator"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the response has results
    And Integer data in "Parameter_FilterNotField" "ne" "Parameter_FilterNotValue"