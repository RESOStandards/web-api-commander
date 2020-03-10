package org.reso.certification.stepdefs;
import io.cucumber.java8.En;

public class DataDictionary implements En {

  public DataDictionary() {

    /*
     * Background Functions
     */
    Given("^an XML Metadata file was provided$", () -> {
    });
    And("^the given file exists$", () -> {
    });
    And("^the file contains valid XML$", () -> {
    });
    And("^the file could be read by the Commander$", () -> {
    });

    /*
     * Generic Functions
     */
    Given("^\"([^\"]*)\" exists in the metadata$", (String fieldName) -> {
    });
    And("^\"([^\"]*)\" values are not null$", (String fieldName) -> {
    });
    Then("^\"([^\"]*)\" should be \"([^\"]*)\" data type$", (String fieldName, String dataTypeName) -> {
    });


    /*
     * Boolean Fields - handled by generic functions
     */

    /*
     * Date Fields
     */
    And("^\"([^\"]*)\" length should be between the bounds in the metadata$", (String fieldName) -> {
    });
    And("^\"([^\"]*)\" length should be less than or equal to the RESO maxlength of (\\d+)$", (String fieldName, Integer maxLength) -> {
    });

    /*
     * Decimal Fields
     */
    And("^\"([^\"]*)\" precision should be between the bounds in the metadata$", (String fieldName) -> {
    });
    And("^\"([^\"]*)\" scale should be between the bounds in the metadata$", (String fieldName) -> {
    });
    And("^\"([^\"]*)\" precision should be less than or equal to the RESO maxlength of (\\d+)$", (String fieldName, Integer maxLength) -> {
    });
    And("^\"([^\"]*)\" scale should be less than or equal to the RESO scale of (\\d+)$", (String fieldName, Integer scale) -> {
    });


    /*
     * Integer Fields - handled by generic functions
     */

    /*
     * Multi-select Fields
     */
    And("^\"([^\"]*)\" enum values can be compiled$", (String fieldName) -> {
    });
    And("^\"([^\"]*)\" should only contain enum values found in the metadata$", (String fieldName) -> {
    });

    /*
     * Single-select Fields - handled by generic functions
     */

    /*
     * String Fields - handled by generic functions
     */

    /*
     * Timestamp Fields - handled by generic functions
     */
  }
}
