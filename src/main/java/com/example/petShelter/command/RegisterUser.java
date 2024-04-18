package com.example.petShelter.command;

import com.example.petShelter.model.Clients;
import com.example.petShelter.service.ClientsService;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;

/**
 * Class which allows to register users  in Telegram .
 * @author  Khilola Kushbakova
 */



@Component("/registerUserInCatShelter")

public class RegisterUser implements Command {
    private final ClientsService clientsService;

    private TelegramBotClient telegramBotClient;

    public RegisterUser(ClientsService clientsService, TelegramBotClient telegramBotClient) {
        this.clientsService = clientsService;
        this.telegramBotClient = telegramBotClient;
    }

    public void execute(Long chatId) {
        Clients client = new Clients();
        telegramBotClient.sendMessage(chatId, "Пришлите пожалуйста, ваше имя");
        String name = telegramBotClient.waitForUserInput(chatId);
        client.setName(name);
        telegramBotClient.sendMessage(chatId, "Пришлите пожалуйста , ваши контакты (номер телефона и почту");
        String contact = telegramBotClient.waitForUserInput(chatId);
        client.setContact(contact);

        clientsService.create(client);
        telegramBotClient.sendMessage(chatId, "User registered successfully in shelter");
    }
}
