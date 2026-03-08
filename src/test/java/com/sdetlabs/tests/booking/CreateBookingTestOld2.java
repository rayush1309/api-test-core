package com.sdetlabs.tests.booking;

import com.sdetlabs.api.constants.Endpoints;
import com.sdetlabs.api.models.Booking;
import com.sdetlabs.api.models.BookingDates;
import com.sdetlabs.tests.base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateBookingTestOld2 extends BaseTest {

    @Test
    public void testCreateBooking() {

        // Step 1: Create the nested object using Builder Pattern
        BookingDates dates = BookingDates.builder()
                .checkin("2026-01-01")
                .checkout("2026-01-02")
                .build();

        // Step 2: Create the main payload using Builder Pattern
        Booking payload = Booking.builder()
                .firstname("Ayush")
                .lastname("Raj")
                .totalprice(1500)
                .depositpaid(true)
                .bookingdates(dates) // Insert nested object here
                .additionalneeds("Extra Pillows")
                .build();

        // Step 3: Execute the request (Notice we pass the 'payload' object directly!)
        given()
                .spec(requestSpec)
                .body(payload) // Jackson library will automatically convert this Java Object to JSON
                .log().all()
                .when()
                .post(Endpoints.CREATE_BOOKING)
                .then()
                .log().all()
                .statusCode(200)
                .body("booking.firstname", equalTo("Ayush"))
                .body("bookingid", notNullValue());
    }
}