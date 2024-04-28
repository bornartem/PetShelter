package com.example.petShelter.command.cat_menu_buttons;

import com.example.petShelter.listener.ContactAVolunteerToAdoptACatMenu;
import com.example.petShelter.model.Animals;
import com.example.petShelter.model.AnimalAvatar;
import com.example.petShelter.model.Shelters;
import com.example.petShelter.service.AnimalsService;
import com.example.petShelter.service.AnimalAvatarService;
import com.example.petShelter.service.TelegramBotClient;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendPhoto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChooseACatTest {

    @Mock
    private AnimalsService animalsService;

    @Mock
    private AnimalAvatarService animalAvatarService;

    @Mock
    private TelegramBot telegramBot;

    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private ContactAVolunteerToAdoptACatMenu contactAVolunteerToAdoptACatMenu;

    private ChooseACat chooseACat;

    @Test
    public void executeMethodShouldSendPhotoAndMenu() {
        chooseACat = new ChooseACat(animalsService,
                animalAvatarService, telegramBot,
                telegramBotClient, contactAVolunteerToAdoptACatMenu);
        long chatId = 12345L;
        Boolean busyAnimalStatus = true;

        Shelters shelter = new Shelters();
        shelter.setId(2L);

        Collection<Animals> freeCats = new ArrayList<>();

        Animals animal1 = new Animals();
        animal1.setId(1L);
        animal1.setName("Шанталь");
        animal1.setAge("5 месяцев");
        animal1.setType("Кошка");
        animal1.setBusyFree(true);
        animal1.setShelters(shelter);

        Animals animal2 = new Animals();
        animal2.setId(2L);
        animal2.setName("Барабашка");
        animal2.setAge("6 месяцев");
        animal2.setType("Кошка");
        animal2.setBusyFree(true);
        animal2.setShelters(shelter);

        freeCats.add(animal1);
        freeCats.add(animal2);

        when(animalsService.findAnimalsBySheltersIdAndBusyFree(shelter.getId(), busyAnimalStatus))
                .thenReturn(freeCats);

        AnimalAvatar avatar = new AnimalAvatar(1L, "\\avatars\\1.jpg",
                13307, "image/jpeg", new byte[10], animal1);
        when(animalAvatarService.findAnimalAvatarByAnimalId(animal1.getId())).thenReturn(avatar);

        chooseACat.execute(chatId);

        verify(telegramBot).execute(any(SendPhoto.class));
        verify(contactAVolunteerToAdoptACatMenu).sendMenuMessage(chatId);
    }
}
