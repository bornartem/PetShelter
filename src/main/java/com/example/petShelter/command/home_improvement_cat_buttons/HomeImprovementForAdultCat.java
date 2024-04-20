package com.example.petShelter.command.home_improvement_cat_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/homeImprovementForAdultCat")
public class HomeImprovementForAdultCat implements Command {
    private final TelegramBotClient telegramBotClient;

    @Autowired
    public HomeImprovementForAdultCat(TelegramBotClient telegramBotClient) {
        this.telegramBotClient = telegramBotClient;
    }

    private final static String ADULT = "Наши требования и что необходимо сделать в этот период вам. \n" +
            "Установить сетки на окнах. Безопасность наших ребятишек – это основное требование к будущим хозяевам, и обязательное условие передачи животного.\n" +
            "Подготовить место для сна, еды, игр животного.\n";

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, ADULT);
    }
}
