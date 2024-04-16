package com.example.petShelter.commandTests.ShelterDogInnerButtons;

import com.example.petShelter.command.ShelterDogInnerButtons.DogShelterSchedule;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DogShelterScheduleTest {

    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private SheltersService sheltersService;

    private DogShelterSchedule dogShelterSchedule;

    private final static long DOG_SHELTER_ID = 1;

    @Test
    void executeMethodShouldSendMessage() {
        Long chatId = 123456L;
        dogShelterSchedule = new DogShelterSchedule(telegramBotClient, sheltersService);

        String schedule = "09:00-20:00";
        when(sheltersService.showSchedule(DOG_SHELTER_ID)).thenReturn(schedule);

        dogShelterSchedule.execute(chatId);

        verify(telegramBotClient, times(1)).sendMessage(chatId, schedule);
        verify(sheltersService).showSchedule(DOG_SHELTER_ID);
    }
}
