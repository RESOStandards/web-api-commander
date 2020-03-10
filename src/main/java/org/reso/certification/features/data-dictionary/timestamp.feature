Feature: Timestamp Testing

  Background:

  @DD1.5_Timestamp @DD1.6_Timestamp @DD1.5 @DD1.6 @DocumentsChangeTimestamp @DD1.5_DocumentsChangeTimestamp @DD1.6_DocumentsChangeTimestamp
  Scenario: DocumentsChangeTimestamp
    Given "DocumentsChangeTimestamp" exists in the metadata
    And "DocumentsChangeTimestamp" timestamp values are not null
    Then "DocumentsChangeTimestamp" should be Timestamp data type
    And "DocumentsChangeTimestamp" length should be between the bounds in the metadata
    And "DocumentsChangeTimestamp" length should be less than or equal to the RESO maxlength of 27

  @DD1.5_Timestamp @DD1.6_Timestamp @DD1.5 @DD1.6 @MajorChangeTimestamp @DD1.5_MajorChangeTimestamp @DD1.6_MajorChangeTimestamp
  Scenario: MajorChangeTimestamp
    Given "MajorChangeTimestamp" exists in the metadata
    And "MajorChangeTimestamp" timestamp values are not null
    Then "MajorChangeTimestamp" should be Timestamp data type
    And "MajorChangeTimestamp" length should be between the bounds in the metadata
    And "MajorChangeTimestamp" length should be less than or equal to the RESO maxlength of 27

  @DD1.5_Timestamp @DD1.6_Timestamp @DD1.5 @DD1.6 @ModificationTimestamp @IDX_Payload @testing @DD1.5_ModificationTimestamp @DD1.6_ModificationTimestamp
  Scenario: ModificationTimestamp
    Given "ModificationTimestamp" exists in the metadata
    And "ModificationTimestamp" timestamp values are not null
    Then "ModificationTimestamp" should be Timestamp data type
    And "ModificationTimestamp" length should be between the bounds in the metadata
    And "ModificationTimestamp" length should be less than or equal to the RESO maxlength of 27

  @DD1.5_Timestamp @DD1.6_Timestamp @DD1.5 @DD1.6 @OffMarketTimestamp @DD1.5_OffMarketTimestamp @DD1.6_OffMarketTimestamp
  Scenario: OffMarketTimestamp
    Given "OffMarketTimestamp" exists in the metadata
    And "OffMarketTimestamp" timestamp values are not null
    Then "OffMarketTimestamp" should be Timestamp data type
    And "OffMarketTimestamp" length should be between the bounds in the metadata
    And "OffMarketTimestamp" length should be less than or equal to the RESO maxlength of 27

  @DD1.5_Timestamp @DD1.6_Timestamp @DD1.5 @DD1.6 @OnMarketTimestamp @DD1.5_OnMarketTimestamp @DD1.6_OnMarketTimestamp
  Scenario: OnMarketTimestamp
    Given "OnMarketTimestamp" exists in the metadata
    And "OnMarketTimestamp" timestamp values are not null
    Then "OnMarketTimestamp" should be Timestamp data type
    And "OnMarketTimestamp" length should be between the bounds in the metadata
    And "OnMarketTimestamp" length should be less than or equal to the RESO maxlength of 27

  @DD1.5_Timestamp @DD1.6_Timestamp @DD1.5 @DD1.6 @OriginalEntryTimestamp @DD1.5_OriginalEntryTimestamp @DD1.6_OriginalEntryTimestamp
  Scenario: OriginalEntryTimestamp
    Given "OriginalEntryTimestamp" exists in the metadata
    And "OriginalEntryTimestamp" timestamp values are not null
    Then "OriginalEntryTimestamp" should be Timestamp data type
    And "OriginalEntryTimestamp" length should be between the bounds in the metadata
    And "OriginalEntryTimestamp" length should be less than or equal to the RESO maxlength of 27

  @DD1.5_Timestamp @DD1.6_Timestamp @DD1.5 @DD1.6 @PendingTimestamp @DD1.5_PendingTimestamp @DD1.6_PendingTimestamp
  Scenario: PendingTimestamp
    Given "PendingTimestamp" exists in the metadata
    And "PendingTimestamp" timestamp values are not null
    Then "PendingTimestamp" should be Timestamp data type
    And "PendingTimestamp" length should be between the bounds in the metadata
    And "PendingTimestamp" length should be less than or equal to the RESO maxlength of 27

  @DD1.5_Timestamp @DD1.6_Timestamp @DD1.5 @DD1.6 @PhotosChangeTimestamp @IDX_Payload @DD1.5_PhotosChangeTimestamp @DD1.6_PhotosChangeTimestamp
  Scenario: PhotosChangeTimestamp
    Given "PhotosChangeTimestamp" exists in the metadata
    And "PhotosChangeTimestamp" timestamp values are not null
    Then "PhotosChangeTimestamp" should be Timestamp data type
    And "PhotosChangeTimestamp" length should be between the bounds in the metadata
    And "PhotosChangeTimestamp" length should be less than or equal to the RESO maxlength of 27

  @DD1.5_Timestamp @DD1.6_Timestamp @DD1.5 @DD1.6 @PriceChangeTimestamp @IDX_Payload @DD1.5_PriceChangeTimestamp @DD1.6_PriceChangeTimestamp
  Scenario: PriceChangeTimestamp
    Given "PriceChangeTimestamp" exists in the metadata
    And "PriceChangeTimestamp" timestamp values are not null
    Then "PriceChangeTimestamp" should be Timestamp data type
    And "PriceChangeTimestamp" length should be between the bounds in the metadata
    And "PriceChangeTimestamp" length should be less than or equal to the RESO maxlength of 27

  @DD1.5_Timestamp @DD1.6_Timestamp @DD1.5 @DD1.6 @StatusChangeTimestamp @IDX_Payload @DD1.5_StatusChangeTimestamp @DD1.6_StatusChangeTimestamp
  Scenario: StatusChangeTimestamp
    Given "StatusChangeTimestamp" exists in the metadata
    And "StatusChangeTimestamp" timestamp values are not null
    Then "StatusChangeTimestamp" should be Timestamp data type
    And "StatusChangeTimestamp" length should be between the bounds in the metadata
    And "StatusChangeTimestamp" length should be less than or equal to the RESO maxlength of 27

  @DD1.5_Timestamp @DD1.6_Timestamp @DD1.5 @DD1.6 @VideosChangeTimestamp @DD1.5_VideosChangeTimestamp @DD1.6_VideosChangeTimestamp
  Scenario: VideosChangeTimestamp
    Given "VideosChangeTimestamp" exists in the metadata
    And "VideosChangeTimestamp" timestamp values are not null
    Then "VideosChangeTimestamp" should be Timestamp data type
    And "VideosChangeTimestamp" length should be between the bounds in the metadata
    And "VideosChangeTimestamp" length should be less than or equal to the RESO maxlength of 27
  
#@DD1.5_Timestamp @DD1.6_Timestamp @DD1.5 @DD1.6 @xxx @DD1.5_xxx @DD1.6_xxx
#Scenario: xxx
#  Given "xxx" exists in the metadata
#  And "xxx" timestamp values are not null
#  Then "xxx" should be Timestamp data type
#  And "xxx" length should be between the bounds in the metadata
#  And "xxx" length should be less than or equal to the RESO maxlength of 27
