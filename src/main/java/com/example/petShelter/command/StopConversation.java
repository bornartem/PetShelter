package com.example.petShelter.command;

import com.example.petShelter.listener.ChoosingShelterMenu;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;

/**
 * The class consists of logic of the project, which has
 the methods to finish conversation between client and volunteer
 * @author Danil
 */
@Component("/stopConversation")
public class StopConversation implements Command{
    private final static String START_MESSAGE = "Привет! Это приют \"Пушистый друг\". " +
            "\nМы помогаем бездомным животным найти новый дом.";

    private final TelegramBotClient telegramBotClient;


    public StopConversation(TelegramBotClient telegramBotClient, ChoosingShelterMenu choosingShelterMenu) {
        this.telegramBotClient = telegramBotClient;

    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, START_MESSAGE);
    }

}