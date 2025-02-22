# This file was autogenerated on: 20241014212338838
Feature: ContactListingNotes

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

  @ContactListingNotes @dd-2.0
  Scenario: ContactKey
    When "ContactKey" exists in the "ContactListingNotes" metadata
    Then "ContactKey" MUST be "String" data type
    And "ContactKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @ContactListingNotes @dd-2.0
  Scenario: ContactListingNotesKey
    When "ContactListingNotesKey" exists in the "ContactListingNotes" metadata
    Then "ContactListingNotesKey" MUST be "String" data type
    And "ContactListingNotesKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @ContactListingNotes @dd-2.0
  Scenario: ListingId
    When "ListingId" exists in the "ContactListingNotes" metadata
    Then "ListingId" MUST be "String" data type
    And "ListingId" length SHOULD be equal to the RESO Suggested Max Length of 255

  @ContactListingNotes @dd-2.0
  Scenario: ListingKey
    When "ListingKey" exists in the "ContactListingNotes" metadata
    Then "ListingKey" MUST be "String" data type
    And "ListingKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @ContactListingNotes @dd-2.0
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "ContactListingNotes" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @ContactListingNotes @dd-2.0
  Scenario: NoteContents
    When "NoteContents" exists in the "ContactListingNotes" metadata
    Then "NoteContents" MUST be "String" data type
    And "NoteContents" length SHOULD be equal to the RESO Suggested Max Length of 500

  @ContactListingNotes @dd-2.0
  Scenario: NotedBy
    When "NotedBy" exists in the "ContactListingNotes" metadata
    Then "NotedBy" MUST be "Single Enumeration" data type
