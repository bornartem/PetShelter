package com.example.petShelter.commandTests.ShelterCatgInnerButtons;

import com.example.petShelter.command.GetHomeImprovementForCat;
import com.example.petShelter.listener.HomeImprovementForCatMenu;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetHomeImprovementForCatTest {

    @Mock
    private HomeImprovementForCatMenu homeImprovementForCatMenu;

    private GetHomeImprovementForCat getHomeImprovementForCat;

    @Test
    void executeMethodShouldSendMenu() {
        Long chatId = 123456L;
        getHomeImprovementForCat = new GetHomeImprovementForCat(homeImprovementForCatMenu);

        getHomeImprovementForCat.execute(chatId);

        verify(homeImprovementForCatMenu, times(1)).sendMenuMessage(chatId);
    }
}
