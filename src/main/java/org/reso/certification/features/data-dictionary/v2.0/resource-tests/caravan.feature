# This file was autogenerated on: 20241014212338838
Feature: Caravan

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

  @Caravan @dd-2.0
  Scenario: CancellationPolicyUrl
    When "CancellationPolicyUrl" exists in the "Caravan" metadata
    Then "CancellationPolicyUrl" MUST be "String" data type
    And "CancellationPolicyUrl" length SHOULD be equal to the RESO Suggested Max Length of 8000

  @Caravan @dd-2.0
  Scenario: CaravanAllowedClassNames
    When "CaravanAllowedClassNames" exists in the "Caravan" metadata
    Then "CaravanAllowedClassNames" MUST be "Multiple Enumeration" data type

  @Caravan @dd-2.0
  Scenario: CaravanAllowedStatuses
    When "CaravanAllowedStatuses" exists in the "Caravan" metadata
    Then "CaravanAllowedStatuses" MUST be "Multiple Enumeration" data type

  @Caravan @dd-2.0
  Scenario: CaravanAreaDescription
    When "CaravanAreaDescription" exists in the "Caravan" metadata
    Then "CaravanAreaDescription" MUST be "String" data type
    And "CaravanAreaDescription" length SHOULD be equal to the RESO Suggested Max Length of 1024

  @Caravan @dd-2.0
  Scenario: CaravanBlackoutDates
    When "CaravanBlackoutDates" exists in the "Caravan" metadata
    Then "CaravanBlackoutDates" MUST be "String" data type
    And "CaravanBlackoutDates" length SHOULD be equal to the RESO Suggested Max Length of 1024

  @Caravan @dd-2.0
  Scenario: CaravanDate
    When "CaravanDate" exists in the "Caravan" metadata
    Then "CaravanDate" MUST be "Date" data type

  @Caravan @dd-2.0
  Scenario: CaravanDaysRecurring
    When "CaravanDaysRecurring" exists in the "Caravan" metadata
    Then "CaravanDaysRecurring" MUST be "String" data type
    And "CaravanDaysRecurring" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Caravan @dd-2.0
  Scenario: CaravanEndTime
    When "CaravanEndTime" exists in the "Caravan" metadata
    Then "CaravanEndTime" MUST be "Timestamp" data type

  @Caravan @dd-2.0
  Scenario: CaravanInputDeadlineDescription
    When "CaravanInputDeadlineDescription" exists in the "Caravan" metadata
    Then "CaravanInputDeadlineDescription" MUST be "String" data type
    And "CaravanInputDeadlineDescription" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Caravan @dd-2.0
  Scenario: CaravanInputDeadlineTimestamp
    When "CaravanInputDeadlineTimestamp" exists in the "Caravan" metadata
    Then "CaravanInputDeadlineTimestamp" MUST be "Timestamp" data type

  @Caravan @dd-2.0
  Scenario: CaravanKey
    When "CaravanKey" exists in the "Caravan" metadata
    Then "CaravanKey" MUST be "String" data type
    And "CaravanKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Caravan @dd-2.0
  Scenario: CaravanName
    When "CaravanName" exists in the "Caravan" metadata
    Then "CaravanName" MUST be "String" data type
    And "CaravanName" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Caravan @dd-2.0
  Scenario: CaravanOrganizerContactInfo
    When "CaravanOrganizerContactInfo" exists in the "Caravan" metadata
    Then "CaravanOrganizerContactInfo" MUST be "String" data type
    And "CaravanOrganizerContactInfo" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Caravan @dd-2.0
  Scenario: CaravanOrganizerKey
    When "CaravanOrganizerKey" exists in the "Caravan" metadata
    Then "CaravanOrganizerKey" MUST be "String" data type
    And "CaravanOrganizerKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Caravan @dd-2.0
  Scenario: CaravanOrganizerMlsId
    When "CaravanOrganizerMlsId" exists in the "Caravan" metadata
    Then "CaravanOrganizerMlsId" MUST be "String" data type
    And "CaravanOrganizerMlsId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Caravan @dd-2.0
  Scenario: CaravanOrganizerName
    When "CaravanOrganizerName" exists in the "Caravan" metadata
    Then "CaravanOrganizerName" MUST be "String" data type
    And "CaravanOrganizerName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Caravan @dd-2.0
  Scenario: CaravanOrganizerResourceName
    When "CaravanOrganizerResourceName" exists in the "Caravan" metadata
    Then "CaravanOrganizerResourceName" MUST be "Single Enumeration" data type

  @Caravan @dd-2.0
  Scenario: CaravanPolicyUrl
    When "CaravanPolicyUrl" exists in the "Caravan" metadata
    Then "CaravanPolicyUrl" MUST be "String" data type
    And "CaravanPolicyUrl" length SHOULD be equal to the RESO Suggested Max Length of 8000

  @Caravan @dd-2.0
  Scenario: CaravanRemarks
    When "CaravanRemarks" exists in the "Caravan" metadata
    Then "CaravanRemarks" MUST be "String" data type
    And "CaravanRemarks" length SHOULD be equal to the RESO Suggested Max Length of 500

  @Caravan @dd-2.0
  Scenario: CaravanStartLocation
    When "CaravanStartLocation" exists in the "Caravan" metadata
    Then "CaravanStartLocation" MUST be "String" data type
    And "CaravanStartLocation" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Caravan @dd-2.0
  Scenario: CaravanStartLocationGiveaways
    When "CaravanStartLocationGiveaways" exists in the "Caravan" metadata
    Then "CaravanStartLocationGiveaways" MUST be "String" data type
    And "CaravanStartLocationGiveaways" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Caravan @dd-2.0
  Scenario: CaravanStartLocationRefreshments
    When "CaravanStartLocationRefreshments" exists in the "Caravan" metadata
    Then "CaravanStartLocationRefreshments" MUST be "String" data type
    And "CaravanStartLocationRefreshments" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Caravan @dd-2.0
  Scenario: CaravanStartTime
    When "CaravanStartTime" exists in the "Caravan" metadata
    Then "CaravanStartTime" MUST be "Timestamp" data type

  @Caravan @dd-2.0
  Scenario: CaravanStatus
    When "CaravanStatus" exists in the "Caravan" metadata
    Then "CaravanStatus" MUST be "Single Enumeration" data type

  @Caravan @dd-2.0
  Scenario: CaravanType
    When "CaravanType" exists in the "Caravan" metadata
    Then "CaravanType" MUST be "Single Enumeration" data type

  @Caravan @dd-2.0
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "Caravan" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @Caravan @dd-2.0
  Scenario: OriginalEntryTimestamp
    When "OriginalEntryTimestamp" exists in the "Caravan" metadata
    Then "OriginalEntryTimestamp" MUST be "Timestamp" data type

  @Caravan @dd-2.0
  Scenario: OriginatingSystemId
    When "OriginatingSystemId" exists in the "Caravan" metadata
    Then "OriginatingSystemId" MUST be "String" data type
    And "OriginatingSystemId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Caravan @dd-2.0
  Scenario: OriginatingSystemKey
    When "OriginatingSystemKey" exists in the "Caravan" metadata
    Then "OriginatingSystemKey" MUST be "String" data type
    And "OriginatingSystemKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Caravan @dd-2.0
  Scenario: OriginatingSystemName
    When "OriginatingSystemName" exists in the "Caravan" metadata
    Then "OriginatingSystemName" MUST be "String" data type
    And "OriginatingSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Caravan @dd-2.0
  Scenario: SourceSystemId
    When "SourceSystemId" exists in the "Caravan" metadata
    Then "SourceSystemId" MUST be "String" data type
    And "SourceSystemId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Caravan @dd-2.0
  Scenario: SourceSystemKey
    When "SourceSystemKey" exists in the "Caravan" metadata
    Then "SourceSystemKey" MUST be "String" data type
    And "SourceSystemKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Caravan @dd-2.0
  Scenario: SourceSystemName
    When "SourceSystemName" exists in the "Caravan" metadata
    Then "SourceSystemName" MUST be "String" data type
    And "SourceSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255
