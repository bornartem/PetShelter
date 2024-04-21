package com.example.petShelter.command.dog_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("/transportingADog")
public class TransportingADog implements Command {
    private final TelegramBotClient telegramBotClient;
    private final String transportingFile;

    @Autowired
    public TransportingADog(TelegramBotClient telegramBotClient,
                            @Qualifier("transportingFile") String transportingFile) {
        this.telegramBotClient = telegramBotClient;
        this.transportingFile = transportingFile;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, transportingFile);
    }
}
