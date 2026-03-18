package com.sportbook.courtservice.dto;

import com.sportbook.courtservice.enums.CourtStatus;
import com.sportbook.courtservice.enums.SportType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourtFilterRequest {

    private SportType sportType;
    private CourtStatus status;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}