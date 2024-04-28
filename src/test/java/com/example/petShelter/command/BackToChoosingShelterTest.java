package com.example.petShelter.command;

import com.example.petShelter.listener.ChoosingShelterMenu;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BackToChoosingShelterTest {

    @Mock
    private ChoosingShelterMenu choosingShelterMenu;

    private BackToChoosingShelter backToChoosingShelter;

    @Test
    void executeMethodShouldSendMenu() {
        Long chatId = 123456L;
        backToChoosingShelter = new BackToChoosingShelter(choosingShelterMenu);

        backToChoosingShelter.execute(chatId);

        verify(choosingShelterMenu, times(1)).sendMenuMessage(chatId);
    }
}
