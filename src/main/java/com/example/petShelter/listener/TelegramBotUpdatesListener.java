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

import java.util.Arrays;
import java.util.List;

/**
 * The class consists of logic of the project, which has
 * the methods to send messages with "TelegramBot"
 *
 * @author Maria Sinyavskaya
 */
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Long clientId;
    private Long chatId;
    private Integer messageId;

    public static String COMMAND_PREFIX = "/";

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;
    private final CommandContainer commandContainer;

    private final TelegramBotClient telegramBotClient;

    private final ChoosingShelterMenu choosingShelterMenu;

    public TelegramBotUpdatesListener(TelegramBot telegramBot,
                                      CommandContainer commandContainer,
                                      TelegramBotClient telegramBotClient,
                                      ChoosingShelterMenu choosingShelterMenu) {
        this.telegramBot = telegramBot;
        this.commandContainer = commandContainer;
        this.telegramBotClient = telegramBotClient;
        this.choosingShelterMenu = choosingShelterMenu;
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

            if (message != null) {
                String userText = message.text();
//                if (update.message().text().equals("/menu")) {
//                    choosingShelterMenu.sendMenuMessage(update.message().chat().id());
//                }
                if (userText.startsWith(COMMAND_PREFIX)) {
                    Long chatId = update.callbackQuery() != null ?
                            update.callbackQuery().message().chat().id() : message.chat().id();
                    commandContainer.process(userText, chatId, Arrays.asList(update));
                } else {
                    telegramBotClient.sendMessage(message.chat().id(), "Не понимаю вас, напишите /help чтобы узнать что я понимаю.");
                }
            } else {
                if (update.callbackQuery() != null) {
                    String userText = update.callbackQuery().data();
                    commandContainer.process(userText, update.callbackQuery().message().chat().id(), Arrays.asList(update));
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}