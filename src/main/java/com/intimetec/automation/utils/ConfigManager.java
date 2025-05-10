package com.intimetec.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigManager {
    private static final Logger logger = Logger.getLogger(ConfigManager.class.getName());
    private static Properties properties;
    private static final String CONFIG_DIR = "src/test/resources/config";

    public static void loadEnvironmentConfig(String environment) {
        String configFile = Paths.get(CONFIG_DIR, environment + ".properties").toString();
        properties = new Properties();

        try (FileInputStream fis = new FileInputStream(configFile)) {
            properties.load(fis);
            logger.info("Loaded configuration for environment: " + environment);
        } catch (IOException e) {
            logger.severe("Failed to load configuration for environment: " + environment);
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout", "30"));
    }

    public static int getRetryAttempts() {
        return Integer.parseInt(properties.getProperty("retryAttempts", "1"));
    }

    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }
}