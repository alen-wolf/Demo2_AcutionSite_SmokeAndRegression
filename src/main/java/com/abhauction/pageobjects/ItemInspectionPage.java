package com.abhauction.pageobjects;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemInspectionPage extends PageObject {

    @FindBy(css = "#root > div > div.item-main > div.item-info > form > button")
    private WebElement bidButton;

    @FindBy(id = "bid-amount")
    private WebElement bidInput;

    @FindBy(css = "#root > div > div.item-main > div.item-info > div.auction-info > h3:nth-child(1) > span")
    private WebElement highestBid;

    @FindBy(css = "#root > div > div.item-main > div.item-info > div.auction-info > h3:nth-child(2) > span")
    private WebElement numberOfBids;

    @FindBy(css = "#root > div > div.bid-not-placed.bid-message")
    private WebElement warningBadInput;

    public ItemInspectionPage(WebDriver driver) {
        super(driver);
    }

    public void submitBid(){this.bidButton.click();}

    public int getHighestBid(){return Integer.parseInt(this.highestBid.getText().replace("$",""));}

    public int getNumberOfBids(){return Integer.parseInt(this.numberOfBids.getText());}

    public void placeValidBid(){
        this.bidInput.sendKeys(String.valueOf(this.getHighestBid()+2));
        this.submitBid();
    }

    public void placeInvalidBid(){
        this.bidInput.sendKeys("-1");
        this.submitBid();
    }

    public boolean badInputWarning(){return this.warningBadInput.isDisplayed();}
}
