Feature: Syntax HRMS API Workflow
  Description: This feature files tests Syntax HRMS API Workflow
  Background:
  Given JWT is generated

    @Random
      Scenario: Creating Dynamic Employee
      Given a request is prepared an employee with dynamic data: "John1155","Smith1155", "Keith1155", "M","1991-07-10","Employee","Cloud Consultant"


  @APIWorkflow
  Scenario: Creating an Employee
    Given a request is prepared to create an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employeeId "Employee.employee_id" is stored as a global variable to be used for other calls

  @APIWorkflow
  Scenario: Retrieving created employee
    Given a request is prepared to retrieve the created employee
    When a GET call is made to retrieve the created employee
    Then the status code for retrieving the created employee is 200
    And the retrieved employee ID "employee.employee_id" matches the globally stored employee ID
    And the retrieved data at "employee" matches the data used to create an employee with employee ID "employee.employee_id"
  |emp_firstname| emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
  |John1155     |Smith1155    |Keith1155      |M         |1991-07-10  |Employee  |Cloud Consultant|
