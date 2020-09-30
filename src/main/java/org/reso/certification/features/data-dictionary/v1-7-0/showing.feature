# This file was autogenerated on: 20200929220925944
Feature: Showing

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

  @AgentOriginatingSystemID @Showing
  Scenario: AgentOriginatingSystemID
    When "AgentOriginatingSystemID" exists in the "Showing" metadata
    Then "AgentOriginatingSystemID" MUST be "String" data type
    And "AgentOriginatingSystemID" length SHOULD be less than or equal to the RESO Suggested Max Length of 25

  @AgentOriginatingSystemName @Showing
  Scenario: AgentOriginatingSystemName
    When "AgentOriginatingSystemName" exists in the "Showing" metadata
    Then "AgentOriginatingSystemName" MUST be "String" data type
    And "AgentOriginatingSystemName" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @AgentSourceSystemID @Showing
  Scenario: AgentSourceSystemID
    When "AgentSourceSystemID" exists in the "Showing" metadata
    Then "AgentSourceSystemID" MUST be "String" data type
    And "AgentSourceSystemID" length SHOULD be less than or equal to the RESO Suggested Max Length of 25

  @AgentSourceSystemName @Showing
  Scenario: AgentSourceSystemName
    When "AgentSourceSystemName" exists in the "Showing" metadata
    Then "AgentSourceSystemName" MUST be "String" data type
    And "AgentSourceSystemName" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @ListingId @Showing
  Scenario: ListingId
    When "ListingId" exists in the "Showing" metadata
    Then "ListingId" MUST be "String" data type
    And "ListingId" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @ListingKey @Showing
  Scenario: ListingKey
    When "ListingKey" exists in the "Showing" metadata
    Then "ListingKey" MUST be "String" data type
    And "ListingKey" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @ListingKeyNumeric @Showing
  Scenario: ListingKeyNumeric
    When "ListingKeyNumeric" exists in the "Showing" metadata
    Then "ListingKeyNumeric" MUST be "Integer" data type

  @ListingOriginatingSystemID @Showing
  Scenario: ListingOriginatingSystemID
    When "ListingOriginatingSystemID" exists in the "Showing" metadata
    Then "ListingOriginatingSystemID" MUST be "String" data type
    And "ListingOriginatingSystemID" length SHOULD be less than or equal to the RESO Suggested Max Length of 25

  @ListingOriginatingSystemName @Showing
  Scenario: ListingOriginatingSystemName
    When "ListingOriginatingSystemName" exists in the "Showing" metadata
    Then "ListingOriginatingSystemName" MUST be "String" data type
    And "ListingOriginatingSystemName" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @ListingSourceSystemID @Showing
  Scenario: ListingSourceSystemID
    When "ListingSourceSystemID" exists in the "Showing" metadata
    Then "ListingSourceSystemID" MUST be "String" data type
    And "ListingSourceSystemID" length SHOULD be less than or equal to the RESO Suggested Max Length of 25

  @ListingSourceSystemName @Showing
  Scenario: ListingSourceSystemName
    When "ListingSourceSystemName" exists in the "Showing" metadata
    Then "ListingSourceSystemName" MUST be "String" data type
    And "ListingSourceSystemName" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @ModificationTimestamp @Showing
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "Showing" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @OriginalEntryTimestamp @Showing
  Scenario: OriginalEntryTimestamp
    When "OriginalEntryTimestamp" exists in the "Showing" metadata
    Then "OriginalEntryTimestamp" MUST be "Timestamp" data type

  @OriginatingSystemAgentKey @Showing
  Scenario: OriginatingSystemAgentKey
    When "OriginatingSystemAgentKey" exists in the "Showing" metadata
    Then "OriginatingSystemAgentKey" MUST be "String" data type
    And "OriginatingSystemAgentKey" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @OriginatingSystemListingKey @Showing
  Scenario: OriginatingSystemListingKey
    When "OriginatingSystemListingKey" exists in the "Showing" metadata
    Then "OriginatingSystemListingKey" MUST be "String" data type
    And "OriginatingSystemListingKey" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @OriginatingSystemShowingKey @Showing
  Scenario: OriginatingSystemShowingKey
    When "OriginatingSystemShowingKey" exists in the "Showing" metadata
    Then "OriginatingSystemShowingKey" MUST be "String" data type
    And "OriginatingSystemShowingKey" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @ShowingAgentKey @Showing
  Scenario: ShowingAgentKey
    When "ShowingAgentKey" exists in the "Showing" metadata
    Then "ShowingAgentKey" MUST be "String" data type
    And "ShowingAgentKey" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @ShowingAgentKeyNumeric @Showing
  Scenario: ShowingAgentKeyNumeric
    When "ShowingAgentKeyNumeric" exists in the "Showing" metadata
    Then "ShowingAgentKeyNumeric" MUST be "Integer" data type

  @ShowingAgentMlsID @Showing
  Scenario: ShowingAgentMlsID
    When "ShowingAgentMlsID" exists in the "Showing" metadata
    Then "ShowingAgentMlsID" MUST be "String" data type
    And "ShowingAgentMlsID" length SHOULD be less than or equal to the RESO Suggested Max Length of 25

  @ShowingEndTimestamp @Showing
  Scenario: ShowingEndTimestamp
    When "ShowingEndTimestamp" exists in the "Showing" metadata
    Then "ShowingEndTimestamp" MUST be "Timestamp" data type

  @ShowingId @Showing
  Scenario: ShowingId
    When "ShowingId" exists in the "Showing" metadata
    Then "ShowingId" MUST be "String" data type
    And "ShowingId" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @ShowingKey @Showing
  Scenario: ShowingKey
    When "ShowingKey" exists in the "Showing" metadata
    Then "ShowingKey" MUST be "String" data type
    And "ShowingKey" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @ShowingKeyNumeric @Showing
  Scenario: ShowingKeyNumeric
    When "ShowingKeyNumeric" exists in the "Showing" metadata
    Then "ShowingKeyNumeric" MUST be "Integer" data type

  @ShowingOriginatingSystemID @Showing
  Scenario: ShowingOriginatingSystemID
    When "ShowingOriginatingSystemID" exists in the "Showing" metadata
    Then "ShowingOriginatingSystemID" MUST be "String" data type
    And "ShowingOriginatingSystemID" length SHOULD be less than or equal to the RESO Suggested Max Length of 25

  @ShowingOriginatingSystemName @Showing
  Scenario: ShowingOriginatingSystemName
    When "ShowingOriginatingSystemName" exists in the "Showing" metadata
    Then "ShowingOriginatingSystemName" MUST be "String" data type
    And "ShowingOriginatingSystemName" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @ShowingRequestedTimestamp @Showing
  Scenario: ShowingRequestedTimestamp
    When "ShowingRequestedTimestamp" exists in the "Showing" metadata
    Then "ShowingRequestedTimestamp" MUST be "Timestamp" data type

  @ShowingSourceSystemID @Showing
  Scenario: ShowingSourceSystemID
    When "ShowingSourceSystemID" exists in the "Showing" metadata
    Then "ShowingSourceSystemID" MUST be "String" data type
    And "ShowingSourceSystemID" length SHOULD be less than or equal to the RESO Suggested Max Length of 25

  @ShowingSourceSystemName @Showing
  Scenario: ShowingSourceSystemName
    When "ShowingSourceSystemName" exists in the "Showing" metadata
    Then "ShowingSourceSystemName" MUST be "String" data type
    And "ShowingSourceSystemName" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @ShowingStartTimestamp @Showing
  Scenario: ShowingStartTimestamp
    When "ShowingStartTimestamp" exists in the "Showing" metadata
    Then "ShowingStartTimestamp" MUST be "Timestamp" data type

  @SourceSystemAgentKey @Showing
  Scenario: SourceSystemAgentKey
    When "SourceSystemAgentKey" exists in the "Showing" metadata
    Then "SourceSystemAgentKey" MUST be "String" data type
    And "SourceSystemAgentKey" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @SourceSystemListingKey @Showing
  Scenario: SourceSystemListingKey
    When "SourceSystemListingKey" exists in the "Showing" metadata
    Then "SourceSystemListingKey" MUST be "String" data type
    And "SourceSystemListingKey" length SHOULD be less than or equal to the RESO Suggested Max Length of 255

  @SourceSystemShowingKey @Showing
  Scenario: SourceSystemShowingKey
    When "SourceSystemShowingKey" exists in the "Showing" metadata
    Then "SourceSystemShowingKey" MUST be "String" data type
    And "SourceSystemShowingKey" length SHOULD be less than or equal to the RESO Suggested Max Length of 255
