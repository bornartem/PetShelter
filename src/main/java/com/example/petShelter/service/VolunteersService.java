package com.example.petShelter.service;

import com.example.petShelter.model.Volunteers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.example.petShelter.repository.VolunteersRepository;

import java.util.List;

/**
 * The class consists of logic of the project, which has
 the methods  to work with "Volunteers" entity
 *
 * @author bornartem
 */
@Service
public class VolunteersService {
    private final VolunteersRepository volunteersRepository;

    @Autowired
    public VolunteersService(VolunteersRepository volunteersRepository) {
        this.volunteersRepository = volunteersRepository;
    }

    /**
     * create volunteer in db
     * called method of repository {@link JpaRepository#save(Object)}
     *
     * @param volunteers contain id, name, contact, can't be null
     * @return create and save volunteer in db
     */
    public Volunteers create(Volunteers volunteers) {
        return volunteersRepository.save(volunteers);
    }

    /**
     * find volunteer by id in db
     * called method of repository {@link JpaRepository#findById(Object)}
     *
     * @param id identifier of volunteer, can't be null
     * @return find volunteer from db by id
     */
    public Volunteers read(long id) {
        return volunteersRepository.findById(id).orElseThrow();
    }

    /**
     * update volunteer in db
     * called method of repository {@link JpaRepository#save(Object)}
     *
     * @param volunteers contain id, name, contact, can't be null
     * @return update and save volunteer in db
     */
    public Volunteers update(Volunteers volunteers) {
        return volunteersRepository.save(volunteers);
    }

    /**
     * delete volunteer by id in db
     * called void method of repository {@link JpaRepository#findById(Object)}
     *
     * @param id identifier of volunteer, can't be null
     */
    public void delete(long id) {
        volunteersRepository.deleteById(id);
    }

    /**
     * get all volunteers from db
     * called method of repository {@link JpaRepository#findAll()}
     *
     * @return all volunteers
     */
    public List<Volunteers> getAll() {
        return volunteersRepository.findAll();
    }

    public Integer getCountVolunteers(){
        return volunteersRepository.getCountVolunteers();
    }

}
