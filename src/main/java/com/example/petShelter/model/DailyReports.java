package com.example.petShelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity(name = "daily_report")
public class DailyReports {

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "id_valunteer")
    private Animals animal;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "id_animal")
    private Valunteers valunteer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDailyReport;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "well")
    private String well;

    @Column(name = "reaction")
    private String reaction;

    @Lob
    @Column(name = "foto_animal")
    private byte[] data;
}
