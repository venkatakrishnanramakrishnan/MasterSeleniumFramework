package org.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class MyFirstTestCase extends BaseTest {
    @Test   ////////@Test is an interface in Test.class
    public void guestCheckOutUsingDirectBankTransfer() throws InterruptedException, IOException {
        //WebDriverManager.chromedriver().setup();
        /////Without the above line we would get the below error
        /////java.lang.IllegalStateException: The path to the driver executable The path to the driver executable must be set by the webdriver.chrome.driver system property; for more information, see https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver. The latest version can be downloaded from http://chromedriver.storage.googleapis.com/index.html
        ///WebDriver driver = new ChromeDriver(); ///WebDriver is an interface in WebDriver.class
        ////We have to inform the path of the driver.exe
        ///this can be done either by hard coding the path or by setting the environment variable
        ///Or use webdrivermanager - We are using this
        InputStream is = getClass().getClassLoader().getResourceAsStream("myBillingAddress.json");

        BillingAddress billingAddress = new BillingAddress();
//        billingAddress.setFirstName("demo");
//        billingAddress.setLastName("user");
//        billingAddress.setAddressLineOne("San Fransico");
//        billingAddress.setCity("San Fransico");
//        billingAddress.setPostalCode("94188");
//        billingAddress.setEmail("test@test.com");
        billingAddress = JacksonUtils.deserializeJson(is,billingAddress);

        ///Navigate to landing page
        driver.get("https://askomdch.com/");
        driver.manage().window().maximize();
        //////Thread.sleep(5000);
        for(int j=1;j<60;j++){
            if(driver.findElement(By.cssSelector("a[class='wp-block-button__link']")).isDisplayed()){
                break;
            }

        }
        //click on store link
        ////driver.findElement(By.cssSelector("a[class='wp-block-button__link']")).click();
        ///homepage being the first page object, we are explicitly creating the object as show below
        ////HomePage homePage = new HomePage(driver);
        HomePage homePage = new HomePage(driver);
        /////
        StorePage storePage = homePage.clickStoreMenuLink();
        storePage.verifyStorePageIsDisplayed();


        ///Search for a product 'jelaby' and validate that when there is no matching result

        ///Validate >>> user finds the msg 'No products were found matching your selection.'

        storePage.enterSearchTextInSearchField("Jeleby");
        ////driver.findElement(By.cssSelector("button[value='Search']")).click();
        storePage.clickSearchButton();


        Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Search results:')]")).isDisplayed(),"Search Result header verification.");
        storePage.verifySearchResultHeader();
        storePage.verifNoMatchingProdFoundInfo();

        ///Search again and validate>>> when there is  matching result the products are listed
        ////driver.findElement(By.cssSelector("#woocommerce-product-search-field-0")).clear();
        storePage.clearSearchFieldInStorePage();

        ////driver.findElement(By.cssSelector("#woocommerce-product-search-field-0")).sendKeys("Blue Shoes");
        storePage.enterSearchTextInSearchField("Shoes");
        ///driver.findElement(By.cssSelector("button[value='Search']")).click();
        storePage.clickSearchButton();

        for(int j=1;j<60;j++){
            if(driver.findElement(By.xpath("//p[@class='woocommerce-result-count']")).isDisplayed()){
                break;
            }

        }
        String actualTxt = driver.findElement(By.xpath("//h1[contains(text(),'Search results')]")).getText();
        /////Assert.
        System.out.println("Venkata - search result found:: "+actualTxt);
        if(actualTxt.contains("shoe")){
            Assert.assertTrue(actualTxt.contains("Shoes"),"Search Result found for shoe.");
        }
        //////Robot robo = new Robot();
        //////Thread.sleep(250);
        ///JavascriptExecutor js = (JavascriptExecutor) driver;
        //This will scroll the page till the element is found
        ////js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//button[@name='add-to-cart']")));

        ////Add a product to Add to Cart
        ///driver.findElement(By.xpath("//button[@name='add-to-cart']")).click();
        storePage.clickAddToCartButton();

        /////////Thread.sleep(3000);

        //click on View cart
        ///driver.findElement(By.cssSelector("div[role='alert'] a[class='button wc-forward']")).click();
        CartPage cartPage = storePage.clickViewCart();
        ///Verify the Cart
        Assert.assertEquals(driver.findElement(By.cssSelector(".has-text-align-center")).getText(),"Cart");
        CheckoutPage checkoutPage = cartPage.clickCheckoutBtn();

        ///below way of writing is called builder Pattern
        checkoutPage.

//                enterFirstName("demo").
//                enterLastName("user").
//                enterAddressLineOne("San Francisco").
//                enterBillingCity("San Francisco").
//                enterBillingPostcode("94188").
//                enterBillingEmail("test@test.com").
                setBillingAddress(billingAddress).
                placeOrder();
        //////Thread.sleep(5000);
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
        //driver.quit();



    }
}
