package com.vehicle.valuation.stepdefinations;

import com.vehicle.valuation.factory.DriverFactory;
import com.vehicle.valuation.pages.SearchVehiclePage;
import com.vehicle.valuation.pages.VehicleDetailsPage;
import com.vehicle.valuation.utils.ReadConfigFile;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class VerifyVehicleValuationStepDef extends DriverFactory {
    private SearchVehiclePage searchVehiclePage;
    private VehicleDetailsPage vehicleDetailspage;
    private List<String> vehicleRegNumbers;
    private Map<String, List<String>> vehicleList;


    @Before
    public void setup() {
        DriverFactory.initBrowser();
    }

    @Given("Fetch the vehicle details from input text file (.*)")
    public void fetch_the_vehicle_details_from_input_text_file_car_input_txt(String inputFile) throws IOException {
        vehicleRegNumbers = ReadConfigFile.getVehicleRegistrationDetails(inputFile);

    }

    @When("Go to the website and search vehicle valuation details")
    public void go_to_the_website_and_search_vehicle_valuation_details() {
        vehicleList = new HashMap<String, List<String>>();
        searchVehiclePage = new SearchVehiclePage();
        vehicleDetailspage = new VehicleDetailsPage();
        for (String vehicleNumber : vehicleRegNumbers) {
            List<String> list = new ArrayList<String>();
            vehicleDetailspage = searchVehiclePage.sendVehicleRegistrationNumber(vehicleNumber);
            if (vehicleDetailspage.isRegistrationNumberDisplayed()) {
                list.add(vehicleDetailspage.getRegistrationNumber());
                list.add(vehicleDetailspage.getModel());
                list.add(vehicleDetailspage.getYear());
                vehicleList.put(vehicleNumber, list);
                searchVehiclePage = vehicleDetailspage.clickBackButtonToSearchPage();
            } else {
                System.out.println("Vehicle details not found :" + vehicleNumber);
                searchVehiclePage = vehicleDetailspage.refreshPage();
            }

        }
    }

    @Then("Compare the details in output text file (.*)")
    public void compare_the_details_in_output_text_file_car_output_txt(String outputFIle) throws IOException {
        Map<String, List<String>> expectedResp = ReadConfigFile.getExpectedDetails(outputFIle);
        for (Map.Entry<String, List<String>> vehicleExpectedDetails : expectedResp.entrySet()) {
            System.out.println("expected Vehicle details : " + vehicleExpectedDetails.getKey() + ", Value : " + vehicleExpectedDetails.getValue());

        }
        for (Map.Entry<String, List<String>> vehicleActualDetails : vehicleList.entrySet()) {
            System.out.println("actual Vehicle details : " + vehicleActualDetails.getKey() + ", Value : " + vehicleActualDetails.getValue());

        }

        assertEquals("vehicle Input details not matched with Output vehicle details", expectedResp, vehicleList);
    }

    @After
    public void tearDown() {
        DriverFactory.close();
    }

}