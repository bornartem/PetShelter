package com.example.petShelter.command.cat_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.listener.ContactAVolunteerToAdoptACatMenu;
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
 * Class for choosing a cat from a shelter.
 * @author  Maria Sinyavskaya
 */

@Component("/chooseACat")
public class ChooseACat implements Command {

    private Logger log = LoggerFactory.getLogger(SheltersService.class);

    private final AnimalsService animalsService;

    private final AnimalAvatarService animalAvatarService;

    private final TelegramBot telegramBot;

    private final TelegramBotClient telegramBotClient;

    private final ContactAVolunteerToAdoptACatMenu contactAVolunteerToAdoptACatMenu;

    private final static long CAT_SHELTER_ID = 2;

    public ChooseACat(AnimalsService animalsService,
                      AnimalAvatarService animalAvatarService,
                      TelegramBot telegramBot,
                      TelegramBotClient telegramBotClient, ContactAVolunteerToAdoptACatMenu contactAVolunteerToAdoptACatMenu) {
        this.animalsService = animalsService;
        this.animalAvatarService = animalAvatarService;
        this.telegramBot = telegramBot;
        this.telegramBotClient = telegramBotClient;
        this.contactAVolunteerToAdoptACatMenu = contactAVolunteerToAdoptACatMenu;
    }

    @Override
    public void execute(Long chatId) {
        Boolean busyAnimalStatus = true;
        Collection<Animals> freeCats = animalsService.findAnimalsBySheltersIdAndBusyFree(CAT_SHELTER_ID, busyAnimalStatus);

        for (Animals cat : freeCats) {
            AnimalAvatar avatar = animalAvatarService.findAnimalAvatarByAnimalId(cat.getId());
            if (avatar != null) {
                try {
                    byte[] photo = Files.readAllBytes(Path.of(avatar.getFilePath()));
                    String caption = "\n " + cat.getType()
                            + " " + cat.getName() + ", " + cat.getAge();
                    telegramBot.execute(new SendPhoto(chatId, photo).caption(caption));
                } catch (IOException e) {
                    log.error("Error reading avatar file for cat with ID " + cat.getId(), e);
                }
            }
        }

        telegramBotClient.sendMessage(chatId, "Чтобы усыновить понравившуюся кошку, " +
                "Вам необходимо связаться с волонтером");

        contactAVolunteerToAdoptACatMenu.sendMenuMessage(chatId);
    }
}
