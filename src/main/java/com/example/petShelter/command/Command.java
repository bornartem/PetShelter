package com.example.petShelter.command;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

public interface Command {

    void execute(Message message, CallbackQuery callbackQuery);
}
