package com.intimetec.automation.pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.intimetec.automation.helpers.ExtentReportManagerUtils.extent;

public class HomePageActions {
    private WebDriver driver;
    private HomePage homePage;
    private ExtentTest test;
    private static final Logger logger = Logger.getLogger(HomePageActions.class.getName());
    private static final ExtentReports extentReports = createExtentReports();

    public HomePageActions(WebDriver driver) {
        this.driver = driver;
        this.homePage = new HomePage(driver);
        this.test = extentReports.createTest("HomePage Test");
        test.info("Initialized HomePageActions object.");
    }

    public static synchronized ExtentReports createExtentReports() {
        if (extent == null) {
            try {
                ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/reports/AutomationTestReport.html");
                sparkReporter.config().setReportName("Automation Test Report");
                sparkReporter.config().setDocumentTitle("Test Execution Report");

                extent = new ExtentReports();
                extent.attachReporter(sparkReporter);
                extent.setSystemInfo("Tester", "QA Team");
                extent.setSystemInfo("Environment", "Production");
            } catch (Exception e) {
                System.err.println("Error initializing ExtentReports: " + e.getMessage());
            }
        }
        return extent;
    }

    public void handleCookieBanner() {
        test.info("Handling cookie banner.");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            test.info("Waiting for cookie banner to be visible.");
            WebElement banner = wait.until(ExpectedConditions.visibilityOf(homePage.getCookieBanner()));
            test.info("Cookie banner is visible.");

            if (banner.isDisplayed()) {
                logger.info("Cookie banner detected. Attempting to dismiss.");
                test.info("Cookie banner detected. Attempting to dismiss.");

                WebElement declineButton = homePage.getCookieAcceptButton();
                test.info("Located 'Decline' button.");
                declineButton.click();

                logger.info("Cookie banner dismissed successfully.");
                test.pass("Cookie banner dismissed successfully.");
            }
        } catch (TimeoutException e) {
            logger.warning("No cookie banner to dismiss. Timeout occurred.");
            test.warning("No cookie banner to dismiss. Timeout occurred.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while handling the cookie banner.", e);
            test.fail("An error occurred while handling the cookie banner: " + e.getMessage());
        }
    }

    public void clickOnCareers() {
        test.info("Clicking on 'Careers' link.");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            test.info("Scrolling to the bottom of the page.");
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            logger.info("Scrolled to the bottom of the page.");
            test.pass("Scrolled to the bottom of the page.");

            test.info("Waiting for 'Careers' link to become clickable.");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement careersLinkElement = wait.until(ExpectedConditions.elementToBeClickable(homePage.getCareersLink()));
            logger.info("Located the 'Careers' link.");
            test.pass("Located the 'Careers' link.");

            test.info("Scrolling 'Careers' link into view.");
            js.executeScript("arguments[0].scrollIntoView(true);", careersLinkElement);
            logger.info("Scrolled to 'Careers' link.");
            test.pass("Scrolled to 'Careers' link.");

            test.info("Clicking on 'Careers' link.");
            careersLinkElement.click();
            logger.info("Clicked on 'Careers' link successfully.");
            test.pass("Clicked on 'Careers' link successfully.");
        } catch (Exception e) {
            logger.warning("Click on 'Careers' failed. Attempting JavaScript click.");
            test.warning("Standard click failed. Attempting JavaScript click.");

            try {
                test.info("Attempting JavaScript click on 'Careers' link.");
                js.executeScript("arguments[0].click();", homePage.getCareersLink());
                logger.info("'Careers' link clicked successfully using JavaScript.");
                test.pass("'Careers' link clicked successfully using JavaScript.");
            } catch (Exception jsException) {
                logger.log(Level.SEVERE, "JavaScript click also failed.", jsException);
                test.fail("JavaScript click on 'Careers' link failed: " + jsException.getMessage());
            }
        }
    }
}