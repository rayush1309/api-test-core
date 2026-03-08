package com.sdetlabs.api.utils;

import com.sdetlabs.api.config.ConfigReader;
import com.sdetlabs.api.constants.Endpoints;
import com.sdetlabs.api.models.AuthPayload;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class TokenManager {

    public static String getToken() {
        // 1. Get credentials from config.properties using our Singleton
        String user = ConfigReader.getInstance().getProperty("username");
        String pass = ConfigReader.getInstance().getProperty("password");

        // 2. Build the Payload
        AuthPayload payload = AuthPayload.builder()
                .username(user)
                .password(pass)
                .build();

        // 3. Hit the Auth API and EXTRACT the token
        String token = given()
                .baseUri(ConfigReader.getInstance().getProperty("base.url"))
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(Endpoints.GET_TOKEN) // "/auth" endpoint
                .then()
                .statusCode(200) // Ensure login was successful
                .extract().path("token"); // Extracting the generated token string

        return token;
    }
}