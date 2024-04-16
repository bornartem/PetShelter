package com.example.petShelter.commandTests.ShelterCatgInnerButtons;

import com.example.petShelter.command.GetInfoAboutCatShelter;
import com.example.petShelter.listener.InfoAboutCatShelterMenu;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetInfoAboutCatShelterTest {

    @Mock
    private InfoAboutCatShelterMenu infoAboutCatShelterMenu;

    private GetInfoAboutCatShelter getInfoAboutCatShelter;

    @Test
    void executeMethodShouldSendMenu() {
        Long chatId = 123456L;
        getInfoAboutCatShelter = new GetInfoAboutCatShelter(infoAboutCatShelterMenu);

        getInfoAboutCatShelter.execute(chatId);

        verify(infoAboutCatShelterMenu, times(1)).sendMenuMessage(chatId);
    }
}
