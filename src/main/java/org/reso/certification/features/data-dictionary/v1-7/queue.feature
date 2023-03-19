# This file was autogenerated on: 20230319104610229
Feature: Queue

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

  @Queue
  Scenario: ClassName
    When "ClassName" exists in the "Queue" metadata
    Then "ClassName" MUST be "Single Enumeration" data type

  @Queue
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "Queue" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @Queue
  Scenario: OriginatingSystemID
    When "OriginatingSystemID" exists in the "Queue" metadata
    Then "OriginatingSystemID" MUST be "String" data type
    And "OriginatingSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Queue
  Scenario: OriginatingSystemName
    Given that the following synonyms for "OriginatingSystemName" DO NOT exist in the "Queue" metadata
      | ProviderName |
      | MLSID |
    When "OriginatingSystemName" exists in the "Queue" metadata
    Then "OriginatingSystemName" MUST be "String" data type
    And "OriginatingSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Queue
  Scenario: OriginatingSystemQueueKey
    Given that the following synonyms for "OriginatingSystemQueueKey" DO NOT exist in the "Queue" metadata
      | ProviderKey |
    When "OriginatingSystemQueueKey" exists in the "Queue" metadata
    Then "OriginatingSystemQueueKey" MUST be "String" data type
    And "OriginatingSystemQueueKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Queue
  Scenario: QueueTransactionKey
    When "QueueTransactionKey" exists in the "Queue" metadata
    Then "QueueTransactionKey" MUST be "String" data type
    And "QueueTransactionKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Queue
  Scenario: QueueTransactionType
    When "QueueTransactionType" exists in the "Queue" metadata
    Then "QueueTransactionType" MUST be "Single Enumeration" data type

  @Queue
  Scenario: ResourceName
    When "ResourceName" exists in the "Queue" metadata
    Then "ResourceName" MUST be "Single Enumeration" data type

  @Queue
  Scenario: ResourceRecordID
    Given that the following synonyms for "ResourceRecordID" DO NOT exist in the "Queue" metadata
      | MLNumber |
      | MLSNumber |
      | ListingNumber |
      | AgentID |
      | OfficeID |
      | ContactID |
    When "ResourceRecordID" exists in the "Queue" metadata
    Then "ResourceRecordID" MUST be "String" data type
    And "ResourceRecordID" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Queue
  Scenario: ResourceRecordKey
    Given that the following synonyms for "ResourceRecordKey" DO NOT exist in the "Queue" metadata
      | SystemUniqueID |
      | ImmediateSourceID |
    When "ResourceRecordKey" exists in the "Queue" metadata
    Then "ResourceRecordKey" MUST be "String" data type
    And "ResourceRecordKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Queue
  Scenario: SourceSystemID
    Given that the following synonyms for "SourceSystemID" DO NOT exist in the "Queue" metadata
      | MLSID |
    When "SourceSystemID" exists in the "Queue" metadata
    Then "SourceSystemID" MUST be "String" data type
    And "SourceSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Queue
  Scenario: SourceSystemName
    Given that the following synonyms for "SourceSystemName" DO NOT exist in the "Queue" metadata
      | ProviderName |
      | MLSID |
    When "SourceSystemName" exists in the "Queue" metadata
    Then "SourceSystemName" MUST be "String" data type
    And "SourceSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Queue
  Scenario: SourceSystemQueueKey
    Given that the following synonyms for "SourceSystemQueueKey" DO NOT exist in the "Queue" metadata
      | ProviderKey |
    When "SourceSystemQueueKey" exists in the "Queue" metadata
    Then "SourceSystemQueueKey" MUST be "String" data type
    And "SourceSystemQueueKey" length SHOULD be equal to the RESO Suggested Max Length of 255
