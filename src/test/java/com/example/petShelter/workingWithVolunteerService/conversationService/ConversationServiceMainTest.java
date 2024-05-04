package com.example.petShelter.workingWithVolunteerService.conversationService;

import com.example.petShelter.service.VolunteersService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ConversationServiceMainTest {


    @Mock
    private TelegramBot telegramBot;

    @Mock
    private VolunteersService volunteerService;

    @InjectMocks
    private ConversationServiceMain conversationServiceMain;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testFirstGivingUsersWithAvailableVolunteer() {

        when(telegramBot.execute(any())).thenReturn(null);

        // Мокируем вызовы методов, связанных с поиском и отправкой сообщений
//        when(conversationServiceMain.findRelaxVolunteers()).thenReturn(123456L);
        when(volunteerService.findFreeVolunteer()).thenReturn(null);

        // Вызываем тестируемый метод
        assertThrows(NullPointerException.class, () -> {
            conversationServiceMain.firstGivingUsers(78910L);
        });

//        conversationServiceMain.firstGivingUsers(78910L);

        // Проверяем, что было отправлено сообщение о найденном волонтере
//        verify(telegramBot).execute(new SendMessage(78910L,
//                "Все волонтеры сейчас заняты, напишите чуть позже"));

        verify(telegramBot, times(1)).execute(new SendMessage(78910L, "Все волонтеры сейчас заняты, напишите чуть позже"));

        // Проверяем, что было отправлено сообщение о начале диалога волонтеру
//        verify(telegramBot).execute(new SendMessage(123456L, anyString()));
    }

}
