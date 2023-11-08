# This file was autogenerated on: 20231107235300344
Feature: ContactListings

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

  @ContactListings @dd-2.0
  Scenario: AgentNotesUnreadYN
    When "AgentNotesUnreadYN" exists in the "ContactListings" metadata
    Then "AgentNotesUnreadYN" MUST be "Boolean" data type

  @ContactListings @dd-2.0
  Scenario: ClassName
    When "ClassName" exists in the "ContactListings" metadata
    Then "ClassName" MUST be "Single Enumeration" data type

  @ContactListings @dd-2.0
  Scenario: ContactKey
    When "ContactKey" exists in the "ContactListings" metadata
    Then "ContactKey" MUST be "String" data type
    And "ContactKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @ContactListings @dd-2.0
  Scenario: ContactListingPreference
    When "ContactListingPreference" exists in the "ContactListings" metadata
    Then "ContactListingPreference" MUST be "Single Enumeration" data type

  @ContactListings @dd-2.0
  Scenario: ContactListingsKey
    When "ContactListingsKey" exists in the "ContactListings" metadata
    Then "ContactListingsKey" MUST be "String" data type
    And "ContactListingsKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @ContactListings @dd-2.0
  Scenario: ContactLoginId
    When "ContactLoginId" exists in the "ContactListings" metadata
    Then "ContactLoginId" MUST be "String" data type
    And "ContactLoginId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @ContactListings @dd-2.0
  Scenario: ContactNotesUnreadYN
    When "ContactNotesUnreadYN" exists in the "ContactListings" metadata
    Then "ContactNotesUnreadYN" MUST be "Boolean" data type

  @ContactListings @dd-2.0
  Scenario: DirectEmailYN
    When "DirectEmailYN" exists in the "ContactListings" metadata
    Then "DirectEmailYN" MUST be "Boolean" data type

  @ContactListings @dd-2.0
  Scenario: LastAgentNoteTimestamp
    When "LastAgentNoteTimestamp" exists in the "ContactListings" metadata
    Then "LastAgentNoteTimestamp" MUST be "Timestamp" data type

  @ContactListings @dd-2.0
  Scenario: LastContactNoteTimestamp
    When "LastContactNoteTimestamp" exists in the "ContactListings" metadata
    Then "LastContactNoteTimestamp" MUST be "Timestamp" data type

  @ContactListings @dd-2.0
  Scenario: ListingId
    When "ListingId" exists in the "ContactListings" metadata
    Then "ListingId" MUST be "String" data type
    And "ListingId" length SHOULD be equal to the RESO Suggested Max Length of 255

  @ContactListings @dd-2.0
  Scenario: ListingKey
    When "ListingKey" exists in the "ContactListings" metadata
    Then "ListingKey" MUST be "String" data type
    And "ListingKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @ContactListings @dd-2.0
  Scenario: ListingModificationTimestamp
    When "ListingModificationTimestamp" exists in the "ContactListings" metadata
    Then "ListingModificationTimestamp" MUST be "Timestamp" data type

  @ContactListings @dd-2.0
  Scenario: ListingSentTimestamp
    When "ListingSentTimestamp" exists in the "ContactListings" metadata
    Then "ListingSentTimestamp" MUST be "Timestamp" data type

  @ContactListings @dd-2.0
  Scenario: ListingViewedYN
    When "ListingViewedYN" exists in the "ContactListings" metadata
    Then "ListingViewedYN" MUST be "Boolean" data type

  @ContactListings @dd-2.0
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "ContactListings" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @ContactListings @dd-2.0
  Scenario: PortalLastVisitedTimestamp
    When "PortalLastVisitedTimestamp" exists in the "ContactListings" metadata
    Then "PortalLastVisitedTimestamp" MUST be "Timestamp" data type

  @ContactListings @dd-2.0
  Scenario: ResourceName
    When "ResourceName" exists in the "ContactListings" metadata
    Then "ResourceName" MUST be "Single Enumeration" data type
