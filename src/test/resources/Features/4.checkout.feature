Feature: Checkout Process
  Scenario: Proceeding to the Next Checkout Step with Valid Information
    Given i am on the Checkout: Your Information Page
    When i enters a valid identity
    And i clicks the Continue button
    And i should be directed to the next checkout step
    And i click finish
    Then order has been dispatched

  Scenario: User inputs invalid checkout information
    Given i am on the Checkout: Your Information Page
    When i inputs a valid first name
    And i inputs a valid last name
    And i inputs an invalid zip/postal code
    And i clicks the Continue button
    Then i should see an error message indicating invalid information

  Scenario: User not fill all form checkout information
    Given i am on the Checkout: Your Information Page
    When i clicks the Continue button
    Then i should cannot access checkout overview




