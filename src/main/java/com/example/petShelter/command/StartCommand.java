package com.example.petShelter.command;

import com.example.petShelter.listener.ChoosingShelterMenu;
import com.example.petShelter.service.TelegramBotClient;
import org.springframework.stereotype.Component;

/**
 * The class consists of logic of the project, which has
 the methods to send messages with "TelegramBot"
 * @author Maria Sinyavskaya
 */
@Component("/start")
public class StartCommand implements Command{
    private final static String START_MESSAGE = "Привет! Это приют \"Пушистый друг\". " +
            "\nМы помогаем бездомным животным найти новый дом.";

    private final TelegramBotClient telegramBotClient;

    private final ChoosingShelterMenu choosingShelterMenu;

    public StartCommand(TelegramBotClient telegramBotClient, ChoosingShelterMenu choosingShelterMenu) {
        this.telegramBotClient = telegramBotClient;
        this.choosingShelterMenu = choosingShelterMenu;
    }

    @Override
    public void execute(Long chatId) {
        telegramBotClient.sendMessage(chatId, START_MESSAGE);
        choosingShelterMenu.sendMenuMessage(chatId);
    }

}