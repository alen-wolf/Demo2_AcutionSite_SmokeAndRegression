package com.abhauction.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TermsPage extends PageObject{

    @FindBy(css = "#root > div > div.terms-body > h1")
    private WebElement title;

    @FindBy(css = "#root > div > div.terms-body > p:nth-child(4) > a")
    private WebElement linkToGenerator;

    @FindBy(css = "#intro > h1")
    private WebElement generatorTitle;

    public TermsPage(WebDriver driver) {
        super(driver);
    }

    public boolean getPageTitle(){return this.title.isDisplayed();}

    public boolean linkDisplayed(){return this.linkToGenerator.isDisplayed();}

    public void goToGenerator(){this.linkToGenerator.click();}

    public boolean generatorTitle(){return this.generatorTitle.isDisplayed();}
}
