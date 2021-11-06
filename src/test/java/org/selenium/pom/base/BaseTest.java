package org.selenium.pom.base;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver; ////instance variable for the driver
    ///this method is public as this is to be shared across tests
    @BeforeMethod
    public void startDriver(){
        driver = new DriverManager().initializeDriver();
    }

    ///this method is public as this is to be shared across tests
    @AfterMethod
    public void stopDriver(){
        driver.quit();
    }
}
