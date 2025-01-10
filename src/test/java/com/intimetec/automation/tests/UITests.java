package com.intimetec.automation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.intimetec.automation.helpers.WebDriverHelper;
import com.intimetec.automation.pages.CareersPage;
import com.intimetec.automation.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

public class UITests {

    private static WebDriver driver;
    private static HomePage homePage;
    private static CareersPage careersPage;
    ExtentReports extentReports = new ExtentReports();
    ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/extent-reports/report.html");

    @BeforeTest
    public void setupExtentReports() {
        extentReports.attachReporter(sparkReporter);
    }

    @BeforeTest
    public void setup() {

        String browser = "chrome";
        driver = WebDriverHelper.getDriver(browser);
        driver.get("https://www.intimetec.com/");
        homePage = new HomePage(driver);

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
        homePage.handleCookieBanner();

        test.info("Navigating to 'Careers' page.");
        homePage.clickOnCareers();

        String parentWindow = driver.getWindowHandle();
        test.info("Storing the parent window handle: " + parentWindow);

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle);
                test.info("Switched to new tab with handle: " + handle);
                break;
            }
        }

        careersPage = new CareersPage(driver);

        test.info("Clicking on 'India Careers'.");
        careersPage.clickOnIndiaCareers();

        test.info("Clicking on 'Australia Language Selector'.");
        careersPage.clickOnAusLanguageSelector();

        test.info("Navigating to 'Careers' page again.");
        homePage.clickOnCareers();

        Set<String> allHandles = driver.getWindowHandles();
        for (String handle : allHandles) {
            driver.switchTo().window(handle);
            if (!handle.equals(parentWindow) && driver.getTitle() != null) {
                test.info("Switched to new tab with handle: " + handle);
                break;
            }
        }

        test.info("Clicking on 'India Careers' again.");
        careersPage.clickOnIndiaCareers();

        test.info("Clicking on 'Korea English Language'.");
        careersPage.clickOnKoreaEnglishLanguage();

        test.info("Navigating to 'Careers' page one more time.");
        homePage.clickOnCareers();

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
