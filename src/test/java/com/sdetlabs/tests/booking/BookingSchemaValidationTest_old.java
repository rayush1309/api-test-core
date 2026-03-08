package com.sdetlabs.tests.booking;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BookingSchemaValidationTest_old {

    @Test(description = "Verify that Create Booking API strictly follows the JSON Schema contract")
    public void validateCreateBookingSchema() {

        // 1. A simple payload just to trigger the API
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

        // 2. Make the request and validate Schema at the gate!
        given()
                .baseUri("https://restful-booker.herokuapp.com") // Ya aapka config.getBaseUrl() agar aap use kar rahe hain
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/booking")
                .then()
                .log().body() // Sirf response body print karega
                .assertThat()
                .statusCode(200)
                // 👇 THE MAGIC BOUNCER IS HERE 👇
                .body(matchesJsonSchemaInClasspath("create-booking-schema.json"));
    }
}