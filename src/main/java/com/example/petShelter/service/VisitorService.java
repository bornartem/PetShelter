package com.example.petShelter.service;

import com.example.petShelter.model.Visitor;
import com.example.petShelter.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bornartem
 */
@Service
public class VisitorService {
    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    /**
     * create visitor in db
     * called method of repository {@link JpaRepository#save(Object)}
     *
     * @param visitor contain id, name, contact, can't be null
     * @return create and save visitor in db
     */
    public Visitor create(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    /**
     * find visitor by id in db
     * called method of repository {@link JpaRepository#findById(Object)}
     * @param id identifier of visitor, can't be null
     * @return find visitor from db by id
     */
    public Visitor read(long id) {
        return visitorRepository.findById(id).orElseThrow();
    }

    public Visitor update(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    public void delete(long id) {
        visitorRepository.deleteById(id);
    }

    /**
     * get all visitors from db
     * called method of repository {@link JpaRepository#findAll()}
     * @return all visitors
     */
    public List<Visitor> getAll() {
        return visitorRepository.findAll();
    }
}
