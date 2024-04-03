package com.example.petShelter.service;

import com.example.petShelter.model.Clients;
import com.example.petShelter.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
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
        return clientsRepository.save(clients);
    }

    /**
     * find client by id in db
     * called method of repository {@link JpaRepository#findById(Object)}
     * @param id identifier of client, can't be null
     * @return find client from db by id
     */
    public Clients read(long id) {
        return clientsRepository.findById(id).orElseThrow();
    }

    public Clients update(Clients clients) {
        return clientsRepository.save(clients);
    }

    public void delete(long id) {
        clientsRepository.deleteById(id);
    }

    /**
     * get all clients from db
     * called method of repository {@link JpaRepository#findAll()}
     * @return all clients
     */
    public List<Clients> getAll() {
        return clientsRepository.findAll();
    }

}
