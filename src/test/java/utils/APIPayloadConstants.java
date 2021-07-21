package utils;

public class APIPayloadConstants {

    public static String createEmployeePayload(){
        String createEmployeePayload="{\n" +
                "  \"emp_firstname\": \"John1155\",\n" +
                "  \"emp_lastname\": \"Smith1155\",\n" +
                "  \"emp_middle_name\": \"Keith1155\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1991-07-10\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"Cloud Consultant\"\n" +
                "}";
        return createEmployeePayload;
    }
}
