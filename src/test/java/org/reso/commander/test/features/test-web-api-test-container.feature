Feature: Commander Platinum Web API Test Container Tests

  Background:
    Given a Web API test container was created using the RESOScript "mock-platinum.resoscript"

  Scenario: Test Container is Initialized using Token-Based Authentication
    Given a Commander instance exists within the test container
    And sample metadata from "good-edmx-and-edm.xml" are loaded into the test container
    And Settings are present in the test container
    And an auth token is provided in "ClientSettings_BearerToken"
    Then the Commander is created using auth token client mode
    And the auth token has a value of "testTokenValue"
    But the Commander is not created using client credentials mode

  Scenario: Metadata validation returns true for known-good metadata
    Given a Commander instance exists within the test container
    And sample metadata from "good-edmx-and-edm.xml" are loaded into the test container
    Then metadata are valid

  Scenario: Metadata validation returns false for known-bad metadata
    Given a Commander instance exists within the test container
    And sample metadata from "bad-edmx-no-keyfield.xml" are loaded into the test container
    Then metadata are invalid