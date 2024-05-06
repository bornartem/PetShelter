package com.example.petShelter.repository;

import com.example.petShelter.model.ConversationPeople;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationPeopleRepository extends JpaRepository<ConversationPeople, Long> {
    void deleteByChatId(Long chatId);

    ConversationPeople findByChatId(Long chatId);
}
