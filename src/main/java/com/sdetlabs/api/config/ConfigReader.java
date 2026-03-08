package com.sdetlabs.api.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    // 1. Private static variable to hold the SINGLE instance of this class
    private static ConfigReader instance;
    private Properties properties;

    // 2. PRIVATE Constructor: This prevents anyone from using 'new ConfigReader()' anywhere else
    private ConfigReader() {
        properties = new Properties();
        try {
            // Path to the config.properties file we just created
            FileInputStream file = new FileInputStream("src/main/resources/config.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file", e);
        }
    }

    // 3. PUBLIC Static Method: The only way to get the instance
    // Synchronized keyword makes it Thread-Safe for parallel test execution
    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader(); // File is read ONLY here, the very first time
        }
        return instance; // For the 2nd, 3rd, 100th test, it just returns the already loaded instance
    }

    // 4. Helper method to fetch the value by passing the key (e.g., "base.url")
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}