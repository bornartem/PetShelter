package com.example.petShelter.workingWithVolunteerService.conversationService;

import com.example.petShelter.model.Volunteers;
import com.example.petShelter.service.TelegramBotClient;
import com.example.petShelter.service.VolunteersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FinishedVolunteerSingUp {

    private static final Logger log = LoggerFactory.getLogger(FinishedVolunteerSingUp.class);
    VolunteersService volunteersService;
    TelegramBotClient telegramBotClient;

    public FinishedVolunteerSingUp(VolunteersService volunteersService, TelegramBotClient telegramBotClient) {
        this.volunteersService = volunteersService;
        this.telegramBotClient = telegramBotClient;
    }

    public void singUp(Long chatId, String text, Volunteers volunteers) {

        if (volunteers.getName() == null || volunteers.getContact() == null) {
            try {
                String[] strings = text.split(": ");
                volunteers.setName(strings[0]);
                volunteers.setContact(strings[1]);
            } catch (Exception e) {
                log.info("volunteer {} was create mistake in message in sing in", volunteers);
            }
            volunteersService.update(volunteers);
            String message = "Вы зарегистрированы в качестве волонтера, с данными:\n" +
                    volunteers + " Сейчас вы не в активном поиске клиентов нажмите /changeActivity" +
                    " чтобы изменить активность.\n\n" +
                    "(Если введенные данные необходимо изменить введите данные еще раз, " +
                    "в том же формате - \"имя: контактное инфо\")";
            telegramBotClient.sendMessage(chatId, message);
        } else {
            try {
                String[] strings = text.split(": ");
                volunteers.setName(strings[0]);
                volunteers.setContact(strings[1]);
            } catch (Exception e) {
                log.info("volunteer {} was create mistake in message in update him data", volunteers);
            }

            volunteersService.update(volunteers);
            String message = "Вы изменили свои данные, теперь они выглядят так:\n" +
                    volunteers + "\nСейчас вы не в активном поиске клиентов нажмите /changeActivity" +
                    " чтобы изменить активность.\n\n" +
                    "(Если введенные данные необходимо изменить введите данные еще раз, " +
                    "в том же формате - \"имя: контактное инфо\")";
            telegramBotClient.sendMessage(chatId, message);
        }
    }


}
