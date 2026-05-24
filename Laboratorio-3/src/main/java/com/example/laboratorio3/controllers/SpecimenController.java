package com.example.laboratorio3.controllers;

import com.example.laboratorio3.domain.dto.request.CreateSpecimenRequest;
import com.example.laboratorio3.domain.dto.request.UpdateSpecimenRequest;
import com.example.laboratorio3.domain.dto.response.GeneralResponse;
import com.example.laboratorio3.domain.dto.response.PageableResponse;
import com.example.laboratorio3.domain.dto.response.SpecimenResponse;
import com.example.laboratorio3.services.SpecimenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/specimens")
@RequiredArgsConstructor
public class SpecimenController {

    private final SpecimenService specimenService;


    private <T> ResponseEntity<GeneralResponse<T>> buildResponse(
            HttpStatus status,
            String message,
            T data,
            HttpServletRequest request
    ) {
        GeneralResponse<T> response = GeneralResponse.<T>builder()
                .message(message)
                .statusCode(status.value())
                .status(status)
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .data(data)
                .build();

        return new ResponseEntity<>(response, status);
    }



    @PostMapping
    public ResponseEntity<GeneralResponse<SpecimenResponse>> createSpecimen(
            @Valid @RequestBody CreateSpecimenRequest requestDto,
            HttpServletRequest request
    ) {
        SpecimenResponse data = specimenService.createSpecimen(requestDto);
        return buildResponse(HttpStatus.CREATED, "Specimen successfully registered in Hyrule Records", data, request);
    }

    @GetMapping
    public ResponseEntity<GeneralResponse<PageableResponse<SpecimenResponse>>> getAllSpecimens(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "asc", required = false) String sortOrder,
            HttpServletRequest request
    ) {
        PageableResponse<SpecimenResponse> data = specimenService.getAllSpecimens(page, size, sortBy, sortOrder);
        return buildResponse(HttpStatus.OK, "Specimens list retrieved successfully from Hyrule", data, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<SpecimenResponse>> getSpecimenById(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        SpecimenResponse data = specimenService.getSpecimenById(id);
        return buildResponse(HttpStatus.OK, "Specimen details found", data, request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse<SpecimenResponse>> updateSpecimen(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateSpecimenRequest requestDto,
            HttpServletRequest request
    ) {
        SpecimenResponse data = specimenService.updateSpecimen(id, requestDto);
        return buildResponse(HttpStatus.OK, "Specimen updated successfully", data, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse<SpecimenResponse>> deleteSpecimen(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        SpecimenResponse data = specimenService.deleteSpecimen(id);
        return buildResponse(HttpStatus.OK, "Specimen purged from Hyrule Records", data, request);
    }
}