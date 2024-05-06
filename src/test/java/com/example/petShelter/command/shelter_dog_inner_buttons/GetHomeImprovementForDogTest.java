package com.example.petShelter.command.shelter_dog_inner_buttons;

import com.example.petShelter.command.GetHomeImprovementForDog;
import com.example.petShelter.listener.HomeImprovementForDogMenu;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetHomeImprovementForDogTest {

    @Mock
    private HomeImprovementForDogMenu homeImprovementForDogMenu;

    private GetHomeImprovementForDog getHomeImprovementForDog;

    @Test
    void executeMethodShouldSendMenu() {
        Long chatId = 123456L;
        getHomeImprovementForDog = new GetHomeImprovementForDog(homeImprovementForDogMenu);

        getHomeImprovementForDog.execute(chatId);

        verify(homeImprovementForDogMenu, times(1)).sendMenuMessage(chatId);
    }
}
