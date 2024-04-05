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
    NO("noCommand");

    public final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

}
