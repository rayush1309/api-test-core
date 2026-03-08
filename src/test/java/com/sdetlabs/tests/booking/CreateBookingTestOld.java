package com.sdetlabs.tests.booking;

import com.sdetlabs.api.constants.Endpoints;
import com.sdetlabs.tests.base.BaseTest;
import org.testng.annotations.Test;

// Static imports for RestAssured and Hamcrest matchers
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateBookingTestOld extends BaseTest {

    @Test
    public void testCreateBooking() {

        // Note: Ye hardcoded JSON String hai. Ise hum next step mein (POJO) se replace karenge!
        String jsonBody = "{\n" +
                "    \"firstname\": \"Ayush\",\n" +
                "    \"lastname\": \"Raj\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2026-01-01\",\n" +
                "        \"checkout\": \"2026-01-02\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";

        // Extremely clean request execution
        given()
                .spec(requestSpec) // Magic: Headers aur URL BaseTest se inject ho gaye!
                .body(jsonBody)
                .when()
                .post(Endpoints.CREATE_BOOKING) // Magic: Endpoint Constants file se aa gaya!
                .then()
                .log().all() // Console mein response print karega
                .statusCode(200) // Verification 1: Server should return 200 OK
                .body("booking.firstname", equalTo("Ayush")) // Verification 2: Firstname check
                .body("bookingid", notNullValue()); // Verification 3: Server ne ID generate ki hai ya nahi
    }
}