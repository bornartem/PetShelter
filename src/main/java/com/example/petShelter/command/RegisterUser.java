package com.example.petShelter.command;


import com.example.petShelter.model.Clients;
import com.example.petShelter.service.ClientsService;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;


/**
 * Class which allows to register users  in Telegram .
 *
 * @author Khilola Kushbakova
 */

@Component("/registerUser")
public class RegisterUser  implements Command {
    private final ClientsService clientsService;
    private final TelegramBotClient telegramBotClient;


    public RegisterUser(ClientsService clientsService, TelegramBotClient telegramBotClient) {
        this.clientsService = clientsService;
        this.telegramBotClient = telegramBotClient;

    }


    @Override
    public void execute(Long chatId) {
        Clients newUser = clientsService.create(clients);
        telegramBotClient.sendMessage(chatId, "User registered: " + newUser.getName());
    }
}
