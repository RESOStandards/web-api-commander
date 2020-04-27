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
    When sample JSON data from "good-integer-bedroomstotal.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "eq" 5 return "true"

  Scenario: Integer 'eq' tests fail when valid response data are compared to null
    When sample JSON data from "good-integer-bedroomstotal.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "eq" null return "false"

  Scenario: Integer 'eq' tests succeed when null response data are compared to null
    When sample JSON data from "good-integer-bedroomstotal-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "eq" null return "true"

  Scenario: Integer 'eq' tests fail when null response data are compared to a valid Integer
    When sample JSON data from "good-integer-bedroomstotal-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "eq" 5 return "false"

    
  # Integer test 'ne'
  Scenario: Integer 'ne' tests succeed when valid response data are compared to a valid Integer
    When sample JSON data from "good-integer-bedroomstotal.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ne" 10 return "true"

  Scenario: Integer 'ne' tests succeed when valid response data are compared to null
    When sample JSON data from "good-integer-bedroomstotal.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ne" null return "true"

  Scenario: Integer 'ne' tests succeed when null response data are compared to null
    When sample JSON data from "good-integer-bedroomstotal-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ne" null return "false"

  Scenario: Integer 'ne' tests fail when null response data are compared to a valid Integer
    When sample JSON data from "good-integer-bedroomstotal-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ne" 10 return "true"


  # Integer test 'gt'
  Scenario: Integer 'gt' tests fail when valid response data are compared to a valid Integer of greater value
    When sample JSON data from "good-integer-bedroomstotal.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "gt" 6 return "false"

  Scenario: Integer 'gt' tests succeed when valid response data are compared to a valid Integer of lesser value
    When sample JSON data from "good-integer-bedroomstotal.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "gt" 4 return "true"

  Scenario: Integer 'gt' tests fail when valid response data are compared to null
    When sample JSON data from "good-integer-bedroomstotal-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "gt" null return "false"


  # Integer test 'ge'
  Scenario: Integer 'ge' tests fail when valid response data are compared to a valid Integer of a lesser value
    When sample JSON data from "good-integer-bedroomstotal.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" 4 return "true"

  Scenario: Integer 'ge' tests succeed when valid response data are compared to a valid Integer of an equal value
    When sample JSON data from "good-integer-bedroomstotal.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" 5 return "true"

  Scenario: Integer 'ge' tests succeed when valid response data are compared to a valid Integer of a greater value
    When sample JSON data from "good-integer-bedroomstotal.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" 10 return "false"

  Scenario: Integer 'ge' tests succeed when valid response data are compared to null
    When sample JSON data from "good-integer-bedroomstotal.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" null return "false"

  Scenario: Integer 'ge' tests succeed when null response data are compared to null
    When sample JSON data from "good-integer-bedroomstotal-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" null return "true"

  Scenario: Integer 'ge' tests fail when null response data are compared to a valid Integer
    When sample JSON data from "good-integer-bedroomstotal-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "ge" 5 return "false"


  # Integer test 'lt'
  Scenario: Integer 'lt' tests succeed when valid response data are compared to a valid Integer of greater value
    When sample JSON data from "good-integer-bedroomstotal.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "lt" 6 return "true"

  Scenario: Integer 'lt' tests fail when valid response data are compared to a valid Integer of lesser value
    When sample JSON data from "good-integer-bedroomstotal.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "lt" 4 return "false"

  Scenario: Integer 'lt' tests fail when valid response data are compared to null
    When sample JSON data from "good-integer-bedroomstotal-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "lt" null return "false"


  # Integer test 'le'
  Scenario: Integer 'le' tests fail when valid response data are compared to a valid Integer of a greater value
    When sample JSON data from "good-integer-bedroomstotal.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" 6 return "true"

  Scenario: Integer 'le' tests succeed when valid response data are compared to a valid Integer of an equal value
    When sample JSON data from "good-integer-bedroomstotal.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" 5 return "true"

  Scenario: Integer 'le' tests fail when valid response data are compared to a valid Integer of a lesser value
    When sample JSON data from "good-integer-bedroomstotal.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" 4 return "false"

  Scenario: Integer 'le' tests succeed when valid response data are compared to null
    When sample JSON data from "good-integer-bedroomstotal.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" null return "false"

  Scenario: Integer 'le' tests succeed when null response data are compared to null
    When sample JSON data from "good-integer-bedroomstotal-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" null return "true"

  Scenario: Integer 'le' tests fail when null response data are compared to a valid Integer
    When sample JSON data from "good-integer-bedroomstotal-null.json" are loaded into the test container
    Then Integer comparisons of "BedroomsTotal" "le" 5 return "false"


  #######################################
  # String Comparisons
  #######################################

  # String test 'contains'
  Scenario: String 'contains' case-sensitive comparison succeeds
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "contains" "Main" returns "true"

  Scenario: String 'contains' case-sensitive comparison fails
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "contains" "main" returns "false"

  Scenario: String 'contains' results do not contain value
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "contains" "1st" returns "false"

  Scenario: String 'contains' data are present and cannot contain null
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "contains" null returns "false"

  Scenario: String 'contains' data are null and cannot contain value
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "contains" "1st" returns "false"


  # String test 'startswith'
  Scenario: String data 'startswith' given value matching case returns true
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "startswith" "M" returns "true"

  Scenario: String data 'startswith' given value not matching case returns false
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "startswith" "m" returns "false"

  Scenario: String data 'startswith' given value equal to a different character
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "startswith" "z" returns "false"

  Scenario: String data 'startswith' returns false when data are present but asserted value is null
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "startswith" null returns "false"

  Scenario: String data 'startswith' returns false when null data are compared to asserted value
    When sample JSON data from "good-string-streetname-null.json" are loaded into the test container
    Then String data in "StreetName" "startswith" "M" returns "false"


  # String test 'endswith'
  Scenario: String data 'endswith' given value matching case returns true
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "endswith" "n" returns "true"

  Scenario: String data 'endswith' given value not matching case returns false
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "endswith" "N" returns "false"

  Scenario: String data 'endswith' given value equal to a different character
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "endswith" "z" returns "false"

  Scenario: String data 'startswith' returns false when data are present but asserted value is null
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "endswith" null returns "false"

  Scenario: String data 'startswith' returns false when null data are compared to asserted value
    When sample JSON data from "good-string-streetname-null.json" are loaded into the test container
    Then String data in "StreetName" "endswith" "M" returns "false"


  # String test 'tolower'
  Scenario: String 'tolower' matches lowercase string
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "tolower" equals "main"

  Scenario: String 'tolower' does not match uppercase string
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "tolower" does not equal "MAIN"

  Scenario: String 'tolower' does not match mixed-case string
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "tolower" does not equal "Main"

  Scenario: String 'tolower' returns null when data are null
    When sample JSON data from "good-string-streetname-null.json" are loaded into the test container
    Then String data in "StreetName" "tolower" is null


  # String test 'toupper'
  Scenario: String 'toupper' matches uppercase string
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "toupper" equals "MAIN"

  Scenario: String 'toupper' does not match lowercase string
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "toupper" does not equal "main"

  Scenario: String 'toupper' does not match mixed-case string
    When sample JSON data from "good-string-streetname.json" are loaded into the test container
    Then String data in "StreetName" "toupper" does not equal "Main"

  Scenario: String 'toupper' returns null when data are null
    When sample JSON data from "good-string-streetname-null.json" are loaded into the test container
    Then String data in "StreetName" "toupper" is null


  #######################################
  # Timestamp Comparisons
  #######################################

  # Timestamp test 'gt'
  Scenario: Timestamp test 'gt' is true when data are greater than given value
    When sample JSON data from "good-timestamp-modificationtimestamp.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "gt" "2020-04-01T00:00:00Z" return "true"

  Scenario: Timestamp test 'gt' is false when data are less than given value
    When sample JSON data from "good-timestamp-modificationtimestamp.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "gt" "2020-04-01T00:00:02Z" return "false"

  Scenario: Timestamp test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-timestamp-modificationtimestamp-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-timestamp-modificationtimestamp.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-timestamp-modificationtimestamp-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "gt" "2020-04-01T00:00:00Z" return "false"


  # Timestamp test 'ge'
  Scenario: Timestamp test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-timestamp-modificationtimestamp.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ge" "2020-04-01T00:00:00Z" return "true"

  Scenario: Timestamp test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-timestamp-modificationtimestamp.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ge" "2020-04-01T00:00:01Z" return "true"

  Scenario: Timestamp test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-timestamp-modificationtimestamp.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ge" "2020-04-03T00:00:00Z" return "false"

  Scenario: Timestamp test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-timestamp-modificationtimestamp-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ge" "2020-04-01T00:00:01Z" return "false"

  Scenario: Timestamp test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-timestamp-modificationtimestamp-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ge" null return "true"


  # Timestamp test 'eq'
  Scenario: Timestamp test 'eq' is true when values are valid and match
    When sample JSON data from "good-timestamp-modificationtimestamp.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "eq" "2020-04-01T00:00:01Z" return "true"

  Scenario: Timestamp test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-timestamp-modificationtimestamp.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "eq" "2020-04-01T00:00:01Z" return "true"

  Scenario: Timestamp test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-timestamp-modificationtimestamp-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "eq" "2020-04-01T00:00:01Z" return "false"

  Scenario: Timestamp test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-timestamp-modificationtimestamp.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "eq" null return "false"

  Scenario: Timestamp test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-timestamp-modificationtimestamp-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "eq" null return "true"


  # Timestamp test 'ne'
  Scenario: Timestamp test 'ne' is true when values are valid and match
    When sample JSON data from "good-timestamp-modificationtimestamp.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ne" "2020-04-01T00:00:00Z" return "true"

  Scenario: Timestamp test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-timestamp-modificationtimestamp-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ne" "2020-04-01T00:00:00Z" return "true"

  Scenario: Timestamp test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-timestamp-modificationtimestamp-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "ne" null return "false"


  # Timestamp test 'lt'
  Scenario: Timestamp test 'lt' is true when data are less than given value
    When sample JSON data from "good-timestamp-modificationtimestamp.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "lt" "2020-04-01T00:00:02Z" return "true"

  Scenario: Timestamp test 'lt' is false when data are greater than given value
    When sample JSON data from "good-timestamp-modificationtimestamp.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "lt" "2020-04-01T00:00:00Z" return "false"

  Scenario: Timestamp test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-timestamp-modificationtimestamp-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-timestamp-modificationtimestamp.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-timestamp-modificationtimestamp-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "lt" "2020-04-01T00:00:00Z" return "false"


  # Timestamp test 'le'
  Scenario: Timestamp test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-timestamp-modificationtimestamp.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "le" "2020-04-01T00:00:02Z" return "true"

  Scenario: Timestamp test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-timestamp-modificationtimestamp.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "le" "2020-04-01T00:00:01Z" return "true"

  Scenario: Timestamp test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-timestamp-modificationtimestamp.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "le" "2020-04-01T00:00:00Z" return "false"

  Scenario: Timestamp test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-timestamp-modificationtimestamp-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "le" "2020-04-01T00:00:01Z" return "false"

  Scenario: Timestamp test 'le' is true when null data are compared to null value
    When sample JSON data from "good-timestamp-modificationtimestamp-null.json" are loaded into the test container
    Then Timestamp comparisons of "ModificationTimestamp" "le" null return "true"
