package com.example.petShelter.controller;

import com.example.petShelter.model.Animals;
import com.example.petShelter.service.AnimalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalsController {

    @Autowired
    private final AnimalsService animalsService;

    public AnimalsController(AnimalsService animalsService) {
        this.animalsService = animalsService;
    }

    @GetMapping("{shelterId}")
    public List<Animals> findAllAnimalsOfCertainShelter(@PathVariable Long shelterId) {
        return animalsService.findAllAnimalsOfCertainShelter(shelterId);
    }

    @DeleteMapping("{animalId}")
    public ResponseEntity removeAnimal(@PathVariable long animalId) {
        animalsService.removeAnimal(animalId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{animalId}")
    public ResponseEntity<Animals> findAnimalById(@PathVariable long animalId) {
        Animals animal = animalsService.findAnimalById(animalId);
        if (animal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animal);
    }

    @GetMapping("/search-animals-by-status/{busyAnimalStatus}")
    public Collection<Animals> findAnimalsByStatus(@PathVariable boolean busyAnimalStatus) {
        return animalsService.findAnimalsByStatus(busyAnimalStatus);

    }

    @GetMapping("/search-animals-by-type/{animalType}")
    public Collection<Animals> findAnimalsByType(String animalType) {
        return animalsService.findAnimalsByType(animalType);
    }

    @PostMapping
    public Animals addNewAnimal(@RequestBody Animals animal) {
        return animalsService.addNewAnimal(animal);
    }

    public ResponseEntity<Animals> changeAnimalInfo(@RequestBody Animals animal) {
        Animals changeAnimalInfo = animalsService.changeAnimalInfo(animal);
        if (changeAnimalInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(changeAnimalInfo);

    }

}
