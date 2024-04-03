package com.example.petShelter.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @Column(name = "operation_mode")
    private String operationMode;
    @Column(name = "contact")
    private String contact;
    @Column(name = "address")
    private String address;
    @Column(name = "drilling_director")
    private String drillingDirector;
    @Column(name = "security_contact")
    private String securityContact;
}