package com.intimetec.automation.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class WebDriverHelper {
    private static WebDriver driver;

    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "firefox":
                    driver = new FirefoxDriver(BrowserProvider.getFirefoxOptions());
                    break;
                case "edge":
                    driver = new EdgeDriver(BrowserProvider.getEdgeOptions());
                    break;
                case "chrome":
                    driver = new ChromeDriver(BrowserProvider.getChromeOptions());
                    break;
                default:
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }
}