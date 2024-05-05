package com.example.petShelter.service;

import com.example.petShelter.exception.NotFoundInDB;
import com.example.petShelter.model.DailyReports;
import com.example.petShelter.repository.DailyReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


/**
 * Service class which consist of business logic of Reports from users
 *
 * @author Khilola Kushbakova
 */
@Slf4j
@Service
public class DailyReportService {

    private final DailyReportRepository dailyReportRepository;
    private final TelegramBotClient telegramBotClient;

    @Autowired
    public DailyReportService(DailyReportRepository dailyReportRepository, TelegramBotClient telegramBotClient) {
        this.dailyReportRepository = dailyReportRepository;
        this.telegramBotClient = telegramBotClient;
    }

    public static final String REMINDER_ONE_DAY = "Уважаемый усыновитель, Вы не прислали вчера отчет. " +
            "Пожалуйста, не забывайте присылать каждый день ";
    public static final String REMINDER_TWO_DAYS = "Уважаемый усыновитель, Мы не получали от Вас отчет" +
            " более 2 дней. С Вами свяжется волонтер в ближайшее время";
    public static final String PASS_REPORT_PERIOD = "Поздравляем, Вы прошли испытательный период";

    public static final String PLUS_REPORT_PERIOD = "Уважаемый усыновитель, в течении 30 дней мы  " +
            "не получили необходимого количества отчетов, поэтому Ваш срок увеличивается на 14 дней";
    public static final String REJECT = "Уважаемый усыновитель, к сожалению Вы не прошли испытательный " +
            "срок и мы вынуждены забрать нашего питомца. Ожидайте звонка от волонтера для дальнейших инструкций";
    public static final Integer COUNT_REPORTS_FOR_PASS = 1;

    /**
     * Creates a new daily report.
     *
     * @param dailyReports The DailyReports object to be created.
     * @return The created DailyReports object.
     */
    public DailyReports save(DailyReports dailyReports) {
        return dailyReportRepository.save(dailyReports);
    }

    /**
     * Find a daily report by its ID.
     *
     * @param id The ID of the daily report to find.
     * @return The DailyReports object if found, otherwise null.
     */
    public DailyReports findDailyReportById(long id) {
        return dailyReportRepository.findById(id).orElse(null);
    }

    /**
     * Deletes a daily report by its ID.
     *
     * @param id The ID of the daily report to delete.
     */
    public void deleteDailyReportById(long id) {
        if (dailyReportRepository.existsById(id)) {
            dailyReportRepository.deleteById(id);
            log.info("Deleted daily report with id = {}", id);
        } else {
            log.error("There is no report with id = {}", id);
            throw new NotFoundInDB("Daily report not found");
        }
    }

    /**
     * Find daily reports by not checked by boolean.
     *
     * @return not checked reports
     */
    public List<DailyReports> findByNotCheck() {
        return dailyReportRepository.findByIsCheckFalse();
    }

    /**
     * Update daily reports by not checked by boolean.
     *
     * @return all reports
     */
    public DailyReports update(DailyReports report) {
        return dailyReportRepository.save(report);
    }

    /**
     * Get all daily reports
     *
     * @return all reports
     */
    public List<DailyReports> getAll() {
        return dailyReportRepository.findAll();
    }

    /**
     * Check daily reports by scheduled
     *
     * @return reminder, reject or congrats if client pass test period
     */
    @Scheduled(cron = "50 11 20 * * *")
    public void reportController() {
        List<DailyReports> dailyReports = dailyReportRepository.findAll();
        int reportCount = dailyReportRepository.getCountReports();

        dailyReports.forEach(reports -> {
            LocalDateTime currentDay = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
            LocalDateTime lastDay = reports.getLocalDateTime().truncatedTo(ChronoUnit.DAYS);

            if (lastDay.plusDays(1).equals(currentDay)) {
                telegramBotClient.sendMessage(reports.getClientId().getChatId(), REMINDER_ONE_DAY);
            } else if (lastDay.plusDays(2).equals(currentDay)) {
                telegramBotClient.sendMessage(reports.getClientId().getChatId(), REMINDER_TWO_DAYS);
            } else if (lastDay.plusDays(3).equals(currentDay)) {
                telegramBotClient.sendMessage(reports.getClientId().getChatId(), REJECT);
            } else if (lastDay.equals(currentDay) && reportCount == COUNT_REPORTS_FOR_PASS) {
                telegramBotClient.sendMessage(reports.getClientId().getChatId(), PASS_REPORT_PERIOD);
            } else {
                telegramBotClient.sendMessage(reports.getClientId().getChatId(), PLUS_REPORT_PERIOD);
            }
        });
    }
}




