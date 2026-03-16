package com.sportbook.bookingservice.exception;

public class BookingConflictException extends RuntimeException {
    public BookingConflictException() {
        super("Já existe um agendamento para essa quadra nesse horário");
    }
}