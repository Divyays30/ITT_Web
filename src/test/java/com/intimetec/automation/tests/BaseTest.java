package com.intimetec.automation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.intimetec.automation.helpers.ExtentReportManagerUtils;
import com.intimetec.automation.helpers.WebDriverHelper;
import com.intimetec.automation.utils.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extentReports;
    protected ExtentTest test;

    @BeforeTest
    public void setup() {
        extentReports = ExtentReportManagerUtils.createExtentReports();
        test = extentReports.createTest(getClass().getSimpleName());

        try {
            String environment = System.getProperty("env", "prod");
            String browserType = System.getProperty("browser", "chrome").toLowerCase();

            String baseUrl = ConfigManager.getBaseUrl(environment);
            int timeout = ConfigManager.getTimeout(environment);

            driver = WebDriverHelper.getDriver(browserType);
            test.info("Initialized " + browserType + " browser");

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));

            driver.get(baseUrl);
            test.info("Navigated to " + baseUrl);

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