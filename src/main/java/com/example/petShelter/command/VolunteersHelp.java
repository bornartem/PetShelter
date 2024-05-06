package com.example.petShelter.command;

import com.example.petShelter.workingWithVolunteerService.conversationService.ConversationServiceMain;
import org.springframework.stereotype.Component;

/**
 * Call volunteer help for clients question
 * @author Danil
 */
@Component("/volunteerHelp")
public class VolunteersHelp implements Command{
private final ConversationServiceMain conversationServiceMain;

    public VolunteersHelp(ConversationServiceMain conversationServiceMain) {
        this.conversationServiceMain = conversationServiceMain;
    }

    @Override
    public void execute(Long chatId) {
        conversationServiceMain.firstGivingUsers(chatId);
    }

}