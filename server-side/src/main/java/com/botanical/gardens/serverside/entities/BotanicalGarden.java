package com.botanical.gardens.serverside.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "botanical_gardens")
public class BotanicalGarden {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Tour> tours;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Attraction> attractions;
}