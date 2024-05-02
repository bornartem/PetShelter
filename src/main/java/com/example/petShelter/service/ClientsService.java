package com.example.petShelter.service;

import com.example.petShelter.model.Animals;
import com.example.petShelter.model.Clients;
import com.example.petShelter.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The class consists of logic of the project, which has
 * the methods  to work with "Clients" entity
 *
 * @author bornartem
 */
@Service
public class ClientsService {
    private final ClientsRepository clientsRepository;

    private final AnimalsService animalsService;

    @Autowired
    public ClientsService(ClientsRepository clientsRepository, AnimalsService animalsService) {
        this.clientsRepository = clientsRepository;
        this.animalsService = animalsService;
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
        clientsRepository.deleteById(id);
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

    public Clients findFirstByChatId(Long chatId) {
        return clientsRepository.findFirstByChatId(chatId);
    }

    /**
     * Method to register a foster animal with an adoptive parent
     * called method of repository {@link JpaRepository#findById(Object)}
     * called method of repository {@link JpaRepository#save(Object)}
     *
     * @param clientId identifier of client, can't be null
     * @param animalId identifier of animal, can't be null
     * @return client who adopted the animal
     */
    public Clients registerAFosterAnimalWithAnAdoptiveParent(Long clientId, Long animalId) {
        Clients client = clientsRepository.findById(clientId).orElseThrow();
        Animals animal = animalsService.findAnimalById(animalId);
        client.setAdoptedAnimal(animal);
        clientsRepository.save(client);
        return client;
    }

}
