package com.abhauction.testplans.regression;

import com.abhauction.pageobjects.HomePage;
import com.abhauction.pageobjects.RegistrationPage;
import com.abhauction.utilities.Utils;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class RegistrationFormTests {
    private static final WebDriver driver = new ChromeDriver();

    //initializing Page Objects
    public static HomePage homePage = new HomePage(driver);
    public static RegistrationPage registrationPage = new RegistrationPage(driver);


    //initializing SELENIUM Actions
    public static Actions actions = new Actions(driver);


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

    @Test(testName = "Empty Input form submission")
    public static void emptyInput() {
        homePage.goToAccountCreation();
        registrationPage.registerUser();
        Assert.assertTrue(registrationPage.getPageTitle(),"Registration page not found!");
    }

    @Test(testName = "Valid Input form submission")
    public static void validInput() {
        homePage.goToAccountCreation();
    }

    @AfterSuite
    public static void exit(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
