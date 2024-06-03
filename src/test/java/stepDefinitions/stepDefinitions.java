package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.LocationDetails;
import pojo.PlaceDetails;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class stepDefinitions {

    RequestSpecification addPlaceResponse;
    Response response;
    ResponseSpecification responseSpec;
    Utils utils = new Utils();

    @Given("add place request is created")
    public void add_place_request_is_created() throws IOException {
        TestDataBuild testData = new TestDataBuild();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        addPlaceResponse = given().spec(utils.requestSpecification()).body(testData.generateAddPlacePayload());
    }

    @When("user calls {string} with POST HTTP request")
    public void user_calls_with_post_http_request(String string) {
        response = addPlaceResponse.when().post("maps/api/place/add/json")
                .then().spec(responseSpec).extract().response();
    }

    @Then("the API Call is successfully done with status code {int}")
    public void the_api_call_is_successfully_done_with_status_code(int expectedStatus) {
        assertEquals(response.getStatusCode(), expectedStatus);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectedValue) {
        JsonPath jsonResponse = new JsonPath(response.asString());
        String currentValue = jsonResponse.get(key).toString();
        assertEquals(currentValue, expectedValue);
    }

}
