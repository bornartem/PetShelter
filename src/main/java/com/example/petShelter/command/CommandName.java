package com.example.petShelter.command;

/**
 * The class consists of logic of the project, which has
 enum of commands
 * @author Maria Sinyavskaya
 */
public enum CommandName {
    START("/start"),
    INFO("/info"),
    VOLUNTEER_HELP("/volunteerHelp"),
    SEND_REPORT("/sendReport"),
    GET_ADDRESS_OF_DOG_SHELTER("/getAddressOfDogShelter"),
    GET_INFO_ABOUT_DOG_SHELTER("/getInfoAboutDogShelter"),
    BACK_TO_CHOOSING_SHELTER("/backToChoosingShelter"),
    GET_INFO_ABOUT_CAT_SHELTER("/getInfoAboutCatShelter"),
    GET_INFO_ABOUT_DOG("/getInfoAboutDog"),
    GET_HOME_IMPROVEMENT_FOR_DOG("/getHomeImprovementForDog"),
    NO("noCommand");

    public final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

}
