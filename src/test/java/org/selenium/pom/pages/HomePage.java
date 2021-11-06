package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class HomePage extends BasePage {
    ////a simple extends results in error 'There is no default constructor available in org.selenium.pom.base.BasePage
    ///To over come this
    ///You can write a subclass constructor that invokes the constructor of the superclass,
    // either implicitly or by using the keyword super.

    ////defining the webelement of HomePage
    private final By storeMenuLink = By.xpath("//a[@class='wp-block-button__link']");

    public HomePage(WebDriver driver){ ///this a parameterized constructor of HomePage which accepts argument 'driver' of type WebDriver
        ///the argument is then passed to the constructor of BasePage constructor using super keyword as shown below
        super(driver);
    }
////////clicking on Store menu is going to result in StorePage
    /////since StorePage is an object, i am stating the return type of clickStoreMenuLink() method is StorePage Object
    /////this is called fluentIterface
    public StorePage clickStoreMenuLink(){
        driver.findElement(storeMenuLink).click();
        return new StorePage(driver);
    }
}
