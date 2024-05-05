package com.example.petShelter.command.home_improvement_dog_buttons;

import com.example.petShelter.configuration.TelegramBotConfiguration;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HomeImprovementForPuppyTest {

    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private TelegramBotConfiguration telegramBotConfiguration;

    private HomeImprovementForPuppy homeImprovementForPuppy;

    @Test
    void testExecute() throws Exception {
        Long chatId = 12345L;
        homeImprovementForPuppy = new HomeImprovementForPuppy(telegramBotClient,
                telegramBotConfiguration.littlePetFile());

        homeImprovementForPuppy.execute(chatId);

        verify(telegramBotClient).sendMessage(chatId, telegramBotConfiguration.littlePetFile());
    }
}
