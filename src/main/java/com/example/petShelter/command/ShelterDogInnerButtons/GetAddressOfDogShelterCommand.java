package com.example.petShelter.command.ShelterDogInnerButtons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;

/**
 * Class which shows the information about a dog shelter address.
 * @author  Maria Sinyavskaya
 */

@Component("/getAddressOfDogShelter")
public class GetAddressOfDogShelterCommand implements Command {

    private final TelegramBotClient telegramBotClient;

    private final SheltersService sheltersService;

    private final static long DOG_SHELTER_ID = 1;

    public GetAddressOfDogShelterCommand(TelegramBotClient telegramBotClient,
                                         SheltersService sheltersService) {
        this.telegramBotClient = telegramBotClient;
        this.sheltersService = sheltersService;
    }

    @Override
    public void execute(Long chatId) {
        String address = sheltersService.showAddress(DOG_SHELTER_ID);
        telegramBotClient.sendMessage(chatId, address);
    }
}
