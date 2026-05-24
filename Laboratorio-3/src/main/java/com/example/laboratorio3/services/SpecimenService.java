package com.example.laboratorio3.services;

import com.example.laboratorio3.domain.dto.request.CreateSpecimenRequest;
import com.example.laboratorio3.domain.dto.request.UpdateSpecimenRequest;
import com.example.laboratorio3.domain.dto.response.PageableResponse;
import com.example.laboratorio3.domain.dto.response.SpecimenResponse;

import java.util.UUID;

public interface SpecimenService {
    SpecimenResponse createSpecimen(CreateSpecimenRequest request);
    PageableResponse getAllSpecimens(int page, int size, String sortBy, String sortOrder);
    SpecimenResponse getSpecimenById(UUID id);
    SpecimenResponse updateSpecimen(UUID id, UpdateSpecimenRequest request);
    SpecimenResponse deleteSpecimen(UUID id);
}
