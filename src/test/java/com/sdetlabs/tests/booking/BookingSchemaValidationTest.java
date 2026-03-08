package com.sdetlabs.tests.booking;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BookingSchemaValidationTest {

    @Test(description = "Test Number 1")
    public void validateCreateBookingSchema_1() {

        // Tracker for Test 1
        System.out.println("🚀 Test 1 is starting on Thread ID: " + Thread.currentThread().getId());

        String payload = "{\n" +
                "    \"firstname\" : \"Ayush\",\n" +
                "    \"lastname\" : \"Raj\",\n" +
                "    \"totalprice\" : 150,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2026-03-01\",\n" +
                "        \"checkout\" : \"2026-03-10\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/booking")
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("create-booking-schema.json"));

        System.out.println("✅ Test 1 is COMPLETE on Thread ID: " + Thread.currentThread().getId());
    }

    @Test(description = "Test Number 2 (The Twin)")
    public void validateCreateBookingSchema_2() {

        // Tracker for Test 2
        System.out.println("🚀 Test 2 is starting on Thread ID: " + Thread.currentThread().getId());

        String payload = "{\n" +
                "    \"firstname\" : \"Ayush\",\n" +
                "    \"lastname\" : \"Raj\",\n" +
                "    \"totalprice\" : 150,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2026-03-01\",\n" +
                "        \"checkout\" : \"2026-03-10\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/booking")
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("create-booking-schema.json"));

        System.out.println("✅ Test 2 is COMPLETE on Thread ID: " + Thread.currentThread().getId());
    }
}