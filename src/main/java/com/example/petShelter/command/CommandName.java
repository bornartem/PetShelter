package com.example.petShelter.command;

/**
 * The class consists of logic of the project, which has
 * enum of commands
 *
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
    REGISTER_USER("/registerUser"),
    GET_CAT_SHELTER_RULES("/ShelterSecurityRulesForCats"),
    GET_DOG_SHELTER_INFO("/getDogShelterInfo"),
    GET_DOG_SHELTER_SCHEDULE("/getDogShelterSchedule"),
    GET_DOG_SHELTER_CAR_PERMISSION("/CarPermissionNumber"),
    GET_DOG_SHELTER_LOCATION("/getDogShelterLocation"),
    GET_DOG_SHELTER_RULES("/ShelterSecurityRulesForDogs"),
    GET_HOME_IMPROVEMENT_FOR_CAT("/getHomeImprovementForCat"),
    GET_INFO_ABOUT_CAT("/getInfoAboutCat"),
    GET_INFO_ABOUT_DOG_MEETING("/getInfoAboutDogMeeting"),
    GET_INFO_ABOUT_CAT_MEETING("/getInfoAboutCatMeeting"),
    DOCUMENTS_FOR_GET_A_DOG("/documentsForGetADog"),
    DOCUMENTS_FOR_GET_A_CAT("/documentsForGetACat"),
    TRANSPORTING_A_DOG("/transportingADog"),
    TRANSPORTING_A_CAT("/transportingACat"),
    DOG_HANDLER_ADVICES("/dogHandlerAdvices"),
    DOG_HANDLER_LIST("/dogHandlersList"),
    REJECT_TO_GET_A_DOG("/rejectToGetADog"),
    REJECT_TO_GET_A_CAT("/rejectToGetACat"),
    HOME_IMPROVEMENT_FOR_PUPPY("/homeImprovementForPuppy"),
    HOME_IMPROVEMENT_FOR_KITTY("/homeImprovementForKitty"),
    HOME_IMPROVEMENT_FOR_ADULT("/homeImprovementForAdult"),
    HOME_IMPROVEMENT_FOR_ADULT_CAT("/homeImprovementForAdultCat"),
    HOME_IMPROVEMENT_WITH_DISABILITIES("/homeImprovementWithDisabilities"),
    HOME_IMPROVEMENT_FOR_CAT_WITH_DISABILITIES("/homeImprovementForCatWithDisabilities"),
    REGISTRATION_NEW_VOLUNTEER("/volunteersLoginIn"),
    CHANGE_ACTIVITY_VOLUNTEER("/changeActivity"),
    REPORT_FROM_USERS("/reportFromUsers"),
    STOP_CONVERSATION("/stopConversation"),
    NO("noCommand");


    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

}
