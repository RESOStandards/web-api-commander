Feature: StringList-Single Testing

  Background:
    Given an XML Metadata file was provided
    And the given file exists
    And the file contains valid XML
    And the file could be read by the Commander

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @AboveGradeFinishedAreaSource @DD1.5_AboveGradeFinishedAreaSource @DD1.6_AboveGradeFinishedAreaSource
  Scenario: AboveGradeFinishedAreaSource
    Given "AboveGradeFinishedAreaSource" exists in the metadata
    And "AboveGradeFinishedAreaSource" enum values can be compiled
    And "AboveGradeFinishedAreaSource" values are not null
    Then "AboveGradeFinishedAreaSource" should be "String" data type
    And "AboveGradeFinishedAreaSource" should only contain enum values found in the metadata
    And "AboveGradeFinishedAreaSource" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @AboveGradeFinishedAreaUnits @DD1.5_AboveGradeFinishedAreaUnits @DD1.6_AboveGradeFinishedAreaUnits
  Scenario: AboveGradeFinishedAreaUnits
    Given "AboveGradeFinishedAreaUnits" exists in the metadata
    And "AboveGradeFinishedAreaUnits" enum values can be compiled
    And "AboveGradeFinishedAreaUnits" values are not null
    Then "AboveGradeFinishedAreaUnits" should be "String" data type
    And "AboveGradeFinishedAreaUnits" should only contain enum values found in the metadata
    And "AboveGradeFinishedAreaUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.5 @ApprovalStatus @DD1.5_ApprovalStatus
  Scenario: ApprovalStatus
    Given "ApprovalStatus" exists in the metadata
    And "ApprovalStatus" enum values can be compiled
    And "ApprovalStatus" values are not null
    Then "ApprovalStatus" should be "String" data type
    And "ApprovalStatus" should only contain enum values found in the metadata
    And "ApprovalStatus" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @AssociationFee @IDX_Payload @DD1.5_AssociationFee @DD1.6_AssociationFee2Frequency
  Scenario: AssociationFee2Frequency
    Given "AssociationFee2Frequency" exists in the metadata
    And "AssociationFee2Frequency" enum values can be compiled
    And "AssociationFee2Frequency" values are not null
    Then "AssociationFee2Frequency" should be "String" data type
    And "AssociationFee2Frequency" should only contain enum values found in the metadata
    And "AssociationFee2Frequency" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @AssociationFee @IDX_Payload @DD1.5_AssociationFeeFrequency @DD1.6_AssociationFeeFrequency
  Scenario: AssociationFeeFrequency
    Given "AssociationFeeFrequency" exists in the metadata
    And "AssociationFeeFrequency" enum values can be compiled
    And "AssociationFeeFrequency" values are not null
    Then "AssociationFeeFrequency" should be "String" data type
    And "AssociationFeeFrequency" should only contain enum values found in the metadata
    And "AssociationFeeFrequency" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @BelowGradeFinishedAreaSource @DD1.5_BelowGradeFinishedAreaSource @DD1.6_BelowGradeFinishedAreaSource
  Scenario: BelowGradeFinishedAreaSource
    Given "BelowGradeFinishedAreaSource" exists in the metadata
    And "BelowGradeFinishedAreaSource" enum values can be compiled
    And "BelowGradeFinishedAreaSource" values are not null
    Then "BelowGradeFinishedAreaSource" should be "String" data type
    And "BelowGradeFinishedAreaSource" should only contain enum values found in the metadata
    And "BelowGradeFinishedAreaSource" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @BelowGradeFinishedAreaUnits @DD1.5_BelowGradeFinishedAreaUnits @DD1.6_BelowGradeFinishedAreaUnits
  Scenario: BelowGradeFinishedAreaUnits
    Given "BelowGradeFinishedAreaUnits" exists in the metadata
    And "BelowGradeFinishedAreaUnits" enum values can be compiled
    And "BelowGradeFinishedAreaUnits" values are not null
    Then "BelowGradeFinishedAreaUnits" should be "String" data type
    And "BelowGradeFinishedAreaUnits" should only contain enum values found in the metadata
    And "BelowGradeFinishedAreaUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @BuildingAreaSource @DD1.5_BuildingAreaSource @DD1.6_BuildingAreaSource
  Scenario: BuildingAreaSource
    Given "BuildingAreaSource" exists in the metadata
    And "BuildingAreaSource" enum values can be compiled
    And "BuildingAreaSource" values are not null
    Then "BuildingAreaSource" should be "String" data type
    And "BuildingAreaSource" should only contain enum values found in the metadata
    And "BuildingAreaSource" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @BuildingAreaUnits @DD1.5_BuildingAreaUnits @DD1.6_BuildingAreaUnits
  Scenario: BuildingAreaUnits
    Given "BuildingAreaUnits" exists in the metadata
    And "BuildingAreaUnits" enum values can be compiled
    And "BuildingAreaUnits" values are not null
    Then "BuildingAreaUnits" should be "String" data type
    And "BuildingAreaUnits" should only contain enum values found in the metadata
    And "BuildingAreaUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @BuyerAgencyCompensationType @DD1.5_BuyerAgencyCompensationType @DD1.6_BuyerAgencyCompensationType
  Scenario: BuyerAgencyCompensationType
    Given "BuyerAgencyCompensationType" exists in the metadata
    And "BuyerAgencyCompensationType" enum values can be compiled
    And "BuyerAgencyCompensationType" values are not null
    Then "BuyerAgencyCompensationType" should be "String" data type
    And "BuyerAgencyCompensationType" should only contain enum values found in the metadata
    And "BuyerAgencyCompensationType" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @BuyerAgentAOR @DD1.5_BuyerAgentAOR @DD1.6_BuyerAgentAOR
  Scenario: BuyerAgentAOR
    Given "BuyerAgentAOR" exists in the metadata
    And "BuyerAgentAOR" enum values can be compiled
    And "BuyerAgentAOR" values are not null
    Then "BuyerAgentAOR" should be "String" data type
    And "BuyerAgentAOR" should only contain enum values found in the metadata
    And "BuyerAgentAOR" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @BuyerOfficeAOR @DD1.5_BuyerOfficeAOR @DD1.6_BuyerOfficeAOR
  Scenario: BuyerOfficeAOR
    Given "BuyerOfficeAOR" exists in the metadata
    And "BuyerOfficeAOR" enum values can be compiled
    And "BuyerOfficeAOR" values are not null
    Then "BuyerOfficeAOR" should be "String" data type
    And "BuyerOfficeAOR" should only contain enum values found in the metadata
    And "BuyerOfficeAOR" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @City @IDX_Payload @DD1.5_City @DD1.6_City
  Scenario: City
    Given "City" exists in the metadata
    And "City" enum values can be compiled
    And "City" values are not null
    Then "City" should be "String" data type
    And "City" should only contain enum values found in the metadata
    And "City" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @CoBuyerAgentAOR @DD1.5_CoBuyerAgentAOR @DD1.6_CoBuyerAgentAOR
  Scenario: CoBuyerAgentAOR
    Given "CoBuyerAgentAOR" exists in the metadata
    And "CoBuyerAgentAOR" enum values can be compiled
    And "CoBuyerAgentAOR" values are not null
    Then "CoBuyerAgentAOR" should be "String" data type
    And "CoBuyerAgentAOR" should only contain enum values found in the metadata
    And "CoBuyerAgentAOR" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @CoBuyerOfficeAOR @DD1.5_CoBuyerOfficeAOR @DD1.6_CoBuyerOfficeAOR
  Scenario: CoBuyerOfficeAOR
    Given "CoBuyerOfficeAOR" exists in the metadata
    And "CoBuyerOfficeAOR" enum values can be compiled
    And "CoBuyerOfficeAOR" values are not null
    Then "CoBuyerOfficeAOR" should be "String" data type
    And "CoBuyerOfficeAOR" should only contain enum values found in the metadata
    And "CoBuyerOfficeAOR" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @CoListAgentAOR @DD1.5_CoListAgentAOR @DD1.6_CoListAgentAOR
  Scenario: CoListAgentAOR
    Given "CoListAgentAOR" exists in the metadata
    And "CoListAgentAOR" enum values can be compiled
    And "CoListAgentAOR" values are not null
    Then "CoListAgentAOR" should be "String" data type
    And "CoListAgentAOR" should only contain enum values found in the metadata
    And "CoListAgentAOR" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @CoListOfficeAOR @DD1.5_CoListOfficeAOR @DD1.6_CoListOfficeAOR
  Scenario: CoListOfficeAOR
    Given "CoListOfficeAOR" exists in the metadata
    And "CoListOfficeAOR" enum values can be compiled
    And "CoListOfficeAOR" values are not null
    Then "CoListOfficeAOR" should be "String" data type
    And "CoListOfficeAOR" should only contain enum values found in the metadata
    And "CoListOfficeAOR" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @Concessions @DD1.5_Concessions @DD1.6_Concessions
  Scenario: Concessions
    Given "Concessions" exists in the metadata
    And "Concessions" enum values can be compiled
    And "Concessions" values are not null
    Then "Concessions" should be "String" data type
    And "Concessions" should only contain enum values found in the metadata
    And "Concessions" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @Country @DD1.5_Country @DD1.6_Country
  Scenario: Country
    Given "Country" exists in the metadata
    And "Country" enum values can be compiled
    And "Country" values are not null
    Then "Country" should be "String" data type
    And "Country" should only contain enum values found in the metadata
    And "Country" length should be less than or equal to the RESO maxlength of 2

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @CountyOrParish @IDX_Payload @DD1.5_CountyOrParish @DD1.6_CountyOrParish
  Scenario: CountyOrParish
    Given "CountyOrParish" exists in the metadata
    And "CountyOrParish" enum values can be compiled
    And "CountyOrParish" values are not null
    Then "CountyOrParish" should be "String" data type
    And "CountyOrParish" should only contain enum values found in the metadata
    And "CountyOrParish" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @DirectionFaces @IDX_Payload @DD1.5_DirectionFaces @DD1.6_DirectionFaces
  Scenario: DirectionFaces
    Given "DirectionFaces" exists in the metadata
    And "DirectionFaces" enum values can be compiled
    And "DirectionFaces" values are not null
    Then "DirectionFaces" should be "String" data type
    And "DirectionFaces" should only contain enum values found in the metadata
    And "DirectionFaces" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @DistanceToBusUnits @DD1.5_DistanceToBusUnits @DD1.6_DistanceToBusUnits
  Scenario: DistanceToBusUnits
    Given "DistanceToBusUnits" exists in the metadata
    And "DistanceToBusUnits" enum values can be compiled
    And "DistanceToBusUnits" values are not null
    Then "DistanceToBusUnits" should be "String" data type
    And "DistanceToBusUnits" should only contain enum values found in the metadata
    And "DistanceToBusUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @DistanceToElectricUnits @DD1.5_DistanceToElectricUnits @DD1.6_DistanceToElectricUnits
  Scenario: DistanceToElectricUnits
    Given "DistanceToElectricUnits" exists in the metadata
    And "DistanceToElectricUnits" enum values can be compiled
    And "DistanceToElectricUnits" values are not null
    Then "DistanceToElectricUnits" should be "String" data type
    And "DistanceToElectricUnits" should only contain enum values found in the metadata
    And "DistanceToElectricUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @DistanceToFreewayUnits @DD1.5_DistanceToFreewayUnits @DD1.6_DistanceToFreewayUnits
  Scenario: DistanceToFreewayUnits
    Given "DistanceToFreewayUnits" exists in the metadata
    And "DistanceToFreewayUnits" enum values can be compiled
    And "DistanceToFreewayUnits" values are not null
    Then "DistanceToFreewayUnits" should be "String" data type
    And "DistanceToFreewayUnits" should only contain enum values found in the metadata
    And "DistanceToFreewayUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @DistanceToGasUnits @DD1.5_DistanceToGasUnits @DD1.6_DistanceToGasUnits
  Scenario: DistanceToGasUnits
    Given "DistanceToGasUnits" exists in the metadata
    And "DistanceToGasUnits" enum values can be compiled
    And "DistanceToGasUnits" values are not null
    Then "DistanceToGasUnits" should be "String" data type
    And "DistanceToGasUnits" should only contain enum values found in the metadata
    And "DistanceToGasUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @DistanceToPhoneServiceUnits @DD1.5_DistanceToPhoneServiceUnits @DD1.6_DistanceToPhoneServiceUnits
  Scenario: DistanceToPhoneServiceUnits
    Given "DistanceToPhoneServiceUnits" exists in the metadata
    And "DistanceToPhoneServiceUnits" enum values can be compiled
    And "DistanceToPhoneServiceUnits" values are not null
    Then "DistanceToPhoneServiceUnits" should be "String" data type
    And "DistanceToPhoneServiceUnits" should only contain enum values found in the metadata
    And "DistanceToPhoneServiceUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @DistanceToPlaceofWorshipUnits @DD1.5_DistanceToPlaceofWorshipUnits @DD1.6_DistanceToPlaceofWorshipUnits
  Scenario: DistanceToPlaceofWorshipUnits
    Given "DistanceToPlaceofWorshipUnits" exists in the metadata
    And "DistanceToPlaceofWorshipUnits" enum values can be compiled
    And "DistanceToPlaceofWorshipUnits" values are not null
    Then "DistanceToPlaceofWorshipUnits" should be "String" data type
    And "DistanceToPlaceofWorshipUnits" should only contain enum values found in the metadata
    And "DistanceToPlaceofWorshipUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @DistanceToSchoolBusUnits @DD1.5_DistanceToSchoolBusUnits @DD1.6_DistanceToSchoolBusUnits
  Scenario: DistanceToSchoolBusUnits
    Given "DistanceToSchoolBusUnits" exists in the metadata
    And "DistanceToSchoolBusUnits" enum values can be compiled
    And "DistanceToSchoolBusUnits" values are not null
    Then "DistanceToSchoolBusUnits" should be "String" data type
    And "DistanceToSchoolBusUnits" should only contain enum values found in the metadata
    And "DistanceToSchoolBusUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @DistanceToSchoolsUnits @DD1.5_DistanceToSchoolsUnits @DD1.6_DistanceToSchoolsUnits
  Scenario: DistanceToSchoolsUnits
    Given "DistanceToSchoolsUnits" exists in the metadata
    And "DistanceToSchoolsUnits" enum values can be compiled
    And "DistanceToSchoolsUnits" values are not null
    Then "DistanceToSchoolsUnits" should be "String" data type
    And "DistanceToSchoolsUnits" should only contain enum values found in the metadata
    And "DistanceToSchoolsUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @DistanceToSewerUnits @DD1.5_DistanceToSewerUnits @DD1.6_DistanceToSewerUnits
  Scenario: DistanceToSewerUnits
    Given "DistanceToSewerUnits" exists in the metadata
    And "DistanceToSewerUnits" enum values can be compiled
    And "DistanceToSewerUnits" values are not null
    Then "DistanceToSewerUnits" should be "String" data type
    And "DistanceToSewerUnits" should only contain enum values found in the metadata
    And "DistanceToSewerUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @DistanceToShoppingUnits @DD1.5_DistanceToShoppingUnits @DD1.6_DistanceToShoppingUnits
  Scenario: DistanceToShoppingUnits
    Given "DistanceToShoppingUnits" exists in the metadata
    And "DistanceToShoppingUnits" enum values can be compiled
    And "DistanceToShoppingUnits" values are not null
    Then "DistanceToShoppingUnits" should be "String" data type
    And "DistanceToShoppingUnits" should only contain enum values found in the metadata
    And "DistanceToShoppingUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @DistanceToStreetUnits @DD1.5_DistanceToStreetUnits @DD1.6_DistanceToStreetUnits
  Scenario: DistanceToStreetUnits
    Given "DistanceToStreetUnits" exists in the metadata
    And "DistanceToStreetUnits" enum values can be compiled
    And "DistanceToStreetUnits" values are not null
    Then "DistanceToStreetUnits" should be "String" data type
    And "DistanceToStreetUnits" should only contain enum values found in the metadata
    And "DistanceToStreetUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @DistanceToWaterUnits @DD1.5_DistanceToWaterUnits @DD1.6_DistanceToWaterUnits
  Scenario: DistanceToWaterUnits
    Given "DistanceToWaterUnits" exists in the metadata
    And "DistanceToWaterUnits" enum values can be compiled
    And "DistanceToWaterUnits" values are not null
    Then "DistanceToWaterUnits" should be "String" data type
    And "DistanceToWaterUnits" should only contain enum values found in the metadata
    And "DistanceToWaterUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @ElementarySchool @IDX_Payload @DD1.5_ElementarySchool @DD1.6_ElementarySchool
  Scenario: ElementarySchool
    Given "ElementarySchool" exists in the metadata
    And "ElementarySchool" enum values can be compiled
    And "ElementarySchool" values are not null
    Then "ElementarySchool" should be "String" data type
    And "ElementarySchool" should only contain enum values found in the metadata
    And "ElementarySchool" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @ElementarySchool @IDX_Payload @DD1.5_ElementarySchoolDistrict @DD1.6_ElementarySchoolDistrict
  Scenario: ElementarySchoolDistrict
    Given "ElementarySchoolDistrict" exists in the metadata
    And "ElementarySchoolDistrict" enum values can be compiled
    And "ElementarySchoolDistrict" values are not null
    Then "ElementarySchoolDistrict" should be "String" data type
    And "ElementarySchoolDistrict" should only contain enum values found in the metadata
    And "ElementarySchoolDistrict" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @ElevationUnits @DD1.5_ElevationUnits @DD1.6_ElevationUnits
  Scenario: ElevationUnits
    Given "ElevationUnits" exists in the metadata
    And "ElevationUnits" enum values can be compiled
    And "ElevationUnits" values are not null
    Then "ElevationUnits" should be "String" data type
    And "ElevationUnits" should only contain enum values found in the metadata
    And "ElevationUnits" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @FarmLandAreaSource @DD1.5_FarmLandAreaSource @DD1.6_FarmLandAreaSource
  Scenario: FarmLandAreaSource
    Given "FarmLandAreaSource" exists in the metadata
    And "FarmLandAreaSource" enum values can be compiled
    And "FarmLandAreaSource" values are not null
    Then "FarmLandAreaSource" should be "String" data type
    And "FarmLandAreaSource" should only contain enum values found in the metadata
    And "FarmLandAreaSource" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @FarmLandAreaUnits @DD1.5_FarmLandAreaUnits @DD1.6_FarmLandAreaUnits
  Scenario: FarmLandAreaUnits
    Given "FarmLandAreaUnits" exists in the metadata
    And "FarmLandAreaUnits" enum values can be compiled
    And "FarmLandAreaUnits" values are not null
    Then "FarmLandAreaUnits" should be "String" data type
    And "FarmLandAreaUnits" should only contain enum values found in the metadata
    And "FarmLandAreaUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @Furnished @DD1.5_Furnished @DD1.6_Furnished
  Scenario: Furnished
    Given "Furnished" exists in the metadata
    And "Furnished" enum values can be compiled
    And "Furnished" values are not null
    Then "Furnished" should be "String" data type
    And "Furnished" should only contain enum values found in the metadata
    And "Furnished" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @HighSchool @IDX_Payload @DD1.5_HighSchool @DD1.6_HighSchool
  Scenario: HighSchool
    Given "HighSchool" exists in the metadata
    And "HighSchool" enum values can be compiled
    And "HighSchool" values are not null
    Then "HighSchool" should be "String" data type
    And "HighSchool" should only contain enum values found in the metadata
    And "HighSchool" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @HighSchool @IDX_Payload @DD1.5_HighSchoolDistrict @DD1.6_HighSchoolDistrict
  Scenario: HighSchoolDistrict
    Given "HighSchoolDistrict" exists in the metadata
    And "HighSchoolDistrict" enum values can be compiled
    And "HighSchoolDistrict" values are not null
    Then "HighSchoolDistrict" should be "String" data type
    And "HighSchoolDistrict" should only contain enum values found in the metadata
    And "HighSchoolDistrict" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @LandLeaseAmountFrequency @DD1.5_LandLeaseAmountFrequency @DD1.6_LandLeaseAmountFrequency
  Scenario: LandLeaseAmountFrequency
    Given "LandLeaseAmountFrequency" exists in the metadata
    And "LandLeaseAmountFrequency" enum values can be compiled
    And "LandLeaseAmountFrequency" values are not null
    Then "LandLeaseAmountFrequency" should be "String" data type
    And "LandLeaseAmountFrequency" should only contain enum values found in the metadata
    And "LandLeaseAmountFrequency" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @LeasableAreaUnits @DD1.5_LeasableAreaUnits @DD1.6_LeasableAreaUnits
  Scenario: LeasableAreaUnits
    Given "LeasableAreaUnits" exists in the metadata
    And "LeasableAreaUnits" enum values can be compiled
    And "LeasableAreaUnits" values are not null
    Then "LeasableAreaUnits" should be "String" data type
    And "LeasableAreaUnits" should only contain enum values found in the metadata
    And "LeasableAreaUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @LeaseAmountFrequency @DD1.5_LeaseAmountFrequency @DD1.6_LeaseAmountFrequency
  Scenario: LeaseAmountFrequency
    Given "LeaseAmountFrequency" exists in the metadata
    And "LeaseAmountFrequency" enum values can be compiled
    And "LeaseAmountFrequency" values are not null
    Then "LeaseAmountFrequency" should be "String" data type
    And "LeaseAmountFrequency" should only contain enum values found in the metadata
    And "LeaseAmountFrequency" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @LeaseTerm @DD1.5_LeaseTerm @DD1.6_LeaseTerm
  Scenario: LeaseTerm
    Given "LeaseTerm" exists in the metadata
    And "LeaseTerm" enum values can be compiled
    And "LeaseTerm" values are not null
    Then "LeaseTerm" should be "String" data type
    And "LeaseTerm" should only contain enum values found in the metadata
    And "LeaseTerm" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @xxx @DD1.5_ListAOR @DD1.6_ListAOR
  Scenario: ListAOR
    Given "ListAOR" exists in the metadata
    And "ListAOR" enum values can be compiled
    And "ListAOR" values are not null
    Then "ListAOR" should be "String" data type
    And "ListAOR" should only contain enum values found in the metadata
    And "ListAOR" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @ListAgentAOR @DD1.5_ListAgentAOR @DD1.6_ListAgentAOR
  Scenario: ListAgentAOR
    Given "ListAgentAOR" exists in the metadata
    And "ListAgentAOR" enum values can be compiled
    And "ListAgentAOR" values are not null
    Then "ListAgentAOR" should be "String" data type
    And "ListAgentAOR" should only contain enum values found in the metadata
    And "ListAgentAOR" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @ListOfficeAOR @DD1.5_ListOfficeAOR @DD1.6_ListOfficeAOR
  Scenario: ListOfficeAOR
    Given "ListOfficeAOR" exists in the metadata
    And "ListOfficeAOR" enum values can be compiled
    And "ListOfficeAOR" values are not null
    Then "ListOfficeAOR" should be "String" data type
    And "ListOfficeAOR" should only contain enum values found in the metadata
    And "ListOfficeAOR" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @ListingAgreement @DD1.5_ListingAgreement @DD1.6_ListingAgreement
  Scenario: ListingAgreement
    Given "ListingAgreement" exists in the metadata
    And "ListingAgreement" enum values can be compiled
    And "ListingAgreement" values are not null
    Then "ListingAgreement" should be "String" data type
    And "ListingAgreement" should only contain enum values found in the metadata
    And "ListingAgreement" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @ListingService @DD1.5_ListingService @DD1.6_ListingService
  Scenario: ListingService
    Given "ListingService" exists in the metadata
    And "ListingService" enum values can be compiled
    And "ListingService" values are not null
    Then "ListingService" should be "String" data type
    And "ListingService" should only contain enum values found in the metadata
    And "ListingService" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @LivingArea @IDX_Payload @DD1.5_LivingAreaSource @DD1.6_LivingAreaSource
  Scenario: LivingAreaSource
    Given "LivingAreaSource" exists in the metadata
    And "LivingAreaSource" enum values can be compiled
    And "LivingAreaSource" values are not null
    Then "LivingAreaSource" should be "String" data type
    And "LivingAreaSource" should only contain enum values found in the metadata
    And "LivingAreaSource" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @LivingArea @IDX_Payload @DD1.5_LivingAreaUnits @DD1.6_LivingAreaUnits
  Scenario: LivingAreaUnits
    Given "LivingAreaUnits" exists in the metadata
    And "LivingAreaUnits" enum values can be compiled
    And "LivingAreaUnits" values are not null
    Then "LivingAreaUnits" should be "String" data type
    And "LivingAreaUnits" should only contain enum values found in the metadata
    And "LivingAreaUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @LotDimensionsSource @DD1.5_LotDimensionsSource @DD1.6_LotDimensionsSource
  Scenario: LotDimensionsSource
    Given "LotDimensionsSource" exists in the metadata
    And "LotDimensionsSource" enum values can be compiled
    And "LotDimensionsSource" values are not null
    Then "LotDimensionsSource" should be "String" data type
    And "LotDimensionsSource" should only contain enum values found in the metadata
    And "LotDimensionsSource" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @LotSizeSource @DD1.5_LotSizeSource @DD1.6_LotSizeSource
  Scenario: LotSizeSource
    Given "LotSizeSource" exists in the metadata
    And "LotSizeSource" enum values can be compiled
    And "LotSizeSource" values are not null
    Then "LotSizeSource" should be "String" data type
    And "LotSizeSource" should only contain enum values found in the metadata
    And "LotSizeSource" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @LotSizeUnits @IDX_Payload @DD1.5_LotSizeUnits @DD1.6_LotSizeUnits
  Scenario: LotSizeUnits
    Given "LotSizeUnits" exists in the metadata
    And "LotSizeUnits" enum values can be compiled
    And "LotSizeUnits" values are not null
    Then "LotSizeUnits" should be "String" data type
    And "LotSizeUnits" should only contain enum values found in the metadata
    And "LotSizeUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @MLSAreaMajor @IDX_Payload @DD1.5_MLSAreaMajor @DD1.6_MLSAreaMajor
  Scenario: MLSAreaMajor
    Given "MLSAreaMajor" exists in the metadata
    And "MLSAreaMajor" enum values can be compiled
    And "MLSAreaMajor" values are not null
    Then "MLSAreaMajor" should be "String" data type
    And "MLSAreaMajor" should only contain enum values found in the metadata
    And "MLSAreaMajor" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @MLSAreaMinor @DD1.5_MLSAreaMinor @DD1.6_MLSAreaMinor
  Scenario: MLSAreaMinor
    Given "MLSAreaMinor" exists in the metadata
    And "MLSAreaMinor" enum values can be compiled
    And "MLSAreaMinor" values are not null
    Then "MLSAreaMinor" should be "String" data type
    And "MLSAreaMinor" should only contain enum values found in the metadata
    And "MLSAreaMinor" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @MajorChangeType @DD1.5_MajorChangeType @DD1.6_MajorChangeType
  Scenario: MajorChangeType
    Given "MajorChangeType" exists in the metadata
    And "MajorChangeType" enum values can be compiled
    And "MajorChangeType" values are not null
    Then "MajorChangeType" should be "String" data type
    And "MajorChangeType" should only contain enum values found in the metadata
    And "MajorChangeType" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @MiddleOrJuniorSchool @IDX_Payload @DD1.5_MiddleOrJuniorSchool @DD1.6_MiddleOrJuniorSchool
  Scenario: MiddleOrJuniorSchool
    Given "MiddleOrJuniorSchool" exists in the metadata
    And "MiddleOrJuniorSchool" enum values can be compiled
    And "MiddleOrJuniorSchool" values are not null
    Then "MiddleOrJuniorSchool" should be "String" data type
    And "MiddleOrJuniorSchool" should only contain enum values found in the metadata
    And "MiddleOrJuniorSchool" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @xxx @DD1.5_MiddleOrJuniorSchoolDistrict @DD1.6_MiddleOrJuniorSchoolDistrict
  Scenario: MiddleOrJuniorSchoolDistrict
    Given "MiddleOrJuniorSchoolDistrict" exists in the metadata
    And "MiddleOrJuniorSchoolDistrict" enum values can be compiled
    And "MiddleOrJuniorSchoolDistrict" values are not null
    Then "MiddleOrJuniorSchoolDistrict" should be "String" data type
    And "MiddleOrJuniorSchoolDistrict" should only contain enum values found in the metadata
    And "MiddleOrJuniorSchoolDistrict" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @MlsStatus @IDX_Payload @DD1.5_MlsStatus @DD1.6_MlsStatus
  Scenario: MlsStatus
    Given "MlsStatus" exists in the metadata
    And "MlsStatus" enum values can be compiled
    And "MlsStatus" values are not null
    Then "MlsStatus" should be "String" data type
    And "MlsStatus" should only contain enum values found in the metadata
    And "MlsStatus" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @MobileDimUnits @DD1.5_MobileDimUnits @DD1.6_MobileDimUnits
  Scenario: MobileDimUnits
    Given "MobileDimUnits" exists in the metadata
    And "MobileDimUnits" enum values can be compiled
    And "MobileDimUnits" values are not null
    Then "MobileDimUnits" should be "String" data type
    And "MobileDimUnits" should only contain enum values found in the metadata
    And "MobileDimUnits" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @OccupantType @DD1.5_OccupantType @DD1.6_OccupantType
  Scenario: OccupantType
    Given "OccupantType" exists in the metadata
    And "OccupantType" enum values can be compiled
    And "OccupantType" values are not null
    Then "OccupantType" should be "String" data type
    And "OccupantType" should only contain enum values found in the metadata
    And "OccupantType" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @OwnershipType @DD1.5_OwnershipType @DD1.6_OwnershipType
  Scenario: OwnershipType
    Given "OwnershipType" exists in the metadata
    And "OwnershipType" enum values can be compiled
    And "OwnershipType" values are not null
    Then "OwnershipType" should be "String" data type
    And "OwnershipType" should only contain enum values found in the metadata
    And "OwnershipType" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @PostalCity @DD1.5_PostalCity @DD1.6_PostalCity
  Scenario: PostalCity
    Given "PostalCity" exists in the metadata
    And "PostalCity" enum values can be compiled
    And "PostalCity" values are not null
    Then "PostalCity" should be "String" data type
    And "PostalCity" should only contain enum values found in the metadata
    And "PostalCity" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @PropertySubType @IDX_Payload @DD1.5_PropertySubType @DD1.6_PropertySubType
  Scenario: PropertySubType
    Given "PropertySubType" exists in the metadata
    And "PropertySubType" enum values can be compiled
    And "PropertySubType" values are not null
    Then "PropertySubType" should be "String" data type
    And "PropertySubType" should only contain enum values found in the metadata
    And "PropertySubType" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @PropertyType @IDX_Payload @DD1.5_PropertyType @DD1.6_PropertyType
  Scenario: PropertyType
    Given "PropertyType" exists in the metadata
    And "PropertyType" enum values can be compiled
    And "PropertyType" values are not null
    Then "PropertyType" should be "String" data type
    And "PropertyType" should only contain enum values found in the metadata
    And "PropertyType" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @StandardStatus @IDX_Payload @DD1.5_StandardStatus @DD1.6_StandardStatus
  Scenario: StandardStatus
    Given "StandardStatus" exists in the metadata
    And "StandardStatus" enum values can be compiled
    And "StandardStatus" values are not null
    Then "StandardStatus" should be "String" data type
    And "StandardStatus" should only contain enum values found in the metadata
    And "StandardStatus" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @StateOrProvince @IDX_Payload @DD1.5_StateOrProvince @DD1.6_StateOrProvince
  Scenario: StateOrProvince
    Given "StateOrProvince" exists in the metadata
    And "StateOrProvince" enum values can be compiled
    And "StateOrProvince" values are not null
    Then "StateOrProvince" should be "String" data type
    And "StateOrProvince" should only contain enum values found in the metadata
    And "StateOrProvince" length should be less than or equal to the RESO maxlength of 2

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @StreetDirPrefix @IDX_Payload @DD1.5_StreetDirPrefix @DD1.6_StreetDirPrefix
  Scenario: StreetDirPrefix
    Given "StreetDirPrefix" exists in the metadata
    And "StreetDirPrefix" enum values can be compiled
    And "StreetDirPrefix" values are not null
    Then "StreetDirPrefix" should be "String" data type
    And "StreetDirPrefix" should only contain enum values found in the metadata
    And "StreetDirPrefix" length should be less than or equal to the RESO maxlength of 15

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @StreetDirSuffix @IDX_Payload @DD1.5_StreetDirSuffix @DD1.6_StreetDirSuffix
  Scenario: StreetDirSuffix
    Given "StreetDirSuffix" exists in the metadata
    And "StreetDirSuffix" enum values can be compiled
    And "StreetDirSuffix" values are not null
    Then "StreetDirSuffix" should be "String" data type
    And "StreetDirSuffix" should only contain enum values found in the metadata
    And "StreetDirSuffix" length should be less than or equal to the RESO maxlength of 15

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @StreetSuffix @IDX_Payload @DD1.5_StreetSuffix @DD1.6_StreetSuffix
  Scenario: StreetSuffix
    Given "StreetSuffix" exists in the metadata
    And "StreetSuffix" enum values can be compiled
    And "StreetSuffix" values are not null
    Then "StreetSuffix" should be "String" data type
    And "StreetSuffix" should only contain enum values found in the metadata
    And "StreetSuffix" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @SubAgencyCompensationType @DD1.5_SubAgencyCompensationType @DD1.6_SubAgencyCompensationType
  Scenario: SubAgencyCompensationType
    Given "SubAgencyCompensationType" exists in the metadata
    And "SubAgencyCompensationType" enum values can be compiled
    And "SubAgencyCompensationType" values are not null
    Then "SubAgencyCompensationType" should be "String" data type
    And "SubAgencyCompensationType" should only contain enum values found in the metadata
    And "SubAgencyCompensationType" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @TransactionBrokerCompensationType @DD1.5_TransactionBrokerCompensationType @DD1.6_TransactionBrokerCompensationType
  Scenario: TransactionBrokerCompensationType
    Given "TransactionBrokerCompensationType" exists in the metadata
    And "TransactionBrokerCompensationType" enum values can be compiled
    And "TransactionBrokerCompensationType" values are not null
    Then "TransactionBrokerCompensationType" should be "String" data type
    And "TransactionBrokerCompensationType" should only contain enum values found in the metadata
    And "TransactionBrokerCompensationType" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @UnitsFurnished @DD1.5_UnitsFurnished @DD1.6_UnitsFurnished
  Scenario: UnitsFurnished
    Given "UnitsFurnished" exists in the metadata
    And "UnitsFurnished" enum values can be compiled
    And "UnitsFurnished" values are not null
    Then "UnitsFurnished" should be "String" data type
    And "UnitsFurnished" should only contain enum values found in the metadata
    And "UnitsFurnished" length should be less than or equal to the RESO maxlength of 25

  @DD1.5_StringList-Single @DD1.6_StringList-Single @DD1.5 @DD1.6 @YearBuiltSource @DD1.5_YearBuiltSource @DD1.6_YearBuiltSource
  Scenario: YearBuiltSource
    Given "YearBuiltSource" exists in the metadata
    And "YearBuiltSource" enum values can be compiled
    And "YearBuiltSource" values are not null
    Then "YearBuiltSource" should be "String" data type
    And "YearBuiltSource" should only contain enum values found in the metadata
    And "YearBuiltSource" length should be less than or equal to the RESO maxlength of 60

  @DD1.6_StringList-Single @DD1.6 @CommonInterest @DD1.6_CommonInterest
  Scenario: CommonInterest
    Given "CommonInterest" exists in the metadata
    And "CommonInterest" enum values can be compiled
    And "CommonInterest" values are not null
    Then "CommonInterest" should be "String" data type
    And "CommonInterest" should only contain enum values found in the metadata
    And "CommonInterest" length should be less than or equal to the RESO maxlength of 60
