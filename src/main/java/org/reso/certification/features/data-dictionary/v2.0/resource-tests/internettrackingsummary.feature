# This file was autogenerated on: 20241014212338838
Feature: InternetTrackingSummary

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

  @InternetTrackingSummary @dd-2.0
  Scenario: EndTimestamp
    When "EndTimestamp" exists in the "InternetTrackingSummary" metadata
    Then "EndTimestamp" MUST be "Timestamp" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: InternetTrackingSummaryKey
    When "InternetTrackingSummaryKey" exists in the "InternetTrackingSummary" metadata
    Then "InternetTrackingSummaryKey" MUST be "String" data type
    And "InternetTrackingSummaryKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTrackingSummary @dd-2.0
  Scenario: ListingId
    When "ListingId" exists in the "InternetTrackingSummary" metadata
    Then "ListingId" MUST be "String" data type
    And "ListingId" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTrackingSummary @dd-2.0
  Scenario: MobileLogins
    When "MobileLogins" exists in the "InternetTrackingSummary" metadata
    Then "MobileLogins" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "InternetTrackingSummary" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: OriginatingSystemName
    When "OriginatingSystemName" exists in the "InternetTrackingSummary" metadata
    Then "OriginatingSystemName" MUST be "String" data type
    And "OriginatingSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTrackingSummary @dd-2.0
  Scenario: ResponseType
    When "ResponseType" exists in the "InternetTrackingSummary" metadata
    Then "ResponseType" MUST be "Single Enumeration" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: StartTimestamp
    When "StartTimestamp" exists in the "InternetTrackingSummary" metadata
    Then "StartTimestamp" MUST be "Timestamp" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: CmaCreatedCount
    When "CmaCreatedCount" exists in the "InternetTrackingSummary" metadata
    Then "CmaCreatedCount" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: CmaEmailedCount
    When "CmaEmailedCount" exists in the "InternetTrackingSummary" metadata
    Then "CmaEmailedCount" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: CmaRanCount
    When "CmaRanCount" exists in the "InternetTrackingSummary" metadata
    Then "CmaRanCount" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: CmaSharedCount
    When "CmaSharedCount" exists in the "InternetTrackingSummary" metadata
    Then "CmaSharedCount" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: InquiryCount
    When "InquiryCount" exists in the "InternetTrackingSummary" metadata
    Then "InquiryCount" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: ImpressionCount
    When "ImpressionCount" exists in the "InternetTrackingSummary" metadata
    Then "ImpressionCount" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: MobileAppImpressionCount
    When "MobileAppImpressionCount" exists in the "InternetTrackingSummary" metadata
    Then "MobileAppImpressionCount" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: ListingsEmailedCount
    When "ListingsEmailedCount" exists in the "InternetTrackingSummary" metadata
    Then "ListingsEmailedCount" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: FavoritedCount
    When "FavoritedCount" exists in the "InternetTrackingSummary" metadata
    Then "FavoritedCount" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: SharedCount
    When "SharedCount" exists in the "InternetTrackingSummary" metadata
    Then "SharedCount" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: ViewCount
    When "ViewCount" exists in the "InternetTrackingSummary" metadata
    Then "ViewCount" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: MobileAppViewCount
    When "MobileAppViewCount" exists in the "InternetTrackingSummary" metadata
    Then "MobileAppViewCount" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TotalLogins
    When "TotalLogins" exists in the "InternetTrackingSummary" metadata
    Then "TotalLogins" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: ShowingCompletedCount
    When "ShowingCompletedCount" exists in the "InternetTrackingSummary" metadata
    Then "ShowingCompletedCount" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: ShowingRequestedCount
    When "ShowingRequestedCount" exists in the "InternetTrackingSummary" metadata
    Then "ShowingRequestedCount" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TrackingDate
    When "TrackingDate" exists in the "InternetTrackingSummary" metadata
    Then "TrackingDate" MUST be "Date" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TrackingType
    When "TrackingType" exists in the "InternetTrackingSummary" metadata
    Then "TrackingType" MUST be "Single Enumeration" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TrackingValues
    When "TrackingValues" exists in the "InternetTrackingSummary" metadata
    Then "TrackingValues" MUST be "String" data type
    And "TrackingValues" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTrackingSummary @dd-2.0
  Scenario: UniqueLogins
    When "UniqueLogins" exists in the "InternetTrackingSummary" metadata
    Then "UniqueLogins" MUST be "Integer" data type
