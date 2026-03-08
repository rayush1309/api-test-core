package com.sdetlabs.tests.booking;

import com.sdetlabs.api.constants.Endpoints;
import com.sdetlabs.api.models.Booking;
import com.sdetlabs.api.utils.AssertionUtils;
import com.sdetlabs.api.utils.PayloadFactory;
import com.sdetlabs.tests.base.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CleanAssertionTest extends BaseTest {

    @Test
    public void testWithArchitectAssertions() {

        // 1. Generate Payload
        Booking payload = PayloadFactory.createRandomBookingPayload();

        // 2. Execute Request & Extract COMPLETE Response Object
        // Notice we removed .then() completely. We just extract the raw response.
        Response response = given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post(Endpoints.CREATE_BOOKING);

        System.out.println("Server Response: " + response.asPrettyString());

        // 3. THE ARCHITECT WAY: Centralized Assertions
        // Agar kal status code 201 ho gaya, toh humein bas yahan pass karna hai, logic abstract hai
        AssertionUtils.assertExpectedStatusCode(response, 200);

        AssertionUtils.assertNotNull(response, "bookingid");

        AssertionUtils.assertResponseBodyValue(response, "booking.firstname", payload.getFirstname());
        AssertionUtils.assertResponseBodyValue(response, "booking.totalprice", payload.getTotalprice());
    }
}