package com.example.laboratorio3.services.impl;

import com.example.laboratorio3.common.mappers.SpecimenMapper;
import com.example.laboratorio3.domain.dto.request.CreateSpecimenRequest;
import com.example.laboratorio3.domain.dto.request.UpdateSpecimenRequest;
import com.example.laboratorio3.domain.dto.response.PageableResponse;
import com.example.laboratorio3.domain.dto.response.SpecimenResponse;
import com.example.laboratorio3.domain.entities.Specimen;
import com.example.laboratorio3.exceptions.ResourceNotFoundException;
import com.example.laboratorio3.repositories.SpecimenRepository;
import com.example.laboratorio3.services.SpecimenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecimenServiceImpl implements SpecimenService {
    private final SpecimenRepository specimenRepository;
    private final SpecimenMapper specimenMapper;

    @Override
    @Transactional
    public SpecimenResponse createSpecimen(CreateSpecimenRequest request) {
        return specimenMapper.toDto(
                (Specimen) specimenRepository.save(specimenMapper.toEntityCreate(request))
        );
    }


    @Override
    public PageableResponse<SpecimenResponse> getAllSpecimens(int page, int size, String sortBy, String sortOrder) {

        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.DESC.name())
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Specimen> specimenPage = specimenRepository.findAll(pageable);

        return specimenMapper.toPageableResponse(specimenPage);
    }

    @Override
    public SpecimenResponse getSpecimenById(UUID id) {
        return specimenMapper.toDto(specimenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specimen not found in Hyrule Records"))
        );
    }

    @Override
    @Transactional
    public SpecimenResponse updateSpecimen(UUID id, UpdateSpecimenRequest request) {
        this.getSpecimenById(id);
        return specimenMapper.toDto((Specimen)specimenRepository.save(specimenMapper.toEntityUpdate(request, id)));
    }

    @Transactional
    public SpecimenResponse deleteSpecimen(UUID id) {
        SpecimenResponse existSpecimen = this.getSpecimenById(id);
        specimenRepository.deleteById(id);
        return existSpecimen;
    }
}