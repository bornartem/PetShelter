package com.example.petShelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.petShelter.model.NotificationTask;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {

    List<NotificationTask> findAllByLocalDateTime(LocalDateTime localDateTime);

    List<NotificationTask> getAllNotifications();
}

