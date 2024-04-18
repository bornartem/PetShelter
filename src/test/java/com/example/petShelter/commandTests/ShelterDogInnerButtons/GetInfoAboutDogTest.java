package com.example.petShelter.commandTests.ShelterDogInnerButtons;

import com.example.petShelter.command.GetInfoAboutDog;
import com.example.petShelter.listener.AllAboutDogMenu;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetInfoAboutDogTest {

    @Mock
    private AllAboutDogMenu allAboutDogMenu;

    private GetInfoAboutDog getInfoAboutDog;

    @Test
    void executeMethodShouldSendMenu() {
        Long chatId = 123456L;
        getInfoAboutDog = new GetInfoAboutDog(allAboutDogMenu);

        getInfoAboutDog.execute(chatId);

        verify(allAboutDogMenu, times(1)).sendMenuMessage(chatId);
    }
}
