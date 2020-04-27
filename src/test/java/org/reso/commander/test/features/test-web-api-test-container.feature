Feature: Commander Platinum Web API Test Container Tests

  Background:
    Given a Web API test container was created using the RESOScript "mock-platinum.resoscript"
    And a Commander instance exists within the test container

  ####################################
  # Metadata Validation
  ####################################
  Scenario: Test Container is Initialized using Token-Based Authentication
    When sample metadata from "good-edmx-and-edm.xml" are loaded into the test container
    And Settings are present in the test container
    And an auth token is provided in "ClientSettings_BearerToken"
    Then the Commander is created using auth token client mode
    And the auth token has a value of "testTokenValue"
    But the Commander is not created using client credentials mode

  Scenario: Metadata validation returns true for known-good metadata
    When sample metadata from "good-edmx-and-edm.xml" are loaded into the test container
    Then metadata are valid

  Scenario: Metadata validation returns false for known-bad metadata
    When sample metadata from "bad-edmx-no-keyfield.xml" are loaded into the test container
    Then metadata are invalid

  Scenario: DataSystem validation returns true for known-good sample data
    When sample JSON data from "good-datasystem.json" are loaded into the test container
    Then schema validation passes for the sample DataSystem data

  Scenario: DataSystem validation returns false for known-bad sample data
    When sample JSON data from "bad-datasystem.json" are loaded into the test container
    Then schema validation fails for the sample DataSystem data

  #######################################
  # Response Validation
  #######################################

  # test 'eq'
  Scenario: Integer 'eq' tests succeed when valid response data are compared to a valid Integer
    When sample JSON data from "good-integer-bedrooms-total.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "eq" 5 return "true"

  Scenario: Integer 'eq' tests fail when valid response data are compared to null
    When sample JSON data from "good-integer-bedrooms-total.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "eq" null return "false"

  Scenario: Integer 'eq' tests succeed when null response data are compared to null
    When sample JSON data from "good-integer-bedrooms-total-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "eq" null return "true"

  Scenario: Integer 'eq' tests fail when null response data are compared to a valid Integer
    When sample JSON data from "good-integer-bedrooms-total-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "eq" 5 return "false"

  # test 'ne'
  Scenario: Integer 'ne' tests succeed when valid response data are compared to a valid Integer
    When sample JSON data from "good-integer-bedrooms-total.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ne" 10 return "true"

  Scenario: Integer 'ne' tests succeed when valid response data are compared to null
    When sample JSON data from "good-integer-bedrooms-total.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ne" null return "true"

  Scenario: Integer 'ne' tests succeed when null response data are compared to null
    When sample JSON data from "good-integer-bedrooms-total-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ne" null return "false"

  Scenario: Integer 'ne' tests fail when null response data are compared to a valid Integer
    When sample JSON data from "good-integer-bedrooms-total-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ne" 10 return "true"


  # test 'gt'
  Scenario: Integer 'gt' tests fail when valid response data are compared to a valid Integer of greater value
    When sample JSON data from "good-integer-bedrooms-total.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "gt" 6 return "false"

  Scenario: Integer 'gt' tests succeed when valid response data are compared to a valid Integer of lesser value
    When sample JSON data from "good-integer-bedrooms-total.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "gt" 4 return "true"

  Scenario: Integer 'gt' tests fail when valid response data are compared to null
    When sample JSON data from "good-integer-bedrooms-total-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "gt" null return "false"


  # test 'ge'
  Scenario: Integer 'ge' tests fail when valid response data are compared to a valid Integer of a lesser value
    When sample JSON data from "good-integer-bedrooms-total.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" 4 return "true"

  Scenario: Integer 'ge' tests succeed when valid response data are compared to a valid Integer of an equal value
    When sample JSON data from "good-integer-bedrooms-total.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" 5 return "true"

  Scenario: Integer 'ge' tests succeed when valid response data are compared to a valid Integer of a greater value
    When sample JSON data from "good-integer-bedrooms-total.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" 10 return "false"

  Scenario: Integer 'ge' tests succeed when valid response data are compared to null
    When sample JSON data from "good-integer-bedrooms-total.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" null return "false"

  Scenario: Integer 'ge' tests succeed when null response data are compared to null
    When sample JSON data from "good-integer-bedrooms-total-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" null return "true"

  Scenario: Integer 'ge' tests fail when null response data are compared to a valid Integer
    When sample JSON data from "good-integer-bedrooms-total-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" 5 return "false"


  # test 'lt'
  Scenario: Integer 'lt' tests succeed when valid response data are compared to a valid Integer of greater value
    When sample JSON data from "good-integer-bedrooms-total.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "lt" 6 return "true"

  Scenario: Integer 'lt' tests fail when valid response data are compared to a valid Integer of lesser value
    When sample JSON data from "good-integer-bedrooms-total.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "lt" 4 return "false"

  Scenario: Integer 'lt' tests fail when valid response data are compared to null
    When sample JSON data from "good-integer-bedrooms-total-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "lt" null return "false"


  # test 'le'
  Scenario: Integer 'le' tests fail when valid response data are compared to a valid Integer of a greater value
    When sample JSON data from "good-integer-bedrooms-total.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" 6 return "true"

  Scenario: Integer 'le' tests succeed when valid response data are compared to a valid Integer of an equal value
    When sample JSON data from "good-integer-bedrooms-total.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" 5 return "true"

  Scenario: Integer 'le' tests fail when valid response data are compared to a valid Integer of a lesser value
    When sample JSON data from "good-integer-bedrooms-total.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" 4 return "false"

  Scenario: Integer 'le' tests succeed when valid response data are compared to null
    When sample JSON data from "good-integer-bedrooms-total.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" null return "false"

  Scenario: Integer 'le' tests succeed when null response data are compared to null
    When sample JSON data from "good-integer-bedrooms-total-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" null return "true"

  Scenario: Integer 'le' tests fail when null response data are compared to a valid Integer
    When sample JSON data from "good-integer-bedrooms-total-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" 5 return "false"