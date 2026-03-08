import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SmokeTest {

   @Test
    public void verifyBookingAPI() {
        // We use a public API to verify our setup works
        baseURI = "https://restful-booker.herokuapp.com";

        given()
                .log().all()
                .when()
                .get("/booking/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("firstname", notNullValue());
    }
}