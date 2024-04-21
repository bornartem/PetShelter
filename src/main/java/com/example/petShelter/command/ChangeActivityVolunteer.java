package com.example.petShelter.command;

import com.example.petShelter.listener.ChoosingShelterMenu;
import com.example.petShelter.model.Volunteers;
import com.example.petShelter.service.TelegramBotClient;
import com.example.petShelter.service.VolunteersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * change volunteer activity(find-able clients|not find-able)
 *
 * @author Danil
 */
@Component("/changeActivity")
public class ChangeActivityVolunteer implements Command {
    private final static String FIRST = "Теперь вы НЕ активны(вышли из поиска клиентов).";
    private final static String SECOND = "Теперь вы в АКТИВНОМ поиске клиентов.";

    private final TelegramBotClient telegramBotClient;
    private final VolunteersService volunteersService;

    @Autowired
    public ChangeActivityVolunteer(TelegramBotClient telegramBotClient, VolunteersService volunteersService) {
        this.telegramBotClient = telegramBotClient;
        this.volunteersService = volunteersService;
    }

    @Override
    public void execute(Long chatId) {
        Volunteers volunteers = volunteersService.findFirstByChatId(chatId);
        if (volunteers.isActivity()) {
            volunteers.setActivity(false);
            volunteersService.update(volunteers);
            telegramBotClient.sendMessage(chatId, FIRST);
        } else {
            volunteers.setActivity(true);
            volunteersService.update(volunteers);
            telegramBotClient.sendMessage(chatId, SECOND);
        }
    }

}