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
    GET_CAT_SHELTER_INFO("/getCatInfo"),
    GET_CAT_SHELTER_SCHEDULE("/getCatShelterSchedule"),
    GET_CAT_SHELTER_ADDRESS("/getAddressOfCatShelter"),
    GET_CAT_SHELTER_CAR_PERMISSION("/CarPermissionNumberCatShelter"),
    GET_CAT_SHELTER_LOCATION("/getCatShelterLocation"),
    REGISTER_USER("/registerUserInCatShelter"),
    GET_CAT_SHELTER_RULES("/ShelterSecurityRulesForCats"),
    GET_DOG_SHELTER_INFO("/getDogShelterInfo"),
    GET_DOG_SHELTER_SCHEDULE("/getDogShelterSchedule"),
    GET_DOG_SHELTER_CAR_PERMISSION("/CarPermissionNumber"),
    GET_DOG_SHELTER_LOCATION("/getDogShelterLocation"),
    GET_DOG_SHELTER_RULES("/ShelterSecurityRulesForDogs"),
    NO("noCommand");


    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

}
