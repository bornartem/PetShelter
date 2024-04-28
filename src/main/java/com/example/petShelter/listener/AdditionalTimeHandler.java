package com.example.petShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.EditMessageText;

/**
 * @author Khilola Kushbakova
 * This class represents a handler for adding extra time to users.
 */
public class AdditionalTimeHandler {

    private TelegramBot bot;

    public AdditionalTimeHandler(TelegramBot bot) {
        this.bot = bot;
    }

    public void addAdditionalTimeToUsers(Update update) {
        String[] dataSplit = update.callbackQuery().data().split(":");
        Long chatId = update.callbackQuery().message().chat().id();
        String idUser = dataSplit[1];
        String messageText = "Выберите, сколько дней нужно добавить";

        InlineKeyboardButton button1 = new InlineKeyboardButton("1. 14 дней")
                .callbackData("days_14:" + idUser);
        InlineKeyboardButton button2 = new InlineKeyboardButton("2. 30 дней")
                .callbackData("days_30:" + idUser);

        Keyboard keyboard = new InlineKeyboardMarkup(new InlineKeyboardButton[]{button1, button2});

        bot.execute(new EditMessageText(chatId, update.callbackQuery().message().messageId(), messageText)
                .replyMarkup((InlineKeyboardMarkup) keyboard));
    }
}
