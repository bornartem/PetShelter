package com.example.petShelter.command.shelter_dog_inner_buttons;

import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetShelterCarPermissionTest {

    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private SheltersService sheltersService;

    private GetShelterCarPermission getShelterCarPermission;

    private final static long DOG_SHELTER_ID = 1;

    @Test
    void executeMethodShouldSendMessage() {
        Long chatId = 123456L;
        getShelterCarPermission = new GetShelterCarPermission(telegramBotClient, sheltersService);

        String securityContact = "898273729";
        when(sheltersService.showSecurityContact(DOG_SHELTER_ID)).thenReturn(securityContact);

        getShelterCarPermission.execute(chatId);

        verify(telegramBotClient, times(1)).sendMessage(chatId, securityContact);
        verify(sheltersService).showSecurityContact(DOG_SHELTER_ID);
    }
}
