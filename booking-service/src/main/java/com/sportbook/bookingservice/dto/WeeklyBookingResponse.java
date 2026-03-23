package com.sportbook.bookingservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class WeeklyBookingResponse {

    private LocalDate day;
    private String dayOfWeek;
    private int totalBookings;
    private List<BookingResponse> bookings;
}