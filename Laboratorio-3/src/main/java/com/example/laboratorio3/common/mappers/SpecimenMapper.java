package com.example.laboratorio3.common.mappers;

import com.example.laboratorio3.domain.dto.request.CreateSpecimenRequest;
import com.example.laboratorio3.domain.dto.request.UpdateSpecimenRequest;
import com.example.laboratorio3.domain.dto.response.PageableResponse;
import com.example.laboratorio3.domain.dto.response.SpecimenResponse;
import com.example.laboratorio3.domain.entities.Specimen;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class SpecimenMapper {

    public Specimen toEntityCreate(CreateSpecimenRequest request) {
        return Specimen.builder()
                .name(request.getName())
                .region(request.getRegion())
                .dangerLevel(request.getDangerLevel())
                .isFriendly(request.getIsFriendly())
                .build();
    }

    public Specimen toEntityUpdate(UpdateSpecimenRequest request, UUID id) {
        return Specimen.builder()
                .id(id)
                .name(request.getName())
                .region(request.getRegion())
                .dangerLevel(request.getDangerLevel())
                .isFriendly(request.getIsFriendly())
                .build();
    }

    public SpecimenResponse toDto(Specimen specimen) {
        return SpecimenResponse.builder()
                .id(specimen.getId())
                .name(specimen.getName())
                .region(specimen.getRegion())
                .dangerLevel(specimen.getDangerLevel())
                .isFriendly(specimen.getIsFriendly())
                .build();
    }

    public PageableResponse<SpecimenResponse> toPageableResponse(org.springframework.data.domain.Page<Specimen> page) {
        List<SpecimenResponse> content = page.getContent().stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return PageableResponse.<SpecimenResponse>builder()
                .content(content)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .isLast(page.isLast())
                .build();
    }
}