package com.example.petShelter.command.home_improvement_cat_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/homeImprovementForAdultCat")
public class HomeImprovementForAdultCat implements Command {
    private final TelegramBotClient telegramBotClient;
    private final String adultPetFile;

    @Autowired
    public HomeImprovementForAdultCat(TelegramBotClient telegramBotClient, String adultPetFile) {
        this.telegramBotClient = telegramBotClient;
        this.adultPetFile = adultPetFile;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, adultPetFile);
    }
}
