package com.example.petShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.pengrad.telegrambot.model.Update;
import com.example.petShelter.repository.NotificationTaskRepository;
import com.example.petShelter.service.NotificationTaskService;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {

//    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
//    private final Pattern pattern = Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4} (?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d) ([а-яА-Яа-яА-Я\\\\s]+|\\\\w+)");

    private final TelegramBot telegramBot;
//    private final NotificationTaskService notificationTaskService;
//    private final NotificationTaskRepository notificationTaskRepository;

    private final CommandProcessor commandProcessor;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, NotificationTaskService notificationTaskService,
                                      NotificationTaskRepository notificationTaskRepository,
                                      CommandProcessor commandProcessor) {
        this.telegramBot = telegramBot;
//        this.notificationTaskService = notificationTaskService;
//        this.notificationTaskRepository = notificationTaskRepository;
        this.commandProcessor = commandProcessor;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        for (Update update : updates) {
            if (update.callbackQuery() != null) {
                CallbackQuery callbackQuery = update.callbackQuery();
                commandProcessor.process(callbackQuery.data(), null, callbackQuery);
            } else {
                Message message = update.message();
                if (message != null && message.text() != null) {
                    commandProcessor.process(message.text(), message, null);
                }
            }
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}