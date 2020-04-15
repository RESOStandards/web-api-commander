Feature: OUID

  Scenario: ChangedByMemberID
    Given "ChangedByMemberID" exists in the metadata
    Then "ChangedByMemberID" should be "String" data type
    And "ChangedByMemberID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: ChangedByMemberKey
    Given "ChangedByMemberKey" exists in the metadata
    Then "ChangedByMemberKey" should be "String" data type
    And "ChangedByMemberKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ChangedByMemberKeyNumeric
    Given "ChangedByMemberKeyNumeric" exists in the metadata
    Then "ChangedByMemberKeyNumeric" should be "Integer" data type

  Scenario: ModificationTimestamp
    Given "ModificationTimestamp" exists in the metadata
    Then "ModificationTimestamp" should be "Timestamp" data type
    And "ModificationTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OrganizationAOR
    Given "OrganizationAOR" exists in the metadata
    And "OrganizationAOR" enum values can be compiled
    Then "OrganizationAOR" should be "String" data type
    And "OrganizationAOR" should only contain enum values found in the metadata
    And "OrganizationAOR" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OrganizationAddress1
    Given "OrganizationAddress1" exists in the metadata
    Then "OrganizationAddress1" should be "String" data type
    And "OrganizationAddress1" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OrganizationAddress2
    Given "OrganizationAddress2" exists in the metadata
    Then "OrganizationAddress2" should be "String" data type
    And "OrganizationAddress2" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OrganizationAorOuid
    Given "OrganizationAorOuid" exists in the metadata
    Then "OrganizationAorOuid" should be "String" data type
    And "OrganizationAorOuid" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OrganizationAorOuidKey
    Given "OrganizationAorOuidKey" exists in the metadata
    Then "OrganizationAorOuidKey" should be "String" data type
    And "OrganizationAorOuidKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OrganizationAorOuidKeyNumeric
    Given "OrganizationAorOuidKeyNumeric" exists in the metadata
    Then "OrganizationAorOuidKeyNumeric" should be "Integer" data type

  Scenario: OrganizationCarrierRoute
    Given "OrganizationCarrierRoute" exists in the metadata
    Then "OrganizationCarrierRoute" should be "String" data type
    And "OrganizationCarrierRoute" length should be less than or equal to the RESO Suggested Max Length of 9

  Scenario: OrganizationCity
    Given "OrganizationCity" exists in the metadata
    Then "OrganizationCity" should be "String" data type
    And "OrganizationCity" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OrganizationComments
    Given "OrganizationComments" exists in the metadata
    Then "OrganizationComments" should be "String" data type
    And "OrganizationComments" length should be less than or equal to the RESO Suggested Max Length of 500

  Scenario: OrganizationContactEmail
    Given "OrganizationContactEmail" exists in the metadata
    Then "OrganizationContactEmail" should be "String" data type
    And "OrganizationContactEmail" length should be less than or equal to the RESO Suggested Max Length of 80

  Scenario: OrganizationContactFax
    Given "OrganizationContactFax" exists in the metadata
    Then "OrganizationContactFax" should be "String" data type
    And "OrganizationContactFax" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: OrganizationContactFirstName
    Given "OrganizationContactFirstName" exists in the metadata
    Then "OrganizationContactFirstName" should be "String" data type
    And "OrganizationContactFirstName" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OrganizationContactFullName
    Given "OrganizationContactFullName" exists in the metadata
    Then "OrganizationContactFullName" should be "String" data type
    And "OrganizationContactFullName" length should be less than or equal to the RESO Suggested Max Length of 150

  Scenario: OrganizationContactJobTitle
    Given "OrganizationContactJobTitle" exists in the metadata
    Then "OrganizationContactJobTitle" should be "String" data type
    And "OrganizationContactJobTitle" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OrganizationContactLastName
    Given "OrganizationContactLastName" exists in the metadata
    Then "OrganizationContactLastName" should be "String" data type
    And "OrganizationContactLastName" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OrganizationContactMiddleName
    Given "OrganizationContactMiddleName" exists in the metadata
    Then "OrganizationContactMiddleName" should be "String" data type
    And "OrganizationContactMiddleName" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OrganizationContactNamePrefix
    Given "OrganizationContactNamePrefix" exists in the metadata
    Then "OrganizationContactNamePrefix" should be "String" data type
    And "OrganizationContactNamePrefix" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: OrganizationContactNameSuffix
    Given "OrganizationContactNameSuffix" exists in the metadata
    Then "OrganizationContactNameSuffix" should be "String" data type
    And "OrganizationContactNameSuffix" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: OrganizationContactPhone
    Given "OrganizationContactPhone" exists in the metadata
    Then "OrganizationContactPhone" should be "String" data type
    And "OrganizationContactPhone" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: OrganizationContactPhoneExt
    Given "OrganizationContactPhoneExt" exists in the metadata
    Then "OrganizationContactPhoneExt" should be "String" data type
    And "OrganizationContactPhoneExt" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: OrganizationCountry
    Given "OrganizationCountry" exists in the metadata
    And "OrganizationCountry" enum values can be compiled
    Then "OrganizationCountry" should be "String" data type
    And "OrganizationCountry" should only contain enum values found in the metadata
    And "OrganizationCountry" length should be less than or equal to the RESO Suggested Max Length of 2

  Scenario: OrganizationCountyOrParish
    Given "OrganizationCountyOrParish" exists in the metadata
    And "OrganizationCountyOrParish" enum values can be compiled
    Then "OrganizationCountyOrParish" should be "String" data type
    And "OrganizationCountyOrParish" should only contain enum values found in the metadata
    And "OrganizationCountyOrParish" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OrganizationMemberCount
    Given "OrganizationMemberCount" exists in the metadata
    Then "OrganizationMemberCount" should be "Integer" data type

  Scenario: OrganizationMlsCode
    Given "OrganizationMlsCode" exists in the metadata
    Then "OrganizationMlsCode" should be "String" data type
    And "OrganizationMlsCode" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OrganizationMlsVendorName
    Given "OrganizationMlsVendorName" exists in the metadata
    Then "OrganizationMlsVendorName" should be "String" data type
    And "OrganizationMlsVendorName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OrganizationMlsVendorOuid
    Given "OrganizationMlsVendorOuid" exists in the metadata
    Then "OrganizationMlsVendorOuid" should be "String" data type
    And "OrganizationMlsVendorOuid" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OrganizationName
    Given "OrganizationName" exists in the metadata
    Then "OrganizationName" should be "String" data type
    And "OrganizationName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OrganizationNationalAssociationId
    Given "OrganizationNationalAssociationId" exists in the metadata
    Then "OrganizationNationalAssociationId" should be "String" data type
    And "OrganizationNationalAssociationId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OrganizationPostalCode
    Given "OrganizationPostalCode" exists in the metadata
    Then "OrganizationPostalCode" should be "String" data type
    And "OrganizationPostalCode" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: OrganizationPostalCodePlus4
    Given "OrganizationPostalCodePlus4" exists in the metadata
    Then "OrganizationPostalCodePlus4" should be "String" data type
    And "OrganizationPostalCodePlus4" length should be less than or equal to the RESO Suggested Max Length of 4

  Scenario: OrganizationSocialMediaType
    Given "OrganizationSocialMediaType" exists in the metadata
    And "OrganizationSocialMediaType" enum values can be compiled
    Then "OrganizationSocialMediaType" should be "String" data type
    And "OrganizationSocialMediaType" should only contain enum values found in the metadata
    And "OrganizationSocialMediaType" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OrganizationSocialMedia[Type]UrlOrId
    Given "OrganizationSocialMedia[Type]UrlOrId" exists in the metadata
    Then "OrganizationSocialMedia[Type]UrlOrId" should be "String" data type
    And "OrganizationSocialMedia[Type]UrlOrId" length should be less than or equal to the RESO Suggested Max Length of 8000

  Scenario: OrganizationStateLicense
    Given "OrganizationStateLicense" exists in the metadata
    Then "OrganizationStateLicense" should be "String" data type
    And "OrganizationStateLicense" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: OrganizationStateLicenseState
    Given "OrganizationStateLicenseState" exists in the metadata
    And "OrganizationStateLicenseState" enum values can be compiled
    Then "OrganizationStateLicenseState" should be "String" data type
    And "OrganizationStateLicenseState" should only contain enum values found in the metadata
    And "OrganizationStateLicenseState" length should be less than or equal to the RESO Suggested Max Length of 2

  Scenario: OrganizationStateOrProvince
    Given "OrganizationStateOrProvince" exists in the metadata
    And "OrganizationStateOrProvince" enum values can be compiled
    Then "OrganizationStateOrProvince" should be "String" data type
    And "OrganizationStateOrProvince" should only contain enum values found in the metadata
    And "OrganizationStateOrProvince" length should be less than or equal to the RESO Suggested Max Length of 2

  Scenario: OrganizationStatus
    Given "OrganizationStatus" exists in the metadata
    Then "OrganizationStatus" should be "Boolean" data type

  Scenario: OrganizationStatusChangeTimestamp
    Given "OrganizationStatusChangeTimestamp" exists in the metadata
    Then "OrganizationStatusChangeTimestamp" should be "Timestamp" data type
    And "OrganizationStatusChangeTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OrganizationType
    Given "OrganizationType" exists in the metadata
    And "OrganizationType" enum values can be compiled
    Then "OrganizationType" should be "String" data type
    And "OrganizationType" should only contain enum values found in the metadata
    And "OrganizationType" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OrganizationUniqueId
    Given "OrganizationUniqueId" exists in the metadata
    Then "OrganizationUniqueId" should be "String" data type
    And "OrganizationUniqueId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OrganizationUniqueIdKey
    Given "OrganizationUniqueIdKey" exists in the metadata
    Then "OrganizationUniqueIdKey" should be "String" data type
    And "OrganizationUniqueIdKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OrganizationUniqueIdKeyNumeric
    Given "OrganizationUniqueIdKeyNumeric" exists in the metadata
    Then "OrganizationUniqueIdKeyNumeric" should be "Integer" data type

  Scenario: OriginalEntryTimestamp
    Given "OriginalEntryTimestamp" exists in the metadata
    Then "OriginalEntryTimestamp" should be "Timestamp" data type
    And "OriginalEntryTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27
