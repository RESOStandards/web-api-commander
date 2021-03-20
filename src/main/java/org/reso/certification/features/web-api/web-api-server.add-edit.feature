Feature: Web API Server Add/Edit Endorsement
  The Add/Edit Endorsement contains tests for Create, Update, and Delete actions, which are MAY requirements at this time.
  # SEE: https://reso.atlassian.net/wiki/spaces/RESOWebAPIRCP/pages/2239399511/RCP+-+WEBAPI-010+Add+Functionality+to+Web+API+Specification

  Background:
    Given a RESOScript file was provided
    And Client Settings and Parameters were read from the file
    And a test container was successfully created from the given RESOScript
    And the test container uses an authorization_code or client_credentials for authentication

  #
  # Create Record Succeeds
  #
  # SEE: https://reso.atlassian.net/wiki/spaces/RESOWebAPIRCP/pages/2239399511/RCP+-+WEBAPI-010+Add+Functionality+to+Web+API+Specification#Example-1---Request
  # POST serviceRoot/Property
  #      OData-Version: 4.01
  #      Content-Type: application/json;odata.metadata=minimal
  #      Accept: application/json
  @create @create-succeeds @add-edit-endorsement @rcp-010 @1.0.2
  Scenario: Create operation succeeds using a given payload
    Given valid metadata have been retrieved
    And request data has been provided in "create-succeeds.json"
    And request data in "create-succeeds.json" is valid JSON
    And schema in "create-succeeds.json" matches the metadata
    And the request header "OData-Version" is "4.01"
    And the request header "Content-Type" contains "application/json"
    And the request header "Accept" is "application/json"
    When a "POST" request is made to the "resource-endpoint" URL with data in "create-succeeds.json"
    Then the test is skipped if the server responds with a status code of 401
    # TODO: check spec for 204
    When the server responds with one of the following status codes
      |200|201|
    Then the response header "OData-Version" is "4.01"
    And the response header "EntityId" is present
    And the response header "Location" is present
    And the response header "Location" is a valid URL
    When the server responds with a 200 status code "valid JSON exists" in the JSON response
    When the server responds with a 200 status code "@odata.context" "is present" in the JSON response
    When the server responds with a 200 status code "@odata.context" "is a valid URL" in the JSON response
    When the server responds with a 200 status code "@odata.id" "is present" in the JSON response
    When the server responds with a 200 status code "@odata.id" "is a valid URL" in the JSON response
    When the server responds with a 200 status code "@odata.editLink" "is present" in the JSON response
    When the server responds with a 200 status code "@odata.editLink" "is a valid URL" in the JSON response
    When the server responds with a 200 status code "@odata.etag" "is present" in the JSON response
    When the server responds with a 200 status code "@odata.etag" "starts with" "W/" in the JSON response
    When the server responds with a 200 status code data from "create-succeeds.json" "exists" in the JSON response
    When a "GET" request is made to the response header "Location" URL
    Then the server responds with a status code of 200
    And the response has header "OData-Version" with one of the following values
      |4.0|4.01|
    And the JSON response contains the data in "create-succeeds.json"
    And the JSON response matches the format advertised in the metadata


  #
  # Create Record Fails
  #
  # SEE: https://reso.atlassian.net/wiki/spaces/RESOWebAPIRCP/pages/2239399511/RCP+-+WEBAPI-010+Add+Functionality+to+Web+API+Specification#Error-Message-Example
  # POST serviceRoot/Property
  #      OData-Version: 4.01
  #      Content-Type: application/json;odata.metadata=minimal
  #      Accept: application/json
  @create @create-fails @add-edit-endorsement @rcp-010 @1.0.2
  Scenario: Create operation fails using a given payload
    Given valid metadata have been retrieved
    And request data has been provided in "create-fails.json"
    And request data in "create-fails.json" is valid JSON
    And schema in "create-fails.json" matches the metadata
    And the request header "OData-Version" is "4.01"
    And the request header "Content-Type" is "application/json;odata.metadata=minimal"
    And the request header "Accept" is "application/json"
    When a "POST" request is made to the "resource-endpoint" URL with data in "create-fails.json"
    Then the server responds with one of the following error codes
      |400|401|403|405|408|500|501|503|
    And the response header "OData-Version" is "4.01"
    And the error response is in a valid format
    And the values in the "target" field in the JSON payload "error.details" path are contained within the metadata