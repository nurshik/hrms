package utils;

import APISteps.GenerateTokenSteps;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class APICommonMethods {

    //Use this method to prepare and create an employee
public static RequestSpecification createEmployeeRequest(String createEmployeePayload){
    RequestSpecification request=given().header(APIConstants.Header_Content_type,APIConstants.Content_type)
            .header(APIConstants.Header_Authorization, GenerateTokenSteps.token)
            .body(createEmployeePayload);
    return request;
}

}
