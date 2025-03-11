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
        WebElement banner = webDriverUtils.waitForElementVisible(
                homePage.getCookieBanner(),
                COOKIE_BANNER_TIMEOUT
        );
        if (banner != null && banner.isDisplayed()) {
            webDriverUtils.clickElement(homePage.getCookieAcceptButton());
        }
        return this;
    }

    public HomePageActions clickOnCareers() {
        webDriverUtils.scrollToBottom();
        WebElement careersLink = webDriverUtils.waitForElementClickable(
                homePage.getCareersLink(),
                CAREERS_LINK_TIMEOUT
        );
        webDriverUtils.scrollToElement(careersLink);

        if (!tryClick(careersLink)) {
            webDriverUtils.clickUsingJS(careersLink);
        }
        return this;
    }

    private boolean tryClick(WebElement element) {
        try {
            webDriverUtils.clickElement(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public HomePageActions storeParentWindowHandle() {
        parentWindowHandle = driver.getWindowHandle();
        return this;
    }

    public HomePageActions switchToNewWindow() {
        if (parentWindowHandle != null) {
            webDriverUtils.switchToNewWindow(parentWindowHandle);
        }
        return this;
    }
}