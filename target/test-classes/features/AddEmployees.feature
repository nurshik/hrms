Feature: Add employee
Background:
  And user is logged in with valid Admin credentials
  When user clicks on PIM option
  And user clicks on Add employee button

  @smoke
  Scenario: Adding employee from add employee page
    And user enters firstname, middlename and lastname
    And user clicks on save button option
    Then employee added successfully

  @smoke
  Scenario: Adding employee from add employee page via feature file
    And user enters firstname "Jack123" middlename "Keith123" and lastname "Smith123"
    And user clicks on save button option
    Then employee added successfully

  @example
  Scenario Outline: Adding employee from add employee page via feature file
    And user enters firstname "<FirstName>" middlename "<MiddleName>" and lastname "<LastName>" in the application
    And user clicks on save button option
    Then employee added successfully

    Examples:
    |FirstName|MiddleName|LastName|
    |Keith14  |Joye14    |Black14 |
    |Keith15  |Joye15    |Black15 |
    |Keith16  |Joye16    |Black16 |

    @datatablewithheader
    Scenario: Adding multiple employees  in a single execution
      When add multiple employees  and verify they are added successfully
      |FirstName|MiddleName|LastName|
      |Jon0404  | Keith10  |Brown10 |
      |Jon0411  | Keith11  |Brown11 |

    @excel
    Scenario: Adding the employee from excel file
    When user adds multiple emlployees from excel file from "newdata" sheet and verify they are added

    @db
    Scenario: Add the employee and testing it from the backend
      When user enters firstname "Jack123" middlename "Keith123" and lastname "Smith123"
      And capture the employeeId
      And user clicks on save button option
      Then query HRMS database
      And verify the data from frontend and backend

