Feature: Rules

  Scenario: ClassName
    Given "ClassName" exists in the metadata
    And "ClassName" enum values can be compiled
    Then "ClassName" should be "String" data type
    And "ClassName" should only contain enum values found in the metadata
    And "ClassName" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: FieldKey
    Given "FieldKey" exists in the metadata
    Then "FieldKey" should be "String" data type
    And "FieldKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: FieldKeyNumeric
    Given "FieldKeyNumeric" exists in the metadata
    Then "FieldKeyNumeric" should be "Integer" data type

  Scenario: FieldName
    Given "FieldName" exists in the metadata
    Then "FieldName" should be "String" data type
    And "FieldName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ModificationTimestamp
    Given "ModificationTimestamp" exists in the metadata
    Then "ModificationTimestamp" should be "Timestamp" data type
    And "ModificationTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OriginalEntryTimestamp
    Given "OriginalEntryTimestamp" exists in the metadata
    Then "OriginalEntryTimestamp" should be "Timestamp" data type
    And "OriginalEntryTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OriginatingSystemID
    Given "OriginatingSystemID" exists in the metadata
    Then "OriginatingSystemID" should be "String" data type
    And "OriginatingSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OriginatingSystemName
    Given "OriginatingSystemName" exists in the metadata
    Then "OriginatingSystemName" should be "String" data type
    And "OriginatingSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OriginatingSystemRuleKey
    Given "OriginatingSystemRuleKey" exists in the metadata
    Then "OriginatingSystemRuleKey" should be "String" data type
    And "OriginatingSystemRuleKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ResourceName
    Given "ResourceName" exists in the metadata
    And "ResourceName" enum values can be compiled
    Then "ResourceName" should be "String" data type
    And "ResourceName" should only contain enum values found in the metadata
    And "ResourceName" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: RuleAction
    Given "RuleAction" exists in the metadata
    Then "RuleAction" should be "String" data type
    And "RuleAction" length should be less than or equal to the RESO Suggested Max Length of 8000

  Scenario: RuleDescription
    Given "RuleDescription" exists in the metadata
    Then "RuleDescription" should be "String" data type
    And "RuleDescription" length should be less than or equal to the RESO Suggested Max Length of 8000

  Scenario: RuleEnabledYN
    Given "RuleEnabledYN" exists in the metadata
    Then "RuleEnabledYN" should be "Boolean" data type

  Scenario: RuleErrorText
    Given "RuleErrorText" exists in the metadata
    Then "RuleErrorText" should be "String" data type
    And "RuleErrorText" length should be less than or equal to the RESO Suggested Max Length of 8000

  Scenario: RuleExpression
    Given "RuleExpression" exists in the metadata
    Then "RuleExpression" should be "String" data type
    And "RuleExpression" length should be less than or equal to the RESO Suggested Max Length of 8000

  Scenario: RuleFormat
    Given "RuleFormat" exists in the metadata
    And "RuleFormat" enum values can be compiled
    Then "RuleFormat" should be "String" data type
    And "RuleFormat" should only contain enum values found in the metadata
    And "RuleFormat" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: RuleHelpText
    Given "RuleHelpText" exists in the metadata
    Then "RuleHelpText" should be "String" data type
    And "RuleHelpText" length should be less than or equal to the RESO Suggested Max Length of 8000

  Scenario: RuleKey
    Given "RuleKey" exists in the metadata
    Then "RuleKey" should be "String" data type
    And "RuleKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: RuleKeyNumeric
    Given "RuleKeyNumeric" exists in the metadata
    Then "RuleKeyNumeric" should be "Integer" data type

  Scenario: RuleName
    Given "RuleName" exists in the metadata
    Then "RuleName" should be "String" data type
    And "RuleName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: RuleOrder
    Given "RuleOrder" exists in the metadata
    Then "RuleOrder" should be "Integer" data type

  Scenario: RuleType
    Given "RuleType" exists in the metadata
    And "RuleType" enum values can be compiled
    Then "RuleType" should be "String" data type
    And "RuleType" should only contain enum values found in the metadata
    And "RuleType" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: RuleVersion
    Given "RuleVersion" exists in the metadata
    Then "RuleVersion" should be "String" data type
    And "RuleVersion" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: RuleWarningText
    Given "RuleWarningText" exists in the metadata
    Then "RuleWarningText" should be "String" data type
    And "RuleWarningText" length should be less than or equal to the RESO Suggested Max Length of 8000

  Scenario: SourceSystemHistoryKey
    Given "SourceSystemHistoryKey" exists in the metadata
    Then "SourceSystemHistoryKey" should be "String" data type
    And "SourceSystemHistoryKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SourceSystemID
    Given "SourceSystemID" exists in the metadata
    Then "SourceSystemID" should be "String" data type
    And "SourceSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: SourceSystemName
    Given "SourceSystemName" exists in the metadata
    Then "SourceSystemName" should be "String" data type
    And "SourceSystemName" length should be less than or equal to the RESO Suggested Max Length of 255
