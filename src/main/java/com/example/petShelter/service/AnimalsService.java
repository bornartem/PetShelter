package com.example.petShelter.service;

import com.example.petShelter.model.Animals;
import com.example.petShelter.repository.AnimalsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class AnimalsService {
    @Autowired
    private final AnimalsRepository animalsRepository;


    public AnimalsService(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    public List<Animals> findAllAnimalsOfCertainShelter(long shelterId) {
        log.info("Was invoked method for findAllAnimalsOfCertainShelter");
        return animalsRepository.findAllByByShelterId(shelterId);

    }

    public Animals findAnimalById(long animalId) {
        Animals animal = animalsRepository.findById(animalId).orElse(null);
        log.info("Was invoked method for findAnimalById");
        if (animal == null) {
            log.error("There is no animal with id = {}", animalId);
        }

        return animal;
    }

    public Collection<Animals> findAnimalsByStatus(boolean busyAnimalStatus) {
        log.info("Was invoked method for findAnimalsByStatus");
        return animalsRepository.findAnimalsByStatus(busyAnimalStatus);
    }

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
