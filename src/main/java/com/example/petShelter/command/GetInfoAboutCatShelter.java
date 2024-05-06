package com.example.petShelter.command;

import com.example.petShelter.listener.InfoAboutCatShelterMenu;
import org.springframework.stereotype.Component;

/**
 * Class that opens the menu for the cat shelter.
 * @author  Maria Sinyavskaya
 */

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
