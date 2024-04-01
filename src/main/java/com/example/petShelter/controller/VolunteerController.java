package com.example.petShelter.controller;

import com.example.petShelter.model.Visitor;
import com.example.petShelter.service.VolunteerService;
import com.example.petShelter.model.Volunteer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author bornartem
 */
@RestController
@RequestMapping("/volunteer")
public class VolunteerController {
    private final VolunteerService volunteerService;

    @Autowired
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping("/create")
    public Volunteer create(@RequestBody Volunteer volunteer) {
        return volunteerService.create(volunteer);
    }

    @GetMapping("/read{id}")
    public Volunteer read(@PathVariable long id) {
        return volunteerService.read(id);
    }

    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "update volunteer",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )
            )
    )
    @PutMapping("/update")
    public Volunteer update(@RequestBody Volunteer volunteer) {
        return volunteerService.update(volunteer);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "delete volunteer from db by id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Visitor.class)
                    )
            )
    })
    @DeleteMapping("/delete{id}")
    public void delete(@PathVariable long id) {
        volunteerService.delete(id);
    }

    @GetMapping("/all")
    public List<Volunteer> getAll() {
        return volunteerService.getAll();
    }
}
