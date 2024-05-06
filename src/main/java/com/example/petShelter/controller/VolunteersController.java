package com.example.petShelter.controller;

import com.example.petShelter.model.Clients;
import com.example.petShelter.service.VolunteersService;
import com.example.petShelter.model.Volunteers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The class consists of methods for REST API in order to make CRUD and
 * other commands for the entity "Volunteers"
 *
 * @author bornartem
 */
@RestController
@RequestMapping("/volunteer")
public class VolunteersController {
    private final VolunteersService volunteersService;

    @Autowired
    public VolunteersController(VolunteersService volunteersService) {
        this.volunteersService = volunteersService;
    }

    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "create volunteer",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteers.class)
                    )
            )
    )
    @PostMapping("/create")
    public ResponseEntity<Volunteers> create(@RequestBody Volunteers volunteers) {
        Volunteers createdVolunteer = volunteersService.create(volunteers);
        if (createdVolunteer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(createdVolunteer);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "find volunteer from db by id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteers.class)
                    )
            )
    })
    @GetMapping("/read{id}")
    public Volunteers read(@PathVariable long id) {
        return volunteersService.read(id);
    }

    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "update volunteer",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteers.class)
                    )
            )
    )
    @PutMapping("/update")
    public ResponseEntity<Volunteers> update(@RequestBody Volunteers volunteers) {
        Volunteers foundVolunteer = volunteersService.update(volunteers);
        if (foundVolunteer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundVolunteer);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "delete volunteer from db by id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteers.class)
                    )
            )
    })
    @DeleteMapping("/delete{id}")
    public void delete(@PathVariable long id) {
        volunteersService.delete(id);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "find all volunteers from db",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteers[].class)
                    )
            )
    })
    @GetMapping("/all")
    public List<Volunteers> getAll() {
        return volunteersService.getAll();
    }

}
