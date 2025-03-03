package com.intimetec.automation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.intimetec.automation.helpers.ExtentReportManagerUtils;
import com.intimetec.automation.helpers.WebDriverHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extentReports;
    protected ExtentTest test;
    private static final String BASE_URL = "https://www.intimetec.com/";

    @BeforeTest
    public void setup() {
        extentReports = ExtentReportManagerUtils.createExtentReports();
        test = extentReports.createTest(getClass().getSimpleName());

        try {
            String browserType = System.getProperty("browser", "chrome").toLowerCase();

            switch (browserType) {
                case "chrome":
                    driver = WebDriverHelper.getDriver("chrome");
                    test.info("Initialized Chrome browser");
                    break;
                case "firefox":
                    driver = WebDriverHelper.getDriver("firefox");
                    test.info("Initialized Firefox browser");
                    break;
                case "edge":
                    driver = WebDriverHelper.getDriver("edge");
                    test.info("Initialized Edge browser");
                    break;
                default:
                    throw new IllegalArgumentException("Browser type not supported: " + browserType);
            }

            driver.get(BASE_URL);
            test.info("Navigated to " + BASE_URL);

        } catch (Exception e) {
            test.fail("Failed to initialize browser: " + e.getMessage());
            throw e;
        }
    }

    @AfterTest
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
                test.info("Browser closed successfully");
            }
        } finally {
            if (extentReports != null) {
                extentReports.flush();
            }
        }
    }
}