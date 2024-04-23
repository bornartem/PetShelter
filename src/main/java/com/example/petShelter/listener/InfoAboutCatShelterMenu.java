package com.example.petShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.stereotype.Component;

/**
 * Class for creating a menu for selecting information about a cat shelter of interest.
 * @author  Maria Sinyavskaya
 */

@Component
public class InfoAboutCatShelterMenu {
    private TelegramBot bot;

    public InfoAboutCatShelterMenu(TelegramBot bot) {
        this.bot = bot;
    }

    public void sendMenuMessage(long chatId) {
        InlineKeyboardButton button1 = new InlineKeyboardButton("О нас").callbackData("/getCatInfo");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Адрес")
                .callbackData("/getAddressOfCatShelter");

        InlineKeyboardButton button3 = new InlineKeyboardButton("Схема проезда").callbackData("/getCatShelterLocation");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Расписание работы").callbackData("/getCatShelterSchedule");

        InlineKeyboardButton button5 = new InlineKeyboardButton("Пропуск на машину").callbackData("/CarPermissionNumberCatShelter");
        InlineKeyboardButton button6 = new InlineKeyboardButton("Техника безопасности").callbackData("/ShelterSecurityRulesForCats");

        InlineKeyboardButton button7 = new InlineKeyboardButton("Регистрация пользователя").callbackData("/registerUserInCatShelter");
        InlineKeyboardButton button8 = new InlineKeyboardButton("Как усыновить кошку").callbackData("/getInfoAboutCat");

        InlineKeyboardButton button9 = new InlineKeyboardButton("Позвать волонтера").callbackData("/volunteerHelp");
        InlineKeyboardButton button10 = new InlineKeyboardButton("Вернуться к выбору приюта")
                .callbackData("/backToChoosingShelter");

        Keyboard keyboard1 = new InlineKeyboardMarkup(new InlineKeyboardButton[][]{{button1, button2},
                {button3, button4}, {button5, button6}, {button7, button8}, {button9, button10}});

        SendResponse response = bot.execute(new SendMessage(chatId, "Информация о приюте кошек:").replyMarkup(keyboard1));
    }
}
