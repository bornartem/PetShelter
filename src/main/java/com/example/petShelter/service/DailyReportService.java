package com.example.petShelter.service;

import com.example.petShelter.command.Command;
import com.example.petShelter.exception.NotFoundInDB;
import com.example.petShelter.model.Clients;
import com.example.petShelter.model.DailyReports;
import com.example.petShelter.repository.ClientsRepository;
import com.example.petShelter.repository.DailyReportRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


/**
 * Service class which consist of business logic of Reports from users
 *
 * @author Khilola Kushbakova
 */
@Slf4j
@Service
public class DailyReportService {

    private final DailyReportRepository dailyReportRepository;

    @Autowired
    public DailyReportService(DailyReportRepository dailyReportRepository) {
        this.dailyReportRepository = dailyReportRepository;
    }

    /**
     * Creates a new daily report.
     *
     * @param dailyReports The DailyReports object to be created.
     * @return The created DailyReports object.
     */
    public DailyReports save(DailyReports dailyReports) {
        return dailyReportRepository.save(dailyReports);
    }

    /**
     * Find a daily report by its ID.
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
            log.info("Deleted daily report with id = {}", id);
        } else {
            log.error("There is no report with id = {}", id);
            throw new NotFoundInDB("Daily report not found");
        }
    }

    /**
     * Find daily reports by not checked by boolean.
     *
     * @return not checked reports
     */
    public List<DailyReports> findByNotCheck() {
        return dailyReportRepository.findByIsCheckFalse();
    }

    /**
     * Update daily reports by not checked by boolean.
     *
     * @return all reports
     */
    public DailyReports update(DailyReports report) {
        return dailyReportRepository.save(report);
    }

    /**
     * Get all daily reports
     *
     * @return all reports
     */
    public List<DailyReports> getAll() {
        return dailyReportRepository.findAll();
    }
}




