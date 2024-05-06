package com.example.petShelter.command;

import com.example.petShelter.listener.ChoosingShelterMenu;
import org.springframework.stereotype.Component;

/**
 * Class to return to the shelter selection menu.
 * @author  Maria Sinyavskaya
 */

@Component("/backToChoosingShelter")
public class BackToChoosingShelter implements Command{

    private final ChoosingShelterMenu choosingShelterMenu;

    public BackToChoosingShelter(ChoosingShelterMenu choosingShelterMenu) {
        this.choosingShelterMenu = choosingShelterMenu;
    }

    @Override
    public void execute(Long chatId) {
        choosingShelterMenu.sendMenuMessage(chatId);
    }
}
