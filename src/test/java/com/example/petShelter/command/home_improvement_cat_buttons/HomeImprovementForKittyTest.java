package com.example.petShelter.command.home_improvement_cat_buttons;

import com.example.petShelter.configuration.TelegramBotConfiguration;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HomeImprovementForKittyTest {

    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private TelegramBotConfiguration telegramBotConfiguration;

    private HomeImprovementForKitty homeImprovementForKitty;

    @Test
    void testExecute() throws Exception {
        Long chatId = 12345L;
        homeImprovementForKitty = new HomeImprovementForKitty(telegramBotClient,
                telegramBotConfiguration.littlePetFile());

        homeImprovementForKitty.execute(chatId);

        verify(telegramBotClient).sendMessage(chatId, telegramBotConfiguration.littlePetFile());
    }
}
