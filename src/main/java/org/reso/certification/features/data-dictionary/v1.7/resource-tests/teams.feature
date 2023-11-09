# This file was autogenerated on: 2023110900245888
Feature: Teams

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

  @Teams @dd-1.7
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "Teams" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @Teams @dd-1.7
  Scenario: OriginalEntryTimestamp
    When "OriginalEntryTimestamp" exists in the "Teams" metadata
    Then "OriginalEntryTimestamp" MUST be "Timestamp" data type

  @Teams @dd-1.7
  Scenario: OriginatingSystemID
    When "OriginatingSystemID" exists in the "Teams" metadata
    Then "OriginatingSystemID" MUST be "String" data type
    And "OriginatingSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Teams @dd-1.7
  Scenario: OriginatingSystemKey
    When "OriginatingSystemKey" exists in the "Teams" metadata
    Then "OriginatingSystemKey" MUST be "String" data type
    And "OriginatingSystemKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Teams @dd-1.7
  Scenario: OriginatingSystemName
    Given that the following synonyms for "OriginatingSystemName" DO NOT exist in the "Teams" metadata
      | ProviderName |
      | MLSID |
    When "OriginatingSystemName" exists in the "Teams" metadata
    Then "OriginatingSystemName" MUST be "String" data type
    And "OriginatingSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Teams @dd-1.7
  Scenario: SocialMediaType
    When "SocialMediaType" exists in the "Teams" metadata
    Then "SocialMediaType" MUST be "Single Enumeration" data type

  @Teams @dd-1.7
  Scenario: SourceSystemID
    Given that the following synonyms for "SourceSystemID" DO NOT exist in the "Teams" metadata
      | MLSID |
    When "SourceSystemID" exists in the "Teams" metadata
    Then "SourceSystemID" MUST be "String" data type
    And "SourceSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Teams @dd-1.7
  Scenario: SourceSystemKey
    Given that the following synonyms for "SourceSystemKey" DO NOT exist in the "Teams" metadata
      | ProviderKey |
    When "SourceSystemKey" exists in the "Teams" metadata
    Then "SourceSystemKey" MUST be "String" data type
    And "SourceSystemKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Teams @dd-1.7
  Scenario: SourceSystemName
    Given that the following synonyms for "SourceSystemName" DO NOT exist in the "Teams" metadata
      | ProviderName |
      | MLSID |
    When "SourceSystemName" exists in the "Teams" metadata
    Then "SourceSystemName" MUST be "String" data type
    And "SourceSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Teams @dd-1.7
  Scenario: TeamAddress1
    When "TeamAddress1" exists in the "Teams" metadata
    Then "TeamAddress1" MUST be "String" data type
    And "TeamAddress1" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Teams @dd-1.7
  Scenario: TeamAddress2
    When "TeamAddress2" exists in the "Teams" metadata
    Then "TeamAddress2" MUST be "String" data type
    And "TeamAddress2" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Teams @dd-1.7
  Scenario: TeamCarrierRoute
    Given that the following synonyms for "TeamCarrierRoute" DO NOT exist in the "Teams" metadata
      | RR |
      | CR |
    When "TeamCarrierRoute" exists in the "Teams" metadata
    Then "TeamCarrierRoute" MUST be "String" data type
    And "TeamCarrierRoute" length SHOULD be equal to the RESO Suggested Max Length of 9

  @Teams @dd-1.7
  Scenario: TeamCity
    When "TeamCity" exists in the "Teams" metadata
    Then "TeamCity" MUST be "String" data type
    And "TeamCity" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Teams @dd-1.7
  Scenario: TeamCountry
    When "TeamCountry" exists in the "Teams" metadata
    Then "TeamCountry" MUST be "Single Enumeration" data type

  @Teams @dd-1.7
  Scenario: TeamCountyOrParish
    Given that the following synonyms for "TeamCountyOrParish" DO NOT exist in the "Teams" metadata
      | County |
    When "TeamCountyOrParish" exists in the "Teams" metadata
    Then "TeamCountyOrParish" MUST be "Single Enumeration" data type

  @Teams @dd-1.7
  Scenario: TeamDescription
    When "TeamDescription" exists in the "Teams" metadata
    Then "TeamDescription" MUST be "String" data type
    And "TeamDescription" length SHOULD be equal to the RESO Suggested Max Length of 1024

  @Teams @dd-1.7
  Scenario: TeamDirectPhone
    When "TeamDirectPhone" exists in the "Teams" metadata
    Then "TeamDirectPhone" MUST be "String" data type
    And "TeamDirectPhone" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Teams @dd-1.7
  Scenario: TeamEmail
    When "TeamEmail" exists in the "Teams" metadata
    Then "TeamEmail" MUST be "String" data type
    And "TeamEmail" length SHOULD be equal to the RESO Suggested Max Length of 80

  @Teams @dd-1.7
  Scenario: TeamFax
    When "TeamFax" exists in the "Teams" metadata
    Then "TeamFax" MUST be "String" data type
    And "TeamFax" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Teams @dd-1.7
  Scenario: TeamKey
    When "TeamKey" exists in the "Teams" metadata
    Then "TeamKey" MUST be "String" data type
    And "TeamKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Teams @dd-1.7
  Scenario: TeamLeadKey
    When "TeamLeadKey" exists in the "Teams" metadata
    Then "TeamLeadKey" MUST be "String" data type
    And "TeamLeadKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Teams @dd-1.7
  Scenario: TeamLeadLoginId
    When "TeamLeadLoginId" exists in the "Teams" metadata
    Then "TeamLeadLoginId" MUST be "String" data type
    And "TeamLeadLoginId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Teams @dd-1.7
  Scenario: TeamLeadMlsId
    When "TeamLeadMlsId" exists in the "Teams" metadata
    Then "TeamLeadMlsId" MUST be "String" data type
    And "TeamLeadMlsId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Teams @dd-1.7
  Scenario: TeamLeadNationalAssociationId
    When "TeamLeadNationalAssociationId" exists in the "Teams" metadata
    Then "TeamLeadNationalAssociationId" MUST be "String" data type
    And "TeamLeadNationalAssociationId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Teams @dd-1.7
  Scenario: TeamLeadStateLicense
    When "TeamLeadStateLicense" exists in the "Teams" metadata
    Then "TeamLeadStateLicense" MUST be "String" data type
    And "TeamLeadStateLicense" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Teams @dd-1.7
  Scenario: TeamLeadStateLicenseState
    When "TeamLeadStateLicenseState" exists in the "Teams" metadata
    Then "TeamLeadStateLicenseState" MUST be "Single Enumeration" data type

  @Teams @dd-1.7
  Scenario: TeamMobilePhone
    When "TeamMobilePhone" exists in the "Teams" metadata
    Then "TeamMobilePhone" MUST be "String" data type
    And "TeamMobilePhone" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Teams @dd-1.7
  Scenario: TeamName
    When "TeamName" exists in the "Teams" metadata
    Then "TeamName" MUST be "String" data type
    And "TeamName" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Teams @dd-1.7
  Scenario: TeamOfficePhone
    When "TeamOfficePhone" exists in the "Teams" metadata
    Then "TeamOfficePhone" MUST be "String" data type
    And "TeamOfficePhone" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Teams @dd-1.7
  Scenario: TeamOfficePhoneExt
    When "TeamOfficePhoneExt" exists in the "Teams" metadata
    Then "TeamOfficePhoneExt" MUST be "String" data type
    And "TeamOfficePhoneExt" length SHOULD be equal to the RESO Suggested Max Length of 10

  @Teams @dd-1.7
  Scenario: TeamPostalCode
    When "TeamPostalCode" exists in the "Teams" metadata
    Then "TeamPostalCode" MUST be "String" data type
    And "TeamPostalCode" length SHOULD be equal to the RESO Suggested Max Length of 10

  @Teams @dd-1.7
  Scenario: TeamPostalCodePlus4
    When "TeamPostalCodePlus4" exists in the "Teams" metadata
    Then "TeamPostalCodePlus4" MUST be "String" data type
    And "TeamPostalCodePlus4" length SHOULD be equal to the RESO Suggested Max Length of 4

  @Teams @dd-1.7
  Scenario: TeamPreferredPhone
    When "TeamPreferredPhone" exists in the "Teams" metadata
    Then "TeamPreferredPhone" MUST be "String" data type
    And "TeamPreferredPhone" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Teams @dd-1.7
  Scenario: TeamPreferredPhoneExt
    When "TeamPreferredPhoneExt" exists in the "Teams" metadata
    Then "TeamPreferredPhoneExt" MUST be "String" data type
    And "TeamPreferredPhoneExt" length SHOULD be equal to the RESO Suggested Max Length of 10

  @Teams @dd-1.7
  Scenario: TeamStateOrProvince
    When "TeamStateOrProvince" exists in the "Teams" metadata
    Then "TeamStateOrProvince" MUST be "Single Enumeration" data type

  @Teams @dd-1.7
  Scenario: TeamStatus
    When "TeamStatus" exists in the "Teams" metadata
    Then "TeamStatus" MUST be "Single Enumeration" data type

  @Teams @dd-1.7
  Scenario: TeamTollFreePhone
    When "TeamTollFreePhone" exists in the "Teams" metadata
    Then "TeamTollFreePhone" MUST be "String" data type
    And "TeamTollFreePhone" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Teams @dd-1.7
  Scenario: TeamVoiceMail
    When "TeamVoiceMail" exists in the "Teams" metadata
    Then "TeamVoiceMail" MUST be "String" data type
    And "TeamVoiceMail" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Teams @dd-1.7
  Scenario: TeamVoiceMailExt
    When "TeamVoiceMailExt" exists in the "Teams" metadata
    Then "TeamVoiceMailExt" MUST be "String" data type
    And "TeamVoiceMailExt" length SHOULD be equal to the RESO Suggested Max Length of 10
