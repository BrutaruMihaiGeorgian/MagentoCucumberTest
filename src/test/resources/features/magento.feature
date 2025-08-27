Feature: Magento demo - account and order flow (BDD + POM + logs)

  Background:
    Given the browser is open
    And I go to "https://magento.softwaretestingboard.com/"
    And I accept cookies if present

  @create
  Scenario: Create a new account
    When I open the Create Account page
    And I register with first name "ion" last name "marius" random email and password "Pa$$w0rd"
    Then I should see the text "Thank you for registering with Main Website Store."

  @order
  Scenario: Place an order with the created account
    When I open the Breathe-Easy Tank product page
    And I choose size M and color Blue and add to cart
    And I proceed to checkout
    And I sign in during checkout with the same email and password
    And I fill the address and choose shipping
    Then I should see order total "$49.00"
    When I place the order
    Then I should see confirmation "Thank you for your purchase!"