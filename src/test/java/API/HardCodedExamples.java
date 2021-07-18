package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    /*Note:
    Given - Preparing the request
    When - making the request/making the call/hitting the endpoint
    Then - verifications/assertions
   */
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjY2MTc5ODMsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYyNjY2MTE4MywidXNlcklkIjoiMjg0MCJ9.7nQNK2FKYjUieGghOCD_MveeCUpRBAe_BZSM7iPLbbk";
    static String emp_id;
    //@Test
    public void sampleTest() {

        RequestSpecification preparedRequest = given().header("Authorization", token).
                header("Content-Type", "application/json").queryParam("employee_id", "21206A");
        Response response = preparedRequest.when().get("/getOneEmployee.php");
        System.out.println(response.asString());


    }

   @Test
    public void aPostCreateEmployee() {
        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json").body("{\n" +
                "  \"emp_firstname\": \"John1155\",\n" +
                "  \"emp_lastname\": \"Smith1155\",\n" +
                "  \"emp_middle_name\": \"Keith1155\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1991-07-10\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"Cloud Consultant\"\n" +
                "}").log().all();
        //log().all() will log and print all information before sending request
        Response response = preparedRequest.when().post("/createEmployee.php");
        //prettyPrint() does the same as System.out.println(response.asString());
        response.prettyPrint();
        String emp_id=response.jsonPath().getString("Employee.employee_id");
        System.out.println(emp_id);
        /**
         * jsonPath() allows us to retrieve specific data from a json object - just like
         * an xpath with selenium
         */
        emp_id = response.jsonPath().getString("Employee.employee_id");
        //System.out.println(employee_id);
        /**
         /**
         * Performing assertions
         */
        response.then().assertThat().statusCode(201);
        /**
         * Using Hamcrest Matchers class equalTo()
         */
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        // Write an assertion that verifies that the response body has the name you used
        response.then().assertThat().body("Employee.emp_firstname", equalTo("John1155"));
        /**
         * Verifying server
         */
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
    }
    @Test
    public void bGetCreatedEmployee() {
        emp_id="23075A";
        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json").queryParam("employee_id", emp_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");

        //response.prettyPrint();

        String empID = response.jsonPath().getString("employee.employee_id");

        boolean comparingEmpIDs = empID.contentEquals(emp_id);

        Assert.assertTrue(comparingEmpIDs);

        // Task: Retrieve the first name and assert that the first name is the same as the one you used
        //DO NOT USE HAMCREST MATCHERS

        String firstName = response.jsonPath().getString("employee.emp_firstname");
        Assert.assertTrue(firstName.contentEquals("John2255"));
    }

    //@Test
    public void cGetAllEmployees() {

        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json");

        Response response = preparedRequest.when().get("/getAllEmployees.php");

        response.prettyPrint();
        String allEmployees = response.prettyPrint();

        /*
         * Creating object of JsonPath class
         */
        JsonPath js = new JsonPath(allEmployees);

        /*
         * Retrieving number of employees in response body
         */
        int count = js.getInt("Employees.size()");

        System.out.println(count);

        //Print out all employee IDs from the response

        for(int i =0; i < count; i++) {

            String employeedIDs = js.getString("Employees["+ i + "].employee_id");
            //System.out.println(employeedIDs);

            /*
             * Verify stored employee ID from previous call is in response body
             */
            if(employeedIDs.contentEquals(emp_id)) {

                /*
                 * printing out the employee ID
                 */
                System.out.println("Employee ID " + emp_id + " is present in response body");
                String firstName = js.getString("Employees["+ i + "].emp_firstname");

                /*
                 * Printing out the first name of the employee that we created
                 */
                System.out.println("Employee name is "+ firstName);
                break;
            }

        }
    }
    @Test
    public void dPutUpdateCreatedEmployee(){
        //Update created employee
        emp_id="23075A";
        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json").body("{\n" +
                "  \"employee_id\": \""+emp_id+"\",\n"+
                "  \"emp_firstname\": \"John2255\",\n" +
                "  \"emp_lastname\": \"Smith2255\",\n" +
                "  \"emp_middle_name\": \"Keith2255\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1991-07-10\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"Cloud Consultant\"\n" +
                "}");

        //log().all() will log and print all information before sending request
        Response response = preparedRequest.when().put("/updateEmployee.php");
        //prettyPrint() does the same as System.out.println(response.asString());
        response.prettyPrint();
    }
}
