package org.reso.certification.stepdefs;

import io.cucumber.java8.En;

public class DataDictionary implements En {
  public DataDictionary() {
    Given("^\"([^\"]*)\" exists in the metadata$", (String fieldName) -> {
    });
    Then("^\"([^\"]*)\" should be \"([^\"]*)\" data type$", (String fieldName, String dataTypeName) -> {
    });
    And("^\"([^\"]*)\" enum values can be compiled$", (String fieldName) -> {
    });
    And("^\"([^\"]*)\" should only contain enum values found in the metadata$", (String fieldName) -> {
    });
    And("^\"([^\"]*)\" length should be less than or equal to the RESO Suggested Max Length of (\\d+)$", (String fieldName, Integer suggestedMax) -> {
    });
    And("^\"([^\"]*)\" precision should be less than or equal to the RESO Suggested Max Length of (\\d+)$", (String fieldName, Integer suggestedMax) -> {
    });
    And("^\"([^\"]*)\" scale should be less than or equal to the RESO Suggested Max Scale of (\\d+)$", (String fieldName, Integer suggestedMax) -> {
    });
  }
}
