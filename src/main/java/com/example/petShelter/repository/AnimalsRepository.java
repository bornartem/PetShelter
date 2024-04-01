package com.example.petShelter.repository;

import com.example.petShelter.model.Animals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface  AnimalsRepository extends JpaRepository<Animals, Long> {
    public List<Animals> findAllByByShelterId(long shelter_id);
    public Collection<Animals> findAnimalsByStatus(boolean busyAnimalStatus);
    public Collection<Animals> findAnimalsByType(String animalType);

}
