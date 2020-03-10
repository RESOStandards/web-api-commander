package org.reso.certification.stepdefs;
import io.cucumber.java8.En;

public class DataDictionary implements En {

  public DataDictionary() {
    /*
     * Boolean Fields
     */
    Given("^\"([^\"]*)\" exists in the metadata$", (String fieldName) -> {
    });
    And("^\"([^\"]*)\" boolean values are not null$", (String fieldName) -> {
    });
    Then("^\"([^\"]*)\" should be Boolean data type$", (String fieldName) -> {
    });


    /*
     * Date Fields
     */
    And("^\"([^\"]*)\" date values are not null$", (String fieldName) -> {
    });
    Then("^\"([^\"]*)\" should be Date data type$", (String fieldName) -> {
    });
    And("^\"([^\"]*)\" length should be between the bounds in the metadata$", (String fieldName) -> {
    });
    And("^\"([^\"]*)\" length should be less than or equal to the RESO maxlength of (\\d+)$", (String fieldName, Integer maxLength) -> {
    });

    /*
     * Decimal Fields
     */
    And("^\"([^\"]*)\" decimal values are not null$", (String arg0) -> {
    });
    Then("^\"([^\"]*)\" should be Decimal data type$", (String arg0) -> {
    });
    And("^\"([^\"]*)\" precision should be between the bounds in the metadata$", (String arg0) -> {
    });
    And("^\"([^\"]*)\" scale should be between the bounds in the metadata$", (String arg0) -> {
    });
    And("^\"([^\"]*)\" precision should be less than or equal to the RESO maxlength of (\\d+)$", (String arg0, Integer arg1) -> {
    });
    And("^\"([^\"]*)\" scale should be less than or equal to the RESO scale of (\\d+)$", (String arg0, Integer arg1) -> {
    });


    /*
     * Integer Fields
     */
    And("^\"([^\"]*)\" integer values are not null$", (String arg0) -> {
    });
    Then("^\"([^\"]*)\" should be Integer data type$", (String arg0) -> {
    });



    /*
     * Multi-select Fields
     */
    And("^\"([^\"]*)\" enum values can be compiled$", (String arg0) -> {
    });
    And("^\"([^\"]*)\" stringlist-multi values are not null$", (String arg0) -> {
    });
    Then("^\"([^\"]*)\" should be Array of Strings data type$", (String arg0) -> {
    });
    And("^\"([^\"]*)\" should only contain enum values found in the metadata$", (String arg0) -> {
    });

    /*
     * Single-select Fields
     */
    And("^\"([^\"]*)\" stringlist-single values are not null$", (String arg0) -> {
    });
    Then("^\"([^\"]*)\" should be String data type$", (String arg0) -> {
    });

    /*
     * String Fields
     */
    And("^\"([^\"]*)\" string values are not null$", (String arg0) -> {
    });

    /*
     * Timestamp Fields
     */
    And("^\"([^\"]*)\" timestamp values are not null$", (String arg0) -> {
    });
    Then("^\"([^\"]*)\" should be Timestamp data type$", (String arg0) -> {
    });

  }
}
