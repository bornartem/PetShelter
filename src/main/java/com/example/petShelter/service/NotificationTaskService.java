package com.example.petShelter.service;

import org.postgresql.core.Notification;
import org.springframework.stereotype.Service;
import com.example.petShelter.model.NotificationTask;
import com.example.petShelter.repository.NotificationTaskRepository;

import java.util.List;

@Service
public class NotificationTaskService {

    private final NotificationTaskRepository notificationTaskRepository;

    public NotificationTaskService(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    public void save(NotificationTask notificationTask) {
        notificationTaskRepository.save(notificationTask);
    }
}