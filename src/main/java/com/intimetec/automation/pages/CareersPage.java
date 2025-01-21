
package com.intimetec.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CareersPage {
    private WebDriver driver;


    private By indiaCareersLink = By.xpath("//a[@class='btn itt-btn-pghost large']");
    private By australiaEnglishLanguageText = By.xpath("//a[text()='Australia (English)']");
    private By koreaEnglishLanguageText = By.xpath("//a[text()='Korea (Korean)']");

    public CareersPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getIndiaCareersLink() {
        return driver.findElement(indiaCareersLink);
    }

    public WebElement getAustraliaEnglishLanguageOption() {
        return driver.findElement(australiaEnglishLanguageText);
    }

    public WebElement getKoreaEnglishLanguageOption() {
        return driver.findElement(koreaEnglishLanguageText);
    }
}