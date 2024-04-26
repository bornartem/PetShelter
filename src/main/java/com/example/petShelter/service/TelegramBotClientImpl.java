package com.example.petShelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The class consists of logic of the project, which has
 * the method to work with "TelegramBot"
 * @author Maria Sinyavskaya
 */
@Service
public class TelegramBotClientImpl implements TelegramBotClient {
    private final TelegramBot telegramBot;

    public TelegramBotClientImpl(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        SendResponse sendResponse = telegramBot.execute(sendMessage);


    }

}