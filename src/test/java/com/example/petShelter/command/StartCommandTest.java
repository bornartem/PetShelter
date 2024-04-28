package com.example.petShelter.command;

import com.example.petShelter.listener.ChoosingShelterMenu;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StartCommandTest {

    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private ChoosingShelterMenu choosingShelterMenu;

    private StartCommand startCommand;

    private final static String START_MESSAGE = "Привет! Это приют \"Пушистый друг\". " +
            "\nМы помогаем бездомным животным найти новый дом.";
    @Test
    void executeMethodShouldSendsMessageAndMenu() {
        Long chatId = 123456L;
        startCommand = new StartCommand(telegramBotClient, choosingShelterMenu);

        startCommand.execute(chatId);

        verify(telegramBotClient, times(1)).sendMessage(chatId, START_MESSAGE);
        verify(choosingShelterMenu, times(1)).sendMenuMessage(chatId);
    }
}

