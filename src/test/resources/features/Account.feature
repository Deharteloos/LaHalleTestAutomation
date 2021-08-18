Feature: Account management features

  @Bug_1264 @connect
  Scenario: Change email with a wrong one
    Given I am on the home page
    When I click on the connection button
    And I log in with my credentials from "credentials"
    And I click on my informations
    And I write a the new wrong email from "new_data"
    Then I should get the error message "L'e-mail renseigné est incorrect"
    But No error message is shown