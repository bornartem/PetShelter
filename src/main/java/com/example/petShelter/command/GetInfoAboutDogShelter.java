package com.example.petShelter.command;

import com.example.petShelter.listener.InfoAboutDogShelterMenu;
import org.springframework.stereotype.Component;

/**
 * Class that opens the menu for the dog shelter.
 * @author  Maria Sinyavskaya
 */

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
