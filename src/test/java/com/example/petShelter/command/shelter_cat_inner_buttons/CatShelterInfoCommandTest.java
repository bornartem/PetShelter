package com.example.petShelter.command.shelter_cat_inner_buttons;

import com.example.petShelter.service.SheltersService;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatShelterInfoCommandTest {

    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private SheltersService sheltersService;

    private CatShelterInfoCommand catShelterInfoCommand;

    private final static long CAT_SHELTER_ID = 2;

    @Test
    void executeMethodShouldSendMessage() {
        Long chatId = 123456L;
        catShelterInfoCommand = new CatShelterInfoCommand(sheltersService, telegramBotClient);

        String info = "FurryFriendCat Улица Ленина, 28 89546732890";
        when(sheltersService.showAnimalInfoById(CAT_SHELTER_ID)).thenReturn(info);

        catShelterInfoCommand.execute(chatId);

        verify(telegramBotClient, times(1)).sendMessage(chatId, info);
        verify(sheltersService).showAnimalInfoById(CAT_SHELTER_ID);
    }
}
