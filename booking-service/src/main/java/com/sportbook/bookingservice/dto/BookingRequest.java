package com.sportbook.bookingservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingRequest {

    @NotNull(message = "ID da quadra é obrigatório")
    private Long courtId;

    @NotBlank(message = "Nome do cliente é obrigatório")
    @Size(min = 3, max = 100)
    private String customerName;

    @NotBlank(message = "Email do cliente é obrigatório")
    @Email(message = "Email inválido")
    private String customerEmail;

    private String customerPhone;

    @NotNull(message = "Data do agendamento é obrigatória")
    @Future(message = "A data deve ser futura")
    private LocalDate bookingDate;

    @NotNull(message = "Horário de início é obrigatório")
    private LocalTime startTime;

    @NotNull(message = "Horário de término é obrigatório")
    private LocalTime endTime;

    private String notes;
}