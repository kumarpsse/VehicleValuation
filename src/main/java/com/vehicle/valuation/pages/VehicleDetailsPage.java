package com.vehicle.valuation.pages;

import com.vehicle.valuation.factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class VehicleDetailsPage extends DriverFactory {

    @FindBy(xpath = " //*[@data-cy='vehicleMakeAndModel']")
    private WebElement model;
    @FindBy(css = ".HeroVehicle__details-XpAI>li:first-of-type")
    private WebElement year;
    @FindBy(id = "e2e-valueDifferentVehicle")
    private WebElement valueDifferentCar;
    @FindBy(xpath = "//*[@class='VRM-module__vrm-hdeF VRM-module__regular-RiIR VRM-module__front-BTQb']")
    private WebElement regNumber;

    public VehicleDetailsPage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
    }

    public String getRegistrationNumber() {
        return regNumber.getText();
    }

    public String getModel() {

        return model.getText();
    }

    public String getYear() {

        return year.getText();
    }

    public SearchVehiclePage clickBackButtonToSearchPage() {
        driver.navigate().back();
        driver.navigate().refresh();
        return new SearchVehiclePage();
    }

    public SearchVehiclePage refreshPage() {
        driver.navigate().refresh();
        return new SearchVehiclePage();
    }

    public Boolean isRegistrationNumberDisplayed() {
        try {
            driver.findElement(new By.ByXPath("//*[@class='VRM-module__vrm-hdeF VRM-module__regular-RiIR VRM-module__front-BTQb']"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

}


