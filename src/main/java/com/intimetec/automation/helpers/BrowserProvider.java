package com.intimetec.automation.helpers;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import java.nio.file.Paths;

public class BrowserProvider {
    private static final String DRIVERS_PATH = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "drivers").toString();

    public static ChromeOptions getChromeOptions() {
        System.setProperty("webdriver.chrome.driver", Paths.get(DRIVERS_PATH, "chromedriver.exe").toString());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return options;
    }

    public static FirefoxOptions getFirefoxOptions() {
        System.setProperty("webdriver.gecko.driver", Paths.get(DRIVERS_PATH, "geckodriver.exe").toString());
        FirefoxOptions options = new FirefoxOptions();
        return options;
    }

    public static EdgeOptions getEdgeOptions() {
        System.setProperty("webdriver.edge.driver", Paths.get(DRIVERS_PATH, "msedgedriver.exe").toString());
        EdgeOptions options = new EdgeOptions();
        return options;
    }
}
