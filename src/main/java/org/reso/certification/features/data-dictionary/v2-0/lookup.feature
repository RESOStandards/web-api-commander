# This file was autogenerated on: 2023031911041682
Feature: Lookup

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

  @Lookup
  Scenario: LegacyODataValue
    When "LegacyODataValue" exists in the "Lookup" metadata
    Then "LegacyODataValue" MUST be "String" data type

  @Lookup
  Scenario: LookupKey
    When "LookupKey" exists in the "Lookup" metadata
    Then "LookupKey" MUST be "String" data type

  @Lookup
  Scenario: LookupName
    When "LookupName" exists in the "Lookup" metadata
    Then "LookupName" MUST be "String" data type

  @Lookup
  Scenario: LookupValue
    When "LookupValue" exists in the "Lookup" metadata
    Then "LookupValue" MUST be "String" data type

  @Lookup
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "Lookup" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @Lookup
  Scenario: StandardLookupValue
    When "StandardLookupValue" exists in the "Lookup" metadata
    Then "StandardLookupValue" MUST be "String" data type