package com.example.petShelter.controller;

import com.example.petShelter.model.Clients;
import com.example.petShelter.model.DailyReports;
import com.example.petShelter.service.DailyReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

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

    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "update client",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Clients.class)
                    )
            )
    )
    @PutMapping("/update")
    public ResponseEntity<DailyReports> update(@org.springframework.web.bind.annotation.RequestBody DailyReports dailyReports) {
        DailyReports updatedDailyReports = dailyReportService.update(dailyReports);
        if (updatedDailyReports == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedDailyReports);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "find all daily reports from db",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DailyReports[].class)
                    )
            )
    })
    @GetMapping("/all")
    public List<DailyReports> getAll() {
        return dailyReportService.getAll();
    }

    @GetMapping("/not-checked-reports")
    public List<DailyReports> findByNotChecked(){
        return dailyReportService.findByNotCheck();
    }
}