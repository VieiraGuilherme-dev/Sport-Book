package com.sportbook.courtservice.exception;

public class CourtNotFoundException extends RuntimeException {
    public CourtNotFoundException(Long id) {
        super("Quadra não encontrada com id: " + id);
    }
}
