# This file was autogenerated on: 20210105220129831
Feature: SocialMedia

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

  @SocialMedia
  Scenario: ClassName
    When "ClassName" exists in the "SocialMedia" metadata
    Then "ClassName" MUST be "Single Enumeration" data type

  @SocialMedia
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "SocialMedia" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type
    And the following synonyms for "ModificationTimestamp" MUST NOT exist in the metadata
      | ModificationDateTime |
      | DateTimeModified |
      | ModDate |
      | DateMod |
      | UpdateDate |
      | UpdateTimestamp |

  @SocialMedia
  Scenario: ResourceName
    When "ResourceName" exists in the "SocialMedia" metadata
    Then "ResourceName" MUST be "Single Enumeration" data type

  @SocialMedia
  Scenario: ResourceRecordID
    When "ResourceRecordID" exists in the "SocialMedia" metadata
    Then "ResourceRecordID" MUST be "String" data type
    And the following synonyms for "ResourceRecordID" MUST NOT exist in the metadata
      | MLNumber |
      | MLSNumber |
      | ListingNumber |
      | AgentID |
      | OfficeID |
      | ContactID |
    And "ResourceRecordID" length SHOULD be equal to the RESO Suggested Max Length of 255

  @SocialMedia
  Scenario: ResourceRecordKey
    When "ResourceRecordKey" exists in the "SocialMedia" metadata
    Then "ResourceRecordKey" MUST be "String" data type
    And the following synonyms for "ResourceRecordKey" MUST NOT exist in the metadata
      | SystemUniqueID |
      | ImmediateSourceID |
    And "ResourceRecordKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @SocialMedia
  Scenario: ResourceRecordKeyNumeric
    When "ResourceRecordKeyNumeric" exists in the "SocialMedia" metadata
    Then "ResourceRecordKeyNumeric" MUST be "Integer" data type
    And the following synonyms for "ResourceRecordKeyNumeric" MUST NOT exist in the metadata
      | SystemUniqueID |
      | ImmediateSourceID |

  @SocialMedia
  Scenario: SocialMediaKey
    When "SocialMediaKey" exists in the "SocialMedia" metadata
    Then "SocialMediaKey" MUST be "String" data type
    And the following synonyms for "SocialMediaKey" MUST NOT exist in the metadata
      | SystemUniqueID |
      | ImmediateSourceID |
    And "SocialMediaKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @SocialMedia
  Scenario: SocialMediaKeyNumeric
    When "SocialMediaKeyNumeric" exists in the "SocialMedia" metadata
    Then "SocialMediaKeyNumeric" MUST be "Integer" data type

  @SocialMedia
  Scenario: SocialMediaType
    When "SocialMediaType" exists in the "SocialMedia" metadata
    Then "SocialMediaType" MUST be "Single Enumeration" data type
    And the following synonyms for "SocialMediaType" MUST NOT exist in the metadata
      | MimeType |

  @SocialMedia
  Scenario: SocialMediaUrlOrId
    When "SocialMediaUrlOrId" exists in the "SocialMedia" metadata
    Then "SocialMediaUrlOrId" MUST be "String" data type
    And "SocialMediaUrlOrId" length SHOULD be equal to the RESO Suggested Max Length of 8000
