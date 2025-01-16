package com.vehicle.valuation.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "target/test-classes"},
        plugin = {
                "pretty",
                "html:target/VehicleValuation-Report",
                "json:target/VehicleValuation-Report/cucumber.json",
                "rerun:target/VehicleValuation-Report/rerun.txt"},
        tags = "@vehicleSearch",
        glue = {"com/vehicle/valuation/stepdefinations/"})
public class VehicleRunner {
}
