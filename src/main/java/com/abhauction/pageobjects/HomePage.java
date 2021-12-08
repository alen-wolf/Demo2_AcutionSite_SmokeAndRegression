package com.abhauction.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject{

    @FindBy(css = "#root > div > div.navbar-body > div.login-and-signup > ul > li:nth-child(3) > a")
    private WebElement signIn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goToAccountCreation(){this.signIn.click();}

}
