package com.example.petShelter.commandTests.ShelterDogInnerButtons;

import com.example.petShelter.command.GetInfoAboutDogShelter;
import com.example.petShelter.listener.InfoAboutDogShelterMenu;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetInfoAboutDogShelterTest {

    @Mock
    private InfoAboutDogShelterMenu infoAboutDogShelterMenu;

    private GetInfoAboutDogShelter getInfoAboutDogShelter;

    @Test
    void executeMethodShouldSendMenu() {
        Long chatId = 123456L;
        getInfoAboutDogShelter = new GetInfoAboutDogShelter(infoAboutDogShelterMenu);

        getInfoAboutDogShelter.execute(chatId);

        verify(infoAboutDogShelterMenu, times(1)).sendMenuMessage(chatId);
    }
}
