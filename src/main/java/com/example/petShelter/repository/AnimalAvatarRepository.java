package com.example.petShelter.repository;

import com.example.petShelter.model.AnimalAvatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalAvatarRepository  extends JpaRepository<AnimalAvatar, Long> {

    AnimalAvatar findAnimalAvatarByAnimalId(Long animalId);
}
