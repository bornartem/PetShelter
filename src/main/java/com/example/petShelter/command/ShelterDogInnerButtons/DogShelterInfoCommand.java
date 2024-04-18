package com.example.petShelter.command.ShelterDogInnerButtons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;
/**
 * Class which shows the information about dog shelter after clicking the button in Telegram to info.
 *
 * @author Khilola Kushbakova
 */
@Component("/getDogShelterInfo")
public class DogShelterInfoCommand implements Command {
    private final SheltersService sheltersService;
    private final static long DOG_SHELTER_ID = 1;
    private TelegramBotClient telegramBotClient;

    public DogShelterInfoCommand(SheltersService sheltersService, TelegramBotClient telegramBotClient) {
        this.sheltersService = sheltersService;
        this.telegramBotClient = telegramBotClient;
    }

    @Override
    public void execute(Long chatId) {
        String dogShelterInfo = sheltersService.showAnimalInfoById(DOG_SHELTER_ID);
        telegramBotClient.sendMessage(chatId, dogShelterInfo);
    }
}
