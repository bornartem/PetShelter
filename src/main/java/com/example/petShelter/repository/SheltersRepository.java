package com.example.petShelter.repository;

import com.example.petShelter.model.Shelters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SheltersRepository extends JpaRepository<Shelters, Long> {
}
