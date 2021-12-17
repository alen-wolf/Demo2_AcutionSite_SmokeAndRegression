package com.abhauction.testplans.regression;

import com.abhauction.pageobjects.HomePage;
import com.abhauction.pageobjects.ShopPage;
import com.abhauction.utilities.StringResources;
import com.abhauction.utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import sun.security.provider.SHA;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class ShopCatalogTests {
    private static final WebDriver driver = new ChromeDriver();

    //initializing Page Objects
    public static HomePage homePage = new HomePage(driver);
    public static ShopPage shopPage = new ShopPage(driver);

    @BeforeSuite
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @BeforeMethod
    public void setUp(){
        driver.manage().window().maximize();
        driver.get(Utils.HOME_URL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //Filter methods
    @Test(testName = "Shop filter from landing page")
    public static void landingPageFilter() {
        Assert.assertTrue(homePage.homepageDisplay(), StringResources.error404("Homepage"));
        homePage.goToFilterFashion();
        Assert.assertTrue(shopPage.fashionFilterDisplay(),StringResources.error404("Fashion filter"));
    }

    @Test(testName = "Applying multiple filters")
    public static void multipleShopFilters() {
        Assert.assertTrue(homePage.homepageDisplay(), StringResources.error404("Homepage"));
        homePage.goToShopPage();
        shopPage.addFashionFilter();
        shopPage.addSportswearFilter();
        Assert.assertTrue(shopPage.fashionFilterDisplay(),StringResources.error404("Fashion filter"));
        Assert.assertTrue(shopPage.sportswearFilterDisplay(),StringResources.error404("Sportswear filter"));
    }

    @Test(testName = "Applying Subcategories")
    public static void subcategoryFilter() {
        Assert.assertTrue(homePage.homepageDisplay(), StringResources.error404("Homepage"));
        homePage.goToShopPage();
        shopPage.sportswearFilterOpenSubcategoryMenu();
        shopPage.selectSubcategoryFemaleSportswear();
        Assert.assertTrue(shopPage.femaleSportswearFilterDisplay(),StringResources.error404("Sportswear/Female category!"));
    }


    @Test(testName = "Empty category")
    public static void emptyCategoryFilter() throws InterruptedException {
        Assert.assertTrue(homePage.homepageDisplay(), StringResources.error404("Homepage"));
        homePage.goToShopPage();
        shopPage.addComputerFilter();
        Thread.sleep(1000);
        Assert.assertEquals(shopPage.checkCatalogSize(),0,StringResources.invalidElement("Shop Items!"));
    }

    //Search methods
    @Test(testName = "Search valid input jacket ")
    public static void searchValidInput() throws InterruptedException {
        Assert.assertTrue(homePage.homepageDisplay(), StringResources.error404("Homepage"));
        homePage.goToShopPage();
        shopPage.searchItem("jacket");
        Thread.sleep(1000);
        Assert.assertTrue(shopPage.itemNameContains("jacket"),StringResources.error404("item/s jacket!"));
    }

    @Test(testName = "Search invalid input egg")
    public static void searchInvalidInput() throws InterruptedException {
        Assert.assertTrue(homePage.homepageDisplay(), StringResources.error404("Homepage"));
        homePage.goToShopPage();
        shopPage.searchItem("egg");
        Thread.sleep(1000);
        Assert.assertEquals(shopPage.checkCatalogSize(),0,StringResources.invalidElement("Egg Item/s!"));
    }

    @Test(testName = "Search empty input")
    public static void searchEmptyInput() throws InterruptedException {
        Assert.assertTrue(homePage.homepageDisplay(), StringResources.error404("Homepage"));
        homePage.goToShopPage();
        shopPage.searchItem("");
        Thread.sleep(1000);
        Assert.assertFalse(shopPage.searchBarBannerDisplay(),StringResources.invalidElement("Search-result-banner!"));
    }

    //Sort by methods
    @Test(testName = "Sort by price (high-low)")
    public static void sortByPriceHighToLow() throws InterruptedException {
        Assert.assertTrue(homePage.homepageDisplay(), StringResources.error404("Homepage"));
        homePage.goToFilterFashion();
        shopPage.selectFilterHigh();
        Thread.sleep(1000);
        Assert.assertTrue(shopPage.checkPriceHigh(),StringResources.sortingError("price high-low!"));
    }

    @Test(testName = "Sort by price (low-high)")
    public static void sortByPriceLowToHigh() throws InterruptedException {
        Assert.assertTrue(homePage.homepageDisplay(), StringResources.error404("Homepage"));
        homePage.goToFilterFashion();
        shopPage.selectFilterLow();
        Thread.sleep(1000);
        Assert.assertTrue(shopPage.checkPriceLow(),StringResources.sortingError("price low-high!"));
    }

//    //Slider methods
//    @Test(testName = "Price Slider maximum")
//    public static void sliderMaxInput() throws InterruptedException, AWTException {
//        Assert.assertTrue(homePage.homepageDisplay(), StringResources.error404("Homepage"));
//        homePage.goToShopPage();
//        Thread.sleep(2000);
//        shopPage.moveSliderMaximumAmount();
//    }

    @AfterSuite
    public static void exit(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
