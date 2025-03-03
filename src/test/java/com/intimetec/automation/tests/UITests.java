package com.intimetec.automation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.intimetec.automation.pages.CareersPageActions;
import com.intimetec.automation.pages.HomePageActions;
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
        homePageActions = new HomePageActions(driver);
        careersPageActions = new CareersPageActions(driver);
        test = extentReports.createTest("Setup");
        test.info("WebDriver initialized.");
        test.info("Navigated to https://www.intimetec.com/");
        test.pass("Setup completed successfully.");

        driver.get("https://www.intimetec.com/");
    }

    @Test
    public void testWebsiteAutomation() {

        test = extentReports.createTest("testWebsiteAutomation");
        homePageActions
                .handleCookieBanner()
                .clickOnCareers()
                .storeParentWindowHandle()
                .switchToNewWindow();

        careersPageActions
                .clickOnIndiaCareers()
                .clickOnLanguageSelector()
                .selectAustraliaEnglish()
                .clickOnLanguageSelector()
                .selectKoreaEnglish();

        test.pass("Test executed successfully.");
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