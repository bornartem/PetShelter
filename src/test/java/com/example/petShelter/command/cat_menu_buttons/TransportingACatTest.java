package com.example.petShelter.command.cat_menu_buttons;

import com.example.petShelter.configuration.TelegramBotConfiguration;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransportingACatTest {
    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private TelegramBotConfiguration telegramBotConfiguration;

    private TransportingACat transportingACat;

    @Test
    void testExecute() throws IOException {
        Long chatId = 12345L;
        transportingACat = new TransportingACat(telegramBotClient, telegramBotConfiguration.transportingFile());

        transportingACat.execute(chatId);

        verify(telegramBotClient).sendMessage(chatId, telegramBotConfiguration.transportingFile());
    }
}
