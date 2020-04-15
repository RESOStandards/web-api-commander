Feature: InternetTracking

  Scenario: ActorCity
    Given "ActorCity" exists in the metadata
    Then "ActorCity" should be "String" data type
    And "ActorCity" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: ActorEmail
    Given "ActorEmail" exists in the metadata
    Then "ActorEmail" should be "String" data type
    And "ActorEmail" length should be less than or equal to the RESO Suggested Max Length of 80

  Scenario: ActorID
    Given "ActorID" exists in the metadata
    Then "ActorID" should be "String" data type
    And "ActorID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: ActorIP
    Given "ActorIP" exists in the metadata
    Then "ActorIP" should be "String" data type
    And "ActorIP" length should be less than or equal to the RESO Suggested Max Length of 39

  Scenario: ActorKey
    Given "ActorKey" exists in the metadata
    Then "ActorKey" should be "String" data type
    And "ActorKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ActorKeyNumeric
    Given "ActorKeyNumeric" exists in the metadata
    Then "ActorKeyNumeric" should be "Integer" data type

  Scenario: ActorLatitude
    Given "ActorLatitude" exists in the metadata
    Then "ActorLatitude" should be "Decimal" data type
    And "ActorLatitude" precision should be less than or equal to the RESO Suggested Max Length of 12
    And "ActorLatitude" scale should be less than or equal to the RESO Suggested Max Scale of 8

  Scenario: ActorLongitude
    Given "ActorLongitude" exists in the metadata
    Then "ActorLongitude" should be "Decimal" data type
    And "ActorLongitude" precision should be less than or equal to the RESO Suggested Max Length of 12
    And "ActorLongitude" scale should be less than or equal to the RESO Suggested Max Scale of 8

  Scenario: ActorOriginatingSystemID
    Given "ActorOriginatingSystemID" exists in the metadata
    Then "ActorOriginatingSystemID" should be "String" data type
    And "ActorOriginatingSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: ActorOriginatingSystemName
    Given "ActorOriginatingSystemName" exists in the metadata
    Then "ActorOriginatingSystemName" should be "String" data type
    And "ActorOriginatingSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ActorPhone
    Given "ActorPhone" exists in the metadata
    Then "ActorPhone" should be "String" data type
    And "ActorPhone" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: ActorPhoneExt
    Given "ActorPhoneExt" exists in the metadata
    Then "ActorPhoneExt" should be "String" data type
    And "ActorPhoneExt" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: ActorPostalCode
    Given "ActorPostalCode" exists in the metadata
    Then "ActorPostalCode" should be "String" data type
    And "ActorPostalCode" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: ActorPostalCodePlus4
    Given "ActorPostalCodePlus4" exists in the metadata
    Then "ActorPostalCodePlus4" should be "String" data type
    And "ActorPostalCodePlus4" length should be less than or equal to the RESO Suggested Max Length of 4

  Scenario: ActorRegion
    Given "ActorRegion" exists in the metadata
    Then "ActorRegion" should be "String" data type
    And "ActorRegion" length should be less than or equal to the RESO Suggested Max Length of 150

  Scenario: ActorSourceSystemID
    Given "ActorSourceSystemID" exists in the metadata
    Then "ActorSourceSystemID" should be "String" data type
    And "ActorSourceSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: ActorSourceSystemName
    Given "ActorSourceSystemName" exists in the metadata
    Then "ActorSourceSystemName" should be "String" data type
    And "ActorSourceSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ActorStateOrProvince
    Given "ActorStateOrProvince" exists in the metadata
    And "ActorStateOrProvince" enum values can be compiled
    Then "ActorStateOrProvince" should be "String" data type
    And "ActorStateOrProvince" should only contain enum values found in the metadata
    And "ActorStateOrProvince" length should be less than or equal to the RESO Suggested Max Length of 2

  Scenario: ActorType
    Given "ActorType" exists in the metadata
    And "ActorType" enum values can be compiled
    Then "ActorType" should be "String" data type
    And "ActorType" should only contain enum values found in the metadata
    And "ActorType" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: ColorDepth
    Given "ColorDepth" exists in the metadata
    Then "ColorDepth" should be "Integer" data type

  Scenario: DeviceType
    Given "DeviceType" exists in the metadata
    And "DeviceType" enum values can be compiled
    Then "DeviceType" should be "String" data type
    And "DeviceType" should only contain enum values found in the metadata
    And "DeviceType" length should be less than or equal to the RESO Suggested Max Length of 128

  Scenario: EventDescription
    Given "EventDescription" exists in the metadata
    Then "EventDescription" should be "String" data type
    And "EventDescription" length should be less than or equal to the RESO Suggested Max Length of 1024

  Scenario: EventKey
    Given "EventKey" exists in the metadata
    Then "EventKey" should be "String" data type
    And "EventKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: EventKeyNumeric
    Given "EventKeyNumeric" exists in the metadata
    Then "EventKeyNumeric" should be "Integer" data type

  Scenario: EventLabel
    Given "EventLabel" exists in the metadata
    Then "EventLabel" should be "String" data type
    And "EventLabel" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: EventOriginatingSystemID
    Given "EventOriginatingSystemID" exists in the metadata
    Then "EventOriginatingSystemID" should be "String" data type
    And "EventOriginatingSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: EventOriginatingSystemName
    Given "EventOriginatingSystemName" exists in the metadata
    Then "EventOriginatingSystemName" should be "String" data type
    And "EventOriginatingSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: EventSourceSystemID
    Given "EventSourceSystemID" exists in the metadata
    Then "EventSourceSystemID" should be "String" data type
    And "EventSourceSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: EventSourceSystemName
    Given "EventSourceSystemName" exists in the metadata
    Then "EventSourceSystemName" should be "String" data type
    And "EventSourceSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: EventTarget
    Given "EventTarget" exists in the metadata
    And "EventTarget" enum values can be compiled
    Then "EventTarget" should be "String" data type
    And "EventTarget" should only contain enum values found in the metadata
    And "EventTarget" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: EventTimestamp
    Given "EventTimestamp" exists in the metadata
    Then "EventTimestamp" should be "Timestamp" data type
    And "EventTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: EventType
    Given "EventType" exists in the metadata
    And "EventType" enum values can be compiled
    Then "EventType" should be "String" data type
    And "EventType" should only contain enum values found in the metadata
    And "EventType" length should be less than or equal to the RESO Suggested Max Length of 128

  Scenario: ObjectID
    Given "ObjectID" exists in the metadata
    Then "ObjectID" should be "String" data type
    And "ObjectID" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ObjectIdType
    Given "ObjectIdType" exists in the metadata
    And "ObjectIdType" enum values can be compiled
    Then "ObjectIdType" should be "String" data type
    And "ObjectIdType" should only contain enum values found in the metadata
    And "ObjectIdType" length should be less than or equal to the RESO Suggested Max Length of 128

  Scenario: ObjectKey
    Given "ObjectKey" exists in the metadata
    Then "ObjectKey" should be "String" data type
    And "ObjectKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ObjectKeyNumeric
    Given "ObjectKeyNumeric" exists in the metadata
    Then "ObjectKeyNumeric" should be "Integer" data type

  Scenario: ObjectOriginatingSystemID
    Given "ObjectOriginatingSystemID" exists in the metadata
    Then "ObjectOriginatingSystemID" should be "String" data type
    And "ObjectOriginatingSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: ObjectOriginatingSystemName
    Given "ObjectOriginatingSystemName" exists in the metadata
    Then "ObjectOriginatingSystemName" should be "String" data type
    And "ObjectOriginatingSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ObjectSourceSystemID
    Given "ObjectSourceSystemID" exists in the metadata
    Then "ObjectSourceSystemID" should be "String" data type
    And "ObjectSourceSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: ObjectSourceSystemName
    Given "ObjectSourceSystemName" exists in the metadata
    Then "ObjectSourceSystemName" should be "String" data type
    And "ObjectSourceSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ObjectType
    Given "ObjectType" exists in the metadata
    And "ObjectType" enum values can be compiled
    Then "ObjectType" should be "String" data type
    And "ObjectType" should only contain enum values found in the metadata
    And "ObjectType" length should be less than or equal to the RESO Suggested Max Length of 128

  Scenario: ObjectURL
    Given "ObjectURL" exists in the metadata
    Then "ObjectURL" should be "String" data type
    And "ObjectURL" length should be less than or equal to the RESO Suggested Max Length of 8000

  Scenario: OriginatingSystemActorKey
    Given "OriginatingSystemActorKey" exists in the metadata
    Then "OriginatingSystemActorKey" should be "String" data type
    And "OriginatingSystemActorKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OriginatingSystemEventKey
    Given "OriginatingSystemEventKey" exists in the metadata
    Then "OriginatingSystemEventKey" should be "String" data type
    And "OriginatingSystemEventKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OriginatingSystemObjectKey
    Given "OriginatingSystemObjectKey" exists in the metadata
    Then "OriginatingSystemObjectKey" should be "String" data type
    And "OriginatingSystemObjectKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ReferringURL
    Given "ReferringURL" exists in the metadata
    Then "ReferringURL" should be "String" data type
    And "ReferringURL" length should be less than or equal to the RESO Suggested Max Length of 8000

  Scenario: ScreenHeight
    Given "ScreenHeight" exists in the metadata
    Then "ScreenHeight" should be "Integer" data type

  Scenario: ScreenWidth
    Given "ScreenWidth" exists in the metadata
    Then "ScreenWidth" should be "Integer" data type

  Scenario: SessionID
    Given "SessionID" exists in the metadata
    Then "SessionID" should be "String" data type
    And "SessionID" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SourceSystemActorKey
    Given "SourceSystemActorKey" exists in the metadata
    Then "SourceSystemActorKey" should be "String" data type
    And "SourceSystemActorKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SourceSystemEventKey
    Given "SourceSystemEventKey" exists in the metadata
    Then "SourceSystemEventKey" should be "String" data type
    And "SourceSystemEventKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SourceSystemObjectKey
    Given "SourceSystemObjectKey" exists in the metadata
    Then "SourceSystemObjectKey" should be "String" data type
    And "SourceSystemObjectKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: TimeZoneOffset
    Given "TimeZoneOffset" exists in the metadata
    Then "TimeZoneOffset" should be "Integer" data type

  Scenario: UserAgent
    Given "UserAgent" exists in the metadata
    Then "UserAgent" should be "String" data type
    And "UserAgent" length should be less than or equal to the RESO Suggested Max Length of 255
