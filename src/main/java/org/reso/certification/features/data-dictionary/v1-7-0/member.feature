Feature: Member

  Scenario: JobTitle
    Given "JobTitle" exists in the metadata
    Then "JobTitle" should be "String" data type
    And "JobTitle" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: LastLoginTimestamp
    Given "LastLoginTimestamp" exists in the metadata
    Then "LastLoginTimestamp" should be "Timestamp" data type
    And "LastLoginTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: MemberAOR
    Given "MemberAOR" exists in the metadata
    And "MemberAOR" enum values can be compiled
    Then "MemberAOR" should be "String" data type
    And "MemberAOR" should only contain enum values found in the metadata
    And "MemberAOR" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: MemberAORMlsId
    Given "MemberAORMlsId" exists in the metadata
    Then "MemberAORMlsId" should be "String" data type
    And "MemberAORMlsId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: MemberAORkey
    Given "MemberAORkey" exists in the metadata
    Then "MemberAORkey" should be "String" data type
    And "MemberAORkey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: MemberAORkeyNumeric
    Given "MemberAORkeyNumeric" exists in the metadata
    Then "MemberAORkeyNumeric" should be "Integer" data type

  Scenario: MemberAddress1
    Given "MemberAddress1" exists in the metadata
    Then "MemberAddress1" should be "String" data type
    And "MemberAddress1" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: MemberAddress2
    Given "MemberAddress2" exists in the metadata
    Then "MemberAddress2" should be "String" data type
    And "MemberAddress2" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: MemberAssociationComments
    Given "MemberAssociationComments" exists in the metadata
    Then "MemberAssociationComments" should be "String" data type
    And "MemberAssociationComments" length should be less than or equal to the RESO Suggested Max Length of 500

  Scenario: MemberCarrierRoute
    Given "MemberCarrierRoute" exists in the metadata
    Then "MemberCarrierRoute" should be "String" data type
    And "MemberCarrierRoute" length should be less than or equal to the RESO Suggested Max Length of 9

  Scenario: MemberCity
    Given "MemberCity" exists in the metadata
    Then "MemberCity" should be "String" data type
    And "MemberCity" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: MemberCountry
    Given "MemberCountry" exists in the metadata
    And "MemberCountry" enum values can be compiled
    Then "MemberCountry" should be "String" data type
    And "MemberCountry" should only contain enum values found in the metadata
    And "MemberCountry" length should be less than or equal to the RESO Suggested Max Length of 2

  Scenario: MemberCountyOrParish
    Given "MemberCountyOrParish" exists in the metadata
    And "MemberCountyOrParish" enum values can be compiled
    Then "MemberCountyOrParish" should be "String" data type
    And "MemberCountyOrParish" should only contain enum values found in the metadata
    And "MemberCountyOrParish" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: MemberDesignation
    Given "MemberDesignation" exists in the metadata
    And "MemberDesignation" enum values can be compiled
    Then "MemberDesignation" should be "String Array" data type
    And "MemberDesignation" should only contain enum values found in the metadata
    And "MemberDesignation" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: MemberDirectPhone
    Given "MemberDirectPhone" exists in the metadata
    Then "MemberDirectPhone" should be "String" data type
    And "MemberDirectPhone" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: MemberEmail
    Given "MemberEmail" exists in the metadata
    Then "MemberEmail" should be "String" data type
    And "MemberEmail" length should be less than or equal to the RESO Suggested Max Length of 80

  Scenario: MemberFax
    Given "MemberFax" exists in the metadata
    Then "MemberFax" should be "String" data type
    And "MemberFax" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: MemberFirstName
    Given "MemberFirstName" exists in the metadata
    Then "MemberFirstName" should be "String" data type
    And "MemberFirstName" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: MemberFullName
    Given "MemberFullName" exists in the metadata
    Then "MemberFullName" should be "String" data type
    And "MemberFullName" length should be less than or equal to the RESO Suggested Max Length of 150

  Scenario: MemberHomePhone
    Given "MemberHomePhone" exists in the metadata
    Then "MemberHomePhone" should be "String" data type
    And "MemberHomePhone" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: MemberIsAssistantTo
    Given "MemberIsAssistantTo" exists in the metadata
    Then "MemberIsAssistantTo" should be "String" data type
    And "MemberIsAssistantTo" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: MemberKey
    Given "MemberKey" exists in the metadata
    Then "MemberKey" should be "String" data type
    And "MemberKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: MemberKeyNumeric
    Given "MemberKeyNumeric" exists in the metadata
    Then "MemberKeyNumeric" should be "Integer" data type

  Scenario: MemberLanguages
    Given "MemberLanguages" exists in the metadata
    And "MemberLanguages" enum values can be compiled
    Then "MemberLanguages" should be "String Array" data type
    And "MemberLanguages" should only contain enum values found in the metadata
    And "MemberLanguages" length should be less than or equal to the RESO Suggested Max Length of 1024

  Scenario: MemberLastName
    Given "MemberLastName" exists in the metadata
    Then "MemberLastName" should be "String" data type
    And "MemberLastName" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: MemberLoginId
    Given "MemberLoginId" exists in the metadata
    Then "MemberLoginId" should be "String" data type
    And "MemberLoginId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: MemberMiddleName
    Given "MemberMiddleName" exists in the metadata
    Then "MemberMiddleName" should be "String" data type
    And "MemberMiddleName" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: MemberMlsAccessYN
    Given "MemberMlsAccessYN" exists in the metadata
    Then "MemberMlsAccessYN" should be "Boolean" data type

  Scenario: MemberMlsId
    Given "MemberMlsId" exists in the metadata
    Then "MemberMlsId" should be "String" data type
    And "MemberMlsId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: MemberMlsSecurityClass
    Given "MemberMlsSecurityClass" exists in the metadata
    And "MemberMlsSecurityClass" enum values can be compiled
    Then "MemberMlsSecurityClass" should be "String" data type
    And "MemberMlsSecurityClass" should only contain enum values found in the metadata
    And "MemberMlsSecurityClass" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: MemberMobilePhone
    Given "MemberMobilePhone" exists in the metadata
    Then "MemberMobilePhone" should be "String" data type
    And "MemberMobilePhone" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: MemberNamePrefix
    Given "MemberNamePrefix" exists in the metadata
    Then "MemberNamePrefix" should be "String" data type
    And "MemberNamePrefix" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: MemberNameSuffix
    Given "MemberNameSuffix" exists in the metadata
    Then "MemberNameSuffix" should be "String" data type
    And "MemberNameSuffix" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: MemberNationalAssociationId
    Given "MemberNationalAssociationId" exists in the metadata
    Then "MemberNationalAssociationId" should be "String" data type
    And "MemberNationalAssociationId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: MemberNickname
    Given "MemberNickname" exists in the metadata
    Then "MemberNickname" should be "String" data type
    And "MemberNickname" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: MemberOfficePhone
    Given "MemberOfficePhone" exists in the metadata
    Then "MemberOfficePhone" should be "String" data type
    And "MemberOfficePhone" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: MemberOfficePhoneExt
    Given "MemberOfficePhoneExt" exists in the metadata
    Then "MemberOfficePhoneExt" should be "String" data type
    And "MemberOfficePhoneExt" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: MemberOtherPhoneType
    Given "MemberOtherPhoneType" exists in the metadata
    And "MemberOtherPhoneType" enum values can be compiled
    Then "MemberOtherPhoneType" should be "String" data type
    And "MemberOtherPhoneType" should only contain enum values found in the metadata
    And "MemberOtherPhoneType" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: MemberOtherPhone[Type]Ext
    Given "MemberOtherPhone[Type]Ext" exists in the metadata
    Then "MemberOtherPhone[Type]Ext" should be "String" data type
    And "MemberOtherPhone[Type]Ext" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: MemberOtherPhone[Type]Number
    Given "MemberOtherPhone[Type]Number" exists in the metadata
    Then "MemberOtherPhone[Type]Number" should be "String" data type
    And "MemberOtherPhone[Type]Number" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: MemberPager
    Given "MemberPager" exists in the metadata
    Then "MemberPager" should be "String" data type
    And "MemberPager" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: MemberPassword
    Given "MemberPassword" exists in the metadata
    Then "MemberPassword" should be "String" data type
    And "MemberPassword" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: MemberPhoneTTYTDD
    Given "MemberPhoneTTYTDD" exists in the metadata
    Then "MemberPhoneTTYTDD" should be "String" data type
    And "MemberPhoneTTYTDD" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: MemberPostalCode
    Given "MemberPostalCode" exists in the metadata
    Then "MemberPostalCode" should be "String" data type
    And "MemberPostalCode" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: MemberPostalCodePlus4
    Given "MemberPostalCodePlus4" exists in the metadata
    Then "MemberPostalCodePlus4" should be "String" data type
    And "MemberPostalCodePlus4" length should be less than or equal to the RESO Suggested Max Length of 4

  Scenario: MemberPreferredPhone
    Given "MemberPreferredPhone" exists in the metadata
    Then "MemberPreferredPhone" should be "String" data type
    And "MemberPreferredPhone" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: MemberPreferredPhoneExt
    Given "MemberPreferredPhoneExt" exists in the metadata
    Then "MemberPreferredPhoneExt" should be "String" data type
    And "MemberPreferredPhoneExt" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: MemberStateLicense
    Given "MemberStateLicense" exists in the metadata
    Then "MemberStateLicense" should be "String" data type
    And "MemberStateLicense" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: MemberStateLicenseState
    Given "MemberStateLicenseState" exists in the metadata
    And "MemberStateLicenseState" enum values can be compiled
    Then "MemberStateLicenseState" should be "String" data type
    And "MemberStateLicenseState" should only contain enum values found in the metadata
    And "MemberStateLicenseState" length should be less than or equal to the RESO Suggested Max Length of 2

  Scenario: MemberStateOrProvince
    Given "MemberStateOrProvince" exists in the metadata
    And "MemberStateOrProvince" enum values can be compiled
    Then "MemberStateOrProvince" should be "String" data type
    And "MemberStateOrProvince" should only contain enum values found in the metadata
    And "MemberStateOrProvince" length should be less than or equal to the RESO Suggested Max Length of 2

  Scenario: MemberStatus
    Given "MemberStatus" exists in the metadata
    And "MemberStatus" enum values can be compiled
    Then "MemberStatus" should be "String" data type
    And "MemberStatus" should only contain enum values found in the metadata
    And "MemberStatus" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: MemberTollFreePhone
    Given "MemberTollFreePhone" exists in the metadata
    Then "MemberTollFreePhone" should be "String" data type
    And "MemberTollFreePhone" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: MemberType
    Given "MemberType" exists in the metadata
    And "MemberType" enum values can be compiled
    Then "MemberType" should be "String" data type
    And "MemberType" should only contain enum values found in the metadata
    And "MemberType" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: MemberVoiceMail
    Given "MemberVoiceMail" exists in the metadata
    Then "MemberVoiceMail" should be "String" data type
    And "MemberVoiceMail" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: MemberVoiceMailExt
    Given "MemberVoiceMailExt" exists in the metadata
    Then "MemberVoiceMailExt" should be "String" data type
    And "MemberVoiceMailExt" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: ModificationTimestamp
    Given "ModificationTimestamp" exists in the metadata
    Then "ModificationTimestamp" should be "Timestamp" data type
    And "ModificationTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OfficeKey
    Given "OfficeKey" exists in the metadata
    Then "OfficeKey" should be "String" data type
    And "OfficeKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OfficeKeyNumeric
    Given "OfficeKeyNumeric" exists in the metadata
    Then "OfficeKeyNumeric" should be "Integer" data type

  Scenario: OfficeMlsId
    Given "OfficeMlsId" exists in the metadata
    Then "OfficeMlsId" should be "String" data type
    And "OfficeMlsId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OfficeName
    Given "OfficeName" exists in the metadata
    Then "OfficeName" should be "String" data type
    And "OfficeName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OriginalEntryTimestamp
    Given "OriginalEntryTimestamp" exists in the metadata
    Then "OriginalEntryTimestamp" should be "Timestamp" data type
    And "OriginalEntryTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OriginatingSystemID
    Given "OriginatingSystemID" exists in the metadata
    Then "OriginatingSystemID" should be "String" data type
    And "OriginatingSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OriginatingSystemMemberKey
    Given "OriginatingSystemMemberKey" exists in the metadata
    Then "OriginatingSystemMemberKey" should be "String" data type
    And "OriginatingSystemMemberKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OriginatingSystemName
    Given "OriginatingSystemName" exists in the metadata
    Then "OriginatingSystemName" should be "String" data type
    And "OriginatingSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

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

  Scenario: SourceSystemMemberKey
    Given "SourceSystemMemberKey" exists in the metadata
    Then "SourceSystemMemberKey" should be "String" data type
    And "SourceSystemMemberKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SourceSystemName
    Given "SourceSystemName" exists in the metadata
    Then "SourceSystemName" should be "String" data type
    And "SourceSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SyndicateTo
    Given "SyndicateTo" exists in the metadata
    And "SyndicateTo" enum values can be compiled
    Then "SyndicateTo" should be "String Array" data type
    And "SyndicateTo" should only contain enum values found in the metadata
    And "SyndicateTo" length should be less than or equal to the RESO Suggested Max Length of 1024
