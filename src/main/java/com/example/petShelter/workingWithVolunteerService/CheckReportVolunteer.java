package com.example.petShelter.workingWithVolunteerService;


import com.example.petShelter.model.DailyReports;
import com.example.petShelter.model.Volunteers;
import com.example.petShelter.service.DailyReportService;
import com.example.petShelter.service.TelegramBotClient;
import com.example.petShelter.service.VolunteersService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendPhoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckReportVolunteer {

    private static final Logger log = LoggerFactory.getLogger(CheckReportVolunteer.class);
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
    @Scheduled(cron = "10 33 * * * *")
    public void checkAt21Hour() {
        log.info("scheduled at 21 o'clock start work");
        List<DailyReports> reports;
        try {
            reports = dailyReportService.findByNotCheck();
        } catch (NullPointerException e) {
            log.info("error in scheduled 21 hour!");
            return;
        }

        Thread thread = new Thread() {
            @Override
            public void start() {
                volunteerFindHimReport(reports);
            }
        };

        thread.start();
    }



    private void volunteerFindHimReport(List<DailyReports> reports) {

        log.info("volunteerFindHimReport for checking start working. Count reports" +
                "{}", reports.size());
        if (reports.isEmpty()) {
            return;
        }



        List<Volunteers> volunteers;
        for (int i = reports.size()-1; i >= 0; ) {
            log.info("report checking sleeping 2 second");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.info("error sleep in check report volunteer");
            }




            log.info("find free volunteers");
            volunteers = volunteersService.findAllActivity();
            log.info("count free volunteers: {}", volunteers.size());
            if (volunteers.isEmpty()) {
                continue;
            }

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


        String reportString = "id отчета: " + report.getClientId().getChatId() +
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
