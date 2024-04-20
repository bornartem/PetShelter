package com.example.petShelter.command.cat_menu_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/rejectToGetACat")
public class RejectToGetACat implements Command {
    private final TelegramBotClient telegramBotClient;

    @Autowired
    public RejectToGetACat(TelegramBotClient telegramBotClient) {
        this.telegramBotClient = telegramBotClient;
    }

    private final static String REJECT = "Обстоятельства при которых нельзя забрать животное из приюта:" +
            " Жилье должно находится в собственности. " +
            "Мы не отдаем животных на съемные квартиры. Если у вас на руках договор о долгосрочной аренде жилья, " +
            "к нему обязательно нужно письменное согласие собственника на проживание в квартире животного.";

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, REJECT);
    }
}
