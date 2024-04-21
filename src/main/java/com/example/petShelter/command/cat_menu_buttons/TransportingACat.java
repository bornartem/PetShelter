package com.example.petShelter.command.cat_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/transportingACat")
public class TransportingACat implements Command {
    private final TelegramBotClient telegramBotClient;
    private final String transportingFile;

    @Autowired
    public TransportingACat(TelegramBotClient telegramBotClient, String transportingFile) {
        this.telegramBotClient = telegramBotClient;
        this.transportingFile = transportingFile;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, transportingFile);
    }
}
