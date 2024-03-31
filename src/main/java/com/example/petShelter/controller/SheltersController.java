package com.example.petShelter.controller;

import com.example.petShelter.model.Shelters;
import com.example.petShelter.service.SheltersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/shelters")
public class SheltersController {
    @Autowired
    private final SheltersService sheltersService;

    public SheltersController(SheltersService sheltersService) {
        this.sheltersService = sheltersService;
    }

    @GetMapping("/showLocation")
    public ResponseEntity<String> showLocation(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude) {

        String location = sheltersService.showLocation(latitude, longitude);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @GetMapping("/all-shelters")
    public ResponseEntity<Collection<Shelters>> listAllShelters() {
        return ResponseEntity.ok(sheltersService.listAllShelters());
    }

    @PostMapping
    public Shelters addShelter(@RequestBody Shelters shelter) {
        return sheltersService.addShelter(shelter);
    }

    @GetMapping("{shelterId}")
    public ResponseEntity<Shelters> findShelterById(long shelterId) {
        Shelters shelter = sheltersService.findShelterById(shelterId);
        if (shelter == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shelter);
    }

    @DeleteMapping("{shelterId}")
    public ResponseEntity removeShelter(@PathVariable long shelterId) {
        sheltersService.removeShelter(shelterId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/show-contacts")
    public String showContacts(long shelterId) {
        return sheltersService.showContacts(shelterId);
    }

    @GetMapping("/show-address")
    public String showAddress(long shelterId) {
        return sheltersService.showAddress(shelterId);
    }

    @GetMapping("/show-security-number")
    public String showSecurityNumber(long shelterId) {
        return sheltersService.showSecurityNumber(shelterId);
    }

    @PutMapping()
    public ResponseEntity<Shelters> changeShelterInfo(@RequestBody Shelters shelter) {
        Shelters changeShelterInfo = sheltersService.changeShelterInfo(shelter);
        if (changeShelterInfo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(changeShelterInfo);
    }
}
