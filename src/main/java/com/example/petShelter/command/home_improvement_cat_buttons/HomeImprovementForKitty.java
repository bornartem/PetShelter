package com.example.petShelter.command.home_improvement_cat_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/homeImprovementForKitty")
public class HomeImprovementForKitty implements Command {
    private final TelegramBotClient telegramBotClient;
    private final String littlePetFile;

    @Autowired
    public HomeImprovementForKitty(TelegramBotClient telegramBotClient, String littlePetFile) {
        this.telegramBotClient = telegramBotClient;
        this.littlePetFile = littlePetFile;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, littlePetFile);
    }
}
