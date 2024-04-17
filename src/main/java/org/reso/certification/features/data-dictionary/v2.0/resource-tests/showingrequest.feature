# This file was autogenerated on: 20240416183305400
Feature: ShowingRequest

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

  @ShowingRequest @dd-2.0
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "ShowingRequest" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @ShowingRequest @dd-2.0
  Scenario: ShowingAgentKey
    When "ShowingAgentKey" exists in the "ShowingRequest" metadata
    Then "ShowingAgentKey" MUST be "String" data type
    And "ShowingAgentKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @ShowingRequest @dd-2.0
  Scenario: ShowingAgentMlsId
    When "ShowingAgentMlsId" exists in the "ShowingRequest" metadata
    Then "ShowingAgentMlsId" MUST be "String" data type
    And "ShowingAgentMlsId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @ShowingRequest @dd-2.0
  Scenario: ShowingId
    When "ShowingId" exists in the "ShowingRequest" metadata
    Then "ShowingId" MUST be "String" data type
    And "ShowingId" length SHOULD be equal to the RESO Suggested Max Length of 255

  @ShowingRequest @dd-2.0
  Scenario: ShowingKey
    When "ShowingKey" exists in the "ShowingRequest" metadata
    Then "ShowingKey" MUST be "String" data type
    And "ShowingKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @ShowingRequest @dd-2.0
  Scenario: ShowingMethodRequest
    When "ShowingMethodRequest" exists in the "ShowingRequest" metadata
    Then "ShowingMethodRequest" MUST be "Multiple Enumeration" data type

  @ShowingRequest @dd-2.0
  Scenario: ShowingRequestDate
    When "ShowingRequestDate" exists in the "ShowingRequest" metadata
    Then "ShowingRequestDate" MUST be "Date" data type

  @ShowingRequest @dd-2.0
  Scenario: ShowingRequestDuration
    When "ShowingRequestDuration" exists in the "ShowingRequest" metadata
    Then "ShowingRequestDuration" MUST be "String" data type
    And "ShowingRequestDuration" length SHOULD be equal to the RESO Suggested Max Length of 50

  @ShowingRequest @dd-2.0
  Scenario: ShowingRequestedDate
    When "ShowingRequestedDate" exists in the "ShowingRequest" metadata
    Then "ShowingRequestedDate" MUST be "Date" data type

  @ShowingRequest @dd-2.0
  Scenario: ShowingRequestedTimestamp
    When "ShowingRequestedTimestamp" exists in the "ShowingRequest" metadata
    Then "ShowingRequestedTimestamp" MUST be "Timestamp" data type

  @ShowingRequest @dd-2.0
  Scenario: ShowingRequestEndTime
    When "ShowingRequestEndTime" exists in the "ShowingRequest" metadata
    Then "ShowingRequestEndTime" MUST be "Timestamp" data type

  @ShowingRequest @dd-2.0
  Scenario: ShowingRequestId
    When "ShowingRequestId" exists in the "ShowingRequest" metadata
    Then "ShowingRequestId" MUST be "String" data type
    And "ShowingRequestId" length SHOULD be equal to the RESO Suggested Max Length of 255

  @ShowingRequest @dd-2.0
  Scenario: ShowingRequestKey
    When "ShowingRequestKey" exists in the "ShowingRequest" metadata
    Then "ShowingRequestKey" MUST be "String" data type
    And "ShowingRequestKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @ShowingRequest @dd-2.0
  Scenario: ShowingRequestNotes
    When "ShowingRequestNotes" exists in the "ShowingRequest" metadata
    Then "ShowingRequestNotes" MUST be "String" data type
    And "ShowingRequestNotes" length SHOULD be equal to the RESO Suggested Max Length of 255

  @ShowingRequest @dd-2.0
  Scenario: ShowingRequestor
    When "ShowingRequestor" exists in the "ShowingRequest" metadata
    Then "ShowingRequestor" MUST be "Multiple Enumeration" data type

  @ShowingRequest @dd-2.0
  Scenario: ShowingRequestStartTime
    When "ShowingRequestStartTime" exists in the "ShowingRequest" metadata
    Then "ShowingRequestStartTime" MUST be "Timestamp" data type

  @ShowingRequest @dd-2.0
  Scenario: ShowingRequestType
    When "ShowingRequestType" exists in the "ShowingRequest" metadata
    Then "ShowingRequestType" MUST be "Multiple Enumeration" data type
