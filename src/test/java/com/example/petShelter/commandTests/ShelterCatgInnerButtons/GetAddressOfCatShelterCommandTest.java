package com.example.petShelter.commandTests.ShelterCatgInnerButtons;

import com.example.petShelter.command.ShelterCatInnerButtons.GetAddressOfCatShelterCommand;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetAddressOfCatShelterCommandTest {

    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private SheltersService sheltersService;

    private GetAddressOfCatShelterCommand getAddressOfCatShelterCommand;

    private final static long CAT_SHELTER_ID = 2;

    @Test
    void executeMethodShouldSendMessage() {
        Long chatId = 123456L;
        getAddressOfCatShelterCommand = new GetAddressOfCatShelterCommand(telegramBotClient, sheltersService);

        String address = "Улица Ленина, 28";
        when(sheltersService.showAddress(CAT_SHELTER_ID)).thenReturn(address);

        getAddressOfCatShelterCommand.execute(chatId);

        verify(telegramBotClient, times(1)).sendMessage(chatId, address);
        verify(sheltersService).showAddress(CAT_SHELTER_ID);
    }
}
