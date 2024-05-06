package com.example.petShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.stereotype.Component;

/**
 * Class for a home improvement menu for a certain type of cat.
 * @author  Maria Sinyavskaya
 */

@Component
public class HomeImprovementForCatMenu {
    private TelegramBot bot;

    public HomeImprovementForCatMenu(TelegramBot bot) {
        this.bot = bot;
    }

    public void sendMenuMessage(long chatId) {
        InlineKeyboardButton button1 = new InlineKeyboardButton("Котенка").callbackData("/homeImprovementForKitty");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Взрослой кошки")
                .callbackData("/homeImprovementForAdultCat");

        InlineKeyboardButton button3 = new InlineKeyboardButton("Кошки с ОВЗ").callbackData("/homeImprovementForCatWithDisabilities");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Вернуться назад")
                .callbackData("/getInfoAboutCatShelter");

        Keyboard keyboard1 = new InlineKeyboardMarkup(new InlineKeyboardButton[][]{{button1, button2},
                {button3, button4}});

        SendResponse response = bot.execute(new SendMessage(chatId,
                        "Обустройство дома для:").replyMarkup(keyboard1));
    }
}
