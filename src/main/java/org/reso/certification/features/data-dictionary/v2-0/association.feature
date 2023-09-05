# This file was autogenerated on: 2023090509434487
Feature: Association

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

  @Association @dd-2.0
  Scenario: AssociationAddress1
    When "AssociationAddress1" exists in the "Association" metadata
    Then "AssociationAddress1" MUST be "String" data type
    And "AssociationAddress1" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Association @dd-2.0
  Scenario: AssociationAddress2
    When "AssociationAddress2" exists in the "Association" metadata
    Then "AssociationAddress2" MUST be "String" data type
    And "AssociationAddress2" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Association @dd-2.0
  Scenario: AssociationCareOf
    When "AssociationCareOf" exists in the "Association" metadata
    Then "AssociationCareOf" MUST be "String" data type
    And "AssociationCareOf" length SHOULD be equal to the RESO Suggested Max Length of 30

  @Association @dd-2.0
  Scenario: AssociationCharterDate
    When "AssociationCharterDate" exists in the "Association" metadata
    Then "AssociationCharterDate" MUST be "Date" data type

  @Association @dd-2.0
  Scenario: AssociationCity
    When "AssociationCity" exists in the "Association" metadata
    Then "AssociationCity" MUST be "String" data type
    And "AssociationCity" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Association @dd-2.0
  Scenario: AssociationCountry
    When "AssociationCountry" exists in the "Association" metadata
    Then "AssociationCountry" MUST be "Single Enumeration" data type

  @Association @dd-2.0
  Scenario: AssociationCountyOrParish
    When "AssociationCountyOrParish" exists in the "Association" metadata
    Then "AssociationCountyOrParish" MUST be "Single Enumeration" data type

  @Association @dd-2.0
  Scenario: AssociationFax
    When "AssociationFax" exists in the "Association" metadata
    Then "AssociationFax" MUST be "String" data type
    And "AssociationFax" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Association @dd-2.0
  Scenario: AssociationKey
    When "AssociationKey" exists in the "Association" metadata
    Then "AssociationKey" MUST be "String" data type
    And "AssociationKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Association @dd-2.0
  Scenario: AssociationMailAddress1
    When "AssociationMailAddress1" exists in the "Association" metadata
    Then "AssociationMailAddress1" MUST be "String" data type
    And "AssociationMailAddress1" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Association @dd-2.0
  Scenario: AssociationMailAddress2
    When "AssociationMailAddress2" exists in the "Association" metadata
    Then "AssociationMailAddress2" MUST be "String" data type
    And "AssociationMailAddress2" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Association @dd-2.0
  Scenario: AssociationMailCareOf
    When "AssociationMailCareOf" exists in the "Association" metadata
    Then "AssociationMailCareOf" MUST be "String" data type
    And "AssociationMailCareOf" length SHOULD be equal to the RESO Suggested Max Length of 30

  @Association @dd-2.0
  Scenario: AssociationMailCity
    When "AssociationMailCity" exists in the "Association" metadata
    Then "AssociationMailCity" MUST be "String" data type
    And "AssociationMailCity" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Association @dd-2.0
  Scenario: AssociationMailCountry
    When "AssociationMailCountry" exists in the "Association" metadata
    Then "AssociationMailCountry" MUST be "Single Enumeration" data type

  @Association @dd-2.0
  Scenario: AssociationMailCountyOrParish
    When "AssociationMailCountyOrParish" exists in the "Association" metadata
    Then "AssociationMailCountyOrParish" MUST be "Single Enumeration" data type

  @Association @dd-2.0
  Scenario: AssociationMailPostalCode
    When "AssociationMailPostalCode" exists in the "Association" metadata
    Then "AssociationMailPostalCode" MUST be "String" data type
    And "AssociationMailPostalCode" length SHOULD be equal to the RESO Suggested Max Length of 10

  @Association @dd-2.0
  Scenario: AssociationMailPostalCodePlus4
    When "AssociationMailPostalCodePlus4" exists in the "Association" metadata
    Then "AssociationMailPostalCodePlus4" MUST be "String" data type
    And "AssociationMailPostalCodePlus4" length SHOULD be equal to the RESO Suggested Max Length of 4

  @Association @dd-2.0
  Scenario: AssociationMailStateOfProvince
    When "AssociationMailStateOfProvince" exists in the "Association" metadata
    Then "AssociationMailStateOfProvince" MUST be "Single Enumeration" data type

  @Association @dd-2.0
  Scenario: AssociationMlsId
    When "AssociationMlsId" exists in the "Association" metadata
    Then "AssociationMlsId" MUST be "String" data type
    And "AssociationMlsId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Association @dd-2.0
  Scenario: AssociationName
    When "AssociationName" exists in the "Association" metadata
    Then "AssociationName" MUST be "String" data type
    And "AssociationName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Association @dd-2.0
  Scenario: AssociationNationalAssociationId
    When "AssociationNationalAssociationId" exists in the "Association" metadata
    Then "AssociationNationalAssociationId" MUST be "String" data type
    And "AssociationNationalAssociationId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Association @dd-2.0
  Scenario: AssociationPhone
    When "AssociationPhone" exists in the "Association" metadata
    Then "AssociationPhone" MUST be "String" data type
    And "AssociationPhone" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Association @dd-2.0
  Scenario: AssociationPostalCode
    When "AssociationPostalCode" exists in the "Association" metadata
    Then "AssociationPostalCode" MUST be "String" data type
    And "AssociationPostalCode" length SHOULD be equal to the RESO Suggested Max Length of 10

  @Association @dd-2.0
  Scenario: AssociationPostalCodePlus4
    When "AssociationPostalCodePlus4" exists in the "Association" metadata
    Then "AssociationPostalCodePlus4" MUST be "String" data type
    And "AssociationPostalCodePlus4" length SHOULD be equal to the RESO Suggested Max Length of 4

  @Association @dd-2.0
  Scenario: AssociationStateOrProvince
    When "AssociationStateOrProvince" exists in the "Association" metadata
    Then "AssociationStateOrProvince" MUST be "Single Enumeration" data type

  @Association @dd-2.0
  Scenario: AssociationStatus
    When "AssociationStatus" exists in the "Association" metadata
    Then "AssociationStatus" MUST be "Single Enumeration" data type

  @Association @dd-2.0
  Scenario: AssociationType
    When "AssociationType" exists in the "Association" metadata
    Then "AssociationType" MUST be "Single Enumeration" data type

  @Association @dd-2.0
  Scenario: ExecutiveOfficerMemberKey
    When "ExecutiveOfficerMemberKey" exists in the "Association" metadata
    Then "ExecutiveOfficerMemberKey" MUST be "String" data type
    And "ExecutiveOfficerMemberKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Association @dd-2.0
  Scenario: ExecutiveOfficerMemberMlsId
    When "ExecutiveOfficerMemberMlsId" exists in the "Association" metadata
    Then "ExecutiveOfficerMemberMlsId" MUST be "String" data type
    And "ExecutiveOfficerMemberMlsId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Association @dd-2.0
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "Association" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @Association @dd-2.0
  Scenario: MultipleListingServiceId
    When "MultipleListingServiceId" exists in the "Association" metadata
    Then "MultipleListingServiceId" MUST be "String" data type
    And "MultipleListingServiceId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Association @dd-2.0
  Scenario: OriginalEntryTimestamp
    When "OriginalEntryTimestamp" exists in the "Association" metadata
    Then "OriginalEntryTimestamp" MUST be "Timestamp" data type

  @Association @dd-2.0
  Scenario: OriginatingSystemAssociationKey
    When "OriginatingSystemAssociationKey" exists in the "Association" metadata
    Then "OriginatingSystemAssociationKey" MUST be "String" data type
    And "OriginatingSystemAssociationKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Association @dd-2.0
  Scenario: OriginatingSystemId
    When "OriginatingSystemId" exists in the "Association" metadata
    Then "OriginatingSystemId" MUST be "String" data type
    And "OriginatingSystemId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Association @dd-2.0
  Scenario: OriginatingSystemName
    When "OriginatingSystemName" exists in the "Association" metadata
    Then "OriginatingSystemName" MUST be "String" data type
    And "OriginatingSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Association @dd-2.0
  Scenario: SocialMediaUrlOrId
    When "SocialMediaUrlOrId" exists in the "Association" metadata
    Then "SocialMediaUrlOrId" MUST be "String" data type
    And "SocialMediaUrlOrId" length SHOULD be equal to the RESO Suggested Max Length of 8000

  @Association @dd-2.0
  Scenario: SocialMediaType
    When "SocialMediaType" exists in the "Association" metadata
    Then "SocialMediaType" MUST be "Single Enumeration" data type

  @Association @dd-2.0
  Scenario: SourceSystemAssociationKey
    When "SourceSystemAssociationKey" exists in the "Association" metadata
    Then "SourceSystemAssociationKey" MUST be "String" data type
    And "SourceSystemAssociationKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Association @dd-2.0
  Scenario: SourceSystemId
    When "SourceSystemId" exists in the "Association" metadata
    Then "SourceSystemId" MUST be "String" data type
    And "SourceSystemId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Association @dd-2.0
  Scenario: SourceSystemName
    When "SourceSystemName" exists in the "Association" metadata
    Then "SourceSystemName" MUST be "String" data type
    And "SourceSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255
