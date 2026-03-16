package com.sportbook.bookingservice.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(Long id) {
        super("Agendamento não encontrado com id: " + id);
    }
}