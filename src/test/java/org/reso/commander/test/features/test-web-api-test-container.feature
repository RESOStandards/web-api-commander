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
  # Integer Comparisons
  #######################################

  # Integer test 'eq'
  Scenario: Integer 'eq' tests succeed when valid response data are compared to a valid Integer
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "eq" 5 return "true"

  Scenario: Integer 'eq' tests fail when valid response data are compared to null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "eq" null return "false"

  Scenario: Integer 'eq' tests succeed when null response data are compared to null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "eq" null return "true"

  Scenario: Integer 'eq' tests fail when null response data are compared to a valid Integer
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "eq" 5 return "false"

    
  # Integer test 'ne'
  Scenario: Integer 'ne' tests succeed when valid response data are compared to a valid Integer
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ne" 10 return "true"

  Scenario: Integer 'ne' tests succeed when valid response data are compared to null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ne" null return "true"

  Scenario: Integer 'ne' tests succeed when null response data are compared to null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ne" null return "false"

  Scenario: Integer 'ne' tests fail when null response data are compared to a valid Integer
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ne" 10 return "true"


  # Integer test 'gt'
  Scenario: Integer test 'gt' is false when data are compared to an Integer of greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "gt" 6 return "false"

  Scenario: Integer test 'gt' is true when data are compared to an Integer of lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "gt" 4 return "true"

  Scenario: Integer test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "gt" null return "false"

  Scenario: Integer test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "gt" null return "false"

  Scenario: Integer test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "gt" 3 return "false"


  # Integer test 'ge'
  Scenario: Integer 'ge' tests fail when valid response data are compared to a valid Integer of a lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" 4 return "true"

  Scenario: Integer 'ge' tests succeed when valid response data are compared to a valid Integer of an equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" 5 return "true"

  Scenario: Integer 'ge' tests succeed when valid response data are compared to a valid Integer of a greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" 10 return "false"

  Scenario: Integer 'ge' tests succeed when valid response data are compared to null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" null return "false"

  Scenario: Integer 'ge' tests succeed when null response data are compared to null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" null return "true"

  Scenario: Integer 'ge' tests fail when null response data are compared to a valid Integer
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" 5 return "false"


  # Integer test 'lt'
  Scenario: Integer test 'lt' is true when data are compared to an Integer of greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "lt" 6 return "true"

  Scenario: Integer test 'lt' is false when data are compared to an Integer of lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "lt" 4 return "false"

  Scenario: Integer test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "lt" null return "false"

  Scenario: Integer test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "lt" null return "false"

  Scenario: Integer test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "lt" 3 return "false"


  # Integer test 'le'
  Scenario: Integer 'le' tests fail when valid response data are compared to a valid Integer of a greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" 6 return "true"

  Scenario: Integer 'le' tests succeed when valid response data are compared to a valid Integer of an equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" 5 return "true"

  Scenario: Integer 'le' tests fail when valid response data are compared to a valid Integer of a lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" 4 return "false"

  Scenario: Integer 'le' tests succeed when valid response data are compared to null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" null return "false"

  Scenario: Integer 'le' tests succeed when null response data are compared to null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" null return "true"

  Scenario: Integer 'le' tests fail when null response data are compared to a valid Integer
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" 5 return "false"


  #######################################
  # String Comparisons
  #######################################

  # String test 'contains'
  Scenario: String 'contains' case-sensitive comparison succeeds
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "contains" "Main" returns "true"

  Scenario: String 'contains' case-sensitive comparison fails
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "contains" "main" returns "false"

  Scenario: String 'contains' results do not contain value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "contains" "1st" returns "false"

  Scenario: String 'contains' data are present and cannot contain null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "contains" null returns "false"

  Scenario: String 'contains' data are null and cannot contain value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "contains" "1st" returns "false"


  # String test 'startswith'
  Scenario: String data 'startswith' given value matching case returns true
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "startswith" "M" returns "true"

  Scenario: String data 'startswith' given value not matching case returns false
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "startswith" "m" returns "false"

  Scenario: String data 'startswith' given value equal to a different character
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "startswith" "z" returns "false"

  Scenario: String data 'startswith' returns false when data are present but asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "startswith" null returns "false"

  Scenario: String data 'startswith' returns false when null data are compared to asserted value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then String data in "StreetName" "startswith" "M" returns "false"


  # String test 'endswith'
  Scenario: String data 'endswith' given value matching case returns true
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "endswith" "n" returns "true"

  Scenario: String data 'endswith' given value not matching case returns false
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "endswith" "N" returns "false"

  Scenario: String data 'endswith' given value equal to a different character
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "endswith" "z" returns "false"

  Scenario: String data 'startswith' returns false when data are present but asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "endswith" null returns "false"

  Scenario: String data 'startswith' returns false when null data are compared to asserted value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then String data in "StreetName" "endswith" "M" returns "false"


  # String test 'tolower'
  Scenario: String 'tolower' matches lowercase string
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "tolower" equals "main"

  Scenario: String 'tolower' does not match uppercase string
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "tolower" does not equal "MAIN"

  Scenario: String 'tolower' does not match mixed-case string
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "tolower" does not equal "Main"

  Scenario: String 'tolower' returns null when data are null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then String data in "StreetName" "tolower" is null


  # String test 'toupper'
  Scenario: String 'toupper' matches uppercase string
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "toupper" equals "MAIN"

  Scenario: String 'toupper' does not match lowercase string
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "toupper" does not equal "main"

  Scenario: String 'toupper' does not match mixed-case string
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then String data in "StreetName" "toupper" does not equal "Main"

  Scenario: String 'toupper' returns null when data are null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then String data in "StreetName" "toupper" is null


  #######################################
  # Timestamp Comparisons
  #######################################

  # Timestamp test 'gt'
  Scenario: Timestamp test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "gt" "2020-04-02T02:02:01.02Z" return "true"

  Scenario: Timestamp test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "gt" "2020-04-02T02:02:02.02Z" return "false"

  Scenario: Timestamp test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "gt" "2020-04-02T02:02:01.02Z" return "false"


  # Timestamp test 'ge'
  Scenario: Timestamp test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ge" "2020-04-02T02:02:01.02Z" return "true"

  Scenario: Timestamp test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ge" "2020-04-02T02:02:02.02Z" return "true"

  Scenario: Timestamp test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ge" "2020-04-02T02:02:03.02Z" return "false"

  Scenario: Timestamp test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ge" "2020-04-02T02:02:02.02Z" return "false"

  Scenario: Timestamp test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ge" null return "true"


  # Timestamp test 'eq'
  Scenario: Timestamp test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "eq" "2020-04-02T02:02:02.02Z" return "true"

  Scenario: Timestamp test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "eq" "2020-04-02T02:02:02.02Z" return "true"

  Scenario: Timestamp test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "eq" "2020-04-02T02:02:02.02Z" return "false"

  Scenario: Timestamp test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "eq" null return "false"

  Scenario: Timestamp test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "eq" null return "true"


  # Timestamp test 'ne'
  Scenario: Timestamp test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ne" "2020-04-02T02:02:01.02Z" return "true"

  Scenario: Timestamp test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ne" "2020-04-02T02:02:01.02Z" return "true"

  Scenario: Timestamp test 'ne' is false when values are present and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ne" null return "true"

  Scenario: Timestamp test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ne" null return "false"


  # Timestamp test 'lt'
  Scenario: Timestamp test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "lt" "2020-04-02T02:02:03.02Z" return "true"

  Scenario: Timestamp test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "lt" "2020-04-02T02:02:01.02Z" return "false"

  Scenario: Timestamp test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "lt" "2020-04-02T02:02:01.02Z" return "false"


  # Timestamp test 'le'
  Scenario: Timestamp test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "le" "2020-04-02T02:02:02.02Z" return "true"

  Scenario: Timestamp test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "le" "2020-04-02T02:02:02.02Z" return "true"

  Scenario: Timestamp test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "le" "2020-04-02T02:02:01.02Z" return "false"

  Scenario: Timestamp test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "le" "2020-04-02T02:02:02.02Z" return "false"

  Scenario: Timestamp test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "le" null return "true"


  #######################################
  # Year Comparisons
  #######################################

  # Year test 'gt'
  Scenario: Year test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "gt" 2019 return "true"

  Scenario: Year test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "gt" 2021 return "false"

  Scenario: Year test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Year test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Year test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "gt" 2019 return "false"


  # Year test 'ge'
  Scenario: Year test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "ge" 2020 return "true"

  Scenario: Year test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "ge" 2019 return "true"

  Scenario: Year test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "ge" 2021 return "false"

  Scenario: Year test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "ge" 2020 return "false"

  Scenario: Year test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "ge" null return "true"

  Scenario: Year test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "ge" null return "false"


  # Year test 'eq'
  Scenario: Year test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "eq" 2020 return "true"

  Scenario: Year test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "eq" 2019 return "false"

  Scenario: Year test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "eq" 2020 return "false"

  Scenario: Year test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "eq" null return "false"

  Scenario: Year test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "eq" null return "true"


  # Year test 'ne'
  Scenario: Year test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "ne" 2021 return "true"

  Scenario: Year test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "ne" 2021 return "true"

  Scenario: Year test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "ne" null return "true"

  Scenario: Year test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "ne" null return "false"


  # Year test 'lt'
  Scenario: Year test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "lt" 2021 return "true"

  Scenario: Year test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "lt" 2019 return "false"

  Scenario: Year test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Year test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Year test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "lt" 2020 return "false"


  # Year test 'le'
  Scenario: Year test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "le" 2022 return "true"

  Scenario: Year test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "le" 2020 return "true"

  Scenario: Year test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "le" 2019 return "false"

  Scenario: Year test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "le" 2020 return "false"

  Scenario: Year test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of "ModificationTimestamp" "le" null return "true"


  #######################################
  # Month Comparisons
  #######################################

  # Month test 'gt'
  Scenario: Month test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "gt" 3 return "true"

  Scenario: Month test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "gt" 5 return "false"

  Scenario: Month test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Month test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Month test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "gt" 3 return "false"


  # Month test 'ge'
  Scenario: Month test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "ge" 4 return "true"

  Scenario: Month test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "ge" 3 return "true"

  Scenario: Month test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "ge" 5 return "false"

  Scenario: Month test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "ge" 4 return "false"

  Scenario: Month test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "ge" null return "true"

  Scenario: Month test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "ge" null return "false"


  # Month test 'eq'
  Scenario: Month test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "eq" 4 return "true"

  Scenario: Month test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "eq" 3 return "false"

  Scenario: Month test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "eq" 4 return "false"

  Scenario: Month test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "eq" null return "false"

  Scenario: Month test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "eq" null return "true"


  # Month test 'ne'
  Scenario: Month test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "ne" 5 return "true"

  Scenario: Month test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "ne" 5 return "true"

  Scenario: Month test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "ne" null return "true"

  Scenario: Month test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "ne" null return "false"


  # Month test 'lt'
  Scenario: Month test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "lt" 5 return "true"

  Scenario: Month test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "lt" 3 return "false"

  Scenario: Month test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Month test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Month test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "lt" 4 return "false"


  # Month test 'le'
  Scenario: Month test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "le" 6 return "true"

  Scenario: Month test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "le" 4 return "true"

  Scenario: Month test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "le" 3 return "false"

  Scenario: Month test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "le" 4 return "false"

  Scenario: Month test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of "ModificationTimestamp" "le" null return "true"


  #######################################
  # Day Comparisons
  #######################################

  # Day test 'gt'
  Scenario: Day test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "gt" 1 return "true"

  Scenario: Day test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "gt" 3 return "false"

  Scenario: Day test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Day test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Day test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "gt" 1 return "false"


  # Day test 'ge'
  Scenario: Day test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "ge" 2 return "true"

  Scenario: Day test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "ge" 1 return "true"

  Scenario: Day test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "ge" 3 return "false"

  Scenario: Day test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "ge" 2 return "false"

  Scenario: Day test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "ge" null return "true"

  Scenario: Day test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "ge" null return "false"


  # Day test 'eq'
  Scenario: Day test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "eq" 2 return "true"

  Scenario: Day test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "eq" 1 return "false"

  Scenario: Day test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "eq" 2 return "false"

  Scenario: Day test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "eq" null return "false"

  Scenario: Day test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "eq" null return "true"


  # Day test 'ne'
  Scenario: Day test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "ne" 3 return "true"

  Scenario: Day test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "ne" 3 return "true"

  Scenario: Day test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "ne" null return "true"

  Scenario: Day test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "ne" null return "false"


  # Day test 'lt'
  Scenario: Day test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "lt" 3 return "true"

  Scenario: Day test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "lt" 1 return "false"

  Scenario: Day test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Day test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Day test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "lt" 2 return "false"


  # Day test 'le'
  Scenario: Day test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "le" 3 return "true"

  Scenario: Day test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "le" 2 return "true"

  Scenario: Day test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "le" 1 return "false"

  Scenario: Day test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "le" 2 return "false"

  Scenario: Day test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of "ModificationTimestamp" "le" null return "true"


  #######################################
  # Hour Comparisons
  #######################################

  # Hour test 'gt'
  Scenario: Hour test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "gt" 1 return "true"

  Scenario: Hour test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "gt" 3 return "false"

  Scenario: Hour test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Hour test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Hour test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "gt" 1 return "false"


  # Hour test 'ge'
  Scenario: Hour test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "ge" 2 return "true"

  Scenario: Hour test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "ge" 1 return "true"

  Scenario: Hour test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "ge" 3 return "false"

  Scenario: Hour test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "ge" 2 return "false"

  Scenario: Hour test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "ge" null return "true"

  Scenario: Hour test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "ge" null return "false"


  # Hour test 'eq'
  Scenario: Hour test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "eq" 2 return "true"

  Scenario: Hour test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "eq" 1 return "false"

  Scenario: Hour test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "eq" 2 return "false"

  Scenario: Hour test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "eq" null return "false"

  Scenario: Hour test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "eq" null return "true"


  # Hour test 'ne'
  Scenario: Hour test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "ne" 3 return "true"

  Scenario: Hour test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "ne" 3 return "true"

  Scenario: Hour test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "ne" null return "true"

  Scenario: Hour test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "ne" null return "false"


  # Hour test 'lt'
  Scenario: Hour test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "lt" 3 return "true"

  Scenario: Hour test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "lt" 1 return "false"

  Scenario: Hour test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Hour test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Hour test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "lt" 2 return "false"


  # Hour test 'le'
  Scenario: Hour test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "le" 3 return "true"

  Scenario: Hour test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "le" 2 return "true"

  Scenario: Hour test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "le" 1 return "false"

  Scenario: Hour test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "le" 2 return "false"

  Scenario: Hour test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of "ModificationTimestamp" "le" null return "true"


  #######################################
  # Minute Comparisons
  #######################################

  # Minute test 'gt'
  Scenario: Minute test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "gt" 1 return "true"

  Scenario: Minute test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "gt" 3 return "false"

  Scenario: Minute test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Minute test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Minute test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "gt" 1 return "false"


  # Minute test 'ge'
  Scenario: Minute test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "ge" 2 return "true"

  Scenario: Minute test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "ge" 1 return "true"

  Scenario: Minute test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "ge" 3 return "false"

  Scenario: Minute test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "ge" 2 return "false"

  Scenario: Minute test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "ge" null return "true"

  Scenario: Minute test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "ge" null return "false"


  # Minute test 'eq'
  Scenario: Minute test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "eq" 2 return "true"

  Scenario: Minute test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "eq" 1 return "false"

  Scenario: Minute test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "eq" 2 return "false"

  Scenario: Minute test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "eq" null return "false"

  Scenario: Minute test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "eq" null return "true"


  # Minute test 'ne'
  Scenario: Minute test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "ne" 3 return "true"

  Scenario: Minute test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "ne" 3 return "true"

  Scenario: Minute test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "ne" null return "true"

  Scenario: Minute test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "ne" null return "false"


  # Minute test 'lt'
  Scenario: Minute test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "lt" 3 return "true"

  Scenario: Minute test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "lt" 1 return "false"

  Scenario: Minute test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Minute test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Minute test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "lt" 2 return "false"


  # Minute test 'le'
  Scenario: Minute test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "le" 3 return "true"

  Scenario: Minute test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "le" 2 return "true"

  Scenario: Minute test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "le" 1 return "false"

  Scenario: Minute test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "le" 2 return "false"

  Scenario: Minute test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of "ModificationTimestamp" "le" null return "true"


  #######################################
  # Second Comparisons
  #######################################

  # Second test 'gt'
  Scenario: Second test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "gt" 1 return "true"

  Scenario: Second test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "gt" 3 return "false"

  Scenario: Second test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Second test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Second test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "gt" 1 return "false"


  # Second test 'ge'
  Scenario: Second test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "ge" 2 return "true"

  Scenario: Second test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "ge" 1 return "true"

  Scenario: Second test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "ge" 3 return "false"

  Scenario: Second test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "ge" 2 return "false"

  Scenario: Second test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "ge" null return "true"

  Scenario: Second test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "ge" null return "false"


  # Second test 'eq'
  Scenario: Second test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "eq" 2 return "true"

  Scenario: Second test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "eq" 1 return "false"

  Scenario: Second test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "eq" 2 return "false"

  Scenario: Second test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "eq" null return "false"

  Scenario: Second test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "eq" null return "true"


  # Second test 'ne'
  Scenario: Second test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "ne" 3 return "true"

  Scenario: Second test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "ne" 3 return "true"

  Scenario: Second test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "ne" null return "true"

  Scenario: Second test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "ne" null return "false"


  # Second test 'lt'
  Scenario: Second test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "lt" 3 return "true"

  Scenario: Second test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "lt" 1 return "false"

  Scenario: Second test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Second test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Second test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "lt" 2 return "false"


  # Second test 'le'
  Scenario: Second test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "le" 3 return "true"

  Scenario: Second test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "le" 2 return "true"

  Scenario: Second test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "le" 1 return "false"

  Scenario: Second test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "le" 2 return "false"

  Scenario: Second test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of "ModificationTimestamp" "le" null return "true"


  #######################################
  # Fractional Second Comparisons
  #######################################

  # Fractional Second test 'gt'
  Scenario: Fractional Second test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "gt" 0.01 return "true"

  Scenario: Fractional Second test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "gt" 0.03 return "false"

  Scenario: Fractional Second test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Fractional Second test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Fractional Second test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "gt" 0.01 return "false"


  # Fractional Second test 'ge'
  Scenario: Fractional Second test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "ge" 0.02 return "true"

  Scenario: Fractional Second test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "ge" 0.01 return "true"

  Scenario: Fractional Second test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "ge" 0.03 return "false"

  Scenario: Fractional Second test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "ge" 0.02 return "false"

  Scenario: Fractional Second test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "ge" null return "true"

  Scenario: Fractional Second test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "ge" null return "false"


  # Fractional Second test 'eq'
  Scenario: Fractional Second test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "eq" 0.02 return "true"

  Scenario: Fractional Second test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "eq" 0.01 return "false"

  Scenario: Fractional Second test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "eq" 0.02 return "false"

  Scenario: Fractional Second test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "eq" null return "false"

  Scenario: Fractional Second test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "eq" null return "true"


  # Fractional Second test 'ne'
  Scenario: Fractional Second test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "ne" 0.03 return "true"

  Scenario: Fractional Second test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "ne" 0.03 return "true"

  Scenario: Fractional Second test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "ne" null return "true"

  Scenario: Fractional Second test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "ne" null return "false"


  # Fractional Second test 'lt'
  Scenario: Fractional Second test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "lt" 0.03 return "true"

  Scenario: Fractional Second test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "lt" 0.01 return "false"

  Scenario: Fractional Second test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Fractional Second test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Fractional Second test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "lt" 0.02 return "false"


  # Fractional Second test 'le'
  Scenario: Fractional Second test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "le" 0.03 return "true"

  Scenario: Fractional Second test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "le" 0.02 return "true"

  Scenario: Fractional Second test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "le" 0.01 return "false"

  Scenario: Fractional Second test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "le" 0.02 return "false"

  Scenario: Fractional Second test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of "ModificationTimestamp" "le" null return "true"
