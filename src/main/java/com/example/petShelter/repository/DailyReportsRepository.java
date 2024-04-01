package com.example.petShelter.repository;

import com.example.petShelter.model.DailyReports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DailyReportsRepository extends JpaRepository<DailyReports, Long> {
    List<DailyReports> findAllByLocalDateTime(LocalDateTime dateTime);
}
