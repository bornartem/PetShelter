package com.example.petShelter.command.home_improvement_dog_buttons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/homeImprovementForPuppy")
public class HomeImprovementForPuppy implements Command {
    private final TelegramBotClient telegramBotClient;

    @Autowired
    public HomeImprovementForPuppy(TelegramBotClient telegramBotClient) {
        this.telegramBotClient = telegramBotClient;
    }

    private final static String PUPPY = "Прежде чем забрать домой щенка, позаботьтесь о том," +
            " чтобы обстановка в доме была безопасной для вашего нового питомца. Защита вещей от щенков и котят" +
            " не только спасет вашего нового питомца от скрытых опасностей, но и убережет ваше имущество от разрушения. " +
            "Речь идет о коврах, шторах, мебели, бытовой электронике и других элементах вашего дома. " +
            "Висящие зарядные устройства, кабели, колонки или электрические провода интересуют и щенков, и котят. " +
            "Уберите их подальше или оберните шнуры в защитный чехол, чтобы снизить риск поражения электрическим током, ожогов или пожара.";

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, PUPPY);
    }
}
