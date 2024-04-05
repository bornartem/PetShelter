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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The class consists of methods for REST API in order to make CRUD and
 other commands for the entity "Volunteers"
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
    public Volunteers create(@RequestBody Volunteers volunteers) {
        return volunteersService.create(volunteers);
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
    public Volunteers update(@RequestBody Volunteers volunteers) {
        return volunteersService.update(volunteers);
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
