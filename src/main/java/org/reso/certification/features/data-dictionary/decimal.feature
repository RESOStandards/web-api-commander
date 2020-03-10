Feature: Decimal Testing

  Background:

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @AboveGradeFinishedArea @DD1.5_AboveGradeFinishedArea @DD1.6_AboveGradeFinishedArea
  Scenario: AboveGradeFinishedArea
    Given "AboveGradeFinishedArea" exists in the metadata
    And "AboveGradeFinishedArea" decimal values are not null
    Then "AboveGradeFinishedArea" should be Decimal data type
    And "AboveGradeFinishedArea" precision should be between the bounds in the metadata
    And "AboveGradeFinishedArea" scale should be between the bounds in the metadata
    And "AboveGradeFinishedArea" precision should be less than or equal to the RESO maxlength of 14
    And "AboveGradeFinishedArea" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @AssociationFee @IDX_Payload @DD1.5_AssociationFee @DD1.6_AssociationFee
  Scenario: AssociationFee
    Given "AssociationFee" exists in the metadata
    And "AssociationFee" decimal values are not null
    Then "AssociationFee" should be Decimal data type
    And "AssociationFee" precision should be between the bounds in the metadata
    And "AssociationFee" scale should be between the bounds in the metadata
    And "AssociationFee" precision should be less than or equal to the RESO maxlength of 14
    And "AssociationFee" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @AssociationFee2 @DD1.5_AssociationFee2 @DD1.6_AssociationFee2
  Scenario: AssociationFee2
    Given "AssociationFee2" exists in the metadata
    And "AssociationFee2" decimal values are not null
    Then "AssociationFee2" should be Decimal data type
    And "AssociationFee2" precision should be between the bounds in the metadata
    And "AssociationFee2" scale should be between the bounds in the metadata
    And "AssociationFee2" precision should be less than or equal to the RESO maxlength of 14
    And "AssociationFee2" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @BelowGradeFinishedArea @DD1.5_BelowGradeFinishedArea @DD1.6_BelowGradeFinishedArea
  Scenario: BelowGradeFinishedArea
    Given "BelowGradeFinishedArea" exists in the metadata
    And "BelowGradeFinishedArea" decimal values are not null
    Then "BelowGradeFinishedArea" should be Decimal data type
    And "BelowGradeFinishedArea" precision should be between the bounds in the metadata
    And "BelowGradeFinishedArea" scale should be between the bounds in the metadata
    And "BelowGradeFinishedArea" precision should be less than or equal to the RESO maxlength of 14
    And "BelowGradeFinishedArea" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @BuildingAreaTotal @DD1.5_BuildingAreaTotal @DD1.6_BuildingAreaTotal
  Scenario: BuildingAreaTotal
    Given "BuildingAreaTotal" exists in the metadata
    And "BuildingAreaTotal" decimal values are not null
    Then "BuildingAreaTotal" should be Decimal data type
    And "BuildingAreaTotal" precision should be between the bounds in the metadata
    And "BuildingAreaTotal" scale should be between the bounds in the metadata
    And "BuildingAreaTotal" precision should be less than or equal to the RESO maxlength of 14
    And "BuildingAreaTotal" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @CableTvExpense @DD1.5_CableTvExpense @DD1.6_CableTvExpense
  Scenario: CableTvExpense
    Given "CableTvExpense" exists in the metadata
    And "CableTvExpense" decimal values are not null
    Then "CableTvExpense" should be Decimal data type
    And "CableTvExpense" precision should be between the bounds in the metadata
    And "CableTvExpense" scale should be between the bounds in the metadata
    And "CableTvExpense" precision should be less than or equal to the RESO maxlength of 14
    And "CableTvExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @CapRate @DD1.5_CapRate @DD1.6_CapRate
  Scenario: CapRate
    Given "CapRate" exists in the metadata
    And "CapRate" decimal values are not null
    Then "CapRate" should be Decimal data type
    And "CapRate" precision should be between the bounds in the metadata
    And "CapRate" scale should be between the bounds in the metadata
    And "CapRate" precision should be less than or equal to the RESO maxlength of 5
    And "CapRate" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @CarportSpaces @IDX_Payload @DD1.5_CarportSpaces @DD1.6_CarportSpaces
  Scenario: CarportSpaces
    Given "CarportSpaces" exists in the metadata
    And "CarportSpaces" decimal values are not null
    Then "CarportSpaces" should be Decimal data type
    And "CarportSpaces" precision should be between the bounds in the metadata
    And "CarportSpaces" scale should be between the bounds in the metadata
    And "CarportSpaces" precision should be less than or equal to the RESO maxlength of 14
    And "CarportSpaces" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @ClosePrice @IDX_Payload @DD1.5_ClosePrice @DD1.6_ClosePrice
  Scenario: ClosePrice
    Given "ClosePrice" exists in the metadata
    And "ClosePrice" decimal values are not null
    Then "ClosePrice" should be Decimal data type
    And "ClosePrice" precision should be between the bounds in the metadata
    And "ClosePrice" scale should be between the bounds in the metadata
    And "ClosePrice" precision should be less than or equal to the RESO maxlength of 14
    And "ClosePrice" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @CoveredSpaces @DD1.5_CoveredSpaces @DD1.6_CoveredSpaces
  Scenario: CoveredSpaces
    Given "CoveredSpaces" exists in the metadata
    And "CoveredSpaces" decimal values are not null
    Then "CoveredSpaces" should be Decimal data type
    And "CoveredSpaces" precision should be between the bounds in the metadata
    And "CoveredSpaces" scale should be between the bounds in the metadata
    And "CoveredSpaces" precision should be less than or equal to the RESO maxlength of 14
    And "CoveredSpaces" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @CultivatedArea @DD1.5_CultivatedArea @DD1.6_CultivatedArea
  Scenario: CultivatedArea
    Given "CultivatedArea" exists in the metadata
    And "CultivatedArea" decimal values are not null
    Then "CultivatedArea" should be Decimal data type
    And "CultivatedArea" precision should be between the bounds in the metadata
    And "CultivatedArea" scale should be between the bounds in the metadata
    And "CultivatedArea" precision should be less than or equal to the RESO maxlength of 14
    And "CultivatedArea" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @ElectricExpense @DD1.5_ElectricExpense @DD1.6_ElectricExpense
  Scenario: ElectricExpense
    Given "ElectricExpense" exists in the metadata
    And "ElectricExpense" decimal values are not null
    Then "ElectricExpense" should be Decimal data type
    And "ElectricExpense" precision should be between the bounds in the metadata
    And "ElectricExpense" scale should be between the bounds in the metadata
    And "ElectricExpense" precision should be less than or equal to the RESO maxlength of 14
    And "ElectricExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @FoundationArea @DD1.5_FoundationArea @DD1.6_FoundationArea
  Scenario: FoundationArea
    Given "FoundationArea" exists in the metadata
    And "FoundationArea" decimal values are not null
    Then "FoundationArea" should be Decimal data type
    And "FoundationArea" precision should be between the bounds in the metadata
    And "FoundationArea" scale should be between the bounds in the metadata
    And "FoundationArea" precision should be less than or equal to the RESO maxlength of 14
    And "FoundationArea" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @FuelExpense @DD1.5_FuelExpense @DD1.6_FuelExpense
  Scenario: FuelExpense
    Given "FuelExpense" exists in the metadata
    And "FuelExpense" decimal values are not null
    Then "FuelExpense" should be Decimal data type
    And "FuelExpense" precision should be between the bounds in the metadata
    And "FuelExpense" scale should be between the bounds in the metadata
    And "FuelExpense" precision should be less than or equal to the RESO maxlength of 14
    And "FuelExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @FurnitureReplacementExpense @DD1.5_FurnitureReplacementExpense @DD1.6_FurnitureReplacementExpense
  Scenario: FurnitureReplacementExpense
    Given "FurnitureReplacementExpense" exists in the metadata
    And "FurnitureReplacementExpense" decimal values are not null
    Then "FurnitureReplacementExpense" should be Decimal data type
    And "FurnitureReplacementExpense" precision should be between the bounds in the metadata
    And "FurnitureReplacementExpense" scale should be between the bounds in the metadata
    And "FurnitureReplacementExpense" precision should be less than or equal to the RESO maxlength of 14
    And "FurnitureReplacementExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @GarageSpaces @IDX_Payload @DD1.5_GarageSpaces @DD1.6_GarageSpaces
  Scenario: GarageSpaces
    Given "GarageSpaces" exists in the metadata
    And "GarageSpaces" decimal values are not null
    Then "GarageSpaces" should be Decimal data type
    And "GarageSpaces" precision should be between the bounds in the metadata
    And "GarageSpaces" scale should be between the bounds in the metadata
    And "GarageSpaces" precision should be less than or equal to the RESO maxlength of 14
    And "GarageSpaces" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.5 @GardnerExpense @DD1.5_GardnerExpense
  Scenario: GardnerExpense
    Given "GardnerExpense" exists in the metadata
    And "GardnerExpense" decimal values are not null
    Then "GardnerExpense" should be Decimal data type
    And "GardnerExpense" precision should be between the bounds in the metadata
    And "GardnerExpense" scale should be between the bounds in the metadata
    And "GardnerExpense" precision should be less than or equal to the RESO maxlength of 14
    And "GardnerExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.6_Decimal @DD1.6 @GardenerExpense @DD1.6_GardenerExpense
  Scenario: GardenerExpense
    Given "GardenerExpense" exists in the metadata
    And "GardenerExpense" decimal values are not null
    Then "GardenerExpense" should be Decimal data type
    And "GardenerExpense" precision should be between the bounds in the metadata
    And "GardenerExpense" scale should be between the bounds in the metadata
    And "GardenerExpense" precision should be less than or equal to the RESO maxlength of 14
    And "GardenerExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @GrossIncome @DD1.5_GrossIncome @DD1.6_GrossIncome
  Scenario: GrossIncome
    Given "GrossIncome" exists in the metadata
    And "GrossIncome" decimal values are not null
    Then "GrossIncome" should be Decimal data type
    And "GrossIncome" precision should be between the bounds in the metadata
    And "GrossIncome" scale should be between the bounds in the metadata
    And "GrossIncome" precision should be less than or equal to the RESO maxlength of 14
    And "GrossIncome" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @GrossScheduledIncome @DD1.5_GrossScheduledIncome @DD1.6_GrossScheduledIncome
  Scenario: GrossScheduledIncome
    Given "GrossScheduledIncome" exists in the metadata
    And "GrossScheduledIncome" decimal values are not null
    Then "GrossScheduledIncome" should be Decimal data type
    And "GrossScheduledIncome" precision should be between the bounds in the metadata
    And "GrossScheduledIncome" scale should be between the bounds in the metadata
    And "GrossScheduledIncome" precision should be less than or equal to the RESO maxlength of 14
    And "GrossScheduledIncome" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @InsuranceExpense @DD1.5_InsuranceExpense @DD1.6_InsuranceExpense
  Scenario: InsuranceExpense
    Given "InsuranceExpense" exists in the metadata
    And "InsuranceExpense" decimal values are not null
    Then "InsuranceExpense" should be Decimal data type
    And "InsuranceExpense" precision should be between the bounds in the metadata
    And "InsuranceExpense" scale should be between the bounds in the metadata
    And "InsuranceExpense" precision should be less than or equal to the RESO maxlength of 14
    And "InsuranceExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @LandLeaseAmount @DD1.5_LandLeaseAmount @DD1.6_LandLeaseAmount
  Scenario: LandLeaseAmount
    Given "LandLeaseAmount" exists in the metadata
    And "LandLeaseAmount" decimal values are not null
    Then "LandLeaseAmount" should be Decimal data type
    And "LandLeaseAmount" precision should be between the bounds in the metadata
    And "LandLeaseAmount" scale should be between the bounds in the metadata
    And "LandLeaseAmount" precision should be less than or equal to the RESO maxlength of 14
    And "LandLeaseAmount" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @LeasableArea @DD1.5_LeasableArea @DD1.6_LeasableArea
  Scenario: LeasableArea
    Given "LeasableArea" exists in the metadata
    And "LeasableArea" decimal values are not null
    Then "LeasableArea" should be Decimal data type
    And "LeasableArea" precision should be between the bounds in the metadata
    And "LeasableArea" scale should be between the bounds in the metadata
    And "LeasableArea" precision should be less than or equal to the RESO maxlength of 14
    And "LeasableArea" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @LeaseAmount @DD1.5_LeaseAmount @DD1.6_LeaseAmount
  Scenario: LeaseAmount
    Given "LeaseAmount" exists in the metadata
    And "LeaseAmount" decimal values are not null
    Then "LeaseAmount" should be Decimal data type
    And "LeaseAmount" precision should be between the bounds in the metadata
    And "LeaseAmount" scale should be between the bounds in the metadata
    And "LeaseAmount" precision should be less than or equal to the RESO maxlength of 14
    And "LeaseAmount" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @LicensesExpense @DD1.5_LicensesExpense @DD1.6_LicensesExpense
  Scenario: LicensesExpense
    Given "LicensesExpense" exists in the metadata
    And "LicensesExpense" decimal values are not null
    Then "LicensesExpense" should be Decimal data type
    And "LicensesExpense" precision should be between the bounds in the metadata
    And "LicensesExpense" scale should be between the bounds in the metadata
    And "LicensesExpense" precision should be less than or equal to the RESO maxlength of 14
    And "LicensesExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @ListPrice @IDX_Payload @DD1.5_ListPrice @DD1.6_ListPrice
  Scenario: ListPrice
    Given "ListPrice" exists in the metadata
    And "ListPrice" decimal values are not null
    Then "ListPrice" should be Decimal data type
    And "ListPrice" precision should be between the bounds in the metadata
    And "ListPrice" scale should be between the bounds in the metadata
    And "ListPrice" precision should be less than or equal to the RESO maxlength of 14
    And "ListPrice" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @ListPrice @IDX_Payload @DD1.5_ListPriceLow @DD1.6_ListPriceLow
  Scenario: ListPriceLow
    Given "ListPriceLow" exists in the metadata
    And "ListPriceLow" decimal values are not null
    Then "ListPriceLow" should be Decimal data type
    And "ListPriceLow" precision should be between the bounds in the metadata
    And "ListPriceLow" scale should be between the bounds in the metadata
    And "ListPriceLow" precision should be less than or equal to the RESO maxlength of 14
    And "ListPriceLow" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @LivingArea @IDX_Payload @DD1.5_LivingArea @DD1.6_LivingArea
  Scenario: LivingArea
    Given "LivingArea" exists in the metadata
    And "LivingArea" decimal values are not null
    Then "LivingArea" should be Decimal data type
    And "LivingArea" precision should be between the bounds in the metadata
    And "LivingArea" scale should be between the bounds in the metadata
    And "LivingArea" precision should be less than or equal to the RESO maxlength of 14
    And "LivingArea" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @LotSizeSquareFeet @IDX_Payload @DD1.5_LotSizeSquareFeet @DD1.6_LotSizeSquareFeet
  Scenario: LotSizeSquareFeet
    Given "LotSizeSquareFeet" exists in the metadata
    And "LotSizeSquareFeet" decimal values are not null
    Then "LotSizeSquareFeet" should be Decimal data type
    And "LotSizeSquareFeet" precision should be between the bounds in the metadata
    And "LotSizeSquareFeet" scale should be between the bounds in the metadata
    And "LotSizeSquareFeet" precision should be less than or equal to the RESO maxlength of 14
    And "LotSizeSquareFeet" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @MaintenanceExpense @DD1.5_MaintenanceExpense @DD1.6_MaintenanceExpense
  Scenario: MaintenanceExpense
    Given "MaintenanceExpense" exists in the metadata
    And "MaintenanceExpense" decimal values are not null
    Then "MaintenanceExpense" should be Decimal data type
    And "MaintenanceExpense" precision should be between the bounds in the metadata
    And "MaintenanceExpense" scale should be between the bounds in the metadata
    And "MaintenanceExpense" precision should be less than or equal to the RESO maxlength of 14
    And "MaintenanceExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @ManagerExpense @DD1.5_ManagerExpense @DD1.6_ManagerExpense
  Scenario: ManagerExpense
    Given "ManagerExpense" exists in the metadata
    And "ManagerExpense" decimal values are not null
    Then "ManagerExpense" should be Decimal data type
    And "ManagerExpense" precision should be between the bounds in the metadata
    And "ManagerExpense" scale should be between the bounds in the metadata
    And "ManagerExpense" precision should be less than or equal to the RESO maxlength of 14
    And "ManagerExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @NetOperatingIncome @DD1.5_NetOperatingIncome @DD1.6_NetOperatingIncome
  Scenario: NetOperatingIncome
    Given "NetOperatingIncome" exists in the metadata
    And "NetOperatingIncome" decimal values are not null
    Then "NetOperatingIncome" should be Decimal data type
    And "NetOperatingIncome" precision should be between the bounds in the metadata
    And "NetOperatingIncome" scale should be between the bounds in the metadata
    And "NetOperatingIncome" precision should be less than or equal to the RESO maxlength of 14
    And "NetOperatingIncome" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @NewTaxesExpense @DD1.5_NewTaxesExpense @DD1.6_NewTaxesExpense
  Scenario: NewTaxesExpense
    Given "NewTaxesExpense" exists in the metadata
    And "NewTaxesExpense" decimal values are not null
    Then "NewTaxesExpense" should be Decimal data type
    And "NewTaxesExpense" precision should be between the bounds in the metadata
    And "NewTaxesExpense" scale should be between the bounds in the metadata
    And "NewTaxesExpense" precision should be less than or equal to the RESO maxlength of 14
    And "NewTaxesExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @OpenParkingSpaces @DD1.5_OpenParkingSpaces @DD1.6_OpenParkingSpaces
  Scenario: OpenParkingSpaces
    Given "OpenParkingSpaces" exists in the metadata
    And "OpenParkingSpaces" decimal values are not null
    Then "OpenParkingSpaces" should be Decimal data type
    And "OpenParkingSpaces" precision should be between the bounds in the metadata
    And "OpenParkingSpaces" scale should be between the bounds in the metadata
    And "OpenParkingSpaces" precision should be less than or equal to the RESO maxlength of 14
    And "OpenParkingSpaces" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @OperatingExpense @DD1.5_OperatingExpense @DD1.6_OperatingExpense
  Scenario: OperatingExpense
    Given "OperatingExpense" exists in the metadata
    And "OperatingExpense" decimal values are not null
    Then "OperatingExpense" should be Decimal data type
    And "OperatingExpense" precision should be between the bounds in the metadata
    And "OperatingExpense" scale should be between the bounds in the metadata
    And "OperatingExpense" precision should be less than or equal to the RESO maxlength of 14
    And "OperatingExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @OriginalListPrice @DD1.5_OriginalListPrice @DD1.6_OriginalListPrice @testing
  Scenario: OriginalListPrice
    Given "OriginalListPrice" exists in the metadata
    And "OriginalListPrice" decimal values are not null
    Then "OriginalListPrice" should be Decimal data type
    And "OriginalListPrice" precision should be between the bounds in the metadata
    And "OriginalListPrice" scale should be between the bounds in the metadata
    And "OriginalListPrice" precision should be less than or equal to the RESO maxlength of 14
    And "OriginalListPrice" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @OtherExpense @DD1.5_OtherExpense @DD1.6_OtherExpense
  Scenario: OtherExpense
    Given "OtherExpense" exists in the metadata
    And "OtherExpense" decimal values are not null
    Then "OtherExpense" should be Decimal data type
    And "OtherExpense" precision should be between the bounds in the metadata
    And "OtherExpense" scale should be between the bounds in the metadata
    And "OtherExpense" precision should be less than or equal to the RESO maxlength of 14
    And "OtherExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @ParkingTotal @DD1.5_ParkingTotal @DD1.6_ParkingTotal
  Scenario: ParkingTotal
    Given "ParkingTotal" exists in the metadata
    And "ParkingTotal" decimal values are not null
    Then "ParkingTotal" should be Decimal data type
    And "ParkingTotal" precision should be between the bounds in the metadata
    And "ParkingTotal" scale should be between the bounds in the metadata
    And "ParkingTotal" precision should be less than or equal to the RESO maxlength of 14
    And "ParkingTotal" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @PastureArea @DD1.5_PastureArea @DD1.6_PastureArea
  Scenario: PastureArea
    Given "PastureArea" exists in the metadata
    And "PastureArea" decimal values are not null
    Then "PastureArea" should be Decimal data type
    And "PastureArea" precision should be between the bounds in the metadata
    And "PastureArea" scale should be between the bounds in the metadata
    And "PastureArea" precision should be less than or equal to the RESO maxlength of 14
    And "PastureArea" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @PestControlExpense @DD1.5_PestControlExpense @DD1.6_PestControlExpense
  Scenario: PestControlExpense
    Given "PestControlExpense" exists in the metadata
    And "PestControlExpense" decimal values are not null
    Then "PestControlExpense" should be Decimal data type
    And "PestControlExpense" precision should be between the bounds in the metadata
    And "PestControlExpense" scale should be between the bounds in the metadata
    And "PestControlExpense" precision should be less than or equal to the RESO maxlength of 14
    And "PestControlExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @PoolExpense @DD1.5_PoolExpense @DD1.6_PoolExpense
  Scenario: PoolExpense
    Given "PoolExpense" exists in the metadata
    And "PoolExpense" decimal values are not null
    Then "PoolExpense" should be Decimal data type
    And "PoolExpense" precision should be between the bounds in the metadata
    And "PoolExpense" scale should be between the bounds in the metadata
    And "PoolExpense" precision should be less than or equal to the RESO maxlength of 14
    And "PoolExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @PreviousListPrice @DD1.5_PreviousListPrice @DD1.6_PreviousListPrice
  Scenario: PreviousListPrice
    Given "PreviousListPrice" exists in the metadata
    And "PreviousListPrice" decimal values are not null
    Then "PreviousListPrice" should be Decimal data type
    And "PreviousListPrice" precision should be between the bounds in the metadata
    And "PreviousListPrice" scale should be between the bounds in the metadata
    And "PreviousListPrice" precision should be less than or equal to the RESO maxlength of 14
    And "PreviousListPrice" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @ProfessionalManagementExpense @DD1.5_ProfessionalManagementExpense @DD1.6_ProfessionalManagementExpense
  Scenario: ProfessionalManagementExpense
    Given "ProfessionalManagementExpense" exists in the metadata
    And "ProfessionalManagementExpense" decimal values are not null
    Then "ProfessionalManagementExpense" should be Decimal data type
    And "ProfessionalManagementExpense" precision should be between the bounds in the metadata
    And "ProfessionalManagementExpense" scale should be between the bounds in the metadata
    And "ProfessionalManagementExpense" precision should be less than or equal to the RESO maxlength of 14
    And "ProfessionalManagementExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @RangeArea @DD1.5_RangeArea @DD1.6_RangeArea
  Scenario: RangeArea
    Given "RangeArea" exists in the metadata
    And "RangeArea" decimal values are not null
    Then "RangeArea" should be Decimal data type
    And "RangeArea" precision should be between the bounds in the metadata
    And "RangeArea" scale should be between the bounds in the metadata
    And "RangeArea" precision should be less than or equal to the RESO maxlength of 14
    And "RangeArea" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @RangeArea @DD1.5_RangeArea @DD1.6_RangeArea
  Scenario: RangeArea
    Given "RangeArea" exists in the metadata
    And "RangeArea" decimal values are not null
    Then "RangeArea" should be Decimal data type
    And "RangeArea" precision should be between the bounds in the metadata
    And "RangeArea" scale should be between the bounds in the metadata
    And "RangeArea" precision should be less than or equal to the RESO maxlength of 14
    And "RangeArea" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @TaxAnnualAmount @IDX_Payload @DD1.5_TaxAnnualAmount @DD1.6_TaxAnnualAmount
  Scenario: TaxAnnualAmount
    Given "TaxAnnualAmount" exists in the metadata
    And "TaxAnnualAmount" decimal values are not null
    Then "TaxAnnualAmount" should be Decimal data type
    And "TaxAnnualAmount" precision should be between the bounds in the metadata
    And "TaxAnnualAmount" scale should be between the bounds in the metadata
    And "TaxAnnualAmount" precision should be less than or equal to the RESO maxlength of 14
    And "TaxAnnualAmount" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @TaxOtherAnnualAssessmentAmount @DD1.5_TaxOtherAnnualAssessmentAmount @DD1.6_TaxOtherAnnualAssessmentAmount
  Scenario: TaxOtherAnnualAssessmentAmount
    Given "TaxOtherAnnualAssessmentAmount" exists in the metadata
    And "TaxOtherAnnualAssessmentAmount" decimal values are not null
    Then "TaxOtherAnnualAssessmentAmount" should be Decimal data type
    And "TaxOtherAnnualAssessmentAmount" precision should be between the bounds in the metadata
    And "TaxOtherAnnualAssessmentAmount" scale should be between the bounds in the metadata
    And "TaxOtherAnnualAssessmentAmount" precision should be less than or equal to the RESO maxlength of 14
    And "TaxOtherAnnualAssessmentAmount" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @TotalActualRent @DD1.5_TotalActualRent @DD1.6_TotalActualRent
  Scenario: TotalActualRent
    Given "TotalActualRent" exists in the metadata
    And "TotalActualRent" decimal values are not null
    Then "TotalActualRent" should be Decimal data type
    And "TotalActualRent" precision should be between the bounds in the metadata
    And "TotalActualRent" scale should be between the bounds in the metadata
    And "TotalActualRent" precision should be less than or equal to the RESO maxlength of 14
    And "TotalActualRent" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @TrashExpense @DD1.5_TrashExpense @DD1.6_TrashExpense
  Scenario: TrashExpense
    Given "TrashExpense" exists in the metadata
    And "TrashExpense" decimal values are not null
    Then "TrashExpense" should be Decimal data type
    And "TrashExpense" precision should be between the bounds in the metadata
    And "TrashExpense" scale should be between the bounds in the metadata
    And "TrashExpense" precision should be less than or equal to the RESO maxlength of 14
    And "TrashExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @VacancyAllowanceRate @DD1.5_VacancyAllowanceRate @DD1.6_VacancyAllowanceRate
  Scenario: VacancyAllowanceRate
    Given "VacancyAllowanceRate" exists in the metadata
    And "VacancyAllowanceRate" decimal values are not null
    Then "VacancyAllowanceRate" should be Decimal data type
    And "VacancyAllowanceRate" precision should be between the bounds in the metadata
    And "VacancyAllowanceRate" scale should be between the bounds in the metadata
    And "VacancyAllowanceRate" precision should be less than or equal to the RESO maxlength of 5
    And "VacancyAllowanceRate" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @WaterSewerExpense @DD1.5_WaterSewerExpense @DD1.6_WaterSewerExpense
  Scenario: WaterSewerExpense
    Given "WaterSewerExpense" exists in the metadata
    And "WaterSewerExpense" decimal values are not null
    Then "WaterSewerExpense" should be Decimal data type
    And "WaterSewerExpense" precision should be between the bounds in the metadata
    And "WaterSewerExpense" scale should be between the bounds in the metadata
    And "WaterSewerExpense" precision should be less than or equal to the RESO maxlength of 14
    And "WaterSewerExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @WoodedArea @DD1.5_WoodedArea @DD1.6_WoodedArea
  Scenario: WoodedArea
    Given "WoodedArea" exists in the metadata
    And "WoodedArea" decimal values are not null
    Then "WoodedArea" should be Decimal data type
    And "WoodedArea" precision should be between the bounds in the metadata
    And "WoodedArea" scale should be between the bounds in the metadata
    And "WoodedArea" precision should be less than or equal to the RESO maxlength of 14
    And "WoodedArea" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @WorkmansCompensationExpense @DD1.5_WorkmansCompensationExpense @DD1.6_WorkmansCompensationExpense
  Scenario: WorkmansCompensationExpense
    Given "WorkmansCompensationExpense" exists in the metadata
    And "WorkmansCompensationExpense" decimal values are not null
    Then "WorkmansCompensationExpense" should be Decimal data type
    And "WorkmansCompensationExpense" precision should be between the bounds in the metadata
    And "WorkmansCompensationExpense" scale should be between the bounds in the metadata
    And "WorkmansCompensationExpense" precision should be less than or equal to the RESO maxlength of 14
    And "WorkmansCompensationExpense" scale should be less than or equal to the RESO scale of 2

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @IrrigationWaterRightsAcres @DD1.5_IrrigationWaterRightsAcres @DD1.6_IrrigationWaterRightsAcres
  Scenario: IrrigationWaterRightsAcres
    Given "IrrigationWaterRightsAcres" exists in the metadata
    And "IrrigationWaterRightsAcres" decimal values are not null
    Then "IrrigationWaterRightsAcres" should be Decimal data type
    And "IrrigationWaterRightsAcres" precision should be between the bounds in the metadata
    And "IrrigationWaterRightsAcres" scale should be between the bounds in the metadata
    And "IrrigationWaterRightsAcres" precision should be less than or equal to the RESO maxlength of 16
    And "IrrigationWaterRightsAcres" scale should be less than or equal to the RESO scale of 4

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @LotSizeAcres @IDX_Payload @DD1.5_LotSizeAcres @DD1.6_LotSizeAcres
  Scenario: LotSizeAcres
    Given "LotSizeAcres" exists in the metadata
    And "LotSizeAcres" decimal values are not null
    Then "LotSizeAcres" should be Decimal data type
    And "LotSizeAcres" precision should be between the bounds in the metadata
    And "LotSizeAcres" scale should be between the bounds in the metadata
    And "LotSizeAcres" precision should be less than or equal to the RESO maxlength of 16
    And "LotSizeAcres" scale should be less than or equal to the RESO scale of 4

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @LotSizeArea @IDX_Payload @DD1.5_LotSizeArea @DD1.6_LotSizeArea
  Scenario: LotSizeArea
    Given "LotSizeArea" exists in the metadata
    And "LotSizeArea" decimal values are not null
    Then "LotSizeArea" should be Decimal data type
    And "LotSizeArea" precision should be between the bounds in the metadata
    And "LotSizeArea" scale should be between the bounds in the metadata
    And "LotSizeArea" precision should be less than or equal to the RESO maxlength of 16
    And "LotSizeArea" scale should be less than or equal to the RESO scale of 4

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @Latitude @DD1.5_Latitude @DD1.6_Latitude @testing
  Scenario: Latitude
    Given "Latitude" exists in the metadata
    And "Latitude" decimal values are not null
    Then "Latitude" should be Decimal data type
    And "Latitude" precision should be between the bounds in the metadata
    And "Latitude" scale should be between the bounds in the metadata
    And "Latitude" precision should be less than or equal to the RESO maxlength of 12
    And "Latitude" scale should be less than or equal to the RESO scale of 8

  @DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @Longitude @DD1.5_Longitude @DD1.6_Longitude
  Scenario: Longitude
    Given "Longitude" exists in the metadata
    And "Longitude" decimal values are not null
    Then "Longitude" should be Decimal data type
    And "Longitude" precision should be between the bounds in the metadata
    And "Longitude" scale should be between the bounds in the metadata
    And "Longitude" precision should be less than or equal to the RESO maxlength of 12
    And "Longitude" scale should be less than or equal to the RESO scale of 8
 





 
  
  
#@DD1.5_Decimal @DD1.6_Decimal @DD1.5 @DD1.6 @xxx @DD1.5_xxx @DD1.6_xxx
#Scenario: xxx
#  Given "xxx" exists in the metadata
#  And "xxx" decimal values are not null
#  Then "xxx" should be Decimal data type
#  And "xxx" precision should be between the bounds in the metadata
#  And "xxx" scale should be between the bounds in the metadata
#  And "xxx" precision should be less than or equal to the RESO maxlength of 14
#  And "xxx" scale should be less than or equal to the RESO scale of 2
