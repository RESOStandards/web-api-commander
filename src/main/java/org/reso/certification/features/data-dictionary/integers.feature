Feature: Integer Testing

  Background:

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @Bathrooms123 @DD1.5_Bathrooms123 @DD1.6_Bathrooms123 @testing @bad
  Scenario: Bathrooms123
    Given "Bathrooms123" exists in the metadata
    And "Bathrooms123" integer values are not null
    Then "Bathrooms123" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @BathroomsFull @IDX_Payload @DD1.5_BathroomsFull @DD1.6_BathroomsFull
  Scenario: BathroomsFull
    Given "BathroomsFull" exists in the metadata
    And "BathroomsFull" integer values are not null
    Then "BathroomsFull" should be Integer data type
    And "BathroomsFull" length should be less than or equal to the RESO maxlength of 3

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @BathroomsHalf @IDX_Payload @DD1.5_BathroomsHalf @DD1.6_BathroomsHalf
  Scenario: BathroomsHalf
    Given "BathroomsHalf" exists in the metadata
    And "BathroomsHalf" integer values are not null
    Then "BathroomsHalf" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @BathroomsOneQuarter @IDX_Payload @DD1.5_BathroomsOneQuarter @DD1.6_BathroomsOneQuarter @testing
  Scenario: BathroomsOneQuarter
    Given "BathroomsOneQuarter" exists in the metadata
    And "BathroomsOneQuarter" integer values are not null
    Then "BathroomsOneQuarter" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @BathroomsPartial @IDX_Payload @DD1.5_BathroomsPartial @DD1.6_BathroomsPartial
  Scenario: BathroomsPartial
    Given "BathroomsPartial" exists in the metadata
    And "BathroomsPartial" integer values are not null
    Then "BathroomsPartial" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @BathroomsThreeQuarter @IDX_Payload @DD1.5_BathroomsThreeQuarter @DD1.6_BathroomsThreeQuarter @testing
  Scenario: BathroomsThreeQuarter
    Given "BathroomsThreeQuarter" exists in the metadata
    And "BathroomsThreeQuarter" integer values are not null
    Then "BathroomsThreeQuarter" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @BathroomsTotalInteger @IDX_Payload @DD1.5_BathroomsTotalInteger @DD1.6_BathroomsTotalInteger
  Scenario: BathroomsTotalInteger
    Given "BathroomsTotalInteger" exists in the metadata
    And "BathroomsTotalInteger" integer values are not null
    Then "BathroomsTotalInteger" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @BedroomsPossible @DD1.5_BedroomsPossible @DD1.6_BedroomsPossible
  Scenario: BedroomsPossible
    Given "BedroomsPossible" exists in the metadata
    And "BedroomsPossible" integer values are not null
    Then "BedroomsPossible" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @BedroomsTotal @IDX_Payload @DD1.5_BedroomsTotal @DD1.6_BedroomsTotal
  Scenario: BedroomsTotal
    Given "BedroomsTotal" exists in the metadata
    And "BedroomsTotal" integer values are not null
    Then "BedroomsTotal" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @BuyerAgentKeyNumeric @DD1.5_BuyerAgentKeyNumeric @DD1.6_BuyerAgentKeyNumeric
  Scenario: BuyerAgentKeyNumeric
    Given "BuyerAgentKeyNumeric" exists in the metadata
    And "BuyerAgentKeyNumeric" integer values are not null
    Then "BuyerAgentKeyNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @BuyerOfficeKeyNumeric @DD1.5_BuyerOfficeKeyNumeric @DD1.6_BuyerOfficeKeyNumeric
  Scenario: BuyerOfficeKeyNumeric
    Given "BuyerOfficeKeyNumeric" exists in the metadata
    And "BuyerOfficeKeyNumeric" integer values are not null
    Then "BuyerOfficeKeyNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @BuyerTeamKeyNumeric @DD1.5_BuyerTeamKeyNumeric @DD1.6_BuyerTeamKeyNumeric
  Scenario: BuyerTeamKeyNumeric
    Given "BuyerTeamKeyNumeric" exists in the metadata
    And "BuyerTeamKeyNumeric" integer values are not null
    Then "BuyerTeamKeyNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @CoBuyerAgentKeyNumeric @DD1.5_CoBuyerAgentKeyNumeric @DD1.6_CoBuyerAgentKeyNumeric
  Scenario: CoBuyerAgentKeyNumeric
    Given "CoBuyerAgentKeyNumeric" exists in the metadata
    And "CoBuyerAgentKeyNumeric" integer values are not null
    Then "CoBuyerAgentKeyNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @CoBuyerOfficeKeyNumeric @DD1.5_CoBuyerOfficeKeyNumeric @DD1.6_CoBuyerOfficeKeyNumeric
  Scenario: CoBuyerOfficeKeyNumeric
    Given "CoBuyerOfficeKeyNumeric" exists in the metadata
    And "CoBuyerOfficeKeyNumeric" integer values are not null
    Then "CoBuyerOfficeKeyNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @CoListAgentKeyNumeric @DD1.5_CoListAgentKeyNumeric @DD1.6_CoListAgentKeyNumeric
  Scenario: CoListAgentKeyNumeric
    Given "CoListAgentKeyNumeric" exists in the metadata
    And "CoListAgentKeyNumeric" integer values are not null
    Then "CoListAgentKeyNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @CoListOfficeKeyNumeric @DD1.5_CoListOfficeKeyNumeric @DD1.6_CoListOfficeKeyNumeric
  Scenario: CoListOfficeKeyNumeric
    Given "CoListOfficeKeyNumeric" exists in the metadata
    And "CoListOfficeKeyNumeric" integer values are not null
    Then "CoListOfficeKeyNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @ConcessionsAmount @DD1.5_ConcessionsAmount @DD1.6_ConcessionsAmount
  Scenario: ConcessionsAmount
    Given "ConcessionsAmount" exists in the metadata
    And "ConcessionsAmount" integer values are not null
    Then "ConcessionsAmount" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @CumulativeDaysOnMarket @DD1.5_CumulativeDaysOnMarket @DD1.6_CumulativeDaysOnMarket
  Scenario: CumulativeDaysOnMarket
    Given "CumulativeDaysOnMarket" exists in the metadata
    And "CumulativeDaysOnMarket" integer values are not null
    Then "CumulativeDaysOnMarket" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @DaysOnMarket @DD1.5_DaysOnMarket @DD1.6_DaysOnMarket
  Scenario: DaysOnMarket
    Given "DaysOnMarket" exists in the metadata
    And "DaysOnMarket" integer values are not null
    Then "DaysOnMarket" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @DistanceToBusNumeric @DD1.5_DistanceToBusNumeric @DD1.6_DistanceToBusNumeric
  Scenario: DistanceToBusNumeric
    Given "DistanceToBusNumeric" exists in the metadata
    And "DistanceToBusNumeric" integer values are not null
    Then "DistanceToBusNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @DistanceToElectricNumeric @DD1.5_DistanceToElectricNumeric @DD1.6_DistanceToElectricNumeric
  Scenario: DistanceToElectricNumeric
    Given "DistanceToElectricNumeric" exists in the metadata
    And "DistanceToElectricNumeric" integer values are not null
    Then "DistanceToElectricNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @DistanceToFreewayNumeric @DD1.5_DistanceToFreewayNumeric @DD1.6_DistanceToFreewayNumeric
  Scenario: DistanceToFreewayNumeric
    Given "DistanceToFreewayNumeric" exists in the metadata
    And "DistanceToFreewayNumeric" integer values are not null
    Then "DistanceToFreewayNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @DistanceToGasNumeric @DD1.5_DistanceToGasNumeric @DD1.6_DistanceToGasNumeric
  Scenario: DistanceToGasNumeric
    Given "DistanceToGasNumeric" exists in the metadata
    And "DistanceToGasNumeric" integer values are not null
    Then "DistanceToGasNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @DistanceToPhoneServiceNumeric @DD1.5_DistanceToPhoneServiceNumeric @DD1.6_DistanceToPhoneServiceNumeric
  Scenario: DistanceToPhoneServiceNumeric
    Given "DistanceToPhoneServiceNumeric" exists in the metadata
    And "DistanceToPhoneServiceNumeric" integer values are not null
    Then "DistanceToPhoneServiceNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @DistanceToPlaceofWorshipNumeric @DD1.5_DistanceToPlaceofWorshipNumeric @DD1.6_DistanceToPlaceofWorshipNumeric
  Scenario: DistanceToPlaceofWorshipNumeric
    Given "DistanceToPlaceofWorshipNumeric" exists in the metadata
    And "DistanceToPlaceofWorshipNumeric" integer values are not null
    Then "DistanceToPlaceofWorshipNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @DistanceToSchoolBusNumeric @DD1.5_DistanceToSchoolBusNumeric @DD1.6_DistanceToSchoolBusNumeric
  Scenario: DistanceToSchoolBusNumeric
    Given "DistanceToSchoolBusNumeric" exists in the metadata
    And "DistanceToSchoolBusNumeric" integer values are not null
    Then "DistanceToSchoolBusNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @DistanceToSchoolsNumeric @DD1.5_DistanceToSchoolsNumeric @DD1.6_DistanceToSchoolsNumeric
  Scenario: DistanceToSchoolsNumeric
    Given "DistanceToSchoolsNumeric" exists in the metadata
    And "DistanceToSchoolsNumeric" integer values are not null
    Then "DistanceToSchoolsNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @DistanceToSewerNumeric @DD1.5_DistanceToSewerNumeric @DD1.6_DistanceToSewerNumeric
  Scenario: DistanceToSewerNumeric
    Given "DistanceToSewerNumeric" exists in the metadata
    And "DistanceToSewerNumeric" integer values are not null
    Then "DistanceToSewerNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @DistanceToShoppingNumeric @DD1.5_DistanceToShoppingNumeric @DD1.6_DistanceToShoppingNumeric
  Scenario: DistanceToShoppingNumeric
    Given "DistanceToShoppingNumeric" exists in the metadata
    And "DistanceToShoppingNumeric" integer values are not null
    Then "DistanceToShoppingNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @DistanceToStreetNumeric @DD1.5_DistanceToStreetNumeric @DD1.6_DistanceToStreetNumeric
  Scenario: DistanceToStreetNumeric
    Given "DistanceToStreetNumeric" exists in the metadata
    And "DistanceToStreetNumeric" integer values are not null
    Then "DistanceToStreetNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @DistanceToWaterNumeric @DD1.5_DistanceToWaterNumeric @DD1.6_DistanceToWaterNumeric
  Scenario: DistanceToWaterNumeric
    Given "DistanceToWaterNumeric" exists in the metadata
    And "DistanceToWaterNumeric" integer values are not null
    Then "DistanceToWaterNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @DocumentsCount @DD1.5_DocumentsCount @DD1.6_DocumentsCount
  Scenario: DocumentsCount
    Given "DocumentsCount" exists in the metadata
    And "DocumentsCount" integer values are not null
    Then "DocumentsCount" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @Elevation @DD1.5_Elevation @DD1.6_Elevation
  Scenario: Elevation
    Given "Elevation" exists in the metadata
    And "Elevation" integer values are not null
    Then "Elevation" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @EntryLevel @DD1.5_EntryLevel @DD1.6_EntryLevel
  Scenario: EntryLevel
    Given "EntryLevel" exists in the metadata
    And "EntryLevel" integer values are not null
    Then "EntryLevel" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @FireplacesTotal @IDX_Payload @DD1.5_FireplacesTotal @DD1.6_FireplacesTotal
  Scenario: FireplacesTotal
    Given "FireplacesTotal" exists in the metadata
    And "FireplacesTotal" integer values are not null
    Then "FireplacesTotal" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @ListAgentKeyNumeric @DD1.5_ListAgentKeyNumeric @DD1.6_ListAgentKeyNumeric
  Scenario: ListAgentKeyNumeric
    Given "ListAgentKeyNumeric" exists in the metadata
    And "ListAgentKeyNumeric" integer values are not null
    Then "ListAgentKeyNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @ListOfficeKeyNumeric @DD1.5_ListOfficeKeyNumeric @DD1.6_ListOfficeKeyNumeric
  Scenario: ListOfficeKeyNumeric
    Given "ListOfficeKeyNumeric" exists in the metadata
    And "ListOfficeKeyNumeric" integer values are not null
    Then "ListOfficeKeyNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @ListTeamKeyNumeric @DD1.5_ListTeamKeyNumeric @DD1.6_ListTeamKeyNumeric
  Scenario: ListTeamKeyNumeric
    Given "ListTeamKeyNumeric" exists in the metadata
    And "ListTeamKeyNumeric" integer values are not null
    Then "ListTeamKeyNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @ListingKey @IDX_Payload @DD1.5_ListingKeyNumeric @DD1.6_ListingKeyNumeric
  Scenario: ListingKeyNumeric
    Given "ListingKeyNumeric" exists in the metadata
    And "ListingKeyNumeric" integer values are not null
    Then "ListingKeyNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @MainLevelBathrooms @DD1.5_MainLevelBathrooms @DD1.6_MainLevelBathrooms
  Scenario: MainLevelBathrooms
    Given "MainLevelBathrooms" exists in the metadata
    And "MainLevelBathrooms" integer values are not null
    Then "MainLevelBathrooms" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @MainLevelBedrooms @DD1.5_MainLevelBedrooms @DD1.6_MainLevelBedrooms
  Scenario: MainLevelBedrooms
    Given "MainLevelBedrooms" exists in the metadata
    And "MainLevelBedrooms" integer values are not null
    Then "MainLevelBedrooms" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @MobileLength @IDX_Payload @DD1.5_MobileLength @DD1.6_MobileLength
  Scenario: MobileLength
    Given "MobileLength" exists in the metadata
    And "MobileLength" integer values are not null
    Then "MobileLength" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @MobileWidth @IDX_Payload @DD1.5_MobileWidth @DD1.6_MobileWidth
  Scenario: MobileWidth
    Given "MobileWidth" exists in the metadata
    And "MobileWidth" integer values are not null
    Then "MobileWidth" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @NumberOfBuildings @DD1.5_NumberOfBuildings @DD1.6_NumberOfBuildings
  Scenario: NumberOfBuildings
    Given "NumberOfBuildings" exists in the metadata
    And "NumberOfBuildings" integer values are not null
    Then "NumberOfBuildings" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @NumberOfFullTimeEmployees @DD1.5_NumberOfFullTimeEmployees @DD1.6_NumberOfFullTimeEmployees
  Scenario: NumberOfFullTimeEmployees
    Given "NumberOfFullTimeEmployees" exists in the metadata
    And "NumberOfFullTimeEmployees" integer values are not null
    Then "NumberOfFullTimeEmployees" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @NumberOfLots @DD1.5_NumberOfLots @DD1.6_NumberOfLots
  Scenario: NumberOfLots
    Given "NumberOfLots" exists in the metadata
    And "NumberOfLots" integer values are not null
    Then "NumberOfLots" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @NumberOfPads @DD1.5_NumberOfPads @DD1.6_NumberOfPads
  Scenario: NumberOfPads
    Given "NumberOfPads" exists in the metadata
    And "NumberOfPads" integer values are not null
    Then "NumberOfPads" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @NumberOfPartTimeEmployees @DD1.5_NumberOfPartTimeEmployees @DD1.6_NumberOfPartTimeEmployees
  Scenario: NumberOfPartTimeEmployees
    Given "NumberOfPartTimeEmployees" exists in the metadata
    And "NumberOfPartTimeEmployees" integer values are not null
    Then "NumberOfPartTimeEmployees" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @NumberOfSeparateElectricMeters @DD1.5_NumberOfSeparateElectricMeters @DD1.6_NumberOfSeparateElectricMeters
  Scenario: NumberOfSeparateElectricMeters
    Given "NumberOfSeparateElectricMeters" exists in the metadata
    And "NumberOfSeparateElectricMeters" integer values are not null
    Then "NumberOfSeparateElectricMeters" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @NumberOfSeparateGasMeters @DD1.5_NumberOfSeparateGasMeters @DD1.6_NumberOfSeparateGasMeters
  Scenario: NumberOfSeparateGasMeters
    Given "NumberOfSeparateGasMeters" exists in the metadata
    And "NumberOfSeparateGasMeters" integer values are not null
    Then "NumberOfSeparateGasMeters" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @NumberOfSeparateWaterMeters @DD1.5_NumberOfSeparateWaterMeters @DD1.6_NumberOfSeparateWaterMeters
  Scenario: NumberOfSeparateWaterMeters
    Given "NumberOfSeparateWaterMeters" exists in the metadata
    And "NumberOfSeparateWaterMeters" integer values are not null
    Then "NumberOfSeparateWaterMeters" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @NumberOfUnitsInCommunity @DD1.5_NumberOfUnitsInCommunity @DD1.6_NumberOfUnitsInCommunity
  Scenario: NumberOfUnitsInCommunity
    Given "NumberOfUnitsInCommunity" exists in the metadata
    And "NumberOfUnitsInCommunity" integer values are not null
    Then "NumberOfUnitsInCommunity" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @NumberOfUnitsLeased @DD1.5_NumberOfUnitsLeased @DD1.6_NumberOfUnitsLeased
  Scenario: NumberOfUnitsLeased
    Given "NumberOfUnitsLeased" exists in the metadata
    And "NumberOfUnitsLeased" integer values are not null
    Then "NumberOfUnitsLeased" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @NumberOfUnitsMoMo @DD1.5_NumberOfUnitsMoMo @DD1.6_NumberOfUnitsMoMo
  Scenario: NumberOfUnitsMoMo
    Given "NumberOfUnitsMoMo" exists in the metadata
    And "NumberOfUnitsMoMo" integer values are not null
    Then "NumberOfUnitsMoMo" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @NumberOfUnitsTotal @IDX_Payload @DD1.5_NumberOfUnitsTotal @DD1.6_NumberOfUnitsTotal
  Scenario: NumberOfUnitsTotal
    Given "NumberOfUnitsTotal" exists in the metadata
    And "NumberOfUnitsTotal" integer values are not null
    Then "NumberOfUnitsTotal" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @NumberOfUnitsVacant @DD1.5_NumberOfUnitsVacant @DD1.6_NumberOfUnitsVacant
  Scenario: NumberOfUnitsVacant
    Given "NumberOfUnitsVacant" exists in the metadata
    And "NumberOfUnitsVacant" integer values are not null
    Then "NumberOfUnitsVacant" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @PhotosCount @IDX_Payload @DD1.5_PhotosCount @DD1.6_PhotosCount
  Scenario: PhotosCount
    Given "PhotosCount" exists in the metadata
    And "PhotosCount" integer values are not null
    Then "PhotosCount" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @RoomsTotal @IDX_Payload @DD1.5_RoomsTotal @DD1.6_RoomsTotal
  Scenario: RoomsTotal
    Given "RoomsTotal" exists in the metadata
    And "RoomsTotal" integer values are not null
    Then "RoomsTotal" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @SeatingCapacity @DD1.5_SeatingCapacity @DD1.6_SeatingCapacity
  Scenario: SeatingCapacity
    Given "SeatingCapacity" exists in the metadata
    And "SeatingCapacity" integer values are not null
    Then "SeatingCapacity" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @Stories @IDX_Payload @DD1.5_Stories @DD1.6_Stories
  Scenario: Stories
    Given "Stories" exists in the metadata
    And "Stories" integer values are not null
    Then "Stories" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @Stories @IDX_Payload @DD1.5_StoriesTotal @DD1.6_StoriesTotal
  Scenario: StoriesTotal
    Given "StoriesTotal" exists in the metadata
    And "StoriesTotal" integer values are not null
    Then "StoriesTotal" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @StreetNumber @IDX_Payload @DD1.5_StreetNumberNumeric @DD1.6_StreetNumberNumeric
  Scenario: StreetNumberNumeric
    Given "StreetNumberNumeric" exists in the metadata
    And "StreetNumberNumeric" integer values are not null
    Then "StreetNumberNumeric" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @TaxAssessedValue @DD1.5_TaxAssessedValue @DD1.6_TaxAssessedValue
  Scenario: TaxAssessedValue
    Given "TaxAssessedValue" exists in the metadata
    And "TaxAssessedValue" integer values are not null
    Then "TaxAssessedValue" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @TaxYear @DD1.5_TaxYear @DD1.6_TaxYear
  Scenario: TaxYear
    Given "TaxYear" exists in the metadata
    And "TaxYear" integer values are not null
    Then "TaxYear" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @VacancyAllowance @DD1.5_VacancyAllowance @DD1.6_VacancyAllowance
  Scenario: VacancyAllowance
    Given "VacancyAllowance" exists in the metadata
    And "VacancyAllowance" integer values are not null
    Then "VacancyAllowance" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @VideosCount @DD1.5_VideosCount @DD1.6_VideosCount
  Scenario: VideosCount
    Given "VideosCount" exists in the metadata
    And "VideosCount" integer values are not null
    Then "VideosCount" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @WalkScore @DD1.5_WalkScore @DD1.6_WalkScore
  Scenario: WalkScore
    Given "WalkScore" exists in the metadata
    And "WalkScore" integer values are not null
    Then "WalkScore" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @YearBuilt @DD1.5_YearBuilt @DD1.6_YearBuilt
  Scenario: YearBuilt
    Given "YearBuilt" exists in the metadata
    And "YearBuilt" integer values are not null
    Then "YearBuilt" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @YearBuiltEffective @DD1.5_YearBuiltEffective @DD1.6_YearBuiltEffective
  Scenario: YearBuiltEffective
    Given "YearBuiltEffective" exists in the metadata
    And "YearBuiltEffective" integer values are not null
    Then "YearBuiltEffective" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @YearEstablished @DD1.5_YearEstablished @DD1.6_YearEstablished
  Scenario: YearEstablished
    Given "YearEstablished" exists in the metadata
    And "YearEstablished" integer values are not null
    Then "YearEstablished" should be Integer data type

  @DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @YearsCurrentOwner @DD1.5_YearsCurrentOwner @DD1.6_YearsCurrentOwner
  Scenario: YearsCurrentOwner
    Given "YearsCurrentOwner" exists in the metadata
    And "YearsCurrentOwner" integer values are not null
    Then "YearsCurrentOwner" should be Integer data type






 #@DD1.5_Integer @DD1.6_Integer @DD1.5 @DD1.6 @xxx @DD1.5_xxx @DD1.6_xxx
 #Scenario: xxx
 #  Given "xxx" exists in the metadata
 #	And "xxx" integer values are not null
 #  Then "xxx" should be Integer data type
 #  And xxx length should be between the bounds in the metadata
 #  And xxx length should be less than or equal to the RESO maxlength of yyyyyyy
