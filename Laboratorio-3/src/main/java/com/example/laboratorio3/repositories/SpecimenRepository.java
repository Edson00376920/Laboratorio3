package com.example.laboratorio3.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpecimenRepository<Specimen> extends JpaRepository<Specimen, UUID> {
}
