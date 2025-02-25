package com.intimetec.automation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.intimetec.automation.helpers.WebDriverHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected static WebDriver driver;
    protected ExtentReports extentReports;
    protected ExtentTest test;

    @BeforeTest
    public void setupExtentReports() {
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/report.html");
        extentReports.attachReporter(sparkReporter);
    }

    @BeforeTest
    public void setup() {
        String browser = System.getProperty("browser", "firefox");
        driver = WebDriverHelper.getDriver(browser);
        driver.get("https://www.intimetec.com/");

        if (extentReports != null) {
            test = extentReports.createTest("Setup");
            test.info("WebDriver initialized.");
            test.info("Navigated to https://www.intimetec.com/");
            test.pass("Setup completed successfully.");
        }
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
