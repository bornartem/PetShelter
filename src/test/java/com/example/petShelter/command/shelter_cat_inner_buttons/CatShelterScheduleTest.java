package com.example.petShelter.command.shelter_cat_inner_buttons;

import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CatShelterScheduleTest {

    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private SheltersService sheltersService;

    private CatShelterSchedule catShelterSchedule;

    private final static long CAT_SHELTER_ID = 2;

    @Test
    void executeMethodShouldSendMessage() {
        Long chatId = 123456L;
        catShelterSchedule = new CatShelterSchedule(telegramBotClient, sheltersService);

        String schedule = "09:00-20:00";
        when(sheltersService.showSchedule(CAT_SHELTER_ID)).thenReturn(schedule);

        catShelterSchedule.execute(chatId);

        verify(telegramBotClient, times(1)).sendMessage(chatId, schedule);
        verify(sheltersService).showSchedule(CAT_SHELTER_ID);
    }
}
