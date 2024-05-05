package com.example.petShelter.workingWithVolunteerService.conversationService;

import com.example.petShelter.exception.NotFoundInDB;
import com.example.petShelter.service.ClientsService;
import com.example.petShelter.service.ConversationPeopleService;
import com.example.petShelter.service.TelegramBotClient;
import com.example.petShelter.service.VolunteersService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class ConversationServiceMain {

    private static final Logger log = LoggerFactory.getLogger(ConversationServiceMain.class);
    @Autowired
    VolunteersService volunteerService;

    @Autowired
    ClientsService clientService;

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private TelegramBotClient telegramBotClient;
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

        telegramBotClient.sendMessage(clientChatId, ConstantsSendMessageInConv.WE_FIND_VOLUNTEER);

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
    telegramBotClient.sendMessage(clientChatId,
            "Все волонтеры сейчас заняты, напишите чуть позже");
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

        telegramBotClient.sendMessage(volChatId, ConstantsSendMessageInConv.HI_MESSAGE);
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

    String VOLUNTEER_MESSAGE_LATE = "Вы успешно проверили отчет от пользователя. Ваша активность - " +
            "false. Если вы готовы принимать клиента/проверять следующий отчет нажмите /changeActivity";
    public void reportConversation(Long volChatId, String text) {
        try {
            Long clientChatId = Long.parseLong(text.split(" ")[3]);
            if (clientService.findFirstByChatId(clientChatId) == null) {
                telegramBotClient.sendMessage(volChatId, "проверьте правильность введенного id");
                return;
            }
            String notNeed = "/ответ на отчет " + clientChatId + " ";
            int len = notNeed.length();

            telegramBotClient.sendMessage(clientChatId, text.substring(len));
            telegramBotClient.sendMessage(volChatId, VOLUNTEER_MESSAGE_LATE);

        } catch (Exception e) {
            log.info("error in send message about checking report to client (report = {})", text);
        }

    }
}
