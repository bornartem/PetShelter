package com.example.petShelter.command.ShelterCatInnerButtons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;
/**
 * Class which shows the information about a cat shelter location.
 * @author  Khilola Kushbakova
 */
@Component ("/getCatShelterLocation")
public class GetCatShelterLocation implements Command {
    private final TelegramBotClient telegramBotClient;

    private final SheltersService sheltersService;

    private final static double CAT_SHELTER_LATITUDE = 59.843930112491435;
    private final static double CAT_SHELTER_LONGITUDE = 30.4450572866618;

    public GetCatShelterLocation(TelegramBotClient telegramBotClient,
                                 SheltersService sheltersService) {
        this.telegramBotClient = telegramBotClient;
        this.sheltersService = sheltersService;
    }

    @Override
    public void execute(Long chatId) {
        String location = sheltersService.showLocation(CAT_SHELTER_LATITUDE, CAT_SHELTER_LONGITUDE);
        telegramBotClient.sendMessage(chatId, location);
    }
}

