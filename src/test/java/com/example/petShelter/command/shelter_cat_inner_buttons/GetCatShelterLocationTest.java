package com.example.petShelter.command.shelter_cat_inner_buttons;

import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetCatShelterLocationTest {

    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private SheltersService sheltersService;

    private GetCatShelterLocation getCatShelterLocation;

    private final static double CAT_SHELTER_LATITUDE = 59.843930112491435;
    private final static double CAT_SHELTER_LONGITUDE = 30.4450572866618;

    @Test
    void executeMethodShouldSendMessage() {
        Long chatId = 123456L;
        getCatShelterLocation = new GetCatShelterLocation(telegramBotClient, sheltersService);

        String location = "Latitude: " + CAT_SHELTER_LATITUDE + ", Longitude: " + CAT_SHELTER_LONGITUDE;
        when(sheltersService.showLocation(CAT_SHELTER_LATITUDE, CAT_SHELTER_LONGITUDE)).thenReturn(location);

        getCatShelterLocation.execute(chatId);

        verify(telegramBotClient, times(1)).sendMessage(chatId, location);
        verify(sheltersService).showLocation(CAT_SHELTER_LATITUDE, CAT_SHELTER_LONGITUDE);
    }
}
