package com.example.petShelter.commandTests;

import com.example.petShelter.command.*;
import com.example.petShelter.command.ShelterCatInnerButtons.*;
import com.example.petShelter.command.ShelterDogInnerButtons.*;
import com.example.petShelter.command.cat_menu_buttons.DocumentsForGetACat;
import com.example.petShelter.command.cat_menu_buttons.GetInfoAboutCatMeeting;
import com.example.petShelter.command.cat_menu_buttons.RejectToGetACat;
import com.example.petShelter.command.cat_menu_buttons.TransportingACat;
import com.example.petShelter.command.dog_menu_buttons.*;
import com.example.petShelter.command.home_improvement_cat_buttons.HomeImprovementForAdultCat;
import com.example.petShelter.command.home_improvement_cat_buttons.HomeImprovementForCatWithDisabilities;
import com.example.petShelter.command.home_improvement_cat_buttons.HomeImprovementForKitty;
import com.example.petShelter.command.home_improvement_dog_buttons.HomeImprovementForAdult;
import com.example.petShelter.command.home_improvement_dog_buttons.HomeImprovementForPuppy;
import com.example.petShelter.command.home_improvement_dog_buttons.HomeImprovementWithDisabilities;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommandContainerTest {
    @Mock
    private StartCommand startCommand;

    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private GetAddressOfDogShelterCommand getAddressOfDogShelterCommand;

    @Mock
    GetInfoAboutDogShelter getInfoAboutDogShelter;

    @Mock
    GetInfoAboutCatShelter getInfoAboutCatShelter;

    @Mock
    BackToChoosingShelter backToChoosingShelter;

    @Mock
    GetInfoAboutDog getInfoAboutDog;

    @Mock
    GetHomeImprovementForDog getHomeImprovementForDog;

    @Mock
    CatShelterInfoCommand catShelterInfoCommand;

    @Mock
    CatShelterSchedule catShelterSchedule;

    @Mock
    GetAddressOfCatShelterCommand getAddressOfCatShelterCommand;

    @Mock
    GetCatShelterCarPermission getCatShelterCarPermission;

    @Mock
    GetCatShelterLocation getCatShelterLocation;

    @Mock
    ShelterSecurityRulesForCats shelterSecurityRulesForCats;

    @Mock
    DogShelterInfoCommand dogShelterInfoCommand;

    @Mock
    DogShelterSchedule dogShelterSchedule;

    @Mock
    GetDogShelterLocation getDogShelterLocation;

    @Mock
    GetShelterCarPermission getShelterCarPermission;

    @Mock
    ShelterSecurityRulesForDogs shelterSecurityRulesForDogs;

    @Mock
    RegisterUser registerUser;

    @Mock
    GetHomeImprovementForCat getHomeImprovementForCat;

    @Mock
    GetInfoAboutCat getInfoAboutCat;
    @Mock
    GetInfoAboutDogMeeting getInfoAboutDogMeeting;
    @Mock
    DocumentsForGetADog documentsForGetADog;
    @Mock
    TransportingADog transportingADog;
    @Mock
    DogHandlerAdvices dogHandlerAdvices;
    @Mock
    DogHandlersList dogHandlersList;
    @Mock
    RejectToGetADog rejectToGetADog;
    @Mock
    HomeImprovementForPuppy homeImprovementForPuppy;
    @Mock
    HomeImprovementForAdult homeImprovementForAdult;
    @Mock
    HomeImprovementWithDisabilities homeImprovementWithDisabilities;
    @Mock
    GetInfoAboutCatMeeting getInfoAboutCatMeeting;
    @Mock
    DocumentsForGetACat documentsForGetACat;
    @Mock
    TransportingACat transportingACat;
    @Mock
    RejectToGetACat rejectToGetACat;
    @Mock
    HomeImprovementForKitty homeImprovementForKitty;
    @Mock
    HomeImprovementForAdultCat homeImprovementForAdultCat;
    @Mock
    HomeImprovementForCatWithDisabilities homeImprovementForCatWithDisabilities;
    private CommandContainer commandContainer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        commandContainer = new CommandContainer(
                startCommand, getAddressOfDogShelterCommand, getInfoAboutDogShelter, getInfoAboutCatShelter,
                backToChoosingShelter, getInfoAboutDog, getHomeImprovementForDog, catShelterInfoCommand,
                catShelterSchedule, getAddressOfCatShelterCommand, getCatShelterCarPermission,
                getCatShelterLocation, shelterSecurityRulesForCats, dogShelterInfoCommand, dogShelterSchedule,
                getDogShelterLocation, getShelterCarPermission, shelterSecurityRulesForDogs, registerUser,
                getHomeImprovementForCat, getInfoAboutCat, getInfoAboutDogMeeting, documentsForGetADog,
                transportingADog, dogHandlerAdvices, dogHandlersList, rejectToGetADog, homeImprovementForPuppy,
                homeImprovementForAdult, homeImprovementWithDisabilities, getInfoAboutCatMeeting, documentsForGetACat,
                transportingACat, rejectToGetACat, homeImprovementForKitty, homeImprovementForAdultCat,
                homeImprovementForCatWithDisabilities
        );
    }

    @Test
    void processMethodMustRunStartCommand() {
        String commandName = CommandName.START.getCommandName();
        Long chatId = 123456L;

        commandContainer.process(commandName, chatId);

        verify(startCommand, times(1)).execute(chatId);
    }

    @Test
    void processShouldNotRunAnyClass() {
        String commandName = "NonExistingCommand";
        Long chatId = 123456L;

        commandContainer.process(commandName, chatId);

        // Убедимся, что ни одна команда не была вызвана
        verifyNoInteractions(startCommand);
        // Проверим, что сообщение не отправлено
        verifyNoInteractions(telegramBotClient);
    }
}
