package com.example.petShelter.command;

import com.example.petShelter.listener.ChoosingShelterMenu;
import com.example.petShelter.model.Volunteers;
import com.example.petShelter.service.TelegramBotClient;
import com.example.petShelter.service.VolunteersService;
import org.springframework.stereotype.Component;

/**
 * The class consists of logic of the project, which has
 the methods to send messages with "TelegramBot"
 * @author Maria Sinyavskaya
 */
@Component("/volunteersLoginIn")
public class RegistrationNewVolunteer implements Command{
    private final static String START_MESSAGE = "Привет! Это регистрация волонтера в приюте \"Пушистый друг\". " +
            "\nВведите через запятую и пробел(, ) свои данные, а конкретнее:\n" +
            "контактную информацию, и ФИО";

    private final TelegramBotClient telegramBotClient;
    private final VolunteersService volunteersService;

//    private final ChoosingShelterMenu choosingShelterMenu;

    public RegistrationNewVolunteer(TelegramBotClient telegramBotClient, VolunteersService volunteersService) {
        this.telegramBotClient = telegramBotClient;
        this.volunteersService = volunteersService;
//        this.choosingShelterMenu = choosingShelterMenu;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, START_MESSAGE);
        Volunteers volunteers = new Volunteers();
        volunteers.setChatId(chatId);
        volunteers.setActivity(false);
        volunteersService.create(volunteers);

//        choosingShelterMenu.sendMenuMessage(chatId);
    }

}