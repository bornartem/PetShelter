package com.example.petShelter.workingWithVolunteerService;

import com.example.petShelter.model.Clients;
import com.example.petShelter.model.DailyReports;
import com.example.petShelter.model.Volunteers;
import com.example.petShelter.service.DailyReportService;
import com.example.petShelter.service.TelegramBotClient;
import com.example.petShelter.service.VolunteersService;
import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CheckReportVolunteerTest {

    @Mock
    DailyReportService dailyReportService;
    @Mock
    VolunteersService volunteersService;
    @Mock
    TelegramBotClient telegramBotClient;
    @Mock
    TelegramBot telegramBot;
    @Mock
    Volunteers volunteer;
    @Mock
    DailyReports dailyReports;

    @InjectMocks
    CheckReportVolunteer checkReportVolunteer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reports = new ArrayList<>();
        DailyReports dailyReports = new DailyReports();
        dailyReports.setIsCheck(false);

        Clients clients = new Clients();
        clients.setChatId(34543454L);
        clients.setId(9383);
        dailyReports.setClientId(clients);
        dailyReports.setPhotos("LSDFSPAKDFJ389ASFLKJA");
        reports.add(dailyReports);

        volunteers = new Volunteers();
        volunteers.setId(1);
        volunteers.setName("Pavel");
    }

    @Test
    public void testCheckAt21HourNullPointerEx() {

        when(dailyReportService.findByNotCheck()).thenThrow(NullPointerException.class);

        checkReportVolunteer.checkAt21Hour();


    }

    public List<DailyReports> reports;
    public Volunteers volunteers;

    @Test
    public void testVolunteerFindHimReport() {
        when(dailyReportService.findByNotCheck()).thenReturn(reports);
        when(volunteersService.findAllActivity()).thenReturn(new ArrayList<>(Collections.singleton(volunteers)));
        when(volunteersService.update(any())).thenReturn(null);
        when(dailyReportService.update(any())).thenReturn(null);
//        when(volunteer.getChatId()).thenReturn(1234567L);
//        when(dailyReports.getClientId()).thenReturn(new Clients());

        checkReportVolunteer.checkAt21Hour();
        long chatId = volunteer.getChatId();


//        volunteers.setActivity(false);
        verify(volunteersService).update(any());
        verify(dailyReportService).update(any());
        verify(volunteersService).findAllActivity();
        verify(telegramBotClient).sendMessage(chatId, checkReportVolunteer.BEGINNING_MESSAGE);

    }


}
