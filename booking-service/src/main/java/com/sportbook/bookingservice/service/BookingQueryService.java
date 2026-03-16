package com.sportbook.bookingservice.service;

import com.sportbook.bookingservice.dto.BookingResponse;
import com.sportbook.bookingservice.entity.Booking;
import com.sportbook.bookingservice.exception.BookingNotFoundException;
import com.sportbook.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingQueryService {

    private final BookingRepository bookingRepository;

    public List<BookingResponse> findAll() {
        return bookingRepository.findAll()
                .stream()
                .map(BookingResponse::from)
                .toList();
    }

    public BookingResponse findById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));

        return BookingResponse.from(booking);
    }

    public List<BookingResponse> findByCourtId(Long courtId) {
        return bookingRepository.findByCourtId(courtId)
                .stream()
                .map(BookingResponse::from)
                .toList();
    }

    public List<BookingResponse> findByCustomerEmail(String email) {
        return bookingRepository.findByCustomerEmail(email)
                .stream()
                .map(BookingResponse::from)
                .toList();
    }

    public List<BookingResponse> findByDate(LocalDate date) {
        return bookingRepository.findByBookingDate(date)
                .stream()
                .map(BookingResponse::from)
                .toList();
    }
}