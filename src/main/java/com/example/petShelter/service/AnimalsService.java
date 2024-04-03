package com.example.petShelter.service;

import com.example.petShelter.model.Animals;
import com.example.petShelter.repository.AnimalsRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * The class consists of logic of the project, which has the methods  to work with "Animals" entity
 *
 * @author Khilola Kushbakova
 */
@Slf4j
@Service
public class AnimalsService {
    @Autowired
    private final AnimalsRepository animalsRepository;

    private Logger log = LoggerFactory.getLogger(AnimalsService.class);

    public AnimalsService(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    /**
     * Method to find all animals belonging to a certain shelter.
     *
     * @param shelterId the identifier of the shelter whose animals are to be found
     * @return a list of animals belonging to the specified shelter
     */
//    public List<Animals> findAllAnimalsOfCertainShelter(long shelterId) {
//        log.info("Was invoked method for findAllAnimalsOfCertainShelter");
//        return animalsRepository.findAllByByShelterId(shelterId);
//
//    }


    /**
     * Method to find an animal by its identifier.
     *
     * @param animalId the identifier of the animal to find
     * @return the found animal or null if the animal was not found
     */
    public Animals findAnimalById(long animalId) {
        Animals animal = animalsRepository.findById(animalId).orElse(null);
        log.info("Was invoked method for findAnimalById");
        if (animal == null) {
            log.error("There is no animal with id = {}", animalId);
        }

        return animal;
    }

    /**
     * Method to find animals based on their status.
     *
     * @param busyAnimalStatus the status of animals to search for
     * @return a collection of animals with the specified status
     */

    public Collection<Animals> findAnimalsByStatus(boolean busyAnimalStatus) {
        log.info("Was invoked method for findAnimalsByStatus");
        return animalsRepository.findAnimalsByBusyFree(busyAnimalStatus);
    }

    /**
     * Method to find animals based on their type.
     *
     * @param animalType the type of animals to search for
     * @return a collection of animals of the specified type, or null if no animals are found
     */
    public Collection<Animals> findAnimalsByType(String animalType) {
        log.info("Was invoked method for findAnimalsByType");
        Collection<Animals> animals = findAnimalsByType(animalType);
        if (animals == null) {
            log.error("There is no animal with type = {}", animalType);
        }

        return animals;
    }

    public Animals addNewAnimal(Animals animal) {
        log.info("Was invoked method for addNewAnimal");
        return animalsRepository.save(animal);
    }

    /**
     * Method to remove an animal by its identifier.
     *
     * @param animalId the identifier of the animal to remove
     */
    public void removeAnimal(long animalId) {
        Animals animal = animalsRepository.findById(animalId).orElse(null);
        animalsRepository.deleteById(animalId);
        log.info("Was invoked method for remove removeAnimal");
        if (animal == null) {
            log.error("There is no animal with id = {}", animalId);
        }
    }

    public Animals changeAnimalInfo(Animals animal) {
        log.info("Was invoked method for changeAnimalInfo");
        return animalsRepository.save(animal);
    }

}
