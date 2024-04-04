package com.example.petShelter.repository;

import com.example.petShelter.model.Volunteers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteersRepository extends JpaRepository<Volunteers, Long> {
}
