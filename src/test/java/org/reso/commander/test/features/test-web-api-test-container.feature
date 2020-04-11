Feature: Commander Platinum Web API Test Container

  Background:
    Given a Web API test container was created using the RESOScript "mock-platinum.resoscript"
    And metadata were loaded from the sample resource "good-edmx-and-edm.xml"

  Scenario: Test Container is using Token-Based Authentication
    Given a Commander instance exists within the test container
    And Settings are present in the test container
    And an auth token is provided in "ClientSettings_BearerToken"
    Then the Commander is created using auth token client mode
    And the auth token has a value of "testTokenValue"
    But the Commander is not created using client credentials mode

  Scenario: Test that the container can validate known-good metadata
