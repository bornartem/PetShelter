package com.example.petShelter.command.ShelterCatInnerButtons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;

/**
 * Class which shows the information about a cat shelter after clicking the button in Telegram to info.
 * @author  Khilola Kushbakova
 */



@Component("/getCatInfo")
public class CatShelterInfoCommand implements Command {
    private final SheltersService sheltersService;
    private final static long CAT_SHELTER_ID = 1;
   private TelegramBotClient telegramBotClient;

    public CatShelterInfoCommand(SheltersService sheltersService, TelegramBotClient telegramBotClient) {
        this.sheltersService = sheltersService;
        this.telegramBotClient = telegramBotClient;
    }

    @Override
    public void execute(Long chatId) {
        String catShelterInfo = sheltersService.showAnimalInfoById(CAT_SHELTER_ID);
        telegramBotClient.sendMessage(chatId, catShelterInfo);
    }
}
