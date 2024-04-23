package com.example.petShelter.service.workingWithVolunteerConversationService;

import com.example.petShelter.model.Volunteers;
import com.example.petShelter.service.TelegramBotClient;
import com.example.petShelter.service.VolunteersService;
import org.springframework.stereotype.Service;

@Service
public class FinishedVolunteerSingUp {

    VolunteersService volunteersService;
    TelegramBotClient telegramBotClient;

    public FinishedVolunteerSingUp(VolunteersService volunteersService, TelegramBotClient telegramBotClient) {
        this.volunteersService = volunteersService;
        this.telegramBotClient = telegramBotClient;
    }

    public void singUp(Long chatId, String text, Volunteers volunteers) {
        if (volunteers.getName().isEmpty() || volunteers.getContact().isBlank()) {
            String[] strings = text.split(", ");
            volunteers.setName(strings[0]);
            volunteers.setContact(strings[1]);
            volunteersService.update(volunteers);
            String message = "Вы зарегестрированны в качестве волонтера, с данными:\n"+
                    volunteers;
            telegramBotClient.sendMessage(chatId, message);
        }


    }
}
