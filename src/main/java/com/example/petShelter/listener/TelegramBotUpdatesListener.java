package com.example.petShelter.listener;

import com.example.petShelter.command.CommandContainer;
import com.example.petShelter.service.TelegramBotClient;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    public static String COMMAND_PREFIX = "/";

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;
    private final CommandContainer commandContainer;

    private final TelegramBotClient telegramBotClient;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, CommandContainer commandContainer, TelegramBotClient telegramBotClient) {
        this.telegramBot = telegramBot;
        this.commandContainer = commandContainer;
        this.telegramBotClient = telegramBotClient;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            Message message = update.message();
            String userText = message.text();

            if (userText.startsWith(COMMAND_PREFIX)) {
                commandContainer.process(userText, message, update.callbackQuery());
            } else {
                telegramBotClient.sendMessage(message.chat().id(), "Не понимаю вас, напишите /help чтобы узнать что я понимаю.");
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
