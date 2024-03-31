package com.example.petShelter.repository;

import com.example.petShelter.model.Animals;
import com.example.petShelter.model.DailyReports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AnimalsRepository extends JpaRepository<Animals, Long> {
}
