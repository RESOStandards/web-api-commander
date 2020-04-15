Feature: Showing

  Scenario: AgentOriginatingSystemID
    Given "AgentOriginatingSystemID" exists in the metadata
    Then "AgentOriginatingSystemID" should be "String" data type
    And "AgentOriginatingSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: AgentOriginatingSystemName
    Given "AgentOriginatingSystemName" exists in the metadata
    Then "AgentOriginatingSystemName" should be "String" data type
    And "AgentOriginatingSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: AgentSourceSystemID
    Given "AgentSourceSystemID" exists in the metadata
    Then "AgentSourceSystemID" should be "String" data type
    And "AgentSourceSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: AgentSourceSystemName
    Given "AgentSourceSystemName" exists in the metadata
    Then "AgentSourceSystemName" should be "String" data type
    And "AgentSourceSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

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

  Scenario: ListingOriginatingSystemID
    Given "ListingOriginatingSystemID" exists in the metadata
    Then "ListingOriginatingSystemID" should be "String" data type
    And "ListingOriginatingSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: ListingOriginatingSystemName
    Given "ListingOriginatingSystemName" exists in the metadata
    Then "ListingOriginatingSystemName" should be "String" data type
    And "ListingOriginatingSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ListingSourceSystemID
    Given "ListingSourceSystemID" exists in the metadata
    Then "ListingSourceSystemID" should be "String" data type
    And "ListingSourceSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: ListingSourceSystemName
    Given "ListingSourceSystemName" exists in the metadata
    Then "ListingSourceSystemName" should be "String" data type
    And "ListingSourceSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ModificationTimestamp
    Given "ModificationTimestamp" exists in the metadata
    Then "ModificationTimestamp" should be "Timestamp" data type
    And "ModificationTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OriginalEntryTimestamp
    Given "OriginalEntryTimestamp" exists in the metadata
    Then "OriginalEntryTimestamp" should be "Timestamp" data type
    And "OriginalEntryTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OriginatingSystemAgentKey
    Given "OriginatingSystemAgentKey" exists in the metadata
    Then "OriginatingSystemAgentKey" should be "String" data type
    And "OriginatingSystemAgentKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OriginatingSystemListingKey
    Given "OriginatingSystemListingKey" exists in the metadata
    Then "OriginatingSystemListingKey" should be "String" data type
    And "OriginatingSystemListingKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OriginatingSystemShowingKey
    Given "OriginatingSystemShowingKey" exists in the metadata
    Then "OriginatingSystemShowingKey" should be "String" data type
    And "OriginatingSystemShowingKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ShowingAgentKey
    Given "ShowingAgentKey" exists in the metadata
    Then "ShowingAgentKey" should be "String" data type
    And "ShowingAgentKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ShowingAgentKeyNumeric
    Given "ShowingAgentKeyNumeric" exists in the metadata
    Then "ShowingAgentKeyNumeric" should be "Integer" data type

  Scenario: ShowingAgentMlsID
    Given "ShowingAgentMlsID" exists in the metadata
    Then "ShowingAgentMlsID" should be "String" data type
    And "ShowingAgentMlsID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: ShowingEndTimestamp
    Given "ShowingEndTimestamp" exists in the metadata
    Then "ShowingEndTimestamp" should be "Timestamp" data type
    And "ShowingEndTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: ShowingId
    Given "ShowingId" exists in the metadata
    Then "ShowingId" should be "String" data type
    And "ShowingId" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ShowingKey
    Given "ShowingKey" exists in the metadata
    Then "ShowingKey" should be "String" data type
    And "ShowingKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ShowingKeyNumeric
    Given "ShowingKeyNumeric" exists in the metadata
    Then "ShowingKeyNumeric" should be "Integer" data type

  Scenario: ShowingOriginatingSystemID
    Given "ShowingOriginatingSystemID" exists in the metadata
    Then "ShowingOriginatingSystemID" should be "String" data type
    And "ShowingOriginatingSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: ShowingOriginatingSystemName
    Given "ShowingOriginatingSystemName" exists in the metadata
    Then "ShowingOriginatingSystemName" should be "String" data type
    And "ShowingOriginatingSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ShowingRequestedTimestamp
    Given "ShowingRequestedTimestamp" exists in the metadata
    Then "ShowingRequestedTimestamp" should be "Timestamp" data type
    And "ShowingRequestedTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: ShowingSourceSystemID
    Given "ShowingSourceSystemID" exists in the metadata
    Then "ShowingSourceSystemID" should be "String" data type
    And "ShowingSourceSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: ShowingSourceSystemName
    Given "ShowingSourceSystemName" exists in the metadata
    Then "ShowingSourceSystemName" should be "String" data type
    And "ShowingSourceSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ShowingStartTimestamp
    Given "ShowingStartTimestamp" exists in the metadata
    Then "ShowingStartTimestamp" should be "Timestamp" data type
    And "ShowingStartTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: SourceSystemAgentKey
    Given "SourceSystemAgentKey" exists in the metadata
    Then "SourceSystemAgentKey" should be "String" data type
    And "SourceSystemAgentKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SourceSystemListingKey
    Given "SourceSystemListingKey" exists in the metadata
    Then "SourceSystemListingKey" should be "String" data type
    And "SourceSystemListingKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SourceSystemShowingKey
    Given "SourceSystemShowingKey" exists in the metadata
    Then "SourceSystemShowingKey" should be "String" data type
    And "SourceSystemShowingKey" length should be less than or equal to the RESO Suggested Max Length of 255
