package com.example.petShelter.commandTests.ShelterCatgInnerButtons;

import com.example.petShelter.command.GetInfoAboutCat;
import com.example.petShelter.listener.AllAboutCatMenu;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetInfoAboutCatTest {

    @Mock
    private AllAboutCatMenu allAboutCatMenu;

    private GetInfoAboutCat getInfoAboutCat;

    @Test
    void executeMethodShouldSendMenu() {
        Long chatId = 123456L;
        getInfoAboutCat = new GetInfoAboutCat(allAboutCatMenu);

        getInfoAboutCat.execute(chatId);

        verify(allAboutCatMenu, times(1)).sendMenuMessage(chatId);
    }
}
