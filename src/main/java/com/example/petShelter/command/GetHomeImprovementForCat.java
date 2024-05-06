package com.example.petShelter.command;

import com.example.petShelter.listener.HomeImprovementForCatMenu;
import org.springframework.stereotype.Component;

/**
 * Class that opens a menu on home improvement for a cat.
 * @author  Maria Sinyavskaya
 */

@Component("/getHomeImprovementForCat")
public class GetHomeImprovementForCat implements Command{

    private final HomeImprovementForCatMenu homeImprovementForCatMenu;

    public GetHomeImprovementForCat(HomeImprovementForCatMenu homeImprovementForCatMenu) {
        this.homeImprovementForCatMenu = homeImprovementForCatMenu;
    }

    @Override
    public void execute(Long chatId) {
        homeImprovementForCatMenu.sendMenuMessage(chatId);
    }
}
