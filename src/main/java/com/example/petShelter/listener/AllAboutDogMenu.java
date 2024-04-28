package com.example.petShelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.stereotype.Component;

/**
 * Class for creating a menu on how to adopt a dog.
 * @author  Maria Sinyavskaya
 */

@Component
public class AllAboutDogMenu {
    private TelegramBot bot;

    public AllAboutDogMenu(TelegramBot bot) {
        this.bot = bot;
    }

    public void sendMenuMessage(long chatId) {
        InlineKeyboardButton button0 = new InlineKeyboardButton("Выбрать собаку").callbackData("/chooseADog");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Знакомство с собакой").callbackData("/getInfoAboutDogMeeting");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Документы для усыновления")
                .callbackData("/documentsForGetADog");

        InlineKeyboardButton button3 = new InlineKeyboardButton("Транспортировка собаки").callbackData("/transportingADog");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Обустройство дома")
                .callbackData("/getHomeImprovementForDog");

        InlineKeyboardButton button5 = new InlineKeyboardButton("Советы кинолога по общению").callbackData("/dogHandlerAdvices");
        InlineKeyboardButton button6 = new InlineKeyboardButton("Проверенные кинологи").callbackData("/dogHandlersList");

        InlineKeyboardButton button7 = new InlineKeyboardButton("Отказ в усыновлении").callbackData("/rejectToGetADog");
        InlineKeyboardButton button8 = new InlineKeyboardButton("Позвать волонтера").callbackData("1");

        InlineKeyboardButton button9 = new InlineKeyboardButton("Информация о приюте")
                .callbackData("/getInfoAboutDogShelter");
        InlineKeyboardButton button10 = new InlineKeyboardButton("Вернуться к выбору приюта")
                .callbackData("/backToChoosingShelter");

        Keyboard keyboard1 = new InlineKeyboardMarkup(new InlineKeyboardButton[][]{{button0},{button1, button2},
                {button3, button4}, {button5, button6}, {button7, button8}, {button9, button10}});

        SendResponse response = bot.execute(
                new SendMessage(chatId,
                        "Как усыновить собаку и создать благоприятные условия для ее проживания:")
                .replyMarkup(keyboard1));
    }
}
