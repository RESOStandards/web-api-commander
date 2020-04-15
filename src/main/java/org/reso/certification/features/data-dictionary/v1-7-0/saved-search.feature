Feature: SavedSearch

  Scenario: ClassName
    Given "ClassName" exists in the metadata
    And "ClassName" enum values can be compiled
    Then "ClassName" should be "String" data type
    And "ClassName" should only contain enum values found in the metadata
    And "ClassName" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: MemberKey
    Given "MemberKey" exists in the metadata
    Then "MemberKey" should be "String" data type
    And "MemberKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: MemberKeyNumeric
    Given "MemberKeyNumeric" exists in the metadata
    Then "MemberKeyNumeric" should be "Integer" data type

  Scenario: MemberMlsId
    Given "MemberMlsId" exists in the metadata
    Then "MemberMlsId" should be "String" data type
    And "MemberMlsId" length should be less than or equal to the RESO Suggested Max Length of 25

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

  Scenario: OriginatingSystemKey
    Given "OriginatingSystemKey" exists in the metadata
    Then "OriginatingSystemKey" should be "String" data type
    And "OriginatingSystemKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OriginatingSystemMemberKey
    Given "OriginatingSystemMemberKey" exists in the metadata
    Then "OriginatingSystemMemberKey" should be "String" data type
    And "OriginatingSystemMemberKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OriginatingSystemMemberName
    Given "OriginatingSystemMemberName" exists in the metadata
    Then "OriginatingSystemMemberName" should be "String" data type
    And "OriginatingSystemMemberName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OriginatingSystemName
    Given "OriginatingSystemName" exists in the metadata
    Then "OriginatingSystemName" should be "String" data type
    And "OriginatingSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ResourceName
    Given "ResourceName" exists in the metadata
    And "ResourceName" enum values can be compiled
    Then "ResourceName" should be "String" data type
    And "ResourceName" should only contain enum values found in the metadata
    And "ResourceName" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: SavedSearchDescription
    Given "SavedSearchDescription" exists in the metadata
    Then "SavedSearchDescription" should be "String" data type
    And "SavedSearchDescription" length should be less than or equal to the RESO Suggested Max Length of 4000

  Scenario: SavedSearchKey
    Given "SavedSearchKey" exists in the metadata
    Then "SavedSearchKey" should be "String" data type
    And "SavedSearchKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SavedSearchKeyNumeric
    Given "SavedSearchKeyNumeric" exists in the metadata
    Then "SavedSearchKeyNumeric" should be "Integer" data type

  Scenario: SavedSearchName
    Given "SavedSearchName" exists in the metadata
    Then "SavedSearchName" should be "String" data type
    And "SavedSearchName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SavedSearchType
    Given "SavedSearchType" exists in the metadata
    And "SavedSearchType" enum values can be compiled
    Then "SavedSearchType" should be "String" data type
    And "SavedSearchType" should only contain enum values found in the metadata
    And "SavedSearchType" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: SearchQuery
    Given "SearchQuery" exists in the metadata
    Then "SearchQuery" should be "String" data type
    And "SearchQuery" length should be less than or equal to the RESO Suggested Max Length of 8000

  Scenario: SearchQueryExceptionDetails
    Given "SearchQueryExceptionDetails" exists in the metadata
    Then "SearchQueryExceptionDetails" should be "String" data type
    And "SearchQueryExceptionDetails" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SearchQueryExceptions
    Given "SearchQueryExceptions" exists in the metadata
    And "SearchQueryExceptions" enum values can be compiled
    Then "SearchQueryExceptions" should be "String" data type
    And "SearchQueryExceptions" should only contain enum values found in the metadata
    And "SearchQueryExceptions" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: SearchQueryHumanReadable
    Given "SearchQueryHumanReadable" exists in the metadata
    Then "SearchQueryHumanReadable" should be "String" data type
    And "SearchQueryHumanReadable" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SearchQueryType
    Given "SearchQueryType" exists in the metadata
    And "SearchQueryType" enum values can be compiled
    Then "SearchQueryType" should be "String" data type
    And "SearchQueryType" should only contain enum values found in the metadata
    And "SearchQueryType" length should be less than or equal to the RESO Suggested Max Length of 50

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
