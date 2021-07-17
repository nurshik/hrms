package API;

import io.restassured.RestAssured;
import io.restassured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class HardCodedExamples {

    /*Note:
    Given - Preparing the request
    When - making the request/making the call/hitting the endpoint
    Then - verifications/assertions
   */
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjY1NDg0MDEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYyNjU5MTYwMSwidXNlcklkIjoiMjg0MCJ9.SD_9OqBvNazLMfXUen6g-thyr8EJsQ4beg9sgn129Hg";

    //@Test
    public void sampleTest() {

        RequestSpecification preparedRequest = given().header("Authorization", token).
                header("Content-Type", "application/json").queryParam("employee_id", "21206A");
        Response response = preparedRequest.when().get("/getOneEmployee.php");
        System.out.println(response.asString());


    }

    @Test
    public void postCreateEmployee() {
        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json").body("{\n" +
                "  \"emp_firstname\": \"John1155\",\n" +
                "  \"emp_lastname\": \"Smith1155\",\n" +
                "  \"emp_middle_name\": \"Keith1155\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1991-07-10\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"Cloud Consultant\"\n" +
                "}").log().all();
        Response response = preparedRequest.when().post("/createEmployee.php");
        response.prettyPrint();
    }
}
