package org.reso.certification.stepdefs;

import io.cucumber.java8.En;

public class DataDictionary implements En {
  public DataDictionary() {
    Given("^\"([^\"]*)\" exists in the metadata$", (String arg0) -> {
    });
    Then("^\"([^\"]*)\" should be \"([^\"]*)\" data type$", (String arg0, String arg1) -> {
    });
    And("^\"([^\"]*)\" enum values can be compiled$", (String arg0) -> {
    });
    And("^\"([^\"]*)\" should only contain enum values found in the metadata$", (String arg0) -> {
    });
    And("^\"([^\"]*)\" length should be less than or equal to the RESO Suggested Max Length of (\\d+)$", (String arg0, Integer arg1) -> {
    });
    And("^\"([^\"]*)\" precision should be less than or equal to the RESO Suggested Max Length of (\\d+)$", (String arg0, Integer arg1) -> {
    });
    And("^\"([^\"]*)\" scale should be less than or equal to the RESO Suggested Max Scale of (\\d+)$", (String arg0, Integer arg1) -> {
    });
  }
}
