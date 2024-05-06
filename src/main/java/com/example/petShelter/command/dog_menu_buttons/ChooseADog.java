package com.example.petShelter.command.dog_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.listener.ContactAVolunteerToAdoptADogMenu;
import com.example.petShelter.model.AnimalAvatar;
import com.example.petShelter.model.Animals;
import com.example.petShelter.service.AnimalAvatarService;
import com.example.petShelter.service.AnimalsService;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendPhoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

/**
 * Class for choosing a dog from a shelter.
 * @author  Maria Sinyavskaya
 */

@Component("/chooseADog")
public class ChooseADog implements Command {

    private Logger log = LoggerFactory.getLogger(SheltersService.class);

    private final AnimalsService animalsService;

    private final AnimalAvatarService animalAvatarService;

    private final TelegramBot telegramBot;

    private final TelegramBotClient telegramBotClient;

    private final ContactAVolunteerToAdoptADogMenu contactAVolunteerToAdoptADogMenu;

    private final static long DOG_SHELTER_ID = 1;

    public ChooseADog(AnimalsService animalsService,
                      AnimalAvatarService animalAvatarService,
                      TelegramBot telegramBot,
                      TelegramBotClient telegramBotClient, ContactAVolunteerToAdoptADogMenu contactAVolunteerToAdoptADogMenu) {
        this.animalsService = animalsService;
        this.animalAvatarService = animalAvatarService;
        this.telegramBot = telegramBot;
        this.telegramBotClient = telegramBotClient;
        this.contactAVolunteerToAdoptADogMenu = contactAVolunteerToAdoptADogMenu;
    }

    @Override
    public void execute(Long chatId) {
        Boolean busyAnimalStatus = true;
        Collection<Animals> freeDogs = animalsService
                .findAnimalsBySheltersIdAndBusyFree(DOG_SHELTER_ID, busyAnimalStatus);

        for (Animals dog : freeDogs) {
            AnimalAvatar avatar = animalAvatarService.findAnimalAvatarByAnimalId(dog.getId());
            if (avatar != null) {
                try {
                    byte[] photo = Files.readAllBytes(Path.of(avatar.getFilePath()));
                    String caption = "\n " + dog.getType()
                            + " " + dog.getName() + ", " + dog.getAge();
                    telegramBot.execute(new SendPhoto(chatId, photo).caption(caption));
                } catch (IOException e) {
                    log.error("Error reading avatar file for dog with ID " + dog.getId(), e);
                }
            }
        }

        telegramBotClient.sendMessage(chatId, "Чтобы усыновить понравившуюся собаку, " +
                "Вам необходимо связаться с волонтером");

        contactAVolunteerToAdoptADogMenu.sendMenuMessage(chatId);
    }
}
