package com.example.petShelter.command.dog_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("/dogHandlerAdvices")
public class DogHandlerAdvices implements Command {
    private final TelegramBotClient telegramBotClient;
    private final String dogHandlerAdvicesFile;

    @Autowired
    public DogHandlerAdvices(TelegramBotClient telegramBotClient,
                             @Qualifier("dogHandlerAdvicesFile") String dogHandlerAdvicesFile) {
        this.telegramBotClient = telegramBotClient;
        this.dogHandlerAdvicesFile = dogHandlerAdvicesFile;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, dogHandlerAdvicesFile);
    }
}
