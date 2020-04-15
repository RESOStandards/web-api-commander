Feature: Prospecting

  Scenario: ActiveYN
    Given "ActiveYN" exists in the metadata
    Then "ActiveYN" should be "Boolean" data type

  Scenario: BccEmailList
    Given "BccEmailList" exists in the metadata
    Then "BccEmailList" should be "String" data type
    And "BccEmailList" length should be less than or equal to the RESO Suggested Max Length of 1024

  Scenario: BccMeYN
    Given "BccMeYN" exists in the metadata
    Then "BccMeYN" should be "Boolean" data type

  Scenario: CcEmailList
    Given "CcEmailList" exists in the metadata
    Then "CcEmailList" should be "String" data type
    And "CcEmailList" length should be less than or equal to the RESO Suggested Max Length of 1024

  Scenario: ClientActivatedYN
    Given "ClientActivatedYN" exists in the metadata
    Then "ClientActivatedYN" should be "Boolean" data type

  Scenario: ConciergeNotificationsYN
    Given "ConciergeNotificationsYN" exists in the metadata
    Then "ConciergeNotificationsYN" should be "Boolean" data type

  Scenario: ConciergeYN
    Given "ConciergeYN" exists in the metadata
    Then "ConciergeYN" should be "Boolean" data type

  Scenario: ContactKey
    Given "ContactKey" exists in the metadata
    Then "ContactKey" should be "String" data type
    And "ContactKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ContactKeyNumeric
    Given "ContactKeyNumeric" exists in the metadata
    Then "ContactKeyNumeric" should be "Integer" data type

  Scenario: DailySchedule
    Given "DailySchedule" exists in the metadata
    And "DailySchedule" enum values can be compiled
    Then "DailySchedule" should be "String Array" data type
    And "DailySchedule" should only contain enum values found in the metadata
    And "DailySchedule" length should be less than or equal to the RESO Suggested Max Length of 1024

  Scenario: DisplayTemplateID
    Given "DisplayTemplateID" exists in the metadata
    Then "DisplayTemplateID" should be "String" data type
    And "DisplayTemplateID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: Language
    Given "Language" exists in the metadata
    And "Language" enum values can be compiled
    Then "Language" should be "String" data type
    And "Language" should only contain enum values found in the metadata
    And "Language" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: LastNewChangedTimestamp
    Given "LastNewChangedTimestamp" exists in the metadata
    Then "LastNewChangedTimestamp" should be "Timestamp" data type
    And "LastNewChangedTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: LastViewedTimestamp
    Given "LastViewedTimestamp" exists in the metadata
    Then "LastViewedTimestamp" should be "Timestamp" data type
    And "LastViewedTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: MessageNew
    Given "MessageNew" exists in the metadata
    Then "MessageNew" should be "String" data type
    And "MessageNew" length should be less than or equal to the RESO Suggested Max Length of 4000

  Scenario: MessageRevise
    Given "MessageRevise" exists in the metadata
    Then "MessageRevise" should be "String" data type
    And "MessageRevise" length should be less than or equal to the RESO Suggested Max Length of 4000

  Scenario: MessageUpdate
    Given "MessageUpdate" exists in the metadata
    Then "MessageUpdate" should be "String" data type
    And "MessageUpdate" length should be less than or equal to the RESO Suggested Max Length of 4000

  Scenario: ModificationTimestamp
    Given "ModificationTimestamp" exists in the metadata
    Then "ModificationTimestamp" should be "Timestamp" data type
    And "ModificationTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: NextSendTimestamp
    Given "NextSendTimestamp" exists in the metadata
    Then "NextSendTimestamp" should be "Timestamp" data type
    And "NextSendTimestamp" length should be less than or equal to the RESO Suggested Max Length of 27

  Scenario: OwnerMemberID
    Given "OwnerMemberID" exists in the metadata
    Then "OwnerMemberID" should be "String" data type
    And "OwnerMemberID" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: OwnerMemberKey
    Given "OwnerMemberKey" exists in the metadata
    Then "OwnerMemberKey" should be "String" data type
    And "OwnerMemberKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: OwnerMemberKeyNumeric
    Given "OwnerMemberKeyNumeric" exists in the metadata
    Then "OwnerMemberKeyNumeric" should be "Integer" data type

  Scenario: ProspectingKey
    Given "ProspectingKey" exists in the metadata
    Then "ProspectingKey" should be "String" data type
    And "ProspectingKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ProspectingKeyNumeric
    Given "ProspectingKeyNumeric" exists in the metadata
    Then "ProspectingKeyNumeric" should be "Integer" data type

  Scenario: ReasonActiveOrDisabled
    Given "ReasonActiveOrDisabled" exists in the metadata
    And "ReasonActiveOrDisabled" enum values can be compiled
    Then "ReasonActiveOrDisabled" should be "String" data type
    And "ReasonActiveOrDisabled" should only contain enum values found in the metadata
    And "ReasonActiveOrDisabled" length should be less than or equal to the RESO Suggested Max Length of 50

  Scenario: SavedSearchKey
    Given "SavedSearchKey" exists in the metadata
    Then "SavedSearchKey" should be "String" data type
    And "SavedSearchKey" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: SavedSearchKeyNumeric
    Given "SavedSearchKeyNumeric" exists in the metadata
    Then "SavedSearchKeyNumeric" should be "Integer" data type

  Scenario: ScheduleType
    Given "ScheduleType" exists in the metadata
    And "ScheduleType" enum values can be compiled
    Then "ScheduleType" should be "String" data type
    And "ScheduleType" should only contain enum values found in the metadata
    And "ScheduleType" length should be less than or equal to the RESO Suggested Max Length of 25

  Scenario: Subject
    Given "Subject" exists in the metadata
    Then "Subject" should be "String" data type
    And "Subject" length should be less than or equal to the RESO Suggested Max Length of 255

  Scenario: ToEmailList
    Given "ToEmailList" exists in the metadata
    Then "ToEmailList" should be "String" data type
    And "ToEmailList" length should be less than or equal to the RESO Suggested Max Length of 1024
