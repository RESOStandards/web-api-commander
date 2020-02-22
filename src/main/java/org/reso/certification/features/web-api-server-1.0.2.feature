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
    And the results match the expected DataSystem JSON schema

  @REQ-WA103-QR1 @core @2.4.1 @query-functions-endorsement
  Scenario: Search Parameters: Select UniqueID
    When a GET request is made to the resolved Url in "REQ-WA103-QR1.select.uniqueId"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the provided "Parameter_UniqueIDValue" is returned in the "Parameter_UniqueID" field

  @REQ-WA103-QR3 @core @2.4.2 @query-functions-endorsement
  Scenario: Query Support: $select
    When a GET request is made to the resolved Url in "REQ-WA103-QR3.select"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And data are present in fields contained within "Parameter_SelectList"

  @REQ-WA103-QR4 @core @2.4.2 @client-paging-endorsement
  Scenario: Query Support: $top
    When a GET request is made to the resolved Url in "REQ-WA103-QR4.top"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And the results contain at most "Parameter_TopCount" records

  @REQ-WA103-QR5 @core @2.4.2 @query-support-endorsement
  Scenario: Query Support: $skip
    When a GET request is made to the resolved Url in "REQ-WA103-QR5.skip"
    Then the server responds with a status code of 200
    And the response is valid JSON
    And a GET request is made to the resolved Url in "REQ-WA103-QR5.skip" with $skip="Parameter_TopCount"
    Then the server responds with a status code of 200
    And the response is valid JSON
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
