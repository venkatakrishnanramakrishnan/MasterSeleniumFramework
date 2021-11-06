package org.selenium.pom.base;

import org.openqa.selenium.WebDriver;

public class BasePage {
    ////This BasePage is common to all our objects
    protected WebDriver driver;
    public BasePage(WebDriver driver){ ////BasePage is a constructor, so no need for a return type
        this.driver = driver; ////here this.driver refers to the instance variable at line# 7,
        /// 'driver' on the R.H.S refers to the
        /////driver coming from WebDriver
    }
}
