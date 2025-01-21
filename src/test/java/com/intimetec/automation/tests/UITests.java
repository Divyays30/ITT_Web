package com.intimetec.automation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.intimetec.automation.helpers.WebDriverHelper;
import com.intimetec.automation.pages.CareersPageActions;
import com.intimetec.automation.pages.HomePageActions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

public class UITests {

    private static WebDriver driver;
    private static HomePageActions homePageActions;
    private static CareersPageActions careersPageActions;
    ExtentReports extentReports = new ExtentReports();
    ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/report.html");

    @BeforeTest
    public void setupExtentReports() {
        extentReports.attachReporter(sparkReporter);
    }

    @BeforeTest
    public void setup() {

        driver = WebDriverHelper.getDriver("chrome");
        driver.get("https://www.intimetec.com/");
        homePageActions = new HomePageActions(driver);
        careersPageActions = new CareersPageActions(driver);

        if (extentReports != null) {
            ExtentTest test = extentReports.createTest("Setup");
            test.info("WebDriver initialized.");
            test.info("Navigated to https://www.intimetec.com/");
            test.pass("Setup completed successfully.");
        }
    }

    @Test
    public void testWebsiteAutomation() {
        ExtentTest test = extentReports.createTest("testWebsiteAutomation");


        test.info("Handling cookie banner.");
        homePageActions.handleCookieBanner();

        test.info("Navigating to 'Careers' page.");
        homePageActions.clickOnCareers();

        String parentWindow = driver.getWindowHandle();
        test.info("Storing the parent window handle: " + parentWindow);


        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle);
                test.info("Switched to new tab with handle: " + handle);
                break;
            }
        }


        test.info("Clicking on 'India Careers'.");
        careersPageActions.clickOnIndiaCareers();


        test.info("Clicking on 'Australia Language Selector'.");
        careersPageActions.clickOnAustraliaCareers();

        test.info("Navigating to 'Careers' page again.");
        homePageActions.clickOnCareers();

        Set<String> allHandles = driver.getWindowHandles();
        for (String handle : allHandles) {
            driver.switchTo().window(handle);
            if (!handle.equals(parentWindow) && driver.getTitle() != null) {
                test.info("Switched to new tab with handle: " + handle);
                break;
            }
        }


        test.info("Clicking on 'India Careers' again.");
        careersPageActions.clickOnIndiaCareers();


        test.info("Clicking on 'Korea English Language'.");
        careersPageActions.clickOnKoreaCareers();


        test.info("Navigating to 'Careers' page one more time.");
        homePageActions.clickOnCareers();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle);
                test.info("Switched to new tab with handle: " + handle);
                break;
            }
        }

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