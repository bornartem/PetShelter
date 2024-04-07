package com.example.petShelter.command;

import com.example.petShelter.listener.InfoAboutDogShelterMenu;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import org.springframework.stereotype.Component;

@Component("/getInfoAboutDogShelter")
public class GetInfoAboutDogShelter implements Command {
    private final InfoAboutDogShelterMenu infoAboutDogShelterMenu;

    public GetInfoAboutDogShelter(InfoAboutDogShelterMenu infoAboutDogShelterMenu) {
        this.infoAboutDogShelterMenu = infoAboutDogShelterMenu;
    }

    @Override
    public void execute(Long chatId) {
        infoAboutDogShelterMenu.sendMenuMessage(chatId);
    }
}
