package com.example.petShelter.command.dog_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("/dogHandlersList")
public class DogHandlersList implements Command {
    private final TelegramBotClient telegramBotClient;
    private final String dogHandlersListFile;

    @Autowired
    public DogHandlersList(TelegramBotClient telegramBotClient,
                           @Qualifier("dogHandlersListFile") String dogHandlersListFile) {
        this.telegramBotClient = telegramBotClient;
        this.dogHandlersListFile = dogHandlersListFile;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, dogHandlersListFile);
    }
}
