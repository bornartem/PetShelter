package com.example.petShelter.repository;

import com.example.petShelter.model.DailyReports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DailyReportRepository extends JpaRepository<DailyReports, Long> {
    List<DailyReports> findByIsCheckFalse();
}
