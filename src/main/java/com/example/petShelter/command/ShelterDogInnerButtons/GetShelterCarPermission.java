package com.example.petShelter.command.ShelterDogInnerButtons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;


/**
 * Class which shows the information about  permission number for the dog shelter,
 * which allows to enter to the shelter territory.
 * @author  Khilola Kushbakova
 */


@Component ("/CarPermissionNumber")
public class GetShelterCarPermission implements Command {
    private final TelegramBotClient telegramBotClient;

    private final SheltersService sheltersService;

    private final static long DOG_SHELTER_ID = 1;


    public GetShelterCarPermission(TelegramBotClient telegramBotClient,
                                   SheltersService sheltersService) {
        this.telegramBotClient = telegramBotClient;
        this.sheltersService = sheltersService;
    }

    @Override
    public void execute(Long chatId) {
        String securityContact = sheltersService.showSecurityContact(DOG_SHELTER_ID );
        telegramBotClient.sendMessage(chatId, securityContact);
    }
}
