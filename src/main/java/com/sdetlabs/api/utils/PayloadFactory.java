package com.sdetlabs.api.utils;

import com.github.javafaker.Faker;
import com.sdetlabs.api.models.Booking;
import com.sdetlabs.api.models.BookingDates;

public class PayloadFactory {

    // Singleton instance of Faker so we don't recreate it every time
    private static final Faker faker = new Faker();

    // Factory Method: Jab bhi call hoga, ek naya, random Booking object dega
    public static Booking createRandomBookingPayload() {

        BookingDates dates = BookingDates.builder()
                .checkin("2026-03-10") // Hum dates ko abhi simple rakhte hain
                .checkout("2026-03-15")
                .build();

        return Booking.builder()
                .firstname(faker.name().firstName()) // Magic: Generates random First Name
                .lastname(faker.name().lastName())   // Magic: Generates random Last Name
                .totalprice(faker.number().numberBetween(500, 5000)) // Random price between 500-5000
                .depositpaid(faker.bool().bool())    // Random true/false
                .bookingdates(dates)
                .additionalneeds(faker.food().dish()) // Random food item as additional need
                .build();
    }
}