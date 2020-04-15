Feature: Office

  Scenario: FranchiseAffiliation
    Given "FranchiseAffiliation" exists in the metadata
    Then "FranchiseAffiliation" should be "String" data type
    And "FranchiseAffiliation" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: IDXOfficeParticipationYN
    Given "IDXOfficeParticipationYN" exists in the metadata
    Then "IDXOfficeParticipationYN" should be "Boolean" data type

  Scenario: MainOfficeKey
    Given "MainOfficeKey" exists in the metadata
    Then "MainOfficeKey" should be "String" data type
    And "MainOfficeKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: MainOfficeKeyNumeric
    Given "MainOfficeKeyNumeric" exists in the metadata
    Then "MainOfficeKeyNumeric" should be "Integer" data type

  Scenario: MainOfficeMlsId
    Given "MainOfficeMlsId" exists in the metadata
    Then "MainOfficeMlsId" should be "String" data type
    And "MainOfficeMlsId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: ModificationTimestamp
    Given "ModificationTimestamp" exists in the metadata
    Then "ModificationTimestamp" should be "Timestamp" data type
    And "ModificationTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OfficeAOR
    Given "OfficeAOR" exists in the metadata
    And "OfficeAOR" enum values can be compiled
    Then "OfficeAOR" should be "String" data type
    And "OfficeAOR" should only contain enum values found in the metadata
    And "OfficeAOR" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OfficeAORMlsId
    Given "OfficeAORMlsId" exists in the metadata
    Then "OfficeAORMlsId" should be "String" data type
    And "OfficeAORMlsId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OfficeAORkey
    Given "OfficeAORkey" exists in the metadata
    Then "OfficeAORkey" should be "String" data type
    And "OfficeAORkey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OfficeAORkeyNumeric
    Given "OfficeAORkeyNumeric" exists in the metadata
    Then "OfficeAORkeyNumeric" should be "Integer" data type

  Scenario: OfficeAddress1
    Given "OfficeAddress1" exists in the metadata
    Then "OfficeAddress1" should be "String" data type
    And "OfficeAddress1" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OfficeAddress2
    Given "OfficeAddress2" exists in the metadata
    Then "OfficeAddress2" should be "String" data type
    And "OfficeAddress2" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OfficeAssociationComments
    Given "OfficeAssociationComments" exists in the metadata
    Then "OfficeAssociationComments" should be "String" data type
    And "OfficeAssociationComments" length should be less than or equal to the RESO Suggested Max Length of 500

  Scenario: OfficeBranchType
    Given "OfficeBranchType" exists in the metadata
    And "OfficeBranchType" enum values can be compiled
    Then "OfficeBranchType" should be "String" data type
    And "OfficeBranchType" should only contain enum values found in the metadata
    And "OfficeBranchType" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OfficeBrokerKey
    Given "OfficeBrokerKey" exists in the metadata
    Then "OfficeBrokerKey" should be "String" data type
    And "OfficeBrokerKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OfficeBrokerKeyNumeric
    Given "OfficeBrokerKeyNumeric" exists in the metadata
    Then "OfficeBrokerKeyNumeric" should be "Integer" data type

  Scenario: OfficeBrokerMlsId
    Given "OfficeBrokerMlsId" exists in the metadata
    Then "OfficeBrokerMlsId" should be "String" data type
    And "OfficeBrokerMlsId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OfficeCity
    Given "OfficeCity" exists in the metadata
    Then "OfficeCity" should be "String" data type
    And "OfficeCity" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OfficeCorporateLicense
    Given "OfficeCorporateLicense" exists in the metadata
    Then "OfficeCorporateLicense" should be "String" data type
    And "OfficeCorporateLicense" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OfficeCountyOrParish
    Given "OfficeCountyOrParish" exists in the metadata
    And "OfficeCountyOrParish" enum values can be compiled
    Then "OfficeCountyOrParish" should be "String" data type
    And "OfficeCountyOrParish" should only contain enum values found in the metadata
    And "OfficeCountyOrParish" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OfficeEmail
    Given "OfficeEmail" exists in the metadata
    Then "OfficeEmail" should be "String" data type
    And "OfficeEmail" length should be less than or equal to the RESO Suggested Max Length of 80

  Scenario: OfficeFax
    Given "OfficeFax" exists in the metadata
    Then "OfficeFax" should be "String" data type
    And "OfficeFax" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: OfficeKey
    Given "OfficeKey" exists in the metadata
    Then "OfficeKey" should be "String" data type
    And "OfficeKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OfficeKeyNumeric
    Given "OfficeKeyNumeric" exists in the metadata
    Then "OfficeKeyNumeric" should be "Integer" data type

  Scenario: OfficeManagerKey
    Given "OfficeManagerKey" exists in the metadata
    Then "OfficeManagerKey" should be "String" data type
    And "OfficeManagerKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OfficeManagerKeyNumeric
    Given "OfficeManagerKeyNumeric" exists in the metadata
    Then "OfficeManagerKeyNumeric" should be "Integer" data type

  Scenario: OfficeManagerMlsId
    Given "OfficeManagerMlsId" exists in the metadata
    Then "OfficeManagerMlsId" should be "String" data type
    And "OfficeManagerMlsId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OfficeMlsId
    Given "OfficeMlsId" exists in the metadata
    Then "OfficeMlsId" should be "String" data type
    And "OfficeMlsId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OfficeName
    Given "OfficeName" exists in the metadata
    Then "OfficeName" should be "String" data type
    And "OfficeName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OfficeNationalAssociationId
    Given "OfficeNationalAssociationId" exists in the metadata
    Then "OfficeNationalAssociationId" should be "String" data type
    And "OfficeNationalAssociationId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OfficePhone
    Given "OfficePhone" exists in the metadata
    Then "OfficePhone" should be "String" data type
    And "OfficePhone" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: OfficePhoneExt
    Given "OfficePhoneExt" exists in the metadata
    Then "OfficePhoneExt" should be "String" data type
    And "OfficePhoneExt" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: OfficePostalCode
    Given "OfficePostalCode" exists in the metadata
    Then "OfficePostalCode" should be "String" data type
    And "OfficePostalCode" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: OfficePostalCodePlus4
    Given "OfficePostalCodePlus4" exists in the metadata
    Then "OfficePostalCodePlus4" should be "String" data type
    And "OfficePostalCodePlus4" length should be less than or equal to the RESO Suggested Max Length of 4

  Scenario: OfficeStateOrProvince
    Given "OfficeStateOrProvince" exists in the metadata
    And "OfficeStateOrProvince" enum values can be compiled
    Then "OfficeStateOrProvince" should be "String" data type
    And "OfficeStateOrProvince" should only contain enum values found in the metadata
    And "OfficeStateOrProvince" length should be less than or equal to the RESO Suggested Max Length of 2

  Scenario: OfficeStatus
    Given "OfficeStatus" exists in the metadata
    And "OfficeStatus" enum values can be compiled
    Then "OfficeStatus" should be "String" data type
    And "OfficeStatus" should only contain enum values found in the metadata
    And "OfficeStatus" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OfficeType
    Given "OfficeType" exists in the metadata
    And "OfficeType" enum values can be compiled
    Then "OfficeType" should be "String" data type
    And "OfficeType" should only contain enum values found in the metadata
    And "OfficeType" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OriginalEntryTimestamp
    Given "OriginalEntryTimestamp" exists in the metadata
    Then "OriginalEntryTimestamp" should be "Timestamp" data type
    And "OriginalEntryTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OriginatingSystemID
    Given "OriginatingSystemID" exists in the metadata
    Then "OriginatingSystemID" should be "String" data type
    And "OriginatingSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OriginatingSystemName
    Given "OriginatingSystemName" exists in the metadata
    Then "OriginatingSystemName" should be "String" data type
    And "OriginatingSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OriginatingSystemOfficeKey
    Given "OriginatingSystemOfficeKey" exists in the metadata
    Then "OriginatingSystemOfficeKey" should be "String" data type
    And "OriginatingSystemOfficeKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SocialMediaType
    Given "SocialMediaType" exists in the metadata
    And "SocialMediaType" enum values can be compiled
    Then "SocialMediaType" should be "String" data type
    And "SocialMediaType" should only contain enum values found in the metadata
    And "SocialMediaType" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: SocialMedia[Type]UrlOrId
    Given "SocialMedia[Type]UrlOrId" exists in the metadata
    Then "SocialMedia[Type]UrlOrId" should be "String" data type
    And "SocialMedia[Type]UrlOrId" length should be less than or equal to the RESO Suggested Max Length of 8000

  Scenario: SourceSystemID
    Given "SourceSystemID" exists in the metadata
    Then "SourceSystemID" should be "String" data type
    And "SourceSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: SourceSystemName
    Given "SourceSystemName" exists in the metadata
    Then "SourceSystemName" should be "String" data type
    And "SourceSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SourceSystemOfficeKey
    Given "SourceSystemOfficeKey" exists in the metadata
    Then "SourceSystemOfficeKey" should be "String" data type
    And "SourceSystemOfficeKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SyndicateAgentOption
    Given "SyndicateAgentOption" exists in the metadata
    And "SyndicateAgentOption" enum values can be compiled
    Then "SyndicateAgentOption" should be "String" data type
    And "SyndicateAgentOption" should only contain enum values found in the metadata
    And "SyndicateAgentOption" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: SyndicateTo
    Given "SyndicateTo" exists in the metadata
    And "SyndicateTo" enum values can be compiled
    Then "SyndicateTo" should be "String Array" data type
    And "SyndicateTo" should only contain enum values found in the metadata
    And "SyndicateTo" length should be less than or equal to the RESO Suggested Max Length of 1024
