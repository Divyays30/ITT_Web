package com.intimetec.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;
    private By cookieBanner = By.id("hs-eu-cookie-confirmation-inner");
    private By cookieAcceptButton = By.id("hs-eu-decline-button");
    private By careersLink = By.xpath("(//a[contains(text(),'Careers')])[3]");
    private By korinCareersLink = By.xpath("(//a[contains(text(),'Careers')])[4]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getCookieBanner() {
        return driver.findElement(cookieBanner);
    }

    public WebElement getCookieAcceptButton() {
        return driver.findElement(cookieAcceptButton);
    }

    public WebElement getCareersLink() {
        return driver.findElement(careersLink);
    }

    public WebElement getKorinCareersLink() {
        return driver.findElement(korinCareersLink);
    }
}