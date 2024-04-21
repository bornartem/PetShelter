package com.example.petShelter.command.dog_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("/rejectToGetADog")
public class RejectToGetADog implements Command {
    private final TelegramBotClient telegramBotClient;
    private final String rejectFile;

    @Autowired
    public RejectToGetADog(TelegramBotClient telegramBotClient,
                           @Qualifier("rejectFile") String rejectFile) {
        this.telegramBotClient = telegramBotClient;
        this.rejectFile = rejectFile;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, rejectFile);
    }
}
