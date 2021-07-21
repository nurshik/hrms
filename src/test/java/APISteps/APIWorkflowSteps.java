package APISteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
import utils.APIConstants;
import utils.APIPayloadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class APIWorkflowSteps  {
    RequestSpecification request;
    Response response;
    static String employee_id;

    @Given("a request is prepared to create an employee")
    public void a_request_is_prepared_to_create_an_employee() {
        request=given().header(APIConstants.Header_Content_type,APIConstants.Content_type)
                .header(APIConstants.Header_Authorization,GenerateTokenSteps.token)
                .body(APIPayloadConstants.createEmployeePayload());
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
       response=request.when().post(APIConstants.CREATE_EMPLOYEE_URI);
       response.prettyPrint();
    }

    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee created contains key {string} and value {string}")
    public void the_employee_created_contains_key_and_value(String message, String employeeCreated) {
        response.then().assertThat().body(message,equalTo(employeeCreated));
    }

    @Then("the employeeId {string} is stored as a global variable to be used for other calls")
    public void the_employee_id_is_stored_as_a_global_variable_to_be_used_for_other_calls(String empID) {
       employee_id=response.jsonPath().getString(empID);
       System.out.println(employee_id);
    }
///////////////////////////////////////////////////////////////////////////////////////
    @Given("a request is prepared to retrieve the created employee")
    public void a_request_is_prepared_to_retrieve_the_created_employee() {
        request=given().header(APIConstants.Header_Authorization,GenerateTokenSteps.token)
                .header(APIConstants.Header_Content_type,APIConstants.Content_type)
                .queryParams("employee_id",employee_id);
    }

    @When("a GET call is made to retrieve the created employee")
    public void a_get_call_is_made_to_retrieve_the_created_employee() {
        response=request.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
    }

    @Then("the status code for retrieving the created employee is {int}")
    public void the_status_code_for_retrieving_the_created_employee_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }
    @Then("the retrieved employee ID {string} matches the globally stored employee ID")
    public void the_retrieved_employee_id_matches_the_globally_stored_employee_id(String empIDfromResponse) {
       String tempEmpID=response.jsonPath().getString(empIDfromResponse);
       Assert.assertEquals(employee_id,tempEmpID);
    }
    @Then("the retrieved data at {string} matches the data used to create an employee with employee ID {string}")
    public void the_retrieved_data_at_matches_the_data_used_to_create_an_employee_with_employee_id
            (String employeeObject, String responseEmployeeID, DataTable dataTable) {
      List<Map<String,String>> expectedData= dataTable.asMaps(String.class,String.class);
      List<Map<String,String>> actualData=response.body().jsonPath().get(employeeObject);
      int index=0;
      for(Map<String,String> map:expectedData){
          Set<String> keys=map.keySet();
          for(String key: keys){
              String expectedValue=map.get(key);
              String actualValue= actualData.get(index).get(key);
              Assert.assertEquals(expectedValue,actualValue);
          }
          index++;
      }
      String empID2=response.body().jsonPath().getString(responseEmployeeID);
      Assert.assertEquals(empID2,employee_id);
    }

}
