package com.example.petShelter.service;

import com.example.petShelter.model.Clients;
import com.example.petShelter.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The class consists of logic of the project, which has
 * the methods  to work with "Clients" entity
 *
 * @author bornartem
 */
@Service
public class ClientsService {
    private final ClientsRepository clientsRepository;

    @Autowired
    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    /**
     * create client in db
     * called method of repository {@link JpaRepository#save(Object)}
     *
     * @param clients contain id, name, contact, can't be null
     * @return create and save client in db
     */
    public Clients create(Clients clients) {
        if (clientsRepository.existsById(clients.getId())) {
            throw new RuntimeException();
        } else {
            return clientsRepository.save(clients);
        }
    }

    /**
     * find client by id in db
     * called method of repository {@link JpaRepository#findById(Object)}
     *
     * @param id identifier of client, can't be null
     * @return find client from db by id
     */
    public Clients read(long id) {
        return clientsRepository.findById(id).orElseThrow();
    }

    /**
     * update client in db
     * called method of repository {@link JpaRepository#save(Object)}
     *
     * @param clients contain id, name, contact, can't be null
     * @return update and save client in db
     */
    public Clients update(Clients clients) {
        return clientsRepository.save(clients);
    }

    /**
     * delete client by id in db
     * called method of repository {@link JpaRepository#findById(Object)}
     *
     * @param id identifier of client, can't be null
     * @return delete client from db by id
     */
    public void delete(long id) {
        if (clientsRepository.existsById(id)) {
            clientsRepository.deleteById(id);
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * get all clients from db
     * called method of repository {@link JpaRepository#findAll()}
     *
     * @return all clients
     */
    public List<Clients> getAll() {
        if (clientsRepository.findAll().isEmpty()) {
            throw new RuntimeException();
        } else {
            return clientsRepository.findAll();
        }
    }

}
