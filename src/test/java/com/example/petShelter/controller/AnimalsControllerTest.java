package com.example.petShelter.controller;

import com.example.petShelter.controller.*;
import com.example.petShelter.model.Animals;
import com.example.petShelter.repository.*;
import com.example.petShelter.service.*;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AnimalsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VolunteersRepository volunteersRepository;
    @MockBean
    private AnimalAvatarRepository animalAvatarRepository;
    @MockBean
    private AnimalsRepository animalsRepository;
    @MockBean
    private ClientsRepository clientsRepository;
    @MockBean
    private SheltersRepository sheltersRepository;
    @SpyBean
    private VolunteersService volunteersService;
    @SpyBean
    private AnimalAvatarService animalAvatarService;
    @SpyBean
    private AnimalsService animalsService;
    @SpyBean
    private ClientsService clientsService;
    @SpyBean
    private SheltersService sheltersService;
    @InjectMocks
    private VolunteersController volunteersController;
    @InjectMocks
    private AnimalAvatarController animalAvatarController;
    @InjectMocks
    private AnimalsController animalsController;
    @InjectMocks
    private ClientsController clientsController;
    @InjectMocks
    private SheltersController sheltersController;

    @Test
    void shouldReturnAddedAnimal() throws Exception {
        long id = 1L;
        String name = "Jack";
        String type = "Dog";
        Boolean busyFree = true;
        LocalDateTime dateTake = LocalDateTime.now();

        JSONObject animalsObject = new JSONObject();
        animalsObject.put(name, name);
        animalsObject.put(type, type);
        animalsObject.put(String.valueOf(busyFree), busyFree);
        animalsObject.put(String.valueOf(dateTake), dateTake);

        Animals animals = new Animals();
        animals.setId(id);
        animals.setName(name);
        animals.setType(type);
        animals.setBusyFree(busyFree);
        animals.setDateTake(dateTake);

        when(animalsRepository.save(any(Animals.class))).thenReturn(animals);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/animals")
                        .content(animalsObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.type").value(type))
                .andExpect(jsonPath("$.busyFree").value(busyFree));
    }

    @Test
    void shouldReturnAnimalById() throws Exception {
        long id = 1L;
        String name = "Jack";
        String type = "Dog";
        Boolean busyFree = true;
        LocalDateTime dateTake = LocalDateTime.now();

        JSONObject animalsObject = new JSONObject();
        animalsObject.put(name, name);
        animalsObject.put(type, type);
        animalsObject.put(String.valueOf(busyFree), busyFree);
        animalsObject.put(String.valueOf(dateTake), dateTake);

        Animals animals = new Animals();
        animals.setId(id);
        animals.setName(name);
        animals.setType(type);
        animals.setBusyFree(busyFree);
        animals.setDateTake(dateTake);

        when(animalsRepository.findById(any(Long.class))).thenReturn(Optional.of(animals));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/animals/getByAnimalID/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.type").value(type))
                .andExpect(jsonPath("$.busyFree").value(busyFree));
    }

    @Test
    void shouldReturnDeleteAnimal() throws Exception {
        long id = 1L;
        String name = "Jack";
        String type = "Dog";
        Boolean busyFree = true;
        LocalDateTime dateTake = LocalDateTime.now();

        JSONObject animalsObject = new JSONObject();
        animalsObject.put(name, name);
        animalsObject.put(type, type);
        animalsObject.put(String.valueOf(busyFree), busyFree);
        animalsObject.put(String.valueOf(dateTake), dateTake);

        Animals animals = new Animals();
        animals.setId(id);
        animals.setName(name);
        animals.setType(type);
        animals.setBusyFree(busyFree);
        animals.setDateTake(dateTake);

        when(animalsRepository.findById(any(Long.class))).thenReturn(Optional.of(animals));
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/animals/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnAnimalByType() throws Exception {
        long id = 1L;
        String name = "Jack";
        String type = "Dog";
        Boolean busyFree = true;
        LocalDateTime dateTake = LocalDateTime.now();

        JSONObject animalsObject = new JSONObject();
        animalsObject.put(name, name);
        animalsObject.put(type, type);
        animalsObject.put(String.valueOf(busyFree), busyFree);
        animalsObject.put(String.valueOf(dateTake), dateTake);

        Animals animals = new Animals();
        animals.setId(id);
        animals.setName(name);
        animals.setType(type);
        animals.setBusyFree(busyFree);
        animals.setDateTake(dateTake);

        when(animalsRepository.findAnimalsByType(any(String.class))).thenReturn(Collections.singleton(animals));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/animals/search-animals-by-type/Dog")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnAnimalByStatus() throws Exception {
        long id = 1L;
        String name = "Jack";
        String type = "Dog";
        Boolean busyFree = true;
        LocalDateTime dateTake = LocalDateTime.now();

        JSONObject animalsObject = new JSONObject();
        animalsObject.put(name, name);
        animalsObject.put(type, type);
        animalsObject.put(String.valueOf(busyFree), busyFree);
        animalsObject.put(String.valueOf(dateTake), dateTake);

        Animals animals = new Animals();
        animals.setId(id);
        animals.setName(name);
        animals.setType(type);
        animals.setBusyFree(busyFree);
        animals.setDateTake(dateTake);

        when(animalsRepository.findAnimalsByType(any(String.class))).thenReturn(Collections.singleton(animals));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/animals/search-animals-by-status/true")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void changeBusyFreeStatusTest() throws Exception {
        Long animalId = 3L;
        String name = "Бруно";
        String age = "7 месяцев";
        String type = "Собака";
        Boolean busyFreeStatus = false;
        JSONObject animalsObject = new JSONObject();

        animalsObject.put("id", animalId);
        animalsObject.put("name", name);
        animalsObject.put("age", age);
        animalsObject.put("type", type);
        animalsObject.put("busyFree", busyFreeStatus);

        Animals animal = new Animals();
        animal.setId(animalId);
        animal.setName(name);
        animal.setAge(age);
        animal.setType(type);
        animal.setBusyFree(!busyFreeStatus);

        when(animalsRepository.findById(animalId)).thenReturn(Optional.of(animal));
        when(animalsRepository.save(animal)).thenReturn(animal);

        mockMvc.perform(put("/animals/change-busy-free-status")
                        .param("animalId", animalId.toString())
                        .param("busyFreeStatus", busyFreeStatus.toString())
                        .content(animalsObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(animalId))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.type").value(type))
                .andExpect(jsonPath("$.busyFree").value(busyFreeStatus));
    }
}
