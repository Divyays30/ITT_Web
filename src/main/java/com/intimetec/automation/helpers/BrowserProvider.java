package com.intimetec.automation.helpers;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;


public class BrowserProvider {

    public static ChromeOptions getChromeOptions() {
        System.setProperty("webdriver.chrome.driver", "com/intimetec/automation/browser/chromedriver");
        ChromeOptions options = new ChromeOptions();
        return options;
    }

    public static FirefoxOptions getFirefoxOptions() {
        System.setProperty("webdriver.gecko.driver", "com/intimetec/automation/browser/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        return options;
    }

    public static EdgeOptions getEdgeOptions() {
        System.setProperty("webdriver.edge.driver", "com/intimetec/automation/browser/msedgedriver");
        EdgeOptions options = new EdgeOptions();
        return options;
    }

}
