package com.sportbook.courtservice.repository;

import com.sportbook.courtservice.entity.Court;
import com.sportbook.courtservice.enums.CourtStatus;
import com.sportbook.courtservice.enums.SportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {

    List<Court> findByStatus(CourtStatus status);

    List<Court> findBySportType(SportType sportType);

    List<Court> findBySportTypeAndStatus(SportType sportType, CourtStatus status);

    boolean existsByNameAndLocation(String name, String location);
}