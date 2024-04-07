package com.example.petShelter.command;

import com.example.petShelter.service.TelegramBotClient;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * The class consists of logic of the project, which has
 the method to find and send messages with "TelegramBot"
 * @author Maria Sinyavskaya
 */
@Service
public class CommandContainer {

    private final ConcurrentHashMap<String, Command> commandMap = new ConcurrentHashMap<>();

    private TelegramBotClient telegramBotClient;

    public CommandContainer(StartCommand commandStart, GetAddressOfDogShelterCommand getAddressOfDogShelterCommand,
                            GetInfoAboutDogShelter getInfoAboutDogShelter,
                            GetInfoAboutCatShelter getInfoAboutCatShelter,
                            BackToChoosingShelter backToChoosingShelter,
                            GetInfoAboutDog getInfoAboutDog,
                            GetHomeImprovementForDog getHomeImprovementForDog) {
        commandMap.put(CommandName.START.getCommandName(), commandStart);
        commandMap.put(CommandName.GET_ADDRESS_OF_DOG_SHELTER.getCommandName(), getAddressOfDogShelterCommand);
        commandMap.put(CommandName.GET_INFO_ABOUT_DOG_SHELTER.getCommandName(), getInfoAboutDogShelter);
        commandMap.put(CommandName.GET_INFO_ABOUT_CAT_SHELTER.getCommandName(), getInfoAboutCatShelter);
        commandMap.put(CommandName.BACK_TO_CHOOSING_SHELTER.getCommandName(), backToChoosingShelter);
        commandMap.put(CommandName.GET_INFO_ABOUT_DOG.getCommandName(), getInfoAboutDog);
        commandMap.put(CommandName.GET_HOME_IMPROVEMENT_FOR_DOG.getCommandName(), getHomeImprovementForDog);
    }

    public void process(String commandName, Long chatId) {
        if (commandMap.containsKey(commandName)) {
            commandMap.get(commandName).execute(chatId);
        }
    }

}