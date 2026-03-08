package com.sdetlabs.tests.listeners;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 2; // Test fail hone par maximum 2 baar try karega

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retrying test: " + result.getName() + " for the " + retryCount + " time.");
            return true;
        }
        return false;
    }
}