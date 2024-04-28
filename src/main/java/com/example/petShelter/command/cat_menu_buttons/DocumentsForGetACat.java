package com.example.petShelter.command.cat_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/documentsForGetACat")
public class DocumentsForGetACat implements Command {
    private final TelegramBotClient telegramBotClient;
    private final String docForGetPet;

    @Autowired
    public DocumentsForGetACat(TelegramBotClient telegramBotClient, String docForGetPet) {
        this.telegramBotClient = telegramBotClient;
        this.docForGetPet = docForGetPet;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, docForGetPet);
    }
}
