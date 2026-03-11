package com.sportbook.courtservice.dto;

import com.sportbook.courtservice.entity.Court;
import com.sportbook.courtservice.enums.CourtStatus;
import com.sportbook.courtservice.enums.SportType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CourtResponse {

    private Long id;
    private String name;
    private SportType sportType;
    private String location;
    private String description;
    private BigDecimal pricePerHour;
    private CourtStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CourtResponse from(Court court) {
        return CourtResponse.builder()
                .id(court.getId())
                .name(court.getName())
                .sportType(court.getSportType())
                .location(court.getLocation())
                .description(court.getDescription())
                .pricePerHour(court.getPricePerHour())
                .status(court.getStatus())
                .createdAt(court.getCreatedAt())
                .updatedAt(court.getUpdatedAt())
                .build();
    }
}
