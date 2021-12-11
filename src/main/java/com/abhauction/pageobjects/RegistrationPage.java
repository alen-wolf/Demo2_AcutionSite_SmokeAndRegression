package com.abhauction.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class RegistrationPage extends PageObject{

    @FindBy(css = "#root > div > form > h1")
    private WebElement pageTitle;

    @FindBy(id = "registration-first-name")
    private WebElement nameInput;

    @FindBy(id = "registration-last-name")
    private WebElement lastNameInput;

    @FindBy(id = "registration-email")
    private WebElement emailInput;

    @FindBy(id = "registration-password")
    private WebElement passwordInput;

    @FindBy(css = "#root > div > form > button")
    private WebElement registerButton;

    @FindBy(css = "#root > div > form > div.registration-message.failed")
    private WebElement failedToRegister;

    @FindBy(css = "#root > div > form > div.registration-message.successful")
    private WebElement successfulRegistration;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void registerUser(){this.registerButton.click();}

    public boolean getPageTitle(){return this.pageTitle.isDisplayed();}

    public void fillInRegistrationform(String name, String lastName, String email, String password){
        this.nameInput.sendKeys(name);
        this.lastNameInput.sendKeys(lastName);
        this.emailInput.sendKeys(email+"@mail.com");
        this.passwordInput.sendKeys(password);
    }

    public boolean userIsRegistered(){return this.successfulRegistration.isDisplayed();}

    public boolean userFailedToRegister(){return this.failedToRegister.isDisplayed();}
}
