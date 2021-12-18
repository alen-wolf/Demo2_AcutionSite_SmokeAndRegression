package com.abhauction.testplans.regression;

import com.abhauction.pageobjects.HomePage;
import com.abhauction.pageobjects.RegistrationPage;
import com.abhauction.utilities.StringResources;
import com.abhauction.utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class RegistrationFormTests {
    private static final WebDriver driver = new ChromeDriver();

    //initializing Page Objects
    public static HomePage homePage = new HomePage(driver);
    public static RegistrationPage registrationPage = new RegistrationPage(driver);

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
        Assert.assertTrue(homePage.homepageDisplay(),StringResources.error404("Homepage"));
        homePage.goToAccountCreation();
        Assert.assertTrue(registrationPage.registrationFormDisplay(),StringResources.error404("Registration form"));
        registrationPage.registerUser();
        Assert.assertTrue(registrationPage.getPageTitle(), StringResources.error404("Registration page"));
    }

    @Test(testName = "Valid Input form submission")
    public static void validInput() {
        Assert.assertTrue(homePage.homepageDisplay(),StringResources.error404("Homepage"));
        homePage.goToAccountCreation();
        Assert.assertTrue(registrationPage.registrationFormDisplay(),StringResources.error404("Registration form"));
        registrationPage.fillInRegistrationform(StringResources.validName,StringResources.validName,StringResources.randomUserEmail(),StringResources.standardPass);
        registrationPage.registerUser();
        Assert.assertTrue(registrationPage.userIsRegistered(), StringResources.error404("User registered"));
    }

    @Test(testName = "Invalid name input form submission")
    public static void invalidNameInput() {
        Assert.assertTrue(homePage.homepageDisplay(),StringResources.error404("Homepage"));
        homePage.goToAccountCreation();
        Assert.assertTrue(registrationPage.registrationFormDisplay(),StringResources.error404("Registration form"));
        registrationPage.fillInRegistrationform(StringResources.invalidName,StringResources.invalidName,StringResources.randomUserEmail(),StringResources.standardPass);
        registrationPage.registerUser();
        Assert.assertFalse(registrationPage.userIsRegistered(), StringResources.formInput("registered invalid name!"));
    }

    @Test(testName = "Invalid email input form submission")
    public static void invalidEmailInput() {
        Assert.assertTrue(homePage.homepageDisplay(),StringResources.error404("Homepage"));
        homePage.goToAccountCreation();
        Assert.assertTrue(registrationPage.registrationFormDisplay(),StringResources.error404("Registration form"));
        registrationPage.fillInRegistrationform(StringResources.validName,StringResources.validName,StringResources.invalidEmail,StringResources.standardPass);
        registrationPage.registerUser();
        Assert.assertFalse(registrationPage.userIsRegistered(), StringResources.formInput("registered invalid email!"));
    }

    @Test(testName = "Register existing user")
    public static void existingUserInput() {
        Assert.assertTrue(homePage.homepageDisplay(),StringResources.error404("Homepage"));
        homePage.goToAccountCreation();
        Assert.assertTrue(registrationPage.registrationFormDisplay(),StringResources.error404("Registration form"));
        registrationPage.fillInRegistrationform(StringResources.validName,StringResources.validName,StringResources.existingEmail,StringResources.standardPass);
        registrationPage.registerUser();
        Assert.assertTrue(registrationPage.userFailedToRegister(), StringResources.formInput("registered existing user!"));
    }

    @AfterSuite
    public static void exit(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
