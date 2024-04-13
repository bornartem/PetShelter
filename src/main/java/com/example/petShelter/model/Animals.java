package com.example.petShelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The class consists of logic of the project, which create "Animals" entity
 *
 * @author Khilola Kushbakova
 */
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Animals {


    @ManyToOne
    @JoinColumn(name = "shelters_id")
    @JsonIgnore
    private Shelters shelters;

    //    @OneToMany(mappedBy = "daily_report")
//    @OneToMany(mappedBy = "animal")
//    private List<DailyReports> dailyReport;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "busy_free", nullable = false)
    private Boolean busyFree;
    @Column(name = "date_take")
    private LocalDateTime dateTake;

    public Animals(long id, String name, Boolean busyFree) {
        this.id = id;
        this.name = name;
        this.busyFree = busyFree;
    }

    @Override
    public String toString() {
        return "Animals{" +

                "id=" + id +
                ", name='" + name + '\'' +

                '}';
    }
}