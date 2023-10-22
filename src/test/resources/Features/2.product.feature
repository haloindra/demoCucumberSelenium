Feature: Product
  Scenario: User picks a product and adds it to the cart
    Given I am on the Sauce Demo homepage
    When the user finds the product Sauce Labs Backpack
    And clicks the Add to Cart button
    Then the product should be added to the cart

  Scenario: User picks all product and adds it to the cart
    Given I am on the Sauce Demo homepage
    When the user finds all the product
    And clicks the all product Add to Cart button
    Then all product should be added to the cart

  Scenario: User remove all product on cart
    Given I am on the Sauce Demo homepage
    When I have all product on cart
    And I click button remove to all product
    Then count cart is empty

  Scenario: User remove all product on cart with reset on state
    Given I am on the Sauce Demo homepage
    When I have all product on cart
    And I click hamburger menu
    And I click reset app state
    Then count cart is empty