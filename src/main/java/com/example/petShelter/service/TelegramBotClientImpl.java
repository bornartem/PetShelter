package com.example.petShelter.service;

import com.example.petShelter.listener.TelegramBotUpdatesListener;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TelegramBotClientImpl implements TelegramBotClient {

    private Logger logger = LoggerFactory.getLogger(TelegramBotClientImpl.class);

    private final TelegramBot telegramBot;

    public TelegramBotClientImpl(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        SendResponse sendResponse = telegramBot.execute(sendMessage);

        if (!sendResponse.isOk()) {
            logger.error("Error sending message {}", sendResponse.description());
        }
    }
}
