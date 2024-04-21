package com.example.petShelter.command.home_improvement_cat_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/homeImprovementForCatWithDisabilities")
public class HomeImprovementForCatWithDisabilities implements Command {
    private final TelegramBotClient telegramBotClient;
    private final String petWithDisabilitiesFile;

    @Autowired
    public HomeImprovementForCatWithDisabilities(TelegramBotClient telegramBotClient,
                                                 String petWithDisabilitiesFile) {
        this.telegramBotClient = telegramBotClient;
        this.petWithDisabilitiesFile = petWithDisabilitiesFile;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, petWithDisabilitiesFile);
    }
}
