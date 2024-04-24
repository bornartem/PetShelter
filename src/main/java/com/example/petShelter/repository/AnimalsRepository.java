package com.example.petShelter.repository;

import com.example.petShelter.model.Animals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;



public interface AnimalsRepository extends JpaRepository<Animals, Long> {
    List<Animals> findBySheltersId(Long sheltersId);
    Collection<Animals> findAnimalsByBusyFree(Boolean busyAnimalStatus);

    Collection<Animals> findAnimalsByType(String animalType);

}
