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

//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "id_animal")
//    private Animals animal;

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
    private String well;

    /**
     *the field shows the change in animal's  behavior
     */
    @Column(name = "reaction")
    private String reaction;

    @Lob
    @Column(name = "foto_animal")
    private byte[] data;

    /**
     *the field shows the status once daily report is checked by volunteers
     */
    @Column(name = "is_check", nullable = false)
    private Boolean is_check;

}