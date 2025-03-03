package com.intimetec.automation.helpers;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;

public class BrowserProvider {
    public static ChromeOptions getChromeOptions() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return options;
    }

    public static FirefoxOptions getFirefoxOptions() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        return options;
    }

    public static EdgeOptions getEdgeOptions() {
        System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        return options;
    }

}
