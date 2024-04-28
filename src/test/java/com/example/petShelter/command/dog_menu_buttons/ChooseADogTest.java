package com.example.petShelter.command.dog_menu_buttons;

import com.example.petShelter.listener.ContactAVolunteerToAdoptADogMenu;
import com.example.petShelter.model.AnimalAvatar;
import com.example.petShelter.model.Animals;
import com.example.petShelter.model.Shelters;
import com.example.petShelter.service.AnimalAvatarService;
import com.example.petShelter.service.AnimalsService;
import com.example.petShelter.service.TelegramBotClient;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendPhoto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChooseADogTest {

    @Mock
    private AnimalsService animalsService;

    @Mock
    private AnimalAvatarService animalAvatarService;

    @Mock
    private TelegramBot telegramBot;

    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private ContactAVolunteerToAdoptADogMenu contactAVolunteerToAdoptADogMenu;

    private ChooseADog chooseADog;

    @Test
    public void executeMethodShouldSendPhotoAndMenu() {
        chooseADog = new ChooseADog(animalsService,
                animalAvatarService, telegramBot,
                telegramBotClient, contactAVolunteerToAdoptADogMenu);
        long chatId = 12345L;
        Boolean busyAnimalStatus = true;

        Shelters shelter = new Shelters();
        shelter.setId(1L);

        Collection<Animals> freeDogs = new ArrayList<>();

        Animals animal1 = new Animals();
        animal1.setId(3L);
        animal1.setName("Бруно");
        animal1.setAge("7 месяцев");
        animal1.setType("Собака");
        animal1.setBusyFree(true);
        animal1.setShelters(shelter);

        Animals animal2 = new Animals();
        animal2.setId(4L);
        animal2.setName("Дрим");
        animal2.setAge("6 месяцев");
        animal2.setType("Собака");
        animal2.setBusyFree(true);
        animal2.setShelters(shelter);

        freeDogs.add(animal1);
        freeDogs.add(animal2);

        when(animalsService.findAnimalsBySheltersIdAndBusyFree(shelter.getId(), busyAnimalStatus))
                .thenReturn(freeDogs);

        AnimalAvatar avatar = new AnimalAvatar(1L, "\\avatars\\3.jpg",
                91520, "image/jpeg", new byte[10], animal1);
        when(animalAvatarService.findAnimalAvatarByAnimalId(animal1.getId())).thenReturn(avatar);

        chooseADog.execute(chatId);

        verify(telegramBot).execute(any(SendPhoto.class));
        verify(contactAVolunteerToAdoptADogMenu).sendMenuMessage(chatId);
    }
}
