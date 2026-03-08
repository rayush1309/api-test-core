package com.sdetlabs.tests.booking;

import com.sdetlabs.api.constants.Endpoints;
import com.sdetlabs.api.models.Booking;
import com.sdetlabs.api.models.BookingDates;
import com.sdetlabs.tests.base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateBookingTestOld3 extends BaseTest {

    @Test
    public void testCreateBookingAndVerify() {

        // Step 1 & 2: Create the Payload using Builder
        BookingDates dates = BookingDates.builder()
                .checkin("2026-01-01")
                .checkout("2026-01-02")
                .build();

        Booking payload = Booking.builder()
                .firstname("Ayush")
                .lastname("Raj")
                .totalprice(1500)
                .depositpaid(true)
                .bookingdates(dates)
                .additionalneeds("Extra Pillows")
                .build();

        // Step 3: Hit POST and EXTRACT the Booking ID
        int extractedBookingId = given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post(Endpoints.CREATE_BOOKING)
                .then()
                .statusCode(200)
                .extract().path("bookingid"); // Extracting data

        System.out.println("========== The Extracted ID is: " + extractedBookingId + " ==========");

        // Step 4: API CHAINING (Hit GET request using the extracted ID)
        given()
                .spec(requestSpec)
                .pathParam("id", extractedBookingId) // Dynamically passing the ID to the URL
                .when()
                .get(Endpoints.GET_SINGLE_BOOKING) // URL becomes: /booking/{id}
                .then()
                .log().all()
                .statusCode(200)
                .body("firstname", equalTo("Ayush")) // Cross-verifying the data
                .body("lastname", equalTo("Raj"));
    }
}