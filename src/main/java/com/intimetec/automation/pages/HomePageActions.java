package com.intimetec.automation.pages;

import com.intimetec.automation.helpers.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class HomePageActions {
    private static final Duration COOKIE_BANNER_TIMEOUT = Duration.ofSeconds(10);
    private static final Duration CAREERS_LINK_TIMEOUT = Duration.ofSeconds(30);

    private final WebDriver driver;
    private final HomePage homePage;
    private final WebDriverUtils webDriverUtils;
    private String parentWindowHandle;

    public HomePageActions(WebDriver driver) {
        this.driver = driver;
        this.homePage = new HomePage(driver);
        this.webDriverUtils = new WebDriverUtils(driver);
    }

    public HomePageActions handleCookieBanner() {
        try {
            WebElement banner = webDriverUtils.waitForElementVisible(homePage.getCookieBanner(), COOKIE_BANNER_TIMEOUT);
            if (banner.isDisplayed()) {
                webDriverUtils.clickElement(homePage.getCookieAcceptButton());
            }
        } catch (Exception e) {
            webDriverUtils.handleException("Handling cookie banner", e);
        }
        return this;
    }

    public HomePageActions clickOnCareers() {
        try {
            webDriverUtils.scrollToBottom();
            WebElement careersLink = webDriverUtils.waitForElementClickable(homePage.getCareersLink(), CAREERS_LINK_TIMEOUT);
            webDriverUtils.scrollToElement(careersLink);
            webDriverUtils.clickElement(careersLink);
        } catch (Exception e) {
            tryJavaScriptClick(homePage.getCareersLink());
        }
        return this;
    }

    private void tryJavaScriptClick(WebElement element) {
        try {
            webDriverUtils.clickUsingJS(element);
        } catch (Exception e) {
            webDriverUtils.handleException("JavaScript click failed", e);
            throw e;
        }
    }

    public HomePageActions storeParentWindowHandle() {
        parentWindowHandle = driver.getWindowHandle();
        return this;
    }

    public HomePageActions switchToNewWindow() {
        try {
            webDriverUtils.switchToNewWindow(parentWindowHandle);
        } catch (Exception e) {
            webDriverUtils.handleException("Switching to new window", e);
            throw e;
        }
        return this;
    }
}