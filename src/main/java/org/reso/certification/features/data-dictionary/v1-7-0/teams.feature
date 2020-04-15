Feature: Teams

  Scenario: ModificationTimestamp
    Given "ModificationTimestamp" exists in the metadata
    Then "ModificationTimestamp" should be "Timestamp" data type
    And "ModificationTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OriginalEntryTimestamp
    Given "OriginalEntryTimestamp" exists in the metadata
    Then "OriginalEntryTimestamp" should be "Timestamp" data type
    And "OriginalEntryTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OriginatingSystemID
    Given "OriginatingSystemID" exists in the metadata
    Then "OriginatingSystemID" should be "String" data type
    And "OriginatingSystemID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OriginatingSystemKey
    Given "OriginatingSystemKey" exists in the metadata
    Then "OriginatingSystemKey" should be "String" data type
    And "OriginatingSystemKey" length should be less than or equal to the RESO Suggested Max Length of 255

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

  Scenario: SourceSystemKey
    Given "SourceSystemKey" exists in the metadata
    Then "SourceSystemKey" should be "String" data type
    And "SourceSystemKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SourceSystemName
    Given "SourceSystemName" exists in the metadata
    Then "SourceSystemName" should be "String" data type
    And "SourceSystemName" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: TeamAddress1
    Given "TeamAddress1" exists in the metadata
    Then "TeamAddress1" should be "String" data type
    And "TeamAddress1" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: TeamAddress2
    Given "TeamAddress2" exists in the metadata
    Then "TeamAddress2" should be "String" data type
    And "TeamAddress2" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: TeamCarrierRoute
    Given "TeamCarrierRoute" exists in the metadata
    Then "TeamCarrierRoute" should be "String" data type
    And "TeamCarrierRoute" length should be less than or equal to the RESO Suggested Max Length of 9

  Scenario: TeamCity
    Given "TeamCity" exists in the metadata
    Then "TeamCity" should be "String" data type
    And "TeamCity" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: TeamCountry
    Given "TeamCountry" exists in the metadata
    And "TeamCountry" enum values can be compiled
    Then "TeamCountry" should be "String" data type
    And "TeamCountry" should only contain enum values found in the metadata
    And "TeamCountry" length should be less than or equal to the RESO Suggested Max Length of 2

  Scenario: TeamCountyOrParish
    Given "TeamCountyOrParish" exists in the metadata
    And "TeamCountyOrParish" enum values can be compiled
    Then "TeamCountyOrParish" should be "String" data type
    And "TeamCountyOrParish" should only contain enum values found in the metadata
    And "TeamCountyOrParish" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: TeamDescription
    Given "TeamDescription" exists in the metadata
    Then "TeamDescription" should be "String" data type
    And "TeamDescription" length should be less than or equal to the RESO Suggested Max Length of 1024

  Scenario: TeamDirectPhone
    Given "TeamDirectPhone" exists in the metadata
    Then "TeamDirectPhone" should be "String" data type
    And "TeamDirectPhone" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: TeamEmail
    Given "TeamEmail" exists in the metadata
    Then "TeamEmail" should be "String" data type
    And "TeamEmail" length should be less than or equal to the RESO Suggested Max Length of 80

  Scenario: TeamFax
    Given "TeamFax" exists in the metadata
    Then "TeamFax" should be "String" data type
    And "TeamFax" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: TeamKey
    Given "TeamKey" exists in the metadata
    Then "TeamKey" should be "String" data type
    And "TeamKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: TeamKeyNumeric
    Given "TeamKeyNumeric" exists in the metadata
    Then "TeamKeyNumeric" should be "Integer" data type

  Scenario: TeamLeadKey
    Given "TeamLeadKey" exists in the metadata
    Then "TeamLeadKey" should be "String" data type
    And "TeamLeadKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: TeamLeadKeyNumeric
    Given "TeamLeadKeyNumeric" exists in the metadata
    Then "TeamLeadKeyNumeric" should be "Integer" data type

  Scenario: TeamLeadLoginId
    Given "TeamLeadLoginId" exists in the metadata
    Then "TeamLeadLoginId" should be "String" data type
    And "TeamLeadLoginId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: TeamLeadMlsId
    Given "TeamLeadMlsId" exists in the metadata
    Then "TeamLeadMlsId" should be "String" data type
    And "TeamLeadMlsId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: TeamLeadNationalAssociationId
    Given "TeamLeadNationalAssociationId" exists in the metadata
    Then "TeamLeadNationalAssociationId" should be "String" data type
    And "TeamLeadNationalAssociationId" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: TeamLeadStateLicense
    Given "TeamLeadStateLicense" exists in the metadata
    Then "TeamLeadStateLicense" should be "String" data type
    And "TeamLeadStateLicense" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: TeamLeadStateLicenseState
    Given "TeamLeadStateLicenseState" exists in the metadata
    And "TeamLeadStateLicenseState" enum values can be compiled
    Then "TeamLeadStateLicenseState" should be "String" data type
    And "TeamLeadStateLicenseState" should only contain enum values found in the metadata
    And "TeamLeadStateLicenseState" length should be less than or equal to the RESO Suggested Max Length of 2

  Scenario: TeamMobilePhone
    Given "TeamMobilePhone" exists in the metadata
    Then "TeamMobilePhone" should be "String" data type
    And "TeamMobilePhone" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: TeamName
    Given "TeamName" exists in the metadata
    Then "TeamName" should be "String" data type
    And "TeamName" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: TeamOfficePhone
    Given "TeamOfficePhone" exists in the metadata
    Then "TeamOfficePhone" should be "String" data type
    And "TeamOfficePhone" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: TeamOfficePhoneExt
    Given "TeamOfficePhoneExt" exists in the metadata
    Then "TeamOfficePhoneExt" should be "String" data type
    And "TeamOfficePhoneExt" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: TeamPostalCode
    Given "TeamPostalCode" exists in the metadata
    Then "TeamPostalCode" should be "String" data type
    And "TeamPostalCode" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: TeamPostalCodePlus4
    Given "TeamPostalCodePlus4" exists in the metadata
    Then "TeamPostalCodePlus4" should be "String" data type
    And "TeamPostalCodePlus4" length should be less than or equal to the RESO Suggested Max Length of 4

  Scenario: TeamPreferredPhone
    Given "TeamPreferredPhone" exists in the metadata
    Then "TeamPreferredPhone" should be "String" data type
    And "TeamPreferredPhone" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: TeamPreferredPhoneExt
    Given "TeamPreferredPhoneExt" exists in the metadata
    Then "TeamPreferredPhoneExt" should be "String" data type
    And "TeamPreferredPhoneExt" length should be less than or equal to the RESO Suggested Max Length of 10

  Scenario: TeamStateOrProvince
    Given "TeamStateOrProvince" exists in the metadata
    And "TeamStateOrProvince" enum values can be compiled
    Then "TeamStateOrProvince" should be "String" data type
    And "TeamStateOrProvince" should only contain enum values found in the metadata
    And "TeamStateOrProvince" length should be less than or equal to the RESO Suggested Max Length of 2

  Scenario: TeamStatus
    Given "TeamStatus" exists in the metadata
    And "TeamStatus" enum values can be compiled
    Then "TeamStatus" should be "String" data type
    And "TeamStatus" should only contain enum values found in the metadata
    And "TeamStatus" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: TeamTollFreePhone
    Given "TeamTollFreePhone" exists in the metadata
    Then "TeamTollFreePhone" should be "String" data type
    And "TeamTollFreePhone" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: TeamVoiceMail
    Given "TeamVoiceMail" exists in the metadata
    Then "TeamVoiceMail" should be "String" data type
    And "TeamVoiceMail" length should be less than or equal to the RESO Suggested Max Length of 16

  Scenario: TeamVoiceMailExt
    Given "TeamVoiceMailExt" exists in the metadata
    Then "TeamVoiceMailExt" should be "String" data type
    And "TeamVoiceMailExt" length should be less than or equal to the RESO Suggested Max Length of 10
