package com.example.petShelter.command;

import com.example.petShelter.command.shelter_cat_inner_buttons.*;
import com.example.petShelter.command.shelter_dog_inner_buttons.*;
import com.example.petShelter.command.cat_menu_buttons.*;
import com.example.petShelter.command.dog_menu_buttons.*;
import com.example.petShelter.command.home_improvement_cat_buttons.HomeImprovementForAdultCat;
import com.example.petShelter.command.home_improvement_cat_buttons.HomeImprovementForCatWithDisabilities;
import com.example.petShelter.command.home_improvement_cat_buttons.HomeImprovementForKitty;
import com.example.petShelter.command.home_improvement_dog_buttons.HomeImprovementForAdult;
import com.example.petShelter.command.home_improvement_dog_buttons.HomeImprovementForPuppy;
import com.example.petShelter.command.home_improvement_dog_buttons.HomeImprovementWithDisabilities;
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
    private final ConcurrentHashMap<String, Command> commandWithListMap = new ConcurrentHashMap<>();

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
                            RegisterUserCommand registerUser, GetHomeImprovementForCat getHomeImprovementForCat, GetInfoAboutCat getInfoAboutCat,
                            GetInfoAboutDogMeeting getInfoAboutDogMeeting, DocumentsForGetADog documentsForGetADog,
                            TransportingADog transportingADog, DogHandlerAdvices dogHandlerAdvices, DogHandlersList dogHandlersList,
                            RejectToGetADog rejectToGetADog, HomeImprovementForPuppy homeImprovementForPuppy,
                            HomeImprovementForAdult homeImprovementForAdult, HomeImprovementWithDisabilities homeImprovementWithDisabilities,
                            GetInfoAboutCatMeeting getInfoAboutCatMeeting, DocumentsForGetACat documentsForGetACat,
                            TransportingACat transportingACat, RejectToGetACat rejectToGetACat, HomeImprovementForKitty homeImprovementForKitty,
                            HomeImprovementForAdultCat homeImprovementForAdultCat, HomeImprovementForCatWithDisabilities homeImprovementForCatWithDisabilities,
                            RegistrationNewVolunteer registrationNewVolunteer, ChangeActivityVolunteer changeActivityVolunteer,
                            VolunteersHelp volunteersHelp, StopConversation stopConversation,
                            ReportCommands reportCommands,
                            ChooseACat chooseACat, ChooseADog chooseADog) {

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
//        commandMap.put(CommandName.REGISTER_USER.getCommandName(), registerUser);
        commandMap.put(CommandName.GET_HOME_IMPROVEMENT_FOR_CAT.getCommandName(), getHomeImprovementForCat);
        commandMap.put(CommandName.GET_INFO_ABOUT_CAT.getCommandName(), getInfoAboutCat);
        commandMap.put(CommandName.GET_INFO_ABOUT_DOG_MEETING.getCommandName(), getInfoAboutDogMeeting);
        commandMap.put(CommandName.DOCUMENTS_FOR_GET_A_DOG.getCommandName(), documentsForGetADog);
        commandMap.put(CommandName.TRANSPORTING_A_DOG.getCommandName(), transportingADog);
        commandMap.put(CommandName.DOG_HANDLER_ADVICES.getCommandName(), dogHandlerAdvices);
        commandMap.put(CommandName.DOG_HANDLER_LIST.getCommandName(), dogHandlersList);
        commandMap.put(CommandName.REJECT_TO_GET_A_DOG.getCommandName(), rejectToGetADog);
        commandMap.put(CommandName.HOME_IMPROVEMENT_FOR_PUPPY.getCommandName(), homeImprovementForPuppy);
        commandMap.put(CommandName.HOME_IMPROVEMENT_FOR_ADULT.getCommandName(), homeImprovementForAdult);
        commandMap.put(CommandName.HOME_IMPROVEMENT_WITH_DISABILITIES.getCommandName(), homeImprovementWithDisabilities);
        commandMap.put(CommandName.GET_INFO_ABOUT_CAT_MEETING.getCommandName(), getInfoAboutCatMeeting);
        commandMap.put(CommandName.DOCUMENTS_FOR_GET_A_CAT.getCommandName(), documentsForGetACat);
        commandMap.put(CommandName.TRANSPORTING_A_CAT.getCommandName(), transportingACat);
        commandMap.put(CommandName.REJECT_TO_GET_A_CAT.getCommandName(), rejectToGetACat);
        commandMap.put(CommandName.HOME_IMPROVEMENT_FOR_KITTY.getCommandName(), homeImprovementForKitty);
        commandMap.put(CommandName.HOME_IMPROVEMENT_FOR_ADULT_CAT.getCommandName(), homeImprovementForAdultCat);
        commandMap.put(CommandName.HOME_IMPROVEMENT_FOR_CAT_WITH_DISABILITIES.getCommandName(), homeImprovementForCatWithDisabilities);
        commandMap.put(CommandName.REGISTRATION_NEW_VOLUNTEER.getCommandName(), registrationNewVolunteer);
        commandMap.put(CommandName.CHANGE_ACTIVITY_VOLUNTEER.getCommandName(), changeActivityVolunteer);
        commandMap.put(CommandName.VOLUNTEER_HELP.getCommandName(), volunteersHelp);
        commandMap.put(CommandName.STOP_CONVERSATION.getCommandName(), stopConversation);
//        commandMap.put(CommandName.REPORT_FROM_USERS.getCommandName(),reportCommands);
        commandMap.put(CommandName.CHOOSE_A_CAT.getCommandName(), chooseACat);
        commandMap.put(CommandName.CHOOSE_A_DOG.getCommandName(), chooseADog);

        commandWithListMap.put(CommandName.REGISTER_USER.getCommandName(), registerUser);
        commandWithListMap.put(CommandName.REPORT_FROM_USERS.getCommandName(), reportCommands);

    }

    public void process(String commandName, Long chatId, List<Update> updateList) {
        if (commandMap.isEmpty()) {
            throw new RuntimeException();
        } else if (commandMap.containsKey(commandName)) {
            commandMap.get(commandName).execute(chatId);
        } else if (commandWithListMap.containsKey(commandName)) {
            commandWithListMap.get(commandName).execute(chatId, updateList);
        }
    }

}