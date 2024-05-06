package com.example.petShelter.command;

import com.example.petShelter.listener.HomeImprovementForDogMenu;
import org.springframework.stereotype.Component;

/**
 * Class that opens a menu on home improvement for a dog.
 * @author  Maria Sinyavskaya
 */

@Component("/getHomeImprovementForDog")
public class GetHomeImprovementForDog implements Command {

    private final HomeImprovementForDogMenu homeImprovementForDogMenu;

    public GetHomeImprovementForDog(HomeImprovementForDogMenu homeImprovementForDogMenu) {
        this.homeImprovementForDogMenu = homeImprovementForDogMenu;
    }

    @Override
    public void execute(Long chatId) {
        homeImprovementForDogMenu.sendMenuMessage(chatId);
    }
}
