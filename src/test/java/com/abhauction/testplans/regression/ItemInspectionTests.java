package com.abhauction.testplans.regression;

import com.abhauction.pageobjects.HomePage;
import com.abhauction.pageobjects.ItemInspectionPage;
import com.abhauction.pageobjects.LogInPage;
import com.abhauction.pageobjects.ShopPage;
import com.abhauction.utilities.StringResources;
import com.abhauction.utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class ItemInspectionTests {
    private static final WebDriver driver = new ChromeDriver();

    //initializing Page Objects
    public static HomePage homePage = new HomePage(driver);
    public static ShopPage shopPage = new ShopPage(driver);
    public static ItemInspectionPage itemInspectionPage = new ItemInspectionPage(driver);
    public static LogInPage logInPage = new LogInPage(driver);

    @BeforeSuite
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @BeforeSuite
    public void set(){
        driver.manage().window().maximize();
        driver.get(Utils.HOME_URL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @BeforeTest
    public void setUp(){
        //Log in user
        Assert.assertTrue(homePage.homepageDisplay(),StringResources.error404("Homepage"));
        homePage.goToLogIn();
        Assert.assertTrue(logInPage.loginFormDisplay(),StringResources.error404("LogIn form"));
        logInPage.fillInlogInForm(StringResources.existingEmail,StringResources.standardPass);
        logInPage.logInUser();
        Assert.assertTrue(logInPage.userIsLoggedIn(),StringResources.error404("Navbar username"));
    }


    @Test(testName = "Check all catalog images for duplicates")
    public static void imgDuplicateCheck() throws InterruptedException {
        Assert.assertTrue(homePage.homepageDisplay(), StringResources.error404("Homepage"));
        homePage.goToShopPage();
        for(int i=0; i<shopPage.checkCatalogSize(); i++) {
            shopPage.goToItemInspectionPageId(i);
            Thread.sleep(1000);
            Assert.assertFalse(shopPage.checkGalleryForDuplicates(), StringResources.invalidElement("Duplicate images!"));
            homePage.goToShopPage();
        }
    }

    @Test(testName = "Place a valid bid")
    public static void placeValidBid() throws InterruptedException {
        homePage.goToShopPage();
        for(int i=0; i<shopPage.checkCatalogSize(); i++) {
            shopPage.goToItemInspectionPageId(i);
            Thread.sleep(1000);
            int numberOfBids = itemInspectionPage.getNumberOfBids();
            int currentHighest = itemInspectionPage.getHighestBid();
            itemInspectionPage.placeValidBid();
            Assert.assertTrue(numberOfBids < itemInspectionPage.getNumberOfBids(),StringResources.formInput("Number of bids!"));
            Assert.assertTrue(currentHighest < itemInspectionPage.getHighestBid(),StringResources.formInput("Highest bid!"));
            homePage.goToShopPage();
        }
    }

    @Test(testName = "Place a empty bid")
    public static void placeEmptyBid() throws InterruptedException {
        homePage.goToShopPage();
        for(int i=0; i<shopPage.checkCatalogSize(); i++) {
            shopPage.goToItemInspectionPageId(i);
            Thread.sleep(1000);
            int numberOfBids = itemInspectionPage.getNumberOfBids();
            int currentHighest = itemInspectionPage.getHighestBid();
            itemInspectionPage.submitBid();
            Assert.assertTrue(itemInspectionPage.badInputWarning(),StringResources.error404("Bad bid Input!"));
            Assert.assertEquals(itemInspectionPage.getNumberOfBids(), numberOfBids, StringResources.formInput("Number of bids!"));
            Assert.assertEquals(itemInspectionPage.getHighestBid(), currentHighest, StringResources.formInput("Highest bid!"));
            homePage.goToShopPage();
        }
    }

    @Test(testName = "Place a low bid")
    public static void placeLowBid() throws InterruptedException {
        homePage.goToShopPage();
        for(int i=0; i<shopPage.checkCatalogSize(); i++) {
            shopPage.goToItemInspectionPageId(i);
            Thread.sleep(1000);
            int numberOfBids = itemInspectionPage.getNumberOfBids();
            int currentHighest = itemInspectionPage.getHighestBid();
            itemInspectionPage.placeInvalidBid();
            Assert.assertTrue(itemInspectionPage.badInputWarning(),StringResources.error404("Bad bid Input!"));
            Assert.assertEquals(itemInspectionPage.getNumberOfBids(), numberOfBids, StringResources.formInput("Number of bids!"));
            Assert.assertEquals(itemInspectionPage.getHighestBid(), currentHighest, StringResources.formInput("Highest bid!"));
            homePage.goToShopPage();
        }
    }

    @AfterTest
    public static void logOutUser(){
        logInPage.logOutUser();
    }

    @AfterSuite
    public static void exit(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
