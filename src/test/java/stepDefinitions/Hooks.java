package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {

        ApiStepDefinitions apiStepDefinitions = new ApiStepDefinitions();
        if(ApiStepDefinitions.placeId == null){
            apiStepDefinitions.add_place_request_is_created("Camilo", "Iceland", "Russian");
            apiStepDefinitions.user_calls_api_with_http_request("AddPlaceAPI", "POST");
            apiStepDefinitions.extract_place_id_from_add_place_api_response();
        }

    }
}
