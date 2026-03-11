package com.sportbook.courtservice.exception;

public class CourtAlreadyExistsException extends RuntimeException {
    public CourtAlreadyExistsException(String name, String location) {
        super("Já existe uma quadra com o nome '" + name + "' no local '" + location + "'");
    }
}
