package com.sportbook.bookingservice.controller;


import com.sportbook.bookingservice.dto.ApiResponse;
import com.sportbook.bookingservice.dto.BookingRequest;
import com.sportbook.bookingservice.dto.BookingResponse;
import com.sportbook.bookingservice.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@Tag(name = "Bookings - Comandos", description = "Operações de escrita de Agendamentos")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @Operation(summary = "Criar novo agendamento")
    public ResponseEntity<ApiResponse<BookingResponse>> create(@Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Agendamento criado com sucesso", response));
    }

    @PostMapping("/{id}/cancel")
    @Operation(summary = "Cancelar agendamento")
    public ResponseEntity<ApiResponse<BookingResponse>> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Agendamento cancelado", bookingService.cancel(id)));
    }
    @PatchMapping("/{id}/complete")
    @Operation(summary = "Concluir agendamento")
    public ResponseEntity<ApiResponse<BookingResponse>> complete(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Agendamento concluído", bookingService.complete(id)));
    }
}
