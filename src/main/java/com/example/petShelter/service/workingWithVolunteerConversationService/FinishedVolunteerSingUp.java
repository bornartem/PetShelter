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
        if (volunteers.getName() == null || volunteers.getContact() == null) {
            String[] strings = text.split(", ");
            volunteers.setName(strings[0]);
            volunteers.setContact(strings[1]);
            volunteersService.update(volunteers);
            String message = "Вы зарегистрированы в качестве волонтера, с данными:\n"+
                    volunteers + " Сейчас вы не в активном поиске клиентов нажмите /changeActivity" +
                    " чтобы изменить активность.";
            telegramBotClient.sendMessage(chatId, message);
        }


    }
}
