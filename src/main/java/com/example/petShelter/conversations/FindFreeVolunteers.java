package com.example.petShelter.conversations;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FindFreeVolunteers {


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

        if (countWaitingClients == 0 || countFreeVolunteers == 0) {
            break;
        } else {
            Long clientChatId;
            Long volChatId;

            /*
                найти любого волонтера
                найти клиента с наименьшим индексом
                (того который дольше всех ждет)
             */


            /*
            нужно использовать метод если найден волонтер
             */


        }

    }

}
