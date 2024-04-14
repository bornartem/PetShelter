package com.example.petShelter.service;

import com.example.petShelter.model.AnimalAvatar;
import com.example.petShelter.model.Animals;
import com.example.petShelter.repository.AnimalAvatarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
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

//        // Инициализация mock-объектов
//        MockitoAnnotations.initMocks(this);
//
//        // Устанавливаем новое значение для поля avatarsDir с помощью рефлексии
//        Field field = out.getClass().getDeclaredField("avatarsDir");
//        field.setAccessible(true);
//        field.set(out, "новый/путь/к/аватаркам");
    }
//
//    @Mock
//    @Value("${path.to.avatars.folder}")
//    private String avatarsDir;

    @Test
    public void uploadImage() {

        Long animalId = 1L;
        String name = "bobik";
        boolean busy = true;
        byte[] fileBytes = {72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100};

        // Инициализируем объект MultipartFile
        MultipartFile multipartFile = new MockMultipartFile("file", "file.txt", "text/plain", fileBytes);

        when(animalsService.findAnimalById(any())).thenReturn(new Animals(animalId, name, busy));
        when(animalAvatarRepository.findById(any())).thenReturn(Optional.empty());
        when(animalAvatarRepository.save(any())).thenReturn(null);
//        when(Path.of(any())).thenReturn(Path.of("\\avatars", "\\1.jpeg"));
//        when(avatarsDir).thenReturn("/avatars   ");

//        out.setAvatarsDir("/avatar");

        try {
            out.uploadImage(animalId, multipartFile);
        } catch (IOException e) {
            throw new RuntimeException("error in working with multiPartFiles");
        }

        verify(animalAvatarRepository, times(1)).save(any());


    }


    /*
    create checking
     */

}
