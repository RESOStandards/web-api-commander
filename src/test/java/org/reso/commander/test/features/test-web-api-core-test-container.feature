Feature: Web API Container Tests

  Background:
    Given a Web API test container was created using the RESOScript "mock.web-api-server.core.2.0.0.resoscript"
    And a Commander instance exists within the test container

  ####################################
  # Metadata Validation
  ####################################
  Scenario: Test Container is Initialized using Token-Based Authentication
    Given Settings are present in the test container
    And an auth token is provided in "ClientSettings_BearerToken"
    Then the Commander is created using auth token client mode
    And the auth token has a value of "testTokenValue"
    But the Commander is not created using client credentials mode

  Scenario: Metadata validation returns true for known-good metadata
    When sample metadata from "RESODataDictionary-1.7.xml" are loaded into the test container
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
  # Query Operators
  #######################################
  Scenario: OData Count validation returns true when the payload has a count property
    When sample JSON data from "good-property-payload-count.json" are loaded into the test container
    Then OData count tests return "true"

  Scenario: OData Count validation returns false when the payload does not have a count property
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then OData count tests return "false"

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
  # Decimal Comparisons
  #######################################

  # Decimal test 'eq'
  Scenario: Decimal 'eq' tests succeed when valid response data are compared to a valid Double
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "eq" 100000.00 return "true"

  Scenario: Decimal 'eq' tests fail when valid response data are compared to null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "eq" null return "false"

  Scenario: Decimal 'eq' tests succeed when null response data are compared to null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "eq" null return "true"

  Scenario: Decimal 'eq' tests fail when null response data are compared to a valid Integer
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "eq" 100000.00 return "false"


  # Decimal test 'ne'
  Scenario: Decimal 'ne' tests succeed when valid response data are compared to a valid Decimal
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "ne" 100000.00 return "true"

  Scenario: Decimal 'ne' tests succeed when valid response data are compared to null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "ne" null return "true"

  Scenario: Decimal 'ne' tests succeed when null response data are compared to null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "ne" null return "false"

  Scenario: Decimal 'ne' tests fail when null response data are compared to a valid Decicmal
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "ne" 100000.00 return "true"


  # Decimal test 'gt'
  Scenario: Decimal test 'gt' is false when data are compared to an Decimal of greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "gt" 600000.00 return "false"

  Scenario: Decimal test 'gt' is true when data are compared to an Decimal of lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "gt" 400.00 return "true"

  Scenario: Decimal test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "gt" null return "false"

  Scenario: Decimal test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "gt" null return "false"

  Scenario: Integer test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "gt" 100000.01 return "false"


  # Decimal test 'ge'
  Scenario: Decimal 'ge' tests fail when valid response data are compared to a valid Decimal of a lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "ge" 400.00 return "true"

  Scenario: Decimal 'ge' tests succeed when valid response data are compared to a valid Decimal of an equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Integer comparisons of "ListPrice" "ge" 100000.00 return "true"

  Scenario: Decimal 'ge' tests succeed when valid response data are compared to a valid Decimal of a greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "ge" 10.00 return "false"

  Scenario: Decimal 'ge' tests succeed when valid response data are compared to null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "ge" null return "false"

  Scenario: Decimal 'ge' tests succeed when null response data are compared to null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "ge" null return "true"

  Scenario: Decimal 'ge' tests fail when null response data are compared to a valid Decimal
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "ge" 500.00 return "false"


  # Decimal test 'lt'
  Scenario: Decimal test 'lt' is true when data are compared to an Decimal of greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "lt" 100000.02 return "true"

  Scenario: Decimal test 'lt' is false when data are compared to an Decimal of lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "lt" 40.02 return "false"

  Scenario: Decimal test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "lt" null return "false"

  Scenario: Decimal test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "lt" null return "false"

  Scenario: Decimal test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "lt" 32.1 return "false"


  # Decimal test 'le'
  Scenario: Decimal 'le' tests fail when valid response data are compared to a valid Decimal of a greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "le" 100000.01 return "true"

  Scenario: Decimal 'le' tests succeed when valid response data are compared to a valid Decimal of an equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "BedroomsTotal" "le" 100000.00 return "true"

  Scenario: Decimal 'le' tests fail when valid response data are compared to a valid Decimal of a lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "le" 40.00 return "false"

  Scenario: Decimal 'le' tests succeed when valid response data are compared to null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "le" null return "false"

  Scenario: Decimal 'le' tests succeed when null response data are compared to null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "le" null return "true"

  Scenario: Decimal 'le' tests fail when null response data are compared to a valid Decimal
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then Decimal comparisons of "ListPrice" "le" 50.00 return "false"

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
    Then comparisons of Timestamp field "ModificationTimestamp" "gt" "2020-04-02T02:02:01.02Z" return "true"

  Scenario: Timestamp test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "gt" "2020-04-02T02:02:02.02Z" return "false"

  Scenario: Timestamp test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "gt" "2020-04-02T02:02:01.02Z" return "false"


  # Timestamp test 'ge'
  Scenario: Timestamp test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "ge" "2020-04-02T02:02:01.02Z" return "true"

  Scenario: Timestamp test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "ge" "2020-04-02T02:02:02.02Z" return "true"

  Scenario: Timestamp test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "ge" "2020-04-02T02:02:03.02Z" return "false"

  Scenario: Timestamp test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "ge" "2020-04-02T02:02:02.02Z" return "false"

  Scenario: Timestamp test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "ge" null return "true"


  # Timestamp test 'eq'
  Scenario: Timestamp test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "eq" "2020-04-02T02:02:02.02Z" return "true"

  Scenario: Timestamp test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "eq" "2020-04-02T02:02:02.02Z" return "true"

  Scenario: Timestamp test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "eq" "2020-04-02T02:02:02.02Z" return "false"

  Scenario: Timestamp test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "eq" null return "false"

  Scenario: Timestamp test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "eq" null return "true"


  # Timestamp test 'ne'
  Scenario: Timestamp test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "ne" "2020-04-02T02:02:01.02Z" return "true"

  Scenario: Timestamp test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "ne" "2020-04-02T02:02:01.02Z" return "true"

  Scenario: Timestamp test 'ne' is false when values are present and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "ne" null return "true"

  Scenario: Timestamp test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "ne" null return "false"


  # Timestamp test 'lt'
  Scenario: Timestamp test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "lt" "2020-04-02T02:02:03.02Z" return "true"

  Scenario: Timestamp test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "lt" "2020-04-02T02:02:01.02Z" return "false"

  Scenario: Timestamp test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "lt" "2020-04-02T02:02:01.02Z" return "false"


  # Timestamp test 'le'
  Scenario: Timestamp test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "le" "2020-04-02T02:02:02.02Z" return "true"

  Scenario: Timestamp test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "le" "2020-04-02T02:02:02.02Z" return "true"

  Scenario: Timestamp test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "le" "2020-04-02T02:02:01.02Z" return "false"

  Scenario: Timestamp test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "le" "2020-04-02T02:02:02.02Z" return "false"

  Scenario: Timestamp test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then comparisons of Timestamp field "ModificationTimestamp" "le" null return "true"


  # Year test 'gt'
  Scenario: Timestamp year test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "gt" 2019 return "true"

  Scenario: Timestamp year test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "gt" 2021 return "false"

  Scenario: Timestamp year test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp year test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp year test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "gt" 2019 return "false"


  # Year test 'ge'
  Scenario: Timestamp year test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "ge" 2020 return "true"

  Scenario: Timestamp year test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "ge" 2019 return "true"

  Scenario: Timestamp year test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "ge" 2021 return "false"

  Scenario: Timestamp year test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "ge" 2020 return "false"

  Scenario: Timestamp year test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "ge" null return "true"

  Scenario: Timestamp year test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "ge" null return "false"


  # Year test 'eq'
  Scenario: Timestamp year test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "eq" 2020 return "true"

  Scenario: Timestamp year test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "eq" 2019 return "false"

  Scenario: Timestamp year test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "eq" 2020 return "false"

  Scenario: Timestamp year test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "eq" null return "false"

  Scenario: Timestamp year test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "eq" null return "true"


  # Year test 'ne'
  Scenario: Timestamp year test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "ne" 2021 return "true"

  Scenario: Timestamp year test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "ne" 2021 return "true"

  Scenario: Timestamp year test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "ne" null return "true"

  Scenario: Timestamp year test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "ne" null return "false"


  # Year test 'lt'
  Scenario: Timestamp year test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "lt" 2021 return "true"

  Scenario: Timestamp year test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "lt" 2019 return "false"

  Scenario: Timestamp year test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp year test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp year test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "lt" 2020 return "false"


  # Year test 'le'
  Scenario: Timestamp year test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "le" 2022 return "true"

  Scenario: Timestamp year test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "le" 2020 return "true"

  Scenario: Timestamp year test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "le" 2019 return "false"

  Scenario: Timestamp year test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "le" 2020 return "false"

  Scenario: Timestamp year test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Timestamp field "ModificationTimestamp" "le" null return "true"


  # Month test 'gt'
  Scenario: Timestamp month test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "gt" 3 return "true"

  Scenario: Timestamp month test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "gt" 5 return "false"

  Scenario: Timestamp month test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp month test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp month test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "gt" 3 return "false"


  # Month test 'ge'
  Scenario: Timestamp month test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "ge" 4 return "true"

  Scenario: Timestamp month test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "ge" 3 return "true"

  Scenario: Timestamp month test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "ge" 5 return "false"

  Scenario: Timestamp month test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "ge" 4 return "false"

  Scenario: Timestamp month test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "ge" null return "true"

  Scenario: Timestamp month test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "ge" null return "false"


  # Month test 'eq'
  Scenario: Timestamp month test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "eq" 4 return "true"

  Scenario: Timestamp month test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "eq" 3 return "false"

  Scenario: Timestamp month test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "eq" 4 return "false"

  Scenario: Timestamp month test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "eq" null return "false"

  Scenario: Timestamp month test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "eq" null return "true"


  # Month test 'ne'
  Scenario: Timestamp month test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "ne" 5 return "true"

  Scenario: Timestamp month test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "ne" 5 return "true"

  Scenario: Timestamp month test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "ne" null return "true"

  Scenario: Timestamp month test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "ne" null return "false"


  # Month test 'lt'
  Scenario: Timestamp month test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "lt" 5 return "true"

  Scenario: Timestamp month test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "lt" 3 return "false"

  Scenario: Timestamp month test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp month test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp month test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "lt" 4 return "false"


  # Month test 'le'
  Scenario: Timestamp month test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "le" 6 return "true"

  Scenario: Timestamp month test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "le" 4 return "true"

  Scenario: Timestamp month test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "le" 3 return "false"

  Scenario: Timestamp month test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "le" 4 return "false"

  Scenario: Timestamp month test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Timestamp field "ModificationTimestamp" "le" null return "true"


  # Day test 'gt'
  Scenario: Timestamp day test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "gt" 1 return "true"

  Scenario: Timestamp day test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "gt" 3 return "false"

  Scenario: Timestamp day test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp day test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp day test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "gt" 1 return "false"


  # Day test 'ge'
  Scenario: Timestamp day test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "ge" 2 return "true"

  Scenario: Timestamp day test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "ge" 1 return "true"

  Scenario: Timestamp day test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "ge" 3 return "false"

  Scenario: Timestamp day test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "ge" 2 return "false"

  Scenario: Timestamp day test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "ge" null return "true"

  Scenario: Timestamp day test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "ge" null return "false"


  # Day test 'eq'
  Scenario: Timestamp day test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "eq" 2 return "true"

  Scenario: Timestamp day test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "eq" 1 return "false"

  Scenario: Timestamp day test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "eq" 2 return "false"

  Scenario: Timestamp day test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "eq" null return "false"

  Scenario: Timestamp day test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "eq" null return "true"


  # Day test 'ne'
  Scenario: Timestamp day test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "ne" 3 return "true"

  Scenario: Timestamp day test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "ne" 3 return "true"

  Scenario: Timestamp day test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "ne" null return "true"

  Scenario: Timestamp day test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "ne" null return "false"


  # Day test 'lt'
  Scenario: Timestamp day test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "lt" 3 return "true"

  Scenario: Timestamp day test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "lt" 1 return "false"

  Scenario: Timestamp day test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp day test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp day test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "lt" 2 return "false"


  # Day test 'le'
  Scenario: Timestamp day test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "le" 3 return "true"

  Scenario: Timestamp day test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "le" 2 return "true"

  Scenario: Timestamp day test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "le" 1 return "false"

  Scenario: Timestamp day test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "le" 2 return "false"

  Scenario: Timestamp day test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Timestamp field "ModificationTimestamp" "le" null return "true"


  # Hour test 'gt'
  Scenario: Timestamp hour test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "gt" 1 return "true"

  Scenario: Timestamp hour test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "gt" 3 return "false"

  Scenario: Timestamp hour test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp hour test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp hour test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "gt" 1 return "false"


  # Hour test 'ge'
  Scenario: Timestamp hour test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "ge" 2 return "true"

  Scenario: Timestamp hour test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "ge" 1 return "true"

  Scenario: Timestamp hour test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "ge" 3 return "false"

  Scenario: Timestamp hour test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "ge" 2 return "false"

  Scenario: Timestamp hour test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "ge" null return "true"

  Scenario: Timestamp hour test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "ge" null return "false"


  # Hour test 'eq'
  Scenario: Timestamp hour test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "eq" 2 return "true"

  Scenario: Timestamp hour test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "eq" 1 return "false"

  Scenario: Timestamp hour test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "eq" 2 return "false"

  Scenario: Timestamp hour test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "eq" null return "false"

  Scenario: Timestamp hour test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "eq" null return "true"


  # Hour test 'ne'
  Scenario: Timestamp hour test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "ne" 3 return "true"

  Scenario: Timestamp hour test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "ne" 3 return "true"

  Scenario: Timestamp hour test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "ne" null return "true"

  Scenario: Timestamp hour test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "ne" null return "false"


  # Hour test 'lt'
  Scenario: Timestamp hour test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "lt" 3 return "true"

  Scenario: Timestamp hour test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "lt" 1 return "false"

  Scenario: Timestamp hour test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp hour test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp hour test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "lt" 2 return "false"


  # Hour test 'le'
  Scenario: Timestamp hour test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "le" 3 return "true"

  Scenario: Timestamp hour test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "le" 2 return "true"

  Scenario: Timestamp hour test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "le" 1 return "false"

  Scenario: Timestamp hour test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "le" 2 return "false"

  Scenario: Timestamp hour test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "hour" comparisons of Timestamp field "ModificationTimestamp" "le" null return "true"


  # Minute test 'gt'
  Scenario: Timestamp minute test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "gt" 1 return "true"

  Scenario: Timestamp minute test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "gt" 3 return "false"

  Scenario: Timestamp minute test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp minute test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp minute test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "gt" 1 return "false"


  # Minute test 'ge'
  Scenario: Timestamp minute test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "ge" 2 return "true"

  Scenario: Timestamp minute test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "ge" 1 return "true"

  Scenario: Timestamp minute test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "ge" 3 return "false"

  Scenario: Timestamp minute test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "ge" 2 return "false"

  Scenario: Timestamp minute test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "ge" null return "true"

  Scenario: Timestamp minute test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "ge" null return "false"


  # Minute test 'eq'
  Scenario: Timestamp minute test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "eq" 2 return "true"

  Scenario: Timestamp minute test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "eq" 1 return "false"

  Scenario: Timestamp minute test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "eq" 2 return "false"

  Scenario: Timestamp minute test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "eq" null return "false"

  Scenario: Timestamp minute test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "eq" null return "true"


  # Minute test 'ne'
  Scenario: Timestamp minute test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "ne" 3 return "true"

  Scenario: Timestamp minute test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "ne" 3 return "true"

  Scenario: Timestamp minute test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "ne" null return "true"

  Scenario: Timestamp minute test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "ne" null return "false"


  # Minute test 'lt'
  Scenario: Timestamp minute test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "lt" 3 return "true"

  Scenario: Timestamp minute test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "lt" 1 return "false"

  Scenario: Timestamp minute test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp minute test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp minute test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "lt" 2 return "false"


  # Minute test 'le'
  Scenario: Timestamp minute test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "le" 3 return "true"

  Scenario: Timestamp minute test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "le" 2 return "true"

  Scenario: Timestamp minute test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "le" 1 return "false"

  Scenario: Timestamp minute test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "le" 2 return "false"

  Scenario: Timestamp minute test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "minute" comparisons of Timestamp field "ModificationTimestamp" "le" null return "true"


  # Second test 'gt'
  Scenario: Timestamp second test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "gt" 1 return "true"

  Scenario: Timestamp second test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "gt" 3 return "false"

  Scenario: Timestamp second test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp second test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp second test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "gt" 1 return "false"


  # Second test 'ge'
  Scenario: Timestamp second test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "ge" 2 return "true"

  Scenario: Timestamp second test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "ge" 1 return "true"

  Scenario: Timestamp second test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "ge" 3 return "false"

  Scenario: Timestamp second test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "ge" 2 return "false"

  Scenario: Timestamp second test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "ge" null return "true"

  Scenario: Timestamp second test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "ge" null return "false"


  # Second test 'eq'
  Scenario: Timestamp second test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "eq" 2 return "true"

  Scenario: Timestamp second test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "eq" 1 return "false"

  Scenario: Timestamp second test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "eq" 2 return "false"

  Scenario: Timestamp second test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "eq" null return "false"

  Scenario: Timestamp second test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "eq" null return "true"


  # Second test 'ne'
  Scenario: Timestamp second test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "ne" 3 return "true"

  Scenario: Timestamp second test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "ne" 3 return "true"

  Scenario: Timestamp second test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "ne" null return "true"

  Scenario: Timestamp second test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "ne" null return "false"


  # Second test 'lt'
  Scenario: Timestamp second test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "lt" 3 return "true"

  Scenario: Timestamp second test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "lt" 1 return "false"

  Scenario: Timestamp second test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp second test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp second test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "lt" 2 return "false"


  # Second test 'le'
  Scenario: Timestamp second test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "le" 3 return "true"

  Scenario: Timestamp second test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "le" 2 return "true"

  Scenario: Timestamp second test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "le" 1 return "false"

  Scenario: Timestamp second test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "le" 2 return "false"

  Scenario: Timestamp second test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "second" comparisons of Timestamp field "ModificationTimestamp" "le" null return "true"


  # Fractional Second test 'gt'
  Scenario: Timestamp fractional second test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "gt" 0.01 return "true"

  Scenario: Timestamp fractional second test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "gt" 0.03 return "false"

  Scenario: Timestamp fractional second test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp fractional second test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "gt" null return "false"

  Scenario: Timestamp fractional second test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "gt" 0.01 return "false"


  # Fractional Second test 'ge'
  Scenario: Timestamp fractional second test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "ge" 0.02 return "true"

  Scenario: Timestamp fractional second test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "ge" 0.01 return "true"

  Scenario: Timestamp fractional second test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "ge" 0.03 return "false"

  Scenario: Timestamp fractional second test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "ge" 0.02 return "false"

  Scenario: Timestamp fractional second test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "ge" null return "true"

  Scenario: Timestamp fractional second test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "ge" null return "false"


  # Fractional Second test 'eq'
  Scenario: Timestamp fractional second test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "eq" 0.02 return "true"

  Scenario: Timestamp fractional second test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "eq" 0.01 return "false"

  Scenario: Timestamp fractional second test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "eq" 0.02 return "false"

  Scenario: Timestamp fractional second test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "eq" null return "false"

  Scenario: Timestamp fractional second test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "eq" null return "true"


  # Fractional Second test 'ne'
  Scenario: Timestamp fractional second test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "ne" 0.03 return "true"

  Scenario: Timestamp fractional second test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "ne" 0.03 return "true"

  Scenario: Timestamp fractional second test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "ne" null return "true"

  Scenario: Timestamp fractional second test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "ne" null return "false"


  # Fractional Second test 'lt'
  Scenario: Timestamp fractional second test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "lt" 0.03 return "true"

  Scenario: Timestamp fractional second test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "lt" 0.01 return "false"

  Scenario: Timestamp fractional second test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp fractional second test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "lt" null return "false"

  Scenario: Timestamp fractional second test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "lt" 0.02 return "false"


  # Fractional Second test 'le'
  Scenario: Timestamp fractional second test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "le" 0.03 return "true"

  Scenario: Timestamp fractional second test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "le" 0.02 return "true"

  Scenario: Timestamp fractional second test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "le" 0.01 return "false"

  Scenario: Timestamp fractional second test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "le" 0.02 return "false"

  Scenario: Timestamp fractional second test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then fractionalsecond comparisons of Timestamp field "ModificationTimestamp" "le" null return "true"



  #######################################
  # Date Comparisons
  #######################################

  # Year test 'gt'
  Scenario: Date year test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "gt" 2019 return "true"

  Scenario: Date year test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "gt" 2021 return "false"

  Scenario: Date year test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "gt" null return "false"

  Scenario: Date year test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "gt" null return "false"

  Scenario: Date year test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "gt" 2019 return "false"


  # Year test 'ge'
  Scenario: Date year test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "ge" 2020 return "true"

  Scenario: Date year test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "ge" 2019 return "true"

  Scenario: Date year test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "ge" 2021 return "false"

  Scenario: Date year test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "ge" 2020 return "false"

  Scenario: Date year test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "ge" null return "true"

  Scenario: Date year test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "ge" null return "false"


  # Year test 'eq'
  Scenario: Date year test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "eq" 2020 return "true"

  Scenario: Date year test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "eq" 2019 return "false"

  Scenario: Date year test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "eq" 2020 return "false"

  Scenario: Date year test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "eq" null return "false"

  Scenario: Date year test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "eq" null return "true"


  # Year test 'ne'
  Scenario: Date year test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "ne" 2021 return "true"

  Scenario: Date year test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "ne" 2021 return "true"

  Scenario: Date year test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "ne" null return "true"

  Scenario: Date year test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "ne" null return "false"


  # Year test 'lt'
  Scenario: Date year test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "lt" 2021 return "true"

  Scenario: Date year test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "lt" 2019 return "false"

  Scenario: Date year test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "lt" null return "false"

  Scenario: Date year test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "lt" null return "false"

  Scenario: Date year test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "lt" 2020 return "false"


  # Year test 'le'
  Scenario: Date year test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "le" 2022 return "true"

  Scenario: Date year test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "le" 2020 return "true"

  Scenario: Date year test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "le" 2019 return "false"

  Scenario: Date year test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "le" 2020 return "false"

  Scenario: Date year test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "year" comparisons of Date field "ListingContractDate" "le" null return "true"


  # Month test 'gt'
  Scenario: Date month test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "gt" 3 return "true"

  Scenario: Date month test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "gt" 5 return "false"

  Scenario: Date month test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "gt" null return "false"

  Scenario: Date month test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "gt" null return "false"

  Scenario: Date month test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "gt" 3 return "false"


  # Month test 'ge'
  Scenario: Date month test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "ge" 4 return "true"

  Scenario: Date month test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "ge" 3 return "true"

  Scenario: Date month test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "ge" 5 return "false"

  Scenario: Date month test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "ge" 4 return "false"

  Scenario: Date month test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "ge" null return "true"

  Scenario: Date month test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "ge" null return "false"


  # Month test 'eq'
  Scenario: Date month test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "eq" 4 return "true"

  Scenario: Date month test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "eq" 3 return "false"

  Scenario: Date month test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "eq" 4 return "false"

  Scenario: Date month test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "eq" null return "false"

  Scenario: Date month test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "eq" null return "true"


  # Month test 'ne'
  Scenario: Date month test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "ne" 5 return "true"

  Scenario: Date month test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "ne" 5 return "true"

  Scenario: Date month test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "ne" null return "true"

  Scenario: Date month test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "ne" null return "false"


  # Month test 'lt'
  Scenario: Date month test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "lt" 5 return "true"

  Scenario: Date month test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "lt" 3 return "false"

  Scenario: Date month test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "lt" null return "false"

  Scenario: Date month test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "lt" null return "false"

  Scenario: Date month test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "lt" 4 return "false"


  # Month test 'le'
  Scenario: Date month test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "le" 6 return "true"

  Scenario: Date month test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "le" 4 return "true"

  Scenario: Date month test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "le" 3 return "false"

  Scenario: Date month test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "le" 4 return "false"

  Scenario: Date month test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "month" comparisons of Date field "ListingContractDate" "le" null return "true"


  # Day test 'gt'
  Scenario: Date day test 'gt' is true when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "gt" 1 return "true"

  Scenario: Date day test 'gt' is false when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "gt" 3 return "false"

  Scenario: Date day test 'gt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "gt" null return "false"

  Scenario: Date day test 'gt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "gt" null return "false"

  Scenario: Date day test 'gt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "gt" 1 return "false"


  # Day test 'ge'
  Scenario: Date day test 'ge' is true when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "ge" 2 return "true"

  Scenario: Date day test 'ge' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "ge" 1 return "true"

  Scenario: Date day test 'ge' is false when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "ge" 3 return "false"

  Scenario: Date day test 'ge' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "ge" 2 return "false"

  Scenario: Date day test 'ge' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "ge" null return "true"

  Scenario: Date day test 'ge' is false when data are compared to null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "ge" null return "false"


  # Day test 'eq'
  Scenario: Date day test 'eq' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "eq" 2 return "true"

  Scenario: Date day test 'eq' is false when values are valid don't mach
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "eq" 1 return "false"

  Scenario: Date day test 'eq' is false when null data are compared to a valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "eq" 2 return "false"

  Scenario: Date day test 'eq' is false when valid data are compared to a null value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "eq" null return "false"

  Scenario: Date day test 'eq' is true when null data are compared to a null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "eq" null return "true"


  # Day test 'ne'
  Scenario: Date day test 'ne' is true when values are valid and match
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "ne" 3 return "true"

  Scenario: Date day test 'ne' is true when values are null and asserted value is a valid
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "ne" 3 return "true"

  Scenario: Date day test 'ne' is true when values are valid and asserted value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "ne" null return "true"

  Scenario: Date day test 'ne' is false when values are null and asserted value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "ne" null return "false"


  # Day test 'lt'
  Scenario: Date day test 'lt' is true when data are less than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "lt" 3 return "true"

  Scenario: Date day test 'lt' is false when data are greater than given value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "lt" 1 return "false"

  Scenario: Date day test 'lt' is false when data are null and known value is null
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "lt" null return "false"

  Scenario: Date day test 'lt' is false when data are present and known value is null
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "lt" null return "false"

  Scenario: Date day test 'lt' is false when data are null and known value is present
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "lt" 2 return "false"


  # Day test 'le'
  Scenario: Date day test 'le' is true when data are compared to known greater value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "le" 3 return "true"

  Scenario: Date day test 'le' is true when data are compared to known equal value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "le" 2 return "true"

  Scenario: Date day test 'le' is false when data are compared to known lesser value
    When sample JSON data from "good-property-payload.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "le" 1 return "false"

  Scenario: Date day test 'le' is false when null data are compared to valid value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "le" 2 return "false"

  Scenario: Date day test 'le' is true when null data are compared to null value
    When sample JSON data from "good-property-payload-null.json" are loaded into the test container
    Then "day" comparisons of Date field "ListingContractDate" "le" null return "true"

  #######################################
  # Lookup Tests
  #######################################
  Scenario: Multi-valued collections filtered by all() pass when they contain exactly the given values or the empty list
    When sample JSON data from "good-property-payload-all-collection.json" are loaded into the test container
    Then collection values in the "AccessibilityFeatures" field contain only "Visitable" or the empty list is "true"

  Scenario: Multi-valued collections filtered by all() fail when they don't contain exactly the given value or the empty list
    When sample JSON data from "bad-property-payload-all-collection.json" are loaded into the test container
    Then collection values in the "AccessibilityFeatures" field contain only "Visitable" or the empty list is "false"

  Scenario: Multi-valued collections filtered by any() pass when they contain the given value
    When sample JSON data from "good-property-payload-any-collection.json" are loaded into the test container
    Then collection results in the "AccessibilityFeatures" field contain "Visitable" is "true"

  Scenario: Multi-valued collections filtered by any() fail when they don't contain the given value
    When sample JSON data from "bad-property-payload-any-collection.json" are loaded into the test container
    Then collection results in the "AccessibilityFeatures" field contain "Visitable" is "false"