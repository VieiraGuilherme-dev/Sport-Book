package com.sportbook.courtservice.controller;

import com.sportbook.courtservice.dto.ApiResponse;
import com.sportbook.courtservice.dto.CourtRequest;
import com.sportbook.courtservice.dto.CourtResponse;
import com.sportbook.courtservice.enums.CourtStatus;
import com.sportbook.courtservice.service.CourtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courts")
@RequiredArgsConstructor
@Tag(name = "Courts - Comandos", description = "Operações de escrita de Quadras Esportivas")
public class CourtController {

    private final CourtService courtService;

    @PostMapping
    @Operation(summary = "Cadastrar nova quadra")
    public ResponseEntity<ApiResponse<CourtResponse>> create(@Valid @RequestBody CourtRequest request) {
        CourtResponse response = courtService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Quadra cadastrada com sucesso", response));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar quadra")
    public ResponseEntity<ApiResponse<CourtResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody CourtRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Quadra atualizada com sucesso", courtService.update(id, request)));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Atualizar status da quadra")
    public ResponseEntity<ApiResponse<CourtResponse>> updateStatus(
            @PathVariable Long id,
            @RequestParam CourtStatus status) {
        return ResponseEntity.ok(ApiResponse.success("Status atualizado com sucesso", courtService.updateStatus(id, status)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover quadra")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        courtService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Quadra removida com sucesso", null));
    }
}