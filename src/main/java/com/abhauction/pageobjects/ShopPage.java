package com.abhauction.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

public class ShopPage extends PageObject{

    @FindBy(css = "#search-bar")
    private WebElement searchBar;

    @FindBy(css = "#root > div > div.navbar-container > div.search-bar > button")
    private WebElement searchSubmit;

    @FindBy(css = "#root > div > div.product-list > div.active-filters > div:nth-child(1) > h2")
    private WebElement getFashionFilterList;

    @FindBy(css = "#root > div > div.filters-and-categories > div.product-categories > div:nth-child(6) > h2")
    private WebElement sportswearFilter;

    @FindBy(css = "#root > div > div.filters-and-categories > div.product-categories > div:nth-child(2) > h2")
    private WebElement fashionFilter;

    @FindBy(css = "#root > div > div.product-list > div.active-filters > div:nth-child(3) > h2")
    private WebElement getSportswearFilterList;

    @FindBy(css = "#root > div > div.filters-and-categories > div.price-filter > input:nth-child(2)")
    private WebElement priceMinimumBox;

    @FindBy(css = "#root > div > div.filters-and-categories > div.price-filter > input:nth-child(3)")
    private WebElement priceMaximumBox;

    @FindBy(css = "#root > div > div.product-list > div.active-filters > div > h2")
    private WebElement priceRangeFilterList;

    @FindBy(css = "#root > div > div.product-list > div.sort-and-view-select > div.sorting-selector > select")
    private WebElement sortByFilter;

    @FindBy(css = "#root > div > div.filters-and-categories > div.product-categories > div:nth-child(10) > h2")
    private WebElement computerFilter;

    @FindBy(css = "#root > div > div.filters-and-categories > div.product-categories > div:nth-child(6) > button")
    private WebElement sportswearFilterExpandButton;

    @FindBy(css = "#Sportswear\\/Female")
    private WebElement sportswearFemaleSubcategory;

    @FindBy(css = "#root > div > div.product-list > div.active-filters > div:nth-child(2) > h2")
    private WebElement femaleSportswearSubcategoryList;

    @FindBy(className = "item-card")
    private List<WebElement> catalog;

    @FindBy(css = "#root > div > div.item-main > div.item-images > div")
    private WebElement imgGallery;

    public ShopPage(WebDriver driver) {
        super(driver);
    }

    public void searchItem(String input){
        this.searchBar.sendKeys(input);
        this.searchSubmit.click();
    }

    public boolean fashionFilterDisplay(){return this.getFashionFilterList.isDisplayed();}

    public void addSportswearFilter(){this.sportswearFilter.click();}

    public void addFashionFilter(){this.fashionFilter.click();}

    public boolean sportswearFilterDisplay(){return this.getSportswearFilterList.isDisplayed();}

    public void addComputerFilter(){this.computerFilter.click();}

    public void sportswearFilterOpenSubcategoryMenu(){this.sportswearFilterExpandButton.click();}

    public void selectSubcategoryFemaleSportswear(){this.sportswearFemaleSubcategory.click();}

    public boolean femaleSportswearFilterDisplay(){return this.femaleSportswearSubcategoryList.isDisplayed();}

    public boolean searchBarBannerDisplay(){return !driver.findElements(By.className("search-result-banner")).isEmpty();}

    public String getMinimumPrice(){return this.priceMinimumBox.getAttribute("value");}

    public String getMaximumPrice(){return this.priceMaximumBox.getAttribute("value");}

    public String getFilterPrice(){return this.priceRangeFilterList.getText();}

    public void goToItemInspectionPageId(int id){
        String allRoute = "//*[@id=\"root\"]/div/div[4]/div[3]/div[numb]/a/img";
        String itemRoute = allRoute.replace("numb",Integer.toString(id+1));
        this.catalog.get(id).findElement(By.xpath(itemRoute)).click();
    }

    public void selectFilterHigh(){
        Select select = new Select(this.sortByFilter);
        select.selectByIndex(3);
    }

    public void selectFilterLow(){
        Select select = new Select(this.sortByFilter);
        select.selectByIndex(4);
    }

    public int checkCatalogSize(){
        return this.catalog.size();
    }

    public boolean checkPriceHigh(){
        double previousPrice = 0;
        for(int i=0; i<catalog.size(); i++) {
            String path = "//*[@id=\"root\"]/div/div[4]/div[3]/div[numb]/a/h2/span";
            String newPath = path.replace("numb",Integer.toString(i+1));
            String itemPriceDollar = catalog.get(i).findElement(By.xpath(newPath)).getText();
            double itemPrice = Double.parseDouble(itemPriceDollar.replace("$",""));
            if(i > 0 && previousPrice < itemPrice)
                return false;
            previousPrice = itemPrice;
        }
        return true;
    }

    public boolean checkPriceLow(){
        double previousPrice = Double.MAX_VALUE;
        for(int i=0; i<catalog.size(); i++) {
            String path = "//*[@id=\"root\"]/div/div[4]/div[3]/div[numb]/a/h2/span";
            String newPath = path.replace("numb",Integer.toString(i+1));
            String itemPriceDollar = catalog.get(i).findElement(By.xpath(newPath)).getText();
            double itemPrice = Double.parseDouble(itemPriceDollar.replace("$",""));
            if(i > 0 && previousPrice > itemPrice)
                return false;
            previousPrice = itemPrice;
        }
        return true;
    }

    public boolean itemNameContains(String item){
        for(int i=0; i<catalog.size(); i++){
            String path = "//*[@id=\"root\"]/div/div[5]/div[3]/div[numb]/a/h1";
            String newPath = path.replace("numb",Integer.toString(i+1));
            String itemName = catalog.get(i).findElement(By.xpath(newPath)).getText();
            if(!itemName.contains(item))
                return false;
        }
        return true;
    }

    public void moveSliderMaximumAmount() throws AWTException, InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveByOffset(181,793).click().release().perform();
        Robot robot = new Robot();
        for(int i=0; i<230; i++){
            robot.keyPress(KeyEvent.VK_RIGHT);
            Thread.sleep(500);
        }
        actions.moveByOffset(238,0).click().release().perform();
    }

    public void moveSliderMinimumAmount() throws AWTException, InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveByOffset(419,793).click().release().perform();
        Robot robot = new Robot();
        for(int i=0; i<230; i++){
            robot.keyPress(KeyEvent.VK_LEFT);
            Thread.sleep(500);
        }
        actions.moveByOffset(-238,0).click().release().perform();
    }

    public boolean checkGalleryForDuplicates() {
        List<WebElement> gallery = imgGallery.findElements(new By.ByTagName("img"));
        for(int i=0; i < gallery.size()-1; i++){
            String img = gallery.get(i).getAttribute("src");
            for (int j=0; j<gallery.size(); j++){
                if(j > i){
                    String imgCompared = gallery.get(j).getAttribute("src");
                    if(img.equals(imgCompared))
                        return true;
                }
            }
        }
        return false;
    }
}
