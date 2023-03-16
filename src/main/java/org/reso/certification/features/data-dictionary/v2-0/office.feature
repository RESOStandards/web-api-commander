# This file was autogenerated on: 20230315200244456
Feature: Office

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

  @Office
  Scenario: FranchiseAffiliation
    When "FranchiseAffiliation" exists in the "Office" metadata
    Then "FranchiseAffiliation" MUST be "String" data type
    And "FranchiseAffiliation" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Office @IDX
  Scenario: IDXOfficeParticipationYN
    When "IDXOfficeParticipationYN" exists in the "Office" metadata
    Then "IDXOfficeParticipationYN" MUST be "Boolean" data type

  @Office @IDX
  Scenario: MainOfficeKey
    When "MainOfficeKey" exists in the "Office" metadata
    Then "MainOfficeKey" MUST be "String" data type
    And "MainOfficeKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Office @IDX
  Scenario: MainOfficeMlsId
    When "MainOfficeMlsId" exists in the "Office" metadata
    Then "MainOfficeMlsId" MUST be "String" data type
    And "MainOfficeMlsId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Office @IDX
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "Office" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @Office
  Scenario: OfficeAOR
    When "OfficeAOR" exists in the "Office" metadata
    Then "OfficeAOR" MUST be "Single Enumeration" data type

  @Office
  Scenario: OfficeAORMlsId
    When "OfficeAORMlsId" exists in the "Office" metadata
    Then "OfficeAORMlsId" MUST be "String" data type
    And "OfficeAORMlsId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Office
  Scenario: OfficeAORkey
    When "OfficeAORkey" exists in the "Office" metadata
    Then "OfficeAORkey" MUST be "String" data type
    And "OfficeAORkey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Office @IDX
  Scenario: OfficeAddress1
    When "OfficeAddress1" exists in the "Office" metadata
    Then "OfficeAddress1" MUST be "String" data type
    And "OfficeAddress1" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Office @IDX
  Scenario: OfficeAddress2
    When "OfficeAddress2" exists in the "Office" metadata
    Then "OfficeAddress2" MUST be "String" data type
    And "OfficeAddress2" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Office
  Scenario: OfficeAssociationComments
    When "OfficeAssociationComments" exists in the "Office" metadata
    Then "OfficeAssociationComments" MUST be "String" data type
    And "OfficeAssociationComments" length SHOULD be equal to the RESO Suggested Max Length of 500

  @Office
  Scenario: OfficeBranchType
    When "OfficeBranchType" exists in the "Office" metadata
    Then "OfficeBranchType" MUST be "Single Enumeration" data type

  @Office @IDX
  Scenario: OfficeBrokerKey
    When "OfficeBrokerKey" exists in the "Office" metadata
    Then "OfficeBrokerKey" MUST be "String" data type
    And "OfficeBrokerKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Office
  Scenario: OfficeBrokerMlsId
    When "OfficeBrokerMlsId" exists in the "Office" metadata
    Then "OfficeBrokerMlsId" MUST be "String" data type
    And "OfficeBrokerMlsId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Office @IDX
  Scenario: OfficeCity
    When "OfficeCity" exists in the "Office" metadata
    Then "OfficeCity" MUST be "String" data type
    And "OfficeCity" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Office
  Scenario: OfficeCorporateLicense
    When "OfficeCorporateLicense" exists in the "Office" metadata
    Then "OfficeCorporateLicense" MUST be "String" data type
    And "OfficeCorporateLicense" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Office
  Scenario: OfficeCountyOrParish
    Given that the following synonyms for "OfficeCountyOrParish" DO NOT exist in the "Office" metadata
      | County |
    When "OfficeCountyOrParish" exists in the "Office" metadata
    Then "OfficeCountyOrParish" MUST be "Single Enumeration" data type

  @Office
  Scenario: OfficeEmail
    When "OfficeEmail" exists in the "Office" metadata
    Then "OfficeEmail" MUST be "String" data type
    And "OfficeEmail" length SHOULD be equal to the RESO Suggested Max Length of 80

  @Office
  Scenario: OfficeFax
    When "OfficeFax" exists in the "Office" metadata
    Then "OfficeFax" MUST be "String" data type
    And "OfficeFax" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Office @IDX
  Scenario: OfficeKey
    When "OfficeKey" exists in the "Office" metadata
    Then "OfficeKey" MUST be "String" data type
    And "OfficeKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Office
  Scenario: OfficeManagerKey
    When "OfficeManagerKey" exists in the "Office" metadata
    Then "OfficeManagerKey" MUST be "String" data type
    And "OfficeManagerKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Office
  Scenario: OfficeManagerMlsId
    When "OfficeManagerMlsId" exists in the "Office" metadata
    Then "OfficeManagerMlsId" MUST be "String" data type
    And "OfficeManagerMlsId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Office @IDX
  Scenario: OfficeMlsId
    When "OfficeMlsId" exists in the "Office" metadata
    Then "OfficeMlsId" MUST be "String" data type
    And "OfficeMlsId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Office @IDX
  Scenario: OfficeName
    When "OfficeName" exists in the "Office" metadata
    Then "OfficeName" MUST be "String" data type
    And "OfficeName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Office
  Scenario: OfficeNationalAssociationId
    When "OfficeNationalAssociationId" exists in the "Office" metadata
    Then "OfficeNationalAssociationId" MUST be "String" data type
    And "OfficeNationalAssociationId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Office @IDX
  Scenario: OfficePhone
    When "OfficePhone" exists in the "Office" metadata
    Then "OfficePhone" MUST be "String" data type
    And "OfficePhone" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Office
  Scenario: OfficePhoneExt
    When "OfficePhoneExt" exists in the "Office" metadata
    Then "OfficePhoneExt" MUST be "String" data type
    And "OfficePhoneExt" length SHOULD be equal to the RESO Suggested Max Length of 10

  @Office @IDX
  Scenario: OfficePostalCode
    When "OfficePostalCode" exists in the "Office" metadata
    Then "OfficePostalCode" MUST be "String" data type
    And "OfficePostalCode" length SHOULD be equal to the RESO Suggested Max Length of 10

  @Office @IDX
  Scenario: OfficePostalCodePlus4
    When "OfficePostalCodePlus4" exists in the "Office" metadata
    Then "OfficePostalCodePlus4" MUST be "String" data type
    And "OfficePostalCodePlus4" length SHOULD be equal to the RESO Suggested Max Length of 4

  @Office @IDX
  Scenario: OfficeStateOrProvince
    When "OfficeStateOrProvince" exists in the "Office" metadata
    Then "OfficeStateOrProvince" MUST be "Single Enumeration" data type

  @Office @IDX
  Scenario: OfficeStatus
    When "OfficeStatus" exists in the "Office" metadata
    Then "OfficeStatus" MUST be "Single Enumeration" data type

  @Office
  Scenario: OfficeType
    When "OfficeType" exists in the "Office" metadata
    Then "OfficeType" MUST be "Single Enumeration" data type

  @Office
  Scenario: OriginalEntryTimestamp
    When "OriginalEntryTimestamp" exists in the "Office" metadata
    Then "OriginalEntryTimestamp" MUST be "Timestamp" data type

  @Office @IDX
  Scenario: OriginatingSystemID
    Given that the following synonyms for "OriginatingSystemID" DO NOT exist in the "Office" metadata
      | MLSID |
    When "OriginatingSystemID" exists in the "Office" metadata
    Then "OriginatingSystemID" MUST be "String" data type
    And "OriginatingSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Office @IDX
  Scenario: OriginatingSystemName
    Given that the following synonyms for "OriginatingSystemName" DO NOT exist in the "Office" metadata
      | ProviderName |
      | MLSID |
    When "OriginatingSystemName" exists in the "Office" metadata
    Then "OriginatingSystemName" MUST be "String" data type
    And "OriginatingSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Office @IDX
  Scenario: OriginatingSystemOfficeKey
    Given that the following synonyms for "OriginatingSystemOfficeKey" DO NOT exist in the "Office" metadata
      | ProviderKey |
    When "OriginatingSystemOfficeKey" exists in the "Office" metadata
    Then "OriginatingSystemOfficeKey" MUST be "String" data type
    And "OriginatingSystemOfficeKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Office
  Scenario: SocialMediaType
    When "SocialMediaType" exists in the "Office" metadata
    Then "SocialMediaType" MUST be "Single Enumeration" data type

  @Office @IDX
  Scenario: SourceSystemID
    Given that the following synonyms for "SourceSystemID" DO NOT exist in the "Office" metadata
      | MLSID |
    When "SourceSystemID" exists in the "Office" metadata
    Then "SourceSystemID" MUST be "String" data type
    And "SourceSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Office @IDX
  Scenario: SourceSystemName
    Given that the following synonyms for "SourceSystemName" DO NOT exist in the "Office" metadata
      | ProviderName |
      | MLSID |
    When "SourceSystemName" exists in the "Office" metadata
    Then "SourceSystemName" MUST be "String" data type
    And "SourceSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Office @IDX
  Scenario: SourceSystemOfficeKey
    Given that the following synonyms for "SourceSystemOfficeKey" DO NOT exist in the "Office" metadata
      | ProviderKey |
    When "SourceSystemOfficeKey" exists in the "Office" metadata
    Then "SourceSystemOfficeKey" MUST be "String" data type
    And "SourceSystemOfficeKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Office
  Scenario: SyndicateAgentOption
    Given that the following synonyms for "SyndicateAgentOption" DO NOT exist in the "Office" metadata
      | SyndicateMemberOption |
    When "SyndicateAgentOption" exists in the "Office" metadata
    Then "SyndicateAgentOption" MUST be "Single Enumeration" data type

  @Office
  Scenario: SyndicateTo
    When "SyndicateTo" exists in the "Office" metadata
    Then "SyndicateTo" MUST be "Multiple Enumeration" data type

  @Office
  Scenario: BillingOfficeKey
    When "BillingOfficeKey" exists in the "Office" metadata
    Then "BillingOfficeKey" MUST be "String" data type
    And "BillingOfficeKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Office
  Scenario: FranchiseNationalAssociationID
    When "FranchiseNationalAssociationID" exists in the "Office" metadata
    Then "FranchiseNationalAssociationID" MUST be "String" data type
    And "FranchiseNationalAssociationID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Office
  Scenario: NumberOfBranches
    When "NumberOfBranches" exists in the "Office" metadata
    Then "NumberOfBranches" MUST be "Integer" data type

  @Office
  Scenario: NumberOfNonMemberSalespersons
    When "NumberOfNonMemberSalespersons" exists in the "Office" metadata
    Then "NumberOfNonMemberSalespersons" MUST be "Integer" data type

  @Office
  Scenario: OfficeAlternateId
    When "OfficeAlternateId" exists in the "Office" metadata
    Then "OfficeAlternateId" MUST be "String" data type
    And "OfficeAlternateId" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Office
  Scenario: OfficeBrokerNationalAssociationID
    When "OfficeBrokerNationalAssociationID" exists in the "Office" metadata
    Then "OfficeBrokerNationalAssociationID" MUST be "String" data type
    And "OfficeBrokerNationalAssociationID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Office
  Scenario: OfficeCountry
    When "OfficeCountry" exists in the "Office" metadata
    Then "OfficeCountry" MUST be "String" data type
    And "OfficeCountry" length SHOULD be equal to the RESO Suggested Max Length of 2

  @Office
  Scenario: OfficeMailAddress1
    When "OfficeMailAddress1" exists in the "Office" metadata
    Then "OfficeMailAddress1" MUST be "String" data type
    And "OfficeMailAddress1" length SHOULD be equal to the RESO Suggested Max Length of 30

  @Office
  Scenario: OfficeMailAddress2
    When "OfficeMailAddress2" exists in the "Office" metadata
    Then "OfficeMailAddress2" MUST be "String" data type
    And "OfficeMailAddress2" length SHOULD be equal to the RESO Suggested Max Length of 30

  @Office
  Scenario: OfficeMailCareOf
    When "OfficeMailCareOf" exists in the "Office" metadata
    Then "OfficeMailCareOf" MUST be "String" data type
    And "OfficeMailCareOf" length SHOULD be equal to the RESO Suggested Max Length of 30

  @Office
  Scenario: OfficeMailCity
    When "OfficeMailCity" exists in the "Office" metadata
    Then "OfficeMailCity" MUST be "String" data type
    And "OfficeMailCity" length SHOULD be equal to the RESO Suggested Max Length of 21

  @Office
  Scenario: OfficeMailCountry
    When "OfficeMailCountry" exists in the "Office" metadata
    Then "OfficeMailCountry" MUST be "String" data type
    And "OfficeMailCountry" length SHOULD be equal to the RESO Suggested Max Length of 2

  @Office
  Scenario: OfficeMailCountyOrParish
    When "OfficeMailCountyOrParish" exists in the "Office" metadata
    Then "OfficeMailCountyOrParish" MUST be "String" data type
    And "OfficeMailCountyOrParish" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Office
  Scenario: OfficeMailPostalCode
    When "OfficeMailPostalCode" exists in the "Office" metadata
    Then "OfficeMailPostalCode" MUST be "String" data type
    And "OfficeMailPostalCode" length SHOULD be equal to the RESO Suggested Max Length of 5

  @Office
  Scenario: OfficeMailPostalCodePlus4
    When "OfficeMailPostalCodePlus4" exists in the "Office" metadata
    Then "OfficeMailPostalCodePlus4" MUST be "String" data type
    And "OfficeMailPostalCodePlus4" length SHOULD be equal to the RESO Suggested Max Length of 4

  @Office
  Scenario: OfficeMailStateOrProvince
    When "OfficeMailStateOrProvince" exists in the "Office" metadata
    Then "OfficeMailStateOrProvince" MUST be "String" data type
    And "OfficeMailStateOrProvince" length SHOULD be equal to the RESO Suggested Max Length of 2

  @Office
  Scenario: OfficeNationalAssociationIdInsertDate
    When "OfficeNationalAssociationIdInsertDate" exists in the "Office" metadata
    Then "OfficeNationalAssociationIdInsertDate" MUST be "Date" data type

  @Office
  Scenario: OfficePreferredMedia
    When "OfficePreferredMedia" exists in the "Office" metadata
    Then "OfficePreferredMedia" MUST be "Single Enumeration" data type

  @Office
  Scenario: OfficePrimaryAorId
    When "OfficePrimaryAorId" exists in the "Office" metadata
    Then "OfficePrimaryAorId" MUST be "String" data type
    And "OfficePrimaryAorId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Office
  Scenario: OfficePrimaryStateOrProvince
    When "OfficePrimaryStateOrProvince" exists in the "Office" metadata
    Then "OfficePrimaryStateOrProvince" MUST be "Single Enumeration" data type

  @Office
  Scenario: OtherPhone
    When "OtherPhone" exists in the "Office" metadata
    Then "OtherPhone" MUST be "String" data type
    And "OtherPhone" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Office
  Scenario: VirtualOfficeWebsiteYN
    When "VirtualOfficeWebsiteYN" exists in the "Office" metadata
    Then "VirtualOfficeWebsiteYN" MUST be "Boolean" data type
