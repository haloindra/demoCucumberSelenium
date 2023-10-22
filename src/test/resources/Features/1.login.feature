Feature: Login Sauce Demo
  Scenario: User successfully login with valid credentials
    Given I am on the Sauce Demo login page
    When I enter username into the field username
    And I enter password into the field password
    And I Click button Login
    Then I successfully login

  Scenario: User attempts to log in with invalid username
    Given I am on the Sauce Demo login page
    When I enter invalid username into the field username
    And I enter password into the field password
    And I Click button Login
    And the user should not be logged in
    Then an error message should be displayed

  Scenario: User attempts to log in with invalid password
    Given I am on the Sauce Demo login page
    When I enter username into the field username
    And I enter invalid password into the field password
    And I Click button Login
    And the user should not be logged in
    Then an error message should be displayed

  Scenario: User attempts to log in with null username
    Given I am on the Sauce Demo login page
    When I enter password into the field password
    And I Click button Login
    And the user should not be logged in
    Then an error message should be displayed

  Scenario: User attempts to log in with null password
    Given I am on the Sauce Demo login page
    When I enter username into the field username
    And I Click button Login
    And the user should not be logged in
    Then an error message should be displayed

  Scenario: User attempts to log in with not fill username and password
    Given I am on the Sauce Demo login page
    When I Click button Login
    And the user should not be logged in
    Then an error message should be displayed