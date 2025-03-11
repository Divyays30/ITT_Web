package com.intimetec.automation.pages;

import com.intimetec.automation.helpers.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.WebElement;

public class CareersPageActions {
    private static final Duration ELEMENT_TIMEOUT = Duration.ofSeconds(30);

    private final CareersPage careersPage;
    private final WebDriverUtils webDriverUtils;

    public CareersPageActions(WebDriver driver) {
        this.careersPage = new CareersPage(driver);
        this.webDriverUtils = new WebDriverUtils(driver);
    }

    public CareersPageActions clickOnIndiaCareers() {
        try {
            webDriverUtils.waitForElementClickable(careersPage.getIndiaCareersLink(), ELEMENT_TIMEOUT);
            webDriverUtils.scrollToElement(careersPage.getIndiaCareersLink());
            webDriverUtils.clickElement(careersPage.getIndiaCareersLink());
        } catch (Exception e) {
            webDriverUtils.handleException("clicking India Careers link", e);
            tryJavaScriptClick(careersPage.getIndiaCareersLink(), "India Careers link");
        }
        return this;
    }

    public CareersPageActions clickOnLanguageSelector() {
        try {
            webDriverUtils.waitForElementClickable(careersPage.getLanguageSelector(), ELEMENT_TIMEOUT);
            webDriverUtils.scrollToElement(careersPage.getLanguageSelector());
            webDriverUtils.clickElement(careersPage.getLanguageSelector());
        } catch (Exception e) {
            webDriverUtils.handleException("clicking Language Selector", e);
            tryJavaScriptClick(careersPage.getLanguageSelector(), "Language Selector");
        }
        return this;
    }

    public CareersPageActions selectAustraliaEnglish() {
        try {
            webDriverUtils.waitForElementClickable(careersPage.getAustraliaEnglishLanguageOption(), ELEMENT_TIMEOUT);
            webDriverUtils.scrollToElement(careersPage.getAustraliaEnglishLanguageOption());
            webDriverUtils.clickElement(careersPage.getAustraliaEnglishLanguageOption());
        } catch (Exception e) {
            webDriverUtils.handleException("selecting Australia English", e);
            tryJavaScriptClick(careersPage.getAustraliaEnglishLanguageOption(), "Australia English option");
        }
        return this;
    }

    public CareersPageActions selectKoreaEnglish() {
        try {
            webDriverUtils.waitForElementClickable(careersPage.getKoreaEnglishLanguageOption(), ELEMENT_TIMEOUT);
            webDriverUtils.scrollToElement(careersPage.getKoreaEnglishLanguageOption());
            webDriverUtils.clickElement(careersPage.getKoreaEnglishLanguageOption());
        } catch (Exception e) {
            webDriverUtils.handleException("selecting Korea English", e);
            tryJavaScriptClick(careersPage.getKoreaEnglishLanguageOption(), "Korea English option");
        }
        return this;
    }

    private void tryJavaScriptClick(WebElement element, String elementName) {
        try {
            webDriverUtils.clickUsingJS(element);
        } catch (Exception e) {
            webDriverUtils.handleException("JavaScript click on " + elementName, e);
            throw e;
        }
    }
}