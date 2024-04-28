package com.example.petShelter.controller;

import com.example.petShelter.controller.*;
import com.example.petShelter.model.Volunteers;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class VolunteerControllerTest {

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
    void shouldReturnCreateVolunteer() throws Exception {
        long id = 1L;
        long chatId = 1L;
        String name = "Artem";
        String contact = "+7-911-081_18_10";
        Boolean activity = true;

        JSONObject volunteersObject = new JSONObject();
        volunteersObject.put(name, name);
        volunteersObject.put(contact, contact);
        volunteersObject.put(String.valueOf(activity), activity);

        Volunteers volunteers = new Volunteers();
        volunteers.setId(id);
        volunteers.setChatId(chatId);
        volunteers.setName(name);
        volunteers.setContact(contact);
        volunteers.setActivity(activity);

        when(volunteersRepository.save(any(Volunteers.class))).thenReturn(volunteers);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/volunteer/create")
                        .content(volunteersObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.chatId").value(chatId))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.contact").value(contact))
                .andExpect(jsonPath("$.activity").value(activity));

    }

    @Test
    void shouldReadVolunteer() throws Exception {
        long id = 1L;
        long chatId = 1L;
        String name = "Artem";
        String contact = "+7-911-081_18_10";
        Boolean activity = true;

        JSONObject volunteersObject = new JSONObject();
        volunteersObject.put(name, name);
        volunteersObject.put(contact, contact);
        volunteersObject.put(String.valueOf(activity), activity);

        Volunteers volunteers = new Volunteers();
        volunteers.setId(id);
        volunteers.setChatId(chatId);
        volunteers.setName(name);
        volunteers.setContact(contact);
        volunteers.setActivity(activity);

        when(volunteersRepository.findById(any(Long.class))).thenReturn(Optional.of(volunteers));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/volunteer/read1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.chatId").value(chatId))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.contact").value(contact))
                .andExpect(jsonPath("$.activity").value(activity));
    }

    @Test
    void shouldReturnUpdateVolunteer() throws Exception {
        long id = 1L;
        long chatId = 1L;
        String name = "Artem";
        String contact = "+7-911-081_18_10";
        Boolean activity = true;

        JSONObject volunteersObject = new JSONObject();
        volunteersObject.put(name, name);
        volunteersObject.put(contact, contact);
        volunteersObject.put(String.valueOf(activity), activity);

        Volunteers volunteers = new Volunteers();
        volunteers.setId(id);
        volunteers.setChatId(chatId);
        volunteers.setName(name);
        volunteers.setContact(contact);
        volunteers.setActivity(activity);

        when(volunteersRepository.save(any(Volunteers.class))).thenReturn(volunteers);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/volunteer/update")
                        .content(volunteersObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.chatId").value(chatId))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.contact").value(contact))
                .andExpect(jsonPath("$.activity").value(activity));
    }

    @Test
    void shouldReturnDeleteVolunteer() throws Exception {
        long id = 1L;
        long chatId = 1L;
        String name = "Artem";
        String contact = "+7-911-081_18_10";
        Boolean activity = true;

        JSONObject volunteersObject = new JSONObject();
        volunteersObject.put(name, name);
        volunteersObject.put(contact, contact);
        volunteersObject.put(String.valueOf(activity), activity);

        Volunteers volunteers = new Volunteers();
        volunteers.setId(id);
        volunteers.setChatId(chatId);
        volunteers.setName(name);
        volunteers.setContact(contact);
        volunteers.setActivity(activity);

        when(volunteersRepository.findById(any(Long.class))).thenReturn(Optional.of(volunteers));
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/volunteer/delete1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnAllVolunteers() throws Exception {
        List<Volunteers> volunteersList = new ArrayList<>(List.of(
                new Volunteers(null, 1L, 1L, "Artem", "+7-911-081_18_10", true),
                new Volunteers(null, 2L, 2L, "Art", "+7-911-081_10_10", true)
        ));
        when(volunteersRepository.findAll()).thenReturn(volunteersList);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/volunteer/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    void findCountAllVolunteers() throws Exception {
        Volunteers volunteer = new Volunteers(null, 1L, 1L, "Artem", "+7-911-081_18_10", true);
        Volunteers volunteer1 = new Volunteers(null, 2L, 2L, "Ar", "+7-911-081_10_10", true);
        Integer count = 2;
        when(volunteersRepository.save(any(Volunteers.class))).thenReturn(volunteer, volunteer1);
        when(volunteersRepository.getCountVolunteers()).thenReturn(count);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/volunteer/count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(count));
    }
}
