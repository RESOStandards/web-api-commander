# This file was autogenerated on: 20201012221036706
Feature: PropertyGreenVerification

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

  @PropertyGreenVerification @IDX
  Scenario: GreenBuildingVerificationKey
    When "GreenBuildingVerificationKey" exists in the "PropertyGreenVerification" metadata
    Then "GreenBuildingVerificationKey" MUST be "String" data type
    And "GreenBuildingVerificationKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @PropertyGreenVerification @IDX
  Scenario: GreenBuildingVerificationKeyNumeric
    When "GreenBuildingVerificationKeyNumeric" exists in the "PropertyGreenVerification" metadata
    Then "GreenBuildingVerificationKeyNumeric" MUST be "Integer" data type

  @PropertyGreenVerification @IDX
  Scenario: GreenBuildingVerificationType
    When "GreenBuildingVerificationType" exists in the "PropertyGreenVerification" metadata
    Then "GreenBuildingVerificationType" MUST be "Single Enumeration" data type
    And "GreenBuildingVerificationType" MAY contain any of the following standard lookups
      | lookupValue | lookupDisplayName |
      | CertifiedPassiveHouse | Certified Passive House |
      | EnergyStarCertifiedHomes | ENERGY STAR Certified Homes |
      | Enerphit | EnerPHit |
      | HersIndexScore | HERS Index Score |
      | HomeEnergyScore | Home Energy Score |
      | HomeEnergyUpgradeCertificateOfEnergyEfficiencyImprovements | Home Energy Upgrade Certificate of Energy Efficiency Improvements |
      | HomeEnergyUpgradeCertificateOfEnergyEfficiencyPerformance | Home Energy Upgrade Certificate of Energy Efficiency Performance |
      | HomePerformanceWithEnergyStar | Home Performance with ENERGY STAR |
      | IndoorAirplus | Indoor airPLUS |
      | LeedForHomes | LEED For Homes |
      | LivingBuildingChallenge | Living Building Challenge |
      | NgbsNewConstruction | NGBS New Construction |
      | NgbsSmallProjectsRemodel | NGBS Small Projects Remodel |
      | NgbsWholeHomeRemodel | NGBS Whole-Home Remodel |
      | PhiusPlus | PHIUS+ |
      | Watersense | WaterSense |
      | ZeroEnergyReadyHome | Zero Energy Ready Home |

  @PropertyGreenVerification @IDX
  Scenario: GreenVerificationBody
    When "GreenVerificationBody" exists in the "PropertyGreenVerification" metadata
    Then "GreenVerificationBody" MUST be "String" data type
    And "GreenVerificationBody" length SHOULD be equal to the RESO Suggested Max Length of 50

  @PropertyGreenVerification @IDX
  Scenario: GreenVerificationMetric
    When "GreenVerificationMetric" exists in the "PropertyGreenVerification" metadata
    Then "GreenVerificationMetric" MUST be "Integer" data type

  @PropertyGreenVerification @IDX
  Scenario: GreenVerificationRating
    When "GreenVerificationRating" exists in the "PropertyGreenVerification" metadata
    Then "GreenVerificationRating" MUST be "String" data type
    And "GreenVerificationRating" length SHOULD be equal to the RESO Suggested Max Length of 50

  @PropertyGreenVerification @IDX
  Scenario: GreenVerificationSource
    When "GreenVerificationSource" exists in the "PropertyGreenVerification" metadata
    Then "GreenVerificationSource" MUST be "Single Enumeration" data type
    And "GreenVerificationSource" MAY contain any of the following standard lookups
      | lookupValue | lookupDisplayName |
      | Administrator | Administrator |
      | Assessor | Assessor |
      | Builder | Builder |
      | ContractorOrInstaller | Contractor or Installer |
      | Other | Other |
      | Owner | Owner |
      | ProgramSponsor | Program Sponsor |
      | ProgramVerifier | Program Verifier |
      | PublicRecords | Public Records |
      | SeeRemarks | See Remarks |

  @PropertyGreenVerification @IDX
  Scenario: GreenVerificationStatus
    When "GreenVerificationStatus" exists in the "PropertyGreenVerification" metadata
    Then "GreenVerificationStatus" MUST be "Single Enumeration" data type
    And "GreenVerificationStatus" MUST contain at least one of the following standard lookups
      | lookupValue | lookupDisplayName |
      | Complete | Complete |
      | InProcess | In Process |
    And "GreenVerificationStatus" MUST contain only standard enumerations

  @PropertyGreenVerification @IDX
  Scenario: GreenVerificationURL
    When "GreenVerificationURL" exists in the "PropertyGreenVerification" metadata
    Then "GreenVerificationURL" MUST be "String" data type
    And "GreenVerificationURL" length SHOULD be equal to the RESO Suggested Max Length of 8000

  @PropertyGreenVerification @IDX
  Scenario: GreenVerificationVersion
    When "GreenVerificationVersion" exists in the "PropertyGreenVerification" metadata
    Then "GreenVerificationVersion" MUST be "String" data type
    And "GreenVerificationVersion" length SHOULD be equal to the RESO Suggested Max Length of 25

  @PropertyGreenVerification @IDX
  Scenario: GreenVerificationYear
    When "GreenVerificationYear" exists in the "PropertyGreenVerification" metadata
    Then "GreenVerificationYear" MUST be "Integer" data type

  @PropertyGreenVerification @IDX
  Scenario: ListingId
    When "ListingId" exists in the "PropertyGreenVerification" metadata
    Then "ListingId" MUST be "String" data type
    And "ListingId" length SHOULD be equal to the RESO Suggested Max Length of 255

  @PropertyGreenVerification @IDX
  Scenario: ListingKey
    When "ListingKey" exists in the "PropertyGreenVerification" metadata
    Then "ListingKey" MUST be "String" data type
    And "ListingKey" length SHOULD be equal to the RESO Suggested Max Length of 255

  @PropertyGreenVerification @IDX
  Scenario: ListingKeyNumeric
    When "ListingKeyNumeric" exists in the "PropertyGreenVerification" metadata
    Then "ListingKeyNumeric" MUST be "Integer" data type

  @PropertyGreenVerification @IDX
  Scenario: ModificationTimestamp
    When "ModificationTimestamp" exists in the "PropertyGreenVerification" metadata
    Then "ModificationTimestamp" MUST be "Timestamp" data type
