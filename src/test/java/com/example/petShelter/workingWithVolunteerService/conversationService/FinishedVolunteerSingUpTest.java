package com.example.petShelter.workingWithVolunteerService.conversationService;

import com.example.petShelter.model.Volunteers;
import com.example.petShelter.service.TelegramBotClient;
import com.example.petShelter.service.VolunteersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FinishedVolunteerSingUpTest {
    @Mock
    VolunteersService volunteersService;
    @Mock
    TelegramBotClient telegramBotClient;

    @InjectMocks
    FinishedVolunteerSingUp finishedVolunteerSingUp;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSingUp() {
        Volunteers volunteer = new Volunteers();
        Long chatId = 3434L;
        String text = "dsfkdsljsd";
        String name = "Ivan";
        String contact = "postAdress@gmail.com";
        String textNormal = name + ": " + contact;
        volunteer.setChatId(chatId);
        volunteer.setId(1L);

        when(volunteersService.update(any())).thenReturn(volunteer);
        String message = "Вы зарегистрированы в качестве волонтера, с данными:\n" +
                volunteer.getId() + name + contact +
                " Сейчас вы не в активном поиске клиентов нажмите /changeActivity" +
                " чтобы изменить активность.\n\n" +
                "(Если введенные данные необходимо изменить введите данные еще раз, " +
                "в том же формате - \"имя: контактное инфо\")";
        finishedVolunteerSingUp.singUp(chatId, text, volunteer);
        finishedVolunteerSingUp.singUp(chatId, textNormal, volunteer);


        verify(telegramBotClient).sendMessage(volunteer.getChatId(), "повторите попытку");
        verify(telegramBotClient).sendMessage(volunteer.getChatId(), message);

    }

    @Test
    public void testEditVolunteerData() {
        Volunteers volunteer = new Volunteers();
        Long chatId = 3434L;

        String name = "Ivan";
        String contact = "postAdress@gmail.com";
        String textNormal = name + ": " + contact;
        volunteer.setName(name);
        volunteer.setContact(contact);
        volunteer.setChatId(chatId);
        volunteer.setId(1L);

        when(volunteersService.update(any())).thenReturn(volunteer);

        finishedVolunteerSingUp.singUp(chatId, textNormal, volunteer);

        String message = "Вы изменили свои данные, теперь они выглядят так:\n" +
                volunteer.getId() + name + contact +
                "\nСейчас вы не в активном поиске клиентов нажмите /changeActivity" +
                " чтобы изменить активность.\n\n" +
                "(Если введенные данные необходимо изменить введите данные еще раз, " +
                "в том же формате - \"имя: контактное инфо\")";


        verify(telegramBotClient).sendMessage(chatId, message);
    }

}

