Feature: HistoryTransactional

  Scenario: ChangeType
    Given "ChangeType" exists in the metadata
    And "ChangeType" enum values can be compiled
    Then "ChangeType" should be "String" data type
    And "ChangeType" should only contain enum values found in the metadata
    And "ChangeType" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ChangedByMemberID
    Given "ChangedByMemberID" exists in the metadata
    Then "ChangedByMemberID" should be "String" data type
    And "ChangedByMemberID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: ChangedByMemberKey
    Given "ChangedByMemberKey" exists in the metadata
    Then "ChangedByMemberKey" should be "String" data type
    And "ChangedByMemberKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ChangedByMemberKeyNumeric
    Given "ChangedByMemberKeyNumeric" exists in the metadata
    Then "ChangedByMemberKeyNumeric" should be "Integer" data type

  Scenario: ClassName
    Given "ClassName" exists in the metadata
    Then "ClassName" should be "String" data type
    And "ClassName" length should be less than or equal to the RESO Suggested Max Length of 255

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

  Scenario: HistoryTransactionalKey
    Given "HistoryTransactionalKey" exists in the metadata
    Then "HistoryTransactionalKey" should be "String" data type
    And "HistoryTransactionalKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: HistoryTransactionalKeyNumeric
    Given "HistoryTransactionalKeyNumeric" exists in the metadata
    Then "HistoryTransactionalKeyNumeric" should be "Integer" data type

  Scenario: ModificationTimestamp
    Given "ModificationTimestamp" exists in the metadata
    Then "ModificationTimestamp" should be "Timestamp" data type
    And "ModificationTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: NewValue
    Given "NewValue" exists in the metadata
    Then "NewValue" should be "String" data type
    And "NewValue" length should be less than or equal to the RESO Suggested Max Length of 8000

  Scenario: OriginatingSystemHistoryKey
    Given "OriginatingSystemHistoryKey" exists in the metadata
    Then "OriginatingSystemHistoryKey" should be "String" data type
    And "OriginatingSystemHistoryKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OriginatingSystemID
    Given "OriginatingSystemID" exists in the metadata
    Then "OriginatingSystemID" should be "String" data type
    And "OriginatingSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OriginatingSystemName
    Given "OriginatingSystemName" exists in the metadata
    Then "OriginatingSystemName" should be "String" data type
    And "OriginatingSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: PreviousValue
    Given "PreviousValue" exists in the metadata
    Then "PreviousValue" should be "String" data type
    And "PreviousValue" length should be less than or equal to the RESO Suggested Max Length of 8000

  Scenario: ResourceName
    Given "ResourceName" exists in the metadata
    Then "ResourceName" should be "String" data type
    And "ResourceName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ResourceRecordID
    Given "ResourceRecordID" exists in the metadata
    Then "ResourceRecordID" should be "String" data type
    And "ResourceRecordID" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ResourceRecordKey
    Given "ResourceRecordKey" exists in the metadata
    Then "ResourceRecordKey" should be "String" data type
    And "ResourceRecordKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ResourceRecordKeyNumeric
    Given "ResourceRecordKeyNumeric" exists in the metadata
    Then "ResourceRecordKeyNumeric" should be "Integer" data type

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
