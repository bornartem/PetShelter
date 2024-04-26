package com.example.petShelter.command;

import com.example.petShelter.model.Clients;
import com.example.petShelter.model.DailyReports;
import com.example.petShelter.service.ClientsService;
import com.example.petShelter.service.TelegramBotClient;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Khilola Kushbakova
 */

@Slf4j
@Component("/reportFromUsers")
public class ReportCommands implements Command {
    private Map<Long, Clients> usersIdUserMap;
    private final Map<Long, DailyReports> reports = new HashMap<>();


    private final ClientsService clientsService;
    private final TelegramBotClient telegramBotClient;
    private final TelegramBot telegramBot;

    public ReportCommands(ClientsService clientsService, TelegramBotClient telegramBotClient, TelegramBot telegramBot) {
        this.clientsService = clientsService;
        this.telegramBotClient = telegramBotClient;
        this.telegramBot = telegramBot;
    }


    /**
     * The method generates a report (fills in the DailyReport object) from the messages
     * sent by the user step by step
     */
    @Override
    public void execute(Long chatId, List<Update> updatesList) {
    }

    private void recordReport(Update update) {

        long chatId = update.message().chat().id();
        String messageString = "";
        DailyReports currentReport = reports.get(chatId);

        if (currentReport == null) {
            telegramBotClient.sendMessage(chatId, "Отправка отчета не активна. Выберите опцию 'Отправить отчет");
            return;
        }

        if (currentReport.isCheck()) {
            String currentText = update.message().text();
            switch (currentReport.getCurrentStep()) {
                case PHOTO:
                    if (update.message().photo() == null) {
                        messageString = " Вам нужно  прикрепить фото";
                    } else {
                        currentReport.setPhotos(List.of());
                        currentReport.nextStep();
                        messageString = "Напишите описание текущего состояния здоровья животного";
                    }
                    break;

                case HEALTH:
                    if (currentText == null) {
                        messageString = "Вы должны написать описание текущего состояния здоровья животного";
                    } else if (currentText.length() <= 50) {
                        messageString = "Ваше описание здоровья не полное. Напишите больше";
                    } else {
                        currentReport.setHealth(currentText);
                        currentReport.nextStep();
                        messageString = "Напишите описание рациона животного";
                    }
                    break;

                case ANIMAL_MENU:
                    if (currentText == null) {
                        messageString = "Вы должны написать рацион";
                    } else if (currentText.length() <= 70) {
                        messageString = "Ваше описание рациона не полное. Напишите больше";
                    } else {
                        currentReport.setAnimalMenu(currentText);
                        currentReport.nextStep();
                        messageString = "Опишите, как животное адаптируется";
                    }
                    break;
                case REACTION:
                    if (currentText == null) {
                        messageString = "Вы должны описать, как животное адаптируется, опишите, если животное ведет себя по-другому";
                    } else if (currentText.length() <= 30) {
                        messageString = "Ваше описание адаптации не полное. Напишите больше";
                    } else {
                        currentReport.setBehavior(currentText);
                        currentReport.nextStep();
                        messageString = "Опишите, если животное ведет себя по-другому";
                    }
                    break;
                default:
                    messageString = "Что-то пошло не так. Попробуйте снова. Нажмите кнопку 'Отправить отчет'";
            }
            if (!messageString.isEmpty()) {
                telegramBotClient.sendMessage(chatId, messageString);
            }
        }
    }
    /**
     * Method to save Reposts.
     */

    private void storeReport(DailyReports newReport) {
        long chatId = newReport.getClientId().getId();
        Clients client = clientsService.read(chatId);
        client.getDailyReports().add(newReport);
        clientsService.create(client);

    }
    /**
     * Sends a message to the user indicating that they have filled out the report incorrectly.
     */
    private void badFilledReport(Update update) {

        String[] data = update.callbackQuery().data().split(":");

        Long idUser = Long.valueOf(data[1]);
        Long chatId = update.callbackQuery().message().chat().id();

        Integer messageId = update.callbackQuery().message().messageId();

        Clients user = usersIdUserMap.get(idUser);

        telegramBot.execute(new SendMessage(chatId, "Пользователь " + user.getName() + " был предупрежден о том, что отчет " +
                "плохо заполнен"));

        telegramBot.execute(new SendMessage(user.getChatId(), "Уважаемый приемный родитель, мы заметили, что " +
                "вы не заполняете отчет достаточно детально." + "\n" + "Пожалуйста, отнеситесь к этой деятельности более ответственно." + "\n" +
                "В противном случае, волонтеры приюта будут вынуждены лично проверить условия благополучия животного."));
    }


    /**
     * This method processes daily reports and notifies the client about extending
     * the trial period if necessary.
     */
    public void processDailyReport(List<DailyReports> dailyReports, Clients client, TelegramBot bot) {
        for (DailyReports report : dailyReports) {
            LocalDateTime currentDate = LocalDateTime.now();

            if (report.isCheck() && report.getDateTime().plusDays(14).isBefore(currentDate)) {
                notifyExtendedTrialPeriod(bot, client.getChatId(), 14);
            }
            if (report.isCheck() && report.getDateTime().plusDays(30).isBefore(currentDate)) {
                notifyExtendedTrialPeriod(bot, client.getChatId(), 30);
            }
        }
    }

    public void congratulateAdopter(TelegramBot bot, long chatId) {
        SendMessage message = new SendMessage(chatId,
                "Поздравляем с прохождением испытательного срока! Мы рады объявить, " +
                        "что вы теперь официальным владельцем вашего усыновленного животного.");
        SendResponse response = bot.execute(message);
    }

    public void notifyExtendedTrialPeriod(TelegramBot bot, long chatId, int days) {
        SendMessage message = new SendMessage(chatId, "Ваш испытательный срок был продлен на " + days +
                " дней. Пожалуйста, продолжайте заботиться и любить вашего усыновленного животного.");
        SendResponse response = bot.execute(message);
    }

    public void notifyFailedTrialPeriod(TelegramBot bot, long chatId) {
        SendMessage message = new SendMessage(chatId, "Мы сожалеем сообщить вам, что вы не прошли " +
                "испытательный срок. Пожалуйста, следуйте предоставленным инструкциям для продолжения.");
        bot.execute(message);
    }

    public void requestVolunteerAssistance(TelegramBot bot, long chatId) {
        SendMessage message = new SendMessage(chatId, "Похоже, что я не могу помочь вам в данный момент. " +
                "Хотели бы вы запросить помощь у волонтера?");
        bot.execute(message);
    }



}
