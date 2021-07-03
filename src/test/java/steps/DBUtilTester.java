package steps;

import utils.ConfigReader;
import utils.Constants;
import utils.DBUtils;

import java.util.List;
import java.util.Map;

public class DBUtilTester {
    public static void main(String[] args) {

        List<Map<String,String>> tableDataAsList=
               DBUtils.getTableDataAsList("select emp_firstname, emp_middle_name, emp_lastname from hs_hr_employees where emp_number='17551';");
        System.out.println(tableDataAsList);
    }
}