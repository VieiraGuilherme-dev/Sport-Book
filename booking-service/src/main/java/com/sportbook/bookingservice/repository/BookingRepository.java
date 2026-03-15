package com.sportbook.bookingservice.repository;


import com.sportbook.bookingservice.entity.Booking;
import com.sportbook.bookingservice.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCourtId(Long courtId);
    List<Booking> findByCustomerEmail(String email);
    List<Booking> findByStatus(BookingStatus status);
    List<Booking> findByBookingDate(LocalDate date);

    @Query("""
        SELECT COUNT(b) > 0 FROM Booking b
        WHERE b.courtId = :courtId
          AND b.bookingDate = :date
          AND b.status NOT IN ('CANCELLED')
          AND (b.startTime < :endTime AND b.endTime > :startTime)
    """)
    boolean existsConflict(
            @Param("courtId") Long courtId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );
}

