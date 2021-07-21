package APISteps;

import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;

import static io.restassured.RestAssured.*;

public class GenerateTokenSteps {
    static String token;
    @Given("JWT is generated")
    public void JWT_is_generated() {
        RequestSpecification generateTokenRequest = given().header("Content-type", "application/json")
                .body("{\n" +
                        "  \"email\": \"LeraG@gmail.com\",\n" +
                        "  \"password\": \"daetkorovamoloko\"\n" +
                        "}");
        Response generateTokenResponse = generateTokenRequest.when().post(APIConstants.GENERATE_TOKEN_URI);
        generateTokenResponse.prettyPrint();
        token="Bearer "+generateTokenResponse.jsonPath().getString("token");
    }
}
