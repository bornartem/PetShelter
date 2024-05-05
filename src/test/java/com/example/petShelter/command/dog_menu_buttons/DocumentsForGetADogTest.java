package com.example.petShelter.command.dog_menu_buttons;

import com.example.petShelter.configuration.TelegramBotConfiguration;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DocumentsForGetADogTest {
    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private TelegramBotConfiguration telegramBotConfiguration;

    private DocumentsForGetADog documentsForGetADog;

    @Test
    void testExecute() throws IOException {
        Long chatId = 12345L;
        documentsForGetADog = new DocumentsForGetADog(telegramBotClient, telegramBotConfiguration.docForGetPet());

        documentsForGetADog.execute(chatId);

        verify(telegramBotClient).sendMessage(chatId, telegramBotConfiguration.docForGetPet());
    }
}
