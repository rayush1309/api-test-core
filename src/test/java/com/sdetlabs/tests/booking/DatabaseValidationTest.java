package com.sdetlabs.tests.booking;

import com.sdetlabs.api.constants.Endpoints;
import com.sdetlabs.api.models.Booking;
import com.sdetlabs.api.utils.AssertionUtils;
import com.sdetlabs.api.utils.DBUtils;
import com.sdetlabs.api.utils.PayloadFactory;
import com.sdetlabs.tests.base.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class DatabaseValidationTest extends BaseTest {

    //@Test
    public void testBookingDataIntegrityWithDatabase() {

        System.out.println("--- STEP 1: API LAYER (Creating Booking) ---");
        Booking payload = PayloadFactory.createRandomBookingPayload();

        Response response = given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post(Endpoints.CREATE_BOOKING);

        AssertionUtils.assertExpectedStatusCode(response, 200);

        // Extract the Primary Key
        int bookingId = response.jsonPath().getInt("bookingid");
        System.out.println("Booking Created Successfully via API. Booking ID: " + bookingId);

        System.out.println("\n--- STEP 2: DATABASE LAYER (Backend Verification) ---");
        // Constructing the SQL Query using the Primary Key
        String sqlQuery = "SELECT * FROM bookings WHERE id = " + bookingId + ";";
        System.out.println("Executing Query: " + sqlQuery);

        // Fetching data directly from MySQL
        Map<String, String> dbRow = DBUtils.getRowData(sqlQuery);

        // The Architect's Check: Did the database actually return data?
        assertNotNull(dbRow, "CRITICAL ERROR: Data not found in Database for ID: " + bookingId);

        System.out.println("\n--- STEP 3: THE TWO-WAY MIRROR (Data Integrity Assertions) ---");
        // Comparing API input payload strictly with Database output
        assertEquals(dbRow.get("firstname"), payload.getFirstname(), "Firstname mismatch in DB!");
        assertEquals(dbRow.get("lastname"), payload.getLastname(), "Lastname mismatch in DB!");
        // Converting DB String to Integer for strict type comparison
        assertEquals(Integer.parseInt(dbRow.get("totalprice")), payload.getTotalprice(), "Price mismatch in DB!");

        System.out.println("✅ BACKEND TRUTH VERIFIED: API and Database are completely in sync!");
    }
}