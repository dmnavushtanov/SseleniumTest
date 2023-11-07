Feature: Mobile bg filter
  Filter by model and price

  Scenario: Navigate to Mobile bg and filter by model and price
    Given User navigate to mobile bg
    When Choose a model from selector
    And Put max price for the model
    Then Correct filter results should appear

