package com.example.petShelter.controller;

import com.example.petShelter.model.DailyReports;
import com.example.petShelter.service.DailyRepostService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Class controller  which is using for API/ Swagger DailyRepost commands
 *
 * @author Khilola Kushbakova
 */
@RestController
@RequestMapping("/daily_reports")
public class DailyReportController {
    @Autowired
    private DailyRepostService dailyRepostService;


    @PostMapping
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Daily report created successfully"
            )
    })
    public DailyReports createDailyReport(@RequestBody DailyReports reports) {
        return dailyRepostService.createDailyReport(reports);
    }


    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Daily report found"
            )
    })
    public DailyReports findDailyReportById(@PathVariable long id) {
        return dailyRepostService.findDailyReportById(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Daily report deleted"
            )
    })
    public void deleteDailyReportById(@PathVariable long id) {
        dailyRepostService.deleteDailyReportById(id);
    }


    @PutMapping
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Changes in daily report saved"
            )
    })
    public DailyReports changeDailyReport(@RequestBody DailyReports reports) {
        return dailyRepostService.changeDailyReport(reports);
    }
}
