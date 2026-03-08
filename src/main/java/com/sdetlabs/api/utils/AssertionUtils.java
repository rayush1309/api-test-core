package com.sdetlabs.api.utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class AssertionUtils {

    // 1. Wrapper for Status Code Validation
    public static void assertExpectedStatusCode(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        // TestNG ka default assertion use kar rahe hain taaki proper error message aaye
        Assert.assertEquals(actualStatusCode, expectedStatusCode,
                "ERROR: Status code mismatch! Expected: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }

    // 2. Wrapper for JSON Body Validation (Key-Value)
    public static void assertResponseBodyValue(Response response, String jsonPath, Object expectedValue) {
        Object actualValue = response.jsonPath().get(jsonPath);
        Assert.assertEquals(actualValue, expectedValue,
                "ERROR: Value mismatch for JSON path '" + jsonPath + "'! Expected: " + expectedValue + ", but got: " + actualValue);
    }

    // 3. Wrapper to check if a key is present and NOT null (e.g., bookingid)
    public static void assertNotNull(Response response, String jsonPath) {
        Object actualValue = response.jsonPath().get(jsonPath);
        Assert.assertNotNull(actualValue, "ERROR: The value for JSON path '" + jsonPath + "' is null!");
    }
}