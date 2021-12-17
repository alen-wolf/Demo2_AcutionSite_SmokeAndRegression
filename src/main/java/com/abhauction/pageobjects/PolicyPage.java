package com.abhauction.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PolicyPage extends PageObject{

    @FindBy(css = "#root > div > div.privacy-body > h1")
    private WebElement pageTitle;

    public PolicyPage(WebDriver driver) {
        super(driver);
    }

    public boolean pageTitleDisplayed(){return this.pageTitle.isDisplayed();}

}
