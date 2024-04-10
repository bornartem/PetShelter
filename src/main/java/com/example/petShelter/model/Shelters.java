package com.example.petShelter.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * The class consists of logic of the project, which create "Shelters" entity
 *
 * @author Khilola Kushbakova
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity(name = "shelter")
public class Shelters {
    @OneToMany(mappedBy = "shelter")
    private List<Animals> animal;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shelter")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "working_hours")
    private String workingHours;
    @Column(name = "contact")
    private String contact;
    @Column(name = "address")
    private String address;
    @Column(name = "location")
    private String location;
    @Column(name = "security_contact")
    private String securityContact;
    @Column(name = "rules")
    private String shelterRules;
}