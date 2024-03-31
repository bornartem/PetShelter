package com.example.petShelter.repository;

import com.example.petShelter.model.Shelters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SheltersRepository extends JpaRepository<Shelters, Long> {

}

//ContainsIgnoreCase
