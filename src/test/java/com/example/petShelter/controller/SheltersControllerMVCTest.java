package com.example.petShelter.controller;

import com.example.petShelter.model.Shelters;
import com.example.petShelter.repository.*;
import com.example.petShelter.service.*;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
        String param1name = "latitude";
        String param1content = "11.0";
        String param2name = "longitude";
        String param2content = "22.0";
        String paramFinish = "Latitude: " + param1content + ", Longitude: " + param2content;


        mockMvc.perform(MockMvcRequestBuilders
                        .get(request)
                        .param(param1name, param1content)
                        .param(param2name, param2content))

                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8 "))
                .andExpect(content().string(paramFinish))
        ;


    }

    List<Shelters> shelters = new ArrayList<>(List.of());

    @BeforeEach
    public void initColl() {
        shelters.add(
                new Shelters(null, 10L, "one", "two", "null", "null", "null", "null", "null")
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

                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("one"))

        ;
    }

    Shelters testSh;

    @BeforeEach
    public void initShelter() {
        testSh = new Shelters(null, 10L, "one", "two", "null", "null", "null", "null", "null");
    }

    @Test
    public void addShelter() throws Exception {
        String request = "/shelters";

        JSONObject shelterObject = new JSONObject();
        shelterObject.put("animals", null);
        shelterObject.put("id", 10L);
        shelterObject.put("name", "one");
        shelterObject.put("workingHours", "two");
        shelterObject.put("contact", null);
        shelterObject.put("address", null);
        shelterObject.put("location", null);
        shelterObject.put("securityContact", null);
        shelterObject.put("shelterRules", null);

        when(sheltersRepository.save(any())).thenReturn(testSh);


        mockMvc.perform(MockMvcRequestBuilders
                .post(request)
                .content(shelterObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("one"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.workingHours").value("two"));
        ;
    }


    @Test
    public void findShelterById() throws Exception {
        String request = "/shelters/10";

        Optional<Shelters> shelters1 = Optional.ofNullable(testSh);

        when(sheltersRepository.findById(any())).thenReturn(shelters1);

        mockMvc.perform(MockMvcRequestBuilders
                .get(request))

                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("one"))

        ;


    }


    @Test
    public void showContacts() throws Exception {
        String request = "/shelters/show-contacts";

        when(sheltersRepository.getReferenceById(any())).thenReturn(testSh);

        mockMvc.perform(MockMvcRequestBuilders

                .get(request)
                .param("shelterId", "10"))


                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8 "))
                .andExpect(content().string("null"))
        ;

    }

    @Test
    public void showAddress() throws Exception {
        String request = "/shelters/show-address";

        when(sheltersRepository.findById(any())).thenReturn(Optional.ofNullable(testSh));

        mockMvc.perform(MockMvcRequestBuilders

                        .get(request)
                        .param("shelterId", "10"))


                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8 "))
                .andExpect(content().string("null"))
        ;

    }


    @Test
    public void showSecurityNumber() throws Exception {
        String request = "/shelters/show-security-number";

        when(sheltersRepository.getReferenceById(any())).thenReturn(testSh);

        mockMvc.perform(MockMvcRequestBuilders

                        .get(request)
                        .param("shelterId", "10"))


                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8 "))
                .andExpect(content().string("null"))
        ;

    }


    @Test
    public void changeShelterInfo() throws Exception {
        String request = "/shelters";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("animals", null);
        jsonObject.put("id", 10L);
        jsonObject.put("name", "one");
        jsonObject.put("workingHours", "two");
        jsonObject.put("contact", null);
        jsonObject.put("address", null);
        jsonObject.put("location", null);
        jsonObject.put("securityContact", null);
        jsonObject.put("shelterRules", null);

        when(sheltersRepository.save(any())).thenReturn(testSh);

        mockMvc.perform(MockMvcRequestBuilders

                .put(request)
                .content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))


                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("one"))

        ;
    }



}
