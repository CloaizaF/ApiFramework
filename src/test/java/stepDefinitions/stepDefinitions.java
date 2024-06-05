package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class stepDefinitions {

    RequestSpecification requestSpec;
    Response response;
    ResponseSpecification responseSpec;
    TestDataBuild testData = new TestDataBuild();
    Utils utils = new Utils();
    static String placeId;

    @Given("add place request is created with name {string} address {string} language {string}")
    public void add_place_request_is_created(String name, String address, String language) throws IOException {
        requestSpec = given().spec(utils.requestSpecification()).body(testData.generateAddPlacePayload(name, address, language));
    }

    @When("user calls {string} with {string} HTTP request")
    public void user_calls_api_with_http_request(String resource, String httpMethod) {
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        APIResources resourceAPI = APIResources.valueOf(resource);

        if (httpMethod.equalsIgnoreCase("POST")) {
            response = requestSpec.when().post(resourceAPI.getResource());
        } else if (httpMethod.equalsIgnoreCase("GET")) {
            response = requestSpec.when().get(resourceAPI.getResource());
        }
        response = response.then().spec(responseSpec).extract().response();

    }

    @Then("the API Call is successfully done with status code {int}")
    public void the_api_call_is_successfully_done_with_status_code(int expectedStatus) {
        assertEquals(response.getStatusCode(), expectedStatus);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectedValue) {
        assertEquals(utils.getKeyValueFromResponse(response, key), expectedValue);
    }

    @When("extract place_id from AddPlaceAPI response")
    public void extract_place_id_from_add_place_api_response() {
        placeId = utils.getKeyValueFromResponse(response, "place_id");
    }

    @When("get place request is created with place_id")
    public void verify_place_id_is_created_in_maps_with_name_using() throws IOException {
        requestSpec = given().spec(utils.requestSpecification()).queryParam("place_id", placeId);
    }

    @When("delete place request is created with place_id")
    public void delete_place_request_is_created_with_place_id() throws IOException {
        requestSpec = given().spec(utils.requestSpecification())
                .body(testData.generateDeletePlacePayload(placeId));
    }
}
