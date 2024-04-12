package com.example.petShelter.controller;

import com.example.petShelter.model.Animals;
import com.example.petShelter.model.Shelters;
import com.example.petShelter.service.AnimalsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * The class consists of methods for REST API in order to make CRUD and
 other commands for the entity "Animals"

 *
 * @author Khilola Kushbakova
 */


@RestController
@RequestMapping("/animals")
@Slf4j
public class AnimalsController {

    @Autowired
    private final AnimalsService animalsService;

    public AnimalsController(AnimalsService animalsService) {
        this.animalsService = animalsService;
    }


    @Operation(summary = "Find all animals of a certain shelter",
            description = "Returns a list of animals belonging to a specific shelter based on the shelterId provided")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of animals"),
            @ApiResponse(responseCode = "404", description = "Shelter with the provided shelterId not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Shelters.class)
                    )
            )
    })
    @GetMapping("/getByShelterID/{shelterId}")
    public List<Animals> findAllAnimalsOfCertainShelter(@PathVariable Long shelterId) {
        return animalsService.findAllAnimalsOfCertainShelter(shelterId);
    }


//    @Operation(summary = "Find all animals of a certain shelter",
//            description = "Returns a list of animals belonging to a specific shelter based on the shelterId provided")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of animals"),
//            @ApiResponse(responseCode = "404", description = "Shelter with the provided shelterId not found",
//                    content = @Content(
//                            mediaType = MediaType.APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = Shelters.class)
//                    )
//            )
//    })
//    @GetMapping("{shelterId}")
//    public List<Animals> findAllAnimalsOfCertainShelter(@PathVariable Long shelterId) {
//        return animalsService.findAllAnimalsOfCertainShelter(shelterId);
//    }


    @Operation(summary = "Remove an animal from the list",
            description = "Deletes the animal with the specified animalId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal removed successfully")
    })
    @DeleteMapping("{animalId}")
    public ResponseEntity removeAnimal(@PathVariable long animalId) {
        animalsService.removeAnimal(animalId);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Find animal by ID",
            description = "Returns the animal with the specified animalId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the animal"),
            @ApiResponse(responseCode = "404", description = "Animal with the provided animalId not found")
    })
    @GetMapping("/getByAnimalID/{animalId}")
    public /*ResponseEntity<Animals>*/ String findAnimalById(@PathVariable long animalId) {
        log.info("animal id is {}", animalId);
        Animals animal = animalsService.findAnimalById(animalId);
        if (animal == null) {
//            return ResponseEntity.notFound().build();
            return null;
        }
        log.info("found animal is {}", animal);
//        return ResponseEntity.ok(animal);
        return animal.toString();
    }


    @Operation(summary = "Find animals by status",
            description = "Returns a collection of animals with a specific busy status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of animals")
    })
    @GetMapping("/search-animals-by-status/{busyAnimalStatus}")
    public Collection<Animals> findAnimalsByStatus(@PathVariable boolean busyAnimalStatus) {
        return animalsService.findAnimalsByStatus(busyAnimalStatus);

    }

    @Operation(summary = "Find animals by type",
            description = "Returns a collection of animals of a specified type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of animals")
    })
    @GetMapping("/search-animals-by-type/{animalType}")
    public Collection<Animals> findAnimalsByType(String animalType) {
        return animalsService.findAnimalsByType(animalType);
    }


//    @Operation(summary = "Add a new animal",
//            description = "Creates and adds a new animal to the list")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "New animal added successfully",
//                    content = @Content(
//                            mediaType = MediaType.APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = Shelters.class)
//                    )
//            )
//    })
    @PostMapping
    public Animals addNewAnimal(@RequestBody Animals animal) {
        return animalsService.addNewAnimal(animal);
    }


    @Operation(summary = "Update animal information",
            description = "Updates the information of an existing animal based on the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal information updated successfully"),
            @ApiResponse(responseCode = "404", description = "Animal with the provided data not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Shelters.class)
                    )
            )
    })
    public ResponseEntity<Animals> changeAnimalInfo(@RequestBody Animals animal) {
        Animals changeAnimalInfo = animalsService.changeAnimalInfo(animal);
        if (changeAnimalInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(changeAnimalInfo);
    }

}
