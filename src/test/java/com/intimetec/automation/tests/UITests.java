package com.intimetec.automation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.intimetec.automation.pages.CareersPageActions;
import com.intimetec.automation.pages.HomePageActions;
import com.intimetec.automation.utils.ConfigManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UITests extends BaseTest {

    private ExtentReports extentReports;
    private ExtentTest test;
    private HomePageActions homePageActions;
    private CareersPageActions careersPageActions;

    @BeforeTest
    public void setup() {

        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/report.html");
        extentReports.attachReporter(sparkReporter);

        String environment = System.getProperty("env", "prod");
        String baseUrl = ConfigManager.getBaseUrl(environment);

        homePageActions = new HomePageActions(driver);
        careersPageActions = new CareersPageActions(driver);

        test = extentReports.createTest("Setup");
        test.info("WebDriver initialized.");

        driver.get(baseUrl);
        test.info("Navigated to " + baseUrl);
        test.pass("Setup completed successfully.");
    }

    @Test(groups = {"homepage", "smoke"})
    public void testCookieBanner() {
        test = extentReports.createTest("Cookie Banner Test");
        homePageActions
                .handleCookieBanner();
        test.pass("Successfully handled cookie banner");
    }

    @Test(groups = {"homepage", "regression"})
    public void testNavigationToCareers() {
        test = extentReports.createTest("Careers Navigation Test");
        homePageActions
                .clickOnCareers()
                .storeParentWindowHandle()
                .switchToNewWindow();
        test.pass("Successfully navigated to careers page");
    }

    @Test(groups = {"careersIndia", "regression"})
    public void testIndiaCareersNavigation() {
        test = extentReports.createTest("India Careers Navigation Test");
        careersPageActions
                .clickOnIndiaCareers();
        test.pass("Successfully navigated to India careers");
    }

    @Test(groups = {"careersAustralia", "regression"})
    public void testAustraliaLanguageSelection() {
        test = extentReports.createTest("Australia Language Selection Test");
        careersPageActions
                .clickOnLanguageSelector()
                .selectAustraliaEnglish();
        test.pass("Successfully changed language to Australia English");
    }

    @Test(groups = {"careersKorea", "regression"})
    public void testKoreaLanguageSelection() {
        test = extentReports.createTest("Korea Language Selection Test");
        careersPageActions
                .clickOnLanguageSelector()
                .selectKoreaEnglish();
        test.pass("Successfully changed language to Korea English");
    }

    @AfterTest
    public void tearDown() {
        if (extentReports != null) {
            extentReports.flush();
        }
        if (driver != null) {
            driver.quit();
        }
    }
}