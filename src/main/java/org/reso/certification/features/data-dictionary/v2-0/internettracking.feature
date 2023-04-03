# This file was autogenerated on: 20230403153708907
Feature: InternetTracking

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

  @InternetTracking @dd-2.0
  Scenario: ActorCity
    When "ActorCity" exists in the "InternetTracking" metadata
    Then "ActorCity" MUST be "String" data type
    And "ActorCity" length SHOULD be equal to the RESO Suggested Max Length of 50

  @InternetTracking @dd-2.0
  Scenario: ActorEmail
    When "ActorEmail" exists in the "InternetTracking" metadata
    Then "ActorEmail" MUST be "String" data type
    And "ActorEmail" length SHOULD be equal to the RESO Suggested Max Length of 80

  @InternetTracking @dd-2.0
  Scenario: ActorID
    When "ActorID" exists in the "InternetTracking" metadata
    Then "ActorID" MUST be "String" data type
    And "ActorID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @InternetTracking @dd-2.0
  Scenario: ActorIP
    When "ActorIP" exists in the "InternetTracking" metadata
    Then "ActorIP" MUST be "String" data type
    And "ActorIP" length SHOULD be equal to the RESO Suggested Max Length of 39

  @InternetTracking @dd-2.0
  Scenario: ActorKey
    When "ActorKey" exists in the "InternetTracking" metadata
    Then "ActorKey" MUST be "String" data type
    And "ActorKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: ActorLatitude
    When "ActorLatitude" exists in the "InternetTracking" metadata
    Then "ActorLatitude" MUST be "Decimal" data type
    And "ActorLatitude" precision SHOULD be equal to the RESO Suggested Max Precision of 12
    And "ActorLatitude" scale SHOULD be equal to the RESO Suggested Max Scale of 8

  @InternetTracking @dd-2.0
  Scenario: ActorLongitude
    When "ActorLongitude" exists in the "InternetTracking" metadata
    Then "ActorLongitude" MUST be "Decimal" data type
    And "ActorLongitude" precision SHOULD be equal to the RESO Suggested Max Precision of 12
    And "ActorLongitude" scale SHOULD be equal to the RESO Suggested Max Scale of 8

  @InternetTracking @dd-2.0
  Scenario: ActorOriginatingSystemID
    When "ActorOriginatingSystemID" exists in the "InternetTracking" metadata
    Then "ActorOriginatingSystemID" MUST be "String" data type
    And "ActorOriginatingSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @InternetTracking @dd-2.0
  Scenario: ActorOriginatingSystemName
    When "ActorOriginatingSystemName" exists in the "InternetTracking" metadata
    Then "ActorOriginatingSystemName" MUST be "String" data type
    And "ActorOriginatingSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: ActorPhone
    When "ActorPhone" exists in the "InternetTracking" metadata
    Then "ActorPhone" MUST be "String" data type
    And "ActorPhone" length SHOULD be equal to the RESO Suggested Max Length of 16

  @InternetTracking @dd-2.0
  Scenario: ActorPhoneExt
    When "ActorPhoneExt" exists in the "InternetTracking" metadata
    Then "ActorPhoneExt" MUST be "String" data type
    And "ActorPhoneExt" length SHOULD be equal to the RESO Suggested Max Length of 10

  @InternetTracking @dd-2.0
  Scenario: ActorPostalCode
    When "ActorPostalCode" exists in the "InternetTracking" metadata
    Then "ActorPostalCode" MUST be "String" data type
    And "ActorPostalCode" length SHOULD be equal to the RESO Suggested Max Length of 10

  @InternetTracking @dd-2.0
  Scenario: ActorPostalCodePlus4
    When "ActorPostalCodePlus4" exists in the "InternetTracking" metadata
    Then "ActorPostalCodePlus4" MUST be "String" data type
    And "ActorPostalCodePlus4" length SHOULD be equal to the RESO Suggested Max Length of 4

  @InternetTracking @dd-2.0
  Scenario: ActorRegion
    When "ActorRegion" exists in the "InternetTracking" metadata
    Then "ActorRegion" MUST be "String" data type
    And "ActorRegion" length SHOULD be equal to the RESO Suggested Max Length of 150

  @InternetTracking @dd-2.0
  Scenario: ActorSourceSystemID
    When "ActorSourceSystemID" exists in the "InternetTracking" metadata
    Then "ActorSourceSystemID" MUST be "String" data type
    And "ActorSourceSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @InternetTracking @dd-2.0
  Scenario: ActorSourceSystemName
    When "ActorSourceSystemName" exists in the "InternetTracking" metadata
    Then "ActorSourceSystemName" MUST be "String" data type
    And "ActorSourceSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: ActorStateOrProvince
    When "ActorStateOrProvince" exists in the "InternetTracking" metadata
    Then "ActorStateOrProvince" MUST be "Single Enumeration" data type

  @InternetTracking @dd-2.0
  Scenario: ActorType
    When "ActorType" exists in the "InternetTracking" metadata
    Then "ActorType" MUST be "Single Enumeration" data type

  @InternetTracking @dd-2.0
  Scenario: ColorDepth
    When "ColorDepth" exists in the "InternetTracking" metadata
    Then "ColorDepth" MUST be "Integer" data type

  @InternetTracking @dd-2.0
  Scenario: DeviceType
    When "DeviceType" exists in the "InternetTracking" metadata
    Then "DeviceType" MUST be "Single Enumeration" data type

  @InternetTracking @dd-2.0
  Scenario: EventDescription
    When "EventDescription" exists in the "InternetTracking" metadata
    Then "EventDescription" MUST be "String" data type
    And "EventDescription" length SHOULD be equal to the RESO Suggested Max Length of 1024

  @InternetTracking @dd-2.0
  Scenario: EventKey
    When "EventKey" exists in the "InternetTracking" metadata
    Then "EventKey" MUST be "String" data type
    And "EventKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: EventLabel
    When "EventLabel" exists in the "InternetTracking" metadata
    Then "EventLabel" MUST be "String" data type
    And "EventLabel" length SHOULD be equal to the RESO Suggested Max Length of 50

  @InternetTracking @dd-2.0
  Scenario: EventOriginatingSystemID
    When "EventOriginatingSystemID" exists in the "InternetTracking" metadata
    Then "EventOriginatingSystemID" MUST be "String" data type
    And "EventOriginatingSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @InternetTracking @dd-2.0
  Scenario: EventOriginatingSystemName
    When "EventOriginatingSystemName" exists in the "InternetTracking" metadata
    Then "EventOriginatingSystemName" MUST be "String" data type
    And "EventOriginatingSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: EventSourceSystemID
    When "EventSourceSystemID" exists in the "InternetTracking" metadata
    Then "EventSourceSystemID" MUST be "String" data type
    And "EventSourceSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @InternetTracking @dd-2.0
  Scenario: EventSourceSystemName
    When "EventSourceSystemName" exists in the "InternetTracking" metadata
    Then "EventSourceSystemName" MUST be "String" data type
    And "EventSourceSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: EventTarget
    When "EventTarget" exists in the "InternetTracking" metadata
    Then "EventTarget" MUST be "Single Enumeration" data type

  @InternetTracking @dd-2.0
  Scenario: EventTimestamp
    When "EventTimestamp" exists in the "InternetTracking" metadata
    Then "EventTimestamp" MUST be "Timestamp" data type

  @InternetTracking @dd-2.0
  Scenario: EventType
    When "EventType" exists in the "InternetTracking" metadata
    Then "EventType" MUST be "Single Enumeration" data type

  @InternetTracking @dd-2.0
  Scenario: ObjectID
    When "ObjectID" exists in the "InternetTracking" metadata
    Then "ObjectID" MUST be "String" data type
    And "ObjectID" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: ObjectIdType
    When "ObjectIdType" exists in the "InternetTracking" metadata
    Then "ObjectIdType" MUST be "Single Enumeration" data type

  @InternetTracking @dd-2.0
  Scenario: ObjectKey
    When "ObjectKey" exists in the "InternetTracking" metadata
    Then "ObjectKey" MUST be "String" data type
    And "ObjectKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: ObjectOriginatingSystemID
    When "ObjectOriginatingSystemID" exists in the "InternetTracking" metadata
    Then "ObjectOriginatingSystemID" MUST be "String" data type
    And "ObjectOriginatingSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @InternetTracking @dd-2.0
  Scenario: ObjectOriginatingSystemName
    When "ObjectOriginatingSystemName" exists in the "InternetTracking" metadata
    Then "ObjectOriginatingSystemName" MUST be "String" data type
    And "ObjectOriginatingSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: ObjectSourceSystemID
    When "ObjectSourceSystemID" exists in the "InternetTracking" metadata
    Then "ObjectSourceSystemID" MUST be "String" data type
    And "ObjectSourceSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @InternetTracking @dd-2.0
  Scenario: ObjectSourceSystemName
    When "ObjectSourceSystemName" exists in the "InternetTracking" metadata
    Then "ObjectSourceSystemName" MUST be "String" data type
    And "ObjectSourceSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: ObjectType
    When "ObjectType" exists in the "InternetTracking" metadata
    Then "ObjectType" MUST be "Single Enumeration" data type

  @InternetTracking @dd-2.0
  Scenario: ObjectURL
    When "ObjectURL" exists in the "InternetTracking" metadata
    Then "ObjectURL" MUST be "String" data type
    And "ObjectURL" length SHOULD be equal to the RESO Suggested Max Length of 8000

  @InternetTracking @dd-2.0
  Scenario: OriginatingSystemActorKey
    When "OriginatingSystemActorKey" exists in the "InternetTracking" metadata
    Then "OriginatingSystemActorKey" MUST be "String" data type
    And "OriginatingSystemActorKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: OriginatingSystemEventKey
    When "OriginatingSystemEventKey" exists in the "InternetTracking" metadata
    Then "OriginatingSystemEventKey" MUST be "String" data type
    And "OriginatingSystemEventKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: OriginatingSystemObjectKey
    When "OriginatingSystemObjectKey" exists in the "InternetTracking" metadata
    Then "OriginatingSystemObjectKey" MUST be "String" data type
    And "OriginatingSystemObjectKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: ReferringURL
    When "ReferringURL" exists in the "InternetTracking" metadata
    Then "ReferringURL" MUST be "String" data type
    And "ReferringURL" length SHOULD be equal to the RESO Suggested Max Length of 8000

  @InternetTracking @dd-2.0
  Scenario: ScreenHeight
    When "ScreenHeight" exists in the "InternetTracking" metadata
    Then "ScreenHeight" MUST be "Integer" data type

  @InternetTracking @dd-2.0
  Scenario: ScreenWidth
    When "ScreenWidth" exists in the "InternetTracking" metadata
    Then "ScreenWidth" MUST be "Integer" data type

  @InternetTracking @dd-2.0
  Scenario: SessionID
    When "SessionID" exists in the "InternetTracking" metadata
    Then "SessionID" MUST be "String" data type
    And "SessionID" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: SourceSystemActorKey
    When "SourceSystemActorKey" exists in the "InternetTracking" metadata
    Then "SourceSystemActorKey" MUST be "String" data type
    And "SourceSystemActorKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: SourceSystemEventKey
    When "SourceSystemEventKey" exists in the "InternetTracking" metadata
    Then "SourceSystemEventKey" MUST be "String" data type
    And "SourceSystemEventKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: SourceSystemObjectKey
    When "SourceSystemObjectKey" exists in the "InternetTracking" metadata
    Then "SourceSystemObjectKey" MUST be "String" data type
    And "SourceSystemObjectKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: TimeZoneOffset
    When "TimeZoneOffset" exists in the "InternetTracking" metadata
    Then "TimeZoneOffset" MUST be "Integer" data type

  @InternetTracking @dd-2.0
  Scenario: UserAgent
    When "UserAgent" exists in the "InternetTracking" metadata
    Then "UserAgent" MUST be "String" data type
    And "UserAgent" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @dd-2.0
  Scenario: SummaryEndTimestamp
    When "SummaryEndTimestamp" exists in the "InternetTracking" metadata
    Then "SummaryEndTimestamp" MUST be "Timestamp" data type

  @InternetTracking @dd-2.0
  Scenario: SummaryStartTimestamp
    When "SummaryStartTimestamp" exists in the "InternetTracking" metadata
    Then "SummaryStartTimestamp" MUST be "Timestamp" data type

  @InternetTracking @dd-2.0
  Scenario: TotalListingImpressions
    When "TotalListingImpressions" exists in the "InternetTracking" metadata
    Then "TotalListingImpressions" MUST be "Integer" data type

  @InternetTracking @dd-2.0
  Scenario: TrackingDate
    When "TrackingDate" exists in the "InternetTracking" metadata
    Then "TrackingDate" MUST be "Date" data type

  @InternetTracking @dd-2.0
  Scenario: TrackingType
    When "TrackingType" exists in the "InternetTracking" metadata
    Then "TrackingType" MUST be "Single Enumeration" data type

  @InternetTracking @dd-2.0
  Scenario: TrackingValues
    When "TrackingValues" exists in the "InternetTracking" metadata
    Then "TrackingValues" MUST be "String" data type
    And "TrackingValues" length SHOULD be equal to the RESO Suggested Max Length of 255

  @InternetTracking @RESI @RLSE @RINC @LAND @MOBI @FARM @COMS @COML @dd-2.0
  Scenario: EventSource
    When "EventSource" exists in the "InternetTracking" metadata
    Then "EventSource" MUST be "Single Enumeration" data type

  @InternetTracking @RESI @RLSE @RINC @LAND @MOBI @FARM @COMS @COML @dd-2.0
  Scenario: TotalListingViews
    When "TotalListingViews" exists in the "InternetTracking" metadata
    Then "TotalListingViews" MUST be "Integer" data type

  @InternetTracking @RESI @RLSE @RINC @LAND @MOBI @FARM @COMS @COML @dd-2.0
  Scenario: TotalListingsFavorited
    When "TotalListingsFavorited" exists in the "InternetTracking" metadata
    Then "TotalListingsFavorited" MUST be "Integer" data type

  @InternetTracking @RESI @RLSE @RINC @LAND @MOBI @FARM @COMS @COML @dd-2.0
  Scenario: TotalListingsShared
    When "TotalListingsShared" exists in the "InternetTracking" metadata
    Then "TotalListingsShared" MUST be "Integer" data type

  @InternetTracking @RESI @RLSE @RINC @LAND @MOBI @FARM @COMS @COML @dd-2.0
  Scenario: TotalListingsEmailed
    When "TotalListingsEmailed" exists in the "InternetTracking" metadata
    Then "TotalListingsEmailed" MUST be "Integer" data type

  @InternetTracking @RESI @RLSE @RINC @LAND @MOBI @FARM @COMS @COML @dd-2.0
  Scenario: TotalCmaReportsCreated
    When "TotalCmaReportsCreated" exists in the "InternetTracking" metadata
    Then "TotalCmaReportsCreated" MUST be "Integer" data type

  @InternetTracking @RESI @RLSE @RINC @LAND @MOBI @FARM @COMS @COML @dd-2.0
  Scenario: TotalCmaReportsRan
    When "TotalCmaReportsRan" exists in the "InternetTracking" metadata
    Then "TotalCmaReportsRan" MUST be "Integer" data type

  @InternetTracking @RESI @RLSE @RINC @LAND @MOBI @FARM @COMS @COML @dd-2.0
  Scenario: TotalCmaReportsShared
    When "TotalCmaReportsShared" exists in the "InternetTracking" metadata
    Then "TotalCmaReportsShared" MUST be "Integer" data type

  @InternetTracking @RESI @RLSE @RINC @LAND @MOBI @FARM @COMS @COML @dd-2.0
  Scenario: TotalCmaReportsEmailed
    When "TotalCmaReportsEmailed" exists in the "InternetTracking" metadata
    Then "TotalCmaReportsEmailed" MUST be "Integer" data type

  @InternetTracking @RESI @RLSE @RINC @LAND @MOBI @FARM @COMS @COML @dd-2.0
  Scenario: TotalShowingsRequested
    When "TotalShowingsRequested" exists in the "InternetTracking" metadata
    Then "TotalShowingsRequested" MUST be "Integer" data type

  @InternetTracking @RESI @RLSE @RINC @LAND @MOBI @FARM @COMS @COML @dd-2.0
  Scenario: TotalShowingsCompleted
    When "TotalShowingsCompleted" exists in the "InternetTracking" metadata
    Then "TotalShowingsCompleted" MUST be "Integer" data type

  @InternetTracking @RESI @RLSE @RINC @LAND @MOBI @FARM @COMS @COML @dd-2.0
  Scenario: TotalLogins
    When "TotalLogins" exists in the "InternetTracking" metadata
    Then "TotalLogins" MUST be "Integer" data type

  @InternetTracking @RESI @RLSE @RINC @LAND @MOBI @FARM @COMS @COML @dd-2.0
  Scenario: UniqueLogins
    When "UniqueLogins" exists in the "InternetTracking" metadata
    Then "UniqueLogins" MUST be "Integer" data type

  @InternetTracking @RESI @RLSE @RINC @LAND @MOBI @FARM @COMS @COML @dd-2.0
  Scenario: MobileLogins
    When "MobileLogins" exists in the "InternetTracking" metadata
    Then "MobileLogins" MUST be "Integer" data type
