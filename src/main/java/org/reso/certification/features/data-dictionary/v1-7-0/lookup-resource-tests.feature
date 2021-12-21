# This feature implements the change proposal outlined in
# section 2.2 of the RESO Data Dictionary 1.7 specification.
#
# The tests for the Lookup resource model is in ../resources/lookup.feature
#
# See: https://github.com/RESOStandards/reso-transport-specifications/blob/cd8bbd2038955e5380598d509fa2245bc98cbfdd/DATA-DICTIONARY.md#lookup-resource
Feature: Lookup Acceptance Tests (RCP-032)

  Background:
    Given a RESOScript or Metadata file are provided
    When a RESOScript file is provided
    Then Client Settings and Parameters can be read from the RESOScript
    And a test container was successfully created from the given RESOScript file
    And the test container uses an Authorization Code or Client Credentials for authentication
    And valid metadata were retrieved from the server
    And valid metadata are loaded into the test container
    Given the Lookup Resource exists in the metadata
    Then data are replicated from the Lookup Resource
    And items are added to the Lookup cache

  #  <!-- OData annotation for String List, Single field -->
  #  <Property Name="OfficeCountyOrParish" Type="Edm.String">
  #  <Annotation Term="RESO.OData.Metadata.LookupName" String="CountyOrParish" />
  #  </Property>
  #
  #  <!-- OData annotation for String List, Multi field -->
  #  <Property Name="ExteriorFeatures" Type="Collection(Edm.String)">
  #  <Annotation Term="RESO.OData.Metadata.LookupName" String="ExteriorFeatures" />
  #  </Property>
  @dd-1.7 @rcp-032
  Scenario: Check Required Annotations
    Given Lookup metadata have been retrieved from the Lookup resource
    Then the term "RESO.OData.Metadata.LookupName" MUST be present in the annotations for each field
    And each LookupName MUST be declared in the String property of the field
    And each given LookupName MUST exist in the Lookup Resource