# This file was autogenerated on: 20200623200643815
Feature: PropertyPowerProduction

  Background:
    Given a RESOScript file was provided
    And Client Settings and Parameters were read from the file
    And a test container was successfully created from the given RESOScript
    And the test container uses an Authorization Code or Client Credentials for authentication
    And valid metadata were retrieved from the server

  @ListingId @PropertyPowerProduction
  Scenario: ListingId
    When "ListingId" exists in the "PropertyPowerProduction" metadata
    Then "ListingId" MUST be "String" data type
    And "ListingId" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @ListingKey @PropertyPowerProduction
  Scenario: ListingKey
    When "ListingKey" exists in the "PropertyPowerProduction" metadata
    Then "ListingKey" MUST be "String" data type
    And "ListingKey" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @ListingKeyNumeric @PropertyPowerProduction
  Scenario: ListingKeyNumeric
    When "ListingKeyNumeric" exists in the "PropertyPowerProduction" metadata
    Then "ListingKeyNumeric" MUST be "Integer" data type

  @ModificationTimestamp @PropertyPowerProduction
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "PropertyPowerProduction" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @PowerProductionAnnual @PropertyPowerProduction
  Scenario: PowerProductionAnnual
    When "PowerProductionAnnual" exists in the "PropertyPowerProduction" metadata
    Then "PowerProductionAnnual" MUST be "Integer" data type

  @PowerProductionAnnualStatus @PropertyPowerProduction
  Scenario: PowerProductionAnnualStatus
    When "PowerProductionAnnualStatus" exists in the "PropertyPowerProduction" metadata
    Then "PowerProductionAnnualStatus" MUST be "Single Enumeration" data type
    And "PowerProductionAnnualStatus" MUST contain only standard enumerations
    And "PowerProductionAnnualStatus" MUST contain at least one standard enumeration

  @PowerProductionKey @PropertyPowerProduction
  Scenario: PowerProductionKey
    When "PowerProductionKey" exists in the "PropertyPowerProduction" metadata
    Then "PowerProductionKey" MUST be "String" data type
    And "PowerProductionKey" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @PowerProductionKeyNumeric @PropertyPowerProduction
  Scenario: PowerProductionKeyNumeric
    When "PowerProductionKeyNumeric" exists in the "PropertyPowerProduction" metadata
    Then "PowerProductionKeyNumeric" MUST be "Integer" data type

  @PowerProductionSize @PropertyPowerProduction
  Scenario: PowerProductionSize
    When "PowerProductionSize" exists in the "PropertyPowerProduction" metadata
    Then "PowerProductionSize" MUST be "Decimal" data type
    And "PowerProductionSize" precision SHOULD be less than or equal to the RESO Suggested Max Length of 5
    And "PowerProductionSize" scale SHOULD be less than or equal to the RESO Suggested Max Scale of 2

  @PowerProductionType @PropertyPowerProduction
  Scenario: PowerProductionType
    When "PowerProductionType" exists in the "PropertyPowerProduction" metadata
    Then "PowerProductionType" MUST be "Single Enumeration" data type

  @PowerProductionYearInstall @PropertyPowerProduction
  Scenario: PowerProductionYearInstall
    When "PowerProductionYearInstall" exists in the "PropertyPowerProduction" metadata
    Then "PowerProductionYearInstall" MUST be "Integer" data type
