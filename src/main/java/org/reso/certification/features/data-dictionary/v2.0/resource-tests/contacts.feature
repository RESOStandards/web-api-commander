# This file was autogenerated on: 20240920124523269
Feature: Contacts

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

  @Contacts @dd-2.0
  Scenario: Anniversary
    When "Anniversary" exists in the "Contacts" metadata
    Then "Anniversary" MUST be "Date" data type

  @Contacts @dd-2.0
  Scenario: AssistantEmail
    When "AssistantEmail" exists in the "Contacts" metadata
    Then "AssistantEmail" MUST be "String" data type
    And "AssistantEmail" length SHOULD be equal to the RESO Suggested Max Length of 80

  @Contacts @dd-2.0
  Scenario: AssistantName
    When "AssistantName" exists in the "Contacts" metadata
    Then "AssistantName" MUST be "String" data type
    And "AssistantName" length SHOULD be equal to the RESO Suggested Max Length of 150

  @Contacts @dd-2.0
  Scenario: AssistantPhone
    When "AssistantPhone" exists in the "Contacts" metadata
    Then "AssistantPhone" MUST be "String" data type
    And "AssistantPhone" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Contacts @dd-2.0
  Scenario: AssistantPhoneExt
    When "AssistantPhoneExt" exists in the "Contacts" metadata
    Then "AssistantPhoneExt" MUST be "String" data type
    And "AssistantPhoneExt" length SHOULD be equal to the RESO Suggested Max Length of 10

  @Contacts @dd-2.0
  Scenario: Birthdate
    When "Birthdate" exists in the "Contacts" metadata
    Then "Birthdate" MUST be "Date" data type

  @Contacts @dd-2.0
  Scenario: BusinessFax
    When "BusinessFax" exists in the "Contacts" metadata
    Then "BusinessFax" MUST be "String" data type
    And "BusinessFax" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Contacts @dd-2.0
  Scenario: Children
    When "Children" exists in the "Contacts" metadata
    Then "Children" MUST be "String" data type
    And "Children" length SHOULD be equal to the RESO Suggested Max Length of 150

  @Contacts @dd-2.0
  Scenario: Company
    When "Company" exists in the "Contacts" metadata
    Then "Company" MUST be "String" data type
    And "Company" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Contacts @dd-2.0
  Scenario: ContactKey
    Given that the following synonyms for "ContactKey" DO NOT exist in the "Contacts" metadata
      | RID |
    When "ContactKey" exists in the "Contacts" metadata
    Then "ContactKey" MUST be "String" data type
    And "ContactKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Contacts @dd-2.0
  Scenario: ContactLoginId
    When "ContactLoginId" exists in the "Contacts" metadata
    Then "ContactLoginId" MUST be "String" data type
    And "ContactLoginId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Contacts @dd-2.0
  Scenario: ContactPassword
    When "ContactPassword" exists in the "Contacts" metadata
    Then "ContactPassword" MUST be "String" data type
    And "ContactPassword" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Contacts @dd-2.0
  Scenario: ContactStatus
    Given that the following synonyms for "ContactStatus" DO NOT exist in the "Contacts" metadata
      | ChoiceList |
    When "ContactStatus" exists in the "Contacts" metadata
    Then "ContactStatus" MUST be "Single Enumeration" data type

  @Contacts @dd-2.0
  Scenario: ContactType
    When "ContactType" exists in the "Contacts" metadata
    Then "ContactType" MUST be "Multiple Enumeration" data type

  @Contacts @dd-2.0
  Scenario: Department
    When "Department" exists in the "Contacts" metadata
    Then "Department" MUST be "String" data type
    And "Department" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Contacts @dd-2.0
  Scenario: DirectPhone
    When "DirectPhone" exists in the "Contacts" metadata
    Then "DirectPhone" MUST be "String" data type
    And "DirectPhone" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Contacts @dd-2.0
  Scenario: Email
    When "Email" exists in the "Contacts" metadata
    Then "Email" MUST be "String" data type
    And "Email" length SHOULD be equal to the RESO Suggested Max Length of 80

  @Contacts @dd-2.0
  Scenario: Email2
    When "Email2" exists in the "Contacts" metadata
    Then "Email2" MUST be "String" data type
    And "Email2" length SHOULD be equal to the RESO Suggested Max Length of 80

  @Contacts @dd-2.0
  Scenario: Email3
    When "Email3" exists in the "Contacts" metadata
    Then "Email3" MUST be "String" data type
    And "Email3" length SHOULD be equal to the RESO Suggested Max Length of 80

  @Contacts @dd-2.0
  Scenario: FirstName
    When "FirstName" exists in the "Contacts" metadata
    Then "FirstName" MUST be "String" data type
    And "FirstName" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Contacts @dd-2.0
  Scenario: FullName
    When "FullName" exists in the "Contacts" metadata
    Then "FullName" MUST be "String" data type
    And "FullName" length SHOULD be equal to the RESO Suggested Max Length of 150

  @Contacts @dd-2.0
  Scenario: HomeAddress1
    When "HomeAddress1" exists in the "Contacts" metadata
    Then "HomeAddress1" MUST be "String" data type
    And "HomeAddress1" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Contacts @dd-2.0
  Scenario: HomeAddress2
    When "HomeAddress2" exists in the "Contacts" metadata
    Then "HomeAddress2" MUST be "String" data type
    And "HomeAddress2" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Contacts @dd-2.0
  Scenario: HomeCarrierRoute
    Given that the following synonyms for "HomeCarrierRoute" DO NOT exist in the "Contacts" metadata
      | RR |
      | CR |
    When "HomeCarrierRoute" exists in the "Contacts" metadata
    Then "HomeCarrierRoute" MUST be "String" data type
    And "HomeCarrierRoute" length SHOULD be equal to the RESO Suggested Max Length of 9

  @Contacts @dd-2.0
  Scenario: HomeCity
    When "HomeCity" exists in the "Contacts" metadata
    Then "HomeCity" MUST be "String" data type
    And "HomeCity" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Contacts @dd-2.0
  Scenario: HomeCountry
    When "HomeCountry" exists in the "Contacts" metadata
    Then "HomeCountry" MUST be "Single Enumeration" data type

  @Contacts @dd-2.0
  Scenario: HomeCountyOrParish
    Given that the following synonyms for "HomeCountyOrParish" DO NOT exist in the "Contacts" metadata
      | County |
    When "HomeCountyOrParish" exists in the "Contacts" metadata
    Then "HomeCountyOrParish" MUST be "Single Enumeration" data type

  @Contacts @dd-2.0
  Scenario: HomeFax
    When "HomeFax" exists in the "Contacts" metadata
    Then "HomeFax" MUST be "String" data type
    And "HomeFax" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Contacts @dd-2.0
  Scenario: HomePhone
    When "HomePhone" exists in the "Contacts" metadata
    Then "HomePhone" MUST be "String" data type
    And "HomePhone" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Contacts @dd-2.0
  Scenario: HomePostalCode
    When "HomePostalCode" exists in the "Contacts" metadata
    Then "HomePostalCode" MUST be "String" data type
    And "HomePostalCode" length SHOULD be equal to the RESO Suggested Max Length of 10

  @Contacts @dd-2.0
  Scenario: HomePostalCodePlus4
    When "HomePostalCodePlus4" exists in the "Contacts" metadata
    Then "HomePostalCodePlus4" MUST be "String" data type
    And "HomePostalCodePlus4" length SHOULD be equal to the RESO Suggested Max Length of 4

  @Contacts @dd-2.0
  Scenario: HomeStateOrProvince
    When "HomeStateOrProvince" exists in the "Contacts" metadata
    Then "HomeStateOrProvince" MUST be "Single Enumeration" data type

  @Contacts @dd-2.0
  Scenario: JobTitle
    When "JobTitle" exists in the "Contacts" metadata
    Then "JobTitle" MUST be "String" data type
    And "JobTitle" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Contacts @dd-2.0
  Scenario: Language
    When "Language" exists in the "Contacts" metadata
    Then "Language" MUST be "Multiple Enumeration" data type

  @Contacts @dd-2.0
  Scenario: LastName
    When "LastName" exists in the "Contacts" metadata
    Then "LastName" MUST be "String" data type
    And "LastName" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Contacts @dd-2.0
  Scenario: LeadSource
    When "LeadSource" exists in the "Contacts" metadata
    Then "LeadSource" MUST be "String" data type
    And "LeadSource" length SHOULD be equal to the RESO Suggested Max Length of 150

  @Contacts @dd-2.0
  Scenario: MiddleName
    When "MiddleName" exists in the "Contacts" metadata
    Then "MiddleName" MUST be "String" data type
    And "MiddleName" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Contacts @dd-2.0
  Scenario: MobilePhone
    When "MobilePhone" exists in the "Contacts" metadata
    Then "MobilePhone" MUST be "String" data type
    And "MobilePhone" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Contacts @dd-2.0
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "Contacts" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @Contacts @dd-2.0
  Scenario: NamePrefix
    Given that the following synonyms for "NamePrefix" DO NOT exist in the "Contacts" metadata
      | Salutation |
      | Title |
    When "NamePrefix" exists in the "Contacts" metadata
    Then "NamePrefix" MUST be "String" data type
    And "NamePrefix" length SHOULD be equal to the RESO Suggested Max Length of 10

  @Contacts @dd-2.0
  Scenario: NameSuffix
    When "NameSuffix" exists in the "Contacts" metadata
    Then "NameSuffix" MUST be "String" data type
    And "NameSuffix" length SHOULD be equal to the RESO Suggested Max Length of 10

  @Contacts @dd-2.0
  Scenario: Nickname
    When "Nickname" exists in the "Contacts" metadata
    Then "Nickname" MUST be "String" data type
    And "Nickname" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Contacts @dd-2.0
  Scenario: Notes
    When "Notes" exists in the "Contacts" metadata
    Then "Notes" MUST be "String" data type
    And "Notes" length SHOULD be equal to the RESO Suggested Max Length of 1024

  @Contacts @dd-2.0
  Scenario: OfficePhone
    When "OfficePhone" exists in the "Contacts" metadata
    Then "OfficePhone" MUST be "String" data type
    And "OfficePhone" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Contacts @dd-2.0
  Scenario: OfficePhoneExt
    When "OfficePhoneExt" exists in the "Contacts" metadata
    Then "OfficePhoneExt" MUST be "String" data type
    And "OfficePhoneExt" length SHOULD be equal to the RESO Suggested Max Length of 10

  @Contacts @dd-2.0
  Scenario: OriginalEntryTimestamp
    When "OriginalEntryTimestamp" exists in the "Contacts" metadata
    Then "OriginalEntryTimestamp" MUST be "Timestamp" data type

  @Contacts @dd-2.0
  Scenario: OriginatingSystemContactKey
    Given that the following synonyms for "OriginatingSystemContactKey" DO NOT exist in the "Contacts" metadata
      | ProviderKey |
    When "OriginatingSystemContactKey" exists in the "Contacts" metadata
    Then "OriginatingSystemContactKey" MUST be "String" data type
    And "OriginatingSystemContactKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Contacts @dd-2.0
  Scenario: OriginatingSystemID
    When "OriginatingSystemID" exists in the "Contacts" metadata
    Then "OriginatingSystemID" MUST be "String" data type
    And "OriginatingSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Contacts @dd-2.0
  Scenario: OriginatingSystemName
    Given that the following synonyms for "OriginatingSystemName" DO NOT exist in the "Contacts" metadata
      | ProviderName |
      | MLSID |
    When "OriginatingSystemName" exists in the "Contacts" metadata
    Then "OriginatingSystemName" MUST be "String" data type
    And "OriginatingSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Contacts @dd-2.0
  Scenario: OtherAddress1
    When "OtherAddress1" exists in the "Contacts" metadata
    Then "OtherAddress1" MUST be "String" data type
    And "OtherAddress1" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Contacts @dd-2.0
  Scenario: OtherAddress2
    When "OtherAddress2" exists in the "Contacts" metadata
    Then "OtherAddress2" MUST be "String" data type
    And "OtherAddress2" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Contacts @dd-2.0
  Scenario: OtherCarrierRoute
    Given that the following synonyms for "OtherCarrierRoute" DO NOT exist in the "Contacts" metadata
      | RR |
      | CR |
    When "OtherCarrierRoute" exists in the "Contacts" metadata
    Then "OtherCarrierRoute" MUST be "String" data type
    And "OtherCarrierRoute" length SHOULD be equal to the RESO Suggested Max Length of 9

  @Contacts @dd-2.0
  Scenario: OtherCity
    When "OtherCity" exists in the "Contacts" metadata
    Then "OtherCity" MUST be "String" data type
    And "OtherCity" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Contacts @dd-2.0
  Scenario: OtherCountry
    When "OtherCountry" exists in the "Contacts" metadata
    Then "OtherCountry" MUST be "Single Enumeration" data type

  @Contacts @dd-2.0
  Scenario: OtherCountyOrParish
    Given that the following synonyms for "OtherCountyOrParish" DO NOT exist in the "Contacts" metadata
      | County |
    When "OtherCountyOrParish" exists in the "Contacts" metadata
    Then "OtherCountyOrParish" MUST be "Single Enumeration" data type

  @Contacts @dd-2.0
  Scenario: OtherPhoneType
    When "OtherPhoneType" exists in the "Contacts" metadata
    Then "OtherPhoneType" MUST be "Single Enumeration" data type

  @Contacts @dd-2.0
  Scenario: OtherPostalCode
    When "OtherPostalCode" exists in the "Contacts" metadata
    Then "OtherPostalCode" MUST be "String" data type
    And "OtherPostalCode" length SHOULD be equal to the RESO Suggested Max Length of 10

  @Contacts @dd-2.0
  Scenario: OtherPostalCodePlus4
    When "OtherPostalCodePlus4" exists in the "Contacts" metadata
    Then "OtherPostalCodePlus4" MUST be "String" data type
    And "OtherPostalCodePlus4" length SHOULD be equal to the RESO Suggested Max Length of 4

  @Contacts @dd-2.0
  Scenario: OtherStateOrProvince
    When "OtherStateOrProvince" exists in the "Contacts" metadata
    Then "OtherStateOrProvince" MUST be "Single Enumeration" data type

  @Contacts @dd-2.0
  Scenario: OwnerMemberID
    Given that the following synonyms for "OwnerMemberID" DO NOT exist in the "Contacts" metadata
      | OwnerAgentID |
    When "OwnerMemberID" exists in the "Contacts" metadata
    Then "OwnerMemberID" MUST be "String" data type
    And "OwnerMemberID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Contacts @dd-2.0
  Scenario: OwnerMemberKey
    Given that the following synonyms for "OwnerMemberKey" DO NOT exist in the "Contacts" metadata
      | OwnerAgentKey |
    When "OwnerMemberKey" exists in the "Contacts" metadata
    Then "OwnerMemberKey" MUST be "String" data type
    And "OwnerMemberKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Contacts @dd-2.0
  Scenario: Pager
    When "Pager" exists in the "Contacts" metadata
    Then "Pager" MUST be "String" data type
    And "Pager" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Contacts @dd-2.0
  Scenario: PhoneTTYTDD
    When "PhoneTTYTDD" exists in the "Contacts" metadata
    Then "PhoneTTYTDD" MUST be "String" data type
    And "PhoneTTYTDD" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Contacts @dd-2.0
  Scenario: PreferredAddress
    When "PreferredAddress" exists in the "Contacts" metadata
    Then "PreferredAddress" MUST be "Single Enumeration" data type

  @Contacts @dd-2.0
  Scenario: PreferredPhone
    When "PreferredPhone" exists in the "Contacts" metadata
    Then "PreferredPhone" MUST be "Single Enumeration" data type

  @Contacts @dd-2.0
  Scenario: ReferredBy
    When "ReferredBy" exists in the "Contacts" metadata
    Then "ReferredBy" MUST be "String" data type
    And "ReferredBy" length SHOULD be equal to the RESO Suggested Max Length of 150

  @Contacts @dd-2.0
  Scenario: ReverseProspectingEnabledYN
    When "ReverseProspectingEnabledYN" exists in the "Contacts" metadata
    Then "ReverseProspectingEnabledYN" MUST be "Boolean" data type

  @Contacts @dd-2.0
  Scenario: SocialMediaType
    When "SocialMediaType" exists in the "Contacts" metadata
    Then "SocialMediaType" MUST be "Single Enumeration" data type

  @Contacts @dd-2.0
  Scenario: SourceSystemContactKey
    Given that the following synonyms for "SourceSystemContactKey" DO NOT exist in the "Contacts" metadata
      | ProviderKey |
    When "SourceSystemContactKey" exists in the "Contacts" metadata
    Then "SourceSystemContactKey" MUST be "String" data type
    And "SourceSystemContactKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Contacts @dd-2.0
  Scenario: SourceSystemID
    Given that the following synonyms for "SourceSystemID" DO NOT exist in the "Contacts" metadata
      | MLSID |
    When "SourceSystemID" exists in the "Contacts" metadata
    Then "SourceSystemID" MUST be "String" data type
    And "SourceSystemID" length SHOULD be equal to the RESO Suggested Max Length of 25

  @Contacts @dd-2.0
  Scenario: SourceSystemName
    Given that the following synonyms for "SourceSystemName" DO NOT exist in the "Contacts" metadata
      | ProviderName |
      | MLSID |
    When "SourceSystemName" exists in the "Contacts" metadata
    Then "SourceSystemName" MUST be "String" data type
    And "SourceSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @Contacts @dd-2.0
  Scenario: SpousePartnerName
    When "SpousePartnerName" exists in the "Contacts" metadata
    Then "SpousePartnerName" MUST be "String" data type
    And "SpousePartnerName" length SHOULD be equal to the RESO Suggested Max Length of 150

  @Contacts @dd-2.0
  Scenario: TollFreePhone
    When "TollFreePhone" exists in the "Contacts" metadata
    Then "TollFreePhone" MUST be "String" data type
    And "TollFreePhone" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Contacts @dd-2.0
  Scenario: VoiceMail
    When "VoiceMail" exists in the "Contacts" metadata
    Then "VoiceMail" MUST be "String" data type
    And "VoiceMail" length SHOULD be equal to the RESO Suggested Max Length of 16

  @Contacts @dd-2.0
  Scenario: VoiceMailExt
    When "VoiceMailExt" exists in the "Contacts" metadata
    Then "VoiceMailExt" MUST be "String" data type
    And "VoiceMailExt" length SHOULD be equal to the RESO Suggested Max Length of 10

  @Contacts @dd-2.0
  Scenario: WorkAddress1
    When "WorkAddress1" exists in the "Contacts" metadata
    Then "WorkAddress1" MUST be "String" data type
    And "WorkAddress1" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Contacts @dd-2.0
  Scenario: WorkAddress2
    When "WorkAddress2" exists in the "Contacts" metadata
    Then "WorkAddress2" MUST be "String" data type
    And "WorkAddress2" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Contacts @dd-2.0
  Scenario: WorkCarrierRoute
    Given that the following synonyms for "WorkCarrierRoute" DO NOT exist in the "Contacts" metadata
      | RR |
      | CR |
    When "WorkCarrierRoute" exists in the "Contacts" metadata
    Then "WorkCarrierRoute" MUST be "String" data type
    And "WorkCarrierRoute" length SHOULD be equal to the RESO Suggested Max Length of 9

  @Contacts @dd-2.0
  Scenario: WorkCity
    When "WorkCity" exists in the "Contacts" metadata
    Then "WorkCity" MUST be "String" data type
    And "WorkCity" length SHOULD be equal to the RESO Suggested Max Length of 50

  @Contacts @dd-2.0
  Scenario: WorkCountry
    When "WorkCountry" exists in the "Contacts" metadata
    Then "WorkCountry" MUST be "Single Enumeration" data type

  @Contacts @dd-2.0
  Scenario: WorkCountyOrParish
    Given that the following synonyms for "WorkCountyOrParish" DO NOT exist in the "Contacts" metadata
      | County |
    When "WorkCountyOrParish" exists in the "Contacts" metadata
    Then "WorkCountyOrParish" MUST be "Single Enumeration" data type

  @Contacts @dd-2.0
  Scenario: WorkPostalCode
    When "WorkPostalCode" exists in the "Contacts" metadata
    Then "WorkPostalCode" MUST be "String" data type
    And "WorkPostalCode" length SHOULD be equal to the RESO Suggested Max Length of 10

  @Contacts @dd-2.0
  Scenario: WorkPostalCodePlus4
    When "WorkPostalCodePlus4" exists in the "Contacts" metadata
    Then "WorkPostalCodePlus4" MUST be "String" data type
    And "WorkPostalCodePlus4" length SHOULD be equal to the RESO Suggested Max Length of 4

  @Contacts @dd-2.0
  Scenario: WorkStateOrProvince
    When "WorkStateOrProvince" exists in the "Contacts" metadata
    Then "WorkStateOrProvince" MUST be "Single Enumeration" data type
