package com.example.petShelter.command;

import com.example.petShelter.command.ShelterCatInnerButtons.*;
import com.example.petShelter.command.ShelterDogInnerButtons.*;
import com.example.petShelter.service.TelegramBotClient;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The class consists of logic of the project, which has
 * the method to find and send messages with "TelegramBot"
 *
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
                            GetHomeImprovementForDog getHomeImprovementForDog, CatShelterInfoCommand catShelterInfoCommand,
                            CatShelterSchedule catShelterSchedule, GetAddressOfCatShelterCommand getAddressOfCatShelterCommand,
                            GetCatShelterCarPermission getCatShelterCarPermission, GetCatShelterLocation getCatShelterLocation,
                            ShelterSecurityRulesForCats shelterSecurityRulesForCats, DogShelterInfoCommand dogShelterInfoCommand,
                            DogShelterSchedule dogShelterSchedule, GetDogShelterLocation getDogShelterLocation,
                            GetShelterCarPermission getShelterCarPermission, ShelterSecurityRulesForDogs shelterSecurityRulesForDogs,
                            RegisterUserCommand registerUser,
                            GetHomeImprovementForCat getHomeImprovementForCat,
                            GetInfoAboutCat getInfoAboutCat) {

        commandMap.put(CommandName.START.getCommandName(), commandStart);
        commandMap.put(CommandName.GET_ADDRESS_OF_DOG_SHELTER.getCommandName(), getAddressOfDogShelterCommand);
        commandMap.put(CommandName.GET_INFO_ABOUT_DOG_SHELTER.getCommandName(), getInfoAboutDogShelter);
        commandMap.put(CommandName.GET_INFO_ABOUT_CAT_SHELTER.getCommandName(), getInfoAboutCatShelter);
        commandMap.put(CommandName.BACK_TO_CHOOSING_SHELTER.getCommandName(), backToChoosingShelter);
        commandMap.put(CommandName.GET_INFO_ABOUT_DOG.getCommandName(), getInfoAboutDog);
        commandMap.put(CommandName.GET_HOME_IMPROVEMENT_FOR_DOG.getCommandName(), getHomeImprovementForDog);
        commandMap.put(CommandName.GET_CAT_SHELTER_INFO.getCommandName(), catShelterInfoCommand);
        commandMap.put(CommandName.GET_CAT_SHELTER_SCHEDULE.getCommandName(), catShelterSchedule);
        commandMap.put(CommandName.GET_CAT_SHELTER_ADDRESS.getCommandName(), getAddressOfCatShelterCommand);
        commandMap.put(CommandName.GET_CAT_SHELTER_CAR_PERMISSION.getCommandName(), getCatShelterCarPermission);
        commandMap.put(CommandName.GET_CAT_SHELTER_LOCATION.getCommandName(), getCatShelterLocation);
        commandMap.put(CommandName.GET_CAT_SHELTER_RULES.getCommandName(), shelterSecurityRulesForCats);
        commandMap.put(CommandName.GET_DOG_SHELTER_INFO.getCommandName(), dogShelterInfoCommand);
        commandMap.put(CommandName.GET_DOG_SHELTER_SCHEDULE.getCommandName(), dogShelterSchedule);
        commandMap.put(CommandName.GET_DOG_SHELTER_LOCATION.getCommandName(), getDogShelterLocation);
        commandMap.put(CommandName.GET_DOG_SHELTER_CAR_PERMISSION.getCommandName(), getShelterCarPermission);
        commandMap.put(CommandName.GET_DOG_SHELTER_RULES.getCommandName(), shelterSecurityRulesForDogs);
        commandMap.put(CommandName.REGISTER_USER.getCommandName(), registerUser);
        commandMap.put(CommandName.GET_HOME_IMPROVEMENT_FOR_CAT.getCommandName(), getHomeImprovementForCat);
        commandMap.put(CommandName.GET_INFO_ABOUT_CAT.getCommandName(), getInfoAboutCat);


    }

    public void process(String commandName, Long chatId, List<Update> updatesList) {
        if (commandMap.isEmpty()) {
            throw new RuntimeException();
        } else if (commandMap.containsKey(commandName)) {
            commandMap.get(commandName).execute(chatId, updatesList);
        }
    }

}