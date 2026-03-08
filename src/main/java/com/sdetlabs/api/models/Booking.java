package com.sdetlabs.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;

    // Yahan humne purani class ko as a data type use kiya (Nested Object)
    private BookingDates bookingdates;

    private String additionalneeds;
}

/*
 {  <-- MAIN OBJECT STARTS (Booking.java)
    "firstname": "Ayush",
    "lastname": "Raj",
    "totalprice": 111,
    "depositpaid": true,

    "bookingdates": {  <-- NESTED OBJECT STARTS (BookingDates.java)
        "checkin": "2026-01-01",
        "checkout": "2026-01-02"
    }, <-- NESTED OBJECT ENDS

    "additionalneeds": "Breakfast"
}  <-- MAIN OBJECT ENDS
 */