# This file was autogenerated on: 20231108150315330
Feature: Field

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

  @Field @dd-1.7
  Scenario: FieldKey
    When "FieldKey" exists in the "Field" metadata
    Then "FieldKey" MUST be "String" data type

  @Field @dd-1.7
  Scenario: ResourceName
    When "ResourceName" exists in the "Field" metadata
    Then "ResourceName" MUST be "String" data type

  @Field @dd-1.7
  Scenario: FieldName
    When "FieldName" exists in the "Field" metadata
    Then "FieldName" MUST be "String" data type

  @Field @dd-1.7
  Scenario: DisplayName
    When "DisplayName" exists in the "Field" metadata
    Then "DisplayName" MUST be "String" data type

  @Field @dd-1.7
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "Field" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type
