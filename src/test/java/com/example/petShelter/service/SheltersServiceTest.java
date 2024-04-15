package com.example.petShelter.service;

import com.example.petShelter.model.Shelters;
import com.example.petShelter.repository.SheltersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


/**
 * The class is testing SheltersService class
 *
 * @author Khilola Kushbakova
 */

@SpringBootTest

class SheltersServiceTest {
    @Mock
    private SheltersRepository sheltersRepository;

    @InjectMocks
    private SheltersService sheltersService;
    @Test
    void listAllShelters() {
        Shelters shelter1 = new Shelters();
        shelter1.setId(1L);
        shelter1.setName("Shelter 1");

        Shelters shelter2 = new Shelters();
        shelter2.setId(2L);
        shelter2.setName("Shelter 2");

        List<Shelters> sheltersList = Arrays.asList(shelter1, shelter2);

        Mockito.when(sheltersRepository.findAll()).thenReturn(sheltersList);


        Collection<Shelters> result = sheltersService.listAllShelters();

        assertNotNull(result);
        assertEquals(sheltersList.size(), result.size());
        assertTrue(result.containsAll(sheltersList));

        Mockito.verify(sheltersRepository, times(1)).findAll();
        Mockito.verifyNoMoreInteractions(sheltersRepository);

    }

    @Test
    void addShelter() {
        Shelters shelter = new Shelters();
        shelter.setId(1L);
        shelter.setName("Test Shelter");

        Shelters addedShelter = sheltersService.addShelter(shelter);

        assertNotNull(addedShelter);
        assertEquals(shelter.getName(), addedShelter.getName());
        assertEquals(shelter.getId(), addedShelter.getId());

    }

    @Test
    void findShelterById() {
        Shelters shelter = new Shelters();
        shelter.setId(1L);
        shelter.setName("Test Shelter");

        sheltersRepository.save(shelter);

        Shelters foundShelter = sheltersService.findShelterById(1L);

        assertNotNull(foundShelter);
        assertEquals(shelter.getName(), foundShelter.getName());
    }

    @Test
    void removeShelter() {
        long shelterId = 1L;
        Shelters shelter = new Shelters();
        shelter.setId(shelterId);
        Mockito.when(sheltersRepository.findById(shelterId)).thenReturn(Optional.of(shelter));

        sheltersService.removeShelter(shelterId);

        Mockito.verify(sheltersRepository, times(1)).deleteById(shelterId);
    }

    @Test
    void showContacts() {
        long shelterId = 1L;
        Shelters shelter = new Shelters();
        shelter.setId(shelterId);
        String contact = "1234567890";
        shelter.setContact(contact);

        Mockito.when(sheltersRepository.getReferenceById(shelterId)).thenReturn(shelter);

        String result = sheltersService.showContacts(shelterId);

        assertEquals(contact, result);
    }

    @Test
    void showAddress() {
        long shelterId = 1L;
        Shelters shelter = new Shelters();
        shelter.setId(shelterId);
        String address = "123 Main St";
        shelter.setAddress(address);
        Mockito.when(sheltersRepository.findById(shelterId)).thenReturn(Optional.of(shelter));

        String result = sheltersService.showAddress(shelterId);

        assertEquals(address, result);
    }

    @Test
    void showLocation() {
        double latitude = 40.7128;
        double longitude = -74.0060;
        String expectedLocation = "Latitude: 40.7128, Longitude: -74.006";

        String result = sheltersService.showLocation(latitude, longitude);

        assertEquals(expectedLocation, result);
    }

    @Test
    void showSecurityNumber() {
        long shelterId = 1L;
        Shelters shelter = new Shelters();
        shelter.setId(shelterId);
        String securityContact = "9876543210";
        shelter.setSecurityContact(securityContact);
        Mockito.when(sheltersRepository.getReferenceById(shelterId)).thenReturn(shelter);

        String result = sheltersService.showSecurityNumber(shelterId);

        assertEquals(securityContact, result);
    }

    @Test
    public void testChangeShelterInfo() throws Exception {
        Shelters shelter = new Shelters();
        shelter.setName("Питомник Кошкин дом");
        shelter.setAddress("123 Test St");
        shelter.setContact("test@test.com");

        Mockito.when(sheltersRepository.save(Mockito.any(Shelters.class))).thenReturn(shelter);
        when(sheltersRepository.findById(any(Long.class))).thenReturn(Optional.of(shelter));

        Shelters updatedShelter = sheltersService.changeShelterInfo(shelter);


        assertEquals("Питомник Кошкин дом", updatedShelter.getName());
        assertEquals("123 Test St", updatedShelter.getAddress());
        assertEquals("test@test.com", updatedShelter.getContact());
    }

    @Test
    public void testShowAnimalInfoById() {
        long id = 1;
        Shelters shelter = new Shelters();
        shelter.setId(id);
        shelter.setName("Test Shelter");
        shelter.setAddress("123 Test St");
        shelter.setContact("test@test.com");

        Mockito.when(sheltersRepository.findById(id)).thenReturn(Optional.of(shelter));

        String result = sheltersService.showAnimalInfoById(id);

        assertEquals("Test Shelter 123 Test St test@test.com", result);
    }

    @Test
    public void testShowSchedule() {
        long id = 1;
        Shelters shelter = new Shelters();
        shelter.setId(id);
        shelter.setWorkingHours("Monday-Friday 9am-5pm");

        Mockito.when(sheltersRepository.findById(id)).thenReturn(Optional.of(shelter));

        String result = sheltersService.showSchedule(id);

        assertEquals("Monday-Friday 9am-5pm", result);
    }


    @Test
    public void testShowSecurityContact() {
        long id = 1;
        Shelters shelter = new Shelters();
        shelter.setId(id);
        shelter.setSecurityContact("123-456-7890");

        Mockito.when(sheltersRepository.findById(id)).thenReturn(Optional.of(shelter));

        String result = sheltersService.showSecurityContact(id);

        assertEquals("123-456-7890", result);
    }


    @Test
    public void testShowShelterRules() {
        long id = 1;
        Shelters shelter = new Shelters();
        shelter.setId(id);
        shelter.setShelterRules("No pets allowed");

        Mockito.when(sheltersRepository.findById(id)).thenReturn(Optional.of(shelter));

        String result = sheltersService.showShelterRules(id);

        assertEquals("No pets allowed", result);
    }
}