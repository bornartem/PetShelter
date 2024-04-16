package com.example.petShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InfoAboutCatShelterMenuTest {

    @Mock
    private TelegramBot bot;

    private InfoAboutCatShelterMenu infoAboutCatShelterMenu;

    @Test
    public void testSendMenuMessage() {
        Long chatId = 123456L;
        infoAboutCatShelterMenu = new InfoAboutCatShelterMenu(bot);

        infoAboutCatShelterMenu.sendMenuMessage(chatId);

        verify(bot).execute(any(SendMessage.class));
    }
}
