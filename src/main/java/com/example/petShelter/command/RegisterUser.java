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
        clientsService.create(client);

        telegramBotClient.sendMessage(chatId, "User registered successfully in shelter");
    }
}
