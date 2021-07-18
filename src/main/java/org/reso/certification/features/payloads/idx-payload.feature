Feature: Payloads Sampling (Web API)
  All Scenarios passing means the given Web API Server has completed data availability testing
  # SEE: https://docs.google.com/document/d/1btCduOpWWzeadeMcSviA8M9dclIz23P-bPUGKwcD0NY/edit?usp=sharing

  Background:
    Given a RESOScript file was provided
    And Client Settings and Parameters were read from the file
    And a test container was successfully created from the given RESOScript
    And the test container uses an authorization_code or client_credentials for authentication

  # TODO: tie back into common metadata validation shared scenario
  @metadata-validation @idx-payload-endorsement @dd-1.7
  Scenario: Request and Validate Server Metadata
    When XML Metadata are requested from the service root in "ClientSettings_WebAPIURI"
    Then the server responds with a status code of 200
    And the server has an OData-Version header value of "4.0" or "4.01"
    And the XML Metadata response is valid XML
    And the XML Metadata returned by the server are valid
    And the XML Metadata returned by the server contains Edm metadata
    And the Edm metadata returned by the server are valid
    And the metadata contains a valid service document
    And each resource MUST have a primary key field by the OData specification

  @idx-payload-endorsement @dd-1.7
  Scenario: IDX User Sampling
    Given samples exist in "payload-samples" in the build directory
    And a RESOScript file was provided for the IDX User
    And Client Settings and Parameters were read from the file
    And a test container was successfully created from the given RESOScript
    And the test container uses an authorization_code or client_credentials for authentication
    When samples from "payload-samples" are fetched as the representative user for each resource in the "IDX" payload
    Then each result MUST contain the string version of the key and the following fields
      |ModificationTimestamp|
    And the "IDX" payload field values MUST match those in the samples