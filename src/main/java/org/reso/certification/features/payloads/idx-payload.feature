Feature: IDX Payload Endorsement (Web API)
  All Scenarios passing means the given Web API server passes the IDX Payloads Endorsement
  # SEE: https://docs.google.com/document/d/1btCduOpWWzeadeMcSviA8M9dclIz23P-bPUGKwcD0NY/edit?usp=sharing

  Background:
    Given a RESOScript file was provided
    And Client Settings and Parameters were read from the file
    And a test container was successfully created from the given RESOScript
    And the test container uses an authorization_code or client_credentials for authentication

  @metadata-validation @idx-payload-endorsement @dd-1.7 @web-api-1.0.2
  Scenario: metadata-validation - Request and Validate Server Metadata
    When XML Metadata are requested from the service root in "ClientSettings_WebAPIURI"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the XML Metadata response is valid XML
    And the XML Metadata returned by the server are valid
    And the XML Metadata returned by the server contains Edm metadata
    And the Edm metadata returned by the server are valid
    And the metadata contains a valid service document

  @standard-resource-sampling @idx-payload-endorsement
  Scenario: Standard Resource Sampling - Request Data from Each Server Resource
    Given that metadata have been requested from the server
    And the metadata contains RESO Standard Resources
    When up to 10000 records are sampled from each RESO Standard resource in the server metadata
    Then each record MUST have the string version of the Primary Key and ModificationTimestamp field
    And the data MUST match what is advertised in the metadata

  @non-standard-resource-sampling @idx-payload-endorsement
  Scenario: Non Standard Resource Sampling - Request Data from Each Server Resource
    Given that metadata have been requested from the server
    When up to 1000 records are sampled from each non standard resource in the server metadata
    Then the data MUST match what is advertised in the metadata