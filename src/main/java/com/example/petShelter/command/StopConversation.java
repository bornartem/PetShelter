package com.example.petShelter.command;

import com.example.petShelter.model.Volunteers;
import com.example.petShelter.service.ConversationPeopleService;
import com.example.petShelter.service.TelegramBotClient;
import com.example.petShelter.service.VolunteersService;
import com.example.petShelter.workingWithVolunteerService.conversationService.ConstantsSendMessageInConv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The class consists of logic of the project, which has
 * the methods to finish conversation between client and volunteer
 *
 * @author Danil
 */
@Component("/stopConversation")
public class StopConversation implements Command {


    private final TelegramBotClient telegramBotClient;
    private final VolunteersService volunteerService;
    private final ConversationPeopleService conversationPeopleService;


    @Autowired
    public StopConversation(TelegramBotClient telegramBotClient, VolunteersService volunteersService, ConversationPeopleService conversationPeopleService) {
        this.telegramBotClient = telegramBotClient;
        this.volunteerService = volunteersService;
        this.conversationPeopleService = conversationPeopleService;

    }

    @Override
    public void execute(Long chatId) {
        Volunteers volunteers = volunteerService.findFirstByChatId(chatId);
        Long clientChatId = conversationPeopleService.findByChatId(chatId).getOpponentChatId();
        if (volunteers != null) {
            telegramBotClient.sendMessage(chatId, ConstantsSendMessageInConv.STOP_MESSAGE_FOR_VOL);
            telegramBotClient.sendMessage(clientChatId, ConstantsSendMessageInConv.STOP_MESSAGE);

            conversationPeopleService.deletePeople(clientChatId);
            conversationPeopleService.deletePeople(chatId);
        }

    }

}