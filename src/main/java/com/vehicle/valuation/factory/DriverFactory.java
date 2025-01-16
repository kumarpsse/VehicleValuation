package com.vehicle.valuation.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    public  static WebDriver driver;
    public  static Properties prop;

    public DriverFactory()  {
        prop=new Properties();
        try {
            ClassLoader classLoader = DriverFactory.class.getClassLoader();
            FileInputStream fileinputStream = new FileInputStream(new File(classLoader.getResource("config.properties").getFile()).getAbsolutePath());
            prop.load(fileinputStream);
        }catch (Exception e){
            e.getMessage();
        }

    }
    public static void initBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
    }

    public static void close(){
        driver.quit();
    }
}
