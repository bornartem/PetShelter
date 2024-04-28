package com.example.petShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.stereotype.Component;

/**
 * Class for creating a menu on how to adopt a cat.
 * @author  Maria Sinyavskaya
 */

@Component
public class AllAboutCatMenu {
    private final TelegramBot bot;

    public AllAboutCatMenu(TelegramBot bot) {
        this.bot = bot;
    }

    public void sendMenuMessage(long chatId) {
        InlineKeyboardButton button0 = new InlineKeyboardButton("Выбрать кошку").callbackData("/chooseACat");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Знакомство с кошкой").callbackData("/getInfoAboutCatMeeting");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Документы для усыновления")
                .callbackData("/documentsForGetACat");

        InlineKeyboardButton button3 = new InlineKeyboardButton("Транспортировка").callbackData("/transportingACat");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Обустройство дома")
                .callbackData("/getHomeImprovementForCat");

        InlineKeyboardButton button5 = new InlineKeyboardButton("Отказ в усыновлении").callbackData("/rejectToGetACat");
        InlineKeyboardButton button6 = new InlineKeyboardButton("Позвать волонтера").callbackData("/volunteerHelp");

        InlineKeyboardButton button7 = new InlineKeyboardButton("Информация о приюте")
                .callbackData("/getInfoAboutCatShelter");
        InlineKeyboardButton button8 = new InlineKeyboardButton("Вернуться к выбору приюта")
                .callbackData("/backToChoosingShelter");

        Keyboard keyboard1 = new InlineKeyboardMarkup(new InlineKeyboardButton[][]{{button0},{button1, button2},
                {button3, button4}, {button5, button6}, {button7, button8}});

        SendResponse response = bot.execute(
                new SendMessage(chatId,
                        "Как усыновить кошку и создать благоприятные условия для ее проживания:")
                .replyMarkup(keyboard1));
    }
}
