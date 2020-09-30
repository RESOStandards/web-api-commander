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
    When metadata are checked for synonyms
    Then field synonyms MUST NOT exist in the metadata
    And enumeration synonyms MUST NOT exist in the metadata

  Scenario: Check for Similar Standard Names
    When metadata are checked for similar standard names
    Then fields that are similar to standard names MAY cause certification to fail
    And enumerations that are similar to standard names MAY cause Certification to fail

  Scenario: Check for Substring Matches of Standard Names
    When metadata are checked for substring matches of standard names
    Then fields with substring matches of standard names MAY cause certification to fail
    And enumerations with substring matches of standard names MAY cause certification to fail