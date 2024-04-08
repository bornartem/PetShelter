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
    private final static String DOG_SHELTER_SCHEDULE = "Мы рады видеть наших гостей ежедневно с 10.00 до 16.00" +
            "Прогулки с собаками ежедневно с 12 до 15 часов.";

    public DogShelterSchedule(TelegramBotClient telegramBotClient,
                                         SheltersService sheltersService) {
        this.telegramBotClient = telegramBotClient;
        this.sheltersService = sheltersService;
    }

    @Override
    public void execute(Long chatId) {
        String schedule = sheltersService.showSchedule(DOG_SHELTER_ID,DOG_SHELTER_SCHEDULE);
        telegramBotClient.sendMessage(chatId, schedule);
    }
}

