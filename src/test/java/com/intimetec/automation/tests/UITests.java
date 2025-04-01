package com.intimetec.automation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.intimetec.automation.pages.CareersPageActions;
import com.intimetec.automation.pages.HomePageActions;
import com.intimetec.automation.utils.ConfigManager;
import com.intimetec.automation.utils.TestSetupManager;
import com.intimetec.automation.constants.TestGroups;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UITests extends BaseTest {

    private ExtentReports extentReports;
    public void setup() {
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/report.html");
        extentReports.attachReporter(sparkReporter);
        TestSetupManager.initializeEnvironment(driver, extentReports);
    }

    @Test(groups = {TestGroups.Smoke.COOKIE})
    public void testCookieBanner() {
        extentReports.createTest("Cookie Banner Test");
        boolean isBannerDismissed = new HomePageActions(driver)
                .handleCookieBanner()
                .isCookieBannerDismissed();

        Assert.assertTrue(isBannerDismissed, "Cookie banner should be dismissed");
    }

    @Test(groups = {TestGroups.Smoke.NAVIGATION})
    public void testNavigationToCareers() {
        extentReports.createTest("Careers Navigation Test");
        String currentUrl = new HomePageActions(driver)
                .clickOnCareers()
                .storeParentWindowHandle()
                .switchToNewWindow()
                .getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("/careers"),
                "URL should contain careers path");
    }

    @Test(groups = {TestGroups.Regression.CAREERS_INDIA})
    public void testIndiaCareersNavigation() {
        extentReports.createTest("India Careers Navigation Test");
        String currentUrl = new CareersPageActions(driver)
                .clickOnIndiaCareers()
                .getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("/india"),
                "URL should contain india path");
    }

    @Test(groups = {TestGroups.Regression.CAREERS_AUSTRALIA})
    public void testAustraliaLanguageSelection() {
        extentReports.createTest("Australia Language Selection Test");
        String selectedLanguage = new CareersPageActions(driver)
                .clickOnLanguageSelector()
                .selectAustraliaEnglish()
                .getSelectedLanguage();

        Assert.assertEquals(selectedLanguage, "Australia (English)",
                "Language should be set to Australia English");
    }

    @Test(groups = {TestGroups.Regression.CAREERS_KOREA})
    public void testKoreaLanguageSelection() {
        extentReports.createTest("Korea Language Selection Test");
        String selectedLanguage = new CareersPageActions(driver)
                .clickOnLanguageSelector()
                .selectKoreaEnglish()
                .getSelectedLanguage();

        Assert.assertEquals(selectedLanguage, "Korea (Korean)",
                "Language should be set to Korea Korean");
    }
}