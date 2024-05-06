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
public class DocumentsForGetACatTest {
    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private TelegramBotConfiguration telegramBotConfiguration;

    private DocumentsForGetACat documentsForGetACat;

    @Test
    void testExecute() throws IOException {
        Long chatId = 12345L;
        documentsForGetACat = new DocumentsForGetACat(telegramBotClient, telegramBotConfiguration.docForGetPet());

        documentsForGetACat.execute(chatId);

        verify(telegramBotClient).sendMessage(chatId, telegramBotConfiguration.docForGetPet());
    }
}
