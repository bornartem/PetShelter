package com.example.petShelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    @JsonIgnore
    @JoinColumn(name = "id_client")
    private Clients clientId;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "animal_id")
    private Animals animals;

    @Override
    public String toString() {
        return "DailyReports{" +
                "idDailyReport=" + idDailyReport +
                ", localDateTime=" + localDateTime +
                ", animalMenu='" + animalMenu + '\'' +
                ", health='" + health + '\'' +
                ", behavior='" + behavior + '\'' +
                ", photos='" + photos + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }
}
