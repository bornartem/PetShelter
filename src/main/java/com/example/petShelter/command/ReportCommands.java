package com.example.petShelter.command;

import com.example.petShelter.model.Animals;
import com.example.petShelter.model.Clients;
import com.example.petShelter.model.DailyReports;
import com.example.petShelter.service.ClientsService;
import com.example.petShelter.service.DailyReportService;
import com.example.petShelter.service.TelegramBotClient;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.response.GetFileResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Khilola Kushbakova
 */

@Slf4j
@Component("/reportFromUsers")
public class ReportCommands implements Command {


    private final ClientsService clientsService;
    private final TelegramBotClient telegramBotClient;
    private final TelegramBot telegramBot;
    private final DailyReportService dailyReportService;

    public ReportCommands(ClientsService clientsService, TelegramBotClient telegramBotClient, TelegramBot telegramBot, DailyReportService dailyReportService) {
        this.clientsService = clientsService;
        this.telegramBotClient = telegramBotClient;
        this.telegramBot = telegramBot;
        this.dailyReportService = dailyReportService;
    }

    private static final String MESSAGE = """
            (ID животного:)(\\s+)(\\d+)(;|;\\s+)
            (Рацион:)(\\s+)([А-я\\d\\s.,!?:]+)(;|;\\s+)
            (Здоровье:)(\\s+)([А-я\\d\\s.,!?:]+)(;|;\\s+)
            (Поведение:)(\\s+)([А-я\\d\\s.,!?:]+)(\\.|\\.\\s+)""";
    private static final String EXAMPLE_REPORT = """
            ID животного: 1;
            Рацион: Ваш текст;
            Здоровье: Ваш текст;
            Поведение: Ваш текст.""";

    private static final String INFO_REPORT = """
             Для отчета нужна следующая информация:
             Фото животного
             ID животного
             Рацион
             Общее самочувствие и привыкание к новому месту
             Изменение в поведении: отказ от старых привычек, приобретение новых
             Скопируйте следующий пример. Не забудьте прикрепить фото!""";

    @Override
    public void execute(Long chatId) {
        try {
            telegramBotClient.sendMessage(chatId, INFO_REPORT);

            telegramBotClient.sendMessage(chatId, EXAMPLE_REPORT);
        } catch (Exception e) {
            log.error("daily report warning");
        }
    }

    public void saveReport(Message message) {
        Long chatId = message.chat().id();
        String text = message.caption();
        Boolean isCheck = false;

        Pattern pattern = Pattern.compile(MESSAGE);
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            int id = Integer.parseInt(matcher.group(3));
            String animalMenu = matcher.group(7);
            String health = matcher.group(11);
            String behavior = matcher.group(15);

            GetFile getFileRequest = new GetFile(message.photo()[1].fileId());
            GetFileResponse getFileResponse = telegramBot.execute(getFileRequest);
            try {
//                File file = getFileResponse.file();
//                file.fileSize();
//                String fullPathPhoto = file.filePath();
                LocalDateTime dateTime = LocalDateTime.now();

                Clients clients = clientsService.findFirstByChatId(chatId);
                Animals animals = new Animals();
                animals.setId(id);

                DailyReports dailyReports = new DailyReports();
                dailyReports.setLocalDateTime(dateTime);
                dailyReports.setAnimalMenu(animalMenu);
                dailyReports.setHealth(health);
                dailyReports.setBehavior(behavior);
                dailyReports.setPhotos(message.photo()[1].fileId());
                dailyReports.setIsCheck(isCheck);
                dailyReports.setAnimals(animals);
                dailyReports.setClientId(clients);

                dailyReportService.save(dailyReports);
                telegramBotClient.sendMessage(chatId, "Отчет успешно отправлен");
            } catch (Exception e) {
                telegramBotClient.sendMessage(chatId, "Загрузка не удалась, убедитесь, что отчет заполнен правильно");
            }
        }
    }
}
