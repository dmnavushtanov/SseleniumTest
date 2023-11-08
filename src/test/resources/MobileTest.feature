Feature: Mobile bg filter
  Filter by model and price

  Scenario: Navigate to Mobile bg and filter by model and price
    Given Navigate to mobile url and accept cookies if exist
    And verify the landing page
    And select car manufacturer from drop down
    And put max price for model
    Then Correct filter results should appear

