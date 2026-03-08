package com.sdetlabs.tests.booking;

import com.sdetlabs.api.constants.Endpoints;
import com.sdetlabs.api.models.Booking;
import com.sdetlabs.api.models.BookingDates;
import com.sdetlabs.tests.base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DataDrivenBookingTest extends BaseTest {

    // 1. DATA PROVIDER: The Data Engine
    // Ye method 3 sets of data return kar raha hai (Firstname, Lastname, Price)
    @DataProvider(name = "bookingData")
    public Object[][] getBookingData() {
        return new Object[][] {
                {"Ayush", "Raj", 1500},        // Row 1: Normal Data
                {"John", "Doe", 500},          // Row 2: Another user
                {"Test_User", "Auto", 9999}    // Row 3: Edge case name with high price
        };
    }

    // 2. THE TEST: Notice humne dataProvider ka naam yahan link kiya hai
    // Test method mein wahi parameters pass honge jo DataProvider bhej raha hai
    @Test(dataProvider = "bookingData")
    public void testCreateMultipleBookings(String fName, String lName, int price) {

        System.out.println("--- RUNNING TEST FOR: " + fName + " " + lName + " with Price: " + price + " ---");

        // Dynamically build payload using the provided parameters
        BookingDates dates = BookingDates.builder()
                .checkin("2026-04-01")
                .checkout("2026-04-05")
                .build();

        Booking payload = Booking.builder()
                .firstname(fName)
                .lastname(lName)
                .totalprice(price)
                .depositpaid(true)
                .bookingdates(dates)
                .additionalneeds("Dinner")
                .build();

        // Hit the API
        given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post(Endpoints.CREATE_BOOKING)
                .then()
                .statusCode(200)
                // Assert that the API accurately saved whatever we passed
                .body("booking.firstname", equalTo(fName))
                .body("booking.lastname", equalTo(lName))
                .body("booking.totalprice", equalTo(price));
    }
}