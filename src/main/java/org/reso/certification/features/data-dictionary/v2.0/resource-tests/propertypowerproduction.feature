# This file was autogenerated on: 20241014212338838
Feature: PropertyPowerProduction

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

  @PropertyPowerProduction @dd-2.0
  Scenario: ListingId
    Given that the following synonyms for "ListingId" DO NOT exist in the "PropertyPowerProduction" metadata
      | MLNumber |
      | MLSNumber |
      | ListingNumber |
    When "ListingId" exists in the "PropertyPowerProduction" metadata
    Then "ListingId" MUST be "String" data type
    And "ListingId" length SHOULD be equal to the RESO Suggested Max Length of 255

  @PropertyPowerProduction @dd-2.0
  Scenario: ListingKey
    Given that the following synonyms for "ListingKey" DO NOT exist in the "PropertyPowerProduction" metadata
      | SystemUniqueID |
      | ImmediateSourceID |
    When "ListingKey" exists in the "PropertyPowerProduction" metadata
    Then "ListingKey" MUST be "String" data type
    And "ListingKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @PropertyPowerProduction @dd-2.0
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "PropertyPowerProduction" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @PropertyPowerProduction @dd-2.0
  Scenario: PowerProductionAnnual
    When "PowerProductionAnnual" exists in the "PropertyPowerProduction" metadata
    Then "PowerProductionAnnual" MUST be "Integer" data type

  @PropertyPowerProduction @dd-2.0
  Scenario: PowerProductionAnnualStatus
    When "PowerProductionAnnualStatus" exists in the "PropertyPowerProduction" metadata
    Then "PowerProductionAnnualStatus" MUST be "Single Enumeration" data type

  @PropertyPowerProduction @dd-2.0
  Scenario: PowerProductionKey
    When "PowerProductionKey" exists in the "PropertyPowerProduction" metadata
    Then "PowerProductionKey" MUST be "String" data type
    And "PowerProductionKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @PropertyPowerProduction @dd-2.0
  Scenario: PowerProductionOwnership
    When "PowerProductionOwnership" exists in the "PropertyPowerProduction" metadata
    Then "PowerProductionOwnership" MUST be "Single Enumeration" data type

  @PropertyPowerProduction @dd-2.0
  Scenario: PowerProductionSize
    When "PowerProductionSize" exists in the "PropertyPowerProduction" metadata
    Then "PowerProductionSize" MUST be "Decimal" data type
    And "PowerProductionSize" precision SHOULD be equal to the RESO Suggested Max Precision of 5
    And "PowerProductionSize" scale SHOULD be equal to the RESO Suggested Max Scale of 2

  @PropertyPowerProduction @dd-2.0
  Scenario: PowerProductionType
    When "PowerProductionType" exists in the "PropertyPowerProduction" metadata
    Then "PowerProductionType" MUST be "Single Enumeration" data type

  @PropertyPowerProduction @dd-2.0
  Scenario: PowerProductionYearInstall
    When "PowerProductionYearInstall" exists in the "PropertyPowerProduction" metadata
    Then "PowerProductionYearInstall" MUST be "Integer" data type
