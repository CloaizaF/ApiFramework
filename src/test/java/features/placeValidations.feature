Feature: Validating Place API's

  Scenario: Verify place is successfully added using AddPlaceAPI
    Given add place request is created
    When user calls "AddPlaceAPI" with POST HTTP request
    Then the API Call is successfully done with status code 200
    And "status" in response body is "OK"
