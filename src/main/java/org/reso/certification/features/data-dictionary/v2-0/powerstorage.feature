# This file was autogenerated on: 2023090509434487
Feature: PowerStorage

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

  @PowerStorage @dd-2.0
  Scenario: PowerStorageKey
    When "PowerStorageKey" exists in the "PowerStorage" metadata
    Then "PowerStorageKey" MUST be "String" data type
    And "PowerStorageKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @PowerStorage @dd-2.0
  Scenario: PowerStorageType
    When "PowerStorageType" exists in the "PowerStorage" metadata
    Then "PowerStorageType" MUST be "Single Enumeration" data type

  @PowerStorage @dd-2.0
  Scenario: NameplateCapacity
    When "NameplateCapacity" exists in the "PowerStorage" metadata
    Then "NameplateCapacity" MUST be "Integer" data type

  @PowerStorage @dd-2.0
  Scenario: DateOfInstallation
    When "DateOfInstallation" exists in the "PowerStorage" metadata
    Then "DateOfInstallation" MUST be "Date" data type

  @PowerStorage @dd-2.0
  Scenario: InformationSource
    When "InformationSource" exists in the "PowerStorage" metadata
    Then "InformationSource" MUST be "String" data type
    And "InformationSource" length SHOULD be equal to the RESO Suggested Max Length of 255
