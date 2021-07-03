package steps;

import io.cucumber.java.en.Then;
import utils.DBUtils;
import utils.GlobalVariables;

import java.util.List;
import java.util.Map;

public class DBSteps {

    @Then("query HRMS database")
    public void query_hrms_database() {
        String query="select emp_firstname, emp_middle_name, " +
                "emp_lastname from hs_hr_employees where emp_number="+GlobalVariables.empId;
        List<Map<String, String>> tableDataAsList=DBUtils.getTableDataAsList(query);
        GlobalVariables.dbFirstName=tableDataAsList.get(0).get("emp_firstname");
        GlobalVariables.dbMiddleName=tableDataAsList.get(0).get("emp_middle_name");
        GlobalVariables.dbLastName=tableDataAsList.get(0).get("emp_lastname");

    }


}
