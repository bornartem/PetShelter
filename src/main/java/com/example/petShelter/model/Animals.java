package com.example.petShelter.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "animals")

public class Animals {
    @Id
    @Column(name = "shelter_id", nullable = false)
    private long shelterId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id", nullable = false)
    private long animalId;

    @Column(name = "animal_name", nullable = false)
    private String animalName;

    @Column(name = "animal_type", nullable = false)
    private String animalType;

    @Column(name = "status", nullable = false)
    private boolean busyAnimalStatus;

    @Column(name = "animal_adopted_date", nullable = false)
    private LocalDate adoptedDate;
}

