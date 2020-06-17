Feature: Check Data Dictionary Variations

  Background:
    Given a RESOScript file was provided
    And Client Settings and Parameters were read from the file
    And a test container was successfully created from the given RESOScript
    And the test container uses an Authorization Code or Client Credentials for authentication
    And valid metadata were retrieved from the server

  Scenario: Check for Data Dictionary Synonyms
    Given metadata exist in the test container
    Then field synonyms MUST NOT exist in the metadata
    And enumeration synonyms MUST NOT exist in the metadata

  Scenario: Check for Similar Standard Names
    Given metadata exist in the test container
    Then fields are checked for similarity with standard names
    And enumerations are checked for similarly with standard names

  Scenario: Check for Substring Matches of Standard Names
    Given metadata exist in the test container
    Then fields are checked for substring matches with standard names
    And enumerations are checked for substring matches with standard names