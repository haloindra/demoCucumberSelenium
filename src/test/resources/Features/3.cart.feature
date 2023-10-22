Feature: Cart Functionality

  Scenario: Adding Items to the Cart
    Given the user is on the Cart page
    When the user adds an item to the cart
    And the user adds another item to the cart
    Then the cart should display the added items
    And the cart total should be updated accordingly

  Scenario: Removing Items from an Empty Cart
    Given the user is on the Cart page
    When the user adds an item to the cart
    And the user adds another item to the cart
    And the user attempts to remove an item from the empty cart
    Then the cart should indicating it's empty

  Scenario: Proceeding to Checkout with an Empty Cart
    Given the user is on the Cart page
    And the cart should indicating it's empty
    When the user clicks on the Proceed to Checkout button
    Then the user cannot access checkout