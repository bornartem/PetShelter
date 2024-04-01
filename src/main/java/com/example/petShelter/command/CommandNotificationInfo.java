package com.example.petShelter.command;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import org.springframework.stereotype.Component;
import com.example.petShelter.model.NotificationTask;
import com.example.petShelter.receiver.TelegramBotClient;
import com.example.petShelter.repository.NotificationTaskRepository;
import com.example.petShelter.service.NotificationTaskService;

import java.util.List;
@Component
public class CommandNotificationInfo implements Command{
    private final TelegramBotClient botClient;
    private final NotificationTaskService notificationTaskService;
    private NotificationTaskRepository notificationTaskRepository;

    public CommandNotificationInfo(TelegramBotClient botClient, NotificationTaskService notificationTaskService) {
        this.botClient = botClient;
        this.notificationTaskService = notificationTaskService;
    }

    public void execute(Message message, CallbackQuery callbackQuery) {
        List<NotificationTask> notifications = notificationTaskRepository.getAllNotifications();
        notifications.forEach(notification -> {
            botClient.sendMessage(callbackQuery.message().chat().id(), notification.toString());
        });
    }
}
