package com.example.petShelter.controller;

import com.example.petShelter.model.Shelters;
import com.example.petShelter.repository.*;
import com.example.petShelter.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class SheltersControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // будем его мокать
    private AnimalAvatarRepository animalAvatarRepository;

    @MockBean // будем его мокать
    private AnimalsRepository animalsRepository;

    @MockBean // будем его мокать
    private ClientsRepository clientsRepository;

    @MockBean // будем его мокать
    private SheltersRepository sheltersRepository;

    @MockBean // будем его мокать
    private VolunteersRepository volunteersRepository;




    @SpyBean
    private AnimalAvatarService animalAvatarService;

    @SpyBean
    private AnimalsService animalsService;

    @SpyBean
    private ClientsService clientsService;

    @SpyBean
    private SheltersService sheltersService;

    @SpyBean
    private VolunteersService volunteersService;


    @InjectMocks //будем с ним работать
    private SheltersController sheltersController;


    @Test
    public void showLocation() throws Exception {

        String request = "/shelters/showLocation";
        String param1 = "latitude";
        String param2 = "longitude";
        String paramFinish = "Latitude: " + param1 + ", Longitude: " + param2;



        mockMvc.perform(MockMvcRequestBuilders
                .get(request)
                .param(param1,"11")
                .param(param2, "22"))

                .andExpect(status().isOk())
        ;


    }

    List<Shelters> shelters = new ArrayList<>(List.of());

    @BeforeEach
    public void initColl() {
        shelters.add(
                new Shelters(new ArrayList<>(), 10L, "one", "two", "null", "null", "null", "null", "null")
        );
        shelters.add(
                new Shelters(null, 20L, "one", "two", null, null, null, null, null)
        );
        shelters.add(
                new Shelters(null, 30L, "one", "two", null, null, null, null, null)
        );
    }

    @Test
    public void listAllShelters() throws Exception {

        String request = "/shelters/all-shelters";

        when(sheltersRepository.findAll()).thenReturn(shelters);

        mockMvc.perform(MockMvcRequestBuilders      // localhost и тп spring сделает сам
                .get(request)
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());



    }


}
