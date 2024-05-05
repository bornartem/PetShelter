package com.example.petShelter.command.dog_menu_buttons;

import com.example.petShelter.configuration.TelegramBotConfiguration;
import com.example.petShelter.service.TelegramBotClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetInfoAboutDogMeetingTest {
    @Mock
    private TelegramBotClient telegramBotClient;

    @Mock
    private TelegramBotConfiguration telegramBotConfiguration;

    private GetInfoAboutDogMeeting getInfoAboutDogMeeting;

    @Test
    void testExecute() throws Exception {
        Long chatId = 12345L;
        getInfoAboutDogMeeting = new GetInfoAboutDogMeeting(telegramBotClient,
                telegramBotConfiguration.meetingFile());

        getInfoAboutDogMeeting.execute(chatId);

        verify(telegramBotClient).sendMessage(chatId, telegramBotConfiguration.meetingFile());
    }
}
