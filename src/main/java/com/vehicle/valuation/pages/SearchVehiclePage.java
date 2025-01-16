package com.vehicle.valuation.pages;

import com.vehicle.valuation.factory.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchVehiclePage extends DriverFactory {

    @FindBy(xpath = "//input[@id='vrm-input' and @name='vrm-input']")
    private WebElement vehicleReg;
    @FindBy(xpath = "//button[@type='submit'][1]")
    private WebElement button;

    public SearchVehiclePage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
    }

    public VehicleDetailsPage sendVehicleRegistrationNumber(String number) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        vehicleReg.sendKeys(number);
        button.click();
        return new VehicleDetailsPage();
    }

}
