package com.sportbook.bookingservice.client;


import com.sportbook.bookingservice.dto.ApiResponse;
import com.sportbook.bookingservice.dto.CourtClientResponse;
import com.sportbook.bookingservice.exception.CourtServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class CourtClient {

    private final RestTemplate restTemplate;

    @Value("${court-service.url}")
    private String courtServiceUrl;

    public CourtClientResponse findById(Long courtId) {
        String url = courtServiceUrl + "/api/v1/courts/" + courtId;
        log.info("Consultando court-service: GET {}", url);
        try {
            ResponseEntity<ApiResponse<CourtClientResponse>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ApiResponse<CourtClientResponse>>() {}
            );
            if (response.getBody() != null && response.getBody().getData() != null) {
                return response.getBody().getData();
            }
            throw new CourtServiceUnavailableException("Resposta inválida do court-service");
        } catch (HttpClientErrorException.NotFound e) {
            throw new CourtServiceUnavailableException("Quadra com id " + courtId + " não encontrada no court-service");
        } catch (Exception e) {
            log.error("Erro ao consultar court-service: {}", e.getMessage());
            throw new CourtServiceUnavailableException("court-service indisponível. Verifique se está rodando na porta 8081");
        }
    }
}