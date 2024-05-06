package com.example.petShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.stereotype.Component;

/**
 * Class for creating a shelter selection menu.
 * @author  Maria Sinyavskaya
 */

@Component
public class ChoosingShelterMenu {

    private TelegramBot bot;

    public ChoosingShelterMenu(TelegramBot bot) {
        this.bot = bot;
    }

    public void sendMenuMessage(long chatId) {
        InlineKeyboardButton button1 = new InlineKeyboardButton("Приют для кошек")
                .callbackData("/getInfoAboutCatShelter");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Приют для собак")
                .callbackData("/getInfoAboutDogShelter");
        Keyboard keyboard = new InlineKeyboardMarkup(new InlineKeyboardButton[]{button1, button2});
        SendResponse response = bot.execute(
                new SendMessage(chatId, "Выберите интересующий Вас приют:").replyMarkup(keyboard));
    }
}
