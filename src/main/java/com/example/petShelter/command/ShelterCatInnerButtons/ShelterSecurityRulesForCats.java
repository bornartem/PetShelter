package com.example.petShelter.command.ShelterCatInnerButtons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;


/**
 * Class which shows the information about a cat shelter security rules on territory of shelter.
 * @author  Khilola Kushbakova
 */



@Component ("/ShelterSecurityRulesForCats")
public class ShelterSecurityRulesForCats implements Command {
    private final TelegramBotClient telegramBotClient;

    private final SheltersService sheltersService;

    private final static long CAT_SHELTER_ID = 1;

    public ShelterSecurityRulesForCats(TelegramBotClient telegramBotClient,
                                       SheltersService sheltersService) {
        this.telegramBotClient = telegramBotClient;
        this.sheltersService = sheltersService;
    }

    @Override
    public void execute(Long chatId) {
        String rules = sheltersService.showShelterRules(CAT_SHELTER_ID);
        telegramBotClient.sendMessage(chatId, rules);
    }
}
