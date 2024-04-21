package com.example.petShelter.command.dog_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("/documentsForGetADog")
public class DocumentsForGetADog implements Command {
    private final TelegramBotClient telegramBotClient;
    private final String docForGetPet;

    @Autowired
    public DocumentsForGetADog(TelegramBotClient telegramBotClient,
                               @Qualifier("docForGetPet") String file) {
        this.telegramBotClient = telegramBotClient;
        this.docForGetPet = file;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, docForGetPet);
    }
}
