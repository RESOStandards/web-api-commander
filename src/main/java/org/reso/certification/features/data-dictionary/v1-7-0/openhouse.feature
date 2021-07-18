# This file was autogenerated on: 20210717230753113
Feature: OpenHouse

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

  @OpenHouse
  Scenario: AppointmentRequiredYN
    When "AppointmentRequiredYN" exists in the "OpenHouse" metadata
    Then "AppointmentRequiredYN" MUST be "Boolean" data type

  @OpenHouse @IDX
  Scenario: ListingId
    Given that the following synonyms for "ListingId" DO NOT exist in the "OpenHouse" metadata
      | MLNumber |
      | MLSNumber |
      | ListingNumber |
    When "ListingId" exists in the "OpenHouse" metadata
    Then "ListingId" MUST be "String" data type
    And "ListingId" length SHOULD be equal to the RESO Suggested Max Length of 255

  @OpenHouse @IDX
  Scenario: ListingKey
    Given that the following synonyms for "ListingKey" DO NOT exist in the "OpenHouse" metadata
      | SystemUniqueID |
      | ImmediateSourceID |
    When "ListingKey" exists in the "OpenHouse" metadata
    Then "ListingKey" MUST be "String" data type
    And "ListingKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @OpenHouse @IDX
  Scenario: ListingKeyNumeric
    Given that the following synonyms for "ListingKeyNumeric" DO NOT exist in the "OpenHouse" metadata
      | SystemUniqueID |
      | ImmediateSourceID |
    When "ListingKeyNumeric" exists in the "OpenHouse" metadata
    Then "ListingKeyNumeric" MUST be "Integer" data type

  @OpenHouse @IDX
  Scenario: ModificationTimestamp
    Given that the following synonyms for "ModificationTimestamp" DO NOT exist in the "OpenHouse" metadata
      | ModificationDateTime |
      | DateTimeModified |
      | ModDate |
      | DateMod |
      | UpdateDate |
      | UpdateTimestamp |
    When "ModificationTimestamp" exists in the "OpenHouse" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @OpenHouse
  Scenario: OpenHouseAttendedBy
    When "OpenHouseAttendedBy" exists in the "OpenHouse" metadata
    Then "OpenHouseAttendedBy" MUST be "Single Enumeration" data type

  @OpenHouse @IDX
  Scenario: OpenHouseDate
    When "OpenHouseDate" exists in the "OpenHouse" metadata
    Then "OpenHouseDate" MUST be "Date" data type

  @OpenHouse @IDX
  Scenario: OpenHouseEndTime
    When "OpenHouseEndTime" exists in the "OpenHouse" metadata
    Then "OpenHouseEndTime" MUST be "Timestamp" data type

  @OpenHouse
  Scenario: OpenHouseId
    When "OpenHouseId" exists in the "OpenHouse" metadata
    Then "OpenHouseId" MUST be "String" data type
    And "OpenHouseId" length SHOULD be equal to the RESO Suggested Max Length of 255

  @OpenHouse @IDX
  Scenario: OpenHouseKey
    When "OpenHouseKey" exists in the "OpenHouse" metadata
    Then "OpenHouseKey" MUST be "String" data type
    And "OpenHouseKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @OpenHouse @IDX
  Scenario: OpenHouseKeyNumeric
    When "OpenHouseKeyNumeric" exists in the "OpenHouse" metadata
    Then "OpenHouseKeyNumeric" MUST be "Integer" data type

  @OpenHouse @IDX
  Scenario: OpenHouseRemarks
    When "OpenHouseRemarks" exists in the "OpenHouse" metadata
    Then "OpenHouseRemarks" MUST be "String" data type
    And "OpenHouseRemarks" length SHOULD be equal to the RESO Suggested Max Length of 500

  @OpenHouse @IDX
  Scenario: OpenHouseStartTime
    When "OpenHouseStartTime" exists in the "OpenHouse" metadata
    Then "OpenHouseStartTime" MUST be "Timestamp" data type

  @OpenHouse @IDX
  Scenario: OpenHouseStatus
    When "OpenHouseStatus" exists in the "OpenHouse" metadata
    Then "OpenHouseStatus" MUST be "Single Enumeration" data type

  @OpenHouse @IDX
  Scenario: OpenHouseType
    When "OpenHouseType" exists in the "OpenHouse" metadata
    Then "OpenHouseType" MUST be "Single Enumeration" data type

  @OpenHouse @IDX
  Scenario: OriginalEntryTimestamp
    Given that the following synonyms for "OriginalEntryTimestamp" DO NOT exist in the "OpenHouse" metadata
      | EntryDate |
      | InputDate |
      | DateTimeCreated |
      | CreatedDate. |
    When "OriginalEntryTimestamp" exists in the "OpenHouse" metadata
    Then "OriginalEntryTimestamp" MUST be "Timestamp" data type

  @OpenHouse @IDX
  Scenario: OriginatingSystemID
    When "OriginatingSystemID" exists in the "OpenHouse" metadata
    Then "OriginatingSystemID" MUST be "String" data type
    And "OriginatingSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @OpenHouse @IDX
  Scenario: OriginatingSystemKey
    Given that the following synonyms for "OriginatingSystemKey" DO NOT exist in the "OpenHouse" metadata
      | ProviderKey |
    When "OriginatingSystemKey" exists in the "OpenHouse" metadata
    Then "OriginatingSystemKey" MUST be "String" data type
    And "OriginatingSystemKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @OpenHouse
  Scenario: OriginatingSystemName
    Given that the following synonyms for "OriginatingSystemName" DO NOT exist in the "OpenHouse" metadata
      | ProviderName |
      | MLSID |
    When "OriginatingSystemName" exists in the "OpenHouse" metadata
    Then "OriginatingSystemName" MUST be "String" data type
    And "OriginatingSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @OpenHouse @IDX
  Scenario: Refreshments
    When "Refreshments" exists in the "OpenHouse" metadata
    Then "Refreshments" MUST be "String" data type
    And "Refreshments" length SHOULD be equal to the RESO Suggested Max Length of 255

  @OpenHouse
  Scenario: ShowingAgentFirstName
    Given that the following synonyms for "ShowingAgentFirstName" DO NOT exist in the "OpenHouse" metadata
      | ShowingMemberFirstName |
    When "ShowingAgentFirstName" exists in the "OpenHouse" metadata
    Then "ShowingAgentFirstName" MUST be "String" data type
    And "ShowingAgentFirstName" length SHOULD be equal to the RESO Suggested Max Length of 50

  @OpenHouse @IDX
  Scenario: ShowingAgentKey
    Given that the following synonyms for "ShowingAgentKey" DO NOT exist in the "OpenHouse" metadata
      | ShowingMemberKey |
    When "ShowingAgentKey" exists in the "OpenHouse" metadata
    Then "ShowingAgentKey" MUST be "String" data type
    And "ShowingAgentKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @OpenHouse @IDX
  Scenario: ShowingAgentKeyNumeric
    Given that the following synonyms for "ShowingAgentKeyNumeric" DO NOT exist in the "OpenHouse" metadata
      | ShowingMemberKeyNumeric |
    When "ShowingAgentKeyNumeric" exists in the "OpenHouse" metadata
    Then "ShowingAgentKeyNumeric" MUST be "Integer" data type

  @OpenHouse
  Scenario: ShowingAgentLastName
    Given that the following synonyms for "ShowingAgentLastName" DO NOT exist in the "OpenHouse" metadata
      | ShowingMemberLastName |
    When "ShowingAgentLastName" exists in the "OpenHouse" metadata
    Then "ShowingAgentLastName" MUST be "String" data type
    And "ShowingAgentLastName" length SHOULD be equal to the RESO Suggested Max Length of 50

  @OpenHouse
  Scenario: ShowingAgentMlsID
    Given that the following synonyms for "ShowingAgentMlsID" DO NOT exist in the "OpenHouse" metadata
      | ShowingMemberMlslD |
    When "ShowingAgentMlsID" exists in the "OpenHouse" metadata
    Then "ShowingAgentMlsID" MUST be "String" data type
    And "ShowingAgentMlsID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @OpenHouse @IDX
  Scenario: SourceSystemID
    Given that the following synonyms for "SourceSystemID" DO NOT exist in the "OpenHouse" metadata
      | MLSID |
    When "SourceSystemID" exists in the "OpenHouse" metadata
    Then "SourceSystemID" MUST be "String" data type
    And "SourceSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @OpenHouse @IDX
  Scenario: SourceSystemKey
    Given that the following synonyms for "SourceSystemKey" DO NOT exist in the "OpenHouse" metadata
      | ProviderKey |
    When "SourceSystemKey" exists in the "OpenHouse" metadata
    Then "SourceSystemKey" MUST be "String" data type
    And "SourceSystemKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @OpenHouse
  Scenario: SourceSystemName
    Given that the following synonyms for "SourceSystemName" DO NOT exist in the "OpenHouse" metadata
      | ProviderName |
      | MLSID |
    When "SourceSystemName" exists in the "OpenHouse" metadata
    Then "SourceSystemName" MUST be "String" data type
    And "SourceSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255
