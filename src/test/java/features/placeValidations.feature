Feature: Validating Place API's

  Scenario Outline: Verify place is successfully added using AddPlaceAPI
    Given add place request is created with name "<name>" address "<address>" language "<language>"
    When user calls "AddPlaceAPI" with POST HTTP request
    Then the API Call is successfully done with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    Examples:
      | name    | address         | language |
      | AAHouse | Martha Avenue   | English  |
      | BBHouse | Lawrence Street | French   |
