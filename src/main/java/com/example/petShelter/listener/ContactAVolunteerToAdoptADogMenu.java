package com.example.petShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.stereotype.Component;

@Component
public class ContactAVolunteerToAdoptADogMenu {

    private final TelegramBot bot;

    public ContactAVolunteerToAdoptADogMenu(TelegramBot bot) {
        this.bot = bot;
    }

    public void sendMenuMessage(long chatId) {
        InlineKeyboardButton button1 = new InlineKeyboardButton("Связаться с волонтером")
                .callbackData("/volunteerHelp");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Вернуться к меню")
                .callbackData("/getInfoAboutDog");
        Keyboard keyboard = new InlineKeyboardMarkup(new InlineKeyboardButton[]{button1, button2});
        SendResponse response = bot.execute(
                new SendMessage(chatId, "Выберите действие:").replyMarkup(keyboard));
    }
}
