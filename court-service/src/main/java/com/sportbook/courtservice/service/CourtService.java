package com.sportbook.courtservice.service;

import com.sportbook.courtservice.dto.CourtRequest;
import com.sportbook.courtservice.dto.CourtResponse;
import com.sportbook.courtservice.entity.Court;
import com.sportbook.courtservice.enums.CourtStatus;
import com.sportbook.courtservice.enums.SportType;
import com.sportbook.courtservice.exception.CourtAlreadyExistsException;
import com.sportbook.courtservice.exception.CourtNotFoundException;
import com.sportbook.courtservice.repository.CourtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourtService {

    private final CourtRepository courtRepository;

    @Transactional(readOnly = true)
    public List<CourtResponse> findAll() {
        return courtRepository.findAll()
                .stream()
                .map(CourtResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public CourtResponse findById(Long id) {
        Court court = courtRepository.findById(id)
                .orElseThrow(() -> new CourtNotFoundException(id));
        return CourtResponse.from(court);
    }

    @Transactional(readOnly = true)
    public List<CourtResponse> findAvailable() {
        return courtRepository.findByStatus(CourtStatus.AVAILABLE)
                .stream()
                .map(CourtResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CourtResponse> findBySportType(SportType sportType) {
        return courtRepository.findBySportType(sportType)
                .stream()
                .map(CourtResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CourtResponse> findAvailableBySportType(SportType sportType) {
        return courtRepository.findBySportTypeAndStatus(sportType, CourtStatus.AVAILABLE)
                .stream()
                .map(CourtResponse::from)
                .toList();
    }

    public CourtResponse create(CourtRequest request) {
        if (courtRepository.existsByNameAndLocation(request.getName(), request.getLocation())) {
            throw new CourtAlreadyExistsException(request.getName(), request.getLocation());
        }

        Court court = Court.builder()
                .name(request.getName())
                .sportType(request.getSportType())
                .location(request.getLocation())
                .description(request.getDescription())
                .pricePerHour(request.getPricePerHour())
                .status(CourtStatus.AVAILABLE)
                .build();

        court = courtRepository.save(court);
        return CourtResponse.from(court);
    }

    public CourtResponse update(Long id, CourtRequest request) {
        Court court = courtRepository.findById(id)
                .orElseThrow(() -> new CourtNotFoundException(id));

        court.setName(request.getName());
        court.setSportType(request.getSportType());
        court.setLocation(request.getLocation());
        court.setDescription(request.getDescription());
        court.setPricePerHour(request.getPricePerHour());

        court = courtRepository.save(court);
        return CourtResponse.from(court);
    }

    public CourtResponse updateStatus(Long id, CourtStatus status) {
        Court court = courtRepository.findById(id)
                .orElseThrow(() -> new CourtNotFoundException(id));

        court.setStatus(status);

        court = courtRepository.save(court);
        return CourtResponse.from(court);
    }

    public void delete(Long id) {
        if (!courtRepository.existsById(id)) {
            throw new CourtNotFoundException(id);
        }

        courtRepository.deleteById(id);
    }
}