package com.example.petShelter.listener;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.stereotype.Component;

@Component
public class InfoAboutDogShelterMenu {
    private TelegramBot bot;

    public InfoAboutDogShelterMenu(TelegramBot bot) {
        this.bot = bot;
    }

    public void sendMenuMessage(long chatId) {
        InlineKeyboardButton button1 = new InlineKeyboardButton("О нас").callbackData("1");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Адрес")
                .callbackData("/getAddressOfDogShelter");

        InlineKeyboardButton button3 = new InlineKeyboardButton("Схема проезда").callbackData("1");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Расписание работы").callbackData("1");

        InlineKeyboardButton button5 = new InlineKeyboardButton("Пропуск на машину").callbackData("1");
        InlineKeyboardButton button6 = new InlineKeyboardButton("Техника безопасности").callbackData("1");

        InlineKeyboardButton button7 = new InlineKeyboardButton("Регистрация пользователя").callbackData("1");
        InlineKeyboardButton button8 = new InlineKeyboardButton("Как усыновить собаку")
                .callbackData("/getInfoAboutDog");

        InlineKeyboardButton button9 = new InlineKeyboardButton("Позвать волонтера").callbackData("1");
        InlineKeyboardButton button10 = new InlineKeyboardButton("Вернуться к выбору приюта")
                .callbackData("/backToChoosingShelter");

        Keyboard keyboard1 = new InlineKeyboardMarkup(new InlineKeyboardButton[][]{{button1, button2},
                {button3, button4}, {button5, button6}, {button7, button8}, {button9, button10}});

        SendResponse response = bot.execute(new SendMessage(chatId, "Информация о приюте собак:").replyMarkup(keyboard1));
    }
}
