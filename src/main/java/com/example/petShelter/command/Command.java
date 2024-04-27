package com.example.petShelter.command;

import com.pengrad.telegrambot.model.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for implementing the command pattern
 * @author  Maria Sinyavskaya
 */

public interface Command {
    default void execute(Long chatId){};
//    {
//        execute(chatId, new ArrayList<>());
//    };
    default void execute(Long chatId, List<Update> updatesList){};
}
