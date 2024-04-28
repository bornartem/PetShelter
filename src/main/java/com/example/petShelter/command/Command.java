package com.example.petShelter.command;

import com.pengrad.telegrambot.model.Update;

import java.util.List;

/**
 * Interface for implementing the command pattern
 * @author  Maria Sinyavskaya
 */

public interface Command {
    default void execute(Long chatId){};
    default void execute(Long chatId, List<Update> updateList){};

}
