package com.intimetec.automation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.intimetec.automation.pages.CareersPageActions;
import com.intimetec.automation.pages.HomePageActions;
import com.intimetec.automation.utils.ConfigManager;
import com.intimetec.automation.constants.TestGroups;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UITests extends BaseTest {

    private ExtentReports extentReports;
    private ExtentTest test;
    private HomePageActions homePageActions;
    private CareersPageActions careersPageActions;

    public void setup() {
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/report.html");
        extentReports.attachReporter(sparkReporter);

        String baseUrl = ConfigManager.getBaseUrl();
        test = extentReports.createTest("Setup");
        test.info("WebDriver initialized.");

        driver.get(baseUrl);
        test.info("Navigated to " + baseUrl);
        test.pass("Setup completed successfully.");
    }

    @Test(groups = {TestGroups.Smoke.COOKIE})
    public void testCookieBanner() {
        boolean isCookieBannerHandled = homePageActions
                .handleCookieBanner()
                .isCookieBannerHandled();

    }

    @Test(groups = {TestGroups.Smoke.NAVIGATION})
    public void testNavigationToCareers() {
        String currentUrl = homePageActions
                .clickOnCareers()
                .storeParentWindowHandle()
                .switchToNewWindow()
                .getCurrentUrl();
    }

    @Test(groups = {TestGroups.Regression.CAREERS_INDIA})
    public void testIndiaCareersNavigation() {
        String currentUrl = careersPageActions
                .clickOnIndiaCareers()
                .getCurrentUrl();
    }

    @Test(groups = {TestGroups.Regression.CAREERS_AUSTRALIA})
    public void testAustraliaLanguageSelection() {
        careersPageActions
                .clickOnLanguageSelector()
                .selectAustraliaEnglish();
    }

    @Test(groups = {TestGroups.Regression.CAREERS_KOREA})
    public void testKoreaLanguageSelection() {
        careersPageActions
                .clickOnLanguageSelector()
                .selectKoreaEnglish();
    }
}