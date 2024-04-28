package com.example.petShelter.repository;

import com.example.petShelter.model.DailyReports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyReportRepository extends JpaRepository <DailyReports, Long> {
    List<DailyReports> findByIsCheckFalse();
}
