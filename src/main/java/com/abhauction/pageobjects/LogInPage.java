package com.abhauction.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LogInPage extends PageObject{

    @FindBy(id = "login-email")
    private WebElement emailInput;

    @FindBy(id = "login-password")
    private WebElement passwordInput;

    @FindBy(id = "login-remember")
    private WebElement rememberMeButton;

    @FindBy(css = "#root > div > form > button")
    private WebElement logInButton;

    @FindBy(css = "#root > div > form > div")
    private WebElement failedToLogIn;

    @FindBy(className = "navbar-username")
    private WebElement loggedInUser;

    @FindBy(css = "#root > div > div.navbar-body > div.login-and-signup > div > button")
    private WebElement logOut;

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public String getEmailInput(){return this.emailInput.getAttribute("value");}

    public String getPasswordInput(){return this.passwordInput.getAttribute("value");}

    public void rememberUser(){this.rememberMeButton.click();}

    public void logInUser(){this.logInButton.click();}

    public boolean userFailedToLogIn(){return this.failedToLogIn.isDisplayed();}

    public boolean userIsLoggedIn(){return this.loggedInUser.isDisplayed();}

    public void logOutUser(){this.logOut.click();}

    public void fillInlogInForm(String email, String password){
        this.emailInput.sendKeys(email);
        this.passwordInput.sendKeys(password);
    }

    public boolean loginFormDisplay(){
        return this.emailInput.isDisplayed() && this.passwordInput.isDisplayed() && this.logInButton.isDisplayed();
    }
}
