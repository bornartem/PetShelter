package com.example.petShelter.controller;

import com.example.petShelter.model.Shelters;
import com.example.petShelter.service.SheltersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * The class consists of methods for REST API in order to make CRUD and other commands for the entity "Shelters"
 *
 * @author Khilola Kushbakova
 */


@RestController
@RequestMapping("/shelters")
public class SheltersController {
    @Autowired
    private final SheltersService sheltersService;

    public SheltersController(SheltersService sheltersService) {
        this.sheltersService = sheltersService;
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "a location based on the provided latitude and longitude"
            )
    })
    @GetMapping("/showLocation")
    public ResponseEntity<String> showLocation(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude) {

        String location = sheltersService.showLocation(latitude, longitude);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of shelters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/all-shelters")
    public ResponseEntity<Collection<Shelters>> listAllShelters() {
        return ResponseEntity.ok(sheltersService.listAllShelters());
    }


    @Operation(summary = "Add a new shelter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shelter added successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Shelters.class)
                    )
            )
    })
    @PostMapping
    public Shelters addShelter(@RequestBody Shelters shelter) {
        return sheltersService.addShelter(shelter);
    }


    @Operation(summary = "Find shelter by ID", description = "Find a shelter by its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shelter found"),
            @ApiResponse(responseCode = "404", description = "Shelter not found")
    })
    @GetMapping("{shelterId}")
    public ResponseEntity<Shelters> findShelterById(long shelterId) {
        Shelters shelter = sheltersService.findShelterById(shelterId);
        if (shelter == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shelter);
    }


    @Operation(summary = "Remove a shelter by ID",
            description = "Removes a shelter from the database using the specified ID")
    @DeleteMapping("{shelterId}")
    public ResponseEntity removeShelter(@PathVariable long shelterId) {
        sheltersService.removeShelter(shelterId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/show-contacts")
    public String showContacts(@Parameter(description = "the contact details of Shelter", example = "shelter@gmail.com, 8935-888-9999")
                               @PathVariable long shelterId) {
        return sheltersService.showContacts(shelterId);
    }

    @GetMapping("/show-address")
    public String showAddress(@Parameter(description = "the address of Shelter", example = "15-37,Maskavas street," +
            " Riga, Latvia , LV-1236")
                              @PathVariable long shelterId) {
        return sheltersService.showAddress(shelterId);
    }

    @GetMapping("/show-security-number")
    public String showSecurityNumber(@Parameter(description = "Shelter's security number", example = "8935-888-9999")
                                     @PathVariable long shelterId) {
        return sheltersService.showSecurityNumber(shelterId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "information about shelter has changed"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PutMapping()
    public ResponseEntity<Shelters> changeShelterInfo(@RequestBody Shelters shelter) {
        Shelters changeShelterInfo = sheltersService.changeShelterInfo(shelter);
        if (changeShelterInfo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(changeShelterInfo);
    }

}
