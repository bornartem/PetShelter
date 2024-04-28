package com.example.petShelter.command;

import com.example.petShelter.listener.ChoosingShelterMenu;
import com.example.petShelter.service.TelegramBotClient;
import com.example.petShelter.service.workingWithVolunteerConversationService.ConversationServiceMain;
import org.springframework.stereotype.Component;

/**
 * Call volunteer help for clients question
 * @author Danil
 */
@Component("/volunteerHelp")
public class VolunteersHelp implements Command{
//    private final static String START_MESSAGE = "Привет! Это приют \"Пушистый друг\". " +
//            "\nМы помогаем бездомным животным найти новый дом.";
//
//    private final TelegramBotClient telegramBotClient;
//
//    private final ChoosingShelterMenu choosingShelterMenu;
private final ConversationServiceMain conversationServiceMain;

    public VolunteersHelp(ConversationServiceMain conversationServiceMain) {
//        this.telegramBotClient = telegramBotClient;
//        this.choosingShelterMenu = choosingShelterMenu;
        this.conversationServiceMain = conversationServiceMain;
    }

    @Override
    public void execute(Long chatId) {
//        telegramBotClient.sendMessage(chatId, START_MESSAGE);
//        choosingShelterMenu.sendMenuMessage(chatId);
        conversationServiceMain.firstGivingUsers(chatId);
    }

}