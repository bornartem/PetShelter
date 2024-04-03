package com.example.petShelter.command;

import com.example.petShelter.service.TelegramBotClient;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;


@Service
public class CommandContainer {

    private final ConcurrentHashMap<String, Command> commandMap = new ConcurrentHashMap<>();

    private TelegramBotClient telegramBotClient;

    public CommandContainer(StartCommand commandStart) {
        commandMap.put(CommandName.START.getCommandName(), commandStart);

    }

    public void process(String commandName, Message message, CallbackQuery callbackQuery) {
        if (commandMap.containsKey(commandName)) {
            commandMap.get(commandName).execute(message, callbackQuery);
        }
    }

}