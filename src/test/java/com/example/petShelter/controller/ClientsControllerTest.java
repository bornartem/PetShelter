package com.example.petShelter.controller;

import com.example.petShelter.model.Animals;
import com.example.petShelter.model.Clients;
import com.example.petShelter.model.DailyReports;
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
public class ClientsControllerTest {

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
    private DailyReportRepository dailyReportRepository;
    @MockBean
    private SheltersRepository sheltersRepository;
    @SpyBean
    private VolunteersService volunteersService;
    @SpyBean
    private DailyReportService dailyReportService;
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
    void shouldReturnCreateClient() throws Exception {
        long id = 1l;
        long chatId = 1l;
        String name = "Artem";
        String contact = "+7-911-081_18_10";

        JSONObject clientsObject = new JSONObject();
        clientsObject.put(name, name);
        clientsObject.put(contact, contact);

        Clients clients = new Clients();
        clients.setId(id);
        clients.setChatId(chatId);
        clients.setName(name);
        clients.setContact(contact);

        when(clientsRepository.save(any(Clients.class))).thenReturn(clients);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/client/create")
                        .content(clientsObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.chatId").value(chatId))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.contact").value(contact));
    }

    @Test
    void shouldReadClient() throws Exception {
        long id = 1l;
        long chatId = 1l;
        String name = "Artem";
        String contact = "+7-911-081_18_10";

        JSONObject clientsObject = new JSONObject();
        clientsObject.put(name, name);
        clientsObject.put(contact, contact);

        Clients clients = new Clients();
        clients.setId(id);
        clients.setChatId(chatId);
        clients.setName(name);
        clients.setContact(contact);

        when(clientsRepository.findById(any(Long.class))).thenReturn(Optional.of(clients));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/read1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.chatId").value(chatId))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.contact").value(contact));
    }

    @Test
    void shouldReturnUpdateClient() throws Exception {
        long id = 1l;
        long chatId = 1l;
        String name = "Artem";
        String contact = "+7-911-081_18_10";

        JSONObject clientsObject = new JSONObject();
        clientsObject.put(name, name);
        clientsObject.put(contact, contact);

        Clients clients = new Clients();
        clients.setId(id);
        clients.setChatId(chatId);
        clients.setName(name);
        clients.setContact(contact);

        when(clientsRepository.save(any(Clients.class))).thenReturn(clients);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/client/update")
                        .content(clientsObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.chatId").value(chatId))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.contact").value(contact));
    }

    @Test
    void shouldDeleteClient() throws Exception {
        long id = 1l;
        long chatId = 1l;
        String name = "Artem";
        String contact = "+7-911-081_18_10";

        JSONObject clientsObject = new JSONObject();
        clientsObject.put(name, name);
        clientsObject.put(contact, contact);

        Clients clients = new Clients();
        clients.setId(id);
        clients.setChatId(chatId);
        clients.setName(name);
        clients.setContact(contact);

        when(clientsRepository.findById(any(Long.class))).thenReturn(Optional.of(clients));
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/client/delete1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnAllClients() throws Exception {
        List<DailyReports> dailyReports = new ArrayList<>();
        List<Clients> clientsList = new ArrayList<>(List.of(
                new Clients(null, 1L, 1L, "Artem", "+7-911-081_18_10", dailyReports, null),
                new Clients(null, 2L, 2L, "Art", "+7-911-081_10_10", dailyReports, null)
        ));

        when(clientsRepository.findAll()).thenReturn(clientsList);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    void shouldRegisterAnimalWithAnAdoptiveParent() throws Exception {
        Long clientId = 1L;
        Long chatId = 12345L;
        String name = "Maria";
        String contact = "89275052208";

        JSONObject clientsObject = new JSONObject();
        clientsObject.put("id", clientId);
        clientsObject.put("chatId", chatId);
        clientsObject.put("name", name);
        clientsObject.put("contact", contact);

        Animals animal = new Animals();
        animal.setId(3L);

        Long animalId = animal.getId();

        Clients clients = new Clients();
        clients.setId(clientId);
        clients.setChatId(chatId);
        clients.setName(name);
        clients.setContact(contact);
        clients.setAdoptedAnimal(animal);

        when(clientsRepository.findById(any(Long.class))).thenReturn(Optional.of(clients));
        when(animalsService.findAnimalById(any(Long.class))).thenReturn(animal);

        when(clientsRepository.save(any(Clients.class))).thenReturn(clients);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/client/registerAnimal")
                        .param("clientId", clientId.toString())
                        .param("animalId", animalId.toString())
                        .content(clientsObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientId))
                .andExpect(jsonPath("$.chatId").value(chatId))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.contact").value(contact));
    }
}
