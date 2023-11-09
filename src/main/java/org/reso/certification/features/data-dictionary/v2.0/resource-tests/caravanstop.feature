# This file was autogenerated on: 20231109002504917
Feature: CaravanStop

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

  @CaravanStop @dd-2.0
  Scenario: CaravanKey
    When "CaravanKey" exists in the "CaravanStop" metadata
    Then "CaravanKey" MUST be "String" data type
    And "CaravanKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @CaravanStop @dd-2.0
  Scenario: CaravanStopKey
    When "CaravanStopKey" exists in the "CaravanStop" metadata
    Then "CaravanStopKey" MUST be "String" data type
    And "CaravanStopKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @CaravanStop @dd-2.0
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "CaravanStop" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @CaravanStop @dd-2.0
  Scenario: OriginalEntryTimestamp
    When "OriginalEntryTimestamp" exists in the "CaravanStop" metadata
    Then "OriginalEntryTimestamp" MUST be "Timestamp" data type

  @CaravanStop @dd-2.0
  Scenario: OriginatingSystemId
    When "OriginatingSystemId" exists in the "CaravanStop" metadata
    Then "OriginatingSystemId" MUST be "String" data type
    And "OriginatingSystemId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @CaravanStop @dd-2.0
  Scenario: OriginatingSystemKey
    When "OriginatingSystemKey" exists in the "CaravanStop" metadata
    Then "OriginatingSystemKey" MUST be "String" data type
    And "OriginatingSystemKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @CaravanStop @dd-2.0
  Scenario: OriginatingSystemName
    When "OriginatingSystemName" exists in the "CaravanStop" metadata
    Then "OriginatingSystemName" MUST be "String" data type
    And "OriginatingSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @CaravanStop @dd-2.0
  Scenario: SourceSystemId
    When "SourceSystemId" exists in the "CaravanStop" metadata
    Then "SourceSystemId" MUST be "String" data type
    And "SourceSystemId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @CaravanStop @dd-2.0
  Scenario: SourceSystemKey
    When "SourceSystemKey" exists in the "CaravanStop" metadata
    Then "SourceSystemKey" MUST be "String" data type
    And "SourceSystemKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @CaravanStop @dd-2.0
  Scenario: SourceSystemName
    When "SourceSystemName" exists in the "CaravanStop" metadata
    Then "SourceSystemName" MUST be "String" data type
    And "SourceSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @CaravanStop @dd-2.0
  Scenario: StopAttendedBy
    When "StopAttendedBy" exists in the "CaravanStop" metadata
    Then "StopAttendedBy" MUST be "Single Enumeration" data type

  @CaravanStop @dd-2.0
  Scenario: StopClassName
    When "StopClassName" exists in the "CaravanStop" metadata
    Then "StopClassName" MUST be "Single Enumeration" data type

  @CaravanStop @dd-2.0
  Scenario: StopDate
    When "StopDate" exists in the "CaravanStop" metadata
    Then "StopDate" MUST be "Date" data type

  @CaravanStop @dd-2.0
  Scenario: StopEndTime
    When "StopEndTime" exists in the "CaravanStop" metadata
    Then "StopEndTime" MUST be "Timestamp" data type

  @CaravanStop @dd-2.0
  Scenario: StopId
    When "StopId" exists in the "CaravanStop" metadata
    Then "StopId" MUST be "String" data type
    And "StopId" length SHOULD be equal to the RESO Suggested Max Length of 255

  @CaravanStop @dd-2.0
  Scenario: StopKey
    When "StopKey" exists in the "CaravanStop" metadata
    Then "StopKey" MUST be "String" data type
    And "StopKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @CaravanStop @dd-2.0
  Scenario: StopOrder
    When "StopOrder" exists in the "CaravanStop" metadata
    Then "StopOrder" MUST be "Integer" data type

  @CaravanStop @dd-2.0
  Scenario: StopRefreshments
    When "StopRefreshments" exists in the "CaravanStop" metadata
    Then "StopRefreshments" MUST be "String" data type
    And "StopRefreshments" length SHOULD be equal to the RESO Suggested Max Length of 255

  @CaravanStop @dd-2.0
  Scenario: StopRemarks
    When "StopRemarks" exists in the "CaravanStop" metadata
    Then "StopRemarks" MUST be "String" data type
    And "StopRemarks" length SHOULD be equal to the RESO Suggested Max Length of 500

  @CaravanStop @dd-2.0
  Scenario: StopResourceName
    When "StopResourceName" exists in the "CaravanStop" metadata
    Then "StopResourceName" MUST be "Single Enumeration" data type

  @CaravanStop @dd-2.0
  Scenario: StopShowingAgentFirstName
    When "StopShowingAgentFirstName" exists in the "CaravanStop" metadata
    Then "StopShowingAgentFirstName" MUST be "String" data type
    And "StopShowingAgentFirstName" length SHOULD be equal to the RESO Suggested Max Length of 50

  @CaravanStop @dd-2.0
  Scenario: StopShowingAgentKey
    When "StopShowingAgentKey" exists in the "CaravanStop" metadata
    Then "StopShowingAgentKey" MUST be "String" data type
    And "StopShowingAgentKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @CaravanStop @dd-2.0
  Scenario: StopShowingAgentLastName
    When "StopShowingAgentLastName" exists in the "CaravanStop" metadata
    Then "StopShowingAgentLastName" MUST be "String" data type
    And "StopShowingAgentLastName" length SHOULD be equal to the RESO Suggested Max Length of 50

  @CaravanStop @dd-2.0
  Scenario: StopShowingAgentMlsId
    When "StopShowingAgentMlsId" exists in the "CaravanStop" metadata
    Then "StopShowingAgentMlsId" MUST be "String" data type
    And "StopShowingAgentMlsId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @CaravanStop @dd-2.0
  Scenario: StopStartTime
    When "StopStartTime" exists in the "CaravanStop" metadata
    Then "StopStartTime" MUST be "Timestamp" data type
