package com.example.petShelter.commandTests.ShelterDogInnerButtons;

import com.example.petShelter.command.ShelterDogInnerButtons.GetAddressOfDogShelterCommand;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetAddressOfDogShelterCommandTest {

    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private SheltersService sheltersService;

    private GetAddressOfDogShelterCommand getAddressOfDogShelterCommand;

    private final static long DOG_SHELTER_ID = 1;

    @Test
    void executeMethodShouldSendMessage() {
        Long chatId = 123456L;
        getAddressOfDogShelterCommand = new GetAddressOfDogShelterCommand(telegramBotClient, sheltersService);

        String address = "Улица Гагарина, 7";
        when(sheltersService.showAddress(DOG_SHELTER_ID)).thenReturn(address);

        getAddressOfDogShelterCommand.execute(chatId);

        verify(telegramBotClient, times(1)).sendMessage(chatId, address);
        verify(sheltersService).showAddress(DOG_SHELTER_ID);
    }
}
