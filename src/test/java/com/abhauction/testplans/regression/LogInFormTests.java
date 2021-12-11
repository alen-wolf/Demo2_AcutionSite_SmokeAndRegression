package com.abhauction.testplans.regression;

import com.abhauction.pageobjects.HomePage;
import com.abhauction.pageobjects.LogInPage;
import com.abhauction.pageobjects.RegistrationPage;
import com.abhauction.utilities.StringResources;
import com.abhauction.utilities.UserAccounts;
import com.abhauction.utilities.Utils;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LogInFormTests {
    private static final WebDriver driver = new ChromeDriver();

    //initializing Page Objects
    public static HomePage homePage = new HomePage(driver);
    public static LogInPage logInPage = new LogInPage(driver);

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

    @Test(testName = "Log in valid user")
    public static void validUserInput() {
        homePage.goToLogIn();
        logInPage.fillInlogInForm("alenn","12345");
        logInPage.logInUser();
        Assert.assertTrue(logInPage.userIsLoggedIn(),StringResources.error404("Logged In navbar name"));
    }

    @Test(testName = "Log in invalid user")
    public static void invalidUserInput() {
        homePage.goToLogIn();
        logInPage.fillInlogInForm(testUser.getEmailAndPassword(),testUser.getEmailAndPassword());
        logInPage.logInUser();
        Assert.assertTrue(logInPage.userFailedToLogIn(),StringResources.formInput("non existing user logged in!"));
    }

    @Test(testName = "Log in wrong password")
    public static void invalidPasswordInput() {
        homePage.goToLogIn();
        logInPage.fillInlogInForm("alenn","1111");
        logInPage.logInUser();
        Assert.assertTrue(logInPage.userFailedToLogIn(),StringResources.formInput("logged in with wrong password!"));
    }

    @Test(testName = "Remember me button and log Out")
    public static void rememberLoginInput() throws InterruptedException {
        homePage.goToLogIn();
        logInPage.fillInlogInForm("alenn","12345");
        logInPage.logInUser();
        logInPage.rememberUser();
        Assert.assertTrue(logInPage.userIsLoggedIn(),StringResources.error404("Logged In navbar name"));
        logInPage.logOutUser();
        Thread.sleep(3000);
        Assert.assertTrue(homePage.userLoggedOut(),StringResources.loggedIn);
        homePage.goToLogIn();
        Assert.assertEquals(logInPage.getEmailInput(),"alenn@mail.com",StringResources.formInput("wrong email!"));
        Assert.assertEquals(logInPage.getPasswordInput(),"12345",StringResources.formInput("wrong password!"));
        logInPage.logInUser();
        Assert.assertTrue(logInPage.userIsLoggedIn(),StringResources.error404("Logged In navbar name"));
    }

    @AfterSuite
    public static void exit(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
