package com.example.petShelter.service;

import com.example.petShelter.exception.NotFoundInDB;
import com.example.petShelter.model.Volunteers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.example.petShelter.repository.VolunteersRepository;

import java.util.List;

/**
 * The class consists of logic of the project, which has
 * the methods  to work with "Volunteers" entity
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
        if (volunteersRepository.existsById(volunteers.getId())) {
            throw new RuntimeException();
        } else {
            volunteersRepository.save(volunteers);
            return volunteers;
        }
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
        if (volunteersRepository.findAll().isEmpty()) {
            throw new RuntimeException();
        }
        return volunteersRepository.findAll();
    }

    /**
     * get integer count volunteers from db
     * called method getCountVolunteers()
     *
     * @return count of volunteers
     */
    public Integer getCountVolunteers() {
        return volunteersRepository.getCountVolunteers();
    }

    public Volunteers findFirstByChatId(long chatId) {
        return volunteersRepository.findFirstByChatId(chatId);
    }

    public Volunteers findFirstActivity() {
//        return volunteersRepository.findFirstByIsActiveTrue();
        //это временно для того чтобы работало, верхнего метода пока что нет
        return volunteersRepository.findFirstByChatId(889317453);
    }

    public void inactiveVolunteerByChatId(Long volChatId) {

        Volunteers volunteers = volunteersRepository.findFirstByChatId(volChatId);
        volunteers.setActivity(false);
        volunteersRepository.save(volunteers);
    }

    public Volunteers findFreeVolunteer() {
        return volunteersRepository.findVolunteersByActivityTrue()
                .stream().findAny()
                .orElseThrow(() -> new NotFoundInDB("Все волонтеры сейчас заняты, Вам ответит первый освободившийся"));
    }
}
