package com.intimetec.automation.pages;

import com.intimetec.automation.helpers.ExtentReportUtils;
import com.intimetec.automation.helpers.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CareersPageActions {
    private WebDriver driver;
    private CareersPage careersPage;

    public CareersPageActions(WebDriver driver) {
        this.driver = driver;
        this.careersPage = new CareersPage(driver);
    }

    public void clickOnIndiaCareers() {
        clickOnCareersLink(careersPage.getIndiaCareersLink(), "https://careers.intimetec.in/intimetec/", "India Careers");
    }

    public void clickOnAustraliaCareers() {
        clickOnCareersLink(careersPage.getAustraliaEnglishLanguageOption(), "https://careers.intimetec.in/australia/", "Australia Careers");
    }

    public void clickOnKoreaCareers() {
        clickOnCareersLink(careersPage.getKoreaEnglishLanguageOption(), "https://careers.intimetec.in/korea/", "Korea Careers");
    }

    private void clickOnCareersLink(WebElement element, String expectedUrl, String linkDescription) {
        ExtentReportUtils report = new ExtentReportUtils("Click on " + linkDescription);
        try {
            WebDriverUtils.scrollToElement(driver, element);
            report.logInfo("Scrolled to '" + linkDescription + "' link.");
            WebDriverUtils.clickUsingJS(driver, element);
            report.logInfo("Clicked on '" + linkDescription + "' link using JavaScript.");
            WebDriverUtils.switchToTab(driver, 1);
            report.logInfo("Switched to new tab: " + driver.getCurrentUrl());

            if (driver.getCurrentUrl().equals(expectedUrl)) {
                report.logPass("Successfully navigated to the correct '" + linkDescription + "' page.");
            } else {
                report.logFail("Navigation to '" + linkDescription + "' page failed. Current URL: " + driver.getCurrentUrl());
            }

            WebDriverUtils.closeCurrentTab(driver);
            report.logInfo("Closed child tab and switched back to the parent tab.");
        } catch (Exception e) {
            report.logFail("An error occurred while navigating to '" + linkDescription + "': " + e.getMessage());
        }
    }
}