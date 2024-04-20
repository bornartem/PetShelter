package com.example.petShelter.command.dog_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/transportingADog")
public class TransportingADog implements Command {
    private final TelegramBotClient telegramBotClient;

    @Autowired
    public TransportingADog(TelegramBotClient telegramBotClient) {
        this.telegramBotClient = telegramBotClient;
    }

    private final static String TRANSPORTING_DOG = "Пожалуйста, учитывайте, что без переноски мы животных не отдаем!";

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, TRANSPORTING_DOG);
    }
}
