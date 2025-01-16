@vehicleSearch
Feature:Vehicle Valuation Verification

  Scenario Outline: Fetch the vehicle details from input text file and verify vehicle valuation details
    Given Fetch the vehicle details from input text file <INPUT_FILE>
    When  Go to the website and search vehicle valuation details
    Then  Compare the details in output text file <OUTPUT_FILE>
    Examples:
      | INPUT_FILE    | OUTPUT_FILE    |
      | car_input.txt | car_output.txt |