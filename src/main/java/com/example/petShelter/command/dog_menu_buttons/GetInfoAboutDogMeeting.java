package com.example.petShelter.command.dog_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("/getInfoAboutDogMeeting")
public class GetInfoAboutDogMeeting implements Command {
    private final TelegramBotClient telegramBotClient;
    private final String meetingFile;

    @Autowired
    public GetInfoAboutDogMeeting(TelegramBotClient telegramBotClient,
                                  @Qualifier("meetingFile") String meetingFile) {
        this.telegramBotClient = telegramBotClient;
        this.meetingFile = meetingFile;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, meetingFile);
    }
}
