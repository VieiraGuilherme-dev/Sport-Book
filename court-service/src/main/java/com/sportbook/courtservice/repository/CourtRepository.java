package com.sportbook.courtservice.repository;

import com.sportbook.courtservice.entity.Court;
import com.sportbook.courtservice.enums.CourtStatus;
import com.sportbook.courtservice.enums.SportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {

    List<Court> findByStatus(CourtStatus status);

    List<Court> findBySportType(SportType sportType);

    List<Court> findBySportTypeAndStatus(SportType sportType, CourtStatus status);

    List<Court> findByBuildingIgnoreCase(String building);

    boolean existsByNameAndLocation(String name, String location);

    @Query("""
        SELECT c FROM Court c
        WHERE (:sportType IS NULL OR c.sportType = :sportType)
          AND (:status IS NULL OR c.status = :status)
          AND (:minPrice IS NULL OR c.pricePerHour >= :minPrice)
          AND (:maxPrice IS NULL OR c.pricePerHour <= :maxPrice)
        ORDER BY c.pricePerHour ASC
    """)
    List<Court> findWithFilters(
            @Param("sportType") SportType sportType,
            @Param("status") CourtStatus status,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice
    );
}