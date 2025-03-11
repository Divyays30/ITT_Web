package com.intimetec.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigManager {
    private static final Logger logger = Logger.getLogger(ConfigManager.class.getName());
    private static final Properties properties = new Properties();
    private static final String CONFIG_PATH = "src/test/resources/config/environment.properties";

    static {
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            properties.load(fis);
            logger.info("Loaded configuration from: " + CONFIG_PATH);
        } catch (IOException e) {
            logger.severe("Failed to load configuration: " + e.getMessage());
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public static String getBaseUrl(String environment) {
        return properties.getProperty(environment + ".baseUrl");
    }

    public static int getTimeout(String environment) {
        return Integer.parseInt(properties.getProperty(environment + ".timeout", "30"));
    }

    public static int getRetryAttempts(String environment) {
        return Integer.parseInt(properties.getProperty(environment + ".retryAttempts", "1"));
    }
}