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
public class InfoAboutDogShelterMenuTest {

    @Mock
    private TelegramBot bot;

    private InfoAboutDogShelterMenu infoAboutDogShelterMenu;

    @Test
    public void testSendMenuMessage() {
        Long chatId = 123456L;
        infoAboutDogShelterMenu = new InfoAboutDogShelterMenu(bot);

        infoAboutDogShelterMenu.sendMenuMessage(chatId);

        verify(bot).execute(any(SendMessage.class));
    }
}
