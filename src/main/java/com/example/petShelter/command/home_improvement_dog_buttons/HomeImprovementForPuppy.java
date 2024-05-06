package com.example.petShelter.command.home_improvement_dog_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("/homeImprovementForPuppy")
public class HomeImprovementForPuppy implements Command {
    private final TelegramBotClient telegramBotClient;
    private final String littlePetFile;

    @Autowired
    public HomeImprovementForPuppy(TelegramBotClient telegramBotClient,
                                   @Qualifier("littlePetFile") String littlePetFile) {
        this.telegramBotClient = telegramBotClient;
        this.littlePetFile = littlePetFile;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, littlePetFile);
    }
}
