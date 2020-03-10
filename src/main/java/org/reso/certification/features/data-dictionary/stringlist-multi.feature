Feature: StringList-Multi Testing

  Background:

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @AccessibilityFeatures @IDX_Payload @DD1.5_AccessibilityFeatures @DD1.6_AccessibilityFeatures
  Scenario: AccessibilityFeatures
    Given "AccessibilityFeatures" exists in the metadata
    And "AccessibilityFeatures" enum values can be compiled
    And "AccessibilityFeatures" stringlist-multi values are not null
    Then "AccessibilityFeatures" should be Array of Strings data type
    And "AccessibilityFeatures" should only contain enum values found in the metadata
    And "AccessibilityFeatures" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @Appliances @IDX_Payload @DD1.5_Appliances @DD1.6_Appliances
  Scenario: Appliances
    Given "Appliances" exists in the metadata
    And "Appliances" enum values can be compiled
    And "Appliances" stringlist-multi values are not null
    Then "Appliances" should be Array of Strings data type
    And "Appliances" should only contain enum values found in the metadata
    And "Appliances" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @ArchitecturalStyle @IDX_Payload @DD1.5_ArchitecturalStyle @DD1.6_ArchitecturalStyle
  Scenario: ArchitecturalStyle
    Given "ArchitecturalStyle" exists in the metadata
    And "ArchitecturalStyle" enum values can be compiled
    And "ArchitecturalStyle" stringlist-multi values are not null
    Then "ArchitecturalStyle" should be Array of Strings data type
    And "ArchitecturalStyle" should only contain enum values found in the metadata
    And "ArchitecturalStyle" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @AssociationAmenities @IDX_Payload @DD1.5_AssociationAmenities @DD1.6_AssociationAmenities
  Scenario: AssociationAmenities
    Given "AssociationAmenities" exists in the metadata
    And "AssociationAmenities" enum values can be compiled
    And "AssociationAmenities" stringlist-multi values are not null
    Then "AssociationAmenities" should be Array of Strings data type
    And "AssociationAmenities" should only contain enum values found in the metadata
    And "AssociationAmenities" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @AssociationFee @IDX_Payload @DD1.5_AssociationFeeIncludes @DD1.6_AssociationFeeIncludes
  Scenario: AssociationFeeIncludes
    Given "AssociationFeeIncludes" exists in the metadata
    And "AssociationFeeIncludes" enum values can be compiled
    And "AssociationFeeIncludes" stringlist-multi values are not null
    Then "AssociationFeeIncludes" should be Array of Strings data type
    And "AssociationFeeIncludes" should only contain enum values found in the metadata
    And "AssociationFeeIncludes" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @Basement @IDX_Payload @DD1.5_Basement @DD1.6_Basement
  Scenario: Basement
    Given "Basement" exists in the metadata
    And "Basement" enum values can be compiled
    And "Basement" stringlist-multi values are not null
    Then "Basement" should be Array of Strings data type
    And "Basement" should only contain enum values found in the metadata
    And "Basement" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @BodyType @IDX_Payload @DD1.5_BodyType @DD1.6_BodyType
  Scenario: BodyType
    Given "BodyType" exists in the metadata
    And "BodyType" enum values can be compiled
    And "BodyType" stringlist-multi values are not null
    Then "BodyType" should be Array of Strings data type
    And "BodyType" should only contain enum values found in the metadata
    And "BodyType" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @BuildingFeatures @DD1.5_BuildingFeatures @DD1.6_BuildingFeatures
  Scenario: BuildingFeatures
    Given "BuildingFeatures" exists in the metadata
    And "BuildingFeatures" enum values can be compiled
    And "BuildingFeatures" stringlist-multi values are not null
    Then "BuildingFeatures" should be Array of Strings data type
    And "BuildingFeatures" should only contain enum values found in the metadata
    And "BuildingFeatures" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @BusinessType @IDX_Payload @DD1.5_BusinessType @DD1.6_BusinessType
  Scenario: BusinessType
    Given "BusinessType" exists in the metadata
    And "BusinessType" enum values can be compiled
    And "BusinessType" stringlist-multi values are not null
    Then "BusinessType" should be Array of Strings data type
    And "BusinessType" should only contain enum values found in the metadata
    And "BusinessType" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @BuyerAgentDesignation @DD1.5_BuyerAgentDesignation @DD1.6_BuyerAgentDesignation
  Scenario: BuyerAgentDesignation
    Given "BuyerAgentDesignation" exists in the metadata
    And "BuyerAgentDesignation" enum values can be compiled
    And "BuyerAgentDesignation" stringlist-multi values are not null
    Then "BuyerAgentDesignation" should be Array of Strings data type
    And "BuyerAgentDesignation" should only contain enum values found in the metadata
    And "BuyerAgentDesignation" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @BuyerFinancing @DD1.5_BuyerFinancing @DD1.6_BuyerFinancing
  Scenario: BuyerFinancing
    Given "BuyerFinancing" exists in the metadata
    And "BuyerFinancing" enum values can be compiled
    And "BuyerFinancing" stringlist-multi values are not null
    Then "BuyerFinancing" should be Array of Strings data type
    And "BuyerFinancing" should only contain enum values found in the metadata
    And "BuyerFinancing" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @CoBuyerAgentDesignation @DD1.5_CoBuyerAgentDesignation @DD1.6_CoBuyerAgentDesignation
  Scenario: CoBuyerAgentDesignation
    Given "CoBuyerAgentDesignation" exists in the metadata
    And "CoBuyerAgentDesignation" enum values can be compiled
    And "CoBuyerAgentDesignation" stringlist-multi values are not null
    Then "CoBuyerAgentDesignation" should be Array of Strings data type
    And "CoBuyerAgentDesignation" should only contain enum values found in the metadata
    And "CoBuyerAgentDesignation" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @CoListAgentDesignation @DD1.5_CoListAgentDesignation @DD1.6_CoListAgentDesignation
  Scenario: CoListAgentDesignation
    Given "CoListAgentDesignation" exists in the metadata
    And "CoListAgentDesignation" enum values can be compiled
    And "CoListAgentDesignation" stringlist-multi values are not null
    Then "CoListAgentDesignation" should be Array of Strings data type
    And "CoListAgentDesignation" should only contain enum values found in the metadata
    And "CoListAgentDesignation" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @CommonWalls @DD1.5_CommonWalls @DD1.6_CommonWalls
  Scenario: CommonWalls
    Given "CommonWalls" exists in the metadata
    And "CommonWalls" enum values can be compiled
    And "CommonWalls" stringlist-multi values are not null
    Then "CommonWalls" should be Array of Strings data type
    And "CommonWalls" should only contain enum values found in the metadata
    And "CommonWalls" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @CommunityFeatures @DD1.5_CommunityFeatures @DD1.6_CommunityFeatures
  Scenario: CommunityFeatures
    Given "CommunityFeatures" exists in the metadata
    And "CommunityFeatures" enum values can be compiled
    And "CommunityFeatures" stringlist-multi values are not null
    Then "CommunityFeatures" should be Array of Strings data type
    And "CommunityFeatures" should only contain enum values found in the metadata
    And "CommunityFeatures" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @ConstructionMaterials @IDX_Payload @DD1.5_ConstructionMaterials @DD1.6_ConstructionMaterials
  Scenario: ConstructionMaterials
    Given "ConstructionMaterials" exists in the metadata
    And "ConstructionMaterials" enum values can be compiled
    And "ConstructionMaterials" stringlist-multi values are not null
    Then "ConstructionMaterials" should be Array of Strings data type
    And "ConstructionMaterials" should only contain enum values found in the metadata
    And "ConstructionMaterials" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @Cooling @IDX_Payload @DD1.5_Cooling @DD1.6_Cooling
  Scenario: Cooling
    Given "Cooling" exists in the metadata
    And "Cooling" enum values can be compiled
    And "Cooling" stringlist-multi values are not null
    Then "Cooling" should be Array of Strings data type
    And "Cooling" should only contain enum values found in the metadata
    And "Cooling" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @CurrentFinancing @DD1.5_CurrentFinancing @DD1.6_CurrentFinancing
  Scenario: CurrentFinancing
    Given "CurrentFinancing" exists in the metadata
    And "CurrentFinancing" enum values can be compiled
    And "CurrentFinancing" stringlist-multi values are not null
    Then "CurrentFinancing" should be Array of Strings data type
    And "CurrentFinancing" should only contain enum values found in the metadata
    And "CurrentFinancing" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @CurrentUse @IDX_Payload @DD1.5_CurrentUse @DD1.6_CurrentUse
  Scenario: CurrentUse
    Given "CurrentUse" exists in the metadata
    And "CurrentUse" enum values can be compiled
    And "CurrentUse" stringlist-multi values are not null
    Then "CurrentUse" should be Array of Strings data type
    And "CurrentUse" should only contain enum values found in the metadata
    And "CurrentUse" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @DevelopmentStatus @DD1.5_DevelopmentStatus @DD1.6_DevelopmentStatus
  Scenario: DevelopmentStatus
    Given "DevelopmentStatus" exists in the metadata
    And "DevelopmentStatus" enum values can be compiled
    And "DevelopmentStatus" stringlist-multi values are not null
    Then "DevelopmentStatus" should be Array of Strings data type
    And "DevelopmentStatus" should only contain enum values found in the metadata
    And "DevelopmentStatus" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @Disclosures @DD1.5_Disclosures @DD1.6_Disclosures
  Scenario: Disclosures
    Given "Disclosures" exists in the metadata
    And "Disclosures" enum values can be compiled
    And "Disclosures" stringlist-multi values are not null
    Then "Disclosures" should be Array of Strings data type
    And "Disclosures" should only contain enum values found in the metadata
    And "Disclosures" length should be less than or equal to the RESO maxlength of 4000

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @DocumentsAvailable @DD1.5_DocumentsAvailable @DD1.6_DocumentsAvailable
  Scenario: DocumentsAvailable
    Given "DocumentsAvailable" exists in the metadata
    And "DocumentsAvailable" enum values can be compiled
    And "DocumentsAvailable" stringlist-multi values are not null
    Then "DocumentsAvailable" should be Array of Strings data type
    And "DocumentsAvailable" should only contain enum values found in the metadata
    And "DocumentsAvailable" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @DoorFeatures @DD1.5_DoorFeatures @DD1.6_DoorFeatures
  Scenario: DoorFeatures
    Given "DoorFeatures" exists in the metadata
    And "DoorFeatures" enum values can be compiled
    And "DoorFeatures" stringlist-multi values are not null
    Then "DoorFeatures" should be Array of Strings data type
    And "DoorFeatures" should only contain enum values found in the metadata
    And "DoorFeatures" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @Electric @IDX_Payload @DD1.5_Electric @DD1.6_Electric
  Scenario: Electric
    Given "Electric" exists in the metadata
    And "Electric" enum values can be compiled
    And "Electric" stringlist-multi values are not null
    Then "Electric" should be Array of Strings data type
    And "Electric" should only contain enum values found in the metadata
    And "Electric" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @ExistingLeaseType @DD1.5_ExistingLeaseType @DD1.6_ExistingLeaseType
  Scenario: ExistingLeaseType
    Given "ExistingLeaseType" exists in the metadata
    And "ExistingLeaseType" enum values can be compiled
    And "ExistingLeaseType" stringlist-multi values are not null
    Then "ExistingLeaseType" should be Array of Strings data type
    And "ExistingLeaseType" should only contain enum values found in the metadata
    And "ExistingLeaseType" length should be less than or equal to the RESO maxlength of 75

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @ExteriorFeatures @IDX_Payload @DD1.5_ExteriorFeatures @DD1.6_ExteriorFeatures
  Scenario: ExteriorFeatures
    Given "ExteriorFeatures" exists in the metadata
    And "ExteriorFeatures" enum values can be compiled
    And "ExteriorFeatures" stringlist-multi values are not null
    Then "ExteriorFeatures" should be Array of Strings data type
    And "ExteriorFeatures" should only contain enum values found in the metadata
    And "ExteriorFeatures" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @Fencing @IDX_Payload @DD1.5_Fencing @DD1.6_Fencing
  Scenario: Fencing
    Given "Fencing" exists in the metadata
    And "Fencing" enum values can be compiled
    And "Fencing" stringlist-multi values are not null
    Then "Fencing" should be Array of Strings data type
    And "Fencing" should only contain enum values found in the metadata
    And "Fencing" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @FinancialDataSource @DD1.5_FinancialDataSource @DD1.6_FinancialDataSource
  Scenario: FinancialDataSource
    Given "FinancialDataSource" exists in the metadata
    And "FinancialDataSource" enum values can be compiled
    And "FinancialDataSource" stringlist-multi values are not null
    Then "FinancialDataSource" should be Array of Strings data type
    And "FinancialDataSource" should only contain enum values found in the metadata
    And "FinancialDataSource" length should be less than or equal to the RESO maxlength of 75

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @FireplaceFeatures @IDX_Payload @DD1.5_FireplaceFeatures @DD1.6_FireplaceFeatures
  Scenario: FireplaceFeatures
    Given "FireplaceFeatures" exists in the metadata
    And "FireplaceFeatures" enum values can be compiled
    And "FireplaceFeatures" stringlist-multi values are not null
    Then "FireplaceFeatures" should be Array of Strings data type
    And "FireplaceFeatures" should only contain enum values found in the metadata
    And "FireplaceFeatures" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @Flooring @IDX_Payload @DD1.5_Flooring @DD1.6_Flooring
  Scenario: Flooring
    Given "Flooring" exists in the metadata
    And "Flooring" enum values can be compiled
    And "Flooring" stringlist-multi values are not null
    Then "Flooring" should be Array of Strings data type
    And "Flooring" should only contain enum values found in the metadata
    And "Flooring" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @FoundationDetails @IDX_Payload @DD1.5_FoundationDetails @DD1.6_FoundationDetails
  Scenario: FoundationDetails
    Given "FoundationDetails" exists in the metadata
    And "FoundationDetails" enum values can be compiled
    And "FoundationDetails" stringlist-multi values are not null
    Then "FoundationDetails" should be Array of Strings data type
    And "FoundationDetails" should only contain enum values found in the metadata
    And "FoundationDetails" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @FrontageType @DD1.5_FrontageType @DD1.6_FrontageType
  Scenario: FrontageType
    Given "FrontageType" exists in the metadata
    And "FrontageType" enum values can be compiled
    And "FrontageType" stringlist-multi values are not null
    Then "FrontageType" should be Array of Strings data type
    And "FrontageType" should only contain enum values found in the metadata
    And "FrontageType" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.5 @Gas @DD1.5_Gas @testing
  Scenario: Gas
    Given "Gas" exists in the metadata
    And "Gas" enum values can be compiled
    And "Gas" stringlist-multi values are not null
    Then "Gas" should be Array of Strings data type
    And "Gas" should only contain enum values found in the metadata
    And "Gas" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @GreenBuildingVerificationType @IDX_Payload @DD1.5_GreenBuildingVerificationType @DD1.6_GreenBuildingVerificationType
  Scenario: GreenBuildingVerificationType
    Given "GreenBuildingVerificationType" exists in the metadata
    And "GreenBuildingVerificationType" enum values can be compiled
    And "GreenBuildingVerificationType" stringlist-multi values are not null
    Then "GreenBuildingVerificationType" should be Array of Strings data type
    And "GreenBuildingVerificationType" should only contain enum values found in the metadata
    And "GreenBuildingVerificationType" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @GreenEnergyEfficient @DD1.5_GreenEnergyEfficient @DD1.6_GreenEnergyEfficient
  Scenario: GreenEnergyEfficient
    Given "GreenEnergyEfficient" exists in the metadata
    And "GreenEnergyEfficient" enum values can be compiled
    And "GreenEnergyEfficient" stringlist-multi values are not null
    Then "GreenEnergyEfficient" should be Array of Strings data type
    And "GreenEnergyEfficient" should only contain enum values found in the metadata
    And "GreenEnergyEfficient" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @GreenEnergyGeneration @DD1.5_GreenEnergyGeneration @DD1.6_GreenEnergyGeneration
  Scenario: GreenEnergyGeneration
    Given "GreenEnergyGeneration" exists in the metadata
    And "GreenEnergyGeneration" enum values can be compiled
    And "GreenEnergyGeneration" stringlist-multi values are not null
    Then "GreenEnergyGeneration" should be Array of Strings data type
    And "GreenEnergyGeneration" should only contain enum values found in the metadata
    And "GreenEnergyGeneration" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @GreenIndoorAirQuality @DD1.5_GreenIndoorAirQuality @DD1.6_GreenIndoorAirQuality
  Scenario: GreenIndoorAirQuality
    Given "GreenIndoorAirQuality" exists in the metadata
    And "GreenIndoorAirQuality" enum values can be compiled
    And "GreenIndoorAirQuality" stringlist-multi values are not null
    Then "GreenIndoorAirQuality" should be Array of Strings data type
    And "GreenIndoorAirQuality" should only contain enum values found in the metadata
    And "GreenIndoorAirQuality" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @GreenLocation @DD1.5_GreenLocation @DD1.6_GreenLocation
  Scenario: GreenLocation
    Given "GreenLocation" exists in the metadata
    And "GreenLocation" enum values can be compiled
    And "GreenLocation" stringlist-multi values are not null
    Then "GreenLocation" should be Array of Strings data type
    And "GreenLocation" should only contain enum values found in the metadata
    And "GreenLocation" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @GreenSustainability @DD1.5_GreenSustainability @DD1.6_GreenSustainability
  Scenario: GreenSustainability
    Given "GreenSustainability" exists in the metadata
    And "GreenSustainability" enum values can be compiled
    And "GreenSustainability" stringlist-multi values are not null
    Then "GreenSustainability" should be Array of Strings data type
    And "GreenSustainability" should only contain enum values found in the metadata
    And "GreenSustainability" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @GreenWaterConservation @DD1.5_GreenWaterConservation @DD1.6_GreenWaterConservation
  Scenario: GreenWaterConservation
    Given "GreenWaterConservation" exists in the metadata
    And "GreenWaterConservation" enum values can be compiled
    And "GreenWaterConservation" stringlist-multi values are not null
    Then "GreenWaterConservation" should be Array of Strings data type
    And "GreenWaterConservation" should only contain enum values found in the metadata
    And "GreenWaterConservation" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @Heating @IDX_Payload @DD1.5_Heating @DD1.6_Heating
  Scenario: Heating
    Given "Heating" exists in the metadata
    And "Heating" enum values can be compiled
    And "Heating" stringlist-multi values are not null
    Then "Heating" should be Array of Strings data type
    And "Heating" should only contain enum values found in the metadata
    And "Heating" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @HorseAmenities @DD1.5_HorseAmenities @DD1.6_HorseAmenities
  Scenario: HorseAmenities
    Given "HorseAmenities" exists in the metadata
    And "HorseAmenities" enum values can be compiled
    And "HorseAmenities" stringlist-multi values are not null
    Then "HorseAmenities" should be Array of Strings data type
    And "HorseAmenities" should only contain enum values found in the metadata
    And "HorseAmenities" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @HoursDaysOfOperation @DD1.5_HoursDaysOfOperation @DD1.6_HoursDaysOfOperation
  Scenario: HoursDaysOfOperation
    Given "HoursDaysOfOperation" exists in the metadata
    And "HoursDaysOfOperation" enum values can be compiled
    And "HoursDaysOfOperation" stringlist-multi values are not null
    Then "HoursDaysOfOperation" should be Array of Strings data type
    And "HoursDaysOfOperation" should only contain enum values found in the metadata
    And "HoursDaysOfOperation" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @IncomeIncludes @DD1.5_IncomeIncludes @DD1.6_IncomeIncludes
  Scenario: IncomeIncludes
    Given "IncomeIncludes" exists in the metadata
    And "IncomeIncludes" enum values can be compiled
    And "IncomeIncludes" stringlist-multi values are not null
    Then "IncomeIncludes" should be Array of Strings data type
    And "IncomeIncludes" should only contain enum values found in the metadata
    And "IncomeIncludes" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @InteriorFeatures @IDX_Payload @DD1.5_InteriorFeatures @DD1.6_InteriorFeatures
  Scenario: InteriorFeatures
    Given "InteriorFeatures" exists in the metadata
    And "InteriorFeatures" enum values can be compiled
    And "InteriorFeatures" stringlist-multi values are not null
    Then "InteriorFeatures" should be Array of Strings data type
    And "InteriorFeatures" should only contain enum values found in the metadata
    And "InteriorFeatures" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @IrrigationSource @DD1.5_IrrigationSource @DD1.6_IrrigationSource
  Scenario: IrrigationSource
    Given "IrrigationSource" exists in the metadata
    And "IrrigationSource" enum values can be compiled
    And "IrrigationSource" stringlist-multi values are not null
    Then "IrrigationSource" should be Array of Strings data type
    And "IrrigationSource" should only contain enum values found in the metadata
    And "IrrigationSource" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @LaborInformation @DD1.5_LaborInformation @DD1.6_LaborInformation
  Scenario: LaborInformation
    Given "LaborInformation" exists in the metadata
    And "LaborInformation" enum values can be compiled
    And "LaborInformation" stringlist-multi values are not null
    Then "LaborInformation" should be Array of Strings data type
    And "LaborInformation" should only contain enum values found in the metadata
    And "LaborInformation" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @LaundryFeatures @IDX_Payload @DD1.5_LaundryFeatures @DD1.6_LaundryFeatures
  Scenario: LaundryFeatures
    Given "LaundryFeatures" exists in the metadata
    And "LaundryFeatures" enum values can be compiled
    And "LaundryFeatures" stringlist-multi values are not null
    Then "LaundryFeatures" should be Array of Strings data type
    And "LaundryFeatures" should only contain enum values found in the metadata
    And "LaundryFeatures" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @LeaseRenewalCompensation @DD1.5_LeaseRenewalCompensation @DD1.6_LeaseRenewalCompensation
  Scenario: LeaseRenewalCompensation
    Given "LeaseRenewalCompensation" exists in the metadata
    And "LeaseRenewalCompensation" enum values can be compiled
    And "LeaseRenewalCompensation" stringlist-multi values are not null
    Then "LeaseRenewalCompensation" should be Array of Strings data type
    And "LeaseRenewalCompensation" should only contain enum values found in the metadata
    And "LeaseRenewalCompensation" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @Levels @IDX_Payload @DD1.5_Levels @DD1.6_Levels
  Scenario: Levels
    Given "Levels" exists in the metadata
    And "Levels" enum values can be compiled
    And "Levels" stringlist-multi values are not null
    Then "Levels" should be Array of Strings data type
    And "Levels" should only contain enum values found in the metadata
    And "Levels" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @ListAgentDesignation @DD1.5_ListAgentDesignation @DD1.6_ListAgentDesignation
  Scenario: ListAgentDesignation
    Given "ListAgentDesignation" exists in the metadata
    And "ListAgentDesignation" enum values can be compiled
    And "ListAgentDesignation" stringlist-multi values are not null
    Then "ListAgentDesignation" should be Array of Strings data type
    And "ListAgentDesignation" should only contain enum values found in the metadata
    And "ListAgentDesignation" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @ListingTerms @DD1.5_ListingTerms @DD1.6_ListingTerms
  Scenario: ListingTerms
    Given "ListingTerms" exists in the metadata
    And "ListingTerms" enum values can be compiled
    And "ListingTerms" stringlist-multi values are not null
    Then "ListingTerms" should be Array of Strings data type
    And "ListingTerms" should only contain enum values found in the metadata
    And "ListingTerms" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @LockBoxType @DD1.5_LockBoxType @DD1.6_LockBoxType
  Scenario: LockBoxType
    Given "LockBoxType" exists in the metadata
    And "LockBoxType" enum values can be compiled
    And "LockBoxType" stringlist-multi values are not null
    Then "LockBoxType" should be Array of Strings data type
    And "LockBoxType" should only contain enum values found in the metadata
    And "LockBoxType" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @LotFeatures @IDX_Payload @DD1.5_LotFeatures @DD1.6_LotFeatures
  Scenario: LotFeatures
    Given "LotFeatures" exists in the metadata
    And "LotFeatures" enum values can be compiled
    And "LotFeatures" stringlist-multi values are not null
    Then "LotFeatures" should be Array of Strings data type
    And "LotFeatures" should only contain enum values found in the metadata
    And "LotFeatures" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @OperatingExpenseIncludes @DD1.5_OperatingExpenseIncludes @DD1.6_OperatingExpenseIncludes
  Scenario: OperatingExpenseIncludes
    Given "OperatingExpenseIncludes" exists in the metadata
    And "OperatingExpenseIncludes" enum values can be compiled
    And "OperatingExpenseIncludes" stringlist-multi values are not null
    Then "OperatingExpenseIncludes" should be Array of Strings data type
    And "OperatingExpenseIncludes" should only contain enum values found in the metadata
    And "OperatingExpenseIncludes" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @OtherEquipment @DD1.5_OtherEquipment @DD1.6_OtherEquipment
  Scenario: OtherEquipment
    Given "OtherEquipment" exists in the metadata
    And "OtherEquipment" enum values can be compiled
    And "OtherEquipment" stringlist-multi values are not null
    Then "OtherEquipment" should be Array of Strings data type
    And "OtherEquipment" should only contain enum values found in the metadata
    And "OtherEquipment" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @OtherStructures @DD1.5_OtherStructures @DD1.6_OtherStructures
  Scenario: OtherStructures
    Given "OtherStructures" exists in the metadata
    And "OtherStructures" enum values can be compiled
    And "OtherStructures" stringlist-multi values are not null
    Then "OtherStructures" should be Array of Strings data type
    And "OtherStructures" should only contain enum values found in the metadata
    And "OtherStructures" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @OwnerPays @DD1.5_OwnerPays @DD1.6_OwnerPays
  Scenario: OwnerPays
    Given "OwnerPays" exists in the metadata
    And "OwnerPays" enum values can be compiled
    And "OwnerPays" stringlist-multi values are not null
    Then "OwnerPays" should be Array of Strings data type
    And "OwnerPays" should only contain enum values found in the metadata
    And "OwnerPays" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @ParkingFeatures @IDX_Payload @DD1.5_ParkingFeatures @DD1.6_ParkingFeatures
  Scenario: ParkingFeatures
    Given "ParkingFeatures" exists in the metadata
    And "ParkingFeatures" enum values can be compiled
    And "ParkingFeatures" stringlist-multi values are not null
    Then "ParkingFeatures" should be Array of Strings data type
    And "ParkingFeatures" should only contain enum values found in the metadata
    And "ParkingFeatures" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @PatioAndPorchFeatures @IDX_Payload @DD1.5_PatioAndPorchFeatures @DD1.6_PatioAndPorchFeatures
  Scenario: PatioAndPorchFeatures
    Given "PatioAndPorchFeatures" exists in the metadata
    And "PatioAndPorchFeatures" enum values can be compiled
    And "PatioAndPorchFeatures" stringlist-multi values are not null
    Then "PatioAndPorchFeatures" should be Array of Strings data type
    And "PatioAndPorchFeatures" should only contain enum values found in the metadata
    And "PatioAndPorchFeatures" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @PetsAllowed @DD1.5_PetsAllowed @DD1.6_PetsAllowed
  Scenario: PetsAllowed
    Given "PetsAllowed" exists in the metadata
    And "PetsAllowed" enum values can be compiled
    And "PetsAllowed" stringlist-multi values are not null
    Then "PetsAllowed" should be Array of Strings data type
    And "PetsAllowed" should only contain enum values found in the metadata
    And "PetsAllowed" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @PoolFeatures @IDX_Payload @DD1.5_PoolFeatures @DD1.6_PoolFeatures
  Scenario: PoolFeatures
    Given "PoolFeatures" exists in the metadata
    And "PoolFeatures" enum values can be compiled
    And "PoolFeatures" stringlist-multi values are not null
    Then "PoolFeatures" should be Array of Strings data type
    And "PoolFeatures" should only contain enum values found in the metadata
    And "PoolFeatures" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @Possession @DD1.5_Possession @DD1.6_Possession
  Scenario: Possession
    Given "Possession" exists in the metadata
    And "Possession" enum values can be compiled
    And "Possession" stringlist-multi values are not null
    Then "Possession" should be Array of Strings data type
    And "Possession" should only contain enum values found in the metadata
    And "Possession" length should be less than or equal to the RESO maxlength of 255

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @PossibleUse @IDX_Payload @DD1.5_PossibleUse @DD1.6_PossibleUse
  Scenario: PossibleUse
    Given "PossibleUse" exists in the metadata
    And "PossibleUse" enum values can be compiled
    And "PossibleUse" stringlist-multi values are not null
    Then "PossibleUse" should be Array of Strings data type
    And "PossibleUse" should only contain enum values found in the metadata
    And "PossibleUse" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @PowerProductionType @DD1.5_PowerProductionType @DD1.6_PowerProductionType
  Scenario: PowerProductionType
    Given "PowerProductionType" exists in the metadata
    And "PowerProductionType" enum values can be compiled
    And "PowerProductionType" stringlist-multi values are not null
    Then "PowerProductionType" should be Array of Strings data type
    And "PowerProductionType" should only contain enum values found in the metadata
    And "PowerProductionType" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @PropertyCondition @DD1.5_PropertyCondition @DD1.6_PropertyCondition
  Scenario: PropertyCondition
    Given "PropertyCondition" exists in the metadata
    And "PropertyCondition" enum values can be compiled
    And "PropertyCondition" stringlist-multi values are not null
    Then "PropertyCondition" should be Array of Strings data type
    And "PropertyCondition" should only contain enum values found in the metadata
    And "PropertyCondition" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @RentIncludes @IDX_Payload @DD1.5_RentIncludes @DD1.6_RentIncludes
  Scenario: RentIncludes
    Given "RentIncludes" exists in the metadata
    And "RentIncludes" enum values can be compiled
    And "RentIncludes" stringlist-multi values are not null
    Then "RentIncludes" should be Array of Strings data type
    And "RentIncludes" should only contain enum values found in the metadata
    And "RentIncludes" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @RoadFrontageType @DD1.5_RoadFrontageType @DD1.6_RoadFrontageType
  Scenario: RoadFrontageType
    Given "RoadFrontageType" exists in the metadata
    And "RoadFrontageType" enum values can be compiled
    And "RoadFrontageType" stringlist-multi values are not null
    Then "RoadFrontageType" should be Array of Strings data type
    And "RoadFrontageType" should only contain enum values found in the metadata
    And "RoadFrontageType" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @RoadResponsibility @DD1.5_RoadResponsibility @DD1.6_RoadResponsibility
  Scenario: RoadResponsibility
    Given "RoadResponsibility" exists in the metadata
    And "RoadResponsibility" enum values can be compiled
    And "RoadResponsibility" stringlist-multi values are not null
    Then "RoadResponsibility" should be Array of Strings data type
    And "RoadResponsibility" should only contain enum values found in the metadata
    And "RoadResponsibility" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @RoadSurfaceType @DD1.5_RoadSurfaceType @DD1.6_RoadSurfaceType
  Scenario: RoadSurfaceType
    Given "RoadSurfaceType" exists in the metadata
    And "RoadSurfaceType" enum values can be compiled
    And "RoadSurfaceType" stringlist-multi values are not null
    Then "RoadSurfaceType" should be Array of Strings data type
    And "RoadSurfaceType" should only contain enum values found in the metadata
    And "RoadSurfaceType" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @Roof @IDX_Payload @DD1.5_Roof @DD1.6_Roof
  Scenario: Roof
    Given "Roof" exists in the metadata
    And "Roof" enum values can be compiled
    And "Roof" stringlist-multi values are not null
    Then "Roof" should be Array of Strings data type
    And "Roof" should only contain enum values found in the metadata
    And "Roof" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @SecurityFeatures @DD1.5_SecurityFeatures @DD1.6_SecurityFeatures
  Scenario: SecurityFeatures
    Given "SecurityFeatures" exists in the metadata
    And "SecurityFeatures" enum values can be compiled
    And "SecurityFeatures" stringlist-multi values are not null
    Then "SecurityFeatures" should be Array of Strings data type
    And "SecurityFeatures" should only contain enum values found in the metadata
    And "SecurityFeatures" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @Sewer @IDX_Payload @DD1.5_Sewer @DD1.6_Sewer
  Scenario: Sewer
    Given "Sewer" exists in the metadata
    And "Sewer" enum values can be compiled
    And "Sewer" stringlist-multi values are not null
    Then "Sewer" should be Array of Strings data type
    And "Sewer" should only contain enum values found in the metadata
    And "Sewer" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @ShowingContactType @DD1.5_ShowingContactType @DD1.6_ShowingContactType
  Scenario: ShowingContactType
    Given "ShowingContactType" exists in the metadata
    And "ShowingContactType" enum values can be compiled
    And "ShowingContactType" stringlist-multi values are not null
    Then "ShowingContactType" should be Array of Strings data type
    And "ShowingContactType" should only contain enum values found in the metadata
    And "ShowingContactType" length should be less than or equal to the RESO maxlength of 75

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @Skirt @IDX_Payload @DD1.5_Skirt @DD1.6_Skirt
  Scenario: Skirt
    Given "Skirt" exists in the metadata
    And "Skirt" enum values can be compiled
    And "Skirt" stringlist-multi values are not null
    Then "Skirt" should be Array of Strings data type
    And "Skirt" should only contain enum values found in the metadata
    And "Skirt" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @SpaFeatures @IDX_Payload @DD1.5_SpaFeatures @DD1.6_SpaFeatures
  Scenario: SpaFeatures
    Given "SpaFeatures" exists in the metadata
    And "SpaFeatures" enum values can be compiled
    And "SpaFeatures" stringlist-multi values are not null
    Then "SpaFeatures" should be Array of Strings data type
    And "SpaFeatures" should only contain enum values found in the metadata
    And "SpaFeatures" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @SpecialLicenses @DD1.5_SpecialLicenses @DD1.6_SpecialLicenses
  Scenario: SpecialLicenses
    Given "SpecialLicenses" exists in the metadata
    And "SpecialLicenses" enum values can be compiled
    And "SpecialLicenses" stringlist-multi values are not null
    Then "SpecialLicenses" should be Array of Strings data type
    And "SpecialLicenses" should only contain enum values found in the metadata
    And "SpecialLicenses" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @SpecialListingConditions @IDX_Payload @DD1.5_SpecialListingConditions @DD1.6_SpecialListingConditions
  Scenario: SpecialListingConditions
    Given "SpecialListingConditions" exists in the metadata
    And "SpecialListingConditions" enum values can be compiled
    And "SpecialListingConditions" stringlist-multi values are not null
    Then "SpecialListingConditions" should be Array of Strings data type
    And "SpecialListingConditions" should only contain enum values found in the metadata
    And "SpecialListingConditions" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @StructureType @DD1.5_StructureType @DD1.6_StructureType
  Scenario: StructureType
    Given "StructureType" exists in the metadata
    And "StructureType" enum values can be compiled
    And "StructureType" stringlist-multi values are not null
    Then "StructureType" should be Array of Strings data type
    And "StructureType" should only contain enum values found in the metadata
    And "StructureType" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @SyndicateTo @DD1.5_SyndicateTo @DD1.6_SyndicateTo
  Scenario: SyndicateTo
    Given "SyndicateTo" exists in the metadata
    And "SyndicateTo" enum values can be compiled
    And "SyndicateTo" stringlist-multi values are not null
    Then "SyndicateTo" should be Array of Strings data type
    And "SyndicateTo" should only contain enum values found in the metadata
    And "SyndicateTo" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.5 @TaxExemptions @DD1.5_TaxExemptions
  Scenario: TaxExemptions
    Given "TaxExemptions" exists in the metadata
    And "TaxExemptions" enum values can be compiled
    And "TaxExemptions" stringlist-multi values are not null
    Then "TaxExemptions" should be Array of Strings data type
    And "TaxExemptions" should only contain enum values found in the metadata
    And "TaxExemptions" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @TaxStatusCurrent @DD1.5_TaxStatusCurrent @DD1.6_TaxStatusCurrent
  Scenario: TaxStatusCurrent
    Given "TaxStatusCurrent" exists in the metadata
    And "TaxStatusCurrent" enum values can be compiled
    And "TaxStatusCurrent" stringlist-multi values are not null
    Then "TaxStatusCurrent" should be Array of Strings data type
    And "TaxStatusCurrent" should only contain enum values found in the metadata
    And "TaxStatusCurrent" length should be less than or equal to the RESO maxlength of 50

  @DD1.5_StringList-Multi @DD1.5 @Telephone @DD1.5_Telephone
  Scenario: Telephone
    Given "Telephone" exists in the metadata
    And "Telephone" enum values can be compiled
    And "Telephone" stringlist-multi values are not null
    Then "Telephone" should be Array of Strings data type
    And "Telephone" should only contain enum values found in the metadata
    And "Telephone" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @TenantPays @DD1.5_TenantPays @DD1.6_TenantPays
  Scenario: TenantPays
    Given "TenantPays" exists in the metadata
    And "TenantPays" enum values can be compiled
    And "TenantPays" stringlist-multi values are not null
    Then "TenantPays" should be Array of Strings data type
    And "TenantPays" should only contain enum values found in the metadata
    And "TenantPays" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @UnitTypeType @IDX_Payload @DD1.5_UnitTypeType @DD1.6_UnitTypeType
  Scenario: UnitTypeType
    Given "UnitTypeType" exists in the metadata
    And "UnitTypeType" enum values can be compiled
    And "UnitTypeType" stringlist-multi values are not null
    Then "UnitTypeType" should be Array of Strings data type
    And "UnitTypeType" should only contain enum values found in the metadata
    And "UnitTypeType" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @Utilities @IDX_Payload @DD1.5_Utilities @DD1.6_Utilities
  Scenario: Utilities
    Given "Utilities" exists in the metadata
    And "Utilities" enum values can be compiled
    And "Utilities" stringlist-multi values are not null
    Then "Utilities" should be Array of Strings data type
    And "Utilities" should only contain enum values found in the metadata
    And "Utilities" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @Vegetation @DD1.5_Vegetation @DD1.6_Vegetation
  Scenario: Vegetation
    Given "Vegetation" exists in the metadata
    And "Vegetation" enum values can be compiled
    And "Vegetation" stringlist-multi values are not null
    Then "Vegetation" should be Array of Strings data type
    And "Vegetation" should only contain enum values found in the metadata
    And "Vegetation" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @View @IDX_Payload @DD1.5_View @DD1.6_View
  Scenario: View
    Given "View" exists in the metadata
    And "View" enum values can be compiled
    And "View" stringlist-multi values are not null
    Then "View" should be Array of Strings data type
    And "View" should only contain enum values found in the metadata
    And "View" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @WaterSource @DD1.5_WaterSource @DD1.6_WaterSource
  Scenario: WaterSource
    Given "WaterSource" exists in the metadata
    And "WaterSource" enum values can be compiled
    And "WaterSource" stringlist-multi values are not null
    Then "WaterSource" should be Array of Strings data type
    And "WaterSource" should only contain enum values found in the metadata
    And "WaterSource" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @WaterfrontFeatures @DD1.5_WaterfrontFeatures @DD1.6_WaterfrontFeatures
  Scenario: WaterfrontFeatures
    Given "WaterfrontFeatures" exists in the metadata
    And "WaterfrontFeatures" enum values can be compiled
    And "WaterfrontFeatures" stringlist-multi values are not null
    Then "WaterfrontFeatures" should be Array of Strings data type
    And "WaterfrontFeatures" should only contain enum values found in the metadata
    And "WaterfrontFeatures" length should be less than or equal to the RESO maxlength of 1024

  @DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @WindowFeatures @DD1.5_WindowFeatures @DD1.6_WindowFeatures
  Scenario: WindowFeatures
    Given "WindowFeatures" exists in the metadata
    And "WindowFeatures" enum values can be compiled
    And "WindowFeatures" stringlist-multi values are not null
    Then "WindowFeatures" should be Array of Strings data type
    And "WindowFeatures" should only contain enum values found in the metadata
    And "WindowFeatures" length should be less than or equal to the RESO maxlength of 1024

  
  


#@DD1.5_StringList-Multi @DD1.6_StringList-Multi @DD1.5 @DD1.6 @xxx @DD1.5_xxx @DD1.6_xxx
#Scenario: xxx
#  Given "xxx" exists in the metadata
#  And "xxx" enum values can be compiled
#  And "xxx" stringlist-multi values are not null
#  Then "xxx" should be Array of Strings data type
#  And "xxx" should only contain enum values found in the metadata 
#  And "xxx" length should be less than or equal to the RESO maxlength of 1024
