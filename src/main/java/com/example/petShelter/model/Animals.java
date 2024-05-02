package com.example.petShelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The class consists of logic of the project, which create "Animals" entity
 *
 * @author Khilola Kushbakova
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Animals {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private long id;
    @ManyToOne
    @JoinColumn(name = "shelters_id")
    @JsonIgnore
    private Shelters shelters;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private String age;
    @Column(name = "type")
    private String type;
    @Column(name = "busy_free", nullable = false)
    private Boolean busyFree;
    @Column(name = "date_take")
    private LocalDateTime dateTake;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients clients;

    @OneToMany(mappedBy = "animals")
    private List<DailyReports> dailyReports;

}