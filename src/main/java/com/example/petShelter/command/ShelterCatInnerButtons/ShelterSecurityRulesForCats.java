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
    private final static String CAT_SHELTER_RULES = "важный момент: перед тем, как ловить животное, озаботьтесь поиском хорошего ветеринарного врача. Поспрашивайте опытных волонтеров, оцените оснащение клиники (какие обследования там могут провести – рентген, УЗИ и т.д.), выясните график работы, порядок записи и приема. Поверьте, потом, с испуганной кошкой на руках, вы будете горько сожалеть, если проигнорируете этот шаг.";

    public ShelterSecurityRulesForCats(TelegramBotClient telegramBotClient,
                                       SheltersService sheltersService) {
        this.telegramBotClient = telegramBotClient;
        this.sheltersService = sheltersService;
    }

    @Override
    public void execute(Long chatId) {
        String rules = sheltersService.showShelterRules(CAT_SHELTER_ID, CAT_SHELTER_RULES);
        telegramBotClient.sendMessage(chatId, rules);
    }
}
