Feature: Check Data Dictionary Variations

  Background:
    Given a RESOScript or Metadata file are provided
    When a RESOScript file is provided
    Then Client Settings and Parameters can be read from the RESOScript
    And a test container was successfully created from the given RESOScript file
    And the test container uses an Authorization Code or Client Credentials for authentication
    And valid metadata were retrieved from the server
    When a metadata file is provided
    Then a test container was successfully created from the given metadata file
    And valid metadata are loaded into the test container

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