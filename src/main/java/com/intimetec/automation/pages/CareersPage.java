package com.intimetec.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

    public class CareersPage {
    private WebDriver driver;
    private By indiaCareersLink = By.xpath("//a[@class='btn itt-btn-pghost large']");
    private By languageSelector = By.xpath("(//div[@class='globe_class'])[2]");
    private By australiaEnglishLanguage = By.xpath("//a[text()='Australia (English)']");
    private By koreaEnglishLanguage = By.xpath("//a[text()='Korea (Korean)']");

    public CareersPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getIndiaCareersLink() {
        return driver.findElement(indiaCareersLink);
    }

    public WebElement getLanguageSelector() {
        return driver.findElement(languageSelector);
    }

    public WebElement getAustraliaEnglishLanguageOption() {
        return driver.findElement(australiaEnglishLanguage);
    }

    public WebElement getKoreaEnglishLanguageOption() {
        return driver.findElement(koreaEnglishLanguage);
    }
}
