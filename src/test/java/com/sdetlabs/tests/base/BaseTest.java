package com.sdetlabs.tests.base;

import com.sdetlabs.api.config.ConfigReader;
import io.qameta.allure.restassured.AllureRestAssured; // <-- NEW IMPORT FOR ALLURE
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected RequestSpecification requestSpec;

    @BeforeMethod
    public void setup() {
        String baseUrl = ConfigReader.getInstance().getProperty("base.url");

        // Building the standard request for all tests
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "application/json")
                .addFilter(new AllureRestAssured()) // <--- THE ARCHITECT'S MAGIC LINE
                .build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}