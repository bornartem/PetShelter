package com.example.petShelter.commandTests.ShelterDogInnerButtons;

import com.example.petShelter.command.ShelterDogInnerButtons.GetDogShelterLocation;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetDogShelterLocationTest {

    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private SheltersService sheltersService;

    private GetDogShelterLocation getDogShelterLocation;

    private final static double DOG_SHELTER_LATITUDE = 41.14046547170331;
    private final static double DOG_SHELTER_LONGITUDE = 1.2202950534118546;

    @Test
    void executeMethodShouldSendMessage() {
        Long chatId = 123456L;
        getDogShelterLocation = new GetDogShelterLocation(telegramBotClient, sheltersService);

        String location = "Latitude: " + DOG_SHELTER_LATITUDE + ", Longitude: " + DOG_SHELTER_LONGITUDE;
        when(sheltersService.showLocation(DOG_SHELTER_LATITUDE, DOG_SHELTER_LONGITUDE)).thenReturn(location);

        getDogShelterLocation.execute(chatId);

        verify(telegramBotClient, times(1)).sendMessage(chatId, location);
        verify(sheltersService).showLocation(DOG_SHELTER_LATITUDE, DOG_SHELTER_LONGITUDE);
    }
}
