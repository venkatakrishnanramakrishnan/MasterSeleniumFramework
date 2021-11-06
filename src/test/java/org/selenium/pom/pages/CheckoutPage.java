package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private final By firstNameFld= By.id("billing_first_name");
    private final By lastNameFld= By.id("billing_last_name");
    private final By addressLineOneFld= By.id("billing_address_1");
    private final By billingCityFld= By.id("billing_city");
    private final By billingPostCodeFld= By.id("billing_postcode");
    private final By billingEmailFld= By.id("billing_email");
    private final By placeOrderBtn= By.id("place_order");
    private final By checkoutBtn= By.cssSelector(".checkout-button");
    private final By successNotice = By.cssSelector(".woocommerce-notice");

    public CheckoutPage enterFirstName(String firstName){
        driver.findElement(firstNameFld).sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName){
        driver.findElement(lastNameFld).sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne){
        driver.findElement(addressLineOneFld).sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterBillingCity(String billingCityname){
        driver.findElement(billingCityFld).sendKeys(billingCityname);
        return this;
    }

    public CheckoutPage enterBillingPostcode(String billingPostcode){
        driver.findElement(billingPostCodeFld).sendKeys(billingPostcode);
        return this;
    }

    public CheckoutPage enterBillingEmail(String billingEmailid){
        driver.findElement(billingEmailFld).sendKeys(billingEmailid);
        return this;
    }

    public CheckoutPage placeOrder(){
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getNotice(){
         return driver.findElement(successNotice).getText();

    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
        return enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                enterAddressLineOne(billingAddress.getAddressLineOne()).
                enterBillingCity(billingAddress.getCity()).
                enterBillingPostcode(billingAddress.getPostalCode()).
                enterBillingEmail(billingAddress.getEmail());


    }


}
