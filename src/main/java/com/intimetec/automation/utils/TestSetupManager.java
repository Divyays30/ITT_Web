package com.intimetec.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

public class TestSetupManager {

    public static void initializeEnvironment(WebDriver driver, ExtentReports extentReports) {
        String baseUrl = ConfigManager.getBaseUrl();
        ExtentTest test = extentReports.createTest("Setup");
        test.info("WebDriver initialized.");

        driver.get(baseUrl);
        test.info("Navigated to " + baseUrl);
        test.pass("Setup completed successfully.");
    }
}