package com.example.petShelter.command.cat_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/getInfoAboutCatMeeting")
public class GetInfoAboutCatMeeting implements Command {
    private final TelegramBotClient telegramBotClient;
    private final String meetingFile;

    @Autowired
    public GetInfoAboutCatMeeting(TelegramBotClient telegramBotClient, String meetingFile) {
        this.telegramBotClient = telegramBotClient;
        this.meetingFile = meetingFile;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, meetingFile);
    }
}
