package com.example.petShelter.commandTests.ShelterCatgInnerButtons;

import com.example.petShelter.command.ShelterCatInnerButtons.GetCatShelterCarPermission;
import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetCatShelterCarPermissionTest {
    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private SheltersService sheltersService;

    private GetCatShelterCarPermission getShelterCarPermission;

    private final static long CAT_SHELTER_ID = 2;

    @Test
    void executeMethodShouldSendMessage() {
        Long chatId = 123456L;
        getShelterCarPermission = new GetCatShelterCarPermission(telegramBotClient, sheltersService);

        String securityContact = "89375050908";
        when(sheltersService.showSecurityContact(CAT_SHELTER_ID)).thenReturn(securityContact);

        getShelterCarPermission.execute(chatId);

        verify(telegramBotClient, times(1)).sendMessage(chatId, securityContact);
        verify(sheltersService).showSecurityContact(CAT_SHELTER_ID);
    }
}
