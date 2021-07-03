Feature: Employee Search
  Background:
    And user is logged in with valid Admin credentials
    And user navigates to employee list page


  @smoke
  Scenario: Search Employee by id
    When user enters valid employee id
    And click on search button
    Then  user sees employee information is displayed

  @smoke
  Scenario: Search Employee by name
    When user enters valid employee name
    And click on search button
    Then  user sees employee information is displayed