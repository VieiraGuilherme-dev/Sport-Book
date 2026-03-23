package com.sportbook.courtservice.dto;

import com.sportbook.courtservice.enums.SportType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourtRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String name;

    @NotNull(message = "Tipo de esporte é obrigatório")
    private SportType sportType;

    @NotBlank(message = "Localização é obrigatória")
    private String location;

    @Size(max = 100, message = "Nome do prédio deve ter no máximo 100 caracteres")
    private String building;

    private String description;

    @NotNull(message = "Preço por hora é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    private BigDecimal pricePerHour;
}   