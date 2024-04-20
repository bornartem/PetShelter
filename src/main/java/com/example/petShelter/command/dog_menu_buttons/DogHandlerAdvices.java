package com.example.petShelter.command.dog_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/dogHandlerAdvices")
public class DogHandlerAdvices implements Command {
    private final TelegramBotClient telegramBotClient;

    @Autowired
    public DogHandlerAdvices(TelegramBotClient telegramBotClient) {
        this.telegramBotClient = telegramBotClient;
    }

    private final static String DOG_HANDLER_ADVICES = "Когда же следует приступать к занятиям? \n" +
            "   К воспитанию собаки нужно приступать в ПЕРВЫЙ день появления ее в доме. " +
            "Мне бы не хотелось ограничивать и усреднять всех щенков по возрасту, т.к. собака может появиться у нас и в полтора месяца, " +
            "и в 11 месяцев. И все это подходит под понятие – щенок. Возраст щенка бывает разный. " +
            "Главное — надо начинать с первых минут приобретения четвероногого питомца.\n";

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, DOG_HANDLER_ADVICES);
    }
}
