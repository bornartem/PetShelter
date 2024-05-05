package com.example.petShelter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * The class consists of logic of the project, which create "Volunteers" entity
 *
 * @author bornartem
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "volunteers")
@JsonIgnoreProperties(value = {"clients"})
public class Volunteers {

    @OneToMany(mappedBy = "volunteer")
    @JsonManagedReference
    private List<Clients> clients;
    @Id
    @Column(name = "id_volunteer")
    private long id;

    @Column(name = "chat_id", nullable = false)
    private long chatId;

    @Column(name = "name")
    private String name;

    @Column(name = "contact")
    private String contact;

    @Column(name = "activity")
    private boolean activity;

    @Override
    public String toString() {
        return "Volunteers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", activity=" + activity +
                ", chatId=" + chatId +
                '}';
    }
}