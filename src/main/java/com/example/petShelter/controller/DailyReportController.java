package com.example.petShelter.controller;

import com.example.petShelter.model.DailyReports;
import com.example.petShelter.service.DailyReportService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * Class controller  which is using for API/ Swagger DailyRepost commands
 *
 * @author Khilola Kushbakova
 */
@RestController
@RequestMapping("/daily_reports")
public class DailyReportController {

    private final DailyReportService dailyReportService;

    public DailyReportController(DailyReportService dailyReportService) {
        this.dailyReportService = dailyReportService;
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Daily report created successfully"
            )
    })
    public ResponseEntity<DailyReports> createDailyReport(@Valid @RequestBody DailyReports report, File photoFile) {
        DailyReports createdReport = dailyReportService.createDailyReport(report, photoFile);
        return ResponseEntity.ok(createdReport);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Daily report found"
            )
    })
    public ResponseEntity<DailyReports> findDailyReportById(@PathVariable long id) {
        DailyReports foundReport = dailyReportService.findDailyReportById(id);
        return ResponseEntity.ok(foundReport);
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Daily report deleted"
            )
    })
    public ResponseEntity<Void> deleteDailyReportById(@PathVariable long id) {
        dailyReportService.deleteDailyReportById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Changes in daily report saved"
            )
    })
    public ResponseEntity<DailyReports> changeDailyReport(@Valid @RequestBody DailyReports report) {
        DailyReports updatedReport = dailyReportService.changeDailyReport(report);
        return ResponseEntity.ok(updatedReport);
    }
}