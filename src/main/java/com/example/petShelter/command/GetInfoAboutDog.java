package com.example.petShelter.command;

import com.example.petShelter.listener.AllAboutDogMenu;
import org.springframework.stereotype.Component;

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
