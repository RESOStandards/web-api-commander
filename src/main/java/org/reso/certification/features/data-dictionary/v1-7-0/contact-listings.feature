Feature: ContactListings

  Scenario: AgentNotesUnreadYN
    Given "AgentNotesUnreadYN" exists in the metadata
    Then "AgentNotesUnreadYN" should be "Boolean" data type

  Scenario: ClassName
    Given "ClassName" exists in the metadata
    And "ClassName" enum values can be compiled
    Then "ClassName" should be "String" data type
    And "ClassName" should only contain enum values found in the metadata
    And "ClassName" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: ContactKey
    Given "ContactKey" exists in the metadata
    Then "ContactKey" should be "String" data type
    And "ContactKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ContactKeyNumeric
    Given "ContactKeyNumeric" exists in the metadata
    Then "ContactKeyNumeric" should be "Integer" data type

  Scenario: ContactListingPreference
    Given "ContactListingPreference" exists in the metadata
    And "ContactListingPreference" enum values can be compiled
    Then "ContactListingPreference" should be "String" data type
    And "ContactListingPreference" should only contain enum values found in the metadata
    And "ContactListingPreference" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: ContactListingsKey
    Given "ContactListingsKey" exists in the metadata
    Then "ContactListingsKey" should be "String" data type
    And "ContactListingsKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ContactListingsKeyNumeric
    Given "ContactListingsKeyNumeric" exists in the metadata
    Then "ContactListingsKeyNumeric" should be "Integer" data type

  Scenario: ContactLoginId
    Given "ContactLoginId" exists in the metadata
    Then "ContactLoginId" should be "String" data type
    And "ContactLoginId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: ContactNotesUnreadYN
    Given "ContactNotesUnreadYN" exists in the metadata
    Then "ContactNotesUnreadYN" should be "Boolean" data type

  Scenario: DirectEmailYN
    Given "DirectEmailYN" exists in the metadata
    Then "DirectEmailYN" should be "Boolean" data type

  Scenario: LastAgentNoteTimestamp
    Given "LastAgentNoteTimestamp" exists in the metadata
    Then "LastAgentNoteTimestamp" should be "Timestamp" data type
    And "LastAgentNoteTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: LastContactNoteTimestamp
    Given "LastContactNoteTimestamp" exists in the metadata
    Then "LastContactNoteTimestamp" should be "Timestamp" data type
    And "LastContactNoteTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

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

  Scenario: ListingModificationTimestamp
    Given "ListingModificationTimestamp" exists in the metadata
    Then "ListingModificationTimestamp" should be "Timestamp" data type
    And "ListingModificationTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: ListingSentTimestamp
    Given "ListingSentTimestamp" exists in the metadata
    Then "ListingSentTimestamp" should be "Timestamp" data type
    And "ListingSentTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: ListingViewedYN
    Given "ListingViewedYN" exists in the metadata
    Then "ListingViewedYN" should be "Boolean" data type

  Scenario: ModificationTimestamp
    Given "ModificationTimestamp" exists in the metadata
    Then "ModificationTimestamp" should be "Timestamp" data type
    And "ModificationTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: PortalLastVisitedTimestamp
    Given "PortalLastVisitedTimestamp" exists in the metadata
    Then "PortalLastVisitedTimestamp" should be "Timestamp" data type
    And "PortalLastVisitedTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: ResourceName
    Given "ResourceName" exists in the metadata
    And "ResourceName" enum values can be compiled
    Then "ResourceName" should be "String" data type
    And "ResourceName" should only contain enum values found in the metadata
    And "ResourceName" length should be less than or equal to the RESO Suggested Max Length of 50
