package com.example.petShelter.service;

import com.example.petShelter.model.AnimalAvatar;
import com.example.petShelter.model.Animals;
import com.example.petShelter.repository.AnimalAvatarRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnimalAvatarServiceTest {


    @Mock
    private AnimalAvatarRepository animalAvatarRepository;

    @Mock
    private AnimalsService animalsService;

    @Mock
    private Path path;

    @InjectMocks
    private AnimalAvatarService out;

    @BeforeEach
    public void initOut() throws NoSuchFieldException, IllegalAccessException {
        out = new AnimalAvatarService(
                animalsService,
                animalAvatarRepository
        );
        out.setAvatarsDir("/avatar");
    }


    @Test
    public void uploadImage() {

        Long animalId = 1L;
        String name = "bobik";
        boolean busy = true;
        Animals animal = new Animals();
        animal.setId(animalId);
        animal.setName(name);
        animal.setBusyFree(busy);
        byte[] fileBytes = {72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100};

        // Инициализируем объект MultipartFile
        MultipartFile multipartFile = new MockMultipartFile("file", "file.txt", "text/plain", fileBytes);

        when(animalsService.findAnimalById(any())).thenReturn(animal);
        when(animalAvatarRepository.findById(any())).thenReturn(Optional.empty());
        when(animalAvatarRepository.save(any())).thenReturn(null);


        try {
            out.uploadImage(animalId, multipartFile);
        } catch (IOException e) {
            throw new RuntimeException("error in working with multiPartFiles");
        }

        verify(animalAvatarRepository, times(1)).save(any());
        verify(animalsService, times(1)).findAnimalById(any());



        Throwable exception = assertThrows(NullPointerException.class, () -> {
            // вызов метода или блок кода, который должен выбрасывать исключение
            out.uploadImage(animalId,null);
        });

    }


    @Test
    public void findAnimalAvatarById() {

        Long animalAvatarId = 1L;

        when(animalAvatarRepository.findById(2L)).thenReturn(Optional.empty());
        when(animalAvatarRepository.findById(animalAvatarId)).thenReturn(Optional.of(new AnimalAvatar()));

        Assertions.assertThat(out.findAnimalAvatarById(2L)).isEqualTo(null);
        Assertions.assertThat(out.findAnimalAvatarById(animalAvatarId)).isEqualTo(new AnimalAvatar());
    }

    @Test
    public void testFindAnimalAvatarByAnimalId_Found() {
        Long animalId = 1L;
        AnimalAvatar expectedAvatar = new AnimalAvatar();
        when(animalAvatarRepository.findAnimalAvatarByAnimalId(animalId)).thenReturn(expectedAvatar);

        AnimalAvatar result = out.findAnimalAvatarByAnimalId(animalId);

        assertNotNull(result);
        assertEquals(expectedAvatar, result);
    }


}
