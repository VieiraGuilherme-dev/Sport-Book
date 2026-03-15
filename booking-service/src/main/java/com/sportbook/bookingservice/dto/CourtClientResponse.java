package com.sportbook.bookingservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourtClientResponse {
    private Long id;
    private String name;
    private String sportType;
    private String location;
    private BigDecimal pricePerHour;
    private String status;
}