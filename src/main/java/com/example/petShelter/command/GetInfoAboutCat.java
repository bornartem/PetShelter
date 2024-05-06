package com.example.petShelter.command;

import com.example.petShelter.listener.AllAboutCatMenu;
import org.springframework.stereotype.Component;

/**
 * Class that opens a menu containing information on how to adopt a cat
 * @author  Maria Sinyavskaya
 */

@Component("/getInfoAboutCat")
public class GetInfoAboutCat implements Command {

    private final AllAboutCatMenu allAboutCatMenu;

    public GetInfoAboutCat(AllAboutCatMenu allAboutCatMenu) {
        this.allAboutCatMenu = allAboutCatMenu;
    }

    @Override
    public void execute(Long chatId) {
        allAboutCatMenu.sendMenuMessage(chatId);
    }
}
