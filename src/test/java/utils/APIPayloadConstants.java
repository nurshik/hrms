package utils;


import org.json.JSONObject;

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
    public static String createEmployeeBody(){
        //We imported dependency for JSON object
        JSONObject obj=new JSONObject();
            obj.put("emp_firstname","John1155");
            obj.put("emp_lastname","Smith1155");
            obj.put("emp_middle_name","Keith1155");
            obj.put("emp_gender", "M");
            obj.put("emp_birthday", "1991-07-10");
            obj.put("emp_status", "Employee");
            obj.put("emp_job_title", "Cloud Consultant");
            return obj.toString();
    }
    public static String createEmployeeBodyMoreDynamic(String firstName, String lastName, String middleName, String gender
    ,String employeeBday, String employeeStatus, String employeeJobTitle){

        JSONObject obj=new JSONObject();
        obj.put("emp_firstname",firstName);
        obj.put("emp_lastname",lastName);
        obj.put("emp_middle_name",middleName);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", employeeBday);
        obj.put("emp_status", employeeStatus);
        obj.put("emp_job_title", employeeJobTitle);
        return obj.toString();
    }
}
