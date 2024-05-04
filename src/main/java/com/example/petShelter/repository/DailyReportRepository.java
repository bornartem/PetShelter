package com.example.petShelter.repository;

import com.example.petShelter.model.DailyReports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DailyReportRepository extends JpaRepository<DailyReports, Long> {
    List<DailyReports> findByIsCheckFalse();
    @Query(value = "select count (*) from daily_report", nativeQuery = true)
    int getCountReports();
}
