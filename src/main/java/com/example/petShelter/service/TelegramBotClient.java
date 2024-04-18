package com.example.petShelter.service;

public interface TelegramBotClient {
    void sendMessage(Long chatId, String message);

    String waitForUserInput(Long chatId);
}
