package com.sdetlabs.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok Annotations (The Architect's magic)
@Data // Automatically generates Getters, Setters, and toString()
@Builder // Allows us to create objects cleanly (Builder Pattern)
@NoArgsConstructor // Creates an empty constructor (Required by Jackson)
@AllArgsConstructor // Creates a constructor with all fields
public class BookingDates {
    private String checkin;
    private String checkout;
}