Feature: String Testing

  Background:

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @AccessCode @DD1.5_AccessCode @DD1.6_AccessCode
  Scenario: AccessCode
    Given "AccessCode" exists in the metadata
    And "AccessCode" string values are not null
    Then "AccessCode" should be String data type
    And "AccessCode" length should be between the bounds in the metadata
    And "AccessCode" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @AdditionalParcelsDescription @DD1.5_AdditionalParcelsDescription @DD1.6_AdditionalParcelsDescription
  Scenario: AdditionalParcelsDescription
    Given "AdditionalParcelsDescription" exists in the metadata
    And "AdditionalParcelsDescription" string values are not null
    Then "AdditionalParcelsDescription" should be String data type
    And "AdditionalParcelsDescription" length should be between the bounds in the metadata
    And "AdditionalParcelsDescription" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @AnchorsCoTenants @DD1.5_AnchorsCoTenants @DD1.6_AnchorsCoTenants
  Scenario: AnchorsCoTenants
    Given "AnchorsCoTenants" exists in the metadata
    And "AnchorsCoTenants" string values are not null
    Then "AnchorsCoTenants" should be String data type
    And "AnchorsCoTenants" length should be between the bounds in the metadata
    And "AnchorsCoTenants" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @AssociationName @DD1.5_AssociationName @DD1.6_AssociationName
  Scenario: AssociationName
    Given "AssociationName" exists in the metadata
    And "AssociationName" string values are not null
    Then "AssociationName" should be String data type
    And "AssociationName" length should be between the bounds in the metadata
    And "AssociationName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @AssociationName2 @DD1.5_AssociationName @DD1.6_AssociationName2
  Scenario: AssociationName2
    Given "AssociationName2" exists in the metadata
    And "AssociationName2" string values are not null
    Then "AssociationName2" should be String data type
    And "AssociationName2" length should be between the bounds in the metadata
    And "AssociationName2" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @AssociationPhone @DD1.5_AssociationPhone @DD1.6_AssociationPhone
  Scenario: AssociationPhone
    Given "AssociationPhone" exists in the metadata
    And "AssociationPhone" string values are not null
    Then "AssociationPhone" should be String data type
    And "AssociationPhone" length should be between the bounds in the metadata
    And "AssociationPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @AssociationPhone2 @DD1.5_AssociationPhone @DD1.6_AssociationPhone2
  Scenario: AssociationPhone2
    Given "AssociationPhone2" exists in the metadata
    And "AssociationPhone2" string values are not null
    Then "AssociationPhone2" should be String data type
    And "AssociationPhone2" length should be between the bounds in the metadata
    And "AssociationPhone2" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuilderModel @DD1.5_BuilderModel @DD1.6_BuilderModel
  Scenario: BuilderModel
    Given "BuilderModel" exists in the metadata
    And "BuilderModel" string values are not null
    Then "BuilderModel" should be String data type
    And "BuilderModel" length should be between the bounds in the metadata
    And "BuilderModel" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuildingName @DD1.5_BuildingName @DD1.6_BuildingName
  Scenario: BuildingName
    Given "BuildingName" exists in the metadata
    And "BuildingName" string values are not null
    Then "BuildingName" should be String data type
    And "BuildingName" length should be between the bounds in the metadata
    And "BuildingName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BusinessName @DD1.5_BusinessName @DD1.6_BusinessName
  Scenario: BusinessName
    Given "BusinessName" exists in the metadata
    And "BusinessName" string values are not null
    Then "BusinessName" should be String data type
    And "BusinessName" length should be between the bounds in the metadata
    And "BusinessName" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgencyCompensation @DD1.5_BuyerAgencyCompensation @DD1.6_BuyerAgencyCompensation
  Scenario: BuyerAgencyCompensation
    Given "BuyerAgencyCompensation" exists in the metadata
    And "BuyerAgencyCompensation" string values are not null
    Then "BuyerAgencyCompensation" should be String data type
    And "BuyerAgencyCompensation" length should be between the bounds in the metadata
    And "BuyerAgencyCompensation" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentHomePhone @DD1.5_BuyerAgentHomePhone @DD1.6_BuyerAgentHomePhone
  Scenario: BuyerAgentHomePhone
    Given "BuyerAgentHomePhone" exists in the metadata
    And "BuyerAgentHomePhone" string values are not null
    Then "BuyerAgentHomePhone" should be String data type
    And "BuyerAgentHomePhone" length should be between the bounds in the metadata
    And "BuyerAgentHomePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentKey @DD1.5_BuyerAgentKey @DD1.6_BuyerAgentKey
  Scenario: BuyerAgentKey
    Given "BuyerAgentKey" exists in the metadata
    And "BuyerAgentKey" string values are not null
    Then "BuyerAgentKey" should be String data type
    And "BuyerAgentKey" length should be between the bounds in the metadata
    And "BuyerAgentKey" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentMlsId @IDX_Payload @DD1.5_BuyerAgentMlsId @DD1.6_BuyerAgentMlsId
  Scenario: BuyerAgentMlsId
    Given "BuyerAgentMlsId" exists in the metadata
    And "BuyerAgentMlsId" string values are not null
    Then "BuyerAgentMlsId" should be String data type
    And "BuyerAgentMlsId" length should be between the bounds in the metadata
    And "BuyerAgentMlsId" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentNamePrefix @DD1.5_BuyerAgentNamePrefix @DD1.6_BuyerAgentNamePrefix
  Scenario: BuyerAgentNamePrefix
    Given "BuyerAgentNamePrefix" exists in the metadata
    And "BuyerAgentNamePrefix" string values are not null
    Then "BuyerAgentNamePrefix" should be String data type
    And "BuyerAgentNamePrefix" length should be between the bounds in the metadata
    And "BuyerAgentNamePrefix" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentNameSuffix @DD1.5_BuyerAgentNameSuffix @DD1.6_BuyerAgentNameSuffix
  Scenario: BuyerAgentNameSuffix
    Given "BuyerAgentNameSuffix" exists in the metadata
    And "BuyerAgentNameSuffix" string values are not null
    Then "BuyerAgentNameSuffix" should be String data type
    And "BuyerAgentNameSuffix" length should be between the bounds in the metadata
    And "BuyerAgentNameSuffix" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentOfficePhoneExt @DD1.5_BuyerAgentOfficePhoneExt @DD1.6_BuyerAgentOfficePhoneExt
  Scenario: BuyerAgentOfficePhoneExt
    Given "BuyerAgentOfficePhoneExt" exists in the metadata
    And "BuyerAgentOfficePhoneExt" string values are not null
    Then "BuyerAgentOfficePhoneExt" should be String data type
    And "BuyerAgentOfficePhoneExt" length should be between the bounds in the metadata
    And "BuyerAgentOfficePhoneExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentPreferredPhoneExt @DD1.5_BuyerAgentPreferredPhoneExt @DD1.6_BuyerAgentPreferredPhoneExt
  Scenario: BuyerAgentPreferredPhoneExt
    Given "BuyerAgentPreferredPhoneExt" exists in the metadata
    And "BuyerAgentPreferredPhoneExt" string values are not null
    Then "BuyerAgentPreferredPhoneExt" should be String data type
    And "BuyerAgentPreferredPhoneExt" length should be between the bounds in the metadata
    And "BuyerAgentPreferredPhoneExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentVoiceMailExt @DD1.5_BuyerAgentVoiceMailExt @DD1.6_BuyerAgentVoiceMailExt
  Scenario: BuyerAgentVoiceMailExt
    Given "BuyerAgentVoiceMailExt" exists in the metadata
    And "BuyerAgentVoiceMailExt" string values are not null
    Then "BuyerAgentVoiceMailExt" should be String data type
    And "BuyerAgentVoiceMailExt" length should be between the bounds in the metadata
    And "BuyerAgentVoiceMailExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerOfficeKey @DD1.5_BuyerOfficeKey @DD1.6_BuyerOfficeKey
  Scenario: BuyerOfficeKey
    Given "BuyerOfficeKey" exists in the metadata
    And "BuyerOfficeKey" string values are not null
    Then "BuyerOfficeKey" should be String data type
    And "BuyerOfficeKey" length should be between the bounds in the metadata
    And "BuyerOfficeKey" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerOfficeMlsId @IDX_Payload @DD1.5_BuyerOfficeMlsId @DD1.6_BuyerOfficeMlsId
  Scenario: BuyerOfficeMlsId
    Given "BuyerOfficeMlsId" exists in the metadata
    And "BuyerOfficeMlsId" string values are not null
    Then "BuyerOfficeMlsId" should be String data type
    And "BuyerOfficeMlsId" length should be between the bounds in the metadata
    And "BuyerOfficeMlsId" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerOfficePhoneExt @DD1.5_BuyerOfficePhoneExt @DD1.6_BuyerOfficePhoneExt
  Scenario: BuyerOfficePhoneExt
    Given "BuyerOfficePhoneExt" exists in the metadata
    And "BuyerOfficePhoneExt" string values are not null
    Then "BuyerOfficePhoneExt" should be String data type
    And "BuyerOfficePhoneExt" length should be between the bounds in the metadata
    And "BuyerOfficePhoneExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerTeamKey @DD1.5_BuyerTeamKey @DD1.6_BuyerTeamKey
  Scenario: BuyerTeamKey
    Given "BuyerTeamKey" exists in the metadata
    And "BuyerTeamKey" string values are not null
    Then "BuyerTeamKey" should be String data type
    And "BuyerTeamKey" length should be between the bounds in the metadata
    And "BuyerTeamKey" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerTeamName @DD1.5_BuyerTeamName @DD1.6_BuyerTeamName
  Scenario: BuyerTeamName
    Given "BuyerTeamName" exists in the metadata
    And "BuyerTeamName" string values are not null
    Then "BuyerTeamName" should be String data type
    And "BuyerTeamName" length should be between the bounds in the metadata
    And "BuyerTeamName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CarrierRoute @DD1.5_CarrierRoute @DD1.6_CarrierRoute
  Scenario: CarrierRoute
    Given "CarrierRoute" exists in the metadata
    And "CarrierRoute" string values are not null
    Then "CarrierRoute" should be String data type
    And "CarrierRoute" length should be between the bounds in the metadata
    And "CarrierRoute" length should be less than or equal to the RESO maxlength of 9

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @City @IDX_Payload @DD1.5_CityRegion @DD1.6_CityRegion
  Scenario: CityRegion
    Given "CityRegion" exists in the metadata
    And "CityRegion" string values are not null
    Then "CityRegion" should be String data type
    And "CityRegion" length should be between the bounds in the metadata
    And "CityRegion" length should be less than or equal to the RESO maxlength of 150

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentHomePhone @DD1.5_CoBuyerAgentHomePhone @DD1.6_CoBuyerAgentHomePhone
  Scenario: CoBuyerAgentHomePhone
    Given "CoBuyerAgentHomePhone" exists in the metadata
    And "CoBuyerAgentHomePhone" string values are not null
    Then "CoBuyerAgentHomePhone" should be String data type
    And "CoBuyerAgentHomePhone" length should be between the bounds in the metadata
    And "CoBuyerAgentHomePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentKey @DD1.5_CoBuyerAgentKey @DD1.6_CoBuyerAgentKey
  Scenario: CoBuyerAgentKey
    Given "CoBuyerAgentKey" exists in the metadata
    And "CoBuyerAgentKey" string values are not null
    Then "CoBuyerAgentKey" should be String data type
    And "CoBuyerAgentKey" length should be between the bounds in the metadata
    And "CoBuyerAgentKey" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentMlsId @IDX_Payload @DD1.5_CoBuyerAgentMlsId @DD1.6_CoBuyerAgentMlsId
  Scenario: CoBuyerAgentMlsId
    Given "CoBuyerAgentMlsId" exists in the metadata
    And "CoBuyerAgentMlsId" string values are not null
    Then "CoBuyerAgentMlsId" should be String data type
    And "CoBuyerAgentMlsId" length should be between the bounds in the metadata
    And "CoBuyerAgentMlsId" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentNamePrefix @DD1.5_CoBuyerAgentNamePrefix @DD1.6_CoBuyerAgentNamePrefix
  Scenario: CoBuyerAgentNamePrefix
    Given "CoBuyerAgentNamePrefix" exists in the metadata
    And "CoBuyerAgentNamePrefix" string values are not null
    Then "CoBuyerAgentNamePrefix" should be String data type
    And "CoBuyerAgentNamePrefix" length should be between the bounds in the metadata
    And "CoBuyerAgentNamePrefix" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentNameSuffix @DD1.5_CoBuyerAgentNameSuffix @DD1.6_CoBuyerAgentNameSuffix
  Scenario: CoBuyerAgentNameSuffix
    Given "CoBuyerAgentNameSuffix" exists in the metadata
    And "CoBuyerAgentNameSuffix" string values are not null
    Then "CoBuyerAgentNameSuffix" should be String data type
    And "CoBuyerAgentNameSuffix" length should be between the bounds in the metadata
    And "CoBuyerAgentNameSuffix" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentOfficePhoneExt @DD1.5_CoBuyerAgentOfficePhoneExt @DD1.6_CoBuyerAgentOfficePhoneExt
  Scenario: CoBuyerAgentOfficePhoneExt
    Given "CoBuyerAgentOfficePhoneExt" exists in the metadata
    And "CoBuyerAgentOfficePhoneExt" string values are not null
    Then "CoBuyerAgentOfficePhoneExt" should be String data type
    And "CoBuyerAgentOfficePhoneExt" length should be between the bounds in the metadata
    And "CoBuyerAgentOfficePhoneExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentPreferredPhoneExt @DD1.5_CoBuyerAgentPreferredPhoneExt @DD1.6_CoBuyerAgentPreferredPhoneExt
  Scenario: CoBuyerAgentPreferredPhoneExt
    Given "CoBuyerAgentPreferredPhoneExt" exists in the metadata
    And "CoBuyerAgentPreferredPhoneExt" string values are not null
    Then "CoBuyerAgentPreferredPhoneExt" should be String data type
    And "CoBuyerAgentPreferredPhoneExt" length should be between the bounds in the metadata
    And "CoBuyerAgentPreferredPhoneExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentVoiceMailExt @DD1.5_CoBuyerAgentVoiceMailExt @DD1.6_CoBuyerAgentVoiceMailExt
  Scenario: CoBuyerAgentVoiceMailExt
    Given "CoBuyerAgentVoiceMailExt" exists in the metadata
    And "CoBuyerAgentVoiceMailExt" string values are not null
    Then "CoBuyerAgentVoiceMailExt" should be String data type
    And "CoBuyerAgentVoiceMailExt" length should be between the bounds in the metadata
    And "CoBuyerAgentVoiceMailExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerOfficeEmail @DD1.5_CoBuyerOfficeEmail @DD1.6_CoBuyerOfficeEmail
  Scenario: CoBuyerOfficeEmail
    Given "CoBuyerOfficeEmail" exists in the metadata
    And "CoBuyerOfficeEmail" string values are not null
    Then "CoBuyerOfficeEmail" should be String data type
    And "CoBuyerOfficeEmail" length should be between the bounds in the metadata
    And "CoBuyerOfficeEmail" length should be less than or equal to the RESO maxlength of 80

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerOfficeFax @DD1.5_CoBuyerOfficeFax @DD1.6_CoBuyerOfficeFax
  Scenario: CoBuyerOfficeFax
    Given "CoBuyerOfficeFax" exists in the metadata
    And "CoBuyerOfficeFax" string values are not null
    Then "CoBuyerOfficeFax" should be String data type
    And "CoBuyerOfficeFax" length should be between the bounds in the metadata
    And "CoBuyerOfficeFax" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerOfficeKey @DD1.5_CoBuyerOfficeKey @DD1.6_CoBuyerOfficeKey
  Scenario: CoBuyerOfficeKey
    Given "CoBuyerOfficeKey" exists in the metadata
    And "CoBuyerOfficeKey" string values are not null
    Then "CoBuyerOfficeKey" should be String data type
    And "CoBuyerOfficeKey" length should be between the bounds in the metadata
    And "CoBuyerOfficeKey" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerOfficeMlsId @IDX_Payload @DD1.5_CoBuyerOfficeMlsId @DD1.6_CoBuyerOfficeMlsId
  Scenario: CoBuyerOfficeMlsId
    Given "CoBuyerOfficeMlsId" exists in the metadata
    And "CoBuyerOfficeMlsId" string values are not null
    Then "CoBuyerOfficeMlsId" should be String data type
    And "CoBuyerOfficeMlsId" length should be between the bounds in the metadata
    And "CoBuyerOfficeMlsId" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerOfficeName @IDX_Payload @DD1.5_CoBuyerOfficeName @DD1.6_CoBuyerOfficeName
  Scenario: CoBuyerOfficeName
    Given "CoBuyerOfficeName" exists in the metadata
    And "CoBuyerOfficeName" string values are not null
    Then "CoBuyerOfficeName" should be String data type
    And "CoBuyerOfficeName" length should be between the bounds in the metadata
    And "CoBuyerOfficeName" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerOfficePhone @DD1.5_CoBuyerOfficePhone @DD1.6_CoBuyerOfficePhone
  Scenario: CoBuyerOfficePhone
    Given "CoBuyerOfficePhone" exists in the metadata
    And "CoBuyerOfficePhone" string values are not null
    Then "CoBuyerOfficePhone" should be String data type
    And "CoBuyerOfficePhone" length should be between the bounds in the metadata
    And "CoBuyerOfficePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerOfficePhoneExt @DD1.5_CoBuyerOfficePhoneExt @DD1.6_CoBuyerOfficePhoneExt
  Scenario: CoBuyerOfficePhoneExt
    Given "CoBuyerOfficePhoneExt" exists in the metadata
    And "CoBuyerOfficePhoneExt" string values are not null
    Then "CoBuyerOfficePhoneExt" should be String data type
    And "CoBuyerOfficePhoneExt" length should be between the bounds in the metadata
    And "CoBuyerOfficePhoneExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerOfficeURL @DD1.5_CoBuyerOfficeURL @DD1.6_CoBuyerOfficeURL
  Scenario: CoBuyerOfficeURL
    Given "CoBuyerOfficeURL" exists in the metadata
    And "CoBuyerOfficeURL" string values are not null
    Then "CoBuyerOfficeURL" should be String data type
    And "CoBuyerOfficeURL" length should be between the bounds in the metadata
    And "CoBuyerOfficeURL" length should be less than or equal to the RESO maxlength of 8000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentHomePhone @DD1.5_CoListAgentHomePhone @DD1.6_CoListAgentHomePhone
  Scenario: CoListAgentHomePhone
    Given "CoListAgentHomePhone" exists in the metadata
    And "CoListAgentHomePhone" string values are not null
    Then "CoListAgentHomePhone" should be String data type
    And "CoListAgentHomePhone" length should be between the bounds in the metadata
    And "CoListAgentHomePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentKey @DD1.5_CoListAgentKey @DD1.6_CoListAgentKey
  Scenario: CoListAgentKey
    Given "CoListAgentKey" exists in the metadata
    And "CoListAgentKey" string values are not null
    Then "CoListAgentKey" should be String data type
    And "CoListAgentKey" length should be between the bounds in the metadata
    And "CoListAgentKey" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentMlsId @IDX_Payload @DD1.5_CoListAgentMlsId @DD1.6_CoListAgentMlsId
  Scenario: CoListAgentMlsId
    Given "CoListAgentMlsId" exists in the metadata
    And "CoListAgentMlsId" string values are not null
    Then "CoListAgentMlsId" should be String data type
    And "CoListAgentMlsId" length should be between the bounds in the metadata
    And "CoListAgentMlsId" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentNamePrefix @DD1.5_CoListAgentNamePrefix @DD1.6_CoListAgentNamePrefix
  Scenario: CoListAgentNamePrefix
    Given "CoListAgentNamePrefix" exists in the metadata
    And "CoListAgentNamePrefix" string values are not null
    Then "CoListAgentNamePrefix" should be String data type
    And "CoListAgentNamePrefix" length should be between the bounds in the metadata
    And "CoListAgentNamePrefix" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentNameSuffix @DD1.5_CoListAgentNameSuffix @DD1.6_CoListAgentNameSuffix
  Scenario: CoListAgentNameSuffix
    Given "CoListAgentNameSuffix" exists in the metadata
    And "CoListAgentNameSuffix" string values are not null
    Then "CoListAgentNameSuffix" should be String data type
    And "CoListAgentNameSuffix" length should be between the bounds in the metadata
    And "CoListAgentNameSuffix" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListOfficeEmail @DD1.5_CoListOfficeEmail @DD1.6_CoListOfficeEmail
  Scenario: CoListOfficeEmail
    Given "CoListOfficeEmail" exists in the metadata
    And "CoListOfficeEmail" string values are not null
    Then "CoListOfficeEmail" should be String data type
    And "CoListOfficeEmail" length should be between the bounds in the metadata
    And "CoListOfficeEmail" length should be less than or equal to the RESO maxlength of 80

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListOfficeFax @DD1.5_CoListOfficeFax @DD1.6_CoListOfficeFax
  Scenario: CoListOfficeFax
    Given "CoListOfficeFax" exists in the metadata
    And "CoListOfficeFax" string values are not null
    Then "CoListOfficeFax" should be String data type
    And "CoListOfficeFax" length should be between the bounds in the metadata
    And "CoListOfficeFax" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListOfficeKey @DD1.5_CoListOfficeKey @DD1.6_CoListOfficeKey
  Scenario: CoListOfficeKey
    Given "CoListOfficeKey" exists in the metadata
    And "CoListOfficeKey" string values are not null
    Then "CoListOfficeKey" should be String data type
    And "CoListOfficeKey" length should be between the bounds in the metadata
    And "CoListOfficeKey" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListOfficeMlsId @IDX_Payload @DD1.5_CoListOfficeMlsId @DD1.6_CoListOfficeMlsId
  Scenario: CoListOfficeMlsId
    Given "CoListOfficeMlsId" exists in the metadata
    And "CoListOfficeMlsId" string values are not null
    Then "CoListOfficeMlsId" should be String data type
    And "CoListOfficeMlsId" length should be between the bounds in the metadata
    And "CoListOfficeMlsId" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListOfficeName @IDX_Payload @DD1.5_CoListOfficeName @DD1.6_CoListOfficeName
  Scenario: CoListOfficeName
    Given "CoListOfficeName" exists in the metadata
    And "CoListOfficeName" string values are not null
    Then "CoListOfficeName" should be String data type
    And "CoListOfficeName" length should be between the bounds in the metadata
    And "CoListOfficeName" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListOfficePhone @DD1.5_CoListOfficePhone @DD1.6_CoListOfficePhone
  Scenario: CoListOfficePhone
    Given "CoListOfficePhone" exists in the metadata
    And "CoListOfficePhone" string values are not null
    Then "CoListOfficePhone" should be String data type
    And "CoListOfficePhone" length should be between the bounds in the metadata
    And "CoListOfficePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListOfficePhoneExt @DD1.5_CoListOfficePhoneExt @DD1.6_CoListOfficePhoneExt
  Scenario: CoListOfficePhoneExt
    Given "CoListOfficePhoneExt" exists in the metadata
    And "CoListOfficePhoneExt" string values are not null
    Then "CoListOfficePhoneExt" should be String data type
    And "CoListOfficePhoneExt" length should be between the bounds in the metadata
    And "CoListOfficePhoneExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListOfficeURL @DD1.5_CoListOfficeURL @DD1.6_CoListOfficeURL
  Scenario: CoListOfficeURL
    Given "CoListOfficeURL" exists in the metadata
    And "CoListOfficeURL" string values are not null
    Then "CoListOfficeURL" should be String data type
    And "CoListOfficeURL" length should be between the bounds in the metadata
    And "CoListOfficeURL" length should be less than or equal to the RESO maxlength of 8000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ConcessionsComments @DD1.5_ConcessionsComments @DD1.6_ConcessionsComments
  Scenario: ConcessionsComments
    Given "ConcessionsComments" exists in the metadata
    And "ConcessionsComments" string values are not null
    Then "ConcessionsComments" should be String data type
    And "ConcessionsComments" length should be between the bounds in the metadata
    And "ConcessionsComments" length should be less than or equal to the RESO maxlength of 200

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ContinentRegion @DD1.5_ContinentRegion @DD1.6_ContinentRegion
  Scenario: ContinentRegion
    Given "ContinentRegion" exists in the metadata
    And "ContinentRegion" string values are not null
    Then "ContinentRegion" should be String data type
    And "ContinentRegion" length should be between the bounds in the metadata
    And "ContinentRegion" length should be less than or equal to the RESO maxlength of 150

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @Contingency @DD1.5_Contingency @DD1.6_Contingency @testing
  Scenario: Contingency
    Given "Contingency" exists in the metadata
    And "Contingency" string values are not null
    Then "Contingency" should be String data type
    And "Contingency" length should be between the bounds in the metadata
    And "Contingency" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CopyrightNotice @DD1.5_CopyrightNotice @DD1.6_CopyrightNotice
  Scenario: CopyrightNotice
    Given "CopyrightNotice" exists in the metadata
    And "CopyrightNotice" string values are not null
    Then "CopyrightNotice" should be String data type
    And "CopyrightNotice" length should be between the bounds in the metadata
    And "CopyrightNotice" length should be less than or equal to the RESO maxlength of 500

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CountryRegion @DD1.5_CountryRegion @DD1.6_CountryRegion
  Scenario: CountryRegion
    Given "CountryRegion" exists in the metadata
    And "CountryRegion" string values are not null
    Then "CountryRegion" should be String data type
    And "CountryRegion" length should be between the bounds in the metadata
    And "CountryRegion" length should be less than or equal to the RESO maxlength of 150

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CrossStreet @DD1.5_CrossStreet @DD1.6_CrossStreet
  Scenario: CrossStreet
    Given "CrossStreet" exists in the metadata
    And "CrossStreet" string values are not null
    Then "CrossStreet" should be String data type
    And "CrossStreet" length should be between the bounds in the metadata
    And "CrossStreet" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @DOH1 @DD1.5_DOH @DD1.6_DOH1
  Scenario: DOH1
    Given "DOH1" exists in the metadata
    And "DOH1" string values are not null
    Then "DOH1" should be String data type
    And "DOH1" length should be between the bounds in the metadata
    And "DOH1" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @DOH2 @DD1.5_DOH @DD1.6_DOH2
  Scenario: DOH2
    Given "DOH2" exists in the metadata
    And "DOH2" string values are not null
    Then "DOH2" should be String data type
    And "DOH2" length should be between the bounds in the metadata
    And "DOH2" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @DOH3 @DD1.5_DOH @DD1.6_DOH3
  Scenario: DOH3
    Given "DOH3" exists in the metadata
    And "DOH3" string values are not null
    Then "DOH3" should be String data type
    And "DOH3" length should be between the bounds in the metadata
    And "DOH3" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @Directions @IDX_Payload @DD1.5_Directions @DD1.6_Directions
  Scenario: Directions
    Given "Directions" exists in the metadata
    And "Directions" string values are not null
    Then "Directions" should be String data type
    And "Directions" length should be between the bounds in the metadata
    And "Directions" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @Disclaimer @DD1.5_Disclaimer @DD1.6_Disclaimer
  Scenario: Disclaimer
    Given "Disclaimer" exists in the metadata
    And "Disclaimer" string values are not null
    Then "Disclaimer" should be String data type
    And "Disclaimer" length should be between the bounds in the metadata
    And "Disclaimer" length should be less than or equal to the RESO maxlength of 500

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @DistanceToBusComments @DD1.5_DistanceToBusComments @DD1.6_DistanceToBusComments
  Scenario: DistanceToBusComments
    Given "DistanceToBusComments" exists in the metadata
    And "DistanceToBusComments" string values are not null
    Then "DistanceToBusComments" should be String data type
    And "DistanceToBusComments" length should be between the bounds in the metadata
    And "DistanceToBusComments" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @DistanceToElectricComments @DD1.5_DistanceToElectricComments @DD1.6_DistanceToElectricComments
  Scenario: DistanceToElectricComments
    Given "DistanceToElectricComments" exists in the metadata
    And "DistanceToElectricComments" string values are not null
    Then "DistanceToElectricComments" should be String data type
    And "DistanceToElectricComments" length should be between the bounds in the metadata
    And "DistanceToElectricComments" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @DistanceToFreewayComments @DD1.5_DistanceToFreewayComments @DD1.6_DistanceToFreewayComments
  Scenario: DistanceToFreewayComments
    Given "DistanceToFreewayComments" exists in the metadata
    And "DistanceToFreewayComments" string values are not null
    Then "DistanceToFreewayComments" should be String data type
    And "DistanceToFreewayComments" length should be between the bounds in the metadata
    And "DistanceToFreewayComments" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @DistanceToGasComments @DD1.5_DistanceToGasComments @DD1.6_DistanceToGasComments
  Scenario: DistanceToGasComments
    Given "DistanceToGasComments" exists in the metadata
    And "DistanceToGasComments" string values are not null
    Then "DistanceToGasComments" should be String data type
    And "DistanceToGasComments" length should be between the bounds in the metadata
    And "DistanceToGasComments" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @DistanceToPhoneServiceComments @DD1.5_DistanceToPhoneServiceComments @DD1.6_DistanceToPhoneServiceComments
  Scenario: DistanceToPhoneServiceComments
    Given "DistanceToPhoneServiceComments" exists in the metadata
    And "DistanceToPhoneServiceComments" string values are not null
    Then "DistanceToPhoneServiceComments" should be String data type
    And "DistanceToPhoneServiceComments" length should be between the bounds in the metadata
    And "DistanceToPhoneServiceComments" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @DistanceToPlaceofWorshipComments @DD1.5_DistanceToPlaceofWorshipComments @DD1.6_DistanceToPlaceofWorshipComments
  Scenario: DistanceToPlaceofWorshipComments
    Given "DistanceToPlaceofWorshipComments" exists in the metadata
    And "DistanceToPlaceofWorshipComments" string values are not null
    Then "DistanceToPlaceofWorshipComments" should be String data type
    And "DistanceToPlaceofWorshipComments" length should be between the bounds in the metadata
    And "DistanceToPlaceofWorshipComments" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @DistanceToSchoolBusComments @DD1.5_DistanceToSchoolBusComments @DD1.6_DistanceToSchoolBusComments
  Scenario: DistanceToSchoolBusComments
    Given "DistanceToSchoolBusComments" exists in the metadata
    And "DistanceToSchoolBusComments" string values are not null
    Then "DistanceToSchoolBusComments" should be String data type
    And "DistanceToSchoolBusComments" length should be between the bounds in the metadata
    And "DistanceToSchoolBusComments" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @DistanceToSchoolsComments @DD1.5_DistanceToSchoolsComments @DD1.6_DistanceToSchoolsComments
  Scenario: DistanceToSchoolsComments
    Given "DistanceToSchoolsComments" exists in the metadata
    And "DistanceToSchoolsComments" string values are not null
    Then "DistanceToSchoolsComments" should be String data type
    And "DistanceToSchoolsComments" length should be between the bounds in the metadata
    And "DistanceToSchoolsComments" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @DistanceToSewerComments @DD1.5_DistanceToSewerComments @DD1.6_DistanceToSewerComments
  Scenario: DistanceToSewerComments
    Given "DistanceToSewerComments" exists in the metadata
    And "DistanceToSewerComments" string values are not null
    Then "DistanceToSewerComments" should be String data type
    And "DistanceToSewerComments" length should be between the bounds in the metadata
    And "DistanceToSewerComments" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @DistanceToShoppingComments @DD1.5_DistanceToShoppingComments @DD1.6_DistanceToShoppingComments
  Scenario: DistanceToShoppingComments
    Given "DistanceToShoppingComments" exists in the metadata
    And "DistanceToShoppingComments" string values are not null
    Then "DistanceToShoppingComments" should be String data type
    And "DistanceToShoppingComments" length should be between the bounds in the metadata
    And "DistanceToShoppingComments" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @DistanceToStreetComments @DD1.5_DistanceToStreetComments @DD1.6_DistanceToStreetComments
  Scenario: DistanceToStreetComments
    Given "DistanceToStreetComments" exists in the metadata
    And "DistanceToStreetComments" string values are not null
    Then "DistanceToStreetComments" should be String data type
    And "DistanceToStreetComments" length should be between the bounds in the metadata
    And "DistanceToStreetComments" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @DistanceToWaterComments @DD1.5_DistanceToWaterComments @DD1.6_DistanceToWaterComments
  Scenario: DistanceToWaterComments
    Given "DistanceToWaterComments" exists in the metadata
    And "DistanceToWaterComments" string values are not null
    Then "DistanceToWaterComments" should be String data type
    And "DistanceToWaterComments" length should be between the bounds in the metadata
    And "DistanceToWaterComments" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @EntryLocation @DD1.5_EntryLocation @DD1.6_EntryLocation
  Scenario: EntryLocation
    Given "EntryLocation" exists in the metadata
    And "EntryLocation" string values are not null
    Then "EntryLocation" should be String data type
    And "EntryLocation" length should be between the bounds in the metadata
    And "EntryLocation" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @Exclusions @DD1.5_Exclusions @DD1.6_Exclusions
  Scenario: Exclusions
    Given "Exclusions" exists in the metadata
    And "Exclusions" string values are not null
    Then "Exclusions" should be String data type
    And "Exclusions" length should be between the bounds in the metadata
    And "Exclusions" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @FrontageLength @DD1.5_FrontageLength @DD1.6_FrontageLength
  Scenario: FrontageLength
    Given "FrontageLength" exists in the metadata
    And "FrontageLength" string values are not null
    Then "FrontageLength" should be String data type
    And "FrontageLength" length should be between the bounds in the metadata
    And "FrontageLength" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @HoursDaysofOperationDescription @DD1.5_HoursDaysofOperationDescription @DD1.6_HoursDaysofOperationDescription
  Scenario: HoursDaysofOperationDescription
    Given "HoursDaysofOperationDescription" exists in the metadata
    And "HoursDaysofOperationDescription" string values are not null
    Then "HoursDaysofOperationDescription" should be String data type
    And "HoursDaysofOperationDescription" length should be between the bounds in the metadata
    And "HoursDaysofOperationDescription" length should be less than or equal to the RESO maxlength of 255

  @DD1.6_String @DD1.6 @HoursDaysOfOperationDescription @DD1.6_HoursDaysOfOperationDescription
  Scenario: HoursDaysOfOperationDescription
    Given "HoursDaysOfOperationDescription" exists in the metadata
    And "HoursDaysOfOperationDescription" string values are not null
    Then "HoursDaysOfOperationDescription" should be String data type
    And "HoursDaysOfOperationDescription" length should be between the bounds in the metadata
    And "HoursDaysOfOperationDescription" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @Inclusions @DD1.5_Inclusions @DD1.6_Inclusions
  Scenario: Inclusions
    Given "Inclusions" exists in the metadata
    And "Inclusions" string values are not null
    Then "Inclusions" should be String data type
    And "Inclusions" length should be between the bounds in the metadata
    And "Inclusions" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @License1 @DD1.5_License @DD1.6_License1
  Scenario: License1
    Given "License1" exists in the metadata
    And "License1" string values are not null
    Then "License1" should be String data type
    And "License1" length should be between the bounds in the metadata
    And "License1" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @License2 @DD1.5_License @DD1.6_License2
  Scenario: License2
    Given "License2" exists in the metadata
    And "License2" string values are not null
    Then "License2" should be String data type
    And "License2" length should be between the bounds in the metadata
    And "License2" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @License3 @DD1.5_License @DD1.6_License3
  Scenario: License3
    Given "License3" exists in the metadata
    And "License3" string values are not null
    Then "License3" should be String data type
    And "License3" length should be between the bounds in the metadata
    And "License3" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentHomePhone @DD1.5_ListAgentHomePhone @DD1.6_ListAgentHomePhone
  Scenario: ListAgentHomePhone
    Given "ListAgentHomePhone" exists in the metadata
    And "ListAgentHomePhone" string values are not null
    Then "ListAgentHomePhone" should be String data type
    And "ListAgentHomePhone" length should be between the bounds in the metadata
    And "ListAgentHomePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentMlsId @IDX_Payload @DD1.5_ListAgentMlsId @DD1.6_ListAgentMlsId
  Scenario: ListAgentMlsId
    Given "ListAgentMlsId" exists in the metadata
    And "ListAgentMlsId" string values are not null
    Then "ListAgentMlsId" should be String data type
    And "ListAgentMlsId" length should be between the bounds in the metadata
    And "ListAgentMlsId" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentNamePrefix @DD1.5_ListAgentNamePrefix @DD1.6_ListAgentNamePrefix
  Scenario: ListAgentNamePrefix
    Given "ListAgentNamePrefix" exists in the metadata
    And "ListAgentNamePrefix" string values are not null
    Then "ListAgentNamePrefix" should be String data type
    And "ListAgentNamePrefix" length should be between the bounds in the metadata
    And "ListAgentNamePrefix" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentNameSuffix @DD1.5_ListAgentNameSuffix @DD1.6_ListAgentNameSuffix
  Scenario: ListAgentNameSuffix
    Given "ListAgentNameSuffix" exists in the metadata
    And "ListAgentNameSuffix" string values are not null
    Then "ListAgentNameSuffix" should be String data type
    And "ListAgentNameSuffix" length should be between the bounds in the metadata
    And "ListAgentNameSuffix" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListOfficeKey @DD1.5_ListOfficeKey @DD1.6_ListOfficeKey
  Scenario: ListOfficeKey
    Given "ListOfficeKey" exists in the metadata
    And "ListOfficeKey" string values are not null
    Then "ListOfficeKey" should be String data type
    And "ListOfficeKey" length should be between the bounds in the metadata
    And "ListOfficeKey" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListOfficeMlsId @IDX_Payload @DD1.5_ListOfficeMlsId @DD1.6_ListOfficeMlsId
  Scenario: ListOfficeMlsId
    Given "ListOfficeMlsId" exists in the metadata
    And "ListOfficeMlsId" string values are not null
    Then "ListOfficeMlsId" should be String data type
    And "ListOfficeMlsId" length should be between the bounds in the metadata
    And "ListOfficeMlsId" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListTeamKey @DD1.5_ListTeamKey @DD1.6_ListTeamKey
  Scenario: ListTeamKey
    Given "ListTeamKey" exists in the metadata
    And "ListTeamKey" string values are not null
    Then "ListTeamKey" should be String data type
    And "ListTeamKey" length should be between the bounds in the metadata
    And "ListTeamKey" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListTeamName @DD1.5_ListTeamName @DD1.6_ListTeamName
  Scenario: ListTeamName
    Given "ListTeamName" exists in the metadata
    And "ListTeamName" string values are not null
    Then "ListTeamName" should be String data type
    And "ListTeamName" length should be between the bounds in the metadata
    And "ListTeamName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @LockBoxLocation @DD1.5_LockBoxLocation @DD1.6_LockBoxLocation
  Scenario: LockBoxLocation
    Given "LockBoxLocation" exists in the metadata
    And "LockBoxLocation" string values are not null
    Then "LockBoxLocation" should be String data type
    And "LockBoxLocation" length should be between the bounds in the metadata
    And "LockBoxLocation" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @LockBoxSerialNumber @DD1.5_LockBoxSerialNumber @DD1.6_LockBoxSerialNumber
  Scenario: LockBoxSerialNumber
    Given "LockBoxSerialNumber" exists in the metadata
    And "LockBoxSerialNumber" string values are not null
    Then "LockBoxSerialNumber" should be String data type
    And "LockBoxSerialNumber" length should be between the bounds in the metadata
    And "LockBoxSerialNumber" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @LotSizeDimensions @IDX_Payload @DD1.5_LotSizeDimensions @DD1.6_LotSizeDimensions
  Scenario: LotSizeDimensions
    Given "LotSizeDimensions" exists in the metadata
    And "LotSizeDimensions" string values are not null
    Then "LotSizeDimensions" should be String data type
    And "LotSizeDimensions" length should be between the bounds in the metadata
    And "LotSizeDimensions" length should be less than or equal to the RESO maxlength of 150

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @Make @DD1.5_Make @DD1.6_Make
  Scenario: Make
    Given "Make" exists in the metadata
    And "Make" string values are not null
    Then "Make" should be String data type
    And "Make" length should be between the bounds in the metadata
    And "Make" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @MapCoordinate @DD1.5_MapCoordinate @DD1.6_MapCoordinate
  Scenario: MapCoordinate
    Given "MapCoordinate" exists in the metadata
    And "MapCoordinate" string values are not null
    Then "MapCoordinate" should be String data type
    And "MapCoordinate" length should be between the bounds in the metadata
    And "MapCoordinate" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @MapCoordinateSource @DD1.5_MapCoordinateSource @DD1.6_MapCoordinateSource
  Scenario: MapCoordinateSource
    Given "MapCoordinateSource" exists in the metadata
    And "MapCoordinateSource" string values are not null
    Then "MapCoordinateSource" should be String data type
    And "MapCoordinateSource" length should be between the bounds in the metadata
    And "MapCoordinateSource" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @MapURL @DD1.5_MapURL @DD1.6_MapURL
  Scenario: MapURL
    Given "MapURL" exists in the metadata
    And "MapURL" string values are not null
    Then "MapURL" should be String data type
    And "MapURL" length should be between the bounds in the metadata
    And "MapURL" length should be less than or equal to the RESO maxlength of 8000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @Model @DD1.5_Model @DD1.6_Model
  Scenario: Model
    Given "Model" exists in the metadata
    And "Model" string values are not null
    Then "Model" should be String data type
    And "Model" length should be between the bounds in the metadata
    And "Model" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @OtherParking @DD1.5_OtherParking @DD1.6_OtherParking
  Scenario: OtherParking
    Given "OtherParking" exists in the metadata
    And "OtherParking" string values are not null
    Then "OtherParking" should be String data type
    And "OtherParking" length should be between the bounds in the metadata
    And "OtherParking" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @OwnerPhone @DD1.5_OwnerPhone @DD1.6_OwnerPhone
  Scenario: OwnerPhone
    Given "OwnerPhone" exists in the metadata
    And "OwnerPhone" string values are not null
    Then "OwnerPhone" should be String data type
    And "OwnerPhone" length should be between the bounds in the metadata
    And "OwnerPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @Ownership @DD1.5_Ownership @DD1.6_Ownership
  Scenario: Ownership
    Given "Ownership" exists in the metadata
    And "Ownership" string values are not null
    Then "Ownership" should be String data type
    And "Ownership" length should be between the bounds in the metadata
    And "Ownership" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ParcelNumber @IDX_Payload @DD1.5_ParcelNumber @DD1.6_ParcelNumber
  Scenario: ParcelNumber
    Given "ParcelNumber" exists in the metadata
    And "ParcelNumber" string values are not null
    Then "ParcelNumber" should be String data type
    And "ParcelNumber" length should be between the bounds in the metadata
    And "ParcelNumber" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ParkManagerName @DD1.5_ParkManagerName @DD1.6_ParkManagerName
  Scenario: ParkManagerName
    Given "ParkManagerName" exists in the metadata
    And "ParkManagerName" string values are not null
    Then "ParkManagerName" should be String data type
    And "ParkManagerName" length should be between the bounds in the metadata
    And "ParkManagerName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ParkManagerPhone @DD1.5_ParkManagerPhone @DD1.6_ParkManagerPhone
  Scenario: ParkManagerPhone
    Given "ParkManagerPhone" exists in the metadata
    And "ParkManagerPhone" string values are not null
    Then "ParkManagerPhone" should be String data type
    And "ParkManagerPhone" length should be between the bounds in the metadata
    And "ParkManagerPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ParkName @IDX_Payload @DD1.5_ParkName @DD1.6_ParkName
  Scenario: ParkName
    Given "ParkName" exists in the metadata
    And "ParkName" string values are not null
    Then "ParkName" should be String data type
    And "ParkName" length should be between the bounds in the metadata
    And "ParkName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @PostalCode @IDX_Payload @DD1.5_PostalCodePlus @DD1.6_PostalCodePlus4
  Scenario: PostalCodePlus4
    Given "PostalCodePlus4" exists in the metadata
    And "PostalCodePlus4" string values are not null
    Then "PostalCodePlus4" should be String data type
    And "PostalCodePlus4" length should be between the bounds in the metadata
    And "PostalCodePlus4" length should be less than or equal to the RESO maxlength of 4

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @PublicSurveyRange @DD1.5_PublicSurveyRange @DD1.6_PublicSurveyRange
  Scenario: PublicSurveyRange
    Given "PublicSurveyRange" exists in the metadata
    And "PublicSurveyRange" string values are not null
    Then "PublicSurveyRange" should be String data type
    And "PublicSurveyRange" length should be between the bounds in the metadata
    And "PublicSurveyRange" length should be less than or equal to the RESO maxlength of 20

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @PublicSurveySection @DD1.5_PublicSurveySection @DD1.6_PublicSurveySection
  Scenario: PublicSurveySection
    Given "PublicSurveySection" exists in the metadata
    And "PublicSurveySection" string values are not null
    Then "PublicSurveySection" should be String data type
    And "PublicSurveySection" length should be between the bounds in the metadata
    And "PublicSurveySection" length should be less than or equal to the RESO maxlength of 20

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @PublicSurveyTownship @DD1.5_PublicSurveyTownship @DD1.6_PublicSurveyTownship
  Scenario: PublicSurveyTownship
    Given "PublicSurveyTownship" exists in the metadata
    And "PublicSurveyTownship" string values are not null
    Then "PublicSurveyTownship" should be String data type
    And "PublicSurveyTownship" length should be between the bounds in the metadata
    And "PublicSurveyTownship" length should be less than or equal to the RESO maxlength of 20

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @RVParkingDimensions @DD1.5_RVParkingDimensions @DD1.6_RVParkingDimensions
  Scenario: RVParkingDimensions
    Given "RVParkingDimensions" exists in the metadata
    And "RVParkingDimensions" string values are not null
    Then "RVParkingDimensions" should be String data type
    And "RVParkingDimensions" length should be between the bounds in the metadata
    And "RVParkingDimensions" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @SerialU @DD1.5_SerialU @DD1.6_SerialU
  Scenario: SerialU
    Given "SerialU" exists in the metadata
    And "SerialU" string values are not null
    Then "SerialU" should be String data type
    And "SerialU" length should be between the bounds in the metadata
    And "SerialU" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @SerialX @DD1.5_SerialX @DD1.6_SerialX
  Scenario: SerialX
    Given "SerialX" exists in the metadata
    And "SerialX" string values are not null
    Then "SerialX" should be String data type
    And "SerialX" length should be between the bounds in the metadata
    And "SerialX" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @SerialXX @DD1.5_SerialXX @DD1.6_SerialXX
  Scenario: SerialXX
    Given "SerialXX" exists in the metadata
    And "SerialXX" string values are not null
    Then "SerialXX" should be String data type
    And "SerialXX" length should be between the bounds in the metadata
    And "SerialXX" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ShowingContactName @DD1.5_ShowingContactName @DD1.6_ShowingContactName
  Scenario: ShowingContactName
    Given "ShowingContactName" exists in the metadata
    And "ShowingContactName" string values are not null
    Then "ShowingContactName" should be String data type
    And "ShowingContactName" length should be between the bounds in the metadata
    And "ShowingContactName" length should be less than or equal to the RESO maxlength of 40

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ShowingContactPhone @DD1.5_ShowingContactPhone @DD1.6_ShowingContactPhone
  Scenario: ShowingContactPhone
    Given "ShowingContactPhone" exists in the metadata
    And "ShowingContactPhone" string values are not null
    Then "ShowingContactPhone" should be String data type
    And "ShowingContactPhone" length should be between the bounds in the metadata
    And "ShowingContactPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ShowingContactPhoneExt @DD1.5_ShowingContactPhoneExt @DD1.6_ShowingContactPhoneExt
  Scenario: ShowingContactPhoneExt
    Given "ShowingContactPhoneExt" exists in the metadata
    And "ShowingContactPhoneExt" string values are not null
    Then "ShowingContactPhoneExt" should be String data type
    And "ShowingContactPhoneExt" length should be between the bounds in the metadata
    And "ShowingContactPhoneExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ShowingInstructions @DD1.5_ShowingInstructions @DD1.6_ShowingInstructions
  Scenario: ShowingInstructions
    Given "ShowingInstructions" exists in the metadata
    And "ShowingInstructions" string values are not null
    Then "ShowingInstructions" should be String data type
    And "ShowingInstructions" length should be between the bounds in the metadata
    And "ShowingInstructions" length should be less than or equal to the RESO maxlength of 4000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @StateRegion @DD1.5_StateRegion @DD1.6_StateRegion
  Scenario: StateRegion
    Given "StateRegion" exists in the metadata
    And "StateRegion" string values are not null
    Then "StateRegion" should be String data type
    And "StateRegion" length should be between the bounds in the metadata
    And "StateRegion" length should be less than or equal to the RESO maxlength of 150

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @StreetSuffix @IDX_Payload @DD1.5_StreetSuffixModifier @DD1.6_StreetSuffixModifier
  Scenario: StreetSuffixModifier
    Given "StreetSuffixModifier" exists in the metadata
    And "StreetSuffixModifier" string values are not null
    Then "StreetSuffixModifier" should be String data type
    And "StreetSuffixModifier" length should be between the bounds in the metadata
    And "StreetSuffixModifier" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @SubAgencyCompensation @DD1.5_SubAgencyCompensation @DD1.6_SubAgencyCompensation
  Scenario: SubAgencyCompensation
    Given "SubAgencyCompensation" exists in the metadata
    And "SubAgencyCompensation" string values are not null
    Then "SubAgencyCompensation" should be String data type
    And "SubAgencyCompensation" length should be between the bounds in the metadata
    And "SubAgencyCompensation" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @SyndicationRemarks @DD1.5_SyndicationRemarks @DD1.6_SyndicationRemarks
  Scenario: SyndicationRemarks
    Given "SyndicationRemarks" exists in the metadata
    And "SyndicationRemarks" string values are not null
    Then "SyndicationRemarks" should be String data type
    And "SyndicationRemarks" length should be between the bounds in the metadata
    And "SyndicationRemarks" length should be less than or equal to the RESO maxlength of 4000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @TaxBlock @DD1.5_TaxBlock @DD1.6_TaxBlock
  Scenario: TaxBlock
    Given "TaxBlock" exists in the metadata
    And "TaxBlock" string values are not null
    Then "TaxBlock" should be String data type
    And "TaxBlock" length should be between the bounds in the metadata
    And "TaxBlock" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @TaxBookNumber @DD1.5_TaxBookNumber @DD1.6_TaxBookNumber
  Scenario: TaxBookNumber
    Given "TaxBookNumber" exists in the metadata
    And "TaxBookNumber" string values are not null
    Then "TaxBookNumber" should be String data type
    And "TaxBookNumber" length should be between the bounds in the metadata
    And "TaxBookNumber" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @TaxLegalDescription @DD1.5_TaxLegalDescription @DD1.6_TaxLegalDescription
  Scenario: TaxLegalDescription
    Given "TaxLegalDescription" exists in the metadata
    And "TaxLegalDescription" string values are not null
    Then "TaxLegalDescription" should be String data type
    And "TaxLegalDescription" length should be between the bounds in the metadata
    And "TaxLegalDescription" length should be less than or equal to the RESO maxlength of 6000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @TaxLot @DD1.5_TaxLot @DD1.6_TaxLot
  Scenario: TaxLot
    Given "TaxLot" exists in the metadata
    And "TaxLot" string values are not null
    Then "TaxLot" should be String data type
    And "TaxLot" length should be between the bounds in the metadata
    And "TaxLot" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @TaxMapNumber @DD1.5_TaxMapNumber @DD1.6_TaxMapNumber
  Scenario: TaxMapNumber
    Given "TaxMapNumber" exists in the metadata
    And "TaxMapNumber" string values are not null
    Then "TaxMapNumber" should be String data type
    And "TaxMapNumber" length should be between the bounds in the metadata
    And "TaxMapNumber" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @TaxParcelLetter @DD1.5_TaxParcelLetter @DD1.6_TaxParcelLetter
  Scenario: TaxParcelLetter
    Given "TaxParcelLetter" exists in the metadata
    And "TaxParcelLetter" string values are not null
    Then "TaxParcelLetter" should be String data type
    And "TaxParcelLetter" length should be between the bounds in the metadata
    And "TaxParcelLetter" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @TaxTract @DD1.5_TaxTract @DD1.6_TaxTract
  Scenario: TaxTract
    Given "TaxTract" exists in the metadata
    And "TaxTract" string values are not null
    Then "TaxTract" should be String data type
    And "TaxTract" length should be between the bounds in the metadata
    And "TaxTract" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @Topography @IDX_Payload @DD1.5_Topography @DD1.6_Topography
  Scenario: Topography
    Given "Topography" exists in the metadata
    And "Topography" string values are not null
    Then "Topography" should be String data type
    And "Topography" length should be between the bounds in the metadata
    And "Topography" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @Township @DD1.5_Township @DD1.6_Township
  Scenario: Township
    Given "Township" exists in the metadata
    And "Township" string values are not null
    Then "Township" should be String data type
    And "Township" length should be between the bounds in the metadata
    And "Township" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @TransactionBrokerCompensation @DD1.5_TransactionBrokerCompensation @DD1.6_TransactionBrokerCompensation
  Scenario: TransactionBrokerCompensation
    Given "TransactionBrokerCompensation" exists in the metadata
    And "TransactionBrokerCompensation" string values are not null
    Then "TransactionBrokerCompensation" should be String data type
    And "TransactionBrokerCompensation" length should be between the bounds in the metadata
    And "TransactionBrokerCompensation" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @UnitNumber @IDX_Payload @DD1.5_UnitNumber @DD1.6_UnitNumber
  Scenario: UnitNumber
    Given "UnitNumber" exists in the metadata
    And "UnitNumber" string values are not null
    Then "UnitNumber" should be String data type
    And "UnitNumber" length should be between the bounds in the metadata
    And "UnitNumber" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @VirtualTourURLUnbranded @IDX_Payload @DD1.5_VirtualTourURLUnbranded @DD1.6_VirtualTourURLUnbranded
  Scenario: VirtualTourURLUnbranded
    Given "VirtualTourURLUnbranded" exists in the metadata
    And "VirtualTourURLUnbranded" string values are not null
    Then "VirtualTourURLUnbranded" should be String data type
    And "VirtualTourURLUnbranded" length should be between the bounds in the metadata
    And "VirtualTourURLUnbranded" length should be less than or equal to the RESO maxlength of 8000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @VirtualTourURLBranded @DD1.5_VirtualTourURLBranded @DD1.6_VirtualTourURLBranded
  Scenario: VirtualTourURLBranded
    Given "VirtualTourURLBranded" exists in the metadata
    And "VirtualTourURLBranded" string values are not null
    Then "VirtualTourURLBranded" should be String data type
    And "VirtualTourURLBranded" length should be between the bounds in the metadata
    And "VirtualTourURLBranded" length should be less than or equal to the RESO maxlength of 8000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @WaterBodyName @IDX_Payload @DD1.5_WaterBodyName @DD1.6_WaterBodyName
  Scenario: WaterBodyName
    Given "WaterBodyName" exists in the metadata
    And "WaterBodyName" string values are not null
    Then "WaterBodyName" should be String data type
    And "WaterBodyName" length should be between the bounds in the metadata
    And "WaterBodyName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @YearBuiltDetails @DD1.5_YearBuiltDetails @DD1.6_YearBuiltDetails
  Scenario: YearBuiltDetails
    Given "YearBuiltDetails" exists in the metadata
    And "YearBuiltDetails" string values are not null
    Then "YearBuiltDetails" should be String data type
    And "YearBuiltDetails" length should be between the bounds in the metadata
    And "YearBuiltDetails" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @Zoning @DD1.5_Zoning @DD1.6_Zoning @testing
  Scenario: Zoning
    Given "Zoning" exists in the metadata
    And "Zoning" string values are not null
    Then "Zoning" should be String data type
    And "Zoning" length should be between the bounds in the metadata
    And "Zoning" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ZoningDescription @DD1.5_ZoningDescription @DD1.6_ZoningDescription
  Scenario: ZoningDescription
    Given "ZoningDescription" exists in the metadata
    And "ZoningDescription" string values are not null
    Then "ZoningDescription" should be String data type
    And "ZoningDescription" length should be between the bounds in the metadata
    And "ZoningDescription" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListingKey @IDX_Payload @DD1.5_ListingKey @DD1.6_ListingKey @testing
  Scenario: ListingKey
    Given "ListingKey" exists in the metadata
    And "ListingKey" string values are not null
    Then "ListingKey" should be String data type
    And "ListingKey" length should be between the bounds in the metadata
    And "ListingKey" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentDesignation @DD1.5_BuyerAgentDesignation @DD1.6_BuyerAgentDesignation @testing
  Scenario: BuyerAgentDesignation
    Given "BuyerAgentDesignation" exists in the metadata
    And "BuyerAgentDesignation" string values are not null
    Then "BuyerAgentDesignation" should be String data type
    And "BuyerAgentDesignation" length should be between the bounds in the metadata
    And "BuyerAgentDesignation" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentFullName @IDX_Payload @DD1.5_BuyerAgentFullName @DD1.6_BuyerAgentFullName @testing
  Scenario: BuyerAgentFullName2
    Given "BuyerAgentFullName" exists in the metadata
    And "BuyerAgentFullName" string values are not null
    Then "BuyerAgentFullName" should be String data type
    And "BuyerAgentFullName" length should be between the bounds in the metadata
    And "BuyerAgentFullName" length should be less than or equal to the RESO maxlength of 150

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentDesignation @DD1.5_CoBuyerAgentDesignation @DD1.6_CoBuyerAgentDesignation
  Scenario: CoBuyerAgentDesignation
    Given "CoBuyerAgentDesignation" exists in the metadata
    And "CoBuyerAgentDesignation" string values are not null
    Then "CoBuyerAgentDesignation" should be String data type
    And "CoBuyerAgentDesignation" length should be between the bounds in the metadata
    And "CoBuyerAgentDesignation" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentFullName @IDX_Payload @DD1.5_CoBuyerAgentFullName @DD1.6_CoBuyerAgentFullName
  Scenario: CoBuyerAgentFullName
    Given "CoBuyerAgentFullName" exists in the metadata
    And "CoBuyerAgentFullName" string values are not null
    Then "CoBuyerAgentFullName" should be String data type
    And "CoBuyerAgentFullName" length should be between the bounds in the metadata
    And "CoBuyerAgentFullName" length should be less than or equal to the RESO maxlength of 150

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentDesignation @DD1.5_CoListAgentDesignation @DD1.6_CoListAgentDesignation
  Scenario: CoListAgentDesignation
    Given "CoListAgentDesignation" exists in the metadata
    And "CoListAgentDesignation" string values are not null
    Then "CoListAgentDesignation" should be String data type
    And "CoListAgentDesignation" length should be between the bounds in the metadata
    And "CoListAgentDesignation" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentFullName @IDX_Payload @DD1.5_CoListAgentFullName @DD1.6_CoListAgentFullName
  Scenario: CoListAgentFullName
    Given "CoListAgentFullName" exists in the metadata
    And "CoListAgentFullName" string values are not null
    Then "CoListAgentFullName" should be String data type
    And "CoListAgentFullName" length should be between the bounds in the metadata
    And "CoListAgentFullName" length should be less than or equal to the RESO maxlength of 150

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentDesignation @DD1.5_ListAgentDesignation @DD1.6_ListAgentDesignation
  Scenario: ListAgentDesignation
    Given "ListAgentDesignation" exists in the metadata
    And "ListAgentDesignation" string values are not null
    Then "ListAgentDesignation" should be String data type
    And "ListAgentDesignation" length should be between the bounds in the metadata
    And "ListAgentDesignation" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentFullName @IDX_Payload @DD1.5_ListAgentFullName @DD1.6_ListAgentFullName
  Scenario: ListAgentFullName
    Given "ListAgentFullName" exists in the metadata
    And "ListAgentFullName" string values are not null
    Then "ListAgentFullName" should be String data type
    And "ListAgentFullName" length should be between the bounds in the metadata
    And "ListAgentFullName" length should be less than or equal to the RESO maxlength of 150

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuilderName @DD1.5_BuilderName @DD1.6_BuilderName
  Scenario: BuilderName
    Given "BuilderName" exists in the metadata
    And "BuilderName" string values are not null
    Then "BuilderName" should be String data type
    And "BuilderName" length should be between the bounds in the metadata
    And "BuilderName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentCellPhone @DD1.5_BuyerAgentCellPhone @DD1.6_BuyerAgentCellPhone
  Scenario: BuyerAgentCellPhone
    Given "BuyerAgentCellPhone" exists in the metadata
    And "BuyerAgentCellPhone" string values are not null
    Then "BuyerAgentCellPhone" should be String data type
    And "BuyerAgentCellPhone" length should be between the bounds in the metadata
    And "BuyerAgentCellPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentDirectPhone @DD1.5_BuyerAgentDirectPhone @DD1.6_BuyerAgentDirectPhone
  Scenario: BuyerAgentDirectPhone
    Given "BuyerAgentDirectPhone" exists in the metadata
    And "BuyerAgentDirectPhone" string values are not null
    Then "BuyerAgentDirectPhone" should be String data type
    And "BuyerAgentDirectPhone" length should be between the bounds in the metadata
    And "BuyerAgentDirectPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentEmail @DD1.5_BuyerAgentEmail @DD1.6_BuyerAgentEmail
  Scenario: BuyerAgentEmail
    Given "BuyerAgentEmail" exists in the metadata
    And "BuyerAgentEmail" string values are not null
    Then "BuyerAgentEmail" should be String data type
    And "BuyerAgentEmail" length should be between the bounds in the metadata
    And "BuyerAgentEmail" length should be less than or equal to the RESO maxlength of 80

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentFax @DD1.5_BuyerAgentFax @DD1.6_BuyerAgentFax
  Scenario: BuyerAgentFax
    Given "BuyerAgentFax" exists in the metadata
    And "BuyerAgentFax" string values are not null
    Then "BuyerAgentFax" should be String data type
    And "BuyerAgentFax" length should be between the bounds in the metadata
    And "BuyerAgentFax" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentFirstName @IDX_Payload @DD1.5_BuyerAgentFirstName @DD1.6_BuyerAgentFirstName
  Scenario: BuyerAgentFirstName
    Given "BuyerAgentFirstName" exists in the metadata
    And "BuyerAgentFirstName" string values are not null
    Then "BuyerAgentFirstName" should be String data type
    And "BuyerAgentFirstName" length should be between the bounds in the metadata
    And "BuyerAgentFirstName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentLastName @IDX_Payload @DD1.5_BuyerAgentLastName @DD1.6_BuyerAgentLastName
  Scenario: BuyerAgentLastName
    Given "BuyerAgentLastName" exists in the metadata
    And "BuyerAgentLastName" string values are not null
    Then "BuyerAgentLastName" should be String data type
    And "BuyerAgentLastName" length should be between the bounds in the metadata
    And "BuyerAgentLastName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentMiddleName @DD1.5_BuyerAgentMiddleName @DD1.6_BuyerAgentMiddleName
  Scenario: BuyerAgentMiddleName
    Given "BuyerAgentMiddleName" exists in the metadata
    And "BuyerAgentMiddleName" string values are not null
    Then "BuyerAgentMiddleName" should be String data type
    And "BuyerAgentMiddleName" length should be between the bounds in the metadata
    And "BuyerAgentMiddleName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentOfficePhone @DD1.5_BuyerAgentOfficePhone @DD1.6_BuyerAgentOfficePhone
  Scenario: BuyerAgentOfficePhone
    Given "BuyerAgentOfficePhone" exists in the metadata
    And "BuyerAgentOfficePhone" string values are not null
    Then "BuyerAgentOfficePhone" should be String data type
    And "BuyerAgentOfficePhone" length should be between the bounds in the metadata
    And "BuyerAgentOfficePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentPager @DD1.5_BuyerAgentPager @DD1.6_BuyerAgentPager
  Scenario: BuyerAgentPager
    Given "BuyerAgentPager" exists in the metadata
    And "BuyerAgentPager" string values are not null
    Then "BuyerAgentPager" should be String data type
    And "BuyerAgentPager" length should be between the bounds in the metadata
    And "BuyerAgentPager" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentPreferredPhone @DD1.5_BuyerAgentPreferredPhone @DD1.6_BuyerAgentPreferredPhone
  Scenario: BuyerAgentPreferredPhone
    Given "BuyerAgentPreferredPhone" exists in the metadata
    And "BuyerAgentPreferredPhone" string values are not null
    Then "BuyerAgentPreferredPhone" should be String data type
    And "BuyerAgentPreferredPhone" length should be between the bounds in the metadata
    And "BuyerAgentPreferredPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentStateLicense @DD1.5_BuyerAgentStateLicense @DD1.6_BuyerAgentStateLicense
  Scenario: BuyerAgentStateLicense
    Given "BuyerAgentStateLicense" exists in the metadata
    And "BuyerAgentStateLicense" string values are not null
    Then "BuyerAgentStateLicense" should be String data type
    And "BuyerAgentStateLicense" length should be between the bounds in the metadata
    And "BuyerAgentStateLicense" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentTollFreePhone @DD1.5_BuyerAgentTollFreePhone @DD1.6_BuyerAgentTollFreePhone
  Scenario: BuyerAgentTollFreePhone
    Given "BuyerAgentTollFreePhone" exists in the metadata
    And "BuyerAgentTollFreePhone" string values are not null
    Then "BuyerAgentTollFreePhone" should be String data type
    And "BuyerAgentTollFreePhone" length should be between the bounds in the metadata
    And "BuyerAgentTollFreePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentURL @DD1.5_BuyerAgentURL @DD1.6_BuyerAgentURL
  Scenario: BuyerAgentURL
    Given "BuyerAgentURL" exists in the metadata
    And "BuyerAgentURL" string values are not null
    Then "BuyerAgentURL" should be String data type
    And "BuyerAgentURL" length should be between the bounds in the metadata
    And "BuyerAgentURL" length should be less than or equal to the RESO maxlength of 8000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerAgentVoiceMail @DD1.5_BuyerAgentVoiceMail @DD1.6_BuyerAgentVoiceMail
  Scenario: BuyerAgentVoiceMail
    Given "BuyerAgentVoiceMail" exists in the metadata
    And "BuyerAgentVoiceMail" string values are not null
    Then "BuyerAgentVoiceMail" should be String data type
    And "BuyerAgentVoiceMail" length should be between the bounds in the metadata
    And "BuyerAgentVoiceMail" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerOfficeEmail @DD1.5_BuyerOfficeEmail @DD1.6_BuyerOfficeEmail
  Scenario: BuyerOfficeEmail
    Given "BuyerOfficeEmail" exists in the metadata
    And "BuyerOfficeEmail" string values are not null
    Then "BuyerOfficeEmail" should be String data type
    And "BuyerOfficeEmail" length should be between the bounds in the metadata
    And "BuyerOfficeEmail" length should be less than or equal to the RESO maxlength of 80

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerOfficeFax @DD1.5_BuyerOfficeFax @DD1.6_BuyerOfficeFax
  Scenario: BuyerOfficeFax
    Given "BuyerOfficeFax" exists in the metadata
    And "BuyerOfficeFax" string values are not null
    Then "BuyerOfficeFax" should be String data type
    And "BuyerOfficeFax" length should be between the bounds in the metadata
    And "BuyerOfficeFax" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerOfficeName @IDX_Payload @DD1.5_BuyerOfficeName @DD1.6_BuyerOfficeName
  Scenario: BuyerOfficeName
    Given "BuyerOfficeName" exists in the metadata
    And "BuyerOfficeName" string values are not null
    Then "BuyerOfficeName" should be String data type
    And "BuyerOfficeName" length should be between the bounds in the metadata
    And "BuyerOfficeName" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerOfficePhone @DD1.5_BuyerOfficePhone @DD1.6_BuyerOfficePhone
  Scenario: BuyerOfficePhone
    Given "BuyerOfficePhone" exists in the metadata
    And "BuyerOfficePhone" string values are not null
    Then "BuyerOfficePhone" should be String data type
    And "BuyerOfficePhone" length should be between the bounds in the metadata
    And "BuyerOfficePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @BuyerOfficeURL @DD1.5_BuyerOfficeURL @DD1.6_BuyerOfficeURL
  Scenario: BuyerOfficeURL
    Given "BuyerOfficeURL" exists in the metadata
    And "BuyerOfficeURL" string values are not null
    Then "BuyerOfficeURL" should be String data type
    And "BuyerOfficeURL" length should be between the bounds in the metadata
    And "BuyerOfficeURL" length should be less than or equal to the RESO maxlength of 8000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentCellPhone @DD1.5_CoBuyerAgentCellPhone @DD1.6_CoBuyerAgentCellPhone
  Scenario: CoBuyerAgentCellPhone
    Given "CoBuyerAgentCellPhone" exists in the metadata
    And "CoBuyerAgentCellPhone" string values are not null
    Then "CoBuyerAgentCellPhone" should be String data type
    And "CoBuyerAgentCellPhone" length should be between the bounds in the metadata
    And "CoBuyerAgentCellPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentDirectPhone @DD1.5_CoBuyerAgentDirectPhone @DD1.6_CoBuyerAgentDirectPhone
  Scenario: CoBuyerAgentDirectPhone
    Given "CoBuyerAgentDirectPhone" exists in the metadata
    And "CoBuyerAgentDirectPhone" string values are not null
    Then "CoBuyerAgentDirectPhone" should be String data type
    And "CoBuyerAgentDirectPhone" length should be between the bounds in the metadata
    And "CoBuyerAgentDirectPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentEmail @DD1.5_CoBuyerAgentEmail @DD1.6_CoBuyerAgentEmail
  Scenario: CoBuyerAgentEmail
    Given "CoBuyerAgentEmail" exists in the metadata
    And "CoBuyerAgentEmail" string values are not null
    Then "CoBuyerAgentEmail" should be String data type
    And "CoBuyerAgentEmail" length should be between the bounds in the metadata
    And "CoBuyerAgentEmail" length should be less than or equal to the RESO maxlength of 80

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentFax @DD1.5_CoBuyerAgentFax @DD1.6_CoBuyerAgentFax
  Scenario: CoBuyerAgentFax
    Given "CoBuyerAgentFax" exists in the metadata
    And "CoBuyerAgentFax" string values are not null
    Then "CoBuyerAgentFax" should be String data type
    And "CoBuyerAgentFax" length should be between the bounds in the metadata
    And "CoBuyerAgentFax" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentFirstName @IDX_Payload @DD1.5_CoBuyerAgentFirstName @DD1.6_CoBuyerAgentFirstName
  Scenario: CoBuyerAgentFirstName
    Given "CoBuyerAgentFirstName" exists in the metadata
    And "CoBuyerAgentFirstName" string values are not null
    Then "CoBuyerAgentFirstName" should be String data type
    And "CoBuyerAgentFirstName" length should be between the bounds in the metadata
    And "CoBuyerAgentFirstName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentLastName @IDX_Payload @DD1.5_CoBuyerAgentLastName @DD1.6_CoBuyerAgentLastName
  Scenario: CoBuyerAgentLastName
    Given "CoBuyerAgentLastName" exists in the metadata
    And "CoBuyerAgentLastName" string values are not null
    Then "CoBuyerAgentLastName" should be String data type
    And "CoBuyerAgentLastName" length should be between the bounds in the metadata
    And "CoBuyerAgentLastName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentMiddleName @DD1.5_CoBuyerAgentMiddleName @DD1.6_CoBuyerAgentMiddleName
  Scenario: CoBuyerAgentMiddleName
    Given "CoBuyerAgentMiddleName" exists in the metadata
    And "CoBuyerAgentMiddleName" string values are not null
    Then "CoBuyerAgentMiddleName" should be String data type
    And "CoBuyerAgentMiddleName" length should be between the bounds in the metadata
    And "CoBuyerAgentMiddleName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentOfficePhone @DD1.5_CoBuyerAgentOfficePhone @DD1.6_CoBuyerAgentOfficePhone
  Scenario: CoBuyerAgentOfficePhone
    Given "CoBuyerAgentOfficePhone" exists in the metadata
    And "CoBuyerAgentOfficePhone" string values are not null
    Then "CoBuyerAgentOfficePhone" should be String data type
    And "CoBuyerAgentOfficePhone" length should be between the bounds in the metadata
    And "CoBuyerAgentOfficePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentPager @DD1.5_CoBuyerAgentPager @DD1.6_CoBuyerAgentPager
  Scenario: CoBuyerAgentPager
    Given "CoBuyerAgentPager" exists in the metadata
    And "CoBuyerAgentPager" string values are not null
    Then "CoBuyerAgentPager" should be String data type
    And "CoBuyerAgentPager" length should be between the bounds in the metadata
    And "CoBuyerAgentPager" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentPreferredPhone @DD1.5_CoBuyerAgentPreferredPhone @DD1.6_CoBuyerAgentPreferredPhone
  Scenario: CoBuyerAgentPreferredPhone
    Given "CoBuyerAgentPreferredPhone" exists in the metadata
    And "CoBuyerAgentPreferredPhone" string values are not null
    Then "CoBuyerAgentPreferredPhone" should be String data type
    And "CoBuyerAgentPreferredPhone" length should be between the bounds in the metadata
    And "CoBuyerAgentPreferredPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentStateLicense @DD1.5_CoBuyerAgentStateLicense @DD1.6_CoBuyerAgentStateLicense
  Scenario: CoBuyerAgentStateLicense
    Given "CoBuyerAgentStateLicense" exists in the metadata
    And "CoBuyerAgentStateLicense" string values are not null
    Then "CoBuyerAgentStateLicense" should be String data type
    And "CoBuyerAgentStateLicense" length should be between the bounds in the metadata
    And "CoBuyerAgentStateLicense" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentTollFreePhone @DD1.5_CoBuyerAgentTollFreePhone @DD1.6_CoBuyerAgentTollFreePhone
  Scenario: CoBuyerAgentTollFreePhone
    Given "CoBuyerAgentTollFreePhone" exists in the metadata
    And "CoBuyerAgentTollFreePhone" string values are not null
    Then "CoBuyerAgentTollFreePhone" should be String data type
    And "CoBuyerAgentTollFreePhone" length should be between the bounds in the metadata
    And "CoBuyerAgentTollFreePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentURL @DD1.5_CoBuyerAgentURL @DD1.6_CoBuyerAgentURL
  Scenario: CoBuyerAgentURL
    Given "CoBuyerAgentURL" exists in the metadata
    And "CoBuyerAgentURL" string values are not null
    Then "CoBuyerAgentURL" should be String data type
    And "CoBuyerAgentURL" length should be between the bounds in the metadata
    And "CoBuyerAgentURL" length should be less than or equal to the RESO maxlength of 8000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerAgentVoiceMail @DD1.5_CoBuyerAgentVoiceMail @DD1.6_CoBuyerAgentVoiceMail
  Scenario: CoBuyerAgentVoiceMail
    Given "CoBuyerAgentVoiceMail" exists in the metadata
    And "CoBuyerAgentVoiceMail" string values are not null
    Then "CoBuyerAgentVoiceMail" should be String data type
    And "CoBuyerAgentVoiceMail" length should be between the bounds in the metadata
    And "CoBuyerAgentVoiceMail" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerOfficeEmail @DD1.5_CoBuyerOfficeEmail @DD1.6_CoBuyerOfficeEmail
  Scenario: CoBuyerOfficeEmail
    Given "CoBuyerOfficeEmail" exists in the metadata
    And "CoBuyerOfficeEmail" string values are not null
    Then "CoBuyerOfficeEmail" should be String data type
    And "CoBuyerOfficeEmail" length should be between the bounds in the metadata
    And "CoBuyerOfficeEmail" length should be less than or equal to the RESO maxlength of 80

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerOfficeFax @DD1.5_CoBuyerOfficeFax @DD1.6_CoBuyerOfficeFax
  Scenario: CoBuyerOfficeFax
    Given "CoBuyerOfficeFax" exists in the metadata
    And "CoBuyerOfficeFax" string values are not null
    Then "CoBuyerOfficeFax" should be String data type
    And "CoBuyerOfficeFax" length should be between the bounds in the metadata
    And "CoBuyerOfficeFax" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerOfficeName @IDX_Payload @DD1.5_CoBuyerOfficeName @DD1.6_CoBuyerOfficeName
  Scenario: CoBuyerOfficeName
    Given "CoBuyerOfficeName" exists in the metadata
    And "CoBuyerOfficeName" string values are not null
    Then "CoBuyerOfficeName" should be String data type
    And "CoBuyerOfficeName" length should be between the bounds in the metadata
    And "CoBuyerOfficeName" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerOfficePhone @DD1.5_CoBuyerOfficePhone @DD1.6_CoBuyerOfficePhone
  Scenario: CoBuyerOfficePhone
    Given "CoBuyerOfficePhone" exists in the metadata
    And "CoBuyerOfficePhone" string values are not null
    Then "CoBuyerOfficePhone" should be String data type
    And "CoBuyerOfficePhone" length should be between the bounds in the metadata
    And "CoBuyerOfficePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoBuyerOfficeURL @DD1.5_CoBuyerOfficeURL @DD1.6_CoBuyerOfficeURL
  Scenario: CoBuyerOfficeURL
    Given "CoBuyerOfficeURL" exists in the metadata
    And "CoBuyerOfficeURL" string values are not null
    Then "CoBuyerOfficeURL" should be String data type
    And "CoBuyerOfficeURL" length should be between the bounds in the metadata
    And "CoBuyerOfficeURL" length should be less than or equal to the RESO maxlength of 8000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentCellPhone @DD1.5_CoListAgentCellPhone @DD1.6_CoListAgentCellPhone
  Scenario: CoListAgentCellPhone
    Given "CoListAgentCellPhone" exists in the metadata
    And "CoListAgentCellPhone" string values are not null
    Then "CoListAgentCellPhone" should be String data type
    And "CoListAgentCellPhone" length should be between the bounds in the metadata
    And "CoListAgentCellPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentDirectPhone @DD1.5_CoListAgentDirectPhone @DD1.6_CoListAgentDirectPhone
  Scenario: CoListAgentDirectPhone
    Given "CoListAgentDirectPhone" exists in the metadata
    And "CoListAgentDirectPhone" string values are not null
    Then "CoListAgentDirectPhone" should be String data type
    And "CoListAgentDirectPhone" length should be between the bounds in the metadata
    And "CoListAgentDirectPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentEmail @DD1.5_CoListAgentEmail @DD1.6_CoListAgentEmail
  Scenario: CoListAgentEmail
    Given "CoListAgentEmail" exists in the metadata
    And "CoListAgentEmail" string values are not null
    Then "CoListAgentEmail" should be String data type
    And "CoListAgentEmail" length should be between the bounds in the metadata
    And "CoListAgentEmail" length should be less than or equal to the RESO maxlength of 80

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentFax @DD1.5_CoListAgentFax @DD1.6_CoListAgentFax
  Scenario: CoListAgentFax
    Given "CoListAgentFax" exists in the metadata
    And "CoListAgentFax" string values are not null
    Then "CoListAgentFax" should be String data type
    And "CoListAgentFax" length should be between the bounds in the metadata
    And "CoListAgentFax" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentFirstName @IDX_Payload @DD1.5_CoListAgentFirstName @DD1.6_CoListAgentFirstName
  Scenario: CoListAgentFirstName
    Given "CoListAgentFirstName" exists in the metadata
    And "CoListAgentFirstName" string values are not null
    Then "CoListAgentFirstName" should be String data type
    And "CoListAgentFirstName" length should be between the bounds in the metadata
    And "CoListAgentFirstName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentLastName @IDX_Payload @DD1.5_CoListAgentLastName @DD1.6_CoListAgentLastName
  Scenario: CoListAgentLastName
    Given "CoListAgentLastName" exists in the metadata
    And "CoListAgentLastName" string values are not null
    Then "CoListAgentLastName" should be String data type
    And "CoListAgentLastName" length should be between the bounds in the metadata
    And "CoListAgentLastName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentMiddleName @DD1.5_CoListAgentMiddleName @DD1.6_CoListAgentMiddleName
  Scenario: CoListAgentMiddleName
    Given "CoListAgentMiddleName" exists in the metadata
    And "CoListAgentMiddleName" string values are not null
    Then "CoListAgentMiddleName" should be String data type
    And "CoListAgentMiddleName" length should be between the bounds in the metadata
    And "CoListAgentMiddleName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentOfficePhone @DD1.5_CoListAgentOfficePhone @DD1.6_CoListAgentOfficePhone
  Scenario: CoListAgentOfficePhone
    Given "CoListAgentOfficePhone" exists in the metadata
    And "CoListAgentOfficePhone" string values are not null
    Then "CoListAgentOfficePhone" should be String data type
    And "CoListAgentOfficePhone" length should be between the bounds in the metadata
    And "CoListAgentOfficePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentOfficePhoneExt @DD1.5_CoListAgentOfficePhoneExt @DD1.6_CoListAgentOfficePhoneExt
  Scenario: CoListAgentOfficePhoneExt
    Given "CoListAgentOfficePhoneExt" exists in the metadata
    And "CoListAgentOfficePhoneExt" string values are not null
    Then "CoListAgentOfficePhoneExt" should be String data type
    And "CoListAgentOfficePhoneExt" length should be between the bounds in the metadata
    And "CoListAgentOfficePhoneExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentPager @DD1.5_CoListAgentPager @DD1.6_CoListAgentPager
  Scenario: CoListAgentPager
    Given "CoListAgentPager" exists in the metadata
    And "CoListAgentPager" string values are not null
    Then "CoListAgentPager" should be String data type
    And "CoListAgentPager" length should be between the bounds in the metadata
    And "CoListAgentPager" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentPreferredPhone @DD1.5_CoListAgentPreferredPhone @DD1.6_CoListAgentPreferredPhone
  Scenario: CoListAgentPreferredPhone
    Given "CoListAgentPreferredPhone" exists in the metadata
    And "CoListAgentPreferredPhone" string values are not null
    Then "CoListAgentPreferredPhone" should be String data type
    And "CoListAgentPreferredPhone" length should be between the bounds in the metadata
    And "CoListAgentPreferredPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentPreferredPhoneExt @DD1.5_CoListAgentPreferredPhoneExt @DD1.6_CoListAgentPreferredPhoneExt
  Scenario: CoListAgentPreferredPhoneExt
    Given "CoListAgentPreferredPhoneExt" exists in the metadata
    And "CoListAgentPreferredPhoneExt" string values are not null
    Then "CoListAgentPreferredPhoneExt" should be String data type
    And "CoListAgentPreferredPhoneExt" length should be between the bounds in the metadata
    And "CoListAgentPreferredPhoneExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentStateLicense @DD1.5_CoListAgentStateLicense @DD1.6_CoListAgentStateLicense
  Scenario: CoListAgentStateLicense
    Given "CoListAgentStateLicense" exists in the metadata
    And "CoListAgentStateLicense" string values are not null
    Then "CoListAgentStateLicense" should be String data type
    And "CoListAgentStateLicense" length should be between the bounds in the metadata
    And "CoListAgentStateLicense" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentTollFreePhone @DD1.5_CoListAgentTollFreePhone @DD1.6_CoListAgentTollFreePhone
  Scenario: CoListAgentTollFreePhone
    Given "CoListAgentTollFreePhone" exists in the metadata
    And "CoListAgentTollFreePhone" string values are not null
    Then "CoListAgentTollFreePhone" should be String data type
    And "CoListAgentTollFreePhone" length should be between the bounds in the metadata
    And "CoListAgentTollFreePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentURL @DD1.5_CoListAgentURL @DD1.6_CoListAgentURL
  Scenario: CoListAgentURL
    Given "CoListAgentURL" exists in the metadata
    And "CoListAgentURL" string values are not null
    Then "CoListAgentURL" should be String data type
    And "CoListAgentURL" length should be between the bounds in the metadata
    And "CoListAgentURL" length should be less than or equal to the RESO maxlength of 8000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentVoiceMail @DD1.5_CoListAgentVoiceMail @DD1.6_CoListAgentVoiceMail
  Scenario: CoListAgentVoiceMail
    Given "CoListAgentVoiceMail" exists in the metadata
    And "CoListAgentVoiceMail" string values are not null
    Then "CoListAgentVoiceMail" should be String data type
    And "CoListAgentVoiceMail" length should be between the bounds in the metadata
    And "CoListAgentVoiceMail" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListAgentVoiceMailExt @DD1.5_CoListAgentVoiceMailExt @DD1.6_CoListAgentVoiceMailExt
  Scenario: CoListAgentVoiceMailExt
    Given "CoListAgentVoiceMailExt" exists in the metadata
    And "CoListAgentVoiceMailExt" string values are not null
    Then "CoListAgentVoiceMailExt" should be String data type
    And "CoListAgentVoiceMailExt" length should be between the bounds in the metadata
    And "CoListAgentVoiceMailExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListOfficeEmail @DD1.5_CoListOfficeEmail @DD1.6_CoListOfficeEmail
  Scenario: CoListOfficeEmail
    Given "CoListOfficeEmail" exists in the metadata
    And "CoListOfficeEmail" string values are not null
    Then "CoListOfficeEmail" should be String data type
    And "CoListOfficeEmail" length should be between the bounds in the metadata
    And "CoListOfficeEmail" length should be less than or equal to the RESO maxlength of 80

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListOfficeFax @DD1.5_CoListOfficeFax @DD1.6_CoListOfficeFax
  Scenario: CoListOfficeFax
    Given "CoListOfficeFax" exists in the metadata
    And "CoListOfficeFax" string values are not null
    Then "CoListOfficeFax" should be String data type
    And "CoListOfficeFax" length should be between the bounds in the metadata
    And "CoListOfficeFax" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListOfficeName @IDX_Payload @DD1.5_CoListOfficeName @DD1.6_CoListOfficeName
  Scenario: CoListOfficeName
    Given "CoListOfficeName" exists in the metadata
    And "CoListOfficeName" string values are not null
    Then "CoListOfficeName" should be String data type
    And "CoListOfficeName" length should be between the bounds in the metadata
    And "CoListOfficeName" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListOfficePhone @DD1.5_CoListOfficePhone @DD1.6_CoListOfficePhone
  Scenario: CoListOfficePhone
    Given "CoListOfficePhone" exists in the metadata
    And "CoListOfficePhone" string values are not null
    Then "CoListOfficePhone" should be String data type
    And "CoListOfficePhone" length should be between the bounds in the metadata
    And "CoListOfficePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListOfficePhoneExt @DD1.5_CoListOfficePhoneExt @DD1.6_CoListOfficePhoneExt
  Scenario: CoListOfficePhoneExt
    Given "CoListOfficePhoneExt" exists in the metadata
    And "CoListOfficePhoneExt" string values are not null
    Then "CoListOfficePhoneExt" should be String data type
    And "CoListOfficePhoneExt" length should be between the bounds in the metadata
    And "CoListOfficePhoneExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @CoListOfficeURL @DD1.5_CoListOfficeURL @DD1.6_CoListOfficeURL
  Scenario: CoListOfficeURL
    Given "CoListOfficeURL" exists in the metadata
    And "CoListOfficeURL" string values are not null
    Then "CoListOfficeURL" should be String data type
    And "CoListOfficeURL" length should be between the bounds in the metadata
    And "CoListOfficeURL" length should be less than or equal to the RESO maxlength of 8000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentCellPhone @DD1.5_ListAgentCellPhone @DD1.6_ListAgentCellPhone
  Scenario: ListAgentCellPhone
    Given "ListAgentCellPhone" exists in the metadata
    And "ListAgentCellPhone" string values are not null
    Then "ListAgentCellPhone" should be String data type
    And "ListAgentCellPhone" length should be between the bounds in the metadata
    And "ListAgentCellPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentDirectPhone @DD1.5_ListAgentDirectPhone @DD1.6_ListAgentDirectPhone
  Scenario: ListAgentDirectPhone
    Given "ListAgentDirectPhone" exists in the metadata
    And "ListAgentDirectPhone" string values are not null
    Then "ListAgentDirectPhone" should be String data type
    And "ListAgentDirectPhone" length should be between the bounds in the metadata
    And "ListAgentDirectPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentEmail @DD1.5_ListAgentEmail @DD1.6_ListAgentEmail
  Scenario: ListAgentEmail
    Given "ListAgentEmail" exists in the metadata
    And "ListAgentEmail" string values are not null
    Then "ListAgentEmail" should be String data type
    And "ListAgentEmail" length should be between the bounds in the metadata
    And "ListAgentEmail" length should be less than or equal to the RESO maxlength of 80

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentFax @DD1.5_ListAgentFax @DD1.6_ListAgentFax
  Scenario: ListAgentFax
    Given "ListAgentFax" exists in the metadata
    And "ListAgentFax" string values are not null
    Then "ListAgentFax" should be String data type
    And "ListAgentFax" length should be between the bounds in the metadata
    And "ListAgentFax" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentFirstName @IDX_Payload @DD1.5_ListAgentFirstName @DD1.6_ListAgentFirstName
  Scenario: ListAgentFirstName
    Given "ListAgentFirstName" exists in the metadata
    And "ListAgentFirstName" string values are not null
    Then "ListAgentFirstName" should be String data type
    And "ListAgentFirstName" length should be between the bounds in the metadata
    And "ListAgentFirstName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentLastName @IDX_Payload @DD1.5_ListAgentLastName @DD1.6_ListAgentLastName
  Scenario: ListAgentLastName
    Given "ListAgentLastName" exists in the metadata
    And "ListAgentLastName" string values are not null
    Then "ListAgentLastName" should be String data type
    And "ListAgentLastName" length should be between the bounds in the metadata
    And "ListAgentLastName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentMiddleName @DD1.5_ListAgentMiddleName @DD1.6_ListAgentMiddleName
  Scenario: ListAgentMiddleName
    Given "ListAgentMiddleName" exists in the metadata
    And "ListAgentMiddleName" string values are not null
    Then "ListAgentMiddleName" should be String data type
    And "ListAgentMiddleName" length should be between the bounds in the metadata
    And "ListAgentMiddleName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentOfficePhone @DD1.5_ListAgentOfficePhone @DD1.6_ListAgentOfficePhone
  Scenario: ListAgentOfficePhone
    Given "ListAgentOfficePhone" exists in the metadata
    And "ListAgentOfficePhone" string values are not null
    Then "ListAgentOfficePhone" should be String data type
    And "ListAgentOfficePhone" length should be between the bounds in the metadata
    And "ListAgentOfficePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentOfficePhoneExt @DD1.5_ListAgentOfficePhoneExt @DD1.6_ListAgentOfficePhoneExt
  Scenario: ListAgentOfficePhoneExt
    Given "ListAgentOfficePhoneExt" exists in the metadata
    And "ListAgentOfficePhoneExt" string values are not null
    Then "ListAgentOfficePhoneExt" should be String data type
    And "ListAgentOfficePhoneExt" length should be between the bounds in the metadata
    And "ListAgentOfficePhoneExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentPager @DD1.5_ListAgentPager @DD1.6_ListAgentPager
  Scenario: ListAgentPager
    Given "ListAgentPager" exists in the metadata
    And "ListAgentPager" string values are not null
    Then "ListAgentPager" should be String data type
    And "ListAgentPager" length should be between the bounds in the metadata
    And "ListAgentPager" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentPreferredPhone @DD1.5_ListAgentPreferredPhone @DD1.6_ListAgentPreferredPhone
  Scenario: ListAgentPreferredPhone
    Given "ListAgentPreferredPhone" exists in the metadata
    And "ListAgentPreferredPhone" string values are not null
    Then "ListAgentPreferredPhone" should be String data type
    And "ListAgentPreferredPhone" length should be between the bounds in the metadata
    And "ListAgentPreferredPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentPreferredPhoneExt @DD1.5_ListAgentPreferredPhoneExt @DD1.6_ListAgentPreferredPhoneExt
  Scenario: ListAgentPreferredPhoneExt
    Given "ListAgentPreferredPhoneExt" exists in the metadata
    And "ListAgentPreferredPhoneExt" string values are not null
    Then "ListAgentPreferredPhoneExt" should be String data type
    And "ListAgentPreferredPhoneExt" length should be between the bounds in the metadata
    And "ListAgentPreferredPhoneExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentStateLicense @DD1.5_ListAgentStateLicense @DD1.6_ListAgentStateLicense
  Scenario: ListAgentStateLicense
    Given "ListAgentStateLicense" exists in the metadata
    And "ListAgentStateLicense" string values are not null
    Then "ListAgentStateLicense" should be String data type
    And "ListAgentStateLicense" length should be between the bounds in the metadata
    And "ListAgentStateLicense" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentTollFreePhone @DD1.5_ListAgentTollFreePhone @DD1.6_ListAgentTollFreePhone
  Scenario: ListAgentTollFreePhone
    Given "ListAgentTollFreePhone" exists in the metadata
    And "ListAgentTollFreePhone" string values are not null
    Then "ListAgentTollFreePhone" should be String data type
    And "ListAgentTollFreePhone" length should be between the bounds in the metadata
    And "ListAgentTollFreePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentURL @DD1.5_ListAgentURL @DD1.6_ListAgentURL
  Scenario: ListAgentURL
    Given "ListAgentURL" exists in the metadata
    And "ListAgentURL" string values are not null
    Then "ListAgentURL" should be String data type
    And "ListAgentURL" length should be between the bounds in the metadata
    And "ListAgentURL" length should be less than or equal to the RESO maxlength of 8000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentVoiceMail @DD1.5_ListAgentVoiceMail @DD1.6_ListAgentVoiceMail
  Scenario: ListAgentVoiceMail
    Given "ListAgentVoiceMail" exists in the metadata
    And "ListAgentVoiceMail" string values are not null
    Then "ListAgentVoiceMail" should be String data type
    And "ListAgentVoiceMail" length should be between the bounds in the metadata
    And "ListAgentVoiceMail" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListAgentVoiceMailExt @DD1.5_ListAgentVoiceMailExt @DD1.6_ListAgentVoiceMailExt
  Scenario: ListAgentVoiceMailExt
    Given "ListAgentVoiceMailExt" exists in the metadata
    And "ListAgentVoiceMailExt" string values are not null
    Then "ListAgentVoiceMailExt" should be String data type
    And "ListAgentVoiceMailExt" length should be between the bounds in the metadata
    And "ListAgentVoiceMailExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListOfficeEmail @DD1.5_ListOfficeEmail @DD1.6_ListOfficeEmail
  Scenario: ListOfficeEmail
    Given "ListOfficeEmail" exists in the metadata
    And "ListOfficeEmail" string values are not null
    Then "ListOfficeEmail" should be String data type
    And "ListOfficeEmail" length should be between the bounds in the metadata
    And "ListOfficeEmail" length should be less than or equal to the RESO maxlength of 80

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListOfficeFax @DD1.5_ListOfficeFax @DD1.6_ListOfficeFax
  Scenario: ListOfficeFax
    Given "ListOfficeFax" exists in the metadata
    And "ListOfficeFax" string values are not null
    Then "ListOfficeFax" should be String data type
    And "ListOfficeFax" length should be between the bounds in the metadata
    And "ListOfficeFax" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListOfficeName @IDX_Payload @DD1.5_ListOfficeName @DD1.6_ListOfficeName
  Scenario: ListOfficeName
    Given "ListOfficeName" exists in the metadata
    And "ListOfficeName" string values are not null
    Then "ListOfficeName" should be String data type
    And "ListOfficeName" length should be between the bounds in the metadata
    And "ListOfficeName" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListOfficePhone @DD1.5_ListOfficePhone @DD1.6_ListOfficePhone
  Scenario: ListOfficePhone
    Given "ListOfficePhone" exists in the metadata
    And "ListOfficePhone" string values are not null
    Then "ListOfficePhone" should be String data type
    And "ListOfficePhone" length should be between the bounds in the metadata
    And "ListOfficePhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListOfficePhoneExt @DD1.5_ListOfficePhoneExt @DD1.6_ListOfficePhoneExt
  Scenario: ListOfficePhoneExt
    Given "ListOfficePhoneExt" exists in the metadata
    And "ListOfficePhoneExt" string values are not null
    Then "ListOfficePhoneExt" should be String data type
    And "ListOfficePhoneExt" length should be between the bounds in the metadata
    And "ListOfficePhoneExt" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListOfficeURL @DD1.5_ListOfficeURL @DD1.6_ListOfficeURL
  Scenario: ListOfficeURL
    Given "ListOfficeURL" exists in the metadata
    And "ListOfficeURL" string values are not null
    Then "ListOfficeURL" should be String data type
    And "ListOfficeURL" length should be between the bounds in the metadata
    And "ListOfficeURL" length should be less than or equal to the RESO maxlength of 8000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ListingId @IDX_Payload @DD1.5_ListingId @DD1.6_ListingId @testing
  Scenario: ListingId
    Given "ListingId" exists in the metadata
    And "ListingId" string values are not null
    Then "ListingId" should be String data type
    And "ListingId" length should be between the bounds in the metadata
    And "ListingId" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @OccupantName @DD1.5_OccupantName @DD1.6_OccupantName
  Scenario: OccupantName
    Given "OccupantName" exists in the metadata
    And "OccupantName" string values are not null
    Then "OccupantName" should be String data type
    And "OccupantName" length should be between the bounds in the metadata
    And "OccupantName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @OccupantPhone @DD1.5_OccupantPhone @DD1.6_OccupantPhone
  Scenario: OccupantPhone
    Given "OccupantPhone" exists in the metadata
    And "OccupantPhone" string values are not null
    Then "OccupantPhone" should be String data type
    And "OccupantPhone" length should be between the bounds in the metadata
    And "OccupantPhone" length should be less than or equal to the RESO maxlength of 16

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @OriginatingSystemKey @IDX_Payload @DD1.5_OriginatingSystemKey @DD1.6_OriginatingSystemKey
  Scenario: OriginatingSystemKey
    Given "OriginatingSystemKey" exists in the metadata
    And "OriginatingSystemKey" string values are not null
    Then "OriginatingSystemKey" should be String data type
    And "OriginatingSystemKey" length should be between the bounds in the metadata
    And "OriginatingSystemKey" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @OriginatingSystemName @IDX_Payload @DD1.5_OriginatingSystemName @DD1.6_OriginatingSystemName
  Scenario: OriginatingSystemName
    Given "OriginatingSystemName" exists in the metadata
    And "OriginatingSystemName" string values are not null
    Then "OriginatingSystemName" should be String data type
    And "OriginatingSystemName" length should be between the bounds in the metadata
    And "OriginatingSystemName" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @OwnerName @DD1.5_OwnerName @DD1.6_OwnerName
  Scenario: OwnerName
    Given "OwnerName" exists in the metadata
    And "OwnerName" string values are not null
    Then "OwnerName" should be String data type
    And "OwnerName" length should be between the bounds in the metadata
    And "OwnerName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @PostalCode @IDX_Payload @DD1.5_PostalCode @DD1.6_PostalCode
  Scenario: PostalCode
    Given "PostalCode" exists in the metadata
    And "PostalCode" string values are not null
    Then "PostalCode" should be String data type
    And "PostalCode" length should be between the bounds in the metadata
    And "PostalCode" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @PrivateOfficeRemarks @DD1.5_PrivateOfficeRemarks @DD1.6_PrivateOfficeRemarks
  Scenario: PrivateOfficeRemarks
    Given "PrivateOfficeRemarks" exists in the metadata
    And "PrivateOfficeRemarks" string values are not null
    Then "PrivateOfficeRemarks" should be String data type
    And "PrivateOfficeRemarks" length should be between the bounds in the metadata
    And "PrivateOfficeRemarks" length should be less than or equal to the RESO maxlength of 4000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @PrivateRemarks @DD1.5_PrivateRemarks @DD1.6_PrivateRemarks
  Scenario: PrivateRemarks
    Given "PrivateRemarks" exists in the metadata
    And "PrivateRemarks" string values are not null
    Then "PrivateRemarks" should be String data type
    And "PrivateRemarks" length should be between the bounds in the metadata
    And "PrivateRemarks" length should be less than or equal to the RESO maxlength of 4000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @PublicRemarks @IDX_Payload @DD1.5_PublicRemarks @DD1.6_PublicRemarks @testing
  Scenario: PublicRemarks
    Given "PublicRemarks" exists in the metadata
    And "PublicRemarks" string values are not null
    Then "PublicRemarks" should be String data type
    And "PublicRemarks" length should be between the bounds in the metadata
    And "PublicRemarks" length should be less than or equal to the RESO maxlength of 4000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @ShowingInstructions @DD1.5_ShowingInstructions @DD1.6_ShowingInstructions @testing
  Scenario: ShowingInstructions
    Given "ShowingInstructions" exists in the metadata
    And "ShowingInstructions" string values are not null
    Then "ShowingInstructions" should be String data type
    And "ShowingInstructions" length should be between the bounds in the metadata
    And "ShowingInstructions" length should be less than or equal to the RESO maxlength of 4000

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @SourceSystemKey @IDX_Payload @DD1.5_SourceSystemKey @DD1.6_SourceSystemKey
  Scenario: SourceSystemKey
    Given "SourceSystemKey" exists in the metadata
    And "SourceSystemKey" string values are not null
    Then "SourceSystemKey" should be String data type
    And "SourceSystemKey" length should be between the bounds in the metadata
    And "SourceSystemKey" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @SourceSystemName @IDX_Payload @DD1.5_SourceSystemName @DD1.6_SourceSystemName
  Scenario: SourceSystemName
    Given "SourceSystemName" exists in the metadata
    And "SourceSystemName" string values are not null
    Then "SourceSystemName" should be String data type
    And "SourceSystemName" length should be between the bounds in the metadata
    And "SourceSystemName" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @StreetAdditionalInfo @DD1.5_StreetAdditionalInfo @DD1.6_StreetAdditionalInfo
  Scenario: StreetAdditionalInfo
    Given "StreetAdditionalInfo" exists in the metadata
    And "StreetAdditionalInfo" string values are not null
    Then "StreetAdditionalInfo" should be String data type
    And "StreetAdditionalInfo" length should be between the bounds in the metadata
    And "StreetAdditionalInfo" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @StreetName @IDX_Payload @DD1.5_StreetName @DD1.6_StreetName @testing
  Scenario: StreetName
    Given "StreetName" exists in the metadata
    And "StreetName" string values are not null
    Then "StreetName" should be String data type
    And "StreetName" length should be between the bounds in the metadata
    And "StreetName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @StreetNumber @IDX_Payload @DD1.5_StreetNumber @DD1.6_StreetNumber
  Scenario: StreetNumber
    Given "StreetNumber" exists in the metadata
    And "StreetNumber" string values are not null
    Then "StreetNumber" should be String data type
    And "StreetNumber" length should be between the bounds in the metadata
    And "StreetNumber" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @SubdivisionName @IDX_Payload @DD1.5_SubdivisionName @DD1.6_SubdivisionName @testing
  Scenario: SubdivisionName
    Given "SubdivisionName" exists in the metadata
    And "SubdivisionName" string values are not null
    Then "SubdivisionName" should be String data type
    And "SubdivisionName" length should be between the bounds in the metadata
    And "SubdivisionName" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_String @DD1.6_String @DD1.5 @DD1.6 @UnparsedAddress @IDX_Payload @DD1.5_UnparsedAddress @DD1.6_UnparsedAddress
  Scenario: UnparsedAddress
    Given "UnparsedAddress" exists in the metadata
    And "UnparsedAddress" string values are not null
    Then "UnparsedAddress" should be String data type
    And "UnparsedAddress" length should be between the bounds in the metadata
    And "UnparsedAddress" length should be less than or equal to the RESO maxlength of 255

