# This file was autogenerated on: 2023031911041682
Feature: LockOrBox

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

  @LockOrBox
  Scenario: KeyOrCredentialId
    When "KeyOrCredentialId" exists in the "LockOrBox" metadata
    Then "KeyOrCredentialId" MUST be "String" data type
    And "KeyOrCredentialId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @LockOrBox
  Scenario: ListAgentFullName
    When "ListAgentFullName" exists in the "LockOrBox" metadata
    Then "ListAgentFullName" MUST be "String" data type
    And "ListAgentFullName" length SHOULD be equal to the RESO Suggested Max Length of 150

  @LockOrBox
  Scenario: ListingAddress1
    When "ListingAddress1" exists in the "LockOrBox" metadata
    Then "ListingAddress1" MUST be "String" data type
    And "ListingAddress1" length SHOULD be equal to the RESO Suggested Max Length of 50

  @LockOrBox
  Scenario: ListingAddress2
    When "ListingAddress2" exists in the "LockOrBox" metadata
    Then "ListingAddress2" MUST be "String" data type
    And "ListingAddress2" length SHOULD be equal to the RESO Suggested Max Length of 50

  @LockOrBox
  Scenario: ListingCity
    When "ListingCity" exists in the "LockOrBox" metadata
    Then "ListingCity" MUST be "String" data type
    And "ListingCity" length SHOULD be equal to the RESO Suggested Max Length of 50

  @LockOrBox
  Scenario: ListingCountry
    When "ListingCountry" exists in the "LockOrBox" metadata
    Then "ListingCountry" MUST be "Single Enumeration" data type

  @LockOrBox
  Scenario: ListingId
    When "ListingId" exists in the "LockOrBox" metadata
    Then "ListingId" MUST be "String" data type
    And "ListingId" length SHOULD be equal to the RESO Suggested Max Length of 255

  @LockOrBox
  Scenario: ListingKey
    When "ListingKey" exists in the "LockOrBox" metadata
    Then "ListingKey" MUST be "String" data type
    And "ListingKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @LockOrBox
  Scenario: ListingLatitude
    When "ListingLatitude" exists in the "LockOrBox" metadata
    Then "ListingLatitude" MUST be "Integer" data type

  @LockOrBox
  Scenario: ListingLongitude
    When "ListingLongitude" exists in the "LockOrBox" metadata
    Then "ListingLongitude" MUST be "Integer" data type

  @LockOrBox
  Scenario: ListingPostalCode
    When "ListingPostalCode" exists in the "LockOrBox" metadata
    Then "ListingPostalCode" MUST be "String" data type
    And "ListingPostalCode" length SHOULD be equal to the RESO Suggested Max Length of 10

  @LockOrBox
  Scenario: ListingPostalCodePlus4
    When "ListingPostalCodePlus4" exists in the "LockOrBox" metadata
    Then "ListingPostalCodePlus4" MUST be "String" data type
    And "ListingPostalCodePlus4" length SHOULD be equal to the RESO Suggested Max Length of 4

  @LockOrBox
  Scenario: ListingStateOrProvince
    When "ListingStateOrProvince" exists in the "LockOrBox" metadata
    Then "ListingStateOrProvince" MUST be "Single Enumeration" data type

  @LockOrBox
  Scenario: ListingTimeZone
    When "ListingTimeZone" exists in the "LockOrBox" metadata
    Then "ListingTimeZone" MUST be "String" data type
    And "ListingTimeZone" length SHOULD be equal to the RESO Suggested Max Length of 4

  @LockOrBox
  Scenario: LockOrBoxAccessTimestamp
    When "LockOrBoxAccessTimestamp" exists in the "LockOrBox" metadata
    Then "LockOrBoxAccessTimestamp" MUST be "Timestamp" data type

  @LockOrBox
  Scenario: LockOrBoxAccessType
    When "LockOrBoxAccessType" exists in the "LockOrBox" metadata
    Then "LockOrBoxAccessType" MUST be "Multiple Enumeration" data type

  @LockOrBox
  Scenario: LockOrBoxId
    When "LockOrBoxId" exists in the "LockOrBox" metadata
    Then "LockOrBoxId" MUST be "String" data type
    And "LockOrBoxId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @LockOrBox
  Scenario: LockOrBoxInstalledTimestamp
    When "LockOrBoxInstalledTimestamp" exists in the "LockOrBox" metadata
    Then "LockOrBoxInstalledTimestamp" MUST be "Timestamp" data type

  @LockOrBox
  Scenario: LockOrBoxKey
    When "LockOrBoxKey" exists in the "LockOrBox" metadata
    Then "LockOrBoxKey" MUST be "String" data type
    And "LockOrBoxKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @LockOrBox
  Scenario: LockOrBoxOriginatingSystemID
    When "LockOrBoxOriginatingSystemID" exists in the "LockOrBox" metadata
    Then "LockOrBoxOriginatingSystemID" MUST be "String" data type
    And "LockOrBoxOriginatingSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @LockOrBox
  Scenario: LockOrBoxOriginatingSystemKey
    When "LockOrBoxOriginatingSystemKey" exists in the "LockOrBox" metadata
    Then "LockOrBoxOriginatingSystemKey" MUST be "String" data type
    And "LockOrBoxOriginatingSystemKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @LockOrBox
  Scenario: LockOrBoxOriginatingSystemName
    When "LockOrBoxOriginatingSystemName" exists in the "LockOrBox" metadata
    Then "LockOrBoxOriginatingSystemName" MUST be "String" data type
    And "LockOrBoxOriginatingSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @LockOrBox
  Scenario: LockOrBoxSourceSystemID
    When "LockOrBoxSourceSystemID" exists in the "LockOrBox" metadata
    Then "LockOrBoxSourceSystemID" MUST be "String" data type
    And "LockOrBoxSourceSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @LockOrBox
  Scenario: LockOrBoxSourceSystemKey
    When "LockOrBoxSourceSystemKey" exists in the "LockOrBox" metadata
    Then "LockOrBoxSourceSystemKey" MUST be "String" data type
    And "LockOrBoxSourceSystemKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @LockOrBox
  Scenario: LockOrBoxSourceSystemName
    When "LockOrBoxSourceSystemName" exists in the "LockOrBox" metadata
    Then "LockOrBoxSourceSystemName" MUST be "String" data type
    And "LockOrBoxSourceSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @LockOrBox
  Scenario: Notes
    When "Notes" exists in the "LockOrBox" metadata
    Then "Notes" MUST be "String" data type
    And "Notes" length SHOULD be equal to the RESO Suggested Max Length of 1024

  @LockOrBox
  Scenario: ShowingAgentAOR
    When "ShowingAgentAOR" exists in the "LockOrBox" metadata
    Then "ShowingAgentAOR" MUST be "Single Enumeration" data type

  @LockOrBox
  Scenario: ShowingAgentEmail
    When "ShowingAgentEmail" exists in the "LockOrBox" metadata
    Then "ShowingAgentEmail" MUST be "String" data type
    And "ShowingAgentEmail" length SHOULD be equal to the RESO Suggested Max Length of 80

  @LockOrBox
  Scenario: ShowingAgentFirstName
    When "ShowingAgentFirstName" exists in the "LockOrBox" metadata
    Then "ShowingAgentFirstName" MUST be "String" data type
    And "ShowingAgentFirstName" length SHOULD be equal to the RESO Suggested Max Length of 50

  @LockOrBox
  Scenario: ShowingAgentFullName
    When "ShowingAgentFullName" exists in the "LockOrBox" metadata
    Then "ShowingAgentFullName" MUST be "String" data type
    And "ShowingAgentFullName" length SHOULD be equal to the RESO Suggested Max Length of 150

  @LockOrBox
  Scenario: ShowingAgentId
    When "ShowingAgentId" exists in the "LockOrBox" metadata
    Then "ShowingAgentId" MUST be "String" data type
    And "ShowingAgentId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @LockOrBox
  Scenario: ShowingAgentLastName
    When "ShowingAgentLastName" exists in the "LockOrBox" metadata
    Then "ShowingAgentLastName" MUST be "String" data type
    And "ShowingAgentLastName" length SHOULD be equal to the RESO Suggested Max Length of 50

  @LockOrBox
  Scenario: ShowingAgentMlsId
    When "ShowingAgentMlsId" exists in the "LockOrBox" metadata
    Then "ShowingAgentMlsId" MUST be "String" data type
    And "ShowingAgentMlsId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @LockOrBox
  Scenario: ShowingAgentPhone
    When "ShowingAgentPhone" exists in the "LockOrBox" metadata
    Then "ShowingAgentPhone" MUST be "String" data type
    And "ShowingAgentPhone" length SHOULD be equal to the RESO Suggested Max Length of 16

  @LockOrBox
  Scenario: ShowingAgentPhoneExt
    When "ShowingAgentPhoneExt" exists in the "LockOrBox" metadata
    Then "ShowingAgentPhoneExt" MUST be "String" data type
    And "ShowingAgentPhoneExt" length SHOULD be equal to the RESO Suggested Max Length of 10

  @LockOrBox
  Scenario: ShowingOfficeId
    When "ShowingOfficeId" exists in the "LockOrBox" metadata
    Then "ShowingOfficeId" MUST be "String" data type
    And "ShowingOfficeId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @LockOrBox
  Scenario: ShowingOfficeName
    When "ShowingOfficeName" exists in the "LockOrBox" metadata
    Then "ShowingOfficeName" MUST be "String" data type
    And "ShowingOfficeName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @LockOrBox
  Scenario: ShowingOfficePhone
    When "ShowingOfficePhone" exists in the "LockOrBox" metadata
    Then "ShowingOfficePhone" MUST be "String" data type
    And "ShowingOfficePhone" length SHOULD be equal to the RESO Suggested Max Length of 16
