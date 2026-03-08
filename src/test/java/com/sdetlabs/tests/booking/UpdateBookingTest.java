package com.sdetlabs.tests.booking;

import com.sdetlabs.api.constants.Endpoints;
import com.sdetlabs.api.models.Booking;
import com.sdetlabs.api.utils.PayloadFactory;
import com.sdetlabs.api.utils.TokenManager;
import com.sdetlabs.tests.base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateBookingTest extends BaseTest {

    @Test
    public void testUpdateBooking() {

        // STEP 1: AUTHENTICATION (Get the Token dynamically)
        String token = TokenManager.getToken();
        System.out.println("====== SECURE TOKEN GENERATED: " + token + " ======");

        // STEP 2: CREATE A BOOKING (Taaki humare paas update karne ke liye data ho)
        Booking createPayload = PayloadFactory.createRandomBookingPayload();
        int bookingId = given()
                .spec(requestSpec)
                .body(createPayload)
                .when()
                .post(Endpoints.CREATE_BOOKING)
                .then()
                .statusCode(200)
                .extract().path("bookingid");

        // STEP 3: UPDATE THE BOOKING (PUT Request)
        // Naya payload banate hain jisme hum firstname update karenge
        Booking updatePayload = PayloadFactory.createRandomBookingPayload();
        updatePayload.setFirstname("Ayush_Updated_By_SDET"); // Humne explicitly naam change kiya

        given()
                .spec(requestSpec)
                .header("Cookie", "token=" + token) // Injecting the token!
                .pathParam("id", bookingId) // API URL: /booking/{id}
                .body(updatePayload)
                .log().all()
                .when()
                .put(Endpoints.UPDATE_BOOKING)
                .then()
                .log().all()
                .statusCode(200)
                // Verify that the server accepted our new updated name
                .body("firstname", equalTo("Ayush_Updated_By_SDET"));
    }
}