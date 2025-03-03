package com.intimetec.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebDriverUtils {
    private static final Logger logger = Logger.getLogger(WebDriverUtils.class.getName());
    private final WebDriver driver;

    public WebDriverUtils(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementVisible(WebElement element, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            handleException("Element not visible: " + element, e);
            throw e;
        }
    }

    public WebElement waitForElementClickable(WebElement element, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            handleException("Element not clickable: " + element, e);
            throw e;
        }
    }

    public void clickElement(WebElement element) {
        try {
            element.click();
            logger.info("Clicked element: " + element);
        } catch (Exception e) {
            handleException("Failed to click element: " + element, e);
            throw e;
        }
    }

    public void scrollToBottom() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            logger.info("Scrolled to bottom of page");
        } catch (Exception e) {
            handleException("Failed to scroll to bottom", e);
            throw e;
        }
    }

    public void scrollToElement(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to element: " + element);
        } catch (Exception e) {
            handleException("Failed to scroll to element: " + element, e);
            throw e;
        }
    }

    public void clickUsingJS(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            logger.info("Clicked element using JavaScript: " + element);
        } catch (Exception e) {
            handleException("Failed to click element using JavaScript: " + element, e);
            throw e;
        }
    }

    public void switchToNewWindow(String parentWindowHandle) {
        try {
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(parentWindowHandle)) {
                    driver.switchTo().window(handle);
                    logger.info("Switched to window: " + handle);
                    return;
                }
            }
            throw new RuntimeException("No new window found to switch to");
        } catch (Exception e) {
            handleException("Failed to switch to new window", e);
            throw e;
        }
    }

    public void handleException(String message, Exception e) {
        logger.log(Level.SEVERE, message, e);
    }

    public void changeLanguage(By languageOption, String languageDescription) {
        selectLanguage(languageOption, languageDescription);
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

    public void scrollToAndClick(By locator, String description) {
        try {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            logger.info("Scrolled to and clicked on " + description);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while interacting with " + description, e);
        }
    }

    public void selectLanguage(By languageOption, String languageDescription) {
        scrollToAndClick(languageOption, languageDescription);
    }
}