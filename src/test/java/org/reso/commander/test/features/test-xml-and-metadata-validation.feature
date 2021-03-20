Feature: Commander XML and Metadata Validation

  Background:
    Given an OData test client has been created


  #######################################
  #  XML Validation Tests
  #######################################
  Scenario: XML Validation using OASIS reference XSDs
    Given metadata were loaded from the sample resource "RESODataDictionary-1.7.xml"
    When XML validation is performed on the resource data
    Then XML validation succeeds

  Scenario: XML Validation fails when XML are malformed
    Given metadata were loaded from the sample resource "bad-edmx-unparsable-xml.xml"
    When XML validation is performed on the resource data
    Then XML validation fails

  #######################################
  #  XML Metadata Validation Tests
  #######################################
  Scenario: XML Metadata validation succeeds when XML Metadata are valid
    Given metadata were loaded from the sample resource "RESODataDictionary-1.7.xml"
    When XML Metadata validation is performed on the resource data
    Then XML Metadata validation succeeds

  Scenario: XML Validation fails when XML Metadata are missing Key element in EntityType definition
    Given metadata were loaded from the sample resource "bad-edmx-no-keyfield.xml"
    When XML Metadata validation is performed on the resource data
    Then XML Metadata validation fails

  #######################################
  #  Edm Validation Tests
  #######################################
  Scenario: Edm validation succeeds when XML Metadata contain a valid Edm
    Given metadata were loaded from the sample resource "RESODataDictionary-1.7.xml"
    When Edm validation is performed on the resource data
    Then Edm Metadata validation succeeds

  Scenario: Edm validation fails when XML Metadata don't contain a valid service document
    Given metadata were loaded from the sample resource "bad-edmx-wrong-edm-binding-target.xml"
    When Edm validation is performed on the resource data
    Then Edm Metadata validation fails
