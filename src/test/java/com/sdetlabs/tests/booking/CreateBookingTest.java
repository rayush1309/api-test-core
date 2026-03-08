package com.sdetlabs.tests.booking;

import com.sdetlabs.api.constants.Endpoints;
import com.sdetlabs.api.models.Booking;
import com.sdetlabs.api.utils.PayloadFactory;
import com.sdetlabs.tests.base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateBookingTest extends BaseTest {

    @Test
    public void testCreateBookingAndVerify() {

        // 1. DATA GENERATION: Get dynamic data from our Factory
        Booking payload = PayloadFactory.createRandomBookingPayload();

        // Console mein print karke dekhte hain kya generate hua
        System.out.println("Generated Firstname: " + payload.getFirstname());
        System.out.println("Generated Price: " + payload.getTotalprice());

        // 2. HIT POST REQUEST
        int extractedBookingId = given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post(Endpoints.CREATE_BOOKING)
                .then()
                .statusCode(200)
                .extract().path("bookingid");

        // 3. HIT GET REQUEST (API CHAINING)
        given()
                .spec(requestSpec)
                .pathParam("id", extractedBookingId)
                .when()
                .get(Endpoints.GET_SINGLE_BOOKING)
                .then()
                .log().body() // Sirf body log karega, pura lamba response nahi
                .statusCode(200)
                // CROSS-VERIFICATION: Jo naam factory ne generate kiya, wahi check karenge!
                .body("firstname", equalTo(payload.getFirstname()))
                .body("lastname", equalTo(payload.getLastname()));
    }
}