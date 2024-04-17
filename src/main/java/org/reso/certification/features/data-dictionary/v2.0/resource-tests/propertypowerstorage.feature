# This file was autogenerated on: 20240416183305400
Feature: PropertyPowerStorage

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

  @PropertyPowerStorage @dd-2.0
  Scenario: DateOfInstallation
    When "DateOfInstallation" exists in the "PropertyPowerStorage" metadata
    Then "DateOfInstallation" MUST be "Date" data type

  @PropertyPowerStorage @dd-2.0
  Scenario: InformationSource
    When "InformationSource" exists in the "PropertyPowerStorage" metadata
    Then "InformationSource" MUST be "String" data type
    And "InformationSource" length SHOULD be equal to the RESO Suggested Max Length of 255

  @PropertyPowerStorage @dd-2.0
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "PropertyPowerStorage" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @PropertyPowerStorage @dd-2.0
  Scenario: NameplateCapacity
    When "NameplateCapacity" exists in the "PropertyPowerStorage" metadata
    Then "NameplateCapacity" MUST be "Decimal" data type
    And "NameplateCapacity" precision SHOULD be equal to the RESO Suggested Max Precision of 3
    And "NameplateCapacity" scale SHOULD be equal to the RESO Suggested Max Scale of 2

  @PropertyPowerStorage @dd-2.0
  Scenario: PowerStorageKey
    When "PowerStorageKey" exists in the "PropertyPowerStorage" metadata
    Then "PowerStorageKey" MUST be "String" data type
    And "PowerStorageKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @PropertyPowerStorage @dd-2.0
  Scenario: PowerStorageType
    When "PowerStorageType" exists in the "PropertyPowerStorage" metadata
    Then "PowerStorageType" MUST be "Single Enumeration" data type
