package com.example.petShelter.command.shelter_cat_inner_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;

/**
 * Class which shows the information about a cat shelter schedule.
 * @author  Khilola Kushbakova
 */

@Component ("/getCatShelterSchedule")
public class CatShelterSchedule implements Command {
    private final TelegramBotClient telegramBotClient;

    private final SheltersService sheltersService;

    private final static long CAT_SHELTER_ID = 2;

    public CatShelterSchedule(TelegramBotClient telegramBotClient,
                              SheltersService sheltersService) {
        this.telegramBotClient = telegramBotClient;
        this.sheltersService = sheltersService;
    }

    @Override
    public void execute(Long chatId) {
        String schedule = sheltersService.showSchedule(CAT_SHELTER_ID);
        telegramBotClient.sendMessage(chatId, schedule);
    }
}

