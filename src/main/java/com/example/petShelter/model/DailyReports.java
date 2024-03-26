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
    private Animals animal;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_valunteer")
    private Valunteers valunteer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDailyReport;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "well", nullable = false)
    private String well;

    @Column(name = "reaction", nullable = false)
    private String reaction;

    @Lob
    @Column(name = "foto_animal", nullable = false)
    private byte[] data;



}
