package com.example.petShelter.command;

import com.example.petShelter.listener.AllAboutDogMenu;
import org.springframework.stereotype.Component;

/**
 * Class that opens a menu containing information on how to adopt a dog
 * @author  Maria Sinyavskaya
 */

@Component("/getInfoAboutDog")
public class GetInfoAboutDog implements Command {

    private final AllAboutDogMenu allAboutDogMenu;

    public GetInfoAboutDog(AllAboutDogMenu allAboutDogMenu) {
        this.allAboutDogMenu = allAboutDogMenu;
    }

    @Override
    public void execute(Long chatId) {
        allAboutDogMenu.sendMenuMessage(chatId);
    }
}
