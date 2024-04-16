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
@Data
@Entity(name = "animals")
public class Animals {

    @JsonIgnore
    @ManyToOne
//    @JoinColumn(name = "id_shelter")
    private Shelters shelter;

    //    @OneToMany(mappedBy = "daily_report")
    @OneToMany(mappedBy = "animal")
    private List<DailyReports> dailyReport;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "busy_free", nullable = false)
    private Boolean busyFree;
    @Column(name = "date_take")
    private LocalDateTime dateTake;

}