Feature: Date Testing

  Background:

  @DD1.5_Date @DD1.6_Date @DD1.5 @DD1.6 @AvailabilityDate @DD1.5_AvailabilityDate @DD1.6_AvailabilityDate
  Scenario: AvailabilityDate
    Given "AvailabilityDate" exists in the metadata
    And "AvailabilityDate" date values are not null
    Then "AvailabilityDate" should be Date data type
    And "AvailabilityDate" length should be between the bounds in the metadata
    And "AvailabilityDate" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_Date @DD1.6_Date @DD1.5 @DD1.6 @CancelationDate @DD1.5_CancelationDate @DD1.6_CancelationDate
  Scenario: CancelationDate
    Given "CancelationDate" exists in the metadata
    And "CancelationDate" date values are not null
    Then "CancelationDate" should be Date data type
    And "CancelationDate" length should be between the bounds in the metadata
    And "CancelationDate" length should be less than or equal to the RESO maxlength of 10

  @DD1.5_Date @DD1.6_Date @DD1.5 @DD1.6 @CloseDate @IDX_Payload @DD1.5_CloseDate @DD1.6_CloseDate @testing
  Scenario: CloseDate
    Given "CloseDate" exists in the metadata
    And "CloseDate" date values are not null
    Then "CloseDate" should be Date data type
    And "CloseDate" length should be between the bounds in the metadata
    And "CloseDate" length should be less than or equal to the RESO maxlength of 10


  @DD1.5_Date @DD1.6_Date @DD1.5 @DD1.6 @ContingentDate @DD1.5_ContingentDate @DD1.6_ContingentDate
  Scenario: ContingentDate
    Given "ContingentDate" exists in the metadata
    And "ContingentDate" date values are not null
    Then "ContingentDate" should be Date data type
    And "ContingentDate" length should be between the bounds in the metadata
    And "ContingentDate" length should be less than or equal to the RESO maxlength of 10


  @DD1.5_Date @DD1.6_Date @DD1.5 @DD1.6 @ContractStatusChangeDate @DD1.5_ContractStatusChangeDate @DD1.6_ContractStatusChangeDate
  Scenario: ContractStatusChangeDate
    Given "ContractStatusChangeDate" exists in the metadata
    And "ContractStatusChangeDate" date values are not null
    Then "ContractStatusChangeDate" should be Date data type
    And "ContractStatusChangeDate" length should be between the bounds in the metadata
    And "ContractStatusChangeDate" length should be less than or equal to the RESO maxlength of 10


  @DD1.5_Date @DD1.6_Date @DD1.5 @DD1.6 @ExpirationDate @DD1.5_ExpirationDate @DD1.6_ExpirationDate
  Scenario: ExpirationDate
    Given "ExpirationDate" exists in the metadata
    And "ExpirationDate" date values are not null
    Then "ExpirationDate" should be Date data type
    And "ExpirationDate" length should be between the bounds in the metadata
    And "ExpirationDate" length should be less than or equal to the RESO maxlength of 10


  @DD1.5_Date @DD1.6_Date @DD1.5 @DD1.6 @LandLeaseExpirationDate @DD1.5_LandLeaseExpirationDate @DD1.6_LandLeaseExpirationDate
  Scenario: LandLeaseExpirationDate
    Given "LandLeaseExpirationDate" exists in the metadata
    And "LandLeaseExpirationDate" date values are not null
    Then "LandLeaseExpirationDate" should be Date data type
    And "LandLeaseExpirationDate" length should be between the bounds in the metadata
    And "LandLeaseExpirationDate" length should be less than or equal to the RESO maxlength of 10


  @DD1.5_Date @DD1.6_Date @DD1.5 @DD1.6 @LeaseExpiration @DD1.5_LeaseExpiration @DD1.6_LeaseExpiration
  Scenario: LeaseExpiration
    Given "LeaseExpiration" exists in the metadata
    And "LeaseExpiration" date values are not null
    Then "LeaseExpiration" should be Date data type
    And "LeaseExpiration" length should be between the bounds in the metadata
    And "LeaseExpiration" length should be less than or equal to the RESO maxlength of 10


  @DD1.5_Date @DD1.6_Date @DD1.5 @DD1.6 @ListingContractDate @IDX_Payload @DD1.5_ListingContractDate @DD1.6_ListingContractDate
  Scenario: ListingContractDate
    Given "ListingContractDate" exists in the metadata
    And "ListingContractDate" date values are not null
    Then "ListingContractDate" should be Date data type
    And "ListingContractDate" length should be between the bounds in the metadata
    And "ListingContractDate" length should be less than or equal to the RESO maxlength of 10


  @DD1.5_Date @DD1.6_Date @DD1.5 @DD1.6 @OffMarketDate @DD1.5_OffMarketDate @DD1.6_OffMarketDate
  Scenario: OffMarketDate
    Given "OffMarketDate" exists in the metadata
    And "OffMarketDate" date values are not null
    Then "OffMarketDate" should be Date data type
    And "OffMarketDate" length should be between the bounds in the metadata
    And "OffMarketDate" length should be less than or equal to the RESO maxlength of 10


  @DD1.5_Date @DD1.6_Date @DD1.5 @DD1.6 @OnMarketDate @IDX_Payload @DD1.5_OnMarketDate @DD1.6_OnMarketDate
  Scenario: OnMarketDate
    Given "OnMarketDate" exists in the metadata
    And "OnMarketDate" date values are not null
    Then "OnMarketDate" should be Date data type
    And "OnMarketDate" length should be between the bounds in the metadata
    And "OnMarketDate" length should be less than or equal to the RESO maxlength of 10


  @DD1.5_Date @DD1.6_Date @DD1.5 @DD1.6 @PurchaseContractDate @DD1.5_PurchaseContractDate @DD1.6_PurchaseContractDate
  Scenario: PurchaseContractDate
    Given "PurchaseContractDate" exists in the metadata
    And "PurchaseContractDate" date values are not null
    Then "PurchaseContractDate" should be Date data type
    And "PurchaseContractDate" length should be between the bounds in the metadata
    And "PurchaseContractDate" length should be less than or equal to the RESO maxlength of 10


  @DD1.5_Date @DD1.6_Date @DD1.5 @DD1.6 @WithdrawnDate @DD1.5_WithdrawnDate @DD1.6_WithdrawnDate
  Scenario: WithdrawnDate
    Given "WithdrawnDate" exists in the metadata
    And "WithdrawnDate" date values are not null
    Then "WithdrawnDate" should be Date data type
    And "WithdrawnDate" length should be between the bounds in the metadata
    And "WithdrawnDate" length should be less than or equal to the RESO maxlength of 10
  
  
  
#@DD1.5_Date @DD1.6_Date @DD1.5 @DD1.6 @xxx @DD1.5_xxx @DD1.6_xxx
#Scenario: xxx
#  Given "xxx" exists in the metadata
#  And "xxx" date values are not null
#  Then "xxx" should be Date data type
#  And "xxx" length should be between the bounds in the metadata
#  And "xxx" length should be less than or equal to the RESO maxlength of 10
