package com.sportbook.bookingservice.exception;

public class CourtServiceUnavailableException extends RuntimeException {
    public CourtServiceUnavailableException(String message) {
        super(message);
    }
}