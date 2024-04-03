package com.example.petShelter.command;

public enum CommandName {
    START("/start"),
    INFO("/info"),
    VOLUNTEER_HELP("/volunteerHelp"),
    SEND_REPORT("/sendReport"),
    TO_SHELTER("/toShelter"),
    NO("noCommand");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

}
