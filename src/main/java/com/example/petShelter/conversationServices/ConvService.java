package com.example.petShelter.conversationServices;

import com.example.petShelter.service.ClientsService;
import com.example.petShelter.service.VolunteersService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;



@Service
public class ConvService {

    @Autowired
    VolunteersService volunteerService;

    @Autowired
    ClientsService clientService;

    private TelegramBot telegramBot;


    /**
     * метод в начале когда пришло сообщение
     * о том что пользователь вызывает волонтера<br>
     * либо отправляем его в таблицу ожидающих, либо создаем
     * общение между клиентом и волонтером
     * @param update пользователь который просит о помощи
     */
    public void firstGivingUsers(Update update) {
        Long clientChatId = update.message().chat().id();
//        String clientText = update.message().text();

        telegramBot.execute(new SendMessage(
                clientChatId,
                SendMessageInConv.WE_FIND_VOLUNTEER
        ));

        Long volId;
        volId = findRelaxVolunteers();

        if (volId == null) {
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
            make check on count waiting client at table
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
     * @param clientChatId client chat id in telegram
     * @param volChatId volunteer chat id in telegram
     */
    private void volunteerReady(Long clientChatId, Long volChatId) {
        telegramBot.execute(new SendMessage(
                clientChatId,
                SendMessageInConv.FINISH_FIND
        ));

            /*
            //делает волонтера неактивным
            volunteerService.inactiveVolunteer(volId);
            */


        //добавил их в таблицу общающихся
        usersToConversationTable(clientChatId, volChatId);


        //отправить волонтеру что он должен первое написать
        telegramBot.execute(new SendMessage(
                volChatId,
                SendMessageInConv.HI_MESSAGE
        ));
    }



    /**
     * добавляем в таблицу общающихся пользователей
     * клиента и волонтера
     *
     * @param clientId id клиента not null
     * @param volId id волонтера not null
     */
    private void usersToConversationTable(Long clientId, Long volId) {
//        id генерируется само

        /*
            методы
            conversationPeopleService.addPeople(clientId, volId);
            conversationPeopleService.addPeople(volId, clientId);
        */

    }


    /**
     * метод который ищет свободного волонтера
     * если такого не находится возвращается null
     * @return id свободного волнтера или null
     */
    private Long findRelaxVolunteers() {
        // поиск по таблице(ее пока что нет)
        return null;
    }



    /**
     * если в листенере пришло сообщение от пользователя
     * который есть в таблице общающихся пользователей
     *
     * @param update      пользователь написавший сообщение
     * @param isVolunteer является пользователь волонтером
     */
    public void continueConversation(Update update, Boolean isVolunteer) {
        Long updateId = update.message().chat().id();
        String message = update.message().text();
        if (isVolunteer && Objects.equals(message, "/stop")) {
            /*
                удалить из общающихся таблицы обоих
            */

            telegramBot.execute(new SendMessage(updateId, SendMessageInConv.STOP_MESSAGE_FOR_VOL));

            /*
                найти в таблице по updateId с кем общается и ему тоже отправить
                telegramBot.execute(new SendMessage(updateId, SendMessageInConv.STOP_MESSAGE));

             */
        } else {
            Long opponentChatId;
            /*
                метод поиска по таблице с кем общается
                opponentChatId =
            */
            // инициализация для того чтобы не было ошибок, потом убрать,
            // когда код сверху будет готов
            opponentChatId = 0L;

            telegramBot.execute(new SendMessage(opponentChatId, message));
        }

    }
}
