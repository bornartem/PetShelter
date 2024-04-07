package com.example.petShelter.service;

import com.example.petShelter.model.Shelters;
import com.example.petShelter.repository.SheltersRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * The class consists of logic of the project, which has the methods  to work with "Shelters" entity
 *
 * @author Khilola Kushbakova
 */


@Service
public class SheltersService {

    @Autowired
    private final SheltersRepository sheltersRepository;

    private Logger log = LoggerFactory.getLogger(SheltersService.class);

    public SheltersService(SheltersRepository sheltersRepository) {
        this.sheltersRepository = sheltersRepository;
    }


    /**
     * Method to list all shelters.
     *
     * @return a collection of all shelters, or null if no shelters are found
     */
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

    public Shelters addShelter(Shelters shelter) {
        log.info("Was invoked method for addShelter");
        return sheltersRepository.save(shelter);
    }

    /**
     * Method to find a shelter by its identifier.
     *
     * @param shelterId the identifier of the shelter to find
     * @return the found shelter or null if the shelter was not found
     */
    public Shelters findShelterById(long shelterId) {
        Shelters shelter = sheltersRepository.findById(shelterId).orElse(null);
        log.info("Was invoked method for findShelterById");
        if (shelter == null) {
            log.error("There is no shelter with id = {}", shelterId);
        }

        return shelter;
    }

    /**
     * Removes a shelter with the given shelterId from the database.
     *
     * @param shelterId The unique identifier of the shelter to be removed
     */
    public void removeShelter(long shelterId) {
        Shelters shelter = sheltersRepository.findById(shelterId).orElse(null);
        sheltersRepository.deleteById(shelterId);
        log.info("Was invoked method for removeShelter");
        if (shelter == null) {
            log.error("There is no shelter with id = {}", shelterId);

        }
    }

    /**
     * Retrieves the contact information of a shelter by its unique identifier.
     *
     * @param shelterId The unique identifier of the shelter
     * @return The contact information of the shelter
     */
    public String showContacts(long shelterId) {
        log.info("Was invoked method for showContacts");
        return sheltersRepository.getReferenceById(shelterId).getContact();

    }

    public String showAddress(long shelterId) {
        log.info("Was invoked method for showAddress");

        final Optional<Shelters> byIdOptionalShelters = sheltersRepository.findById(shelterId);
        return byIdOptionalShelters.isPresent() ? byIdOptionalShelters.get().getAddress() : null;
    }

    /**
     * This method generates a string representation of a location based on the provided latitude and longitude.
     *
     * @param latitude  The latitude of the location
     * @param longitude The longitude of the location
     * @return A string representing the location in the format "Latitude: [latitude], Longitude: [longitude]"
     */
    public String showLocation(double latitude, double longitude) {
        String location = "Latitude: " + latitude + ", Longitude: " + longitude;
        return location;
    }

    public String showSecurityNumber(long shelterId) {
        log.info("Was invoked method for giveSecurityNumber");
        return sheltersRepository.getReferenceById(shelterId).getSecurityContact();
    }



    /**
     * This method is used to change the information of a shelter in the database.
     *
     * @param shelter The shelter object containing the updated information
     * @return The updated shelter object after saving to the database
     */

    public Shelters changeShelterInfo(Shelters shelter) {
        log.info("Was invoked method for change changeShelterInfo");
        return sheltersRepository.save(shelter);

    }

}
