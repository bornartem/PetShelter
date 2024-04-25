package com.example.petShelter.command.home_improvement_dog_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("/homeImprovementWithDisabilities")
public class HomeImprovementWithDisabilities implements Command {
    private final TelegramBotClient telegramBotClient;
    private final String petWithDisabilitiesFile;

    @Autowired
    public HomeImprovementWithDisabilities(TelegramBotClient telegramBotClient,
                                           @Qualifier("petWithDisabilitiesFile") String petWithDisabilitiesFile) {
        this.telegramBotClient = telegramBotClient;
        this.petWithDisabilitiesFile = petWithDisabilitiesFile;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, petWithDisabilitiesFile);
    }
}
