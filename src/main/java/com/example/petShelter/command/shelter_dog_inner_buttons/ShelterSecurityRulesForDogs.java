package com.example.petShelter.command.shelter_dog_inner_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;
/**
 * Class which shows the information about a dog shelter security rules on territory of shelter.
 * @author  Khilola Kushbakova
 */
@Component ("/ShelterSecurityRulesForDogs")
public class ShelterSecurityRulesForDogs implements Command {
    private final TelegramBotClient telegramBotClient;

    private final SheltersService sheltersService;

    private final static long DOG_SHELTER_ID = 1;


    public ShelterSecurityRulesForDogs(TelegramBotClient telegramBotClient,
                              SheltersService sheltersService) {
        this.telegramBotClient = telegramBotClient;
        this.sheltersService = sheltersService;
    }

    @Override
    public void execute(Long chatId) {
        String rules = sheltersService.showShelterRules(DOG_SHELTER_ID);
        telegramBotClient.sendMessage(chatId, rules);
    }
}
