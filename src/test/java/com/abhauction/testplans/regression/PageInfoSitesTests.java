package com.abhauction.testplans.regression;

import com.abhauction.pageobjects.*;
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

public class PageInfoSitesTests {
    private static final WebDriver driver = new ChromeDriver();

    //initializing Page Objects
    public static HomePage homePage = new HomePage(driver);
    public static AboutUsPage aboutUsPage = new AboutUsPage(driver);
    public static TermsPage termsPage = new TermsPage(driver);
    public static PolicyPage policyPage = new PolicyPage(driver);

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

    @Test(testName = "AboutUsPageAssert")
    public static void aboutUsAssertion() {
        Assert.assertTrue(homePage.homepageDisplay(), StringResources.error404("Homepage"));
        homePage.goToAboutUs();
        Assert.assertTrue(aboutUsPage.getPageTitle(),StringResources.error404("About us title"));
        Assert.assertTrue(aboutUsPage.getPageImage(),StringResources.error404("Image"));
    }

    @Test(testName = "TermsOfConditionsAssert")
    public static void termsAssertion() {
        Assert.assertTrue(homePage.homepageDisplay(), StringResources.error404("Homepage"));
        homePage.goToTerms();
        Assert.assertTrue(termsPage.getPageTitle(),StringResources.error404("Terms of service page title"));
        Assert.assertTrue(termsPage.linkDisplayed(),StringResources.error404("Generator link"));
        termsPage.goToGenerator();
        Assert.assertTrue(termsPage.generatorTitle(),StringResources.error404("Wrong page"));
    }

    @Test(testName = "PrivacyPolicyAssert")
    public static void policyAssertion() {
        Assert.assertTrue(homePage.homepageDisplay(), StringResources.error404("Homepage"));
        homePage.goToPolicy();
        Assert.assertTrue(policyPage.pageTitleDisplayed(),StringResources.error404("Privacy Policy page title"));
    }

    @AfterSuite
    public static void exit(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
