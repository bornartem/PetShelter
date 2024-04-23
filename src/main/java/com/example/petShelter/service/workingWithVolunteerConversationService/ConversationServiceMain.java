package com.example.petShelter.service.workingWithVolunteerConversationService;

import com.example.petShelter.exception.NotFoundInDB;
import com.example.petShelter.model.ConversationPeople;
import com.example.petShelter.service.ClientsService;
import com.example.petShelter.service.ConversationPeopleService;
import com.example.petShelter.service.VolunteersService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class ConversationServiceMain {

    @Autowired
    VolunteersService volunteerService;

    @Autowired
    ClientsService clientService;

    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private ConversationPeopleService conversationPeopleService;


    /**
     * method in beginning of dialog between client and volunteer
     * use when client call volunteer for help.<br>
     * if not found volunteer - client will be added "wait users" table
     *
     * @param clientChatId client chat id, who call about help
     */
    public void firstGivingUsers(Long clientChatId) {

        telegramBot.execute(new SendMessage(
                clientChatId,
                ConstantsSendMessageInConv.WE_FIND_VOLUNTEER
        ));

        Long volId;
        try {
            volId = findRelaxVolunteers();
        } catch (NotFoundInDB e) {
            volId = null;
        }

        if (Objects.equals(volId, null)) {
            volunteerNotFound(clientChatId);
        } else {
            volunteerReady(clientChatId, volId);
        }
    }


    /**
     * add client to table for waiting in queue.
     *
     * @param clientChatId is client chat id in telegram
     */
    private void volunteerNotFound(Long clientChatId) {
        telegramBot.execute(new SendMessage(
                clientChatId,
                "Все волонтеры сейчас заняты, напишите чуть позже")
        );
    }


    /**
     * method for create conversation between client and volunteer
     *
     * @param clientChatId client chat id in telegram
     * @param volChatId    volunteer chat id in telegram
     */
    public void volunteerReady(Long clientChatId, Long volChatId) {
        telegramBot.execute(new SendMessage(
                clientChatId,
                ConstantsSendMessageInConv.FINISH_FIND
        ));

        volunteerService.inactiveVolunteerByChatId(volChatId);

        conversationPeopleService.addPeople(clientChatId, volChatId);

        telegramBot.execute(new SendMessage(
                volChatId,
                ConstantsSendMessageInConv.HI_MESSAGE
        ));
    }


    /**
     * method find free volunteer, but he is not found it, he made
     * exception "NotFoundInDB"
     *
     * @return any free volunteer id
     */
    private Long findRelaxVolunteers() {
        return volunteerService.findFreeVolunteer().getChatId();
    }


    /**
     * method if in listener got letter from user, who is in mable "conversation people"
     * @param userChatId users chat id, who write message
     * @param message his message
     * @param isVolunteer is he volunteer
     */
    public void continueConversation(Long userChatId, String message, Boolean isVolunteer) {

        ConversationPeople people = conversationPeopleService.findByChatId(userChatId);
        Long opponentChatId = people.getOpponentChatId();

        if (isVolunteer && Objects.equals(message, "!stop")) {

            telegramBot.execute(new SendMessage(userChatId, ConstantsSendMessageInConv.STOP_MESSAGE_FOR_VOL));
            telegramBot.execute(new SendMessage(opponentChatId, ConstantsSendMessageInConv.STOP_MESSAGE));

            conversationPeopleService.deletePeople(opponentChatId);
            conversationPeopleService.deletePeople(userChatId);

        } else {
            telegramBot.execute(new SendMessage(opponentChatId, message));
        }

    }
}
