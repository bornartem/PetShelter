package com.example.petShelter.command;

import com.example.petShelter.listener.InfoAboutCatShelterMenu;
import com.example.petShelter.listener.InfoAboutDogShelterMenu;
import org.springframework.stereotype.Component;

@Component("/getInfoAboutCatShelter")
public class GetInfoAboutCatShelter implements Command {
    private final InfoAboutCatShelterMenu infoAboutCatShelterMenu;

    public GetInfoAboutCatShelter(InfoAboutCatShelterMenu infoAboutCatShelterMenu) {
        this.infoAboutCatShelterMenu = infoAboutCatShelterMenu;
    }

    @Override
    public void execute(Long chatId) {
        infoAboutCatShelterMenu.sendMenuMessage(chatId);
    }
}
