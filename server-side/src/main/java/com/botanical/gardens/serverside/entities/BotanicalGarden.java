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
    @Column(name = "garden_id", nullable = false)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.MERGE)
    @ToString.Exclude
    private List<Tour> tours;

    @ManyToMany
    @JoinTable(
            name = "gardens_attractions",
            joinColumns = @JoinColumn(name = "garden_id"),
            inverseJoinColumns = @JoinColumn(name = "attraction_id")
    )
    private List<Attraction> attractions;
}