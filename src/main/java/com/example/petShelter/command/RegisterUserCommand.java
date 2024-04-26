package com.example.petShelter.command;


import com.example.petShelter.model.Clients;
import com.example.petShelter.service.ClientsService;
import com.example.petShelter.service.TelegramBotClient;
import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Class which allows to register users  in Telegram .
 *
 * @author Khilola Kushbakova
 */

@Component("/registerUser")
public class RegisterUserCommand implements Command {
    private Long clientId;
    private Long chatId;
    private Integer messageId;

    private final ClientsService clientsService;
    private final TelegramBotClient telegramBotClient;


    public RegisterUserCommand(ClientsService clientsService, TelegramBotClient telegramBotClient) {
        this.clientsService = clientsService;
        this.telegramBotClient = telegramBotClient;

    }

    @Override
    public void execute(Long chatId, List<Update> updatesList) {
        try {
            Update update = updatesList.get(0);
            this.clientId = Long.valueOf(update.callbackQuery().data().substring(3));
            this.chatId = update.callbackQuery().message().chat().id();
            this.messageId = update.callbackQuery().message().messageId();

            Clients client = new Clients();
            telegramBotClient.sendMessage(chatId, "Пожалуйста, пришлите ваши данные для регистрации в " +
                    "следующем порядке: имя, фамилия, номер телефона, почта");


            String userInput = waitForUserInput(chatId);
            String[] userDetails = userInput.split(" ");

            if (userDetails.length < 4) {
                telegramBotClient.sendMessage(chatId, "Please enter all required data.");
                return;
            }

            String name = userDetails[0];
            String surname = userDetails[1];
            String phoneNumber = userDetails[2];
            String email = userDetails[3];

            if (name.isEmpty() || surname.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || !isValidContact(phoneNumber) || !isValidContact(email)) {
                telegramBotClient.sendMessage(chatId, "Please enter valid data.");
                return;
            }

            client.setName(name + " " + surname);
            client.setContact(phoneNumber + " " + email);

            clientsService.create(client);

            telegramBotClient.sendMessage(chatId, "User " + client.getName() + " Регистрация успешна !");
            return;
        } catch (Exception e) {
        }
    }


    private boolean isValidContact(String contact) {
        String phoneRegex = "^\\+7-9\\d{2}-\\d{3}-\\d{2}-\\d{2}$";
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return contact.matches(phoneRegex) || contact.matches(emailRegex);
    }

    public String waitForUserInput(Long chatId) {
        Update update = getUpdate();
        while (!update.message().chat().id().equals(chatId) || update.message().text() == null) {
            update = getUpdate();
        }
        return update.message().text();
    }

    private Update getUpdate() {
        // Implement logic to fetch and return the latest update
        return null;
    }

}
