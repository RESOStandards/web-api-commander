Feature: OpenHouse

  Scenario: AppointmentRequiredYN
    Given "AppointmentRequiredYN" exists in the metadata
    Then "AppointmentRequiredYN" should be "Boolean" data type

  Scenario: ListingId
    Given "ListingId" exists in the metadata
    Then "ListingId" should be "String" data type
    And "ListingId" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ListingKey
    Given "ListingKey" exists in the metadata
    Then "ListingKey" should be "String" data type
    And "ListingKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ListingKeyNumeric
    Given "ListingKeyNumeric" exists in the metadata
    Then "ListingKeyNumeric" should be "Integer" data type

  Scenario: ModificationTimestamp
    Given "ModificationTimestamp" exists in the metadata
    Then "ModificationTimestamp" should be "Timestamp" data type
    And "ModificationTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OpenHouseAttendedBy
    Given "OpenHouseAttendedBy" exists in the metadata
    And "OpenHouseAttendedBy" enum values can be compiled
    Then "OpenHouseAttendedBy" should be "String" data type
    And "OpenHouseAttendedBy" should only contain enum values found in the metadata
    And "OpenHouseAttendedBy" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OpenHouseDate
    Given "OpenHouseDate" exists in the metadata
    Then "OpenHouseDate" should be "Date" data type
    And "OpenHouseDate" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: OpenHouseEndTime
    Given "OpenHouseEndTime" exists in the metadata
    Then "OpenHouseEndTime" should be "Timestamp" data type
    And "OpenHouseEndTime" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OpenHouseId
    Given "OpenHouseId" exists in the metadata
    Then "OpenHouseId" should be "String" data type
    And "OpenHouseId" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OpenHouseKey
    Given "OpenHouseKey" exists in the metadata
    Then "OpenHouseKey" should be "String" data type
    And "OpenHouseKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OpenHouseKeyNumeric
    Given "OpenHouseKeyNumeric" exists in the metadata
    Then "OpenHouseKeyNumeric" should be "Integer" data type

  Scenario: OpenHouseRemarks
    Given "OpenHouseRemarks" exists in the metadata
    Then "OpenHouseRemarks" should be "String" data type
    And "OpenHouseRemarks" length should be less than or equal to the RESO Suggested Max Length of 500

  Scenario: OpenHouseStartTime
    Given "OpenHouseStartTime" exists in the metadata
    Then "OpenHouseStartTime" should be "Timestamp" data type
    And "OpenHouseStartTime" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OpenHouseStatus
    Given "OpenHouseStatus" exists in the metadata
    And "OpenHouseStatus" enum values can be compiled
    Then "OpenHouseStatus" should be "String" data type
    And "OpenHouseStatus" should only contain enum values found in the metadata
    And "OpenHouseStatus" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OpenHouseType
    Given "OpenHouseType" exists in the metadata
    And "OpenHouseType" enum values can be compiled
    Then "OpenHouseType" should be "String" data type
    And "OpenHouseType" should only contain enum values found in the metadata
    And "OpenHouseType" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OriginalEntryTimestamp
    Given "OriginalEntryTimestamp" exists in the metadata
    Then "OriginalEntryTimestamp" should be "Timestamp" data type
    And "OriginalEntryTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OriginatingSystemID
    Given "OriginatingSystemID" exists in the metadata
    Then "OriginatingSystemID" should be "String" data type
    And "OriginatingSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OriginatingSystemKey
    Given "OriginatingSystemKey" exists in the metadata
    Then "OriginatingSystemKey" should be "String" data type
    And "OriginatingSystemKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OriginatingSystemName
    Given "OriginatingSystemName" exists in the metadata
    Then "OriginatingSystemName" should be "String" data type
    And "OriginatingSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: Refreshments
    Given "Refreshments" exists in the metadata
    Then "Refreshments" should be "String" data type
    And "Refreshments" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ShowingAgentFirstName
    Given "ShowingAgentFirstName" exists in the metadata
    Then "ShowingAgentFirstName" should be "String" data type
    And "ShowingAgentFirstName" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: ShowingAgentKey
    Given "ShowingAgentKey" exists in the metadata
    Then "ShowingAgentKey" should be "String" data type
    And "ShowingAgentKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ShowingAgentKeyNumeric
    Given "ShowingAgentKeyNumeric" exists in the metadata
    Then "ShowingAgentKeyNumeric" should be "Integer" data type

  Scenario: ShowingAgentLastName
    Given "ShowingAgentLastName" exists in the metadata
    Then "ShowingAgentLastName" should be "String" data type
    And "ShowingAgentLastName" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: ShowingAgentMlsID
    Given "ShowingAgentMlsID" exists in the metadata
    Then "ShowingAgentMlsID" should be "String" data type
    And "ShowingAgentMlsID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: SourceSystemID
    Given "SourceSystemID" exists in the metadata
    Then "SourceSystemID" should be "String" data type
    And "SourceSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: SourceSystemKey
    Given "SourceSystemKey" exists in the metadata
    Then "SourceSystemKey" should be "String" data type
    And "SourceSystemKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SourceSystemName
    Given "SourceSystemName" exists in the metadata
    Then "SourceSystemName" should be "String" data type
    And "SourceSystemName" length should be less than or equal to the RESO Suggested Max Length of 255
