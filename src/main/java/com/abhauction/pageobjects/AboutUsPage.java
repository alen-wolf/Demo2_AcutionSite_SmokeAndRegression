package com.abhauction.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutUsPage extends PageObject{

    @FindBy(css ="#root > div > div.about-body > div.about-text > h1")
    private WebElement title;

    @FindBy(css = "#root > div > div.about-body > div.about-images > img:nth-child(1)")
    private WebElement img;

    public AboutUsPage(WebDriver driver) {
        super(driver);
    }

    public boolean getPageTitle(){return this.title.isDisplayed();}

    public boolean getPageImage(){return this.img.isDisplayed();}

}
