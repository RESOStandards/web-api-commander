Feature: Commander Platinum Web API Test Container Tests

  Background:
    Given a Web API test container was created using the RESOScript "mock-platinum.resoscript"
    And a Commander instance exists within the test container

  Scenario: Test Container is Initialized using Token-Based Authentication
    When sample metadata from "good-edmx-and-edm.xml" are loaded into the test container
    And Settings are present in the test container
    And an auth token is provided in "ClientSettings_BearerToken"
    Then the Commander is created using auth token client mode
    And the auth token has a value of "testTokenValue"
    But the Commander is not created using client credentials mode

  Scenario: Metadata validation returns true for known-good metadata
    When sample metadata from "good-edmx-and-edm.xml" are loaded into the test container
    Then metadata are valid

  Scenario: Metadata validation returns false for known-bad metadata
    When sample metadata from "bad-edmx-no-keyfield.xml" are loaded into the test container
    Then metadata are invalid

  Scenario: DataSystem validation returns true for known-good sample data
    When sample JSON data from "good-datasystem.json" are loaded into the test container
    Then schema validation passes for the sample DataSystem data

  Scenario: DataSystem validation returns false for known-bad sample data
    When sample JSON data from "bad-datasystem.json" are loaded into the test container
    Then schema validation fails for the sample DataSystem data