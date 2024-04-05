package com.example.petShelter.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * The class consists of logic of the project, which create "Animal Avatar" entity
 *
 * @author Khilola Kushbakova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AnimalAvatar {
    @Id
    @GeneratedValue
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;
    @OneToOne
    private Animals animal;
}
