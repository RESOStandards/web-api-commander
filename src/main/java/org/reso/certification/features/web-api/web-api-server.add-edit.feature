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
  #
  # This is without the prefer header and minimal value
  #
  @create @create-succeeds @add-edit-endorsement @rcp-010 @2.0.0
  Scenario: Create operation succeeds using a given payload
    Given valid metadata have been retrieved
    And request data has been provided in "create-succeeds.json"
    And request data in "create-succeeds.json" is valid JSON
    And schema in "create-succeeds.json" matches the metadata
    And the request header "OData-Version" "equals" one of the following values
      |4.0|4.01|
    And the request header "Content-Type" "contains" "application/json"
    And the request header "Accept" "contains" "application/json"
    When a "POST" request is made to the "resource-endpoint" URL with data in "create-succeeds.json"
    Then the server responds with one of the following status codes
      |201|
    And the response header "OData-Version" "equals" one of the following values
      |4.0|4.01|
    And the response header "EntityId" "MUST" "be present"
    And the response header "Location" "MUST" "be present"
    And the response header "Location" "is a valid URL"
    And the response header "Location" "MUST" reference the resource being created
    And the response is valid JSON
    And the JSON response "MUST" contain "@odata.context"
    And the JSON response value "@odata.context" "is a valid URL"
    And the JSON response "MUST" contain "@odata.id"
    And the JSON response value "@odata.id" "is a valid URL"
    And the JSON response "MAY" contain "@odata.editLink"
    And the JSON response value "@odata.editLink" "is a valid URL"
    And the JSON response "MAY" contain "@odata.etag"
    And the JSON response value "@odata.etag" "starts with" "W/"
    And the JSON response "MUST" contain all JSON data in "create-succeeds.json"
    When a "GET" request is made to the URL in response header "Location"
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
  #      Content-Type: application/json
  #      Accept: application/json
  @create @create-fails @add-edit-endorsement @rcp-010 @2.0.0
  Scenario: Create operation fails using a given payload
    Given valid metadata have been retrieved
    And request data has been provided in "create-fails.json"
    And request data in "create-fails.json" is valid JSON
    And schema in "create-fails.json" matches the metadata
    And the request header "OData-Version" "equals" one of the following values
      |4.0|4.01|
    And the request header "Content-Type" "MUST" "be present"
    And the request header "Content-Type" "equals" "application/json"
    And the request header "Accept" "MUST" "be present"
    And the request header "Accept" "contains" "application/json"
    When a "POST" request is made to the "resource-endpoint" URL with data in "create-fails.json"
    Then the server responds with one of the following error codes
      |400|
    And the response has header "OData-Version" with one of the following values
      |4.0|4.01|
    And the error response is in a valid format
    And the values in the "target" field in the JSON payload "error.details" path are contained within the metadata
