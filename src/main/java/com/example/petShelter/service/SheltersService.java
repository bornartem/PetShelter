package com.example.petShelter.service;

import com.example.petShelter.model.Shelters;
import com.example.petShelter.repository.SheltersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public class SheltersService {
    @Autowired
    private final SheltersRepository sheltersRepository;

    public SheltersService(SheltersRepository sheltersRepository) {
        this.sheltersRepository = sheltersRepository;
    }

    public Collection<Shelters> listAllShelters() {
        log.info("Was invoked method for listAllShelters");
        Collection<Shelters> shelters = sheltersRepository.findAll();
        if (shelters == null) {
            log.error("There is no any shelter ");
        }
        if (shelters.isEmpty()) {
            log.warn("No shelters found, add a new shelter");
        }

        return shelters;
    }

    public Shelters addShelter(Shelters shelter){
        log.info("Was invoked method for addShelter");
        return sheltersRepository.save(shelter);
    }
    public Shelters findShelterById(long shelterId) {
        Shelters shelter = sheltersRepository.findById(shelterId).orElse(null);
        log.info("Was invoked method for findShelterById");
        if (shelter == null) {
            log.error("There is no shelter with id = {}", shelterId);
        }

        return shelter;
    }

    public void removeShelter(long shelterId) {
        Shelters shelter = sheltersRepository.findById(shelterId).orElse(null);
        sheltersRepository.deleteById(shelterId);
        log.info("Was invoked method for removeShelter");
        if (shelter == null) {
            log.error("There is no shelter with id = {}", shelterId);

        }
    }
    public String showContacts (long shelterId){
        log.info("Was invoked method for showContacts");
        return sheltersRepository.getReferenceById(shelterId).getContacts();

    }
    public String showAddress (long shelterId) {
        log.info("Was invoked method for showAddress");
        return sheltersRepository.getReferenceById(shelterId).getAddress();
    }


    public String showLocation(double latitude, double longitude) {
        String location = "Latitude: " + latitude + ", Longitude: " + longitude;
        return location;
    }


    public String showSecurityNumber (long shelterId) {
        log.info("Was invoked method for giveSecurityNumber");
        return sheltersRepository.getReferenceById(shelterId).getSecurityContacts();
    }
    public Shelters changeShelterInfo(Shelters shelter) {
        log.info("Was invoked method for change changeShelterInfo");
        return sheltersRepository.save(shelter);

    }
}
