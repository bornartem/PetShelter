package com.example.petShelter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.geom.Point2D;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shelters")

public class Shelters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelter_id", nullable = false)
    @JoinColumn(name = "shelter_id", referencedColumnName = "shelter_id")
    private long shelterId;

    @Column(name = "shelter_name", nullable = false)
    private String shelterName;

    @Column(name = "shelter_open_hours", nullable = false)
    private String shelterOpenHours;

    @Column(name = "shelter_contacts", nullable = false)
    private String contacts;

    @Column(name = "shelter_address", nullable = false)
    private String address;

    @Column(name = "shelter_location", nullable = false)
    private Point2D.Double location;

    @Column(name = "shelter_security_contacts", nullable = false)
    private String securityContacts;

}
