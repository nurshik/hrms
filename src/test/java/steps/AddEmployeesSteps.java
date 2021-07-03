package steps;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.AddEmployeePage;
import pages.DashBoardPage;
import pages.EmployeeListPage;
import utils.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeesSteps extends CommonMethods {
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        DashBoardPage dash=new DashBoardPage();
        click(dash.pimOption);
    }

    @When("user clicks on Add employee button")
    public void user_clicks_on_add_employee_button() {
        DashBoardPage dash=new DashBoardPage();
        click(dash.addEmployeeBtn);
    }

    @When("user enters firstname, middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        AddEmployeePage addEmp=new AddEmployeePage();
        sendText(addEmp.firstName, "Nelson123");
        sendText(addEmp.middleName,"MS");
        sendText(addEmp.lastName, "Smith12");
    }

    @When("user enters firstname {string} middlename {string} and lastname {string}")
    public void user_enters_firstname_middlename_and_lastname(String firstname, String middlename, String lastname) {
        AddEmployeePage addEmp=new AddEmployeePage();
        sendText(addEmp.firstName, firstname);
        sendText(addEmp.middleName,middlename);
        sendText(addEmp.lastName, lastname);
        GlobalVariables.firstName=firstname;
        GlobalVariables.middleName=middlename;
        GlobalVariables.lastName=lastname;

    }

    @When("user enters firstname {string} middlename {string} and lastname {string} in the application")
    public void user_enters_firstname_middlename_and_lastname_in_the_application(String FirstName, String MiddleName, String LastName) {
        AddEmployeePage addEmp=new AddEmployeePage();
        sendText(addEmp.firstName, FirstName);
        sendText(addEmp.middleName,MiddleName);
        sendText(addEmp.lastName, LastName);

    }

    @When("user clicks on save button option")
    public void user_clicks_on_save_button_option() {
        AddEmployeePage addEmp=new AddEmployeePage();
        click(addEmp.saveBtn);

    }
    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee added successfully");
    }

    @When("add multiple employees  and verify they are added successfully")
    public void add_multiple_employees_and_verify_they_are_added_successfully(DataTable employees) throws InterruptedException {
        List<Map<String,String>> employeeNames=employees.asMaps();
        for(Map<String, String> employeename: employeeNames){
            String firstnamevalue = employeename.get("FirstName");
            String middlenamevalue = employeename.get("MiddleName");
            String lastnamevalue = employeename.get("LastName");
            String fullName=firstnamevalue + " "+ middlenamevalue + " " + lastnamevalue;
            System.out.println(fullName);

            AddEmployeePage addEmployeePage = new AddEmployeePage();
            sendText(addEmployeePage.firstName, firstnamevalue);
            sendText(addEmployeePage.middleName, middlenamevalue);
            sendText(addEmployeePage.lastName, lastnamevalue);
            click(addEmployeePage.saveBtn);

            //assertion take it as HW
            EmployeeListPage empPage=new EmployeeListPage();
            Assert.assertTrue(empPage.profileName.getText().equals(fullName));

            Thread.sleep(5000);
            DashBoardPage dash = new DashBoardPage();
            click(dash.addEmployeeBtn);
            Thread.sleep(3000);
        }
    }

    @When("user adds multiple emlployees from excel file from {string} sheet and verify they are added")
    public void user_adds_multiple_emlployees_from_excel_file_from_sheet_and_verify_they_are_added(String sheetName) throws InterruptedException {
    List<Map<String,String>> newEmployees= ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH,sheetName);

    DashBoardPage dash=new DashBoardPage();
    AddEmployeePage addEmployeePage=new AddEmployeePage();
    Iterator<Map<String,String>> it=newEmployees.iterator();
    while(it.hasNext()){
        Map<String,String> mapNewEmp= it.next();
        sendText(addEmployeePage.firstName,mapNewEmp.get("FirstName"));
        sendText(addEmployeePage.middleName, mapNewEmp.get("MiddleName"));
        sendText(addEmployeePage.lastName, mapNewEmp.get("LastName"));
        click(addEmployeePage.saveBtn);
        //aseertion complete as homework
       // Thread.sleep(3000);
    }
    }

    @When("capture the employeeId")
    public void capture_the_employee_id() {
       AddEmployeePage addEmployeePage=new AddEmployeePage();
       GlobalVariables.empId=addEmployeePage.employeeId.getAttribute("value");
    }

    @Then("verify the data from frontend and backend")
    public void verify_the_data_from_frontend_and_backend() {
        System.out.println("Backend");
        System.out.println("DBFirstName "+GlobalVariables.dbFirstName);
        System.out.println("DBMiddleName "+GlobalVariables.dbMiddleName);
        System.out.println("DBLastName "+GlobalVariables.dbLastName);
        System.out.println("Frontend");
        System.out.println("FirstName "+GlobalVariables.firstName);
        System.out.println("MiddleName "+GlobalVariables.middleName);
        System.out.println("LastName "+GlobalVariables.lastName);
        Assert.assertEquals(GlobalVariables.dbFirstName,GlobalVariables.firstName);
        Assert.assertEquals(GlobalVariables.dbMiddleName,GlobalVariables.middleName);
        Assert.assertEquals(GlobalVariables.dbLastName,GlobalVariables.lastName);

    }


}
