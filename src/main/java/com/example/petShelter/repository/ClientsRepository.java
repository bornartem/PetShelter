package com.example.petShelter.repository;

import com.example.petShelter.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Clients, Long> {

}
