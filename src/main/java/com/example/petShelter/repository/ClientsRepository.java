package com.example.petShelter.repository;

import com.example.petShelter.model.Clients;
import com.example.petShelter.model.Volunteers;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ClientsRepository extends JpaRepository<Clients, Long> {
    Clients findFirstByChatId(long chatId);
}
