package com.example.petShelter.controller;

import com.example.petShelter.repository.*;
import com.example.petShelter.service.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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

        String request = "/showLocation";
        String param1 = "param1";
        String param2 = "param2";
        String paramFinish = "Latitude: " + param1 + ", Longitude: " + param2;



        mockMvc.perform(
                get(request).param(param1, param2).accept(paramFinish));


    }

}
