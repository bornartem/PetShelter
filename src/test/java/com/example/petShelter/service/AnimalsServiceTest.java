package com.example.petShelter.service;

import com.example.petShelter.model.Animals;
import com.example.petShelter.repository.AnimalsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * The class is testing AnimalsService class
 *
 * @author Khilola Kushbakova
 */

@SpringBootTest
class AnimalsServiceTest {

    @Mock
    private AnimalsRepository animalsRepository;

    @InjectMocks
    private AnimalsService animalsService;

//    @Test
//    public void testFindAllAnimalsOfCertainShelter() {
//        long shelterId = 1;
//        List<Animals> animalsList = new ArrayList<>();
//        when(animalsRepository.findAllByShelterId(shelterId)).thenReturn(animalsList);
//
//        List<Animals> result = animalsService.findAllAnimalsOfCertainShelter(shelterId);
//
//        assertEquals(animalsList, result);
//    }

    @Test
    public void testFindAnimalById() {
        long animalId = 1;
        Animals animal = new Animals();
        when(animalsRepository.findById(animalId)).thenReturn(Optional.of(animal));

        Animals result = animalsService.findAnimalById(animalId);

        assertEquals(animal, result);
    }

    @Test
    public void testFindAnimalsByStatus() {
        boolean busyAnimalStatus = true;
        List<Animals> animalsList = new ArrayList<>();
        when(animalsRepository.findAnimalsByBusyFree(busyAnimalStatus)).thenReturn(animalsList);

        Collection<Animals> result = animalsService.findAnimalsByStatus(busyAnimalStatus);

        assertEquals(animalsList, result);
    }

    @Test
    public void testFindAnimalsBySheltersIdAndBusyFree() {
        Long shelterId = 2l;
        boolean busyAnimalStatus = true;
        List<Animals> animalsList = new ArrayList<>();
        when(animalsRepository.findAnimalsBySheltersIdAndBusyFree(shelterId, busyAnimalStatus))
                .thenReturn(animalsList);

        Collection<Animals> result = animalsService
                .findAnimalsBySheltersIdAndBusyFree(shelterId, busyAnimalStatus);

        assertEquals(animalsList, result);
    }

    @Test
    public void testFindAnimalsByType() {
        String animalType = "Dog";
        List<Animals> animalsList = new ArrayList<>();
        when(animalsRepository.findAnimalsByType(animalType)).thenReturn(animalsList);

        Collection<Animals> result = animalsService.findAnimalsByType(animalType);

        assertEquals(animalsList, result);
    }

    @Test
    public void testAddNewAnimal() {
        Animals animal = new Animals();
        when(animalsRepository.save(animal)).thenReturn(animal);

        Animals result = animalsService.addNewAnimal(animal);

        assertEquals(animal, result);
    }

    @Test
    public void testRemoveAnimal() {
        long animalId = 1;
        Animals animal = new Animals();
        when(animalsRepository.findById(animalId)).thenReturn(Optional.of(animal));

        animalsService.removeAnimal(animalId);

        verify(animalsRepository, times(1)).deleteById(animalId);
    }

    @Test
    public void testChangeAnimalInfo() {
        Animals animal = new Animals();
        when(animalsRepository.save(animal)).thenReturn(animal);

        Animals result = animalsService.changeAnimalInfo(animal);

        assertEquals(animal, result);
    }

    @Test
    public void shouldChangeBusyFreeStatus() {
        Long animalId = 1L;
        Boolean busyFreeStatus = false;
        Animals animal = new Animals();
        animal.setId(animalId);
        animal.setBusyFree(!busyFreeStatus);

        when(animalsRepository.findById(animalId)).thenReturn(Optional.of(animal));
        when(animalsRepository.save(animal)).thenReturn(animal);

        Animals updatedAnimal = animalsService.changeBusyFreeStatus(animalId, busyFreeStatus);

        assertEquals(animal, updatedAnimal);
    }
}