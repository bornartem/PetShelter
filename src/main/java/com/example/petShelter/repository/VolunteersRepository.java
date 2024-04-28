package com.example.petShelter.repository;

import com.example.petShelter.model.Volunteers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VolunteersRepository extends JpaRepository<Volunteers, Long> {
    @Query(value = "select count (*) from volunteers", nativeQuery = true)
    int getCountVolunteers();

    Volunteers findFirstByChatId(long chatId);

    List<Volunteers> findVolunteersByActivityTrue();

    @Query(value = "SELECT id_volunteer FROM volunteers ORDER BY id_volunteer DESC LIMIT 1;", nativeQuery = true)
    long getMaxIdByVolunteers();
}
