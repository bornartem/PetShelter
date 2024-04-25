package com.example.petShelter.service;

import com.example.petShelter.model.DailyReports;
import com.example.petShelter.repository.DailyReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 *Service class which consist of business logic of Reports from users
 *
 * @author Khilola Kushbakova
 */
@Slf4j
@Service
public class DailyRepostService {

    @Autowired
    private DailyReportRepository dailyReportRepository;

    /**
     * Creates a new daily report.
     *
     * @param reports The DailyReports object to be created.
     * @return The created DailyReports object.
     */
    public DailyReports createDailyReport(DailyReports reports) {
        return dailyReportRepository.save(reports);
    }

    /**
     * Finds a daily report by its ID.
     *
     * @param id The ID of the daily report to find.
     * @return The DailyReports object if found, otherwise null.
     */
    public DailyReports findDailyReportById(long id) {
        return dailyReportRepository.findById(id).orElse(null);
    }

    /**
     * Deletes a daily report by its ID.
     *
     * @param id The ID of the daily report to delete.
     */
    public void deleteDailyReportById(long id) {
        if (dailyReportRepository.existsById(id)) {
            dailyReportRepository.deleteById(id);
            log.info("Was invoked method for deleteDailyReportById");
        } else {
            log.error("There is no report with id = {}", id);
            throw new RuntimeException();
        }
    }

    /**
     * Updates an existing daily report.
     *
     * @param reports The updated DailyReports object.
     * @return The updated DailyReports object.
     */
    public DailyReports changeDailyReport(DailyReports reports){
        return dailyReportRepository.save(reports);
    }
    }




