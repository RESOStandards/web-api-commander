package org.reso.certification.stepdefs;

import io.cucumber.java8.En;

public class DataDictionary implements En {
  public DataDictionary() {
    Given("^\"([^\"]*)\" exists in the metadata$", (String fieldName) -> {
    });
    And("^metadata were retrieved from the server$", () -> {
    });
    And("^metadata are valid$", () -> {
    });
    Then("^\"([^\"]*)\" MUST be \"([^\"]*)\" data type$", (String fieldName, String typeName) -> {
    });
    And("^\"([^\"]*)\" length SHOULD be less than or equal to the RESO Suggested Max Length of (\\d+)$", (String fieldName, Integer suggestedMax) -> {
    });
    And("^\"([^\"]*)\" enum values exist in the metadata$", (String fieldName) -> {
    });
    And("^\"([^\"]*)\" enum types MUST have exactly one member$", (String fieldName) -> {
    });
    And("^\"([^\"]*)\" MUST only contain enum values found in the metadata$", (String fieldName) -> {
    });
    And("^\"([^\"]*)\" is not a synonym for another field$", (String fieldName) -> {
    });
    And("^\"([^\"]*)\" enum types MUST have at least one member$", (String fieldName) -> {
    });
    And("^\"([^\"]*)\" SHOULD have no more than the RESO Suggested Max Length of (\\d+) item\\(s\\)$", (String fieldName, Integer suggestedMax) -> {
    });
    And("^\"([^\"]*)\" precision SHOULD be less than or equal to the RESO Suggested Max Length of (\\d+)$", (String fieldName, Integer suggestedMax) -> {
    });
    And("^\"([^\"]*)\" scale SHOULD be less than or equal to the RESO Suggested Max Scale of (\\d+)$", (String fieldName, Integer suggestedMax) -> {
    });
  }
}
