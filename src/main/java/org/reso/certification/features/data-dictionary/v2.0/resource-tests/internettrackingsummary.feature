# This file was autogenerated on: 20231108152427929
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
  Scenario: TotalCmaReportsCreated
    When "TotalCmaReportsCreated" exists in the "InternetTrackingSummary" metadata
    Then "TotalCmaReportsCreated" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TotalCmaReportsEmailed
    When "TotalCmaReportsEmailed" exists in the "InternetTrackingSummary" metadata
    Then "TotalCmaReportsEmailed" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TotalCmaReportsRan
    When "TotalCmaReportsRan" exists in the "InternetTrackingSummary" metadata
    Then "TotalCmaReportsRan" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TotalCmaReportsShared
    When "TotalCmaReportsShared" exists in the "InternetTrackingSummary" metadata
    Then "TotalCmaReportsShared" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TotalInquiries
    When "TotalInquiries" exists in the "InternetTrackingSummary" metadata
    Then "TotalInquiries" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TotalListingImpressions
    When "TotalListingImpressions" exists in the "InternetTrackingSummary" metadata
    Then "TotalListingImpressions" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TotalListingImpressionsMobileApp
    When "TotalListingImpressionsMobileApp" exists in the "InternetTrackingSummary" metadata
    Then "TotalListingImpressionsMobileApp" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TotalListingsEmailed
    When "TotalListingsEmailed" exists in the "InternetTrackingSummary" metadata
    Then "TotalListingsEmailed" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TotalListingsFavorited
    When "TotalListingsFavorited" exists in the "InternetTrackingSummary" metadata
    Then "TotalListingsFavorited" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TotalListingsShared
    When "TotalListingsShared" exists in the "InternetTrackingSummary" metadata
    Then "TotalListingsShared" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TotalListingViews
    When "TotalListingViews" exists in the "InternetTrackingSummary" metadata
    Then "TotalListingViews" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TotalListingViewsMobileApp
    When "TotalListingViewsMobileApp" exists in the "InternetTrackingSummary" metadata
    Then "TotalListingViewsMobileApp" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TotalLogins
    When "TotalLogins" exists in the "InternetTrackingSummary" metadata
    Then "TotalLogins" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TotalShowingsCompleted
    When "TotalShowingsCompleted" exists in the "InternetTrackingSummary" metadata
    Then "TotalShowingsCompleted" MUST be "Integer" data type

  @InternetTrackingSummary @dd-2.0
  Scenario: TotalShowingsRequested
    When "TotalShowingsRequested" exists in the "InternetTrackingSummary" metadata
    Then "TotalShowingsRequested" MUST be "Integer" data type

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
