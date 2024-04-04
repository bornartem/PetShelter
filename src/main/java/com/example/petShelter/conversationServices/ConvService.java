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
     * о том что пользователь вызывает волонтера
     *
     * @param update пользователь который просит о помощи
     */
    public void firstGivingUsers(Update update) {
        Long ClientId = update.message().chat().id();
        String clientText = update.message().text();

        telegramBot.execute(new SendMessage(
                ClientId,
                SendMessageInConv.WE_FIND_VOLUNTEER
        ));

        Long volId;
        volId = findRelaxVolunteers();

        if (volId == null) {
            //добавляем в ожидающих

        } else {

            telegramBot.execute(new SendMessage(
                    ClientId,
                    SendMessageInConv.FINISH_FIND
            ));

            /*
            //делает волонтера неактивным
            volunteerService.inactiveVolunteer(volId);
            */


            //добавил их в таблицу общающихся
            usersToConversationTable(ClientId, volId);


            //отправить волонтеру что он должен первое написать
            telegramBot.execute(new SendMessage(
                    volId,
                    SendMessageInConv.HI_MESSAGE
            ));

        }
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
