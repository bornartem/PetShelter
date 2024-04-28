package com.example.petShelter.workingWithVolunteerService;


import com.example.petShelter.model.DailyReports;
import com.example.petShelter.model.Volunteers;
import com.example.petShelter.service.DailyReportService;
import com.example.petShelter.service.TelegramBotClient;
import com.example.petShelter.service.VolunteersService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckReportVolunteer {

    String BEGINNING_MESSAGE = """
            Проверьте отчет от клиента, ответьте одним сообщением!\
            Сообщение начинайте с команды '/ответ на отчет {id}'\
             id - будет вам дан в проверяемом отчете
            то есть ваш ответ должен выглядеть таким образом:
            /ответ на отчет 13892495 Здравствуйте я проверил ваш отчет, все хорошо... и тд и тп""";

    DailyReportService dailyReportService;
    VolunteersService volunteersService;
    TelegramBotClient telegramBotClient;
    TelegramBot telegramBot;

    @Autowired
    CheckReportVolunteer(DailyReportService dailyReportService, VolunteersService volunteersService,
                         TelegramBotClient telegramBotClient,TelegramBot telegramBot) {
        this.dailyReportService = dailyReportService;
        this.volunteersService = volunteersService;
        this.telegramBotClient = telegramBotClient;
        this.telegramBot = telegramBot;
    }

    /**
     * go to every not checking daily reports and give it volunteers
     * for check. it's at 21 o'clock every day. stop when
     * all reports will be checking
     * @throws InterruptedException
     */
    @Scheduled(cron = "20 0 18 * * *")
    public void checkAt21Hour() throws InterruptedException {

        List<DailyReports> reports = dailyReportService.findByNotCheck();
        List<Volunteers> volunteers;
        for (int i = reports.size()-1; i >= 0; ) {
//            wait(1000);

            volunteers = volunteersService.findAllActivity();

            for (Volunteers volunteer : volunteers) {
                if (reports.isEmpty()) {
                    break;
                }
                volunteer.setActivity(false);
                volunteersService.update(volunteer);
                sendReport(volunteer, reports.remove(i));
                i--;
            }

        }

    }

    /**
     * send report to volunteer
     * @param volunteer free volunteer that should check report
     * @param report it's report for checking
     */
    private void sendReport(Volunteers volunteer, DailyReports report) {
        long chatId = volunteer.getChatId();
        telegramBotClient.sendMessage(chatId, BEGINNING_MESSAGE);


        String reportString = "id отчета: " + report.getClientId() +
                "\nЗдоровье:\n" + report.getHealth() +
                "\nАдаптация:\n" + report.getBehavior() +
                "\nОписание рациона:\n" + report.getAnimalMenu() +
                "\nФото:\n";
        telegramBotClient.sendMessage(chatId, reportString);
        String photo = report.getPhotos();
        telegramBot.execute(new SendPhoto(chatId, photo));


        report.setIsCheck(true);
        dailyReportService.update(report);
    }

}
