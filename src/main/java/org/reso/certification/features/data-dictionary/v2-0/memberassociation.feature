# This file was autogenerated on: 20230403153708907
Feature: MemberAssociation

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

  @MemberAssociation @dd-2.0
  Scenario: AssociationKey
    When "AssociationKey" exists in the "MemberAssociation" metadata
    Then "AssociationKey" MUST be "String" data type
    And "AssociationKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @MemberAssociation @dd-2.0
  Scenario: AssociationMlsId
    When "AssociationMlsId" exists in the "MemberAssociation" metadata
    Then "AssociationMlsId" MUST be "String" data type
    And "AssociationMlsId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @MemberAssociation @dd-2.0
  Scenario: AssociationNationalAssociationId
    When "AssociationNationalAssociationId" exists in the "MemberAssociation" metadata
    Then "AssociationNationalAssociationId" MUST be "String" data type
    And "AssociationNationalAssociationId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @MemberAssociation @dd-2.0
  Scenario: AssociationStaffYN
    When "AssociationStaffYN" exists in the "MemberAssociation" metadata
    Then "AssociationStaffYN" MUST be "Boolean" data type

  @MemberAssociation @dd-2.0
  Scenario: MemberAssociationBillStatus
    When "MemberAssociationBillStatus" exists in the "MemberAssociation" metadata
    Then "MemberAssociationBillStatus" MUST be "Single Enumeration" data type

  @MemberAssociation @dd-2.0
  Scenario: MemberAssociationBillStatusDescription
    When "MemberAssociationBillStatusDescription" exists in the "MemberAssociation" metadata
    Then "MemberAssociationBillStatusDescription" MUST be "String" data type
    And "MemberAssociationBillStatusDescription" length SHOULD be equal to the RESO Suggested Max Length of 100

  @MemberAssociation @dd-2.0
  Scenario: MemberAssociationDuesPaidDate
    When "MemberAssociationDuesPaidDate" exists in the "MemberAssociation" metadata
    Then "MemberAssociationDuesPaidDate" MUST be "Date" data type

  @MemberAssociation @dd-2.0
  Scenario: MemberAssociationJoinDate
    When "MemberAssociationJoinDate" exists in the "MemberAssociation" metadata
    Then "MemberAssociationJoinDate" MUST be "Date" data type

  @MemberAssociation @dd-2.0
  Scenario: MemberAssociationOrientationDate
    When "MemberAssociationOrientationDate" exists in the "MemberAssociation" metadata
    Then "MemberAssociationOrientationDate" MUST be "Date" data type

  @MemberAssociation @dd-2.0
  Scenario: MemberAssociationPrimaryIndicator
    When "MemberAssociationPrimaryIndicator" exists in the "MemberAssociation" metadata
    Then "MemberAssociationPrimaryIndicator" MUST be "String" data type
    And "MemberAssociationPrimaryIndicator" length SHOULD be equal to the RESO Suggested Max Length of 20

  @MemberAssociation @dd-2.0
  Scenario: MemberAssociationStatus
    When "MemberAssociationStatus" exists in the "MemberAssociation" metadata
    Then "MemberAssociationStatus" MUST be "Single Enumeration" data type

  @MemberAssociation @dd-2.0
  Scenario: MemberAssociationStatusDate
    When "MemberAssociationStatusDate" exists in the "MemberAssociation" metadata
    Then "MemberAssociationStatusDate" MUST be "Date" data type

  @MemberAssociation @dd-2.0
  Scenario: MemberKey
    When "MemberKey" exists in the "MemberAssociation" metadata
    Then "MemberKey" MUST be "String" data type
    And "MemberKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @MemberAssociation @dd-2.0
  Scenario: MemberLocalDuesWaivedYN
    When "MemberLocalDuesWaivedYN" exists in the "MemberAssociation" metadata
    Then "MemberLocalDuesWaivedYN" MUST be "Boolean" data type

  @MemberAssociation @dd-2.0
  Scenario: MemberMlsId
    When "MemberMlsId" exists in the "MemberAssociation" metadata
    Then "MemberMlsId" MUST be "String" data type
    And "MemberMlsId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @MemberAssociation @dd-2.0
  Scenario: MemberNationalDuesWaivedYN
    When "MemberNationalDuesWaivedYN" exists in the "MemberAssociation" metadata
    Then "MemberNationalDuesWaivedYN" MUST be "Boolean" data type

  @MemberAssociation @dd-2.0
  Scenario: MemberStatelDuesWaivedYN
    When "MemberStatelDuesWaivedYN" exists in the "MemberAssociation" metadata
    Then "MemberStatelDuesWaivedYN" MUST be "Boolean" data type

  @MemberAssociation @dd-2.0
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "MemberAssociation" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type

  @MemberAssociation @dd-2.0
  Scenario: OriginalEntryTimestamp
    When "OriginalEntryTimestamp" exists in the "MemberAssociation" metadata
    Then "OriginalEntryTimestamp" MUST be "Timestamp" data type

  @MemberAssociation @dd-2.0
  Scenario: OriginatingSystemId
    When "OriginatingSystemId" exists in the "MemberAssociation" metadata
    Then "OriginatingSystemId" MUST be "String" data type
    And "OriginatingSystemId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @MemberAssociation @dd-2.0
  Scenario: OriginatingSystemMemberKey
    When "OriginatingSystemMemberKey" exists in the "MemberAssociation" metadata
    Then "OriginatingSystemMemberKey" MUST be "String" data type
    And "OriginatingSystemMemberKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @MemberAssociation @dd-2.0
  Scenario: OriginatingSystemName
    When "OriginatingSystemName" exists in the "MemberAssociation" metadata
    Then "OriginatingSystemName" MUST be "String" data type
    And "OriginatingSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255

  @MemberAssociation @dd-2.0
  Scenario: SourceSystemId
    When "SourceSystemId" exists in the "MemberAssociation" metadata
    Then "SourceSystemId" MUST be "String" data type
    And "SourceSystemId" length SHOULD be equal to the RESO Suggested Max Length of 25

  @MemberAssociation @dd-2.0
  Scenario: SourceSystemMemberKey
    When "SourceSystemMemberKey" exists in the "MemberAssociation" metadata
    Then "SourceSystemMemberKey" MUST be "String" data type
    And "SourceSystemMemberKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @MemberAssociation @dd-2.0
  Scenario: SourceSystemName
    When "SourceSystemName" exists in the "MemberAssociation" metadata
    Then "SourceSystemName" MUST be "String" data type
    And "SourceSystemName" length SHOULD be equal to the RESO Suggested Max Length of 255
