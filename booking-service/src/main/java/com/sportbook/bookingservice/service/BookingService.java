package com.sportbook.bookingservice.service;

import com.sportbook.bookingservice.client.CourtClient;
import com.sportbook.bookingservice.dto.BookingRequest;
import com.sportbook.bookingservice.dto.BookingResponse;
import com.sportbook.bookingservice.dto.CourtClientResponse;
import com.sportbook.bookingservice.entity.Booking;
import com.sportbook.bookingservice.enums.BookingStatus;
import com.sportbook.bookingservice.exception.BookingConflictException;
import com.sportbook.bookingservice.exception.BookingNotFoundException;
import com.sportbook.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CourtClient courtClient;

    @Transactional
    public BookingResponse create(BookingRequest request) {
        if (!request.getEndTime().isAfter(request.getStartTime())) {
            throw new IllegalArgumentException("O horário de término deve ser após o horário de início");
        }

        CourtClientResponse court = courtClient.findById(request.getCourtId());
        log.info("Quadra encontrada: {} - Status: {}", court.getName(), court.getStatus());

        if (!"AVAILABLE".equals(court.getStatus())) {
            throw new IllegalArgumentException("A quadra '" + court.getName() + "' não está disponível para agendamento");
        }

        boolean conflict = bookingRepository.existsConflict(
                request.getCourtId(),
                request.getBookingDate(),
                request.getStartTime(),
                request.getEndTime()
        );
        if (conflict) {
            throw new BookingConflictException();
        }

        long minutes = Duration.between(request.getStartTime(), request.getEndTime()).toMinutes();
        BigDecimal hours = BigDecimal.valueOf(minutes).divide(BigDecimal.valueOf(60));
        BigDecimal totalPrice = court.getPricePerHour().multiply(hours);

        Booking booking = Booking.builder()
                .courtId(request.getCourtId())
                .customerName(request.getCustomerName())
                .customerEmail(request.getCustomerEmail())
                .customerPhone(request.getCustomerPhone())
                .bookingDate(request.getBookingDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .totalPrice(totalPrice)
                .status(BookingStatus.CONFIRMED)
                .notes(request.getNotes())
                .build();

        Booking saved = bookingRepository.save(booking);
        log.info("Agendamento criado: id={}, quadra={}, data={}", saved.getId(), court.getName(), saved.getBookingDate());

        return BookingResponse.from(saved, court.getName());
    }

    @Transactional
    public BookingResponse cancel(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new IllegalArgumentException("Agendamento já está cancelado");
        }
        if (booking.getStatus() == BookingStatus.COMPLETED) {
            throw new IllegalArgumentException("Não é possível cancelar um agendamento já concluído");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        return BookingResponse.from(bookingRepository.save(booking));
    }

    @Transactional
    public BookingResponse complete(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));

        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new IllegalArgumentException("Apenas agendamentos confirmados podem ser concluídos");
        }

        booking.setStatus(BookingStatus.COMPLETED);
        return BookingResponse.from(bookingRepository.save(booking));
    }
}