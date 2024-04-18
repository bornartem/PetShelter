package com.example.petShelter.command.ShelterDogInnerButtons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;

/**
 * Class which shows the information about a dog shelter location.
 * @author  Khilola Kushbakova
 */

@Component("/getDogShelterLocation")
public class GetDogShelterLocation implements Command {
    private final TelegramBotClient telegramBotClient;

    private final SheltersService sheltersService;

    private final static double DOG_SHELTER_LATITUDE = 41.14046547170331;
    private final static double DOG_SHELTER_LONGITUDE = 1.2202950534118546;

    public GetDogShelterLocation(TelegramBotClient telegramBotClient,
                                 SheltersService sheltersService) {
        this.telegramBotClient = telegramBotClient;
        this.sheltersService = sheltersService;
    }

    @Override
    public void execute(Long chatId) {
        String location = sheltersService.showLocation(DOG_SHELTER_LATITUDE, DOG_SHELTER_LONGITUDE);
        telegramBotClient.sendMessage(chatId, location);
    }
}

