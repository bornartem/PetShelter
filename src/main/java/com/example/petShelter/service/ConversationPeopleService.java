package com.example.petShelter.service;

import com.example.petShelter.model.ConversationPeople;
import com.example.petShelter.repository.ConversationPeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationPeopleService {
    private final ConversationPeopleRepository conversationRepository;

    @Autowired
    public ConversationPeopleService(ConversationPeopleRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public void savePeople(Long chatId, Long chatId2, boolean isVolunteer) {
        conversationRepository.save(new ConversationPeople(Integer.MAX_VALUE, chatId, chatId2, isVolunteer));
    }

    public void deletePeople(Long chatId) {
        conversationRepository.deleteByChatId(chatId);
    }

    public ConversationPeople findByChatId(Long chatId) {
        return conversationRepository.findByChatId(chatId);
    }

    public void addPeople(Long clientId, Long volId) {
        conversationRepository.save(new ConversationPeople(Integer.MAX_VALUE, clientId, volId, false));
        conversationRepository.save(new ConversationPeople(Integer.MAX_VALUE,  volId, clientId, true));
    }
}
