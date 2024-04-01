package com.example.petShelter.command;

import com.example.petShelter.service.TelegramBotClient;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import org.springframework.stereotype.Component;


@Component("/start")
public class StartCommand implements Command{
    private final static String START_MESSAGE = "Привет! Это приют \"Пушистый друг\". " +
            "Мы помогаем бездомным животным найти новый дом";

    private TelegramBotClient telegramBotClient;

    public StartCommand(TelegramBotClient telegramBotClient) {
        this.telegramBotClient = telegramBotClient;
    }

    @Override
    public void execute(Message message, CallbackQuery callbackQuery) {
        telegramBotClient.sendMessage(message.chat().id(), START_MESSAGE);
    }
}