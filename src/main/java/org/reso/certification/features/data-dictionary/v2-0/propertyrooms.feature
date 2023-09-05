# This file was autogenerated on: 2023090509434487
Feature: PropertyRooms

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

  @PropertyRooms @dd-2.0
  Scenario: ListingId
    Given that the following synonyms for "ListingId" DO NOT exist in the "PropertyRooms" metadata
      | MLNumber |
      | MLSNumber |
      | ListingNumber |
    When "ListingId" exists in the "PropertyRooms" metadata
    Then "ListingId" MUST be "String" data type
    And "ListingId" length SHOULD be equal to the RESO Suggested Max Length of 255

  @PropertyRooms @dd-2.0
  Scenario: ListingKey
    Given that the following synonyms for "ListingKey" DO NOT exist in the "PropertyRooms" metadata
      | SystemUniqueID |
      | ImmediateSourceID |
    When "ListingKey" exists in the "PropertyRooms" metadata
    Then "ListingKey" MUST be "String" data type
    And "ListingKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @PropertyRooms @dd-2.0
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "PropertyRooms" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @PropertyRooms @dd-2.0
  Scenario: RoomArea
    When "RoomArea" exists in the "PropertyRooms" metadata
    Then "RoomArea" MUST be "Decimal" data type
    And "RoomArea" precision SHOULD be equal to the RESO Suggested Max Precision of 14
    And "RoomArea" scale SHOULD be equal to the RESO Suggested Max Scale of 2

  @PropertyRooms @dd-2.0
  Scenario: RoomAreaSource
    When "RoomAreaSource" exists in the "PropertyRooms" metadata
    Then "RoomAreaSource" MUST be "Single Enumeration" data type

  @PropertyRooms @dd-2.0
  Scenario: RoomAreaUnits
    When "RoomAreaUnits" exists in the "PropertyRooms" metadata
    Then "RoomAreaUnits" MUST be "Single Enumeration" data type

  @PropertyRooms @dd-2.0
  Scenario: RoomDescription
    When "RoomDescription" exists in the "PropertyRooms" metadata
    Then "RoomDescription" MUST be "String" data type
    And "RoomDescription" length SHOULD be equal to the RESO Suggested Max Length of 1024

  @PropertyRooms @dd-2.0
  Scenario: RoomDimensions
    When "RoomDimensions" exists in the "PropertyRooms" metadata
    Then "RoomDimensions" MUST be "String" data type
    And "RoomDimensions" length SHOULD be equal to the RESO Suggested Max Length of 50

  @PropertyRooms @dd-2.0
  Scenario: RoomFeatures
    When "RoomFeatures" exists in the "PropertyRooms" metadata
    Then "RoomFeatures" MUST be "Multiple Enumeration" data type

  @PropertyRooms @dd-2.0
  Scenario: RoomKey
    When "RoomKey" exists in the "PropertyRooms" metadata
    Then "RoomKey" MUST be "String" data type
    And "RoomKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @PropertyRooms @dd-2.0
  Scenario: RoomLength
    When "RoomLength" exists in the "PropertyRooms" metadata
    Then "RoomLength" MUST be "Decimal" data type
    And "RoomLength" precision SHOULD be equal to the RESO Suggested Max Precision of 14
    And "RoomLength" scale SHOULD be equal to the RESO Suggested Max Scale of 2

  @PropertyRooms @dd-2.0
  Scenario: RoomLengthWidthSource
    When "RoomLengthWidthSource" exists in the "PropertyRooms" metadata
    Then "RoomLengthWidthSource" MUST be "Single Enumeration" data type

  @PropertyRooms @dd-2.0
  Scenario: RoomLengthWidthUnits
    When "RoomLengthWidthUnits" exists in the "PropertyRooms" metadata
    Then "RoomLengthWidthUnits" MUST be "Single Enumeration" data type

  @PropertyRooms @dd-2.0
  Scenario: RoomLevel
    When "RoomLevel" exists in the "PropertyRooms" metadata
    Then "RoomLevel" MUST be "Single Enumeration" data type

  @PropertyRooms @dd-2.0
  Scenario: RoomType
    When "RoomType" exists in the "PropertyRooms" metadata
    Then "RoomType" MUST be "Single Enumeration" data type

  @PropertyRooms @dd-2.0
  Scenario: RoomWidth
    When "RoomWidth" exists in the "PropertyRooms" metadata
    Then "RoomWidth" MUST be "Decimal" data type
    And "RoomWidth" precision SHOULD be equal to the RESO Suggested Max Precision of 14
    And "RoomWidth" scale SHOULD be equal to the RESO Suggested Max Scale of 2

  @PropertyRooms @dd-2.0
  Scenario: BedroomClosetType
    When "BedroomClosetType" exists in the "PropertyRooms" metadata
    Then "BedroomClosetType" MUST be "Single Enumeration" data type
