Feature: Payloads Sampling (Web API)
  All Scenarios passing means the given Web API Server has completed data availability testing
  # SEE: https://docs.google.com/document/d/1btCduOpWWzeadeMcSviA8M9dclIz23P-bPUGKwcD0NY/edit?usp=sharing

  Background:
    Given a RESOScript file was provided
    And Client Settings and Parameters were read from the file
    And a test container was successfully created from the given RESOScript
    And the test container uses an authorization_code or client_credentials for authentication

  @metadata-validation @payloads-sampling @dd-1.7
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

  @standard-resource-sampling @dd-1.7 @payloads-sampling
  Scenario: Standard Resource Sampling
    Given that metadata have been retrieved from the server and validated
    And the metadata contains RESO Standard Resources
    And "payload-samples" has been created in the build directory
    Then up to 100000 records are sampled from each resource with payload samples stored in "payload-samples"

  @local-resource-sampling @dd-1.7 @payloads-sampling
  Scenario: Non Standard Resource Sampling - Request Data from Each Server Resource
    Given that metadata have been retrieved from the server and validated
    And the metadata contains local resources
    Then up to 100000 records are sampled from each local resource

  @payloads-sampling @dd-1.7
  Scenario: A Data Availability Report is Created from Sampled Records
    Given standard and local resources have been processed
    Then a data availability report is created in "data-availability-report.json"
