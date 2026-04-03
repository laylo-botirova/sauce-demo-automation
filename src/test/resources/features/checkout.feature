Feature: Checkout functionality

  Scenario: Successful checkout
    Given user is logged in
    When user adds product "Sauce Labs Backpack" to cart
    And user clicks checkout
    And user enters checkout information
    Then user should complete the order

  Scenario: Checkout with multiple items
    Given user is logged in
    When user adds products to cart
      | Sauce Labs Backpack |
      | Sauce Labs Onesie   |
    And user clicks checkout
    And user enters checkout information
    Then user should validate total price
    And user should complete the order