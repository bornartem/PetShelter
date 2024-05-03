package com.example.petShelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
/**
 * The class consists of logic of the project, which create "Clients" entity
 *
 * @author bornartem
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity(name = "clients")
public class Clients {

    @ManyToOne
//    @JoinColumn(name="id_volunteer")
    @JsonIgnore
    private Volunteers volunteer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private long id;

    @Column(name = "chat_id", nullable = false)
    private long chatId;

    @Column(name = "name")
    private String name;

    @Column(name = "contact")
    private String contact;

    @OneToMany(mappedBy = "clientId")
    @JsonIgnore
    private List<DailyReports> dailyReports;

    @JsonIgnore
    @OneToOne
    private Animals adoptedAnimal;

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}