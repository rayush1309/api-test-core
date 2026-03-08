package com.sdetlabs.tests.base;

import com.sdetlabs.api.config.ConfigReader;
import org.testng.annotations.Test;

public class ConfigTest {

    @Test
    public void verifyConfigReader() {
        // Hum Singleton ka instance mangwa rahe hain aur usko key pass kar rahe hain
        String url = ConfigReader.getInstance().getProperty("base.url");
        String env = ConfigReader.getInstance().getProperty("environment");

        // Print karke dekhte hain ki kya mila
        System.out.println("The Base URL fetched is: " + url);
        System.out.println("The Environment is: " + env);
    }
}