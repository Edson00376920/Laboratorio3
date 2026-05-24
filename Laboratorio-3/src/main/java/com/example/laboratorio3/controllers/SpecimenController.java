package com.example.laboratorio3.controllers;

import com.example.laboratorio3.domain.dto.response.PageableResponse;
import com.example.laboratorio3.domain.dto.response.SpecimenResponse;
import com.example.laboratorio3.services.SpecimenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/specimens")
@RequiredArgsConstructor
public class SpecimenController {

    private final SpecimenService specimenService;

    @GetMapping
    public ResponseEntity<PageableResponse<SpecimenResponse>> getAllSpecimens(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "asc", required = false) String sortOrder
    ) {
        PageableResponse<SpecimenResponse> response = specimenService.getAllSpecimens(page, size, sortBy, sortOrder);
        return ResponseEntity.ok(response);
    }
}
