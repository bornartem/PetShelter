package com.example.petShelter.service.workingWithVolunteerConversationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FindFreeVolunteersScheduled {

    @Autowired
    private ConversationServiceMain conversationService;


    /**
     * work with waiting clients
     */
    @Scheduled(cron = "0 * * * * *")
    public void task() throws InterruptedException {
        Integer countWaitingClients = 0;
        Integer countFreeVolunteers = 0;

        /*
            find count waiting client at table
            countWaitingClients =
            find count free volunteers
            countFreeVolunteers =
         */

        if (countWaitingClients != 0 && countFreeVolunteers != 0) {
            Long clientChatId = 0L;
            Long volChatId = 0L;

            /*
                найти любого волонтера
                найти клиента с наименьшим индексом
                (того который дольше всех ждет)
             */



            conversationService.volunteerReady(clientChatId, volChatId);


        }

    }

}
