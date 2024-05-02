package com.example.petShelter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The class consists of logic of the project, which create "DailyReports" entity
 *
 * @author Khilola Kushbakova
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "daily_report")
public class DailyReports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_daily_report")
    private long idDailyReport;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime localDateTime;

    /**
     * the field shows the health and the general animal's  welfare
     */
    @Column(name = "animal_menu")
    private String animalMenu;

    @Column(name = "well")
    private String health;

    /**
     * the field shows the change in animal's  behavior
     */
    @Column(name = "reaction")
    private String behavior;

    @Column(name = "photo_animal")
    private String photos;

    @Column(name = "is_check", nullable = false)
    private Boolean isCheck;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Clients clientId;
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animals animals;
}
