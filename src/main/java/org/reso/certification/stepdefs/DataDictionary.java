package org.reso.certification.stepdefs;

import io.cucumber.java8.En;

public class DataDictionary implements En {
  public DataDictionary() {
    Given("^\"([^\"]*)\" exists in the metadata$", (String fieldName) -> {
    });
    Then("^\"([^\"]*)\" MUST be \"([^\"]*)\" data type$", (String arg0, String arg1) -> {
    });
    And("^\"([^\"]*)\" enum values exist in the metadata$", (String arg0) -> {
    });
    And("^\"([^\"]*)\" enum types MUST have at least one member$", (String arg0) -> {
    });
    And("^\"([^\"]*)\" should only contain enum values found in the metadata$", (String arg0) -> {
    });
    And("^\"([^\"]*)\" length should be less than or equal to the RESO Suggested Max Length of (\\d+)$", (String arg0, Integer arg1) -> {
    });
    And("^\"([^\"]*)\" precision should be less than or equal to the RESO Suggested Max Length of (\\d+)$", (String arg0, Integer arg1) -> {
    });
    And("^\"([^\"]*)\" scale should be less than or equal to the RESO Suggested Max Scale of (\\d+)$", (String arg0, Integer arg1) -> {
    });
    And("^\"([^\"]*)\" enumerations must match  MUST have annotations if they contain special characters$", (String arg0) -> {
    });
    And("^metadata were retrieved from the server$", () -> {
    });
    And("^\"([^\"]*)\" is not a synonym for another field$", (String arg0) -> {
    });
    And("^RESO enumerations for \"([^\"]*)\" MUST have annotations if they contain special characters$", (String arg0) -> {
    });
  }
}
