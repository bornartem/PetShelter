package com.example.petShelter.command.ShelterDogInnerButtons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;


/**
 * Class which shows the information about dog shelter schedule.
 * @author  Khilola Kushbakova
 */


@Component ("/getDogShelterSchedule")
public class DogShelterSchedule implements Command {
    private final TelegramBotClient telegramBotClient;

    private final SheltersService sheltersService;

    private final static long DOG_SHELTER_ID = 1;

    public DogShelterSchedule(TelegramBotClient telegramBotClient,
                                         SheltersService sheltersService) {
        this.telegramBotClient = telegramBotClient;
        this.sheltersService = sheltersService;
    }

    @Override
    public void execute(Long chatId) {
        String schedule = sheltersService.showSchedule(DOG_SHELTER_ID);
        telegramBotClient.sendMessage(chatId, schedule);
    }
}

