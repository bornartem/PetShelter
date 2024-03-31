package com.example.petShelter.command;

import com.example.petShelter.service.TelegramBotClient;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;

import static com.example.petShelter.command.CommandName.*;

@Component("/no")
public class NoCommand implements Command {

    private final static String NO_COMMAND_MESSAGE = "Извините, я не понимаю Ваш запрос";

    private TelegramBotClient telegramBotClient;

    public NoCommand(TelegramBotClient telegramBotClient) {
        this.telegramBotClient = telegramBotClient;
    }
    @Override
    public void execute(Message message, CallbackQuery callbackQuery) {
        telegramBotClient.sendMessage(message.chat().id(), NO_COMMAND_MESSAGE);
    }
}
