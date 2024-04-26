package com.example.petShelter.command;

import com.example.petShelter.model.Volunteers;
import com.example.petShelter.service.TelegramBotClient;
import com.example.petShelter.service.VolunteersService;
import org.springframework.stereotype.Component;

/**
 * The class contains method for sing in new volunteer
 * @author Danil
 */
@Component("/volunteersLoginIn")
public class RegistrationNewVolunteer implements Command{
    private final static String START_MESSAGE = "Привет! Это регистрация волонтера в приюте \"Пушистый друг\". " +
            "\nВведите через двоеточие и пробел(: ) свои данные, а конкретнее:\n" +
            "ФИО и контактную информацию\n" +
            "Пример:\n" +
            "Иванов Иван Иванович: +7 123 456 78 90\n" +
            "или\n" +
            "Степанов Петр: postadres123@mail.ru\n" +
            "или\n" +
            "Валера: 91234567890, email345@gmail.com";

    private final TelegramBotClient telegramBotClient;
    private final VolunteersService volunteersService;


    public RegistrationNewVolunteer(TelegramBotClient telegramBotClient, VolunteersService volunteersService) {
        this.telegramBotClient = telegramBotClient;
        this.volunteersService = volunteersService;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, START_MESSAGE);
        Volunteers volunteers = new Volunteers();
        volunteers.setId(volunteersService.getMaxIdByVolunteers()+1);
        volunteers.setChatId(chatId);
        volunteers.setActivity(false);
        volunteersService.create(volunteers);
    }

}