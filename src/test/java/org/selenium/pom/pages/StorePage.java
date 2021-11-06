package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.testng.Assert;

public class StorePage extends BasePage {


    public StorePage(WebDriver driver) {
        super(driver);
    }

    private final By pageHeader= By.xpath("//h1[contains(text(),'Store')]");
    private final By searchField = By.xpath("//input[@id='woocommerce-product-search-field-0']");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By searchInfo = By.xpath("//p[text()='No products were found matching your selection.']");
    private final By searchResultHeader = By.xpath("//h1[contains(text(),'Search results:')]");
    private final By viewCartLink = By.cssSelector("a[title='View cart']");
    ///private final By  addToCart = By.xpath("//a[contains(text(),'Add to cart']");
    private final By addToCart = By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']");



    public void verifyStorePageIsDisplayed() throws InterruptedException {
        ////Thread.sleep(10000);
        Assert.assertTrue(driver.findElement(pageHeader).isDisplayed(),"Store Page should be displayed.");
    }

    public void enterSearchTextInSearchField(String txtToSearch) throws InterruptedException{
        ////Thread.sleep(10000);
        System.out.println("inside the method that enters a string in search field.");
        driver.findElement(searchField).sendKeys(txtToSearch);
        ////return this;
    }

    public void clearSearchFieldInStorePage(){
        driver.findElement(searchField).clear();
    }

    public StorePage clickSearchButton(){

        driver.findElement(searchBtn).click();
        return this;
    }

    public void verifNoMatchingProdFoundInfo(){
        Assert.assertTrue(driver.findElement(searchInfo).isDisplayed(),"No product match Info is expected.");
    }

    public void verifySearchResultHeader(){
        Assert.assertTrue(driver.findElement(searchResultHeader).isDisplayed(),"Search results: expected.");
    }

    ///How to handle Dynamic UI Element
    private By getAddToCartButtonElement(String productName){
        return By.cssSelector("'a[aria-label='Add'"+productName+"'to your cart");
    }



    public CartPage clickViewCart(){
        driver.findElement(viewCartLink).click();
        return new CartPage(driver);
    }

    public void clickAddToCartButton(){
        driver.findElement(addToCart).click();
    }


}
