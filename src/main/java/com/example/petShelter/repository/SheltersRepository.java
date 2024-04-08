package com.example.petShelter.repository;

import com.example.petShelter.model.Shelters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SheltersRepository extends JpaRepository<Shelters, Long> {
    String showAnimalInfoById(long id, String shelterInfo);

    String showSchedule(long id, String contact);

    String showSecurityContact(long id, String securityContact);

    String showShelterRules(long id, String dogShelterRules);
}
