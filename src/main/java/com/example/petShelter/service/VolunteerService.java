package com.example.petShelter.service;

import com.example.petShelter.model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.example.petShelter.repository.VolunteerRepository;

import java.util.List;
/**
 * @author bornartem
 */
@Service
public class VolunteerService {
    private final VolunteerRepository volunteerRepository;

    @Autowired
    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    public Volunteer create(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    public Volunteer read(long id) {
        return volunteerRepository.findById(id).orElseThrow();
    }
    /**
     * update volunteer in db
     * called method of repository {@link JpaRepository#save(Object)}
     * @param volunteer contain id, name, contact, can't be null
     * @return update and save volunteer in db
     */
    public Volunteer update(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }
    /**
     * delete volunteer by id in db
     * called void method of repository {@link JpaRepository#findById(Object)}
     * @param id identifier of volunteer, can't be null
     */
    public void delete(long id) {
        volunteerRepository.deleteById(id);
    }

    public List<Volunteer> getAll() {
        return volunteerRepository.findAll();
    }
}
