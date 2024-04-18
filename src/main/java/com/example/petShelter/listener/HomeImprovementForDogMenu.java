package com.example.petShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.stereotype.Component;

/**
 * Class for a home improvement menu for a certain type of dog.
 * @author  Maria Sinyavskaya
 */

@Component
public class HomeImprovementForDogMenu {
    private TelegramBot bot;

    public HomeImprovementForDogMenu(TelegramBot bot) {
        this.bot = bot;
    }

    public void sendMenuMessage(long chatId) {
        InlineKeyboardButton button1 = new InlineKeyboardButton("Щенка").callbackData("1");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Взрослой собаки")
                .callbackData("1");

        InlineKeyboardButton button3 = new InlineKeyboardButton("Собаки с ОВЗ").callbackData("1");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Вернуться назад")
                .callbackData("/getInfoAboutDogShelter");

        Keyboard keyboard1 = new InlineKeyboardMarkup(new InlineKeyboardButton[][]{{button1, button2},
                {button3, button4}});

        SendResponse response = bot.execute(new SendMessage(chatId,
                        "Обустройство дома для:").replyMarkup(keyboard1));
    }
}
