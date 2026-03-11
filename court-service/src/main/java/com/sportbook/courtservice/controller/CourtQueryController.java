package com.sportbook.courtservice.controller;

import com.sportbook.courtservice.dto.ApiResponse;
import com.sportbook.courtservice.dto.CourtResponse;
import com.sportbook.courtservice.enums.SportType;
import com.sportbook.courtservice.service.CourtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courts")
@RequiredArgsConstructor
@Tag(name = "Courts - Consultas", description = "Consultas de Quadras Esportivas")
public class CourtQueryController {

    private final CourtService courtService;

    @GetMapping
    @Operation(summary = "Listar todas as quadras")
    public ResponseEntity<ApiResponse<List<CourtResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(courtService.findAll()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar quadra por ID")
    public ResponseEntity<ApiResponse<CourtResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(courtService.findById(id)));
    }

    @GetMapping("/available")
    @Operation(summary = "Listar quadras disponíveis")
    public ResponseEntity<ApiResponse<List<CourtResponse>>> findAvailable() {
        return ResponseEntity.ok(ApiResponse.success(courtService.findAvailable()));
    }

    @GetMapping("/sport/{sportType}")
    @Operation(summary = "Listar quadras por tipo de esporte")
    public ResponseEntity<ApiResponse<List<CourtResponse>>> findBySportType(@PathVariable SportType sportType) {
        return ResponseEntity.ok(ApiResponse.success(courtService.findBySportType(sportType)));
    }

    @GetMapping("/sport/{sportType}/available")
    @Operation(summary = "Listar quadras disponíveis por tipo de esporte")
    public ResponseEntity<ApiResponse<List<CourtResponse>>> findAvailableBySportType(@PathVariable SportType sportType) {
        return ResponseEntity.ok(ApiResponse.success(courtService.findAvailableBySportType(sportType)));
    }
}