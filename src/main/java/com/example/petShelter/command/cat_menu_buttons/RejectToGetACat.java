package com.example.petShelter.command.cat_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/rejectToGetACat")
public class RejectToGetACat implements Command {
    private final TelegramBotClient telegramBotClient;
    private final String rejectFile;

    @Autowired
    public RejectToGetACat(TelegramBotClient telegramBotClient, String rejectFile) {
        this.telegramBotClient = telegramBotClient;
        this.rejectFile = rejectFile;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, rejectFile);
    }
}
