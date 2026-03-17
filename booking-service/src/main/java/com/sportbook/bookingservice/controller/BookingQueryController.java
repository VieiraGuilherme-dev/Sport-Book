package com.sportbook.bookingservice.controller;



import com.sportbook.bookingservice.dto.ApiResponse;
import com.sportbook.bookingservice.dto.BookingResponse;
import com.sportbook.bookingservice.service.BookingQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@Tag(name = "Bookings - Consultas", description = "Consultas de Agendamentos")
public class BookingQueryController {

    private final BookingQueryService bookingQueryService;

    @GetMapping
    @Operation(summary = "Listar todos os agendamentos")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(bookingQueryService.findAll()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar agendamento por ID")
    public ResponseEntity<ApiResponse<BookingResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(bookingQueryService.findById(id)));
    }

    @GetMapping("/court/{courtId}")
    @Operation(summary = "Listar agendamentos por quadra")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> findByCourtId(@PathVariable Long courtId) {
        return ResponseEntity.ok(ApiResponse.success(bookingQueryService.findByCourtId(courtId)));
    }

    @GetMapping("/customer")
    @Operation(summary = "Listar agendamentos por email do cliente")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(ApiResponse.success(bookingQueryService.findByCustomerEmail(email)));
    }

    @GetMapping("/date/{date}")
    @Operation(summary = "Listar agendamentos por data")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> findByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(ApiResponse.success(bookingQueryService.findByDate(date)));
    }

}
