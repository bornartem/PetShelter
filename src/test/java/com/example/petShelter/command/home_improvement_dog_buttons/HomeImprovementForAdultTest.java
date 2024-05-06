package com.example.petShelter.command.home_improvement_dog_buttons;

import com.example.petShelter.configuration.TelegramBotConfiguration;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HomeImprovementForAdultTest {

    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private TelegramBotConfiguration telegramBotConfiguration;

    private HomeImprovementForAdult homeImprovementForAdult;

    @Test
    void testExecute() throws Exception {
        Long chatId = 12345L;
        homeImprovementForAdult = new HomeImprovementForAdult(telegramBotClient,
                telegramBotConfiguration.adultPetFile());

        homeImprovementForAdult.execute(chatId);

        verify(telegramBotClient).sendMessage(chatId, telegramBotConfiguration.adultPetFile());
    }
}
