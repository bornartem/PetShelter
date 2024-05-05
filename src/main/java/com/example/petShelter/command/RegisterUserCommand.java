package com.example.petShelter.command;


import com.example.petShelter.model.Clients;
import com.example.petShelter.service.ClientsService;
import com.example.petShelter.service.TelegramBotClient;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Class which allows to register users  in Telegram .
 *
 * @author Khilola Kushbakova
 */

@Component("/registerUser")
public class RegisterUserCommand implements Command {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserCommand.class);

    private final ClientsService clientsService;
    private final TelegramBotClient telegramBotClient;


    public RegisterUserCommand(ClientsService clientsService, TelegramBotClient telegramBotClient) {
        this.clientsService = clientsService;
        this.telegramBotClient = telegramBotClient;

    }


    @Override
    public void execute(Long chatId, List<Update> updatesList) {
        try {
            if (clientsService.findFirstByChatId(chatId) != null) {
                return;
            }

            Clients client = new Clients();
            client.setChatId(chatId);
            clientsService.create(client);
            telegramBotClient.sendMessage(chatId, "Пожалуйста, пришлите ваши данные для регистрации в " +
                    "следующем порядке: имя, фамилия, номер телефона, почта. Элементы регистрации строго" +
                    "разделять запятой и пробелом(, )");


//
        } catch (Exception e) {
            log.warn("exception in user registry");
        }
    }



    private boolean isValidContact(String contact) {
        String phoneRegex = "^\\+7-9\\d{2}-\\d{3}-\\d{2}-\\d{2}$";
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return contact.matches(phoneRegex) || contact.matches(emailRegex);
    }


    public void continueReg(Clients clients, Long chatId, String userText) {
        String[] userDetails = userText.split(", ");
        if (userDetails.length < 4) {
            telegramBotClient.sendMessage(chatId, "Please enter all required data.");
            return;
        }

        String name = userDetails[0];
        String surname = userDetails[1];
        String phoneNumber = userDetails[2];
        String email = userDetails[3];

        if (name.isEmpty() || surname.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() /*|| !isValidContact(phoneNumber) || !isValidContact(email)*/) {
            telegramBotClient.sendMessage(chatId, "Please enter valid data.");
            return;
        }

        clients.setName(name + " " + surname);
        clients.setContact(phoneNumber + " " + email);

        clientsService.update(clients);

        telegramBotClient.sendMessage(chatId, "User, " + clients.getName() + ", Регистрация успешна !");

    }
}
