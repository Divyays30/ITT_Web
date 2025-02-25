package com.intimetec.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebDriverUtils {
    private WebDriver driver;
    private By languageSelector;
    private static final Logger logger = Logger.getLogger(WebDriverUtils.class.getName());

    public WebDriverUtils(WebDriver driver, By languageSelector) {
        this.driver = driver;
        this.languageSelector = languageSelector;
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to element: " + element);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to scroll to element: " + element, e);
        }
    }

    public void changeLanguage(By languageOption, ExtentTest test, String languageDescription) {
        selectLanguage(languageOption, test, languageDescription);
    }

    public static void clickUsingJS(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            logger.info("Clicked on element using JavaScript: " + element);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to click on element using JavaScript: " + element, e);
        }
    }

    public static void switchToTab(WebDriver driver, int tabIndex) {
        try {
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(tabIndex));
            logger.info("Switched to tab with index: " + tabIndex);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to switch to tab with index: " + tabIndex, e);
        }
    }

    public static void closeCurrentTab(WebDriver driver) {
        try {
            driver.close();
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0));
            logger.info("Closed current tab and switched to the first tab.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to close current tab and switch to the first tab.", e);
        }
    }

    public void scrollToAndClick(By locator, ExtentTest test, String description) {
        try {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            test.info("Scrolled to " + description);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            test.info("Clicked on " + description);
            logger.info("Scrolled to and clicked on " + description);
        } catch (Exception e) {
            test.fail("An error occurred while interacting with " + description + ": " + e.getMessage());
            logger.log(Level.SEVERE, "An error occurred while interacting with " + description, e);
        }
    }

    public void selectLanguage(By languageOption, ExtentTest test, String languageDescription) {
        scrollToAndClick(languageSelector, test, "language selector");
        scrollToAndClick(languageOption, test, languageDescription);
    }

    public static void scrollToBottom(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            logger.info("Scrolled to the bottom of the page.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to scroll to the bottom of the page.", e);
        }
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.info("Element is clickable: " + element);
            return clickableElement;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to wait for element to be clickable: " + element, e);
            throw e;
        }
    }

    public static void clickElement(WebDriver driver, WebElement element) {
        try {
            element.click();
            logger.info("Clicked on element: " + element);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to click on element: " + element, e);
            throw e;
        }
    }
}