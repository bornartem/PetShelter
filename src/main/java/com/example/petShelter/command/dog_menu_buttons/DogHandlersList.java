package com.example.petShelter.command.dog_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/dogHandlersList")
public class DogHandlersList implements Command {
    private final TelegramBotClient telegramBotClient;

    @Autowired
    public DogHandlersList(TelegramBotClient telegramBotClient) {
        this.telegramBotClient = telegramBotClient;
    }

    private final static String DOG_HANDLERS = "Рекомендации по проверенным кинологам для дальнейшего обращения к ним\n" +
            "\n" +
            "Кушбакова Хилола – опыт 20 лет, +7 911999 11 11\n" +
            "\n" +
            "Синявская Мария – опыт 15 лет, +7 911888 11 11\n" +
            "\n" +
            "Ким Даниил – опыт 10 лет, +7 911777 11 11\n";

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, DOG_HANDLERS);
    }
}
