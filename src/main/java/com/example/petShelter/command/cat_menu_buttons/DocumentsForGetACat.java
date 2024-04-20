package com.example.petShelter.command.cat_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/documentsForGetACat")
public class DocumentsForGetACat implements Command {
    private final TelegramBotClient telegramBotClient;

    @Autowired
    public DocumentsForGetACat(TelegramBotClient telegramBotClient) {
        this.telegramBotClient = telegramBotClient;
    }

    private final static String DOCUMENTS = " Как происходит передача животного:\n" +
            "В назначенный день мы встречаемся и заполняем двусторонний договор о передаче.\n" +
            "При себе необходимо иметь паспорт и переноску (лучше пластиковую с надежными защелками). \n";

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, DOCUMENTS);
    }
}
