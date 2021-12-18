package com.abhauction.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject{

    @FindBy(css = "#root > div > div.navbar-body > div.login-and-signup > ul > li:nth-child(3) > a")
    private WebElement signIn;

    @FindBy(css = "#root > div > div.navbar-body > div.login-and-signup > ul > li:nth-child(1) > a")
    private WebElement logIn;

    @FindBy(css = "#root > div > div.landing-main > div.featured-item > a")
    private WebElement bidNowButton;

    @FindBy(css = "#root > div > div.landing-main > div.categories > ul > li:nth-child(1) > a")
    private WebElement fashionFilter;

    @FindBy(css = "#root > div > div.navbar-container > div.nav-buttons > a:nth-child(2)")
    private WebElement shopButton;

    @FindBy(css = "#root > div > div.footer > div.footer-pages > ul > li:nth-child(1) > a")
    private WebElement aboutUsPageLink;

    @FindBy(css = "#root > div > div.footer > div.footer-pages > ul > li:nth-child(2) > a")
    private WebElement termsAndConditionsPageLink;

    @FindBy(css = "#root > div > div.footer > div.footer-pages > ul > li:nth-child(3) > a")
    private WebElement privacyPolicyPageLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goToAccountCreation(){this.signIn.click();}

    public void goToLogIn(){this.logIn.click();}

    public boolean userLoggedOut(){return this.logIn.isDisplayed();}

    public boolean homepageDisplay(){return  this.bidNowButton.isDisplayed();}

    public void goToFilterFashion(){this.fashionFilter.click();}

    public void goToShopPage(){this.shopButton.click();}

    public void goToAboutUs(){this.aboutUsPageLink.click();}

    public void goToTerms(){this.termsAndConditionsPageLink.click();}

    public void goToPolicy(){this.privacyPolicyPageLink.click();}
}
