Feature: Search features

  Background:
    Given I am on the home page
    And I click on the search button

  @bug_1123
  Scenario Outline: Some articles in search results not visible
    When I type a <text>
    And I click on the first element in the collection list
    Then The results are displayed
    And Some items are missing

    Examples:
      |text     |
      |collants |