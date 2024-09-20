# This file was autogenerated on: 20240919210554468
Feature: Rules

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

  @Rules @dd-2.0
  Scenario: ClassName
    When "ClassName" exists in the "Rules" metadata
    Then "ClassName" MUST be "Single Enumeration" data type

  @Rules @dd-2.0
  Scenario: FieldKey
    When "FieldKey" exists in the "Rules" metadata
    Then "FieldKey" MUST be "String" data type
    And "FieldKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Rules @dd-2.0
  Scenario: FieldName
    When "FieldName" exists in the "Rules" metadata
    Then "FieldName" MUST be "String" data type
    And "FieldName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Rules @dd-2.0
  Scenario: ModificationTimestamp
    Given that the following synonyms for "ModificationTimestamp" DO NOT exist in the "Rules" metadata
      | ModificationDateTime |
      | DateTimeModified |
      | ModDate |
      | DateMod |
      | UpdateDate |
      | UpdateTimestamp |
    When "ModificationTimestamp" exists in the "Rules" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @Rules @dd-2.0
  Scenario: OriginalEntryTimestamp
    Given that the following synonyms for "OriginalEntryTimestamp" DO NOT exist in the "Rules" metadata
      | EntryDate |
      | InputDate |
      | DateTimeCreated |
      | CreatedDate. |
    When "OriginalEntryTimestamp" exists in the "Rules" metadata
    Then "OriginalEntryTimestamp" MUST be "Timestamp" data type

  @Rules @dd-2.0
  Scenario: OriginatingSystemID
    When "OriginatingSystemID" exists in the "Rules" metadata
    Then "OriginatingSystemID" MUST be "String" data type
    And "OriginatingSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Rules @dd-2.0
  Scenario: OriginatingSystemName
    Given that the following synonyms for "OriginatingSystemName" DO NOT exist in the "Rules" metadata
      | ProviderName |
      | MLSID |
    When "OriginatingSystemName" exists in the "Rules" metadata
    Then "OriginatingSystemName" MUST be "String" data type
    And "OriginatingSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Rules @dd-2.0
  Scenario: OriginatingSystemRuleKey
    When "OriginatingSystemRuleKey" exists in the "Rules" metadata
    Then "OriginatingSystemRuleKey" MUST be "String" data type
    And "OriginatingSystemRuleKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Rules @dd-2.0
  Scenario: ResourceName
    When "ResourceName" exists in the "Rules" metadata
    Then "ResourceName" MUST be "Single Enumeration" data type

  @Rules @dd-2.0
  Scenario: RuleAction
    When "RuleAction" exists in the "Rules" metadata
    Then "RuleAction" MUST be "String" data type
    And "RuleAction" length SHOULD be equal to the RESO Suggested Max Length of 8000

  @Rules @dd-2.0
  Scenario: RuleDescription
    When "RuleDescription" exists in the "Rules" metadata
    Then "RuleDescription" MUST be "String" data type
    And "RuleDescription" length SHOULD be equal to the RESO Suggested Max Length of 8000

  @Rules @dd-2.0
  Scenario: RuleEnabledYN
    When "RuleEnabledYN" exists in the "Rules" metadata
    Then "RuleEnabledYN" MUST be "Boolean" data type

  @Rules @dd-2.0
  Scenario: RuleErrorText
    When "RuleErrorText" exists in the "Rules" metadata
    Then "RuleErrorText" MUST be "String" data type
    And "RuleErrorText" length SHOULD be equal to the RESO Suggested Max Length of 8000

  @Rules @dd-2.0
  Scenario: RuleExpression
    When "RuleExpression" exists in the "Rules" metadata
    Then "RuleExpression" MUST be "String" data type
    And "RuleExpression" length SHOULD be equal to the RESO Suggested Max Length of 8000

  @Rules @dd-2.0
  Scenario: RuleFormat
    When "RuleFormat" exists in the "Rules" metadata
    Then "RuleFormat" MUST be "Single Enumeration" data type

  @Rules @dd-2.0
  Scenario: RuleHelpText
    When "RuleHelpText" exists in the "Rules" metadata
    Then "RuleHelpText" MUST be "String" data type
    And "RuleHelpText" length SHOULD be equal to the RESO Suggested Max Length of 8000

  @Rules @dd-2.0
  Scenario: RuleKey
    When "RuleKey" exists in the "Rules" metadata
    Then "RuleKey" MUST be "String" data type
    And "RuleKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Rules @dd-2.0
  Scenario: RuleName
    When "RuleName" exists in the "Rules" metadata
    Then "RuleName" MUST be "String" data type
    And "RuleName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Rules @dd-2.0
  Scenario: RuleOrder
    When "RuleOrder" exists in the "Rules" metadata
    Then "RuleOrder" MUST be "Integer" data type

  @Rules @dd-2.0
  Scenario: RuleType
    When "RuleType" exists in the "Rules" metadata
    Then "RuleType" MUST be "Single Enumeration" data type

  @Rules @dd-2.0
  Scenario: RuleVersion
    When "RuleVersion" exists in the "Rules" metadata
    Then "RuleVersion" MUST be "String" data type
    And "RuleVersion" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Rules @dd-2.0
  Scenario: RuleWarningText
    When "RuleWarningText" exists in the "Rules" metadata
    Then "RuleWarningText" MUST be "String" data type
    And "RuleWarningText" length SHOULD be equal to the RESO Suggested Max Length of 8000

  @Rules @dd-2.0
  Scenario: SourceSystemHistoryKey
    When "SourceSystemHistoryKey" exists in the "Rules" metadata
    Then "SourceSystemHistoryKey" MUST be "String" data type
    And "SourceSystemHistoryKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Rules @dd-2.0
  Scenario: SourceSystemID
    When "SourceSystemID" exists in the "Rules" metadata
    Then "SourceSystemID" MUST be "String" data type
    And "SourceSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Rules @dd-2.0
  Scenario: SourceSystemName
    When "SourceSystemName" exists in the "Rules" metadata
    Then "SourceSystemName" MUST be "String" data type
    And "SourceSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255
