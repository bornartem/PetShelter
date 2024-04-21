package com.example.petShelter.service.workingWithVolunteerConversationService;

import com.example.petShelter.model.ConversationPeople;
import com.example.petShelter.model.Volunteers;
import com.example.petShelter.service.ClientsService;
import com.example.petShelter.service.ConversationPeopleService;
import com.example.petShelter.service.VolunteersService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
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
     * метод в начале когда пришло сообщение
     * о том что пользователь вызывает волонтера<br>
     * либо отправляем его в таблицу ожидающих, либо создаем
     * общение между клиентом и волонтером
     *
     * @param clientChatId пользователь который просит о помощи
     */
    public void firstGivingUsers(Long clientChatId) {
//        Long clientChatId = update.message().chat().id();
//        String clientText = update.message().text();

        telegramBot.execute(new SendMessage(
                clientChatId,
                SendMessageInConv.WE_FIND_VOLUNTEER
        ));

        Long volId = null;
        volId = findRelaxVolunteers();

        if (volId.equals(null)) {
            //добавляем в ожидающих
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

        Integer countWaitingClients = 0;
        Integer countAllVolunteers = 0;

        /*
            find count waiting client at table
            countWaitingClients =
            find count all volunteers
            countAllVolunteers =
         */

        if (countWaitingClients > 2 * countAllVolunteers) {
            telegramBot.execute(new SendMessage(clientChatId, SendMessageInConv.CROWDED_TABLE));
        } else {
            telegramBot.execute(new SendMessage(clientChatId, SendMessageInConv.PLEASE_WAIT));

            /*
                метод добавит клиента в таблицу с ожидающими пользователями
             */
        }

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
                SendMessageInConv.FINISH_FIND
        ));

            /*
            делает волонтера неактивным
            volunteerService.inactiveVolunteer(volId);
            */
        volunteerService.inactiveVolunteerByChatId(volChatId);


        //добавил их в таблицу общающихся
//        usersToConversationTable(clientChatId, volChatId);
        conversationPeopleService.addPeople(clientChatId, volChatId);


        //отправить волонтеру что он должен первое написать
        telegramBot.execute(new SendMessage(
                volChatId,
                SendMessageInConv.HI_MESSAGE
        ));
    }


//    /**
//     * добавляем в таблицу общающихся пользователей
//     * клиента и волонтера
//     *
//     * @param clientId id клиента not null
//     * @param volId id волонтера not null
//     */
//    private void usersToConversationTable(Long clientId, Long volId) {
//        /*
//            методы
//            conversationPeopleService.addPeople(clientId, volId);
//            conversationPeopleService.addPeople(volId, clientId);
//        */
//        //добавляется два раза
//        conversationPeopleService.addPeople(clientId, volId);
////        conversationPeopleService.addPeople(volId, clientId);
//
//    }


    /**
     * метод который ищет свободного волонтера
     * если такого не находится возвращается null
     *
     * @return id свободного волнтера или null
     */
    private Long findRelaxVolunteers() {
        /*
        сначало делаю проверку есть ли ожидающие пользователи
        и если да то возвращаю null
         */


        /*
        поиск свободного волонтера
         */
        return volunteerService.findFirstActivity().getChatId();
    }


    /**
     * если в листенере пришло сообщение от пользователя
     * который есть в таблице общающихся пользователей
     *
     * @param userChatId  пользователь написавший сообщение
     * @param message его сообщение
     * @param isVolunteer является пользователь волонтером
     */
    public void continueConversation(Long userChatId, String message, Boolean isVolunteer) {
//        Long userChatId = update.message().chat().id();
//        String message = update.message().text();
        ConversationPeople people = conversationPeopleService.findByChatId(userChatId);
        Long opponentChatId = people.getOpponentChatId();
        if (isVolunteer && Objects.equals(message, "!stop")) {
            telegramBot.execute(new SendMessage(userChatId, SendMessageInConv.STOP_MESSAGE_FOR_VOL));

            /*
                найти в таблице по userChatId с кем общается и ему тоже отправить
                telegramBot.execute(new SendMessage(userChatId, SendMessageInConv.STOP_MESSAGE));
             */
            telegramBot.execute(new SendMessage(opponentChatId, SendMessageInConv.STOP_MESSAGE));

            /*
                удалить из общающихся таблицы обоих
            */

            conversationPeopleService.deletePeople(opponentChatId);
            conversationPeopleService.deletePeople(userChatId);

        } else {
//            Long opponentChatId;
            /*
                метод поиска по таблице с кем общается
                opponentChatId =
            */
            // инициализация для того чтобы не было ошибок, потом убрать,
            // когда код сверху будет готов
//            opponentChatId = 0L;

            telegramBot.execute(new SendMessage(opponentChatId, message));
        }

    }
}
