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
public class ContactAVolunteerToAdoptACatMenuTest {
    @Mock
    private TelegramBot telegramBot;
    private ContactAVolunteerToAdoptACatMenu menu;

    @Test
    public void testSendMenuMessage() {
        Long chatId = 123456L;
        menu = new ContactAVolunteerToAdoptACatMenu(telegramBot);

        menu.sendMenuMessage(chatId);

        verify(telegramBot).execute(any(SendMessage.class));
    }
}
