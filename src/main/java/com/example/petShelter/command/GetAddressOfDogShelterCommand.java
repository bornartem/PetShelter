package com.example.petShelter.command;

import com.example.petShelter.listener.BotMenu;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import org.springframework.stereotype.Component;

@Component("/getAddressOfDogShelter")
public class GetAddressOfDogShelterCommand implements Command{

    private final TelegramBotClient telegramBotClient;

    private final SheltersService sheltersService;

    private final BotMenu botMenu;

    private final static long DOG_SHELTER_ID = 1;

    public GetAddressOfDogShelterCommand(TelegramBotClient telegramBotClient, SheltersService sheltersService, BotMenu botMenu) {
        this.telegramBotClient = telegramBotClient;
        this.sheltersService = sheltersService;
        this.botMenu = botMenu;
    }

    @Override
    public void execute(Message message, CallbackQuery callbackQuery) {
        String address = sheltersService.showAddress(DOG_SHELTER_ID);
        //telegramBotClient.sendMessage(callbackQuery.message().chat().id(), address);
        botMenu.sendMenuMessage(callbackQuery.message().chat().id());
    }
}
