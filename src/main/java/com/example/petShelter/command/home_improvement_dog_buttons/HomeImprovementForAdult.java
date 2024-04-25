package com.example.petShelter.command.home_improvement_dog_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("/homeImprovementForAdult")
public class HomeImprovementForAdult implements Command {
    private final TelegramBotClient telegramBotClient;
    private final String adultPetFile;

    @Autowired
    public HomeImprovementForAdult(TelegramBotClient telegramBotClient,
                                   @Qualifier("adultPetFile") String adultPetFile) {
        this.telegramBotClient = telegramBotClient;
        this.adultPetFile = adultPetFile;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, adultPetFile);
    }
}
