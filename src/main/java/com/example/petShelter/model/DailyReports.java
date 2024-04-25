package com.example.petShelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    private LocalDateTime dateTime;

    /**
     *the field shows the health and the general animal's  welfare
     */
    @Column(name = "well")
    private String health;

    /**
     *the field shows the change in animal's  behavior
     */
    @Column(name = "reaction")
    private String behavior;

    @Column(name = "animal_menu")
    private String animalMenu;

    @Lob
    @Column(name = "foto_animal")
    private List<Byte> photos;

    /**
     *the field shows the status once daily report is checked by volunteers
     */
    @Column(name = "is_check", nullable = false)
    private Boolean isCheck;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients client;


    private DailyReportsEnum currentStep;

    public void nextStep() {
        if(currentStep == null)
            currentStep = DailyReportsEnum.PHOTO;
        else if(currentStep == DailyReportsEnum.PHOTO)
            currentStep = DailyReportsEnum.HEALTH;
        else if(currentStep == DailyReportsEnum.HEALTH)
            currentStep = DailyReportsEnum.ANIMAL_MENU;
        else if(currentStep == DailyReportsEnum.ANIMAL_MENU)
            currentStep = DailyReportsEnum.REACTION;
        else if(currentStep == DailyReportsEnum.REACTION)
            currentStep = null;
    }

    public boolean isCheck() {
        return this.isCheck;
    }
}
