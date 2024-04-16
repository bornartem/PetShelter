package com.example.petShelter.commandTests.ShelterDogInnerButtons;

import com.example.petShelter.command.ShelterDogInnerButtons.DogShelterInfoCommand;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DogShelterInfoCommandTest {

    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private SheltersService sheltersService;

    private DogShelterInfoCommand dogShelterInfoCommand;

    private final static long DOG_SHELTER_ID = 1;

    @Test
    void executeMethodShouldSendMessage() {
        Long chatId = 123456L;
        dogShelterInfoCommand = new DogShelterInfoCommand(sheltersService, telegramBotClient);

        String info = "FurryFriend Улица Гагарина, 7 89876543210";
        when(sheltersService.showAnimalInfoById(DOG_SHELTER_ID)).thenReturn(info);

        dogShelterInfoCommand.execute(chatId);

        verify(telegramBotClient, times(1)).sendMessage(chatId, info);
        verify(sheltersService).showAnimalInfoById(DOG_SHELTER_ID);
    }
}
