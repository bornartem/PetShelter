package com.example.petShelter.command.ShelterDogInnerButtons;

import com.example.petShelter.command.Command;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;


/**
 * Class which shows the information about dog shelter after clicking the button in Telegram to info.
 * @author  Khilola Kushbakova
 */



@Component("/getDogShelterInfo")
public class DogShelterInfoCommand implements Command {
    private final SheltersService sheltersService;
    private final static long DOG_SHELTER_ID = 1;
    private final static String DOG_INFO = "это муниципальный приют для бездомных собак. В нем живет " +
            "почти 2500 собак  Большие и маленькие, пушистые и гладкие, веселые и задумчивые -" +
            " и на всех одна большая мечта - встретить своего Человека и найти Дом.";
    private TelegramBotClient telegramBotClient;

    public DogShelterInfoCommand(SheltersService sheltersService, TelegramBotClient telegramBotClient) {
        this.sheltersService = sheltersService;
        this.telegramBotClient = telegramBotClient;
    }
    @Override
    public void execute(Long chatId) {
        String dogShelterInfo = sheltersService.showAnimalInfoById(DOG_SHELTER_ID, DOG_INFO);
        telegramBotClient.sendMessage(chatId, dogShelterInfo);
    }
}
