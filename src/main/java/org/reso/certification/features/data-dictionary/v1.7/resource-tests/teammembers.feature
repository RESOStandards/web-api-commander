# This file was autogenerated on: 2023110900245888
Feature: TeamMembers

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

  @TeamMembers @dd-1.7
  Scenario: MemberKey
    Given that the following synonyms for "MemberKey" DO NOT exist in the "TeamMembers" metadata
      | AgentKey |
    When "MemberKey" exists in the "TeamMembers" metadata
    Then "MemberKey" MUST be "String" data type
    And "MemberKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @TeamMembers @dd-1.7
  Scenario: MemberLoginId
    Given that the following synonyms for "MemberLoginId" DO NOT exist in the "TeamMembers" metadata
      | AgentLoginId |
    When "MemberLoginId" exists in the "TeamMembers" metadata
    Then "MemberLoginId" MUST be "String" data type
    And "MemberLoginId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @TeamMembers @dd-1.7
  Scenario: MemberMlsId
    Given that the following synonyms for "MemberMlsId" DO NOT exist in the "TeamMembers" metadata
      | AgentMlsId |
    When "MemberMlsId" exists in the "TeamMembers" metadata
    Then "MemberMlsId" MUST be "String" data type
    And "MemberMlsId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @TeamMembers @dd-1.7
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "TeamMembers" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @TeamMembers @dd-1.7
  Scenario: OriginalEntryTimestamp
    When "OriginalEntryTimestamp" exists in the "TeamMembers" metadata
    Then "OriginalEntryTimestamp" MUST be "Timestamp" data type

  @TeamMembers @dd-1.7
  Scenario: OriginatingSystemID
    When "OriginatingSystemID" exists in the "TeamMembers" metadata
    Then "OriginatingSystemID" MUST be "String" data type
    And "OriginatingSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @TeamMembers @dd-1.7
  Scenario: OriginatingSystemKey
    Given that the following synonyms for "OriginatingSystemKey" DO NOT exist in the "TeamMembers" metadata
      | ProviderKey |
    When "OriginatingSystemKey" exists in the "TeamMembers" metadata
    Then "OriginatingSystemKey" MUST be "String" data type
    And "OriginatingSystemKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @TeamMembers @dd-1.7
  Scenario: OriginatingSystemName
    Given that the following synonyms for "OriginatingSystemName" DO NOT exist in the "TeamMembers" metadata
      | ProviderName |
      | MLSID |
    When "OriginatingSystemName" exists in the "TeamMembers" metadata
    Then "OriginatingSystemName" MUST be "String" data type
    And "OriginatingSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @TeamMembers @dd-1.7
  Scenario: SourceSystemID
    Given that the following synonyms for "SourceSystemID" DO NOT exist in the "TeamMembers" metadata
      | MLSID |
    When "SourceSystemID" exists in the "TeamMembers" metadata
    Then "SourceSystemID" MUST be "String" data type
    And "SourceSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @TeamMembers @dd-1.7
  Scenario: SourceSystemKey
    Given that the following synonyms for "SourceSystemKey" DO NOT exist in the "TeamMembers" metadata
      | ProviderKey |
    When "SourceSystemKey" exists in the "TeamMembers" metadata
    Then "SourceSystemKey" MUST be "String" data type
    And "SourceSystemKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @TeamMembers @dd-1.7
  Scenario: SourceSystemName
    Given that the following synonyms for "SourceSystemName" DO NOT exist in the "TeamMembers" metadata
      | ProviderName |
      | MLSID |
    When "SourceSystemName" exists in the "TeamMembers" metadata
    Then "SourceSystemName" MUST be "String" data type
    And "SourceSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @TeamMembers @dd-1.7
  Scenario: TeamImpersonationLevel
    When "TeamImpersonationLevel" exists in the "TeamMembers" metadata
    Then "TeamImpersonationLevel" MUST be "Single Enumeration" data type

  @TeamMembers @dd-1.7
  Scenario: TeamKey
    When "TeamKey" exists in the "TeamMembers" metadata
    Then "TeamKey" MUST be "String" data type
    And "TeamKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @TeamMembers @dd-1.7
  Scenario: TeamMemberKey
    Given that the following synonyms for "TeamMemberKey" DO NOT exist in the "TeamMembers" metadata
      | TeamAgentKey |
    When "TeamMemberKey" exists in the "TeamMembers" metadata
    Then "TeamMemberKey" MUST be "String" data type
    And "TeamMemberKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @TeamMembers @dd-1.7
  Scenario: TeamMemberNationalAssociationId
    Given that the following synonyms for "TeamMemberNationalAssociationId" DO NOT exist in the "TeamMembers" metadata
      | TeamAgentNationalAssociationId |
    When "TeamMemberNationalAssociationId" exists in the "TeamMembers" metadata
    Then "TeamMemberNationalAssociationId" MUST be "String" data type
    And "TeamMemberNationalAssociationId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @TeamMembers @dd-1.7
  Scenario: TeamMemberStateLicense
    Given that the following synonyms for "TeamMemberStateLicense" DO NOT exist in the "TeamMembers" metadata
      | TeamAgentStateLicense |
    When "TeamMemberStateLicense" exists in the "TeamMembers" metadata
    Then "TeamMemberStateLicense" MUST be "String" data type
    And "TeamMemberStateLicense" length SHOULD be equal to the RESO Suggested Max Length of 50

  @TeamMembers @dd-1.7
  Scenario: TeamMemberType
    Given that the following synonyms for "TeamMemberType" DO NOT exist in the "TeamMembers" metadata
      | TeamAgentType |
    When "TeamMemberType" exists in the "TeamMembers" metadata
    Then "TeamMemberType" MUST be "Single Enumeration" data type
