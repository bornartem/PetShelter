package com.example.petShelter.command.ShelterCatInnerButtons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;


/**
 * Class which shows the information about  permission number for the cat shelter,
 *  * which allows to enter to the shelter territory.
 * @author  Khilola Kushbakova
 */


@Component ("/CarPermissionNumberCatShelter")
public class GetCatShelterCarPermission implements Command {
    private final TelegramBotClient telegramBotClient;

    private final SheltersService sheltersService;

    private final static long CAT_SHELTER_ID = 1;

    public GetCatShelterCarPermission(TelegramBotClient telegramBotClient,
                                      SheltersService sheltersService) {
        this.telegramBotClient = telegramBotClient;
        this.sheltersService = sheltersService;
    }

    @Override
    public void execute(Long chatId) {
        String securityContact = sheltersService.showSecurityContact(CAT_SHELTER_ID);
        telegramBotClient.sendMessage(chatId, securityContact);
    }
}
