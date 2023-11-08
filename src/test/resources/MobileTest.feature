Feature: Mobile bg filter
  Filter by model and price

  Scenario Outline: Navigate to Mobile bg and filter by model and price
    Given Navigate to mobile <url> and accept cookies if exist
    And verify the landing page
    And select car manufacturer <carManufacturer> from drop down
    And put max price <price> for model
    And Click search btn
    Then Verify on the result page
    Then Verify price <price> of the first car
    Then Verify car model <carManufacturer>
    Then close driver
    Examples:
      | url                      | carManufacturer | price |
      | "https://www.mobile.bg/" | "Audi"    | "5000"     |
      | "https://www.mobile.bg/" | "Audi"    | "10000"     |
      | "https://www.mobile.bg/" | "Audi"    | "30000"     |



