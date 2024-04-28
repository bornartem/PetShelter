package com.example.petShelter.command.shelter_dog_inner_buttons;

import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShelterSecurityRulesForDogsTest {

    @Mock
    private SheltersService sheltersService;

    @Mock
    private TelegramBotClient telegramBotClient;

    private ShelterSecurityRulesForDogs securityRulesForDogs;

    private final static long DOG_SHELTER_ID = 1;

    @Test
    void executeMethodShouldSendMessage() {
        Long chatId = 123456L;
        securityRulesForDogs = new ShelterSecurityRulesForDogs(telegramBotClient, sheltersService);

        String rules = "Security rules for dog shelter";
        when(sheltersService.showShelterRules(DOG_SHELTER_ID)).thenReturn(rules);

        securityRulesForDogs.execute(chatId);

        verify(telegramBotClient, times(1)).sendMessage(chatId, rules);
        verify(sheltersService).showShelterRules(DOG_SHELTER_ID);
    }

}
