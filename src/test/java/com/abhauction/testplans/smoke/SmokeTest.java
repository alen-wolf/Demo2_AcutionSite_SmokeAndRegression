package com.abhauction.testplans.smoke;

import com.abhauction.pageobjects.*;
import com.abhauction.utilities.StringResources;
import com.abhauction.utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SmokeTest {
    private static final WebDriver driver = new ChromeDriver();

    //initializing Page Objects
    public static HomePage homePage = new HomePage(driver);
    public static LogInPage logInPage = new LogInPage(driver);
    public static RegistrationPage registrationPage = new RegistrationPage(driver);
    public static ShopPage shopPage = new ShopPage(driver);
    public static ItemInspectionPage itemInspectionPage = new ItemInspectionPage(driver);

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

    @Test(testName = "New user bidding flow")
    public static void validUserInput() throws InterruptedException {
        Assert.assertTrue(homePage.homepageDisplay(), StringResources.error404("Homepage"));
        //Register user
        homePage.goToAccountCreation();
        Assert.assertTrue(registrationPage.registrationFormDisplay(),StringResources.error404("Registration form"));
        registrationPage.fillInRegistrationform(StringResources.validName,StringResources.validName,StringResources.randomMailStatic,StringResources.standardPass);
        registrationPage.registerUser();
        Assert.assertTrue(registrationPage.userIsRegistered(), StringResources.error404("User registered"));
        //Log in User
        homePage.goToLogIn();
        Assert.assertTrue(logInPage.loginFormDisplay(),StringResources.error404("LogIn form"));
        logInPage.fillInlogInForm(StringResources.randomMailStatic,StringResources.standardPass);
        logInPage.logInUser();
        Assert.assertTrue(logInPage.userIsLoggedIn(),StringResources.error404("Navbar username"));
        //Go to shop and filter
        homePage.goToShopPage();
        shopPage.sportswearFilterOpenSubcategoryMenu();
        shopPage.selectSubcategoryFemaleSportswear();
        Assert.assertTrue(shopPage.femaleSportswearFilterDisplay(),StringResources.error404("Sportswear/Female category!"));
        //Sort by price
        shopPage.selectFilterLow();
        Thread.sleep(1000);
        Assert.assertTrue(shopPage.checkPriceLow(),StringResources.sortingError("price low-high!"));
        //Go to item and place bid
        shopPage.goToItemInspectionPageId(1);
        Thread.sleep(1000);
        int numberOfBids = itemInspectionPage.getNumberOfBids();
        int currentHighest = itemInspectionPage.getHighestBid();
        itemInspectionPage.placeValidBid();
        Thread.sleep(1000);
        Assert.assertTrue(numberOfBids < itemInspectionPage.getNumberOfBids(),StringResources.formInput("Number of bids!"));
        Assert.assertTrue(currentHighest < itemInspectionPage.getHighestBid(),StringResources.formInput("Highest bid!"));
        //Search bar jacket
        shopPage.searchItem("jacket");
        Thread.sleep(1000);
        Assert.assertTrue(shopPage.itemNameContains("jacket"),StringResources.error404("item/s jacket!"));
        //Click on jacket and place bid
        shopPage.goToItemSearchPageId(1);
        Thread.sleep(1000);
        numberOfBids = itemInspectionPage.getNumberOfBids();
        currentHighest = itemInspectionPage.getHighestBid();
        itemInspectionPage.placeValidBid();
        Thread.sleep(1000);
        Assert.assertTrue(numberOfBids < itemInspectionPage.getNumberOfBids(),StringResources.formInput("Number of bids!"));
        Assert.assertTrue(currentHighest < itemInspectionPage.getHighestBid(),StringResources.formInput("Highest bid!"));
        //Log out
        logInPage.logOutUser();
        Thread.sleep(1000);
        //TODO Go to my account, remove first bid item and put item up for auction!
    }

    @AfterSuite
    public static void exit(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
