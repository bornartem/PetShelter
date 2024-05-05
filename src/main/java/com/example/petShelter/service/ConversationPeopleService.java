package com.example.petShelter.service;

import com.example.petShelter.model.ConversationPeople;
import com.example.petShelter.repository.ConversationPeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
/**
 * The class consists of logic of the project, which has
 * the methods  to conversation between client and volunteer
 *
 * @author bornartem
 */
@Service
public class ConversationPeopleService {
    private final ConversationPeopleRepository conversationRepository;

    @Autowired
    public ConversationPeopleService(ConversationPeopleRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    /**
     * create conversation in db
     * called method of repository {@link JpaRepository#save(Object)}
     *
     * @return create and save conversation in db
     */
    public void savePeople(Long chatId, Long chatId2, boolean isVolunteer) {
        conversationRepository.save(new ConversationPeople(Integer.MAX_VALUE, chatId, chatId2, isVolunteer));
    }

    /**
     * delete conversation by chatId in db
     * called method of repository {@link JpaRepository#delete(Object)} (Object)}
     *
     * @param chatId identifier of conversation, can't be null
     * @return delete conversation from db by id
     */
    public void deletePeople(Long chatId) {
        conversationRepository.delete(conversationRepository.findByChatId(chatId));
    }

    /**
     * find conversation by chatId in db
     * called method of repository {@link JpaRepository#findById(Object)}
     *
     * @param chatId identifier of conversation, can't be null
     * @return find conversation from db by chatId
     */
    public ConversationPeople findByChatId(Long chatId) {
        return conversationRepository.findByChatId(chatId);
    }

    /**
     * add people in db
     * called method of repository {@link JpaRepository#save(Object)}
     *
     * @return add and save people in db
     */
    public void addPeople(Long clientId, Long volId) {
        conversationRepository.save(new ConversationPeople(Integer.MAX_VALUE, clientId, volId, false));
        conversationRepository.save(new ConversationPeople(Integer.MAX_VALUE,  volId, clientId, true));
    }
}
