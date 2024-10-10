Feature: Validating Place API's

  @AddPlace @Regression
  Scenario Outline: Verify place is successfully added using AddPlaceAPI
    Given add place request is created with name "<name>" address "<address>" language "<language>"
    When user calls "AddPlaceAPI" with "POST" HTTP request
    Then the API Call is successfully done with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    When extract place_id from AddPlaceAPI response
    And get place request is created with place_id
    And user calls "GetPlaceAPI" with "GET" HTTP request
    Then "name" in response body is "<name>"
    Examples:
      | name    | address         | language |
      | AAHouse | Martha Avenue   | English  |
      | BBHouse | Lawrence Street | French   |

  @DeletePlace @Regression
  Scenario: Verify place is deleted by using DeletePlaceAPI
    Given delete place request is created with place_id
    When user calls "DeletePlaceAPI" with "POST" HTTP request
    Then the API Call is successfully done with status code 200