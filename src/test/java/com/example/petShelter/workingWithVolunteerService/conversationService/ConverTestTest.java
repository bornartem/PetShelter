package com.example.petShelter.workingWithVolunteerService.conversationService;

import com.example.petShelter.exception.NotFoundInDB;
import com.example.petShelter.model.Clients;
import com.example.petShelter.model.Volunteers;
import com.example.petShelter.service.ClientsService;
import com.example.petShelter.service.ConversationPeopleService;
import com.example.petShelter.service.TelegramBotClient;
import com.example.petShelter.service.VolunteersService;
import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

public class ConverTestTest {

    @Mock
    private VolunteersService volunteersService;

    @Mock
    private ClientsService clientsService;

    @Mock
    private TelegramBot telegramBot;

    @Spy
    private TelegramBotClient telegramBotClient;

    @Mock
    private ConversationPeopleService conversationPeopleService;

    @InjectMocks
    private ConversationServiceMain conversationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFirstGivingUsersVolunteerFound() throws NotFoundInDB {
        // Arrange
        Long clientChatId = 123L;
        Long volunteerChatId = 456L;
        Volunteers volunteers = new Volunteers();
        volunteers.setChatId(volunteerChatId);
        when(volunteersService.findFreeVolunteer()).thenReturn(volunteers);

        // Act
        conversationService.firstGivingUsers(clientChatId);

        // Assert
        verify(telegramBotClient).sendMessage(clientChatId, ConstantsSendMessageInConv.WE_FIND_VOLUNTEER);
        verify(volunteersService).inactiveVolunteerByChatId(volunteerChatId);
        verify(conversationPeopleService).addPeople(clientChatId, volunteerChatId);
        verify(telegramBotClient).sendMessage(volunteerChatId, ConstantsSendMessageInConv.HI_MESSAGE);
    }

    @Test
    public void testFirstGivingUsersVolunteerNotFound() throws NotFoundInDB {
        // Arrange
        Long clientChatId = 123L;

        when(volunteersService.findFreeVolunteer()).thenThrow(new NotFoundInDB("not"));

        // Act
        conversationService.firstGivingUsers(clientChatId);

        // Assert
        verify(telegramBotClient).sendMessage(clientChatId, ConstantsSendMessageInConv.WE_FIND_VOLUNTEER);
        verify(telegramBotClient).sendMessage(clientChatId, "Все волонтеры сейчас заняты, напишите чуть позже");
    }


    @Test
    public void testReportConversation() {
        String VOLUNTEER_MESSAGE_LATE = "Вы успешно проверили отчет от пользователя. Ваша активность - " +
                "false. Если вы готовы принимать клиента/проверять следующий отчет нажмите /changeActivity";

        Long clientChatId = 345434L;
        Long clientChatIdNormal = 23433443L;
        String textError = "/ответ на отчет " + clientChatId + " Все хорошо!";
        String textNormal = "/ответ на отчет " + clientChatIdNormal + " Все хорошо!";

        Long volChatId = 2345L;
        Clients clients = new Clients();

        when(clientsService.findFirstByChatId(clientChatId)).thenReturn(null);

        conversationService.reportConversation(volChatId, textError);

        verify(telegramBotClient).sendMessage(volChatId, "проверьте правильность введенного id");


        when(clientsService.findFirstByChatId(clientChatIdNormal)).thenReturn(clients);

        conversationService.reportConversation(volChatId, textNormal);



        verify(telegramBotClient).sendMessage(clientChatIdNormal, "Все хорошо!");
        verify(telegramBotClient).sendMessage(volChatId, VOLUNTEER_MESSAGE_LATE);


    }

}