package com.abhauction.testplans.regression;

import com.abhauction.pageobjects.HomePage;
import com.abhauction.pageobjects.LogInPage;
import com.abhauction.utilities.StringResources;
import com.abhauction.utilities.Utils;
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
        Assert.assertTrue(homePage.homepageDisplay(),StringResources.error404("Homepage"));
        homePage.goToLogIn();
        Assert.assertTrue(logInPage.loginFormDisplay(),StringResources.error404("LogIn form"));
        logInPage.fillInlogInForm(StringResources.existingEmail,StringResources.standardPass);
        logInPage.logInUser();
        Assert.assertTrue(logInPage.userIsLoggedIn(),StringResources.error404("Navbar username"));
        logInPage.logOutUser();
    }

    @Test(testName = "Log in invalid user")
    public static void invalidUserInput() {
        Assert.assertTrue(homePage.homepageDisplay(),StringResources.error404("Homepage"));
        homePage.goToLogIn();
        Assert.assertTrue(logInPage.loginFormDisplay(),StringResources.error404("LogIn form"));
        logInPage.fillInlogInForm(StringResources.unknownEmail,StringResources.standardPass);
        logInPage.logInUser();
        Assert.assertTrue(logInPage.userFailedToLogIn(),StringResources.formInput(StringResources.invalidLogIn));
    }

    @Test(testName = "Log in wrong password")
    public static void invalidPasswordInput() {
        Assert.assertTrue(homePage.homepageDisplay(),StringResources.error404("Homepage"));
        homePage.goToLogIn();
        Assert.assertTrue(logInPage.loginFormDisplay(),StringResources.error404("LogIn form"));
        logInPage.fillInlogInForm(StringResources.existingEmail,StringResources.badPass);
        logInPage.logInUser();
        Assert.assertTrue(logInPage.userFailedToLogIn(),StringResources.formInput("wrong password accepted!"));
    }

    @Test(testName = "Remember me button and log Out")
    public static void rememberLoginInput() {
        Assert.assertTrue(homePage.homepageDisplay(),StringResources.error404("Homepage"));
        homePage.goToLogIn();
        Assert.assertTrue(logInPage.loginFormDisplay(),StringResources.error404("LogIn form"));
        logInPage.fillInlogInForm(StringResources.existingEmail,StringResources.standardPass);
        logInPage.logInUser();
        logInPage.rememberUser();
        Assert.assertTrue(logInPage.userIsLoggedIn(),StringResources.error404("Navbar username"));
        logInPage.logOutUser();
        Assert.assertTrue(homePage.userLoggedOut(),StringResources.loggedIn);
        homePage.goToLogIn();
        Assert.assertEquals(logInPage.getEmailInput(),StringResources.existingEmail,StringResources.formInput("unmatching email!"));
        Assert.assertEquals(logInPage.getPasswordInput(),StringResources.standardPass,StringResources.formInput("unmatching password!"));
        logInPage.logInUser();
        Assert.assertTrue(logInPage.userIsLoggedIn(),StringResources.error404("Navbar username"));
    }

    @AfterSuite
    public static void exit(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
