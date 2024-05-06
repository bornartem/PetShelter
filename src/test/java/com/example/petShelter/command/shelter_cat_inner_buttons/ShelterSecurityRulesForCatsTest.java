package com.example.petShelter.command.shelter_cat_inner_buttons;

import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShelterSecurityRulesForCatsTest {

    @Mock
    private SheltersService sheltersService;

    @Mock
    private TelegramBotClient telegramBotClient;

    private ShelterSecurityRulesForCats securityRulesForCats;

    private final static long CAT_SHELTER_ID = 2;

    @Test
    void executeMethodShouldSendMessage() {
        Long chatId = 123456L;
        securityRulesForCats = new ShelterSecurityRulesForCats(telegramBotClient, sheltersService);

        String rules = "Security rules for cat shelter";
        when(sheltersService.showShelterRules(CAT_SHELTER_ID)).thenReturn(rules);

        securityRulesForCats.execute(chatId);

        verify(telegramBotClient, times(1)).sendMessage(chatId, rules);
        verify(sheltersService).showShelterRules(CAT_SHELTER_ID);
    }

}
