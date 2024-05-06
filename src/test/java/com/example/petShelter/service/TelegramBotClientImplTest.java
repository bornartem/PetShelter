package com.example.petShelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TelegramBotClientImplTest {

    @Mock
    private TelegramBot telegramBot;

    private TelegramBotClientImpl telegramBotClient;

    @Test
    public void testSendMessage() {
        telegramBotClient = new TelegramBotClientImpl(telegramBot);
        Long chatId = 123456L;
        String message = "Test message";

        telegramBotClient.sendMessage(chatId, message);

        SendMessage expectedSendMessage = new SendMessage(chatId, message);
        verify(telegramBot).execute(expectedSendMessage);
    }
}
