package com.example.petShelter.command.cat_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/getInfoAboutCatMeeting")
public class GetInfoAboutCatMeeting implements Command {
    private final TelegramBotClient telegramBotClient;

    @Autowired
    public GetInfoAboutCatMeeting(TelegramBotClient telegramBotClient) {
        this.telegramBotClient = telegramBotClient;
    }

    private final static String INFO_ABOUT_MEETING = "Первое, что необходимо – это выяснить," +
            " все ли ваши домочадцы готовы принять в семью нового члена. Помните, что животное не игрушка," +
            " или сувенир в подарок. Это новый член семьи, который живет с вами долгую, счастливую жизнь.";

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, INFO_ABOUT_MEETING);
    }
}
