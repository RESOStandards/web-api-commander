# This feature implements the change proposal outlined in
# section 2.2 of the RESO Data Dictionary 1.7 specification.
#
# The tests for the Lookup resource model is in ../resources/lookup.feature
#
# See: https://github.com/RESOStandards/reso-transport-specifications/blob/cd8bbd2038955e5380598d509fa2245bc98cbfdd/DATA-DICTIONARY.md#lookup-resource
Feature: Lookup Acceptance Tests (RCP-032)

  Background:
    When a RESOScript file is provided
    Then Client Settings and Parameters can be read from the RESOScript
    And a test container was successfully created from the given RESOScript file
    And the test container uses an Authorization Code or Client Credentials for authentication
    And valid metadata were retrieved from the server
    When the "Lookup" Resource exists in the metadata
    Then valid data is replicated from the "Lookup" Resource

  @dd-1.7 @rcp-032
  Scenario: Ensure That Required Lookup Resource Fields Are Present in Server Metadata
    When the "Lookup" Resource exists in the metadata
    Then "Lookup" Resource data and metadata MUST contain the following fields
      | LookupKey |
      | LookupName |
      | LookupValue |
      | ModificationTimestamp |


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
    When the "Lookup" Resource exists in the metadata
    Then Lookup fields MUST have an annotation with term "RESO.OData.Metadata.LookupName" and string value of LookupName