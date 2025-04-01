package com.intimetec.automation.pages;

import com.intimetec.automation.helpers.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;

public class CareersPageActions {
    private static final Duration ELEMENT_TIMEOUT = Duration.ofSeconds(30);

    private final CareersPage careersPage;
    private final WebDriverUtils webDriverUtils;

    public CareersPageActions(WebDriver driver) {
        this.careersPage = new CareersPage(driver);
        this.webDriverUtils = new WebDriverUtils(driver);
    }

    public CareersPageActions clickOnIndiaCareers() {
        WebElement indiaCareersLink = webDriverUtils.waitForElementClickable(
                careersPage.getIndiaCareersLink(),
                ELEMENT_TIMEOUT
        );
        webDriverUtils.scrollToElement(indiaCareersLink);
        webDriverUtils.clickElement(indiaCareersLink);
        return this;
    }

    public CareersPageActions clickOnLanguageSelector() {
        WebElement languageSelector = webDriverUtils.waitForElementClickable(
                careersPage.getLanguageSelector(),
                ELEMENT_TIMEOUT
        );
        webDriverUtils.scrollToElement(languageSelector);
        webDriverUtils.clickElement(languageSelector);
        return this;
    }

    public CareersPageActions selectAustraliaEnglish() {
        WebElement australiaOption = webDriverUtils.waitForElementClickable(
                careersPage.getAustraliaEnglishLanguageOption(),
                ELEMENT_TIMEOUT
        );
        webDriverUtils.scrollToElement(australiaOption);
        webDriverUtils.clickElement(australiaOption);
        return this;
    }

    public CareersPageActions selectKoreaEnglish() {
        WebElement koreaOption = webDriverUtils.waitForElementClickable(
                careersPage.getKoreaEnglishLanguageOption(),
                ELEMENT_TIMEOUT
        );
        webDriverUtils.scrollToElement(koreaOption);
        webDriverUtils.clickElement(koreaOption);
        return this;
    }

    public String getCurrentUrl() {
        webDriverUtils.getCurrentUrl();
    }

    public String getSelectedLanguage() {
        return webDriverUtils.getText(careersPage.getLanguageSelector());
    }
}