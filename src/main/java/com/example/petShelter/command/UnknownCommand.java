package com.example.petShelter.command;

import com.example.petShelter.service.TelegramBotClient;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import org.springframework.stereotype.Component;

@Component
public class UnknownCommand implements Command{
    public static final String UNKNOWN_MESSAGE = "Не понимаю вас, напишите /help чтобы узнать что я понимаю.";

    private final TelegramBotClient telegramBotClient;

    public UnknownCommand(TelegramBotClient telegramBotClient) {
        this.telegramBotClient = telegramBotClient;
    }

    @Override
    public void execute(Message message, CallbackQuery callbackQuery) {
        telegramBotClient.sendMessage(message.chat().id(), UNKNOWN_MESSAGE);
    }
}
