package com.abhauction.testplans.regression;

import com.abhauction.pageobjects.HomePage;
import com.abhauction.pageobjects.RegistrationPage;
import com.abhauction.utilities.StringResources;
import com.abhauction.utilities.UserAccounts;
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

    //create new user
    public static UserAccounts testUser = new UserAccounts(StringResources.randomEmail());


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
        Assert.assertTrue(registrationPage.getPageTitle(), StringResources.error404("Registration title"));
    }

    @Test(testName = "Valid Input form submission")
    public static void validInput() {
        homePage.goToAccountCreation();
        registrationPage.registerUser();
        registrationPage.fillInRegistrationform(testUser.getFirstName(),testUser.getLastName(),testUser.getEmailAndPassword(),testUser.getEmailAndPassword());
        registrationPage.registerUser();
        Assert.assertTrue(registrationPage.userIsRegistered(), StringResources.error404("Successful registration"));
    }

    @Test(testName = "Invalid name input form submission")
    public static void invalidNameInput() {
        homePage.goToAccountCreation();
        registrationPage.registerUser();
        registrationPage.fillInRegistrationform("123&*#A","V122*",testUser.getEmailAndPassword(),testUser.getEmailAndPassword());
        registrationPage.registerUser();
        Assert.assertFalse(registrationPage.userIsRegistered(), StringResources.formInput("registered invalid name!"));
    }

    @Test(testName = "Invalid email input form submission")
    public static void invalidEmailInput() {
        homePage.goToAccountCreation();
        registrationPage.registerUser();
        registrationPage.fillInRegistrationform("Alenn","Vukk*",testUser.getEmailAndPassword()+"#_",testUser.getEmailAndPassword());
        registrationPage.registerUser();
        Assert.assertFalse(registrationPage.userIsRegistered(), StringResources.formInput("registered invalid email!"));
    }

    @Test(testName = "Register existing user")
    public static void existingUserInput() {
        homePage.goToAccountCreation();
        registrationPage.registerUser();
        registrationPage.fillInRegistrationform("Alenn","Vukk*","alenn",testUser.getEmailAndPassword());
        registrationPage.registerUser();
        Assert.assertTrue(registrationPage.userFailedToRegister(), StringResources.formInput("registered existing user!"));
    }

    @AfterSuite
    public static void exit(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
