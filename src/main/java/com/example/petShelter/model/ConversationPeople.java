package com.example.petShelter.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * The class contains people who in conversation
 *
 * @author danil
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "conversation_people")
public class ConversationPeople {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "chat_id", nullable = false)
    private long chatId;
    @Column(name = "opponent_chat_id", nullable = false)
    private long opponentChatId;

    @Column(name = "is_volunteer", nullable = false)
    private Boolean isVolunteer;

}